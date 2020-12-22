package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.project.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity  {
    //handler : 메인 쓰레드와 서브 쓰레드 간에 메세지 수신하기 위해서 사용
    // LinkedList 객체 생성시 데이터 저장 영역이 생기지 않으며, 서로 인접 데이터를 가리킨다.
    TextView showText;
    Button connectBtn;
    EditText ip_EditText;
    EditText port_EditText;
    EditText editText_message;
    Handler msghandler;
    Button start_button;


    SocketClient client;    // 서버 접속을 위한 클래스
    ReceiveThread receive;  // 서버에서 보내온 데이터 수신을 위한 클래스
    SendThread send;        // 핸드폰에서 서버로 데이터 전송을 위한 클래스
    Socket socket;          // 네트워크 접속을 위한 클래스

    LinkedList<SocketClient> threadList; // 객체 생성시 데이터 저장 영역이 생기지 않으며, 서로 인접한 데이터를 가리킨다.

    // 1. Handler를 이용해서 쓰레드 간 메세지 전달
    // 2-1. client ip.port
    // 2-2. receive thread


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //액티비티 초기화
        setContentView(R.layout.activity_main);//액티비티 레이아웃 설정

        ip_EditText = (EditText)findViewById(R.id.ip_EditText);
        port_EditText = (EditText)findViewById(R.id.port_EditText);
        connectBtn = (Button)findViewById(R.id.connect_Button);
        threadList = new LinkedList<MainActivity.SocketClient>();
        ip_EditText.setText("");
        port_EditText.setText("90");
        start_button = (Button)findViewById(R.id.start_button);

        //연결

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client = new SocketClient(ip_EditText.getText().toString(), port_EditText.getText().toString());
                threadList.add(client);
                client.start();
            }
        });
        //전송

        Button t = (Button) findViewById(R.id.start_button);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), d.class);
                startActivity(intent);
                finish();
            }
        });
    }
    class SocketClient extends Thread{
        boolean threadAlive;
        String ip;
        String port;
        DataOutputStream output = null;

        public SocketClient(String ip, String port){
            threadAlive = true;
            this.ip = ip;
            this.port = port;
        }

        @Override
        public void run() {
            try{
                socket = new Socket(ip, Integer.parseInt(port));
                output = new DataOutputStream(socket.getOutputStream());
                receive = new ReceiveThread(socket);
                receive.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    class ReceiveThread extends Thread{
        private Socket sock = null;
        DataInputStream input;
        public ReceiveThread(Socket socket){
            this.sock = socket;
            try{
                input = new DataInputStream(sock.getInputStream());
            }catch (Exception e){

            }
        }
        public void run(){
            try{
                while(input != null){
                    String msg;
                    int count = input.available();
                    byte[] rcv = new byte[count];
                    input.read(rcv);
                    msg = new String(rcv);
                    //메세지 수신 후 Handler로 전달
                    if(count > 0){
                        Log.d(ACTIVITY_SERVICE, "test" + msg);
                        Message hdmsg = msghandler.obtainMessage();
                        hdmsg.what = 1111;
                        hdmsg.obj = msg;
                        msghandler.sendMessage(hdmsg);
                        Log.d(ACTIVITY_SERVICE, hdmsg.obj.toString());
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    class SendThread extends Thread{
        Socket socket;
        String sendmsg = editText_message.getText().toString() + "\n";
        DataOutputStream output;
        public SendThread(Socket socket){
            this.socket = socket;
            try {
                output = new DataOutputStream(socket.getOutputStream());
            } catch (Exception e){

            }
        }
        public void run(){
            try {
                Log.d(ACTIVITY_SERVICE, "1111111");
                if(output != null){
                    if(sendmsg != null){
                        output.write(sendmsg.getBytes());
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}