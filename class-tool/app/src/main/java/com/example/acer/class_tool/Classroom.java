package com.example.acer.class_tool;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Classroom extends AppCompatActivity {

    private Button btn_exit;                     //新增按鈕變數
    private Button btn_choose;                     //新增按鈕變數
    private Button btn_group;                     //新增按鈕變數
    private TextView textView2;                   //新增TextView 變數
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        btn_exit = (Button) findViewById(R.id.btn_exit);                        //找到button 元件
        btn_choose = (Button) findViewById(R.id.btn_choose);                  //找到button 元件
        btn_group = (Button) findViewById(R.id.btn_group);                   //找到button 元件
        textView2 = (TextView) findViewById(R.id.textView2);                   //找到textView 元件


        btn_exit.setOnClickListener(btn_exitListener);                          //監聽button  點擊事件
        btn_choose.setOnClickListener(btn_chooseListener);                    //監聽button  點擊事件
        btn_group.setOnClickListener(btn_groupListener);                     //監聽button  點擊事件

    }



    //監聽button_exit
    private Button.OnClickListener btn_exitListener = new Button.OnClickListener(){

        public void onClick(View v){
            finish();                       //關閉畫面
        }

    };

    //監聽button_choose
    private Button.OnClickListener btn_chooseListener = new Button.OnClickListener(){

        public void onClick(View v){

            // 建立 "選擇檔案 Action" 的 Intent
            Intent intent = new Intent( Intent.ACTION_GET_CONTENT );

            // 過濾檔案格式
            intent.setType( "*/*" );

            // 建立 "檔案選擇器" 的 Intent  (第二個參數: 選擇器的標題)
            Intent destIntent = Intent.createChooser( intent, "選擇檔案" );

            // 切換到檔案選擇器 (它的處理結果, 會觸發 onActivityResult 事件)
            startActivityForResult( destIntent, 0 );

        }

    };


    //監聽button_group
    private Button.OnClickListener btn_groupListener = new Button.OnClickListener(){

        public void onClick(View v){

            try {
                textView2.setText("1111111");
                Workbook workbook = Workbook.getWorkbook(new File("file://storage/sdcard0/FreeMyCard/test.xls"));
                textView2.setText("2222222");
                Sheet sheet = workbook.getSheet("Sheet1");
                textView2.setText(sheet.getCell(0, 1).getContents());
                textView2.setText(sheet.getColumns());
                textView2.setText(sheet.getRows());
                workbook.close();
            } catch (BiffException e) {
                textView2.setText("BiffException");
                e.printStackTrace();
            } catch (IOException e) {
                textView2.setText("IOException");
                e.printStackTrace();
            }
        }

    };


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // 有選擇檔案
        if ( resultCode == RESULT_OK ) {
            // 取得檔案的 Uri
            uri = data.getData();
//            uri = getFilePathFromUri(context, uri);
            if( uri != null ) {

                //  Uri 顯示
                textView2.setText(uri.getPath());

            } else {
                textView2.setText("無效的檔案路徑 !!");
            }
        } else {
            textView2.setText("取消選擇檔案 !!");
        }//end if

    }

    //MediaStore.Images.Media.DATA 跟 MediaStore.Audio.Media.DATA 都是 "_data"
    protected static String getFilePathFromUri(Context context, Uri uri) {
        CursorLoader cursorLoader= new CursorLoader(
                context,
                uri,
                new String[]{ "_data" },
                null,
                null,
                null
        );
        String returnStr = "";
        Cursor cursor = cursorLoader.loadInBackground();
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            returnStr = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
        }
        return returnStr;
    }

}
