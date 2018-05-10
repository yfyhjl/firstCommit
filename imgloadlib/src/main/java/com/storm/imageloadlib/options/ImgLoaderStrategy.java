package com.storm.imageloadlib.options;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.Util;
import com.storm.imageloadlib.listener.ImgLoaderBaseListener;
import com.storm.imageloadlib.listener.ImgLoaderInterface;

import java.io.File;

import static com.bumptech.glide.Glide.with;

/**
 * 图片加载策略（此处使用Glide） Glide 支持优先级处理 与 Activity/Fragment 生命周期一致
 * 多种图片格式的缓存，适用于更多的内容表现形式（如Gif、WebP、缩略图、Video） 支持多种数据源: 网络、本地、资源、Assets 等
 * 生命周期集成（根据Activity或者Fragment的生命周期管理图片加载请求） 高效处理Bitmap: 使用Bitmap
 * Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力.
 * 高效的缓存策略，灵活（Picasso只会缓存原始尺寸的图片，Glide缓存的是多种规格），
 * 加载速度快且内存开销小（默认Bitmap格式采用RGB_565内存使用至少减少一半）
 *
 * @param
 * @author zony
 * @time 17-4-27 下午2:19
 */
public class ImgLoaderStrategy implements ImgLoaderInterface {
	private static final String TAG = "ImgLoaderStrategy";

	private Context mContext;

	@Override
	public <T> void showImage(T model, final ImageView imageView, ImgLoaderOptions options) {
		if (isReturn(imageView)) {
			return;
		}

		// 装配基本的参数
		RequestBuilder<Drawable> dtr = with(mContext).load(model);
		// 装配附加参数
		dtr.apply(loadOptions(dtr, options)).into(imageView);
	}

