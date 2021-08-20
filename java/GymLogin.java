package com.example.gymupdraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GymLogin extends AppCompatActivity {

    Button btnRegister;
    private EditText mEmailField;
    private EditText mPasswordField;



    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_login);


        mAuth=FirebaseAuth.getInstance();

        mEmailField=(EditText)findViewById(R.id.EmailField);
        mPasswordField=(EditText)findViewById(R.id.PasswordField);

        Button mLoginbtn = (Button) findViewById(R.id.Loginbtn);
        btnRegister = (Button) findViewById(R.id.btnResgister);

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){

                   // startActivity(new Intent(GymLogin.this, AccountActivity.class));

                }
            }
        };

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignin();
            }



        });



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GymLogin.this, Try.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }



    private void startSignin(){


        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(GymLogin.this, "Fields are Empty.", Toast.LENGTH_LONG).show();


        } else  {
           /* mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {
                        Toast.makeText(GymLogin.this, "Entered credentials are incorrect", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(GymLogin.this, "Logged in Successfully!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(GymLogin.this, AccountActivity.class));
                    }



                }
            }); */
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(GymLogin.this,acivity_plan.class));

                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}