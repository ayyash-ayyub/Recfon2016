package com.ayyash.recfon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ayyash.recfon.R;

public class HalamanDepan extends AppCompatActivity {
    Button signin;
    Button signup;
    private boolean loggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_depan);


        signin = (Button)findViewById(R.id.signin);
        signup = (Button)findViewById(R.id.Signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(HalamanDepan.this, Register.class);
                startActivity(it);
                finish();

            }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(HalamanDepan.this,Login.class);
                startActivity(it);
                finish();


            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(ConfigUmum.LOGGEDIN_SHARED_PREF, false);

        if (loggedIn) {
            Intent intent = new Intent(HalamanDepan.this, MainMenu.class);
            startActivity(intent);
            finish();
        }
    }
}