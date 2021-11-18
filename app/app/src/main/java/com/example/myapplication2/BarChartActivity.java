package com.example.myapplication2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication2.db.AppDatabase;
import com.example.myapplication2.db.Result;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BarChartActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{

    private AppDatabase db;
    private TextView mDisplayDate;
    private Button mButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);


        db = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();

        mDisplayDate = (TextView) findViewById(R.id.tvDate);


        mButton = findViewById(R.id.showDate);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        showChart(20211101);

    }

        private void showDatePickerDialog(){
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    BarChartActivity.this,
                    this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = year + "년 " + (month+1) + "월";
        mDisplayDate.setText(date);
        num = year*10000+(month+1)*100;
        Log.d("DB", Integer.toString(num));
        showChart(num);
    }

    private void showChart(int num){
        List<Result> resultList = db.HRVDao().getResult(num);

        int a = resultList.size();
        Log.d("DB", Integer.toString(a));

        BarChart chart = findViewById(R.id.barchart);

        chart.getDescription().setEnabled(false);

        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);
        chart.setMaxHighlightDistance(50f);

        // enable scaling and dragging
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);

        chart.setMaxVisibleValueCount(200);
        chart.setPinchZoom(false);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXOffset(6f);

        YAxis yl = chart.getAxisLeft();
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        yl.setAxisMaximum(100f); // this replaces setStartAtZero(true)


        chart.getAxisRight().setEnabled(false);

        XAxis xl = chart.getXAxis();
        //xl.setDrawGridLines(true);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        float f = xl.getAxisMaximum();
        xl.setLabelCount(31);

        ArrayList<BarEntry> values1 = new ArrayList<>();

        int floor = num - (num%100);
        Log.d("DB", Integer.toString(floor));

        int i = 0;
        for(Result result : resultList){
            values1.add(new BarEntry(result.date-floor, Float.parseFloat(result.percent)));
            Log.d("DB", result.percent);
            i++;
            Log.d("DB", Integer.toString(i));
        }

        BarDataSet set1 = new BarDataSet(values1, "스트레스 비율");
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);


        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        chart.animateY(1000);
        BarData data = new BarData(dataSets);
        chart.setData(data);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //stopPlay(); //이 액티비티에서 종료되어야 하는 활동 종료시켜주는 함수
        Intent intent = new Intent(BarChartActivity.this, MainActivity.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동 
        finish();   //현재 액티비티 종료
    }
}

