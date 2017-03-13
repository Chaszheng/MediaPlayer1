package com.volcano.player.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.volcano.player.R;
import com.volcano.player.Util.Utils;
import com.volcano.player.adapter.MainAdapter;
import com.volcano.player.fragment.AudioFragment;
import com.volcano.player.fragment.VideoFragment;

import java.util.ArrayList;

import static android.support.v4.view.ViewCompat.animate;
import static android.support.v4.view.ViewCompat.setTranslationX;

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
        initIndicator();

    }

    private void initIndicator() {
        int screenWidth = Utils.getScreenWith(this);
        vw_indicator.getLayoutParams().width = screenWidth / 2;
        //通知view去更新它的布局宽度
        vw_indicator.requestLayout();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initListener() {
        tv_audio.setOnClickListener(this);
        tv_video.setOnClickListener(this);
        vw_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //当某一页滚动的时候
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scrollIndicator(position,positionOffset);
            }
            //当某一页被选择的时候
            @Override
            public void onPageSelected(int position) {
                changeTitleTextState(position==0);
            }
            //当某一页滚动状态改变的时候
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /**
     * 滚动指示线
     * @param position 指示线所在的位置
     * @param  positionOffset 指示线所在位置并且所占的百分比
    * */
    private void scrollIndicator(int position, float positionOffset) {
        //TODO 以后要导入其他方法
        //指示线长度
        int width = Utils.getScreenWith(getApplicationContext())/ 2;
        setTranslationX(vw_indicator,width * position + width * positionOffset);
        vw_indicator.requestLayout();
    }

    @Override
    public void onClick(View view, int id) {
        switch (id){
            case R.id.tv_audio:
                vw_pager.setCurrentItem(0);
//                changeTitleTextState(false);
                break;
            case R.id.tv_video:
                vw_pager.setCurrentItem(1);
//                changeTitleTextState(true);
                break;
            default:
                break;
        }
    }
    /**
     * 改变抬头文本的属性
     * @param isSelectVideo
     * ture 文本高亮 变大
     */
    private void changeTitleTextState(boolean isSelectVideo) {
        //改变文本颜色
        tv_video.setSelected(isSelectVideo);
        tv_audio.setSelected(!isSelectVideo);
//        改变文本大小
        float value = isSelectVideo?1.2f:1.0f;
        //TODO  导入其他包
        animate(tv_video).scaleX(value);
        animate(tv_audio).scaleX(value);
     }

    @Override
    public void initData() {
        changeTitleTextState(true);
        initViewPager();
    }
/**
 * 初始化ViewPager
 */
    private void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new VideoFragment());
        fragments.add(new AudioFragment());
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(),fragments);
        vw_pager.setAdapter(adapter);
    }
}
