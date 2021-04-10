//Reekoye Gopal
//
//05/04/2021
//
//
//
//Client.java


package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import data.Connection;
import data.Packet;
import data.PacketHandler;


//Class to handle multi threaded instance of a UDP Client
//


public class Client implements Runnable
{
	private Connection connection;
	private boolean running;

	private DatagramSocket socket;
	private Thread process, send, receive;

	public Client(String addr, int port)
	{
		try{
			socket=new DatagramSocket();
			connection = new Connection(socket, InetAddress.getByName(addr), port, 0);
			this.init();
		}catch (SocketException | UnknownHostException e){
			e.printStackTrace();
		}
	}

	//Client initialisation
	//
	private void init()
	{
		process = new Thread(this, "server_process");
		process.start();
	}

	//send data 

	public void send(final byte[] data)
	{
		send = new Thread("Sending Thread"){
			public void run()
			{
				connection.send(data);
			}
		};
		send.start();
	}

	//Receive data on the given server connection
	//
	public String receive(final PacketHandler handler)
	{
      String s;
		receive = new Thread("receive_thread"){
			public void run()
			{
				while(running)
				{
					byte[] buffer = new byte[1024];
					DatagramPacket dgpacket = new DatagramPacket(buffer, buffer.length);

					try{
						socket.receive(dgpacket);
                  s= new String(dgpacket.getData());
                  
					}catch(IOException e)
					{
						e.printStackTrace();
					}
               

					handler.process(new Packet(dgpacket.getData(), dgpacket.getAddress(), dgpacket.getPort()));

				}
			}  
          return s;

		};

		receive.start();
	}

	//close current connection
	//
	public void close()
	{
		connection.close();
		running = false;
	}

	@Override
	public void run()
	{
		running = true;
	}
}
