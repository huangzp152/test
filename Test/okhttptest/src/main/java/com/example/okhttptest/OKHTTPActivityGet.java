package com.example.okhttptest;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/23.
 */
public class OKHTTPActivityGet {
    OkHttpClient okHttpClient = new OkHttpClient();
    final Request request = new Request.Builder().url("http://www.baidu.com").build();
    Call call = okHttpClient.newCall(request);
    public void getTest() throws IOException {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("header ","fail");
            }

            @Override
            public void onResponse(Response response) throws IOException {
//                request.body().toString();
                Log.d("header ","success");
                Log.d("header ",request.body().toString());
            }
        });
    }
}
