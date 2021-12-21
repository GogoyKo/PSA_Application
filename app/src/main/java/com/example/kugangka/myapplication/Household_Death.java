package com.example.kugangka.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Household_Death extends Activity {
    private GridView lvProduct;
    private DeathListAdapter adapter;
    private List<Death_Gridview_Items> mDeathGridviewItemsList;

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
    Button show;

    ImageView add_member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household__death);

        final String QR_Code = getIntent().getStringExtra("qr_code_number");
        lvProduct = (GridView) findViewById(R.id.listview_product);

        add_member = findViewById(R.id.add_member);
        show = findViewById(R.id.show);

        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeathGridviewItemsList = new ArrayList<>();


                final String url= CertificationForm.UploadURLStatic+"/Android/Household_Death.php?qr_number="+ QR_Code;

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

                                        D1 = jsonobject.getString("d1").trim();
                                        D2 = jsonobject.getString("d2").trim();
                                        Lastname = jsonobject.getString("lastname").trim();
                                        Firstname = jsonobject.getString("firstname").trim();
                                        Gender = jsonobject.getString("gender").trim();
                                        Age_at_death = jsonobject.getString("age_at_death").trim();
                                        Death_reg_d6 = jsonobject.getString("death_reg_d6").trim();
                                        Death_reg_d7 = jsonobject.getString("death_reg_d7").trim();
                                        qr_number = jsonobject.getString("qr_number").trim();

                                        mDeathGridviewItemsList.add(new Death_Gridview_Items(rdlyhm_id, D1, D2,Firstname, Lastname,Gender,Age_at_death,Death_reg_d6,Death_reg_d7));
                                        adapter = new DeathListAdapter(getApplicationContext(), mDeathGridviewItemsList);
                                        lvProduct.setAdapter(adapter);


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





        add_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int GEO = geo_iden_id;
                String qr_number = QR_Code;
                Intent intent = new Intent(Household_Death.this, Reg_Death.class);
                intent.putExtra("qr_code_number", qr_number);
                intent.putExtra("SQ", "SF");
                intent.putExtra("D2", D2);
                intent.putExtra("geo_iden_id", GEO);
                startActivity(intent);
            }
        });

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Household_Death.this,Death_Update_Form.class);

                intent.putExtra("ID",view.getTag().toString());
                intent.putExtra("geo_iden_id", geo_iden_id);
                startActivity(intent);
            }
        });
    }
}
