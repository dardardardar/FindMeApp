package com.uts.findmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PageDetailLaporan_Activity extends AppCompatActivity {
    ArrayList<Laporan> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail_laporan);
        final Bundle dat = getIntent().getExtras();
        int id = dat.getInt("key");
        Log.d("apalah","" + id);

        TextView t1 = findViewById(R.id.txt_nama);
        TextView t2 = findViewById(R.id.txt_tgl_lahir);
        TextView t3 = findViewById(R.id.txt_umur);
        TextView t4 = findViewById(R.id.txt_gender);
        TextView t5 = findViewById(R.id.txt_ket);
        TextView t6 = findViewById(R.id.txt_telp);

        for(Laporan l : list){
            if(l.getId() == id){
                t1.setText(l.getName());
                t2.setText(l.getDob());
                t3.setText(Integer.toString(l.getAge()));
                t4.setText(l.getGender());
                t5.setText(l.getDesc());
                t6.setText(l.getPhone());
            }
        }
    }
    public void LoadData(){
        SharedPreferences sp = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("laporan",null);

        Type type = new TypeToken<ArrayList<Laporan>>() {}.getType();
        list = gson.fromJson(json,type);
        if(list == null){
            list = new ArrayList<Laporan>();
        }
    }
}
