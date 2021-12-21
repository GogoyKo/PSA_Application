package com.example.kugangka.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class CertificationForm extends AppCompatActivity{
    Button Proceed, signatureButton, signatureButton2, signatureButton3,signatureButton4;

   // public static String UploadURLStatic = "http://192.168.101.1:8089";
   //public static String UploadURLStatic = "http://10.0.2.2:8089/PSA";
    public static String UploadURLStatic = "http://psasurveyapp.000webhostapp.com";
    String qr_code;
     ImageView  signImage, signImage2,signImage3,signImage4;
    EditText EnumName, team_supervisor,date_2,cen_area_super,date_3,co_rsso_po,date_4,
            date1,  date2,  date3, date4;
    ImageView back;
    ByteArrayOutputStream byteArrayOutputStream;
    private ProgressDialog pd;
   TextView QR_Text;

   String Fullname = "";
    private Bitmap bitmap;
   private String UploadUrl = UploadURLStatic+"/Android/upload.php";

    private String QR_Manager = UploadURLStatic+"/Android/QR_Manager.php";


    private static final String TAG = "MainActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    private DatePickerDialog.OnDateSetListener mDateSetListener3;
    private DatePickerDialog.OnDateSetListener mDateSetListener4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_form);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        final String val2 = sharedPreferences.getString("firstname", null);
        final String val3 = sharedPreferences.getString("lastname", null);
        back = findViewById(R.id.back);


        final String my_id = String.valueOf(val1);

        final String url= CertificationForm.UploadURLStatic+"/Android/My_Profile.php?enumerator_id="+ my_id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String firstname = jsonobject.getString("firstname").trim();
                                String lastname = jsonobject.getString("lastname").trim();
                                Fullname = firstname +" "+lastname;
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



        pd = new ProgressDialog(CertificationForm.this);
        pd.setMessage("loading");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(true);

        Button get_date = findViewById(R.id.get_date);
        Button get_date2 = findViewById(R.id.get_date_2);
        Button get_date3 = findViewById(R.id.get_date_3);
        Button get_date4 = findViewById(R.id.get_date_4);

        signatureButton =  findViewById(R.id.getSign);
        signatureButton2 =  findViewById(R.id.getSign2);
        signatureButton3 =  findViewById(R.id.getSign3);
        signatureButton4 =  findViewById(R.id.getSign4);

        final ImageView signImage = (ImageView) findViewById(R.id.imageView1);
        final ImageView signImage2 = (ImageView) findViewById(R.id.imageView2);
        final ImageView signImage3 = (ImageView) findViewById(R.id.imageView3);
        final ImageView signImage4 = (ImageView) findViewById(R.id.imageView4);

        Proceed = findViewById(R.id.Proceed);


        EnumName= findViewById(R.id.EnumName);
        team_supervisor = findViewById(R.id.team_supervisor);
        cen_area_super = findViewById(R.id.cen_area_super);
        co_rsso_po = findViewById(R.id.co_rsso_po);
        QR_Text = findViewById(R.id.qr_text);
        date1 = findViewById(R.id.date_1);
        date2 = findViewById(R.id.date_2);
        date3 = findViewById(R.id.date_3);
        date4 = findViewById(R.id.date_4);

        final String image_path = getIntent().getStringExtra("imagePath");
        final Bitmap bitmap = BitmapFactory.decodeFile(image_path);
        signImage.setImageBitmap(bitmap);

        final String image_path2 = getIntent().getStringExtra("imagePath2");
        final Bitmap bitmap2 = BitmapFactory.decodeFile(image_path2);
        signImage2.setImageBitmap(bitmap2);

        final String image_path3 = getIntent().getStringExtra("imagePath3");
        final Bitmap bitmap3 = BitmapFactory.decodeFile(image_path3);
        signImage3.setImageBitmap(bitmap3);

        final String image_path4 = getIntent().getStringExtra("imagePath4");
        final Bitmap bitmap4 = BitmapFactory.decodeFile(image_path4);
        signImage4.setImageBitmap(bitmap4);




        String value1 = getIntent().getStringExtra("EnumName");
        String value2 = getIntent().getStringExtra("team_supervisor");
        String value3 = getIntent().getStringExtra("cen_area_super");
        String value4 = getIntent().getStringExtra("co_rsso_po");
        final String value5 = getIntent().getStringExtra("qr_code_number");


        String value6 = getIntent().getStringExtra("date_1");
        String value7 = getIntent().getStringExtra("date_2");
        String value8 = getIntent().getStringExtra("date_3");
        String value9 = getIntent().getStringExtra("date_4");
        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        EnumName.setText(val2 + " " + val3);
        QR_Text.setText(value5);


        team_supervisor.setText(value2);
        cen_area_super.setText(value3);
        co_rsso_po.setText(value4);

        date1.setText(value6);
        date2.setText(value7);
        date3.setText(value8);
        date4.setText(value9);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SQ = getIntent().getStringExtra("SQ");
                if(SQ.equals("SQ")){

                    int GEO = geo_iden_id;
                    Intent intent = new Intent(CertificationForm.this, Survey_Questions.class);
                    intent.putExtra("qr_code_number", value5);
                    intent.putExtra("geo_iden_id", GEO);

                    startActivity(intent);
                    finish();
                }else if(SQ.equals("SF")){

                    try {
                        final String url = UploadURLStatic+"/Android/Household_GeoIden.php?qr_number=" +value5;
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
                                                String QR = value5;
                                                int GEO = geo_iden_id;
                                                Intent SurveyForms = new Intent(CertificationForm.this, SurveyForms.class);
                                                SurveyForms.putExtra("Lname", Lname);
                                                SurveyForms.putExtra("Fname", Fname);
                                                SurveyForms.putExtra("qr_code_number", QR);
                                                SurveyForms.putExtra("geo_iden_id", GEO);
                                                CertificationForm.this.startActivity(SurveyForms);
                                                finish();
                                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                            }
                                        } catch (JSONException e) {

                                            int GEO = geo_iden_id;

                                            Intent SurveyForms = new Intent(CertificationForm.this, SurveyForms.class);
                                            SurveyForms.putExtra("qr_code_number", QR_Text.getText().toString());
                                            SurveyForms.putExtra("Fname", "No Geographic identification");
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

        get_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CertificationForm.this,
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

                String date = month + "/" + day + "/" + year;
                date1.setText(date);

            }
        };
        get_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CertificationForm.this,
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

                String date = month + "/" + day + "/" + year;
                date2.setText(date);

            }
        };

        get_date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CertificationForm.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener3,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener3 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                date3.setText(date);

            }
        };
        get_date4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CertificationForm.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener4,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener4 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                date4.setText(date);

            }
        };




        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        pd.show();
                if (EnumName.getText().toString().length() <= 0) { EnumName.setError("Please Enter Text!");
                  }

                if (date1.getText().toString().length() <= 0) { date1.setError("Please Enter Date!");
                  }


                if(EnumName.getText().toString().length() <= 0 || date1.getText().toString().length() <= 0  ){
                    Toast.makeText(CertificationForm.this,"Please Enter Text",Toast.LENGTH_SHORT).show();

                }

                else if (bitmap==null)
                {
                    Toast.makeText(CertificationForm.this,"Please Upload Image",Toast.LENGTH_SHORT).show();

                }

                    else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        qr_manager();
                                        JSONObject jsonObject = new JSONObject(response);

                                        String Response = jsonObject.getString("response");
                                        pd.hide();
                                        Toast.makeText(CertificationForm.this, Response, Toast.LENGTH_LONG).show();

                                        try {
                                            final String url = UploadURLStatic+"/Android/Household_GeoIden.php?qr_number=" +value5;
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
                                                                    String QR = value5;
                                                                    int GEO = geo_iden_id;
                                                                    Intent SurveyForms = new Intent(CertificationForm.this, SurveyForms.class);
                                                                    SurveyForms.putExtra("Lname", Lname);
                                                                    SurveyForms.putExtra("Fname", Fname);
                                                                    SurveyForms.putExtra("qr_code_number", QR);
                                                                    SurveyForms.putExtra("geo_iden_id", GEO);


                                                                    CertificationForm.this.startActivity(SurveyForms);
                                                                    //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                                                }
                                                            } catch (JSONException e) {

                                                                int GEO = geo_iden_id;
                                                                Intent SurveyForms = new Intent(CertificationForm.this, SurveyForms.class);
                                                                SurveyForms.putExtra("qr_code_number", QR_Text.getText().toString());
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
                            String name1 = EnumName.getText().toString();
                            String name2 = team_supervisor.getText().toString();
                            String name3 = cen_area_super.getText().toString();
                            String name4 = co_rsso_po.getText().toString();
                            final String qr_code = QR_Text.getText().toString();
                            String date_1 = date1.getText().toString();
                            String date_2 = date2.getText().toString();
                            String date_3 = date3.getText().toString();
                            String date_4 = date4.getText().toString();
                            if (team_supervisor.getText().toString().length() <= 0 || date2.getText().toString().length() <= 0 || bitmap2 == null ){


                                name2 = "";
                                name3 = "";
                                name4 = "";

                                date_2 = "";
                                date_3 = "";
                                date_4 = "";

                                String image_2 = "";
                                String image_3 = "";
                                String image_4 = "";



                                params.put("enumerator_id", String.valueOf(val1));
                                params.put("geo_iden_id", String.valueOf(GEO));

                                params.put("EnumName", name1);
                                params.put("signature1", imageToString(bitmap));
                                params.put("team_supervisor", name2);
                                params.put("signature2", image_2);
                                params.put("cen_area_super", name3);
                                params.put("signature3", image_3);
                                params.put("co_rsso_po", name4);
                                params.put("signature4", image_4);
                                params.put("qr_number", qr_code);

                                params.put("date_1", date_1);
                                params.put("date_2", date_2);
                                params.put("date_3", date_3);
                                params.put("date_4", date_4);

                                return params;
                            }else {


                                String image1 = imageToString(bitmap);
                                String image2 = imageToString(bitmap2);
                                String image3 = "";
                                String image4 = "";

                                params.put("enumerator_id", String.valueOf(val1));
                                params.put("geo_iden_id", String.valueOf(GEO));
                                params.put("EnumName", name1);
                                params.put("signature1", image1);
                                params.put("team_supervisor", name2);
                                params.put("signature2", image2);
                                params.put("cen_area_super", name3);
                                params.put("signature3", image3);
                                params.put("co_rsso_po", name4);
                                params.put("signature4", image4);
                                params.put("qr_number", qr_code);

                                params.put("date_1", date_1);
                                params.put("date_2", date_2);
                                params.put("date_3", date_3);
                                params.put("date_4", date_4);

                                return params;
                            }




                        }
                    };

                    MySingleton.getInstance(CertificationForm.this).addToRequestQueue(stringRequest);

                }

                }

        });






        signatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signature();
                finish();

            }
        });
        signatureButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signature2();
                finish();
            }
        });
        signatureButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signature3();
                finish();
            }
        });
        signatureButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signature4();
            }
        });

    }
    private void signature(){

        String value1 = EnumName.getText().toString();
        String value2 = team_supervisor.getText().toString();
        String value3 = cen_area_super.getText().toString();
        String value4 = co_rsso_po.getText().toString();
        String value5 = getIntent().getStringExtra("imagePath2");
        String value6 = getIntent().getStringExtra("imagePath3");
        String value7 = getIntent().getStringExtra("imagePath4");
        String value8 = QR_Text.getText().toString();
        String value9 = date1.getText().toString();
        String value10 = date2.getText().toString();
        String value11 = date3.getText().toString();
        String value12 = date4.getText().toString();
       int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);


        Intent intent1 = new Intent(this, GetSignature.class);

        intent1.putExtra("EnumName", value1);
        intent1.putExtra("team_supervisor", value2);
        intent1.putExtra("cen_area_super", value3);
        intent1.putExtra("co_rsso_po", value4);
        intent1.putExtra("imagePath2", value5);
        intent1.putExtra("imagePath3", value6);
        intent1.putExtra("imagePath4", value7);
        intent1.putExtra("qr_code_number", value8);
        intent1.putExtra("date_1", value9);
        intent1.putExtra("date_2", value10);
        intent1.putExtra("date_3", value11);
        intent1.putExtra("date_4", value12);
        intent1.putExtra("geo_iden_id", geo_iden_id);



        startActivity(intent1);
    }
    private void signature2(){

        String value1 = EnumName.getText().toString();
        String value2 = team_supervisor.getText().toString();
        String value3 = cen_area_super.getText().toString();
        String value4 = co_rsso_po.getText().toString();
        String value5 = getIntent().getStringExtra("imagePath");
        String value6 = getIntent().getStringExtra("imagePath3");
        String value7 = getIntent().getStringExtra("imagePath4");
        String value8 = QR_Text.getText().toString();
        String value9 = date1.getText().toString();
        String value10 = date2.getText().toString();
        String value11 = date3.getText().toString();
        String value12 = date4.getText().toString();
        int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);
        Intent intent2 = new Intent(this, GetSignature2.class);

        intent2.putExtra("EnumName", value1);
        intent2.putExtra("team_supervisor", value2);
        intent2.putExtra("cen_area_super", value3);
        intent2.putExtra("co_rsso_po", value4);
        intent2.putExtra("imagePath", value5);
        intent2.putExtra("imagePath3", value6);
        intent2.putExtra("imagePath4", value7);
        intent2.putExtra("qr_code_number", value8);
        intent2.putExtra("date_1", value9);
        intent2.putExtra("date_2", value10);
        intent2.putExtra("date_3", value11);
        intent2.putExtra("date_4", value12);
        intent2.putExtra("geo_iden_id", geo_iden_id);

        startActivity(intent2);

    }
    private void signature3(){

        String value1 = EnumName.getText().toString();
        String value2 = team_supervisor.getText().toString();
        String value3 = cen_area_super.getText().toString();
        String value4 = co_rsso_po.getText().toString();
        String value5 = getIntent().getStringExtra("imagePath2");
        String value6 = getIntent().getStringExtra("imagePath");
        String value7 = getIntent().getStringExtra("imagePath4");
        String value8 = QR_Text.getText().toString();
        String value9 = date1.getText().toString();
        String value10 = date2.getText().toString();
        String value11 = date3.getText().toString();
        String value12 = date4.getText().toString();
        int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        Intent intent3 = new Intent(this, GetSignature3.class);
        intent3.putExtra("EnumName", value1);
        intent3.putExtra("team_supervisor", value2);
        intent3.putExtra("cen_area_super", value3);
        intent3.putExtra("co_rsso_po", value4);
        intent3.putExtra("imagePath2", value5);
        intent3.putExtra("imagePath", value6);
        intent3.putExtra("imagePath4", value7);
        intent3.putExtra("qr_code_number", value8);
        intent3.putExtra("date_1", value9);
        intent3.putExtra("date_2", value10);
        intent3.putExtra("date_3", value11);
        intent3.putExtra("date_4", value12);
        intent3.putExtra("geo_iden_id", geo_iden_id);
        startActivity(intent3);

    }
    private void signature4(){

        String value1 = EnumName.getText().toString();
        String value2 = team_supervisor.getText().toString();
        String value3 = cen_area_super.getText().toString();
        String value4 = co_rsso_po.getText().toString();
        String value5 = getIntent().getStringExtra("imagePath2");
        String value6 = getIntent().getStringExtra("imagePath3");
        String value7 = getIntent().getStringExtra("imagePath");
        String value8 = QR_Text.getText().toString();
        String value9 = date1.getText().toString();
        String value10 = date2.getText().toString();
        String value11 = date3.getText().toString();
        String value12 = date4.getText().toString();
        int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);
        Intent intent4 = new Intent(this, GetSignature4.class);

        intent4.putExtra("EnumName", value1);
        intent4.putExtra("team_supervisor", value2);
        intent4.putExtra("cen_area_super", value3);
        intent4.putExtra("co_rsso_po", value4);
        intent4.putExtra("imagePath2", value5);
        intent4.putExtra("imagePath3", value6);
        intent4.putExtra("imagePath", value7);
        intent4.putExtra("qr_code_number", value8);
        intent4.putExtra("date_1", value9);
        intent4.putExtra("date_2", value10);
        intent4.putExtra("date_3", value11);
        intent4.putExtra("date_4", value12);
        intent4.putExtra("geo_iden_id", geo_iden_id);
        startActivity(intent4);

    }
    private String imageToString(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imgBytes, Base64.DEFAULT);
        return encodedImage;

    }

    public void qr_manager(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, QR_Manager,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            String Response = jsonObject.getString("success");
                            Toast.makeText(CertificationForm.this, Response, Toast.LENGTH_LONG).show();

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


                String QR_num = QR_Text.getText().toString();


                params.put("qr_code_number", QR_num);


                return params;
            }
        };
        MySingleton.getInstance(CertificationForm.this).addToRequestQueue(stringRequest);

    }
}