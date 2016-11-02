package com.ayyash.recfon.frekuensi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
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

import com.ayyash.recfon.R;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class FormFrekuensiMakan extends AppCompatActivity {

    public static final String KEY_EMAIL = "txt_email";
    public static final String KEY_MKN = "makanan";
    public static final String KEY_IDMKN = "idmakanan";
    public static final String KEY_UKUR = "ukuran";
    public static final String KEY_JML = "jumlah";
    public static final String KEY_JML_FREQ= "jml_frekuensi";
    public static final String KEY_FREQ = "satuan_frekuensi";
    public static final String KEY_PROTEIN = "protein1";
    public static final String KEY_LEMAK = "lemak1";
    public static final String KEY_KALORI = "kalori1";
    public static final String KEY_ENERGI = "energi1";

    TextView tv,namaMakanan,satuan,jmlFrekuensi, keteranganPilihan;
    Button hitung,btnKeluar;
    Integer nM;
    int indexMakanan;
    RadioGroup rg, rgJenisMakanan;
    RadioButton r1,r2,r3,r4,r5,r6,r7,rm1,rm2,rm3,rm4,rm5,rm6,rm7;
    double urt;
    int frekuensi;
    double n;
    SeekBar seekBar,seekBar2;
    double progress;
    double pengali;
    ImageView Img;
    String ukuran="";
    String email;
    double berat;
    ProgressDialog PD;
    private ItemObject.ObjectBelajar objectBelajar;
    String penampungProgres, penampungUkuran, penampungFrekuensi, penampungSatuan;
    String hEnergiSort;
    String hProteinSort ;
    String hLemakSort;
    String hKaloriSort;
    String id_bahan_makanan;
    double e = 0;
    double p = 0;
    double l = 0;
    double k = 0;
    EditText txtFrekuensi;
    Spinner satuanFrekuensi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_frekuensi_makan);
        // tv = (TextView) findViewById(R.id.textView);
        satuan = (TextView) findViewById(R.id.textView2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        namaMakanan = (TextView) findViewById(R.id.textView3);
        txtFrekuensi = (EditText) findViewById(R.id.txtFrekuensiUye);
        satuanFrekuensi = (Spinner) findViewById(R.id.spinner2) ;

        Toast.makeText(getApplicationContext(),"sdads"+ txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();

        btnKeluar = (Button) findViewById(R.id.batal);

        hitung = (Button) findViewById(R.id.hitung);
//        btnKeluar =(Button)findViewById(R.id.button3);
        satuan.setText(String.valueOf(progress));

        Img = (ImageView) findViewById(R.id.imageViewUye);
        rgJenisMakanan = (RadioGroup)findViewById(R.id.rgJenisMakanan);
        keteranganPilihan =(TextView)findViewById(R.id.tJenisMakan);
        rm1 = (RadioButton) findViewById(R.id.rm1);
        rm2 = (RadioButton) findViewById(R.id.rm2);
        rm3 = (RadioButton) findViewById(R.id.rm3);
        rm4 = (RadioButton) findViewById(R.id.rm4);
        rm5 = (RadioButton) findViewById(R.id.rm5);
        rm6 = (RadioButton) findViewById(R.id.rm6);
        rm7 = (RadioButton) findViewById(R.id.rm7);

        rm2.setVisibility(View.GONE);

        rg = (RadioGroup) findViewById(R.id.rg);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        r3 = (RadioButton) findViewById(R.id.radioButton3);
        r4 = (RadioButton) findViewById(R.id.radioButton4);
        r5 = (RadioButton) findViewById(R.id.radioButton5);
        r6 = (RadioButton) findViewById(R.id.radioButton6);
        r7 = (RadioButton) findViewById(R.id.radioButton7);
        r3.setVisibility(View.GONE);


        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);
        final DecimalFormat df = new DecimalFormat("#.##");


        progress = 1;
        pengali = 1;
        satuan.setText( String.valueOf(progress));


        //seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = ((float) i / 2);
                satuan.setText( String.valueOf(progress));
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

                Intent i = new Intent(getApplicationContext(), FrekuensiBulanan.class);
                startActivity(i);
                finish();
            }
        });


        Intent i = getIntent();

        nM = i.getIntExtra ("makanan", 0);


        switch (nM) {
            case 1:

                id_bahan_makanan = "1";
                namaMakanan.setText("Nasi");
                Img.setImageResource(R.drawable.nasi);
                rm1.setText("Nasi");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);


                 e = 177.00;
                 p = 3.90;
                 l = 0.20;
                 k = 39.95;
                berat = 50;
                ukuran = "centong";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 177.00;
                            p = 3.90;
                            l = 0.20;
                            k = 39.95;
                            berat = 50;
                            Img.setImageResource(R.drawable.nasi);
                            namaMakanan.setText("Nasi");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("centong");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();


                        urt = berat * pengali * n /frekuensi;


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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });


                break;

            case 2:
                id_bahan_makanan = "2";
                namaMakanan.setText("Nasi merah");

                Img.setImageResource(R.drawable.nasi);
                rm1.setText("Nasi merah");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  176.00;
                p =  3.65;
                l = 0.45;
                k = 38.10;
                berat = 50;
                ukuran = "centong";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 176.00;
                            p = 3.65;
                            l = 0.45;
                            k = 38.10;
                            berat = 50;
                            Img.setImageResource(R.drawable.nasi);
                            namaMakanan.setText("Nasi");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("centong");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 3:

                id_bahan_makanan = "3";
                namaMakanan.setText("Nasi uduk / nasi kuning");

                Img.setImageResource(R.drawable.nasi);
                rm1.setText("Nasi uduk / nasi kuning");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  198.79;
                p =  4.26;
                l = 1.99;
                k = 41.31;
                berat = 50;
                ukuran = "centong";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  198.79;
                            p =  4.26;
                            l = 1.99;
                            k = 41.31;
                            berat = 50;
                            Img.setImageResource(R.drawable.nasi);
                            namaMakanan.setText("Nasi");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("centong");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 4:

                id_bahan_makanan = "4";
                namaMakanan.setText("Mie instant goreng");

                Img.setImageResource(R.drawable.mie_instant_goreng);
                rm1.setText("Mie instant goreng");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  459.00;
                p =  9.40;
                l = 20.00;
                k = 60.00;
                berat = 85;
                ukuran = "bungkus";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  459.00;
                            p =  9.40;
                            l = 20.00;
                            k = 60.00;
                            berat = 85;
                            Img.setImageResource(R.drawable.mie_instant_goreng);
                            namaMakanan.setText("Mie instant goreng");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("bungkus");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;
            case 5:

                id_bahan_makanan = "5";
                namaMakanan.setText("Mie instant rebus");

                Img.setImageResource(R.drawable.mie_instant_rebus);
                rm1.setText("Mie instant rebus");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  464.00;
                p =  10.10;
                l = 59.40;
                k = 1.40;
                berat = 70;
                ukuran = "bungkus";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  464.00;
                            p =  10.10;
                            l = 59.40;
                            k = 1.40;
                            berat = 70;
                            Img.setImageResource(R.drawable.mie_instant_rebus);
                            namaMakanan.setText("Mie instant rebus");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("bungkus");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;


                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 6:

                id_bahan_makanan = "6";
                namaMakanan.setText("Nasi goreng");

                Img.setImageResource(R.drawable.nasigoreng);
                rm1.setText("Nasi goreng");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  384.18;
                p =  7.56;
                l = 21.37;
                k = 38.82;
                berat = 50;
                ukuran = "centong";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  384.18;
                            p =  7.56;
                            l = 21.37;
                            k = 38.82;
                            berat = 50;
                            Img.setImageResource(R.drawable.nasigoreng);
                            namaMakanan.setText("Nasi goreng");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("centong");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 7:
                id_bahan_makanan = "7";
                namaMakanan.setText("Mie goreng (Bukan instant)");
                Img.setImageResource(R.drawable.mie_goreng_bukan_instan);
                rm1.setText("Mie goreng (Bukan instant)");
                rm2.setText("kwetiaw goreng");
                rm3.setText("Bihun Goreng");
                rm3.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);

                e = 156.74;
                p = 4.22;
                l = 11.93;
                k = 38.82;
                berat = 300;
                ukuran = "Porsi";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 156.74;
                            p = 4.22;
                            l = 11.93;
                            k = 38.82;
                            berat = 300;
                            Img.setImageResource(R.drawable.mie_goreng_bukan_instan);
                            namaMakanan.setText("Mie goreng (bukan instant)");

                        }else if(rm2.isChecked()){
                            e = 144.59;
                            p = 3.47;
                            l = 11.20;
                            k = 7.63;
                            berat = 275;

                            Img.setImageResource(R.drawable.kwetiau_goreng);
                            namaMakanan.setText("Kwetiaw goreng");
                        }else if (rm3.isChecked()){
                            e = 398.51;
                            p = 3.23;
                            l = 22.48;
                            k = 6.62;
                            berat = 250;

                            Img.setImageResource(R.drawable.bihun_goreng);
                            namaMakanan.setText("Bihun goreng");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("Porsi");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();


                         urt = berat * pengali * n /frekuensi;



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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 8:

                id_bahan_makanan = "8";
                namaMakanan.setText("Bubur ayam");

                Img.setImageResource(R.drawable.bubur_ayam);
                rm1.setText("Bubur ayam");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  223.32;
                p =  13.00;
                l = 6.53;
                k = 29.19;
                berat = 300;
                ukuran = "porsi";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  223.32;
                            p =  13.00;
                            l = 6.53;
                            k = 29.19;
                            berat = 300;
                            Img.setImageResource(R.drawable.bubur_ayam);
                            namaMakanan.setText("Bubur ayam");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("porsi");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 9:

                id_bahan_makanan = "9";
                namaMakanan.setText("Roti tawar");

                Img.setImageResource(R.drawable.roti_tawar);
                rm1.setText("Roti tawar");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  361.83;
                p =  7.17;
                l = 9.32;
                k = 65.14;
                berat = 35;
                ukuran = "lembar A";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  361.83;
                            p =  7.17;
                            l = 9.32;
                            k = 65.14;
                            berat = 35;
                            Img.setImageResource(R.drawable.roti_tawar);
                            namaMakanan.setText("Roti tawar");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("lembar A");
                r2.setText("lembar B");
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 35;
                            ukuran = "lembar A";
                        }else if(r2.isChecked()){
                            berat = 20;
                            ukuran = "lembar B";
                        }
                    }
                });



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 10:

                id_bahan_makanan = "10";
                namaMakanan.setText("Lontong / Ketupat sayur");

                Img.setImageResource(R.drawable.lontong_sayur);
                rm1.setText("Lontong / Ketupat sayur");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  150.79;
                p =  2.65;
                l = 7.43;
                k = 29.19;
                berat = 350;
                ukuran = "porsi";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  150.79;
                            p =  2.65;
                            l = 7.43;
                            k = 29.19;
                            berat = 350;
                            Img.setImageResource(R.drawable.lontong_sayur);
                            namaMakanan.setText("Lontong / Ketupat sayur");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("porsi");
                r2.setVisibility(View.GONE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 11:
                id_bahan_makanan = "11";
                namaMakanan.setText("Ubi goreng)");
                Img.setImageResource(R.drawable.ubi_goreng);
                rm1.setText("Ubi goreng");
                rm2.setText("Singkong goreng");
                rm3.setText("Ubi rebus");
                rm4.setText("Singkong rebus");
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);
                rm4.setVisibility(View.VISIBLE);

                e = 176.95;
                p = 2.71;
                l = 3.25;
                k = 35.29;
                berat = 75;
                urt = berat * pengali * n /frekuensi;
                ukuran = "potong";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 176.95;
                            p = 2.71;
                            l = 3.25;
                            k = 35.29;
                            berat = 75;

                            r1.setText("potong");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);
                            r3.setVisibility(View.GONE);



                            ukuran = "potong";


                            Img.setImageResource(R.drawable.ubi_goreng);
                            namaMakanan.setText("Ubi goreng");

                        }else if(rm2.isChecked()){
                            e = 175.53;
                            p = 0.97;
                            l = 3.07;
                            k = 35.78;
                            berat = 40;

                            r1.setText("potong");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);
                            r3.setVisibility(View.GONE);


                            ukuran = "potong";

                            Img.setImageResource(R.drawable.singkong_goreng);
                            namaMakanan.setText("Singkong goreng");

                        }else if (rm3.isChecked()){
                            e = 88.00;
                            p = 0.40;
                            l = 0.40;
                            k = 20.60;
                            berat = 150;

                            r1.setText("buah sedang");
                            r2.setText("buah besar");
                            r3.setText("buah kecil");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.VISIBLE);
                            ukuran = "buah sedang";
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 150;
                                        ukuran = "buah sedang";
                                    }else if (r2.isChecked()) {
                                        berat = 200;
                                        ukuran = "buah besar";
                                    }else if (r3.isChecked()) {
                                        berat = 100;
                                        ukuran = "buah kecil";
                                    }
                                }
                            });
                            Img.setImageResource(R.drawable.ubi_rebus);
                            namaMakanan.setText("Ubi rebus");

                        }else if (rm4.isChecked()){
                            e = 154.00;
                            p = 1.00;
                            l = 0.30;
                            k = 36.80;
                            berat = 100;

                            r1.setText("potong besar");
                            r2.setText("potong kecil");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.GONE);
                            ukuran = "potong besar";
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                                if (r1.isChecked()) {
                                    berat = 100;
                                    ukuran = "potong besar";
                                }else if (r2.isChecked()) {
                                    berat = 50;

                                    ukuran = "potong kecil";
                                }

                                }
                            });
                            Img.setImageResource(R.drawable.singkong_rebus);
                            namaMakanan.setText("Singkong rebus");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("potong");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();
                        urt = berat * pengali * n /frekuensi;

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 12:

                id_bahan_makanan = "12";
                namaMakanan.setText("Perkedel kentang");

                Img.setImageResource(R.drawable.perkedel_kentang);
                rm1.setText("Perkedel kentang");
                rm2.setText("Kentang goreng");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);

                e =  126.02;
                p =  3.86;
                l = 7.57;
                k = 10.44;
                berat = 40;
                ukuran = "buah besar";


                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  126.02;
                            p =  3.86;
                            l = 7.57;
                            k = 10.44;
                            berat = 40;


                            r1.setText("buah besar");
                            r2.setText("buah sedang");
                            r3.setText("buah kecil");

                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.VISIBLE);

                            ukuran = "buah besar";
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                                    if (r1.isChecked()) {
                                        berat = 40;

                                        ukuran = "buah besar";
                                    }else if (r2.isChecked()) {
                                        berat = 20;

                                        ukuran = "buah sedang";
                                    }else if (r3.isChecked()) {
                                        berat = 10;

                                        ukuran = "buah kecil";
                                    }

                                }
                            });
                            Img.setImageResource(R.drawable.perkedel_kentang);
                            namaMakanan.setText("Perkedel kentang");
                        }
                        if (rm2.isChecked()){
                            e =  316.00;
                            p =  3.80;
                            l = 16.10;
                            k = 39.00;
                            berat = 100;

                            r1.setText("piring");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);
                            r3.setVisibility(View.GONE);


                            ukuran = "piring";

                            Img.setImageResource(R.drawable.kentang_goreng);
                            namaMakanan.setText("Kentang goreng");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("buah besar");
                r2.setText("buah sedang");
                r3.setText("buah kecil");

                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;


                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });
                break;

            case 13:

                id_bahan_makanan = "13";
                namaMakanan.setText("Labu siam masak santan");

                Img.setImageResource(R.drawable.labu_siam_masak_santan);
                rm1.setText("Labu siam masak santan");
                rm2.setText("Gulai daun singkong");
                rm3.setText("Sayur lodeh");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);

                e =  80.91;
                p =  1.49;
                l = 5.57;
                k = 7.11;
                berat = 55;
                ukuran = "sendok sayur";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  80.91;
                            p =  1.49;
                            l = 5.57;
                            k = 7.11;
                            berat = 55;

                            r1.setText("sendok sayur");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);


                                ukuran = "sendok sayur";

                            Img.setImageResource(R.drawable.labu_siam_masak_santan);
                            namaMakanan.setText("Labu siam masak santan");
                        }
                        if (rm2.isChecked()){
                            e =  131.62;
                            p =  3.75;
                            l = 9.11;
                            k = 9.42;
                            berat = 200;

                            r1.setText("mangkok");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);


                                ukuran = "mangkok";


                            Img.setImageResource(R.drawable.gulai_daun_singkong);
                            namaMakanan.setText("Gulai daun singkong");
                        }
                        if (rm3.isChecked()){
                            e =  102.30;
                            p =  2.72;
                            l = 5.57;
                            k = 7.11;
                            berat = 30;
                            ukuran = "sendok sayur";

                            r1.setText("sendok sayur");
                            r2.setText("mangkok");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 30;
                                        ukuran = "sendok sayur";
                                    }else if (r2.isChecked()) {
                                        berat = 250;
                                        ukuran = "mangkok";
                                    }
                                }
                            });


                            Img.setImageResource(R.drawable.sayur_lodeh);
                            namaMakanan.setText("Sayur lodeh");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("sendok sayur");
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.GONE);
                r3.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 14:

                id_bahan_makanan = "14";
                namaMakanan.setText("Tumis kacang panjang");
                Img.setImageResource(R.drawable.tumis_kacang_panjang);
                rm1.setText("Tumis kacang panjang");
                rm2.setText("Tumis kangkung");
                rm3.setText("Tumis sawi hijau / Bok choy");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);

                e =  174.38;
                p =  1.93;
                l =  16.05;
                k =  4.45;
                berat = 15;
                ukuran = "sendok makan";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  174.38;
                            p =  1.93;
                            l =  16.05;
                            k =  4.45;
                            berat = 15;
                            ukuran = "sendok makan";

                            r1.setText("sendok makan");
                            r2.setText("piring");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                if (r1.isChecked()) {
                                    berat = 15;
                                    ukuran = "sendok makan";
                                }else if (r2.isChecked()) {
                                    berat = 100;
                                    ukuran = "piring";
                                }
                            }
                        });

                            Img.setImageResource(R.drawable.tumis_kacang_panjang);
                            namaMakanan.setText("Tumis kacang panjang");
                        }
                        if (rm2.isChecked()){
                            e =  167.53;
                            p =  2.26;
                            l =  16.96;
                            k =  3.48;
                            berat = 10;
                            ukuran = "sendok makan";

                            r1.setText("sendok makan");
                            r2.setText("piring");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 10;
                                        ukuran = "sendok makan";
                                    }else if (r2.isChecked()) {
                                        berat = 100;
                                        ukuran = "piring";
                                    }
                                }
                            });



                            Img.setImageResource(R.drawable.tumis_kangkung);
                            namaMakanan.setText("Tumis kangkung");
                        }
                        if (rm3.isChecked()){
                            e =  125.47;
                            p =  1.42;
                            l =  12.49;
                            k =  3.24;
                            berat = 15;
                            ukuran = "sendok makan";

                            r1.setText("sendok makan");
                            r2.setText("piring");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 15;
                                        ukuran = "sendok makan";
                                    }else if (r2.isChecked()) {
                                        berat = 100;
                                        ukuran = "piring";
                                    }
                                }
                            });



                            Img.setImageResource(R.drawable.tumis_sawi_hijau);
                            namaMakanan.setText("Tumis sawi hijau / Bok choy");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("sendok makan");
                r2.setText("piring");
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;


                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 15:

                id_bahan_makanan = "15";
                namaMakanan.setText("Sayur sop");
                Img.setImageResource(R.drawable.sayur_sop);
                rm1.setText("Sayur sop");
                rm2.setText("Sayur bening bayam");
                rm3.setText("Sayur asam");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);

                e =  44.00;
                p =  1.65;
                l =  0.36;
                k =  9.43;
                berat = 30;
                ukuran = "sendok sayur";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  44.00;
                            p =  1.65;
                            l =  0.36;
                            k =  9.43;
                            berat = 30;
                            ukuran = "sendok sayur";

                            r1.setText("sendok sayur");
                            r2.setText("mangkok");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 30;
                                        ukuran = "sendok sayur";
                                    }else if (r2.isChecked()) {
                                        berat = 250;
                                        ukuran = "mangkok";
                                    }
                                }
                            });


                            Img.setImageResource(R.drawable.sayur_sop);
                            namaMakanan.setText("Sayur sop");
                        }
                        if (rm2.isChecked()){
                            e =  72.57;
                            p =  2.71;
                            l =  0.53;
                            k =  15.25;
                            berat = 15;
                            ukuran = "sendok sayur";

                            r1.setText("sendok sayur");
                            r2.setText("mangkok");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                  @Override
                                  public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                      if (r1.isChecked()) {
                                          berat = 15;
                                          ukuran = "sendok sayur";
                                      } else if (r2.isChecked()) {
                                          berat = 100;
                                          ukuran = "mangkok";
                                      }
                                  }
                              });

                            Img.setImageResource(R.drawable.sayur_bayam_bening);
                            namaMakanan.setText("Sayur bening bayam");
                        }
                        if (rm3.isChecked()){
                            e =  157.20;
                            p =  7.43;
                            l =  6.96;
                            k =  19.01;
                            berat = 30;
                            ukuran = "sendok sayur";

                            r1.setText("sendok sayur");
                            r2.setText("mangkok");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 30;

                                        ukuran = "sendok sayur";
                                    }else if (r2.isChecked()) {
                                        berat = 250;

                                        ukuran = "mangkok";
                                    }
                                }
                            });



                            Img.setImageResource(R.drawable.sayur_asem);
                            namaMakanan.setText("Sayur asam");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("sendok sayur");
                r2.setText("mangkok");
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;


                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 16:

                id_bahan_makanan = "16";
                namaMakanan.setText("Sayur urap");
                Img.setImageResource(R.drawable.sayur_urap);
                rm1.setText("Sayur urap");
                rm2.setText("Sayur pecel");
                rm3.setText("Gado - gado / Karedok");
                rm4.setText("Ketoprak");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);
                rm4.setVisibility(View.VISIBLE);

                e =  81.75;
                p =  2.62;
                l =  5.32;
                k =  7.42;
                berat = 15;
                ukuran = "sendok makan";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  81.75;
                            p =  2.62;
                            l =  5.32;
                            k =  7.42;
                            berat = 15;
                            ukuran = "sendok makan";

                            r1.setText("sendok makan");
                            r2.setText("porsi");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 15;

                                        ukuran = "sendok makan";
                                    }else if (r2.isChecked()) {
                                        berat = 150;

                                        ukuran = "porsi";
                                    }
                                }
                            });


                            Img.setImageResource(R.drawable.sayur_urap);
                            namaMakanan.setText("Sayur urap");
                        }
                        if (rm2.isChecked()){
                            e =  309.79;
                            p =  13.20;
                            l =  24.61;
                            k =  13.49;
                            berat = 130;

                            r1.setText("porsi");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);


                                ukuran = "porsi";


                            Img.setImageResource(R.drawable.sayur_pecel);
                            namaMakanan.setText("Sayur pecel");
                        }
                        if (rm3.isChecked()){
                            e =  153.15;
                            p =  6.43;
                            l =  10.61;
                            k =  10.16;
                            berat = 20;
                            ukuran = "sendok makan";

                            r1.setText("sendok makan");
                            r2.setText("porsi");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 20;
                                        ukuran = "sendok makan";
                                    }else if (r2.isChecked()) {
                                        berat = 200;
                                        ukuran = "porsi";
                                    }
                                }
                            });



                            Img.setImageResource(R.drawable.gado_gado);
                            namaMakanan.setText("Gado - gado / Karedok");
                        }
                        if (rm4.isChecked()){
                            e =  335.95;
                            p =  11.84;
                            l =  17.50;
                            k =  35.38;
                            berat = 435;

                            r1.setText("porsi");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);


                            ukuran = "porsi";


                            Img.setImageResource(R.drawable.ketoprak);
                            namaMakanan.setText("Ketoprak");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("sendok makan");
                r2.setText("porsi");
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;


                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 17:

                id_bahan_makanan = "17";
                namaMakanan.setText("Pisang");

                Img.setImageResource(R.drawable.pisang);
                rm1.setText("Pisang");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  108.00;
                p =  1.00;
                l =  0.80;
                k =  24.30;
                berat = 100;
                ukuran = "buah";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  108.00;
                            p =  1.00;
                            l =  0.80;
                            k =  24.30;
                            berat = 100;
                            Img.setImageResource(R.drawable.pisang);
                            namaMakanan.setText("Pisang");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("buah");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 18:

                id_bahan_makanan = "18";
                namaMakanan.setText("Mangga");

                Img.setImageResource(R.drawable.mangga);
                rm1.setText("Mangga");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  52.00;
                p =  0.70;
                l =  0;
                k =  12.30;
                berat = 250;
                ukuran = "buah sedang";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  52.00;
                            p =  0.70;
                            l =  0;
                            k =  12.30;
                            berat = 250;
                            Img.setImageResource(R.drawable.mangga);
                            namaMakanan.setText("Mangga");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("buah sedang");
                r2.setText("buah besar");

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 250;
                            ukuran = "buah sedang";
                        }else if (r2.isChecked()){
                            berat = 350;
                            ukuran = "buah besar";
                        }
                    }
                });



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 19:

                id_bahan_makanan = "19";
                namaMakanan.setText("Alpukat");

                Img.setImageResource(R.drawable.alpukat);
                rm1.setText("Alpukat");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  85.00;
                p =  0.90;
                l =  6.50;
                k =  7.70;
                berat = 130;
                ukuran = "buah sedang";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  85.00;
                            p =  0.90;
                            l =  6.50;
                            k =  7.70;
                            berat = 130;
                            Img.setImageResource(R.drawable.alpukat);
                            namaMakanan.setText("Alpukat");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("buah sedang");
                r2.setText("buah besar");

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 130;
                            ukuran = "buah sedang";
                        }else if (r2.isChecked()){
                            berat = 170;
                            ukuran = "buah besar";
                        }
                    }
                });



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 20:

                id_bahan_makanan = "20";
                namaMakanan.setText("Jeruk");

                Img.setImageResource(R.drawable.jeruk);
                rm1.setText("Jeruk");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  45.00;
                p =  0.90;
                l =  0.20;
                k =  11.20;
                berat = 100;
                ukuran = "buah sedang";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  45.00;
                            p =  0.90;
                            l =  0.20;
                            k =  11.20;
                            berat = 100;
                            Img.setImageResource(R.drawable.jeruk);
                            namaMakanan.setText("Jeruk");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("buah sedang");
                r2.setText("buah besar");

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 100;
                            ukuran = "buah sedang";
                        }else if (r2.isChecked()){
                            berat = 110;
                            ukuran = "buah besar";
                        }
                    }
                });



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 21:

                id_bahan_makanan = "21";
                namaMakanan.setText("Pepaya");

                Img.setImageResource(R.drawable.pepaya);
                rm1.setText("Pepaya");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  46.00;
                p =  0.50;
                l =  0.10;
                k =  12.20;
                berat = 100;
                ukuran = "potong panjang";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  46.00;
                            p =  0.50;
                            l =  0.10;
                            k =  12.20;
                            berat = 100;
                            Img.setImageResource(R.drawable.pepaya);
                            namaMakanan.setText("Pepaya");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("potong panjang");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 22:

                id_bahan_makanan = "22";
                namaMakanan.setText("Semangka");
                Img.setImageResource(R.drawable.semangka);
                rm1.setText("Semangka");
                rm2.setText("Melon");
                rm3.setText("Apel");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);

                e =  28.00;
                p =  0.50;
                l =  0.20;
                k =  6.90;
                berat = 100;
                ukuran = "potong";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  28.00;
                            p =  0.50;
                            l =  0.20;
                            k =  6.90;
                            berat = 100;

                            r1.setText("potong");
                            r2.setVisibility(View.GONE);

                            if (r1.isChecked()) {
                                urt = berat * pengali * n /frekuensi;
                                ukuran = "potong";
                            }

                            Img.setImageResource(R.drawable.semangka);
                            namaMakanan.setText("Semangka");
                        }
                        if (rm2.isChecked()){
                            e =  31.00;
                            p =  0.30;
                            l =  0.30;
                            k =  6.70;
                            berat = 100;
                            ukuran = "potong panjang";

                            r1.setText("potong panjang");
                            r2.setVisibility(View.GONE);

                            Img.setImageResource(R.drawable.melon);
                            namaMakanan.setText("Melon");
                        }
                        if (rm3.isChecked()){
                            e =  58.00;
                            p =  0.30;
                            l =  0.40;
                            k =  14.90;
                            berat = 100;
                            ukuran = "buah sedang";

                            r1.setText("buah sedang");
                            r2.setText("buah besar");

                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 100;
                                        ukuran = "buah sedang";
                                    }else if (r2.isChecked()){
                                        berat = 200;
                                        ukuran = "buah besar";
                                    }
                                }
                            });


                            Img.setImageResource(R.drawable.apel);
                            namaMakanan.setText("Apel");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("potong");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();
