package com.humorboy.practice.bean;

import com.socks.library.KLog;

import java.util.List;

/**
 * ClassName: WeatherInfo<p>
 * Author:humorboy<p>
 * Fuction: 天气查询信息<p>
 * CreateDate:2016/2/14 0:41<p>
 * UpdateUser:<p>
 * UpdateDate:<p>
 */
public class WeatherInfo {


    /**
     * status : 1
     * count : 1
     * info : OK
     * infocode : 10000
     * lives : [{"province":"北京","city":"东城区","adcode":"110101","weather":"晴","temperature":"-2","winddirection":"西北","windpower":"9","humidity":"16","reporttime":"2018-02-02 14:00:00"}]
     */

    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<LivesEntity> lives;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public void setLives(List<LivesEntity> lives) {
        this.lives = lives;
    }

    public String getStatus() {
        return status;
    }

    public String getCount() {
        return count;
    }

    public String getInfo() {
        return info;
    }

    public String getInfocode() {
        return infocode;
    }

    public List<LivesEntity> getLives() {
        return lives;
    }

    public static class LivesEntity {
        /**
         * province : 北京
         * city : 东城区
         * adcode : 110101
         * weather : 晴
         * temperature : -2
         * winddirection : 西北
         * windpower : 9
         * humidity : 16
         * reporttime : 2018-02-02 14:00:00
         */

        private String province;
        private String city;
        private String adcode;
        private String weather;
        private String temperature;
        private String winddirection;
        private String windpower;
        private String humidity;
        private String reporttime;

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public void setWinddirection(String winddirection) {
            this.winddirection = winddirection;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public void setReporttime(String reporttime) {
            this.reporttime = reporttime;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getAdcode() {
            return adcode;
        }

        public String getWeather() {
            return weather;
        }

        public String getTemperature() {
            return temperature;
        }

        public String getWinddirection() {
            return winddirection;
        }

        public String getWindpower() {
            return windpower;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getReporttime() {
            return reporttime;
        }
    }
}
