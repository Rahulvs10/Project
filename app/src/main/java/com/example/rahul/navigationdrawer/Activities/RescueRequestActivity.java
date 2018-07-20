package com.example.rahul.navigationdrawer.Activities;

import android.content.Intent;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.rahul.navigationdrawer.Objects.Rescue;
import com.example.rahul.navigationdrawer.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RescueRequestActivity extends AppCompatActivity {

    EditText nameText,ageText, noText, descText, contactText;
    double lat, lon;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    RadioButton male, female, other;
    String gender = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameText = findViewById(R.id.nameText);
        ageText = findViewById(R.id.ageText);
        noText = findViewById(R.id.noText);
        descText = findViewById(R.id.descText);
        contactText = findViewById(R.id.phoneText);
        male = findViewById(R.id.maleButton);
        female = findViewById(R.id.femaleButton);
        other = findViewById(R.id.otherButton);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Rescue_request");
        getLocation();

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "female";
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "other";
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.upload_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_post) {

            if((nameText.getText().toString()).length()==0){
                Snackbar.make(nameText,"Please fill all details",Snackbar.LENGTH_SHORT).show();
            } else if((ageText.getText().toString()).length()==0){
                Snackbar.make(nameText,"Please fill all details",Snackbar.LENGTH_SHORT).show();
            } else if((noText.getText().toString()).length()==0){
                Snackbar.make(nameText,"Please fill all details",Snackbar.LENGTH_SHORT).show();
            } else if((contactText.getText().toString()).length()==0){
                Snackbar.make(nameText,"Please fill all details",Snackbar.LENGTH_SHORT).show();
            } else if(gender == null){
                Snackbar.make(nameText,"Please fill all details",Snackbar.LENGTH_SHORT).show();
            } else {
                Rescue rescue = new Rescue(nameText.getText().toString().trim(), descText.getText().toString().trim(), Integer.parseInt(ageText.getText().toString()),
                        Integer.parseInt(noText.getText().toString()),gender, lat, lon, Long.parseLong(contactText.getText().toString()));
                mMessagesDatabaseReference.push().setValue(rescue);
                finish();
            }
            return true;
        }
        else if (id == android.R.id.home) {
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                // This activity is NOT part of this app's task, so create a new task
                // when navigating up, with a synthesized back stack.
                TaskStackBuilder.create(this)
                        // Add all of this activity's parents to the back stack
                        .addNextIntentWithParentStack(upIntent)
                        // Navigate up to the closest parent
                        .startActivities();
            } else {
                // This activity is part of this app's task, so simply
                // navigate up to the logical parent activity.
                onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getLocation() {
        Log.i("TAG","getLocationCalled");
        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(RescueRequestActivity.this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                        else
                            Log.i("TAG", "Location null");
                    }
                });
    }
}
