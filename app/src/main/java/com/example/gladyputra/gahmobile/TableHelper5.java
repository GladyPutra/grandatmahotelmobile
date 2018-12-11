package com.example.gladyputra.gahmobile;

import android.content.Context;

import com.example.gladyputra.gahmobile.mModel.Laporan5;

import java.util.ArrayList;

public class TableHelper5 {
    Context c;
    private String[] laporan5Header = {"Nama","Banyak Transaksi"};
    private String[][] laporan5;

    public TableHelper5(Context c) {
        this.c = c;
    }

    public String[] getLaporan5Header() {
        return laporan5Header;
    }

    public String[][] returnLaporan5Array(ArrayList<Laporan5> laporan5s)
    {
        laporan5 = new String[laporan5s.size()][2];
        Laporan5 s;
        for(int i = 0;i<laporan5s.size();i++)
        {
            s=laporan5s.get(i);

            laporan5[i][0]=s.getNama();
            laporan5[i][1]=String.valueOf(s.getBanyak_transaksi());

        }

        return laporan5;
    }
}
