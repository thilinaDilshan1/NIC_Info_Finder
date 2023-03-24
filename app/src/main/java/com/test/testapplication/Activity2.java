package com.test.testapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import java.time.*;
import java.util.*;

public class Activity2 extends AppCompatActivity {
Button btn;
TextView nicOut,bDayOut,genderOut,ageOut;
String nic,pureNic;
boolean check;

    int months[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        MainActivity main=new MainActivity();
        btn=findViewById(R.id.btn2);
        nicOut=findViewById(R.id.textView2);
        bDayOut=findViewById(R.id.bDayOutput);
        genderOut=findViewById(R.id.genderOutput);
        ageOut=findViewById(R.id.ageOutput);


        //Intent i=new Intent();
        pureNic = getIntent().getStringExtra("nic");
        check=getIntent().getExtras().getBoolean("check");
        if(pureNic.length()==10){
            nic=pureNic;
        }
        else{
            nic=pureNic.substring(2,7)+pureNic.substring(8,12);
        }
        nicOut.setText(pureNic);

        //find year
        String y = nic.substring(0, 2);//get the first two digits from nic number
        String year="19"+y;

        //get day and month
        String d = nic.substring(2, 5);
        int daysValue = Integer.parseInt(d);
        if (daysValue > 500) {
            daysValue = daysValue - 500;//get the actual days of the year of female's nic
        }
        leapyearCheck(Integer.parseInt("19" + nic.substring(0, 2)));
        int month = 0;
        int date = 0;
        for (int m = 0; m < months.length; m++) {
            if (daysValue > months[m]) {
                daysValue = daysValue - months[m];
            } else {
                date = daysValue;
                month = m + 1;
                break;
            }
        }
        String fullBirthday=year+ " - "+ Integer.toString(month)+ " - "+Integer.toString(date);

        bDayOut.setText(fullBirthday);

        //get gender
        String s = nic.substring(2, 5);
        if (Integer.parseInt(s) > 500) {
            genderOut.setText("Female");
        } else {
            genderOut.setText("Male");
        }

        //age
        //obtains an instance of LocalDate from a year, month and date
        LocalDate dob = LocalDate.of(Integer.parseInt(year), month, date);
        //obtains the current date from the system clock
        LocalDate curDate = LocalDate.now();
        //calculates the difference betwween two dates
        Period period = Period.between(dob, curDate);
        //prints the differnce in years, months, and days
        //System.out.printf("Your age is %d years %d months and %d days.", period.getYears(), period.getMonths(), period.getDays());
        String yyyy=Integer.toString(period.getYears());
        String mm=Integer.toString(period.getMonths());
        String dd=Integer.toString(period.getDays());
        ageOut.setText(yyyy+" years, "+mm+" months, "+dd+" days");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    public int leapyearCheck(int yr) {
        if (yr % 100 != 0 || yr % 4 == 0 && yr % 400 == 0) {
            months[1] = 29;//when leap year
        } else {
            months[1] = 28; //otherwise
        }

        return months[1];
    }
}