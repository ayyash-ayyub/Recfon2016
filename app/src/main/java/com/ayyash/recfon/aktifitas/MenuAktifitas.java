package com.ayyash.recfon.aktifitas;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ayyash.recfon.MainMenu;
import com.ayyash.recfon.R;

public class MenuAktifitas extends AppCompatActivity {
    Button btnFisik, btnSedentari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aktivitas);

        btnFisik = (Button)findViewById(R.id.btnAFisik);
        btnFisik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AktifitasFisik.class);
                startActivity(i);
                finish();
            }
        });

        btnSedentari = (Button)findViewById(R.id.btnASedentari);
        btnSedentari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AktifitasSedentari.class);
                startActivity(i);
                finish();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        toolbar.setNavigationIcon(R.drawable.logo_atas);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MenuAktifitas.this, MainMenu.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuHelp) {
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
        builder.setMessage(Html.fromHtml("Mohon untuk mengisi  "+
                "semua pertanyaan dihalaman ini dengan sebenar-benarnya dengan cara ;."+ "<br><br><br>"  +
                "<b>" + "1.Menuliskan berat & tinggi badan pada form yang telah disediakan." + "</b><br>" +
                "<b>" + "2.Menjawab dengan opsi yang sesuai pada masing-masing pertanyaan." + "</b><br>" +
                "<b>" + "3.Khusus riwayat penyakit, anda dapat memilih lebih dari satu dengan pilihan yang sesuai." + "</b><br>" +
                "<br><br><br>" + "Pertanyaan di halaman ini cukup diisi sekali saja. jika ada perubahan data, maka akan otomatis mengganti/mengupdate data yang lama" +
                ""));

        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.LEFT);
        dialog.show();

    }
}
