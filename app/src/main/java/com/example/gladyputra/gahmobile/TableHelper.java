package com.example.gladyputra.gahmobile;

import android.content.Context;

import com.example.gladyputra.gahmobile.mModel.Spacecraft;

import java.util.ArrayList;

public class TableHelper {
    Context c;
    private String[] spaceProbeHeader = {"Tanggal Transaksi","Jumlah Tarif","Jenis Tamu","Tahun Transaksi"};
    private String[][] spaceProbes;

    public TableHelper(Context c) {
        this.c = c;
    }

    public String[] getSpaceProbeHeader() {
        return spaceProbeHeader;
    }

    public String[][] returnSpaceProbeArray(ArrayList<Spacecraft> spacecrafts)
    {
        spaceProbes = new String[spacecrafts.size()][4];
        Spacecraft s;
        for(int i = 0;i<spacecrafts.size();i++)
        {
            s=spacecrafts.get(i);

            spaceProbes[i][0]=s.getTanggal_transaksi();
            spaceProbes[i][1]=String.valueOf(s.getJumlah_tarif());
            spaceProbes[i][2]=s.getJenis_tamu();
            spaceProbes[i][3]=s.getTahun_transaksi();

        }

        return spaceProbes;
    }
}
