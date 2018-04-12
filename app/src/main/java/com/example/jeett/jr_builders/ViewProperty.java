package com.example.jeett.jr_builders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewProperty extends AppCompatActivity {

    FirebaseDatabase mFdb;
    DatabaseReference mRef;
    ChildEventListener mCEListener;
    ArrayList<property> arr;
    MyAdapterPr announceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property);
        getSupportActionBar().setTitle("Property");
        arr = new ArrayList<>();
        mFdb = FirebaseDatabase.getInstance();
        mRef = mFdb.getReference().child("property");
        retrive();
        announceAdapter = new MyAdapterPr(arr, R.layout.layout_property, getApplicationContext());
        ListView l = (ListView) findViewById(R.id.recyclerView);
        l.setAdapter(announceAdapter);

    }

    void retrive() {
        if (mCEListener == null) {
            mCEListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    property ann = dataSnapshot.getValue(property.class);
                    announceAdapter.add(ann);

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mRef.addChildEventListener(mCEListener);
        }
    }

}
