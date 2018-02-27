package com.humorboy.practice.module.weather.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.humorboy.practice.R;
import com.humorboy.practice.annotation.ActivityFragmentInject;
import com.humorboy.practice.base.BaseActivity;
import com.humorboy.practice.bean.WeatherInfo;
import com.humorboy.practice.module.news.presenter.INewsListPresenterImpl;
import com.humorboy.practice.module.weather.presenter.IWeathersPresenter;
import com.humorboy.practice.module.weather.presenter.IWeathersPresenterImpl;
import com.humorboy.practice.module.weather.view.IWeathersView;
import com.humorboy.practice.utils.ClickUtils;
import com.humorboy.practice.utils.RxBus;
import com.humorboy.practice.utils.SpUtil;
import com.humorboy.practice.utils.ThemeUtil;
import com.humorboy.practice.utils.ToastUtil;
import com.humorboy.practice.utils.ViewUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.socks.library.KLog;
import com.zhy.changeskin.SkinManager;

import static com.amap.api.services.core.SearchUtils.getSHA1;

@ActivityFragmentInject(contentViewId = R.layout.weather_activity,
        menuId = R.menu.menu_settings,
        hasNavigationView = true,
        toolbarTitle = R.string.weather,
        toolbarIndicator = R.drawable.ic_list_white,
        menuDefaultCheckedItem = R.id.action_weathers)
public class WeathersActivity extends BaseActivity<IWeathersPresenter> implements IWeathersView {
    private TextView city;
    private TextView temp;
    private TextView report_time;
    private TextView humidity;
    private SmartRefreshLayout smartRefreshLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void initView() {
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refresh_layout);
        //设置 Header 为 Material风格
        ClassicsHeader header = new ClassicsHeader(this);
        header.setPrimaryColors(this.getResources().getColor(R.color.primary), Color.WHITE);
        this.smartRefreshLayout.setRefreshHeader(header);
        //下拉刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        city = (TextView) findViewById(R.id.city);
        temp = (TextView) findViewById(R.id.temp);
        report_time = (TextView) findViewById(R.id.report_time);
        humidity  = (TextView) findViewById(R.id.humidity);
        mPresenter = new IWeathersPresenterImpl(this, "110101");
        WeatherInfoFragment weatherInfoFragment = (WeatherInfoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (weatherInfoFragment == null) {
            weatherInfoFragment = WeatherInfoFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, weatherInfoFragment);
            transaction.commit();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_night_mode:
        }
    }

    @Override
    public void updateWeatherInfo(WeatherInfo data, String message) {
        if(data != null){
            KLog.e("天气预报  data = "+data.getLives().get(0).getCity()+data.getLives().get(0).getHumidity()+" message  = "+message);
            city.setText(data.getLives().get(0).getCity());
            temp.setText(data.getLives().get(0).getTemperature()+"°");
            report_time.setText("发布时间:"+data.getLives().get(0).getReporttime().substring(11));
            humidity.setText("湿度:"+data.getLives().get(0).getHumidity());
            collapsingToolbarLayout.setTitle(data.getLives().get(0).getProvince());
            setToolbarTitle(data.getLives().get(0).getProvince());
        }
        KLog.e("天气预报  data = "+data+" message  = "+message);
        if(data != null){
            KLog.e("天气预报  data = "+data);
        }

        if(message != null){
            KLog.e("天气预报  message = "+message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
