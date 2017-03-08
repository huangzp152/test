package com.example.okhttptest;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/23.
 */
public class OKHTTPActivityPost {

    //创建okHttpClient对象
    OkHttpClient mOkHttpClient = new OkHttpClient();

    void postTest(){
        FormEncodingBuilder builder = new FormEncodingBuilder().add("username","hzp");
        Request request =  new Request.Builder().url("http://www.baidu.com").post(builder.build()).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("header ","fail");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("header ","success");
            }
        });
    }
}
