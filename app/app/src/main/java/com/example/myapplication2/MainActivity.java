package com.example.myapplication2;

//Projects in Series 3:
//1. Access CSV in an Android Studio Project
//2. Access SQLite in an Android Studio Project
//3. Access WebView in an Android Studio Project
//4. Access NFC in an Android Studio Project
//5. Access Maps in an Android Studio Project


//androidx.appcompat.app.AppCompatActivity and android.os.Bundle are put in by default when basic
//activity selected when new project is created in Android Studio. All of the other imports where
//put in manually later during the making of the project.
//START
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


//END

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Creating Java equivalent objects for the widgets in our activity main user interface which we created
    //in xml that we want to interact with (change) or give values to in some way.
    //START
    private TextView timeTextView;
    private Button btnReadCSV;
    private Button btnShowChart;

    //END

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect our java objects to the appropriate widget in our user interface
        //START
        btnReadCSV = findViewById(R.id.btnReadCSV);
        btnShowChart = findViewById(R.id.btnShowChart);
        timeTextView = findViewById(R.id.txtClock);

        Timer timer = new Timer();

        TimerTask TT = new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M월 d일(E)\na h:mm:ss");
                String dateTime = simpleDateFormat.format(calendar.getTime());
                timeTextView.setText(dateTime);
            }
        };
        timer.schedule(TT,0,1000);
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M월 d일(E)\na h:mm");
//        String dateTime = simpleDateFormat.format(calendar.getTime());
//        timeTextView.setText(dateTime);
//        Log.d("date", dateTime);

        //FINISH

        //This is setting the instructions of what to do when the button "Read CSV File" is pressed. When
        //we put "this" inside the brackets it is telling the system to use the main onClick method
        //for the onClickListener for this button. You only have one onClick that is not nested ie
        //the onClick is inside the setOnClickListener.
        btnReadCSV.setOnClickListener(this);

        btnShowChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), com.example.myapplication2.ReadCSV.class);
                startActivity(intent);
//                Intent intent = new Intent(MainActivity.this, com.example.myapplication2.BarChartActivity.class);
//                startActivity(intent);
            }
        });
    }

    //When our user clicks on Read CSV File our app will launch our ReadCSV activity.
    public void onClick(View v){
//        Intent intent = new Intent(v.getContext(), com.example.myapplication2.ReadCSV.class);
//        startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:119"));
        startActivity(intent);
    }
}