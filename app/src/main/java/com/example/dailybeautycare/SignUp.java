package com.example.dailybeautycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText SignupEmail,SignupPassword,ConfirmSignupPassword;
    private Button SignupButton,AgainloginBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_sign_up);
        SignupEmail=findViewById(R.id.SignupEmail);
        SignupPassword=findViewById(R.id.Signuppasswordid);
        ConfirmSignupPassword=findViewById(R.id.confirmpasswordid);
        SignupButton=findViewById(R.id.Signupbutton);
        AgainloginBtn=findViewById(R.id.Loginbtn);
        SignupButton.setOnClickListener(this);
        AgainloginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Signupbutton:
                userRegister();
                break;

            case R.id.Loginbtn:
                Intent intent=new Intent(getApplicationContext(),LogInPage.class);
                startActivity(intent);

                break;
        }
    }

    private void userRegister() {
        String email=SignupEmail.getText().toString().trim();
        String password=SignupPassword.getText().toString().trim();
        //checking the validity of the email
        if(email.isEmpty())
        {
            SignupEmail.setError("Enter an email address");
            SignupEmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            SignupEmail.setError("Enter a valid email address");
            SignupEmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(email.isEmpty())
        {
            SignupPassword.setError("Enter a password");
            SignupPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    finish();
                    Toast.makeText(getApplicationContext(), "Successfully Sign up", Toast.LENGTH_SHORT).show();

                } else {
                    // If sign in fails, display a message to the user.
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "User already has an account", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });


    }

}