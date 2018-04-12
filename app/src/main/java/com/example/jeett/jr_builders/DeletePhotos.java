package com.example.jeett.jr_builders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeletePhotos extends AppCompatActivity {


    FirebaseDatabase mFdb;
    DatabaseReference mRef;
    ChildEventListener mListener;
    ArrayList<property> arr;
    EditText et;Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_photos);

        et = (EditText) findViewById(R.id.deletephe);
        btn = (Button) findViewById(R.id.deleteph);

        mFdb = FirebaseDatabase.getInstance();
        mRef = mFdb.getReference().child("uploads");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener==null){
                    mListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Upload p = dataSnapshot.getValue(Upload.class);
                            if(p.getName().equals(et.getText().toString())){
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

