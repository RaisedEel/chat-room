package org.raisedeel.client;

import java.io.BufferedReader;

public class ClientReceiver implements Runnable {

  private final BufferedReader socketReader;

  public ClientReceiver(BufferedReader socketReader) {
    this.socketReader = socketReader;
  }

  @Override
  public void run() {
    try {
      String messageReceived;
      while (!(messageReceived = socketReader.readLine()).equals("quit")) {
        System.out.println("Host: " + messageReceived);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
