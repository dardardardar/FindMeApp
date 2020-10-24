package com.uts.findmeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class ReportList extends ArrayAdapter<Laporan> {
    private static final String tag = "ReportList";
    private Context c;
    int res;
    public ReportList(@NonNull Context context, int resource, @NonNull ArrayList<Laporan> objects) {
        super(context, resource, objects);
        c = context;
        res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int Id = getItem(position).getId();
        String Name = getItem(position).getName();
        String Dob = getItem(position).getDob();
        int Age = getItem(position).getAge();
        String Gender = getItem(position).getGender();
        String Desc = getItem(position).getDesc();
        String Phone = getItem(position).getPhone();
        String Type = getItem(position).getType();
        String Date = getItem(position).getDate();

        Laporan report = new Laporan(Id,Name,Dob,Age,Gender,Desc,Phone,Type,Date);
        LayoutInflater inflater = LayoutInflater.from(c);
            convertView = inflater.inflate(res,parent,false);

        TextView t1 = convertView.findViewById(R.id.rname);
        TextView t2 = convertView.findViewById(R.id.rdesc);
        TextView t3 = convertView.findViewById(R.id.rdate);

        t1.setText(Name);
        t2.setText(Desc);
        t3.setText(Date);

        return convertView;
    }
}
