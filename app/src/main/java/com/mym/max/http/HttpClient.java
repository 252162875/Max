package com.mym.max.http;


import com.mym.max.bean.HomeBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 *
 */
public interface HttpClient {
    String home = "adeasylife/wxMall/ajaxIndex";
    String uploadAvatar = "VolleyTest//servlet/DeviceIDServlet";

    class Builder {
        public static HttpClient getService() {
            return HttpUtils.getInstance().getServer(HttpClient.class);
        }

        public static HttpClient getDouBanService() {
            return HttpUtils.getInstance().getDouBanServer(HttpClient.class);
        }

        public static HttpClient getTingServer() {
            return HttpUtils.getInstance().getTingServer(HttpClient.class);
        }

        public static HttpClient getGankIOServer() {
            return HttpUtils.getInstance().getGankIOServer(HttpClient.class);
        }
    }


    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST(home)
    Observable<HomeBean> getHomeData(@FieldMap Map<String, String> params);

}