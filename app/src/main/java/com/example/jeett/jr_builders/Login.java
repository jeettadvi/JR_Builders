package com.example.jeett.jr_builders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.LENGTH_SHORT;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button loginbutton;
    private Button signbutton;
    private EditText email;
    private EditText pass;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        loginbutton = (Button) findViewById(R.id.loginbutton);
        signbutton = (Button) findViewById(R.id.signupbutton);

        loginbutton.setOnClickListener(this);
        signbutton.setOnClickListener(this);



    }

    private void userLogin()
    {
        String gemail = email.getText().toString().trim();
        String gpass = pass.getText().toString().trim();

        if(TextUtils.isEmpty(gemail))
        {
            //email is empty
            Toast.makeText(this,"Please enter email", LENGTH_SHORT).show();
            //stop the function execution further
            return;
        }
        if(TextUtils.isEmpty(gpass))
        {
            //pass is empty
            Toast.makeText(this,"Pease enter password", LENGTH_SHORT).show();
            //stop the function execution further
            return;
        }
        //if validation ok
        //show the progress dialog
        progressDialog.setMessage("Login...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(gemail,gpass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            //User is successfully register loged in....
                            finish();
                            startActivity(new Intent(getApplicationContext(),Home.class));

                        }else
                        {
                            Toast.makeText(Login.this,"Incorrect email and password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if(view == loginbutton)
        {
            String gemail = email.getText().toString().trim();
            String gpass = pass.getText().toString().trim();

            String bemail = "builder@gmail.com";
            String bpass = "builder";

            if(gemail.equals(bemail) && gpass.equals(bpass))
            {
                Intent intent = new Intent(this,Add_Property.class);
                startActivity(intent);
            }
        }

        if (view == loginbutton)
        {
            userLogin();
        }

        if(view == signbutton)
        {
            finish();
            Intent intent = new Intent(this,Signup.class);
            startActivity(intent);
        }
    }
}
