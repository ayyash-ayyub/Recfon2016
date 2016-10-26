package com.ayyash.recfon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ayyash.recfon.R;

import java.util.Calendar;

public class HalamanDepan extends AppCompatActivity {
    Button signin;
    Button signup;
    private boolean loggedIn = false;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_depan);
        context = this;

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


        //SharedPreferences sharedPref = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        long startTime = sharedPreferences.getLong("alarm_start_time", 0L);

        Calendar now = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startTime);
        long difference = now.getTimeInMillis() - cal.getTimeInMillis();

        // 1000 * 60 * 60 * 24 * 2 * 3
        if(difference >= 1000 * 60 * 60 * 24 * 2 * 3){
            Intent intent = new Intent(context, NotifikasiListener.class);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, 10408, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            if(alarmManager != null){
                alarmManager.cancel(pIntent);
            }

            // disable murupkan
            ComponentName receiver = new ComponentName(context, SimpleBootReceiver.class);
            PackageManager pm = context.getPackageManager();

            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        loggedIn = sharedPreferences.getBoolean(ConfigUmum.LOGGEDIN_SHARED_PREF, false);
        if (loggedIn) {
            Intent intent = new Intent(HalamanDepan.this, MainMenu.class);
            startActivity(intent);
            finish();
        }
    }
}