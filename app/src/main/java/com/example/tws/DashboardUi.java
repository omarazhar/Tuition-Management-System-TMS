package com.example.tws;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DashboardUi extends AppCompatActivity {

    //TextView textView;
    private LinearLayout calenderHeader;
    private GridView gridView;
    private TextView textView;
    private static int MAX_CAL_DAY = 42;
    DashboardAdapter dashboardAdapter;

    private Button searchTuition;
    private Button getResources;
    private Button studentOrClass;
    private Button salary;


    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        initialize();

        searchTuition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchTuitionIntent = new Intent(getApplicationContext(), SearchTuition.class);
                startActivity(searchTuitionIntent);
                }
            }
        );

        Calendar calendar = Calendar.getInstance();
        Calendar calendars = (Calendar)calendar.clone();

        // determine the cell for current month's beginning
        calendars.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCells = calendars.get(Calendar.DAY_OF_WEEK) - 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd, MM,yy ");

        // move calendar backwards to the beginning of the week
        calendars.add(Calendar.DAY_OF_MONTH, -monthBeginningCells);

        ArrayList<Date> cells = new ArrayList<>();

        // fill cells

        while (cells.size() < MAX_CAL_DAY)
        {
            sdf.format(calendars.getTime());
            cells.add(calendars.getTime());
            calendars.add(Calendar.DAY_OF_MONTH, 1);
        }

//        String s = DateFormat.getDateInstance().format(calendar.getTime());
//        textView.setText(s);

        dashboardAdapter = new DashboardAdapter(getApplicationContext(), cells);
        gridView = findViewById(R.id.calendar_grid);
        gridView.setAdapter(dashboardAdapter);

    }
    private void initialize() {
        searchTuition = findViewById(R.id.search_tuition);
        getResources = findViewById(R.id.get_resource);
        studentOrClass = findViewById(R.id.student_or_classroom);
        salary = findViewById(R.id.dashboard_salary);

        calenderHeader = findViewById(R.id.calendar_header);
        gridView = findViewById(R.id.calendar_grid);
    }
}
