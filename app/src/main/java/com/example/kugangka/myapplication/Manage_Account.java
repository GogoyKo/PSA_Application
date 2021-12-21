package com.example.kugangka.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Manage_Account extends AppCompatActivity {

    private String UploadUrl = CertificationForm.UploadURLStatic+"/Android/Manage_User.php";
    TextView Name,Email,Gender,Age,Username, Mobile;
    String firstname,lastname, email,username, mobile, Image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__account);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        Button Update = findViewById(R.id.update);
        final ImageView img1 = findViewById(R.id.Img1);

        EditText Firstname = findViewById(R.id.firstname);
        EditText Lastname = findViewById(R.id.lastname);
        final EditText Username = findViewById(R.id.username);
        final EditText Email = findViewById(R.id.email);
        final EditText Number = findViewById(R.id.number);

        Intent intent = getIntent();

        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        email = intent.getStringExtra("email");
        mobile = intent.getStringExtra("mobile");
        username = intent.getStringExtra("username");
        Image1 = intent.getStringExtra("image");



        Firstname.setText(firstname);
        Lastname .setText(lastname);
        Email.setText(email);
        Number.setText(mobile);
        Username .setText(username);
        Picasso.with(getApplicationContext()).load(Image1).into(img1);


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);
                final int val1 = sharedPreferences.getInt("enumerator_id", 0);
                final String my_id = String.valueOf(val1);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    String Response = jsonObject.getString("success");
                                    Toast.makeText(Manage_Account.this, Response, Toast.LENGTH_LONG).show();

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

                        String id = my_id;
                        String email = Email.getText().toString();
                        String mobile = Number.getText().toString();
                        String username = Username.getText().toString();

                        params.put("enumerator_id", id);
                        params.put("email", email);
                        params.put("mobile", mobile);
                        params.put("username", username);

                        return params;
                    }
                };
                MySingleton.getInstance(Manage_Account.this).addToRequestQueue(stringRequest);
            }
        });
    }


}
