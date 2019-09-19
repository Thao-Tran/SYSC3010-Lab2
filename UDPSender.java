import java.net.*;
import java.util.Scanner;

public class UDPSender {

	public static void main(String[] args) 
   {
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port n" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
			 int n = Integer.parseInt(args[2]);
	         socket = new DatagramSocket() ;
     
	         Scanner in;
	         in = new Scanner (System.in);
	         String message = null;
			 int msgCount = 1;
	         while (msgCount <= n)
	         {
	        		 message = "Message"+Integer.toString(msgCount);
	        		 byte [] data = message.getBytes() ;
	        		 DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
	        		 socket.send( packet ) ;
					 msgCount++;
					 DatagramPacket ackPacket = new DatagramPacket(new byte[data.length+5], data.length+5);
					 socket.receive(ackPacket);
					 System.out.println( ackPacket.getAddress() + " " + ackPacket.getPort() + ":     " +  new String(ackPacket.getData()).trim()) ;
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}

