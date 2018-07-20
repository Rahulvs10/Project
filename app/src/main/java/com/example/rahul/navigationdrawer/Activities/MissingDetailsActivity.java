package com.example.rahul.navigationdrawer.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahul.navigationdrawer.Objects.People;
import com.example.rahul.navigationdrawer.Objects.Rescue;
import com.example.rahul.navigationdrawer.Objects.Resource;
import com.example.rahul.navigationdrawer.R;
import com.squareup.picasso.Picasso;

public class MissingDetailsActivity extends AppCompatActivity {

    TextView nameText,ageText, descText, genderText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameText = findViewById(R.id.nameText);
        ageText = findViewById(R.id.ageText);
        descText = findViewById(R.id.descText);
        genderText = findViewById(R.id.genderText);
        imageView = findViewById(R.id.imageView);


        final People people = (People) getIntent().getSerializableExtra("object");

        Picasso.get().load(people.getPhoto_url()).into(imageView);

        nameText.setText(people.getName());
        genderText.setText(people.getGender().toUpperCase());
        ageText.setText(String.valueOf(people.getAge()));
        descText.setText(people.getDescription());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + people.getContact()));
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
