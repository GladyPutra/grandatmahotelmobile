package com.example.gladyputra.gahmobile.mMySQL;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.gladyputra.gahmobile.TableHelper5;
import com.example.gladyputra.gahmobile.mModel.Laporan5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;

public class MySQLClient5 {
    private static final String URL_LPR5 = "http://192.168.19.140/J_3/API/table/Index5.php/";

    private final Context x;
    private SimpleTableDataAdapter adapter;

    public MySQLClient5(Context x) {
        this.x = x;
    }

    public  void  retrieve (final TableView tb){
        final ArrayList<Laporan5> laporan5s = new ArrayList<>();
        AndroidNetworking.get(URL_LPR5).setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jo;
                Laporan5 s;
                try {
                    for (int i=0;i<response.length();i++)
                    {
                        jo=response.getJSONObject(i);

                        String nama=jo.getString("nama");
                        int banyak_transaksi= jo.getInt("banyak_transaksi");

                        s=new Laporan5();
                        s.setNama(nama);
                        s.setBanyak_transaksi(banyak_transaksi);

                        laporan5s.add(s);
                    }

                    adapter = new SimpleTableDataAdapter(x, new TableHelper5(x).returnLaporan5Array(laporan5s));
                    tb.setDataAdapter(adapter);

                } catch (JSONException e) {
                    Toast.makeText(x,"Respon berhasil, tapi data tidak bisa diambil "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(ANError anError) {
                anError.printStackTrace();
                Toast.makeText(x,"GAGAL : ERROR : "+anError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
