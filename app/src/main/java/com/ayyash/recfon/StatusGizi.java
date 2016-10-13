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

import java.util.ArrayList;

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
    ArrayList list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_gizi);


        txt_Berat = (EditText)findViewById(R.id.txtBerat);
        txt_Tinggi = (EditText)findViewById(R.id.txtTinggi);

        list = new ArrayList<>();

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



        cbTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(StatusGizi.this, "Test Uyeeeah", Toast.LENGTH_LONG).show();
                if(cbTidak.isChecked()==true){
                    cbHipertensi.setEnabled(false);
                    cbDiabetes.setEnabled(false);
                    cbKolesterol.setEnabled(false);
                    cbJantung.setEnabled(false);
                    cbGinjal.setEnabled(false);
                    cbKanker.setEnabled(false);
                    cbStroke.setEnabled(false);
                    cbPunggung.setEnabled(false);
                    cbPunggung.setEnabled(false);
                    cbPengapuran.setEnabled(false);
                    cbTBC.setEnabled(false);

                }else if(cbTidak.isChecked()==false){
                    cbHipertensi.setEnabled(true);
                    cbDiabetes.setEnabled(true);
                    cbKolesterol.setEnabled(true);
                    cbJantung.setEnabled(true);
                    cbGinjal.setEnabled(true);
                    cbKanker.setEnabled(true);
                    cbStroke.setEnabled(true);
                    cbPunggung.setEnabled(true);
                    cbPunggung.setEnabled(true);
                    cbPengapuran.setEnabled(true);
                    cbTBC.setEnabled(true);
                }

            }
        });





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

                if (rgUkurBB.getCheckedRadioButtonId() == -1 || rgUkurTB.getCheckedRadioButtonId() == -1
                        || rgMerokok.getCheckedRadioButtonId() == -1 || rgAlkohol.getCheckedRadioButtonId() == -1
                        || rgMakan.getCheckedRadioButtonId() ==-1 || rgSarapan.getCheckedRadioButtonId() ==-1){
                    Toast.makeText(getApplicationContext(),"Mohon Lengkapi data",Toast.LENGTH_LONG).show();
                }else {

//                    Toast.makeText(getApplicationContext(),
//                            "Berat : " + txt_Berat.getText().toString() + ", Ukur BB :" + rbUkurBB.getText().toString()
//                                    + ", Tinggi :"+ txt_Tinggi.getText().toString() + ",  :" +  rbUkurTB.getText().toString()
//                                    + ", Merokok :" + rbMerokok.getText().toString() + " Alkohol : "+ rbAlkohol.getText().toString()
//                                    + " Makan :" + rbMakan.getText().toString() + " Sarapan :" + rbSarapan.getText().toString()
//                                    , Toast.LENGTH_SHORT).show();

//                    Save();





                       // txt.setText(txt.getText().toString() + " , " + str);
                    ambilValueCheckList();


                       Toast.makeText(StatusGizi.this,"Penyakit : " +list.toString(), Toast.LENGTH_SHORT).show();












                }
            }


        });


        fonts1 =  Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Lato-Regular.ttf");




//        TextView t1 =(TextView)findViewById(R.id.signinhere);
//        t1.setTypeface(fonts1);
    }

    String tidak,hipertensi,diabetes,kolestrol,jantung,ginjal,kanker,stroke,punggung,pengapuran,tbc;

    public void ambilValueCheckList(){

        if(cbTidak.isChecked()){
             tidak= cbTidak.getText().toString();
            list.add(tidak);
        }
        if(cbTidak.isChecked()==false){
            list.remove(tidak);
        }


        if(cbHipertensi.isChecked()==true){
            hipertensi  = cbHipertensi.getText().toString();
            list.add(hipertensi);
        }
        if(cbHipertensi.isChecked()==false){
            list.remove(hipertensi);
        }



        if(cbDiabetes.isChecked()==true){
             diabetes = cbDiabetes.getText().toString();
            list.add(diabetes);
        }
        if(cbDiabetes.isChecked()==false){
            list.remove(diabetes);
        }


        if(cbKolesterol.isChecked()==true){
             kolestrol = cbKolesterol.getText().toString();
            list.add(kolestrol);
        }
        if(cbKolesterol.isChecked()==false){
            list.remove(kolestrol);
        }



        if(cbJantung.isChecked()==true){
             jantung = cbJantung.getText().toString();
            list.add(jantung);

        }
        if(cbJantung.isChecked()==false){
            list.remove(jantung);
        }


        if(cbGinjal.isChecked()==true){
             ginjal = cbGinjal.getText().toString();
            list.add(ginjal);

        }
        if(cbGinjal.isChecked()==false){
            list.remove(ginjal);
        }


        if(cbKanker.isChecked()==true){
             kanker = cbKanker.getText().toString();
            list.add(kanker);

        }
        if(cbKanker.isChecked()==false){
            list.remove(kanker);
        }


        if(cbStroke.isChecked()==true){
             stroke = cbStroke.getText().toString();
            list.add(stroke);
        }
        if(cbStroke.isChecked()==false){
            list.remove(stroke);
        }



        if(cbPunggung.isChecked()==true){
             punggung = cbPunggung.getText().toString();
            list.add(punggung);

        }
        if(cbPunggung.isChecked()==false){
            list.remove(punggung);
        }



        if(cbPengapuran.isChecked()==true){
             pengapuran = cbPengapuran.getText().toString();
            list.add(pengapuran);

        }
        if(cbPengapuran.isChecked()==false){
            list.remove(pengapuran);
        }


        if(cbTBC.isChecked()==true){
             tbc = cbTBC.getText().toString();
            list.add(tbc);

        }
        if(cbTBC.isChecked()==false){
            list.remove(tbc);
        }




    }





    @Override
    public void onBackPressed() {
        Intent it = new Intent(getApplicationContext(), HalamanDepan.class);
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
