import java.io.*;
import java.net.*;

public class Server1 {
	public static void main(String args[]) throws Exception
	{
		System.out.println("Server has started");
		ServerSocket ss=new ServerSocket(9000);
		Socket s=ss.accept();
		System.out.println("Client connected");
		DataInputStream in=new DataInputStream(s.getInputStream());
		String st;
		DataOutputStream out=new DataOutputStream(s.getOutputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		while(true){
		String str=in.readUTF();
		System.out.println("client: "+str);				
		if(str.equals("bye"))
		break;
		System.out.print("->");
		str=br.readLine();
		out.writeUTF(str);
		}

		System.out.println("Client_Port: " + s.getPort());
		System.out.println("Server_Port: " + s.getLocalPort());
		System.out.println("Client_IP: " + s.getLocalAddress());
		System.out.println("Server_IP: " + s.getInetAddress());
		s.close();
	}
}
