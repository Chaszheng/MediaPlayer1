package com.volcano.player.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.volcano.player.R;

import static android.support.v7.appcompat.R.styleable.View;

/**
 * Created by admin on 2017/3/13.
 * activity的基类,其他的activityd都应该继承这个基类
 * 1. 处理共同操作,避免重复操作
 * 2. 代码规范
 * 3. 提供常用的方法和变量,避免代码重复,方便调用
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResID());
//        android.R.id.content 这个可以获取Activity的根view
        View rootview = findViewById(android.R.id.content);
        setButtonOnClickListener(rootview);
        initView();
        initListener();
        initData();
    }
/**
 * 查找View,这个方法可以省去强转操作
 * @param id 需要强转的id
 */
    public <T> T findView(int id){
        T view = (T) findViewById(id);
        return view;
    }

    /**
     * 遍历View 中所有的Button 和 ImageButton 设置监听
     */
    private void setButtonOnClickListener(View rootview) {
        if (rootview instanceof Button || rootview instanceof ImageButton){
            rootview.setOnClickListener(this);
        }else if (rootview instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) rootview;
            for (int i = 0; i < viewGroup.getChildCount(); i++){
                View child = viewGroup.getChildAt(i);
//              递归
                setButtonOnClickListener(child);
            }
        }
    }
    /**
    * 抽取一个Toast用于将提示显示在屏幕正中间
    * @param text 所要设置的文本
    * */
    public void showToast(String text){
        Toast toast =Toast.makeText(this,text,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
    * 监听接口重写的方法
    * 用于判断点击事件是退出还是其他 其他交由子类去处理
    * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            default:
//                如果单击的不是返回按钮,则还是由子类去处理
                onClick(v, v.getId());
                break;
        }
    }

    /**
     * 返回一个用于显示界面的布局id
     * @return 返回查找的id
     */
    public abstract int getLayoutResID();
    /**
    * 初始化View
    * */
    public abstract void initView();
    /**
    * 初始化监听器
    * */
    public abstract void initListener();
    /**
    * 单击事件在这个方法中处理
    * @param view:单击的事件
    * @param id : 单击事件的ID
    * */
    public abstract void onClick(View view,int id);
    /**
    * 初始化数据
    * */
    public abstract void initData();

}
