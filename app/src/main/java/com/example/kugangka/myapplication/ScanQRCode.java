package com.example.kugangka.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ScanQRCode extends AppCompatActivity {

    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    String getUser;
    String intentData = "";
    boolean isEmail = false;
    private ProgressDialog pd;
    EditText QR_Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        final SharedPreferences sharedPreferences = getSharedPreferences("mysharepref12", Context.MODE_PRIVATE);

        final int val1 = sharedPreferences.getInt("enumerator_id", 0);

        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        surfaceView = findViewById(R.id.surfaceView);
        Button proceed = findViewById(R.id.Proceed);
        QR_Code = findViewById(R.id.qr_code);


        pd = new ProgressDialog(ScanQRCode.this);
        pd.setMessage("loading");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url= CertificationForm.UploadURLStatic+"/Android/QData.php?qr_number="+QR_Code.getText().toString().trim();
                pd.show();

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    pd.hide();
                                    JSONArray jsonarray = new JSONArray(response);
                                    for(int i=0; i < jsonarray.length(); i++) {
                                        JSONObject jsonobject = jsonarray.getJSONObject(i);

                                        String Qr_Code=jsonobject.getString("qr_code_number");
                                        String Status =jsonobject.getString("status");
                                        txtBarcodeValue.setText(Qr_Code);

                                        if(Status.equals("1")){
                                            try {
                                                final String url = CertificationForm.UploadURLStatic+"/Android/Household_GeoIden.php?qr_number=" + QR_Code.getText().toString().trim();
                                                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                                                        url,
                                                        new Response.Listener<String>() {
                                                            @Override
                                                            public void onResponse(String response) {
                                                                try {
                                                                    JSONArray jsonarray = new JSONArray(response);
                                                                    for (int i = 0; i < jsonarray.length(); i++) {
                                                                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                                                                        int id = jsonobject.getInt("geo_iden_id");
                                                                        String Lname = jsonobject.getString("Lname");
                                                                        String Fname = jsonobject.getString("Fname");
                                                                        String QR = jsonobject.getString("qr_number");
                                                                        Intent Household_Geo = new Intent(ScanQRCode.this, SurveyForms.class);
                                                                        Household_Geo.putExtra("Lname", Lname);
                                                                        Household_Geo.putExtra("Fname", Fname);
                                                                        Household_Geo.putExtra("qr_code_number", QR);


                                                                        ScanQRCode.this.startActivity(Household_Geo);
                                                                        //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                                                    }
                                                                } catch (JSONException e) {


                                                                    final String url= CertificationForm.UploadURLStatic+"/Android/QData.php?qr_number="+QR_Code.getText().toString().trim();
                                                                    pd.show();

                                                                    StringRequest stringRequest = new StringRequest(Request.Method.GET,
                                                                            url,
                                                                            new Response.Listener<String>() {
                                                                                @Override
                                                                                public void onResponse(String response) {
                                                                                    try {
                                                                                        pd.hide();
                                                                                        JSONArray jsonarray = new JSONArray(response);
                                                                                        for(int i=0; i < jsonarray.length(); i++) {
                                                                                            JSONObject jsonobject = jsonarray.getJSONObject(i);

                                                                                            String Fname= "No Geographic identification";
                                                                                            String Lname= "No Geographic identification";
                                                                                            String Qr_Code=jsonobject.getString("qr_code_number");

                                                                                            //txtBarcodeValue.setText(Fname);

                                                                                            Intent userdashboard = new Intent(ScanQRCode.this, SurveyForms.class);
                                                                                            userdashboard.putExtra("Fname", Fname);
                                                                                            userdashboard.putExtra("Lname", Lname);
                                                                                            userdashboard.putExtra("qr_code_number", Qr_Code);

                                                                                            finish();
                                                                                            startActivity(userdashboard);


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
                                                            }
                                                        },
                                                        new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {
                                                                if (error != null) {

                                                                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                                                                    recreate();
                                                                }
                                                            }
                                                        }
                                                );

                                                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                                            }catch (Exception ex){

                                                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                                                recreate();
                                            }

                                          /*  Intent userdashboard = new Intent(ScanQRCode.this, SurveyForms.class);
                                            userdashboard.putExtra("qr_code_number", Qr_Code);
                                            finish();
                                            startActivity(userdashboard);
                                            */
                                        }else {
                                            Toast.makeText(getApplicationContext(), "QR code not used.", Toast.LENGTH_SHORT).show();
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

    private void initialiseDetectorsAndSources() {



        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScanQRCode.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(ScanQRCode.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    txtBarcodeValue.post(new Runnable() {

                        @Override
                        public void run() {
                            cameraSource.stop();
                            intentData = barcodes.valueAt(0).displayValue.trim();
                            getSqlDetails();

                        }
                    });
                }
            }
        });
    }
    private void getSqlDetails() {

        final String url= CertificationForm.UploadURLStatic+"/Android/QData.php?qr_number="+intentData;
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pd.hide();
                            JSONArray jsonarray = new JSONArray(response);
                            for(int i=0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String Qr_Code=jsonobject.getString("qr_code_number");
                                String Status =jsonobject.getString("status");
                                txtBarcodeValue.setText(Qr_Code);

                                if(Status.equals("1")){
                                    try {
                                        final String url = CertificationForm.UploadURLStatic+"/Android/Household_GeoIden.php?qr_number=" + intentData;
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
                                                                String QR = jsonobject.getString("qr_number");
                                                                Intent Household_Geo = new Intent(ScanQRCode.this, SurveyForms.class);
                                                                Household_Geo.putExtra("Lname", Lname);
                                                                Household_Geo.putExtra("Fname", Fname);
                                                                Household_Geo.putExtra("qr_code_number", QR);


                                                                ScanQRCode.this.startActivity(Household_Geo);
                                                                //Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
                                                            }
                                                        } catch (JSONException e) {


                                                            final String url= CertificationForm.UploadURLStatic+"/Android/QData.php?qr_number="+intentData;
                                                            pd.show();

                                                            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                                                                    url,
                                                                    new Response.Listener<String>() {
                                                                        @Override
                                                                        public void onResponse(String response) {
                                                                            try {
                                                                                pd.hide();
                                                                                JSONArray jsonarray = new JSONArray(response);
                                                                                for(int i=0; i < jsonarray.length(); i++) {
                                                                                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                                                                                    String Fname= "No Geographic identification";
                                                                                    String Lname= "No Geographic identification";
                                                                                    String Qr_Code=jsonobject.getString("qr_code_number");

                                                                                    //txtBarcodeValue.setText(Fname);

                                                                                    Intent userdashboard = new Intent(ScanQRCode.this, SurveyForms.class);
                                                                                    userdashboard.putExtra("Fname", Fname);
                                                                                    userdashboard.putExtra("Lname", Lname);
                                                                                    userdashboard.putExtra("qr_code_number", Qr_Code);

                                                                                    finish();
                                                                                    startActivity(userdashboard);


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
                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        if (error != null) {

                                                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                                                            recreate();
                                                        }
                                                    }
                                                }
                                        );

                                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                                    }catch (Exception ex){

                                        Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                                        recreate();
                                    }
                                }else {
                                    Toast.makeText(getApplicationContext(), "QR code not used.", Toast.LENGTH_SHORT).show();
                                    recreate();
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





    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();


    }


    private void getGeoIdenData() {


    }
}
