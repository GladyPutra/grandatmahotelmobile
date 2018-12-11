package com.example.gladyputra.gahmobile;

import android.content.Context;
import android.content.SharedPreferences;

public class DataDiri {
    private static DataDiri mInstance;
    private static Context mCtx;

    private static final String Shared_Pref_Name = "mysharedpref12";
    //Untuk Data Diri
    private static final String Key_IDDataDiri = "id_data_diri";
    private static final String Key_Nama = "nama";
    private static final String Key_NoIdentitas = "no_identitas";
    private static final String Key_NoTelepon = "no_telepon";
    private static final String Key_Email = "email";
    private static final String Key_Alamat = "alamat";
    private static final String Key_JenisKelamin = "jenis_kelamin";
    private static final String Key_TglBuat = "tgl_buat";

    private DataDiri (Context context) {
        mCtx = context;
    }

    public static synchronized DataDiri getInstance (Context context) {
        if (mInstance == null) {
            mInstance = new DataDiri(context);
        }
        return mInstance;
    }

    public boolean DataDiriUser(int id_data_diri,String nama, String no_identitas, String no_telepon, String email, String alamat, String jenis_kelamin, String tgl_buat)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(Key_IDDataDiri,id_data_diri);
        editor.putString(Key_Nama,nama);
        editor.putString(Key_NoIdentitas,no_identitas);
        editor.putString(Key_NoTelepon,no_telepon);
        editor.putString(Key_Email,email);
        editor.putString(Key_Alamat,alamat);
        editor.putString(Key_JenisKelamin,jenis_kelamin);
        editor.putString(Key_TglBuat,tgl_buat);

        editor.apply();

        return true;
    }

    //get Data Diri
    public int getIDDataDiri()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Key_IDDataDiri,0);
    }

    public String getNama()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_Nama,null);
    }

    public String getNoIdentitas()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_NoIdentitas,null);
    }

    public String getNoTelepon()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_NoTelepon,null);
    }

    public String getEmail()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_Email,null);
    }

    public String getAlamat()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_Alamat,null);
    }

    public String getJenisKelamin()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_JenisKelamin,null);
    }

    public String getTglBuat()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_TglBuat,null);
    }
}
