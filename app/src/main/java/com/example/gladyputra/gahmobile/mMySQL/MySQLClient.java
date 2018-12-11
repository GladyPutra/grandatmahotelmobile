package com.example.gladyputra.gahmobile.mMySQL;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.gladyputra.gahmobile.TableHelper;
import com.example.gladyputra.gahmobile.mModel.Spacecraft;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;

public class MySQLClient {
    private static final String URL_LPR1 = "http://192.168.19.140/J_3/API/table/Index.php/";

    private final Context x;
    private SimpleTableDataAdapter adapter;

    public MySQLClient(Context x) {
        this.x = x;
    }

    public  void  retrieve (final TableView tb){
        final ArrayList<Spacecraft> spacecrafts = new ArrayList<>();
        AndroidNetworking.get(URL_LPR1).setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jo;
                Spacecraft s;
                try {
                    for (int i=0;i<response.length();i++)
                    {
                        jo=response.getJSONObject(i);

                        String tanggal_transaksi=jo.getString("tanggal_transaksi");
                        float jumlah_tarif= jo.getLong("jumlah_tarif");
                        String jenis_tamu=jo.getString("jenis_tamu");
                        String tahun_transaksi=jo.getString("tahun_transaksi");

                        s=new Spacecraft();
                        s.setTanggal_transaksi(tanggal_transaksi);
                        s.setJumlah_tarif(jumlah_tarif);
                        s.setJenis_tamu(jenis_tamu);
                        s.setTahun_transaksi(tahun_transaksi);

                        spacecrafts.add(s);
                    }

                    adapter = new SimpleTableDataAdapter(x, new TableHelper(x).returnSpaceProbeArray(spacecrafts));
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
