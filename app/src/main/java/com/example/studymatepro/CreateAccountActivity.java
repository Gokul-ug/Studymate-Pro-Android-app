package com.example.studymatepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Objects;

public class CreateAccountActivity extends AppCompatActivity {

    EditText emailEditText,passwordEditText,confirmPasswordEditText;
    Button createAccountBtn;
    ProgressBar progressBar;
    TextView loginBtnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailEditText=findViewById(R.id.email_Edit_Text);
        passwordEditText=findViewById(R.id.password_Edit_Text);
        confirmPasswordEditText=findViewById(R.id.confirm_password_Edit_Text);
        createAccountBtn=findViewById(R.id.create_account_btn);
        progressBar=findViewById(R.id.progress_bar);
        loginBtnTextView=findViewById(R.id.sign_up_text_view_btn);


        createAccountBtn.setOnClickListener(v-> createAccount());
        loginBtnTextView.setOnClickListener(v-> finish());

    }

    void createAccount(){

        String email =emailEditText.getText().toString();
        String password =passwordEditText.getText().toString();
        String confirmPassword =confirmPasswordEditText.getText().toString();

        boolean isValidation =validateData(email,password,confirmPassword);
        if(!isValidation){
            return;
        }

        createAccountInFirebase(email,password);

    }

    void createAccountInFirebase(String email,String password){
        //Creating Account in Firebase
        changeInProgress(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CreateAccountActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        changeInProgress(false);
                        if(task.isSuccessful())
                        {
                            //creating acc is done
                            Toast.makeText(CreateAccountActivity.this,"Account is Successful Created,Check email to Verify",Toast.LENGTH_SHORT).show();
                            Objects.requireNonNull(firebaseAuth.getCurrentUser()).sendEmailVerification();
                            firebaseAuth.signOut();

                            finish(); //logout from the app
                        }else {
                            //failure
                            Toast.makeText(CreateAccountActivity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                );



    }

    void changeInProgress(boolean inProgress){
        //Changing the visibility of the progressBar

        if(inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            createAccountBtn.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            createAccountBtn.setVisibility(View.VISIBLE);
        }

    }


    boolean validateData(String email,String password,String confirmPassword){
        //Email validation

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }

        if (password.length()<6){
            passwordEditText.setError("Invalid");
            return false;
        }
        if (!password.equals(confirmPassword)){
            confirmPasswordEditText.setError("Password not Matched");
            return false;
        }

        return true;

    }



}