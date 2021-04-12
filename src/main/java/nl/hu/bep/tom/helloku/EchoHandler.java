package nl.hu.bep.tom.helloku;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoHandler implements Runnable {

    private SynchronizedString string;
    private Socket socket;

    public EchoHandler(Socket socket, SynchronizedString string) {
        this.string = string;
        this.socket = socket;
    }

    private void handleCommand(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println("Received " + command);

            if (command.equals("quit")) {
                break;
            } else if (command.equals("print")) {
                System.out.println(this.string);
            } else {
                this.string.appendLine(command);
            }
        }
    }

    private void handleHttp(Scanner scanner) throws IOException {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //TODO, iets met de rest
            if(line.length() == 0){
                break;
            }
        }

        String result = this.string.getValue();

        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.print("HTTP/1.1 200 OK\r\n");
        writer.print("Content-Type: text/plain\r\n");
        writer.print("Content-Length: " + result.length() +"\r\n");
        writer.print("\r\n");
        writer.print(result);
        writer.print("\r\n");

        writer.close();
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());

            if (!scanner.hasNextLine()) {
                socket.close();
                return;
            }

            String firstLine = scanner.nextLine();

            if (firstLine.startsWith("GET")) {
                System.out.println("Handling http");
                handleHttp(scanner);
            } else {
                System.out.println("Handling raw");
                handleCommand(scanner);
            }

            System.out.println("Done with this handler");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
