package com.ayyash.recfon;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    public static final String KEY_EMAIL = "email";
    public static final String KEY_BB = "berat_badan";
    public static final String KEY_UKUBB = "ukur_bb";
    public static final String KEY_TB = "berat_badan";
    public static final String KEY_UKURTB = "ukur_bb";
    public static final String KEY_ROKOK = "rokok";
    public static final String KEY_ALKOHOL = "alkohol";
    public static final String KEY_PENYAKIT = "penyakit";
    public static final String KEY_MAKAN = "makan";
    public static final String KEY_SARAPAN = "sarapan";


    TextView signinhere;
    Button save;

    EditText txt_Berat, txt_tinggi;
    RadioGroup rgUkurBB, rgUkurTB, rgMerokok, rgAlkohol, rgMakan, rgSarapan;
    RadioButton rbUkurBB, rbUkurTB, rbMerokok, rbAlkohol, rbMakan, rbSarapan;

    Typeface fonts1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        txt_Berat = (EditText)findViewById(R.id.txtBerat);
        txt_tinggi = (EditText)findViewById(R.id.txtTinggi);

        rgUkurBB = (RadioGroup)findViewById(R.id.rgUkurBB);
        rgUkurTB          = (RadioGroup)findViewById(R.id.rgUkurTB);
        rgMerokok      = (RadioGroup)findViewById(R.id.rgMerokok);
        rgAlkohol   = (RadioGroup)findViewById(R.id.rgAlkohol);
        rgMakan     = (RadioGroup)findViewById(R.id.rgMakanToday);
        rgSarapan     = (RadioGroup)findViewById(R.id.rgSarapan);


        signinhere = (TextView)findViewById(R.id.signinhere);
        save = (Button)findViewById(R.id.btnSaveStatusGizi);

        signinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Register.this, Login.class);
                startActivity(it);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get selected radio button from radioGroup
                int selectedBB = rgUkurBB.getCheckedRadioButtonId();
                int selectedTB = rgUkurTB.getCheckedRadioButtonId();
                int selectedRokok = rgMerokok.getCheckedRadioButtonId();
                int selectedAlkohol = rgAlkohol.getCheckedRadioButtonId();
                int selectedMakan = rgMakan.getCheckedRadioButtonId();
                int selectedSarapan = rgMakan.getCheckedRadioButtonId();


//                String UkurBB = Integer.toString(selectedBB);
//                String UkurTB = Integer.toString(selectedTB);
//                String st = Integer.toString(selectedRokok);
//                String pk = Integer.toString(selectedAlkohol);
//                String dki = Integer.toString(selectedMakan);
//                String dki = Integer.toString(selectedSarapan);

                rbUkurBB    = (RadioButton)findViewById(selectedBB);
                rbUkurTB        = (RadioButton)findViewById(selectedTB);
                rbMerokok            = (RadioButton)findViewById(selectedRokok);
                rbAlkohol     = (RadioButton)findViewById(selectedAlkohol);
                rbMakan           = (RadioButton)findViewById(selectedMakan);
                rbSarapan           = (RadioButton)findViewById(selectedSarapan);

                if (txt_Berat.getText().toString().equals("") || txt_tinggi.getText().toString().equals("") || rgUkurBB.getCheckedRadioButtonId() == -1 || rgUkurTB.getCheckedRadioButtonId() == -1
                        || rgMerokok.getCheckedRadioButtonId() == -1 || rgAlkohol.getCheckedRadioButtonId() == -1 || rgMakan.getCheckedRadioButtonId() ==-1 || rgSarapan.getCheckedRadioButtonId() ==-1 ){
                    Toast.makeText(getApplicationContext(),"Mohon Lengkapi data",Toast.LENGTH_LONG).show();
                }else {

                    Toast.makeText(Register.this,
                            "Berat Badan : " + txt_Berat.getText().toString() + ", Ukur BB :" + rbUkurBB.getText().toString() + ", tinggi :"
                                    + txt_tinggi.getText().toString() + ", UKUR TB :" + rbUkurTB.getText().toString() + " Merokok : "
                                    + rbMerokok.getText().toString() + " Alkohol :" + rbAlkohol.getText().toString() + " Makan :" + rbMakan.getText().toString() + "Sarapan :"
                                    + rbSarapan.getText().toString() , Toast.LENGTH_SHORT).show();

//                    Save();
                }
            }


        });


        fonts1 =  Typeface.createFromAsset(Register.this.getAssets(),
                "fonts/Lato-Regular.ttf");




        TextView t1 =(TextView)findViewById(R.id.signinhere);
        t1.setTypeface(fonts1);
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(Register.this, HalamanDepan.class);
        startActivity(it);
        finish();
    }

//    private void Save() {
//        final String nama = txt_nama.getText().toString().trim();
//        final String jk = rbJK.getText().toString().trim();
//        final String tgl_lahir = txt_tanggal.getText().toString().trim();
//        final String status = rbStatus.getText().toString().trim();
//        final String pekerjaan = rbPekerjaan.getText().toString().trim();
//        final String telp = txt_hp.getText().toString().trim();
//        final String email = txt_email.getText().toString().trim();
//        final String password = txt_password.getText().toString().trim();
//        final String sMhs = rbStatusUser.getText().toString().trim();
//        final String tinggalDKI = rbDKI.getText().toString().trim();
//
//
//        //parsing id kelas
////            final String sIdKelas = getIdKelas(ambilIDKelas);
//        //final String sIdKelas = "100000";
//        //final int saveIdKelas = Integer.parseInt(sIdKelas);
//
//        StringRequest sR = new StringRequest(Request.Method.POST, "http://103.43.45.237/recfon/api/register.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(getApplicationContext(), Login.class);
//                        startActivity(i);
//                        finish();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put(KEY_NAMA, nama);
//                params.put(KEY_JK, jk);
//                params.put(KEY_TGL_LAHIR, tgl_lahir);
//                params.put(KEY_STATUS, status);
//                params.put(KEY_PEKERJAAN, pekerjaan);
//                params.put(KEY_HP, telp);
//                params.put(KEY_EMAIL, email);
//                params.put(KEY_PASS, password);
//                params.put(KEY_MHS, sMhs);
//                params.put(KEY_DKI, tinggalDKI);
//                return params;
//            }
//
//        };
////        Toast.makeText(getApplicationContext(), txt_email + " makanan = " + makanan, Toast.LENGTH_LONG).show();
//        int socketTimeout = 30000;//30 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        sR.setRetryPolicy(policy);
//        requestQueue.add(sR);
//    }
}
