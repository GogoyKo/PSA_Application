package com.example.kugangka.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kugangka.myapplication.R;
import com.example.kugangka.myapplication.MainHelpers.GMailSender;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ForgotPassword extends Activity {

    EditText etContent, etRecipient;
    Button btnSend;
    String email, pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etRecipient = (EditText)findViewById(R.id.etRecipient);
        btnSend = (Button) findViewById(R.id.btnSend);



        //String url= "http://10.0.2.2/practice1/QData.php?username="+getUser;
        //final String url= "http://172.17.100.2:8089/PSA/android/My_Profile.php?enumerator_id="+IDnumber;



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url= CertificationForm.UploadURLStatic+"/Android/recover_password.php?email="+etRecipient.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonarray = new JSONArray(response);

                                    for(int i=0; i < jsonarray.length(); i++) {

                                        JSONObject jsonobject = jsonarray.getJSONObject(i);


                                        email = jsonobject.getString("email");
                                        pass = jsonobject.getString("password");


                                        final ProgressDialog dialog = new ProgressDialog(ForgotPassword.this);
                                        dialog.setTitle("Sending Email");
                                        dialog.setMessage("Please wait");
                                        dialog.show();
                                        Thread sender = new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    GMailSender sender = new GMailSender("mewho063@gmail.com", "!143mahalkita");
                                                    sender.sendMail("Reset Password",
                                                            "Reset here: psasurveyapp.000webhostapp."+" "+"com/reset.php"+" "+"Your reset code :"+pass,
                                                            "mewho063@gmail.com",
                                                            etRecipient.getText().toString()
                                                    );

                                                    dialog.dismiss();

                                                } catch (Exception e) {
                                                    Log.e("mylog", "Error: " + e.getMessage());
                                                }
                                            }
                                        });
                                        sender.start();

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
        });


    }

    private void sendMessage() {



    }


    private String md5(String in){
        MessageDigest digest;

        try{
            digest  = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++){
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return  sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return  null;

    }

}
