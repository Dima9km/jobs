package com.dima.jobs.ui.screens.profile;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dima.jobs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfileFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        readData();
    }

    private void initUI(View view) {
        firstName = view.findViewById(R.id.etFirstName);
        patronymic = view.findViewById(R.id.etPatronymic);
        lastName = view.findViewById(R.id.etLastName);
        sex = view.findViewById(R.id.spSex);
        birthday = view.findViewById(R.id.tvBirthdayText);

        datePicker = view.findViewById(R.id.btnDatePicker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btnSave = view.findViewById(R.id.btnSaveData);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                showToast();
            }
        });
    }

    private void showDatePicker() {
        new DatePickerDialog(getContext(), onDateSetListener,
                pickedDate.get(Calendar.YEAR),
                pickedDate.get(Calendar.MONTH),
                pickedDate.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void saveData() {
        PreferenceManager.getDefaultSharedPreferences(getContext())
                .edit()
                .putString(FIRST_NAME, firstName.getText().toString())
                .putString(PATRONYMIC, patronymic.getText().toString())
                .putString(LAST_NAME, lastName.getText().toString())
                .putInt(SEX, sex.getSelectedItemPosition())
                .putString(BIRTHDAY, birthday.getText().toString())
                .apply();
    }

    private void readData() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        firstName.setText(prefs.getString(FIRST_NAME, "-"));
        patronymic.setText(prefs.getString(PATRONYMIC, "-"));
        lastName.setText(prefs.getString(LAST_NAME, "-"));
        sex.setSelection(prefs.getInt(SEX, 1));
        birthday.setText(prefs.getString(BIRTHDAY, "-"));
    }

    private void showToast() {
        Toast.makeText(getContext(), "All data changed", Toast.LENGTH_LONG).show();
    }
}
