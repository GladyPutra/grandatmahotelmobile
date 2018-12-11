package com.example.gladyputra.gahmobile;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Date;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String Shared_Pref_Name = "mysharedpref12";
    //Untuk User
    private static final String Key_Username = "username";
    private static final String Key_IDUser = "id_user";
    private static final String Key_IDRole = "id_role";
    private static final String Key_IDKota = "id_kota";
    //Untuk Role
    private static final String Key_IDRole2 = "id_role";
    private static final String Key_Role = "nama_role";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(int id_user,String username, int id_role, int id_kota)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(Key_IDUser,id_user);
        editor.putString(Key_Username,username);
        editor.putInt(Key_IDRole,id_role);
        editor.putInt(Key_IDKota,id_kota);

        editor.apply();

        return true;
    }

    public boolean userRole(int id_role, String nama_role)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(Key_IDRole2,id_role);
        editor.putString(Key_Role,nama_role);

        editor.apply();

        return true;
    }

    public boolean isLoggedin()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(Key_Username,null)!=null)
        {
            return true;
        }
        return false;
    }

    public boolean logout()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    //get Data Username
    public String getUserName()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_Username,null);
    }

    //get Role User
    public String getRole()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Key_Role,null);
    }
}
