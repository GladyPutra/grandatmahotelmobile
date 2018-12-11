package com.example.gladyputra.gahmobile.mMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gladyputra.gahmobile.mModel.Kamar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    Spinner spinner;
    String jsonData;
    EditText txtkamar;

    ProgressDialog progressDialog;

    ArrayList<String> kamars = new ArrayList<>();

    public DataParser(Context c, Spinner spinner, String jsonData,EditText txtkamar) {
        this.c = c;
        this.spinner = spinner;
        this.jsonData = jsonData;
        this.txtkamar = txtkamar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(c);
        progressDialog.setTitle("Parse");
        progressDialog.setMessage("Parsing...Please Wait...");
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        progressDialog.dismiss();

        if(result == 0)
        {
            Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(c,"Parsed Succes !!!",Toast.LENGTH_SHORT).show();

            ArrayAdapter arrayAdapter = new ArrayAdapter(c,android.R.layout.simple_list_item_1,kamars);
            spinner.setAdapter(arrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                    Toast.makeText(c,kamars.get(position),Toast.LENGTH_SHORT).show();
                    txtkamar.setText(kamars.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private int parseData()
    {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            kamars.clear();
            Kamar kamar = null;

            for(int i = 0;i<jsonArray.length();i++)
            {
                jsonObject=jsonArray.getJSONObject(i);

                String id_kamar = jsonObject.getString("id_kamar");

                kamar = new Kamar();
                kamar.setId_kamar(id_kamar);

                kamars.add(id_kamar);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
