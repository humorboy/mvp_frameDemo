package com.humorboy.practice.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.humorboy.practice.R;
import com.humorboy.practice.biz.personcenter.IUserLoginView;
import com.humorboy.practice.biz.personcenter.LoginPresenter;
import com.humorboy.practice.constant.Event;
import com.humorboy.practice.ui.base.BaseActivity;
import com.humorboy.practice.util.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

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

    private void checkPermission() {
        AndPermission.with(this)
                .requestCode(100)
                .permission(
                        Manifest.permission.CAMERA,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.LOCATION_HARDWARE)
                .callback(listener)
                .start();
    }

    private PermissionListener listener = new PermissionListener() {

        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            Log.d(TAG,"permission check success!");
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            finish();
        }
    };
}
