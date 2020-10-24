package com.uts.findmeapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Found extends Fragment {

    ArrayList<Laporan> list;
    ArrayList<Laporan> list2 = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoadData();
        for(Laporan l : list){
            if(l.getType().equals("Found")){
                list2.add(l);
            }
        }
        View view = inflater.inflate(R.layout.fragment_found,container,false);
        ImageButton add = view.findViewById(R.id.img_btn_found);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),PageFormLaporan_Activity.class));
            }
        });
        return view;
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
