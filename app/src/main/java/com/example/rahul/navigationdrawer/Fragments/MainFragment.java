package com.example.rahul.navigationdrawer.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rahul.navigationdrawer.Activities.MissingDetailsActivity;
import com.example.rahul.navigationdrawer.Activities.RescueRequestDetails;
import com.example.rahul.navigationdrawer.Activities.ResourceDetailsActivity;
import com.example.rahul.navigationdrawer.Objects.People;
import com.example.rahul.navigationdrawer.Objects.Rescue;
import com.example.rahul.navigationdrawer.Objects.Resource;
import com.example.rahul.navigationdrawer.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference1;
    private DatabaseReference mMessagesDatabaseReference2;
    private DatabaseReference mMessagesDatabaseReference3;
    private ChildEventListener mChildEventListener1;
    private ChildEventListener mChildEventListener2;
    private ChildEventListener mChildEventListener3;
    LinearLayout layout1, layout2, layout3;
    TextView mainText1,mainText2,mainText3, descText1,descText2,descText3;
    ImageView imageView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        layout1 = rootView.findViewById(R.id.layout1);
        layout2 = rootView.findViewById(R.id.layout2);
        layout3 = rootView.findViewById(R.id.layout3);

        mainText1 = rootView.findViewById(R.id.mainText1);
        mainText2 = rootView.findViewById(R.id.mainText2);
        mainText3 = rootView.findViewById(R.id.mainText3);
        descText1 = rootView.findViewById(R.id.descipText1);
        descText2 = rootView.findViewById(R.id.descipText2);
        descText3 = rootView.findViewById(R.id.descipText3);
        imageView = rootView.findViewById(R.id.imageView3);
        progressBar = rootView.findViewById(R.id.progress_bar);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference1 = mFirebaseDatabase.getReference().child("Rescue_request");
        mMessagesDatabaseReference2 = mFirebaseDatabase.getReference().child("missing");
        mMessagesDatabaseReference3 = mFirebaseDatabase.getReference().child("Resource");

        if (mChildEventListener1 == null) {
            mChildEventListener1 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    final Resource resource = dataSnapshot.getValue(Resource.class);
                    if(resource != null)
                    {
                        mainText2.setText((resource.getStatus()).toUpperCase()+": "+resource.getRes_name());
                        descText2.setText(resource.getQuantity()+" for approximately "+resource.getSupply_for()+" people.");
                        layout2.setVisibility(View.VISIBLE);
                        layout2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent details = new Intent(getActivity(), ResourceDetailsActivity.class);
                                details.putExtra("object",resource);
                                startActivity(details);
                            }
                        });
                    }
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };
            mMessagesDatabaseReference3.addChildEventListener(mChildEventListener1);
        }

        if (mChildEventListener2 == null) {
            mChildEventListener2 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    final Rescue rescue = dataSnapshot.getValue(Rescue.class);
                    if(rescue != null)
                    {
                        mainText1.setText(rescue.getGender().toUpperCase()+", "+rescue.getAge()+" and "+rescue.getHow_may() +" others");
                        if(rescue.getDescription().length()==0)
                            descText1.setVisibility(View.GONE);
                        else
                            descText1.setText(rescue.getDescription());
                        layout1.setVisibility(View.VISIBLE);
                        layout1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent details = new Intent(getActivity(), RescueRequestDetails.class);
                                details.putExtra("object",rescue);
                                startActivity(details);
                            }
                        });
                    }
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };
            mMessagesDatabaseReference1.addChildEventListener(mChildEventListener2);
        }

        if (mChildEventListener3 == null) {
            mChildEventListener3 = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    final People people = dataSnapshot.getValue(People.class);
                    progressBar.setVisibility(View.GONE);
                    if(people != null)
                    {
                        mainText3.setText(people.getName()+", "+people.getAge()+" years old");
                        if(people.getDescription().length()==0)
                            descText3.setVisibility(View.GONE);
                        else
                            descText3.setText(people.getDescription());

                        Picasso.get().load(people.getPhoto_url()).into(imageView);
                        layout3.setVisibility(View.VISIBLE);
                        layout3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent details = new Intent(getActivity(), MissingDetailsActivity.class);
                                details.putExtra("object",people);
                                startActivity(details);
                            }
                        });
                    }

                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };
            mMessagesDatabaseReference2.addChildEventListener(mChildEventListener3);
        }

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");
    }

}
