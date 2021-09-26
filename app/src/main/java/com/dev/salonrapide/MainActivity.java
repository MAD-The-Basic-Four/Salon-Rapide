package com.dev.salonrapide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToLogin(){
        Intent intent = new Intent(MainActivity.this, loginPage.class);
        startActivity(intent);
    }

    public void goToSignUp(){
        Intent intent = new Intent(MainActivity.this, signUp.class);
        startActivity(intent);
    }

    public void loginBtn(View view){
        goToLogin();

    }

    public void signUpBtn(View view){
        goToSignUp();

    }

}