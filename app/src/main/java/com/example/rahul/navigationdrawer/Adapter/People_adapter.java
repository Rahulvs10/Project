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

import com.example.rahul.navigationdrawer.Objects.People;
import com.example.rahul.navigationdrawer.Objects.Rescue;
import com.example.rahul.navigationdrawer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class People_adapter extends ArrayAdapter<People> {

    public People_adapter(Context context, int resource, List<People> objects) {
        super(context, resource, objects);

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

        People people = getItem(position);

        distTextView.setVisibility(View.GONE);
        messageTextView.setText(people.getName()+", "+people.getAge()+" years old");
        if(people.getDescription().length()==0)
            descTextView.setVisibility(View.GONE);
        else
            descTextView.setText(people.getDescription());

        Picasso.get().load(people.getPhoto_url()).into(photo);

        return convertView;
    }

    @Nullable
    @Override
    public People getItem(int position) {
        if(getCount()>=1)
            return super.getItem(getCount()-position-1);
        else
            return super.getItem(position);
    }
}
