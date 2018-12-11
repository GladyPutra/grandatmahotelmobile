package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class LupaPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtpass,txtpass2;
    private Button btn_done,btn_batal;

    private ProgressDialog progressDialog;

    private final String username = SharedPrefManager.getInstance(this).getUserName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        txtpass = (EditText) findViewById(R.id.txtpass);
        txtpass2 = (EditText) findViewById(R.id.txtpass2);

        btn_done = (Button) findViewById(R.id.btn_done);
        btn_batal = (Button) findViewById(R.id.btn_batal);
        progressDialog = new ProgressDialog(this);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
        btn_done.setOnClickListener(this);
        btn_batal.setOnClickListener(this);
    }

    private void gantiPassword()
    {
        final String password = txtpass.getText().toString().trim();
        final String password2 = txtpass2.getText().toString().trim();

        progressDialog.setMessage("Mengganti Kata Sandi ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        if(!password2.isEmpty() && !password.isEmpty()){
                            if(password2.equalsIgnoreCase(password)) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                                txtpass2.setError("Kata Sandi Masih Belum Sama");
                        }
                        else if(password2.isEmpty() && password.isEmpty())
                        {
                            txtpass2.setError("Konfirmasi Kata Sandi Masih Kosong");
                            txtpass.setError("Kata Sandi Masih Kosong");

                        }
                        else if(password2.isEmpty() && !password.isEmpty())
                            txtpass2.setError("Konfrimasi Kata Sandi Masih Kosong");
                        else if(!password2.isEmpty() && password.isEmpty())
                            txtpass.setError("Isi Kata Sandi Baru Terlebih Dahulu");
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
                params.put("password",password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view==btn_done)
            gantiPassword();
        if(view==btn_batal)
            startActivity(new Intent(this,ProfileActivity.class));
    }
}
