package com.ayyash.recfon;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ItemObject {
    public class ObjectBelajar {
        @SerializedName("result")
        public List<Results> result;

        public class Results {
            @SerializedName("id")
            public String id;

            @SerializedName("nama_makanan")
            public String nama_makanan;

            @SerializedName("jumlah_besaran_makanan")
            public String jumlah_besaran_makanan;

            @SerializedName("besaran_makanan")
            public String besaran_makanan;
        }
    }
}
