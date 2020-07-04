package com.dima.jobs;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbarProfile;
    TextView birthday;
    EditText firstName;
    EditText patronymic;
    EditText lastName;
    Spinner sex;
    Button datePicker;
    Button btnSave;

    String FIRST_NAME = "first name";
    String PATRONYMIC = "patronymic";
    String LAST_NAME = "last name";
    String SEX = "sex";
    String BIRTHDAY = "birthday";

    final Calendar pickedDate = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            pickedDate.set(Calendar.YEAR, year);
            pickedDate.set(Calendar.MONTH, monthOfYear);
            pickedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            birthday.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(pickedDate.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initUI();
        readData();
    }

    private void initUI() {
        toolbarProfile = findViewById(R.id.toolbar_profile);
        toolbarProfile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        firstName = findViewById(R.id.first_name);
        patronymic = findViewById(R.id.patronymic);
        lastName = findViewById(R.id.last_name);
        sex = findViewById(R.id.sex);
        birthday = findViewById(R.id.birthday_text);

        datePicker = findViewById(R.id.datepicker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btnSave = findViewById(R.id.save_data);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                showToast();
            }
        });
    }

    private void showDatePicker() {
        new DatePickerDialog(ProfileActivity.this, onDateSetListener,
                pickedDate.get(Calendar.YEAR),
                pickedDate.get(Calendar.MONTH),
                pickedDate.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void saveData() {
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .putString(FIRST_NAME, firstName.getText().toString())
                .putString(PATRONYMIC, patronymic.getText().toString())
                .putString(LAST_NAME, lastName.getText().toString())
                .putInt(SEX, sex.getSelectedItemPosition())
                .putString(BIRTHDAY, birthday.getText().toString())
                .apply();
    }

    private void readData() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        firstName.setText(prefs.getString(FIRST_NAME, "-"));
        patronymic.setText(prefs.getString(PATRONYMIC, "-"));
        lastName.setText(prefs.getString(LAST_NAME, "-"));
        sex.setSelection(prefs.getInt(SEX, 1));
        birthday.setText(prefs.getString(BIRTHDAY, "-"));
    }

    private void showToast() {
        Toast.makeText(ProfileActivity.this, "All data changed", Toast.LENGTH_LONG).show();
    }
}
