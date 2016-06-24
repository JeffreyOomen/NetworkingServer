package NetworkingServer;

import java.io.*; 
import java.net.*; 

public class TCPServer { 

  public static void main(String argv[]) throws Exception { 
      String clientSentence; //The sentence send by the client
      String capitalizedSentence; //The sentence send by the client, but capitalized by this server

      ServerSocket welcomeSocket = new ServerSocket(8003); //Opens a new socket on the server
      System.out.println("Server Listening");
      
      //This loop will loop at all times, but is blocked by accept().
      //Only when a client connects the loop will execute further. 
      //When the client is gone again the loop restarts again and stops at accept().
      //This is why while(true) is used. Only one client can connect at the same time though.
      //This can be fixed with threading.
      while(true) { 
    	  Socket connectionSocket = welcomeSocket.accept(); 
    	  System.out.println("Server accepted a client");
    	  
    	  //Reads text from a character-input stream, buffering characters so as to provide 
    	  //for the efficient reading of characters, arrays, and lines.
           BufferedReader inFromClient = 
              new BufferedReader(new
              InputStreamReader(connectionSocket.getInputStream())); 

           DataOutputStream  outToClient = 
             new DataOutputStream(connectionSocket.getOutputStream()); 

           clientSentence = inFromClient.readLine(); 

           capitalizedSentence = clientSentence.toUpperCase() + '\n'; 

           outToClient.writeBytes(capitalizedSentence); 
        } 
    } 
} 
