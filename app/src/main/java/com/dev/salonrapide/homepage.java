package com.dev.salonrapide;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class homepage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

   public void goToSalonRapideBranches(){

        Intent intent = new Intent(homepage.this,salonRapideBranches.class);
        startActivity(intent);
    }

    public void goToMap(){

        Intent intent = new Intent(homepage.this,MapsActivity.class);
        startActivity(intent);
    }

    public void goToAddNotes(){

        Intent intent = new Intent(homepage.this,addNotes.class);
        startActivity(intent);
    }

    /*public void goToShop(){

        Intent intent = new Intent(homepage.this,shop.class);
        startActivity(intent);
    }*/

    public void goToProfile(){

        Intent intent = new Intent(homepage.this,userProfile.class);
        startActivity(intent);
    }

   public void srbBtn(View view) {
       goToSalonRapideBranches();
   }

    public void nsmBtn(View view){
        goToMap();
    }

    public void anBtn(View view){
        goToAddNotes();
    }

    /*public void shopBtn(View view){
        goToShop();
    }*/

    public void profileBtn(View view){
        goToProfile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homepagemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(homepage.this, loginPage.class));
        }
        return super.onOptionsItemSelected(item);
    }
}