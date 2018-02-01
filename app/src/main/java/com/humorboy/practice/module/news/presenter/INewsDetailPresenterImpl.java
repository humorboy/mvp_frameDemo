package com.humorboy.practice.module.news.presenter;


import com.humorboy.practice.base.BasePresenterImpl;
import com.humorboy.practice.bean.NeteastNewsDetail;
import com.humorboy.practice.module.news.model.INewsDetailInteractor;
import com.humorboy.practice.module.news.model.INewsDetailInteractorImpl;
import com.humorboy.practice.module.news.view.INewsDetailView;

/**
 * ClassName: INewsDetailPresenterImpl<p>
 * Author: humorboy<p>
 * Fuction: 新闻详情代理接口实现<p>
 * CreateDate: 2016/2/19 21:11<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class INewsDetailPresenterImpl extends BasePresenterImpl<INewsDetailView, NeteastNewsDetail>
        implements INewsDetailPresenter {

    public INewsDetailPresenterImpl(INewsDetailView newsDetailView, String postId) {
        super(newsDetailView);
        INewsDetailInteractor<NeteastNewsDetail> newsDetailInteractor = new INewsDetailInteractorImpl();
        mSubscription = newsDetailInteractor.requestNewsDetail(this, postId);
    }

    @Override
    public void requestSuccess(NeteastNewsDetail data) {
        mView.initNewsDetail(data);
    }

    @Override
    public void requestError(String msg) {
        super.requestError(msg);
        mView.toast(msg);
    }
}
