package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gladyputra.gahmobile.DataHistoriReservasi;
import com.example.gladyputra.gahmobile.R;

public class DetilReservasiAllActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtbooking,txttgl,txtnama_kamar,txtstatus;
    private Button btn_done;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_reservasi_all);

        txtbooking = (TextView) findViewById(R.id.txtbooking);
        txttgl = (TextView) findViewById(R.id.txttgl);
        txtnama_kamar = (TextView) findViewById(R.id.txtharga_kamar);
        txtstatus = (TextView) findViewById(R.id.txtstatus);

        btn_done = (Button) findViewById(R.id.btn_done);

        txtbooking.setText(DataHistoriReservasi.getInstance(this).getIDBooking());
        txttgl.setText(DataHistoriReservasi.getInstance(this).getTglReservasi());
        txtnama_kamar.setText(DataHistoriReservasi.getInstance(this).getNamaKamar());
        txtstatus.setText(DataHistoriReservasi.getInstance(this).getStatusBatal());

        progressDialog = new ProgressDialog(this);

        btn_done.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == btn_done) {
            finish();
            startActivity(new Intent(this, DetilReservasiActivity.class));
        }
    }
}
