package com.software.sulthan.kasirtoko;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SemuaAnggota extends AppCompatActivity {
    // Progress Dialog
    private ProgressDialog pDialog;

    // Membuat objek JSONParser
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> memberList;

    // inisialisasi url semuanggota.php
    //private static String url_semua_anggota = "http://10.0.2.2/kolaborasiphp/bab9/crudjsonphp/semuaanggota.php";
    private static String url_semua_anggota = "http://mobapp.sentrasolusi.net/customer/semuaanggota.php";

    // inisialisasi nama node dari json yang dihasilkan oleh php
    private static final String TAG_SUKSES = "sukses";
    private static final String TAG_MEMBER = "member";
    private static final String TAG_IDMEM = "id";
    private static final String TAG_NAMA = "nama";

    // buat JSONArray member
    JSONArray member = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semuaanggotafr);

    }
}
