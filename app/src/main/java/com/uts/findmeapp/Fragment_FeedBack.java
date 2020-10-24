package com.uts.findmeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_FeedBack extends Fragment {

    EditText et_opini, et_kritik, et_saran;
    TextView errorMsg;
    Button sendBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback,container,false);

        et_opini    = view.findViewById(R.id.opini_et);
        et_kritik   = view.findViewById(R.id.kritik_et);
        et_saran    = view.findViewById(R.id.saran_et);
        errorMsg    = (TextView) view.findViewById(R.id.error_msg);

        sendBtn     = view.findViewById(R.id.btn_send);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String opini    = et_opini.getText().toString();
                String kritik   = et_kritik.getText().toString();
                String saran    = et_saran.getText().toString();

                if(opini.isEmpty() || kritik.isEmpty() || saran.isEmpty()) {
                    errorMsg.setText("Semua harus di Isi");
                } else {
                    errorMsg.setText("");
                    Toast.makeText(getActivity(), "Umpan Balik Anda berhasil dikirim", Toast.LENGTH_LONG).show();
                    clearField();
                    return;
                }
            }
        });

        return view;
    }

    public void clearField() {
        et_saran.setText("");
        et_kritik.setText("");
        et_opini.setText("");
    }

}
