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
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class P_three extends JFrame {
    static JTextField tf1,tf2,tf3,tf4;
    static JLabel l1,l2,l3,l4;
    static TextArea ta;
    static JPanel p;
    static JButton b,sendb;
    static int port;
    static String name,server_n;
    static BufferedReader br,br1;
    static Socket socket;
    static PrintWriter pr;
    P_three(){
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
        
        l1=new JLabel("Enter ip: ");
        l1.setBounds(5,5,100,40);
        l1.setSize(100,40);
        l1.setBackground(Color.WHITE);
        l1.setForeground(Color.BLACK);
        p.add(l1);
        
        tf1=new JTextField("127.0.0.1");
        tf1.setBounds(80,10,100,25);
        tf1.setBackground(Color.WHITE);
        tf1.setForeground(Color.BLACK);
        p.add(tf1);
        
        l2=new JLabel("Enter port: ");
        l2.setBounds(5,60,100,40);
        l2.setSize(100,40);
        l2.setBackground(Color.WHITE);
        l2.setForeground(Color.BLACK);
        p.add(l2);
        
        tf2=new JTextField("1234");
        tf2.setBounds(80,65,100,25);
        tf2.setBackground(Color.WHITE);
        tf2.setForeground(Color.BLACK);
        p.add(tf2);
        
        
        l3=new JLabel("Enter name: ");
        l3.setBounds(5,110,100,40);
        l3.setSize(100,40);
        l3.setBackground(Color.WHITE);
        l3.setForeground(Color.BLACK);
        p.add(l3);
        
        tf3=new JTextField("a");
        tf3.setBounds(80,115,100,25);
        tf3.setBackground(Color.WHITE);
        tf3.setForeground(Color.BLACK);
        p.add(tf3);
        
        b=new JButton("Connect");
        b.setBounds(40,150,100,25);
        p.add(b);
        
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
        
    }
    public static void main(String[] args) {
        new P_three().setVisible(true);
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                server_n=tf1.getText();
                port=Integer.parseInt(tf2.getText());
                name=tf3.getText();
                try{
                System.out.println("Client started.....");
                socket=new Socket(server_n,port);
                recive.start(); 
                tf3.setText("");
            }
            catch(IOException e){
                System.out.println(e);
            }
            }
            
        });
        sendb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            try{        
                    pr =new PrintWriter(socket.getOutputStream(),true);//true for auto flush data replace of pr.flush();
                    pr.println("from "+name+" :"+tf4.getText());
            }        
            catch(IOException e){
                System.out.println("Data sending error");
            }
            }
        });
    }
    static Thread recive=new Thread(new Runnable(){ 
        public void run(){
                while(true){
                    try{
                       //for printing data from server
                        br1=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        //System.out.println(">> "+br1.readLine());
                        ta.setText(ta.getText()+"\n"+br1.readLine());
                    }
                    catch(IOException e){
                        System.out.println("Reciving Error");
                    }
                }
        }
    });
}
