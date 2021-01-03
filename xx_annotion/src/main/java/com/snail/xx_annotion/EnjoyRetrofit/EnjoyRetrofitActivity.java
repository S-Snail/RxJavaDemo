package com.snail.xx_annotion.EnjoyRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.snail.xx_annotion.EnjoyRetrofit.api.EnjoyWeatherApi;
import com.snail.xx_annotion.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

public class EnjoyRetrofitActivity extends AppCompatActivity {

    EnjoyWeatherApi weatherApi;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enjoy_retrofit);

        EnjoyRetrofit enjoyRetrofit = new EnjoyRetrofit.Builder().baseUrl("https://restapi.amap.com").build();
        //通过动态代理，获取EnjoyRetrofit的实例
        weatherApi = enjoyRetrofit.create(EnjoyWeatherApi.class);

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
        tv_content = findViewById(R.id.tv_content);
    }

    private void testGet() {
        Call call = weatherApi.getWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log("GET","failed --> " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_content.setText("GET -> failed!" + e.getMessage());
                        Toast.makeText(EnjoyRetrofitActivity.this, "Get failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                log("GET",responseBody);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_content.setText("GET -> success!" + responseBody);
                        Toast.makeText(EnjoyRetrofitActivity.this, "Get success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void testPost() {
        Call call = weatherApi.postWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b");
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                log("POST","failed -> " + e.getMessage());
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       tv_content.setText("POST -> failed!" + e.getMessage());
                       Toast.makeText(EnjoyRetrofitActivity.this, "POST failed", Toast.LENGTH_SHORT).show();
                   }
               });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                log("POST",responseBody);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_content.setText("POST -> success!" + responseBody);
                        Toast.makeText(EnjoyRetrofitActivity.this, "POST Success", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void log(String tag,String msg) {
        Log.d("测试EnjoyRetrofit -- "+ tag + "->", msg);
    }
}