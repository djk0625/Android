package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class d extends AppCompatActivity implements View.OnClickListener {
    Button button1,button2,button3;
    int count1 = 0;
    int count2 = 0;
    int count3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button1:
                count1++;
                Intent intent = new Intent(this, a.class);
                startActivity(intent);
                break;
            case R.id.button2:
                count2++;
                Intent intent2 = new Intent(this, a.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                count3++;
                Intent intent3 = new Intent(this, a.class);
                startActivity(intent3);
                break;
        }
    }
}