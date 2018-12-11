package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gladyputra.gahmobile.Constants;
import com.example.gladyputra.gahmobile.DataKamar;
import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtnama,txtjmlh,txtharga;
    private Button btn_profil,btn_cari;
    private EditText txtcari;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        txtnama = (TextView) findViewById(R.id.txtnama_kamar);
        txtjmlh = (TextView) findViewById(R.id.txtjmlh);
        txtharga = (TextView) findViewById(R.id.txtharga_kamar);
        txtcari = (EditText) findViewById(R.id.txtcari);

        btn_profil = (Button) findViewById(R.id.btn_profil);
        btn_cari = (Button) findViewById(R.id.btn_cari);

        txtnama.setText("");
        txtjmlh.setText("");
        txtharga.setText("");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu ...");

        btn_profil.setOnClickListener(this);
        btn_cari.setOnClickListener(this);

    }

    public void cariKamar()
    {
        //final String namaKamar = txtnama.getText().toString().trim();
        final String nama_kamar = txtcari.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_CARI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        if(!nama_kamar.isEmpty()) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (obj.getBoolean("status")) {
                                    DataKamar.getInstance(getApplicationContext())
                                            .cariKamar(
                                                    obj.getString("nama_kamar"),
                                                    obj.getInt("jumlah_kamar"),
                                                    obj.getLong("harga_kamar")
                                            );
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "Kamar Berhasil Dicari...",
                                            Toast.LENGTH_LONG
                                    ).show();
                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            obj.getString("message"),
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else if(nama_kamar.isEmpty())
                        {
                            txtcari.setError("Cari Masih Kosong");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nama_kamar",nama_kamar);
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view==btn_profil)
            startActivity(new Intent(this,ProfileActivity.class));
        if(view==btn_cari) {
            cariKamar();

            txtnama.setText(DataKamar.getInstance(this).getNamaKamar());
            txtjmlh.setText(String.valueOf(DataKamar.getInstance(this).getJumlahKamar()));
            txtharga.setText(String.valueOf(DataKamar.getInstance(this).getHargaKamar()));
        }
    }
}
