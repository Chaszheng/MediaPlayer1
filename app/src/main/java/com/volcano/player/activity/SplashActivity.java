package com.volcano.player.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.volcano.player.R;


/**
 * Created by admin on 2017/3/13.
 * 创建一个欢迎界面
 */

public class SplashActivity extends BaseActivity {

    private Handler handler;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View view, int id) {

    }

    @Override
    public void initData() {
        delayEnterHome();
    }
    /**
     * 延迟3s 进入首页
     */
    private void delayEnterHome() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enterHome();
            }
        },3000);
    }
    /**
     * 进入首页
     */
    private void enterHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    /**
     * 禁止点击返回
     */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                enterHome();

                handler.removeCallbacksAndMessages(null);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
