package com.example.gladyputra.gahmobile;

import android.content.Context;

import com.example.gladyputra.gahmobile.mModel.Laporan2;

import java.util.ArrayList;

public class TableHelper2 {
    Context c;
    private String[] laporan2Header = {"Nama Kamar","Jumlah Tamu","Bulan","Tahun"};
    private String[][] laporan2;

    public TableHelper2(Context c) {
        this.c = c;
    }

    public String[] getLaporan2Header() {
        return laporan2Header;
    }

    public String[][] returnLaporan2Array(ArrayList<Laporan2> laporan2s)
    {
        laporan2 = new String[laporan2s.size()][4];
        Laporan2 s;
        for(int i = 0;i<laporan2s.size();i++)
        {
            s=laporan2s.get(i);

            laporan2[i][0]=s.getNama_kamar();
            laporan2[i][1]=String.valueOf(s.getJumlah_tamu());
            laporan2[i][2]=s.getBulan();
            laporan2[i][3]=s.getTahun();

        }

        return laporan2;
    }
}
