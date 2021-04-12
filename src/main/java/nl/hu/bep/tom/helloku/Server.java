package nl.hu.bep.tom.helloku;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

public class Server {
    private int port;
    private SynchronizedString currentString = new SynchronizedString();

    public Server(int port){
        this.port = port;
    }

    public void start() throws IOException {
        System.out.println("Starting on port " + port);

        Timer timer = new Timer();
        timer.schedule(new ClearTask(currentString), 30 * 1000);

        ServerSocket serverSocket = new ServerSocket(this.port);

        while(true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Received connection");

            Runnable echoHandler = new EchoHandler(clientSocket, this.currentString);
            Thread clientThread = new Thread(echoHandler);
            System.out.println("Starting Handler");
            clientThread.start();
        }
    }
}
