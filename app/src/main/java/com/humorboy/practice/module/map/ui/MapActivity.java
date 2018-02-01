package com.humorboy.practice.module.map.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.humorboy.practice.R;
import com.humorboy.practice.annotation.ActivityFragmentInject;
import com.humorboy.practice.base.BaseActivity;
import com.humorboy.practice.module.map.presenter.IMapPresenter;
import com.humorboy.practice.module.map.view.IMapView;
import com.humorboy.practice.utils.MeasureUtil;

@ActivityFragmentInject(contentViewId = R.layout.activity_map,
        menuId = R.menu.menu_settings,
        toolbarTitle = R.string.map,
        enableSlidr = false)
public class MapActivity extends BaseActivity<IMapPresenter> implements IMapView {
    MapView mMapView = null;
    AMap aMap;
    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    private UiSettings mUiSettings;//定义一个UiSettings对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if(aMap == null){
            aMap = mMapView.getMap();
        }
        aMap.setMyLocationEnabled(true);
        showMyLocation();
    }

    @Override
    protected void initView() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            // 4.4设置全屏并动态修改Toolbar的位置实现类5.0效果，确保布局延伸到状态栏的效果
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
            mlp.topMargin = MeasureUtil.getStatusBarHeight(this);
        }
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    private void showMyLocation() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.mipmap.location_icon));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(STROKE_COLOR);
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(5);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(FILL_COLOR);
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);

        //设置UI
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
//        mUiSettings.setMyLocationButtonEnabled(true); //定位按钮
        mUiSettings.setAllGesturesEnabled(true);
//        mUiSettings.setCompassEnabled(true);  //指北针
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void initMap() {
        //检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
    }
}
