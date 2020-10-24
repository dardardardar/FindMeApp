package com.uts.findmeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragment_Home extends Fragment {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        Button btnlost = view.findViewById(R.id.btnlost);
        Button btnfound = view.findViewById(R.id.btnfound);
        Button btntest = view.findViewById(R.id.btntest);
        btnlost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loadFragment(new Fragment_Lost());
            }
        });
        btnfound.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loadFragment(new Fragment_Found());
            }
        });
        btntest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loadFragment(new Fragment_Testimoni());
            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragment);
        fragmentTransaction.commit(); // save the changes

    }
}
