package com.example.gladyputra.gahmobile.mMySQL;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.gladyputra.gahmobile.TableHelper2;
import com.example.gladyputra.gahmobile.mModel.Laporan2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;

public class MySQLClient2 {
    private static final String URL_LPR2 = "http://192.168.19.140/J_3/API/table/Index2.php/";

    private final Context x;
    private SimpleTableDataAdapter adapter;

    public MySQLClient2(Context x) {
        this.x = x;
    }

    public  void  retrieve (final TableView tb){
        final ArrayList<Laporan2> laporan2s = new ArrayList<>();
        AndroidNetworking.get(URL_LPR2).setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jo;
                Laporan2 s;
                try {
                    for (int i=0;i<response.length();i++)
                    {
                        jo=response.getJSONObject(i);

                        String nama_kamar=jo.getString("nama_kamar");
                        int jumlah_tamu= jo.getInt("jumlah_tamu");
                        String bulan=jo.getString("bulan");
                        String tahun=jo.getString("tahun");

                        s=new Laporan2();
                        s.setNama_kamar(nama_kamar);
                        s.setJumlah_tamu(jumlah_tamu);
                        s.setBulan(bulan);
                        s.setTahun(tahun);

                        laporan2s.add(s);
                    }

                    adapter = new SimpleTableDataAdapter(x, new TableHelper2(x).returnLaporan2Array(laporan2s));
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
