package p_one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
public class Cl {
    static BufferedReader br,br1;
    static Socket socket;
    static PrintWriter pr;
        public static void main(String args[]){
            try{
                System.out.println("Client started.....");
                socket=new Socket("localhost",1234);
                recive.start();
                
                while(true){
                    //read data from user by keyboard
                    br=new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("Enter Data....");
                    String m=br.readLine();
                
                    //for sending data to server
                    pr =new PrintWriter(socket.getOutputStream(),true);//true for auto flush data replace of pr.flush();
                    pr.println(m);
                    //s.close();
                }
            }
            catch(IOException e){
                System.out.println(e);
            }
        }
static Thread recive=new Thread(new Runnable(){ 
        public void run(){
                while(true){
                    try{
                       //for printing data from server
                        br1=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        System.out.println(">> "+br1.readLine());
                    }
                    catch(IOException e){
                        System.out.println("Reciving Error");
                    }
                }
        }
    });
}
