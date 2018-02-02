package com.humorboy.practice.http.service;

import com.humorboy.practice.bean.NeteastNewsDetail;
import com.humorboy.practice.bean.NeteastNewsSummary;
import com.humorboy.practice.bean.WeatherInfo;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * ClassName: NewsService<p>
 * Author: humorboy<p>
 * Fuction: 请求数据服务<p>
 * CreateDate:2016/2/13 20:34<p>
 * UpdateUser:<p>
 * UpdateDate:<p>
 */
public interface NewsService {

    /**
     * 请求新闻列表 例子：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     *
     * @param type      新闻类别：headline为头条,local为北京本地,fangchan为房产,list为其他
     * @param id        新闻类别id
     * @param startPage 开始的页码
     * @return 被观察对象
     */
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NeteastNewsSummary>>> getNewsList(
            @Path("type") String type,
            @Path("id") String id,
            @Path("startPage") int startPage);

    /**
     * 新闻详情：例子：http://c.m.163.com/nc/article/BFNFMVO800034JAU/full.html
     *
     * @param postId 新闻详情的id
     * @return 被观察对象
     */
    @GET("nc/article/{postId}/full.html")
    Observable<Map<String, NeteastNewsDetail>> getNewsDetail(
            @Path("postId") String postId);

    /**
     * 天气情况 例子：http://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=9d668922bd8931b2fbc078fbde09e415
     *
     * @param city 城市名称
     * @return 被观察者
     */
    @GET("weather/weatherInfo{key}/{city}")
    Observable<WeatherInfo> getWeatherInfo(
            @Path("key") String key,
            @Query("city") String city);

}
