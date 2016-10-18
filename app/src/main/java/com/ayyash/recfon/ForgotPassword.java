package com.ayyash.recfon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassword extends AppCompatActivity {
    public static final String KEY_EMAIL = "txt_email";
    public static final String KEY_HP = "txt_hp";

    private EditText email,no_hp;
    Button Konfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText)findViewById(R.id.txtEmail);
        no_hp = (EditText)findViewById(R.id.txtHp);

        Konfirmasi = (Button)findViewById(R.id.btnKonfirmasi);
        Konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Confirm();
            }
        });
    }

    public void Confirm(){
        final String txt_email = email.getText().toString().trim();
        final String txt_hp = no_hp.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,ConfigUmum.URL_GET_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        final AlertDialog alertDialog = new AlertDialog.Builder(
                                ForgotPassword.this).create();

                        // Setting Dialog Title
                        alertDialog.setTitle("Password");

                        // Setting Dialog Message
                        alertDialog.setMessage(response);

                        // Setting Icon to Dialog
//                        alertDialog.setIcon(R.drawable.tick);

                        // Setting OK Button
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog closed
                                alertDialog.dismiss();
                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("aaa", error.toString());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put(KEY_EMAIL, txt_email);
                params.put(KEY_HP, txt_hp);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
