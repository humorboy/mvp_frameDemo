package com.focustech.electronicbrand.ui.personcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.electronicbrand.R;
import com.focustech.electronicbrand.constant.Event;
import com.focustech.electronicbrand.ui.base.BaseActivity;
import com.focustech.electronicbrand.util.DensityUtil;
import com.squareup.picasso.Picasso;

import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity{
    private Button btnback;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
        super.onCreate(savedInstanceState);
    }

    /**
    * 初始化actionbar
    */
    @Override
    public void initViews() {
//        initActionBar();
        image = (ImageView) findViewById(R.id.image);
        btnback = (Button) findViewById(R.id.back);
    }

    private void initActionBar() {
        if(back.getVisibility()==View.VISIBLE){
            back.setVisibility(View.GONE);
        }
    }

    @Override
    public void initListeners() {
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Picasso.with(this).load("http://cms-bucket.nosdn.127.net/91e63b9d964f467d9c1396a92c274b2d20170607120459.jpeg").resize(DensityUtil.dip2px(this,200), DensityUtil.dip2px(this,200)).centerCrop().into(image);
        EventBus.getDefault().post(Event.IMAGE_LOADER_SUCCESS);
    }

    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
        super.onClick(v);
    }

    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("主页");
        initActionBar();
    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
