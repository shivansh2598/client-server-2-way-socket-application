
import java.net.*;
import java.io.*;

class DatagramClientHandler implements Runnable
{
	DatagramSocket ds;
	String msg;
	int count=0;
	
	DatagramClientHandler(DatagramSocket ds)
	{
		this.ds=ds;
		msg="";
	}

	@Override
	public void run(){
	
			while(true){
			byte buff[]=new byte[100];	
			DatagramPacket dp1 = new DatagramPacket(buff, buff.length);
			
			try
			{
				ds.receive(dp1);
				System.out.println(new String(dp1.getData()));
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
	

public class DatagramClient {

	public static void main(String[] args) throws IOException {
	
		DatagramSocket ds = new DatagramSocket();
		int port = 9001;
		InetAddress ip = InetAddress.getByName("localhost");
		DatagramClientHandler dch = new DatagramClientHandler(ds);
		new Thread(dch).start();
		
		while(true)
		{
		
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			String msg=br.readLine();
			int count=0;
			if (msg.equals("bye"))
				count=1;
			byte buff[]=msg.getBytes();
		
			DatagramPacket dp = new DatagramPacket( buff, buff.length, ip, port );
			ds.send(dp);
			
		
			if(count==1)
				break;
		
		}
		
		System.out.println("Client disconnected");
		ds.close();
	}

}
