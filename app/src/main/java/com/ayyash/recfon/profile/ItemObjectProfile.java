package com.ayyash.recfon.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ItemObjectProfile {
    public class ObjectProfile {
        @SerializedName("result")
        public List<Results> result;

        public class Results {
            @SerializedName("filled_times")
            public String filled_times;

            @SerializedName("akg_energy")
            public String akg_energy;

            @SerializedName("akg_protein")
            public String akg_protein;

            @SerializedName("akg_lemak")
            public String akg_lemak;

            @SerializedName("akg_karbohidrat")
            public String akg_karbohidrat;

        }
    }
}
