package com.muhammadyaseenfatima.registeranimationscreen;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button SignUpButton;
    private EditText NameSignUpTextInput, emailSignUpTextInput, passwordSignUpTextInput;
    private TextInputLayout NameSignUpLayout, EmailSignUpLayout, passwordsSignUpLayout;
    LottieAnimationView animation_view;
    LinearLayout Animation_linearLayout,main_LinearLayout;
    TextView status;


    private static final Pattern NAME_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    //"(?=\\S+$)" +           //no white spaces
                    ".{3,100}" +               //at least 4 characters
                    "$");

    private static final Pattern PASS_PATTERN =
            Pattern.compile("^" +
                    //  "(?=.*[0-9])" +         //at least 1 digit
                    // "(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    // "(?=.*[a-zA-Z])" +      //any letter
                    // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    // "(?=\\S+$)" +           //no white spaces
                    ".{6,100}" +               //at least 4 characters
                    "$");


    String emailPatternString = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final Pattern Email_Pattern = Pattern.compile(emailPatternString);

    private boolean validateName() {

        String userFirstNameInput = NameSignUpTextInput.getText().toString().trim();
        if (userFirstNameInput.isEmpty()) {
            NameSignUpLayout.setError(getString(R.string.enter_name));
            return false;
        } else if (NAME_PATTERN.matcher(userFirstNameInput).matches()) {
            NameSignUpLayout.setError(null);
            return true;
        } else {
            NameSignUpLayout.setError("Like : Ali");
            return false;
        }
    }

    private boolean validateEmail() {
        String emailInput = emailSignUpTextInput.getText().toString().trim();
        if (emailInput.isEmpty()) {
            EmailSignUpLayout.setError(getString(R.string.enter_email));
            return false;
        } else if (Email_Pattern.matcher(emailInput).matches()) {
            EmailSignUpLayout.setError(null);
            return true;
        } else {
            EmailSignUpLayout.setError("Like: yasinayub7172@gmail.com");
            return false;
        }

    }


    private boolean validatePass() {

        String passInput = passwordSignUpTextInput.getText().toString().trim();

        if (passInput.isEmpty()) {
            passwordsSignUpLayout.setError(getString(R.string.enter_passwords));
            return false;
        } else if (PASS_PATTERN.matcher(passInput).matches()) {
            passwordsSignUpLayout.setError(null);
            return true;
        } else {
            passwordsSignUpLayout.setError("Like:1@Yasin");
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUpButton = findViewById(R.id.SignUpButton);
        NameSignUpTextInput = findViewById(R.id.NameSignUpTextInput);
        emailSignUpTextInput = findViewById(R.id.emailSignUpTextInput);
        passwordSignUpTextInput = findViewById(R.id.passwordSignUpTextInput);
        NameSignUpLayout = findViewById(R.id.NameSignUpLayout);
        EmailSignUpLayout = findViewById(R.id.EmailSignUpLayout);
        passwordsSignUpLayout = findViewById(R.id.passwordsSignUpLayout);
        Animation_linearLayout=findViewById(R.id.Animation_linearLayout);
        animation_view=findViewById(R.id.animation_view);
        main_LinearLayout=findViewById(R.id.main_LinearLayout);
        status=findViewById(R.id.status);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateName() && validateEmail() && validatePass()) {
                    Animation_linearLayout.setVisibility(View.VISIBLE);
                    animation_view.playAnimation();
                    main_LinearLayout.setVisibility(View.INVISIBLE);
                    status.setText("Craete Account");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animation_view.setAnimation(R.raw.person);
                            animation_view.playAnimation();

                            status.setText("create Account SuccesFully");
                            animation_view.setRepeatCount(0);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            },3000);
                        }
                    },3000);



                }
            }
        });

    }
}