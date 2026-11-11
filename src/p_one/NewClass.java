/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p_one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NewClass {
static ServerSocket ss;
static boolean s,c;
static Socket send,recive;
static BufferedReader from_client;
static BufferedReader br;
static PrintWriter pr;
//static int recive_p,send_p;
//static String name,server,port;
//static Scanner sc=new Scanner(System.in);
     NewClass(){
        while(client()){}
        while(server()){}
    }
    static boolean server(){
        boolean result;
        try{
            ss=new ServerSocket(8888);  //enter other client server port other
            recive=ss.accept();
            result=true;
        }
        catch(IOException ex){
            System.out.println(ex);
            result=false;
        }return result;
    }
    static boolean client(){
        boolean result;
        try{
            send=new Socket("localhost",9999); //create your own port
            result=true;
        }
//        catch(ConnectException e){
//                System.out.println("hiiii");
//                result=false;
//        }  
        catch(IOException ex){
            System.out.println(ex);
            result=false;
        }return result;
    }
    static String p;
    static Thread t1=new Thread(new Runnable(){ 
        public void run(){
               while(true){ try{
                //for reading data from client and printing
                  from_client=new BufferedReader(new InputStreamReader(recive.getInputStream()));
                  System.out.println("\n>>"+from_client.readLine());
                }
                
                catch(IllegalThreadStateException e){
                    System.out.println("hii");
                }
                catch(IOException e){
                    System.out.println("hooooooooii");
                }
               }
        }
    });
//    static Thread t2=new Thread(new Runnable(){ 
//        public void run(){
//                
//        }
//    });
     public static void main(String args[]) throws Exception {
        int i=0;
        while(server()){
            s=true;
            System.out.print("connected to client        ");
            t1.start();
            break;
        }
        while(client()){
            c=true;
            System.out.print("connected to server        ");
            break;
        }
        
        while(true){
                i++;
                //System.out.println(i);
                
//                //taking data from user to send
//                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//                System.out.println("Enter Data....");
//                String m=br.readLine();
//                
//                //for sending data to server
//                PrintWriter pr =new PrintWriter(send.getOutputStream(),true); //true for auto flush data replace of pr.flush();
//                pr.println(m);
                
                  
                //}
                if(s && c){
                    try{
                    //taking data from user to send
                    br=new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Enter Data....");
                    String m=br.readLine();
                
                    //for sending data to server
                    pr =new PrintWriter(send.getOutputStream(),true); //true for auto flush data replace of pr.flush();
                    pr.println(m);
                }
                catch(IllegalThreadStateException e){
                    System.out.println("hii");
                }
                catch(IOException e){
                    System.out.println("hooooooooii");
                }
                }

        } 
     }
}