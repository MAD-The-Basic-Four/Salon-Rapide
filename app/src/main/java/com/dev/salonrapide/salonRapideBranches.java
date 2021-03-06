package com.dev.salonrapide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class salonRapideBranches extends AppCompatActivity {

    RecyclerView recyclerView; //initialising recycler view
    viewSalonRapideBranchesAdapter vSRBAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_rapide_branches);

        recyclerView = (RecyclerView) findViewById(R.id.rv); //finding saloons by id
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Saloon"), MainModel.class)
                        .build();

        vSRBAdapter = new viewSalonRapideBranchesAdapter(options);
        recyclerView.setAdapter(vSRBAdapter );

    }
    @Override
    protected void onStart() {
        super.onStart();
        vSRBAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vSRBAdapter.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView =(SearchView)item.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Saloon").orderByChild("salon_name").startAt(str).endAt(str+"~"), MainModel.class)
                        .build();

        vSRBAdapter = new viewSalonRapideBranchesAdapter(options);
        vSRBAdapter.startListening();
        recyclerView.setAdapter(vSRBAdapter);
    }
}
