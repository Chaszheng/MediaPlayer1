package com.volcano.player.Util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by admin on 2017/3/13.
 */

public class Utils {
    /**
     * 遍历View 中所有的Button 和 ImageButton 设置监听
     * @param  rootview 遍历的对象
     * @param listener 监听器
     */
    public static void setButtonOnClickListener(View rootview, View.OnClickListener listener) {
        if (rootview instanceof Button || rootview instanceof ImageButton){
            rootview.setOnClickListener(listener);
        }else if (rootview instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) rootview;
            for (int i = 0; i < viewGroup.getChildCount(); i++){
                View child = viewGroup.getChildAt(i);
//              递归
                setButtonOnClickListener(child,listener);
            }
        }
    }
    /**
     * 遍历View 中所有的Button 和 ImageButton 设置监听
     * @param  context 上下文
     * @param text 显示的文本
     */
    public static void showToast(Context context,String text){
        Toast toast = Toast.makeText(context,text,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     *  获取屏幕宽度
     *  @param context 上下文
     */
    public static int getScreenWith(Context context) {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        //noinspection deprecation
        int width = windowManager.getDefaultDisplay().getWidth();
        return width;
    }
    /**
     *  获取屏幕高度
     *  @param context 上下文
     */
    public static int getScreenHight(Context context) {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        //noinspection deprecation
        int height = windowManager.getDefaultDisplay().getHeight();
        return height;
    }
}
