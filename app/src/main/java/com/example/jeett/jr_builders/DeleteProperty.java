package com.example.jeett.jr_builders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeleteProperty extends AppCompatActivity {


    FirebaseDatabase mFdb;
    DatabaseReference mRef;
    ChildEventListener mListener;
    ArrayList<property> arr;
    EditText et;Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_property);

        et = (EditText) findViewById(R.id.deleteprop);
        btn = (Button) findViewById(R.id.deletepr);

        mFdb = FirebaseDatabase.getInstance();
        mRef = mFdb.getReference().child("property");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener==null){
                    mListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            property p = dataSnapshot.getValue(property.class);
                            if(p.getTital().equals(et.getText().toString())){
                                dataSnapshot.getRef().removeValue();
                            }
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
                    mRef.addChildEventListener(mListener);
                }
            }
        });


    }


}
