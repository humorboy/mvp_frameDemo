package com.humorboy.practice.module.weather.model;


import com.humorboy.practice.callback.RequestCallback;

import rx.Subscription;

/**
 * ClassName: IWeathersInteractor<p>
 * Author: humorboy<p>
 * Fuction: 新闻列表Model层接口<p>
 * CreateDate: 2016/2/17 21:02<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface IWeathersInteractor<T> {

    Subscription requestWeatherInfo(RequestCallback<T> callback, String city);

}
