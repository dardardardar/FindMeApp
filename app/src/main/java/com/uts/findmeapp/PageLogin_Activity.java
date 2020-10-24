package com.uts.findmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PageLogin_Activity extends AppCompatActivity {

    EditText et_username, et_password;
    Button loginBtn, textRegis_btn;
    TextView errorMsg;
    ArrayList<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_login);
        LoadData();
        et_username = findViewById(R.id.username_field);
        et_password = findViewById(R.id.password_field);
        errorMsg = (TextView) findViewById(R.id.txt_error);

        textRegis_btn = (Button) findViewById(R.id.btn_txt_regis);
        textRegis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPageRegis();

            }
        });

        loginBtn = (Button) findViewById(R.id.btn_login2);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }
    public void LoadData(){
        SharedPreferences sp = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("usersaccount",null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        list = gson.fromJson(json,type);
        if(list == null){
            list = new ArrayList<User>();
        }
    }
    public void validate() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        //validasi bagian username&password harus sesuai dengan yg didaftarkan blm ada

        if(username.equals("")) {
            errorMsg.setText("Username harus diisi");
        } else if(password.equals("")) {
            errorMsg.setText("Password harus diisi");
        }
        else if(!checkUser(username)){
            errorMsg.setText("Username yang diinput salah");
        }
        else if(!checkPass(password)){
            errorMsg.setText("Password yang diinput salah");
        }
        else {
            finish();
            Intent i = new Intent(PageLogin_Activity.this, MainNavi_Activity.class);
            i.putExtra("key",username);
            startActivity(i);
        }
    }
    public boolean checkUser(String name){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getUsername().equals(name)){
                return true;
            }
        }
        return false;
    }
    public boolean checkPass(String pass){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }

    public void openPageRegis(){
        Intent intent = new Intent(this, PageDaftar_Activity.class);

        startActivity(intent);
    }
}
