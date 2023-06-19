package com.example.tws;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentDashboardAdapter extends ArrayAdapter<Date> {
    List<Date> cellDates;
    LayoutInflater inflater;
    List<Integer> DaysToTeach;

    public StudentDashboardAdapter(@NonNull Context context, List<Date> cellDates) {
        super(context, R.layout.single_cell_layout_student);
        this.cellDates = cellDates;
        inflater = LayoutInflater.from(context);
        DaysToTeach = new ArrayList<Integer>(2);
        DaysToTeach.add(0, 6);
        DaysToTeach.add(1, 2);
    }


    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Calendar calendar = Calendar.getInstance();
        Date date = cellDates.get(position);
        calendar.setTime(date);

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.single_cell_layout_student, parent, false);
        }

        for (int i : DaysToTeach){
            int l = date.getDay();
            if (i == l)
            {
                convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            }
        }


        TextView singleCell = convertView.findViewById(R.id.single_cell_text_student);
        singleCell.setText(String.valueOf(calendar.get(Calendar.DATE)));


        return convertView;
    }

    @Override
    public int getCount() {
        return cellDates.size();
    }

    @Override
    public int getPosition(@Nullable Date item) {
        return cellDates.indexOf(item);
    }

    @Nullable
    @Override
    public Date getItem(int position) {
        return cellDates.get(position);
    }
}
