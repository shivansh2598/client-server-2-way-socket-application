import java.net.*;
import java.util.ArrayList;
import java.io.*;

class DatagramServerHandler implements Runnable{
	DatagramSocket ds;
	String msg;
	int count=0;
	DatagramPacket dp;
	
	
	DatagramServerHandler(DatagramSocket ds)
	{
		this.ds=ds;
		msg="";
	}
	

	@Override
	public void run(){
			while(true){
			byte buff[]=new byte[100];
			dp = new DatagramPacket(buff, buff.length);
			try
			{
				ds.receive(dp);
				DatagramServer.addClient(dp.getPort());
				System.out.println(new String(dp.getData()));
			}
		
			catch(Exception e)
			{
	//			System.out.println(e);
				ds.close();
				break;
			}
			
		
		}
	}

	
}

public class DatagramServer {
	
	static ArrayList<Integer> clientPort=new ArrayList<>();
	
	public static synchronized void addClient(int port)
	{
		clientPort.add(port);
	}

	public static void main(String[] args) throws IOException {
		
		System.out.println(" Server Started ");
		DatagramSocket ds = new DatagramSocket(9001);
		DatagramServerHandler dsh = new DatagramServerHandler(ds);
		new Thread(dsh).start();
		
		while(true){
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String msg=br.readLine();
			byte reply[] = msg.getBytes();
			
			for( Integer clientP : clientPort){
				DatagramPacket dp1 = new DatagramPacket(reply, reply.length,InetAddress.getByName("localhost"),clientP);
				ds.send(dp1);
			}
		}
		
	}

}
