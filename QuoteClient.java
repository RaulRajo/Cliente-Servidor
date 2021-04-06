package actividad3;

import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuoteClient extends Observable implements Runnable{


    public void run() {
        // get a datagram socket
        try{
        DatagramSocket socket = new DatagramSocket();

        // send request
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);

        // get response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Quote of the Moment: " + received);

        setChanged();
        this.notifyObservers(received);
        socket.close();
    } 
        catch (SocketException ex){
            Logger.getLogger(QuoteClient.class.getName()).log(Level.SEVERE, null, ex);} catch (IOException ex) {   
        }   
    }
}


