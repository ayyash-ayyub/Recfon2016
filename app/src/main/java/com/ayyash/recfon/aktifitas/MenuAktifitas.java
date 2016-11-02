package com.ayyash.recfon.aktifitas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    }
}
