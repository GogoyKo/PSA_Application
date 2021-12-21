package com.example.kugangka.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class qr_change extends AppCompatActivity {

    Button change;
    String QR_Code;
    EditText new_qr;
    TextView current;

    int id_cert, id_geo,id_interview,id_house,id_popu,id_death;

     String QR_Manager = CertificationForm.UploadURLStatic+"/Android/QR_Manager.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_change);

        final Intent intent = getIntent();
        String Fname = intent.getStringExtra("Fname");
        String Lname = intent.getStringExtra("Lname");
        QR_Code = intent.getStringExtra("qr_code_number");

        change = findViewById(R.id.change);
        new_qr = findViewById(R.id.new_qr);
        current = findViewById(R.id.current);

        current.setText("Current QR number: "+QR_Code);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url= CertificationForm.UploadURLStatic+"/Android/QData.php?qr_number="+new_qr.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonarray = new JSONArray(response);
                                    for(int i=0; i < jsonarray.length(); i++) {
                                        JSONObject jsonobject = jsonarray.getJSONObject(i);


                                        String Status =jsonobject.getString("status");
                                      if(Status.equals("1")){
                                              Toast.makeText(qr_change.this, "QR Code already used", Toast.LENGTH_LONG).show();
                                      }else{
                                          String UploadUrl = CertificationForm.UploadURLStatic+"/Android/change_qr.php";
                                          StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String response) {
                                                          try {
                                                              qr_manager();
                                                              JSONObject jsonObject = new JSONObject(response);
                                                              String Response = jsonObject.getString("success");
                                                              Toast.makeText(qr_change.this, Response, Toast.LENGTH_LONG).show();
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
                                                  String value1 = new_qr.getText().toString();
                                                  params.put("qr_number",QR_Code);
                                                  params.put("new_qr", value1);
                                                  return params;
                                              }
                                          };
                                          Intent intent1 = new Intent(qr_change.this, UserDashboard.class);
                                          startActivity(intent1);
                                          finish();
                                          MySingleton.getInstance(qr_change.this).addToRequestQueue(stringRequest);
                                      }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Invalid QR Code.", Toast.LENGTH_SHORT).show();
                                    recreate();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if(error != null){

                                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                                    recreate();
                                }
                            }
                        }
                );
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                return;
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
                            Toast.makeText(qr_change.this, Response, Toast.LENGTH_LONG).show();
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
                params.put("qr_code_number", new_qr.getText().toString());
                return params;
            }
        };
        MySingleton.getInstance(qr_change.this).addToRequestQueue(stringRequest);
    }

}
