import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class client2 {

	public static void main(String[] args) throws IOException {
		MulticastSocket s = new MulticastSocket(4444);
		 s.joinGroup(InetAddress.getByName("230.0.0.1")); // as an example
		 byte[] buf = new byte[1000];
		 DatagramPacket recv = new DatagramPacket(buf, buf.length);
		 s.receive(recv);
		 System.out.println(new String(recv.getData()));;
		 InetAddress nIp =  recv.getAddress();
		 int port = recv.getPort();
		 String msg = "something to check";
		 byte buf1[]=msg.getBytes();
		 DatagramPacket dp = new DatagramPacket(buf1,buf1.length,nIp,port);
		 s.send(dp);
	}

}
