package com.example.weekeend2hw;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.style.UpdateAppearance;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int ADD_CELEB_REQUEST_FROM_MAIN = 1; //request code
    public static final int UPDATE_CELEB_REQUEST_FROM_MAIN = 2;//request code
    private CelebrityViewModel celebrityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        setTitle("MY CELEBRITIES");

        final CelebrityAdapter adapter = new CelebrityAdapter();
        recyclerView.setAdapter(adapter);

        celebrityViewModel = ViewModelProviders.of(this).get(CelebrityViewModel.class);
        celebrityViewModel.getAllCelebrities().observe(this, new Observer<List<Celebrity>>() {
            @Override
            public void onChanged(List<Celebrity> celebrities) {
                adapter.setCelebrity(celebrities);

                //update our Recycler View
            }
        });


       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {   // implementation of
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                celebrityViewModel.delete(adapter.getCelebrityAt(viewHolder.getAdapterPosition())); //swipe to delete item at this position
                Toast.makeText(MainActivity.this , "Celebrity Deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


       adapter.setOnItemClickListener(new CelebrityAdapter.OnitemClickListener() {
           @Override
           public void onItemClick(Celebrity celebrity) {
               Intent intent = new Intent(MainActivity.this,EditCelebrityActivity.class);
               intent.putExtra(EditCelebrityActivity.EXTRA_CELEBNAME, celebrity.getCelebrityName());
               intent.putExtra(EditCelebrityActivity.EXTRA_CELEBDESCRIPTION, celebrity.getProfession());
               intent.putExtra(EditCelebrityActivity.EXTRA_CELEBFAMELEVEL, celebrity.getFameLevel());
               startActivityForResult(intent, UPDATE_CELEB_REQUEST_FROM_MAIN);

           }
       });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CELEB_REQUEST_FROM_MAIN && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddCelebrityActivity.EXTRA_CELEBNAME);
            String profession = data.getStringExtra((AddCelebrityActivity.EXTRA_CELEBDESCRIPTION));
            int fameLevel = data.getIntExtra(AddCelebrityActivity.EXTRA_CELEBFAMELEVEL, 1);

            Celebrity thisCelebrity = new Celebrity(name, profession, fameLevel);
            celebrityViewModel.insert(thisCelebrity);

            Toast.makeText(this, "Celebrity Saved", Toast.LENGTH_SHORT).show();
        } else if(requestCode == UPDATE_CELEB_REQUEST_FROM_MAIN && resultCode == RESULT_OK){
            String name = data.getStringExtra(EditCelebrityActivity.EXTRA_CELEBNAME);
            String profession = data.getStringExtra((EditCelebrityActivity.EXTRA_CELEBDESCRIPTION));
            int fameLevel = data.getIntExtra(EditCelebrityActivity.EXTRA_CELEBFAMELEVEL, 1);

            Celebrity thisCelebrity = new Celebrity(name, profession, fameLevel);
            celebrityViewModel.update(thisCelebrity);


        }
        else {
            Toast.makeText(this, "Celebrity Not Saved", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mnuDeleteAll) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.addCelebrity) {
            Intent intent = new Intent(MainActivity.this, AddCelebrityActivity.class);
            startActivityForResult(intent, ADD_CELEB_REQUEST_FROM_MAIN);

        } else if (id == R.id.removeCelebrity) {

        } else if (id == R.id.myFavorites) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.readFile) {
            Intent intent = new Intent(getApplicationContext(),ReadAndWriteActivity.class);
            startActivity(intent);

        } else if (id == R.id.writeFile) {
            Intent intent = new Intent(getApplicationContext(),ReadAndWriteActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
