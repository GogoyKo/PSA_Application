package com.example.kugangka.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class Housing_Census_Question extends AppCompatActivity {

    private String UploadUrl = CertificationForm.UploadURLStatic+"/Android/house_cen_ques.php";
    private String QR_Manager = CertificationForm.UploadURLStatic+"/Android/QR_Manager.php";
    Button Proceed;
    Spinner B1, B2, B3, H1, H2, H3, H4;
    TextView QR;
    String qr_code;
    private ProgressDialog pd;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_census_question);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);


        pd = new ProgressDialog(Housing_Census_Question.this);
        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        QR = findViewById(R.id.QR);
        Intent intent = getIntent();
         qr_code = intent.getStringExtra("qr_code_number");
        QR.setText(qr_code);

        back = findViewById(R.id.back);

        B1 = (Spinner) findViewById(R.id.b1);
        B2 = (Spinner) findViewById(R.id.b2);
        B3 = (Spinner) findViewById(R.id.b3);
        H1 = (Spinner) findViewById(R.id.h1);
        H2 = (Spinner) findViewById(R.id.h2);
        H3 = (Spinner) findViewById(R.id.h3);
        H4 = (Spinner) findViewById(R.id.h4);
        Proceed = findViewById(R.id.Proceed);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SQ = getIntent().getStringExtra("SQ");
                if(SQ.equals("SQ")){
                    Intent intent = new Intent(Housing_Census_Question.this, Survey_Questions.class);
                    intent.putExtra("qr_code_number", qr_code);
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
                                                Intent SurveyForms = new Intent(Housing_Census_Question.this, SurveyForms.class);
                                                SurveyForms.putExtra("Lname", Lname);
                                                SurveyForms.putExtra("Fname", Fname);
                                                SurveyForms.putExtra("qr_code_number", QR);
                                                Housing_Census_Question.this.startActivity(SurveyForms);
                                                finish();
                                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                            }
                                        } catch (JSONException e) {


                                            Intent SurveyForms = new Intent(Housing_Census_Question.this, SurveyForms.class);
                                            SurveyForms.putExtra("qr_code_number", qr_code);
                                            SurveyForms.putExtra("Fname", "No Geographic identification");
                                            SurveyForms.putExtra("Lname", "No Geographic identification");
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

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
pd.show();

                if (B1.getSelectedItem().toString().length() <= 0 || B2.getSelectedItem().toString().length() <= 0 || B3.getSelectedItem().toString().length() <= 0
                        || H1.getSelectedItem().toString().length() <= 0 || H2.getSelectedItem().toString().length() <= 0
                        || H3.getSelectedItem().toString().length() <= 0 || H4.getSelectedItem().toString().length() <= 0) {
                    Toast.makeText(Housing_Census_Question.this, "Please Enter Text", Toast.LENGTH_SHORT).show(); }



                else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        qr_manager();
                                        JSONObject jsonObject = new JSONObject(response);

                                        String Response = jsonObject.getString("response");
                                        pd.hide();
                                        Toast.makeText(Housing_Census_Question.this, Response, Toast.LENGTH_LONG).show();



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

                            String b1 = B1.getSelectedItem().toString();
                            String b2 = B2.getSelectedItem().toString();
                            String b3 = B3.getSelectedItem().toString();

                            String h1 = H1.getSelectedItem().toString();
                            String h2 = H2.getSelectedItem().toString();
                            String h3 = H3.getSelectedItem().toString();
                            String h4 = H4.getSelectedItem().toString();
                            final String QR_Code = QR.getText().toString();


                            params.put("enumerator_id", String.valueOf(val1));
                            params.put("geo_iden_id", String.valueOf(GEO));
                            params.put("b1", "" + b1);
                            params.put("b2", b2);
                            params.put("b3", b3);
                            params.put("h1", h1);
                            params.put("h2", h2);
                            params.put("h3", h3);
                            params.put("h4", h4);
                            params.put("qr_number", QR_Code);

                            return params;

                        }
                    };
                    MySingleton.getInstance(Housing_Census_Question.this).addToRequestQueue(stringRequest);
                }

            }
        });

    }
    public void qr_manager(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, QR_Manager,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            String Response = jsonObject.getString("success");
                            Toast.makeText(Housing_Census_Question.this, Response, Toast.LENGTH_LONG).show();

                            try {
                                final String url = CertificationForm.UploadURLStatic+"/Android/Household_GeoIden.php?qr_number=" +QR.getText().toString();
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
                                                        String qr = qr_code;
                                                        Intent SurveyForms = new Intent(Housing_Census_Question.this, SurveyForms.class);
                                                        SurveyForms.putExtra("Lname", Lname);
                                                        SurveyForms.putExtra("Fname", Fname);
                                                        SurveyForms.putExtra("qr_code_number", qr);


                                                        Housing_Census_Question.this.startActivity(SurveyForms);
                                                        //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                                    }
                                                } catch (JSONException e) {


                                                    Intent SurveyForms = new Intent(Housing_Census_Question.this, SurveyForms.class);
                                                    SurveyForms.putExtra("qr_code_number", qr_code);
                                                    SurveyForms.putExtra("Fname", "No Geographic identification");
                                                    SurveyForms.putExtra("Lname", "No Geographic identification");
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
        MySingleton.getInstance(Housing_Census_Question.this).addToRequestQueue(stringRequest);


    }
}
