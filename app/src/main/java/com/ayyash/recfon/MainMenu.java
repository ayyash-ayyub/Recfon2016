package com.ayyash.recfon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.ayyash.recfon.aktifitas.AktifitasFisik;

public class MainMenu extends AppCompatActivity {
    private int mutedColor;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Button aktifkan;

    boolean ayyash=false;
    SharedPreferences spp;
    SharedPreferences.Editor edd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SharedPreferences sp = getSharedPreferences("ayyash", MODE_WORLD_READABLE);
        final Boolean ambil =  sp.getBoolean("ayyash", false);


   //  Toast.makeText(MainMenu.this, "apa: "+ambil.toString(), Toast.LENGTH_SHORT).show();


        String htmlText = " %s ";
        String myData = "<h4 style='text-align:justify'><strong>SURVEY ONLINE STUDI AWAL TENTANG TRANSISI " +
                "RESIKO OBESITAS PADA MAHASISWA DAN ALUMNI UNIVERSITAS INDONESIA.</strong></h4>" +

                "<p style='text-align:justify'>Survey ini diselenggarakan oleh SEAMEO RECFON (Pusat Studi Regional Penelitian Gizi dan Kesehatan Masyarakat) yang memiliki tanggung jawab untuk terus meningkatkan status gizi masyarakat di Asia Tenggara, termasuk Indonesia.</p>" +
                "" +
                "" +
                "<p style='text-align:justify'>Anda diminta untuk menjawab setiap pertanyaan terkait <strong>data diri, status gizi, kebiasaan makan, dan aktifitas fisik.</strong> Waktu yang dibutuhkan untuk pengisian <strong>berkisar 15-20 menit.</strong> Setiap pertanyaan dilengkapi dengan instruksi untuk mempermudah Anda dalam menjawab. Tidak ada jawaban salah maupun benar, maka dimohon untuk menjawab sesuai dengan keadaan anda. Kuesioner yang sudah dilengkapi akan terekam secara otomatis</p>" +
                "" +
                "" +
                "<p style='text-align:justify'>Menu untuk mengisi data survey, data kecukupan asupan makan, dan kualitas aktivitas fisik akan muncul setelah Anda menekan tombol <strong style='color:red;'>SAYA SETUJU MENGIKUTI SURVEY</strong>.  Seluruh hasil jawaban Anda <strong>bersifat rahasia</strong> dan hanya akan diketahui oleh Anda pribadi serta tim survey.</p>" +
                "" +
                "<p style='text-align:justify'>Info lebih jelas terkait survey ini dapat ditujukan pada:</p>" +
                "<p style='text-align:center'>Ir. Helda Khusun, PhD./ Dr. dr. Aria Kekalih, MTI/ Dr. Luh Ade Ari Wiradnyani, MSc.\n" +
                "SEAMEO-Regional Center for Food and Nutrition/RECFON\n" +
                "Pusat Kajian Makanan dan Gizi Regional Universitas Indonesia\n" +
                "Jl. Salemba Raya no 6, Jakarta Pusat 10430\n" +
                "Telp 021-31930205, 3913932\n</p>";


        WebView webView = (WebView) findViewById(R.id.tampilanWeb);
        webView.loadData(String.format(htmlText, myData), "text/html", "utf-8");

        /* NavigationView */
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        aktifkan = (Button) findViewById(R.id.buttonAktif);
        if (ambil) {
            aktifkan.setVisibility(View.INVISIBLE);
        }




        aktifkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //locked=true;
               // isPhysicalTheftEnabled

                ayyash=true;
                spp = getSharedPreferences("ayyash", MODE_WORLD_READABLE);
                 edd= spp.edit();
                edd.putBoolean("ayyash", ayyash);
                edd.commit();

               // Toast.makeText(MainMenu.this, "apa: "+ambil.toString(), Toast.LENGTH_SHORT).show();

                aktifkan.setVisibility(View.GONE);
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        // On click of menu icon on toolbar

        toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ambil) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    Toast.makeText(getApplicationContext(), "Anda harus menyetujui terlebih dahulu dengan cara klik tombol, SAYA SETUJU MENGIKUTI SURVEY, untuk dapat mengikuti survey", Toast.LENGTH_LONG).show();
                }

            }
        });

        // On click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // Set item in checked state
                menuItem.setChecked(true);

                //TODO: handle navigation
                int id = menuItem.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.kebiasaan) {
                    //   Toast.makeText(getApplicationContext(),"Kebiasaan coy",Toast.LENGTH_LONG).show();
                    // finish();
                }else if (id == R.id.aktifitasFisik){
                    Intent i = new Intent(MainMenu.this, AktifitasFisik.class);
                    startActivity(i);
                    finish();
                }else if (id == R.id.foodRecord) {
                    Intent i = new Intent(MainMenu.this, MenuFoodsRecord.class);
                    startActivity(i);
                    finish();
                    // Toast.makeText(getApplicationContext(),"Kebiasaan Food Record",Toast.LENGTH_LONG).show();
                }else if (id == R.id.StatusGizi){
                    Intent i = new Intent(MainMenu.this, StatusGizi.class);
                    startActivity(i);
                    finish();
                }else if (id == R.id.keluarCuy) {
                    SharedPreferences sp = getSharedPreferences("ayyash", MODE_WORLD_READABLE);
                    SharedPreferences.Editor edd = sp.edit();

                    edd.clear();
                    edd.commit();
                    logout();

                }


                //Closing drawer on item click
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }


    private void logout() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Anda akan logout dari aplikasi?");
        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {


                        SharedPreferences preferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();


                        editor.putBoolean(ConfigUmum.LOGGEDIN_SHARED_PREF, false);


                        editor.putString(ConfigUmum.NIS_SHARED_PREF, "");

                        editor.commit();
                        //clear sp IP


                        Intent intent = new Intent(MainMenu.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("Batal",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
