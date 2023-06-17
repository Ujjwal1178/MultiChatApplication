	package com.projectTask.multichatapp.Network;
	
	import java.io.IOException;
	
	import java.io.InputStream;
	import java.net.ServerSocket;
	import java.net.Socket;
	import java.util.ArrayList ;
	import com.projectTask.multichatapp.utils.configReader;
	
	public class Server {
		
		ServerSocket serverSocket ;
		ArrayList<ServerWorker> workers = new ArrayList<>(); // Contains all the client sockets
		public Server() throws IOException {
			int PORT = Integer.parseInt(configReader.getValue("PORTNO"));
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server Starts and waiting for the clients to join....");
			handelClientRequest();
			
		}
		//Multiple client handshaking
		public void handelClientRequest() throws IOException {
			while(true) {
				Socket clientSocket = serverSocket.accept() ;// HandShaking
				//Per client - per thread
				ServerWorker serverWorker = new ServerWorker(clientSocket,this); // Creating a new Worker
				workers.add(serverWorker) ;
				serverWorker.start();
				}
		}
		
		
		//constructor
		/* FOr single client */
		/*public Server() throws IOException{
			int PORT = Integer.parseInt(configReader.getValue("PORTNO"));
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server Started and waiting for the client connection.....");
			Socket socket = serverSocket.accept() ;// HandShaking
			System.out.println("Client joins the Server");
			InputStream in =socket.getInputStream(); // reades bytes from the network
			byte arr[] = in.readAllBytes();
			String str = new String(arr); // bytes convert into string
			System.out.println("Message received from the Client\n"+str);
			in.close();
			socket.close();
		
		}*/
	
		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
			@SuppressWarnings("unused")
			Server server = new Server();
	
		}
	
	}
