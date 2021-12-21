package com.example.kugangka.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Death_Member extends Activity {

    TextView yes_btn, no_btn;
    String SQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death__member);

        yes_btn = findViewById(R.id.yes_btn);
        no_btn = findViewById(R.id.no_btn);
        final Intent intent = getIntent();
        final String qr_code = intent.getStringExtra("qr_code_number");
        SQ = getIntent().getStringExtra("SQ");
        yes_btn.setOnClickListener(new View.OnClickListener() {
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
                                            String QR = jsonobject.getString("qr_number");
                                            Intent SurveyForms = new Intent(Death_Member.this, SurveyForms.class);
                                            SurveyForms.putExtra("Lname", Lname);
                                            SurveyForms.putExtra("Fname", Fname);
                                            SurveyForms.putExtra("qr_code_number", QR);

                                            Death_Member.this.startActivity(SurveyForms);
                                            //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);

                                        }
                                    } catch (JSONException e) {


                                        Intent SurveyForms = new Intent(Death_Member.this, SurveyForms.class);
                                        SurveyForms.putExtra("qr_code_number", qr_code);
                                        SurveyForms.putExtra("Fname", "No Geographic identification");
                                        SurveyForms.putExtra("Lname", "No Geographic identification");

                                        SurveyForms.putExtra("SQ", SQ);

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

           finish();
            }
        });
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Death_Member.this, Reg_Death.class);
                intent.putExtra("qr_code_number", qr_code);
                intent.putExtra("SQ", SQ);
               startActivity(intent);
               finish();

            }
        });

    }

}
