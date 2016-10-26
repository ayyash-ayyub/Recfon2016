package com.ayyash.recfon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Ayyash on 26/10/2016.
 */
public class SimpleBootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Set the alarm here.
            // new code
            SharedPreferences sharedPreferences = context.getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            if (sharedPreferences.getBoolean("alarm_aktif", false)){
                long start_time = sharedPreferences.getLong("start_time", 0);
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(start_time);

                Intent newIntent = new Intent(context, NotifikasiListener.class);
                PendingIntent pIntent = PendingIntent.getBroadcast(context, 10408, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 2, pIntent);
            }

        }
    }
}
