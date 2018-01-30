package com.humorboy.practice.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.humorboy.practice.R;
import com.humorboy.practice.constant.Event;
import com.humorboy.practice.ui.adapter.ItemListAdapter;
import com.humorboy.practice.ui.base.BaseActivity;
import com.humorboy.practice.view.SearchLayout;

import java.util.ArrayList;
import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity{
    private RecyclerView item_recyclerview;
    private ItemListAdapter mAdapter;
    private ArrayList<HashMap<String,String>> data = new ArrayList<>();
    private SearchLayout searchView;
    private EditText inputEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
        super.onCreate(savedInstanceState);
    }

    /**
    * 初始化actionbar
    */
    @Override
    public void initViews(Bundle bundle) {
        initActionBar();
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
    }

    private void initActionBar() {

    }

    @Override
    public void initListeners() {
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        EventBus.getDefault().post(Event.IMAGE_LOADER_SUCCESS);
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
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
        super.onClick(v);
    }

    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("主页");
        initActionBar();
    }
}
