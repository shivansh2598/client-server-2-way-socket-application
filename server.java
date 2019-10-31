import java.util.*;
import java.io.*;
import java.net.*;



public class server
{
    public static void main(String args[]) throws Exception
    {
        DatagramSocket skt= new DatagramSocket(9001);
        System.out.println("server started");

        while(true)
        {
            byte buffer[]=new byte[10000];
            DatagramPacket request= new DatagramPacket(buffer, buffer.length);
            skt.receive(request);

            System.out.println(new String(request.getData()));

            String msg = "Hello Client";

            byte b[] = msg.getBytes();

            DatagramPacket reply = new DatagramPacket(b, b.length, request.getAddress(), request.getPort());

            skt.send(reply);

        }

        
    }
}