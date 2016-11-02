package com.ayyash.recfon.profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayyash.recfon.R;

import java.util.List;

/**
 * Created by Isfahani on 30-Jul-16.
 */
public class MainAdapterProfile extends RecyclerView.Adapter<MainHolderProfile> {

    ProgressDialog progressDialog;



    public List<ItemObjectProfile.ObjectProfile.Results> resultsList;
    public Context context;

    public MainAdapterProfile(Context context, List<ItemObjectProfile.ObjectProfile.Results> resultsList) {

        this.context = context;
        this.resultsList = resultsList;
    }

    @Override
    public MainHolderProfile onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_profile, null);
        MainHolderProfile mainHolder = new MainHolderProfile(view);
        return mainHolder;



    }




//    public void DeleteData(String Url) {
//
//        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
//                new Response.Listener<String>() {;
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("uye jadi hapus", response);
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("uye gagal hapus", error.toString());
//
//            }
//        });
//        queue.add(stringRequest);
//    }

    @Override
    public void onBindViewHolder(MainHolderProfile holder, final int position) {
        holder.satu.setText("Tanggal            : "+resultsList.get(position).filled_times);
        holder.dua.setText("Energy          : "+resultsList.get(position).akg_energy);
        holder.tiga.setText("Protein        : "+resultsList.get(position).akg_protein);
        holder.empat.setText("Lemak         : " +resultsList.get(position).akg_lemak);
        holder.lima.setText("Karbohidrat    : "+resultsList.get(position).akg_karbohidrat);
       // holder.enam.setText("Asupan Gizi  : " +resultsList.get(position).keterangan);
//
//
//        final String nama_makanan =resultsList.get(position).activity;
//        final String idd = resultsList.get(position).id;
//
//        holder.cardview_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                progressDialog = new ProgressDialog(context);
//                progressDialog.setCancelable(false);
//                progressDialog.setMessage("Silahkan Tunggu...");
//
//
//              //  DeleteData(ConfigUmum.URL_DELETE_ACTIVITY+idd);
//                // Intent i = new Intent(context, Pengalih.class);
//                //  view.getContext().startActivity(i);
//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setTitle("Konfirmasi");
//                builder.setMessage("Apakah anda yakin ingin menghapus\n" +
//                        nama_makanan + " ?");
//                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                      //  http://103.43.45.237/recfon/api/delete_activity.php?id=13
//                        DeleteData(ConfigUmum.URL_DELETE_ACTIVITY+idd);
////                        dialog.dismiss();
//
//                        //   Intent i = new Intent(context, SarapanActivity.class);
//
//                        //
//                        //  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
//                        //view.getContext().startActivity(i);
//
//                     //   Toast.makeText(context,"UYE:"+idd, Toast.LENGTH_SHORT).show();
//
//
//                        Activity activity = (Activity)view.getContext();
//                        activity.finish();
//                        view.getContext().startActivity(activity.getIntent());
//
//                    }
//                });
//                builder.setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
//
//
//                //   Toast.makeText(context,"ID nya: "+resultsList.get(position).nama_makanan, Toast.LENGTH_LONG).show();
//
//
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return this.resultsList.size();
    }
}
