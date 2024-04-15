package org.raisedeel.client;

import java.io.PrintWriter;
import java.util.Scanner;

public class ClientSender implements Runnable {

  private final PrintWriter socketWriter;
  private final Scanner scanner;

  public ClientSender(PrintWriter socketWriter, Scanner scanner) {
    this.socketWriter = socketWriter;
    this.scanner = scanner;
  }

  @Override
  public void run() {
    String message;
    while (!(message = scanner.nextLine().trim()).equals("quit")) {
      socketWriter.println(message);
    }

    socketWriter.println(message);
    System.out.println("\nYou disconnected from the server.");
  }
}
