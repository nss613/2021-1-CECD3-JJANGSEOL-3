package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private Button btn_119;
    private Button btn_help;
    private Button btn_family;
    TextView nowTime;

//    long mNow= System.currentTimeMillis();
//    Date mRedate=new Date(mNow);
//    SimpleDateFormat mFormat= new SimpleDateFormat("yyyy-MM-dd\nHH:mm");
//    String formatDate=mFormat.format(mRedate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nowTime=(TextView) findViewById(R.id.nowTime);
        nowTime.setText(getTime());

//        btn_119=(Button)findViewById(R.id.button);
//        btn_119.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myintent= new Intent(Intent.ACTION_VIEW,Uri.parse("119"));
//                startActivity(myintent);
//            }
//        });



//        btn_119.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent myintent=new Intent(Intent.ACTION_VIEW,Uri.parse("tel:119"));
//                startActivity(myintent);
//
//            }
//        });


//        public void onButtonClicked(View v){
//            Intent myIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("tel:119"));
//            startActivity(myIntent);
//
//        }


    }

    private String getTime() {
        long mNow = System.currentTimeMillis();
        Date mRedate = new Date(mNow);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd\n HH:mm");
        String formatDate = mFormat.format(mRedate);
        return getTime();
    }



}