package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.RequestHandler;
import com.example.gladyputra.gahmobile.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtuser,txtpass,txtpass2;
    private Button btn_register;
    private ProgressDialog progressDialog;
    private Spinner spin_kota;

    private TextView txtlogin,txtkota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);

        if(SharedPrefManager.getInstance(this).isLoggedin())
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
            return;
        }

        txtuser = (EditText) findViewById(R.id.txtharga_kamar);
        txtpass = (EditText) findViewById(R.id.txtpass);
        txtpass2 = (EditText) findViewById(R.id.txtpass2);
        btn_register = (Button) findViewById(R.id.btn_register);

        progressDialog = new ProgressDialog(this);
        spin_kota = (Spinner) findViewById(R.id.spin_kota);

        txtlogin = (TextView) findViewById(R.id.txtlogin);
        txtkota = (TextView) findViewById(R.id.txtkota);

        btn_register.setOnClickListener(this);
        txtlogin.setOnClickListener(this);

        ArrayAdapter<String> listKota = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.kota));
        listKota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_kota.setAdapter(listKota);

        spin_kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1)
                    txtkota.setText("1");
                else if(i==2)
                    txtkota.setText("2");
                else
                    Toast.makeText(getApplicationContext(), "Pilih Kota yang Ada", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void register()
    {
        final String username = txtuser.getText().toString().trim();
        final String password = txtpass.getText().toString().trim();
        final String password2 = txtpass2.getText().toString().trim();
        final String id_kota = (txtkota.getText().toString().trim());

        progressDialog.setMessage("Mendaftarkan Pengguna ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        if(!username.isEmpty() && !password.isEmpty()){
                            if(password2.equalsIgnoreCase(password)) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), Register2Activity.class));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                                txtpass2.setError("Kata Sandi Masih Belum Sama");
                        }
                        else if(username.isEmpty() && password.isEmpty())
                        {
                            txtuser.setError("Nama Pengguna Masih Kosong");
                            txtpass.setError("Kata Sandi Masih Kosong");

                        }
                        else if(username.isEmpty() && !password.isEmpty())
                            txtuser.setError("Nama Pengguna Masih Kosong");
                        else if(!username.isEmpty() && password.isEmpty())
                            txtpass.setError("Kata Sandi Masih Kosong");
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
                params.put("username",username);
                params.put("password",password);
                params.put("id_kota", id_kota);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view==btn_register) {
            register();
        }
        if(view==txtlogin)
            startActivity(new Intent(this,LoginActivity.class));
    }
}