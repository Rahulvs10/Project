package com.example.rahul.navigationdrawer.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rahul.navigationdrawer.Objects.Rescue;
import com.example.rahul.navigationdrawer.R;

public class RescueRequestDetails extends AppCompatActivity {

    TextView nameText,ageText, noText, descText, genderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameText = findViewById(R.id.nameText);
        ageText = findViewById(R.id.ageText);
        noText = findViewById(R.id.noText);
        descText = findViewById(R.id.descText);
        genderText = findViewById(R.id.genderText);

        final Rescue rescue = (Rescue)getIntent().getSerializableExtra("object");

        nameText.setText(rescue.getName());
        genderText.setText(rescue.getGender().toUpperCase());
        ageText.setText(String.valueOf(rescue.getAge()));
        noText.setText(String.valueOf(rescue.getHow_may()));
        descText.setText(rescue.getDescription());

        final String uri = "geo:0,0?q="+rescue.getLat()+","+rescue.getLon()+"(rescue)";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.locate);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(uri));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                }
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.contact);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + rescue.getContact()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
                onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
