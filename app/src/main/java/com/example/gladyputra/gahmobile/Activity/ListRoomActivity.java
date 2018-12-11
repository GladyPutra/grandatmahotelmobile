package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gladyputra.gahmobile.Constants;
import com.example.gladyputra.gahmobile.DataKamar;
import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.RequestHandler;
import com.example.gladyputra.gahmobile.Room;
import com.example.gladyputra.gahmobile.RoomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListRoomActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String GET_ROOMS_URL = "http://192.168.19.140/J_3/API/table/IndexKamar.php/";
    public static final String GET_ROOMS1_URL = "http://192.168.19.140/J_3/API/cariKamar/CariKamar1.php/";
    public static final String GET_ROOMS2_URL = "http://192.168.19.140/J_3/API/cariKamar/CariKamar2.php/";
    public static final String GET_ROOMS3_URL = "http://192.168.19.140/J_3/API/cariKamar/CariKamar3.php/";
    public static final String GET_ROOMS4_URL = "http://192.168.19.140/J_3/API/cariKamar/CariKamar4.php/";

    RecyclerView recyclerView;
    List<Room> roomList;
    RoomAdapter adapter;

    private ProgressDialog progressDialog;

    private EditText txtcari;
    private Button btn_cari,btn_back,btn_cek1,btn_cek2,btn_cek3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_room);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu ...");

        txtcari = (EditText) findViewById(R.id.txtcari);
        btn_cari = (Button) findViewById(R.id.btn_cari);
        btn_back = (Button) findViewById(R.id.btn_kembali);
        btn_cek1 = (Button) findViewById(R.id.btn_cek1);
        btn_cek2 = (Button) findViewById(R.id.btn_cek2);
        btn_cek3 = (Button) findViewById(R.id.btn_cek3);

        roomList = new ArrayList<>();

        loadRooms();

        btn_cari.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_cek1.setOnClickListener(this);
        btn_cek2.setOnClickListener(this);
        btn_cek3.setOnClickListener(this);
    }

    private void loadRooms()
    {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_ROOMS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i = 0;i<array.length();i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                String nama = object.getString("nama_kamar");
                                int img=0;
                                if(nama.equalsIgnoreCase("Superior"))
                                {
                                    img= R.drawable.superior;
                                }
                                if(nama.equalsIgnoreCase("Junior Suite"))
                                {
                                    img= R.drawable.junior_suite;
                                }
                                if(nama.equalsIgnoreCase("Double Deluxe"))
                                {
                                    img= R.drawable.double_deluxe;
                                }
                                if(nama.equalsIgnoreCase("Executive Deluxe"))
                                {
                                    img= R.drawable.executive_deluxe;
                                }

                                Room room = new Room(
                                        img,
                                        object.getString("id_kamar"),
                                        object.getString("nama_kamar"),
                                        object.getString("status_booking"),
                                        object.getLong("harga_kamar"));

                                roomList.add(room);
                            }
                            adapter = new RoomAdapter(roomList,getApplicationContext(),R.layout.list_kamar);
                            recyclerView.setAdapter(adapter);

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Kamar Berhasil Ditampilkan...",
                                    Toast.LENGTH_LONG
                            ).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loadRooms1()
    {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_ROOMS1_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i = 0;i<array.length();i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                String nama = object.getString("nama_kamar");
                                int img=0;
                                if(nama.equalsIgnoreCase("Superior"))
                                {
                                    img= R.drawable.superior;
                                }
                                if(nama.equalsIgnoreCase("Junior Suite"))
                                {
                                    img= R.drawable.junior_suite;
                                }
                                if(nama.equalsIgnoreCase("Double Deluxe"))
                                {
                                    img= R.drawable.double_deluxe;
                                }
                                if(nama.equalsIgnoreCase("Executive Deluxe"))
                                {
                                    img= R.drawable.executive_deluxe;
                                }

                                Room room = new Room(
                                        img,
                                        object.getString("id_kamar"),
                                        object.getString("nama_kamar"),
                                        object.getString("status_booking"),
                                        object.getLong("harga_kamar"));

                                roomList.add(room);
                            }
                            adapter = new RoomAdapter(roomList,getApplicationContext(),R.layout.list_kamar);
                            recyclerView.setAdapter(adapter);

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Kamar Berhasil Ditampilkan...",
                                    Toast.LENGTH_LONG
                            ).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loadRooms2()
    {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_ROOMS2_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i = 0;i<array.length();i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                String nama = object.getString("nama_kamar");
                                int img=0;
                                if(nama.equalsIgnoreCase("Superior"))
                                {
                                    img= R.drawable.superior;
                                }
                                if(nama.equalsIgnoreCase("Junior Suite"))
                                {
                                    img= R.drawable.junior_suite;
                                }
                                if(nama.equalsIgnoreCase("Double Deluxe"))
                                {
                                    img= R.drawable.double_deluxe;
                                }
                                if(nama.equalsIgnoreCase("Executive Deluxe"))
                                {
                                    img= R.drawable.executive_deluxe;
                                }

                                Room room = new Room(
                                        img,
                                        object.getString("id_kamar"),
                                        object.getString("nama_kamar"),
                                        object.getString("status_booking"),
                                        object.getLong("harga_kamar"));

                                roomList.add(room);
                            }
                            adapter = new RoomAdapter(roomList,getApplicationContext(),R.layout.list_kamar);
                            recyclerView.setAdapter(adapter);

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Kamar Berhasil Ditampilkan...",
                                    Toast.LENGTH_LONG
                            ).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loadRooms3()
    {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_ROOMS3_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i = 0;i<array.length();i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                String nama = object.getString("nama_kamar");
                                int img=0;
                                if(nama.equalsIgnoreCase("Superior"))
                                {
                                    img= R.drawable.superior;
                                }
                                if(nama.equalsIgnoreCase("Junior Suite"))
                                {
                                    img= R.drawable.junior_suite;
                                }
                                if(nama.equalsIgnoreCase("Double Deluxe"))
                                {
                                    img= R.drawable.double_deluxe;
                                }
                                if(nama.equalsIgnoreCase("Executive Deluxe"))
                                {
                                    img= R.drawable.executive_deluxe;
                                }

                                Room room = new Room(
                                        img,
                                        object.getString("id_kamar"),
                                        object.getString("nama_kamar"),
                                        object.getString("status_booking"),
                                        object.getLong("harga_kamar"));

                                roomList.add(room);
                            }
                            adapter = new RoomAdapter(roomList,getApplicationContext(),R.layout.list_kamar);
                            recyclerView.setAdapter(adapter);

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Kamar Berhasil Ditampilkan...",
                                    Toast.LENGTH_LONG
                            ).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loadRooms4()
    {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_ROOMS4_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i = 0;i<array.length();i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                String nama = object.getString("nama_kamar");
                                int img=0;
                                if(nama.equalsIgnoreCase("Superior"))
                                {
                                    img= R.drawable.superior;
                                }
                                if(nama.equalsIgnoreCase("Junior Suite"))
                                {
                                    img= R.drawable.junior_suite;
                                }
                                if(nama.equalsIgnoreCase("Double Deluxe"))
                                {
                                    img= R.drawable.double_deluxe;
                                }
                                if(nama.equalsIgnoreCase("Executive Deluxe"))
                                {
                                    img= R.drawable.executive_deluxe;
                                }

                                Room room = new Room(
                                        img,
                                        object.getString("id_kamar"),
                                        object.getString("nama_kamar"),
                                        object.getString("status_booking"),
                                        object.getLong("harga_kamar"));

                                roomList.add(room);
                            }
                            adapter = new RoomAdapter(roomList,getApplicationContext(),R.layout.list_kamar);
                            recyclerView.setAdapter(adapter);

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Kamar Berhasil Ditampilkan...",
                                    Toast.LENGTH_LONG
                            ).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view == btn_cari)
        {
            roomList.clear();
            if(txtcari.getText().toString().equalsIgnoreCase("Superior"))
            {
                loadRooms1();
            }
                else if(txtcari.getText().toString().equalsIgnoreCase("Double Deluxe"))
                {
                    loadRooms2();
                }
                    else if(txtcari.getText().toString().equalsIgnoreCase("Executive Deluxe"))
                    {
                        loadRooms3();
                    }
                        else if(txtcari.getText().toString().equalsIgnoreCase("Junior Suite"))
                        {
                            loadRooms4();
                        }
                            else if(txtcari.getText().toString().isEmpty())
                            {
                                loadRooms();
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Kotak Dialog Masih Kosong...",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                            else
                            {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Nama Kamar yang Anda Tidak Ada...",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
        }
        if(view == btn_back)
        {
            Intent intent = new Intent(ListRoomActivity.this,ProfileActivity.class);
            startActivity(intent);
        }
        if(view == btn_cek1)
        {

            Intent intent = new Intent(ListRoomActivity.this,RoomDetailActivity.class);
            startActivity(intent);
        }
        if(view == btn_cek2)
        {

            Intent intent = new Intent(ListRoomActivity.this,RoomDetailActivity.class);
            startActivity(intent);
        }
        if(view == btn_cek3)
        {

            Intent intent = new Intent(ListRoomActivity.this,RoomDetailActivity.class);
            startActivity(intent);
        }
    }
}
