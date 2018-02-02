package com.humorboy.practice.module.weather.model;

import com.humorboy.practice.base.BaseSubscriber;
import com.humorboy.practice.bean.NeteastNewsSummary;
import com.humorboy.practice.bean.WeatherInfo;
import com.humorboy.practice.callback.RequestCallback;
import com.humorboy.practice.http.Api;
import com.humorboy.practice.http.HostType;
import com.humorboy.practice.http.manager.RetrofitManager;
import com.socks.library.KLog;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * ClassName: IWeathersInteractorImpl<p>
 * Author: humorboy<p>
 * Fuction: 新闻列表Model层接口实现<p>
 * CreateDate: 2016/2/17 21:02<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class IWeathersInteractorImpl implements IWeathersInteractor<WeatherInfo> {
    @Override
    public Subscription requestWeatherInfo(RequestCallback<WeatherInfo> callback, String city) {
        return RetrofitManager.getInstance(HostType.WEATHER_INFO)
                .getWeatherInfoObservable(city)
                .subscribe(new BaseSubscriber<>(callback));
    }

//    @Override
//    public Subscription requestWeatherInfo(final RequestCallback<List<WeatherInfo>> callback, String city) {
//        KLog.e("天气预报：" + city );
//        return RetrofitManager.getInstance(HostType.WEATHER_INFO)
//                .getWeatherInfoObservable(city)
//                .flatMap(
//                        new Func1<Map<String, List<NeteastNewsSummary>>, Observable<NeteastNewsSummary>>() {
//                            // map得到list转换成item
//                            @Override
//                            public Observable<NeteastNewsSummary> call(Map<String, List<NeteastNewsSummary>> map) {
//                                if (id.equals(Api.HOUSE_ID)) {
//                                    // 房产实际上针对地区的它的id与返回key不同
//                                    return Observable.from(map.get("北京"));
//                                }
//                                return Observable.from(map.get(id));
//                            }
//                        })
//                .toSortedList(new Func2<NeteastNewsSummary, NeteastNewsSummary, Integer>() {
//                    // 按时间先后排序
//                    @Override
//                    public Integer call(NeteastNewsSummary neteastNewsSummary, NeteastNewsSummary neteastNewsSummary2) {
//                        return neteastNewsSummary2.ptime.compareTo(neteastNewsSummary.ptime);
//                    }
//                }).subscribe(new BaseSubscriber<>(callback));
//    }
}
