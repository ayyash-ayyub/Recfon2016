package com.ayyash.recfon.aktifitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.ConfigUmum;
import com.ayyash.recfon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AktifitasSedentari extends AppCompatActivity {
    Button bas1,bas2,bas3,bas4,bas5,bas6,bas7,bas8,bas9,bas10;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktifitas_sedentari);

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        bas1 = (Button)findViewById(R.id.bas1);
        bas2 = (Button)findViewById(R.id.bas2);
        bas3 = (Button)findViewById(R.id.bas3);
        bas4 = (Button)findViewById(R.id.bas4);
        bas5 = (Button)findViewById(R.id.bas5);
        bas6 = (Button)findViewById(R.id.bas6);
        bas7 = (Button)findViewById(R.id.bas7);
        bas8 = (Button)findViewById(R.id.bas8);
        bas9 = (Button)findViewById(R.id.bas9);
        bas10 = (Button)findViewById(R.id.bas10);

        getDataNgisi();


        bas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 1);
                startActivity(i);
            }
        });
        bas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 2);
                startActivity(i);
            }
        });
        bas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 3);
                startActivity(i);
            }
        });
        bas4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 4);
                startActivity(i);
            }
        });
        bas5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 5);
                startActivity(i);
            }
        });
        bas6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 6);
                startActivity(i);
            }
        });
        bas7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 7);
                startActivity(i);
            }
        });
        bas8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 8);
                startActivity(i);
            }
        });
        bas9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 9);
                startActivity(i);
            }
        });
        bas10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FormAktifitasSedentari.class);
                i.putExtra("aktifitas", 10);
                startActivity(i);
            }
        });

    }

    private void getDataNgisi(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        //  System.out.println("url get : "+ConfigUmum.URL_LIST_MAKANAN+email);

        JsonObjectRequest req = new JsonObjectRequest(ConfigUmum.URL_LIST_SEDENTARI+email, null,
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
                                String d7b = (String) obj.get("status_activity");

                                if(d7.equals("1") && d7b.equals("1")){
                                    bas1.setEnabled(false);
                                }
                                if(d7.equals("2")&&d7b.equals("1")){
                                    bas2.setEnabled(false);
                                }
                                if(d7.equals("3")&&d7b.equals("1")){
                                    bas3.setEnabled(false);
                                }
                                if(d7.equals("4")&&d7b.equals("1")){
                                    bas4.setEnabled(false);
                                }
                                if(d7.equals("5")&&d7b.equals("1")){
                                    bas5.setEnabled(false);
                                }
                                if(d7.equals("6")&&d7b.equals("1")){
                                    bas6.setEnabled(false);
                                }
                                if(d7.equals("7")&&d7b.equals("1")){
                                    bas7.setEnabled(false);
                                }
                                if(d7.equals("8")&&d7b.equals("1")){
                                    bas8.setEnabled(false);
                                }
                                if(d7.equals("9")&&d7b.equals("1")){
                                    bas9.setEnabled(false);
                                }
                                if(d7.equals("10")&&d7b.equals("1")){
                                    bas10.setEnabled(false);
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
