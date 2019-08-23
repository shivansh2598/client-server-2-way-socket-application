import java.io.*;
import java.net.*;

public class Client1 {
	public static void main(String args[]) throws Exception
	{
			String ip="localhost";
			InetAddress i=InetAddress.getByName("localhost");
			int port=9000;
			Socket s= new Socket(ip,port,i, 60000);
			String st;
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			DataInputStream in=new DataInputStream(s.getInputStream());
			while(true)
			{
				System.out.print("->");
				st=br.readLine();
				out.writeUTF(st);
				if(st.equals("bye"))
				break;
				String str=in.readUTF();
				System.out.println("server: "+str);				
			}

			System.out.println("Server_Port: " + s.getPort());
			System.out.println("Client_Port: " + s.getLocalPort());
			System.out.println("Client_IP: " + s.getLocalAddress());
			System.out.println("Server_IP: " + s.getInetAddress());
			s.close();
	}
}