//                        System.out.println(urt+" ="+ berat +"*"+ pengali +"*"+ n +"/"+frekuensi);
                        Save();
                    }
                });

                break;

            case 23:

                id_bahan_makanan = "23";
                namaMakanan.setText("Tahu goreng");

                Img.setImageResource(R.drawable.tahu_goreng);
                rm1.setText("Tahu goreng");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.GONE);

                e =  164.90;
                p =  9.81;
                l =  14.23;
                k =  0.72;
                berat = 80;
                ukuran = "potong besar";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  164.90;
                            p =  9.81;
                            l =  14.23;
                            k =  0.72;
                            berat = 80;

                            Img.setImageResource(R.drawable.tahu_goreng);
                            namaMakanan.setText("Tahu goreng");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("potong besar");
                r2.setText("potong sedang");
                r3.setText("potong kecil");

                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 80;
                            ukuran = "potong besar";
                        }else if (r2.isChecked()) {
                            berat = 40;
                            ukuran = "potong sedang";
                        }else if (r3.isChecked()) {
                            berat = 20;
                            ukuran = "potong kecil";
                        }

                    }
                });

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 24:

                id_bahan_makanan = "24";
                namaMakanan.setText("Tempe goreng");

                Img.setImageResource(R.drawable.tempe_goreng);
                rm1.setText("Tempe goreng");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.GONE);

                e =  220.82;
                p =  12.73;
                l =  16.09;
                k =  8.27;
                berat = 50;
                ukuran = "potong B";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  220.82;
                            p =  12.73;
                            l =  16.09;
                            k =  8.27;
                            berat = 50;
                            ukuran = "potong B";


                            Img.setImageResource(R.drawable.tempe_goreng);
                            namaMakanan.setText("Tempe goreng");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("potong B");
                r2.setText("potong A");
                r3.setText("potong C");

                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 50;
                            ukuran = "potong B";
                        }else if (r2.isChecked()) {
                            berat = 30;
                            ukuran = "potong A";
                        }else if (r3.isChecked()) {
                            berat = 25;
                            ukuran = "potong C";
                        }
                    }
                });


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 25:

                id_bahan_makanan = "25";
                namaMakanan.setText("Telur ayam rebus");

                Img.setImageResource(R.drawable.telur_ayam_rebus);
                rm1.setText("Telur ayam rebus");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  154.00;
                p =  12.40;
                l =  10.80;
                k =  0.70;
                berat = 60;
                ukuran = "butir";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  154.00;
                            p =  12.40;
                            l =  10.80;
                            k =  0.70;
                            berat = 60;
                            Img.setImageResource(R.drawable.telur_ayam_rebus);
                            namaMakanan.setText("Telur ayam rebus");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("butir");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 26:

                id_bahan_makanan = "26";
                namaMakanan.setText("Telur ayam goreng / Mata sapi / Dadar");

                Img.setImageResource(R.drawable.telur_dadar);
                rm1.setText("Telur ayam goreng / Mata sapi / Dadar");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  191.80;
                p =  11.80;
                l =  15.15;
                k =  0.67;
                berat = 60;
                ukuran = "butir";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  191.80;
                            p =  11.80;
                            l =  15.15;
                            k =  0.67;
                            berat = 60;
                            Img.setImageResource(R.drawable.telur_dadar);
                            namaMakanan.setText("Telur ayam goreng / Mata sapi / Dadar");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("butir");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 27:

                id_bahan_makanan = "27";
                namaMakanan.setText("Telur bebek asin");

                Img.setImageResource(R.drawable.telur_asin_bebek);
                rm1.setText("Telur bebek asin");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  187.00;
                p =  10.90;
                l =  12.40;
                k =  7.90;
                berat = 70;
                ukuran = "butir";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  187.00;
                            p =  10.90;
                            l =  12.40;
                            k =  7.90;
                            berat = 70;
                            Img.setImageResource(R.drawable.telur_asin_bebek);
                            namaMakanan.setText("Telur bebek asin");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("butir");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 28:

                id_bahan_makanan = "28";
                namaMakanan.setText("Soto makasar");
                Img.setImageResource(R.drawable.soto_makassar);
                rm1.setText("Soto makasar");
                rm2.setText("Gulai sapi / Kambing");
                rm3.setText("Rawon daging");
                rm4.setText("Tongseng sapi / kambing");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);
                rm4.setVisibility(View.VISIBLE);

                e =  123.02;
                p =  16.37;
                l =  6.25;
                k =  0.89;
                berat = 250;
                ukuran = "porsi";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  123.02;
                            p =  16.37;
                            l =  6.25;
                            k =  0.89;
                            berat = 250;

                            r1.setText("porsi");
                            r2.setVisibility(View.GONE);


                            ukuran = "porsi";


                            Img.setImageResource(R.drawable.soto_makassar);
                            namaMakanan.setText("Soto makasar");
                        }
                        if (rm2.isChecked()){
                            e =  134.27;
                            p =  7.73;
                            l =  9.34;
                            k =  5.12;
                            berat = 220;

                            r1.setText("porsi");
                            r2.setVisibility(View.GONE);

                            ukuran = "porsi";


                            Img.setImageResource(R.drawable.gulai_sapi_kambing);
                            namaMakanan.setText("Gulai sapi / Kambing");
                        }
                        if (rm3.isChecked()){
                            e =  208.00;
                            p =  16.08;
                            l =  15.24;
                            k =  1.12;
                            berat = 285;

                            r1.setText("porsi");
                            r1.setVisibility(View.VISIBLE);

                            ukuran = "porsi";


                            Img.setImageResource(R.drawable.rawon_daging);
                            namaMakanan.setText("Rawon daging");
                        }if (rm4.isChecked()){
                            e =  201.30;
                            p =  13.02;
                            l =  13.87;
                            k =  5.08;
                            berat = 350;

                            r1.setText("porsi");
                            r1.setVisibility(View.VISIBLE);

                            ukuran = "porsi";


                            Img.setImageResource(R.drawable.tongseng_sapi);
                            namaMakanan.setText("Tongseng sapi / kambing");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("porsi");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 29:

                id_bahan_makanan = "29";
                namaMakanan.setText("Sate kambing");

                Img.setImageResource(R.drawable.sate_kambing);
                rm1.setText("Sate kambing");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  332.00;
                p =  21.73;
                l =  22.84;
                k =  13.43;
                berat = 10;
                ukuran = "tusuk";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  332.00;
                            p =  21.73;
                            l =  22.84;
                            k =  13.43;
                            berat = 10;
                            Img.setImageResource(R.drawable.sate_kambing);
                            namaMakanan.setText("Sate kambing");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("tusuk");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 30:

                id_bahan_makanan = "30";
                namaMakanan.setText("Steak daging sapi");

                Img.setImageResource(R.drawable.steak_daging);
                rm1.setText("Steak daging sapi");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  210.47;
                p =  17.00;
                l =  12.38;
                k =  6.86;
                berat = 250;
                ukuran = "porsi";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  210.47;
                            p =  17.00;
                            l =  12.38;
                            k =  6.86;
                            berat = 250;
                            Img.setImageResource(R.drawable.steak_daging);
                            namaMakanan.setText("Steak daging sapi");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("porsi");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 31:
                id_bahan_makanan = "31";
                namaMakanan.setText("Bakso sapi)");
                Img.setImageResource(R.drawable.bakso_sapi);
                rm1.setText("Bakso sapi");
                rm2.setText("Sosis sapi bakar");
                rm3.setText("Sosis ayam bakar");
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);

                e = 197.00;
                p = 21.00;
                l = 9.00;
                k = 8.00;
                berat = 90;
                ukuran = "buah besar";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // Toast.makeText(getApplicationContext(),adapterView.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
