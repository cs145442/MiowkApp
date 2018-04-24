package com.self.khetan.miowkapp;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by khetan on 11/6/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    //Creating Variable for storing color
    private int themeColor;

    //Defining the custom overloaded Constructor
    public WordAdapter(Activity context, List<Word> objects, int color) {
        super(context, 0, objects);
        themeColor = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.miowk_number_view, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView mioNum = (TextView) listItemView.findViewById(R.id.miowk_numTV);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        mioNum.setText(currentAndroidFlavor.getMiowkNum());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView engNum = (TextView) listItemView.findViewById(R.id.eng_numTV);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        engNum.setText(currentAndroidFlavor.getEngNum());

        //Find the ImageView in the miowk_number_view.xml with the ID ic_numView
        ImageView ic_num = (ImageView) listItemView.findViewById(R.id.ic_numView);
        //Get the source id of Icon from the current AndroidFlavor object and
        // set this image on the icon ImageView
        if(currentAndroidFlavor.getIc_num() == 0){
            ic_num.setVisibility(View.GONE);
        }
        else {
            ic_num.setImageResource(currentAndroidFlavor.getIc_num());
        }


        //Setting the theme color
        View detailView = listItemView.findViewById(R.id.miowk_num_View);
        int color = ContextCompat.getColor(getContext(), themeColor);
        detailView.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
