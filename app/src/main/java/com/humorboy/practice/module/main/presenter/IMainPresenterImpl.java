package com.humorboy.practice.module.main.presenter;

import com.humorboy.practice.base.BasePresenterImpl;
import com.humorboy.practice.module.main.view.IMainView;

/**
 * ClassName: <p>
 * Author: humorboy<p>
 * Fuction: <p>
 * CreateDate: 2016/2/27 0:17<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class IMainPresenterImpl extends BasePresenterImpl<IMainView, Void> implements IMainPresenter {

    public IMainPresenterImpl(IMainView view) {
        super(view);
        mView.initItemState();
    }
}
