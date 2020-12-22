package aaaaa1;


import java.net.*;
import java.util.Random;
import java.io.*;


public class server {
    public static void main(String[] args) {
       
       ServerSocket serversocket = null;
       Socket socket = null;
       
       try {
          serversocket = new ServerSocket(3008);
          socket = serversocket.accept();
          System.out.println("연결 성공");
          Thread thread2 = new receiverthread(socket);
          thread2.start();
       } catch (Exception e1) {

       } finally {
          try {
//             System.out.println("연결 실패");
             serversocket.close();
          } catch (Exception e1) {
             
          }
       }
    }
 }

 class receiverthread extends Thread {
    Socket socket;

    receiverthread(Socket socket) {
       this.socket = socket;
    }

    public void run() {
       //A A1 = new A();
       try {
          Random rand = new Random();
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         OutputStream out = socket.getOutputStream();
         
         
       // String str1 = in.readLine();
        //System.out.println(str1);
        
          while (true) {
             String str = in.readLine();

             System.out.println(str);
          //   A1.insert(str);
             if(str.equals("a")) {
            	
               int n = rand.nextInt(100);
               String str3 = Integer.toString(n);
               String a = str3;
               
               out.write(a.getBytes());
               
               
               
             
              out.write(a.getBytes());
            }else if(str.equals("bSensor")) {
            	
               int p = rand.nextInt(100);
                String str3 = Integer.toString(p);
               String a = str3;
               out.write(a.getBytes());
            }else if(str.equals("cSensor")) {
                int temperature= rand.nextInt(100);
                int humidity = rand.nextInt(100);

                 String t_str = Integer.toString(temperature);
               

                 String h_str = Integer.toString(humidity);
                

                 String result = t_str + "," + h_str;
                 out.write(result.getBytes());
                 
             }

            else if(str.equals("")) {
				System.out.println("쓰레드 종료");
				break;
			}
          }
       } catch (Exception e1) {

       } finally {
          try {
             socket.close();
          } catch (Exception e1) {

          }

       }

    }
 }
