package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.lang.*;
import java.io.*;
import java.util.*;

public class MioThread extends Thread{
    Socket s;
    
    public MioThread(Socket s){
        this.s = s;
    }

    public void run(){

        try (BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            
            Random random = new Random();
            int numero = random.nextInt(100);

            String confronto;

            do{

                confronto = in.readLine();

                switch(confronto.compareTo(String.valueOf(numero))){
                    case 1:
                        out.writeBytes(">" + "\n");
                        break;

                    case -1:
                    out.writeBytes("<" + "\n");
                        break;
                    
                    default:
                        out.writeBytes("!!" + '\n');
                        break;

                }

                
                
            }while(!confronto.equals("0"));
            s.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    
}