package com.ayyash.recfon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import com.ayyash.recfon.R;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button btnLogin;
    Button login;
    TextView acc;
    Typeface fonts1;




    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        email = (EditText)findViewById(R.id.txtEmail);
        password = (EditText)findViewById(R.id.txtPassword);
        btnLogin = (Button)findViewById(R.id.signin1);

        login = (Button) findViewById(R.id.signin1);
        acc = (TextView) findViewById(R.id.create);

        fonts1 =  Typeface.createFromAsset(Login.this.getAssets(),
                "fonts/Lato-Regular.ttf");

        login.setTypeface(fonts1);

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(Login.this, "hai: "+email.getText().toString()+","+pass.getText().toString(),Toast.LENGTH_LONG).show();
                if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    // cek dari db
                    login();

                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(ConfigUmum.LOGGEDIN_SHARED_PREF, false);

        if (loggedIn) {
            Intent intent = new Intent(Login.this, MainMenu.class);
            startActivity(intent);
            finish();
        }
    }


    private void login() {
        final String nisA = email.getText().toString().trim();
        final String passwordA = password.getText().toString().trim();


        Toast.makeText(Login.this, "hai: "+nisA +" "+passwordA,Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,ConfigUmum.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase(ConfigUmum.LOGIN_SUCCESS)) {
                            SharedPreferences sharedPreferences = Login.this.getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putBoolean(ConfigUmum.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(ConfigUmum.NIS_SHARED_PREF, nisA);

                            editor.commit();

                            Intent i = new Intent(Login.this, MainMenu.class);
                            startActivity(i);
                        } else {

                            Toast.makeText(Login.this, "username/password salah /masalah koneksi ke server", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("aaa", error.toString());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put(ConfigUmum.KEY_EMAIL, nisA);
                params.put(ConfigUmum.KEY_PASSWORD, passwordA);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(Login.this, HalamanDepan.class);
        startActivity(it);
        finish();
    }
}
