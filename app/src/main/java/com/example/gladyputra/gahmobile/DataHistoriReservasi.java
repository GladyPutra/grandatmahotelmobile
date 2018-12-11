package com.example.gladyputra.gahmobile;

import android.content.Context;
import android.content.SharedPreferences;

public class DataHistoriReservasi {
    private static  DataHistoriReservasi mInstance;
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
    private static final String Key_StatusBatal = "status_batal";
    private static final String Key_HargaKamar = "harga_kamar";
    private static final String Key_TglCheckin = "tgl_checkin";
    private static final String Key_TglCheckout = "tgl_checkout";
    private static final String Key_TglTransaksi = "tgl_transaksi";
    private static final String Key_JenisStatus = "jenis_status";
    private static final String Key_NamaItem = "nama_item";
    private static final String Key_JmlhItem = "jumlah_item";
    private static final String Key_HargaItem = "harga_item";
    private static final String Key_JmlhTarif = "jumlah_tarif";

    private DataHistoriReservasi (Context context) {
        mCtx = context;
    }

    public static synchronized DataHistoriReservasi getInstance (Context context) {
        if (mInstance == null) {
            mInstance = new DataHistoriReservasi(context);
        }
        return mInstance;
    }

    public boolean historiReservasiUser(String id_booking, int jmlh_kamar, int jmlh_anak, int jmlh_dewasa, String tgl_reservasi, String id_kamar, String nama_kamar, String status_batal, float harga_kamar, String tgl_menginap,String tgl_selesai, String tgl_transaksi, String jenis_status, String nama_item, int jumlah_item, float harga_item, float jumlah_tarif)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Key_IDBooking,id_booking);
        editor.putInt(Key_JmlhKamar,jmlh_kamar);
        editor.putInt(Key_JmlhAnak,jmlh_anak);
        editor.putInt(Key_JmlhDewasa,jmlh_dewasa);
        editor.putString(Key_TglReservasi,tgl_reservasi);
        editor.putString(Key_IDKamar,id_kamar);
        editor.putString(Key_NamaKamar,nama_kamar);
        editor.putString(Key_StatusBatal,status_batal);
        editor.putFloat(Key_HargaKamar,harga_kamar);
        editor.putString(Key_TglCheckin,tgl_menginap);
        editor.putString(Key_TglCheckout,tgl_selesai);
        editor.putString(Key_TglTransaksi,tgl_transaksi);
        editor.putString(Key_JenisStatus,jenis_status);
        editor.putString(Key_NamaItem,nama_item);
        editor.putInt(Key_JmlhItem,jumlah_item);
        editor.putFloat(Key_HargaItem,harga_item);
        editor.putFloat(Key_JmlhTarif,jumlah_tarif);

        editor.apply();

        return true;
    }

    //get Data Histori Reservasi
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

    public String getTglCheckin()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglCheckin,null);
    }

    public String getTglCheckout()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglCheckout,null);
    }

    public String getTglTransaksi()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglTransaksi,null);
    }

    public String getJenisStatus()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_JenisStatus,null);
    }

    public String getNamaItem()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_NamaItem,null);
    }

    public int getJumlahItem()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key_JmlhItem,0);
    }

    public float getHargaItem()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(Key_HargaItem,0);
    }

    public float getJumlahTarif()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(Key_JmlhTarif,0);
    }
}
