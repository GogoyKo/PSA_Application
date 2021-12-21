package com.example.kugangka.myapplication;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class LoginRequest extends StringRequest {
    //private static final String LOGIN_REQUEST_URL = "https://test-psa.000webhostapp.com/practice/Login.php";
    //private static final String LOGIN_REQUEST_URL = "http://172.17.100.2:8089/PSA/Android/Login.php";
    private static final String LOGIN_REQUEST_URL = CertificationForm.UploadURLStatic+"/Android/Login.php";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }
    public Map<String, String> getParams(){
        return params;
    }
}

