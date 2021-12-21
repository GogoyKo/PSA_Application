package com.example.kugangka.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Death_Update_Form extends AppCompatActivity {

    int rdlyhm_id;
    int death_num;

    String D1;
    String D2 ;
    String Lastname;
    String Firstname;
    String Gender;
    String Age_at_death;
    String Death_reg_d6;
    String Death_reg_d7;
    String qr_number;

    EditText edit_d1, edit_d2, lastname, firstname, edit_gender,
            edit_old_died, edit_register, edit_DCertificate;

    Spinner d1, d2, gender, old_died, register, death_certificate;
    TextView QR;
    Button update_btn;

    private ProgressDialog pd;
    String v1, v2, v3, v4, v5, v6, v7, v8;
    TextView current_1, current_2, current_3, current_4, current_5, current_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death__update__form);

        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        pd = new ProgressDialog(Death_Update_Form.this);
        pd.setMessage("loading");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(true);


        QR = findViewById(R.id.QR);

        edit_d1 = findViewById(R.id.edit_d1);
                d1 = findViewById(R.id.d1);
        edit_d2 = findViewById(R.id.edit_d2);
                d2 = findViewById(R.id.d2);
        lastname = findViewById(R.id.lastname);
                firstname = findViewById(R.id.firstname);
        edit_gender = findViewById(R.id.edit_gender);
                gender = findViewById(R.id.gender);
        edit_old_died = findViewById(R.id.edit_old_died);
                old_died = findViewById(R.id.old_died);
        edit_register = findViewById(R.id.edit_register);
                register = findViewById(R.id.register);
        edit_DCertificate = findViewById(R.id.edit_DCertificate);
                death_certificate = findViewById(R.id.death_certificate);
                update_btn = findViewById(R.id.update_btn);

                current_1 = findViewById(R.id.current_1);
                current_2 = findViewById(R.id.current_2);
                current_3 = findViewById(R.id.current_3);
                current_4 = findViewById(R.id.current_4);
                current_5 = findViewById(R.id.current_5);
                current_6 = findViewById(R.id.current_6);
        edit_d1.setEnabled(false);
        d1.setEnabled(false);





        String QR_Code = getIntent().getStringExtra("ID");



        final String url= CertificationForm.UploadURLStatic+"/Android/Household_Death_ID.php?rdlyhm_id="+QR_Code;

        d1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=d1.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_d1);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        d2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=d2.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_d2);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=gender.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_gender);
                edit.setEnabled(false);
                edit.setText(val1);

            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        old_died.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=old_died.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_old_died);
                edit.setEnabled(false);
                edit.setText(val1);
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        register.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=register.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_register);
                edit.setEnabled(false);
                edit.setText(val1);
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        death_certificate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=death_certificate.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_DCertificate);
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


                                rdlyhm_id = jsonobject.getInt("rdlyhm_id");
                                death_num = jsonobject.getInt("death_num");

                               v1 =  D1 = jsonobject.getString("d1").trim();
                                 v2 =   D2 = jsonobject.getString("d2").trim();
                                v3 =   Lastname = jsonobject.getString("lastname").trim();
                               v4 =   Firstname = jsonobject.getString("firstname").trim();
                                v5 =   Gender = jsonobject.getString("gender").trim();
                                 v6 =   Age_at_death = jsonobject.getString("age_at_death").trim();
                                v7 =   Death_reg_d6 = jsonobject.getString("death_reg_d6").trim();
                               v8 =   Death_reg_d7 = jsonobject.getString("death_reg_d7").trim();
                                qr_number = jsonobject.getString("qr_number").trim();


                                lastname .setText(Lastname);
                                firstname.setText(Firstname);

                             /*   edit_d1.setText(D1);
                                edit_d2.setText(D2);
                                edit_gender.setText(Gender);
                                edit_old_died.setText(Age_at_death);
                                edit_register.setText(Death_reg_d6);
                                edit_DCertificate.setText(Death_reg_d7);
                                */
                                QR.setText(qr_number);

                                current_1.setText(v1);
                                current_2.setText(v2);
                                current_3.setText(v5);

                                current_4.setText(v6);
                                current_5.setText(v7);
                                current_6.setText(v8);



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






        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                String UploadUrl = CertificationForm.UploadURLStatic+"/Android/Household_Death_Update.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    String Response = jsonObject.getString("success");
                                    pd.hide();
                                    Toast.makeText(Death_Update_Form.this, Response, Toast.LENGTH_LONG).show();
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
                        int GEO = geo_iden_id;

                        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

                        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

                        int id = rdlyhm_id;
                        int death_id = death_num;
                        String value1 = v1;
                        String value2 = edit_d2.getText().toString().trim();
                        String value3 = lastname.getText().toString().trim();
                        String value4 = firstname.getText().toString().trim();
                        String value5 = edit_gender.getText().toString().trim();
                        String value6 = edit_old_died.getText().toString().trim();
                        String value7 = edit_register.getText().toString().trim();
                        String value8 = edit_DCertificate.getText().toString().trim();
                        String value9 = qr_number;


                        params.put("enumerator_id", String.valueOf(val1));
                        params.put("geo_iden_id", String.valueOf(GEO));
                        params.put("death_num", String.valueOf(death_id));
                        params.put("rdlyhm_id", String.valueOf(id));
                        params.put("d1", value1);
                        params.put("d2", value2);
                        params.put("lastname", value3);
                        params.put("firstname", value4);
                        params.put("gender", value5);
                        params.put("age_at_death", value6);
                        params.put("death_reg_d6", value7);
                        params.put("death_reg_d7", value8);
                        params.put("qr_number", value9);



                        return params;
                    }
                };
                finish();

                MySingleton.getInstance(Death_Update_Form.this).addToRequestQueue(stringRequest);
            }
        });
    }
}
