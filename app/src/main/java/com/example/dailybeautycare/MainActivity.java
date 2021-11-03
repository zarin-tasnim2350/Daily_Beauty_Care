package com.example.dailybeautycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private ImageView logo;
private Button logInbtn,signUpbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo=findViewById(R.id.imageviewid);
        logInbtn=findViewById(R.id.logInbutton);
        signUpbtn=findViewById(R.id.Signupbutton);

        logInbtn.setOnClickListener(this);
        signUpbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logInbutton:
                Intent intent= new Intent(getApplicationContext(),LogInPage.class);
                startActivity(intent);
                break;

            case R.id.Signupbutton:
                Intent intent1= new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent1);
                break;
        }

    }
}