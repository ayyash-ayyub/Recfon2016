package com.ayyash.recfon.profile;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayyash.recfon.R;

/**
 * Created by Abdul Rizal Adompo on 30-Jul-16.
 */

public class MainHolderProfile extends RecyclerView.ViewHolder {

    public ImageView img_avatar;
    public TextView satu, dua,tiga,empat,lima,enam;
    public CardView cardview_item;

    public MainHolderProfile(View itemView) {
        super(itemView);

        satu = (TextView) itemView.findViewById(R.id.aa);
        dua = (TextView) itemView.findViewById(R.id.bb);
        tiga = (TextView) itemView.findViewById(R.id.cc);
        empat = (TextView) itemView.findViewById(R.id.dd);
        lima = (TextView) itemView.findViewById(R.id.ee);
      //  enam = (TextView) itemView.findViewById(R.id.ff);
        cardview_item = (CardView) itemView.findViewById(R.id.cardview_item_profile);
    }
}
