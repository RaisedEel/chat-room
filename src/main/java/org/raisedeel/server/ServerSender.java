package org.raisedeel.server;

import java.io.PrintWriter;
import java.util.Scanner;

public class ServerSender implements Runnable {

  private final PrintWriter socketWriter;
  private final Scanner scanner;

  public ServerSender(PrintWriter socketWriter, Scanner scanner) {
    this.socketWriter = socketWriter;
    this.scanner = scanner;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      String message = scanner.nextLine().trim();
      socketWriter.println(message);
    }
  }
}
