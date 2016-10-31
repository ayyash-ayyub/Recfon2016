package com.ayyash.recfon;

import android.net.Uri;

/**
 * Created by Abdul Rizal Adompo on 9/18/2016.
 */
public class ConfigUmum {

    public static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";

    public static final String IP="recfon.projectaplikasi.web.id";

    public static final String LOGIN_URL = "http://"+IP+"/api/login.php";
    public static final String CEK_INPUT_SEBELUMNYA = "http://"+IP+"/api/check_input_sebelumnya.php?";

    //buat pagi

    public static String URL_GET_PASS = "http://"+IP+"/api/lupapasword.php";
    public static String URL_UPDATE_STATUS_GIZI = "http://"+IP+"/api/update_status_gizi.php";
    public static String URL_SHOW_FREKUENSI = "http://"+IP+"/api/get_frekuensi.php?email=";
    public static String URL_SHOW_ACTIVITY = "http://"+IP+"/api/get_record_activity.php?email=";
    public static String URL_LIST_MAKANAN = "http://"+IP+"/api/get_list_makanan.php?email=";
    public static String URL_INSERT_FOOD_RECALL = "http://"+IP+"/api/insert_frekuensi_makan.php";


    public static String URL_SHOW_PAGI = "http://"+IP+"/api/get_record_pagi.php?email=";
    public static String URL_DELETE_PAGI = "http://"+IP+"/api/delete_food_record.php?id=";
    public static String URL_INSERT_PAGI = "http://"+IP+"/api/insert_record_pagi.php";

    //buat selingan pagi
    public static String URL_SHOW_SELINGAN_PAGI = "http://"+IP+"/api/get_record_selingan_pagi.php?email=";
    public static String URL_INSERT_SELINGAN_PAGI = "http://"+IP+"/api/insert_record_selingan_pagi.php";

    //buat makan siang
    public static String URL_SHOW_MAKAN_SIANG = "http://"+IP+"/api/get_record_makan_siang.php?email=";
    public static String URL_INSERT_MAKAN_SIANG = "http://"+IP+"/api/insert_record_makan_siang.php";

 //buat makan Selingan siang
    public static String URL_SHOW_SELINGAN_SIANG = "http://"+IP+"/api/get_record_selingan_siang.php?email=";
    public static String URL_INSERT_SELINGAN_SIANG = "http://"+IP+"/api/insert_record_selingan_siang.php";

 //buat makan malam
    public static String URL_SHOW_MAKAN_MALAM = "http://"+IP+"/api/get_record_makan_malam.php?email=";
    public static String URL_INSERT_MAKAN_MALAM = "http://"+IP+"/api/insert_record_makan_malam.php";


 //buat makan selingan malam
    public static String URL_SHOW_SELINGAN_MALAM = "http://"+IP+"/api/get_record_selingan_malam.php?email=";
    public static String URL_INSERT_SELINGAN_MALAM = "http://"+IP+"/api/insert_record_selingan_malam.php";


// buat get Kecukupan asupan
    public static String URL_SHOW_AKG_PROFILE = "http://"+IP+"/api/get_profile_kecukupan_asupan.php?email=";


    //activity
    public static String URL_INSERT_ACTIVITY = "http://"+IP+"/api/insert_activity.php";
    public static String URL_DELETE_ACTIVITY = "http://"+IP+"/api/delete_activity.php?id=";

    public static String URL_INSERT_ASUPAN = "http://"+IP+"/api/insert_asupan.php";

    //get BMI Profile
    public static String URL_BMI_PROFILE = "http://"+IP+"/api/get_profile_status_gizi.php?email=";


    //get AktifitasFisik dan duduk
    public static String URL_FISIK_DUDUK = "http://"+IP+"/api/get_profile_aktifitas_fisik.php?email=";






    //regiser
    public static String REGISTER = "http://"+IP+"/api/register.php";


    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String LOGIN_SUCCESS = "success";
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String AMBIL_NAMA = "nama";
    public static final String NIS_SHARED_PREF = "email";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String ID_KELAS = "id_kelas";
}
