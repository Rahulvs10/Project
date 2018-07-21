package com.example.rahul.navigationdrawer.Fragments;


import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rahul.navigationdrawer.Activities.MissingActivity;
import com.example.rahul.navigationdrawer.Activities.MissingDetailsActivity;
import com.example.rahul.navigationdrawer.Activities.RescueRequestActivity;
import com.example.rahul.navigationdrawer.Activities.RescueRequestDetails;
import com.example.rahul.navigationdrawer.Adapter.People_adapter;
import com.example.rahul.navigationdrawer.Adapter.Rescue_adapter;
import com.example.rahul.navigationdrawer.Objects.People;
import com.example.rahul.navigationdrawer.Objects.Rescue;
import com.example.rahul.navigationdrawer.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class People_fragment extends Fragment {


    public People_fragment() {
        // Required empty public constructor
    }

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    ListView listView;
    People_adapter adapter;
    List<People> rescue_list;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu1_layout, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("missing");
        rescue_list = new ArrayList<>();

        progressBar = rootView.findViewById(R.id.progress_bar);
        listView = rootView.findViewById(R.id.listView);
        adapter = new People_adapter(getActivity(),R.layout.list_item1,rescue_list);
        listView.setAdapter(adapter);


        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    People people = dataSnapshot.getValue(People.class);
                    if(people != null)
                        adapter.add(people);
                    progressBar.setVisibility(View.GONE);
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };
            mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
        }

        rescue_list = new ArrayList<People>();
        listView = rootView.findViewById(R.id.listView);

        FloatingActionButton request_button = rootView.findViewById(R.id.fab);
        request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(getActivity(), MissingActivity.class);
                startActivity(open);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                People people = adapter.getItem(position);
                Intent details = new Intent(getActivity(), MissingDetailsActivity.class);
                details.putExtra("object",people);
                startActivity(details);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Missing People");
    }
}
