package com.humorboy.practice.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.SpriteFactory;
import com.github.ybq.android.spinkit.Style;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.Wave;
import com.humorboy.practice.R;
import com.humorboy.practice.ui.base.BaseActivity;

public class LoadingViewActivity extends BaseActivity {

    private SpinKitView spin_kit_circle;
    private SpinKitView spin_kit_wave;
    private SpinKitView spin_kit_rotatingcircle;
    private SpinKitView spin_kit_threebounce;
    private SpinKitView spin_kit_fadingcircle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_loading_view);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("加载框");
    }

    @Override
    public void initViews(Bundle bundle) {
        spin_kit_circle = (SpinKitView) findViewById(R.id.spin_kit_circle);
        Circle circle = new Circle();
        spin_kit_circle.setIndeterminateDrawable(circle);

        spin_kit_wave = (SpinKitView) findViewById(R.id.spin_kit_wave);
        Wave wave = new Wave();
        spin_kit_wave.setIndeterminateDrawable(wave);

        spin_kit_rotatingcircle = (SpinKitView) findViewById(R.id.spin_kit_rotatingcircle);
        RotatingCircle rotatingcircle = new RotatingCircle();
        spin_kit_rotatingcircle.setIndeterminateDrawable(rotatingcircle);

        spin_kit_threebounce = (SpinKitView) findViewById(R.id.spin_kit_threebounce);
        ThreeBounce threebounce = new ThreeBounce();
        spin_kit_threebounce.setIndeterminateDrawable(threebounce);

        spin_kit_fadingcircle = (SpinKitView) findViewById(R.id.spin_kit_fadingcircle);
        FadingCircle fadingcircle = new FadingCircle();
        spin_kit_fadingcircle.setIndeterminateDrawable(fadingcircle);
    }


    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }
}
