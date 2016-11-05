package com.ayyash.recfon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ayyash.recfon.aktifitas.AktifitasFisik;
import com.ayyash.recfon.aktifitas.MenuAktifitas;
import com.ayyash.recfon.frekuensi.FrekuensiBulanan;
import com.ayyash.recfon.makanmalam.KonfirmasiMakanMalam;
import com.ayyash.recfon.makansiang.KonfirmasiMakanSiang;
import com.ayyash.recfon.profile.ProfileUser;
import com.ayyash.recfon.selinganmalam.KonfirmasiSelinganMalam;
import com.ayyash.recfon.selinganpagi.KonfirmasiSelinganSarapan;
import com.ayyash.recfon.selingansiang.KonfirmasiSelinganSiang;

public class MainMenu extends AppCompatActivity {
    Button statusGizi, makanHarian, aktifitasFisik, frekuensiMakan, profile, penilaian, logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu2);
        statusGizi = (Button) findViewById(R.id.btnsarapanpagi);
        makanHarian = (Button) findViewById(R.id.selinganPagi);
        aktifitasFisik = (Button) findViewById(R.id.makanSiang);
        frekuensiMakan = (Button) findViewById(R.id.seliSiang);
        profile = (Button) findViewById(R.id.makanMalam);
        penilaian = (Button) findViewById(R.id.selinganMala);
        logOut = (Button) findViewById(R.id.btnAsupan);
         /* Top toolbar */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.logo_atas);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);


        //makan pagi
        //makan pagi
        statusGizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StatusGizi.class);
                startActivity(i);
                finish();
            }
        });


        makanHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainMenu.this, MenuFoodsRecord.class);
                startActivity(i);
                finish();

            }
        });

        aktifitasFisik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, MenuAktifitas.class);
                startActivity(i);
                finish();

            }
        });

        frekuensiMakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, FrekuensiBulanan.class);
                startActivity(i);
//                finish();

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, ProfileUser.class);
                startActivity(i);
                finish();

            }
        });
        penilaian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, PenilaianSurvei.class);
                startActivity(i);
                finish();

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("ayyash", MODE_WORLD_READABLE);
                SharedPreferences.Editor edd = sp.edit();

                edd.clear();
                edd.commit();
                logout();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {

            logout();
        } else if (id == R.id.menuHelp) {
            // Toast.makeText(MainMenu.this,"ini help", Toast.LENGTH_LONG).show();
            help();
        }
        return super.onOptionsItemSelected(item);
    }


    private void help() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Petunjuk");
        // builder.setMessage("Anda diminta menuliskan jenis dan jumlah yang makanan dan minuman yang dikonsumsi selama 24 jam HARI INI (sejak bangun tidur hingga tidur lagi)");
        // (Html.fromHtml("Hello "+"<b>"+"World"+"</b>"));
        builder.setMessage(Html.fromHtml("Anda diminta menjawab pertanyaan disetiap tombol menu "+
                "yang ada dihalaman ini mulai : Status Gizi, Catatan makan harian, Aktifitas Fisik dan frekuensi makan lainnya."+ "<br><br><br>"  +
                "<b>" + "Setelah mengisi ke empat menu diatas (3 kali untuk catatan makan harian), anda akan dapat melihat laporan kalori anda pada menu <strong style='color:red;'>Profil kalori anda</strong> ." + "</b>" +
                "<br><br><br>" + "Diakhir survey, mohon diisi menu penilaian suvey sebagai masukan dan umpan balik bagi kami" +
                ""));

        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.LEFT);
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(MainMenu.this);

        // Setting Dialog Title
        alertDialog.setTitle("Konfirmasi");
        // Setting Dialog Message
        alertDialog.setMessage("Apakah Anda yakin sudah memasukan semua menu sarapan Anda?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.i);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // Write your code here to invoke YES event

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




}
