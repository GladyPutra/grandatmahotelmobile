package com.example.gladyputra.gahmobile;

import android.content.Context;

import com.example.gladyputra.gahmobile.mModel.Laporan3;
import com.example.gladyputra.gahmobile.mModel.Laporan4;

import java.util.ArrayList;

public class TableHelper4 {
    Context c;
    private String[] laporan4Header = {"Bulan","Jumlah","Tahun"};
    private String[][] laporan4;

    public TableHelper4(Context c) {
        this.c = c;
    }

    public String[] getLaporan4Header() {
        return laporan4Header;
    }

    public String[][] returnLaporan4Array(ArrayList<Laporan4> laporan4s)
    {
        laporan4 = new String[laporan4s.size()][3];
        Laporan4 s;
        for(int i = 0;i<laporan4s.size();i++)
        {
            s=laporan4s.get(i);

            laporan4[i][0]=s.getBulan();
            laporan4[i][1]=String.valueOf(s.getJumlah());
            laporan4[i][2]=s.getTahun();

        }

        return laporan4;
    }
}
