package com.example.acer.class_tool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Features extends AppCompatActivity {

    private Button btn_enter;                     //新增按鈕變數
    private Button btn_back;                     //新增按鈕變數

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);


        btn_enter = (Button) findViewById(R.id.btn_enter);                  //找到button 元件
        btn_back = (Button) findViewById(R.id.btn_back);                  //找到button 元件

        btn_enter.setOnClickListener(btn_enterListener);                    //監聽button  點擊事件
        btn_back.setOnClickListener(btn_backListener);                    //監聽button  點擊事件

    }//end onCreate

    //監聽button_enter
    private Button.OnClickListener btn_enterListener = new Button.OnClickListener(){

        public void onClick(View v){
            Intent intent = new Intent();
            intent.setClass(Features.this,Classroom.class);
            startActivity(intent);
        }

    };

    //監聽button_back
    private Button.OnClickListener btn_backListener = new Button.OnClickListener(){

        public void onClick(View v){
            finish();                       //關閉畫面

        }

    };

}
