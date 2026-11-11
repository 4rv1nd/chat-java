/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p_one;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class P_one extends JFrame {
    static JTextField tf1,tf2,tf3,tf4;
    static JLabel l1,l2,l3,l4;
    static TextArea ta;
    static JPanel p;
    static JButton b,sendb;
    static int port,server_n;
    static ArrayList<Socket> clients=new ArrayList();
    static ServerSocket server;
    static BufferedReader from_client;
    static Socket socket;
    static String data;
    P_one(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(500,600);
        setBackground(Color.blue);
        setLayout(null);
        setLocation(400,50);
        
        p=new JPanel();
        p.setLayout(null);
        p.setBackground(Color.LIGHT_GRAY);
        p.setSize(485,600);
        add(p);

        //for data sending
        l4=new JLabel("Enter Text");
        
        l4.setBounds(50,265,100,30);
        l4.setSize(100,40);
        l4.setBackground(Color.WHITE);
        l4.setForeground(Color.BLACK);
        p.add(l4);
        
        tf4=new JTextField();
        tf4.setBounds(5,300,180,30);
        tf4.setBackground(Color.WHITE);
        tf4.setForeground(Color.BLACK);
        p.add(tf4);
        
        
        sendb=new JButton("Send");
        sendb.setBounds(40,340,100,25);
        p.add(sendb);
        
        ta=new TextArea();
        ta.setBounds(190,5,290,550);
        ta.setBackground(Color.WHITE);
        ta.setForeground(Color.BLACK);
        ta.setEditable(false);
        p.add(ta);
        
        port=Integer.parseInt(JOptionPane.showInputDialog("Enter Port Number To start a server"));
        
        System.out.println("Server Started on: "+port);
    }
    public static void main(String[] args) {
        new P_one().setVisible(true);
        
        try{
           
           server=new ServerSocket(port);
           System.out.println("Waiting.....");
           client_connections.start();
           recive.start();
           
       }
        catch(IOException e){
           System.out.println(e);
       }
        sendb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                data="From Server: "+tf4.getText();
                ta.setText(ta.getText()+"\nYou: "+tf4.getText());
                tf4.setText("");
                if(!data.equals(""))
                      broadcast();
                }
            
        });
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
                        //System.out.println(">> "+data);
                        ta.setText(ta.getText()+"\n"+data);
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
