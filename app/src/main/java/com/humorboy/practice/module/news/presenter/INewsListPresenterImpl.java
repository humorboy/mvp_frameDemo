package com.humorboy.practice.module.news.presenter;

import com.humorboy.practice.base.BasePresenterImpl;
import com.humorboy.practice.bean.NeteastNewsSummary;
import com.humorboy.practice.constant.DataLoadType;
import com.humorboy.practice.module.news.model.INewsListInteractor;
import com.humorboy.practice.module.news.model.INewsListInteractorImpl;
import com.humorboy.practice.module.news.view.INewsListView;

import java.util.List;

/**
 * ClassName: INewsListPresenterImpl<p>
 * Author: humorboy<p>
 * Fuction: 新闻列表代理接口实现<p>
 * CreateDate: 2016/2/18 14:39<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class INewsListPresenterImpl extends BasePresenterImpl<INewsListView, List<NeteastNewsSummary>> implements INewsListPresenter {

    private INewsListInteractor<List<NeteastNewsSummary>> mNewsListInteractor;
    private String mNewsType;
    private String mNewsId;
    private int mStartPage;

    private boolean mIsRefresh = true;
    private boolean mHasInit;

    public INewsListPresenterImpl(INewsListView newsListView, String newsId, String newsType) {
        super(newsListView);
        mNewsListInteractor = new INewsListInteractorImpl();
        mSubscription = mNewsListInteractor.requestNewsList(this, newsType, newsId, mStartPage);
        mNewsType = newsType;
        mNewsId = newsId;
    }

    @Override
    public void beforeRequest() {
        if (!mHasInit) {
            mHasInit = true;
            mView.showProgress();
        }
    }

    @Override
    public void requestError(String e) {
        super.requestError(e);
        mView.updateNewsList(null, e, mIsRefresh ? DataLoadType.TYPE_REFRESH_FAIL : DataLoadType.TYPE_LOAD_MORE_FAIL);
    }

    @Override
    public void requestSuccess(List<NeteastNewsSummary> data) {
        if (data != null) {
            mStartPage += 20;
        }
        mView.updateNewsList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);

    }

    @Override
    public void refreshData() {
        mStartPage = 0;
        mIsRefresh = true;
        mSubscription = mNewsListInteractor.requestNewsList(this, mNewsType, mNewsId, mStartPage);
    }

    @Override
    public void loadMoreData() {
        mIsRefresh = false;
        mSubscription = mNewsListInteractor.requestNewsList(this, mNewsType, mNewsId, mStartPage);
    }

}
