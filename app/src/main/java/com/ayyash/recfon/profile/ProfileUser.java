package com.ayyash.recfon.profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.ConfigUmum;
import com.ayyash.recfon.Persetujuan;
import com.ayyash.recfon.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileUser extends AppCompatActivity {

    ProgressDialog progressDialog;
    private ItemObjectProfile.ObjectProfile objectProfile;
    private MainAdapterProfile adapter;
    private RecyclerView rv_item;
    String email;
    Context context;
    TextView bmi,keterangan, statusAk, rataDu, txtStatusAKG;


    // This string will hold the results
    String data1;
    String data2;
    String data3;
    String data4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Silahkan Tunggu...");

        rv_item = (RecyclerView) findViewById(R.id.rv_item_profile);


        //nambain kampret ini biar muncul
        rv_item.setLayoutManager(new LinearLayoutManager(context));
        rv_item.setHasFixedSize(true);

        bmi = (TextView) findViewById(R.id.bmii);
        keterangan = (TextView) findViewById(R.id.ket);
       statusAk = (TextView)findViewById(R.id.statuasAkfFisik);
        rataDu = (TextView)findViewById(R.id.rataDuduk);
        txtStatusAKG = (TextView)findViewById(R.id.txtStatusAKG);

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        getBMI();
        getAktifitasFisik();

        GetData(ConfigUmum.URL_SHOW_AKG_PROFILE + email);
       // System.out.println("gila : "+ConfigUmum.URL_SHOW_AKG_PROFILE + email);
    }


    private void getBMI(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        final JsonObjectRequest request = new JsonObjectRequest( ConfigUmum.URL_BMI_PROFILE+email,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
              //  Log.d("sabtu", response.toString());


             //   Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();


                try {
                    JSONObject result = response.getJSONObject("result");
                    String aa = (String) result.get("bmi");
                    String bb = (String) result.get("keterangan");

                    data1 = "";
                    data2 = "";
                    data1 += "Indeks Massa Tubuh: " + aa;
                    data2 += "Keterangan : "+ bb;


                    bmi.setText(data1);
                    keterangan.setText(data2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Masalah pada koneksi, atau data makan kurang lengkap", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Persetujuan.class);
                startActivity(intent);
                finish();
            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        queue.add(request);
    }


    private void getAktifitasFisik(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        final JsonObjectRequest request = new JsonObjectRequest( ConfigUmum.URL_FISIK_DUDUK+email,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //  Log.d("sabtu", response.toString());


                //   Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();


                try {
                    JSONObject result = response.getJSONObject("result");
                    String aa = (String) result.get("status_aktifitas_fisik");
                    String bb = (String) result.get("rata_rata_duduk");

                    data3 = "";
                    data4 = "";
                    data3 += "Rata-rata Aktifitas Fisik: " + aa;
                    data4 += "Rata-rata duduk : "+ bb+" Menit";


                    statusAk.setText(data3);
                    rataDu.setText(data4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Masalah pada koneksi, atau data makan kurang lengkap", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),Persetujuan.class);
                startActivity(intent);
                finish();
            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        queue.add(request);
    }

    public void GetData(String URL) {

        progressDialog.show();


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            ;

            @Override
            public void onResponse(String response) {

//                String result = response.getJSONObject("result");

               if(response.contains("null")){
//                    Toast.makeText(getApplicationContext(), "Mohon lengkapi data rekam makan harian minimal 3 hari untuk melihat Kecukupan asupan makanan (AKG)", Toast.LENGTH_LONG).show();
                    rv_item.setVisibility(View.GONE);
                   txtStatusAKG.setVisibility(View.VISIBLE);

                }else{
                   GsonBuilder builder = new GsonBuilder();
                   Gson mGson = builder.create();
                   objectProfile = mGson.fromJson(response, ItemObjectProfile.ObjectProfile.class);
                   adapter = new MainAdapterProfile(getApplication(), objectProfile.result);
                   //  adapter = new MainAdapterProfile((Response.Listener<String>) getApplication(), objectBelajar.result);
                   rv_item.setAdapter(adapter);

               }
// else {

                    //  Toast.makeText(getApplicationContext(), "Data aktifitas: "+response.toString(), Toast.LENGTH_SHORT).show();
             //  }
                    progressDialog.hide();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke server, periksa jaringan anda :(", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(getApplicationContext(),Persetujuan.class);
        startActivity(intent);
        finish();

    }
}
