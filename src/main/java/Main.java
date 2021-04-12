import nl.hu.bep.tom.helloku.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

            String portEnv = System.getenv("PORT");
            if (portEnv == null) {
                portEnv = "9005";
            }

            int port = Integer.parseInt(portEnv);

            Server server = new Server(port);

            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
