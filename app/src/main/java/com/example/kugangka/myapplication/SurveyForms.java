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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SurveyForms extends AppCompatActivity {
private ProgressDialog pd;
    String QR_Code;
    ImageView home;
    TextView Qr_Number, change_qr_number;
    int geo_iden_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_forms);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        final CardView Certification1 = (CardView) findViewById(R.id.Certification);
        final CardView InterviewRecBtn1 = (CardView) findViewById(R.id.InterviewRecBtn);
        final CardView GEOIdentification1 = (CardView) findViewById(R.id.GEOIdentification);
        final CardView Population_Census_Question1 = (CardView) findViewById(R.id.population_census_question);
        final CardView Housing_Census1 = (CardView) findViewById(R.id.Housing_Census);
        final CardView rdly1 = (CardView) findViewById(R.id.rdly);
        final TextView id_textName = (TextView) findViewById(R.id.id_textName);
        final TextView id_textLast = (TextView) findViewById(R.id.id_textLast);

        change_qr_number = findViewById(R.id.change_qr_number);

         Qr_Number = findViewById(R.id.qr_number);
        home = findViewById(R.id.home);

        // final ImageView imageView = (ImageView) findViewById(R.id.img);
        final Intent intent = getIntent();
        // String imageURL = intent.getStringExtra("imagePath");
       final String Firstname = intent.getStringExtra("Fname");
        final String Lastname = intent.getStringExtra("Lname");

        QR_Code = intent.getStringExtra("qr_code_number");

        id_textName.setText(Firstname);
        id_textLast.setText(Lastname);
        Qr_Number.setText(QR_Code);





        final String url= CertificationForm.UploadURLStatic+"/Android/Household_GeoIden.php?qr_number="+ QR_Code;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            for(int i=0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                               geo_iden_id = jsonobject.getInt("geo_iden_id");
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
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);



        change_qr_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int GEO = geo_iden_id;
                Intent intent = new Intent(SurveyForms.this, qr_change.class);
                intent.putExtra("Fname", Firstname);
                intent.putExtra("Lname", Lastname);
                intent.putExtra("qr_code_number", QR_Code);
                intent.putExtra("geo_iden_id", GEO);
                startActivity(intent);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SurveyForms.this, UserDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        Certification1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //retrieve current household data
                getCertificationData();
                //



            }
        });
        InterviewRecBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //retrieve current household data
                getInterView();
                //

            }
        });
        GEOIdentification1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieve current household data
                getGeoIdenData();
                //

            }
        });

        Population_Census_Question1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getPopulation();


            }
        });

        Housing_Census1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getHouse();

            }
        });
        rdly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               getDeath();


            }
        });


    }

    private void getCertificationData() {

        final String url= CertificationForm.UploadURLStatic+"/Android/Household_Certificate.php?qr_number="+ QR_Code;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                int GEO = geo_iden_id;
                                int id = jsonobject.getInt("certification_id");
                                String enumerator = jsonobject.getString("enumerator").trim();
                                String signature1 = jsonobject.getString("signature1").trim();
                                String team_supervisor = jsonobject.getString("team_supervisor").trim();
                                String signature2 = jsonobject.getString("signature2").trim();
                                String census_area_supervisor = jsonobject.getString("census_area_supervisor");
                                String signature3 = jsonobject.getString("signature3");
                                String co_rsso_po = jsonobject.getString("co_rsso_po");
                                String signature4 = jsonobject.getString("signature4");
                                String date_1 = jsonobject.getString("date_1");
                                String date_2 = jsonobject.getString("date_2");
                                String date_3 = jsonobject.getString("date_3");
                                String date_4 = jsonobject.getString("date_4");
                                String qr_number = jsonobject.getString("qr_number");

                                Intent Household_Certification = new Intent(SurveyForms.this, Household_Certification.class);
                                Household_Certification.putExtra("ID", id);
                                Household_Certification.putExtra("EnumName", enumerator);
                                Household_Certification.putExtra("imagePath", signature1);
                                Household_Certification.putExtra("team_supervisor", team_supervisor);
                                Household_Certification.putExtra("imagePath2", signature2);
                                Household_Certification.putExtra("cen_area_super", census_area_supervisor);
                                Household_Certification.putExtra("imagePath3", signature3);
                                Household_Certification.putExtra("co_rsso_po", co_rsso_po);
                                Household_Certification.putExtra("imagePath4", signature4);

                                Household_Certification.putExtra("date_1", date_1);
                                Household_Certification.putExtra("date_2", date_2);
                                Household_Certification.putExtra("date_3", date_3);
                                Household_Certification.putExtra("date_4", date_4);
                                Household_Certification.putExtra("qr_code_number", qr_number);

                                Household_Certification.putExtra("SQ", "SF");

                                Household_Certification.putExtra("geo_iden_id", GEO);

                                SurveyForms.this.startActivity(Household_Certification);
                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                            }
                        } catch (JSONException e) {
                            int GEO = geo_iden_id;
                            Intent Certification1 = new Intent(SurveyForms.this, CertificationForm.class);
                            Certification1.putExtra("qr_code_number", QR_Code);
                            Certification1.putExtra("SQ", "SF");
                            Certification1.putExtra("geo_iden_id", GEO);
                            startActivity(Certification1);
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

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void getGeoIdenData() {

        final String url= CertificationForm.UploadURLStatic+"/Android/Household_GeoIden.php?qr_number="+ QR_Code;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                int id = jsonobject.getInt("geo_iden_id");

                                String province = jsonobject.getString("province").trim();
                                String province_num = jsonobject.getString("province_num").trim();
                                String city = jsonobject.getString("city").trim();
                                String city_num = jsonobject.getString("city_num").trim();
                                String brgy = jsonobject.getString("brgy");
                                String brgy_num = jsonobject.getString("brgy_num");
                                String EAN = jsonobject.getString("EAN");
                                String BSN = jsonobject.getString("BSN");
                                String HUSN = jsonobject.getString("HUSN");
                                String HSN = jsonobject.getString("HSN");
                                String LNTR = jsonobject.getString("LNTR");
                                String Lname = jsonobject.getString("Lname");
                                String Fname = jsonobject.getString("Fname");
                                String Address = jsonobject.getString("Address");
                                    String qr_number = jsonobject.getString("qr_number");

                                Intent Household_Geo = new Intent(SurveyForms.this, Household_Geo.class);

                                Household_Geo.putExtra("ID", id);
                                Household_Geo.putExtra("province", province);
                                Household_Geo.putExtra("province_num", province_num);
                                Household_Geo.putExtra("city", city);
                                Household_Geo.putExtra("city_num", city_num);
                                Household_Geo.putExtra("brgy", brgy);
                                Household_Geo.putExtra("brgy_num", brgy_num);
                                Household_Geo.putExtra("EAN", EAN);
                                Household_Geo.putExtra("BSN", BSN);
                                Household_Geo.putExtra("HUSN", HUSN);
                                Household_Geo.putExtra("HSN", HSN);
                                Household_Geo.putExtra("LNTR", LNTR);
                                Household_Geo.putExtra("Lname", Lname);
                                Household_Geo.putExtra("Fname", Fname);
                                Household_Geo.putExtra("Address", Address);
                                Household_Geo.putExtra("qr_code_number", qr_number);
                                Household_Geo.putExtra("geo_iden_id", id);


                                Household_Geo.putExtra("SQ", "SF");




                                SurveyForms.this.startActivity(Household_Geo);
                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                            }
                        } catch (JSONException e) {
                            int GEO = geo_iden_id;
                            Intent Geo = new Intent(SurveyForms.this, GEO_Identification.class);
                            Geo.putExtra("qr_code_number", QR_Code);
                            Geo.putExtra("SQ", "SF");
                            Geo.putExtra("geo_iden_id", GEO);
                            startActivity(Geo);
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

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void getInterView() {

        final String url= CertificationForm.UploadURLStatic+"/Android/Household_Interview.php?qr_number="+ QR_Code;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                int GEO = geo_iden_id;
                                int id = jsonobject.getInt("interview_rec_id");
                                String date_visit = jsonobject.getString("date_visit").trim();
                                String time_began = jsonobject.getString("time_began").trim();
                                String time_end = jsonobject.getString("time_end").trim();
                                String result_visit = jsonobject.getString("result_visit").trim();
                                String date_visit2 = jsonobject.getString("date_visit2").trim();
                                String time_visit = jsonobject.getString("time_visit").trim();
                                String num_of_visit = jsonobject.getString("num_of_visit");
                                String result_of_visit = jsonobject.getString("result_of_visit");
                                String num_of_household_mem = jsonobject.getString("num_of_household_mem");
                                String num_of_males = jsonobject.getString("num_of_males");
                                String num_of_females = jsonobject.getString("num_of_females");
                                String mode_of_date_collection = jsonobject.getString("mode_of_date_collection");
                                String qr_number = jsonobject.getString("qr_number");

                                Intent Household_Interview = new Intent(SurveyForms.this, Household_Interview.class);

                                Household_Interview.putExtra("ID", id);

                                Household_Interview.putExtra("date_visit", date_visit);
                                Household_Interview.putExtra("time_began", time_began);
                                Household_Interview.putExtra("time_end", time_end);
                                Household_Interview.putExtra("result_visit", result_visit);
                                Household_Interview.putExtra("date_visit2", date_visit2);
                                Household_Interview.putExtra("time_visit", time_visit);
                                Household_Interview.putExtra("num_of_visit", num_of_visit);
                                Household_Interview.putExtra("result_of_visit", result_of_visit);
                                Household_Interview.putExtra("num_of_household_mem", num_of_household_mem);
                                Household_Interview.putExtra("num_of_males", num_of_males);
                                Household_Interview.putExtra("num_of_females", num_of_females);
                                Household_Interview.putExtra("mode_of_date_collection", mode_of_date_collection);

                                Household_Interview.putExtra("qr_code_number", qr_number);
                                Household_Interview.putExtra("geo_iden_id", GEO);

                                Household_Interview.putExtra("SQ", "SF");



                                SurveyForms.this.startActivity(Household_Interview);
                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                            }
                        } catch (JSONException e) {
                            int GEO = geo_iden_id;
                            Intent Interview = new Intent(SurveyForms.this, Interview_Record.class);
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat Time = new SimpleDateFormat("HH:mm:ss aa");
                            String time = Time.format(calendar.getTime());


                            Interview.putExtra("time", time);
                            Interview.putExtra("qr_code_number", QR_Code);
                            Interview.putExtra("SQ", "SF");
                            Interview.putExtra("geo_iden_id", GEO);
                            startActivity(Interview);

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

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void getHouse() {

        final String url= CertificationForm.UploadURLStatic+"/Android/Household_House.php?qr_number="+ QR_Code;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                int GEO = geo_iden_id;
                                int id = jsonobject.getInt("house_cen_ques_id");
                                String b1 = jsonobject.getString("b1").trim();
                                String b2 = jsonobject.getString("b2").trim();
                                String b3 = jsonobject.getString("b3").trim();
                                String h1 = jsonobject.getString("h1").trim();
                                String h2 = jsonobject.getString("h2").trim();
                                String h3 = jsonobject.getString("h3").trim();
                                String h4 = jsonobject.getString("h4").trim();
                                String qr_number = jsonobject.getString("qr_number");
                                Intent Household_Housing = new Intent(SurveyForms.this, Household_Housing.class);
                                Household_Housing.putExtra("ID", id);
                                Household_Housing.putExtra("b1", b1);
                                Household_Housing.putExtra("b2", b2);
                                Household_Housing.putExtra("b3", b3);
                                Household_Housing.putExtra("h1", h1);
                                Household_Housing.putExtra("h2", h2);
                                Household_Housing.putExtra("h3", h3);
                                Household_Housing.putExtra("h4", h4);
                                Household_Housing.putExtra("qr_code_number", qr_number);

                                Household_Housing.putExtra("geo_iden_id", GEO);
                                Household_Housing.putExtra("SQ", "SF");
                                SurveyForms.this.startActivity(Household_Housing);
                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                            }
                        } catch (JSONException e) {
                            int GEO = geo_iden_id;
                            Intent House = new Intent(SurveyForms.this, Housing_Census_Question.class);
                            House.putExtra("qr_code_number", QR_Code);
                            House.putExtra("SQ", "SF");
                            House.putExtra("geo_iden_id", GEO);
                            startActivity(House);
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

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void getPopulation() {

        final String url= CertificationForm.UploadURLStatic+"/Android/Household_Population.php?qr_number="+ QR_Code;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);


                                int GEO = geo_iden_id;
                                String lastname_p1 = jsonobject.getString("lastname_p1").trim();
                                String firstname_p1 = jsonobject.getString("firstname_p1").trim();
                                String p2 = jsonobject.getString("p2").trim();
                                String p3 = jsonobject.getString("p3").trim();
                                String month_p4 = jsonobject.getString("month_p4").trim();
                                String p5 = jsonobject.getString("p5").trim();

                                String p6 = jsonobject.getString("p6").trim();
                                String p7 = jsonobject.getString("p7").trim();
                                String p8 = jsonobject.getString("p8").trim();
                                String p9 = jsonobject.getString("p9").trim();
                                String p10 = jsonobject.getString("p10").trim();
                                String p11 = jsonobject.getString("p11").trim();
                                String p12 = jsonobject.getString("p12").trim();

                                String p13 = jsonobject.getString("p13").trim();
                                String p14 = jsonobject.getString("p14").trim();
                                String p15 = jsonobject.getString("p15").trim();
                                String p16 = jsonobject.getString("p16").trim();
                                String p17 = jsonobject.getString("qr_number").trim();


                                Intent Household_Population = new Intent(SurveyForms.this, Household_Population.class);
                                Household_Population.putExtra("lastname_p1", lastname_p1);
                                Household_Population.putExtra("firstname_p1", firstname_p1);
                                Household_Population.putExtra("p2", p2);
                                Household_Population.putExtra("p3", p3);
                                Household_Population.putExtra("month_p4", month_p4);
                                Household_Population.putExtra("p5", p5);

                                Household_Population.putExtra("p6", p6);
                                Household_Population.putExtra("p7", p7);
                                Household_Population.putExtra("p8", p8);
                                Household_Population.putExtra("p9", p9);
                                Household_Population.putExtra("p10", p10);
                                Household_Population.putExtra("p11", p11);
                                Household_Population.putExtra("p12", p12);

                                Household_Population.putExtra("p13", p13);
                                Household_Population.putExtra("p14", p14);
                                Household_Population.putExtra("p15", p15);
                                Household_Population.putExtra("p16", p16);
                                Household_Population.putExtra("qr_code_number", p17);

                                Household_Population.putExtra("geo_iden_id", GEO);

                                Household_Population.putExtra("SQ", "SF");

                                SurveyForms.this.startActivity(Household_Population);
                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                            }
                        } catch (JSONException e) {
                            int GEO = geo_iden_id;
                            Intent Population = new Intent(SurveyForms.this, Population_Census_Question.class);
                            Population.putExtra("qr_code_number", QR_Code);
                            Population.putExtra("SQ", "SF");
                            Population.putExtra("geo_iden_id", GEO);
                            startActivity(Population);
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

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void getDeath() {

        final String url= CertificationForm.UploadURLStatic+"/Android/Household_Death.php?qr_number="+ QR_Code;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                int GEO = geo_iden_id;
                                String d1 = jsonobject.getString("d1").trim();
                                String d2 = jsonobject.getString("d2").trim();
                                String lastname = jsonobject.getString("lastname").trim();
                                String firstname = jsonobject.getString("firstname").trim();
                                String gender = jsonobject.getString("gender").trim();
                                String age_at_death = jsonobject.getString("age_at_death").trim();
                                String death_reg_d6 = jsonobject.getString("death_reg_d6");
                                String death_reg_d7 = jsonobject.getString("death_reg_d7");
                                String qr_number = jsonobject.getString("qr_number");

                                Intent Household_Death = new Intent(SurveyForms.this, Household_Death.class);
                                Household_Death.putExtra("date_visit", d1);
                                Household_Death.putExtra("time_began", d2);
                                Household_Death.putExtra("time_end", lastname);
                                Household_Death.putExtra("result_visit", firstname);
                                Household_Death.putExtra("date_visit2", gender);
                                Household_Death.putExtra("time_visit", age_at_death);
                                Household_Death.putExtra("num_of_visit", death_reg_d6);
                                Household_Death.putExtra("result_of_visit", death_reg_d7);
                                Household_Death.putExtra("qr_code_number", qr_number);
                                Household_Death.putExtra("geo_iden_id", GEO);
                                Household_Death.putExtra("SQ", "SF");
                                SurveyForms.this.startActivity(Household_Death);
                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                            }
                        } catch (JSONException e) {
                            int GEO = geo_iden_id;
                            Intent Death = new Intent(SurveyForms.this, Reg_Death.class);
                            Death.putExtra("qr_code_number", QR_Code);
                            Death.putExtra("SQ", "SF");
                            Death.putExtra("geo_iden_id", GEO);
                            startActivity(Death);
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

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


}
