package com.ghj.common.util;

import okhttp3.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author GeHejun
 * @date 2019/6/25 17:15
 */
public class OKHttpUtil {

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new RetryIntercepter(3)).build();

    /**
     * @param url getUrl
     * @return java.lang.String
     * @author xiaobu
     * @date 2019/3/4 11:20
     * @descprition
     * @version 1.0
     */
    public static void get(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        deal(request, callback);
    }



    /**
     * @param url , json
     * @return java.lang.String
     * @author xiaobu
     * @date 2019/3/4 11:13
     * @descprition
     * @version 1.0 post+json方式
     */
    public static void postJson(String url, String json, Callback callback) throws IOException {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        deal(request, callback);
    }



    /**
     * @author xiaobu
     * @date 2019/3/4 15:58
     * @param url , params]
     * @return java.lang.String
     * @descprition  post方式请求
     * @version 1.0
     */
    public static void postMap(String url, Map<String, String> params, Callback callback) {
        StringBuilder content = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            content.append(entry.getKey()).append("=").append(entry.getValue());
            if (iterator.hasNext()) {
                content.append("&");
            }
        }
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_TEXT, content.toString());
        Request request = new Request.Builder().url(url).post(requestBody).build();
        deal(request, callback);
    }

    private static void deal(Request request, Callback callback) {
        client.newCall(request).enqueue(callback);
    }
}
