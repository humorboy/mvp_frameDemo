package com.humorboy.practice.module.news.presenter;


import com.humorboy.practice.base.BasePresenter;

/**
 * ClassName: INewsChannelPresenter<p>
 * Author: humorboy<p>
 * Fuction: 新闻频道管理代理接口<p>
 * CreateDate: 2016/2/20 13:59<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsChannelPresenter extends BasePresenter {

    void onItemAddOrRemove(String channelName, boolean selectState);

    void onItemSwap(int fromPos, int toPos);

}
