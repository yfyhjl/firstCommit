package com.storm.imageload.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.storm.imageload.R;
import com.storm.imageloadlib.options.ImgLoaderOptions;

import java.util.ArrayList;
import java.util.List;

public class GlideTranformationsAdapter extends RecyclerView.Adapter<GlideTranformationsAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mData = new ArrayList<>();
    private ImgLoaderOptions options;

    public GlideTranformationsAdapter(Context context) {
        mContext = context;
        for (int i = 1; i <= 24; i++) {
            mData.add(i + "");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(mContext, R.layout.item_glide_tranformations, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 设置名称
        holder.name.setText("POSITION:" + (position + 1));
        int integer = Integer.parseInt(mData.get(position));
        switch (integer) {
//            case 1: {
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .diskCacheStrategy(ImgLoaderOptions.DiskCache.SOURCE)
//                        .animator(R.anim.glide_anim)
//                        .bitmapTransform(new CenterCrop(mContext),
//                                new MaskTransformation(mContext, R.drawable.mask_starfish))
//                        .transform(new GlideRotateTransformation(mContext, 60))
//                        .skipMemoryCache(false)
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            }
//            case 2: {
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new CenterCrop(mContext),
//                                new MaskTransformation(mContext, R.drawable.mask_chat_right))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            }
//            case 3:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new CropTransformation(mContext, 300, 100, CropTransformation.CropType.TOP))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 4:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new CropTransformation(mContext, 300, 100))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 5:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new CropTransformation(mContext, 300, 100, CropTransformation.CropType.BOTTOM))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 6:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new CropSquareTransformation(mContext))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 7:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new CropCircleTransformation(mContext))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 8:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new ColorFilterTransformation(mContext, Color.argb(80, 255, 0, 0)))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 9:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new GrayscaleTransformation(mContext))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 10:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0,
//                                RoundedCornersTransformation.CornerType.BOTTOM))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 11:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new BlurTransformation(mContext, 25))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 12:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .diskCacheStrategy(ImgLoaderOptions.DiskCache.SOURCE)
//                        .bitmapTransform(new ToonFilterTransformation(mContext))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 13:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new ColorFilterTransformation(mContext, Color.argb(80, 255, 0, 0)))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.demo, holder.image, options);
//                break;
//            case 14:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new ContrastFilterTransformation(mContext, 2.0f))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 15:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new InvertFilterTransformation(mContext))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 16:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new PixelationFilterTransformation(mContext, 20))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 17:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new SketchFilterTransformation(mContext))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 18:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(
//                                new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 19:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new BrightnessFilterTransformation(mContext, 0.5f))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 20:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new KuwaharaFilterTransformation(mContext, 25))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 21:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new VignetteFilterTransformation(mContext, new PointF(0.5f, 0.5f),
//                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 22:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new GlideCircleTransform(mContext))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 23:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new GlideRotateTransformation(mContext, 3f))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
//            case 24:
//                options = new ImgLoaderOptions.Builder()
//                        .errorId(R.mipmap.ic_launcher)
//                        .placeholderId(R.mipmap.ic_launcher)
//                        .bitmapTransform(new GlideRoundTransform(mContext, 10))
//                        .build();
//                ImgLoadUtil.showImage(mContext, R.drawable.check, holder.image, options);
//                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_glide_tranfromations);
            name = (TextView) itemView.findViewById(R.id.tv_glide_name);
        }
    }
}
