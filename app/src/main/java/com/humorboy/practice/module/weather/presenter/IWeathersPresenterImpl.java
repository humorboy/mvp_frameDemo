package com.humorboy.practice.module.weather.presenter;

import com.humorboy.practice.base.BasePresenterImpl;
import com.humorboy.practice.bean.NeteastNewsSummary;
import com.humorboy.practice.bean.WeatherInfo;
import com.humorboy.practice.callback.RequestCallback;
import com.humorboy.practice.constant.DataLoadType;
import com.humorboy.practice.module.news.model.INewsListInteractorImpl;
import com.humorboy.practice.module.weather.model.IWeathersInteractorImpl;
import com.humorboy.practice.module.weather.view.IWeathersView;

import java.util.List;

/**
 * ClassName: <p>
 * Author: humorboy<p>
 * Fuction: <p>
 * CreateDate: 2016/2/27 0:17<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class IWeathersPresenterImpl extends BasePresenterImpl<IWeathersView, WeatherInfo> implements IWeathersPresenter {
    private IWeathersInteractorImpl mWeathersInteractor;
    private String mCity;
    public IWeathersPresenterImpl(IWeathersView view,String city) {
        super(view);
        mWeathersInteractor = new IWeathersInteractorImpl();
        mSubscription = mWeathersInteractor.requestWeatherInfo(this,city);
        mCity = city;
    }

    @Override
    public void requestError(String e) {
        super.requestError(e);
        mView.updateWeatherInfo(null,e);
    }

    @Override
    public void requestSuccess(WeatherInfo data) {
        if (data != null) {
            mView.updateWeatherInfo(data,null);
        }

    }

}
