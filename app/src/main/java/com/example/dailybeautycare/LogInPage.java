package com.example.dailybeautycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInPage extends AppCompatActivity implements View.OnClickListener{
private EditText LoginEmail,LoginPassword;
private TextView SignupTextview;
private Button LoginButton;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        LoginEmail=findViewById(R.id.emailid);
        LoginPassword=findViewById(R.id.passwordid);
        SignupTextview=findViewById(R.id.textviewtwo);
        LoginButton=findViewById(R.id.loginbuttonid);
        SignupTextview.setOnClickListener(this);
        LoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.loginbuttonid:
                UserLogin();
                break;

            case R.id.textviewtwo:
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                break;
        }
    }

    private void UserLogin() {
        String email=LoginEmail.getText().toString().trim();
        String password=LoginPassword.getText().toString().trim();
        //checking the validity of the email
        if(email.isEmpty())
        {
            LoginEmail.setError("Enter an email address");
            LoginEmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            LoginEmail.setError("Enter a valid email address");
            LoginEmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(email.isEmpty())
        {
            LoginPassword.setError("Enter a password");
            LoginPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}