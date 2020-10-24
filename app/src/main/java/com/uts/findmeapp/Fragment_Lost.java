package com.uts.findmeapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class Fragment_Lost extends Fragment {
    ImageButton add;
    ArrayList<Laporan> list;
    ArrayList<Laporan> list2 = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoadData();
        for(Laporan l : list){
            if(l.getType().equals("Lost")){
                list2.add(l);
            }
        }
        View v = inflater.inflate(R.layout.fragment_lost,container,false);
        add = v.findViewById(R.id.img_btn_lost);
        ListView item = v.findViewById(R.id.reportlist);
        ReportList r = new ReportList(getActivity(), R.layout.listview_adapter, list2);
        item.setAdapter(r);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),PageFormLaporan_Activity.class);
                startActivity(i);
            }
        });
        item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                for(int i =0 ; i < list2.size(); i++){
                    if(pos == i){
                        Intent in = new Intent(getActivity(),PageDetailLaporan_Activity.class);
                        in.putExtra("key",list2.get(i).getId());
                        startActivity(in);
                    }
                }
            }
        });
        return v;
    }
    public void LoadData(){
        SharedPreferences sp = getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("laporan",null);

        Type type = new TypeToken<ArrayList<Laporan>>() {}.getType();
        list = gson.fromJson(json,type);
        if(list == null){
            list = new ArrayList<Laporan>();
        }
    }
}
