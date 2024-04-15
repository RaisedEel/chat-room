package org.raisedeel.server;

import java.io.BufferedReader;

public class ServerReceiver implements Runnable {

  private final BufferedReader socketReader;

  public ServerReceiver(BufferedReader socketReader) {
    this.socketReader = socketReader;
  }

  @Override
  public void run() {
    try {
      String messageReceived;
      while (!(messageReceived = socketReader.readLine()).equals("quit")) {
        System.out.println("Guest: " + messageReceived);
      }

      System.out.println("\nGuest has disconnected.");
      System.out.println("Please press ENTER to exit the program.");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
