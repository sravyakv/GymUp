package com.example.gymupdraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Try extends AppCompatActivity {

    private TextInputLayout txtFullNameLayout, txtEmailLayout, txtPasswordLayout, txtRePasswordLayout, txtPhoneLayout;
    private EditText edtTxtFullName, edtTxtEmail, edtTxtPassword, edtTxtRePassword, edtTxtPhone;
    private MaterialButton btnCreate;
    private Button btnLoginBack;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);



        txtFullNameLayout = findViewById(R.id.txtFullNameLayout);
        txtEmailLayout = findViewById(R.id.txtEmailLayout);
        txtPasswordLayout = findViewById(R.id.txtPasswordLayout);
        txtRePasswordLayout = findViewById(R.id.txtRePasswordLayout);
        txtPhoneLayout = findViewById(R.id.txtPhoneLayout);
        btnCreate = findViewById(R.id.btnCreate);
        btnLoginBack = findViewById(R.id.btnLoginBack);

        //Authentiation
        fAuth = FirebaseAuth.getInstance();

        edtTxtFullName = findViewById(R.id.edtTxtFullName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.PasswordField);
        edtTxtRePassword = findViewById(R.id.edtTxtRePassword);
        edtTxtPhone = findViewById(R.id.edtTxtPhone);

        /* imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, GymLogin.class);
                startActivity(intent);
            }
        }); */

        btnLoginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Try.this, GymLogin.class);
                startActivity(intent);
            }
        });


        if(fAuth.getCurrentUser() != null){
            Intent intent = new Intent(Try.this, GymLogin.class);
            startActivity(intent);
            finish();
        }



        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()){
                    /*edtTxtFullName.setText("");
                    edtTxtEmail.setText("");
                    edtTxtPassword.setText("");
                    edtTxtRePassword.setText("");
                    edtTxtPhone.setText("");*/

                    String Fname = edtTxtFullName.getText().toString();
                    String Email = edtTxtEmail.getText().toString().trim();
                    String Password = edtTxtPassword.getText().toString().trim();

                    fAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Try.this, "Successfully Created Account", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Try.this, GymLogin.class);
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(Try.this, "Error 404!"+ task.getException(), Toast.LENGTH_SHORT).show();

                            }


                        }
                    });



                }
            }
        });
    }

    public boolean validateFields(){
        if(!validateFullName() | !validateEmail() | !validatePassword() | !validateRePassword() | !validatePhone()){
            return false;
        }
        return true;
    }


    private boolean validateFullName(){

        String val = txtFullNameLayout.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            txtFullNameLayout.setError("Field Can not be Empty");
            edtTxtFullName.setText("");
            return false;
        }
        else{
            txtFullNameLayout.setError(null);
            txtFullNameLayout.setEnabled(false);
            return true;
        }
    }



    private boolean validateEmail(){

        String val = txtEmailLayout.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            txtEmailLayout.setError("Field Can not be Empty");
            edtTxtEmail.setText("");
            return false;
        }
        else if (!val.matches(checkEmail)){
            txtEmailLayout.setError("Invalid Email");
            edtTxtEmail.setText("");
            return false;
        }
        else{
            txtEmailLayout.setError(null);
            txtEmailLayout.setEnabled(false);
            return true;
        }
    }

    private boolean validatePassword(){

        String val = txtPasswordLayout.getEditText().getText().toString().trim();
        //String checkPassword = "^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{4,}" + "$";

        if(val.isEmpty()){
            txtPasswordLayout.setError("Field Can not be Empty");
            edtTxtPassword.setText("");
            return false;
        }
        else if (val.length() <= 7){
            txtPasswordLayout.setError("Password should contain atleast 8 Characters");
            edtTxtPassword.setText("");
            return false;
        }

        else{
            txtPasswordLayout.setError(null);
            txtPasswordLayout.setEnabled(false);
            return true;
        }
    }

    private boolean validateRePassword(){

        String val = txtRePasswordLayout.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            txtRePasswordLayout.setError("Field Can not be Empty");
            edtTxtRePassword.setText("");
            return false;
        }
        else if (!val.matches(txtPasswordLayout.getEditText().getText().toString().trim())){
            txtRePasswordLayout.setError("Password Do not Match");
            edtTxtRePassword.setText("");
            return false;
        }

        else{
            txtRePasswordLayout.setError(null);
            txtRePasswordLayout.setEnabled(false);
            return true;
        }
    }

    private boolean validatePhone(){

        String val = txtPhoneLayout.getEditText().getText().toString().trim();
        String checkPhone = "\\A\\w{1,10}\\z";
        if(val.isEmpty()){
            txtPhoneLayout.setError("Field Can not be Empty");
            edtTxtPhone.setText("");
            return false;
        }
        else if (!val.matches(checkPhone)){
            txtPhoneLayout.setError("Invalid Phone Number");
            edtTxtPhone.setText("");
            return false;
        }
        else if (val.length() > 10){
            txtPhoneLayout.setError("Phone length exceeds");
            edtTxtPhone.setText("");
            return false;
        }
        else{
            txtPhoneLayout.setError(null);
            txtPhoneLayout.setEnabled(false);
            return true;
        }
    }


}