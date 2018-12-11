package com.example.gladyputra.gahmobile;

import android.content.Context;
import android.content.SharedPreferences;

public class DataReservasi {
    private static  DataReservasi mInstance;
    private static Context mCtx;
    private static final String Shared_Pref_Name = "mysharedpref12";

    //Untuk Reservasi
    private static final String Key_IDBooking = "id_booking";
    private static final String Key_JmlhKamar = "jumlah_kamar";
    private static final String Key_JmlhAnak = "jumlah_anak";
    private static final String Key_JmlhDewasa = "jumlah_dewasa";
    private static final String Key_TglReservasi = "tgl_reservasi";
    private static final String Key_IDKamar = "id_kamar";
    private static final String Key_NamaKamar = "nama_kamar";
    private static final String Key_Fasilitas = "fasilitas";
    private static final String Key_StatusBatal = "status_batal";
    private static final String Key_HargaKamar = "harga_kamar";
    private static final String Key_TglSelesai = "tgl_selesai";
    private static final String Key_TglMenginap = "tgl_menginap";

    private DataReservasi (Context context) {
        mCtx = context;
    }

    public static synchronized DataReservasi getInstance (Context context) {
        if (mInstance == null) {
            mInstance = new DataReservasi(context);
        }
        return mInstance;
    }

    public boolean reservasiUser(String id_booking, int jmlh_kamar, int jmlh_anak, int jmlh_dewasa, String tgl_reservasi, String id_kamar, String fasilitas, String nama_kamar, String status_batal, float harga_kamar, String tgl_menginap,String tgl_selesai)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Key_IDBooking,id_booking);
        editor.putInt(Key_JmlhKamar,jmlh_kamar);
        editor.putInt(Key_JmlhAnak,jmlh_anak);
        editor.putInt(Key_JmlhDewasa,jmlh_dewasa);
        editor.putString(Key_TglReservasi,tgl_reservasi);
        editor.putString(Key_IDKamar,id_kamar);
        editor.putString(Key_Fasilitas,fasilitas);
        editor.putString(Key_NamaKamar,nama_kamar);
        editor.putString(Key_StatusBatal,status_batal);
        editor.putFloat(Key_HargaKamar,harga_kamar);
        editor.putString(Key_TglMenginap,tgl_menginap);
        editor.putString(Key_TglSelesai,tgl_selesai);

        editor.apply();

        return true;
    }

    //get Data Reservasi
    public String getIDBooking()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_IDBooking,null);
    }

    public int getJmlhKamar()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key_JmlhKamar,0);
    }

    public int getJmlhAnak()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key_JmlhAnak,0);
    }

    public int getJmlhDewasa()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key_JmlhDewasa,0);
    }

    public String getTglReservasi()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglReservasi,null);
    }

    public String getIDKamar()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_IDKamar,null);
    }

    public String getFasilitas()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_Fasilitas,null);
    }

    public String getNamaKamar()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_NamaKamar,null);
    }

    public String getStatusBatal()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_StatusBatal,null);
    }

    public float getHargaKamar()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(Key_HargaKamar,0);
    }

    public String getTglMenginap()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglMenginap,null);
    }

    public String getTglSelesai()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglSelesai,null);
    }
}
