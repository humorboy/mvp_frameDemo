package com.humorboy.practice.module.map.presenter;

import com.humorboy.practice.base.BasePresenterImpl;
import com.humorboy.practice.module.map.view.IMapView;

/**
 * ClassName: <p>
 * Author: humorboy<p>
 * Fuction: <p>
 * CreateDate: 2016/2/27 0:17<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class IMapPresenterImpl extends BasePresenterImpl<IMapView, Void> implements IMapPresenter {

    public IMapPresenterImpl(IMapView view) {
        super(view);
        mView.initMap();
    }
}