//                        if (adapterView.getSelectedItem().toString() == "Hari"){
//                            frekuensi=0;
//                            frekuensi += 1;
//                        }else if (adapterView.getSelectedItem().toString() == "Minggu"){
//                            frekuensi += 7;
//                        }else if (adapterView.getSelectedItem().toString() == "Bulan"){
//                            frekuensi += 30;
//                        }
                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });

                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 197.00;
                            p = 21.00;
                            l = 9.00;
                            k = 8.00;
                            berat = 90;
                            ukuran = "buah besar";

                            r1.setText("buah besar");
                            r2.setText("buah sedang");
                            r3.setText("buah kecil");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.VISIBLE);

                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 90;
                                        ukuran = "buah besar";
                                    }else if (r2.isChecked()) {
                                        berat = 15;
                                        ukuran = "buah sedang";
                                    }else if (r3.isChecked()) {
                                        berat = 5;
                                        ukuran = "buah kecil";
                                    }
                                }
                            });



                            Img.setImageResource(R.drawable.bakso_sapi);
                            namaMakanan.setText("Bakso sapi");

                        }else if(rm2.isChecked()){
                            e = 380.75;
                            p = 12.55;
                            l = 31.83;
                            k = 11.00;
                            berat = 70;
                            ukuran = "buah sosis besar";

                            r1.setText("buah sosis besar");
                            r1.setText("buah sosis sedang");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.GONE);

                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 70;
                                        ukuran = "buah sosis besar";
                                    }else if (r2.isChecked()) {
                                        berat = 30;
                                        ukuran = "buah sosis sedang";
                                    }
                                }
                            });

                            Img.setImageResource(R.drawable.sosis_sapi_goreng);
                            namaMakanan.setText("Sosis sapi bakar");

                        }else if (rm3.isChecked()){
                            e = 287.00;
                            p = 15.63;
                            l = 20.80;
                            k = 9.28;
                            berat = 70;
                            ukuran = "buah sosis besar";

                            r1.setText("buah sosis besar");
                            r1.setText("buah sosis sedang");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.GONE);

                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 70;
                                        ukuran = "buah sosis besar";
                                    }else if (r2.isChecked()) {
                                        berat = 30;
                                        ukuran = "buah sosis sedang";
                                    }
                                }
                            });


                            Img.setImageResource(R.drawable.sosis_ayam_goreng);
                            namaMakanan.setText("Sosis ayam bakar");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("buah besar");
                r2.setText("buah sedang");
                r3.setText("buah kecil");
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();

                        urt = berat * pengali * n /frekuensi;

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 32:

                id_bahan_makanan = "32";
                namaMakanan.setText("Opor / Kare ayam");

                Img.setImageResource(R.drawable.opor_ayam);
                rm1.setText("Opor / Kare ayam");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  302.43;
                p =  15.77;
                l =  27.29;
                k =  1.26;
                berat = 140;
                ukuran = "potong";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  302.43;
                            p =  15.77;
                            l =  27.29;
                            k =  1.26;
                            berat = 140;
                            Img.setImageResource(R.drawable.opor_ayam);
                            namaMakanan.setText("Opor / Kare ayam");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("potong");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 33:

                id_bahan_makanan = "33";
                namaMakanan.setText("Ayam goreng dada");

                Img.setImageResource(R.drawable.ayam_goreng_dada);
                rm1.setText("Ayam goreng dada");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  294.6422018;
                p =  27.00917431;
                l =  19.706422;
                k =  2.788990826;
                berat = 60;
                ukuran = "dada atas";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  294.6422018;
                            p =  27.00917431;
                            l =  19.706422;
                            k =  2.788990826;
                            berat = 60;
                            Img.setImageResource(R.drawable.ayam_goreng_dada);
                            namaMakanan.setText("Ayam goreng dada");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("dada atas");
                r2.setText("dada bawah");

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 60;
                            ukuran = "dada atas";
                        }else if (r2.isChecked()){
                            berat = 50;
                            ukuran = "dada bawah";
                        }
                    }
                });


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 34:

                id_bahan_makanan = "34";
                namaMakanan.setText("Sate ayam");

                Img.setImageResource(R.drawable.sate_ayam);
                rm1.setText("Sate ayam");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  511.86;
                p =  30.07;
                l =  42.56;
                k =  4.97;
                berat = 10;
                ukuran = "tusuk";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  511.86;
                            p =  30.07;
                            l =  42.56;
                            k =  4.97;
                            berat = 10;
                            Img.setImageResource(R.drawable.sate_ayam);
                            namaMakanan.setText("Sate ayam");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("tusuk");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 35:
                id_bahan_makanan = "35";
                namaMakanan.setText("Tongkol goreng / Pesmol / Masak balado)");
                Img.setImageResource(R.drawable.tongkol_goreng);
                rm1.setText("Tongkol goreng / Pesmol / Masak balado");
                rm2.setText("Lele goreng / Pesmol / Masak balado");
                rm3.setText("Mujair goreng / Pesmol / Masak balado");
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);

                e = 211.04;
                p = 11.76;
                l = 15.45;
                k = 6.87;
                berat = 60;
                ukuran = "potong";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 211.04;
                            p = 11.76;
                            l = 15.45;
                            k = 6.87;
                            berat = 60;

                            r1.setText("potong");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);
                            r3.setVisibility(View.GONE);

                            ukuran = "potong";

                            Img.setImageResource(R.drawable.tongkol_goreng);
                            namaMakanan.setText("Tongkol goreng / Pesmol / Masak balado");

                        }else if(rm2.isChecked()){
                            e = 399.69;
                            p = 33.78;
                            l = 28.80;
                            k = 2.22;
                            berat = 70;

                            r1.setText("ekor besar");
                            r2.setText("ekor sedang");
                            r3.setText("ekor kecil");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.VISIBLE);
                            ukuran = "ekor besar";
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 70;
                                        ukuran = "ekor besar";
                                    }else if (r2.isChecked()) {
                                        berat = 40;
                                        ukuran = "ekor sedang";
                                    }else if (r3.isChecked()) {
                                        berat = 30;
                                        ukuran = "ekor kecil";
                                    }
                                }
                            });

                            Img.setImageResource(R.drawable.lele_goreng);
                            namaMakanan.setText("Lele goreng / Pesmol / Masak balado");

                        }else if (rm3.isChecked()){
                            e = 234.22;
                            p = 24.29;
                            l = 14.72;
                            k = 0;
                            berat = 40;
                            ukuran = "ekor sedang";

                            r1.setText("ekor sedang");
                            r2.setText("ekor kecil");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.GONE);

                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 40;
                                        ukuran = "ekor sedang";
                                    }else if (r2.isChecked()) {
                                        berat = 25;
                                        ukuran = "ekor kecil";
                                    }
                                }
                            });
                            Img.setImageResource(R.drawable.mujair_goreng);
                            namaMakanan.setText("Mujair goreng / Pesmol / Masak balado");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("potong");
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.GONE);
                r3.setVisibility(View.GONE);


                System.out.println("urt luar :"+ urt +"="+berat +"*"+ pengali +"*"+ n +"/"+frekuensi);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;


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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 36:
                id_bahan_makanan = "36";
                namaMakanan.setText("Udang goreng / Pesmol / Masak balado)");
                Img.setImageResource(R.drawable.udang_goreng);
                rm1.setText("Udang goreng / Pesmol / Masak balado");
                rm2.setText("Udang kukus / Rebus / Masak sup");
                rm3.setText("Cumi kukus / Rebus / Masak sup");
                rm4.setText("Cumi goreng / Pesmol / Masak balado");
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);
                rm4.setVisibility(View.VISIBLE);

                e = 238.84;
                p = 13.79;
                l = 20.08;
                k = 1.45;
                berat = 10;
                ukuran = "ekor sedang";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 238.84;
                            p = 13.79;
                            l = 20.08;
                            k = 1.45;
                            berat = 10;

                            r1.setText("ekor sedang");
                            r2.setText("ekor kecil");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.GONE);
                            berat = 10;
                            ukuran = "ekor sedang";
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 10;
                                        ukuran = "ekor sedang";
                                    }else if(r2.isChecked()) {
                                        berat = 6;
                                        ukuran = "ekor kecil";
                                    }
                                }
                            });


                            Img.setImageResource(R.drawable.udang_goreng);
                            namaMakanan.setText("Udang goreng / Pesmol / Masak balado");

                        }else if(rm2.isChecked()){
                            e = 84.00;
                            p = 17.10;
                            l = 0.90;
                            k = 1.80;
                            berat = 10;

                            r1.setText("ekor sedang");
                            r2.setText("ekor kecil");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.GONE);
                            berat = 10;
                            ukuran = "ekor sedang";
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {


                                if (r1.isChecked()) {
                                    berat = 10;
                                    ukuran = "ekor sedang";
                                }else if (r2.isChecked()) {
                                    berat = 6;
                                    ukuran = "ekor kecil";
                                }
                                }
                            });

                            Img.setImageResource(R.drawable.udang_rebus);
                            namaMakanan.setText("Udang kukus / Rebus / Masak sup");

                        }else if (rm3.isChecked()){
                            e = 75.00;
                            p = 16.10;
                            l = 0.70;
                            k = 0.10;
                            berat = 40;

                            r1.setText("cumi sotong sedang");
                            r2.setText("cumi sotong kecil");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.GONE);
                            berat = 40;
                            ukuran = "cumi sotong sedang";
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                                    if (r1.isChecked()) {
                                        berat = 40;
                                        ukuran = "cumi sotong sedang";
                                    }else if (r2.isChecked()) {
                                        berat = 20;
                                        ukuran = "cumi sotong kecil";
                                    }
                                }
                            });

                            Img.setImageResource(R.drawable.cumi_kukus);
                            namaMakanan.setText("Cumi kukus / Rebus / Masak sup");

                        }else if (rm4.isChecked()){
                            e = 278.27;
                            p = 11.31;
                            l = 19.92;
                            k = 14.33;
                            berat = 100;

                            r1.setText("porsi");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);
                            r3.setVisibility(View.GONE);


                            ukuran = "porsi";


                            Img.setImageResource(R.drawable.cumi_goreng_tepung);
                            namaMakanan.setText("Cumi goreng / Pesmol / Masak balado");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("ekor sedang");
                r2.setText("ekor kecil");
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.GONE);



                System.out.println("urt luar :"+ urt +"="+berat +"*"+ pengali +"*"+ n +"/"+frekuensi);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();


                        urt = berat * pengali * n /frekuensi;


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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 37:

                id_bahan_makanan = "37";
                namaMakanan.setText("Hati ayam goreng");
                Img.setImageResource(R.drawable.hati_ayam_goreng);
                rm1.setText("Hati ayam goreng");
                rm2.setText("Rempela ayam goreng");
                rm3.setText("Usus ayam goreng");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);

                e =  248.07;
                p =  116.17;
                l =  37.63;
                k =  5.19;
                berat = 30;
                ukuran = "potong";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  248.07;
                            p =  116.17;
                            l =  37.63;
                            k =  5.19;
                            berat = 30;

                            r1.setText("potong");
                            r2.setVisibility(View.GONE);

                            ukuran = "potong";


                            Img.setImageResource(R.drawable.hati_ayam_goreng);
                            namaMakanan.setText("Hati ayam goreng");
                        }
                        if (rm2.isChecked()){
                            e =  181.44;
                            p =  14.81;
                            l =  12.68;
                            k =  1.17;
                            berat = 15;

                            r1.setText("potong");
                            r2.setVisibility(View.GONE);

                            ukuran = "potong";


                            Img.setImageResource(R.drawable.rempela_ayam_goreng);
                            namaMakanan.setText("Rempela ayam goreng");
                        }
                        if (rm3.isChecked()){
                            e =  403.34;
                            p =  14.40;
                            l =  38.70;
                            k =  0;
                            berat = 20;

                            r1.setText("tusuk");

                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);


                                ukuran = "tusuk";

                            Img.setImageResource(R.drawable.usus);
                            namaMakanan.setText("Usus ayam goreng");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("potong");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;


                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 38:

                id_bahan_makanan = "38";
                namaMakanan.setText("Risoles");

                Img.setImageResource(R.drawable.risoles);
                rm1.setText("Risoles");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  261.57;
                p =  6.81;
                l =  17.66;
                k =  20.00;
                berat = 50;
                ukuran = "buah";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  261.57;
                            p =  6.81;
                            l =  17.66;
                            k =  20.00;
                            berat = 50;
                            Img.setImageResource(R.drawable.risoles);
                            namaMakanan.setText("Risoles");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("buah");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 39:

                id_bahan_makanan = "39";
                namaMakanan.setText("Pisang goreng");

                Img.setImageResource(R.drawable.pisang_goreng);
                rm1.setText("Pisang goreng");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  233.80;
                p =  3.32;
                l =  9.13;
                k =  38.60;
                berat = 60;
                ukuran = "buah";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  233.80;
                            p =  3.32;
                            l =  9.13;
                            k =  38.60;
                            berat = 60;
                            Img.setImageResource(R.drawable.pisang_goreng);
                            namaMakanan.setText("Pisang goreng");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("buah");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 40:
                id_bahan_makanan = "40";
                namaMakanan.setText("Perkedel jagung");
                Img.setImageResource(R.drawable.perkedel_jagung);
                rm1.setText("Perkedel jagung");
                rm2.setText("Bakwan sayuran");
                rm2.setVisibility(View.VISIBLE);

                e = 224.06;
                p = 5.19;
                l = 8.09;
                k = 34.66;
                berat = 65;
                ukuran = "buah";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 224.06;
                            p = 5.19;
                            l = 8.09;
                            k = 34.66;
                            berat = 65;
                            Img.setImageResource(R.drawable.perkedel_jagung);
                            namaMakanan.setText("Perkedel jagung");

                        }else if(rm2.isChecked()){
                            e = 214.10;
                            p = 3.55;
                            l = 12.64;
                            k = 23.75;
                            berat = 50;
                            Img.setImageResource(R.drawable.bakwan_sayuran);
                            namaMakanan.setText("Bakwan sayuran");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("buah");
                r2.setVisibility(View.GONE);


                System.out.println("urt luar :"+ urt +"="+berat +"*"+ pengali +"*"+ n +"/"+frekuensi);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();



                        urt = berat * pengali * n /frekuensi;




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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 41:
                id_bahan_makanan = "41";
                namaMakanan.setText("Roti manis / Roti isi");
                Img.setImageResource(R.drawable.roti_manis_isi);
                rm1.setText("Roti manis / Roti isi");
                rm2.setText("Bolu kukus");
                rm2.setVisibility(View.VISIBLE);

                e = 267.00;
                p = 8.00;
                l = 0;
                k = 48.00;
                berat = 75;
                ukuran = "buah";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 267.00;
                            p = 8.00;
                            l = 0;
                            k = 48.00;
                            berat = 75;
                            Img.setImageResource(R.drawable.roti_manis_isi);
                            namaMakanan.setText("Roti manis / Roti isi");

                        }else if(rm2.isChecked()){
                            e = 241.18;
                            p = 4.74;
                            l = 1.91;
                            k = 51.64;
                            berat = 35;
                            Img.setImageResource(R.drawable.kue_bolu_kukus);
                            namaMakanan.setText("Bolu kukus");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();


                        urt = berat * pengali * n /frekuensi;

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 42:

                id_bahan_makanan = "42";
                namaMakanan.setText("Donat");

                Img.setImageResource(R.drawable.donat);
                rm1.setText("Donat");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  427.19;
                p =  6.39;
                l =  18.13;
                k =  62.11;
                berat = 30;
                ukuran = "buah";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  427.19;
                            p =  6.39;
                            l =  18.13;
                            k =  62.11;
                            berat = 30;
                            Img.setImageResource(R.drawable.donat);
                            namaMakanan.setText("Donat");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("buah");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 43:
                id_bahan_makanan = "43";
                namaMakanan.setText("Martabak telur");
                Img.setImageResource(R.drawable.martabak_telur);
                rm1.setText("Martabak telur");
                rm2.setText("Martabak manis");
                rm2.setVisibility(View.VISIBLE);

                e = 274.55;
                p = 8.88;
                l = 17.23;
                k = 22.34;
                berat = 40;
                ukuran = "potong";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 274.55;
                            p = 8.88;
                            l = 17.23;
                            k = 22.34;
                            berat = 40;
                            Img.setImageResource(R.drawable.martabak_telur);
                            namaMakanan.setText("Martabak telur");

                        }else if(rm2.isChecked()){
                            e = 336.34;
                            p = 8.85;
                            l = 6.45;
                            k = 63.48;
                            berat = 55;
                            Img.setImageResource(R.drawable.martabak_manis);
                            namaMakanan.setText("Martabak manis");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("potong");
                r2.setVisibility(View.GONE);


                System.out.println("urt luar :"+ urt +"="+berat +"*"+ pengali +"*"+ n +"/"+frekuensi);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();

                        urt = berat * pengali * n /frekuensi;

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 44:

                id_bahan_makanan = "44";
                namaMakanan.setText("Batagor");

                Img.setImageResource(R.drawable.batagor);
                rm1.setText("Batagor");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  244.21;
                p =  8.39;
                l =  16.23;
                k =  17.21;
                berat = 40;
                ukuran = "buah";



                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  244.21;
                            p =  8.39;
                            l =  16.23;
                            k =  17.21;
                            berat = 40;
                            Img.setImageResource(R.drawable.batagor);
                            namaMakanan.setText("Batagor");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("buah");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;


                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 45:
                id_bahan_makanan = "45";
                namaMakanan.setText("Wafer");
                Img.setImageResource(R.drawable.wafer);
                rm1.setText("Wafer");
                rm2.setText("Wafer roll / Astor");
                rm3.setText("Biskuit crackers, Malkist (Roma)");
                rm4.setText("Biskuit coklat (Good time, Choco mania)");
                rm5.setText("Biskuit krim (Oreo)");
                rm6.setText("Biskuit selai (Slai Olai)");
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);
                rm4.setVisibility(View.VISIBLE);
                rm5.setVisibility(View.VISIBLE);
                rm6.setVisibility(View.VISIBLE);



                e = 500.00;
                p = 5.00;
                l = 25.00;
                k = 65.00;
                berat = 10;
                ukuran = "keping";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 500.00;
                            p = 5.00;
                            l = 25.00;
                            k = 65.00;
                            berat = 10;
                            r1.setText("keping");
                            r1.setVisibility(View.VISIBLE);

                            ukuran = "keping";

                            Img.setImageResource(R.drawable.wafer);
                            namaMakanan.setText("Wafer");

                        }else if(rm2.isChecked()){
                            e = 467.00;
                            p = 6.70;
                            l = 16.70;
                            k = 73.30;
                            berat = 5;

                            r1.setText("roll");
                            r1.setVisibility(View.VISIBLE);


                            ukuran = "roll";

                            Img.setImageResource(R.drawable.wafer_roll_astor);
                            namaMakanan.setText("Wafer roll / Astor");
                        }else if(rm3.isChecked()){
                            e = 444.00;
                            p = 11.10;
                            l = 16.70;
                            k = 72.20;
                            berat = 10;

                            r1.setText("keping");
                            r1.setVisibility(View.VISIBLE);


                            ukuran = "keping";


                            Img.setImageResource(R.drawable.biskuit_crackers_malkist);
                            namaMakanan.setText("Biskuit crackers, Malkist (Roma)");
                        }else if(rm4.isChecked()){
                            e = 474.00;
                            p = 5.30;
                            l = 21.10;
                            k = 68.40;
                            berat = 6;

                            r1.setText("keping");
                            r1.setVisibility(View.VISIBLE);


                            ukuran = "keping";

                            Img.setImageResource(R.drawable.biskuit_coklat);
                            namaMakanan.setText("Biskuit coklat (Good time, Choco mania)");
                        }else if(rm5.isChecked()){
                            e = 476.00;
                            p = 3.40;
                            l = 20.40;
                            k = 71.40;
                            berat = 10;

                            r1.setText("keping");
                            r1.setVisibility(View.VISIBLE);


                            ukuran = "keping";


                            Img.setImageResource(R.drawable.biskuit_krim);
                            namaMakanan.setText("Biskuit krim (Oreo)");
                        }else if(rm6.isChecked()){
                            e = 438.00;
                            p = 6.30;
                            l = 12.50;
                            k = 75.00;
                            berat = 10;

                            r1.setText("keping");
                            r1.setVisibility(View.VISIBLE);

                            ukuran = "keping";


                            Img.setImageResource(R.drawable.biskuit_selai);
                            namaMakanan.setText("Biskuit selai (Slai Olai)");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("keping");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();

                        urt = berat * pengali * n /frekuensi;


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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 46:

                id_bahan_makanan = "46";
                namaMakanan.setText("Coklat batang (Silver queen, Cadbury, Chunky bar)");

                Img.setImageResource(R.drawable.coklat_batang);
                rm1.setText("Coklat batang (Silver queen, Cadbury, Chunky bar)");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.GONE);

                e =  567.00;
                p =  10.00;
                l =  33.30;
                k =  53.30;
                berat = 100;
                ukuran = "Chunky Bar";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  567.00;
                            p =  10.00;
                            l =  33.30;
                            k =  53.30;
                            berat = 100;


                            Img.setImageResource(R.drawable.coklat_batang);
                            namaMakanan.setText("Coklat batang (Silver queen, Cadbury, Chunky bar)");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("Chunky Bar");
                r2.setText("Silver Queen Midi");
                r3.setText("Silver Queen Jumbo");
                r4.setText("Silver Queen Chunky");
                r5.setText("Cadbury kecil");
                r6.setText("Cadbury sedang");
                r7.setText("Cadbury besar");

                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);
                r5.setVisibility(View.VISIBLE);
                r6.setVisibility(View.VISIBLE);
                r7.setVisibility(View.VISIBLE);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 100;
                            ukuran = "Chunky Bar";
                        }else if (r2.isChecked()) {
                            berat = 33;
                            ukuran = "Silver Queen Midi";
                        }else if (r3.isChecked()) {
                            berat = 68;
                            ukuran = "Silver Queen Jumbo";
                        }else if (r4.isChecked()) {
                            berat = 36;
                            ukuran = "Silver Queen Chunky";
                        }else if (r5.isChecked()) {
                            berat = 30;
                            ukuran = "Cadbury kecil";
                        }else if (r6.isChecked()) {
                            berat = 65;
                            ukuran = "Cadbury sedang";
                        }else if (r7.isChecked()) {
                            berat = 165;
                            ukuran = "Cadbury besar";
                        }
                    }
                });

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 47:
                id_bahan_makanan = "47";
                namaMakanan.setText("Cilok");
                Img.setImageResource(R.drawable.cilok);
                rm1.setText("Cilok");
                rm2.setText("Cireng");
                rm3.setText("Seblak basah");
                rm3.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);

                e = 232.00;
                p = 3.37;
                l = 0.50;
                k = 55.07;
                berat = 2000;
                ukuran = "2000 rupiah";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 232.00;
                            p = 3.37;
                            l = 0.50;
                            k = 55.07;
                            berat = 54;

                            r1.setText("2000 rupiah");
                            r2.setVisibility(View.GONE);


                            ukuran = "2000 rupiah";

                            Img.setImageResource(R.drawable.cilok);
                            namaMakanan.setText("Cilok");

                        }else if(rm2.isChecked()){
                            e = 300.11;
                            p = 6.39;
                            l = 18.52;
                            k = 29.92;
                            berat = 22;

                            r1.setText("buah");
                            r2.setVisibility(View.GONE);


                            ukuran = "buah";


                            Img.setImageResource(R.drawable.cireng);
                            namaMakanan.setText("Cireng");
                        }else if (rm3.isChecked()){
                            e = 257.04;
                            p = 2.98;
                            l = 15.00;
                            k = 28.57;
                            berat = 45;

                            r1.setText("buah");
                            r2.setVisibility(View.GONE);


                            ukuran = "buah";


                            Img.setImageResource(R.drawable.seblak_basah);
                            namaMakanan.setText("Seblak basah");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("2000 rupiah");
                r2.setVisibility(View.GONE);


                System.out.println("urt luar :"+ urt +"="+berat +"*"+ pengali +"*"+ n +"/"+frekuensi);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();



                            urt = berat * pengali * n /frekuensi;


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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 48:
                id_bahan_makanan = "48";
                namaMakanan.setText("Kerupuk udang");
                Img.setImageResource(R.drawable.kerupuk_udang);
                rm1.setText("Kerupuk udang");
                rm2.setText("Keripik kentang");
                rm3.setText("Keripik singkong");
                rm3.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);

                e = 407.48;
                p = 14.00;
                l = 13.74;
                k = 57.39;
                berat = 5;
                ukuran = "buah";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 407.48;
                            p = 14.00;
                            l = 13.74;
                            k = 57.39;
                            berat = 5;

                            r1.setText("buah");
                            r2.setVisibility(View.GONE);


                            ukuran = "buah";

                            Img.setImageResource(R.drawable.kerupuk_udang);
                            namaMakanan.setText("Kerupuk udang");

                        }else if(rm2.isChecked()){
                            e = 531.00;
                            p = 5.20;
                            l = 32.20;
                            k = 55.00;
                            berat = 10;

                            r1.setText("genggam");
                            r2.setVisibility(View.GONE);


                            ukuran = "genggam";


                            Img.setImageResource(R.drawable.keripik_kentang);
                            namaMakanan.setText("Keripik kentang");
                        }else if (rm3.isChecked()){
                            e = 522.00;
                            p = 4.30;
                            l = 69.60;
                            k = 73.90;
                            berat = 30;

                            r1.setText("genggam");
                            r2.setVisibility(View.GONE);


                            ukuran = "genggam";


                            Img.setImageResource(R.drawable.keripik_singkong);
                            namaMakanan.setText("Keripik singkong");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("buah");
                r2.setVisibility(View.GONE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();



                        urt = berat * pengali * n /frekuensi;


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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 49:

                id_bahan_makanan = "49";
                namaMakanan.setText("Minuman bersoda (Fanta, Sprite, Coca cola, Pepsi)");

                Img.setImageResource(R.drawable.minuman_bersoda_softdrink);
                rm1.setText("Minuman bersoda (Fanta, Sprite, Coca cola, Pepsi)");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.GONE);

                e =  40.00;
                p =  0;
                l =  0;
                k =  10.80;
                berat = 425;
                ukuran = "botol";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain

                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  40.00;
                            p =  0;
                            l =  0;
                            k =  10.80;
                            berat = 425;
                            Img.setImageResource(R.drawable.minuman_bersoda_softdrink);
                            namaMakanan.setText("Minuman bersoda (Fanta, Sprite, Coca cola, Pepsi)");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("botol");
                r2.setText("kaleng");

                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 425;
                            ukuran = "botol";
                        }else if (r2.isChecked()) {
                            berat = 330;

                            ukuran = "kaleng";
                        }
                    }
                });

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 50:

                id_bahan_makanan = "50";
                namaMakanan.setText("Minuman isotonik (Pocari sweet, Mizone)");

                Img.setImageResource(R.drawable.minuman_isotonik);
                rm1.setText("Minuman isotonik (Pocari sweet, Mizone)");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.GONE);

                e =  25.00;
                p =  0;
                l =  0;
                k =  6.00;
                berat = 500;
                ukuran = "Botol Mizone";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  25.00;
                            p =  0;
                            l =  0;
                            k =  6.00;
                            berat = 500;


                            Img.setImageResource(R.drawable.minuman_isotonik);
                            namaMakanan.setText("Minuman isotonik (Pocari sweet, Mizone)");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("Botol Mizone");
                r2.setText("Kaleng");
                r3.setText("Botol kecil Pocari");
                r4.setText("Botol sedang Pocari");
                r5.setText("Botol Besar Pocari");

                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);
                r5.setVisibility(View.VISIBLE);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 500;
                            ukuran = "Botol Mizone";
                        }else if (r2.isChecked()) {
                            berat = 330;
                            ukuran = "Kaleng";
                        }else if (r3.isChecked()) {
                            berat = 350;
                            ukuran = "Botol kecil Pocari";
                        }else if (r4.isChecked()) {
                            berat = 500;
                            ukuran = "Botol sedang Pocari";
                        }else if (r5.isChecked()) {
                            berat = 900;
                            ukuran = "Botol Besar Pocari";
                        }
                    }
                });



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 51:

                id_bahan_makanan = "51";
                namaMakanan.setText("Minuman berenergy (Krating daeng, Red bull)");

                Img.setImageResource(R.drawable.minuman_berenergy);
                rm1.setText("Minuman berenergy (Krating daeng, Red bull)");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.GONE);

                e =  67.00;
                p =  1.00;
                l =  0;
                k =  16.70;
                berat = 150;
                ukuran = "botol";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  67.00;
                            p =  1.00;
                            l =  0;
                            k =  16.70;
                            berat = 150;

                            Img.setImageResource(R.drawable.minuman_berenergy);
                            namaMakanan.setText("Minuman berenergy (Krating daeng, Red bull)");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);


                r1.setText("botol");
                r2.setText("kaleng");

                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 150;
                            ukuran = "botol";
                        }else if (r2.isChecked()) {
                            berat = 355;
                            ukuran = "kaleng";
                        }
                    }
                });




                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 52:
                id_bahan_makanan = "52";
                namaMakanan.setText("Minuman kemasan sari buah (Buahvita, Floridina, Minute maid pulpy, Ale ale, Country choice)");
                Img.setImageResource(R.drawable.minuman_kemasan_sari_buah);
                rm1.setText("Minuman kemasan sari buah (Buahvita, Floridina, Minute maid pulpy, Ale ale, Country choice)");
                rm2.setText("Minuman serbuk sari buah (Nutrisari, Marimas)");
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.VISIBLE);

                e = 48.00;
                p = 0;
                l = 0;
                k = 12.00;
                berat = 400;
                ukuran = "Botol Minute Maid";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 48.00;
                            p = 0;
                            l = 0;
                            k = 12.00;
                            berat = 400;
                            ukuran = "Botol Minute Maid";

                            r1.setText("Botol Minute Maid");
                            r2.setText("Botol Floridina");
                            r3.setText("Kotak Buahvita/Country Choice");
                            r4.setText("Gelas Ale ale");

                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.VISIBLE);
                            r4.setVisibility(View.VISIBLE);

                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 400;
                                        ukuran = "Botol Minute Maid";
                                    }else if (r2.isChecked()) {
                                        berat = 360;
                                        ukuran = "Botol Floridina";
                                    }else if (r3.isChecked()) {
                                        berat = 250;
                                        ukuran = "Kotak Buahvita/Country Choice";
                                    }else if (r4.isChecked()) {
                                        berat = 200;
                                        ukuran = "Gelas Ale ale";
                                    }
                                }
                            });



                            Img.setImageResource(R.drawable.minuman_kemasan_sari_buah);
                            namaMakanan.setText("Minuman kemasan sari buah (Buahvita, Floridina, Minute maid pulpy, Ale ale, Country choice)");

                        }else if(rm2.isChecked()){
                            e = 429.00;
                            p = 0;
                            l = 0;
                            k = 100.00;
                            berat = 14;
                            ukuran = "Sachet Nutrisari";

                            r1.setText("Sachet Nutrisari");
                            r2.setText("Sachet Marimas");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.GONE);
                            r4.setVisibility(View.GONE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 14;

                                        ukuran = "Sachet Nutrisari";
                                    }else if (r2.isChecked()) {
                                        berat = 8;

                                        ukuran = "Sachet Marimas";
                                    }
                                }
                            });


                            Img.setImageResource(R.drawable.minuman_serbuk_sari_buah);
                            namaMakanan.setText("Minuman serbuk sari buah (Nutrisari, Marimas)");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

        r1.setText("Botol Minute Maid");
        r2.setText("Botol Floridina");
        r3.setText("Kotak Buahvita/Country Choice");
        r4.setText("Gelas Ale ale");

        r1.setVisibility(View.VISIBLE);
        r2.setVisibility(View.VISIBLE);
        r3.setVisibility(View.VISIBLE);
        r4.setVisibility(View.VISIBLE);


                System.out.println("urt luar :"+ urt +"="+berat +"*"+ pengali +"*"+ n +"/"+frekuensi);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();



                        urt = berat * pengali * n /frekuensi;


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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 53:

                id_bahan_makanan = "53";
                namaMakanan.setText("Teh manis bukan kemasan (Gula pasirnya)");

                Img.setImageResource(R.drawable.teh_manis_bukan_kemasan);
                rm1.setText("Teh manis bukan kemasan (Gula pasirnya)");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  394;
                p =  0;
                l =  0;
                k =  94;
                berat = 5;
                ukuran = "Sendok Teh";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  394;
                            p =  0;
                            l =  0;
                            k =  94;
                            berat = 5;
                            Img.setImageResource(R.drawable.teh_manis_bukan_kemasan);
                            namaMakanan.setText("Teh manis bukan kemasan (Gula pasirnya)");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("Sendok Teh");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;



                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 54:
                id_bahan_makanan = "54";
                namaMakanan.setText("Kopi instant bubuk (Abc, Good day, Nescafe, Kapal api))");
                Img.setImageResource(R.drawable.kopi_bubuk_instant);
                rm1.setText("Kopi instant bubuk (Abc, Good day, Nescafe, Kapal api)");
                rm2.setText("Kopi instant bubuk siap minum (kopiko 78, Good day)");
                rm3.setText("Kopi manis bukan kemasan (Gula pasirnya)");
                rm2.setVisibility(View.VISIBLE);
                rm3.setVisibility(View.VISIBLE);

                e = 129.00;
                p = 0;
                l = 0;
                k = 35.00;
                berat = 31;
                ukuran = "Sachet ABC";

                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // Toast.makeText(getApplicationContext(),adapterView.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
