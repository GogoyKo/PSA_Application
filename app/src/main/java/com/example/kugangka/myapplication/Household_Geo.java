package com.example.kugangka.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Household_Geo extends AppCompatActivity {

    EditText booklet_1, booklet_2, province, province_num1, municipal, municipal_num1, barangay,
    barangay_num1, enumerator_num1, building_num1, house_num1, household_num1, line_num1, lastname,
            firstname, address;
    private ProgressDialog pd;
    Button update_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household__geo);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        pd = new ProgressDialog(Household_Geo.this);
        pd.setMessage("loading");
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(true);


        final int ID = getIntent().getIntExtra("ID", 0);

        update_btn = findViewById(R.id.update_btn);

        province = findViewById(R.id.province);
        province_num1 = findViewById(R.id.province_num1);
        municipal = findViewById(R.id.municipal);
        municipal_num1 = findViewById(R.id.municipal_num1);
        barangay = findViewById(R.id.barangay);
        barangay_num1 = findViewById(R.id.barangay_num1);
        enumerator_num1 = findViewById(R.id.enumerator_num1);
        building_num1 = findViewById(R.id.building_num1);
        house_num1 = findViewById(R.id.house_num1);
        household_num1 = findViewById(R.id.household_num1);
        line_num1 = findViewById(R.id.line_num1);
        lastname = findViewById(R.id.lastname);
        firstname = findViewById(R.id.firstname);
        address = findViewById(R.id.address);

        //Delete ni

        //Delete ni
        String value3 = getIntent().getStringExtra("province");
        String value4 = getIntent().getStringExtra("province_num");
        String value5 = getIntent().getStringExtra("city");
        String value6 = getIntent().getStringExtra("city_num");
        String value7 = getIntent().getStringExtra("brgy");
        String value8 = getIntent().getStringExtra("brgy_num");
        String value9 = getIntent().getStringExtra("EAN");
        String value10 = getIntent().getStringExtra("BSN");
        String value11= getIntent().getStringExtra("HUSN");
        String value12 = getIntent().getStringExtra("HSN");
        String value13 = getIntent().getStringExtra("LNTR");
        String value14 = getIntent().getStringExtra("Lname");
        String value15 = getIntent().getStringExtra("Fname");
        String value16 = getIntent().getStringExtra("Address");
        final String value17 = getIntent().getStringExtra("qr_code_number");

        //Delete ni

        //Delete ni
        province.setText(value3);
        province_num1.setText(value4);
        municipal.setText(value5);
        municipal_num1.setText(value6);
        barangay.setText(value7);
        barangay_num1.setText(value8);
        enumerator_num1.setText(value9);
        building_num1.setText(value10);
        house_num1.setText(value11);
        household_num1.setText(value12);
        line_num1.setText(value13);
        lastname.setText(value14);
        firstname.setText(value15);
        address.setText(value16);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                if (province.getText().toString().length() <= 0 || province_num1.getText().toString().length() <= 0
                        || municipal.getText().toString().length() <= 0 || municipal_num1.getText().toString().length() <= 0
                        || barangay.getText().toString().length() <= 0 || barangay_num1.getText().toString().length() <= 0
                        || enumerator_num1.getText().toString().length() <= 0 || building_num1.getText().toString().length() <= 0
                        || house_num1.getText().toString().length() <= 0 || building_num1.getText().toString().length() <= 0
                        || line_num1.getText().toString().length() <= 0 || lastname.getText().toString().length() <= 0
                        || firstname.getText().toString().length() <= 0 || address.getText().toString().length() <= 0) {

                    Toast.makeText(getApplicationContext(), "Empty  Fields", Toast.LENGTH_LONG).show();
                } else {

                    String UploadUrl = CertificationForm.UploadURLStatic+"/Android/Household_GeoIden_Update.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);

                                        String Response = jsonObject.getString("success");
                                        pd.hide();
                                        Toast.makeText(Household_Geo.this, Response, Toast.LENGTH_LONG).show();
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



                            int id = ID;
                            String value1 = province.getText().toString();
                            String value2 = province_num1.getText().toString();
                            String value3 = municipal.getText().toString();
                            String value4 = municipal_num1.getText().toString();
                            String value5 = barangay.getText().toString();
                            String value6 = barangay_num1.getText().toString();
                            String value7 = enumerator_num1.getText().toString();
                            String value8 = building_num1.getText().toString();
                            String value9 = house_num1.getText().toString();
                            String value10 = household_num1.getText().toString();
                            String value11 = line_num1.getText().toString();
                            String value12 = lastname.getText().toString();
                            String value13 = firstname.getText().toString();
                            String value14 = address.getText().toString();


                            String value15 = value17;



                            params.put("enumerator_id", String.valueOf(val1));
                            params.put("geo_iden_id", String.valueOf(id));
                            params.put("province", value1);
                            params.put("province_num", value2);
                            params.put("city", value3);
                            params.put("city_num", value4);
                            params.put("brgy", value5);
                            params.put("brgy_num", value6);
                            params.put("EAN", value7);
                            params.put("BSN", value8);
                            params.put("HUSN", value9);
                            params.put("HSN", value10);
                            params.put("LNTR", value11);
                            params.put("Lname", value12);
                            params.put("Fname", value13);
                            params.put("Address", value14);
                            params.put("qr_number", value15);




                            return params;
                        }
                    };
                    finish();

                    MySingleton.getInstance(Household_Geo.this).addToRequestQueue(stringRequest);
                }
            }
        });

    }
}
