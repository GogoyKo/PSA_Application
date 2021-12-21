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
import android.widget.TextView;
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


public class Household_Population extends Activity {
    private GridView lvProduct;
    private PopulationListAdapter adapter;
    private List<Population_Gridview_Items> mPopulationGridviewItemsList;
    Button btn_show;

    ImageView add_member;


    int pop_id;

    String p1;
    String p_1 ;
    String p2;
    String p3;
    String month_p4;
    String p5;
    String p6;
    String p7;
    String p8;
    String p9;
    String p10;
    String p11;
    String p12 ;
    String p13 ;
    String p14;
    String p15;
    String p16;
    String qr_number;

    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household__population);

        lvProduct = (GridView)findViewById(R.id.listview_product);
        btn_show = findViewById(R.id.btn_show);

        add_member = findViewById(R.id.add_member);
        show = findViewById(R.id.show);

        final String QR_Code = getIntent().getStringExtra("qr_code_number");

        final int geo_iden_id = getIntent().getIntExtra("geo_iden_id",0);

        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopulationGridviewItemsList = new ArrayList<>();

                final String url= CertificationForm.UploadURLStatic+"/Android/Household_Population.php?qr_number="+ QR_Code;

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonarray = new JSONArray(response);

                                    for(int i=0; i < jsonarray.length(); i++) {


                                        JSONObject jsonobject = jsonarray.getJSONObject(i);


                                        pop_id = jsonobject.getInt("popu_cen_ques_id");

                                        p1 = jsonobject.getString("lastname_p1").trim();
                                        p_1 = jsonobject.getString("firstname_p1").trim();
                                        p2 = jsonobject.getString("p2").trim();
                                        p3 = jsonobject.getString("p3").trim();
                                        month_p4 = jsonobject.getString("month_p4").trim();
                                        p5 = jsonobject.getString("p5").trim();
                                        p6 = jsonobject.getString("p6").trim();
                                        p7 = jsonobject.getString("p7").trim();
                                        p8 = jsonobject.getString("p8").trim();
                                        p9 = jsonobject.getString("p9").trim();
                                        p10 = jsonobject.getString("p10").trim();
                                        p11 = jsonobject.getString("p11").trim();
                                        p12 = jsonobject.getString("p12").trim();
                                        p13 = jsonobject.getString("p13").trim();
                                        p14 = jsonobject.getString("p14").trim();
                                        p15 = jsonobject.getString("p15").trim();
                                        p16 = jsonobject.getString("p16").trim();
                                        qr_number = jsonobject.getString("qr_number").trim();


                                        //Add sample data for list

                                        mPopulationGridviewItemsList.add(new Population_Gridview_Items(pop_id,  p1, p_1,  p2, p3, month_p4,
                                                p5, p6,p7, p8, p9, p10,p11, p12, p13,
                                                p14,p15,p16, qr_number));
                                        //  mPopulationGridviewItemsList.add(new Population_Gridview_Items(4, "iPhone5", 300, "Apple iPhone5 16GB"));

                                        //Init adapter
                                        adapter = new PopulationListAdapter(getApplicationContext(), mPopulationGridviewItemsList);
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
                Intent intent = new Intent(Household_Population.this, Population_Census_Question.class);
                intent.putExtra("qr_code_number", qr_number);
                intent.putExtra("SQ", "SF");
                intent.putExtra("geo_iden_id", GEO);
                startActivity(intent);
            }
        });


        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Household_Population.this,Population_Update_Form.class);

                intent.putExtra("ID",view.getTag().toString());
                intent.putExtra("geo_iden_id", geo_iden_id);


                startActivity(intent);
            }
        });




    }



}
