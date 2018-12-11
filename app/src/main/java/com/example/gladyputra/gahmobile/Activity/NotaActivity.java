package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gladyputra.gahmobile.Nota;
import com.example.gladyputra.gahmobile.R;

public class NotaActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtid_booking,txtnama,txtalamat,txttgl_reservasi,txttgl_checkin,txttgl_checkout,txtjmlh_kamar,txtjmlh_anak,txtjmlh_dewasa,
        txtnama_kamar,txttempat_tidur,txtharga_kamar,txtnama_item,txtjmlh_item,txtharga_item,txttotal_tarif;

    private Button btn_done;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        txtid_booking = (TextView) findViewById(R.id.txtid_booking0001);
        txtnama = (TextView) findViewById(R.id.txtnama0002);
        txtalamat = (TextView) findViewById(R.id.txtalamat0003);
        txttgl_reservasi= (TextView) findViewById(R.id.txttgl0004);
        txttgl_checkin = (TextView) findViewById(R.id.txtcheckin0006);
        txttgl_checkout = (TextView) findViewById(R.id.txtcheckout0007);
        txtjmlh_kamar = (TextView) findViewById(R.id.txtjmlh_kamar0008);
        txtjmlh_anak = (TextView) findViewById(R.id.txtjmlh_anak0009);
        txtjmlh_dewasa = (TextView) findViewById(R.id.txtjmlh_dewasa00010);
        txtnama_kamar = (TextView) findViewById(R.id.txtjenis_kamar00011);
        txttempat_tidur = (TextView) findViewById(R.id.txttempat_tidur00012);
        txtharga_kamar = (TextView) findViewById(R.id.txtharga_kamar00013);
        txtnama_item = (TextView) findViewById(R.id.txtlayanan00014);
        txtjmlh_item = (TextView) findViewById(R.id.txtjmlh00015);
        txtharga_item = (TextView) findViewById(R.id.txtharga00016);
        txttotal_tarif = (TextView) findViewById(R.id.txttotal00017);

        btn_done = (Button) findViewById(R.id.btn_done);

        txtid_booking.setText(Nota.getInstance(this).getIDBooking());
        txtnama.setText(Nota.getInstance(this).getNama());
        txtalamat.setText(Nota.getInstance(this).getAlamat());
        txttgl_reservasi.setText(Nota.getInstance(this).getTglReservasi());
        txttgl_checkin.setText(Nota.getInstance(this).getCheckin());
        txttgl_checkout.setText(Nota.getInstance(this).getCheckout());
        txtjmlh_kamar.setText(String.valueOf(Nota.getInstance(this).getJmlhKamar()));
        txtjmlh_anak.setText(String.valueOf(Nota.getInstance(this).getJmlhAnak()));
        txtjmlh_dewasa.setText(String.valueOf(Nota.getInstance(this).getJmlhDewasa()));
        txtnama_kamar.setText(Nota.getInstance(this).getNamaKamar());
        txttempat_tidur.setText(Nota.getInstance(this).getTempatTidur());
        txtharga_kamar.setText(String.valueOf(Nota.getInstance(this).getHargaKamar()));
        txtnama_item.setText(Nota.getInstance(this).getNamaItem());
        txtjmlh_item.setText(String.valueOf(Nota.getInstance(this).getJmlhItem()));
        txtharga_item.setText(String.valueOf(Nota.getInstance(this).getHargaItem()));
        txttotal_tarif.setText(String.valueOf(Nota.getInstance(this).getTotalTarif()));

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
