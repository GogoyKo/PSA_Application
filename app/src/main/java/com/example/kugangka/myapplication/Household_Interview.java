package com.example.kugangka.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Household_Interview extends AppCompatActivity {

    EditText Day_1,Hour_1,
            Hour_2,
            Month_2,Hour_3,
            Visit_1,Household_num1,Males_num,Females_num,
            Res_1,Res_2,Mode_Col;
    Button get_current_time,Getdate_btn,Begandate_btn,get_time,get_date;
    Button update_btn;

    String monthName;
    private ProgressDialog pd;
    TextView current_1,current_2,current_3;

    Spinner Result_1, Result_2, Mode_collection;

    private static final String TAG = "MainActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household__interview);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }


        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        pd = new ProgressDialog(Household_Interview.this);
        pd.setMessage("loading");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(true);

        final int ID = getIntent().getIntExtra("ID", 0);
        Result_1 = findViewById(R.id.Result_1);
                Result_2 = findViewById(R.id.Result_2);
        Mode_collection = findViewById(R.id.Mode_collection);

        Day_1 = findViewById(R.id.Day_1);
        Hour_1 = findViewById(R.id.Hour_1);
        Hour_2 = findViewById(R.id.Hour_2);
        Month_2 = findViewById(R.id.Month_2);
        Hour_3 = findViewById(R.id.Hour_3);
        Visit_1 = findViewById(R.id.Visit_1);
        Household_num1 = findViewById(R.id.Household_num1);
        Males_num = findViewById(R.id.Males_num);
        Females_num = findViewById(R.id.Females_num);
        Res_1 = findViewById(R.id.Res_1);
        Res_2 = findViewById(R.id.Res_2);
        Mode_Col = findViewById(R.id.Mode_Col);

        current_1 = findViewById(R.id.current_1);
        current_2 = findViewById(R.id.current_2);
        current_3 = findViewById(R.id.current_3);

        get_current_time = findViewById(R.id.get_current_time);
        Getdate_btn = findViewById(R.id.Getdate_btn);
        Begandate_btn = findViewById(R.id.Begandate_btn);
        get_time = findViewById(R.id.get_time);
        get_date = findViewById(R.id.get_date);

        update_btn = findViewById(R.id.update_btn);


        String Value1 = getIntent().getStringExtra("date_visit");
        String Value2 = getIntent().getStringExtra("time_began");
        String Value3 = getIntent().getStringExtra("time_end");
        String Value4 = getIntent().getStringExtra("result_visit");

        String Value5 = getIntent().getStringExtra("date_visit2");
        String Value6 = getIntent().getStringExtra("time_visit");
        String Value7 = getIntent().getStringExtra("num_of_visit");
        String Value8 = getIntent().getStringExtra("result_of_visit");

        String Value9 = getIntent().getStringExtra("num_of_household_mem");
        String Value10 = getIntent().getStringExtra("num_of_males");
        String Value11= getIntent().getStringExtra("num_of_females");
        String Value12 = getIntent().getStringExtra("mode_of_date_collection");
        final String Value13 = getIntent().getStringExtra("qr_code_number");


        Day_1.setText(Value1);
        Hour_1.setText(Value2);
        Hour_2.setText(Value3);
        current_1.setText(Value4);
        Month_2.setText(Value5);
        Hour_3.setText(Value6);
        Visit_1.setText(Value7);
        current_2.setText(Value8);

        Household_num1.setText(Value9);
        Males_num.setText(Value10);
        Females_num.setText(Value11);
        current_3.setText(Value12);

        get_current_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat Time = new SimpleDateFormat("HH:mm:ss aa");
                String CurrentTime = Time.format(calendar.getTime());
                Hour_2.setText(CurrentTime);

            }
        });

        Getdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Household_Interview.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);


                if(month == 1){
                    monthName = "Jan";
                }
                if(month == 2){
                    monthName = "Feb";
                }
                if(month == 3){
                    monthName = "Mar";
                }
                if(month == 4){
                    monthName = "Apr";
                }
                if(month == 5){
                    monthName = "May";
                }
                if(month == 6){
                    monthName = "Jun";
                }
                if(month == 7){
                    monthName = "Jul";
                }
                if(month == 8){
                    monthName = "Aug";
                }
                if(month == 9){
                    monthName = "Sep";
                }
                if(month == 10){
                    monthName = "Nov";
                }
                if(month == 11){
                    monthName = "Dec";
                }
                if(month == 12){
                    monthName = "Dec";
                }

                String date = monthName + "/" + day + "/" + year;

                Day_1.setText(date);

            }
        };





        Begandate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                final int minute = mcurrentTime.get(Calendar.MINUTE);
                final int second = mcurrentTime.get(Calendar.SECOND);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Household_Interview.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String format;
                        if (selectedHour == 0) {
                            selectedHour += 12;
                            format = "AM";
                        }
                        else if (selectedHour == 12) {

                            format = "PM";
                        }
                        else if (selectedHour > 12) {

                            selectedHour -= 12;

                            format = "PM";
                        }
                        else {

                            format = "AM";
                        }
                        Hour_1.setText(selectedHour + ":" + minute + ":"+second+" "+format);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        get_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Household_Interview.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);


                if(month == 1){
                    monthName = "Jan";
                }
                if(month == 2){
                    monthName = "Feb";
                }
                if(month == 3){
                    monthName = "Mar";
                }
                if(month == 4){
                    monthName = "Apr";
                }
                if(month == 5){
                    monthName = "May";
                }
                if(month == 6){
                    monthName = "Jun";
                }
                if(month == 7){
                    monthName = "Jul";
                }
                if(month == 8){
                    monthName = "Aug";
                }
                if(month == 9){
                    monthName = "Sep";
                }
                if(month == 10){
                    monthName = "Nov";
                }
                if(month == 11){
                    monthName = "Dec";
                }
                if(month == 12){
                    monthName = "Dec";
                }

                String date = monthName + "/" + day + "/" + year;

                Month_2.setText(date);

            }
        };

        get_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                final int minute = mcurrentTime.get(Calendar.MINUTE);
                final int second = mcurrentTime.get(Calendar.SECOND);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Household_Interview.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String format;
                        if (selectedHour == 0) {
                            selectedHour += 12;
                            format = "AM";
                        }
                        else if (selectedHour == 12) {

                            format = "PM";
                        }
                        else if (selectedHour > 12) {

                            selectedHour -= 12;

                            format = "PM";
                        }
                        else {

                            format = "AM";
                        }
                        Hour_3.setText(selectedHour + ":" + minute + ":"+second+" "+format);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        Result_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String msupplier=Result_1.getSelectedItem().toString();

                Res_1.setText(msupplier);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        Result_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String msupplier=Result_2.getSelectedItem().toString();

                Res_2.setText(msupplier);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        Mode_collection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String msupplier=Mode_collection.getSelectedItem().toString();

                Mode_Col.setText(msupplier);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
