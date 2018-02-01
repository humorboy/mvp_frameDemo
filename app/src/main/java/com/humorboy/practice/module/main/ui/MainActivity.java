package com.humorboy.practice.module.main.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.humorboy.practice.R;
import com.humorboy.practice.annotation.ActivityFragmentInject;
import com.humorboy.practice.app.AppManager;
import com.humorboy.practice.module.main.presenter.IMainPresenter;
import com.humorboy.practice.module.main.presenter.IMainPresenterImpl;
import com.humorboy.practice.module.main.view.IMainView;
import com.humorboy.practice.module.settings.presenter.ISettingsPresenterImpl;
import com.humorboy.practice.ui.adapter.ItemListAdapter;
import com.humorboy.practice.base.BaseActivity;
import com.humorboy.practice.module.SearchLayout;
import com.humorboy.practice.utils.ViewUtil;

import java.util.ArrayList;
import java.util.HashMap;


@ActivityFragmentInject(contentViewId = R.layout.main_activity,
        menuId = R.menu.menu_news,
        hasNavigationView = true,
        toolbarTitle = R.string.mains,
        toolbarIndicator = R.drawable.ic_list_white,
        menuDefaultCheckedItem = R.id.action_mains)
public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView {
    private RecyclerView item_recyclerview;
    private ItemListAdapter mAdapter;
    private ArrayList<HashMap<String,String>> data = new ArrayList<>();
    private SearchLayout searchView;
    private EditText inputEdittext;

    @Override
    protected void initView(){
        initData();
        item_recyclerview = (RecyclerView) findViewById(R.id.item_recyclerview);
        item_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter  = new ItemListAdapter(this,data);
        item_recyclerview.setAdapter(mAdapter);
        searchView = (SearchLayout) findViewById(R.id.searchView);
        searchView.setOnClickSearch(new SearchLayout.ICallBack() {
            @Override
            public void SearchAciton(String string) {
                mAdapter.setData(data);
            }
        });
        inputEdittext = searchView.getSearch_EditText();

        // 设了默认的windowBackground使得冷启动没那么突兀，这里再设置为空减少过度绘制
//        getWindow().setBackgroundDrawable(null);
        ViewUtil.quitFullScreen(this);
//        AppManager.getAppManager().orderNavActivity(getClass().getName(), false);

        mPresenter = new IMainPresenterImpl(this);
    }

    public void initData() {
        insertItem("线程池");
        insertItem("语音识别");
        insertItem("LBS");
        insertItem("视频");
        insertItem("JNI");
        insertItem("图片");
        insertItem("UI");
        insertItem("动画");
        insertItem("Loading View");
    }

    private void insertItem(String itemText){
        HashMap<String,String> map = new HashMap<>();
        map.put("name",itemText);
        data.add(map);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void initItemState() {

    }
}
