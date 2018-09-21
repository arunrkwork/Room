package kapp.room.student.activities;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Date;

import kapp.room.student.R;
import kapp.room.student.db.entity.Employee;
import kapp.room.student.db.viewmodel.UIViewModel;
import kapp.room.student.dialogs.DatePickerFragment;

public class AddEmployeeActivity extends AppCompatActivity implements View.OnClickListener{

    private UIViewModel mUIViewModel;
    private Employee mEmployee;

    EditText edFirstName, edLastName, edDate, edAatharNumber, edQualification, edAddress;
    ImageView btnImagDatePicker;
    FloatingActionButton fabAdd;
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edDate = findViewById(R.id.edDate);
        edAatharNumber = findViewById(R.id.edAatharNumber);
        edQualification = findViewById(R.id.edQualification);
        edAddress = findViewById(R.id.edAddress);
        btnImagDatePicker = findViewById(R.id.btnImgDatePicker);
        btnImagDatePicker.setOnClickListener(this);
        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(this);

        mUIViewModel = ViewModelProviders.of(this).get(UIViewModel.class);

    }

    @Override
    public void onClick(View v) {
        if (v == fabAdd) {
            Employee employee = new Employee();
            employee.setEmpFirstName(getValue(edFirstName));
            employee.setEmpLastName(getValue(edLastName));
            employee.setEmpBirthday(new Date());
            employee.setAadharNumber(getValue(edAatharNumber));
            employee.setQualification(getValue(edQualification));
            employee.setEmpBirthday(date);
            employee.setDeptId(1);
            employee.setAddress(getValue(edAddress));
            addEmployee(employee);
        } else if (v == btnImagDatePicker) {
            showDatePicker();
        }
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();

        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);

        date.setCallBack(ondate);
        date.show(getSupportFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date.setTime(calendar.getTimeInMillis());
            edDate.setText(String.valueOf(year) + "-" + s2d(monthOfYear + 1)
                    + "-" + s2d(dayOfMonth));
        }
    };

    private String s2d(int num) {
        return (num < 10 ? "0" : "") + num;
    }


    private String getValue(EditText editText) {
        String string = editText.getText().toString();
        if (TextUtils.isEmpty(string))
            return "";
        else return string;
    }

    private void addEmployee(Employee employee) {
        mUIViewModel.insertEmployee(employee);
    }
}
