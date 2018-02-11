package com.example.awesomefat.csc548_spring2018_baseconvert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class BaseToDecimal extends AppCompatActivity
{
    private EditText inputET;
    private TextView answerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_to_decimal);

        this.inputET = (EditText)this.findViewById(R.id.inputET);
        this.answerTV = (TextView)this.findViewById(R.id.answerTV);
    }
    private int mapCharToNum(char ch)
    {
        if (ch >= '0' && ch <= '9')
            return ch - '0';

        if (ch >= 'A' && ch <= 'Z')
        {
            return ch - 'A' + 10;
        }
        return ch - 'a' + 10;
    }

    private int convertBaseToDec(int base, String numStr)
    {
        int len = numStr.length();

        int val = 0;
        int exp = 0;

        for (int i = len - 1; i >=0; i--)
        {
            char ch = numStr.charAt(i);

            val = val + (int) java.lang.Math.pow(base, exp) * mapCharToNum(ch);

            exp++;
        }

        return val;
    }

    private boolean isValidNum(int base, String numStr)
    {
        int len = numStr.length();

        for (int i = 0; i < len; i++)
        {
            char ch = numStr.charAt(i);

            if (!(ch >= '0' && ch <= '9') &&
                    !(ch >= 'A' && ch <= 'Z') &&
                    !(ch >= 'a' && ch <= 'z'))
            {
                return false;
            }

            if (mapCharToNum(ch) >= base)
                return false;
        }
        return true;
    }
    public void baseButtonPressed(View v)

    {
        Button button = (Button)v;

        if (this.inputET.getText().toString().equals(""))
        {
            answerTV.setText("");
            Toast.makeText(BaseToDecimal.this, "Enter a number to convert",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String numStr = inputET.getText().toString();

        int base = Integer.parseInt(button.getText().toString());

        if (!isValidNum(base, numStr))
        {
            answerTV.setText("");
            Toast.makeText(BaseToDecimal.this, "Invalid number for selected base",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int decVal = convertBaseToDec(base, numStr);

        answerTV.setText("" + decVal);

    }
}
