package ayyash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import ayyash.R;

public class PerhitunganPagi extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan_pagi);
        tv = (TextView)findViewById(R.id.textView);
        satuan = (TextView)findViewById(R.id.textView2);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        namaMakanan = (TextView)findViewById(R.id.textView3);
        btnKeluar =(Button)findViewById(R.id.batal);

        hitung = (Button)findViewById(R.id.hitung);
//        btnKeluar =(Button)findViewById(R.id.button3);
        satuan.setText(String.valueOf(progress));

        Img = (ImageView)findViewById(R.id.imageViewUye);
        rg = (RadioGroup)findViewById(R.id.rg);
        r1 = (RadioButton)findViewById(R.id.radioButton);
        r2 = (RadioButton)findViewById(R.id.radioButton2);
        r3 = (RadioButton)findViewById(R.id.radioButton3);
        r4 = (RadioButton)findViewById(R.id.radioButton4);
        r5 = (RadioButton)findViewById(R.id.radioButton5);
        r6 = (RadioButton)findViewById(R.id.radioButton6);
        r7 = (RadioButton)findViewById(R.id.radioButton7);
        r3.setVisibility(View.INVISIBLE);
        r4.setVisibility(View.INVISIBLE);
        r5.setVisibility(View.INVISIBLE);
        r6.setVisibility(View.INVISIBLE);
        r7.setVisibility(View.INVISIBLE);
        rg.setVisibility(View.INVISIBLE);


        progress = 0.5;
        pengali =0.5;
        satuan.setText("porsi: "+String.valueOf(progress));


        //seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = ((float)i / 2);
                satuan.setText("porsi: "+String.valueOf(progress));
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

                Intent i = new Intent(PerhitunganPagi.this, MainMenu.class);
                startActivity(i);
                finish();
            }
        });


        Intent i = getIntent();
        indexMakanan=  i.getIntExtra("indexMakanan",0);
        nM = i.getStringExtra("namaMakanan");

        tv.setText("index ke: "+indexMakanan);
        namaMakanan.setText(""+nM);


        switch (indexMakanan){
            case 0:

                Img.setImageResource(R.drawable.nasi);

                rg.setVisibility(View.VISIBLE);

                r1.setText("1 Centong Plastik");
                r2.setText("1 Centong Rice Cook");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 177.00;
                        double p = 3.90;
                        double l = 0.20;
                        double k = 39.95;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        } else if(r2.isChecked()){
                            urt = 50*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        Intent i = new Intent(PerhitunganPagi.this, SarapanActivity.class);
                        startActivity(i);
                        finish();


                    }
                });

                break;

            case 1:

                Img.setImageResource(R.drawable.nasi_merah);

                rg.setVisibility(View.VISIBLE);

                r1.setText("1 Centong Plastik");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 176;
                        double p = 3.65;
                        double l = 0.45;
                        double k = 38.1;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });

                break;

            case 2:

                Img.setImageResource(R.drawable.nasi_uduk);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 Centong Plastik");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 198.78;
                        double p = 4.26;
                        double l = 1.99;
                        double k = 41.31;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });

                break;


            case 3:

                Img.setImageResource(R.drawable.nasigoreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 Centong Plastik");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 384.18;
                        double p = 7.56;
                        double l = 21.37;
                        double k = 38.82;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;

                    }
                });

                break;

            case 4:
                Img.setImageResource(R.drawable.bubur_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 223.32;
                        double p = 13.00;
                        double l = 6.53;
                        double k = 29.19;

                        if(r1.isChecked()){
                            urt = 300*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 5:
                Img.setImageResource(R.drawable.mie_instant_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 bungkus");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 459;
                        double p = 9.40;
                        double l = 20.00;
                        double k = 60.00;

                        if(r1.isChecked()){
                            urt = 85*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;


            case 6:
                Img.setImageResource(R.drawable.mie_instant_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 bungkus");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 464.00;
                        double p = 10.10;
                        double l = 59.40;
                        double k = 1.40;

                        if(r1.isChecked()){
                            urt = 70*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 7:
                Img.setImageResource(R.drawable.mie_goreng_bukan_instan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 156.74;
                        double p = 4.22;
                        double l = 11.93;
                        double k = 38.82;

                        if(r1.isChecked()){
                            urt = 300*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 8:
                Img.setImageResource(R.drawable.kwetiau_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 144.59;
                        double p = 3.47;
                        double l = 11.20;
                        double k = 7.63;

                        if(r1.isChecked()){
                            urt = 275*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });

                break;

            case 9:
                Img.setImageResource(R.drawable.bihun_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 398.51;
                        double p = 3.23;
                        double l = 22.48;
                        double k = 6.62;

                        if(r1.isChecked()){
                            urt = 250*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 10:
                Img.setImageResource(R.drawable.mie_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 169.66;
                        double p = 5.81;
                        double l = 11.20;
                        double k = 44.30;

                        if(r1.isChecked()){
                            urt = 350*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 11:
                Img.setImageResource(R.drawable.spaghetti);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 343.41;
                        double p = 11.44;
                        double l = 17.99;
                        double k = 33.02;

                        if(r1.isChecked()){
                            urt = 300*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 12:
                Img.setImageResource(R.drawable.lontong_sayur);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 150.79;
                        double p = 2.65;
                        double l = 7.43;
                        double k = 29.19;

                        if(r1.isChecked()){
                            urt = 350*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 13:
                Img.setImageResource(R.drawable.lontong_isi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 210.17;
                        double p = 5.77;
                        double l = 6.13;
                        double k = 34.20;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 14:
                Img.setImageResource(R.drawable.roti_tawar);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("1 lembar A");
                r2.setText("1 lembar B");
                r3.setText("1 lembar C");


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 361.83;
                        double p = 7.17;
                        double l = 9.32;
                        double k = 65.14;

                        if(r1.isChecked()){
                            urt = 35*pengali;
                        }else if(r2.isChecked()){
                            urt = 20*pengali;
                        }else if(r3.isChecked()){
                            urt = 30*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;


            case 15:
                Img.setImageResource(R.drawable.ubi_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 Potong");
                r2.setVisibility(View.INVISIBLE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 176.95;
                        double p = 2.71;
                        double l = 3.25;
                        double k = 35.29;

                        if(r1.isChecked()){
                            urt = 75*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 16:
                Img.setImageResource(R.drawable.ubi_rebus);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");
                r3.setText("1 buah kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 88.00;
                        double p = 0.40;
                        double l = 0.40;
                        double k = 20.60;

                        if(r1.isChecked()){
                            urt = 150*pengali;
                        }else if(r2.isChecked()){
                            urt = 200*pengali;
                        }else if(r3.isChecked()){
                            urt = 100*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 17:
                Img.setImageResource(R.drawable.perkedel_jagung);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 126.02;
                        double p = 3.86;
                        double l = 7.57;
                        double k = 10.44;

                        if(r1.isChecked()){
                            urt = 20*pengali;
                        } if(r2.isChecked()){
                            urt = 40*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 18:
                Img.setImageResource(R.drawable.kentang_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 piring");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 316.00;
                        double p = 3.80;
                        double l = 16.10;
                        double k = 39.00;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 19:
                Img.setImageResource(R.drawable.singkong_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 175.53;
                        double p = 0.97;
                        double l = 3.07;
                        double k = 35.78;

                        if(r1.isChecked()){
                            urt = 40*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 20:
                Img.setImageResource(R.drawable.singkong_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong besar");
                r2.setText("1 potong kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 154.00;
                        double p = 1.00;
                        double l = 0.30;
                        double k = 36.80;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        } if(r2.isChecked()){
                            urt = 50*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 21:
                Img.setImageResource(R.drawable.tape_tapai_singkong);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 169.00;
                        double p = 1.40;
                        double l = 0.30;
                        double k = 40.20;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 22:
                Img.setImageResource(R.drawable.labu_siam_masak_santan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok sayur");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 80.91;
                        double p = 1.49;
                        double l = 5.57;
                        double k = 7.11;

                        if(r1.isChecked()){
                            urt = 55*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 23:
                Img.setImageResource(R.drawable.gulai_daun_singkong);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 mangkok");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 131.62;
                        double p = 3.75;
                        double l = 9.11;
                        double k = 9.42;

                        if(r1.isChecked()){
                            urt = 200*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 24:
                Img.setImageResource(R.drawable.sayur_lodeh);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok sayur");
                r2.setText("1 mangkok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 102.30;
                        double p = 2.72;
                        double l = 5.57;
                        double k = 7.11;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        } if(r2.isChecked()){
                            urt = 250*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 25:
                Img.setImageResource(R.drawable.pare_masak_santan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok sayur");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 120.22;
                        double p = 2.02;
                        double l = 10.32;
                        double k = 5.99;

                        if(r1.isChecked()){
                            urt = 50*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 26:
                Img.setImageResource(R.drawable.cap_cay);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 154.63;
                        double p = 5.04;
                        double l = 13.16;
                        double k = 3.99;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 27:
                Img.setImageResource(R.drawable.tumis_kacang_panjang);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setText("1 piring");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 174.38;
                        double p = 1.93;
                        double l = 16.05;
                        double k = 4.45;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        } if(r2.isChecked()){
                            urt = 100*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 28:
                Img.setImageResource(R.drawable.tumis_kangkung);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setText("1 piring");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 167.53;
                        double p = 2.26;
                        double l = 16.96;
                        double k = 3.48;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        } if(r2.isChecked()){
                            urt = 100*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                    }
                });
                break;

            case 29:
                Img.setImageResource(R.drawable.tumis_sawi_putih);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setText("1 piring");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 125.47;
                        double p = 1.42;
                        double l = 12.49;
                        double k = 3.24;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        } if(r2.isChecked()){
                            urt = 100*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 30:
                Img.setImageResource(R.drawable.sayur_sop);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setText("1 sendok sayur");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 110.92;
                        double p = 0.79;
                        double l = 11.82;
                        double k = 1.35;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        } if(r2.isChecked()){
                            urt = 20*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 31:
                Img.setImageResource(R.drawable.terong_balado);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setText("1 potong");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 80.20;
                        double p = 1.41;
                        double l = 5.54;
                        double k = 7.31;

                        if(r1.isChecked()){
                            urt = 90*pengali;
                        } if(r2.isChecked()){
                            urt = 15*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 32:
                Img.setImageResource(R.drawable.sayur_sop);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok sayur");
                r2.setText("1 mangkok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 44.00;
                        double p = 1.65;
                        double l = 0.36;
                        double k = 9.43;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        } if(r2.isChecked()){
                            urt = 250*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 33:
                Img.setImageResource(R.drawable.sayur_bening_bayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok sayur");
                r2.setText("1 mangkok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 72.57;
                        double p = 2.71;
                        double l = 0.53;
                        double k = 15.25;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        } if(r2.isChecked()){
                            urt = 100*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 34:
                Img.setImageResource(R.drawable.sayur_asem);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok sayur");
                r2.setText("1 mangkok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 157.20;
                        double p = 7.43;
                        double l = 6.96;
                        double k = 19.01;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        } if(r2.isChecked()){
                            urt = 250*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 35:
                Img.setImageResource(R.drawable.sayur_urap);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setText("1 porsi");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 81.75;
                        double p = 2.62;
                        double l = 5.32;
                        double k = 7.42;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        } if(r2.isChecked()){
                            urt = 150*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 36:
                Img.setImageResource(R.drawable.sayur_pecel);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 309.79;
                        double p = 13.20;
                        double l = 24.61;
                        double k = 13.49;

                        if(r1.isChecked()){
                            urt = 130*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 37:
                Img.setImageResource(R.drawable.gado_gado);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setText("1 sendok makan");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 153.15;
                        double p = 6.43;
                        double l = 10.61;
                        double k = 10.16;

                        if(r1.isChecked()){
                            urt = 200*pengali;
                        } if(r2.isChecked()){
                            urt = 20*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 38:
                Img.setImageResource(R.drawable.ketoprak);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 335.95;
                        double p = 11.84;
                        double l = 17.50;
                        double k = 35.38;

                        if(r1.isChecked()){
                            urt = 435*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 39:
                Img.setImageResource(R.drawable.sawi_hijau_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setText("1 sendok sayur");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 22.00;
                        double p = 1.87;
                        double l = 0.44;
                        double k = 3.74;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        } if(r2.isChecked()){
                            urt = 20*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 40:
                Img.setImageResource(R.drawable.daun_singkong_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setText("1 sendok sayur");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 72.00;
                        double p = 5.44;
                        double l = 0.96;
                        double k = 10.40;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        } if(r2.isChecked()){
                            urt = 50*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 41:
                Img.setImageResource(R.drawable.jengkol_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 192.00;
                        double p = 5.40;
                        double l = 0.30;
                        double k = 40.70;

                        if(r1.isChecked()){
                            urt = 24*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 42:
                Img.setImageResource(R.drawable.selada_bokor);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 lembar");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 18.00;
                        double p = 1.20;
                        double l = 0.20;
                        double k = 2.90;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 43:
                Img.setImageResource(R.drawable.ketimun);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 8.00;
                        double p = 0.20;
                        double l = 0.20;
                        double k = 1.40;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 44:
                Img.setImageResource(R.drawable.terung_belanda);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah d-5cm");
                r2.setText("1 buah-4cm");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 52.00;
                        double p = 2.10;
                        double l = 1.10;
                        double k = 8.60;

                        if(r1.isChecked()){
                            urt = 40*pengali;
                        } if(r2.isChecked()){
                            urt = 20*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 45:
                Img.setImageResource(R.drawable.tomat);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah besar");
                r2.setText("1 buah sedang");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 24.00;
                        double p = 1.30;
                        double l = 0.50;
                        double k = 4.70;

                        if(r1.isChecked()){
                            urt = 120*pengali;
                        } if(r2.isChecked()){
                            urt = 90*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 46:
                Img.setImageResource(R.drawable.pare_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);
                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 25;
                        double p = 2.3;
                        double l = 0.2;
                        double k = 3.5;

                        if(r1.isChecked()){
                            urt = 50*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 47:
                Img.setImageResource(R.drawable.pisang);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 108.00;
                        double p = 1.00;
                        double l = 0.80;
                        double k = 24.30;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 48:
                Img.setImageResource(R.drawable.mangga);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 52.00;
                        double p = 0.70;
                        double l = 0;
                        double k = 12.30;

                        if(r1.isChecked()){
                            urt = 250*pengali;
                        } if(r2.isChecked()){
                            urt = 350*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 49:
                Img.setImageResource(R.drawable.alpukat);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 85.00;
                        double p = 0.90;
                        double l = 6.50;
                        double k = 7.70;

                        if(r1.isChecked()){
                            urt = 130*pengali;
                        } if(r2.isChecked()){
                            urt = 170*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 50:
                Img.setImageResource(R.drawable.jeruk);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 45.00;
                        double p = 0.90;
                        double l = 0.20;
                        double k = 11.20;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        } if(r2.isChecked()){
                            urt = 110*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 51:
                Img.setImageResource(R.drawable.pepaya);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong panjang");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 46.00;
                        double p = 0.50;
                        double l = 0.10;
                        double k = 12.20;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 52:
                Img.setImageResource(R.drawable.nanas);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong panjang");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 40.00;
                        double p = 0.60;
                        double l = 0.30;
                        double k = 9.90;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 53:
                Img.setImageResource(R.drawable.semangka);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 28.00;
                        double p = 0.50;
                        double l = 0.20;
                        double k = 6.90;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 54:
                Img.setImageResource(R.drawable.melon);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong panjang");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 31.00;
                        double p = 0.30;
                        double l = 0.30;
                        double k = 6.70;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 55:
                Img.setImageResource(R.drawable.pir);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 34.00;
                        double p = 0.50;
                        double l = 0.20;
                        double k = 7.60;

                        if(r1.isChecked()){
                            urt = 120*pengali;
                        } if(r2.isChecked()){
                            urt = 200*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 56:
                Img.setImageResource(R.drawable.apel);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 58.00;
                        double p = 0.30;
                        double l = 0.40;
                        double k = 14.90;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        } if(r2.isChecked()){
                            urt = 200*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 57:
                Img.setImageResource(R.drawable.anggur);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 68.00;
                        double p = 0.80;
                        double l = 0;
                        double k = 16.30;

                        if(r1.isChecked()){
                            urt = 12*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 58:
                Img.setImageResource(R.drawable.buah_naga);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong sedang");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 31.00;
                        double p = 0.30;
                        double l = 0.30;
                        double k = 6.70;

                        if(r1.isChecked()){
                            urt = 80*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 59:
                Img.setImageResource(R.drawable.duku);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 63.00;
                        double p = 1.00;
                        double l = 0.20;
                        double k = 16.10;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 60:
                Img.setImageResource(R.drawable.durian_lokal);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 biji durian lokal");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 149.00;
                        double p = 2.50;
                        double l = 3.00;
                        double k = 21.50;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 61:
                Img.setImageResource(R.drawable.durian_montong);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 biji durian montong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 149.00;
                        double p = 2.50;
                        double l = 3.00;
                        double k = 21.50;

                        if(r1.isChecked()){
                            urt = 150*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 62:
                Img.setImageResource(R.drawable.jambu_biji);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 49.00;
                        double p = 0.90;
                        double l = 0.30;
                        double k = 12.20;

                        if(r1.isChecked()){
                            urt = 250*pengali;
                        } if(r2.isChecked()){
                            urt = 300*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 63:
                Img.setImageResource(R.drawable.kurma);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 biji");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 296.00;
                        double p = 2.50;
                        double l = 0;
                        double k = 71.50;

                        if(r1.isChecked()){
                            urt = 8*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 64:
                Img.setImageResource(R.drawable.rambutan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 69.00;
                        double p = 0.90;
                        double l = 0.10;
                        double k = 18.10;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 65:
                Img.setImageResource(R.drawable.salak);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 77.00;
                        double p = 0.40;
                        double l = 0.10;
                        double k = 2.90;

                        if(r1.isChecked()){
                            urt = 40*pengali;
                        } if(r2.isChecked()){
                            urt = 70*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 66:
                Img.setImageResource(R.drawable.strawberry);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 23.00;
                        double p = 1.70;
                        double l = 0.10;
                        double k = 2.70;

                        if(r1.isChecked()){
                            urt = 17*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 67:
                Img.setImageResource(R.drawable.belimbing);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 36;
                        double p = 0.4;
                        double l = 0.4;
                        double k = 8.8;

                        if(r1.isChecked()){
                            urt = 150*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 68:
                Img.setImageResource(R.drawable.nangka);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 biji sedang");
                r2.setText("1 biji besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 106;
                        double p = 1.2;
                        double l = 0.3;
                        double k = 27.6;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        } if(r2.isChecked()){
                            urt = 35*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 69:
                Img.setImageResource(R.drawable.salad_buah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 201.42;
                        double p = 1.65;
                        double l = 17.86;
                        double k = 9.09;

                        if(r1.isChecked()){
                            urt = 300*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 70:
                Img.setImageResource(R.drawable.tempe_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong besar");
                r2.setText("1 potong segitiga");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 220.82;
                        double p = 12.73;
                        double l = 16.09;
                        double k = 8.27;

                        if(r1.isChecked()){
                            urt = 50*pengali;
                        } if(r2.isChecked()){
                            urt = 30*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 71:
                Img.setImageResource(R.drawable.tempe_goreng_tepung);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 329.79;
                        double p = 13.33;
                        double l = 18.06;
                        double k = 32.17;

                        if(r1.isChecked()){
                            urt = 50*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 72:
                Img.setImageResource(R.drawable.tahu_goreng);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");
                r3.setText("1 buah kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 164.90;
                        double p = 9.81;
                        double l = 14.23;
                        double k = 0.72;

                        if(r1.isChecked()){
                            urt = 40*pengali;
                        }else if(r2.isChecked()){
                            urt = 80*pengali;
                        }else if(r3.isChecked()){
                            urt = 20*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 73:
                Img.setImageResource(R.drawable.tahu_isi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 128.02;
                        double p = 8.97;
                        double l = 7.29;
                        double k = 8.65;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 74:
                Img.setImageResource(R.drawable.tempe_orek);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 185.11;
                        double p = 12.67;
                        double l = 10.75;
                        double k = 11.61;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 75:
                Img.setImageResource(R.drawable.tahu_pepes);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 bungkus");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 95.82;
                        double p = 10.07;
                        double l = 6.22;
                        double k = 1.59;

                        if(r1.isChecked()){
                            urt = 85*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 76:
                Img.setImageResource(R.drawable.tahu_bacem);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 305.08;
                        double p = 7.59;
                        double l = 26.87;
                        double k = 8.64;

                        if(r1.isChecked()){
                            urt = 36*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 77:
                Img.setImageResource(R.drawable.tempe_bacem);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 351.75;
                        double p = 9.65;
                        double l = 28.87;
                        double k = 14.17;

                        if(r1.isChecked()){
                            urt = 50*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 78:
                Img.setImageResource(R.drawable.telur_ayam_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 butir");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 154.00;
                        double p = 12.40;
                        double l = 10.80;
                        double k = 0.70;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 79:
                Img.setImageResource(R.drawable.telur_dadar);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 butir");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 191.80;
                        double p = 11.80;
                        double l = 15.15;
                        double k = 0.67;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 80:
                Img.setImageResource(R.drawable.telur_asin_bebek);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 butir");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 187.00;
                        double p = 10.90;
                        double l = 12.40;
                        double k = 7.90;

                        if(r1.isChecked()){
                            urt = 70*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 81:
                Img.setImageResource(R.drawable.soto_makassar);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 123.02;
                        double p = 16.37;
                        double l = 6.25;
                        double k = 0.89;

                        if(r1.isChecked()){
                            urt = 250*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 82:
                Img.setImageResource(R.drawable.gulai_sapi_kambing);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 134.27;
                        double p = 7.73;
                        double l = 9.34;
                        double k = 5.12;

                        if(r1.isChecked()){
                            urt = 220*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 83:
                Img.setImageResource(R.drawable.rawon_daging);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 208.00;
                        double p = 16.08;
                        double l = 15.24;
                        double k = 1.12;

                        if(r1.isChecked()){
                            urt = 285*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 84:
                Img.setImageResource(R.drawable.tongseng_sapi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 201.30;
                        double p = 13.02;
                        double l = 13.87;
                        double k = 5.08;

                        if(r1.isChecked()){
                            urt = 350*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 85:
                Img.setImageResource(R.drawable.sate_kambing);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 tusuk");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 332.00;
                        double p = 21.73;
                        double l = 22.84;
                        double k = 13.43;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 86:
                Img.setImageResource(R.drawable.steak_daging);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 210.47;
                        double p = 17.00;
                        double l = 12.38;
                        double k = 6.86;

                        if(r1.isChecked()){
                            urt = 250*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 87:
                Img.setImageResource(R.drawable.bakso_sapi);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("1 buah sedang");
                r2.setText("1 buah besar");
                r3.setText("1 buah kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 197.00;
                        double p = 21.00;
                        double l = 9.00;
                        double k = 8.00;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        }else if(r2.isChecked()){
                            urt = 90*pengali;
                        }else if(r3.isChecked()){
                            urt = 5*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 88:
                Img.setImageResource(R.drawable.sosis_sapi_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sosis sedang");
                r2.setText("1 buah sosis besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 380.75;
                        double p = 12.55;
                        double l = 31.83;
                        double k = 11.00;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        } if(r2.isChecked()){
                            urt = 70*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 89:
                Img.setImageResource(R.drawable.sosis_ayam_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah sosis sedang");
                r2.setText("1 buah sosis besar");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 287.00;
                        double p = 15.63;
                        double l = 20.80;
                        double k = 9.28;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        } if(r2.isChecked()){
                            urt = 70*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 90:
                Img.setImageResource(R.drawable.opor_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 302.43;
                        double p = 15.77;
                        double l = 27.29;
                        double k = 1.26;

                        if(r1.isChecked()){
                            urt = 140*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 91:
                Img.setImageResource(R.drawable.sate_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 tusuk");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 511.86;
                        double p = 30.07;
                        double l = 42.56;
                        double k = 4.97;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 92:
                Img.setImageResource(R.drawable.soto_ayam);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 251.28;
                        double p = 15.78;
                        double l = 19.85;
                        double k = 2.20;

                        if(r1.isChecked()){
                            urt = 300*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 93:
                Img.setImageResource(R.drawable.bebek_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah potong dada");
                r2.setText("1 buah potong paha");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 376.27;
                        double p = 14.55;
                        double l = 35.09;
                        double k = 0;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        } if(r2.isChecked()){
                            urt = 100*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 94:
                Img.setImageResource(R.drawable.nugget_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 134.73;
                        double p = 6.78;
                        double l = 9.00;
                        double k = 5.81;

                        if(r1.isChecked()){
                            urt = 20*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 95:
                Img.setImageResource(R.drawable.ayam_goreng_dada);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong dada atas");
                r2.setText("1 potong dada bawah");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 294.6422018;
                        double p = 27.00917431;
                        double l = 19.706422;
                        double k = 2.788990826;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        } if(r2.isChecked()){
                            urt = 50*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 96:
                Img.setImageResource(R.drawable.ayam_goreng_sayap);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong sayap");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 401.7981651;
                        double p = 25.10091743;
                        double l = 33.6513761;
                        double k = 0;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 97:
                Img.setImageResource(R.drawable.ayam_goreng_paha);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong paha atas");
                r2.setText("1 potong paha bawah");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 395.93;
                        double p = 24.95;
                        double l = 33.06;
                        double k = 0;

                        if(r1.isChecked()){
                            urt = 40*pengali;
                        } if(r2.isChecked()){
                            urt = 30*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                    }
                });
                break;

            case 98:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_dada_atas);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong dada atas");
                r2.setText("1 potong dada mentok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 264;
                        double p = 21.2;
                        double l = 16.6;
                        double k = 8.5;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        } if(r2.isChecked()){
                            urt = 170*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 99:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_dada_atas_tanpa_kulit);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong dada atas");
                r2.setText("1 potong dada mentok");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 153;
                        double p = 27.3;
                        double l = 4.8;
                        double k = 0.3;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        } if(r2.isChecked()){
                            urt = 170*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 100:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_sayap);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 337;
                        double p = 20.8;
                        double l = 23;
                        double k = 11.7;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 101:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_sayap_tanpa_kulit);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 236;
                        double p = 28.7;
                        double l = 12.1;
                        double k = 3;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 102:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_paha_bawah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 274;
                        double p = 20.6;
                        double l = 17.7;
                        double k = 8;

                        if(r1.isChecked()){
                            urt = 70*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 103:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_paha_bawah_tanpa_kulit);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 170;
                        double p = 25.6;
                        double l = 7.4;
                        double k = 0;

                        if(r1.isChecked()){
                            urt = 70*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 104:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_paha_atas);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 309;
                        double p = 17.2;
                        double l = 22.1;
                        double k = 10.3;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 105:
                Img.setImageResource(R.drawable.ayam_goreng_tepung_paha_atas_tanpa_kulit);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 179;
                        double p = 22.4;
                        double l = 10;
                        double k = 0;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 106:
                Img.setImageResource(R.drawable.tongkol_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 211.04;
                        double p = 11.76;
                        double l = 15.45;
                        double k = 6.87;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 107:
                Img.setImageResource(R.drawable.lele_goreng);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("1 ekor besar");
                r2.setText("1 ekor sedang");
                r3.setText("1 ekor kecil");


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 399.69;
                        double p = 33.78;
                        double l = 28.80;
                        double k = 2.22;

                        if(r1.isChecked()){
                            urt = 70*pengali;
                        }else if(r2.isChecked()){
                            urt = 40*pengali;
                        }else if(r3.isChecked()){
                            urt = 30*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 108:
                Img.setImageResource(R.drawable.mujair_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 ekor sedang");
                r2.setText("1 ekor kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 234.22;
                        double p = 24.29;
                        double l = 14.72;
                        double k = 0;

                        if(r1.isChecked()){
                            urt = 40*pengali;
                        } if(r2.isChecked()){
                            urt = 25*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 109:
                Img.setImageResource(R.drawable.teri_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 232.02;
                        double p = 28.63;
                        double l = 12.42;
                        double k = 0;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 110:
                Img.setImageResource(R.drawable.ikan_bakar);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 123;
                        double p = 20;
                        double l = 4.8;
                        double k = 0;

                        if(r1.isChecked()){
                            urt = 175*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 111:
                Img.setImageResource(R.drawable.ikan_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 ekor sedang");
                r2.setText("1 ekor kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 135.54;
                        double p = 16.56;
                        double l = 3.99;
                        double k = 8.16;

                        if(r1.isChecked()){
                            urt = 130*pengali;
                        } if(r2.isChecked()){
                            urt = 100*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 112:
                Img.setImageResource(R.drawable.udang_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 ekor sedang");
                r2.setText("1 ekor kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 238.84;
                        double p = 13.79;
                        double l = 20.08;
                        double k = 1.45;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        } if(r2.isChecked()){
                            urt = 6*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 113:
                Img.setImageResource(R.drawable.udang_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 ekor sedang");
                r2.setText("1 ekor kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 84.00;
                        double p = 17.10;
                        double l = 0.90;
                        double k = 1.80;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        } if(r2.isChecked()){
                            urt = 6*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 114:
                Img.setImageResource(R.drawable.cumi_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 cumi sotong sedang");
                r2.setText("1 cumi sotong kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 75.00;
                        double p = 16.10;
                        double l = 0.70;
                        double k = 0.10;

                        if(r1.isChecked()){
                            urt = 40*pengali;
                        } if(r2.isChecked()){
                            urt = 20*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 115:
                Img.setImageResource(R.drawable.cumi_goreng_tepung);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 278.27;
                        double p = 11.31;
                        double l = 19.92;
                        double k = 14.33;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 116:
                Img.setImageResource(R.drawable.hati_ayam_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 248.07;
                        double p = 116.17;
                        double l = 37.63;
                        double k = 5.19;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 117:
                Img.setImageResource(R.drawable.rempela_ayam_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 181.44;
                        double p = 14.81;
                        double l = 12.68;
                        double k = 1.17;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 118:
                Img.setImageResource(R.drawable.usus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 tusuk");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 403.34;
                        double p = 14.40;
                        double l = 38.70;
                        double k = 0;

                        if(r1.isChecked()){
                            urt = 20*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 119:
                Img.setImageResource(R.drawable.kacang_tanah_rebus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 piring");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 525.00;
                        double p = 27.90;
                        double l = 42.70;
                        double k = 17.40;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 120:
                Img.setImageResource(R.drawable.kacang_atom);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 genggam");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 533.30;
                        double p = 6.70;
                        double l = 28.90;
                        double k = 57.80;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 121:
                Img.setImageResource(R.drawable.bubur_kacang_hijau);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 176.68;
                        double p = 5.92;
                        double l = 8.95;
                        double k = 21.27;

                        if(r1.isChecked()){
                            urt = 150*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 122:
                Img.setImageResource(R.drawable.risoles);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 261.57;
                        double p = 6.81;
                        double l = 17.66;
                        double k = 20.00;

                        if(r1.isChecked()){
                            urt = 50*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 123:
                Img.setImageResource(R.drawable.lemper);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 250.17;
                        double p = 8.22;
                        double l = 12.73;
                        double k = 25.33;

                        if(r1.isChecked()){
                            urt = 35*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 124:
                Img.setImageResource(R.drawable.bakpia);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 272.00;
                        double p = 8.70;
                        double l = 6.70;
                        double k = 44.10;

                        if(r1.isChecked()){
                            urt = 25*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 125:
                Img.setImageResource(R.drawable.pisang_goreng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 233.80;
                        double p = 3.32;
                        double l = 9.13;
                        double k = 38.60;

                        if(r1.isChecked()){
                            urt = 60*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 126:
                Img.setImageResource(R.drawable.perkedel_jagung);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 224.06;
                        double p = 5.19;
                        double l = 8.09;
                        double k = 34.66;

                        if(r1.isChecked()){
                            urt = 65*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 127:
                Img.setImageResource(R.drawable.bakwan_sayuran);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 214.10;
                        double p = 3.55;
                        double l = 12.64;
                        double k = 23.75;

                        if(r1.isChecked()){
                            urt = 50*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 128:
                Img.setImageResource(R.drawable.roti_manis_isi);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 267.00;
                        double p = 8.00;
                        double l = 0;
                        double k = 48.00;

                        if(r1.isChecked()){
                            urt = 75*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 129:
                Img.setImageResource(R.drawable.bakwan_sayuran);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 427.19;
                        double p = 6.39;
                        double l = 18.13;
                        double k = 62.11;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 130:
                Img.setImageResource(R.drawable.kue_bolu_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 241.18;
                        double p = 4.74;
                        double l = 1.91;
                        double k = 51.64;

                        if(r1.isChecked()){
                            urt = 35*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 131:
                Img.setImageResource(R.drawable.martabak_telur);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 274.55;
                        double p = 8.88;
                        double l = 17.23;
                        double k = 22.34;

                        if(r1.isChecked()){
                            urt = 40*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 132:
                Img.setImageResource(R.drawable.martabak_manis);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 336.34;
                        double p = 8.85;
                        double l = 6.45;
                        double k = 63.48;

                        if(r1.isChecked()){
                            urt = 55*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 133:
                Img.setImageResource(R.drawable.batagor);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 244.21;
                        double p = 8.39;
                        double l = 16.23;
                        double k = 17.21;

                        if(r1.isChecked()){
                            urt = 40*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 134:
                Img.setImageResource(R.drawable.pempek);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);

                r1.setText("1 buah kapal selam");
                r2.setText("1 buah lenjer");
                r3.setText("1 buah kulit");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 318.79;
                        double p = 6.70;
                        double l = 6.87;
                        double k = 56.78;

                        if(r1.isChecked()){
                            urt = 85*pengali;
                        }else if(r2.isChecked()){
                            urt = 100*pengali;
                        }else if(r3.isChecked()){
                            urt = 76*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 135:
                Img.setImageResource(R.drawable.siomay_kukus);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 319.38;
                        double p = 4.65;
                        double l = 0.52;
                        double k = 71.67;

                        if(r1.isChecked()){
                            urt = 50*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 136:
                Img.setImageResource(R.drawable.cilok);
                rg.setVisibility(View.VISIBLE);

                r1.setText("2000 rupiah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 232.00;
                        double p = 3.37;
                        double l = 0.50;
                        double k = 55.07;

                        if(r1.isChecked()){
                            urt = 54*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 137:
                Img.setImageResource(R.drawable.cireng);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 300.11;
                        double p = 6.39;
                        double l = 18.52;
                        double k = 29.92;

                        if(r1.isChecked()){
                            urt = 22*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 138:
                Img.setImageResource(R.drawable.comro);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 257.03;
                        double p = 2.98;
                        double l = 15.00;
                        double k = 18.57;

                        if(r1.isChecked()){
                            urt = 45*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });


                break;

            case 139:
                Img.setImageResource(R.drawable.seblak_basah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 185.33;
                        double p = 9.20;
                        double l = 8.18;
                        double k = 18.73;

                        if(r1.isChecked()){
                            urt = 220*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 140:
                Img.setImageResource(R.drawable.wafer);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 keping");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 500;
                        double p = 5.00;
                        double l = 25.00;
                        double k = 65.00;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 141:
                Img.setImageResource(R.drawable.wafer_roll_astor);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 roll");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 4.67;
                        double p = 6.70;
                        double l = 16.70;
                        double k = 73.30;

                        if(r1.isChecked()){
                            urt = 5*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });

                break;

            case 142:
                Img.setImageResource(R.drawable.biskuit_crackers_malkist);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 keping");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 444.00;
                        double p = 11.10;
                        double l = 16.70;
                        double k = 72.20;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });


                break;

            case 143:
                Img.setImageResource(R.drawable.biskuit_coklat);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 keping");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 474.00;
                        double p = 5.30;
                        double l = 21.10;
                        double k = 68.40;

                        if(r1.isChecked()){
                            urt = 6*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });


                break;

            case 144:
                Img.setImageResource(R.drawable.biskuit_krim);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 keping");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 476;
                        double p = 3.40;
                        double l = 20.40;
                        double k = 71.40;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });


                break;

            case 145:
                Img.setImageResource(R.drawable.biskuit_selai);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 keping");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 438.00;
                        double p = 6.30;
                        double l = 12.50;
                        double k = 75.00;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
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
                r2.setText("Midi");
                r3.setText("Jumbo");
                r4.setText("Chunky");
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

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }else if(r2.isChecked()){
                            urt = 33*pengali;
                        }else if(r3.isChecked()){
                            urt = 68*pengali;
                        }else if(r4.isChecked()){
                            urt = 36*pengali;
                        }else if(r5.isChecked()){
                            urt = 30*pengali;
                        }else if(r6.isChecked()){
                            urt = 65*pengali;
                        }else if(r7.isChecked()){
                            urt = 165*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });

                break;

            case 147:
                Img.setImageResource(R.drawable.pizza);

                rg.setVisibility(View.VISIBLE);

                r1.setText("1 iris besar");
                r2.setVisibility(View.VISIBLE);
                r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 298;
                        double p = 12;
                        double l = 14.2;
                        double k = 30.5;

                        if(r1.isChecked()){
                            urt = 70*pengali;
                        }else if(r2.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });


                break;

            case 148:

                Img.setImageResource(R.drawable.rempeyek_kacang);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 532;
                        double p = 7.00;
                        double l = 47.48;
                        double k = 21.69;

                        if(r1.isChecked()){
                            urt = 15*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });


                break;

            case 149:
                Img.setImageResource(R.drawable.kerupuk_udang);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setVisibility(View.INVISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 407.48;
                        double p = 14.00;
                        double l = 13.74;
                        double k = 57.39;

                        if(r1.isChecked()){
                            urt = 5*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });



                break;

            case 150:
                Img.setImageResource(R.drawable.keripik_kentang);

                rg.setVisibility(View.VISIBLE);

                r1.setText("1 genggam");
                r2.setVisibility(View.INVISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 531.00;
                        double p = 5.20;
                        double l = 32.20;
                        double k = 55.00;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });

                break;

            case 151:
                Img.setImageResource(R.drawable.keripik_singkong);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 genggam");
                r2.setVisibility(View.INVISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 522;
                        double p = 4.30;
                        double l = 69.60;
                        double k = 73.90;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });


                break;

            case 152:
                Img.setImageResource(R.drawable.kerupuk_melinjo_emping);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 genggam");
                r2.setVisibility(View.INVISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 345.00;
                        double p = 12.00;
                        double l = 1.50;
                        double k = 71.50;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });

                break;

            case 153:
                Img.setImageResource(R.drawable.happy_tos);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 bungkus kecil");
                r2.setText("1 bungkus besar");
                r2.setVisibility(View.VISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 500.00;
                        double p = 7.10;
                        double l = 25.00;
                        double k = 64.30;

                        if(r1.isChecked()){
                            urt = 55*pengali;
                        }else if(r2.isChecked()){
                            urt = 160*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });

                break;

            case 154:
                Img.setImageResource(R.drawable.popcorn);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 piring");
                // r2.setText("1 bungkus besar");
                r2.setVisibility(View.INVISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 380.00;
                        double p = 6.90;
                        double l = 0.80;
                        double k = 86.40;

                        if(r1.isChecked()){
                            urt = 100*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });

                break;

            case 155:
                Img.setImageResource(R.drawable.permen);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 buah");
                r2.setText("1 lolipop");
                r2.setVisibility(View.VISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 444;
                        double p = 7.10;
                        double l = 25.00;
                        double k = 64.30;

                        if(r1.isChecked()){
                            urt = 55*pengali;
                        }else if(r2.isChecked()){
                            urt = 160*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 156:
                Img.setImageResource(R.drawable.minuman_bersoda_softdrink);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 botol");
                r2.setText("1 kaleng");
                r2.setVisibility(View.VISIBLE);
                //  r2.setText("S1 iris kecil");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 40;
                        double p = 0;
                        double l = 0;
                        double k = 10.80;

                        if(r1.isChecked()){
                            urt = 425*pengali;
                        }else if(r2.isChecked()){
                            urt = 330*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });

                break;



            case 157:
                Img.setImageResource(R.drawable.minuman_isotonik);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 botol mizone");
                r2.setText("1 kaleng");
                r3.setText("1 botol kecil pocari");
                r4.setText("1 botol sedang pocari");
                r5.setText("1 botol besar pocari");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 25.00;
                        double p = 0;
                        double l = 0;
                        double k = 6.00;

                        if(r1.isChecked()){
                            urt = 500*pengali;
                        } if(r2.isChecked()){
                            urt = 330*pengali;
                        }if(r3.isChecked()){
                            urt = 350*pengali;
                        }if(r4.isChecked()){
                            urt = 500*pengali;
                        }if(r5.isChecked()){
                            urt = 900*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 158:
                Img.setImageResource(R.drawable.minuman_berenergy);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 botol");
                r2.setText("1 kaleng");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 67.00;
                        double p = 1.00;
                        double l = 0;
                        double k = 16.70;

                        if(r1.isChecked()){
                            urt = 150*pengali;
                        } if(r2.isChecked()){
                            urt = 355*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 159:
                Img.setImageResource(R.drawable.minuman_kemasan_sari_buah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 botol minute maid");
                r2.setText("1 botol floridina");
                r3.setText("1 kotak buavita / country choice");
                r4.setText("1 gelas ale ale");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 48.00;
                        double p = 0;
                        double l = 0;
                        double k = 12.00;

                        if(r1.isChecked()){
                            urt = 400*pengali;
                        } if(r2.isChecked()){
                            urt = 360*pengali;
                        }if (r3.isChecked()){
                            urt = 250*pengali;
                        }if (r4.isChecked()){
                            urt = 200*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;




            case 160:
                Img.setImageResource(R.drawable.minuman_serbuk_sari_buah);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sachet nutrisari ");
                r2.setText("1 sachet marimas");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 429.00;
                        double p = 0;
                        double l = 0;
                        double k = 100.00;

                        if(r1.isChecked()){
                            urt = 14*pengali;
                        } if(r2.isChecked()){
                            urt = 8*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 161:
                Img.setImageResource(R.drawable.teh_manis_bukan_kemasan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok teh");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 394;
                        double p = 0;
                        double l = 0;
                        double k = 94;

                        if(r1.isChecked()){
                            urt = 5*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
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

                r1.setText("1 botol kaca");
                r2.setText("1 botol plastik A");
                r3.setText("1 botol plastik B");
                r4.setText("1 kotak A");
                r5.setText("1 kotak B");
                r6.setText("1 gelas");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 39.00;
                        double p = 0;
                        double l = 0;
                        double k = 10.00;

                        if(r1.isChecked()){
                            urt = 220*pengali;
                        }else if(r2.isChecked()){
                            urt = 450*pengali;
                        }else if(r3.isChecked()){
                            urt = 500*pengali;
                        }else if(r4.isChecked()){
                            urt = 250*pengali;
                        }else if(r5.isChecked()){
                            urt = 300*pengali;
                        }else if(r6.isChecked()){
                            urt = 180*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 163:
                Img.setImageResource(R.drawable.kopi_bubuk_instant);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);

                r1.setText("1 sachet ABC");
                r2.setText("1 sachet Nescafe");
                r3.setText("1 sachet Good Day");
                r4.setText("1 sachet Kapal Api");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 129.00;
                        double p = 0;
                        double l = 0;
                        double k = 35.00;

                        if(r1.isChecked()){
                            urt = 31*pengali;
                        }else if(r2.isChecked()){
                            urt = 17.5*pengali;
                        }else if(r3.isChecked()){
                            urt = 20*pengali;
                        }else if(r4.isChecked()){
                            urt = 25*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 164:
                Img.setImageResource(R.drawable.kopi_instant_siap_minum);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 botol good day");
                r2.setText("1 botol kopiko 78C");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 54.00;
                        double p = 1.70;
                        double l = 1.50;
                        double k = 9.20;

                        if(r1.isChecked()){
                            urt = 250*pengali;
                        } if(r2.isChecked()){
                            urt = 240*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 165:
                Img.setImageResource(R.drawable.kopi_manis_bukan_kemasan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan gula pasir");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 394;
                        double p = 0;
                        double l = 0;
                        double k = 94;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 166:
                Img.setImageResource(R.drawable.es_kelapa_muda);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 gelas");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 49.23;
                        double p = 0.29;
                        double l = 0.24;
                        double k = 11.48;

                        if(r1.isChecked()){
                            urt = 350*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 167:
                Img.setImageResource(R.drawable.es_teler);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 porsi");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 155.00;
                        double p = 1.98;
                        double l = 6.86;
                        double k = 23.93;

                        if(r1.isChecked()){
                            urt = 300*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 168:
                Img.setImageResource(R.drawable.orange_water);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 botol kaca");
                r2.setText("1 botol plastik");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 32.00;
                        double p = 0;
                        double l = 0;
                        double k = 8.00;

                        if(r1.isChecked()){
                            urt = 140*pengali;
                        } if(r2.isChecked()){
                            urt = 600*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 169:
                Img.setImageResource(R.drawable.sari_kacang_hijau);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 kotak");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 36.00;
                        double p = 0.80;
                        double l = 0;
                        double k = 8.80;

                        if(r1.isChecked()){
                            urt = 250*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 170:
                Img.setImageResource(R.drawable.susu_cair);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 kotak");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 55.00;
                        double p = 3.00;
                        double l = 10.00;
                        double k = 9.00;

                        if(r1.isChecked()){
                            urt = 200*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 171:
                Img.setImageResource(R.drawable.susu_bubuk);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 429.00;
                        double p = 7.10;
                        double l = 10.70;
                        double k = 71.40;

                        if(r1.isChecked()){
                            urt = 5*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 172:
                Img.setImageResource(R.drawable.susu_kental_manis);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setText("1 sachet");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 343.00;
                        double p = 8.20;
                        double l = 10.00;
                        double k = 55.00;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        } if(r2.isChecked()){
                            urt = 40*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 173:
                Img.setImageResource(R.drawable.yogurt);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 botol");
                r2.setText("1 kotak");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 52.00;
                        double p = 3.30;
                        double l = 2.50;
                        double k = 4.00;

                        if(r1.isChecked()){
                            urt = 250*pengali;
                        } if(r2.isChecked()){
                            urt = 200*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 174:
                Img.setImageResource(R.drawable.es_krim);
                rg.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.VISIBLE);
                r4.setVisibility(View.VISIBLE);

                r1.setText("1 batang");
                r2.setText("1 cup");
                r3.setText("1 cone");
                r4.setText("1 scoop");


                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 88.00;
                        double p = 0.40;
                        double l = 0.40;
                        double k = 20.60;

                        if(r1.isChecked()){
                            urt = 86*pengali;
                        }else if(r2.isChecked()){
                            urt = 100*pengali;
                        }else if(r3.isChecked()){
                            urt = 110*pengali;
                        }else if(r4.isChecked()){
                            urt = 50*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 175:
                Img.setImageResource(R.drawable.sereal_instant_energen);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sachet");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 448.00;
                        double p = 3.40;
                        double l = 12.10;
                        double k = 82.80;

                        if(r1.isChecked()){
                            urt = 30*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 176:
                Img.setImageResource(R.drawable.susu_kedelai);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 gelas");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 54.00;
                        double p = 2.30;
                        double l = 1.90;
                        double k = 6.80;

                        if(r1.isChecked()){
                            urt = 330*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });break;

            case 177:
                Img.setImageResource(R.drawable.puding);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 potong");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 88.40;
                        double p = 1.89;
                        double l = 2.38;
                        double k = 14.75;

                        if(r1.isChecked()){
                            urt = 120*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 178:
                Img.setImageResource(R.drawable.abon_sapi_ikan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 212.00;
                        double p = 18.00;
                        double l = 10.60;
                        double k = 59.30;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 179:
                Img.setImageResource(R.drawable.abon_sapi_ikan);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 435.00;
                        double p = 27.20;
                        double l = 20.20;
                       double k = 36.10;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 180:
                Img.setImageResource(R.drawable.selai);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 239.00;
                        double p = 0.50;
                        double l = 0.60;
                        double k = 64.50;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 181:
                Img.setImageResource(R.drawable.selai);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setVisibility(View.INVISIBLE);

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 590.00;
                        double p = 27.00;
                        double l = 49.00;
                        double k = 20.90;

                        if(r1.isChecked()){
                            urt = 10*pengali;
                        }

                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;


                        //BATAS JANGAN DIUTAK ATIK
                    }
                });
                break;

            case 182:
                Img.setImageResource(R.drawable.madu);
                rg.setVisibility(View.VISIBLE);

                r1.setText("1 sendok makan");
                r2.setText("1 sachet");

                hitung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double e = 294.00;
                        double p = 0.30;
                        double l = 0;
                        double k = 79.50;

                        if(r1.isChecked()){
                            urt = 12*pengali;
                        } if(r2.isChecked()){
                            urt = 20*pengali;
                        }


                        //JANGAN DIGANTI BAGIAN INI
                        double hEnergi =  (urt/100)*e;
                        double hProtein = (urt/100)*p;
                        double hLemak = (urt/100)*l;
                        double hKalori = (urt/100)*k;

                        //buat batasi digit coma
                        double hEnergiSort = Math.round(hEnergi*100)/100;
                        double hProteinSort = Math.round(hProtein*100)/100;
                        double hLemakSort = Math.round(hLemak*100)/100;
                        double hKaloriSort = Math.round(hKalori*100)/100;

//                        energi.setText("Energi           : " + hEnergiSort +" kalori");
//                        protein.setText("Protein         : "+hProteinSort +" gram");
//                        lemak.setText("Lemak             : "+hLemakSort + " gram");
//                        karbohidrat.setText("Karbohidrat : "+hKaloriSort + " gram");
                        //JANGAN DIUTAK ATIK
                    }
                });
                break;




        }

    }
}
