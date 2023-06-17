package com.projectTask.multichatapp.Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.io.BufferedReader;
// Thread == worker
// Worker need a job to perform
//For job you give runnable
//once job is created via Runnable so write the job logic inside a run function 
//Assign job to a thread
public class ServerWorker extends Thread{
	private Socket clientSocket ;
	private InputStream in ;
	private OutputStream out ;
	private Server server ;
	public ServerWorker(Socket clientSocket, Server server) throws IOException{
		this.server = server; 
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream() ; // Client data read; 
		out = clientSocket.getOutputStream() ;// Client data write
		System.out.println("New CLient Comes");
	}
	@Override
	public void run() {
		//read data from the client  and BroadCast the data to all
		BufferedReader br  = new BufferedReader(new InputStreamReader(in));
		String line ;
		try {
		while(true) {
		
				line = br.readLine();
				System.out.println("Line Read...."+line);
				if(line.equalsIgnoreCase("quit")) {
					break; // client chat end
				}
			//	out.write(line.getBytes()); // data send to client
				//BroadCast to all
				for(ServerWorker serverWorker : server.workers) {
					line = line + "\n";
					serverWorker.out.write(line.getBytes());
				}
			} 
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
		}
		finally{
			try {
			if(br != null) {
				br.close();
			}
			if(in != null){
				in.close();
			}
			if(out != null) {
				out.close();
			}
			if(clientSocket != null) {
				clientSocket.close();
			}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}
}
