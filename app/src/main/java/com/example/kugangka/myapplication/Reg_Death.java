package com.example.kugangka.myapplication;

import android.animation.LayoutTransition;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Reg_Death extends AppCompatActivity {

    private String UploadUrl = CertificationForm.UploadURLStatic+"/Android/reg_death_last2years.php";
    private String QR_Manager = CertificationForm.UploadURLStatic+"/Android/QR_Manager.php";
    Button Proceed, Add;
    Spinner D1, D2,
            Gender,
            AgeDeath,
            DeathReg_D6,
            DeathReg_D7;
    EditText Lastname, Firstname, Edit_gender, Edit_old_died, Edit_register, Edit_DCertificate, Edit_d1, Edit_d2;
    TextView QR;
    private ProgressDialog pd;
    LinearLayout m_layout;
    ImageView back;
    String d1, d2;
    LinearLayout container;

    String SQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg__death);


        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        pd = new ProgressDialog(Reg_Death.this);
        pd.setMessage("loading");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(true);


        m_layout = findViewById(R.id.m_layout);
        m_layout.setVisibility(View.GONE);

        SQ = getIntent().getStringExtra("SQ");
        final String d2_value = getIntent().getStringExtra("D2");



        container = (LinearLayout)findViewById(R.id.container);

        QR = findViewById(R.id.QR);

       final String qr_code = getIntent().getStringExtra("qr_code_number");
        QR.setText(qr_code);

        D1 = (Spinner) findViewById(R.id.d1);
        D2 = (Spinner) findViewById(R.id.d2);
        Lastname = (EditText) findViewById(R.id.lastname);

        Edit_gender = findViewById(R.id.edit_gender);
        Edit_old_died = findViewById(R.id.edit_old_died);
        Edit_register = findViewById(R.id.edit_register);
        Edit_DCertificate = findViewById(R.id.edit_DCertificate);
        Edit_d1 = findViewById(R.id.edit_d1);
        Edit_d2 = findViewById(R.id.edit_d2);

        //Todo to Test lang

        back = findViewById(R.id.back);

        Firstname = (EditText) findViewById(R.id.firstname);
        Gender = (Spinner) findViewById(R.id.gender);
        AgeDeath = (Spinner) findViewById(R.id.old_died);
        DeathReg_D6 = (Spinner) findViewById(R.id.register);
        DeathReg_D7 = (Spinner) findViewById(R.id.death_certificate);
        Proceed = findViewById(R.id.Proceed);
        Add = findViewById(R.id.Add);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SQ.equals("SQ")){
                    int GEO = geo_iden_id;
                    Intent intent = new Intent(Reg_Death.this, Survey_Questions.class);
                    intent.putExtra("qr_code_number", qr_code);
                    intent.putExtra("geo_iden_id", GEO);
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
                                                Intent SurveyForms = new Intent(Reg_Death.this, SurveyForms.class);
                                                SurveyForms.putExtra("Lname", Lname);
                                                SurveyForms.putExtra("Fname", Fname);
                                                SurveyForms.putExtra("qr_code_number", QR);
                                                SurveyForms.putExtra("geo_iden_id", GEO);
                                                Reg_Death.this.startActivity(SurveyForms);
                                                finish();
                                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                            }
                                        } catch (JSONException e) {


                                            int GEO = geo_iden_id;
                                            Intent SurveyForms = new Intent(Reg_Death.this, SurveyForms.class);
                                            SurveyForms.putExtra("qr_code_number", qr_code);
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


        Proceed.setOnClickListener(new View.OnClickListener() {
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
                                            String QR = qr_code;
                                            int GEO = geo_iden_id;
                                            Intent SurveyForms = new Intent(Reg_Death.this, SurveyForms.class);
                                            SurveyForms.putExtra("Lname", Lname);
                                            SurveyForms.putExtra("Fname", Fname);
                                            SurveyForms.putExtra("qr_code_number", QR);
                                            SurveyForms.putExtra("geo_iden_id", GEO);


                                            Reg_Death.this.startActivity(SurveyForms);
                                            //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                        }
                                    } catch (JSONException e) {


                                        int GEO = geo_iden_id;
                                        Intent SurveyForms = new Intent(Reg_Death.this, SurveyForms.class);
                                        SurveyForms.putExtra("qr_code_number", qr_code);
                                        SurveyForms.putExtra("Fname", "Geographic identification not filled");
                                        SurveyForms.putExtra("Lname", "Geographic identification not filled");
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

            }
        });




        D1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

               String val1= D1.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_d1);
                edit.setEnabled(false);
                edit.setText(val1);

                if(D1.getSelectedItem().toString().equals("No, END INTERVIEW")){
                    edit.setText(val1);
                    D2.setSelection(0, true);
                    D1.setSelection(0, true);
                    Gender.setSelection(0, true);
                    AgeDeath.setSelection(0, true);
                    DeathReg_D6.setSelection(0, true);
                    DeathReg_D7.setSelection(0, true);
                    EditText edit_d2 = findViewById(R.id.edit_d2);
                    EditText edit_gender = findViewById(R.id.edit_gender);
                    EditText edit_old_died = findViewById(R.id.edit_old_died);
                    EditText edit_register = findViewById(R.id.edit_register);
                    EditText edit_DCertificate = findViewById(R.id.edit_DCertificate);
                    edit_d2.setText(null);
                    edit_gender.setText(null);
                    edit_old_died.setText(null);
                    edit_register.setText(null);
                    edit_DCertificate.setText(null);
                    Lastname.setText(null);
                    Lastname.setEnabled(false);
                    Firstname.setText(null);
                    Firstname.setEnabled(false);

                    Intent intent = new Intent(Reg_Death.this, Death_Member.class);
                    intent.putExtra("qr_code_number", qr_code);
                    intent.putExtra("SQ", SQ);
                    startActivity(intent);
                    finish();

                }else if(D1.getSelectedItem().toString().equals("Yes")) {
                    m_layout.setVisibility(View.VISIBLE);

                }
                else{
                    Lastname.setEnabled(true);
                    Firstname.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        D2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                 String val1=D2.getSelectedItem().toString();
                 EditText edit = findViewById(R.id.edit_d2);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=Gender.getSelectedItem().toString();
                EditText edit = findViewById(R.id.edit_gender);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        AgeDeath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                 String val1=AgeDeath.getSelectedItem().toString();
                  EditText edit = findViewById(R.id.edit_old_died);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        DeathReg_D6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=DeathReg_D6.getSelectedItem().toString();
                 EditText edit = findViewById(R.id.edit_register);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        DeathReg_D7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String val1=DeathReg_D7.getSelectedItem().toString();
                 EditText edit = findViewById(R.id.edit_DCertificate);
                edit.setEnabled(false);
                edit.setText(val1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });









        Add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                final Integer[] a = {1};



                // Todo Validate All Fields if Empty or not
                if (Lastname.getText().toString().length() <= 0 || Firstname.getText().toString().length() <= 0) {
                    Toast.makeText(Reg_Death.this, "Empty field", Toast.LENGTH_SHORT).show();
                }
                else if( Edit_gender.getText().toString().length() <= 0 || Edit_old_died.getText().toString().length() <= 0 ||
                        Edit_register.getText().toString().length() <= 0 || Edit_DCertificate.getText().toString().length() <= 0 ||
                        Edit_d1.getText().toString().length() <= 0 || Edit_d2.getText().toString().length() <= 0){
                    Toast.makeText(Reg_Death.this, "Please select item below", Toast.LENGTH_SHORT).show();
                }

                // Todo Validate Each Fields if Empty or not
                else if (Lastname.getText().toString().length() <= 0) {
                    Lastname.setError("Please enter lastname");
                }
                else if (Firstname.getText().toString().length() <= 0) {
                    Firstname.setError("Please enter firstname");
                }
                else {

                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.death_field, null);

                // Todo Declare Items

                final EditText lastname = addView.findViewById(R.id.lastname);
                final EditText firstname = addView.findViewById(R.id.firstname);
                final EditText edit_gender = addView.findViewById(R.id.edit_gender);
                final EditText edit_old_death = addView.findViewById(R.id.edit_old_death);
                final EditText edit_register = addView.findViewById(R.id.edit_register);
                final EditText edit_DCertificate = addView.findViewById(R.id.edit_DCertificate);

                //final EditText lastname = addView.findViewById(R.id.lastname);
                //final EditText firstname = addView.findViewById(R.id.firstname);


                // Todo Get Text Value

                lastname.setText(Lastname.getText().toString());
                firstname.setText(Firstname.getText().toString());
                edit_gender.setText(Edit_gender.getText().toString());
                edit_old_death.setText(Edit_old_died.getText().toString());
                edit_register.setText(Edit_register.getText().toString());
                edit_DCertificate.setText(Edit_DCertificate.getText().toString());
                d1 = Edit_d1.getText().toString();
                d2 = Edit_d2.getText().toString();

                // lastname.setText(Lastname.getText().toString());
                // firstname.setText(Firstname.getText().toString());


                // Todo Set EditText to blank
                Lastname.setText(null);
                Firstname.setText(null);
                Edit_gender.setText(null);
                Edit_old_died.setText(null);
                Edit_register.setText(null);
                Edit_DCertificate.setText(null);



                Gender.setSelection(0, true);
                AgeDeath.setSelection(0, true);
                DeathReg_D6.setSelection(0, true);
                DeathReg_D7.setSelection(0, true);


                // Todo Create Remove Button
                final Button buttonRemove = (Button) addView.findViewById(R.id.remove_btn);
                buttonRemove.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                     a[0] = a[0] -1;
                        ((LinearLayout) addView.getParent()).removeView(addView);
                    }
                });


                // Todo Create Edit Button
                final Button buttonEdit = (Button) addView.findViewById(R.id.edit_btn);
                buttonEdit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Lastname.setText(lastname.getText().toString());
                        Firstname.setText(firstname.getText().toString());
                        Edit_gender.setText(edit_gender.getText().toString());
                        Edit_old_died.setText(edit_old_death.getText().toString());
                        Edit_register.setText(edit_register.getText().toString());
                        Edit_DCertificate.setText(edit_DCertificate.getText().toString());
                        Edit_d1.setText(d1);
                        Edit_d2.setText(d2);


                        ((LinearLayout) addView.getParent()).removeView(addView);

                    }
                });


                // Todo Create Save Button
                final Button buttonSave = (Button) addView.findViewById(R.id.save_btn);
                buttonSave.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        pd.show();

                        // Todo Disable Buttons
                        buttonEdit.setEnabled(false);
                        buttonSave.setEnabled(false);
                        buttonRemove.setEnabled(false);

                        Edit_d1.setEnabled(false);
                        Edit_d2.setEnabled(false);
                        D1.setEnabled(false);
                        D2.setEnabled(false);


                        StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        try {
                                            qr_manager();
                                            JSONObject jsonObject = new JSONObject(response);
pd.hide();
                                            String Response = jsonObject.getString("success");

                                            Toast.makeText(Reg_Death.this, Response, Toast.LENGTH_LONG).show();



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

                                  /*  String lastname = Lastname.getText().toString();
                                    String firstname = Firstname.getText().toString();
                                    String gender = Gender.getSelectedItem().toString();
                                    String agedeath = AgeDeath.getSelectedItem().toString();
                                    String deathreg_d6 = DeathReg_D6.getSelectedItem().toString();
                                    String deathreg_d7 = DeathReg_D7.getSelectedItem().toString();
                                    */

                                EditText lastname = addView.findViewById(R.id.lastname);
                                EditText firstname = addView.findViewById(R.id.firstname);
                                EditText edit_gender = addView.findViewById(R.id.edit_gender);
                                EditText edit_old_death = addView.findViewById(R.id.edit_old_death);
                                EditText edit_register = addView.findViewById(R.id.edit_register);
                                EditText edit_DCertificate = addView.findViewById(R.id.edit_DCertificate);


                                final String QR_Code = QR.getText().toString();

                                params.put("enumerator_id", String.valueOf(val1));
                                params.put("geo_iden_id", String.valueOf(GEO));
                                params.put("d1", d1);
                                params.put("d2", d2);
                                params.put("lastname", lastname.getText().toString());
                                params.put("firstname", firstname.getText().toString());
                                params.put("gender", edit_gender.getText().toString());
                                params.put("age_at_death", edit_old_death.getText().toString());
                                params.put("death_reg_d6", edit_register.getText().toString());
                                params.put("death_reg_d7", edit_DCertificate.getText().toString());
                                params.put("qr_number", QR_Code);


                                return params;

                            }
                        };
                        MySingleton.getInstance(Reg_Death.this).addToRequestQueue(stringRequest);

                    }
                });


                container.addView(addView, 0);
            }
            }});

        LayoutTransition transition = new LayoutTransition();
        container.setLayoutTransition(transition);















    }

    public void qr_manager(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, QR_Manager,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            String Response = jsonObject.getString("success");
                            Toast.makeText(Reg_Death.this, Response, Toast.LENGTH_LONG).show();

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
        MySingleton.getInstance(Reg_Death.this).addToRequestQueue(stringRequest);


    }
}
