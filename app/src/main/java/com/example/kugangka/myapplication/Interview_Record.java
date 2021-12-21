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
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Interview_Record extends AppCompatActivity {
    private String UploadUrl = CertificationForm.UploadURLStatic+"/Android/Interview_record.php";
    private String QR_Manager = CertificationForm.UploadURLStatic+"/Android/QR_Manager.php";
    Spinner Result_1,
           Result_2,Mode_collection;

    Button Proceed, GetTime, GetDate,get_current_time,get_began_time;
    EditText  Day_1,Hour_1,
            Hour_2,
            Month_2,Hour_3,
            Visit_1,Household_num1,Males_num,Females_num,
            Res_1,Res_2,Mode_Col;
    TextView QR;
    String monthName;
    private ProgressDialog pd;
    ImageView back;

    private static final String TAG = "MainActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview__record);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);


        pd = new ProgressDialog(Interview_Record.this);
        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

         QR = findViewById(R.id.QR);
        Intent intent = getIntent();
        String time = intent.getStringExtra("time");
        final String qr_code = intent.getStringExtra("qr_code_number");
        QR.setText(qr_code);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        String Month = month.format(calendar.getTime());
        SimpleDateFormat day = new SimpleDateFormat("dd");
        String Day = day.format(calendar.getTime());
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        String Year = year.format(calendar.getTime());

        back = findViewById(R.id.back);


        Day_1 = findViewById(R.id.Day_1);
        Day_1.setText(Month+"/"+Day+"/"+Year);

        Hour_1 = findViewById(R.id.Hour_1);


        Hour_2 = findViewById(R.id.Hour_2);


        Res_1 = findViewById(R.id.Res_1);
        Result_1 = findViewById(R.id.Result_1);

        Month_2 = findViewById(R.id.Month_2);

        Hour_3 = findViewById(R.id.Hour_3);

        Visit_1 = findViewById(R.id.Visit_1);

        Res_2 = findViewById(R.id.Res_2);
        Result_2 = findViewById(R.id.Result_2);

        Household_num1 = findViewById(R.id.Household_num1);
        Males_num = findViewById(R.id.Males_num);
        Females_num = findViewById(R.id.Females_num);


        Mode_collection = findViewById(R.id.Mode_collection);
        Mode_Col = findViewById(R.id.Mode_Col);

        Proceed = findViewById(R.id.Proceed);
        GetTime = findViewById(R.id.get_time);
        GetDate = findViewById(R.id.get_date);
        get_current_time = findViewById(R.id.get_current_time);
        get_began_time = findViewById(R.id.get_began_time);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SQ = getIntent().getStringExtra("SQ");
                if(SQ.equals("SQ")){
                    Intent intent = new Intent(Interview_Record.this, Survey_Questions.class);
                    intent.putExtra("qr_code_number", qr_code);
                    intent.putExtra("geo_iden_id", geo_iden_id);
                    startActivity(intent);
                    finish();
                }else if(SQ.equals("SF")){

                    try {
                        final String url = CertificationForm.UploadURLStatic+"/Android/Household_GeoIden.php?qr_number=" +qr_code;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONArray jsonarray = new JSONArray(response);
                                            for (int i = 0; i < jsonarray.length(); i++) {
                                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                                String Lname = jsonobject.getString("Lname");
                                                String Fname = jsonobject.getString("Fname");
                                                String QR = qr_code;
                                                int GEO = geo_iden_id;
                                                Intent SurveyForms = new Intent(Interview_Record.this, SurveyForms.class);
                                                SurveyForms.putExtra("Lname", Lname);
                                                SurveyForms.putExtra("Fname", Fname);
                                                SurveyForms.putExtra("qr_code_number", QR);
                                                SurveyForms.putExtra("geo_iden_id", GEO);
                                                Interview_Record.this.startActivity(SurveyForms);
                                                finish();
                                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                            }
                                        } catch (JSONException e) {


                                            int GEO = geo_iden_id;
                                            Intent SurveyForms = new Intent(Interview_Record.this, SurveyForms.class);
                                            SurveyForms.putExtra("qr_code_number", qr_code);
                                            SurveyForms.putExtra("Fname", "No Geographic identification");
                                            SurveyForms.putExtra("Lname", "No Geographic identification");
                                            SurveyForms.putExtra("geo_iden_id", geo_iden_id);
                                            startActivity(SurveyForms);
                                            finish();

                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        if (error != null) {

                                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                        );

                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                    }catch (Exception ex){

                        Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                    }









                }
            }
        });

        get_current_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat Time = new SimpleDateFormat("HH:mm:ss aa");
                String CurrentTime = Time.format(calendar.getTime());
                Hour_2.setText(CurrentTime);

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


        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

