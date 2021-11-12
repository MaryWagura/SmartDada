package com.example.hi;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class RegisterActivity extends AppCompatActivity {
    private EditText email,password;
    private Button login,signup;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        email =  findViewById(R.id.et_signup_p_email);
        password =  findViewById(R.id.et_signup_p_password);

        login =  findViewById(R.id.btn_signup_p_login);
        signup = findViewById(R.id.btn_signup_p_signup);

        progressBar =  findViewById(R.id.progress_signup_p);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                login.setVisibility(View.GONE);
                Intent i =  new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String emailString =  email.getText().toString();
                String passwordString =  password.getText().toString();
//        check if email and password is empty or not then pass them to create account method to sign up to firebase
                if (emailString.isEmpty()) {
                    toastMessage("Email required");
                }else if(passwordString.isEmpty()){
                    toastMessage("Password required");

                }else{
//            if validation is okay pass to create account method
                    progressBar.setVisibility(View.VISIBLE);
                    signup.setVisibility(View.GONE);
                    createAccount(emailString,passwordString);

                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

//            send user to Home Screen
            redirect(currentUser);
        }
    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            redirect(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.GONE);
                            signup.setVisibility(View.VISIBLE);

                            toastMessage("Authentication failed.");

                            redirect(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    //    toast message to user
    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

    private void redirect(Object currentuser) {
//        check if current user is not null
        if(currentuser != null){
//            redirect to dashbord activity
            startActivity(new Intent(getApplicationContext(),MainPageActivity.class));
            toastMessage(currentuser.toString());
        }
    }



}
