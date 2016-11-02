package com.ayyash.recfon;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.makansiang.MakanSiangActivity;

import java.util.HashMap;
import java.util.Map;

public class PerbandinganAsupan extends AppCompatActivity {

    Button btnSimpanAsupan;
    RadioButton r1,r2,r3;
    RadioGroup rgg;
    String info="";
    String email;
    public static final String INFO = "txt_info";
    public static final String KEY_EMAIL = "txt_email";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perbandingan_asup);
        rgg = (RadioGroup)findViewById(R.id.rrggAsupan);
        r1 = (RadioButton)findViewById(R.id.rbSepertiBiasa);
        r2 = (RadioButton)findViewById(R.id.rbLebihBanyak);
        r3 = (RadioButton)findViewById(R.id.rbLebihSedikit);

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Silahkan Tunggu...");
        GetDataSebelumnya(ConfigUmum.CEK_INPUT_SEBELUMNYA +"email="+email+"&waktumakan=6");

        btnSimpanAsupan = (Button)findViewById(R.id.btnYaAsupan);

        btnSimpanAsupan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(r1.isChecked()){
                    info ="seperti biasa";


                }
                else if(r2.isChecked()){
                    info ="lebih banyak";


                }
                else if(r3.isChecked()){
                    info ="lebih sedikit";


                }

                SaveInfoAsupan();

            }


        });


    }





    private void SaveInfoAsupan() {
        final String txt_email  = email.toString().trim();
        final String txt_info    = info.toString().trim();


        progressDialog.show();
        StringRequest sR = new StringRequest(Request.Method.POST, ConfigUmum.URL_INSERT_ASUPAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), Persetujuan.class);
                        startActivity(i);
                        finish();
                        System.out.println(response);
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Gagal Menghubungkan ke Server, Cek kembali koneksi anda", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, txt_email);
                params.put(INFO, txt_info);

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

                if(response.contains("3")){
                    Intent i = new Intent(getApplicationContext(),MakanSiangActivity.class);
                    startActivity(i);
                    finish();
                }else {

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


                    // Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    //GetData(ConfigUmum.URL_SHOW_MAKAN_SIANG + email);
                }else {
                    Toast.makeText(getApplicationContext(),"Silahkan lengkapi semua data makan diatas.",Toast.LENGTH_SHORT).show();
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

    }


}
