package com.ayyash.recfon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class PenilaianSurvei extends AppCompatActivity {
    public static final String KEY_Email = "txtEmail";
    public static final String KEY_MANFAAT = "rManfaat";
    public static final String KEY_JUMLAH = "rJumlah";
    public static final String KEY_SESUAI= "rSesuai";
    public static final String KEY_SELURUH = "rSeluruh";
    public static final String KEY_ONLINE = "rOnline";
    public static final String KEY_OFFLINE = "rOffline";
    public static final String KEY_SARAN = "txtSaran";

    RadioGroup rgManfaat, rgJumlah, rgSesuai, rgSeluruh,rgOn,rgOf;
    EditText saran;
    Button kirim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian_survei);


    }
}
