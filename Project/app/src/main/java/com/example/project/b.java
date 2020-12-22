package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class b extends AppCompatActivity implements View.OnClickListener {
    Button button7,button8,button9;
    int count7 = 0;
    int count8 = 0;
    int count9 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b);
        button7=(Button)findViewById(R.id.button7);
        button8=(Button)findViewById(R.id.button8);
        button9=(Button)findViewById(R.id.button9);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button7:
                count7++;
                Intent intent = new Intent(this, c.class);
                startActivity(intent);
                break;
            case R.id.button8:
                count8++;
                Intent intent2 = new Intent(this, c.class);
                startActivity(intent2);
                break;
            case R.id.button9:
                count9++;
                Intent intent3 = new Intent(this, c.class);
                startActivity(intent3);
                break;
        }
    }
}