package com.ayyash.recfon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Isfahani on 30-Jul-16.
 */
public class MainAdapter extends RecyclerView.Adapter<MainHolder> {

    ProgressDialog progressDialog;



    public List<ItemObject.ObjectBelajar.Results> resultsList;
    public Context context;

    public MainAdapter(Context context, List<ItemObject.ObjectBelajar.Results> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, null);
        MainHolder mainHolder = new MainHolder(view);
        return mainHolder;
    }




    public void DeleteData(String Url) {

        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {;
                    @Override
                    public void onResponse(String response) {
                        Log.d("uye", response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("uye", error.toString());

            }
        });
        queue.add(stringRequest);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, final int position) {
        if (resultsList.get(position).nama_makanan.equalsIgnoreCase("Tidak Makan")){
            holder.txt_name.setText("Anda Telah Memilih Tidak Makan");
            holder.txt_office.setVisibility(View.INVISIBLE);
            holder.txtUkuran.setVisibility(View.INVISIBLE);
        }else {
            holder.txt_name.setText("Nama Makanan : " + resultsList.get(position).nama_makanan);
            holder.txt_office.setText("Porsi : " + resultsList.get(position).jumlah_besaran_makanan);
            holder.txtUkuran.setText("Ukuran : " + resultsList.get(position).besaran_makanan);
        }

        final String nama_makanan =resultsList.get(position).nama_makanan;
        final String idd = resultsList.get(position).id;

        holder.cardview_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                progressDialog = new ProgressDialog(context);
//                progressDialog.setCancelable(false);
//                progressDialog.setMessage("Silahkan Tunggu...");


              //  DeleteData(ConfigUmum.URL_DELETE_PAGI+idd);
                // Intent i = new Intent(context, Pengalih.class);
                //  view.getContext().startActivity(i);


                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Konfirmasi");
                builder.setMessage("Apakah anda yakin ingin menghapus\n" +
                        nama_makanan + " ?");
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteData(ConfigUmum.URL_DELETE_PAGI+idd);
//                        dialog.dismiss();

                        //   Intent i = new Intent(context, SarapanActivity.class);

                        //
                        //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        //view.getContext().startActivity(i);


                        Activity activity = (Activity)view.getContext();
                        activity.finish();
                        view.getContext().startActivity(activity.getIntent());
                      //  Toast.makeText(context, "Temuan si okta",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


                //   Toast.makeText(context,"ID nya: "+resultsList.get(position).nama_makanan, Toast.LENGTH_LONG).show();


            }
        });
    }



    @Override
    public int getItemCount() {
        return this.resultsList.size();
    }
}
