package com.example.kugangka.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class My_Profile extends AppCompatActivity {

    TextView Name,Email,Gender,Age,Username, Mobile;
    String firstname,lastname, email, gender,age,username, mobile;
    Button Close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__profile);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        Name = (TextView)findViewById(R.id.Name);
        Email = (TextView) findViewById(R.id.Email);
        Gender = (TextView) findViewById(R.id.gender);
        Age = (TextView) findViewById(R.id.age);
        Mobile = (TextView) findViewById(R.id.Mobile);
        Username = (TextView) findViewById(R.id.username);

        Close = (Button) findViewById(R.id.close);


        ImageView ImageView = (ImageView)findViewById(R.id.Image1);

        Intent intent = getIntent();

        firstname = intent.getStringExtra("firstname");
        lastname = intent.getStringExtra("lastname");
        email = intent.getStringExtra("email");
        gender = intent.getStringExtra("gender");
        age = intent.getStringExtra("age");
        mobile = intent.getStringExtra("mobile");
        username = intent.getStringExtra("username");

        String imageURL = intent.getStringExtra("image");

        Name.setText(firstname+" "+lastname);
        Email.setText(email);
        Username.setText(username);
        Gender.setText(gender);
        Age.setText(""+age);
        Mobile.setText(mobile);
        Picasso.with(getApplicationContext()).load(imageURL).into(ImageView);

        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
