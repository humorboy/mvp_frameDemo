package com.humorboy.practice.module.news.model;


import com.humorboy.practice.callback.RequestCallback;

import rx.Subscription;

/**
 * ClassName: INewsDetailInteractor<p>
 * Author: humorboy<p>
 * Fuction: 新闻详情的Model层接口<p>
 * CreateDate: 2016/2/19 21:02<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsDetailInteractor<T> {

    Subscription requestNewsDetail(RequestCallback<T> callback, String id);

}
