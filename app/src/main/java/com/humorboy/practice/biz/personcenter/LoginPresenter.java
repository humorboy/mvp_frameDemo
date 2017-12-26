package com.humorboy.practice.biz.personcenter;

import com.humorboy.practice.bean.home.LoginResp;
import com.humorboy.practice.biz.BasePresenter;
import com.humorboy.practice.bridge.BridgeFactory;
import com.humorboy.practice.bridge.Bridges;
import com.humorboy.practice.bridge.cache.sharePref.EBSharedPrefManager;
import com.humorboy.practice.bridge.cache.sharePref.EBSharedPrefUser;
import com.humorboy.practice.bridge.http.OkHttpManager;
import com.humorboy.practice.bridge.security.SecurityManager;
import com.humorboy.practice.capabilities.http.ITRequestResult;
import com.humorboy.practice.capabilities.http.Param;
import com.humorboy.practice.constant.URLUtil;

/**
 * <功能详细描述>
 *
 * @author caoyinfei
 * @version [版本号, 2016/5/4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginPresenter extends BasePresenter<IUserLoginView> {

    public LoginPresenter() {

    }

    public void login(String useName, String password) {
        //网络层
        mvpView.showLoading();
        SecurityManager securityManager = BridgeFactory.getBridge(Bridges.SECURITY);
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.USER_LOGIN, getName(), new ITRequestResult<LoginResp>() {
                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccessful(LoginResp entity) {
                        mvpView.onSuccess();
                        EBSharedPrefManager manager = BridgeFactory.getBridge(Bridges.SHARED_PREFERENCE);
                        manager.getKDPreferenceUserInfo().saveString(EBSharedPrefUser.USER_NAME, "abc");
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        mvpView.onError(errorMsg, "");
                    }

                }, LoginResp.class, new Param("username", useName),
                new Param("pas", securityManager.get32MD5Str(password)));
    }
}
