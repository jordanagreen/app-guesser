package com.example.jordanagreen.appguesser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jordan on 6/20/2016.
 */
public class AppAdapter extends ArrayAdapter<App> {

    public AppAdapter(Context context, ArrayList<App> apps){
        super(context, 0, apps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        App app = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_list_item, parent, false);
        }
        // Lookup view for data population
        TextView appName = (TextView) convertView.findViewById(R.id.appName);
        // Populate the data into the template view using the data object
        appName.setText(app.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