//                        if (adapterView.getSelectedItem().toString() == "Hari"){
//                            frekuensi=0;
//                            frekuensi += 1;
//                        }else if (adapterView.getSelectedItem().toString() == "Minggu"){
//                            frekuensi += 7;
//                        }else if (adapterView.getSelectedItem().toString() == "Bulan"){
//                            frekuensi += 30;
//                        }
                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });




                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e = 129.00;
                            p = 0;
                            l = 0;
                            k = 35.00;
                            berat = 31;
                            ukuran = "Sachet ABC";

                            r1.setText("Sachet ABC");
                            r2.setText("Sachet Nescafe");
                            r3.setText("Sachet Good Day");
                            r4.setText("Sachet Kapal Api");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.VISIBLE);
                            r4.setVisibility(View.VISIBLE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 31;
                                        ukuran = "Sachet ABC";
                                    }else if (r2.isChecked()) {
                                        berat = 17.5;
                                        ukuran = "Sachet Nescafe";
                                    }else if (r3.isChecked()) {
                                        berat = 20;
                                        ukuran = "Sachet Good Day";
                                    }else if (r4.isChecked()) {
                                        berat = 25;
                                        ukuran = "Sachet Kapal Api";
                                    }
                                }
                            });



                            Img.setImageResource(R.drawable.kopi_bubuk_instant);
                            namaMakanan.setText("Kopi instant bubuk (Abc, Good day, Nescafe, Kapal api)");

                        }else if(rm2.isChecked()){
                            e = 54.00;
                            p = 1.70;
                            l = 1.50;
                            k = 9.20;
                            berat = 250;
                            ukuran = "Botol Good Day";

                            r1.setText("Botol Good Day");
                            r1.setText("Botol Kopiko 78C");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            r3.setVisibility(View.GONE);
                            r4.setVisibility(View.GONE);
                            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                    if (r1.isChecked()) {
                                        berat = 250;
                                        ukuran = "Botol Good Day";
                                    }else if (r2.isChecked()) {
                                        berat = 240;
                                        ukuran = "Botol Kopiko 78C";
                                    }
                                }
                            });



                            Img.setImageResource(R.drawable.kopi_instant_siap_minum);
                            namaMakanan.setText("Kopi instant bubuk siap minum (kopiko 78, Good day)");

                        }else if (rm3.isChecked()){
                            e = 394;
                            p = 0;
                            l = 0;
                            k = 94;
                            berat = 10;

                            r1.setText("Sendok Makan Gula Pasir");
                            r1.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.GONE);
                            r3.setVisibility(View.GONE);
                            r4.setVisibility(View.GONE);


                            ukuran = "buah sosis besar";


                            Img.setImageResource(R.drawable.kopi_manis_bukan_kemasan);
                            namaMakanan.setText("Kopi manis bukan kemasan (Gula pasirnya)");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);

                r1.setText("Sachet ABC");
                r2.setText("Sachet Nescafe");
                r3.setText("Sachet Good Day");
                r4.setText("Sachet Kapal Api");
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);


                System.out.println("urt luar :"+ urt +"="+berat +"*"+ pengali +"*"+ n +"/"+frekuensi);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

