package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class a extends AppCompatActivity implements View.OnClickListener {
    Button button4,button5,button6;
    int count4 = 0;
    int count5 = 0;
    int count6 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button4:
                count4++;
                Intent intent = new Intent(this, b.class);
                startActivity(intent);
                break;
            case R.id.button5:
                count5++;
                Intent intent2 = new Intent(this, b.class);
                startActivity(intent2);
                break;
            case R.id.button6:
                count6++;
                Intent intent3 = new Intent(this, b.class);
                startActivity(intent3);
                break;
        }
    }
}