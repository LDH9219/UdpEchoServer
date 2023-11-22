// 컴파일: javac ServerUdp.java
// 사용법: java ServerUdp Port-No

import java.net.*;
import java.io.*;


class ServerUDP { 
  public static void main(String args[]) throws Exception 
    { 
  
    if(args.length != 1){
          System.out.println("사용법: java ServerUDP port-번호");
	System.exit(0);
    }

      int serverPort = Integer.parseInt(args[0]);

      DatagramSocket serverSocket = new DatagramSocket(serverPort); 
  
      byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024]; 
  
      while(true) 
        { 
  
          DatagramPacket receivePacket = 
                  new DatagramPacket(receiveData, receiveData.length); 
           serverSocket.receive(receivePacket); 

           String sentence = new String(receivePacket.getData()); 
  
          InetAddress IPAddress = receivePacket.getAddress(); 
  
          int port = receivePacket.getPort(); 
  
                      String capitalizedSentence = sentence.toUpperCase(); 

          sendData = capitalizedSentence.getBytes(); 
  
          DatagramPacket sendPacket = 
             new DatagramPacket(sendData, sendData.length, IPAddress, 
                               port); 
  
          serverSocket.send(sendPacket); 
        } 
    } 
}  

