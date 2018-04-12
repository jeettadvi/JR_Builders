package com.example.jeett.jr_builders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText mno = (EditText) findViewById(R.id.mobileno);
        final EditText feedback = (EditText) findViewById(R.id.feedback);

        Button bemail = (Button) findViewById(R.id.bemail);


        bemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gname = name.getText().toString();
                String gemail = email.getText().toString();
                String gmno = mno.getText().toString();
                String gfeedback = feedback.getText().toString();


                if(TextUtils.isEmpty(gname))
                {
                    name.setError("Enater name");
                    name.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(gmno))
                {
                    name.setError("Enater mobile no");
                    name.requestFocus();
                    return;
                }


                Boolean onError = false;
                if(!isValidEmail(gemail)) {
                    onError = true;
                    email.setError("Invalid Email");
                    return;
                }

                if(TextUtils.isEmpty(gfeedback))
                {
                    feedback.setError("Write Feedback");
                    feedback.requestFocus();
                    return;
                }


                Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);

                //Fill it with data
                sendEmail.setType("plain/text");
                sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL,new String[]{"jr.builders@gmail.com"});
                sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Name : "+gname +'\n' +"Mobile no : "+gmno +'\n'+"email : "+gemail +'\n' +"Feedback : "+'\n'+gfeedback);

                startActivity(Intent.createChooser(sendEmail,"Send fay6eedback..."));



            }
        });


    }
    private boolean isValidEmail(String bemail)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(bemail);
        return matcher.matches();

    }
}
