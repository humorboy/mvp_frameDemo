package com.humorboy.practice.module.news.view;

import com.humorboy.practice.base.BaseView;
import com.humorboy.practice.bean.NeteastNewsDetail;

/**
 * ClassName: INewsDetailView<p>
 * Author: humorboy<p>
 * Fuction: 新闻详情视图接口<p>
 * CreateDate: 2016/2/19 14:52<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsDetailView extends BaseView {

    void initNewsDetail(NeteastNewsDetail data);

}
