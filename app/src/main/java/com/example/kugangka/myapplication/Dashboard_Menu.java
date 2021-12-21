package com.example.kugangka.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_menu);

        TextView logout = (TextView) findViewById(R.id.logout);
        TextView manage = (TextView) findViewById(R.id.manage);
        TextView change_pass = (TextView) findViewById(R.id.change_pass);
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(Dashboard_Menu.this).logout();
                finish();
                startActivity(new Intent(Dashboard_Menu.this, MainActivity.class));
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSqlDetails();
                finish();


            }
        });

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://psasurveyapp.000webhostapp.com/change_password.php"));
                startActivity(viewIntent);
            }
        });

    }

    private void getSqlDetails() {
        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);
        final int val1 = sharedPreferences.getInt("enumerator_id", 0);
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
                                String email = jsonobject.getString("email").trim();
                                String mobile = jsonobject.getString("mobile").trim();
                                String username = jsonobject.getString("username").trim();
                                String image = jsonobject.getString("image");

                                Intent My_Profile = new Intent(Dashboard_Menu.this, Manage_Account.class);

                                My_Profile.putExtra("firstname", firstname);
                                My_Profile.putExtra("lastname", lastname);
                                My_Profile.putExtra("email", email);
                                My_Profile.putExtra("mobile", mobile);
                                My_Profile.putExtra("username", username);
                                My_Profile.putExtra("image", image);

                                Dashboard_Menu.this.startActivity(My_Profile);


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
