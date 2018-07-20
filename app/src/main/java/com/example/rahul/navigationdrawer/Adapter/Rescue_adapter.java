package com.example.rahul.navigationdrawer.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahul.navigationdrawer.Objects.Rescue;
import com.example.rahul.navigationdrawer.R;

import java.util.List;

public class Rescue_adapter extends ArrayAdapter<Rescue> {

    double lat1, lon1;

    public Rescue_adapter(Context context, int resource, List<Rescue> objects, double lat1, double lon1) {
        super(context, resource, objects);
        this.lat1 = lat1;
        this.lon1 = lon1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_item1, parent, false);
        }

        TextView distTextView =  convertView.findViewById(R.id.distance);
        TextView messageTextView =  convertView.findViewById(R.id.mainText);
        TextView descTextView =  convertView.findViewById(R.id.descipText);
        ImageView photo = convertView.findViewById(R.id.imageView);
        photo.setVisibility(View.GONE);

        Rescue rescue = getItem(position);

        Log.i("My location",lat1+", "+lon1);
        Log.i("My location",rescue.getLat()+", "+rescue.getLon());
        double dist = getDistance(lat1, lon1, rescue.getLat(), rescue.getLon(),'K');
        distTextView.setText((int)dist+"m away from you");
        messageTextView.setText(rescue.getGender().toUpperCase()+", "+rescue.getAge()+" and "+rescue.getHow_may() +" others");
        if(rescue.getDescription().length()==0)
            descTextView.setVisibility(View.GONE);
        else
            descTextView.setText(rescue.getDescription());

        return convertView;
    }

    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    private double getDistance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @Nullable
    @Override
    public Rescue getItem(int position) {
        return super.getItem(getCount()-position-1);
    }
}
