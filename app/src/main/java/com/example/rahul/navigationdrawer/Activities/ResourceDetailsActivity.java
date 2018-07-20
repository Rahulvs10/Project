package com.example.rahul.navigationdrawer.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.rahul.navigationdrawer.Objects.Rescue;
import com.example.rahul.navigationdrawer.Objects.Resource;
import com.example.rahul.navigationdrawer.R;

public class ResourceDetailsActivity extends AppCompatActivity {

    TextView nameText, quantityText, supplyForText, resNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameText = findViewById(R.id.nameText);
        quantityText = findViewById(R.id.quantityText);
        supplyForText = findViewById(R.id.supplyForText);
        resNameText = findViewById(R.id.resNameText);


        final Resource resource = (Resource)getIntent().getSerializableExtra("object");


        nameText.setText(resource.getName());
        quantityText.setText(resource.getQuantity());
        supplyForText.setText(String.valueOf(resource.getSupply_for()));
        resNameText.setText(resource.getRes_name());

        final String uri = "geo:0,0?q="+resource.getLat()+","+resource.getLon()+"(resource)";

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
                intent.setData(Uri.parse("tel:" + resource.getContact()));
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
