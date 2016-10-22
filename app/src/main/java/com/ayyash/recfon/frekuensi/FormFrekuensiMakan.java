package com.ayyash.recfon.frekuensi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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
import com.ayyash.recfon.ConfigUmum;
import com.ayyash.recfon.ItemObject;
import com.ayyash.recfon.MenuFoodsRecord;
import com.ayyash.recfon.PerhitunganPagi;
import com.ayyash.recfon.R;
import com.ayyash.recfon.SarapanActivity;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class FormFrekuensiMakan extends AppCompatActivity {

    public static final String KEY_EMAIL = "txt_email";
    public static final String KEY_MKN = "makanan";
    public static final String KEY_UKUR = "ukuran";
    public static final String KEY_JML = "jumlah";
    public static final String KEY_PROTEIN = "protein1";
    public static final String KEY_LEMAK = "lemak1";
    public static final String KEY_KALORI = "kalori1";
    public static final String KEY_ENERGI = "energi1";

    TextView tv,namaMakanan,satuan;
    Button hitung,btnKeluar;
    String nM;
    int indexMakanan;
    RadioGroup rg, rgJenisMakanan;
    RadioButton r1,r2,r3,r4,r5,r6,r7,rm1,rm2,rm3,rm4,rm5,rm6,rm7;
    double urt;
    SeekBar seekBar;
    double progress;
    double pengali;
    ImageView Img;
    String ukuran="";
    String email;
    int id_waktu_makan=1;
    ProgressDialog PD;
    private ItemObject.ObjectBelajar objectBelajar;
    String penampungProgres, penampungUkuran;
    String hEnergiSort;
    String hProteinSort ;
    String hLemakSort;
    String hKaloriSort;
    double e = 0;
    double p = 0;
    double l = 0;
    double k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_frekuensi_makan);
        // tv = (TextView) findViewById(R.id.textView);
        satuan = (TextView) findViewById(R.id.textView2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        namaMakanan = (TextView) findViewById(R.id.textView3);
        btnKeluar = (Button) findViewById(R.id.batal);

        hitung = (Button) findViewById(R.id.hitung);
//        btnKeluar =(Button)findViewById(R.id.button3);
        satuan.setText(String.valueOf(progress));

        Img = (ImageView) findViewById(R.id.imageViewUye);
        rgJenisMakanan = (RadioGroup)findViewById(R.id.rgJenisMakanan);
        rm1 = (RadioButton) findViewById(R.id.rm1);
        rm2 = (RadioButton) findViewById(R.id.rm2);
        rm3 = (RadioButton) findViewById(R.id.rm3);
        rm4 = (RadioButton) findViewById(R.id.rm4);
        rm5 = (RadioButton) findViewById(R.id.rm5);
        rm6 = (RadioButton) findViewById(R.id.rm6);
        rm7 = (RadioButton) findViewById(R.id.rm7);
//        rm1.setVisibility(View.GONE);
        rm2.setVisibility(View.GONE);
        rm3.setVisibility(View.GONE);
        rm4.setVisibility(View.GONE);
        rm5.setVisibility(View.GONE);
        rm6.setVisibility(View.GONE);
        rm7.setVisibility(View.GONE);


        rg = (RadioGroup) findViewById(R.id.rg);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        r3 = (RadioButton) findViewById(R.id.radioButton3);
        r4 = (RadioButton) findViewById(R.id.radioButton4);
        r5 = (RadioButton) findViewById(R.id.radioButton5);
        r6 = (RadioButton) findViewById(R.id.radioButton6);
        r7 = (RadioButton) findViewById(R.id.radioButton7);
        r3.setVisibility(View.GONE);
        r4.setVisibility(View.GONE);
        r5.setVisibility(View.GONE);
        r6.setVisibility(View.GONE);
        r7.setVisibility(View.GONE);
        rg.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);
        final DecimalFormat df = new DecimalFormat("#.##");


        progress = 0.5;
        pengali = 0.5;
        satuan.setText("porsi: " + String.valueOf(progress));


        //seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = ((float) i / 2);
                satuan.setText("porsi: " + String.valueOf(progress));
                pengali = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //batas seekbar

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), SarapanActivity.class);
                startActivity(i);
                finish();
            }
        });


        Intent i = getIntent();
//        indexMakanan = i.getIntExtra("indexMakanan", 0);
        nM = i.getStringExtra("makanan");

//        tv.setText("index ke: " + indexMakanan);
        namaMakanan.setText("" + nM);


        switch (nM) {

            case "Mie Goreng":

                Img.setImageResource(R.drawable.mie_goreng_bukan_instan);
                rm1.setText("Mie goreng (Bukan instant)");
                rm2.setText("kwetiaw goreng");
                rm3.setText("kwetiaw goreng");
                rm3.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);

                rm1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (rm1.isChecked()){
                            e = 198.78;
                            p = 4.26;
                            l = 1.99;
                            k = 41.31;
                            Img.setImageResource(R.drawable.mie_goreng_bukan_instan);
                            namaMakanan.setText("kwetiaw goreng");
                        }else if(rm2.isChecked()){
                            e = 198.78;
                            p = 4.26;
                            l = 1.99;
                            k = 41.31;
                            Img.setImageResource(R.drawable.kwetiau_goreng);
                            namaMakanan.setText("Kwetiaw goreng");
                        }else if (rm3.isChecked()){
                            e = 198.78;
                            p = 4.26;
                            l = 1.99;
                            k = 41.31;
                            Img.setImageResource(R.drawable.bihun_goreng);
                            namaMakanan.setText("Bihun goreng");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("Centong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Centong ";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;


                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



//                        Save();
                    }
                });

                break;

        }

    }


    private void Save() {
        final String txt_email = email.toString().trim();
        final String makanan = namaMakanan.getText().toString().trim();
        final String jumlah = penampungProgres.toString().trim();
        final String ukuran = penampungUkuran.toString().trim();
        final String energi1 = hEnergiSort;
        final String protein1 = hProteinSort;
        final String lemak1 = hLemakSort;
        final String kalori1 = hKaloriSort;

        //parsing id kelas
//            final String sIdKelas = getIdKelas(ambilIDKelas);
        //final String sIdKelas = "100000";
        //final int saveIdKelas = Integer.parseInt(sIdKelas);

        StringRequest sR = new StringRequest(Request.Method.POST, ConfigUmum.URL_INSERT_PAGI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), SarapanActivity.class);
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
        //  Toast.makeText(getApplicationContext(), "Menambahkan makanan = " + makanan, Toast.LENGTH_LONG).show();
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