	@Override
	public <T> void showImage(T model, final ImageView imageView, ImgLoaderOptions options,
							  final ImgLoaderBaseListener listener) {
		if (isReturn(imageView)) {
			return;
		}
		// 装配基本的参数
		RequestBuilder<Drawable> dtr = with(mContext).load(model);

		dtr.apply(loadOptions(dtr, options)).listener(new RequestListener<Drawable>() {

			@Override
			public boolean onLoadFailed(@Nullable GlideException e, Object model,
										Target<Drawable> target, boolean isFirstResource) {
				// for (Exception exception : e.getCauses()) {
				// Log.i(TAG, "ImgLoaderStrategy listener Causes: " + exception.toString());
				// }

				for (Exception exception : e.getRootCauses()) {
					Log.i(TAG, "ImgLoaderStrategy listener RootCauses: " + exception.toString());
					if (listener != null) {
						Log.i(TAG, "ImgLoaderStrategy listener onRequestFailed: "
								+ exception.toString());
						listener.onRequestFailed(exception);
					}
				}
				return false;
			}

			@Override
			public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
										   DataSource dataSource, boolean isFirstResource) {
				Log.i(TAG, "ImgLoaderStrategy listener onResourceReady: " + resource);
				if (listener != null) {
					listener.onRequestSuccess(resource);
				}
				return false;
			}
		});
		// 装配附加参数
		dtr.into(new ImgTarget<Drawable>(imageView, listener));
	}

	@Override
	public <T> void downloadOnly(Context context, T model, final ImgLoaderBaseListener listener) {
		if (context == null || model == null) {
			Log.w(TAG, "ImgLoaderStrategy showImage: model is null !");
			return;
		}
		with(context).download(model).listener(new RequestListener<File>() {
			@Override
			public boolean onLoadFailed(@Nullable GlideException e, Object model,
										Target<File> target, boolean isFirstResource) {
				if (listener != null) {
					listener.onRequestFailed(e);
				}
				return false;
			}

			@Override
			public boolean onResourceReady(File resource, Object model, Target<File> target,
										   DataSource dataSource, boolean isFirstResource) {
				if (listener != null) {
					listener.onLoadSuccess(BitmapFactory.decodeFile(resource.getAbsolutePath()));
				}
				return false;
			}
		}).submit();
	}

	/**
	 * 用来装载由外部设置的参数
	 *
	 * @param
	 * @author zony
	 * @time 17-4-27 下午2:12
	 */
	private RequestOptions loadOptions(RequestBuilder<Drawable> dtr, ImgLoaderOptions options) {
		RequestOptions requestOptions = new RequestOptions();
		if (options == null) {
			return requestOptions;
		}
		if (options.getErrorId() != -1) {
			requestOptions.error(options.getErrorId());
		}

		if (options.getPlaceholderId() != -1) {
			requestOptions.placeholder(options.getPlaceholderId());
		}

		if (options.getCrossDuration() >= 0) {
			dtr.transition(new DrawableTransitionOptions().crossFade(options.getCrossDuration()));
		} else {
			requestOptions.dontAnimate();
		}

		if (options.getOverrideHeight() > 0 && options.getOverrideWidth() > 0) {
			requestOptions.override(options.getOverrideWidth(), options.getOverrideHeight());
		}

		requestOptions.diskCacheStrategy(options.getDiskCacheStrategy().getStrategy());
		requestOptions.priority(options.getPriority().getPriority());
		requestOptions.skipMemoryCache(options.isSkipMemoryCache());
		if (options.bitmapTransform() != null) {
			requestOptions.bitmapTransform(options.bitmapTransform());
		}
		if (options.isCenterCrop()) {
			requestOptions.centerCrop();
		}
		if (options.isFitCenter()) {
			requestOptions.fitCenter();
		}
		if (options.thumbnail() > 0) {
			dtr.thumbnail(options.thumbnail());
		}

		if (options.transform() != null) {
			requestOptions.transform(options.transform());
		}
		return requestOptions;
	}

	/**
	 * 获取图片缓存文件
	 *
	 * @param
	 * @author zony
	 * @time 17-9-20 上午10:38
	 */
	@Override
	public <T> File getCacheFile(Context context, T imgUrl) {

		File cacheFile = null;
		if (context == null) {
			Log.w(TAG, "ImgLoaderStrategy getCacheFile context is null !");
			return cacheFile;
		}
		try {
			cacheFile = DiskLruCacheWrapper.get(Glide.getPhotoCacheDir(context), 250 * 1024 * 1024)
					.get(new OriginalKey((String) imgUrl, EmptySignature.obtain()));

			// 使用下面方法须在子线程中
			// cacheFile = Glide.with(context).downloadOnly().load(imgUrl).submit().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheFile;
	}

	/**
	 * 暂停所有正在下载或等待下载的任务
	 *
	 * @param context Context
	 */
	@Override
	public void pauseAllTasks(Context context) {
		if (context == null) {
			Log.w(TAG, "ImgLoaderStrategy pauseAllTasks context is null !");
			return;
		}
		with(context).pauseRequests();
	}

	/**
	 * 恢复所有任务
	 *
	 * @param context Context
	 */
	@Override
	public void resumeAllTasks(Context context) {
		if (context == null) {
			Log.w(TAG, "ImgLoaderStrategy resumeAllTasks context is null !");
			return;
		}
		with(context).resumeRequests();
	}

	/**
	 * 清除磁盘缓存
	 *
	 * @param context Context
	 */
	@Override
	public void clearDiskCache(final Context context) {
		if (context == null) {
			Log.w(TAG, "ImgLoaderStrategy clearDiskCache context is null !");
			return;
		}
		new Thread() {
			@Override
			public void run() {
				Glide.get(context).clearDiskCache();
			}
		}.start();
	}

	/**
	 * 清除所有缓存
	 *
	 * @param context Context
	 */
	@Override
	public void clearAll(Context context) {
		if (context == null) {
			Log.w(TAG, "ImgLoaderStrategy clearAll context is null !");
			return;
		}
		clearDiskCache(context);
		Glide.get(context).clearMemory();
	}

	/**
	 * 判断异常，是否需要返回
	 *
	 * @param
	 * @author zony
	 * @time 17-12-7 上午9:45
	 */
	private boolean isReturn(ImageView imageView) {
		if (imageView == null) {
			Log.w(TAG, "ImgLoaderStrategy imageView is null !");
			return true;
		}

		mContext = imageView.getContext();

		// 非主线程中使用ApplicationContext
		if (!Util.isOnMainThread()) {
			Log.i(TAG, "getContext: getApplicationContext");
			mContext = mContext.getApplicationContext();
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
				&& ((Activity) mContext).isDestroyed()) {
			Log.w(TAG, "ImgLoaderStrategy activity is destory !");
			return true;
		}
		return false;
	}
}
