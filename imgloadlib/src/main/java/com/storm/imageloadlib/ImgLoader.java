package com.storm.imageloadlib;

import android.content.Context;
import android.widget.ImageView;

import com.storm.imageloadlib.listener.ImgLoaderBaseListener;
import com.storm.imageloadlib.listener.ImgLoaderInterface;
import com.storm.imageloadlib.options.ImgLoaderOptions;
import com.storm.imageloadlib.options.ImgLoaderStrategy;

import java.io.File;

/**
 * 单例模式图片加载
 *
 * @param
 * @author zony
 * @time 17-4-27 下午2:11
 */
public class ImgLoader implements ImgLoaderInterface {

	private static volatile ImgLoader instance = null;

	private ImgLoaderInterface imageLoader;

	private ImgLoader() {
		// 默认使用Glide,可替换为其他图片加载框架
		imageLoader = new ImgLoaderStrategy();
	}

	public static ImgLoader getInstance() {
		if (instance == null) {
			synchronized (ImgLoader.class) {
				if (instance == null) {
					instance = new ImgLoader();
				}
			}
		}
		return instance;
	}

	// 可实时替换图片加载框架，默认使用Glide
	public void setImageLoader(ImgLoaderInterface loader) {
		if (loader != null) {
			imageLoader = loader;
		}
	}

	@Override
	public <T> void showImage(T model, ImageView imageView, ImgLoaderOptions options) {
		imageLoader.showImage(model, imageView, options);
	}

	@Override
	public <T> void showImage(T model, ImageView imageView, ImgLoaderOptions options, ImgLoaderBaseListener listener) {
		imageLoader.showImage(model, imageView, options, listener);
	}

	@Override
	public <T> void downloadOnly(Context context, T model, ImgLoaderBaseListener listener) {
		imageLoader.downloadOnly(context, model, listener);
	}

	@Override
	public <T> File getCacheFile(Context context, T imgUrl) {
		return imageLoader.getCacheFile(context, imgUrl);
	}

	@Override
	public void pauseAllTasks(Context context) {
		imageLoader.pauseAllTasks(context);
	}

	@Override
	public void resumeAllTasks(Context context) {
		imageLoader.resumeAllTasks(context);
	}

	@Override
	public void clearDiskCache(Context context) {
		imageLoader.clearDiskCache(context);
	}

	@Override
	public void clearAll(Context context) {
		imageLoader.clearAll(context);
	}

}