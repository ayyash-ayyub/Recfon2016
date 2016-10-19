package com.ayyash.recfon.selinganpagi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.ConfigUmum;
import com.ayyash.recfon.ItemObject;
import com.ayyash.recfon.MainAdapter;
import com.ayyash.recfon.MenuFoodsRecord;
import com.ayyash.recfon.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class SelinganPagiActivity extends AppCompatActivity {
    public static final String KEY_EMAIL = "txt_email";
    public static final String KEY_MKN = "makanan";
    public static final String KEY_UKUR = "ukuran";
    public static final String KEY_JML = "jumlah";
    public static final String KEY_PROTEIN = "protein1";
    public static final String KEY_LEMAK = "lemak1";
    public static final String KEY_KALORI = "kalori1";
    public static final String KEY_ENERGI = "energi1";

    String email;
    private ItemObject.ObjectBelajar objectBelajar;
    private MainAdapter adapter;
    private RecyclerView rv_item;
    private LinearLayoutManager layoutManager;
    private ProgressDialog progressDialog;
    TextView tanggal;
    TextView tidakSarapan;
    CardView bgSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selingan_pagi_activity);

        bgSpinner = (CardView)findViewById(R.id.bgSpiner);
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

        tidakSarapan = (TextView)findViewById(R.id.textView9);
        tidakSarapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveTidak();
            }
        });

        GetDataSebelumnya(ConfigUmum.CEK_INPUT_SEBELUMNYA +"email="+email+"&waktumakan=1");




    }


    private void SaveTidak() {
        final String txt_email = email.toString().trim();
        final String makanan = "";
        final String jumlah = "";
        final String ukuran = "";
        final String energi1 = "";
        final String protein1 = "";
        final String lemak1 = "";
        final String kalori1 = "";

        //parsing id kelas
//            final String sIdKelas = getIdKelas(ambilIDKelas);
        //final String sIdKelas = "100000";
        //final int saveIdKelas = Integer.parseInt(sIdKelas);

        StringRequest sR = new StringRequest(Request.Method.POST, ConfigUmum.URL_INSERT_SELINGAN_PAGI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), SelinganPagiActivity.class);
                        startActivity(i);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, txt_email);
                params.put(KEY_MKN, makanan);
                params.put(KEY_UKUR, ukuran);
                params.put(KEY_JML, jumlah);
                params.put(KEY_ENERGI, energi1);
                params.put(KEY_PROTEIN, protein1);
                params.put(KEY_LEMAK, lemak1);
                params.put(KEY_KALORI, kalori1);
                return params;
            }

        };
        //Toast.makeText(getApplicationContext(), txt_email + " makanan = " + makanan, Toast.LENGTH_LONG).show();
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        sR.setRetryPolicy(policy);
        requestQueue.add(sR);
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
                if (response.contains("tidak makan")){
                    bgSpinner.setVisibility(View.GONE);
                    tidakSarapan.setVisibility(View.VISIBLE);
                    rv_item.setVisibility(View.GONE);
                }

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

    public void GetDataSebelumnya(String URL) {

        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            ;

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                //Integer jml_input = Integer.valueOf(response);

                if(response.equals("1")){
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    GetData(ConfigUmum.URL_SHOW_SELINGAN_PAGI + email);
                }else {
                    Toast.makeText(getApplicationContext(),"Data sarapan pagi belum diisi, silakan periksa kembali!",Toast.LENGTH_LONG).show();
                    finish();
                }

                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Gagal Konek ke server, periksa jaringan anda :(", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
    }




    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SelinganPagiActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("Konfirmasi");
        // Setting Dialog Message
        alertDialog.setMessage("Apakah Anda yakin sudah memasukan semua menu sarapan Anda?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.i);

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
