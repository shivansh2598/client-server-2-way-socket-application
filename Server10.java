import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.Serializable;

class Detail implements Serializable
{
	private static final long serialVersionUID = 1L;
	String ClientName;
	String msg;

	Detail(String ClientName,String msg)
	{
		this.ClientName=ClientName;
		this.msg=msg;
	}
}

class ClientHandler implements Runnable, Serializable
{
	private static final long serialVersionUID = 1L;
	Socket ClientSocket;
	DataInputStream in;
	ObjectOutputStream out;
	BufferedReader br;
	String st="",str="";
	ArrayList<ClientHandler> Clients;
	String ClientName="Anoymous";
	public ClientHandler(Socket ClientSocket,ArrayList<ClientHandler> Clients) throws Exception
	{
		this.ClientSocket=ClientSocket;
		this.Clients=Clients;
		in=new DataInputStream(this.ClientSocket.getInputStream());
		out=new ObjectOutputStream(this.ClientSocket.getOutputStream());
		br=new BufferedReader(new InputStreamReader(System.in));
	}
	//override run function
	public void run()
	{
		try{
			while(true){
				String str=in.readUTF();
				if(str.startsWith("say"))
				{
					int index=str.indexOf(" ");
					str=str.substring(index+1);
					Detail obj=new Detail(ClientName,str);	
					outtoall(obj);
				}
				else if(str.startsWith("bye")){
				// System.out.println("client: "+str);
					st="Byee Client";
					outtoone(st);
					break;
				}
				else if(str.startsWith("name"))
				{
					int index=str.indexOf(" ");
					ClientName=str.substring(index+1);
				}
				else
				{
					st="Hello new client, please enter a username";
					outtoone(st);
					// out.writeUTF(st);
				}		
		}
	}
		catch(Exception e)
		{
			System.out.println(e);
		}

		try
		{
			System.out.println("Client_Port: " + this.ClientSocket.getPort());
			System.out.println("Server_Port: " + this.ClientSocket.getLocalPort());
			System.out.println("Client_IP: " + this.ClientSocket.getLocalAddress());
			System.out.println("Server_IP: " + this.ClientSocket.getInetAddress());
			this.ClientSocket.close();
		}

		catch(Exception e)
		{
			System.out.println(e);
		}

		finally
		{
			try{
				out.close();
				in.close();
			}
			catch(Exception E)
			{
				System.out.println("Error occured");
			}
		}
		
	}

	private void outtoone(String msg) throws Exception{
		Detail obj=new Detail("server",msg);
		out.writeObject(obj);
	}

	private void outtoall(Detail obj) throws Exception {
		for (ClientHandler i : Clients)
		{
			i.out.writeObject(obj);
		}
	}

} 

public class Server10{

	private static ArrayList<ClientHandler> Clients=new ArrayList<>();
	private static ExecutorService pool= Executors.newFixedThreadPool(4);

	public static void main(String args[]) throws Exception
	{
		System.out.println("Server has started");
		ServerSocket ss=new ServerSocket(9001);
		while(true){
			Socket s=ss.accept();
			System.out.println("Client connected");
			ClientHandler clientThread = new ClientHandler(s,Clients);
			Clients.add(clientThread);
			pool.execute(clientThread);

		}

	}
}
