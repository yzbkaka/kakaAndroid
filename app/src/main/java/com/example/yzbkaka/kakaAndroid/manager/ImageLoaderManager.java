package com.example.yzbkaka.kakaAndroid.manager;

/**
 * Created by yzbkaka on 19-12-31.
 */

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yzbkaka.kakaAndroid.R;
import com.example.yzbkaka.kakaAndroid.application.AppContext;
import com.example.yzbkaka.kakaAndroid.common.Const;

/**
 * 图片加载
 */
public class ImageLoaderManager {

    private static RequestOptions normal_image_options = RequestOptions
            .placeholderOf(R.drawable.ic_img_default)
            .error(R.drawable.ic_img_default)
            .centerCrop();

    private static RequestOptions head_options = RequestOptions
            .placeholderOf(R.mipmap.ic_launcher_round)
            .centerCrop();


    public static void displayImage(Object resource, ImageView imageView,int type){
        switch (type){
            case Const.IMAGE_LOADER.HEAD_IMG:  //banner图片
                loadImg(resource,head_options,imageView);
                break;
            case Const.IMAGE_LOADER.NOMAL_IMG:  //文章图片
                loadImg(resource,normal_image_options,imageView);
                break;
            default:
                break;
        }
    }


    private static void loadImg(Object resource,RequestOptions type,ImageView imageView){
        Glide.with(AppContext.getContext()).load(resource).apply(type).into(imageView);
    }
}
