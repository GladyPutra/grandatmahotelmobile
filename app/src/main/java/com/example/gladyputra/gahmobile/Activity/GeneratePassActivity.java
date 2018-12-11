package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class GeneratePassActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtuser;
    private Button btn_done,btn_batal;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pass);

        txtuser = (EditText) findViewById(R.id.txtharga_kamar);
        btn_batal= (Button) findViewById(R.id.btn_batal);
        btn_done = (Button) findViewById(R.id.btn_done);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu ...");

        btn_done.setOnClickListener(this);
        btn_batal.setOnClickListener(this);
    }

    private void genPass()
    {
        final String username = txtuser.getText().toString().trim();
        final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_PASS2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        if(!username.isEmpty()) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                String pass = jsonObject.getString("value");
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                dlgAlert.setMessage(pass);
                                dlgAlert.setTitle("Password Baru Anda");
                                dlgAlert.setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                            }
                                        });
                                dlgAlert.create().show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else if(username.isEmpty())
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
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_batal)
        {
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(view == btn_done)
        {
            genPass();
        }
    }
}
