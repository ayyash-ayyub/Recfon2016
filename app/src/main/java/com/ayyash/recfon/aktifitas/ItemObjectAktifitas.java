package com.ayyash.recfon.aktifitas;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ItemObjectAktifitas {
    public class ObjectAkatifitas {
        @SerializedName("result")
        public List<Results> result;

        public class Results {
            @SerializedName("id")
            public String id;

            @SerializedName("activity")
            public String activity;

            @SerializedName("kategori")
            public String kategori;

            @SerializedName("durasi")
            public String durasi;
        }
    }
}
