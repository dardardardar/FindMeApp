package com.uts.findmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PageHelp4_Activity extends AppCompatActivity {

    EditText et_masalah4;
    TextView errorMsg;
    Button kirimBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_help4);

        et_masalah4 = findViewById(R.id.et_masalah1);
        errorMsg = findViewById(R.id.error_msg);
        kirimBtn4 = findViewById(R.id.btn_kirim4);
        kirimBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masalah1 = et_masalah4.getText().toString();
                if(masalah1.isEmpty()){
                    errorMsg.setText("Ketik deskripsi masalah yang Anda alami");
                } else {
                    Toast.makeText(getApplicationContext(), "Deskripsi masalah Anda telah terkirim. Terima Kasih", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(PageHelp4_Activity.this, MainNavi_Activity.class));
                }
            }
        });

    }
}
