package com.storm.imageloadlib.options;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 自定义 GlideModule
 */
@GlideModule
public class CustomGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        String cacheDir = "imageCache";
        // 250M Disk cache
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, cacheDir, 250 * 1024 * 1024));
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheDir, 250 * 1024 * 1024));

        // Apply options to the builder here.
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(memoryCacheSize));
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

// Glide 4.0
//        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
//                .setMemoryCacheScreens(2)
//                .build();
//        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    /**
     * 禁止解析Manifest文件
     * 主要针对V3升级到v4的用户，可以提升初始化速度，避免一些潜在错误。
     *
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}