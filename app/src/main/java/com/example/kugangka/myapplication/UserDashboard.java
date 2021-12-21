package com.example.kugangka.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputFilter;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class UserDashboard extends AppCompatActivity {


    TextView ID_number;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        final CardView Scan = (CardView) findViewById(R.id.Scan);
        final CardView ConductSurvey = (CardView) findViewById(R.id.ConductSurvey);
        final CardView My_Profile = (CardView) findViewById(R.id.My_Profile);
        final CardView VisitUs = (CardView) findViewById(R.id.VisitUs);
        final CardView About = (CardView) findViewById(R.id.About);
        final CardView Manual = (CardView) findViewById(R.id.Manual);
        final ImageView menu = (ImageView) findViewById(R.id.menu);

        pd = new ProgressDialog(UserDashboard.this);
        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        ID_number = (TextView)findViewById(R.id.ID_number);


        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }





        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Menu = new Intent(UserDashboard.this, Dashboard_Menu.class);
                startActivity(Menu);
            }
        });

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        ID_number.setText(String.valueOf(val1));

        Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Scan = new Intent(UserDashboard.this, ScanQRCode.class);
                startActivity(Scan);
            }
        });
        ConductSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ScanToSurvey = new Intent(UserDashboard.this, ScanToSurvey.class);
                startActivity(ScanToSurvey);
            }
        });
        My_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSqlDetails();
            }
        });

        VisitUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.psa.gov.ph"));
                startActivity(viewIntent);
            }
        });
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent About_App = new Intent(UserDashboard.this, About_App.class);
                startActivity(About_App);
            }
        });

        Manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Manual = new Intent(UserDashboard.this, User_Manual.class);
                startActivity(Manual);
            }
        });


    }





    private void getSqlDetails() {
        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);
        final int val1 = sharedPreferences.getInt("enumerator_id", 0);
        final String my_id = String.valueOf(val1);
        //String url= "http://10.0.2.2/practice1/QData.php?username="+getUser;
       //final String url= "http://172.17.100.2:8089/PSA/android/My_Profile.php?enumerator_id="+IDnumber;
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

                                String id = jsonobject.getString("enumerator_id");
                                String firstname = jsonobject.getString("firstname").trim();
                                String lastname = jsonobject.getString("lastname").trim();
                                String email = jsonobject.getString("email").trim();
                                int age = jsonobject.getInt("age");
                                String mobile = jsonobject.getString("mobile").trim();
                                String gender = jsonobject.getString("gender").trim();
                                String username = jsonobject.getString("username").trim();
                                String image = jsonobject.getString("image");

                                Intent My_Profile = new Intent(UserDashboard.this, My_Profile.class);
                                My_Profile.putExtra("enumerator_id", id);
                                My_Profile.putExtra("firstname", firstname);
                                My_Profile.putExtra("lastname", lastname);
                                My_Profile.putExtra("email", email);
                                My_Profile.putExtra("age", ""+age);
                                My_Profile.putExtra("mobile", mobile);
                                My_Profile.putExtra("gender", gender);
                                My_Profile.putExtra("username", username);
                               My_Profile.putExtra("image", image);

                                UserDashboard.this.startActivity(My_Profile);

                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);

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
