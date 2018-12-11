package com.example.gladyputra.gahmobile.mMySQL;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.gladyputra.gahmobile.TableHelper4;
import com.example.gladyputra.gahmobile.mModel.Laporan4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;

public class MySQLClient4 {
    private static final String URL_LPR4 = "http://192.168.19.140/J_3/API/table/Index4.php/";

    private final Context x;
    private SimpleTableDataAdapter adapter;

    public MySQLClient4(Context x) {
        this.x = x;
    }

    public  void  retrieve (final TableView tb){
        final ArrayList<Laporan4> laporan4s = new ArrayList<>();
        AndroidNetworking.get(URL_LPR4).setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jo;
                Laporan4 s;
                try {
                    for (int i=0;i<response.length();i++)
                    {
                        jo=response.getJSONObject(i);

                        String bulan=jo.getString("bulan");
                        int jumlah= jo.getInt("jumlah");
                        String tahun=jo.getString("tahun");

                        s=new Laporan4();
                        s.setBulan(bulan);
                        s.setJumlah(jumlah);
                        s.setTahun(tahun);

                        laporan4s.add(s);
                    }

                    adapter = new SimpleTableDataAdapter(x, new TableHelper4(x).returnLaporan4Array(laporan4s));
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
