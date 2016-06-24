package NetworkingServer;

import java.io.*; 
import java.net.*; 
  
public class UDPServer { 
	
  public static void main(String args[]) throws Exception { 
	  DatagramSocket serverSocket = new DatagramSocket(1046); //Set up a port
	  System.out.println("Server Listening");
	  
      byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024]; 
  
   	  //This loop will loop at all times, but is blocked by accept().
      //Only when a client connects the loop will execute further. 
      //When the client is gone again the loop restarts again and stops at accept().
      //This is why while(true) is used. Only one client can connect at the same time though.
      //This can be fixed with threading.
      while(true) { 
  
          DatagramPacket receivePacket = 
             new DatagramPacket(receiveData, receiveData.length); 
          
           serverSocket.receive(receivePacket); 
           System.out.println("Server accepted a client");
           
           String sentence = new String(receivePacket.getData()); //Get the data send by the client
           
           //InetAdress can consist of an ip and eventually a hostname
           //This method will get the ip-adres off the sender (client)
           InetAddress IPAddress = receivePacket.getAddress(); 
           
           //Get the port of the client's process
           int port = receivePacket.getPort(); 
   
           //Make the message of the client uppercase
           String capitalizedSentence = sentence.toUpperCase(); 
           //Transform the message to data (bytes)
           sendData = capitalizedSentence.getBytes(); 
   
           DatagramPacket sendPacket = 
              new DatagramPacket(sendData, sendData.length, IPAddress, port); 
   
           serverSocket.send(sendPacket); 
        } 
    } 
}  