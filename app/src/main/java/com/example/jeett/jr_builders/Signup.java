package com.example.jeett.jr_builders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.google.firebase.auth.FirebaseAuth.*;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    private Button registerbutton;
    private EditText email;
    private EditText pass;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        progressDialog = new ProgressDialog(this);

        //we can use the object to register all the user to the server
        firebaseAuth = FirebaseAuth.getInstance();

        databaseUser = FirebaseDatabase.getInstance().getReference("user");

        //intilize views
        registerbutton = (Button) findViewById(R.id.registerbutton);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);

        registerbutton.setOnClickListener(this);
    }

    private void registerUser()
    {
        final String gemail = email.getText().toString().trim();
        final String gpass = pass.getText().toString().trim();

        if(TextUtils.isEmpty(gemail))
        {
            //email is empty
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            //stop the function execution further
            return;
        }
        if(TextUtils.isEmpty(gpass))
        {
            //pass is empty
            Toast.makeText(this,"Pease enter password",Toast.LENGTH_SHORT).show();
            //stop the function execution further
            return;
        }
        //if validation ok
        //show the progress dialog
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        //this method takes two arguments email and pass
        //we can add listener to track completion
        firebaseAuth.createUserWithEmailAndPassword(gemail,gpass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            //User is successfully register loged in....
                            finish();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                           //String id = databaseUser.push().getKey();
                           // User user = new User(id, gemail,gpass);
                           // databaseUser.child(id).setValue(user);

                        }else
                        {
                            Toast.makeText(Signup.this,"Could not register , please try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if(view == registerbutton)
        {
            registerUser();

            //addUser();
        }

    }

   /* private void addUser() {

        String gemail = email.getText().toString().trim();
        String gpass = pass.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(gemail)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist

            String id = databaseUser.push().getKey();

            //creating an Artist Object
            User user = new User(id, gpass);

            //Saving the Artist
            databaseUser.child(id).setValue(user);


        } else {

        }


    }
    */
}
