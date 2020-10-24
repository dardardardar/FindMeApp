package com.uts.findmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PageDaftar_Activity extends AppCompatActivity {

    EditText et_name, et_username, et_email, et_password, et_cPassword;
    CheckBox cbAgreement;
    TextView errorMsg;
    Button regisBtn, txtLogin_Btn;
    ArrayList<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_daftar);
        LoadData();
        et_name      = findViewById(R.id.name_field);
        et_username  = findViewById(R.id.username_field);
        et_email     = findViewById(R.id.email_field);
        et_password  = findViewById(R.id.password_field2);
        et_cPassword = findViewById(R.id.cpassword_field);
        cbAgreement  = (CheckBox) findViewById(R.id.cb_agreement);
        errorMsg     = (TextView) findViewById(R.id.txt_error);

        txtLogin_Btn = (Button) findViewById(R.id.btn_txt_login);
        txtLogin_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });

        regisBtn = (Button) findViewById(R.id.btn_login2);
        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        String name      = et_name.getText().toString();
        String username  = et_username.getText().toString();
        String email     = et_email.getText().toString();
        String password  = et_password.getText().toString();
        String cPassword = et_cPassword.getText().toString();

        if(name.equals("") || username.equals("") || email.equals("") || password.equals("") || cPassword.equals("")){
            errorMsg.setText("Semua harus diisi");
        } else if (name.length()<5) {
            errorMsg.setText("Nama minimal 5 karakter");
        } else if(username.length()<5) {
            errorMsg.setText("Username minimal 5 karakter");
        } else if (!email.endsWith("@gmail.com")) {
            errorMsg.setText("Email harus '@gmail.com'");
        } else if (password.length()<8) {
            errorMsg.setText("Password minimal 8 karakter");
        } else if (!cPassword.equals(password)) {
            errorMsg.setText("Konfirmasi Password harus sesuai dengan Password");
        } else if (!cbAgreement.isChecked()) {
            errorMsg.setText("Syarat dan ketentuan harus disetujui");
        } else {
            list.add(new User("U" + list.size() + 1 ,name,username,email,password));
            SaveData();
            finish();
            startActivity(new Intent(PageDaftar_Activity.this, PageLogin_Activity.class));
        }
    }
    public void SaveData(){
        SharedPreferences sp = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        ed.putString("usersaccount",json);
        ed.apply();
    }

    public void openLoginPage() {
        Intent intent = new Intent(this, PageLogin_Activity.class);
        startActivity(intent);
    }
}
