package com.example.kugangka.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Household_Housing extends AppCompatActivity {
    EditText edit_b1, edit_b2, edit_b3, edit_h1,
            edit_h2, edit_h3, edit_h4;
    Button btn_update;
    Spinner b1, b2, b3, h1, h2, h3, h4;
    TextView current,current_2,current_3,current_4,current_5,current_6,current_7;

    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household__housing);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);
        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        pd = new ProgressDialog(Household_Housing.this);
        pd.setMessage("loading");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(true);

        final int ID = getIntent().getIntExtra("ID", 0);
        String value1 = getIntent().getStringExtra("b1");
        String value2 = getIntent().getStringExtra("b2");
        String value3 = getIntent().getStringExtra("b3");
        String value4 = getIntent().getStringExtra("h1");
        String value5 = getIntent().getStringExtra("h2");
        String value6 = getIntent().getStringExtra("h3");
        String value7 = getIntent().getStringExtra("h4");
        final String qr_code_number = getIntent().getStringExtra("qr_code_number");


        edit_b1 = findViewById(R.id.edit_b1);
        edit_b2 = findViewById(R.id.edit_b2);
        edit_b3 = findViewById(R.id.edit_b3);
        edit_h1 = findViewById(R.id.edit_h1);
        edit_h2 = findViewById(R.id.edit_h2);
        edit_h3 = findViewById(R.id.edit_h3);
        edit_h4 = findViewById(R.id.edit_h4);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        h1 = findViewById(R.id.h1);
        h2 = findViewById(R.id.h2);
        h3 = findViewById(R.id.h3);
        h4 = findViewById(R.id.h4);

        current = findViewById(R.id.current_1);
        current_2 = findViewById(R.id.current_2);
        current_3 = findViewById(R.id.current_3);
        current_4 = findViewById(R.id.current_4);
        current_5 = findViewById(R.id.current_5);
        current_6 = findViewById(R.id.current_6);
        current_7 = findViewById(R.id.current_7);




        current.setText(value1);
        current_2.setText(value2);
        current_3.setText(value3);
        current_4.setText(value4);
        current_5.setText(value5);
        current_6.setText(value6);
        current_7.setText(value7);


        btn_update = findViewById(R.id.btn_update);

        b1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=b1.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_b1);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        b2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=b2.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_b2);
                edit.setEnabled(false);
                edit.setText(val1);
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        b3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=b3.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_b3);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        h1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=h1.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_h1);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        h2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=h2.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_h2);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        h3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=h3.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_h3);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        h4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=h4.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_h4);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                if (edit_b1.getText().toString().length() <= 0 || edit_b2.getText().toString().length() <= 0
                        || edit_b3.getText().toString().length() <= 0 || edit_h1.getText().toString().length() <= 0
                        || edit_h2.getText().toString().length() <= 0 || edit_h3.getText().toString().length() <= 0
                        || edit_h4.getText().toString().length() <= 0) {

                    Toast.makeText(getApplicationContext(), "Empty  Fields", Toast.LENGTH_LONG).show();
                } else {

                    String UploadUrl = CertificationForm.UploadURLStatic+"/Android/Household_House_Update.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);

                                        String Response = jsonObject.getString("success");
                                        pd.hide();
                                        Toast.makeText(Household_Housing.this, Response, Toast.LENGTH_LONG).show();
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
                            String value1 = edit_b1.getText().toString();
                            String value2 = edit_b2.getText().toString();
                            String value3 = edit_b3.getText().toString();
                            String value4 = edit_h1.getText().toString();
                            String value5 = edit_h2.getText().toString();
                            String value6 = edit_h3.getText().toString();
                            String value7 = edit_h4.getText().toString();
                            String value8 = qr_code_number;


                            params.put("enumerator_id", String.valueOf(val1));
                            params.put("house_cen_ques_id", String.valueOf(id));
                            params.put("geo_iden_id", String.valueOf(GEO));
                            params.put("b1", value1);
                            params.put("b2", value2);
                            params.put("b3", value3);
                            params.put("h1", value4);
                            params.put("h2", value5);
                            params.put("h3", value6);
                            params.put("h4", value7);
                            params.put("qr_number", value8);


                            return params;
                        }
                    };
                    finish();

                    MySingleton.getInstance(Household_Housing.this).addToRequestQueue(stringRequest);
                }
            }
        });

    }
}
