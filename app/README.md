import imageload

1、根目录build.gradle repositories下添加如下：

        maven { url 'https://maven.google.com' }

2、引入imgloadlib Module

2、copy com.storm.imageload.utils.ImgLoadUtil

3、Application onCreate中添加如下代码：

    ImgLoadUtil.setTagId();

4、AndroidManifest.xml中加入： 

    <meta-data
                android:name="com.storm.imageloadlib.CustomGlideModule"
                android:value="GlideModule" />

