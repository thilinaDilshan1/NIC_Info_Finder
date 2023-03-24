package com.test.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn;
EditText input;
String nic;
CheckBox checkBox;
boolean check;

    int months[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input=findViewById(R.id.nicInput);
        btn=findViewById(R.id.btn1);
        checkBox=findViewById(R.id.checkBoxNewNic);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nic=input.getText().toString();
                check=checkBox.isChecked();
                if(nic.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter the NIC", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i =new Intent(MainActivity.this,Activity2.class);
                    i.putExtra("nic",nic);
                    i.putExtra("check",check);
                    startActivity(i);
                }
            }
        });

        findViewById(R.id.image_twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUrl("https://twitter.com/Thilina75704889");
            }
        });

        findViewById(R.id.image_fb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUrl("https://www.facebook.com/thilina.dilshan.7906");
            }
        });

        findViewById(R.id.image_insta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUrl("https://www.instagram.com/_thilina_dilshan/");
            }
        });

        Intent intent=new Intent(Intent.ACTION_SEND);
        final Intent chooser;
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"tilinadilshan1999@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"NIC Info Finder");
        intent.setType("message/rfc@22");
        chooser=Intent.createChooser(intent,"Send Email to Developer");

        findViewById(R.id.image_mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(chooser);
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(chooser);
            }
        });
    }

    private void getUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


}