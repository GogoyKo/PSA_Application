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
import com.squareup.picasso.Picasso;

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

public class Household_Certification extends AppCompatActivity{
    Button Proceed, signatureButton, signatureButton2, signatureButton3,signatureButton4;

    String qr_code;
    ImageView  signImage, signImage2,signImage3,signImage4, imageView_1,imageView_2;
    EditText EnumName, team_supervisor,date_2,cen_area_super,date_3,co_rsso_po,date_4,
            date1,  date2,  date3, date4;
    String image_path,image_path2,image_path3,image_path4;

    TextView last_enum,last_superV;

    private ProgressDialog pd;

    TextView QR_Text;
    private Bitmap bitmap;
    private String UploadUrl = CertificationForm.UploadURLStatic+"/Android/Household_Certificate_Update.php";
    private String QR_Manager = CertificationForm.UploadURLStatic+"/Android/QR_Manager.php";


    private static final String TAG = "MainActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    private DatePickerDialog.OnDateSetListener mDateSetListener3;
    private DatePickerDialog.OnDateSetListener mDateSetListener4;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household__certification);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);
        final String val2 = sharedPreferences.getString("firstname", null);
        final String val3 = sharedPreferences.getString("lastname", null);

        pd = new ProgressDialog(Household_Certification.this);
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

       signImage = (ImageView) findViewById(R.id.imageView1);
         signImage2 = (ImageView) findViewById(R.id.imageView2);
        signImage3 = (ImageView) findViewById(R.id.imageView3);
       signImage4 = (ImageView) findViewById(R.id.imageView4);


        imageView_1 = findViewById(R.id.imageView_1);
        imageView_2 = findViewById(R.id.imageView_2);

        last_enum = findViewById(R.id.last_enum);
        last_superV = findViewById(R.id.last_superV);

        Proceed = findViewById(R.id.Proceed);


        EnumName= findViewById(R.id.EnumName);
        team_supervisor = findViewById(R.id.team_supervisor);
        cen_area_super = findViewById(R.id.cen_area_super);
        co_rsso_po = findViewById(R.id.co_rsso_po);
        QR_Text = findViewById(R.id.QR);
        date1 = findViewById(R.id.date_1);
        date2 = findViewById(R.id.date_2);
        date3 = findViewById(R.id.date_3);
        date4 = findViewById(R.id.date_4);


        ID = getIntent().getIntExtra("ID", 0);
        String value1 = getIntent().getStringExtra("EnumName");
        String value2 = getIntent().getStringExtra("team_supervisor");
        String value3 = getIntent().getStringExtra("cen_area_super");
        String value4 = getIntent().getStringExtra("co_rsso_po");
        String value5 = getIntent().getStringExtra("qr_code_number");

        String value6 = getIntent().getStringExtra("date_1");
        String value7 = getIntent().getStringExtra("date_2");
        String value8 = getIntent().getStringExtra("date_3");
        String value9 = getIntent().getStringExtra("date_4");

        String value10 = getIntent().getStringExtra("imagePath");
        String value11 = getIntent().getStringExtra("imagePath2");
        String value12 = getIntent().getStringExtra("imagePath3");
        String value13 = getIntent().getStringExtra("imagePath4");

        EnumName.setText(val2+" "+val3);

        last_enum.setText(value1);
        last_superV.setText(value2);

       team_supervisor.setText(value2);
        cen_area_super.setText(value3);
        co_rsso_po.setText(value4);
        QR_Text.setText(value5);

        date1.setText(value6);
        date2.setText(value7);
        date3.setText(value8);
        date4.setText(value9);
        image_path = getIntent().getStringExtra("imagePath");
        image_path2 = getIntent().getStringExtra("imagePath2");
        image_path3 = getIntent().getStringExtra("imagePath3");
        image_path4 = getIntent().getStringExtra("imagePath4");



            Picasso.with(this).load(value10).into(imageView_1);
            Picasso.with(this).load(value11).into(imageView_2);
            /*
            Picasso.with(this).load(value12).into(signImage3);
            Picasso.with(this).load(value13).into(signImage4);
            */

        final Bitmap bitmap = BitmapFactory.decodeFile(image_path);
        signImage.setImageBitmap(bitmap);


        final Bitmap bitmap2 = BitmapFactory.decodeFile(image_path2);
        signImage2.setImageBitmap(bitmap2);

