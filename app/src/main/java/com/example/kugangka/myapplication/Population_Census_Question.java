package com.example.kugangka.myapplication;


import android.animation.LayoutTransition;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.analytics.ecommerce.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Population_Census_Question extends AppCompatActivity {
    private String UploadUrl = CertificationForm.UploadURLStatic+"/Android/population_cen.php";
    private String QR_Manager = CertificationForm.UploadURLStatic+"/Android/QR_Manager.php";

    Button Proceed;
    TextView Month_P4,QR;
    EditText Lastname, Firstname, P5, P9, P14, P16;


    // Todo Spinner with editText above
    EditText edit_p2,edit_p3,edit_p6,edit_p7,edit_p8,edit_p10,edit_p11,edit_p12,edit_p13,edit_p15;

    Spinner P2,P3, P6, P7, P8, P10, P11, P12, P13, P15;
    String qr_code;
    private ProgressDialog pd;
    ImageView back;
    String monthName;
//test
    LinearLayout container;

    private static final String TAG = "MainActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_population_census_question);
//test
        container = (LinearLayout)findViewById(R.id.container);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }


        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        pd = new ProgressDialog(Population_Census_Question.this);
        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

      QR = findViewById(R.id.QR);
        Intent intent = getIntent();
        qr_code = intent.getStringExtra("qr_code_number");
        QR.setText(qr_code);

        back = findViewById(R.id.back);


        Lastname = findViewById(R.id.lastname);
        Firstname = findViewById(R.id.firstname);
        P5 = findViewById(R.id.p5);
        P9 = findViewById(R.id.p9);
        P14 = findViewById(R.id.p14);
        P16 = findViewById(R.id.p16);

        P2 = findViewById(R.id.p2);
        P3 = findViewById(R.id.p3);
        Month_P4 = findViewById(R.id.month_p4);

        P6 = findViewById(R.id.p6);
        P7 = findViewById(R.id.p7);
        P8 = findViewById(R.id.p8);
        P10 = findViewById(R.id.p10);
        P11 = findViewById(R.id.p11);
        P12 = findViewById(R.id.p12);
        P13 = findViewById(R.id.p13);
        P15 = findViewById(R.id.p15);


        edit_p2 = findViewById(R.id.edit_p2);
        edit_p3 = findViewById(R.id.edit_p3);
        edit_p6 = findViewById(R.id.edit_p6);
        edit_p7 = findViewById(R.id.edit_p7);
        edit_p8 = findViewById(R.id.edit_p8);
        edit_p10 = findViewById(R.id.edit_p10);
        edit_p11 = findViewById(R.id.edit_p11);
        edit_p12 = findViewById(R.id.edit_p12);
        edit_p13 = findViewById(R.id.edit_p13);
        edit_p15 = findViewById(R.id.edit_p15);


        Proceed = findViewById(R.id.Proceed);

       Button Add = findViewById(R.id.Add);

       final Button Date = findViewById(R.id.p4_date);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SQ = getIntent().getStringExtra("SQ");
                int GEO = geo_iden_id;
                if(SQ.equals("SQ")){
                    Intent intent = new Intent(Population_Census_Question.this, Survey_Questions.class);
                    intent.putExtra("qr_code_number", qr_code);
                    intent.putExtra("geo_iden_id", GEO);
                    startActivity(intent);
                    finish();
                }else if(SQ.equals("SF")){

                    try {
                        final String Qr_num = qr_code;
                        final String url = CertificationForm.UploadURLStatic+"/Android/Household_GeoIden.php?qr_number=" +Qr_num;
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
                                                String QR_number = Qr_num;
                                                int GEO = geo_iden_id;
                                                Intent SurveyForms = new Intent(Population_Census_Question.this, SurveyForms.class);
                                                SurveyForms.putExtra("Lname", Lname);
                                                SurveyForms.putExtra("Fname", Fname);
                                                SurveyForms.putExtra("qr_code_number", QR_number);
                                                SurveyForms.putExtra("geo_iden_id", GEO);
                                                Population_Census_Question.this.startActivity(SurveyForms);
                                                finish();
                                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                            }
                                        } catch (JSONException e) {


                                            int GEO = geo_iden_id;
                                            Intent SurveyForms = new Intent(Population_Census_Question.this, SurveyForms.class);
                                            SurveyForms.putExtra("qr_code_number", qr_code);
                                            SurveyForms.putExtra("Fname", "aldkalsdklasdk");
                                            SurveyForms.putExtra("Lname", "No Geographic identification");
                                            SurveyForms.putExtra("geo_iden_id", GEO);
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

        P2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P2.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p2);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        P3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P3.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p3);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        P6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P6.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p6);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        P7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P7.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p7);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        P8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P8.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p8);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        P10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P10.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p10);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        P11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P11.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p11);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        P12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P12.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p12);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        P13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P13.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p13);
                edit.setEnabled(false);
                edit.setText(val1);



                if(edit.getText().toString().equals("") || edit.getText().toString().equals("Yes")){
                    EditText edit1 = findViewById(R.id.p14);
                    edit1.setText("");
                    edit1.setEnabled(true);
                }
                else{
                    EditText edit1 = findViewById(R.id.p14);
                    edit1.setText("No, SKIP TO P15");
                    edit1.setEnabled(false);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        P15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=P15.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_p15);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        // Todo Get the Date
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Population_Census_Question.this,
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
                Month_P4.setText(date);

            }
        };


        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                                            Intent SurveyForms = new Intent(Population_Census_Question.this, SurveyForms.class);
                                            SurveyForms.putExtra("Lname", Lname);
                                            SurveyForms.putExtra("Fname", Fname);
                                            SurveyForms.putExtra("qr_code_number", QR);
                                            SurveyForms.putExtra("geo_iden_id", GEO);


                                            Population_Census_Question.this.startActivity(SurveyForms);
                                            //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                        }
                                    } catch (JSONException e) {


                                        int GEO = geo_iden_id;
                                        Intent SurveyForms = new Intent(Population_Census_Question.this, SurveyForms.class);
                                        SurveyForms.putExtra("qr_code_number", qr_code);
                                        SurveyForms.putExtra("Fname", "No Geographic identification");
                                        SurveyForms.putExtra("Lname", "No Geographic identification");
                                        SurveyForms.putExtra("geo_iden_id", GEO);

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
            }
        });

        //Todo To add Family Member
        Add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {


                // Todo Validate All Fields if Empty or not
                if(Lastname.getText().toString().length() <= 0 || Firstname.getText().toString().length() <= 0 ||
                        P5.getText().toString().length() <= 0 || P9.getText().toString().length() <= 0 ||
                        P14.getText().toString().length() <= 0 || P16.getText().toString().length() <= 0 ||
                        edit_p2.getText().toString().length() <= 0 || edit_p3.getText().toString().length() <= 0 ||
                        Month_P4.getText().toString().length() <= 0 || edit_p6.getText().toString().length() <= 0 ||
                        edit_p7.getText().toString().length() <= 0 || edit_p8.getText().toString().length() <= 0 ||
                        edit_p10.getText().toString().length() <= 0 ||edit_p11.getText().toString().length() <= 0 ||
                        edit_p12.getText().toString().length() <= 0 || edit_p13.getText().toString().length() <= 0 ||
                        edit_p15.getText().toString().length() <= 0){
                    Toast.makeText(Population_Census_Question.this,"Empty field",Toast.LENGTH_SHORT).show();
                }

               // Todo Validate Each Fields if Empty or not
                if(Lastname.getText().toString().length() <= 0){ Lastname.setError("Please enter text!"); }
               else if(Firstname.getText().toString().length() <= 0){ Firstname.setError("Please enter text!"); }
                else  if(P5.getText().toString().length() <= 0){ P5.setError("Please enter text!"); }
                else if(P9.getText().toString().length() <= 0){ P9.setError("Please enter text!"); }
                else if(P14.getText().toString().length() <= 0){ P14.setError("Please enter text!"); }
                else if(P16.getText().toString().length() <= 0){ P16.setError("Please enter text!"); }
                else if(edit_p2.getText().toString().length() <= 0){ edit_p2.setError("Please select item below"); }
                else if(edit_p3.getText().toString().length() <= 0){ edit_p3.setError("Please select item below"); }
                else if(Month_P4.getText().toString().length() <= 0){ Month_P4.setError("Please enter date"); }
                else if(edit_p6.getText().toString().length() <= 0){ edit_p6.setError("Please select item below"); }
                else if(edit_p7.getText().toString().length() <= 0){ edit_p7.setError("Please select item below"); }
                else if(edit_p8.getText().toString().length() <= 0){ edit_p8.setError("Please select item below"); }
                else if(edit_p10.getText().toString().length() <= 0){ edit_p10.setError("Please select item below"); }
                else if(edit_p11.getText().toString().length() <= 0){ edit_p11.setError("Please select item below"); }
                else if(edit_p12.getText().toString().length() <= 0){ edit_p12.setError("Please select item below"); }
                else if(edit_p13.getText().toString().length() <= 0){ edit_p13.setError("Please select item below"); }
                else if(edit_p15.getText().toString().length() <= 0){ edit_p15.setError("Please select item below"); }


         else {
                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View addView = layoutInflater.inflate(R.layout.population_field, null);

                    // Todo Declare Items
                    final EditText lastname = addView.findViewById(R.id.lastname);
                    final EditText firstname = addView.findViewById(R.id.firstname);
                    final EditText p2 = addView.findViewById(R.id.p2);
                    final EditText p3 = addView.findViewById(R.id.p3);
                    final EditText month_P4 = addView.findViewById(R.id.month_P4);
                    final EditText p5 = addView.findViewById(R.id.p5);
                    final EditText p6 = addView.findViewById(R.id.p6);
                    final EditText p7 = addView.findViewById(R.id.p7);
                    final EditText p8 = addView.findViewById(R.id.p8);
                    final EditText p9 = addView.findViewById(R.id.p9);
                    final EditText p10 = addView.findViewById(R.id.p10);
                    final EditText p11 = addView.findViewById(R.id.p11);
                    final EditText p12 = addView.findViewById(R.id.p12);
                    final EditText p13 = addView.findViewById(R.id.p13);
                    final EditText p14 = addView.findViewById(R.id.p14);
                    final EditText p15 = addView.findViewById(R.id.p15);
                    final EditText p16 = addView.findViewById(R.id.p16);


                    // Todo Get Text Value
                    lastname.setText(Lastname.getText().toString());
                    firstname.setText(Firstname.getText().toString());
                    p2.setText(edit_p2.getText().toString());
                    p3.setText(edit_p3.getText().toString());
                    month_P4.setText(Month_P4.getText().toString());
                    p5.setText(P5.getText().toString());
                    p6.setText(edit_p6.getText().toString());
                    p7.setText(edit_p7.getText().toString());
                    p8.setText(edit_p8.getText().toString());
                    p9.setText(P9.getText().toString());
                    p10.setText(edit_p10.getText().toString());
                    p11.setText(edit_p11.getText().toString());
                    p12.setText(edit_p12.getText().toString());
                    p13.setText(edit_p13.getText().toString());
                    p14.setText(P14.getText().toString());
                    p15.setText(edit_p15.getText().toString());
                    p16.setText(P16.getText().toString());


                    // Todo Set EditText to blank
                    Lastname.setText(null);
                    Firstname.setText(null);
                    P5.setText(null);
                    P9.setText(null);
                    P14.setText(null);
                    P16.setText(null);
                    Month_P4.setText(null);


                    edit_p2.setText(null);
                     edit_p3.setText(null);
                     edit_p6.setText(null);
                     edit_p7.setText(null);
                     edit_p8.setText(null);
                     edit_p10.setText(null);
                     edit_p11.setText(null);
                     edit_p12.setText(null);
                     edit_p13.setText(null);
                     edit_p15.setText(null);

                    // Todo Set Spinner to blank
                    P2.setSelection(0, true);
                    P3.setSelection(0, true);
                    P6.setSelection(0, true);
                    P7.setSelection(0, true);
                    P8.setSelection(0, true);
                    P10.setSelection(0, true);
                    P11.setSelection(0, true);
                    P12.setSelection(0, true);
                    P13.setSelection(0, true);
                    P15.setSelection(0, true);

                    // Todo Create Remove Button
                    final Button buttonRemove = (Button) addView.findViewById(R.id.remove_btn);
                    buttonRemove.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            ((LinearLayout) addView.getParent()).removeView(addView);
                        }
                    });

                    // Todo Create Edit Button
                    final Button buttonEdit = (Button) addView.findViewById(R.id.edit_btn);
                    buttonEdit.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            Lastname.setText(lastname.getText().toString());
                            Firstname.setText(firstname.getText().toString());
                            P5.setText(p5.getText().toString());
                            P9.setText(p9.getText().toString());
                            P14.setText(p14.getText().toString());
                            P16.setText(p16.getText().toString());
                            Month_P4.setText(month_P4.getText().toString());
                            edit_p2.setText(p2.getText().toString());
                            edit_p3.setText(p3.getText().toString());
                            edit_p6.setText(p6.getText().toString());
                            edit_p7.setText(p7.getText().toString());
                            edit_p8.setText(p8.getText().toString());
                            edit_p10.setText(p10.getText().toString());
                            edit_p11.setText(p11.getText().toString());
                            edit_p12.setText(p12.getText().toString());
                            edit_p13.setText(p13.getText().toString());
                            edit_p15.setText(p15.getText().toString());

                            ((LinearLayout) addView.getParent()).removeView(addView);

                        }
                    });

                    // Todo Create Save Button
                    final Button buttonSave = (Button) addView.findViewById(R.id.save_btn);
                    buttonSave.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
