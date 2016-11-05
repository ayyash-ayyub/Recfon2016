package com.ayyash.recfon;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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

    public static final String KEY_Email = "txtEmail";
    public static final String KEY_BERAT = "BB";
    public static final String KEY_UKURBB = "ukurBB";
    public static final String KEY_TINGGI= "TB";
    public static final String KEY_UKURTB = "ukurTB";
    public static final String KEY_MEROKOK = "merokok";
    public static final String KEY_ALKOHOL = "alkohol";
    public static final String KEY_PENYAKIT = "penyakit";
    public static final String KEY_MAKAN = "makan";
    public static final String KEY_SARAPAN = "sarapan";




    Button simpan;

    EditText txt_Berat, txt_Tinggi;
    RadioGroup rgUkurBB, rgUkurTB, rgMerokok, rgAlkohol, rgMakan, rgSarapan;
    RadioButton rbUkurBB, rbUkurTB, rbMerokok, rbAlkohol, rbMakan, rbSarapan;
    CheckBox cbTidak, cbHipertensi, cbDiabetes, cbKolesterol, cbJantung, cbGinjal, cbKanker, cbStroke,cbPunggung,cbPengapuran,cbTBC;
    TextView txt;

    String email;

    Typeface fonts1;
    ArrayList list ;

    ProgressDialog PD;

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

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
        toolbar.setNavigationIcon(R.drawable.logo_atas);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);


        SharedPreferences sharedPreferences = getSharedPreferences(ConfigUmum.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(ConfigUmum.NIS_SHARED_PREF, "tidak tersedia");

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

                if (txt_Berat.getText().equals("")||txt_Tinggi.getText().equals("")||rgUkurBB.getCheckedRadioButtonId() == -1 || rgUkurTB.getCheckedRadioButtonId() == -1
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

//
                    ambilValueCheckList();

//                       Toast.makeText(StatusGizi.this,"Penyakit : " +list.toString(), Toast.LENGTH_SHORT).show();

                    Save();

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

    private void Save() {
        PD.show();
        final String txtEmail = email.toString().trim();
        final String BB = txt_Berat.getText().toString().trim();
        final String ukurBB = rbUkurBB.getText().toString().trim();
        final String TB = txt_Tinggi.getText().toString().trim();
        final String ukurTB = rbUkurTB.getText().toString().trim();
        final String merokok = rbMerokok.getText().toString().trim();
        final String alkohol = rbAlkohol.getText().toString().trim();
        final String penyakit = list.toString();
        final String makan = rbMakan.getText().toString().trim();
        final String sarapan = rbSarapan.getText().toString().trim();

       // Toast.makeText(getApplicationContext(),TB,Toast.LENGTH_LONG).show();

        //parsing id kelas
//            final String sIdKelas = getIdKelas(ambilIDKelas);
        //final String sIdKelas = "100000";
        //final int saveIdKelas = Integer.parseInt(sIdKelas);

        StringRequest sR = new StringRequest(Request.Method.POST, ConfigUmum.URL_UPDATE_STATUS_GIZI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), Persetujuan.class);
                        startActivity(i);
                        finish();
                        System.out.println("sql"+response);
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
                params.put(KEY_BERAT, BB);
                params.put(KEY_UKURBB, ukurBB);
                params.put(KEY_TINGGI, TB);
                params.put(KEY_UKURTB, ukurTB);
                params.put(KEY_MEROKOK, merokok);
                params.put(KEY_ALKOHOL, alkohol);
                params.put(KEY_PENYAKIT, penyakit);
                params.put(KEY_MAKAN, makan);
                params.put(KEY_SARAPAN, sarapan);
                params.put(KEY_Email, txtEmail);

                return params;
            }

        };
//        Toast.makeText(getApplicationContext(), txt_email + " makanan = " + makanan, Toast.LENGTH_LONG).show();
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        sR.setRetryPolicy(policy);
        requestQueue.add(sR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuHelp) {
            // Toast.makeText(MainMenu.this,"ini help", Toast.LENGTH_LONG).show();
            help();
        }
        return super.onOptionsItemSelected(item);
    }


    private void help() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Petunjuk");
        // builder.setMessage("Anda diminta menuliskan jenis dan jumlah yang makanan dan minuman yang dikonsumsi selama 24 jam HARI INI (sejak bangun tidur hingga tidur lagi)");
        // (Html.fromHtml("Hello "+"<b>"+"World"+"</b>"));
        builder.setMessage(Html.fromHtml("Mohon untuk mengisi  "+
                "semua pertanyaan dihalaman ini dengan sebenar-benarnya dengan cara ;."+ "<br><br><br>"  +
                "<b>" + "1.Menuliskan berat & tinggi badan pada form yang telah disediakan." + "</b><br>" +
                "<b>" + "2.Menjawab dengan opsi yang sesuai pada masing-masing pertanyaan." + "</b><br>" +
                "<b>" + "3.Khusus riwayat penyakit, anda dapat memilih lebih dari satu dengan pilihan yang sesuai." + "</b><br>" +
                "<br><br><br>" + "Pertanyaan di halaman ini cukup diisi sekali saja. jika ada perubahan data, maka akan otomatis mengganti/mengupdate data yang lama" +
                ""));

        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.LEFT);
        dialog.show();

    }
}
