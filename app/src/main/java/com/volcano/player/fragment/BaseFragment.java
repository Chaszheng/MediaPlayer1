package com.volcano.player.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volcano.player.R;
import com.volcano.player.Util.Utils;
import com.volcano.player.interfaces.UiOpration;

/**
 * Created by admin on 2017/3/13.
 * Fragment的基类,其他Fragment 继承这个类
 */

public  abstract class BaseFragment extends Fragment implements UiOpration {

    private View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(getLayoutResID(),null);
        Utils.setButtonOnClickListener(rootview,this);
        initView();
        initListener();
        initData();
        return rootview;
    }
    /**
     * 查找View,这个方法可以省去强转操作
     * @param id 需要强转的id
     */
    public <T> T findView(int id){
        T view = (T) rootview.findViewById(id);
        return view;
    }
    /**
     * 遍历View 中所有的Button 和 ImageButton 设置监听
     * @param text 显示的文本
     */
    public void showToast(String text){
        Utils.showToast(getActivity(), text);
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
                getActivity().finish();
                break;
            default:
                // 如果单击的不是返回按钮,则还是由子类去完成处理
                onClick(v,v.getId());
                break;
        }
    }
}