pd.show();

                if (Day_1.getText().toString().length() <= 0) { Day_1.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }
                if (Hour_1.getText().toString().length() <= 0) { Hour_1.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }
                if (Hour_2.getText().toString().length() <= 0) { Hour_2.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }
                if (Res_1.getText().toString().length() <= 0) { Res_1.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }
                if (Month_2.getText().toString().length() <= 0) { Month_2.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }

                if (Hour_3.getText().toString().length() <= 0) { Hour_3.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }

                if (Visit_1.getText().toString().length() <= 0) { Visit_1.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }
                if (Res_2.getText().toString().length() <= 0) { Res_2.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }
                if (Household_num1.getText().toString().length() <= 0) { Household_num1.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }
                if (Males_num.getText().toString().length() <= 0) { Males_num.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }
                if (Females_num.getText().toString().length() <= 0) { Females_num.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }
                if (Mode_Col.getText().toString().length() <= 0) { Mode_Col.setError("Please Enter Text!");
                    Toast.makeText(Interview_Record.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }

                else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        qr_manager();
                                        JSONObject jsonObject = new JSONObject(response);

                                        String Response = jsonObject.getString("success");
                                        pd.hide();
                                        Toast.makeText(Interview_Record.this, Response, Toast.LENGTH_LONG).show();

                                        try {
                                            final String url = CertificationForm.UploadURLStatic+"/Android/Household_GeoIden.php?qr_number=" +qr_code;
                                            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                                                    url,
                                                    new Response.Listener<String>() {
                                                        @Override
                                                        public void onResponse(String response) {
                                                            try {
                                                                JSONArray jsonarray = new JSONArray(response);
                                                                for (int i = 0; i < jsonarray.length(); i++) {
                                                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                                                                    String Lname = jsonobject.getString("Lname");
                                                                    String Fname = jsonobject.getString("Fname");
                                                                    String QR = qr_code;
                                                                    int GEO = geo_iden_id;
                                                                    Intent SurveyForms = new Intent(Interview_Record.this, SurveyForms.class);
                                                                    SurveyForms.putExtra("Lname", Lname);
                                                                    SurveyForms.putExtra("Fname", Fname);
                                                                    SurveyForms.putExtra("qr_code_number", QR);
                                                                    SurveyForms.putExtra("geo_iden_id", GEO);


                                                                    Interview_Record.this.startActivity(SurveyForms);
                                                                    //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                                                }
                                                            } catch (JSONException e) {


                                                                int GEO = geo_iden_id;
                                                                Intent SurveyForms = new Intent(Interview_Record.this, SurveyForms.class);
                                                                SurveyForms.putExtra("qr_code_number", qr_code);
                                                                SurveyForms.putExtra("Fname", "No Geographic identification");
                                                                SurveyForms.putExtra("Lname", "No Geographic identification");
                                                                SurveyForms.putExtra("geo_iden_id", geo_iden_id);
                                                                startActivity(SurveyForms);

                                                            }
                                                        }
                                                    },
                                                    new Response.ErrorListener() {
                                                        @Override
                                                        public void onErrorResponse(VolleyError error) {
                                                            if (error != null) {

                                                                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    }
                                            );

                                            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                                        }catch (Exception ex){

                                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                                        }


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

                            String day1 = Day_1.getText().toString();
                            final String Date_1 = day1;


                            String hour1 = Hour_1.getText().toString();

                            final String Time_1 = hour1;

                            String hour2 = Hour_2.getText().toString();
                            final String Time_2 = hour2;

                            final String result1 = Res_1.getText().toString();

                            String month2 = Month_2.getText().toString();

                            final String Date_2 = month2;

                            String hour3 = Hour_3.getText().toString();

                            final String Time_3 = hour3;

                            final String visit1 = Visit_1.getText().toString();

                            final String result2 = Res_2.getText().toString();

                            String household_num1 = Household_num1.getText().toString();
                            String males_num1 = Males_num.getText().toString();
                            String females_num1 = Females_num.getText().toString();
                            final String mode_collection = Mode_Col.getText().toString();

                            final String QR_Code = QR.getText().toString();



                            params.put("enumerator_id", String.valueOf(val1));
                            params.put("geo_iden_id", String.valueOf(GEO));
                            params.put("date_visit", Date_1);
                            params.put("time_began", Time_1);
                            params.put("time_end", Time_2);

                            params.put("result_visit", result1);
                            params.put("date_visit2", Date_2);
                            params.put("time_visit", Time_3);

                            params.put("num_of_visit", visit1);
                            params.put("result_of_visit", result2);
                            params.put("num_of_household_mem", household_num1);

                            params.put("num_of_males", males_num1);
                            params.put("num_of_females", females_num1);
                            params.put("mode_of_date_collection", mode_collection);

                            params.put("qr_number", QR_Code);


                            return params;
                        }
                    };
                    MySingleton.getInstance(Interview_Record.this).addToRequestQueue(stringRequest);
                }

            }
        });

        GetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                final int minute = mcurrentTime.get(Calendar.MINUTE);
                final int second = mcurrentTime.get(Calendar.SECOND);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Interview_Record.this, new TimePickerDialog.OnTimeSetListener() {
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
        GetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Interview_Record.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });








        get_began_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                final int minute = mcurrentTime.get(Calendar.MINUTE);
                final int second = mcurrentTime.get(Calendar.SECOND);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Interview_Record.this, new TimePickerDialog.OnTimeSetListener() {
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
        GetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Interview_Record.this,
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
    }



    public void qr_manager(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, QR_Manager,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            String Response = jsonObject.getString("success");
                            Toast.makeText(Interview_Record.this, Response, Toast.LENGTH_LONG).show();






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();


                String QR_num = QR.getText().toString();


                params.put("qr_code_number", QR_num);


                return params;
            }
        };
        MySingleton.getInstance(Interview_Record.this).addToRequestQueue(stringRequest);


    }
}
