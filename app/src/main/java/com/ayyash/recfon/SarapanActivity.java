package com.ayyash.recfon;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class SarapanActivity extends AppCompatActivity {

    String email;
    private ItemObject.ObjectBelajar objectBelajar;
    private MainAdapter adapter;
    private RecyclerView rv_item;
    private LinearLayoutManager layoutManager;
    private ProgressDialog progressDialog;
    TextView tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sarapan_activity);


        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        rv_item = (RecyclerView) findViewById(R.id.rv_item);
        tanggal = (TextView) findViewById(R.id.txtTanggal);

        layoutManager = new LinearLayoutManager(getApplication());
        rv_item.setHasFixedSize(true);
        rv_item.setLayoutManager(layoutManager);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Silahkan Tunggu...");

        GetData(ConfigUmum.URL_SHOW_PAGI + email);


        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime = sdf.format(new Date());

        tanggal.setText("Data hari ini : " + currentDateandTime);

        // Toast.makeText(SarapanActivity.this, "Hari ini: "+currentDateandTime, Toast.LENGTH_SHORT).show();


        //Toast.makeText(getApplicationContext(), "Login as : "+ ConfigUmum.URL_SHOW_PAGI+email, Toast.LENGTH_LONG).show();
    }


    public void GetData(String URL) {

        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            ;

            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                objectBelajar = mGson.fromJson(response, ItemObject.ObjectBelajar.class);
                adapter = new MainAdapter(getApplication(), objectBelajar.result);
                rv_item.setAdapter(adapter);
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke server, periksa jaringan anda :(", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });
        queue.add(stringRequest);
    }









}
