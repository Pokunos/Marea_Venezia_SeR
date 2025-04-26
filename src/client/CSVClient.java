package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import shared.Protocol;

public class CSVClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int port = 1690;

    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket(SERVER_ADDRESS, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to CSV server. Enter commands (type 'exit' to quit)");
            while (true) {
                System.out.println("> ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                out.println(input);
                String response = in.readLine();

                if (response.startsWith(Protocol.OK)) {
                    System.out.println("Response:");
                    String[] lines = response.split(Protocol.LINE_SEPARATOR);
                }
            }
        }
    }
}