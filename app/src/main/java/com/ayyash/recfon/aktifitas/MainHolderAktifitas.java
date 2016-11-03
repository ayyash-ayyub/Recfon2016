package com.ayyash.recfon.aktifitas;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayyash.recfon.R;

/**
 * Created by Isfahani on 30-Jul-16.
 */

public class MainHolderAktifitas extends RecyclerView.ViewHolder {

    public ImageView img_avatar;
    public TextView txt_name, txt_durasi, txt_frekuensi;
    public CardView cardview_item;

    public MainHolderAktifitas(View itemView) {
        super(itemView);

        txt_name = (TextView) itemView.findViewById(R.id.aa);
        txt_durasi = (TextView) itemView.findViewById(R.id.txtDurasi);
        txt_frekuensi = (TextView)itemView.findViewById(R.id.txtFrekuensia);
        cardview_item = (CardView) itemView.findViewById(R.id.cardview_item_aktifitas);
    }
}
