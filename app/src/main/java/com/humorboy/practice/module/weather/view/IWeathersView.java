package com.humorboy.practice.module.weather.view;

import com.humorboy.practice.base.BaseView;
import com.humorboy.practice.bean.WeatherInfo;

/**
 * ClassName: <p>
 * Author: humorboy<p>
 * Fuction: <p>
 * CreateDate: 2016/2/27 0:17<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface IWeathersView extends BaseView {

    void updateWeatherInfo(WeatherInfo data,String message);

}
