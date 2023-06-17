package com.projectTask.multichatapp.Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.swing.JTextArea;

import java.net.Socket;
import java.net.UnknownHostException;

import com.projectTask.multichatapp.utils.configReader;

public class Client {
	
	Socket socket;
	OutputStream out;
	InputStream in; 
	ClientWorker worker;
	JTextArea textArea;
	//constructor
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(configReader.getValue("PORTNO"));
		socket = new Socket(configReader.getValue("SERVER_IP"),PORT);
//		System.out.println("Client comes.");
		out = socket.getOutputStream();
		in = socket.getInputStream() ;
		this.textArea = textArea ;
		readMessages(); 
//		System.out.println("ENter the Message send to the server");
//		//@SuppressWarnings("resource")
//		Scanner sc = new Scanner(System.in);
//		String message = sc.nextLine();
//		OutputStream out = socket.getOutputStream();// Write Bytes on network
//		out.write(message.getBytes());
//		System.out.println("Message send to the server");
//		sc.close();
//		out.close();
//		socket.close();
	
	}
	public void sendMessage(String message) throws IOException {
		message = message + "\n";
		out.write(message.getBytes());
	}
	public void readMessages() {
		worker = new ClientWorker(in,textArea); //calling a read thread
		worker.start();
	}

//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//		@SuppressWarnings("unused")
//		Client client = new Client(); 
//
//	}

}
