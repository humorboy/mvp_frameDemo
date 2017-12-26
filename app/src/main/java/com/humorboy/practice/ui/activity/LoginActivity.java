package com.humorboy.practice.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;

import com.humorboy.practice.R;
import com.humorboy.practice.biz.personcenter.IUserLoginView;
import com.humorboy.practice.biz.personcenter.LoginPresenter;
import com.humorboy.practice.constant.Event;
import com.humorboy.practice.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity implements IUserLoginView {
    private TextInputEditText userName;
    private TextInputEditText password;
    private Button login;

    private LoginPresenter mUserLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        presenter = mUserLoginPresenter = new LoginPresenter();
        mUserLoginPresenter.attachView(this);
    }

    @Override
    public void initViews() {
        userName = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.passowrd);
        login = (Button) findViewById(R.id.login);
    }

    private void initActionBar() {
        if(back.getVisibility()==View.VISIBLE){
            back.setVisibility(View.GONE);
        }
    }

    @Override
    public void initListeners() {
        login.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setHeader() {
        super.setHeader();
        title.setText("登录");
        initActionBar();
    }

    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
        switch (event){
            case IMAGE_LOADER_SUCCESS:
                clearEditContent();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
//                mUserLoginPresenter.login(userName.getText().toString(), password.getText().toString());
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
        super.onClick(v);
    }

    @Override
    public void clearEditContent() {
        userName.setText("");
        password.setText("");
    }

    @Override
    public void onError(String errorMsg, String code) {
        showToast(errorMsg);
    }

    @Override
    public void onSuccess() {
        startActivity(MainActivity.class,null);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
