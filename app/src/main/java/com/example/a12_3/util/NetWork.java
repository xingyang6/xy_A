package com.example.a12_3.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Okio;

public class NetWork {
    public static String token;
    public static   String baseurl="http://124.93.196.45:10001";
    public static final OkHttpClient client=new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request.Builder builder=chain.request().newBuilder();
                    if (token!=null){
                        builder.addHeader("Authorization",token);
                    }
                    return chain.proceed(builder.build());
                }
            }).build();

    private static final MediaType JSON=MediaType.Companion.parse("application/json;charset=utf-8");

    public static void doGet(String path,OkResult okResult){
        Request request=new Request.Builder().url(baseurl+path).get().build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }

    public static void doPost(String path,String parm,OkResult okResult){
        Request request=new Request.Builder().url(baseurl+path).post(RequestBody.create(parm,JSON)).build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }
    public static void doPut(String path,String parm,OkResult okResult){
        Request request=new Request.Builder().url(baseurl+path).put(RequestBody.create(parm,JSON)).build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }

    public static void doDelect(String path,OkResult okResult){
        Request request=new Request.Builder().url(baseurl+path).delete().build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }

    public static void doImager(Context context, String path, ImageView imageView){
        Glide.with(context).load(baseurl+ path).into(imageView);

    }

    public static void doAdress(Context context, TextView textView){
        //申明对象
        CityPickerView mpicker=new CityPickerView();
        mpicker.init(context);
        //添加默认配置
        CityConfig cityConfig=new CityConfig.Builder().build();
        mpicker.setConfig(cityConfig);
        mpicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
               textView.setText(province.getName()+city+district);
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(context,"已取消");
            }
        });
        mpicker.showCityPicker();
    }



    private static final Handler HANDLER=new Handler(Looper.getMainLooper());
    static class OkCallback implements Callback{
        private final OkResult okResult;

        OkCallback(OkResult okResult) {
            this.okResult = okResult;
        }

        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            e.printStackTrace();
            HANDLER.post(new Runnable() {
                @Override
                public void run() {

                }
            });
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String string= Objects.requireNonNull(response.body().string());
                HANDLER.post(()->{
                    try {
                        okResult.succes(new JSONObject(string));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
        }
    }
}
