package com.ziana.bmidraft;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity
{
    EditText myHeight;
    RadioGroup weightGroup;
    TextView bmiResult, healthStatus;
    Double weight = 0.00;
    Double heightValue = 0.00;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHeight = (EditText) findViewById(R.id.txtHeight);
        weightGroup = (RadioGroup) findViewById(R.id.rgWeightRanges);
        bmiResult = (TextView) findViewById(R.id.bmiResult);
        healthStatus = (TextView) findViewById(R.id.tvHealthStatus);


    }


    public void workoutBMI(View view)
    {
        try
        {
            heightValue = Double.valueOf(myHeight.getText().toString());


        //Hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(myHeight.getWindowToken(), 0);


        boolean checked = ((RadioButton) view).isChecked();
        //int weight = weightGroup.getCheckedRadioButtonId();
        // if(!myHeight.getText().toString().isEmpty())

        // {
           //int range = weightGroup.getCheckedRadioButtonId();
            switch (view.getId())
            {

                case R.id.range1:
                    weight = 50.00;
                    if (checked)
                    {
                        startBMI=0.00;
                        calculateBMI();

                    }
                    break;
                case R.id.range2:
                    weight = 65.00;
                    if (checked)
                    {
                        startBMI=0.00;
                        calculateBMI();
                    }

                    break;

                case R.id.range3:

                    weight = 70.00;
                    if (checked)
                    {

                        startBMI=0.00;
                        calculateBMI();

                    }

                    break;
                default:Toast.makeText(getApplicationContext(), "unknown", Toast.LENGTH_SHORT).show();

            }


        } catch (NumberFormatException nfe)
        {

            Toast.makeText(getApplicationContext(), "Enter height", Toast.LENGTH_SHORT).show();


        }
    }
    Double startBMI =0.00;
    public void calculateBMI()
    {
        Double newHeightValueMeters = heightValue / 100.00;
        Double height = Math.pow(newHeightValueMeters, 2);
        final Double userBMI = (weight / height);


       // bmiResult.setText(String.valueOf(Math.round(userBMI * 100.0) / 100.0));

        //counter BMI

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(startBMI<userBMI)
                {
                    try
                    {
                            Thread.sleep(15);
                    }catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                    bmiResult.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {

                            bmiResult.setText(""+Math.round(startBMI*100.00)/100.00);

                        }
                    });
                    startBMI+=1.1;
                }
            }
        }).start();



        if (userBMI <= 18.5)
        {
            healthStatus.setText("Underweight");
            healthStatus.setTextColor(Color.RED);
        }
        else if (userBMI > 18.5 && userBMI < 24.5)
        {
            healthStatus.setText("Healthy weight");
            healthStatus.setTextColor(Color.GREEN);
        }
        else if (userBMI > 24.5 && userBMI < 29.9)
        {
            healthStatus.setText("Overweight");
            healthStatus.setTextColor(Color.MAGENTA);
        }
        else if (userBMI > 29.9)
        {
            healthStatus.setText("Obese");
            healthStatus.setTextColor(Color.RED);

        }
        else
        {
            healthStatus.setText("???????");
        }


    }





}




