package com.example.gladyputra.gahmobile;

import android.content.Context;
import android.content.SharedPreferences;

public class Nota {
    private static Nota mInstance;
    private static Context mCtx;
    private static final String Shared_Pref_Name = "mysharedpref12";

    //Untuk Nota
    private static final String Key_IDBooking = "id_booking";
    private static final String Key_Nama = "nama";
    private static final String Key_Alamat = "alamat";
    private static final String Key_TglReservasi = "tgl_reservasi";
    private static final String Key_TglCheckin = "tgl_checkin";
    private static final String Key_TglCheckout = "tgl_checkout";
    private static final String Key_JmlhKamar = "jumlah_kamar";
    private static final String Key_JmlhAnak = "jumlah_anak";
    private static final String Key_JmlhDewasa = "jumlah_dewasa";
    private static final String Key_NamaKamar = "nama_kamar";
    private static final String Key_TempatTidur = "tempat_tidur";
    private static final String Key_HargaKamar = "harga_kamar";
    private static final String Key_NamaItem = "nama_item";
    private static final String Key_JmlhItem = "jumlah_item";
    private static final String Key_HargaItem = "harga_item";
    private static final String Key_TotalTarif = "total_tarif";

    private Nota (Context context) {
        mCtx = context;
    }

    public static synchronized Nota getInstance (Context context) {
        if (mInstance == null) {
            mInstance = new Nota(context);
        }
        return mInstance;
    }

    public boolean notaUser(String id_booking, String nama, String alamat, String tgl_reservasi, String tgl_checkin, String tgl_checkout, int jmlh_kamar, int jmlh_anak, int jmlh_dewasa, String nama_kamar, String tempat_tidur, float harga_kamar, String nama_item, int jmlh_item, float harga_item, float total_tarif)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Key_IDBooking,id_booking);
        editor.putString(Key_Nama,nama);
        editor.putString(Key_Alamat,alamat);
        editor.putString(Key_TglReservasi,tgl_reservasi);
        editor.putString(Key_TglCheckin,tgl_checkin);
        editor.putString(Key_TglCheckout,tgl_checkout);
        editor.putInt(Key_JmlhKamar,jmlh_kamar);
        editor.putInt(Key_JmlhAnak,jmlh_anak);
        editor.putInt(Key_JmlhDewasa,jmlh_dewasa);
        editor.putString(Key_NamaKamar,nama_kamar);
        editor.putString(Key_TempatTidur,tempat_tidur);
        editor.putFloat(Key_HargaKamar,harga_kamar);
        editor.putString(Key_NamaItem,nama_item);
        editor.putInt(Key_JmlhItem,jmlh_item);
        editor.putFloat(Key_HargaItem,harga_item);
        editor.putFloat(Key_TotalTarif,total_tarif);

        editor.apply();

        return true;
    }

    public String getIDBooking()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_IDBooking,null);
    }

    public String getNama()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_Nama,null);
    }

    public String getAlamat()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_Alamat,null);
    }

    public String getTglReservasi()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglReservasi,null);
    }

    public String getCheckin()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglCheckin,null);
    }

    public String getCheckout()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglCheckout,null);
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

    public String getNamaKamar()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_NamaKamar,null);
    }

    public String getTempatTidur()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TempatTidur,null);
    }

    public float getHargaKamar()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(Key_HargaKamar,0);
    }

    public String getNamaItem()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_NamaItem,null);
    }

    public int getJmlhItem()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key_JmlhItem,0);
    }

    public float getHargaItem()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(Key_HargaItem,0);
    }

    public float getTotalTarif()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(Key_TotalTarif,0);
    }

}
