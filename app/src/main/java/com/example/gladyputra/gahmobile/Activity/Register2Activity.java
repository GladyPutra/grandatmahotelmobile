package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtnama,txtidentitas,txttelpon,txtemail,txtalamat;
    private Button btn_daftar;
    private RadioGroup rad_grup;
    private RadioButton radioButton;
    private ProgressDialog progressDialog;
    private TextView txtlogin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        txtnama = (EditText) findViewById(R.id.txtharga_kamar);
        txtidentitas = (EditText) findViewById(R.id.txtidentitas);
        txttelpon = (EditText) findViewById(R.id.txttelpon);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtalamat = (EditText) findViewById(R.id.txtalamat);
        txtlogin2 = (TextView) findViewById(R.id.txtlogin2);

        progressDialog = new ProgressDialog(this);
        btn_daftar = (Button) findViewById(R.id.btn_daftar);
        rad_grup = (RadioGroup) findViewById(R.id.rad_grup);

        btn_daftar.setOnClickListener(this);
        txtlogin2.setOnClickListener(this);
    }

    private void register()
    {
        int radID = rad_grup.getCheckedRadioButtonId();

        radioButton = findViewById(radID);

        final String nama = txtnama.getText().toString().trim();
        final String no_identitas = txtidentitas.getText().toString().trim();
        final String no_telepon = txttelpon.getText().toString().trim();
        final String email = (txtemail.getText().toString().trim());
        final String alamat = (txtalamat.getText().toString().trim());
        final String jenis_kelamin = (radioButton.getText().toString().trim());

        progressDialog.setMessage("Mendaftarkan Pengguna ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        if (!nama.isEmpty() && !no_identitas.isEmpty() && !no_telepon.isEmpty() && !email.isEmpty() && !alamat.isEmpty()){
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            if (nama.isEmpty()) {
                                txtnama.setError("Nama Lengkap Masih Kosong");
                            }
                            if (no_identitas.isEmpty()) {
                                txtidentitas.setError("Nomor Identitas Masih Kosong");
                            }
                            if (no_telepon.isEmpty()) {
                                txttelpon.setError("Nomor Telpon Masih Kosong");
                            }
                            if (email.isEmpty()) {
                                txtemail.setError("Email Masih Kosong");
                            }
                            if (alamat.isEmpty()) {
                                txtalamat.setError("Alamat Masih Kosong");
                            }
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
                params.put("nama",nama);
                params.put("no_identitas",no_identitas);
                params.put("no_telepon", no_telepon);
                params.put("email", email);
                params.put("alamat", alamat);
                params.put("jenis_kelamin", jenis_kelamin);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view==btn_daftar) {
            register();
            Toast.makeText(getApplicationContext(), "Pendaftaran Berhasil...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(view==txtlogin2)
            startActivity(new Intent(this,LoginActivity.class));
    }

    public void checkButton(View view)
    {
        int radID = rad_grup.getCheckedRadioButtonId();

        radioButton = findViewById(radID);
    }
}
