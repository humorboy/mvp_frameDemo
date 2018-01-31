package com.humorboy.practice.module.news.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.humorboy.practice.R;
import com.humorboy.practice.annotation.ActivityFragmentInject;
import com.humorboy.practice.base.BaseFragment;
import com.humorboy.practice.base.BaseRecyclerAdapter;
import com.humorboy.practice.base.BaseRecyclerViewHolder;
import com.humorboy.practice.base.BaseSpacesItemDecoration;
import com.humorboy.practice.bean.NeteastNewsSummary;
import com.humorboy.practice.callback.OnEmptyClickListener;
import com.humorboy.practice.callback.OnItemClickAdapter;
import com.humorboy.practice.callback.OnLoadMoreListener;
import com.humorboy.practice.constant.DataLoadType;
import com.humorboy.practice.module.news.presenter.INewsListPresenter;
import com.humorboy.practice.module.news.presenter.INewsListPresenterImpl;
import com.humorboy.practice.module.news.view.INewsListView;
import com.humorboy.practice.utils.ClickUtils;
import com.humorboy.practice.utils.GlideUtils;
import com.humorboy.practice.utils.MeasureUtil;
import com.humorboy.practice.widget.ThreePointLoadingView;
import com.humorboy.practice.widget.refresh.RefreshLayout;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: NewsListFragment<p>
 * Author: oubowu<p>
 * Fuction: 新闻列表界面<p>
 * CreateDate: 2016/2/17 20:50<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
@ActivityFragmentInject(contentViewId = R.layout.fragment_news_list,
        handleRefreshLayout = true)
public class NewsListFragment extends BaseFragment<INewsListPresenter> implements INewsListView {

    protected static final String NEWS_ID = "news_id";
    protected static final String NEWS_TYPE = "news_type";
    protected static final String POSITION = "position";

    protected String mNewsId;
    protected String mNewsType;

    private BaseRecyclerAdapter<NeteastNewsSummary> mAdapter;
    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private ThreePointLoadingView mLoadingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsId = getArguments().getString(NEWS_ID);
            mNewsType = getArguments().getString(NEWS_TYPE);
            mPosition = getArguments().getInt(POSITION);
        }

    }

    public static NewsListFragment newInstance(String newsId, String newsType, int position) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_ID, newsId);
        bundle.putString(NEWS_TYPE, newsType);
        bundle.putInt(POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View fragmentRootView) {

        mLoadingView = (ThreePointLoadingView) fragmentRootView.findViewById(R.id.tpl_view);
        mLoadingView.setOnClickListener(this);

        mRecyclerView = (RecyclerView) fragmentRootView.findViewById(R.id.recycler_view);

        mRefreshLayout = (RefreshLayout) fragmentRootView.findViewById(R.id.refresh_layout);

        mPresenter = new INewsListPresenterImpl(this, mNewsId, mNewsType);

    }

    @Override
    public void showProgress() {
        mLoadingView.play();
    }

    @Override
    public void hideProgress() {
        mLoadingView.stop();
    }

    @Override
    public void updateNewsList(final List<NeteastNewsSummary> data, String errorMsg, @DataLoadType.DataLoadTypeChecker int type) {

        if (mAdapter == null) {
            initNewsList(data);
        }

        mAdapter.showEmptyView(false, "");

        switch (type) {
            case DataLoadType.TYPE_REFRESH_SUCCESS:
                mRefreshLayout.refreshFinish();
                mAdapter.enableLoadMore(true);
                mAdapter.setData(data);
                break;
            case DataLoadType.TYPE_REFRESH_FAIL:
                mRefreshLayout.refreshFinish();
                mAdapter.enableLoadMore(false);
                mAdapter.showEmptyView(true, errorMsg);
                mAdapter.notifyDataSetChanged();
                break;
            case DataLoadType.TYPE_LOAD_MORE_SUCCESS:
                mAdapter.loadMoreSuccess();
                if (data == null || data.size() == 0) {
                    mAdapter.enableLoadMore(null);
                    toast("全部加载完毕");
                    return;
                }
                mAdapter.addMoreData(data);
                break;
            case DataLoadType.TYPE_LOAD_MORE_FAIL:
                mAdapter.loadMoreFailed(errorMsg);
                break;
        }
    }

    private void initNewsList(final List<NeteastNewsSummary> data) {
        // mAdapter为空肯定为第一次进入状态
        mAdapter = new BaseRecyclerAdapter<NeteastNewsSummary>(getActivity(), data) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_news_summary;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, NeteastNewsSummary item) {
                GlideUtils.loadDefault(item.imgsrc, holder.getImageView(R.id.iv_news_summary_photo), null, null, DiskCacheStrategy.RESULT);
                //                Glide.with(getActivity()).load(item.imgsrc).asBitmap().animate(R.anim.image_load).diskCacheStrategy(DiskCacheStrategy.RESULT)
                //                        .placeholder(R.drawable.ic_loading).error(R.drawable.ic_fail).into(holder.getImageView(R.id.iv_news_summary_photo));
                holder.getTextView(R.id.tv_news_summary_title).setText(item.title);
                holder.getTextView(R.id.tv_news_summary_digest).setText(item.digest);
                holder.getTextView(R.id.tv_news_summary_ptime).setText(item.ptime);
            }
        };

        mAdapter.setOnItemClickListener(new OnItemClickAdapter() {
            @Override
            public void onItemClick(View view, int position) {

                if (ClickUtils.isFastDoubleClick()) {
                    return;
                }

                // imgextra不为空的话，无新闻内容，直接打开图片浏览
                KLog.e(mAdapter.getData().get(position).title + ";" + mAdapter.getData().get(position).postid);

                view = view.findViewById(R.id.iv_news_summary_photo);

                if (mAdapter.getData().get(position).postid == null) {
                    toast("此新闻浏览不了哎╮(╯Д╰)╭");
                    return;
                }

                // 跳转到新闻详情
                if (!TextUtils.isEmpty(mAdapter.getData().get(position).digest)) {
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    intent.putExtra("postid", mAdapter.getData().get(position).postid);
                    intent.putExtra("imgsrc", mAdapter.getData().get(position).imgsrc);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view.findViewById(R.id.iv_news_summary_photo), "photos");
                        getActivity().startActivity(intent, options.toBundle());
                    } else {
                        //让新的Activity从一个小的范围扩大到全屏
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth()/* / 2*/, view.getHeight()/* / 2*/, 0, 0);
                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                    }
                }
            }
        });

        mAdapter.setOnEmptyClickListener(new OnEmptyClickListener() {
            @Override
            public void onEmptyClick() {
                showProgress();
                mPresenter.refreshData();
            }
        });

        mAdapter.setOnLoadMoreListener(10, new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                mPresenter.loadMoreData();
                // mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
            }
        });

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new BaseSpacesItemDecoration(MeasureUtil.dip2px(getActivity(), 4)));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(250);
        mRecyclerView.getItemAnimator().setMoveDuration(250);
        mRecyclerView.getItemAnimator().setChangeDuration(250);
        mRecyclerView.getItemAnimator().setRemoveDuration(250);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefreshing() {
                mPresenter.refreshData();
            }
        });
    }
}
