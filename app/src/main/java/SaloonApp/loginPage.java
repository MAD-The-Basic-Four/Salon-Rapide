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

public class loginPage extends AppCompatActivity {

    private EditText mloginEmail, mloginPassword;
    private RelativeLayout mlogin,mgoToSignUp;
    private TextView mgoToForgotPassword;
    private TextView mgoToAdminLogin;

    private FirebaseAuth firebaseAuth;

    ProgressBar mprogressbarofloginpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

      //getSupportActionBar().hide();

        mloginEmail = findViewById(R.id.loginEmail);
        mloginPassword = findViewById(R.id.loginPassword);
        mlogin = findViewById(R.id.login);
        mgoToSignUp = findViewById(R.id.goToSignUp);
        mgoToForgotPassword = findViewById(R.id.goToForgotPassword);
        mprogressbarofloginpage = findViewById(R.id.progressbarofloginpage);
        mgoToAdminLogin = findViewById(R.id.goToAdminLogin);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();

        if(firebaseUser!=null){
            finish();
            startActivity(new Intent(loginPage.this, homepage.class));
        }

       mgoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginPage.this, signUp.class));
            }
        });

        mgoToAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginPage.this, adminLogin.class));
            }
        });

       mgoToForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginPage.this, forgotPassword.class));

            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = mloginEmail.getText().toString().trim();
                String password = mloginPassword.getText().toString().trim();

                if(mail.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Login the user

                    mprogressbarofloginpage.setVisibility(View.VISIBLE);

                    firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                checkmailverification();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Account doesnt exist!", Toast.LENGTH_SHORT).show();
                                mprogressbarofloginpage.setVisibility(View.INVISIBLE);
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
            startActivity(new Intent(loginPage.this, homepage.class));
        }
        else{
            mprogressbarofloginpage.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Verify your email first!", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}