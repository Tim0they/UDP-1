// package main;
// import java.net.DatagramPacket;
// import java.net.DatagramSocket;
// import java.net.InetAddress;
// import client.Client;
// import server.Server;
// import data.Connection;
// import data.Packet;
// import data.PacketHandler;
// import java.net.SocketException;
// import java.net.UnknownHostException;
// import java.lang.String;

// public class testmainClient
// {
//    public static void main(String[] args)
//    {
//       InetAddress ia;
//       try{
//          ia = InetAddress.getLocalHost();
//          Client client = new Client(ia.toString(), 1900);
//          int i=8;
//          byte[] bi=(i+"").getBytes();
//          client.send(bi);
//          System.out.println(client.receive());
      
      
//       }catch(UnknownHostException e){
// 			e.printStackTrace();
//       }
//    }
// }