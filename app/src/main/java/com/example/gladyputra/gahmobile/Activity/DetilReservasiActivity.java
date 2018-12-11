package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gladyputra.gahmobile.DataHistoriReservasi;
import com.example.gladyputra.gahmobile.DataReservasi;
import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.SharedPrefManager;

public class DetilReservasiActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtbooking,txtjmlh_kamar,txtjmlh_anak,txtjmlh_dewasa,txttgl,txtid_kamar,txtnama_kamar,txtstatus,txtharga_kamar2,
            txttgl_checkin,txttgl_checkout,txttgl_transaksi,txtjenis_status,txtnama_item,txtjmlh_item,txtharga_item,txtjmlh_tarif;
    private Button btn_done;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_reservasi);

        txtbooking = (TextView) findViewById(R.id.txtbooking);
        txtjmlh_kamar = (TextView) findViewById(R.id.txtjmlh_kamar);
        txtjmlh_anak = (TextView) findViewById(R.id.txtjmlh_anak);
        txtjmlh_dewasa = (TextView) findViewById(R.id.txtjmlh_dewasa);
        txttgl = (TextView) findViewById(R.id.txttgl);
        txtid_kamar = (TextView) findViewById(R.id.txtid_kamar);
        txtnama_kamar = (TextView) findViewById(R.id.txtharga_kamar);
        txtstatus = (TextView) findViewById(R.id.txtstatus);
        txtharga_kamar2 = (TextView) findViewById(R.id.txtharga_kamar2);
        txttgl_checkin = (TextView) findViewById(R.id.txttgl_checkin);
        txttgl_checkout = (TextView) findViewById(R.id.txttgl_checkout);
        txttgl_transaksi = (TextView) findViewById(R.id.txttgl_transaksi00);
        txtjenis_status = (TextView) findViewById(R.id.txtjenis_status00);
        txtnama_item = (TextView) findViewById(R.id.txtnama_item00);
        txtjmlh_item = (TextView) findViewById(R.id.txtjmlh_item00);
        txtharga_item = (TextView) findViewById(R.id.txtharga_item00);
        txtjmlh_tarif = (TextView) findViewById(R.id.txtjmlh_tarif00);

        btn_done = (Button) findViewById(R.id.btn_done);

        txtbooking.setText(DataHistoriReservasi.getInstance(this).getIDBooking());
        txtjmlh_kamar.setText(String.valueOf(DataHistoriReservasi.getInstance(this).getJmlhKamar()));
        txtjmlh_anak.setText(String.valueOf(DataHistoriReservasi.getInstance(this).getJmlhAnak()));
        txtjmlh_dewasa.setText(String.valueOf(DataHistoriReservasi.getInstance(this).getJmlhDewasa()));
        txttgl.setText(DataHistoriReservasi.getInstance(this).getTglReservasi());
        txtid_kamar.setText(DataHistoriReservasi.getInstance(this).getIDKamar());
        txtnama_kamar.setText(DataHistoriReservasi.getInstance(this).getNamaKamar());
        txtstatus.setText(DataHistoriReservasi.getInstance(this).getStatusBatal());
        txtharga_kamar2.setText(String.valueOf(DataHistoriReservasi.getInstance(this).getHargaKamar()));
        txttgl_checkin.setText(DataHistoriReservasi.getInstance(this).getTglCheckin());
        txttgl_checkout.setText(DataHistoriReservasi.getInstance(this).getTglCheckout());
        txttgl_transaksi.setText(DataHistoriReservasi.getInstance(this).getTglTransaksi());
        txtjenis_status.setText(DataHistoriReservasi.getInstance(this).getJenisStatus());
        txtnama_item.setText(DataHistoriReservasi.getInstance(this).getNamaItem());
        txtjmlh_item.setText(String.valueOf(DataHistoriReservasi.getInstance(this).getJumlahItem()));
        txtharga_item.setText(String.valueOf(DataHistoriReservasi.getInstance(this).getHargaItem()));
        txtjmlh_tarif.setText(String.valueOf(DataHistoriReservasi.getInstance(this).getJumlahTarif()));

        progressDialog = new ProgressDialog(this);

        btn_done.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == btn_done) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}