//                        Toast.makeText(getApplicationContext(),"uhuy" + txtFrekuensi.getText().toString().trim(),Toast.LENGTH_LONG).show();


                        urt = berat * pengali * n /frekuensi;



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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

                case 55:

                id_bahan_makanan = "55";
                namaMakanan.setText("Es teler / Es campur / Es goyobod");

                Img.setImageResource(R.drawable.es_teler);
                rm1.setText("Es teler / Es campur / Es goyobod");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  155.00;
                p =  0.29;
                l =  6.86;
                k =  23.93;
                berat = 300;
                ukuran = "porsi";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  155.00;
                            p =  0.29;
                            l =  6.86;
                            k =  23.93;
                            berat = 300;
                            Img.setImageResource(R.drawable.es_teler);
                            namaMakanan.setText("Es teler / Es campur / Es goyobod");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("porsi");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                            urt = berat * pengali * n /frekuensi;


                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });
                    break;

            case 56:

                id_bahan_makanan = "56";
                namaMakanan.setText("Susu cair siap minum / Kemasan");

                Img.setImageResource(R.drawable.susu_cair);
                rm1.setText("Susu cair siap minum / Kemasan");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  55.00;
                p =  3.00;
                l =  10.00;
                k =  9.00;
                berat = 200;
                ukuran = "kotak";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  55.00;
                            p =  3.00;
                            l =  10.00;
                            k =  9.00;
                            berat = 200;
                            Img.setImageResource(R.drawable.susu_cair);
                            namaMakanan.setText("Susu cair siap minum / Kemasan");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("kotak");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;


                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 57:

                id_bahan_makanan = "57";
                namaMakanan.setText("Susu bubuk");

                Img.setImageResource(R.drawable.susu_bubuk);
                rm1.setText("Susu bubuk");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  429.00;
                p =  7.10;
                l =  10.70;
                k =  71.40;
                berat = 5;
                ukuran = "Sendok Makan";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  429.00;
                            p =  7.10;
                            l =  10.70;
                            k =  71.40;
                            berat = 5;
                            Img.setImageResource(R.drawable.susu_bubuk);
                            namaMakanan.setText("Susu bubuk");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("Sendok Makan");
                r2.setVisibility(View.GONE);



                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);


                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 58:

                id_bahan_makanan = "58";
                namaMakanan.setText("Susu kental manis");

                Img.setImageResource(R.drawable.susu_kental_manis);
                rm1.setText("Susu kental manis");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  343.00;
                p =  8.20;
                l =  10.00;
                k =  55.00;
                berat = 10;
                ukuran = "Sendok Makan";

                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  343.00;
                            p =  8.20;
                            l =  10.00;
                            k =  55.00;
                            berat = 10;
                            Img.setImageResource(R.drawable.susu_kental_manis);
                            namaMakanan.setText("Susu kental manis");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("Sendok Makan");
                r2.setText("Sachet");

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 10;
                            ukuran = "Sendok Makan";
                        }else if (r2.isChecked()){
                            berat = 40;
                            ukuran = "Sachet";
                        }
                    }
                });





                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 59:

                id_bahan_makanan = "59";
                namaMakanan.setText("Yogurt");

                Img.setImageResource(R.drawable.yogurt);
                rm1.setText("Yogurt");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.GONE);

                e =  52.00;
                p =  3.30;
                l =  2.50;
                k =  4.00;
                berat = 250;
                ukuran = "Botol";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  52.00;
                            p =  3.30;
                            l =  2.50;
                            k =  4.00;
                            berat = 250;
                            Img.setImageResource(R.drawable.yogurt);
                            namaMakanan.setText("Yogurt");

                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("Botol");
                r2.setText("Kotak");

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 250;
                            ukuran = "Botol";
                        }else if (r2.isChecked()){
                            berat = 200;
                            ukuran = "Kotak";
                        }
                    }
                });





                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

            case 60:

                id_bahan_makanan = "60";
                namaMakanan.setText("Es krim");

                Img.setImageResource(R.drawable.es_krim);
                rm1.setText("Es krim");
                keteranganPilihan.setVisibility(View.GONE);
                rm1.setVisibility(View.VISIBLE);
                rm2.setVisibility(View.GONE);

                e =  210.00;
                p =  4.00;
                l =  12.50;
                k =  20.60;
                berat = 86;
                ukuran = "Batang";
                //jangan diapa-apain
                satuanFrekuensi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(adapterView.getSelectedItemId()==0){
                            frekuensi=1;
                            penampungSatuan="Hari";
                        }else if(adapterView.getSelectedItemId()==1){
                            frekuensi=7;
                            penampungSatuan="Minggu";

                        }else if(adapterView.getSelectedItemId()==2){
                            frekuensi=30;
                            penampungSatuan="Bulan";

                        }
