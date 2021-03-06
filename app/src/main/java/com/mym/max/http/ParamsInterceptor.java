package com.mym.max.http;

import android.util.Log;


import com.mym.max.app.Constants;
import com.mym.max.utils.PrefUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        long t1 = System.nanoTime();
        Request orgRequest = chain.request();
        RequestBody body = orgRequest.body();
        if (body != null) {
            RequestBody newBody = null;
            if (body instanceof FormBody) {
                newBody = addParamsToFormBody((FormBody) body);
            } else if (body instanceof MultipartBody) {
                newBody = addParamsToMultipartBody((MultipartBody) body);
            }
            if (null != newBody) {
                Request newRequest = orgRequest.newBuilder()
                        .url(orgRequest.url())
                        .method(orgRequest.method(), newBody)
                        .build();

                //收集请求参数，方便调试
                String requestLog = String.format("Sending request %s on %s%n%s",
                        newRequest.url(), chain.connection(), newRequest.headers());
                if (newRequest.method().compareToIgnoreCase("post") == 0) {
                    requestLog = requestLog + bodyToString(newRequest);
                }
                //打印请求参数
                Log.e("ParamsInterceptor", "Request: " + requestLog);

                Response response = chain.proceed(newRequest);

                long t2 = System.nanoTime();
                //打印回调
                String s = String.format("Received Response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers());
                Log.e("ParamsInterceptor", s);


                final String responseString = new String(response.body().bytes());
                //打印回调
                Log.e("ParamsInterceptor", "Response: " + responseString);
                return response.newBuilder()
                        .body(ResponseBody.create(response.body().contentType(), responseString))
                        .build();
            }
        }
        return chain.proceed(orgRequest);
    }

    /**
     * 为MultipartBody类型请求体添加参数
     *
     * @param body
     * @return
     */
    private MultipartBody addParamsToMultipartBody(MultipartBody body) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addPart(body.part(i));
        }

        return builder.build();
    }

    /**
     * 为FormBody类型请求体添加参数
     *
     * @param body
     * @return
     */
    private FormBody addParamsToFormBody(FormBody body) {
        FormBody.Builder builder = new FormBody.Builder();
        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
        }

        return builder.build();
    }

    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}