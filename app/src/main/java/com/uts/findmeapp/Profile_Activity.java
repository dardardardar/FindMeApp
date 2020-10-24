package com.uts.findmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Profile_Activity extends AppCompatActivity {
    ArrayList<User> list;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_profile);


        EditText name = findViewById(R.id.profile_name);
        EditText username = findViewById(R.id.profile_username);
        EditText email = findViewById(R.id.profile_email);
        EditText password = findViewById(R.id.profile_password);


        final Bundle dat = getIntent().getExtras();
        User u = getUsername(dat.getString("key"));

        name.setText(u.getName());
        username.setText(u.getUsername());
        email.setText(u.getEmail());
        password.setText(u.getPassword());
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
    public User getUsername(String s){
        for(User user : list){
            if(user.getUsername().equals(s)){
                return user;
            }
        }
        return new User(null,null,null,null,null);
    }
}
