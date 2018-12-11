package com.example.gladyputra.gahmobile.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gladyputra.gahmobile.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ShowReport4Activity extends AppCompatActivity implements View.OnClickListener {

    BarChart barChart;
    Button btn_done,btn_ekspor,btn_detil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report4);

        barChart = (BarChart) findViewById(R.id.bar1);
        btn_done = (Button) findViewById(R.id.btn_done);
        btn_ekspor = (Button) findViewById(R.id.btn_ekspor);
        btn_detil = (Button) findViewById(R.id.btn_detil);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(true);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0,2f));
        barEntries.add(new BarEntry(1,2f));
        barEntries.add(new BarEntry(2,1f));
        barEntries.add(new BarEntry(3,1f));
        barEntries.add(new BarEntry(4,1f));

        BarDataSet barDataSet = new BarDataSet(barEntries,"Nama Pelanggan");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);

        data.setBarWidth(0.9f);
        barChart.setData(data);


        String[] months = new String[] {"Desi","Johan S.","Kuncoro","Dwi Cahya Sadewa","Joko"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setGranularity(1);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setAxisMinimum(1);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

        btn_done.setOnClickListener(this);
        btn_ekspor.setOnClickListener(this);
        btn_detil.setOnClickListener(this);
    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues;
        public MyAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btn_done)
        {
            startActivity(new Intent(this,ProfileActivity.class));
        }
        if(view == btn_ekspor)
        {
            Toast.makeText(getApplicationContext(), "Ekspor Berhasil...", Toast.LENGTH_LONG).show();
            btn_ekspor.setClickable(false);
            btn_ekspor.setText("Sudah di Ekspor");
        }
        if(view == btn_detil)
        {

            startActivity(new Intent(this,Laporan5Activity.class));
        }
    }
}
