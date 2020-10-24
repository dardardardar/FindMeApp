package com.uts.findmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageAwal_Activity extends AppCompatActivity {

    private Button loginBtn, regisBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_awal);

        regisBtn = (Button) findViewById(R.id.btn_regis);
        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisPage();
            }
        });

        loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });
    }

    public void openLoginPage() {
        Intent intent = new Intent(this, PageLogin_Activity.class);
        startActivity(intent);
    }

    public void openRegisPage() {
        Intent intent = new Intent(this, PageDaftar_Activity.class);
        startActivity(intent);
    }
}
