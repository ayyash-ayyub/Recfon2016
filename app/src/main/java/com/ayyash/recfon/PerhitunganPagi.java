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

public class PerhitunganPagi extends AppCompatActivity {


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
    RadioGroup rg;
    RadioButton r1,r2,r3,r4,r5,r6,r7;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan_pagi);
       // tv = (TextView) findViewById(R.id.textView);
        satuan = (TextView) findViewById(R.id.textView2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        namaMakanan = (TextView) findViewById(R.id.textView3);
        btnKeluar = (Button) findViewById(R.id.batal);

        hitung = (Button) findViewById(R.id.hitung);
//        btnKeluar =(Button)findViewById(R.id.button3);
        satuan.setText(String.valueOf(progress));

        Img = (ImageView) findViewById(R.id.imageViewUye);
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

                Intent i = new Intent(PerhitunganPagi.this, SarapanActivity.class);
                startActivity(i);
                finish();
            }
        });


        Intent i = getIntent();
        indexMakanan = i.getIntExtra("indexMakanan", 0);
        nM = i.getStringExtra("namaMakanan");

//        tv.setText("index ke: " + indexMakanan);
        namaMakanan.setText("" + nM);


        switch (indexMakanan) {
            case 0:

                Img.setImageResource(R.drawable.nasi);

                rg.setVisibility(View.VISIBLE);

                r1.setText("Centong ");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 177.00;
                        double p = 3.90;
                        double l = 0.20;
                        double k = 39.95;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Centong ";

                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();


                    }
                });

                break;

            case 1:

                Img.setImageResource(R.drawable.nasi);

                rg.setVisibility(View.VISIBLE);

                r1.setText("Centong ");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 176;
                        double p = 3.65;
                        double l = 0.45;
                        double k = 38.1;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Centong ";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });

                break;

            case 2:

                Img.setImageResource(R.drawable.nasi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Centong ");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 198.78;
                        double p = 4.26;
                        double l = 1.99;
                        double k = 41.31;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Centong ";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
//                        Toast.makeText(getApplicationContext(),
//                                "Email: " + email +
//                                        "Makanan: " + namaMakanan.getText().toString().trim() +
//                                        "Jumlah: " + penampungProgres +
//                                        "Ukuran: " + penampungUkuran +
//                                        "Energi: " + hEnergiSort.toString().trim() +
//                                        "Protein: " + hProteinSort.toString().trim() +
//                                        "Lemak: " + hLemakSort.toString().trim() +
//                                        "Kalori: " + hKaloriSort.toString().trim() , Toast.LENGTH_LONG).show();
                    }
                });

                break;


            case 3:

                Img.setImageResource(R.drawable.nasi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Centong ");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 384.18;
                        double p = 7.56;
                        double l = 21.37;
                        double k = 38.82;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Centong Plastik";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();

                    }
                });

                break;

            case 4:
                Img.setImageResource(R.drawable.bubur_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 223.32;
                        double p = 13.00;
                        double l = 6.53;
                        double k = 29.19;

                        if (r1.isChecked()) {
                            urt = 300 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 5:
                Img.setImageResource(R.drawable.mie_instant_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Bungkus");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 459;
                        double p = 9.40;
                        double l = 20.00;
                        double k = 60.00;

                        if (r1.isChecked()) {
                            urt = 85 * pengali;
                            ukuran = "Bungkus";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;


            case 6:
                Img.setImageResource(R.drawable.mie_instant_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Bungkus");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 464.00;
                        double p = 10.10;
                        double l = 59.40;
                        double k = 1.40;

                        if (r1.isChecked()) {
                            urt = 70 * pengali;
                            ukuran = "Bungkus";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 7:
                Img.setImageResource(R.drawable.mie_goreng_bukan_instan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 156.74;
                        double p = 4.22;
                        double l = 11.93;
                        double k = 38.82;

                        if (r1.isChecked()) {
                            urt = 300 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 8:
                Img.setImageResource(R.drawable.madu);
                rg.setVisibility(View.VISIBLE);

                final String makanan = "Madu";


                r1.setText("Sendok makan");
                r2.setText("Sachet");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 294.00;
                        double p = 0.30;
                        double l = 0;
                        double k = 79.50;

                        if (r1.isChecked()) {
                            urt = 12 * pengali;
                            ukuran = "Sendok makan";
                        }
                        if (r2.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Sachet";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);





//                        Toast.makeText(getApplicationContext(),
//                                "Email: " + email +
//                                        "Makanan: " + makanan +
//                                        "Jumlah: " + penampungProgres +
//                                        "Ukuran: " + penampungUkuran +
//                                        "Energi: " + hEnergiSort +
//                                        "Protein: " + hProteinSort +
//                                        "Lemak: " + hLemakSort +
//                                        "Kalori: " + hKaloriSort, Toast.LENGTH_LONG).show();

                        Save();


                    }
                });
//                Img.setImageResource(R.drawable.kwetiau_goreng);
//                rg.setVisibility(View.VISIBLE);
//
//                r1.setText("Porsi");
//                r2.setVisibility(View.GONE);
//
//                hitung.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        double e = 144.59;
//                        double p = 3.47;
//                        double l = 11.20;
//                        double k = 7.63;
//
//                        if (r1.isChecked()) {
//                            urt = 275 * pengali;
//                            ukuran = "Porsi";
//                        }
//
//                        //JANGAN DIGANTI BAGIAN INI
//                        double hEnergi = (urt / 100) * e;
//                        double hProtein = (urt / 100) * p;
//                        double hLemak = (urt / 100) * l;
//                        double hKalori = (urt / 100) * k;
//
//                        //buat batasi digit coma
////                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
////                        double hProteinSort = Math.round(hProtein * 100) / 100;
////                        double hLemakSort = Math.round(hLemak * 100) / 100;
////                        double hKaloriSort = Math.round(hKalori * 100) / 100;
//
//                        hEnergiSort = df.format(hEnergi);
//                        hProteinSort = df.format(hProtein);
//                        hLemakSort = df.format(hLemak);
//                        hKaloriSort = df.format(hKalori);
//
//                        //JANGAN DIUTAK ATIK
//
//                        penampungProgres = String.valueOf(progress);
//                        penampungUkuran = String.valueOf(ukuran);
//
//
//
//                        Save();
//                    }
//                });

                break;

            case 9:
                Img.setImageResource(R.drawable.bihun_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 398.51;
                        double p = 3.23;
                        double l = 22.48;
                        double k = 6.62;

                        if (r1.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 10:
                Img.setImageResource(R.drawable.mie_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 169.66;
                        double p = 5.81;
                        double l = 11.20;
                        double k = 44.30;

                        if (r1.isChecked()) {
                            urt = 350 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 11:
                Img.setImageResource(R.drawable.spaghetti);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 343.41;
                        double p = 11.44;
                        double l = 17.99;
                        double k = 33.02;

                        if (r1.isChecked()) {
                            urt = 300 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 12:
                Img.setImageResource(R.drawable.lontong_sayur);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 150.79;
                        double p = 2.65;
                        double l = 7.43;
                        double k = 29.19;

                        if (r1.isChecked()) {
                            urt = 350 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 13:
                Img.setImageResource(R.drawable.lontong_isi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 210.17;
                        double p = 5.77;
                        double l = 6.13;
                        double k = 34.20;

                        if (r1.isChecked()) {
                            urt = 60 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 14:
                Img.setImageResource(R.drawable.roti_tawar);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);

                r1.setText("Lembar A");
                r2.setText("Lembar B");


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 361.83;
                        double p = 7.17;
                        double l = 9.32;
                        double k = 65.14;

                        if (r1.isChecked()) {
                            urt = 35 * pengali;
                            ukuran = "Lembar A";
                        } else if (r2.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Lembar B";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;


            case 15:
                Img.setImageResource(R.drawable.ubi_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 176.95;
                        double p = 2.71;
                        double l = 3.25;
                        double k = 35.29;

                        if (r1.isChecked()) {
                            urt = 75 * pengali;
                            ukuran = "Potong";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 16:
                Img.setImageResource(R.drawable.ubi_rebus);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");
                r3.setText("Buah kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 88.00;
                        double p = 0.40;
                        double l = 0.40;
                        double k = 20.60;

                        if (r1.isChecked()) {
                            urt = 150 * pengali;
                            ukuran = "Buah sedang";
                        } else if (r2.isChecked()) {
                            urt = 200 * pengali;
                            ukuran = "Buah sedang";
                        } else if (r3.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Buah kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 17:
                Img.setImageResource(R.drawable.perkedel_kentang);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah besar");
                r2.setText("Buah sedang");
                r3.setText("Buah kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 126.02;
                        double p = 3.86;
                        double l = 7.57;
                        double k = 10.44;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Buah sedang";
                        }else if (r2.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Buah besar";
                        }else if (r3.isChecked()){
                            urt = 10 * pengali;
                            ukuran = "Buah Kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 18:
                Img.setImageResource(R.drawable.kentang_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Piring");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 316.00;
                        double p = 3.80;
                        double l = 16.10;
                        double k = 39.00;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Piring";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 19:
                Img.setImageResource(R.drawable.singkong_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 175.53;
                        double p = 0.97;
                        double l = 3.07;
                        double k = 35.78;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 20:
                Img.setImageResource(R.drawable.singkong_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong besar");
                r2.setText("Potong kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 154.00;
                        double p = 1.00;
                        double l = 0.30;
                        double k = 36.80;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Potong besar";
                        }
                        if (r2.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Potong kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 21:
                Img.setImageResource(R.drawable.tape_tapai_singkong);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 169.00;
                        double p = 1.40;
                        double l = 0.30;
                        double k = 40.20;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 22:
                Img.setImageResource(R.drawable.labu_siam_masak_santan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok sayur");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 80.91;
                        double p = 1.49;
                        double l = 5.57;
                        double k = 7.11;

                        if (r1.isChecked()) {
                            urt = 55 * pengali;
                            ukuran = "Sendok sayur";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 23:
                Img.setImageResource(R.drawable.gulai_daun_singkong);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Mangkok");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 131.62;
                        double p = 3.75;
                        double l = 9.11;
                        double k = 9.42;

                        if (r1.isChecked()) {
                            urt = 200 * pengali;
                            ukuran = "Mangkok";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 24:
                Img.setImageResource(R.drawable.sayur_lodeh);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok sayur");
                r2.setText("Mangkok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 102.30;
                        double p = 2.72;
                        double l = 5.57;
                        double k = 7.11;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Sendok sayur";
                        }
                        if (r2.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Mangkok";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 25:
                Img.setImageResource(R.drawable.pare_masak_santan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok sayur");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 120.22;
                        double p = 2.02;
                        double l = 10.32;
                        double k = 5.99;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Sendok sayur";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 26:
                Img.setImageResource(R.drawable.cap_cay);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 154.63;
                        double p = 5.04;
                        double l = 13.16;
                        double k = 3.99;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 27:
                Img.setImageResource(R.drawable.tumis_kacang_panjang);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setText("Piring");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 174.38;
                        double p = 1.93;
                        double l = 16.05;
                        double k = 4.45;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "sendok makan";
                        }
                        if (r2.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Piring";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 28:
                Img.setImageResource(R.drawable.tumis_kangkung);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setText("Piring");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 167.53;
                        double p = 2.26;
                        double l = 16.96;
                        double k = 3.48;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan";
                        }
                        if (r2.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Piring";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
                        double hProteinSort = Math.round(hProtein * 100) / 100;
                        double hLemakSort = Math.round(hLemak * 100) / 100;
                        double hKaloriSort = Math.round(hKalori * 100) / 100;


                    }
                });
                break;

            case 29:
                Img.setImageResource(R.drawable.tumis_sawi_hijau);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setText("Piring");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 125.47;
                        double p = 1.42;
                        double l = 12.49;
                        double k = 3.24;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Sendok makan";
                        }
                        if (r2.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Piring";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 30:
                Img.setImageResource(R.drawable.tumis_sawi_putih);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setText("Sendok sayur");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 110.92;
                        double p = 0.79;
                        double l = 11.82;
                        double k = 1.35;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan";
                        }
                        if (r2.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Sendok sayur";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 31:
                Img.setImageResource(R.drawable.terong_balado);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setText("Potong");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 80.20;
                        double p = 1.41;
                        double l = 5.54;
                        double k = 7.31;

                        if (r1.isChecked()) {
                            urt = 90 * pengali;
                            ukuran = "Porsi";
                        }
                        if (r2.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Potong";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 32:
                Img.setImageResource(R.drawable.sayur_sop);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok sayur");
                r2.setText("Mangkok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 44.00;
                        double p = 1.65;
                        double l = 0.36;
                        double k = 9.43;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Sendok sayur";
                        }
                        if (r2.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Mangkok";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 33:
                Img.setImageResource(R.drawable.sayur_bayam_bening);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok sayur");
                r2.setText("Mangkok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 72.57;
                        double p = 2.71;
                        double l = 0.53;
                        double k = 15.25;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Sendok sayur";
                        }
                        if (r2.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Mangkok";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 34:
                Img.setImageResource(R.drawable.sayur_asem);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok sayur");
                r2.setText("Mangkok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 157.20;
                        double p = 7.43;
                        double l = 6.96;
                        double k = 19.01;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Sendok sayur";
                        }
                        if (r2.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Mangkok";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 35:
                Img.setImageResource(R.drawable.sayur_urap);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setText("Porsi");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 81.75;
                        double p = 2.62;
                        double l = 5.32;
                        double k = 7.42;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Sendok makan";
                        }
                        if (r2.isChecked()) {
                            urt = 150 * pengali;
                            ukuran = "Porsi";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 36:
                Img.setImageResource(R.drawable.sayur_pecel);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 309.79;
                        double p = 13.20;
                        double l = 24.61;
                        double k = 13.49;

                        if (r1.isChecked()) {
                            urt = 130 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 37:
                Img.setImageResource(R.drawable.gado_gado);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setText("Sendok makan");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 153.15;
                        double p = 6.43;
                        double l = 10.61;
                        double k = 10.16;

                        if (r1.isChecked()) {
                            urt = 200 * pengali;
                            ukuran = "Porsi";
                        }
                        if (r2.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Sendok makan";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 38:
                Img.setImageResource(R.drawable.ketoprak);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 335.95;
                        double p = 11.84;
                        double l = 17.50;
                        double k = 35.38;

                        if (r1.isChecked()) {
                            urt = 435 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 39:
                Img.setImageResource(R.drawable.sawi_hijau_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setText("Sendok sayur");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 22.00;
                        double p = 1.87;
                        double l = 0.44;
                        double k = 3.74;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan";
                        }
                        if (r2.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Sendok sayur";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 40:
                Img.setImageResource(R.drawable.daun_singkong_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setText("Sendok sayur");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 72.00;
                        double p = 5.44;
                        double l = 0.96;
                        double k = 10.40;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan";
                        }
                        if (r2.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Sendok sayur";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 41:
                Img.setImageResource(R.drawable.jengkol_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 192.00;
                        double p = 5.40;
                        double l = 0.30;
                        double k = 40.70;

                        if (r1.isChecked()) {
                            urt = 24 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 42:
                Img.setImageResource(R.drawable.selada_bokor);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Lembar");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 18.00;
                        double p = 1.20;
                        double l = 0.20;
                        double k = 2.90;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Lembar";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 43:
                Img.setImageResource(R.drawable.ketimun);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 8.00;
                        double p = 0.20;
                        double l = 0.20;
                        double k = 1.40;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 44:
                Img.setImageResource(R.drawable.terung_belanda);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah d-5cm");
                r2.setText("Buah -4cm");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 52.00;
                        double p = 2.10;
                        double l = 1.10;
                        double k = 8.60;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Buah d-5cm";
                        }
                        if (r2.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Buah -4cm";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 45:
                Img.setImageResource(R.drawable.tomat);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah besar");
                r2.setText("Buah sedang");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 24.00;
                        double p = 1.30;
                        double l = 0.50;
                        double k = 4.70;

                        if (r1.isChecked()) {
                            urt = 120 * pengali;
                            ukuran = "Buah besar";
                        }
                        if (r2.isChecked()) {
                            urt = 90 * pengali;
                            ukuran = "Buah sedang";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 46:
                Img.setImageResource(R.drawable.pare_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 25;
                        double p = 2.3;
                        double l = 0.2;
                        double k = 3.5;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 47:
                Img.setImageResource(R.drawable.pisang);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 108.00;
                        double p = 1.00;
                        double l = 0.80;
                        double k = 24.30;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 48:
                Img.setImageResource(R.drawable.mangga);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 52.00;
                        double p = 0.70;
                        double l = 0;
                        double k = 12.30;

                        if (r1.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Buah sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 350 * pengali;
                            ukuran = "Buah besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 49:
                Img.setImageResource(R.drawable.alpukat);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 85.00;
                        double p = 0.90;
                        double l = 6.50;
                        double k = 7.70;

                        if (r1.isChecked()) {
                            urt = 130 * pengali;
                            ukuran = "Buah sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 170 * pengali;
                            ukuran = "Buah besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 50:
                Img.setImageResource(R.drawable.jeruk);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 45.00;
                        double p = 0.90;
                        double l = 0.20;
                        double k = 11.20;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Buah sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 110 * pengali;
                            ukuran = "Buah besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 51:
                Img.setImageResource(R.drawable.pepaya);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong panjang");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 46.00;
                        double p = 0.50;
                        double l = 0.10;
                        double k = 12.20;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Potong panjang";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 52:
                Img.setImageResource(R.drawable.nanas);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong panjang");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 40.00;
                        double p = 0.60;
                        double l = 0.30;
                        double k = 9.90;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Potong panjang";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 53:
                Img.setImageResource(R.drawable.semangka);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 28.00;
                        double p = 0.50;
                        double l = 0.20;
                        double k = 6.90;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 54:
                Img.setImageResource(R.drawable.melon);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong panjang");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 31.00;
                        double p = 0.30;
                        double l = 0.30;
                        double k = 6.70;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Potong panjang";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 55:
                Img.setImageResource(R.drawable.pir);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 34.00;
                        double p = 0.50;
                        double l = 0.20;
                        double k = 7.60;

                        if (r1.isChecked()) {
                            urt = 120 * pengali;
                            ukuran = "Buah sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 200 * pengali;
                            ukuran = "Buah besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 56:
                Img.setImageResource(R.drawable.apel);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 58.00;
                        double p = 0.30;
                        double l = 0.40;
                        double k = 14.90;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Buah sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 200 * pengali;
                            ukuran = "Buah besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 57:
                Img.setImageResource(R.drawable.anggur);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 68.00;
                        double p = 0.80;
                        double l = 0;
                        double k = 16.30;

                        if (r1.isChecked()) {
                            urt = 12 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 58:
                Img.setImageResource(R.drawable.buah_naga);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong sedang");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 31.00;
                        double p = 0.30;
                        double l = 0.30;
                        double k = 6.70;

                        if (r1.isChecked()) {
                            urt = 80 * pengali;
                            ukuran = "Potong sedang";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 59:
                Img.setImageResource(R.drawable.duku);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 63.00;
                        double p = 1.00;
                        double l = 0.20;
                        double k = 16.10;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 60:
                Img.setImageResource(R.drawable.durian_lokal);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Biji durian lokal");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 149.00;
                        double p = 2.50;
                        double l = 3.00;
                        double k = 21.50;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Biji durian lokal";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 61:
                Img.setImageResource(R.drawable.durian_montong);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Biji durian montong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 149.00;
                        double p = 2.50;
                        double l = 3.00;
                        double k = 21.50;

                        if (r1.isChecked()) {
                            urt = 150 * pengali;
                            ukuran = "Biji durian montong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 62:
                Img.setImageResource(R.drawable.jambu_biji);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 49.00;
                        double p = 0.90;
                        double l = 0.30;
                        double k = 12.20;

                        if (r1.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Buah sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 300 * pengali;
                            ukuran = "Buah besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 63:
                Img.setImageResource(R.drawable.kurma);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Biji");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 296.00;
                        double p = 2.50;
                        double l = 0;
                        double k = 71.50;

                        if (r1.isChecked()) {
                            urt = 8 * pengali;
                            ukuran = "Biji";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 64:
                Img.setImageResource(R.drawable.rambutan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 69.00;
                        double p = 0.90;
                        double l = 0.10;
                        double k = 18.10;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 65:
                Img.setImageResource(R.drawable.salak);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 77.00;
                        double p = 0.40;
                        double l = 0.10;
                        double k = 2.90;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Buah sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 70 * pengali;
                            ukuran = "Buah besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 66:
                Img.setImageResource(R.drawable.strawberry);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 23.00;
                        double p = 1.70;
                        double l = 0.10;
                        double k = 2.70;

                        if (r1.isChecked()) {
                            urt = 17 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 67:
                Img.setImageResource(R.drawable.belimbing);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 36;
                        double p = 0.4;
                        double l = 0.4;
                        double k = 8.8;

                        if (r1.isChecked()) {
                            urt = 150 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
                        double hProteinSort = Math.round(hProtein * 100) / 100;
                        double hLemakSort = Math.round(hLemak * 100) / 100;
                        double hKaloriSort = Math.round(hKalori * 100) / 100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 68:
                Img.setImageResource(R.drawable.nangka);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Biji sedang");
                r2.setText("Biji besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 106;
                        double p = 1.2;
                        double l = 0.3;
                        double k = 27.6;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Biji sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 35 * pengali;
                            ukuran = "Biji besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 69:
                Img.setImageResource(R.drawable.salad_buah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 201.42;
                        double p = 1.65;
                        double l = 17.86;
                        double k = 9.09;

                        if (r1.isChecked()) {
                            urt = 300 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 70:
                Img.setImageResource(R.drawable.tempe_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong besar");
                r2.setText("Potong segitiga");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 220.82;
                        double p = 12.73;
                        double l = 16.09;
                        double k = 8.27;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Potong besar";
                        }
                        if (r2.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Potong sedang";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 71:
                Img.setImageResource(R.drawable.tempe_goreng_tepung);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 329.79;
                        double p = 13.33;
                        double l = 18.06;
                        double k = 32.17;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 72:
                Img.setImageResource(R.drawable.tahu_goreng);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");
                r3.setText("Buah kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 164.90;
                        double p = 9.81;
                        double l = 14.23;
                        double k = 0.72;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Buah sedang";
                        } else if (r2.isChecked()) {
                            urt = 80 * pengali;
                            ukuran = "Buah besar";
                        } else if (r3.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Buah kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 73:
                Img.setImageResource(R.drawable.tahu_isi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 128.02;
                        double p = 8.97;
                        double l = 7.29;
                        double k = 8.65;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 74:
                Img.setImageResource(R.drawable.tempe_orek);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 185.11;
                        double p = 12.67;
                        double l = 10.75;
                        double k = 11.61;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Sendok makan";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 75:
                Img.setImageResource(R.drawable.tahu_pepes);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Bungkus");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 95.82;
                        double p = 10.07;
                        double l = 6.22;
                        double k = 1.59;

                        if (r1.isChecked()) {
                            urt = 85 * pengali;
                            ukuran = "Bungkus";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 76:
                Img.setImageResource(R.drawable.tahu_bacem);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 305.08;
                        double p = 7.59;
                        double l = 26.87;
                        double k = 8.64;

                        if (r1.isChecked()) {
                            urt = 36 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 77:
                Img.setImageResource(R.drawable.tempe_bacem);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 351.75;
                        double p = 9.65;
                        double l = 28.87;
                        double k = 14.17;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 78:
                Img.setImageResource(R.drawable.telur_ayam_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Butir");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 154.00;
                        double p = 12.40;
                        double l = 10.80;
                        double k = 0.70;

                        if (r1.isChecked()) {
                            urt = 60 * pengali;
                            ukuran = "Butir";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 79:
                Img.setImageResource(R.drawable.telur_dadar);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Butir");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 191.80;
                        double p = 11.80;
                        double l = 15.15;
                        double k = 0.67;

                        if (r1.isChecked()) {
                            urt = 60 * pengali;
                            ukuran = "Butir";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 80:
                Img.setImageResource(R.drawable.telur_asin_bebek);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Butir");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 187.00;
                        double p = 10.90;
                        double l = 12.40;
                        double k = 7.90;

                        if (r1.isChecked()) {
                            urt = 70 * pengali;
                            ukuran = "Butir";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 81:
                Img.setImageResource(R.drawable.soto_makassar);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 123.02;
                        double p = 16.37;
                        double l = 6.25;
                        double k = 0.89;

                        if (r1.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 82:
                Img.setImageResource(R.drawable.gulai_sapi_kambing);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 134.27;
                        double p = 7.73;
                        double l = 9.34;
                        double k = 5.12;

                        if (r1.isChecked()) {
                            urt = 220 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 83:
                Img.setImageResource(R.drawable.rawon_daging);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 208.00;
                        double p = 16.08;
                        double l = 15.24;
                        double k = 1.12;

                        if (r1.isChecked()) {
                            urt = 285 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 84:
                Img.setImageResource(R.drawable.tongseng_sapi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 201.30;
                        double p = 13.02;
                        double l = 13.87;
                        double k = 5.08;

                        if (r1.isChecked()) {
                            urt = 350 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 85:
                Img.setImageResource(R.drawable.sate_kambing);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Tusuk");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 332.00;
                        double p = 21.73;
                        double l = 22.84;
                        double k = 13.43;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Tusuk";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 86:
                Img.setImageResource(R.drawable.steak_daging);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 210.47;
                        double p = 17.00;
                        double l = 12.38;
                        double k = 6.86;

                        if (r1.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 87:
                Img.setImageResource(R.drawable.bakso_sapi);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("Buah sedang");
                r2.setText("Buah besar");
                r3.setText("Buah kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 197.00;
                        double p = 21.00;
                        double l = 9.00;
                        double k = 8.00;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Buah sedang";
                        } else if (r2.isChecked()) {
                            urt = 90 * pengali;
                            ukuran = "Buah besar";
                        } else if (r3.isChecked()) {
                            urt = 5 * pengali;
                            ukuran = "Buah kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 88:
                Img.setImageResource(R.drawable.sosis_sapi_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah sosis sedang");
                r2.setText("Buah sosis besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 380.75;
                        double p = 12.55;
                        double l = 31.83;
                        double k = 11.00;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Buah sosis sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 70 * pengali;
                            ukuran = "Buah sosis besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 89:
                Img.setImageResource(R.drawable.sosis_sapi_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah sosis sedang");
                r2.setText("Buah sosis besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 287.00;
                        double p = 15.63;
                        double l = 20.80;
                        double k = 9.28;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Buah sosis sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 70 * pengali;
                            ukuran = "Buah sosis besar";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 90:
                Img.setImageResource(R.drawable.opor_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 302.43;
                        double p = 15.77;
                        double l = 27.29;
                        double k = 1.26;

                        if (r1.isChecked()) {
                            urt = 140 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 91:
                Img.setImageResource(R.drawable.sate_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Tusuk");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 511.86;
                        double p = 30.07;
                        double l = 42.56;
                        double k = 4.97;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Tusuk";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 92:
                Img.setImageResource(R.drawable.soto_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 251.28;
                        double p = 15.78;
                        double l = 19.85;
                        double k = 2.20;

                        if (r1.isChecked()) {
                            urt = 300 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 93:
                Img.setImageResource(R.drawable.bebek_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah potong dada");
                r2.setText("Buah potong paha");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 376.27;
                        double p = 14.55;
                        double l = 35.09;
                        double k = 0;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Buah potong dada";
                        }
                        if (r2.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Buah potong paha";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 94:
                Img.setImageResource(R.drawable.nugget_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 134.73;
                        double p = 6.78;
                        double l = 9.00;
                        double k = 5.81;

                        if (r1.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 95:
                Img.setImageResource(R.drawable.ayam_goreng_dada);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong dada atas");
                r2.setText("Potong dada bawah");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 294.6422018;
                        double p = 27.00917431;
                        double l = 19.706422;
                        double k = 2.788990826;

                        if (r1.isChecked()) {
                            urt = 60 * pengali;
                            ukuran = "Potong dada atas";
                        }
                        if (r2.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Potong dada bawah";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 96:
                Img.setImageResource(R.drawable.ayam_goreng_sayap);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong sayap");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 401.7981651;
                        double p = 25.10091743;
                        double l = 33.6513761;
                        double k = 0;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Potong sayap";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 97:
                Img.setImageResource(R.drawable.ayam_goreng_paha);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong paha atas");
                r2.setText("Potong paha bawah");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 395.93;
                        double p = 24.95;
                        double l = 33.06;
                        double k = 0;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Potong paha atas";
                        }
                        if (r2.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Potong paha bawah";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();


                    }
                });
                break;

            case 98:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_dada_atas);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong dada atas");
                r2.setText("Potong dada mentok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 0;
                        double p = 0;
                        double l = 0;
                        double k = 0;

                        if (r1.isChecked()) {
                                e = 264;
                             p = 21.2;
                             l = 16.6;
                             k = 8.5;
                            urt = 100 * pengali;
                            ukuran = "Potong dada atas ";
                        }
                        if (r2.isChecked()) {
                             e = 153;
                             p = 27.3;
                             l = 4.8;
                             k = 0.3;
                            urt = 170 * pengali;
                            ukuran = "Potong dada bawah";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 99:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_dada_atas_tanpa_kulit);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong dada atas");
                r2.setText("Potong dada mentok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 0;
                        double p = 0;
                        double l = 0;
                        double k = 0;
                        if (r1.isChecked()) {
                             e = 264;
                             p = 21.2;
                             l = 16.6;
                             k = 8.5;
                            urt = 100 * pengali;
                            ukuran = "Potong dada atas ";
                        }
                        if (r2.isChecked()) {
                             e = 153;
                             p = 27.3;
                             l = 4.8;
                             k = 0.3;
                            urt = 170 * pengali;
                            ukuran = "Potong dada bawah";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 100:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_sayap);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        double e = 337;
//                        double p = 20.8;
//                        double l = 23;
//                        double k = 11.7;
//
//                        if (r1.isChecked()) {
//                            urt = 60 * pengali;
//                            ukuran = "Potong";
//                        }
                        double e = 0;
                        double p = 0;
                        double l = 0;
                        double k = 0;
                        if (r1.isChecked()) {
                             e = 337;
                             p = 20.8;
                             l = 23;
                             k = 11.7;

                            urt = 60 * pengali;
                            ukuran = "Potong";
                        }
                        if (r2.isChecked()) {
                             e = 236;
                             p = 28.7;
                             l = 12.1;
                             k = 3;

                            urt = 60 * pengali;
                            ukuran = "Potong";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 101:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_sayap_tanpa_kulit);
                rg.setVisibility(View.VISIBLE);

                r2.setText("Potong");
                r1.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        double e = 236;
//                        double p = 28.7;
//                        double l = 12.1;
//                        double k = 3;
//
//                        if (r1.isChecked()) {
//                            urt = 60 * pengali;
//                            ukuran = "Potong";
//                        }
                        double e = 0;
                        double p = 0;
                        double l = 0;
                        double k = 0;
                        if (r1.isChecked()) {
                             e = 337;
                             p = 20.8;
                             l = 23;
                             k = 11.7;

                            urt = 60 * pengali;
                            ukuran = "Potong";
                        }
                        if (r2.isChecked()) {
                             e = 236;
                             p = 28.7;
                             l = 12.1;
                             k = 3;

                            urt = 60 * pengali;
                            ukuran = "Potong ";
                        }

                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 102:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_paha_bawah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        double e = 274;
//                        double p = 20.6;
//                        double l = 17.7;
//                        double k = 8;
//
//                        if (r1.isChecked()) {
//                            urt = 70 * pengali;
//                            ukuran = "Potong";
//                        }
                        double e = 0;
                        double p = 0;
                        double l = 0;
                        double k = 0;
                        if (r1.isChecked()) {
                             e = 274;
                             p = 20.6;
                             l = 17.7;
                             k = 8;

                            urt = 70 * pengali;
                            ukuran = "Potong";
                        }
                        if (r2.isChecked()) {
                             e = 170;
                             p = 25.6;
                             l = 7.4;
                             k = 0;

                            urt = 70 * pengali;
                            ukuran = "Potong";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 103:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_paha_bawah_tanpa_kulit);
                rg.setVisibility(View.VISIBLE);

                r2.setText("Potong");
                r1.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        double e = 170;
//                        double p = 25.6;
//                        double l = 7.4;
//                        double k = 0;
//
//                        if (r1.isChecked()) {
//                            urt = 70 * pengali;
//                            ukuran = "Potong";
//                        }
                        double e = 0;
                        double p = 0;
                        double l = 0;
                        double k = 0;
                        if (r1.isChecked()) {
                             e = 274;
                             p = 20.6;
                             l = 17.7;
                             k = 8;

                            urt = 70 * pengali;
                            ukuran = "Potong";
                        }
                        if (r2.isChecked()) {
                             e = 170;
                             p = 25.6;
                             l = 7.4;
                             k = 0;

                            urt = 70 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 104:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_paha_atas);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        double e = 309;
//                        double p = 17.2;
//                        double l = 22.1;
//                        double k = 10.3;
//
//                        if (r1.isChecked()) {
//                            urt = 100 * pengali;
//                            ukuran = "Potong";
//                        }
                        double e = 0;
                        double p = 0;
                        double l = 0;
                        double k = 0;
                        if (r1.isChecked()) {
                             e = 309;
                             p = 17.2;
                             l = 22.1;
                             k = 10.3;

                            urt = 100 * pengali;
                            ukuran = "Potong";
                        }
                        if (r2.isChecked()) {
                             e = 179;
                             p = 22.4;
                             l = 10;
                             k = 0;

                            urt = 100 * pengali;
                            ukuran = "Potong";
                        }



                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 105:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_paha_atas_tanpa_kulit);
                rg.setVisibility(View.VISIBLE);

                r2.setText("Potong");
                r1.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        double e = 179;
//                        double p = 22.4;
//                        double l = 10;
//                        double k = 0;
//
//                        if (r1.isChecked()) {
//                            urt = 100 * pengali;
//                            ukuran = "Potong";
//                        }
                        double e = 0;
                        double p = 0;
                        double l = 0;
                        double k = 0;
                        if (r1.isChecked()) {
                             e = 309;
                             p = 17.2;
                             l = 22.1;
                             k = 10.3;

                            urt = 100 * pengali;
                            ukuran = "Potong";
                        }
                        if (r2.isChecked()) {
                             e = 179;
                             p = 22.4;
                             l = 10;
                             k = 0;

                            urt = 100 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 106:
                Img.setImageResource(R.drawable.tongkol_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 211.04;
                        double p = 11.76;
                        double l = 15.45;
                        double k = 6.87;

                        if (r1.isChecked()) {
                            urt = 60 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 107:
                Img.setImageResource(R.drawable.lele_goreng);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("Ekor besar");
                r2.setText("Ekor sedang");
                r3.setText("Ekor kecil");


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 399.69;
                        double p = 33.78;
                        double l = 28.80;
                        double k = 2.22;

                        if (r1.isChecked()) {
                            urt = 70 * pengali;
                            ukuran = "Ekor besar";
                        } else if (r2.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Ekor sedang";
                        } else if (r3.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Ekor kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 108:
                Img.setImageResource(R.drawable.mujair_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Ekor sedang");
                r2.setText("Ekor kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 234.22;
                        double p = 24.29;
                        double l = 14.72;
                        double k = 0;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Ekor sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 25 * pengali;
                            ukuran = "Ekor kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 109:
                Img.setImageResource(R.drawable.teri_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 232.02;
                        double p = 28.63;
                        double l = 12.42;
                        double k = 0;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 110:
                Img.setImageResource(R.drawable.ikan_bakar);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 123;
                        double p = 20;
                        double l = 4.8;
                        double k = 0;

                        if (r1.isChecked()) {
                            urt = 175 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 111:
                Img.setImageResource(R.drawable.ikan_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Ekor sedang");
                r2.setText("Ekor kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 135.54;
                        double p = 16.56;
                        double l = 3.99;
                        double k = 8.16;

                        if (r1.isChecked()) {
                            urt = 130 * pengali;
                            ukuran = "Ekor sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Ekor kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 112:
                Img.setImageResource(R.drawable.udang_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Ekor sedang");
                r2.setText("Ekor kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 238.84;
                        double p = 13.79;
                        double l = 20.08;
                        double k = 1.45;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Ekor sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 6 * pengali;
                            ukuran = "Ekor kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 113:
                Img.setImageResource(R.drawable.udang_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Ekor sedang");
                r2.setText("Ekor kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 84.00;
                        double p = 17.10;
                        double l = 0.90;
                        double k = 1.80;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Ekor sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 6 * pengali;
                            ukuran = "Ekor kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 114:
                Img.setImageResource(R.drawable.cumi_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Cumi sotong sedang");
                r2.setText("Cumi sotong kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 75.00;
                        double p = 16.10;
                        double l = 0.70;
                        double k = 0.10;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Cumi potong sedang";
                        }
                        if (r2.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Cumi sotong kecil";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 115:
                Img.setImageResource(R.drawable.cumi_goreng_tepung);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 278.27;
                        double p = 11.31;
                        double l = 19.92;
                        double k = 14.33;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 116:
                Img.setImageResource(R.drawable.hati_ayam_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 248.07;
                        double p = 116.17;
                        double l = 37.63;
                        double k = 5.19;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 117:
                Img.setImageResource(R.drawable.rempela_ayam_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 181.44;
                        double p = 14.81;
                        double l = 12.68;
                        double k = 1.17;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 118:
                Img.setImageResource(R.drawable.usus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Tusuk");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 403.34;
                        double p = 14.40;
                        double l = 38.70;
                        double k = 0;

                        if (r1.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Tusuk";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 119:
                Img.setImageResource(R.drawable.kacang_tanah_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Piring");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 525.00;
                        double p = 27.90;
                        double l = 42.70;
                        double k = 17.40;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Piring";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 120:
                Img.setImageResource(R.drawable.kacang_atom);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Genggam");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 533.30;
                        double p = 6.70;
                        double l = 28.90;
                        double k = 57.80;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Genggam";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 121:
                Img.setImageResource(R.drawable.bubur_kacang_hijau);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 176.68;
                        double p = 5.92;
                        double l = 8.95;
                        double k = 21.27;

                        if (r1.isChecked()) {
                            urt = 150 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 122:
                Img.setImageResource(R.drawable.risoles);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 261.57;
                        double p = 6.81;
                        double l = 17.66;
                        double k = 20.00;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 123:
                Img.setImageResource(R.drawable.lemper);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 250.17;
                        double p = 8.22;
                        double l = 12.73;
                        double k = 25.33;

                        if (r1.isChecked()) {
                            urt = 35 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 124:
                Img.setImageResource(R.drawable.bakpia);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 272.00;
                        double p = 8.70;
                        double l = 6.70;
                        double k = 44.10;

                        if (r1.isChecked()) {
                            urt = 25 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 125:
                Img.setImageResource(R.drawable.pisang_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 233.80;
                        double p = 3.32;
                        double l = 9.13;
                        double k = 38.60;

                        if (r1.isChecked()) {
                            urt = 60 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 126:
                Img.setImageResource(R.drawable.perkedel_jagung);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 224.06;
                        double p = 5.19;
                        double l = 8.09;
                        double k = 34.66;

                        if (r1.isChecked()) {
                            urt = 65 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 127:
                Img.setImageResource(R.drawable.bakwan_sayuran);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 214.10;
                        double p = 3.55;
                        double l = 12.64;
                        double k = 23.75;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 128:
                Img.setImageResource(R.drawable.roti_manis_isi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 267.00;
                        double p = 8.00;
                        double l = 0;
                        double k = 48.00;

                        if (r1.isChecked()) {
                            urt = 75 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 129:
                Img.setImageResource(R.drawable.bakwan_sayuran);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 427.19;
                        double p = 6.39;
                        double l = 18.13;
                        double k = 62.11;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 130:
                Img.setImageResource(R.drawable.kue_bolu_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 241.18;
                        double p = 4.74;
                        double l = 1.91;
                        double k = 51.64;

                        if (r1.isChecked()) {
                            urt = 35 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 131:
                Img.setImageResource(R.drawable.martabak_telur);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 274.55;
                        double p = 8.88;
                        double l = 17.23;
                        double k = 22.34;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 132:
                Img.setImageResource(R.drawable.martabak_manis);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 336.34;
                        double p = 8.85;
                        double l = 6.45;
                        double k = 63.48;

                        if (r1.isChecked()) {
                            urt = 55 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 133:
                Img.setImageResource(R.drawable.batagor);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 244.21;
                        double p = 8.39;
                        double l = 16.23;
                        double k = 17.21;

                        if (r1.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 134:
                Img.setImageResource(R.drawable.pempek);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("Buah kapal selam");
                r2.setText("Buah lenjer");
                r3.setText("Buah kulit");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 318.79;
                        double p = 6.70;
                        double l = 6.87;
                        double k = 56.78;

                        if (r1.isChecked()) {
                            urt = 85 * pengali;
                            ukuran = "Buah kapal salam";
                        } else if (r2.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Buah lenjer";
                        } else if (r3.isChecked()) {
                            urt = 76 * pengali;
                            ukuran = "Buah kulit";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 135:
                Img.setImageResource(R.drawable.siomay_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 319.38;
                        double p = 4.65;
                        double l = 0.52;
                        double k = 71.67;

                        if (r1.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 136:
                Img.setImageResource(R.drawable.cilok);
                rg.setVisibility(View.VISIBLE);

                r1.setText("2000 rupiah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 232.00;
                        double p = 3.37;
                        double l = 0.50;
                        double k = 55.07;

                        if (r1.isChecked()) {
                            urt = 54 * pengali;
                            ukuran = "2000 rupiah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 137:
                Img.setImageResource(R.drawable.cireng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 300.11;
                        double p = 6.39;
                        double l = 18.52;
                        double k = 29.92;

                        if (r1.isChecked()) {
                            urt = 22 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 138:
                Img.setImageResource(R.drawable.comro);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 257.03;
                        double p = 2.98;
                        double l = 15.00;
                        double k = 18.57;

                        if (r1.isChecked()) {
                            urt = 45 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });


                break;

            case 139:
                Img.setImageResource(R.drawable.seblak_basah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 185.33;
                        double p = 9.20;
                        double l = 8.18;
                        double k = 18.73;

                        if (r1.isChecked()) {
                            urt = 220 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 140:
                Img.setImageResource(R.drawable.wafer);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Keping");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 500;
                        double p = 5.00;
                        double l = 25.00;
                        double k = 65.00;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Keping";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 141:
                Img.setImageResource(R.drawable.wafer_roll_astor);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Roll");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 4.67;
                        double p = 6.70;
                        double l = 16.70;
                        double k = 73.30;

                        if (r1.isChecked()) {
                            urt = 5 * pengali;
                            ukuran = "Roll";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });

                break;

            case 142:
                Img.setImageResource(R.drawable.biskuit_crackers_malkist);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Keping");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 444.00;
                        double p = 11.10;
                        double l = 16.70;
                        double k = 72.20;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "keping";

                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });


                break;

            case 143:
                Img.setImageResource(R.drawable.biskuit_coklat);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Keping");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 474.00;
                        double p = 5.30;
                        double l = 21.10;
                        double k = 68.40;

                        if (r1.isChecked()) {
                            urt = 6 * pengali;
                            ukuran = "Keping";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });


                break;

            case 144:
                Img.setImageResource(R.drawable.biskuit_krim);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Keping");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 476;
                        double p = 3.40;
                        double l = 20.40;
                        double k = 71.40;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Keping";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });


                break;

            case 145:
                Img.setImageResource(R.drawable.biskuit_selai);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Keping");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 438.00;
                        double p = 6.30;
                        double l = 12.50;
                        double k = 75.00;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Keping";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });

                break;

            case 146:
                Img.setImageResource(R.drawable.coklat_batang);

                rg.setVisibility(View.VISIBLE);
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);
                r5.setVisibility(View.VISIBLE);
                r6.setVisibility(View.VISIBLE);
                r7.setVisibility(View.VISIBLE);

                r1.setText("Chunky Bar");
                r2.setText("SilverQueen Midi");
                r3.setText("SilverQueen Jumbo");
                r4.setText("SilverQueen Chunky");
                r5.setText("Cadbury kecil");
                r6.setText("Cadbury sedang");
                r7.setText("Cadbury besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 567.00;
                        double p = 10.00;
                        double l = 33.30;
                        double k = 53.30;

                        if (r1.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Chunky Bar";
                        } else if (r2.isChecked()) {
                            urt = 33 * pengali;
                            ukuran = "SilverQueen Midi";
                        } else if (r3.isChecked()) {
                            urt = 68 * pengali;
                            ukuran = "SilverQueen Jumbo";
                        } else if (r4.isChecked()) {
                            urt = 36 * pengali;
                            ukuran = "SilverQueen Chunky";
                        } else if (r5.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Cadbury kecil";
                        } else if (r6.isChecked()) {
                            urt = 65 * pengali;
                            ukuran = "Cadbury sedang";
                        } else if (r7.isChecked()) {
                            urt = 165 * pengali;
                            ukuran = "Cadbury besar";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });

                break;

            case 147:
                Img.setImageResource(R.drawable.pizza);

                rg.setVisibility(View.VISIBLE);

                r1.setText("Iris besar");
                r2.setVisibility(View.VISIBLE);
                r2.setText("Iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 298;
                        double p = 12;
                        double l = 14.2;
                        double k = 30.5;

                        if (r1.isChecked()) {
                            urt = 70 * pengali;
                            ukuran = "Iris besar";
                        } else if (r2.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "iris kecil";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });


                break;

            case 148:

                Img.setImageResource(R.drawable.rempeyek_kacang);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 532;
                        double p = 7.00;
                        double l = 47.48;
                        double k = 21.69;

                        if (r1.isChecked()) {
                            urt = 15 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });


                break;

            case 149:
                Img.setImageResource(R.drawable.kerupuk_udang);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setVisibility(View.GONE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 407.48;
                        double p = 14.00;
                        double l = 13.74;
                        double k = 57.39;

                        if (r1.isChecked()) {
                            urt = 5 * pengali;
                            ukuran = "Buah";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });


                break;

            case 150:
                Img.setImageResource(R.drawable.keripik_kentang);

                rg.setVisibility(View.VISIBLE);

                r1.setText("Genggam");
                r2.setVisibility(View.GONE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 531.00;
                        double p = 5.20;
                        double l = 32.20;
                        double k = 55.00;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Genggam";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });

                break;

            case 151:
                Img.setImageResource(R.drawable.keripik_singkong);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Genggam");
                r2.setVisibility(View.GONE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 522;
                        double p = 4.30;
                        double l = 69.60;
                        double k = 73.90;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Genggam";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });


                break;

            case 152:
                Img.setImageResource(R.drawable.kerupuk_melinjo_emping);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Genggam");
                r2.setVisibility(View.GONE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 345.00;
                        double p = 12.00;
                        double l = 1.50;
                        double k = 71.50;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Genggam";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });

                break;

            case 153:
                Img.setImageResource(R.drawable.happy_tos);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Bungkus kecil");
                r2.setText("Bungkus besar");
                r2.setVisibility(View.VISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 500.00;
                        double p = 7.10;
                        double l = 25.00;
                        double k = 64.30;

                        if (r1.isChecked()) {
                            urt = 55 * pengali;
                            ukuran = "Bungkus kecil";
                        } else if (r2.isChecked()) {
                            urt = 160 * pengali;
                            ukuran = "Bungkus besar";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });

                break;

            case 154:
                Img.setImageResource(R.drawable.selai);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 590.00;
                        double p = 27.00;
                        double l = 49.00;
                        double k = 20.90;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
//                Img.setImageResource(R.drawable.popcorn);
//                rg.setVisibility(View.VISIBLE);
//
//                r1.setText("Piring");
//                // r2.setText("1 bungkus besar");
//                r2.setVisibility(View.GONE);
//                //  r2.setText("S1 iris kecil");
//
//                hitung.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        double e = 380.00;
//                        double p = 6.90;
//                        double l = 0.80;
//                        double k = 86.40;
//
//                        if (r1.isChecked()) {
//                            urt = 100 * pengali;
//                            ukuran = "Piring";
//                        }
//
//                        //JANGAN DIGANTI BAGIAN INI
//                        double hEnergi = (urt / 100) * e;
//                        double hProtein = (urt / 100) * p;
//                        double hLemak = (urt / 100) * l;
//                        double hKalori = (urt / 100) * k;
//
//                        //buat batasi digit coma
////                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
////                        double hProteinSort = Math.round(hProtein * 100) / 100;
////                        double hLemakSort = Math.round(hLemak * 100) / 100;
////                        double hKaloriSort = Math.round(hKalori * 100) / 100;
//
//                        hEnergiSort = df.format(hEnergi);
//                        hProteinSort = df.format(hProtein);
//                        hLemakSort = df.format(hLemak);
//                        hKaloriSort = df.format(hKalori);
//
//                        //JANGAN DIUTAK ATIK
//
//                        penampungProgres = String.valueOf(progress);
//                        penampungUkuran = String.valueOf(ukuran);
//
//
//
//                        Save();
//                    }
//                });

                break;

            case 155:
                Img.setImageResource(R.drawable.permen);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Buah");
                r2.setText("Lolipop");
                r2.setVisibility(View.VISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 444;
                        double p = 7.10;
                        double l = 25.00;
                        double k = 64.30;

                        if (r1.isChecked()) {
                            urt = 55 * pengali;
                            ukuran = "Buah";
                        } else if (r2.isChecked()) {
                            urt = 160 * pengali;
                            ukuran = "Lolipop";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 156:
                Img.setImageResource(R.drawable.minuman_bersoda_softdrink);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Botol");
                r2.setText("Kaleng");
                r2.setVisibility(View.VISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 40;
                        double p = 0;
                        double l = 0;
                        double k = 10.80;

                        if (r1.isChecked()) {
                            urt = 425 * pengali;
                            ukuran = "Botol";
                        } else if (r2.isChecked()) {
                            urt = 330 * pengali;
                            ukuran = "Kaleng";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });

                break;


            case 157:
                Img.setImageResource(R.drawable.minuman_isotonik);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Botol mizone");
                r2.setText("Kaleng");
                r3.setText("Botol kecil pocari");
                r4.setText("Botol sedang pocari");
                r5.setText("Botol besar pocari");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 25.00;
                        double p = 0;
                        double l = 0;
                        double k = 6.00;

                        if (r1.isChecked()) {
                            urt = 500 * pengali;
                            ukuran = "Botol mizone";
                        }
                        if (r2.isChecked()) {
                            urt = 330 * pengali;
                            ukuran = "Kaleng";
                        }
                        if (r3.isChecked()) {
                            urt = 350 * pengali;
                            ukuran = "Botol kecil pocari";
                        }
                        if (r4.isChecked()) {
                            urt = 500 * pengali;
                            ukuran = "Botol sedang pocari";
                        }
                        if (r5.isChecked()) {
                            urt = 900 * pengali;
                            ukuran = "Botol besar pocari";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 158:
                Img.setImageResource(R.drawable.minuman_berenergy);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Botol");
                r2.setText("Kaleng");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 67.00;
                        double p = 1.00;
                        double l = 0;
                        double k = 16.70;

                        if (r1.isChecked()) {
                            urt = 150 * pengali;
                            ukuran = "Botol";
                        }
                        if (r2.isChecked()) {
                            urt = 355 * pengali;
                            ukuran = "Kaleng";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 159:
                Img.setImageResource(R.drawable.minuman_kemasan_sari_buah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Botol minute maid");
                r2.setText("Botol floridina");
                r3.setText("Kotak buavita / country choice");
                r4.setText("Gelas ale ale");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 48.00;
                        double p = 0;
                        double l = 0;
                        double k = 12.00;

                        if (r1.isChecked()) {
                            urt = 400 * pengali;
                            ukuran = "Botol minute maid";
                        }
                        if (r2.isChecked()) {
                            urt = 360 * pengali;
                            ukuran = "Botol floridina";
                        }
                        if (r3.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Kotak buavita / country choice";
                        }
                        if (r4.isChecked()) {
                            urt = 200 * pengali;
                            ukuran = "Gelas ale ale";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;


            case 160:
                Img.setImageResource(R.drawable.minuman_serbuk_sari_buah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sachet nutrisari ");
                r2.setText("Sachet marimas");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 429.00;
                        double p = 0;
                        double l = 0;
                        double k = 100.00;

                        if (r1.isChecked()) {
                            urt = 14 * pengali;
                            ukuran = "Sachet nutrisari";
                        }
                        if (r2.isChecked()) {
                            urt = 8 * pengali;
                            ukuran = "Sachet marimas";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 161:
                Img.setImageResource(R.drawable.teh_manis_bukan_kemasan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok teh");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 394;
                        double p = 0;
                        double l = 0;
                        double k = 94;

                        if (r1.isChecked()) {
                            urt = 5 * pengali;
                            ukuran = "Sendok teh";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 162:
                Img.setImageResource(R.drawable.teh_kemasan_siap_minum);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);
                r5.setVisibility(View.VISIBLE);
                r6.setVisibility(View.VISIBLE);

                r1.setText("Botol kaca");
                r2.setText("Botol plastik A");
                r3.setText("Botol plastik B");
                r4.setText("Kotak A");
                r5.setText("Kotak B");
                r6.setText("Gelas");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 39.00;
                        double p = 0;
                        double l = 0;
                        double k = 10.00;

                        if (r1.isChecked()) {
                            urt = 220 * pengali;
                            ukuran = "Botol kaca";
                        } else if (r2.isChecked()) {
                            urt = 450 * pengali;
                            ukuran = "Botol plastik A";
                        } else if (r3.isChecked()) {
                            urt = 500 * pengali;
                            ukuran = "Botol plastik B";
                        } else if (r4.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Kotak A";
                        } else if (r5.isChecked()) {
                            urt = 300 * pengali;
                            ukuran = "Kotak B";
                        } else if (r6.isChecked()) {
                            urt = 180 * pengali;
                            ukuran = "Gelas";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 163:
                Img.setImageResource(R.drawable.kopi_bubuk_instant);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);

                r1.setText("Sachet ABC");
                r2.setText("Sachet Nescafe");
                r3.setText("Sachet Good Day");
                r4.setText("Sachet Kapal Api");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 129.00;
                        double p = 0;
                        double l = 0;
                        double k = 35.00;

                        if (r1.isChecked()) {
                            urt = 31 * pengali;
                            ukuran = "Sachet ABC";
                        } else if (r2.isChecked()) {
                            urt = 17.5 * pengali;
                            ukuran = "Sachet Nescafe";
                        } else if (r3.isChecked()) {
                            urt = 20 * pengali;
                            ukuran = "Sachet Good Day";
                        } else if (r4.isChecked()) {
                            urt = 25 * pengali;
                            ukuran = "Sachet Kapal Api";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 164:
                Img.setImageResource(R.drawable.kopi_instant_siap_minum);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Botol good day");
                r2.setText("Botol kopiko 78C");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 54.00;
                        double p = 1.70;
                        double l = 1.50;
                        double k = 9.20;

                        if (r1.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Botol good day";
                        }
                        if (r2.isChecked()) {
                            urt = 240 * pengali;
                            ukuran = "Botol kopiko 78C";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 165:
                Img.setImageResource(R.drawable.kopi_manis_bukan_kemasan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan gula pasir");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 394;
                        double p = 0;
                        double l = 0;
                        double k = 94;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan gula pasir";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 166:
                Img.setImageResource(R.drawable.es_kelapa_muda);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Gelas");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 49.23;
                        double p = 0.29;
                        double l = 0.24;
                        double k = 11.48;

                        if (r1.isChecked()) {
                            urt = 350 * pengali;
                            ukuran = "Gelas";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 167:
                Img.setImageResource(R.drawable.es_teler);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 155.00;
                        double p = 1.98;
                        double l = 6.86;
                        double k = 23.93;

                        if (r1.isChecked()) {
                            urt = 300 * pengali;
                            ukuran = "Porsi";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 168:
                Img.setImageResource(R.drawable.orange_water);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Botol kaca");
                r2.setText("Botol plastik");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 32.00;
                        double p = 0;
                        double l = 0;
                        double k = 8.00;

                        if (r1.isChecked()) {
                            urt = 140 * pengali;
                            ukuran = "Botol kaca";
                        }
                        if (r2.isChecked()) {
                            urt = 600 * pengali;
                            ukuran = "Botol plastik";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 169:
                Img.setImageResource(R.drawable.sari_kacang_hijau);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Kotak");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 36.00;
                        double p = 0.80;
                        double l = 0;
                        double k = 8.80;

                        if (r1.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Kotak";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 170:
                Img.setImageResource(R.drawable.susu_cair);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Kotak");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 55.00;
                        double p = 3.00;
                        double l = 10.00;
                        double k = 9.00;

                        if (r1.isChecked()) {
                            urt = 200 * pengali;
                            ukuran = "Kotak";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 171:
                Img.setImageResource(R.drawable.susu_bubuk);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 429.00;
                        double p = 7.10;
                        double l = 10.70;
                        double k = 71.40;

                        if (r1.isChecked()) {
                            urt = 5 * pengali;
                            ukuran = "Sendok makan";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 172:
                Img.setImageResource(R.drawable.susu_kental_manis);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setText("Sachet");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 343.00;
                        double p = 8.20;
                        double l = 10.00;
                        double k = 55.00;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan";
                        }
                        if (r2.isChecked()) {
                            urt = 40 * pengali;
                            ukuran = "Sachet";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 173:
                Img.setImageResource(R.drawable.yogurt);
                rg.setVisibility(View.VISIBLE);

                r1.setText("botol");
                r2.setText("Kotak");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 52.00;
                        double p = 3.30;
                        double l = 2.50;
                        double k = 4.00;

                        if (r1.isChecked()) {
                            urt = 250 * pengali;
                            ukuran = "Botol";
                        }
                        if (r2.isChecked()) {
                            urt = 200 * pengali;
                            ukuran = "Kotak";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 174:
                Img.setImageResource(R.drawable.es_krim);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);

                r1.setText("Batang");
                r2.setText("Cup");
                r3.setText("Cone");
                r4.setText("Scoop");


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 88.00;
                        double p = 0.40;
                        double l = 0.40;
                        double k = 20.60;

                        if (r1.isChecked()) {
                            urt = 86 * pengali;
                            ukuran = "Batang";
                        } else if (r2.isChecked()) {
                            urt = 100 * pengali;
                            ukuran = "Cup";
                        } else if (r3.isChecked()) {
                            urt = 110 * pengali;
                            ukuran = "Cone";
                        } else if (r4.isChecked()) {
                            urt = 50 * pengali;
                            ukuran = "Scoop";
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 175:
                Img.setImageResource(R.drawable.sereal_instant_energen);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sachet");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 448.00;
                        double p = 3.40;
                        double l = 12.10;
                        double k = 82.80;

                        if (r1.isChecked()) {
                            urt = 30 * pengali;
                            ukuran = "Sachet";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 176:
                Img.setImageResource(R.drawable.susu_kedelai);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Gelas");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 54.00;
                        double p = 2.30;
                        double l = 1.90;
                        double k = 6.80;

                        if (r1.isChecked()) {
                            urt = 330 * pengali;
                            ukuran = "Gelas";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 177:
                Img.setImageResource(R.drawable.puding);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Potong");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 88.40;
                        double p = 1.89;
                        double l = 2.38;
                        double k = 14.75;

                        if (r1.isChecked()) {
                            urt = 120 * pengali;
                            ukuran = "Potong";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 178:
                Img.setImageResource(R.drawable.abon_sapi_ikan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 212.00;
                        double p = 18.00;
                        double l = 10.60;
                        double k = 59.30;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makani";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 179:
                Img.setImageResource(R.drawable.abon_sapi_ikan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 435.00;
                        double p = 27.20;
                        double l = 20.20;
                        double k = 36.10;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;

            case 180:
                Img.setImageResource(R.drawable.selai);
                rg.setVisibility(View.VISIBLE);

                r1.setText("Sendok makan");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 239.00;
                        double p = 0.50;
                        double l = 0.60;
                        double k = 64.50;

                        if (r1.isChecked()) {
                            urt = 10 * pengali;
                            ukuran = "Sendok makan";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;


            case 181:
                Img.setImageResource(R.drawable.air);
                rg.setVisibility(View.VISIBLE);

                r1.setText("200 ml");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 0;
                        double p = 0;
                        double l = 0;
                        double k = 0;

                        if (r1.isChecked()) {
                            urt = 0 * pengali;
                            ukuran = "200 ml";
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi = (urt / 100) * e;
                        double hProtein = (urt / 100) * p;
                        double hLemak = (urt / 100) * l;
                        double hKalori = (urt / 100) * k;

                        //buat batasi digit coma
//                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
//                        double hProteinSort = Math.round(hProtein * 100) / 100;
//                        double hLemakSort = Math.round(hLemak * 100) / 100;
//                        double hKaloriSort = Math.round(hKalori * 100) / 100;

                        hEnergiSort = df.format(hEnergi);
                        hProteinSort = df.format(hProtein);
                        hLemakSort = df.format(hLemak);
                        hKaloriSort = df.format(hKalori);

                        //JANGAN DIUTAK ATIK

                        penampungProgres = String.valueOf(progress);
                        penampungUkuran = String.valueOf(ukuran);



                        Save();
                    }
                });
                break;


//            case 181:
//                Img.setImageResource(R.drawable.selai);
//                rg.setVisibility(View.VISIBLE);
//
//                r1.setText("Sendok makan");
//                r2.setVisibility(View.GONE);
//
//                hitung.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        double e = 590.00;
//                        double p = 27.00;
//                        double l = 49.00;
//                        double k = 20.90;
//
//                        if (r1.isChecked()) {
//                            urt = 10 * pengali;
//                            ukuran = "Sendok makan";
//                        }
//
//                        //JANGAN DIGANTI BAGIAN INI
//                        double hEnergi = (urt / 100) * e;
//                        double hProtein = (urt / 100) * p;
//                        double hLemak = (urt / 100) * l;
//                        double hKalori = (urt / 100) * k;
//
//                        //buat batasi digit coma
////                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
////                        double hProteinSort = Math.round(hProtein * 100) / 100;
////                        double hLemakSort = Math.round(hLemak * 100) / 100;
////                        double hKaloriSort = Math.round(hKalori * 100) / 100;
//
//                        hEnergiSort = df.format(hEnergi);
//                        hProteinSort = df.format(hProtein);
//                        hLemakSort = df.format(hLemak);
//                        hKaloriSort = df.format(hKalori);
//
//                        //JANGAN DIUTAK ATIK
//
//                        penampungProgres = String.valueOf(progress);
//                        penampungUkuran = String.valueOf(ukuran);
//
//
//
//                        Save();
//                    }
//                });
//                break;

//            case 182:
//                Img.setImageResource(R.drawable.madu);
//                rg.setVisibility(View.VISIBLE);
//
//                final String makanan = "Madu";
//
//
//                r1.setText("Sendok makan");
//                r2.setText("Sachet");
//
//                hitung.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        double e = 294.00;
//                        double p = 0.30;
//                        double l = 0;
//                        double k = 79.50;
//
//                        if (r1.isChecked()) {
//                            urt = 12 * pengali;
//                            ukuran = "Sendok makan";
//                        }
//                        if (r2.isChecked()) {
//                            urt = 20 * pengali;
//                            ukuran = "Sachet";
//                        }
//
//
//                        //JANGAN DIGANTI BAGIAN INI
//                        double hEnergi = (urt / 100) * e;
//                        double hProtein = (urt / 100) * p;
//                        double hLemak = (urt / 100) * l;
//                        double hKalori = (urt / 100) * k;
//
//                        //buat batasi digit coma
////                        double hEnergiSort = Math.round(hEnergi * 100) / 100;
////                        double hProteinSort = Math.round(hProtein * 100) / 100;
////                        double hLemakSort = Math.round(hLemak * 100) / 100;
////                        double hKaloriSort = Math.round(hKalori * 100) / 100;
//
//                        hEnergiSort = df.format(hEnergi);
//                        hProteinSort = df.format(hProtein);
//                        hLemakSort = df.format(hLemak);
//                        hKaloriSort = df.format(hKalori);
//
//                        //JANGAN DIUTAK ATIK
//
//                        penampungProgres = String.valueOf(progress);
//                        penampungUkuran = String.valueOf(ukuran);
//
//
//
//
//
//                        Toast.makeText(getApplicationContext(),
//                                "Email: " + email +
//                                        "Makanan: " + makanan +
//                                        "Jumlah: " + penampungProgres +
//                                        "Ukuran: " + penampungUkuran +
//                                        "Energi: " + hEnergiSort +
//                                        "Protein: " + hProteinSort +
//                                        "Lemak: " + hLemakSort +
//                                        "Kalori: " + hKaloriSort, Toast.LENGTH_LONG).show();
//
//                        Save();
//
//
//                    }
//                });
//                break;

        }



    }

//    private void Save(){
//        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(PerhitunganPagi.this);
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Sarapan");
//        // Setting Dialog Message
//        alertDialog.setMessage("Apakah Anda yakin sudah memasukan semua menu sarapan Anda?");
//        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.x);
//
//        // Setting Positive "Yes" Button
//        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog,int which) {
//
//                // Write your code here to invoke YES event
//                ConfirmSave();
//            }
//        });
//
//        // Setting Negative "NO" Button
//        alertDialog.setNegativeButton("Cek Kembali", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to invoke NO event
////                Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
//                dialog.cancel();
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
////    }
//    }

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
                        Toast.makeText(PerhitunganPagi.this, response, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(PerhitunganPagi.this, SarapanActivity.class);
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
