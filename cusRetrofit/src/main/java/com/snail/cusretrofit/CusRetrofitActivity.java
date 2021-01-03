package com.snail.cusretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.media.ImageWriter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.snail.cusretrofit.api.IWeatherApi;
import com.snail.cusretrofit.cus_retrofit.CusRetrofit;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CusRetrofitActivity extends AppCompatActivity {

    String baseUrl = "https://restapi.amap.com";
    String city = "110101";
    String key = "ae6c53e2186f33bbf240a12d80672d1b";
    private IWeatherApi iWeatherApi;
    private String TAG = "自定义CusRetrofit";
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_retrofit);
        tv_content = findViewById(R.id.tv_content);
        //todo Builder模式构建CusRetrofit实例
        CusRetrofit cusRetrofit = new CusRetrofit.Builder().baseUrl(baseUrl).build();
        //todo 2 反射+动态代理 获取IWeatherApi的实例
        iWeatherApi = cusRetrofit.create(IWeatherApi.class);
        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPost();
            }
        });

        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGet();
            }
        });
    }

    private void testPost() {
        Call postCall = iWeatherApi.postWeather(city, key);
        postCall.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.d(TAG, "POST请求失败" + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_content.setText("POST -> failed!" + e.getMessage());
                        Toast.makeText(CusRetrofitActivity.this, "POST Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d(TAG, responseBody);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_content.setText("POST -> success!" + responseBody);
                        Toast.makeText(CusRetrofitActivity.this, "POST Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void testGet() {
        Call getCall = iWeatherApi.getWeather(city, key);
        getCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "Get请求失败");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_content.setText("GET -> failed!" + e.getMessage());
                        Toast.makeText(CusRetrofitActivity.this, "GET failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsebBody = response.body().string();
                Log.d(TAG, responsebBody);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_content.setText("GET -> success!" + responsebBody);
                        Toast.makeText(CusRetrofitActivity.this, "GET Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}