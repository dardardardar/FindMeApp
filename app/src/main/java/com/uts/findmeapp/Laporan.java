package com.uts.findmeapp;

public class Laporan {
    private int Id;
    private String Name;
    private String Dob;
    private int Age;
    private String Gender;
    private String Desc;
    private String Phone;
    private String Type;
    private String Date;
    private String Image;

    public Laporan(int id, String name, String dob, int age, String gender, String desc, String phone, String type, String date) {
        Id = id;
        Name = name;
        Dob = dob;
        Age = age;
        Gender = gender;
        Desc = desc;
        Phone = phone;
        Type = type;
        Date = date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

