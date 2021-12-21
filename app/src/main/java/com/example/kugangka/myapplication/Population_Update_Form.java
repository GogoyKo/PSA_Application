package com.example.kugangka.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Population_Update_Form extends AppCompatActivity {

    int pop_id;
    int person_num;

    String p1;
    String p_1 ;
    String p2;
    String p3;
    String month_p4;
    String p5;
    String p6;
    String p7;
    String p8;
    String p9;
    String p10;
    String p11;
    String p12 ;
    String p13 ;
    String p14;
    String p15;
    String p16;
    String qr_number;

    TextView current_1,current_2,current_3,current_4,current_5,current_6,current_7,current_8,
            current_9,current_10,current_11,current_12,current_13,current_14,current_15;
    Spinner P2, P3,P6, P7, P8, P10, P11, P12, P13, P15;
    private ProgressDialog pd;
    String monthName;
    private static final String TAG = "MainActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_population_update_form);



        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int Val1 = sharedPreferences.getInt("enumerator_id", 0);

        pd = new ProgressDialog(Population_Update_Form.this);
        pd.setMessage("loading");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(true);

        final EditText val0 = findViewById(R.id.lastname);
        final EditText val1 = findViewById(R.id.firstname);
        final EditText val2 = findViewById(R.id.edit_p2);
        final EditText val3 = findViewById(R.id.edit_p3);
        final TextView val4 = findViewById(R.id.month_p4);
        final EditText val5 = findViewById(R.id.p5);
        final EditText val6 = findViewById(R.id.edit_p6);
        final EditText val7 = findViewById(R.id.edit_p7);
        final EditText val8 = findViewById(R.id.edit_p8);
        final EditText val9 = findViewById(R.id.p9);
        final EditText val10 = findViewById(R.id.edit_p10);
        final EditText val11 = findViewById(R.id.edit_p11);
        final EditText val12 = findViewById(R.id.edit_p12);
        final EditText val13 = findViewById(R.id.edit_p13);
        final EditText val14 = findViewById(R.id.p14);
        final EditText val15 = findViewById(R.id.edit_p15);
        final EditText val16 = findViewById(R.id.p16);
        final TextView val17 = findViewById(R.id.QR);

        current_1 = findViewById(R.id.current_1);
        current_2 = findViewById(R.id.current_2);
        current_3 = findViewById(R.id.current_3);
        current_4 = findViewById(R.id.current_4);
        current_5 = findViewById(R.id.current_5);
        current_6 = findViewById(R.id.current_6);
        current_7 = findViewById(R.id.current_7);
        current_8 = findViewById(R.id.current_8);
        current_9 = findViewById(R.id.current_9);
        current_10 = findViewById(R.id.current_10);
        current_11 = findViewById(R.id.current_11);
        current_12 = findViewById(R.id.current_12);
        current_13 = findViewById(R.id.current_13);
        current_14 = findViewById(R.id.current_14);
        current_15 = findViewById(R.id.current_15);

        P2 = findViewById(R.id.p2);
        P3 = findViewById(R.id.p3);
        P6 = findViewById(R.id.p6);
        P7 = findViewById(R.id.p7);
        P8 = findViewById(R.id.p8);
        P10 = findViewById(R.id.p10);
        P11 = findViewById(R.id.p11);
        P12 = findViewById(R.id.p12);
        P13 = findViewById(R.id.p13);
        P15 = findViewById(R.id.p15);


        String QR_Code = getIntent().getStringExtra("ID");

        Button date_btn = findViewById(R.id.p4_date);
        Button update_btn = findViewById(R.id.update_btn);
        Button cancel_btn = findViewById(R.id.cancel_btn);


        final String url= CertificationForm.UploadURLStatic+"/Android/Household_ID.php?popu_cen_ques_id="+QR_Code;


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



        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {


                                JSONObject jsonobject = jsonarray.getJSONObject(i);


                                pop_id = jsonobject.getInt("popu_cen_ques_id");

                                person_num = jsonobject.getInt("person_num");

                                p1 = jsonobject.getString("lastname_p1").trim();
                                p_1 = jsonobject.getString("firstname_p1").trim();
                                p2 = jsonobject.getString("p2").trim();
                                p3 = jsonobject.getString("p3").trim();
                                month_p4 = jsonobject.getString("month_p4").trim();
                                p5 = jsonobject.getString("p5").trim();
                                p6 = jsonobject.getString("p6").trim();
                                p7 = jsonobject.getString("p7").trim();
                                p8 = jsonobject.getString("p8").trim();
                                p9 = jsonobject.getString("p9").trim();
                                p10 = jsonobject.getString("p10").trim();
                                p11 = jsonobject.getString("p11").trim();
                                p12 = jsonobject.getString("p12").trim();
                                p13 = jsonobject.getString("p13").trim();
                                p14 = jsonobject.getString("p14").trim();
                                p15 = jsonobject.getString("p15").trim();
                                p16 = jsonobject.getString("p16").trim();
                                qr_number = jsonobject.getString("qr_number").trim();



                                val0.setText(p1);
                                val1.setText(p_1);
                                current_1.setText(p2);
                                current_2.setText(p3);
                                current_3.setText(month_p4);
                                current_4.setText(p5);
                                current_5.setText(p6);
                                current_6.setText(p7);
                                current_7.setText(p8);
                                current_8.setText(p9);
                                current_9.setText(p10);
                                current_10.setText(p11);
                                current_11.setText(p12);
                                current_12.setText(p13);
                                current_13.setText(p14);
                                current_14.setText(p15);
                                current_15.setText(p16);
                                val17.setText(qr_number);



                            }
                        } catch (JSONException e) {

                            e.printStackTrace();


                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){

                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );




        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Population_Update_Form.this,
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

                val4.setText(date);

            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);



        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                String UploadUrl = CertificationForm.UploadURLStatic+"/Android/Household_Population_Update.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    String Response = jsonObject.getString("success");
                                    pd.hide();
                                    Toast.makeText(Population_Update_Form.this, Response, Toast.LENGTH_LONG).show();
                                    finish();

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

                        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

                        final int Val1 = sharedPreferences.getInt("enumerator_id", 0);
                        int GEO = geo_iden_id;
                        int per_num = person_num;

                        int id = pop_id;
                        String fname = val0.getText().toString();
                        String lname = val1.getText().toString();
                        String p2 = val2.getText().toString();
                        String p3 = val3.getText().toString();
                        String month_p4 = val4.getText().toString();
                        String p5 = val5.getText().toString();
                        String p6 = val6.getText().toString();
                        String p7 = val7.getText().toString();
                        String p8 = val8.getText().toString();
                        String p9 = val9.getText().toString();
                        String p10 = val10.getText().toString();
                        String p11 = val11.getText().toString();
                        String p12 = val12.getText().toString();
                        String p13 = val13.getText().toString();
                        String p14 = val14.getText().toString();
                        String p15 = val15.getText().toString();
                        String p16 = val16.getText().toString();
                        String p17 = qr_number;


                        params.put("enumerator_id", String.valueOf(Val1));
                        params.put("geo_iden_id", String.valueOf(GEO));
                        params.put("popu_cen_ques_id", String.valueOf(id));
                        params.put("person_num", String.valueOf(per_num));
                        params.put("lastname_p1", fname);
                        params.put("firstname_p1", lname);
                        params.put("p2", p2);
                        params.put("p3", p3);
                        params.put("month_p4", month_p4);
                        params.put("p5", p5);
                        params.put("p6", p6);
                        params.put("p7", p7);
                        params.put("p8", p8);
                        params.put("p9", p9);
                        params.put("p10", p10);
                        params.put("p11", p11);
                        params.put("p12", p12);
                        params.put("p13", p13);
                        params.put("p14", p14);
                        params.put("p15", p15);
                        params.put("p16", p16);
                        params.put("qr_number", p17);


                        return params;
                    }
                };
                finish();

                MySingleton.getInstance(Population_Update_Form.this).addToRequestQueue(stringRequest);
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
