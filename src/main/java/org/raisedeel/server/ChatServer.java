package org.raisedeel.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

  private final int port;

  public ChatServer(int port) {
    this.port = port;
  }

  public static void main(String[] args) {
    new ChatServer(8080).start();
  }

  public void start() {
    try (ServerSocket server = new ServerSocket(port);
         Socket client = server.accept();
         PrintWriter socketWriter = new PrintWriter(client.getOutputStream(), true);
         BufferedReader socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
         Scanner scanner = new Scanner(System.in);
    ) {
      System.out.println("Connection established with client.");
      System.out.println("Start writing messages below... \n");

      Thread receiver = new Thread(new ServerReceiver(socketReader));
      Thread sender = new Thread(new ServerSender(socketWriter, scanner));

      receiver.start();
      sender.start();

      // Sincronizamos los hilos para cerrar correctamente todos los procesos pendientes
      receiver.join();
      socketWriter.println("quit");
      sender.interrupt();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
