package com.volcano.player.interfaces;

import android.view.View;

/**
 * Created by admin on 2017/3/13.
 * UI操作接口
 */

public interface UiOpration extends View.OnClickListener {

    /**
     * 返回一个用于显示界面的布局id
     * @return 返回查找的id
     */
    int getLayoutResID();
    /**
     * 初始化View
     * */
    void initView();
    /**
     * 初始化监听器
     * */
    void initListener();
    /**
     * 单击事件在这个方法中处理
     * @param view:单击的事件
     * @param id : 单击事件的ID
     * */
    void onClick(View view, int id);
    /**
     * 初始化数据
     * */
    void initData();
}
