package com.ayyash.recfon;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayyash.recfon.aktifitas.AktifitasSedentari;
import com.ayyash.recfon.aktifitas.MenuAktifitas;

import java.util.HashMap;
import java.util.Map;

public class PenilaianSurvei extends AppCompatActivity {
    public static final String KEY_EMAIL = "txtEmail";
    public static final String KEY_MANFAAT = "rManfaat";
    public static final String KEY_JUMLAH = "rJumlah";
    public static final String KEY_SESUAI= "rSesuai";
    public static final String KEY_SELURUH = "rSeluruh";
    public static final String KEY_ONLINE = "rOnline";
    public static final String KEY_OFFLINE = "rOffline";
    public static final String KEY_SARAN = "txtSaran";

    ProgressDialog PD;

    RadioGroup rgManfaat, rgJumlah, rgSesuai, rgSeluruh,rgOn,rgOff;
    RadioButton rbM1,rbM2,rbM3,rbJ1,rbJ2,rbJ3,rbS1,rbS2,rbS3,rbSl1,rbSl2,rbSl3,rbOn1,rbOn2,rbOff1,rbOff2;
    String txtManfaat, txtJumlah, txtSesuai, txtSeluruh, txtOn, txtOff, email;
    EditText saran;
    Button kirim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian_survei);

        rgManfaat = (RadioGroup)findViewById(R.id.rgManfaat);
        rgJumlah = (RadioGroup)findViewById(R.id.rgJumlah);
        rgSesuai = (RadioGroup)findViewById(R.id.rgSesuai);
        rgSeluruh = (RadioGroup)findViewById(R.id.rgSeluruh);
        rgOn = (RadioGroup)findViewById(R.id.rgOnline);
        rgOff = (RadioGroup)findViewById(R.id.rgOf);

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);


        rbM1 = (RadioButton)findViewById(R.id.rgM1);
        rbM2 = (RadioButton)findViewById(R.id.rgM2);
        rbM3 = (RadioButton)findViewById(R.id.rgM3);
        rbJ1 = (RadioButton)findViewById(R.id.rgJ1);
        rbJ2 = (RadioButton)findViewById(R.id.rgJ2);
        rbJ3 = (RadioButton)findViewById(R.id.rgJ3);
        rbS1 = (RadioButton)findViewById(R.id.rgS1);
        rbS2 = (RadioButton)findViewById(R.id.rgS2);
        rbS3 = (RadioButton)findViewById(R.id.rgS3);
        rbSl1 = (RadioButton)findViewById(R.id.rgSl1);
        rbSl2 = (RadioButton)findViewById(R.id.rgSl2);
        rbSl3 = (RadioButton)findViewById(R.id.rgSl3);
        rbOn1 = (RadioButton)findViewById(R.id.rgO1);
        rbOn2 = (RadioButton)findViewById(R.id.rgO2);
        rbOff1 = (RadioButton)findViewById(R.id.rgOf1);
        rbOff2 = (RadioButton)findViewById(R.id.rgOf2);

        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

        txtManfaat = "Tidak Bermanfaat";
        rgManfaat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbM1.isChecked()){
                    txtManfaat = "Tidak Bermanfaat";
                }else if (rbM2.isChecked()){
                    txtManfaat = "Cukup Bermanfaat";
                }else if (rbM3.isChecked()){
                    txtManfaat = "Sangat Bermanfaat";
                }
            }
        });

        txtJumlah = "Terlalu Banyak";
        rgJumlah.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbJ1.isChecked()){
                    txtJumlah = "Terlalu Banyak";
                }else if (rbJ2.isChecked()){
                    txtJumlah = "Cukup";
                }else if (rbJ3.isChecked()){
                    txtJumlah = "Terlalu Sedikit";
                }
            }
        });

        txtSesuai = "Tidak Sesuai";
        rgSesuai.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbS1.isChecked()){
                    txtSesuai = "Tidak Sesuai";
                }else if (rbS2.isChecked()){
                    txtSesuai = "Cukup Sesuai";
                }else if (rbS3.isChecked()){
                    txtSesuai = "Sangat Sesuai";
                }
            }
        });

        txtSeluruh = "Tidak Penting";
        rgSeluruh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbJ1.isChecked()){
                    txtSeluruh = "Tidak Penting";
                }else if (rbJ2.isChecked()){
                    txtSeluruh = "Cukup Penting";
                }else if (rbJ3.isChecked()){
                    txtSeluruh = "Sangat Penting";
                }
            }
        });

        txtOn = "Tidak Bersedia";
        rgOn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbJ1.isChecked()){
                    txtOn = "Tidak Bersedia";
                }else if (rbJ2.isChecked()){
                    txtOn = "Bersedia";
                }
            }
        });

        txtOff = "Tidak Bersedia";
        rgOff.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbJ1.isChecked()){
                    txtOff = "Tidak Bersedia";
                }else if (rbJ2.isChecked()){
                    txtOff = "Bersedia";
                }
            }
        });

        saran = (EditText)findViewById(R.id.etSaran);

        kirim = (Button)findViewById(R.id.button);
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
//                Toast.makeText(getApplicationContext(),email+", Manfaat :"+txtManfaat+", Jumlah : "+txtJumlah+", Sesuai : "+txtSesuai+", Seluruh : "+txtSeluruh+"Saran : "+ saran.getText(),Toast.LENGTH_LONG).show();
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PenilaianSurvei.this, MainMenu.class);
        startActivity(i);
        finish();
    }

    private void Save() {

//        Toast.makeText(getApplicationContext(),email+", Manfaat :"+txtManfaat+", Jumlah : "+txtJumlah+", Sesuai : "+txtSesuai+", Seluruh : "+txtSeluruh+"Saran : "+ saran.getText(),Toast.LENGTH_LONG).show();

        PD.show();

        final String txtEmail = email.trim();
        final String rManfaat = txtManfaat.trim();
        final String rSesuai = txtSesuai.trim();
        final String rSeluruh = txtSeluruh.trim();
        final String rJumlah = txtJumlah.trim();
        final String rOnline = txtOn.trim();
        final String rOffline = txtOff.trim();
        final String txtSaran = saran.getText().toString().trim();

       // Toast.makeText(getApplicationContext(),"Email : "+txtEmail+" Manfaat : "+rManfaat+" Sesuai :"+rSesuai+" Seluruh : "+ rSeluruh+", Jumlah : "+rJumlah +
             //   ", Online : "+rOnline+ ", Offline : "+rOffline+", Saran :"+txtSaran,Toast.LENGTH_LONG).show();


        StringRequest sR = new StringRequest(Request.Method.POST, ConfigUmum.URL_INSERT_PENILAIAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(getApplicationContext(), Persetujuan.class);
//                        startActivity(i);
//                        finish();
                      //  System.out.println("sql"+response);
                        PD.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        PD.dismiss();
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, txtEmail);
                params.put(KEY_MANFAAT, rManfaat);
                params.put(KEY_JUMLAH, rJumlah);
                params.put(KEY_SESUAI, rSesuai);
                params.put(KEY_SELURUH, rSeluruh);
                params.put(KEY_ONLINE, rOnline);
                params.put(KEY_OFFLINE, rOffline);
                params.put(KEY_SARAN, txtSaran);


                return params;
            }

        };
//        Toast.makeText(getApplicationContext(), txt_email + " makanan = " + makanan, Toast.LENGTH_LONG).show();
        int socketTimeout = 3000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        sR.setRetryPolicy(policy);
        requestQueue.add(sR);
    }
}
