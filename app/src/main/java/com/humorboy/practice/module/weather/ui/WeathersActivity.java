package com.humorboy.practice.module.weather.ui;

import android.content.res.ColorStateList;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.CheckedTextView;

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
import com.humorboy.practice.utils.ViewUtil;
import com.socks.library.KLog;
import com.zhy.changeskin.SkinManager;

import static com.amap.api.services.core.SearchUtils.getSHA1;

@ActivityFragmentInject(contentViewId = R.layout.activity_settings,
        menuId = R.menu.menu_settings,
        hasNavigationView = true,
        toolbarTitle = R.string.settings,
        toolbarIndicator = R.drawable.ic_list_white,
        menuDefaultCheckedItem = R.id.action_settings)
public class WeathersActivity extends BaseActivity<IWeathersPresenter> implements IWeathersView {

    @Override
    protected void initView() {
        mPresenter = new IWeathersPresenterImpl(this, "110101");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ctv_night_mode:

        }
    }

    @Override
    public void updateWeatherInfo(WeatherInfo data, String message) {
        KLog.e("天气预报  data = "+data+" message  = "+message);
        if(data != null){
            KLog.e("天气预报  data = "+data);
        }

        if(message != null){
            KLog.e("天气预报  message = "+message);
        }
    }
}
