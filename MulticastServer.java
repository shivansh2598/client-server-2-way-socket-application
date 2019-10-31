import java.io.IOException;
import java.net.*;

public class server {

	public static void main(String[] args) throws IOException {
			DatagramSocket ds = new DatagramSocket();
			System.out.println("Server started");
			InetAddress ip = InetAddress.getByName("230.0.0.1");
			String msg = "Hello All";
			byte buff[]=msg.getBytes();
			int port = 4444;
			DatagramPacket dp = new DatagramPacket(buff,buff.length,ip,port);
			ds.send(dp);
			
			byte recbuff[] = new byte[1000]; 
			DatagramPacket rdp = new DatagramPacket(recbuff,recbuff.length);
			ds.receive(rdp);
			
			System.out.println(new String(rdp.getData()));
			System.out.println(rdp.getAddress());
			
			
	}	

}
