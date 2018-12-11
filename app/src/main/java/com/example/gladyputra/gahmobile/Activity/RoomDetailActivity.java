package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gladyputra.gahmobile.Constants;
import com.example.gladyputra.gahmobile.DataKamar;
import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.Room;
import com.example.gladyputra.gahmobile.RoomAdapter;
import com.example.gladyputra.gahmobile.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

public class RoomDetailActivity extends AppCompatActivity {

    ListRoomActivity listRoomActivity;

    int count = 1;

    private ProgressDialog progressDialog;

    private TextView txtid,txtnama,txtstatus,txtharga,txttempat,txtsmoking,txtfasilitas;
    private ImageView imageView00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        txtid = (TextView) findViewById(R.id.txtnama00);
        txtnama = (TextView) findViewById(R.id.txtkamar00);
        txtstatus = (TextView) findViewById(R.id.txtstatus00);
        txtharga = (TextView) findViewById(R.id.txtharga00);
        txttempat = (TextView) findViewById(R.id.txttidur00);
        txtsmoking = (TextView) findViewById(R.id.txtsmoking00);
        txtfasilitas = (TextView) findViewById(R.id.txtfasilitas00);

        imageView00 = (ImageView) findViewById(R.id.imageView00);

        if(count == 1) {
            txtid.setText("1001JS");
            txtnama.setText("Junior Suite");
            txtstatus.setText("TIDAK TERSEDIA");
            txtharga.setText("Rp. 700.000,00");
            txttempat.setText("King");
            txtsmoking.setText("TIDAK");
            txtfasilitas.setText("Layout - ruang duduk terpisah\n" +
                    "Internet - WiFi Gratis\n" +
                    "Hiburan - Televisi LCD dengan channel TV premium channels\n" +
                    "Makan Minum - Pembuat kopi/teh, minibar, layanan kamar 24-jam, air minum kemasan gratis,termasuk sarapan\n" +
                    "Untuk tidur - Seprai kualitas premium dan gorden/tirai kedap cahaya\n" +
                    "Kamar Mandi - Kamar mandi pribadi dengan bathtub dan shower terpisah, jubah mandi, dan sandal\n" +
                    "Kemudahan - Brankas (muat laptop), Meja tulis, dan Telepon; tempat tidur lipat/tambahan tersedia berdasarkan permintaan\n" +
                    "Kenyamanan - AC dan layanan pembenahan kamar harian Merokok/Dilarang Merokok");
            imageView00.setImageResource(R.drawable.junior_suite);
            count++;
        }

        if(count == 2) {
            txtid.setText("1003ED");
            txtnama.setText("Executive Deluxe");
            txtstatus.setText("TIDAK TERSEDIA");
            txtharga.setText("Rp. 650.000,00");
            txttempat.setText("King");
            txtsmoking.setText("TIDAK");
            txtfasilitas.setText("Internet - WiFi Gratis\n" +
                    "Hiburan - Televisi LCD dengan channel TV premium channels\n" +
                    "Makan Minum - Pembuat kopi/teh, minibar, layanan kamar 24-jam, air minum kemasan gratis,termasuk sarapan\n" +
                    "Untuk tidur - Seprai kualitas premium dan gorden/tirai kedap cahaya\n" +
                    "Kamar Mandi - Kamar mandi pribadi dengan shower, jubah mandi, dan sandal\n" +
                    "Kemudahan - Brankas (muat laptop), Meja tulis, dan Telepon; tempat tidur lipat/tambahan tersedia berdasarkan permintaan\n" +
                    "Kenyamanan - AC dan layanan pembenahan kamar harian Merokok/Dilarang Merokok");
            imageView00.setImageResource(R.drawable.executive_deluxe);
            count++;
        }

        if(count == 3) {
            txtid.setText("1013S");
            txtnama.setText("Superior");
            txtstatus.setText("TERSEDIA");
            txtharga.setText("Rp. 550.000,00");
            txttempat.setText("Double");
            txtsmoking.setText("TIDAK");
            txtfasilitas.setText("Internet - WiFi Gratis\n" +
                    "Hiburan - Televisi LCD dengan channel TV premium channels\n" +
                    "Makan Minum - Pembuat kopi/teh, minibar, layanan kamar 24-jam, air minum kemasan gratis,termasuk sarapan\n" +
                    "Untuk tidur - Seprai kualitas premium dan gorden/tirai kedap cahaya\n" +
                    "Kamar Mandi - Kamar mandi pribadi dengan shower, jubah mandi, dan sandal\n" +
                    "Kemudahan - Brankas (muat laptop), Meja tulis, dan Telepon; tempat tidur lipat/tambahan tersedia berdasarkan permintaan\n" +
                    "Kenyamanan - AC dan layanan pembenahan kamar harian Merokok/Dilarang Merokok");
            imageView00.setImageResource(R.drawable.superior);
            count--;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu ...");
    }
}
