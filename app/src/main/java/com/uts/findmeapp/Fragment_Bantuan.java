package com.uts.findmeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Bantuan extends Fragment {

    Button helpBtn1, helpBtn2, helpBtn3, helpBtn4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bantuan,container,false);

        helpBtn1 = (Button) view.findViewById(R.id.help1_btn);
        helpBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageHelp1_Activity.class);
                startActivity(intent);
            }
        });

        helpBtn2 = (Button) view.findViewById(R.id.help2_btn);
        helpBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageHelp2_Activity.class);
                startActivity(intent);
            }
        });

        helpBtn3 = (Button) view.findViewById(R.id.help3_btn);
        helpBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageHelp3_Activity.class);
                startActivity(intent);
            }
        });

        helpBtn4 = (Button) view.findViewById(R.id.help4_btn);
        helpBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageHelp4_Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
