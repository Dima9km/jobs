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

import androidx.fragment.app.Fragment;

import com.dima.jobs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private TextView birthday;
    private EditText firstName;
    private EditText patronymic;
    private EditText lastName;
    private Spinner sex;
    private Button datePicker;
    private Button btnSave;

    private String FIRSTNAME = "first name";
    private String PATRONYMIC = "patronymic";
    private String LASTNAME = "last name";
    private String SEX = "sex";
    private String BIRTHDAY = "birthday";

    private final Calendar pickedDate = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
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
    public void onStart() {
        super.onStart();
        initUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        readData();
    }

    private void initUI() {
        firstName = getView().findViewById(R.id.etFirstName);
        patronymic = getView().findViewById(R.id.etPatronymic);
        lastName = getView().findViewById(R.id.etLastName);
        sex = getView().findViewById(R.id.spSex);
        birthday = getView().findViewById(R.id.tvBirthdayText);

        datePicker = getView().findViewById(R.id.btnDatePicker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btnSave = getView().findViewById(R.id.btnSaveData);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(getContext(), "All data changed", Toast.LENGTH_LONG).show();
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

    private void readData() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        firstName.setText(prefs.getString(FIRSTNAME, ""));
        patronymic.setText(prefs.getString(PATRONYMIC, ""));
        lastName.setText(prefs.getString(LASTNAME, ""));
        sex.setSelection(prefs.getInt(SEX, 0));
        birthday.setText(prefs.getString(BIRTHDAY, ""));
    }

    private void saveData() {
        PreferenceManager.getDefaultSharedPreferences(getContext())
                .edit()
                .putString(FIRSTNAME, firstName.getText().toString())
                .putString(PATRONYMIC, patronymic.getText().toString())
                .putString(LASTNAME, lastName.getText().toString())
                .putInt(SEX, sex.getSelectedItemPosition())
                .putString(BIRTHDAY, birthday.getText().toString())
                .apply();
    }
}
