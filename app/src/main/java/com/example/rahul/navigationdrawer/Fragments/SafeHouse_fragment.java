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
import android.widget.TextView;

import com.example.rahul.navigationdrawer.Activities.RescueRequestActivity;
import com.example.rahul.navigationdrawer.Activities.RescueRequestDetails;
import com.example.rahul.navigationdrawer.Adapter.Rescue_adapter;
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
public class SafeHouse_fragment extends Fragment {


    public SafeHouse_fragment() {
        // Required empty public constructor
    }

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    ListView listView;
    double lat, lon;
    Rescue_adapter adapter;
    List<Rescue> rescue_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu1_layout, container, false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Rescue_request");


//        getLocation();
        rescue_list = new ArrayList<Rescue>();
        listView = rootView.findViewById(R.id.listView);

        FloatingActionButton request_button = rootView.findViewById(R.id.fab);
        request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(getActivity(), RescueRequestActivity.class);
                startActivity(open);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Rescue rescue = adapter.getItem(position);
                Intent details = new Intent(getActivity(), RescueRequestDetails.class);
                details.putExtra("object",rescue);
                startActivity(details);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 4");
    }

//    private void getLocation() {
//        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
//        mFusedLocationClient.getLastLocation()
//                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            // Logic to handle location object
//                            lat = location.getLatitude();
//                            lon = location.getLongitude();
//                            adapter = new Rescue_adapter(getActivity(),R.layout.list_item1,rescue_list,lat,lon);
//                            listView.setAdapter(adapter);
//
//                            if (mChildEventListener == null) {
//                                mChildEventListener = new ChildEventListener() {
//                                    @Override
//                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                                        Rescue rescue = dataSnapshot.getValue(Rescue.class);
//                                        if(rescue != null)
//                                            adapter.add(rescue);
//                                    }
//
//                                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
//                                    public void onChildRemoved(DataSnapshot dataSnapshot) {}
//                                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
//                                    public void onCancelled(DatabaseError databaseError) {}
//                                };
//                                mMessagesDatabaseReference.addChildEventListener(mChildEventListener);
//                            }
//                        }
//                        else
//                            Log.i("TAG", "Location null");
//                    }
//                });
//    }

}
