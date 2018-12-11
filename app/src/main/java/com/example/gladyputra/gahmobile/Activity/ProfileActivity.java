package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gladyputra.gahmobile.Constants;
import com.example.gladyputra.gahmobile.DataDiri;
import com.example.gladyputra.gahmobile.DataHistoriReservasi;
import com.example.gladyputra.gahmobile.DataReservasi;
import com.example.gladyputra.gahmobile.Nota;
import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.RequestHandler;
import com.example.gladyputra.gahmobile.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtuser,txtrole;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*
        if(!SharedPrefManager.getInstance(this).isLoggedin())
        {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }*/

        txtuser = (TextView) findViewById(R.id.txtharga_kamar);
        txtrole = (TextView) findViewById(R.id.txtrole);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu ...");

        txtuser.setText(SharedPrefManager.getInstance(this).getUserName());
        txtrole.setText(SharedPrefManager.getInstance(this).getRole());

        if(txtrole.getText().toString().equalsIgnoreCase("General Manager") || txtrole.getText().toString().equalsIgnoreCase("Owner"))
        {
            finish();
            startActivity(new Intent(this, Profile2Activity.class));
        }
    }

    private void showReservasi()
    {
        final String username = txtuser.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_RESERVASI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getBoolean("status")) {
                                DataReservasi.getInstance(getApplicationContext())
                                        .reservasiUser(
                                                obj.getString("id_booking"),
                                                obj.getInt("jumlah_kamar"),
                                                obj.getInt("jumlah_anak"),
                                                obj.getInt("jumlah_dewasa"),
                                                obj.getString("tgl_reservasi"),
                                                obj.getString("id_kamar"),
                                                obj.getString("fasilitas"),
                                                obj.getString("nama_kamar"),
                                                obj.getString("status_batal"),
                                                obj.getLong("harga_kamar"),
                                                obj.getString("tgl_menginap"),
                                                obj.getString("tgl_selesai")
                                        );
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Data Reservasi Berhasil Ditampilkan...",
                                        Toast.LENGTH_LONG
                                ).show();
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),ReservasiActivity.class));
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
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
                params.put("username",username);
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void showHistoriReservasi()
    {
        final String username = txtuser.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_HISTORIRESERVASI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getBoolean("status")) {
                                DataHistoriReservasi.getInstance(getApplicationContext())
                                        .historiReservasiUser(
                                                obj.getString("id_booking"),
                                                obj.getInt("jumlah_kamar"),
                                                obj.getInt("jumlah_anak"),
                                                obj.getInt("jumlah_dewasa"),
                                                obj.getString("tgl_reservasi"),
                                                obj.getString("id_kamar"),
                                                obj.getString("nama_kamar"),
                                                obj.getString("status_batal"),
                                                obj.getLong("harga_kamar"),
                                                obj.getString("tgl_checkin"),
                                                obj.getString("tgl_checkout"),
                                                obj.getString("tgl_transaksi"),
                                                obj.getString("jenis_status"),
                                                obj.getString("nama_item"),
                                                obj.getInt("jumlah_item"),
                                                obj.getLong("harga_item"),
                                                obj.getLong("jumlah_tarif")
                                        );
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Data Histori Reservasi Berhasil Ditampilkan...",
                                        Toast.LENGTH_LONG
                                ).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(),DetilReservasiAllActivity.class));
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
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
                params.put("username",username);
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void showNota()
    {
        final String username = txtuser.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_NOTA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getBoolean("status")) {
                                Nota.getInstance(getApplicationContext())
                                        .notaUser(
                                                obj.getString("id_booking"),
                                                obj.getString("nama"),
                                                obj.getString("alamat"),
                                                obj.getString("tgl_reservasi"),
                                                obj.getString("tgl_checkin"),
                                                obj.getString("tgl_checkout"),
                                                obj.getInt("jumlah_kamar"),
                                                obj.getInt("jumlah_anak"),
                                                obj.getInt("jumlah_dewasa"),
                                                obj.getString("nama_kamar"),
                                                obj.getString("tempat_tidur"),
                                                obj.getLong("harga_kamar"),
                                                obj.getString("nama_item"),
                                                obj.getInt("jumlah_item"),
                                                obj.getLong("harga_tarif"),
                                                obj.getLong("jumlah_tarif")
                                        );
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Nota Berhasil Ditampilkan...",
                                        Toast.LENGTH_LONG
                                ).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(),NotaActivity.class));
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
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
                params.put("username",username);
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void showDataDiri()
    {
        final String username = txtuser.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_DATADIRI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getBoolean("status")) {
                                DataDiri.getInstance(getApplicationContext())
                                        .DataDiriUser(
                                                obj.getInt("id_data_diri"),
                                                obj.getString("nama"),
                                                obj.getString("no_identitas"),
                                                obj.getString("no_telepon"),
                                                obj.getString("email"),
                                                obj.getString("alamat"),
                                                obj.getString("jenis_kelamin"),
                                                obj.getString("tgl_buat")
                                        );
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Data Diri Berhasil Ditampilkan...",
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
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
                params.put("username",username);
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
            {
                case R.id.menuLogout:
                    SharedPrefManager.getInstance(this).logout();
                    finish();
                    startActivity(new Intent(this, LoginActivity.class));
                    break;
                case R.id.menuPassword:
                    finish();
                    startActivity(new Intent(this, LupaPasswordActivity.class));
                    break;
                case R.id.menuKamar:
                    finish();
                    startActivity(new Intent(this,RoomActivity.class));
                    break;
                case R.id.menuReservasi:
                    showReservasi();
                    break;
                case R.id.menuDataDiri:
                    showDataDiri();
                    finish();
                    startActivity(new Intent(getApplicationContext(),ShowDataDiriActivity.class));
                    break;
                case R.id.menuKamar2:
                    finish();
                    startActivity(new Intent(this,ListRoomActivity.class));
                    break;
                case R.id.menuReservasi2:
                    showDataDiri();
                    finish();
                    startActivity(new Intent(this,InputReservasiActivity.class));
                    break;
                case R.id.menuHistoriReservasi:
                    showHistoriReservasi();
                    break;
                case R.id.menuTampilNota:
                    showNota();
//                    startActivity(new Intent(this,NotaActivity.class));
                    break;
            }
        return true;
    }
}
