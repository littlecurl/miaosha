package cn.edu.heuet.quickshop.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import cn.edu.heuet.quickshop.R;
import cn.edu.heuet.quickshop.loader.ModelImageLoader;

public class GuideActivity extends AppCompatActivity {

    Banner banner;
    Button bt_start;
    private List<Integer> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullScreenConfig();

        setContentView(R.layout.activity_guide);

        initUI();

        initBannerData();

        initViews();
    }

    // 全屏显示
    private void fullScreenConfig() {
        // 去除ActionBar(因使用的是NoActivity的主题，故此句有无皆可)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去除状态栏，如 电量、Wifi信号等
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initUI() {
        banner = findViewById(R.id.banner);
        bt_start = findViewById(R.id.bt_start);
    }

    // 初始化banner数据
    private void initBannerData() {
        imageList = new ArrayList<>();
        imageList.add(R.drawable.launcher_01);
        imageList.add(R.drawable.launcher_02);
        imageList.add(R.drawable.launcher_03);
        imageList.add(R.drawable.launcher_04);
    }

    private void initViews() {
        banner.setImageLoader(new ModelImageLoader())
                .setImages(imageList)
                .isAutoPlay(false)
                .start();


        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == imageList.size() - 1) {
                    bt_start.setVisibility(View.VISIBLE);
                    bt_start.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                            finish();
                        }
                    });
                } else {
                    bt_start.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}
