package com.example.gladyputra.gahmobile.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.TableHelper4;
import com.example.gladyputra.gahmobile.mMySQL.MySQLClient4;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class Laporan4Activity extends AppCompatActivity {

    TableView<String[]> tb;
    TableHelper4 tableHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tableHelper=new TableHelper4(this);
        tb=(TableView<String[]>) findViewById(R.id.tableView);
        tb.setColumnCount(3);
        tb.setHeaderBackgroundColor(Color.parseColor("#ffff4444"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this,tableHelper.getLaporan4Header()));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MySQLClient4(Laporan4Activity.this).retrieve(tb);
            }
        });
    }

}
