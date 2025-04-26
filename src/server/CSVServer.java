package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CSVServer {
    private static final int port = 1690;
    private static final String CSV_FILE = "ps2023_orario.csv";

    public static void main(String[] args) {
        try {
            CSVData csvData = new CSVData(CSV_FILE);
            System.out.println("CSV data loaded successfully.");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server available at port: " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, csvData);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Error on the server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
