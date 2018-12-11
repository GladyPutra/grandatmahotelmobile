package com.example.gladyputra.gahmobile.mMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    Spinner spinner;
    EditText txtkamar;

    ProgressDialog progressDialog;

    public Downloader(Context c, String urlAddress, Spinner spinner,EditText txtkamar) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.spinner = spinner;
        this.txtkamar = txtkamar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(c);
        progressDialog.setTitle("Fecth");
        progressDialog.setMessage("Fetching...Please Wait...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressDialog.dismiss();

        if(s == null)
        {
            Toast.makeText(c,"Unable to Retrieve, returned null",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(c,"Success",Toast.LENGTH_SHORT).show();

            DataParser parser = new DataParser(c,spinner,s,txtkamar);
            parser.execute();
        }
    }

    private String downloadData()
    {
        HttpURLConnection con = Connector.connect(urlAddress);
        if(con == null)
        {
            return null;
        }

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(con.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            StringBuffer response = new StringBuffer();

            if(bufferedReader != null)
            {
                while((line=bufferedReader.readLine()) != null)
                {
                    response.append(line+"\n");
                }

                bufferedReader.close();
            }
            else
            {
                return null;
            }

            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
