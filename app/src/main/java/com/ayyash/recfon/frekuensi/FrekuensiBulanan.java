package com.ayyash.recfon.frekuensi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.ConfigUmum;
import com.ayyash.recfon.MainMenu;
import com.ayyash.recfon.MenuFoodsRecord;
import com.ayyash.recfon.R;

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
        b51,b52,b53,b54,b55,b56,b57,b58,b59,b60,
        b61,b62,b63,b64,b65,b66,b67,b68,b69,b70,b71,b72,b73,b74;
    String email;


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
        b61 = (Button)findViewById(R.id.bb61);
        b62 = (Button)findViewById(R.id.bb62);
        b63 = (Button)findViewById(R.id.bb63);
        b64 = (Button)findViewById(R.id.bb64);
        b65 = (Button)findViewById(R.id.bb65);
        b66 = (Button)findViewById(R.id.bb66);
        b67 = (Button)findViewById(R.id.bb67);
        b68 = (Button)findViewById(R.id.bb68);
        b69 = (Button)findViewById(R.id.bb69);
        b70 = (Button)findViewById(R.id.bb70);
        b71 = (Button)findViewById(R.id.bb71);
        b72 = (Button)findViewById(R.id.bb72);
        b73 = (Button)findViewById(R.id.bb73);
        b74 = (Button)findViewById(R.id.bb74);

        cekJalan();
        getBMI();
    }



    public void cekJalan(){

        b73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),""+ b73.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormFrekuensiMakan.class);
                i.putExtra("makanan", "Mie Goreng");
                startActivity(i);
                Toast.makeText(getApplicationContext(),""+ b7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        b74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),""+ b74.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(FrekuensiBulanan.this, MenuFoodsRecord.class);
        startActivity(i);
        finish();
    }

    private void getBMI(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        final JsonArrayRequest  request = new JsonArrayRequest( ConfigUmum.URL_LIST_MAKANAN+email,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //  Log.d("sabtu", response.toString());

                System.out.println("response"+response);


                //   Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Masalah pada koneksi, atau data makan kurang lengkap", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        queue.add(request);
    }


}
