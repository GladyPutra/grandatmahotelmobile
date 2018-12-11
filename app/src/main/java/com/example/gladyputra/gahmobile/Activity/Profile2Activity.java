package com.example.gladyputra.gahmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gladyputra.gahmobile.R;
import com.example.gladyputra.gahmobile.SharedPrefManager;

public class Profile2Activity extends AppCompatActivity {

    private TextView txtuser,txtrole;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        txtuser = (TextView) findViewById(R.id.txtharga_kamar);
        txtrole = (TextView) findViewById(R.id.txtrole);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu ...");

        txtuser.setText(SharedPrefManager.getInstance(this).getUserName());
        txtrole.setText(SharedPrefManager.getInstance(this).getRole());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuPassword:
                finish();
                startActivity(new Intent(this, LupaPasswordActivity.class));
                break;
            case R.id.menuLaporan1:
                finish();
                startActivity(new Intent(this,ShowReport1Activity.class));
                break;
            case R.id.menuLaporan2:
                finish();
                startActivity(new Intent(this,ShowReport2Activity.class));
                break;
            case R.id.menuLaporan3:
                finish();
                startActivity(new Intent(this,ShowReport3Activity.class));
                break;
            case R.id.menuLaporan4:
                finish();
                startActivity(new Intent(this,ShowReport4Activity.class));
                break;
            case R.id.menuLaporan5:
                finish();
                startActivity(new Intent(this,ShowReport5Activity.class));
                break;
//            case R.id.menuLaporan6:
//                finish();
//                startActivity(new Intent(this,ShowReport6Activity.class));
//                break;
        }
        return true;
    }
}
