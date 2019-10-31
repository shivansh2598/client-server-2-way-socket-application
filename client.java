import java.io.*;
import java.net.*;

public class client {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket skt = new DatagramSocket();
        InetAddress ip=InetAddress.getByName("localhost"); 
        String msg = "Hello server";
        byte b[] = msg.getBytes();
        int port = 9001;

        DatagramPacket request = new DatagramPacket(b, b.length, ip, port);

        skt.send(request);

        byte buffer[] = new byte[10000];

        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        skt.receive(reply);
        System.out.println(new String(reply.getData()));

        skt.close();

    }
}