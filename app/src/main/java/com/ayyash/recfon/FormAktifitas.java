package com.ayyash.recfon;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
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

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class FormAktifitas extends AppCompatActivity {


    public static final String KEY_EMAIL = "txt_email";
    public static final String KEY_MKN = "makanan";
    public static final String KEY_UKUR = "ukuran";
    public static final String KEY_JML = "jumlah";
    public static final String KEY_PROTEIN = "protein1";
    public static final String KEY_LEMAK = "lemak1";
    public static final String KEY_KALORI = "kalori1";
    public static final String KEY_ENERGI = "energi1";

    TextView tv,namaMakanan;
    Button btnKeluar;
    String nM;
    int indexActivity;
    String email;

    ProgressDialog PD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan_pagi);
        tv = (TextView) findViewById(R.id.textView);

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);


        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), SarapanActivity.class);
                startActivity(i);
                finish();
            }
        });


        Intent i = getIntent();
        indexActivity = i.getIntExtra("indexActivity", 0);
        nM = i.getStringExtra("namaActivity");

        tv.setText("index ke: " + indexActivity);
        namaMakanan.setText("" + nM);


        switch (indexActivity) {
            case 0:

             break;
        }

    }

    private void Save(){
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(FormAktifitas.this);

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
                ConfirmSave();
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

    private void ConfirmSave() {
        final String txt_email = email.toString().trim();
        final String makanan = namaMakanan.getText().toString().trim();

        StringRequest sR = new StringRequest(Request.Method.POST, "http://103.43.45.237/recfon/api/insert_record_pagi.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), SarapanActivity.class);
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
                return params;
            }

        };
        Toast.makeText(getApplicationContext(), txt_email + " makanan = " + makanan, Toast.LENGTH_LONG).show();
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        sR.setRetryPolicy(policy);
        requestQueue.add(sR);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MenuFoodsRecord.class);
        startActivity(i);
        finish();
    }
}