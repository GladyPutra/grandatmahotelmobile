package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gladyputra.gahmobile.DataDiri;
import com.example.gladyputra.gahmobile.R;

public class ShowDataDiriActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtnama,txtidentitas,txttelpon,txtemail,txtalamat, txtjenis, txttgl;
    private Button btn_daftar,btn_ubah;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_diri);

        txtnama = (TextView) findViewById(R.id.txtharga_kamar);
        txtidentitas = (TextView) findViewById(R.id.txtidentitas);
        txttelpon = (TextView) findViewById(R.id.txttelpon);
        txtemail = (TextView) findViewById(R.id.txtemail);
        txtalamat = (TextView) findViewById(R.id.txtalamat);
        txtjenis = (TextView) findViewById(R.id.txtjenis_kelamin);
        txttgl = (TextView) findViewById(R.id.txttgl_buat);

        progressDialog = new ProgressDialog(this);
        btn_daftar = (Button) findViewById(R.id.btn_daftar);
        btn_ubah = (Button) findViewById(R.id.btn_ubah);

        txtnama.setText(DataDiri.getInstance(this).getNama());
        txtidentitas.setText(DataDiri.getInstance(this).getNoIdentitas());
        txttelpon.setText(DataDiri.getInstance(this).getNoTelepon());
        txtemail.setText(DataDiri.getInstance(this).getEmail());
        txtalamat.setText(DataDiri.getInstance(this).getAlamat());
        txtjenis.setText(DataDiri.getInstance(this).getJenisKelamin());
        txttgl.setText(DataDiri.getInstance(this).getTglBuat());

        btn_daftar.setOnClickListener(this);
        btn_ubah.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btn_daftar) {
            startActivity(new Intent(this,ProfileActivity.class));
        }
        if(view == btn_ubah)
        {
            finish();
            startActivity(new Intent(this,UpdateDataDiriActivity.class));
        }
    }
}
