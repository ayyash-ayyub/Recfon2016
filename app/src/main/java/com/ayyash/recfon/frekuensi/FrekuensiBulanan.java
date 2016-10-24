package com.ayyash.recfon.frekuensi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.ConfigUmum;
import com.ayyash.recfon.MainMenu;
import com.ayyash.recfon.MenuFoodsRecord;
import com.ayyash.recfon.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ayyash on 10/20/2016.
 */

public class FrekuensiBulanan extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,
            b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,
            b21,b22,b23,b24,b25,b26,b27,b28,b29,b30,
            b31,b32,b33,b34,b35,b36,b37,b38,b39,b40,
            b41,b42,b43,b44,b45,b46,b47,b48,b49,b50,
            b51,b52,b53,b54,b55,b56,b57,b58,b59,b60;
    String email;

    String data7, data7a;



    //    String makanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frekuensi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        b1 = (Button)findViewById(R.id.bb1);
        b2 = (Button)findViewById(R.id.bb2);
        b3 = (Button)findViewById(R.id.bb3);
        b4 = (Button)findViewById(R.id.bb4);
        b5 = (Button)findViewById(R.id.bb5);
        b6 = (Button)findViewById(R.id.bb6);
        b7 = (Button)findViewById(R.id.bb7);
        b8 = (Button)findViewById(R.id.bb8);
        b9 = (Button)findViewById(R.id.bb9);
        b10 = (Button)findViewById(R.id.bb10);
        b11 = (Button)findViewById(R.id.bb11);
        b12 = (Button)findViewById(R.id.bb12);
        b13 = (Button)findViewById(R.id.bb13);
        b14 = (Button)findViewById(R.id.bb14);
        b15 = (Button)findViewById(R.id.bb15);
        b16 = (Button)findViewById(R.id.bb16);
        b17 = (Button)findViewById(R.id.bb17);
        b18 = (Button)findViewById(R.id.bb18);
        b19 = (Button)findViewById(R.id.bb19);
        b20 = (Button)findViewById(R.id.bb20);
        b21 = (Button)findViewById(R.id.bb21);
        b22 = (Button)findViewById(R.id.bb22);
        b23 = (Button)findViewById(R.id.bb23);
        b24 = (Button)findViewById(R.id.bb24);
        b25 = (Button)findViewById(R.id.bb25);
        b26 = (Button)findViewById(R.id.bb26);
        b27 = (Button)findViewById(R.id.bb27);
        b28 = (Button)findViewById(R.id.bb28);
        b29 = (Button)findViewById(R.id.bb29);
        b30 = (Button)findViewById(R.id.bb30);
        b31 = (Button)findViewById(R.id.bb31);
        b32 = (Button)findViewById(R.id.bb32);
        b33 = (Button)findViewById(R.id.bb33);
        b34 = (Button)findViewById(R.id.bb34);
        b35 = (Button)findViewById(R.id.bb35);
        b36 = (Button)findViewById(R.id.bb36);
        b37 = (Button)findViewById(R.id.bb37);
        b38 = (Button)findViewById(R.id.bb38);
        b39 = (Button)findViewById(R.id.bb39);
        b40 = (Button)findViewById(R.id.bb40);
        b41 = (Button)findViewById(R.id.bb41);
        b42 = (Button)findViewById(R.id.bb42);
        b43 = (Button)findViewById(R.id.bb43);
        b44 = (Button)findViewById(R.id.bb44);
        b45 = (Button)findViewById(R.id.bb45);
        b46 = (Button)findViewById(R.id.bb46);
        b47 = (Button)findViewById(R.id.bb47);
        b48 = (Button)findViewById(R.id.bb48);
        b49 = (Button)findViewById(R.id.bb49);
        b50 = (Button)findViewById(R.id.bb50);
        b51 = (Button)findViewById(R.id.bb51);
        b52 = (Button)findViewById(R.id.bb52);
        b53 = (Button)findViewById(R.id.bb53);
        b54 = (Button)findViewById(R.id.bb54);
        b55 = (Button)findViewById(R.id.bb55);
        b56 = (Button)findViewById(R.id.bb56);
        b57 = (Button)findViewById(R.id.bb57);
        b58 = (Button)findViewById(R.id.bb58);
        b59 = (Button)findViewById(R.id.bb59);
        b60 = (Button)findViewById(R.id.bb60);

        cekJalan();
        getDataNgisi();
    }



    public void cekJalan(){

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 1);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 2);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 3);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 4);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 5);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 6);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 7);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 8);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 9);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 10);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 11);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 12);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 13);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 14);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 15);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 16);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 17);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 18);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 19);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 20);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 21);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 22);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 23);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 24);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 25);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 26);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 27);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 28);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 29);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 30);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 31);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 32);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 33);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 34);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 35);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 36);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        b37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 37);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 38);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 39);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 40);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 41);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 42);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 43);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 44);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 45);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 46);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 47);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 48);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 49);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 50);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 51);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 52);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 53);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 54);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 55);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 56);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 57);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b58.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 58);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 59);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        b60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", 60);
                startActivity(i);