//                        Toast.makeText(getApplicationContext(),"Sekarang : "+String.valueOf(frekuensi),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        frekuensi = 1;
                        penampungSatuan="Hari";
                    }
                });
                //jangan diapa-apain



                //Sesuaikan
                rgJenisMakanan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rm1.isChecked()){
                            e =  210.00;
                            p =  4.00;
                            l =  12.50;
                            k =  20.60;
                            berat = 86;
                            Img.setImageResource(R.drawable.es_krim);
                            namaMakanan.setText("Es krim");
                        }
                    }
                });

                rg.setVisibility(View.VISIBLE);
                r1.setText("Batang");
                r2.setText("Cup");
                r3.setText("Cone");
                r4.setText("Scoop");

                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (r1.isChecked()) {
                            berat = 86;
                            ukuran = "Batang";
                        }else if (r2.isChecked()) {
                            berat = 100;
                            ukuran = "Cup";
                        }else if (r3.isChecked()) {
                            berat = 110;
                            ukuran = "Cone";
                        }else if (r4.isChecked()) {
                            berat = 50;
                            ukuran = "Scoop";
                        }
                    }
                });




                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x = txtFrekuensi.getText().toString().trim();
                        n = Integer.parseInt(x);

                        urt = berat * pengali * n /frekuensi;

                        //Batas disesuaikan

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

