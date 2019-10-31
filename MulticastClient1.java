import java.io.IOException;
import java.net.*;



public class client1 {

	public static void main(String[] args) throws IOException {
		MulticastSocket s = new MulticastSocket(4444);
		s.joinGroup(InetAddress.getByName("230.0.0.1"));
		 byte[] buf = new byte[1000];
		 DatagramPacket recv = new DatagramPacket(buf, buf.length);
		 s.receive(recv);
		 System.out.println(new String(recv.getData()));;
	}

}
