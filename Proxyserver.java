package shivansh;
import java.util.*;
import java.net.*;
import java.io.*;


public class Proxyserver {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(10089);
		Socket s = ss.accept();
		
		DataInputStream in = new DataInputStream(s.getInputStream());
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		
		String msgFromClient = in.readUTF();
		
		Socket temp = new Socket("localhost",9999);
		DataOutputStream tempout = new DataOutputStream(temp.getOutputStream());
		tempout.writeUTF(msgFromClient);
		
		DataInputStream tempin = new DataInputStream(temp.getInputStream());
		String msgFromServer=tempin.readUTF();
		
		out.writeUTF(msgFromServer);
		
	}

}
