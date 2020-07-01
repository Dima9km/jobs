package com.dima.jobs;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbarProfile = findViewById(R.id.toolbar_profile);
        toolbarProfile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        EditText firstName = findViewById(R.id.first_name);
        EditText patronymic = findViewById(R.id.patronymic);
        EditText lastName = findViewById(R.id.last_name);
        Spinner sex = findViewById(R.id.sex);
        final TextView birthday = findViewById(R.id.birthday_text);
        Button datePicker = findViewById(R.id.datepicker);

        Calendar startedDate = null;
        final Calendar pickedDate = Calendar.getInstance();
        if (pickedDate == null) {
            startedDate = Calendar.getInstance();
        } else {
            startedDate = pickedDate;
        }

        final Calendar finalStartedDate = startedDate;
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        pickedDate.set(Calendar.YEAR, year);
                        pickedDate.set(Calendar.MONTH, monthOfYear);
                        pickedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        birthday.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(pickedDate.getTime()));
                    }
                };

                new DatePickerDialog(ProfileActivity.this, onDateSetListener,
                        finalStartedDate.get(Calendar.YEAR),
                        finalStartedDate.get(Calendar.MONTH),
                        finalStartedDate.get(Calendar.DAY_OF_MONTH))
                        .show();

            }
        });


    }
}
