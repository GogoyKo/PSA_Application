package com.example.kugangka.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Survey_Questions extends AppCompatActivity {
    private ProgressDialog pd;
    ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_questions);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        home = findViewById(R.id.home);

        final CardView Certification1 = (CardView) findViewById(R.id.Certification);
        final CardView InterviewRecBtn1 = (CardView) findViewById(R.id.InterviewRecBtn);
        final CardView GEOIdentification1 = (CardView) findViewById(R.id.GEOIdentification);
        final CardView Population_Census_Question1 = (CardView) findViewById(R.id.population_census_question);
        final CardView Housing_Census1 = (CardView) findViewById(R.id.Housing_Census);
        final CardView rdly1 = (CardView) findViewById(R.id.rdly);

       /*
        Certification1.setEnabled(false);

        InterviewRecBtn1.setEnabled(false);
        Population_Census_Question1.setEnabled(false);
        Housing_Census1.setEnabled(false);
        rdly1.setEnabled(false);
 */
        final TextView QRNumber = (TextView) findViewById(R.id.qr_number);

        Intent intent = getIntent();
        final String qr_code_number = intent.getStringExtra("qr_code_number");
        final String time = intent.getStringExtra("time");
        QRNumber.setText(qr_code_number);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Survey_Questions.this, UserDashboard.class);
                startActivity(intent);
                finish();
            }
        });


        Certification1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Select Geographic Identification first.", Toast.LENGTH_LONG).show();
                /*
                try {
                    final String url = CertificationForm.UploadURLStatic+"/Android/Household_GeoIden.php?qr_number=" +qr_code_number;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonarray = new JSONArray(response);
                                        for (int i = 0; i < jsonarray.length(); i++) {
                                            JSONObject jsonobject = jsonarray.getJSONObject(i);

                                            String Fname = jsonobject.getString("Fname");
                                            int geo_iden_id = jsonobject.getInt("geo_iden_id");

                                            Intent Certification = new Intent(Survey_Questions.this, CertificationForm.class);
                                            Certification.putExtra("qr_code_number", qr_code_number);
                                            Certification.putExtra("SQ", "SQ");
                                            Certification.putExtra("Fname", Fname);
                                            Certification.putExtra("geo_iden_id", geo_iden_id);
                                            startActivity(Certification);
                                            finish();

                                            //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                        }
                                    } catch (JSONException e) {

                                        Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();

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

                /*Intent Certification = new Intent(Survey_Questions.this, CertificationForm.class);
                Certification.putExtra("qr_code_number", qr_code_number);*/


            }
        });
        InterviewRecBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), "Select Geographic Identification first.", Toast.LENGTH_LONG).show();
                /*
                Intent InterviewRecBtn = new Intent(Survey_Questions.this, Interview_Record.class);
                InterviewRecBtn.putExtra("time", time);
                InterviewRecBtn.putExtra("qr_code_number", qr_code_number);
                InterviewRecBtn.putExtra("SQ", "SQ");
                InterviewRecBtn.putExtra("geo_iden_id", geo_iden_id);
                startActivity(InterviewRecBtn);
                */

            }
        });
        GEOIdentification1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent GEOIdentification = new Intent(Survey_Questions.this, GEO_Identification.class);
                GEOIdentification.putExtra("qr_code_number", qr_code_number);
                GEOIdentification.putExtra("SQ", "SQ");
                GEOIdentification.putExtra("geo_iden_id", geo_iden_id);
                startActivity(GEOIdentification);

            }
        });

        Population_Census_Question1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Select Geographic Identification first.", Toast.LENGTH_LONG).show();
/*
                Intent Population_census_question = new Intent(Survey_Questions.this, Population_Census_Question.class);
                Population_census_question.putExtra("qr_code_number", qr_code_number);
                Population_census_question.putExtra("SQ", "SQ");
                Population_census_question.putExtra("geo_iden_id", geo_iden_id);
                startActivity(Population_census_question);
*/
            }
        });

        Housing_Census1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Select Geographic Identification first.", Toast.LENGTH_LONG).show();
            /*
                Intent Housing_Census = new Intent(Survey_Questions.this, Housing_Census_Question.class);
                Housing_Census.putExtra("qr_code_number", qr_code_number);
                Housing_Census.putExtra("SQ", "SQ");
                Housing_Census.putExtra("geo_iden_id", geo_iden_id);
                startActivity(Housing_Census);
*/
            }
        });
        rdly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Select Geographic Identification first.", Toast.LENGTH_LONG).show();
                /*
                Intent rdly = new Intent(Survey_Questions.this, Reg_Death.class);
                rdly.putExtra("qr_code_number", qr_code_number);
                rdly.putExtra("SQ", "SQ");
                rdly.putExtra("geo_iden_id", geo_iden_id);
                startActivity(rdly);
*/
            }
        });


    }
}
