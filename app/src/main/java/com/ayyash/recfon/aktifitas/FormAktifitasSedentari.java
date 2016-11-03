package com.ayyash.recfon.aktifitas;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.ConfigUmum;
import com.ayyash.recfon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormAktifitasSedentari extends AppCompatActivity {
    public static final String KEY_EMAIL = "txt_email";
    public static final String KEY_AKTIFITAS = "aktifitas";
    public static final String KEY_DURASI = "durasi";


    Button Simpan;
    TextView namaAktifitas;
    EditText txtFrekuensi, Menit;
    Integer nM;
    int indexActivity;
    private String email, kategori;


    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aktifitas_sendentari);

        namaAktifitas = (TextView) findViewById(R.id.txtActivity);


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

        nM = i.getIntExtra ("aktifitas", 0);

        switch (nM) {
            case 1 :
                namaAktifitas.setText("Duduk Di Transportasi (Hari Kerja)");
                break;
            case 2 :
                namaAktifitas.setText("Duduk Di Transportasi (Hari Libur)");
                break;
            case 3 :
                namaAktifitas.setText("Duduk Ditempat Kerja (Hari Kerja)");
                break;
            case 4 :
                namaAktifitas.setText("Duduk Ditempat Kerja (Hari Libur)");
                break;
            case 5 :
                namaAktifitas.setText("Duduk di Rumah (Hari Kerja)");
                break;
            case 6 :
                namaAktifitas.setText("Duduk di Rumah (Hari Libur)");
                break;
            case 7 :
                namaAktifitas.setText("Duduk Menonton TV dan Bersantai (Hari Kerja)");
                break;
            case 8 :
                namaAktifitas.setText("Duduk Menonton TV dan Bersantai (Hari Libur)");
                break;
            case 9 :
                namaAktifitas.setText("Tidur (Hari Kerja)");
                break;
            case 10 :
                namaAktifitas.setText("Tidur (Hari Libur)");
                break;

        }

        //Toast.makeText(getApplicationContext(), "index :"+indexActivity+", Nama : "+ nm,Toast.LENGTH_LONG).show();


        Simpan = (Button)findViewById(R.id.btnSimpan);
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });

    }

    private void Save() {
        PD.show();
        final String txt_email = email.toString().trim();
        final String aktifitas = nM.toString().trim();
        final String durasi = Menit.getText().toString().trim();

        StringRequest sR = new StringRequest(Request.Method.POST, ConfigUmum.URL_INSERT_ACTIVITY_SEDENTARI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
//                        // namaAktifitas.setText(response);
//                        //Intent intent = getIntent();

//
//
                            Intent i = new Intent(FormAktifitasSedentari.this, AktifitasSedentari.class);
//                            i.putExtra("segar","reload");
                            startActivity(i);
                            finish();
//
//


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        PD.dismiss();
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, txt_email);
                params.put(KEY_AKTIFITAS, aktifitas);
                params.put(KEY_DURASI, durasi);
                return params;
            }

        };

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        sR.setRetryPolicy(policy);
        requestQueue.add(sR);
    }



    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), AktifitasSedentari.class);
        startActivity(i);
        finish();
    }

}
