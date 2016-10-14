package com.ayyash.recfon.aktifitas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.ConfigUmum;
import com.ayyash.recfon.aktifitas.ItemObjectAktifitas;
import com.ayyash.recfon.aktifitas.MainAdapterAktifitas;
import com.ayyash.recfon.MenuFoodsRecord;
import com.ayyash.recfon.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AktifitasFisik extends AppCompatActivity {

    ProgressDialog progressDialog;
    private ItemObjectAktifitas.ObjectBelajar objectBelajar;
    private MainAdapterAktifitas adapter;
    private RecyclerView rv_item;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktifitas_fisik);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Silahkan Tunggu...");

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        GetData(ConfigUmum.URL_SHOW_PAGI + email);
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
                objectBelajar = mGson.fromJson(response, ItemObjectAktifitas.ObjectBelajar.class);
                System.out.println("Respond "+ response);
                adapter = new MainAdapterAktifitas(getApplication(), objectBelajar.result);
                rv_item.setAdapter(adapter);
//                if(response.contains("1")){
//                    tidakSarapan.setVisibility(View.INVISIBLE);
//                }else {
//                    tidakSarapan.setVisibility(View.VISIBLE);
//                }

                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke server, periksa jaringan anda :(", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });
//        int socketTimeout = 30000;//30 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AktifitasFisik.this);

        // Setting Dialog Title
        alertDialog.setTitle("Sarapan");
        // Setting Dialog Message
        alertDialog.setMessage("Apakah Anda yakin sudah memasukan semua menu sarapan Anda?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.x);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                // Write your code here to invoke YES event
                Intent intent = new Intent(getApplicationContext(),MenuFoodsRecord.class);
                startActivity(intent);
                finish();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("Cek Kembali", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
//                Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
//    }

    }
}
