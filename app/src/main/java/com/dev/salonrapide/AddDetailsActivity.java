package com.dev.salonrapide;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddDetailsActivity extends AppCompatActivity {

    EditText SalonName, Address, OwnerName, PhoneNo, Email, ImageUrl;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        SalonName = (EditText)findViewById(R.id.txtSalonName);
        Address = (EditText)findViewById(R.id.txtAddress);
        OwnerName = (EditText)findViewById(R.id.txtOwnerName);
        PhoneNo = (EditText)findViewById(R.id.txtPhoneNo);
        Email = (EditText)findViewById(R.id.txtEmail);
        ImageUrl = (EditText)findViewById(R.id.txtImageUrl);


        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        //setting up on click event
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();//calls the insert method
                clearAll();//clears all data
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//redirects back
            }
        });

    }
    //method to fetch details from all the fields in add details xml and store into a varibale
    //then set to map so the that value can be passed to firebase
    //this method should be called in btnAdd this allow the button the perform the Creation funcion
    private void insertData()
    {
        //Map is created here and next the data from field will be sent to map
        Map<String, Object> map = new HashMap<>();
        //data from text field attached to map
        map.put("salon_name", SalonName.getText().toString());
        map.put("address", Address.getText().toString());
        map.put("owner_name", OwnerName.getText().toString());
        map.put("phone_no", PhoneNo.getText().toString());
        map.put("email", Email.getText().toString());
        map.put("s_url", ImageUrl.getText().toString());

        //(getting refernce from firebase database)maping is done and now the data should be sent to firebase

        FirebaseDatabase.getInstance().getReference().child("Saloon").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddDetailsActivity.this, "Data Inserted Succesfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddDetailsActivity.this, "Error while updating", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    //clears data in create field after entering values
    private void clearAll(){
        SalonName.setText("");
        Address.setText("");
        OwnerName.setText("");
        PhoneNo.setText("");
        Email.setText("");
        ImageUrl.setText("");

    }
}