pd.show();

                if (Day_1.getText().toString().length() <= 0 || Hour_1.getText().toString().length() <= 0
                        || Hour_2.getText().toString().length() <= 0 || Res_1.getText().toString().length() <= 0
                        || Month_2.getText().toString().length() <= 0 || Hour_3.getText().toString().length() <= 0
                        || Visit_1.getText().toString().length() <= 0|| Res_2.getText().toString().length() <= 0
                        || Household_num1.getText().toString().length() <= 0|| Males_num.getText().toString().length() <= 0
                        || Females_num.getText().toString().length() <= 0|| Mode_Col.getText().toString().length() <= 0) {

                    Toast.makeText(getApplicationContext(), "Empty  Fields", Toast.LENGTH_LONG).show();
                } else {

                    String UploadUrl = CertificationForm.UploadURLStatic+"/Android/Household_Interview_Update.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);

                                        String Response = jsonObject.getString("success");
                                        pd.show();
                                        Toast.makeText(Household_Interview.this, Response, Toast.LENGTH_LONG).show();
                                        finish();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<>();



                            final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

                            final int val1 = sharedPreferences.getInt("enumerator_id", 0);

                            int GEO = geo_iden_id;
                            int id = ID;
                            String value1 = Day_1.getText().toString();
                            String value2 = Hour_1.getText().toString();
                            String value3 = Hour_2.getText().toString();
                            String value4 = Res_1.getText().toString();
                            String value5 = Month_2.getText().toString();
                            String value6 = Hour_3.getText().toString();
                            String value7 = Visit_1.getText().toString();
                            String value8 = Res_2.getText().toString();
                            String value9 = Household_num1.getText().toString();
                            String value10 = Males_num.getText().toString();
                            String value11 = Females_num.getText().toString();
                            String value12 = Mode_Col.getText().toString();
                            String value13 =Value13;


                            params.put("enumerator_id", String.valueOf(val1));
                            params.put("geo_iden_id", String.valueOf(GEO));
                            params.put("interview_rec_id", String.valueOf(id));
                            params.put("date_visit", value1);
                            params.put("time_began", value2);
                            params.put("time_end", value3);
                            params.put("result_visit", value4);
                            params.put("date_visit2", value5);
                            params.put("time_visit", value6);
                            params.put("num_of_visit", value7);
                            params.put("result_of_visit", value8);
                            params.put("num_of_household_mem", value9);
                            params.put("num_of_males", value10);
                            params.put("num_of_females", value11);
                            params.put("mode_of_date_collection", value12);
                            params.put("qr_number", value13);



                            return params;
                        }
                    };
                    finish();

                    MySingleton.getInstance(Household_Interview.this).addToRequestQueue(stringRequest);
                }
            }
        });

    }
}