//                        Toast.makeText(getApplicationContext(),"ciu :"+penampungSatuan,Toast.LENGTH_LONG).show();

                        Save();
                    }
                });

                break;

        }

    }


    private void Save() {
      //  System.out.println(urt+" ="+ berat +"*"+ pengali +"*"+ n +"/"+frekuensi);
        final String txt_email = email.toString().trim();
        final String idmakanan = id_bahan_makanan.toString().trim();
        final String makanan = namaMakanan.getText().toString().trim();
        final String jumlah = penampungProgres.toString().trim();
        final String ukuran = penampungUkuran.toString().trim();
        final String jml_frekuensi = String.valueOf(n);
        final String satuan_frekuensi  = penampungSatuan.toString().trim();
        final String energi1 = hEnergiSort;
        final String protein1 = hProteinSort;
        final String lemak1 = hLemakSort;
        final String kalori1 = hKaloriSort;

        PD.show();

        //parsing id kelas
//            final String sIdKelas = getIdKelas(ambilIDKelas);
        //final String sIdKelas = "100000";
        //final int saveIdKelas = Integer.parseInt(sIdKelas);

        StringRequest sR = new StringRequest(Request.Method.POST, ConfigUmum.URL_INSERT_FOOD_RECALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), FrekuensiBulanan.class);
                        startActivity(i);
                        finish();
                        PD.dismiss();
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        PD.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, txt_email);
                params.put(KEY_MKN, makanan);
                params.put(KEY_IDMKN, idmakanan);
                params.put(KEY_UKUR, ukuran);
                params.put(KEY_JML, jumlah);
                params.put(KEY_JML_FREQ, jml_frekuensi);
                params.put(KEY_FREQ, satuan_frekuensi);
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
        Intent i = new Intent(getApplicationContext(), FrekuensiBulanan.class);
        startActivity(i);
        finish();
    }
}