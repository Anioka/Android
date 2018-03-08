package com.lmb_europa.campscouttest;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class DatePicker extends Activity implements View.OnClickListener {

    Button btnStartDatePicker, btnEndDatePicker, btnConfirm, btnShowOnMap;
    EditText txtStartDate, txtEndDate;
    private int mYear, mMonth, mDay;

    private int stDay, stMonth, stYear, enDay, enMonth, enYear;

    String sDates, eDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        btnConfirm = (Button)findViewById(R.id.btn_confirm);
        btnShowOnMap = (Button)findViewById(R.id.btn_showOnMap);
        btnStartDatePicker=(Button)findViewById(R.id.btn_start_date);
        btnEndDatePicker=(Button)findViewById(R.id.btn_end_date);
        txtStartDate=(EditText)findViewById(R.id.in_date);
        txtEndDate=(EditText)findViewById(R.id.in_time);

        txtStartDate.setFocusable(false);
        txtEndDate.setFocusable(false);

        btnConfirm.setOnClickListener(this);
        btnShowOnMap.setOnClickListener(this);
        btnStartDatePicker.setOnClickListener(this);
        btnEndDatePicker.setOnClickListener(this);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Wrong date format!")
                .setMessage("It seams like you have selected a wrong date format!.\nPlease change your end date date")
                .setPositiveButton("Go back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    @Override
    public void onClick(View v) {

        if (v == btnStartDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            stDay = mDay;
            stMonth = mMonth;
            stYear = mYear;

            DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(android.widget.DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtStartDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            sDates = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog1.show();
        }
        if (v == btnEndDatePicker) {

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            enDay = mDay;
            enMonth = mMonth;
            enYear = mYear;

            DatePickerDialog datePickerDialog2 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(android.widget.DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                             /*if (enYear >= stYear) {
                                 if ((enMonth > stMonth) || (enMonth == stMonth && enDay > stDay)) {*/

                            txtEndDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year); //predomislila sam se, ovo nesto jebe klasu da radi kompletno
                            eDates = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                                 /*}
                             }
                            else {
                                txtEndDate.getText().clear(); //ideja je ako se zajebe unos da se EditText ocisti i da korisnik ponovo unese datum
                                showAlert();
                            }*///ne radi
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog2.show();
        }
        if (v == btnShowOnMap)
        {
            /*Intent i = new Intent(DatePicker.this, AvailableSpotsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("startDate", sDates);
            bundle.putString("endDate", eDates);
            i.putExtras(bundle);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();*/
        }
        if (v == btnConfirm)
        {
            Intent i = new Intent(DatePicker.this, SpotsList.class);
            Bundle bundle = new Bundle();
            bundle.putString("startDate", sDates);
            bundle.putString("endDate", eDates);
            i.putExtras(bundle);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}
