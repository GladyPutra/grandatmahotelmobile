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
import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.RequestHandler;
import com.example.gladyputra.gahmobile.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtuser,txtpass;
    private Button btn_login;
    private ProgressDialog progressDialog;

    private TextView txtregister;
    private TextView txtlupa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if(SharedPrefManager.getInstance(this).isLoggedin())
        {
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
            return;
        }

        txtuser = (EditText) findViewById(R.id.txtharga_kamar);
        txtpass = (EditText) findViewById(R.id.txtpass);
        btn_login = (Button) findViewById(R.id.btn_login);

        txtregister = (TextView) findViewById(R.id.txtregister);
        txtlupa = (TextView) findViewById(R.id.txtlupa);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu ...");

        btn_login.setOnClickListener(this);
        txtregister.setOnClickListener(this);
        txtlupa.setOnClickListener(this);
    }

    private void userLogin()
    {
        final String username = txtuser.getText().toString().trim();
        final String password = txtpass.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        if(!username.isEmpty() && !password.isEmpty()) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (obj.getBoolean("status")) {
                                    SharedPrefManager.getInstance(getApplicationContext())
                                            .userLogin(
                                                    obj.getInt("id_user"),
                                                    obj.getString("username"),
                                                    obj.getInt("id_role"),
                                                    obj.getInt("id_kota")
                                            );
                                    SharedPrefManager.getInstance(getApplicationContext())
                                            .userRole(
                                                    obj.getInt("id_role"),
                                                    obj.getString("nama_role")
                                            );
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "Berhasil Masuk...",
                                            Toast.LENGTH_LONG
                                    ).show();
                                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                    finish();
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
                        }else if(password.isEmpty() && username.isEmpty())
                        {
                            txtpass.setError("Kata Sandi Masih Kosong");
                            txtuser.setError("Nama Pengguna Masih Kosong");
                        }
                        else if(password.isEmpty() && !username.isEmpty())
                        {
                            txtpass.setError("Kata Sandi Masih Kosong");
                        }
                        else if(!password.isEmpty() && username.isEmpty())
                        {
                            txtuser.setError("Nama Pengguna Masih Kosong");
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
                params.put("username",username);
                params.put("password",password);
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_login)
        {
            userLogin();
        }
        if(view==txtregister)
            startActivity(new Intent(this,RegisterActivity.class));
        if(view == txtlupa)
        {
            startActivity(new Intent(this,GeneratePassActivity.class));
        }
    }
}
