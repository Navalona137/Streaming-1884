package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class Serveur{ 
    private static ServerSocket server;
    private static int port = 9876;

    private static File myFile = new File("D:/PROG/Java/Mr Naina/Streaming multimetida/hira");
    private static File[] liste=myFile.listFiles();
    private static int longueur=liste.length;
    private static String music = new String();

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        server = new ServerSocket(port);
        while(true){
            System.out.println("Waiting for the client request");

            Socket socket = server.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message received:" + message);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            for(int j=0;j<longueur;j++){
                try{
                    music = music + " , " + liste[j].getName();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(music);
            oos.writeObject(music);
            ois.close();
            oos.close();
            socket.close();

            if(message.equalsIgnoreCase("music")) break;
        }
        System.out.println("Shutting down Socket server!");
        server.close();
    }
}