/*
        final Bitmap bitmap3 = BitmapFactory.decodeFile(image_path3);
        signImage3.setImageBitmap(bitmap3);


        final Bitmap bitmap4 = BitmapFactory.decodeFile(image_path4);
        signImage4.setImageBitmap(bitmap4);

*/


        get_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Household_Certification.this,
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
                        Household_Certification.this,
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
                        Household_Certification.this,
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
                        Household_Certification.this,
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
                if (EnumName.getText().toString().length() <= 0) { EnumName.setError("Please Enter Text!"); }

                if (date1.getText().toString().length() <= 0) { date1.setError("Please Enter Date!"); }


                if(EnumName.getText().toString().length() <= 0 || date1.getText().toString().length() <= 0  ){
                    Toast.makeText(Household_Certification.this,"Please Enter Text",Toast.LENGTH_SHORT).show();

                }

                else if (bitmap==null)
                {
                    Toast.makeText(Household_Certification.this,"Please Upload Image",Toast.LENGTH_SHORT).show();
                }

                else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {

                                        JSONObject jsonObject = new JSONObject(response);

                                        String Response = jsonObject.getString("success");
                                        pd.hide();
                                        Toast.makeText(Household_Certification.this, Response, Toast.LENGTH_LONG).show();
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
                            String name1 = EnumName.getText().toString();
                            String name2 = team_supervisor.getText().toString();
                            String name3 = cen_area_super.getText().toString();
                            String name4 = co_rsso_po.getText().toString();
                            String qr_code = QR_Text.getText().toString();
                            String date_1 = date1.getText().toString();
                            String date_2 = date2.getText().toString();
                            String date_3 = date3.getText().toString();
                            String date_4 = date4.getText().toString();


                            if (team_supervisor.getText().toString().length() <= 0 || date2.getText().toString().length() <= 0 || bitmap2 == null){


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
                                params.put("certification_id", String.valueOf(id));
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
                                params.put("certification_id", String.valueOf(id));
                                params.put("EnumName", name1);
                                params.put("signature1", image1);
                                params.put("team_supervisor", name2);
                                params.put("signature2", image2);
                                params.put("cen_area_super", name3);
                                params.put("signature3", "");
                                params.put("co_rsso_po", name4);
                                params.put("signature4", "");
                                params.put("qr_number", qr_code);

                                params.put("date_1", date_1);
                                params.put("date_2", date_2);
                                params.put("date_3", date_3);
                                params.put("date_4", date_4);

                                return params;
                            }

                        }
                    };

                    MySingleton.getInstance(Household_Certification.this).addToRequestQueue(stringRequest);

                }

            }

        });
        signatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signature();
            }
        });
        signatureButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signature2();
            }
        });
        signatureButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signature3();
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

date2.setText(null);
        int ID = getIntent().getIntExtra("ID", 0);
        String value1 = EnumName.getText().toString();
        String value2 = last_superV.getText().toString();
        String value3 = cen_area_super.getText().toString();
        String value4 = co_rsso_po.getText().toString();
        String value5 = "http://10.0.2.2:8089/PSA/android/uploads/.png";
        String value6 = image_path3;
        String value7 = image_path4;
        String value8 = QR_Text.getText().toString();
        String value9 = date1.getText().toString();
        String value10 = date2.getText().toString();
        String value11 = date3.getText().toString();
        String value12 = date4.getText().toString();
        int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        Intent intent1 = new Intent(this, GetSignature.class);
        intent1.putExtra("ID", ID);
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
        intent1.putExtra("update", "update");
        intent1.putExtra("geo_iden_id", geo_iden_id);

        startActivity(intent1);
    }
    private void signature2(){
        int ID = getIntent().getIntExtra("ID", 0);
        String value1 = EnumName.getText().toString();
        String value2 = team_supervisor.getText().toString();
        String value3 = cen_area_super.getText().toString();
        String value4 = co_rsso_po.getText().toString();
        String value5 = image_path;
        String value6 = image_path3;
        String value7 = image_path4;
        String value8 = QR_Text.getText().toString();
        String value9 = date1.getText().toString();
        String value10 = date2.getText().toString();
        String value11 = date3.getText().toString();
        String value12 = date4.getText().toString();
        int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        Intent intent2 = new Intent(this, GetSignature2.class);
        intent2.putExtra("ID", ID);
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
        intent2.putExtra("update", "update");
        intent2.putExtra("geo_iden_id", geo_iden_id);
        startActivity(intent2);

    }
    private void signature3(){
        int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);
        int ID = getIntent().getIntExtra("ID", 0);
        String value1 = EnumName.getText().toString();
        String value2 = team_supervisor.getText().toString();
        String value3 = cen_area_super.getText().toString();
        String value4 = co_rsso_po.getText().toString();
        String value5 = image_path2;
        String value6 = image_path;
        String value7 = image_path4;
        String value8 = QR_Text.getText().toString();
        String value9 = date1.getText().toString();
        String value10 = date2.getText().toString();
        String value11 = date3.getText().toString();
        String value12 = date4.getText().toString();

        Intent intent3 = new Intent(this, GetSignature3.class);
        intent3.putExtra("ID", ID);
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
        intent3.putExtra("update", "update");
        intent3.putExtra("geo_iden_id", geo_iden_id);
        startActivity(intent3);

    }
    private void signature4(){
        int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);
        int ID = getIntent().getIntExtra("ID", 0);
        String value1 = EnumName.getText().toString();
        String value2 = team_supervisor.getText().toString();
        String value3 = cen_area_super.getText().toString();
        String value4 = co_rsso_po.getText().toString();
        String value5 = image_path2;
        String value6 = image_path3;
        String value7 = image_path;
        String value8 = QR_Text.getText().toString();
        String value9 = date1.getText().toString();
        String value10 = date2.getText().toString();
        String value11 = date3.getText().toString();
        String value12 = date4.getText().toString();
        Intent intent4 = new Intent(this, GetSignature4.class);
        intent4.putExtra("ID", ID);
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
        intent4.putExtra("update", "update");
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

    private void getCertificationData() {

        final String url= CertificationForm.UploadURLStatic+"/Android/Household_Certificate.php?qr_number="+  getIntent().getStringExtra("qr_code_number");

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);

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


                                Picasso.with(getApplicationContext()).load(signature1).into(signImage);
                                Picasso.with(getApplicationContext()).load(signature2).into(signImage2);
                                Picasso.with(getApplicationContext()).load(signature3).into(signImage3);
                                Picasso.with(getApplicationContext()).load(signature4).into(signImage4);
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
    }

}

