package com.focustech.electronicbrand.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.electronicbrand.EBApplication;
import com.focustech.electronicbrand.R;
import com.focustech.electronicbrand.biz.BasePresenter;
import com.focustech.electronicbrand.biz.IMvpView;
import com.focustech.electronicbrand.bridge.BridgeFactory;
import com.focustech.electronicbrand.bridge.Bridges;
import com.focustech.electronicbrand.bridge.http.OkHttpManager;
import com.focustech.electronicbrand.constant.Event;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import de.greenrobot.event.EventBus;

/**
 * <基础activity>
 *
 * @author caoyinfei
 * @version [版本号, 2014-3-24]
 * @see [相关类/方法]
 * @since [V1]
 */
public abstract class BaseActivity extends Activity implements CreateInit, PublishActivityCallBack, PresentationLayerFunc, IMvpView, OnClickListener {

    private PresentationLayerFuncHelper presentationLayerFuncHelper;

    /**
     * 返回按钮
     */
    public LinearLayout back;

    /**
     * 标题，右边字符
     */
    protected TextView title, right;

    public BasePresenter presenter;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.right_in, R.anim.right_out);//activity  切换动画
        super.onCreate(savedInstanceState);
        //状态栏设置
        setTranslucentStatus(true);
        setSystemBarTintDrawable(ContextCompat.getDrawable(this,R.drawable.dwPrimary));
        presentationLayerFuncHelper = new PresentationLayerFuncHelper(this);
        initViews();
        initData();
        setHeader();
        initListeners();
        EBApplication.ebApplication.addActivity(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void setHeader() {
        back = (LinearLayout) findViewById(R.id.ll_back);
        title = (TextView) findViewById(R.id.tv_title);
        right = (TextView) findViewById(R.id.tv_right);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    public void onEventMainThread(Event event) {

    }

    @Override
    protected void onResume() {
        EBApplication.ebApplication.currentActivityName = this.getClass().getName();
        super.onResume();
    }

    @Override
    public void startActivity(Class<?> openClass, Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void openActivityForResult(Class<?> openClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void setResultOk(Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) ;
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showToast(String msg) {
        presentationLayerFuncHelper.showToast(msg);
    }

    @Override
    public void showProgressDialog() {
        presentationLayerFuncHelper.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        presentationLayerFuncHelper.hideProgressDialog();
    }

    @Override
    public void showSoftKeyboard(View focusView) {
        presentationLayerFuncHelper.showSoftKeyboard(focusView);
    }

    @Override
    public void hideSoftKeyboard() {
        presentationLayerFuncHelper.hideSoftKeyboard();
    }

    @Override
    protected void onDestroy() {
        EBApplication.ebApplication.deleteActivity(this);
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView(this);
        }
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.cancelActivityRequest(TAG);
        super.onDestroy();
    }

    /**
     * set status bar translucency
     *
     * @param on
     */
    public void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    /**
     * use SytemBarTintManager
     *
     * @param tintDrawable
     */
    public void setSystemBarTintDrawable(Drawable tintDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            if (tintDrawable != null) {
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setTintDrawable(tintDrawable);
            } else {
                mTintManager.setStatusBarTintEnabled(false);
                mTintManager.setTintDrawable(null);
            }
        }
    }

}
