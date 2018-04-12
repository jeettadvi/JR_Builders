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

public class Contactus extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);


        final EditText name = (EditText) findViewById(R.id.name);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText mobileno = (EditText) findViewById(R.id.mobileno);
        final EditText subject = (EditText) findViewById(R.id.subject);
        final EditText message = (EditText) findViewById(R.id.message);

        Button bemail = (Button) findViewById(R.id.bemail);
        bemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gname = name.getText().toString();
                String gemail = email.getText().toString();
                String gmno = mobileno.getText().toString();
                String gsubject = subject.getText().toString();
                String gmessage = message.getText().toString();

                if(TextUtils.isEmpty(gname))
                {
                    name.setError("Enater name");
                    name.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(gmno))
                {
                    name.setError("Enater Mobile no");
                    name.requestFocus();
                    return;
                }

                Boolean onError = false;
                if(!isValidEmail(gemail))
                {
                    onError = true;
                    email.setError("Invalid Email");
                    return;
                }

                if(TextUtils.isEmpty(gsubject))
                {
                    subject.setError("Enter subject");
                    subject.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(gmessage))
                {
                    message.setError("Enter Message");
                    message.requestFocus();
                    return;
                }


                Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);

                //Fill it with data
                sendEmail.setType("plain/text");
                sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL,new String[]{"jr.builders@gmail.com"});
                sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT,gsubject);
                sendEmail.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Name : "+gname +'\n' +"email : "+gemail+ '\n'+"mobile no : "+gmno +'\n' +"Message : "+'\n'+gmessage);

                startActivity(Intent.createChooser(sendEmail,"Send mail..."));

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
