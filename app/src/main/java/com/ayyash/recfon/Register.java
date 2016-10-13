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

    public static final String KEY_NAMA = "nama";
    public static final String KEY_JK = "jk";
    public static final String KEY_TGL_LAHIR= "tgl_lahir";
    public static final String KEY_STATUS = "status";
    public static final String KEY_PEKERJAAN = "pekerjaan";
    public static final String KEY_HP = "telp";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASS = "password";
    public static final String KEY_MHS = "sMhs";
    public static final String KEY_DKI = "tinggalDKI";



    TextView signinhere;
    Button signup;

    EditText txt_nama, txt_tanggal, txt_email, txt_hp, txt_password;
    RadioGroup rgjk, rgstatus, rgpekerjaan, rgtinggal, rgstatus_user;
    RadioButton rbStatusUser, rbJK, rbPekerjaan, rbDKI, rbStatus;

    Typeface fonts1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        txt_nama = (EditText)findViewById(R.id.txtNama);
        txt_tanggal = (EditText)findViewById(R.id.txtTgl);
        txt_email = (EditText)findViewById(R.id.email);
        txt_hp = (EditText)findViewById(R.id.telp);
        txt_password = (EditText)findViewById(R.id.password);

        rgstatus_user = (RadioGroup)findViewById(R.id.rParticipant);
        rgjk          = (RadioGroup)findViewById(R.id.rJk);
        rgstatus      = (RadioGroup)findViewById(R.id.rStatus);
        rgpekerjaan   = (RadioGroup)findViewById(R.id.rKerja);
        rgtinggal     = (RadioGroup)findViewById(R.id.rDki);


        signinhere = (TextView)findViewById(R.id.signinhere);
        signup = (Button)findViewById(R.id.signup1);

        signinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Register.this, Login.class);
                startActivity(it);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get selected radio button from radioGroup
                int selectedSU = rgstatus_user.getCheckedRadioButtonId();
                int selectedJK = rgjk.getCheckedRadioButtonId();
                int selectedST = rgstatus.getCheckedRadioButtonId();
                int selectedPK = rgpekerjaan.getCheckedRadioButtonId();
                int selectedDKI = rgtinggal.getCheckedRadioButtonId();

                rbStatusUser    = (RadioButton) findViewById(selectedSU);
                rbStatus        = (RadioButton)findViewById(selectedST);
                rbJK            = (RadioButton)findViewById(selectedJK);
                rbPekerjaan     = (RadioButton)findViewById(selectedPK);
                rbDKI           = (RadioButton)findViewById(selectedDKI);

                Toast.makeText(Register.this,
                        "nama : "+txt_nama.getText().toString()+", tanggal :"+txt_tanggal.getText().toString()+", email :"
                                +txt_email.getText().toString()+", hp :"+txt_hp.getText().toString()+", password :"+txt_password.getText().toString()+" Status User : "
                                +rbStatusUser.getText().toString()+" Status :"+rbStatus.getText().toString()+" Jenis Kelamin :" +rbJK.getText().toString()+"Pekerjaan :"
                                +rbPekerjaan.getText().toString()+" DKI :"+rbDKI.getText().toString()+"", Toast.LENGTH_SHORT).show();

                Save();
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

    private void Save() {
        final String nama = txt_nama.getText().toString().trim();
        final String jk = rbJK.getText().toString().trim();
        final String tgl_lahir = txt_tanggal.getText().toString().trim();
        final String status = rbStatus.getText().toString().trim();
        final String pekerjaan = rbPekerjaan.getText().toString().trim();
        final String telp = txt_hp.getText().toString().trim();
        final String email = txt_email.getText().toString().trim();
        final String password = txt_password.getText().toString().trim();
        final String sMhs = rbStatusUser.getText().toString().trim();
        final String tinggalDKI = rbDKI.getText().toString().trim();


        //parsing id kelas
//            final String sIdKelas = getIdKelas(ambilIDKelas);
        //final String sIdKelas = "100000";
        //final int saveIdKelas = Integer.parseInt(sIdKelas);

        StringRequest sR = new StringRequest(Request.Method.POST, "http://103.43.45.237/recfon/api/register.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_NAMA, nama);
                params.put(KEY_JK, jk);
                params.put(KEY_TGL_LAHIR, tgl_lahir);
                params.put(KEY_STATUS, status);
                params.put(KEY_PEKERJAAN, pekerjaan);
                params.put(KEY_HP, telp);
                params.put(KEY_EMAIL, email);
                params.put(KEY_PASS, password);
                params.put(KEY_MHS, sMhs);
                params.put(KEY_DKI, tinggalDKI);
                return params;
            }

        };
//        Toast.makeText(getApplicationContext(), txt_email + " makanan = " + makanan, Toast.LENGTH_LONG).show();


////        Toast.makeText(getApplicationContext(), txt_email + " makanan = " + makanan, Toast.LENGTH_LONG).show();
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        sR.setRetryPolicy(policy);
        requestQueue.add(sR);
    }
}
