package org.raisedeel.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

  private final String address;
  private final int port;

  public ChatClient(String address, int port) {
    this.address = address;
    this.port = port;
  }

  public static void main(String[] args) {
    new ChatClient("localhost", 8080).start();
  }

  public void start() {
    try (Socket client = new Socket(address, port);
         PrintWriter socketWriter = new PrintWriter(client.getOutputStream(), true);
         BufferedReader socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
         Scanner scanner = new Scanner(System.in);
    ) {
      System.out.println("Connection established with server.");
      System.out.println("Start writing messages below... \n");

      Thread receiver = new Thread(new ClientReceiver(socketReader));
      Thread sender = new Thread(new ClientSender(socketWriter, scanner));

      receiver.start();
      sender.start();

      // Esperamos a que el cliente use "quit" para desconectarse
      sender.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}