package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gladyputra.gahmobile.Constants;
import com.example.gladyputra.gahmobile.DataReservasi;
import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.RequestHandler;
import com.example.gladyputra.gahmobile.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ReservasiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtbooking,txtjmlh_kamar,txtjmlh_anak,txtjmlh_dewasa,txttgl,txtid_kamar,txtfasilitas,txtnama_kamar,txtstatus,txtharga_kamar2,txttgl_selesai,txttgl_menginap;
    private Button btn_batal,btn_done,btn_edit2;
    private ProgressDialog progressDialog;

    private final String username = SharedPrefManager.getInstance(this).getUserName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasi);

        txtbooking = (TextView) findViewById(R.id.txtbooking);
        txtjmlh_kamar = (TextView) findViewById(R.id.txtjmlh_kamar);
        txtjmlh_anak = (TextView) findViewById(R.id.txtjmlh_anak);
        txtjmlh_dewasa = (TextView) findViewById(R.id.txtjmlh_dewasa);
        txttgl = (TextView) findViewById(R.id.txttgl);
        txtid_kamar = (TextView) findViewById(R.id.txtid_kamar);
        txtfasilitas = (TextView) findViewById(R.id.txtfasilitas);
        txtnama_kamar = (TextView) findViewById(R.id.txtharga_kamar);
        txtstatus = (TextView) findViewById(R.id.txtstatus);
        txtharga_kamar2 = (TextView) findViewById(R.id.txtharga_kamar2);
        txttgl_selesai = (TextView) findViewById(R.id.txttgl_selesai);
        txttgl_menginap = (TextView) findViewById(R.id.txttgl_menginap);

        btn_batal = (Button) findViewById(R.id.btn_batal);
        btn_done = (Button) findViewById(R.id.btn_done);
        btn_edit2 = (Button) findViewById(R.id.btn_edit2);

        txtbooking.setText(DataReservasi.getInstance(this).getIDBooking());
        txtjmlh_kamar.setText(String.valueOf(DataReservasi.getInstance(this).getJmlhKamar()));
        txtjmlh_anak.setText(String.valueOf(DataReservasi.getInstance(this).getJmlhAnak()));
        txtjmlh_dewasa.setText(String.valueOf(DataReservasi.getInstance(this).getJmlhDewasa()));
        txttgl.setText(DataReservasi.getInstance(this).getTglReservasi());
        txtid_kamar.setText(DataReservasi.getInstance(this).getIDKamar());
        txtfasilitas.setText(DataReservasi.getInstance(this).getFasilitas());
        txtnama_kamar.setText(DataReservasi.getInstance(this).getNamaKamar());
        txtstatus.setText(DataReservasi.getInstance(this).getStatusBatal());
        txtharga_kamar2.setText(String.valueOf(DataReservasi.getInstance(this).getHargaKamar()));
        txttgl_selesai.setText(DataReservasi.getInstance(this).getTglSelesai());
        txttgl_menginap.setText(DataReservasi.getInstance(this).getTglMenginap());

        progressDialog = new ProgressDialog(this);

        btn_done.setOnClickListener(this);
        btn_batal.setOnClickListener(this);
        btn_edit2.setOnClickListener(this);
    }

   private void batalReservasi()
    {
        progressDialog.setMessage("Membatalkan Reservasi ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_BATAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username", username);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        if(view == btn_edit2)
        {
            if(txtstatus.getText().toString().equalsIgnoreCase("TIDAK")) {
                finish();
                startActivity(new Intent(this, EditReservasiActivity.class));
            }
            else
            {
                dlgAlert.setMessage("Anda Tidak Bisa Mengubah Reservasi ini.");
                dlgAlert.setTitle("Perhatian !");
                dlgAlert.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(),ReservasiActivity.class));
                            }
                        });
                dlgAlert.create().show();
            }
        }
        if(view == btn_done)
            startActivity(new Intent(this,ProfileActivity.class));
        if(view == btn_batal)
        {
            if(txtstatus.getText().toString().equalsIgnoreCase("TIDAK"))
            {
                dlgAlert.setMessage("Apakah Anda yakin ingin membatalkan reservasi ini ?");
                dlgAlert.setTitle("Perhatian !");
                dlgAlert.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                batalReservasi();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }
                        });
                dlgAlert.setNegativeButton("Tidak",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(new Intent(getApplicationContext(),ReservasiActivity.class));
                            }
                        });
                dlgAlert.create().show();
            }
            else
            {
                dlgAlert.setMessage("Anda Sudah Membatalkan Reservasi ini.");
                dlgAlert.setTitle("Perhatian !");
                dlgAlert.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(),ReservasiActivity.class));
                            }
                        });
                dlgAlert.create().show();
            }
        }
    }
}
