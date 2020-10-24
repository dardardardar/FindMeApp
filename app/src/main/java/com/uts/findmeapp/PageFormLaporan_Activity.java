package com.uts.findmeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PageFormLaporan_Activity extends AppCompatActivity {

    EditText et_name, et_desc, et_age, et_date, et_numb;
    RadioGroup radioGroup, radioGroup2;
    RadioButton rb_male, rb_female, rb_lost, rb_found;
    TextView errorMsg,uploadimg;
    Button submitBtn;
    String currentPhotoPath;
    ArrayList<Laporan> list = new ArrayList<Laporan>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_form_laporan);

        et_name      = findViewById(R.id.form_name_field);
        et_desc      = findViewById(R.id.keterangan_field);
        et_age       = findViewById(R.id.form_umur);
        et_date      = findViewById(R.id.form_tgl_lahir);
        et_numb      = findViewById(R.id.form_telp);
        uploadimg    = findViewById(R.id.upload_image);
        radioGroup   = findViewById(R.id.rbGroup);
        rb_male      = findViewById(R.id.rbMale);
        rb_female    = findViewById(R.id.rbFemale);
        rb_lost      = findViewById(R.id.rbLost);
        rb_found     = findViewById(R.id.rbFound);
        radioGroup2 = findViewById(R.id.rbGroup_tipe);
        errorMsg     = (TextView) findViewById(R.id.txt_error);

        submitBtn = (Button) findViewById(R.id.submit_Btn);
        uploadimg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,105);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

    }
    public void SaveData(){
        SharedPreferences sp = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        ed.putString("laporan",json);
        ed.apply();
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 105) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp +"."+getFileExt(contentUri);
                Log.d("tag", "onActivityResult: Gallery Image Uri:  " +  imageFileName);


            }
        }
    }
    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    public void validate() {

        int isSelected = radioGroup.getCheckedRadioButtonId();
        int isSelected2 = radioGroup2.getCheckedRadioButtonId();
        String name      = et_name.getText().toString();
        String desc      = et_desc.getText().toString();
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(isSelected==-1) {
            errorMsg.setText("Semua harus diisi");
            return;
        } else if(isSelected2==-1) {
            errorMsg.setText("Semua harus diisi");
        } else if(name.equals("") || desc.equals("") || et_age.getText().equals("") || et_date.getText().equals("") || et_numb.getText().equals("")){
            errorMsg.setText("Semua harus diisi");
        } else if (name.length()<5) {
            errorMsg.setText("Nama minimal 5 karakter");
        } else if (desc.length()<10){
            errorMsg.setText("Minimal 100 karakter");
        } else if (desc.length()>500){
            errorMsg.setText("Maksimal 500 karakter");
        } else if (et_numb.getText().length()<10) {
            errorMsg.setText("Nomor kontak minimal 10 angka");
        } else {
            finish();
            list.add(new Laporan(list.size() + 1,name,et_date.getText().toString(),Integer.parseInt(et_age.getText().toString()),"Perempuan",desc,et_numb.getText().toString(),"Lost",format.format(d)));

            SaveData();
            Toast toast = Toast.makeText(getApplicationContext(), "Upload Data Success", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void LoadData(){
        SharedPreferences sp = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("laporan",null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        list = gson.fromJson(json,type);
        if(list == null){
            list = new ArrayList<Laporan>();
        }
    }
    public String getRadio(RadioGroup rg){

        int isSelected = rg.getCheckedRadioButtonId();
        RadioButton rb = findViewById(isSelected);
        return rb.getText().toString();

    }

}
