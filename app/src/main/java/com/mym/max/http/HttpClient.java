package com.mym.max.http;


import com.mym.max.bean.HomeBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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
    }


    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST(home)
    Observable<HomeBean> getHomeData(@FieldMap Map<String, String> params);
//
//    /**
//     * 上传头像
//     */
//    @Multipart
//    @POST(uploadAvatar)
//    Observable<String> uploadAvatar(@Part("str") String str, @PartMap Map imgs);

}