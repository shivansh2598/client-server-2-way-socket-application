import java.io.*;
import java.net.*;

class Detail
{
	String ClientName;
	String msg;

	Detail(String ClientName,String msg)
	{
		this.ClientName=ClientName;
		this.msg=msg;
	}
}

class ClientThread implements Runnable
{
	ObjectInputStream in;
	String st;
	Socket client;

	ClientThread(Socket s) throws Exception
	{
		this.client=s;
		in=new ObjectInputStream(s.getInputStream());
		st="";
	}

	public void run()
	{
		try{
			while(true)
			{
				Detail obj= (Detail) in.readObject();
				System.out.println(obj.ClientName+" : "+ obj.msg);
				System.out.print("->");
			}	
		}
		catch(Exception e)
		{
			System.out.println("Exception in client thread");
		}
	}
}

public class Client10 {
	public static void main(String args[]) throws Exception
	{
			// String ip="localhost";
			// InetAddress i=InetAddress.getByName("localhost");
			// int port=9000;
			// Socket s= new Socket(ip,port,i, 60000);
			Socket s =new Socket("localhost",9001);
			String st;
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			ClientThread client=new ClientThread (s);
			new Thread(client).start();

			while(true)
			{
				System.out.print("->");
				st=br.readLine();
				out.writeUTF(st);
				if(st.equals("bye")){
					try
					{
						out.close();
						s.close();
					}
					catch(Exception e){
					System.out.println("Error in client");
				}
				break;
			}
							
			}

			System.out.println("Server_Port: " + s.getPort());
			System.out.println("Client_Port: " + s.getLocalPort());
			System.out.println("Client_IP: " + s.getLocalAddress());
			System.out.println("Server_IP: " + s.getInetAddress());
			
	}
}
