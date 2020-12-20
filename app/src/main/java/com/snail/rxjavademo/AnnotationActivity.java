package com.snail.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.snail.xx_annotion.homework.Autowired;
import com.snail.xx_annotion.homework.IntentExtraInjectUtil;
import com.snail.xx_annotion.inject.InjectView;
import com.snail.xx_annotion.inject.InjectViewUtil;

public class AnnotationActivity extends AppCompatActivity {


    @InjectView(R.id.tv)
    TextView tv;
    @Autowired("name")
    public String name;
    @Autowired("isBoy")
    public boolean isBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
//        try {
//            InjectViewUtil.inject(this);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            Log.d("Inject_detail","inject failed");
//        }
//        tv.setText("注解测试成功");

        name = "Snail";
        isBody = true;
    }

    public void onClick(View view) {
        try {
            Intent intent = IntentExtraInjectUtil.injectActIntent(this);
            intent.setClass(this, AnnotationActivity2.class);
            startActivity(intent);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Toast.makeText(this, "注入异常", Toast.LENGTH_SHORT).show();
        }
    }
}