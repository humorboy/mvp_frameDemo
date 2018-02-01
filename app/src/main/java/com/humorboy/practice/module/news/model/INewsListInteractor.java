package com.humorboy.practice.module.news.model;


import com.humorboy.practice.callback.RequestCallback;

import rx.Subscription;

/**
 * ClassName: INewsListInteractor<p>
 * Author: humorboy<p>
 * Fuction: 新闻列表Model层接口<p>
 * CreateDate: 2016/2/17 21:02<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsListInteractor<T> {

    Subscription requestNewsList(RequestCallback<T> callback, String type, String id, int startPage);

}