//                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(FrekuensiBulanan.this, MainMenu.class);
        startActivity(i);
        finish();
    }

    private void getDataNgisi(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        //  System.out.println("url get : "+ConfigUmum.URL_LIST_MAKANAN+email);

        JsonObjectRequest req = new JsonObjectRequest(ConfigUmum.URL_LIST_MAKANAN+email, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("ar"+response.toString());
                        JSONArray ayyash = null;
                        try {
                            ayyash = response.getJSONArray("result");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        for (int i = 0; i < ayyash.length(); i++) {
                            try {
                                JSONObject obj = ayyash.getJSONObject(i);

                                //tujuh
                                String d7 = (String) obj.get("id");
                                String d7b = (String) obj.get("status_makanan");
//
//                                data7 = "";
//                                data7a = "";
//
//                                data7 +=d7;
//                                data7a +=d7b;
//
//
//                                Toast.makeText(getApplicationContext(),d7+" dan "+d7b,Toast.LENGTH_LONG).show();
                                if(d7.equals("1") && d7b.equals("1")){
                                    b3.setEnabled(false);
                                }else if(d7.equals("1") && d7b.equals("0")) {
                                    b3.setEnabled(true);
                                }

                                if(d7.equals("2")&&d7b.equals("1")){
                                    b2.setEnabled(false);
                                }
                                if(d7.equals("3")&&d7b.equals("1")){
                                    b3.setEnabled(false);
                                }
                                if(d7.equals("4")&&d7b.equals("1")){
                                    b4.setEnabled(false);
                                }
                                if(d7.equals("5")&&d7b.equals("1")){
                                    b5.setEnabled(false);
                                }
                                if(d7.equals("6")&&d7b.equals("1")){
                                    b6.setEnabled(false);
                                }
                                if(d7.equals("7")&&d7b.equals("1")){
                                    b7.setEnabled(false);
                                }
                                if(d7.equals("8")&&d7b.equals("1")){
                                    b8.setEnabled(false);
                                }
                                if(d7.equals("9")&&d7b.equals("1")){
                                    b9.setEnabled(false);
                                }
                                if(d7.equals("10")&&d7b.equals("1")){
                                    b10.setEnabled(false);
                                }
                                if(d7.equals("11")&&d7b.equals("1")){
                                    b11.setEnabled(false);
                                }
                                if(d7.equals("12")&&d7b.equals("1")){
                                    b12.setEnabled(false);
                                }
                                if(d7.equals("13")&&d7b.equals("1")){
                                    b13.setEnabled(false);
                                }
                                if(d7.equals("14")&&d7b.equals("1")){
                                    b14.setEnabled(false);
                                }
                                if(d7.equals("15")&&d7b.equals("1")){
                                    b15.setEnabled(false);
                                }
                                if(d7.equals("16")&&d7b.equals("1")){
                                    b16.setEnabled(false);
                                }
                                if(d7.equals("17")&&d7b.equals("1")){
                                    b17.setEnabled(false);
                                }
                                if(d7.equals("18")&&d7b.equals("1")){
                                    b18.setEnabled(false);
                                }
                                if(d7.equals("19")&&d7b.equals("1")){
                                    b19.setEnabled(false);
                                }
                                if(d7.equals("20")&&d7b.equals("1")){
                                    b20.setEnabled(false);
                                }
                                if(d7.equals("21")&&d7b.equals("1")){
                                    b21.setEnabled(false);
                                }
                                if(d7.equals("22")&&d7b.equals("1")){
                                    b22.setEnabled(false);
                                }
                                if(d7.equals("23")&&d7b.equals("1")){
                                    b23.setEnabled(false);
                                }
                                if(d7.equals("24")&&d7b.equals("1")){
                                    b24.setEnabled(false);
                                }
                                if(d7.equals("25")&&d7b.equals("1")){
                                    b25.setEnabled(false);
                                }
                                if(d7.equals("26")&&d7b.equals("1")){
                                    b26.setEnabled(false);
                                }
                                if(d7.equals("27")&&d7b.equals("1")){
                                    b27.setEnabled(false);
                                }
                                if(d7.equals("28")&&d7b.equals("1")){
                                    b28.setEnabled(false);
                                }
                                if(d7.equals("29")&&d7b.equals("1")){
                                    b29.setEnabled(false);
                                }
                                if(d7.equals("30")&&d7b.equals("1")){
                                    b30.setEnabled(false);
                                }
                                if(d7.equals("31")&&d7b.equals("1")){
                                    b31.setEnabled(false);
                                }
                                if(d7.equals("32")&&d7b.equals("1")){
                                    b32.setEnabled(false);
                                }
                                if(d7.equals("33")&&d7b.equals("1")){
                                    b33.setEnabled(false);
                                }
                                if(d7.equals("34")&&d7b.equals("1")){
                                    b34.setEnabled(false);
                                }
                                if(d7.equals("35")&&d7b.equals("1")){
                                    b35.setEnabled(false);
                                }
                                if(d7.equals("36")&&d7b.equals("1")){
                                    b36.setEnabled(false);
                                }
                                if(d7.equals("37")&&d7b.equals("1")){
                                    b37.setEnabled(false);
                                }
                                if(d7.equals("38")&&d7b.equals("1")){
                                    b38.setEnabled(false);
                                }
                                if(d7.equals("39")&&d7b.equals("1")){
                                    b39.setEnabled(false);
                                }
                                if(d7.equals("40")&&d7b.equals("1")){
                                    b40.setEnabled(false);
                                }
                                if(d7.equals("41")&&d7b.equals("1")){
                                    b41.setEnabled(false);
                                }
                                if(d7.equals("42")&&d7b.equals("1")){
                                    b42.setEnabled(false);
                                }
                                if(d7.equals("43")&&d7b.equals("1")){
                                    b43.setEnabled(false);
                                }
                                if(d7.equals("44")&&d7b.equals("1")){
                                    b44.setEnabled(false);
                                }
                                if(d7.equals("45")&&d7b.equals("1")){
                                    b45.setEnabled(false);
                                }
                                if(d7.equals("46")&&d7b.equals("1")){
                                    b46.setEnabled(false);
                                }
                                if(d7.equals("47")&&d7b.equals("1")){
                                    b47.setEnabled(false);
                                }
                                if(d7.equals("48")&&d7b.equals("1")){
                                    b48.setEnabled(false);
                                }
                                if(d7.equals("49")&&d7b.equals("1")){
                                    b49.setEnabled(false);
                                }
                                if(d7.equals("50")&&d7b.equals("1")){
                                    b50.setEnabled(false);
                                }
                                if(d7.equals("51")&&d7b.equals("1")){
                                    b51.setEnabled(false);
                                }
                                if(d7.equals("52")&&d7b.equals("1")){
                                    b52.setEnabled(false);
                                }
                                if(d7.equals("53")&&d7b.equals("1")){
                                    b53.setEnabled(false);
                                }
                                if(d7.equals("54")&&d7b.equals("1")){
                                    b54.setEnabled(false);
                                }
                                if(d7.equals("55")&&d7b.equals("1")){
                                    b55.setEnabled(false);
                                }
                                if(d7.equals("56")&&d7b.equals("1")){
                                    b56.setEnabled(false);
                                }
                                if(d7.equals("57")&&d7b.equals("1")){
                                    b57.setEnabled(false);
                                }
                                if(d7.equals("58")&&d7b.equals("1")){
                                    b58.setEnabled(false);
                                }
                                if(d7.equals("59")&&d7b.equals("1")){
                                    b59.setEnabled(false);
                                }
                                if(d7.equals("60")&&d7b.equals("1")){
                                    b60.setEnabled(false);
                                }



                                //batas tujuh


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        queue.add(req);
    }


}
