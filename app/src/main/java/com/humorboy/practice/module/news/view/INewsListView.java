package com.humorboy.practice.module.news.view;

import android.support.annotation.NonNull;


import com.humorboy.practice.base.BaseView;
import com.humorboy.practice.bean.NeteastNewsSummary;
import com.humorboy.practice.constant.DataLoadType;

import java.util.List;

/**
 * ClassName: INewsListView<p>
 * Author: humorboy<p>
 * Fuction: 新闻列表视图接口<p>
 * CreateDate: 2016/2/18 14:42<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsListView extends BaseView {

    void updateNewsList(List<NeteastNewsSummary> data, @NonNull String errorMsg, @DataLoadType.DataLoadTypeChecker int type);

}
