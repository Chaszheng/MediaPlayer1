package com.volcano.player.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import com.volcano.player.R;
import com.volcano.player.Util.Utils;
import com.volcano.player.interfaces.UiOpration;

/**
 * Created by admin on 2017/3/13.
 * activity的基类,其他的activityd都应该继承这个基类
 * 1. 处理共同操作,避免重复操作
 * 2. 代码规范
 * 3. 提供常用的方法和变量,避免代码重复,方便调用
 */

public abstract class BaseActivity extends FragmentActivity implements UiOpration {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResID());
//        android.R.id.content 这个可以获取Activity的根view
        View rootview = findViewById(android.R.id.content);
        Utils.setButtonOnClickListener(rootview,this);
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
     * @param text 显示的文本
     */
    public void showToast(String text){
        Utils.showToast(this, text);
    }
    /**
     * 实现点击事件
     * 若是退出 则直接退出
     * 若是其他操作 则由子类自己去完成
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:  //处理共同操作 退出
                finish();
                break;
            default:
                // 如果单击的不是返回按钮,则还是由子类去完成处理
                onClick(v,v.getId());
                break;
        }
    }
}
