package com.uts.findmeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Testimoni extends Fragment {

    ImageButton imageButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_testimoni,container,false);

        imageButton = view.findViewById(R.id.img_btn_test);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), PageHelp1_Activity.class); //GANTI JADI PAGE FORM TESTIMONI
//                startActivity(intent);
            }
        });

        return view;
    }
}
