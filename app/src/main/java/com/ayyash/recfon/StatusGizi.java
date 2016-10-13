package com.ayyash.recfon;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatusGizi extends AppCompatActivity {
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




    Button simpan;

    EditText txt_Berat, txt_Tinggi;
    RadioGroup rgUkurBB, rgUkurTB, rgMerokok, rgAlkohol, rgMakan, rgSarapan;
    RadioButton rbUkurBB, rbUkurTB, rbMerokok, rbAlkohol, rbMakan, rbSarapan;
    CheckBox cbTidak, cbHipertensi, cbDiabetes, cbKolesterol, cbJantung, cbGinjal, cbKanker, cbStroke,cbPunggung,cbPengapuran,cbTBC;
    TextView txt;

    Typeface fonts1;
    ArrayList<String> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_gizi);

        list = new ArrayList<String>();
        txt_Berat = (EditText)findViewById(R.id.txtBerat);
        txt_Tinggi = (EditText)findViewById(R.id.txtTinggi);

        cbTidak = (CheckBox)findViewById(R.id.cbTidak);
        cbHipertensi = (CheckBox)findViewById(R.id.cbHipertensi);
        cbDiabetes = (CheckBox)findViewById(R.id.cbDiabetes);
        cbKolesterol = (CheckBox)findViewById(R.id.cbKolesterol);
        cbJantung = (CheckBox)findViewById(R.id.cbJantung);
        cbGinjal = (CheckBox)findViewById(R.id.cbGinjal);
        cbKanker = (CheckBox)findViewById(R.id.cbKanker);
        cbStroke = (CheckBox)findViewById(R.id.cbStroke);
        cbPunggung = (CheckBox)findViewById(R.id.cbPunggung);
        cbPengapuran = (CheckBox)findViewById(R.id.cbPengapuran);
        cbTBC = (CheckBox)findViewById(R.id.cbTBC);


        rgUkurBB    = (RadioGroup)findViewById(R.id.rgUkurBB);
        rgUkurTB          = (RadioGroup)findViewById(R.id.rgUkurTB);
        rgMerokok      = (RadioGroup)findViewById(R.id.rgMerokok);
        rgAlkohol   = (RadioGroup)findViewById(R.id.rgAlkohol);
        rgMakan     = (RadioGroup)findViewById(R.id.rgMakanToday);
        rgSarapan     = (RadioGroup)findViewById(R.id.rgSarapan);
        txt = (TextView)findViewById(R.id.textView5);

        



        simpan = (Button)findViewById(R.id.btnSaveStatusGizi);


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get selected radio button from radioGroup
                int selectedBB = rgUkurBB.getCheckedRadioButtonId();
                int selectedTB = rgUkurTB.getCheckedRadioButtonId();
                int selectedRokok = rgMerokok.getCheckedRadioButtonId();
                int selectedAlkohol = rgAlkohol.getCheckedRadioButtonId();
                int selectedMakan = rgMakan.getCheckedRadioButtonId();
                int selectedSarapan = rgSarapan.getCheckedRadioButtonId();

                for (String str : list) {

                    txt.setText(txt.getText().toString() + " , " + str);

                }


//                String su = Integer.toString(selectedSU);
//                String jk = Integer.toString(selectedSU);
//                String st = Integer.toString(selectedSU);
//                String pk = Integer.toString(selectedSU);
//                String dki = Integer.toString(selectedSU);

                rbUkurBB    = (RadioButton) findViewById(selectedBB);
                rbUkurTB        = (RadioButton)findViewById(selectedTB);
                rbMerokok           = (RadioButton)findViewById(selectedRokok);
                rbAlkohol     = (RadioButton)findViewById(selectedAlkohol);
                rbMakan          = (RadioButton)findViewById(selectedMakan);
                rbSarapan          = (RadioButton)findViewById(selectedSarapan);

                if (txt_Berat.getText().toString().equals("") ||  txt_Tinggi.getText().toString().equals("")
                        || rgUkurBB.getCheckedRadioButtonId() == -1 || rgUkurTB.getCheckedRadioButtonId() == -1
                        || rgMerokok.getCheckedRadioButtonId() == -1 || rgAlkohol.getCheckedRadioButtonId() == -1
                        || rgMakan.getCheckedRadioButtonId() ==-1 || rgSarapan.getCheckedRadioButtonId() ==-1){
                    Toast.makeText(getApplicationContext(),"Mohon Lengkapi data",Toast.LENGTH_LONG).show();
                }else {

                    Toast.makeText(getApplicationContext(),
                            "Berat : " + txt_Berat.getText().toString() + ", Ukur BB :" + rbUkurBB.getText().toString()
                                    + ", Tinggi :"+ txt_Tinggi.getText().toString() + ",  :" +  rbUkurTB.getText().toString()
                                    + ", Merokok :" + rbMerokok.getText().toString() + " Alkohol : "+ rbAlkohol.getText().toString()
                                    + " Makan :" + rbMakan.getText().toString() + " Sarapan :" + rbSarapan.getText().toString()
                                    , Toast.LENGTH_SHORT).show();

//                    Save();
                }
            }


        });


        fonts1 =  Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Lato-Regular.ttf");




//        TextView t1 =(TextView)findViewById(R.id.signinhere);
//        t1.setTypeface(fonts1);
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(getApplicationContext(), HalamanDepan.class);
        startActivity(it);
        finish();
    }



//    public void onCheckboxClicked(View view) {
//
//        boolean checked = ((CheckBox) view).isChecked();
//
//        switch(view.getId()) {
//            case R.id.cbTidak:
//                list.add(cbTidak.getTag().toString());
//
//                break;
//            case R.id.cbHipertensi:
//                list.add(cbTidak.getTag().toString());
//
//                break;
//
//            case R.id.cbDiabetes:
//                list.add(cbDiabetes.getTag().toString());
//
//                break;
//            case R.id.cbKolesterol:
//                list.add(cbKolesterol.getTag().toString());
//
//                break;
//            case R.id.cbJantung:
//                list.add(cbJantung.getTag().toString());
//
//                break;
//            case R.id.cbGinjal:
//                list.add(cbGinjal.getTag().toString());
//
//                break;
//            case R.id.cbKanker:
//                list.add(cbKanker.getTag().toString());
//
//                break;
//            case R.id.cbStroke:
//                list.add(cbStroke.getTag().toString());
//
//                break;
//            case R.id.cbPunggung:
//                list.add(cbPunggung.getTag().toString());
//
//                break;
//            case R.id.cbPengapuran:
//                list.add(cbPengapuran.getTag().toString());
//
//                break;
//            case R.id.cbTBC:
//                list.add(cbTBC.getTag().toString());
//
//                break;
//
//        }
//    }

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
