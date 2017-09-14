package com.example.admin.picker_spinner_autocompletetextview;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
   private TimePicker timepicker;
    private DatePicker datePicker;
    private TextView tv_result;


    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private  String [] languages;

    private AutoCompleteTextView autoComplete;
    private ArrayAdapter<String> autoCompleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timepicker = (TimePicker) findViewById(R.id.timepicker);
        datePicker = (DatePicker) findViewById(R.id.datepicker);
        tv_result = (TextView) findViewById(R.id.tv_result);

        timepicker.setIs24HourView(false);

        timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                tv_result.setText(hourOfDay+" : "+minute);
            }
        });


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                tv_result.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
            }
        });

        //Spinner
        languages = getResources().getStringArray(R.array.language);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,languages);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println("Seleted"+laguages[position]);// cách 1 của thầy
                Toast.makeText(MainActivity.this, "Selected"+languages[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //auto Complete

        autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        autoCompleteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,languages);
        autoComplete.setAdapter(autoCompleteAdapter);

    }



          public  void showDatePicker(View view){
              Calendar calendar = Calendar.getInstance();
              int year = calendar.get(Calendar.YEAR);
              int month = calendar.get(Calendar.MONTH);
              int day = calendar.get(Calendar.DAY_OF_MONTH);
              DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                  tv_result.setText(dayOfMonth+"/"+month+"/"+year);
                  }
              };
              DatePickerDialog dialog = new DatePickerDialog(this,onDateSetListener,year,month,day);
              dialog.show();


          }
           public  void showTimePicker(View view){
           Calendar calender = Calendar.getInstance();
           int hour = calender.get(Calendar.HOUR);
           int min = calender.get(Calendar.MINUTE);

           TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
               @Override
               public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                   tv_result.setText(hourOfDay+" : "+minute);
               }
           };
           TimePickerDialog dialog = new TimePickerDialog(this, onTimeSetListener, hour, min,true);
           dialog.show();
       }

}
