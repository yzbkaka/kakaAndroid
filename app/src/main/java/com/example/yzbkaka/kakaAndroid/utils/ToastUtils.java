package com.example.yzbkaka.kakaAndroid.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.yzbkaka.kakaAndroid.R;

/**
 * Created by yzbkaka on 19-12-29.
 */

public class ToastUtils {

    private static String oldMsg;

    protected static Toast toast = null;

    /**
     * 第一次提示时间
     */
    private static long oneTime = 0;

    /**
     * 第二次提示时间
     */
    private static long twoTime = 0;


    public static void showToast(Context context,String string){
        if(toast == null){
            toast = Toast.makeText(context,string,Toast.LENGTH_SHORT);
            toast.show();
            oldMsg = string;
            oneTime = System.currentTimeMillis();
        }else {
            twoTime = System.currentTimeMillis();
            if (string.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = string;
                toast.setText(string);
                toast.show();
            }
        }
        oneTime = twoTime;
    }


    public static void showToast(Context context,int resId){
        showToast(context,context.getString(resId));
    }
}
