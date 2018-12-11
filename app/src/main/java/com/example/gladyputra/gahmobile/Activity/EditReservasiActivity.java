package com.example.gladyputra.gahmobile.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.gladyputra.gahmobile.Constants;
import com.example.gladyputra.gahmobile.DataDiri;
import com.example.gladyputra.gahmobile.DataReservasi;
import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditReservasiActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "InputReservasiActivity";

    private EditText txttgl_menginap,txttgl_selesai,txtjmlh_kamar,txtjmlh_anak,txtjmlh_dewasa,txtid_tarif;
    private TextView txtid_booking;
    private Button button,btn_batal;
    private Spinner spin_tarif;

    private DatePickerDialog.OnDateSetListener mDateSetListener,mDateSetListener2;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reservasi);

        txttgl_menginap = (EditText) findViewById(R.id.txttgl_menginap04);
        txttgl_selesai = (EditText) findViewById(R.id.txttgl_selesai05);
        txtjmlh_kamar = (EditText) findViewById(R.id.txtjmlh_kamar06);
        txtjmlh_anak = (EditText) findViewById(R.id.txtjmlh_anak07);
        txtjmlh_dewasa = (EditText) findViewById(R.id.txtjmlh_dewasa08);
        txtid_tarif = (EditText) findViewById(R.id.txtid_tarif10);
        txtid_booking = (TextView) findViewById(R.id.txtid_booking001);
        spin_tarif = (Spinner) findViewById(R.id.spin_tarif09);

        progressDialog = new ProgressDialog(this);

        button = (Button) findViewById(R.id.btn_reservasi01);
        btn_batal = (Button) findViewById(R.id.btn_batal02);

        txtid_booking.setText(DataReservasi.getInstance(this).getIDBooking());
        txttgl_menginap.setText(DataReservasi.getInstance(this).getTglMenginap());
        txttgl_selesai.setText(DataReservasi.getInstance(this).getTglSelesai());
        txtjmlh_kamar.setText(String.valueOf(DataReservasi.getInstance(this).getJmlhKamar()));
        txtjmlh_anak.setText(String.valueOf(DataReservasi.getInstance(this).getJmlhAnak()));
        txtjmlh_dewasa.setText(String.valueOf(DataReservasi.getInstance(this).getJmlhDewasa()));

        button.setOnClickListener(this);
        btn_batal.setOnClickListener(this);
        txttgl_menginap.setOnClickListener(this);
        txttgl_selesai.setOnClickListener(this);

        ArrayAdapter<String> listTarif = new ArrayAdapter<String>(EditReservasiActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.item));
        listTarif.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_tarif.setAdapter(listTarif);

        spin_tarif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1)
                    txtid_tarif.setText("1");
                else if(i==2)
                    txtid_tarif.setText("2");
                else if(i==3)
                    txtid_tarif.setText("3");
                else if(i==4)
                    txtid_tarif.setText("4");
                else
                    Toast.makeText(getApplicationContext(), "Pilih Item yang Ada", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d(TAG,"onDataSet : dd/mm/yyy;" + day + "/" + month + "/" + year);

                String date = year + "-" + month + "-" + day;
                txttgl_menginap.setText(date);
            }
        };

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d(TAG,"onDataSet : dd/mm/yyy;" + day + "/" + month + "/" + year);

                String date = year + "-" + month + "-" + day;
                txttgl_selesai.setText(date);
            }
        };
    }

    private void reservasi()
    {
        final String id_booking = txtid_booking.getText().toString().trim();
        final String tgl_menginap = txttgl_menginap.getText().toString().trim();
        final String tgl_selesai = txttgl_selesai.getText().toString().trim();
        final String jumlah_kamar = txtjmlh_kamar.getText().toString().trim();
        final String jumlah_anak = txtjmlh_anak.getText().toString().trim();
        final String jumlah_dewasa = txtjmlh_dewasa.getText().toString().trim();
        final String id_tarif = txtid_tarif.getText().toString().trim();

        progressDialog.setMessage("Mengubah Reservasi ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_EDITRESERVASI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        if(!tgl_menginap.isEmpty() && !tgl_selesai.isEmpty() && !jumlah_kamar.isEmpty() && !jumlah_anak.isEmpty() && !jumlah_dewasa.isEmpty()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), ReservasiActivity.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            if(tgl_menginap.isEmpty())
                                txttgl_menginap.setError("Tanggal Menginap Masih Kosong");
                            if(tgl_selesai.isEmpty())
                                txttgl_selesai.setError("Tanggal Selesai Masih Kosong");
                            if(jumlah_kamar.isEmpty())
                                txtjmlh_kamar.setError("jumlah Kamar Masih Kosong");
                            if(jumlah_anak.isEmpty())
                                txtjmlh_anak.setError("Jumlah Anak Masih Kosong");
                            if(jumlah_dewasa.isEmpty())
                                txtjmlh_dewasa.setError("Jumlah Dewasa Masih Kosong");
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
                params.put("id_booking", id_booking);
                params.put("tgl_menginap", tgl_menginap);
                params.put("tgl_selesai", tgl_selesai);
                params.put("jumlah_kamar", jumlah_kamar);
                params.put("jumlah_anak",jumlah_anak);
                params.put("jumlah_dewasa",jumlah_dewasa);
                params.put("id_tarif",id_tarif);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if(view == button)
        {
            reservasi();
        }
        if(view == btn_batal)
        {
            finish();
            startActivity(new Intent(this, ReservasiActivity.class));
        }
        if(view == txttgl_menginap)
        {
            DatePickerDialog dialog = new DatePickerDialog(EditReservasiActivity.this,
                    android.R.style.Theme_Holo_Light_DarkActionBar, mDateSetListener, year,month,day);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }

        if(view == txttgl_selesai)
        {
            DatePickerDialog dialog = new DatePickerDialog(EditReservasiActivity.this,
                    android.R.style.Theme_Holo_Light_DarkActionBar, mDateSetListener2, year,month,day);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
}
