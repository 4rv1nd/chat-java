package p_one;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Sr {
    
    static ArrayList<Socket> clients=new ArrayList();
    static ServerSocket server;
    static BufferedReader from_client;
    static Socket socket;
    static String data;
   public static void main(String args[]){
       try{
           System.out.println("Waiting.....");
           server=new ServerSocket(1234);
           
           client_connections.start();
           recive.start();
           
           while(true){
                System.out.println("enter data to send"); 
                Scanner s=new Scanner(System.in);
                data=s.nextLine();
                if(!data.equals(""))
                      broadcast();
           }
       }
       catch(IOException e){
           System.out.println(e);
       }
       
   }
   
   static void broadcast(){
       try{
            for(int i=0;i<clients.size();i++){
           
                Socket ts=clients.get(i);
                //for sending data server to all clients
                PrintWriter pr =new PrintWriter(ts.getOutputStream(),true);
                pr.println(data);
                
            }
            data="";
       }
       catch(IOException e){
           System.out.println("data sending error");
       }
           
   }
   static Thread client_connections=new Thread(new Runnable(){ 
        public void run(){
                while(true){
                    try{
                        socket=server.accept();
                        clients.add(socket);
                        System.out.println("Connected.....");
                        System.out.println(clients.size());
                    }
                    
                    catch(IOException e){
                        System.out.println("Connection error");
                    }
                }
        }
    });
   static Thread recive=new Thread(new Runnable(){ 
        public void run(){
                while(true){
                    try{
                        //for reading data from client and printing
                        from_client=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        data=from_client.readLine();
                        System.out.println(">> "+data);
                        if(!data.equals(""))
                            broadcast();
                    }
                    catch(NullPointerException e){
                        //System.out.println("hiiiiiiiii");
                    }
                    catch(IOException e){
                        System.out.println("Reciving Error");
                    }
                }
        }
    });
}
