package com.example.gladyputra.gahmobile;

import android.content.Context;
import android.content.SharedPreferences;

public class DataKamar {
    private static  DataKamar mInstance;
    private static Context mCtx;
    private static final String Shared_Pref_Name = "mysharedpref12";

    private static final String Key_NamaKamar = "nama_kamar";
    private static final String Key_JumlahKamar = "jumlah_kamar";
    private static final String Key_HargaKamar = "harga_kamar";

    private DataKamar (Context context) {
        mCtx = context;
    }

    public static synchronized DataKamar getInstance (Context context) {
        if (mInstance == null) {
            mInstance = new DataKamar(context);
        }
        return mInstance;
    }

    public boolean cariKamar(String nama_kamar,int jumlah_kamar, float harga_kamar)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Key_NamaKamar,nama_kamar);
        editor.putInt(Key_JumlahKamar,jumlah_kamar);
        editor.putFloat(Key_HargaKamar,harga_kamar);

        editor.apply();

        return true;
    }

    public String getNamaKamar()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_NamaKamar,null);
    }

    public int getJumlahKamar()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key_JumlahKamar,0);
    }

    public float getHargaKamar()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(Key_HargaKamar,0);
    }
}
