package SaloonApp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class salonRapideBranches extends AppCompatActivity {

    RecyclerView recyclerView; //initialising recycler view
    com.dev.salonrapide.MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_rapide_branches);

        recyclerView = (RecyclerView) findViewById(R.id.rv); //finding saloons by id
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<com.dev.salonrapide.MainModel> options =
                new FirebaseRecyclerOptions.Builder<com.dev.salonrapide.MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Saloon"), com.dev.salonrapide.MainModel.class)
                        .build();

        mainAdapter = new com.dev.salonrapide.MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.startListening();
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
        FirebaseRecyclerOptions<com.dev.salonrapide.MainModel> options =
                new FirebaseRecyclerOptions.Builder<com.dev.salonrapide.MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Saloon").orderByChild("salon_name").startAt(str).endAt(str+"~"), com.dev.salonrapide.MainModel.class)
                        .build();

        mainAdapter = new com.dev.salonrapide.MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }
}
