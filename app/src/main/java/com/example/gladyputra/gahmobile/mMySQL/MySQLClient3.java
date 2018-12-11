package com.example.gladyputra.gahmobile.mMySQL;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.gladyputra.gahmobile.TableHelper3;
import com.example.gladyputra.gahmobile.mModel.Laporan3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;

public class MySQLClient3 {
    private static final String URL_LPR3 = "http://192.168.19.140/J_3/API/table/Index3.php/";

    private final Context x;
    private SimpleTableDataAdapter adapter;

    public MySQLClient3(Context x) {
        this.x = x;
    }

    public  void  retrieve (final TableView tb){
        final ArrayList<Laporan3> laporan3s = new ArrayList<>();
        AndroidNetworking.get(URL_LPR3).setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jo;
                Laporan3 s;
                try {
                    for (int i=0;i<response.length();i++)
                    {
                        jo=response.getJSONObject(i);

                        String nama_kota=jo.getString("nama_kota");
                        float pendapatan= jo.getLong("pendapatan");
                        String tahun=jo.getString("tahun");

                        s=new Laporan3();
                        s.setNama_kota(nama_kota);
                        s.setPendapatan(pendapatan);
                        s.setTahun(tahun);

                        laporan3s.add(s);
                    }

                    adapter = new SimpleTableDataAdapter(x, new TableHelper3(x).returnLaporan3Array(laporan3s));
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
