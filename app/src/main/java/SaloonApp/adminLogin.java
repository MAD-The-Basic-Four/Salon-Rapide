package SaloonApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class adminLogin extends AppCompatActivity {

    private EditText madminLoginEmail, madminLoginPassword;
    private RelativeLayout madminLogin,mgoToSignUp;
    private TextView mgoToForgotPassword;
    private TextView mgoToLoginPage;

    private FirebaseAuth firebaseAuth;

    ProgressBar mprogressbarofadminloginpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        //getSupportActionBar().hide();

        madminLoginEmail = findViewById(R.id.adminLoginEmail);
        madminLoginPassword = findViewById(R.id.adminLoginPassword);
        madminLogin = findViewById(R.id.adminLogin);
        mgoToSignUp = findViewById(R.id.goToSignUp);
        mgoToForgotPassword = findViewById(R.id.goToForgotPassword);
        mprogressbarofadminloginpage = findViewById(R.id.progressbarofadminloginpage);
        mgoToLoginPage = findViewById(R.id.goToLoginPage);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){
            finish();
            startActivity(new Intent(adminLogin.this, com.dev.salonrapide.adminHomepage.class));
        }

        mgoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminLogin.this, com.dev.salonrapide.signUp.class));
            }
        });

        mgoToLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminLogin.this, com.dev.salonrapide.loginPage.class));
            }
        });

        mgoToForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminLogin.this, com.dev.salonrapide.forgotPassword.class));

            }
        });

        madminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = madminLoginEmail.getText().toString().trim();
                String password = madminLoginPassword.getText().toString().trim();

                if(mail.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Login the admin

                    mprogressbarofadminloginpage.setVisibility(View.VISIBLE);

                    firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                checkmailverification();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Account doesnt exist!", Toast.LENGTH_SHORT).show();
                                mprogressbarofadminloginpage.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });
    }

    private void checkmailverification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser.isEmailVerified()==true){
            Toast.makeText(getApplicationContext(),"Logged in", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(adminLogin.this, com.dev.salonrapide.adminHomepage.class));
        }
        else{
            mprogressbarofadminloginpage.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Verify your email first!", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}