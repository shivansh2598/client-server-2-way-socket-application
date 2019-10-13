package shivansh;
import java.util.*;
import java.net.*;
import java.io.*;

public class Clientproxy {

	public static void main(String[] args) throws Exception {
		Socket s = new Socket("localhost",10089);
		String str = "Hello Server through proxy server";
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeUTF(str);
		
		DataInputStream in = new DataInputStream(s.getInputStream());
		String msg = in.readUTF();
		System.out.println(msg);
		s.close();
	}

}
