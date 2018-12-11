package com.example.gladyputra.gahmobile;

import android.content.Context;

import com.example.gladyputra.gahmobile.mModel.Laporan3;

import java.util.ArrayList;

public class TableHelper3 {
    Context c;
    private String[] laporan3Header = {"Nama Kota","Pendapatan","Tahun"};
    private String[][] laporan3;

    public TableHelper3(Context c) {
        this.c = c;
    }

    public String[] getLaporan3Header() {
        return laporan3Header;
    }

    public String[][] returnLaporan3Array(ArrayList<Laporan3> laporan3s)
    {
        laporan3 = new String[laporan3s.size()][3];
        Laporan3 s;
        for(int i = 0;i<laporan3s.size();i++)
        {
            s=laporan3s.get(i);

            laporan3[i][0]=s.getNama_kota();
            laporan3[i][1]=String.valueOf(s.getPendapatan());
            laporan3[i][2]=s.getTahun();

        }

        return laporan3;
    }
}
