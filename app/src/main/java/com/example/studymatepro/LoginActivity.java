package com.example.studymatepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {


    EditText emailEditText,passwordEditText;
    Button loginBtn;
    ProgressBar progressBar;
    TextView signupBtnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailEditText=findViewById(R.id.email_Edit_Text);
        passwordEditText=findViewById(R.id.password_Edit_Text);
        loginBtn=findViewById(R.id.login_btn);
        progressBar=findViewById(R.id.progress_bar);
        signupBtnTextView=findViewById(R.id.sign_up_text_view_btn);

        loginBtn.setOnClickListener(v-> loginUser());
        signupBtnTextView.setOnClickListener((v)->startActivity(new Intent(LoginActivity.this,CreateAccountActivity.class)));



    }

    void loginUser(){

        String email =emailEditText.getText().toString();
        String password =passwordEditText.getText().toString();

        boolean isValidation =validateData(email,password);
        if(!isValidation){
            return;
        }

        loginAccountInFirebase(email,password);

    }

    void loginAccountInFirebase(String email, String password){
        //Creating Account in Firebase
        changeInProgress(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        changeInProgress(false);
                        if(task.isSuccessful())
                        {//login success
                            if(firebaseAuth.getCurrentUser().isEmailVerified())
                            {
                                //Email verified
                                Toast.makeText(LoginActivity.this,"Login Successfully!!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));


                            }else{
                                //Email not verified
                                Toast.makeText(LoginActivity.this,"Email not verified,Verify your Email",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            //login fail
                            Toast.makeText(LoginActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                }
        );

    }

    void changeInProgress(boolean inProgress){
        //Changing the visibility of the progressBar

        if(inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }

    }


    boolean validateData(String email,String password){
        //Email validation

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }

        if (password.length()<6){
            passwordEditText.setError("Invalid");
            return false;
        }

        return true;

    }



}