package com.ayyash.recfon.aktifitas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.ayyash.recfon.MenuFoodsRecord;
import com.ayyash.recfon.R;

import java.util.HashMap;
import java.util.Map;

public class FormAktifitas extends AppCompatActivity {


    public static final String KEY_EMAIL = "txt_email";
    public static final String KEY_AKTIFITAS = "aktifitas";
    public static final String KEY_KATEGORI = "catagory";
    public static final String KEY_FREKUENSI = "frekuensi";
    public static final String KEY_DURASI = "durasi";


    Button Simpan;
    TextView namaAktifitas;
    EditText txtFrekuensi, Menit;
    String nM;
    int indexActivity;
    private String email, kategori;


    ProgressDialog PD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aktifitas);

        namaAktifitas = (TextView) findViewById(R.id.txtActivity);

        txtFrekuensi = (EditText)findViewById(R.id.txtFrekuensi);
        txtFrekuensi.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                txtFrekuensi.setFocusable(true);
                txtFrekuensi.requestFocus();
                txtFrekuensi.setText(null);
                return false;
            }
        });

        Menit = (EditText)findViewById(R.id.txtMenit);
        Menit.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Menit.setFocusable(true);
                Menit.requestFocus();
                Menit.setText(null);
                return false;
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);



        Intent i = getIntent();
        indexActivity = i.getIntExtra("indexActivity", 0);
        nM = i.getStringExtra("namaActivity");

        //Toast.makeText(getApplicationContext(), "index :"+indexActivity+", Nama : "+ nm,Toast.LENGTH_LONG).show();

        namaAktifitas.setText(nM);

        switch (indexActivity) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:

                kategori = "Berat";

                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                kategori = "Sedang";
                break;
            case 22:
                kategori = "Ringan";
                break;
        }

        Simpan = (Button)findViewById(R.id.btnSimpan);
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });

    }

    private void Save() {
        final String txt_email = email.toString().trim();
        final String aktifitas = namaAktifitas.getText().toString().trim();
        final String catagory = kategori.trim();
        final String frekuensi = txtFrekuensi.getText().toString().trim();
        final String durasi = Menit.getText().toString().trim();

        StringRequest sR = new StringRequest(Request.Method.POST, ConfigUmum.URL_INSERT_ACTIVITY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                       // namaAktifitas.setText(response);
                        Intent i = new Intent(getApplicationContext(), AktifitasFisik.class);
                        startActivity(i);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, txt_email);
                params.put(KEY_AKTIFITAS, aktifitas);
                params.put(KEY_KATEGORI, catagory);
                params.put(KEY_FREKUENSI, frekuensi);
                params.put(KEY_DURASI, durasi);
                return params;
            }

        };
       // Toast.makeText(getApplicationContext(), txt_email + " aktifitas = " + aktifitas+" sasda"+durasi, Toast.LENGTH_LONG).show();
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        sR.setRetryPolicy(policy);
        requestQueue.add(sR);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), AktifitasFisik.class);
        startActivity(i);
        finish();
    }
}
