package com.humorboy.practice.module.news.view;


import com.humorboy.practice.base.BaseView;
import com.humorboy.practice.db.NewsChannelTable;

import java.util.List;

/**
 * ClassName: INewsView<p>
 * Author: humorboy<p>
 * Fuction: 新闻视图接口<p>
 * CreateDate: 2016/2/17 20:25<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsView extends BaseView {

    void initViewPager(List<NewsChannelTable> newsChannels);

    void initRxBusEvent();

}
