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

            String stringaRicevuta;
            boolean stato = true;
            int cont = 0;

            
            do{
                stringaRicevuta = in.readLine();
                int sNum = Integer.parseInt(stringaRicevuta);
                
                if(sNum >= 100 || sNum < 0){
                    cont++;
                    out.writeBytes("!!" + "\n");
                }else{
                    if(sNum < numero){
                        cont++;
                        out.writeBytes("<" + "\n");
                    }else if(sNum >numero){
                        cont++;
                        out.writeBytes(">" + "\n");
                    }else if(sNum == numero){
                        cont++;
                        out.writeBytes("=" + "\n");
                        out.writeBytes(cont + "\n");
                    }
                }   
            }while(stato);

            s.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    
}
