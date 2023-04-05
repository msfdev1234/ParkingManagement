package com.example.parkingmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.example.parkingmanagement.LoadingDialougs.LoadingDialog;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    LocationManager locationManager;

    EditText searchViewEdtTxt;
    ImageView P_Search_Bttn;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolBar;
    ActionBarDrawerToggle toggle;
    FrameLayout frame_layout;

    private LoadingDialog loadingDialog;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

   // private  FirebaseRecyclerAdapter<Space, Spaces_ViewHolder> adapter;
    private ImageView empty_Image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nav_view);
        empty_Image = findViewById(R.id.empty_Addresses_Icon);
        searchViewEdtTxt = (EditText) findViewById(R.id.searchBox_Main);
        frame_layout = (FrameLayout) findViewById(R.id.frame_layout) ;
        frame_layout.removeAllViews();

        loadingDialog = new LoadingDialog(MainActivity.this);

        recyclerView = findViewById(R.id.spaces_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        toolBar = findViewById(R.id.Toolbar_Main);

        this.setSupportActionBar(toolBar);

        toggle = new ActionBarDrawerToggle(this , drawerLayout, toolBar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapsFragment supportMapFragment =
                new MapsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, supportMapFragment).commit();
        checkAvailability();

    }

    private void checkAvailability() {
        DatabaseReference spaces_Ref = FirebaseDatabase.getInstance().getReference().child("Addresses")
                .child("a");

        spaces_Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int size = (int) dataSnapshot.getChildrenCount();
                if (size < 1){
                    loadingDialog.dismissDialog();
                    empty_Image.setVisibility(View.VISIBLE);
                } else {
                    empty_Image.setVisibility(View.INVISIBLE);
                    //getName();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        MapsFragment supportMapFragment =
                new MapsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, supportMapFragment).commit();
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((MainActivity.this),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Allow Location Permision?")
                        .setMessage("Use Your Location")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {


            return true;
        }
    }

}