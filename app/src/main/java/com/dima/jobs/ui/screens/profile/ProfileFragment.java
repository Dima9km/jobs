package com.dima.jobs.ui.screens.profile;

import android.app.DatePickerDialog;
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
import com.dima.jobs.data.model.UserProfile;
import com.dima.jobs.data.repository.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private TextView birthday;
    private EditText firstName;
    private EditText patronymic;
    private EditText lastName;
    private Spinner sex;
    private Spinner userLocation;

    private UserProfile userProfile;
    private Repository repository;

    private final Calendar pickedDate = Calendar.getInstance();
    private final DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            pickedDate.set(Calendar.YEAR, year);
            pickedDate.set(Calendar.MONTH, monthOfYear);
            pickedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            birthday.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(pickedDate.getTime()));
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = new Repository();
        repository.setPreferences(PreferenceManager.getDefaultSharedPreferences(getContext()));
        userProfile = repository.getProfile();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        userProfile = repository.getProfile();
        fillUpUI();
    }

    private void initUI() {
        firstName = getView().findViewById(R.id.etFirstName);
        patronymic = getView().findViewById(R.id.etPatronymic);
        lastName = getView().findViewById(R.id.etLastName);
        sex = getView().findViewById(R.id.spSex);
        userLocation = getView().findViewById(R.id.spLocation);
        birthday = getView().findViewById(R.id.tvBirthdayText);

        Button datePicker = getView().findViewById(R.id.btnDatePicker);
        datePicker.setOnClickListener(v -> showDatePicker());

        Button btnSave = getView().findViewById(R.id.btnSaveData);
        btnSave.setOnClickListener(v -> {
            updateProfile();
            repository.updateProfile(userProfile);
            Toast.makeText(getContext(), "All data changed", Toast.LENGTH_LONG).show();
        });
    }

    private void showDatePicker() {
        new DatePickerDialog(getContext(), onDateSetListener,
                pickedDate.get(Calendar.YEAR),
                pickedDate.get(Calendar.MONTH),
                pickedDate.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void fillUpUI() {
        firstName.setText(userProfile.getFirstName());
        patronymic.setText(userProfile.getPatronymic());
        lastName.setText(userProfile.getLastName());
        sex.setSelection(userProfile.getSex());
        userLocation.setSelection(userProfile.getUserLocation());
        birthday.setText(userProfile.getBirthday());
    }

    private void updateProfile() {
        userProfile.setFirstName(firstName.getText().toString());
        userProfile.setPatronymic(patronymic.getText().toString());
        userProfile.setLastName(lastName.getText().toString());
        userProfile.setSex(sex.getSelectedItemPosition());
        userProfile.setUserLocation(userLocation.getSelectedItemPosition());
        userProfile.setBirthday(birthday.getText().toString());
    }
}
