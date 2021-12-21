package com.example.kugangka.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.fido.u2f.api.common.RegisterRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GEO_Identification extends AppCompatActivity {
    private String UploadUrl = CertificationForm.UploadURLStatic+"/Android/GEO_Identification.php";
    private String QR_Manager = CertificationForm.UploadURLStatic+"/Android/QR_Manager.php";
    EditText
            provincenum1,
            municipalnum1,
            barangaynum1,
            enumeratornum1,
            buildingnum1,
            housenum1,
            householdnum1,
            linenum1;
    EditText Eprovice, Emunicipal,Ebarangay,Elastname,Efirstname,Eaddress;

    ImageView back;
    private ProgressDialog pd;
     TextView QR;
    int geo_iden_id;
    Button Proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_identification);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        pd = new ProgressDialog(GEO_Identification.this);
        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

         QR = findViewById(R.id.QR);
        Intent intent = getIntent();
        final String qr_code = intent.getStringExtra("qr_code_number");
        QR.setText(qr_code);


        back = findViewById(R.id.back);

        provincenum1 = (EditText) findViewById(R.id.province_num1);

        municipalnum1 = (EditText) findViewById(R.id.municipal_num1);

        barangaynum1 = (EditText) findViewById(R.id.barangay_num1);

        enumeratornum1 = (EditText) findViewById(R.id.enumerator_num1);

        buildingnum1 = (EditText) findViewById(R.id.building_num1);



        housenum1 = (EditText) findViewById(R.id.house_num1);



        householdnum1 = (EditText) findViewById(R.id.household_num1);


        linenum1 = (EditText) findViewById(R.id.line_num1);


        Eprovice = (EditText)findViewById(R.id.province);
        Emunicipal = (EditText) findViewById(R.id.municipal);
        Ebarangay = (EditText) findViewById(R.id.barangay);
        Elastname = (EditText) findViewById(R.id.lastname);
        Efirstname = (EditText) findViewById(R.id.firstname);
        Eaddress = (EditText) findViewById(R.id.address);

        Proceed = (Button) findViewById(R.id.Proceed);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SQ = getIntent().getStringExtra("SQ");
                int GEO = geo_iden_id;
                if(SQ.equals("SQ")){
                    Intent intent = new Intent(GEO_Identification.this, Survey_Questions.class);
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
                                                Intent SurveyForms = new Intent(GEO_Identification.this, SurveyForms.class);
                                                SurveyForms.putExtra("Lname", Lname);
                                                SurveyForms.putExtra("Fname", Fname);
                                                SurveyForms.putExtra("qr_code_number", QR);
                                                SurveyForms.putExtra("geo_iden_id", GEO);
                                                GEO_Identification.this.startActivity(SurveyForms);
                                                finish();
                                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                            }
                                        } catch (JSONException e) {


                                            int GEO = geo_iden_id;
                                            Intent SurveyForms = new Intent(GEO_Identification.this, SurveyForms.class);
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

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
pd.show();
                if (provincenum1.getText().toString().length() <= 0){ provincenum1.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}

                if (municipalnum1.getText().toString().length() <= 0){ municipalnum1.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}

                if (barangaynum1.getText().toString().length() <= 0){ barangaynum1.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}

                if (enumeratornum1.getText().toString().length() <= 0){ enumeratornum1.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}

                if (buildingnum1.getText().toString().length() <= 0){ buildingnum1.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}

                if (housenum1.getText().toString().length() <= 0){ housenum1.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}

                if (householdnum1.getText().toString().length() <= 0){ householdnum1.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}

                if (linenum1.getText().toString().length() <= 0){ linenum1.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}

                if (Eprovice.getText().toString().length() <= 0){ Eprovice.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}
                if (Emunicipal.getText().toString().length() <= 0){ Emunicipal.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}
                if (Ebarangay.getText().toString().length() <= 0){ Ebarangay.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}
                if (Elastname.getText().toString().length() <= 0){ Elastname.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}
                if (Efirstname.getText().toString().length() <= 0){ Efirstname.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}
                if (Eaddress.getText().toString().length() <= 0){ Eaddress.setError("Please Enter Text!");
                    Toast.makeText(GEO_Identification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();}

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
                                        Toast.makeText(GEO_Identification.this, Response, Toast.LENGTH_LONG).show();



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

                            final int val1 = sharedPreferences.getInt("enumerator_id", 0);
                            int GEO = geo_iden_id;

                            String province1 = provincenum1.getText().toString();
                            String province = province1;
                            String municipal1 = municipalnum1.getText().toString();
                            String municipal = municipal1;
                            String barangay1 = barangaynum1.getText().toString();
                            String baragay = barangay1;
                            String enumerator1 = enumeratornum1.getText().toString();
                            String enumertator = enumerator1;
                            String building1 = buildingnum1.getText().toString();
                            String building = building1;
                            String house1 = housenum1.getText().toString();
                            String house = house1;
                            String household1 = householdnum1.getText().toString();
                            String household = household1;
                            String line1 = linenum1.getText().toString();
                            String line = line1;

                            String eprovince = Eprovice.getText().toString().trim();
                            String emunicipal = Emunicipal.getText().toString().trim();
                            String ebarangay = Ebarangay.getText().toString().trim();
                            String elastname = Elastname.getText().toString().trim();
                            String efirstname = Efirstname.getText().toString().trim();
                            String eaddress = Eaddress.getText().toString().trim();

                            String QR_Code = QR.getText().toString();


                            //Delete ni

                            // delete

                            params.put("enumerator_id", String.valueOf(val1));

                            params.put("province", eprovince);
                            params.put("province_num", province);
                            params.put("city", emunicipal);
                            params.put("city_num", municipal);
                            params.put("barangay", ebarangay);
                            params.put("barangay_num", baragay);

                            params.put("enumerator", enumertator);
                            params.put("building", building);
                            params.put("house", house);
                            params.put("household", household);
                            params.put("line", line);

                            params.put("lastname", elastname);
                            params.put("firstname", efirstname);
                            params.put("address", eaddress);
                            params.put("qr_number", QR_Code);
                            return params;
                        }
                    };
                    MySingleton.getInstance(GEO_Identification.this).addToRequestQueue(stringRequest);

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
                            int GEO = geo_iden_id;
                            String Response = jsonObject.getString("success");
                            Toast.makeText(GEO_Identification.this, Response, Toast.LENGTH_LONG).show();
                            Intent SurveyForms = new Intent(GEO_Identification.this, SurveyForms.class);
                            SurveyForms.putExtra("qr_code_number", QR.getText().toString());
                            SurveyForms.putExtra("Fname", Efirstname.getText().toString());
                            SurveyForms.putExtra("Lname", Elastname.getText().toString());
                            SurveyForms.putExtra("geo_iden_id", GEO);

                            startActivity(SurveyForms);

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
        MySingleton.getInstance(GEO_Identification.this).addToRequestQueue(stringRequest);


    }

}
