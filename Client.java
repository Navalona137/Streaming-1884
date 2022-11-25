package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.Socket;

public class Client{ 
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        socket = new Socket(host.getHostName(), 9876);
        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending request to Socket Server");
   
        oos.writeObject("music");

        ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        String[] messages = message.split(",");
        for(int i=0; i<messages.length; i++){
            System.out.println(messages[i]);
        }

        ois.close();
        oos.close();
        Thread.sleep(100);
    }
    
}
