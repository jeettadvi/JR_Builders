package com.example.jeett.jr_builders;

/**
 * Created by jeett on 08-Apr-18.
 */

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Belal on 2/23/2017.
 */

public class MyAdapterPr extends ArrayAdapter<property> {

    private ArrayList<property> arrayList;
    Context context;
    int r;

    public MyAdapterPr(ArrayList<property> arrayList, int r, Context context){
        super(context,r,arrayList);
        this.arrayList = arrayList;
        this.context = context;
        this.r = r;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(r, null, false);

        TextView title = (TextView) view.findViewById(R.id.textViewName);
        TextView description = (TextView) view.findViewById(R.id.textViewDescription);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        final Button contact = (Button) view.findViewById(R.id.contact);
        property data = arrayList.get(position);

        description.setText("Description: "+data.getDiscription());
        title.setText("Title: "+data.getTital());
        Glide.with(context).load(data.getUrl()).into(img);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(context,Contactus.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(i);
                }catch (Exception e) {
                    Log.d("JR_BUILDER", "onClick: "+e.getMessage());
                }
            }
        });

        return view;
    }
}