pd.show();

                            // Todo Disable Buttons
                            buttonRemove.setEnabled(false);
                            buttonSave.setEnabled(false);
                            buttonEdit.setEnabled(false);

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                qr_manager();
                                                JSONObject jsonObject = new JSONObject(response);
                                                String Response = jsonObject.getString("success");
                                                pd.hide();
                                                Toast.makeText(Population_Census_Question.this, Response, Toast.LENGTH_LONG).show();

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

                                    // Todo Get Text Value
                                    EditText lastname = addView.findViewById(R.id.lastname);
                                    EditText firstname = addView.findViewById(R.id.firstname);
                                    EditText p2 = addView.findViewById(R.id.p2);
                                    EditText p3 = addView.findViewById(R.id.p3);
                                    EditText month_p4 = addView.findViewById(R.id.month_P4);
                                    EditText p5 = addView.findViewById(R.id.p5);
                                    EditText p6 = addView.findViewById(R.id.p6);
                                    EditText p7 = addView.findViewById(R.id.p7);
                                    EditText p8 = addView.findViewById(R.id.p8);
                                    EditText p9 = addView.findViewById(R.id.p9);
                                    EditText p10 = addView.findViewById(R.id.p10);
                                    EditText p11 = addView.findViewById(R.id.p11);
                                    EditText p12 = addView.findViewById(R.id.p12);
                                    EditText p13 = addView.findViewById(R.id.p13);
                                    EditText p14 = addView.findViewById(R.id.p14);
                                    EditText p15 = addView.findViewById(R.id.p15);
                                    EditText p16 = addView.findViewById(R.id.p16);
                                    String QR_Code = QR.getText().toString();


                                    // Todo Send to Web as JSON format

                                    params.put("enumerator_id", String.valueOf(val1));
                                    params.put("geo_iden_id", String.valueOf(GEO));
                                    params.put("lastname_p1", lastname.getText().toString());
                                    params.put("firstname_p1", firstname.getText().toString());
                                    params.put("p2", p2.getText().toString());
                                    params.put("p3", p3.getText().toString());
                                    params.put("month_p4", month_p4.getText().toString());
                                    params.put("p5", p5.getText().toString());
                                    params.put("p6", p6.getText().toString());
                                    params.put("p7", p7.getText().toString());
                                    params.put("p8", p8.getText().toString());
                                    params.put("p9", p9.getText().toString());
                                    params.put("p10", p10.getText().toString());
                                    params.put("p11", p11.getText().toString());
                                    params.put("p12", p12.getText().toString());
                                    params.put("p13", p13.getText().toString());
                                    params.put("p14", p14.getText().toString());
                                    params.put("p15", p15.getText().toString());
                                    params.put("p16", p16.getText().toString());
                                    params.put("qr_number", QR_Code);


                                    return params;

                                }
                            };
                            MySingleton.getInstance(Population_Census_Question.this).addToRequestQueue(stringRequest);

                        }

                    });




                    container.addView(addView, 0);
                }
            }});

        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);


    }

    public void qr_manager(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, QR_Manager,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            String Response = jsonObject.getString("success");
                            Toast.makeText(Population_Census_Question.this, Response, Toast.LENGTH_LONG).show();

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
        MySingleton.getInstance(Population_Census_Question.this).addToRequestQueue(stringRequest);


    }


    public void Proceed(){



    }


}
