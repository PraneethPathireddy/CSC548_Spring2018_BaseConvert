package com.example.awesomefat.csc548_spring2018_baseconvert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class DecimalToBase extends AppCompatActivity
{

    private EditText inputET;
    private TextView answerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal_to_base);

        this.inputET = (EditText)this.findViewById(R.id.inputET);
        this.answerTV = (TextView)this.findViewById(R.id.answerTV);
    }

    private void strReverse(char arr[])
    {
        for (int i = 0; i < arr.length / 2; i++)
        {
            char tmp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = tmp;
        }
    }


    private String convertDecToBase(String numStr, int base)
    {
        int  num = Integer.parseInt(numStr);
        String res = "";

        while (num > 0)
        {
            if ((num % base) <= 9)
                res += Integer.toString(num % base);
            else
                res += Character.toString((char)('A' + (num % base) - 10));

            num /= base;
        }
        if (res == "")
            return "0";

        char cArr[] = res.toCharArray();

        strReverse(cArr);

        String finalStr = new String(cArr);

        return finalStr;
    }

    public void baseButtonPressed(View v)
    {
        Button button = (Button)v;

        if (this.inputET.getText().toString().equals(""))
        {
            answerTV.setText("");
            Toast.makeText(DecimalToBase.this, "Enter a number to convert",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String numStr = inputET.getText().toString();

        int base = Integer.parseInt(button.getText().toString());

        answerTV.setText("Base " + base + " :  " + convertDecToBase(numStr, base));

    }

}
