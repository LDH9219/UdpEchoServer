/* 컴파일: javac UDPMyEcho.java*/
/* 사용법: java UDPMyEcho localhost 3000*/

import java.net.*;
import java.io.*;

public class UDPMyEcho {
	final static int MAXBUFFER = 512;
	public static void main(String [] args){
		if(args.length != 2){
			System.out.println("사용법: java UDPMyEcho host-ip port");
			System.exit(0);
		}
		byte buffer[] = new byte[MAXBUFFER];
		int bufferLength = 0;
		int port = Integer.parseInt(args[1]);
		
		try{
			InetAddress inetaddr = InetAddress.getByName(args[0]);
			DatagramSocket Socket = new DatagramSocket();	
			DatagramPacket send_packet;					
			DatagramPacket recv_packet;					
			
			while(true){
				
				System.out.print("Input Data : ");
				
				bufferLength = System.in.read(buffer);
				
				send_packet = new DatagramPacket(buffer, buffer.length, inetaddr, port);
				Socket.send(send_packet);
				
				recv_packet = new DatagramPacket(buffer, buffer.length);
				Socket.receive(recv_packet);
				
				
				/*String result = new String(buffer, 0, bufferLength - 2);*/
				String result = new String(buffer, 0, bufferLength);
				System.out.println("Echo Data : " + result);
				
				if(result.indexOf("BYE") >= 0) 
				break;
			}
			Socket.close();
		}catch(UnknownHostException ex){	
			System.out.println("Error in the host address ");
		}catch(IOException e){
			System.out.println(e);
		}
	}
}