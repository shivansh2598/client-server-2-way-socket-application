package shivansh;
import java.util.*;
import java.net.*;
import java.io.*;


public class Mainserver {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(9999);
		Socket s = ss.accept();
		
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		DataInputStream in = new DataInputStream(s.getInputStream());
		System.out.println(in.readUTF());
		
		String msg = "Hello client via proxy server";
		out.writeUTF(msg);
	}

}
