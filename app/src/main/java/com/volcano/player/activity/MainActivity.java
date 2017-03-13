package com.volcano.player.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.TextView;


import com.volcano.player.R;

import static android.support.v4.view.ViewCompat.animate;

public class MainActivity extends BaseActivity {


    private TextView tv_audio;
    private TextView tv_video;
    private View vw_indicator;
    private ViewPager vw_pager;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tv_audio = findView(R.id.tv_audio);
        tv_video = findView(R.id.tv_video);
        vw_indicator = findView(R.id.vw_indicator);
        vw_pager = findView(R.id.vw_pager);

    }

    @Override
    public void initListener() {
        tv_audio.setOnClickListener(this);
        tv_video.setOnClickListener(this);
    }

    @Override
    public void onClick(View view, int id) {
        switch (id){
            case R.id.tv_audio:
                changeTitleTextState(false);
                break;
            case R.id.tv_video:
                changeTitleTextState(true);
                break;
            default:
                break;
        }
    }

    private void changeTitleTextState(boolean isSelectVideo) {
        //改变文本颜色
        tv_video.setSelected(isSelectVideo);
        tv_audio.setSelected(!isSelectVideo);
//        改变文本大小
        float value = isSelectVideo?1.2f:1.0f;
        //TODO  导入其他包
        animate(tv_video).scaleX(value);
     }

    @Override
    public void initData() {
        changeTitleTextState(true);
    }
}
