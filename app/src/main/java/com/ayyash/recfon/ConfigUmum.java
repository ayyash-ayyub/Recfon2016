package com.ayyash.recfon;

import android.net.Uri;

/**
 * Created by Abdul Rizal Adompo on 9/18/2016.
 */
public class ConfigUmum {

    public static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

    public static final String IP="103.43.45.237";

    public static final String LOGIN_URL = "http://"+IP+"/recfon/api/login.php";

    //buat pagi
    public static String URL_SHOW_ACTIVITY = "http://"+IP+"/recfon/api/get_record_activity.php?email=";


    public static String URL_SHOW_PAGI = "http://"+IP+"/recfon/api/get_record_pagi.php?email=";
    public static String URL_DELETE_PAGI = "http://"+IP+"/recfon/api/delete_food_record.php?id=";
    public static String URL_INSERT_PAGI = "http://"+IP+"/recfon/api/insert_record_pagi.php";

    //buat selingan pagi
    public static String URL_SHOW_SELINGAN_PAGI = "http://"+IP+"/recfon/api/get_record_selingan_pagi.php?email=";
    public static String URL_INSERT_SELINGAN_PAGI = "http://"+IP+"/recfon/api/insert_record_selingan_pagi.php";

    //buat makan siang
    public static String URL_SHOW_MAKAN_SIANG = "http://"+IP+"/recfon/api/get_record_makan_siang.php?email=";
    public static String URL_INSERT_MAKAN_SIANG = "http://"+IP+"/recfon/api/insert_record_makan_siang.php";

 //buat makan Selingan siang
    public static String URL_SHOW_SELINGAN_SIANG = "http://"+IP+"/recfon/api/get_record_selingan_siang.php?email=";
    public static String URL_INSERT_SELINGAN_SIANG = "http://"+IP+"/recfon/api/insert_record_selingan_siang.php";

 //buat makan malam
    public static String URL_SHOW_MAKAN_MALAM = "http://"+IP+"/recfon/api/get_record_makan_malam.php?email=";
    public static String URL_INSERT_MAKAN_MALAM = "http://"+IP+"/recfon/api/insert_record_makan_malam.php";


 //buat makan selingan malam
    public static String URL_SHOW_SELINGAN_MALAM = "http://"+IP+"/recfon/api/get_record_selingan_malam.php?email=";
    public static String URL_INSERT_SELINGAN_MALAM = "http://"+IP+"/recfon/api/insert_record_selingan_malam.php";


//    public static String URL_SIMPAN_PAGI(String email, String bahan_makanan, String jumlah, String besaran_makanan) {
//        return Uri.encode("http://"+IP+"/insert_record_pagi.php?email=" + email + "&bahan_makanan=" + bahan_makanan + "&jumlah=" + jumlah + "&besaran_makanan=" + besaran_makanan, ALLOWED_URI_CHARS);
//    }

    // delete activity

    public static String URL_INSERT_ACTIVITY = "http://"+IP+"/recfon/api/insert_activity.php";
    public static String URL_DELETE_ACTIVITY = "http://"+IP+"/recfon/api/delete_activity.php?id=";


    //regiser
    public static String REGISTER = "http://"+IP+"/recfon/api/register.php";


    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String LOGIN_SUCCESS = "success";
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String AMBIL_NAMA = "nama";
    public static final String NIS_SHARED_PREF = "email";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String ID_KELAS = "id_kelas";
}
