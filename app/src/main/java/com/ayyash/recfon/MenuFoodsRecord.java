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
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.R;
import com.ayyash.recfon.makanmalam.KonfirmasiMakanMalam;
import com.ayyash.recfon.makansiang.KonfirmasiMakanSiang;
import com.ayyash.recfon.selinganmalam.KonfirmasiSelinganMalam;
import com.ayyash.recfon.selinganpagi.KonfirmasiSelinganSarapan;
import com.ayyash.recfon.selingansiang.KonfirmasiSelinganSiang;

import java.util.HashMap;
import java.util.Map;

public class MenuFoodsRecord extends AppCompatActivity {

    Button sPagi,selinganPagi, makanSiang,selinganSiang,makanMalam,selinganMalam,perbandinganAsupan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu_food_record);
        sPagi = (Button)findViewById(R.id.btnsarapanpagi);
        selinganPagi= (Button)findViewById(R.id.selinganPagi);
        makanSiang = (Button)findViewById(R.id.makanSiang);
        selinganSiang =(Button)findViewById(R.id.seliSiang);
        makanMalam = (Button)findViewById(R.id.makanMalam);
        selinganMalam = (Button)findViewById(R.id.selinganMala);
        perbandinganAsupan = (Button)findViewById(R.id.btnAsupan);


        //selinganPagi.setEnabled(false);

        help();
         /* Top toolbar */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.logo_atas);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);


        //makan pagi
        sPagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuFoodsRecord.this, KonfirmasiSarapan.class);
                startActivity(i);
//                finish();
            }
        });


        selinganPagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent i = new Intent(MenuFoodsRecord.this, KonfirmasiSelinganSarapan.class);
                    startActivity(i);
//                    finish();

            }
        });

        makanSiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuFoodsRecord.this, KonfirmasiMakanSiang.class);
                startActivity(i);
//                finish();

            }
        });

        selinganSiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuFoodsRecord.this, KonfirmasiSelinganSiang.class);
                startActivity(i);
//                finish();

            }
        });
        makanMalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuFoodsRecord.this, KonfirmasiMakanMalam.class);
                startActivity(i);
//                finish();

            }
        });
        selinganMalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuFoodsRecord.this, KonfirmasiSelinganMalam.class);
                startActivity(i);
//                finish();

            }
        });

        perbandinganAsupan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuFoodsRecord.this, PerbandinganAsupan.class);
                startActivity(i);
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
        }else if(id==R.id.menuHelp){
           // Toast.makeText(MenuFoodsRecord.this,"ini help", Toast.LENGTH_LONG).show();
            help();
        }
        return super.onOptionsItemSelected(item);
    }


    private void help(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Petunjuk");
       // builder.setMessage("Anda diminta menuliskan jenis dan jumlah yang makanan dan minuman yang dikonsumsi selama 24 jam HARI INI (sejak bangun tidur hingga tidur lagi)");
       // (Html.fromHtml("Hello "+"<b>"+"World"+"</b>"));
        builder.setMessage(Html.fromHtml("Anda diminta menuliskan jenis dan jumlah "+"<b>"+ "makanan dan minuman" + "</b>"+" yang dikonsumsi"+"<b>"+" " +
                "selama 24 jam HARI INI (sejak bangun tidur hingga tidur lagi)"+ "</b>"+"<br><br><br>"+"<u>"+"Cara pengisian :"+"</u>"+"<br>"+
                "<b>"+"Pilih jenis makanan dan minuman yang dikonsumsi, kemudian isikan jumlah yang dikonsumsi sesuai ukuran wadah yang tersedia"+"</b>"+
                "<br><br><br>"+"<p style='text-align:justify'>Setelah anda mengisi ke 6 aktifitas harian dari pagi sampai dengan selingan malam , jangan lupa juga mengisi <strong style='color:red;'>PERBANDINGAN ASUPAN MAKAN</strong> pada tombol hijau yang berada  bawah.</p>"+
                ""));

        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.LEFT);
        dialog.show();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MenuFoodsRecord.this, MainMenu.class);
        startActivity(i);
        finish();
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


                        Intent intent = new Intent(MenuFoodsRecord.this, Login.class);
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
