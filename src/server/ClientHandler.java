package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import shared.Protocol;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private CSVData csvData;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket clientSocket, CSVData csvData) {
        this.clientSocket = clientSocket;
        this.csvData = csvData;

    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String response = processRequest(inputLine);
                out.println(response);
            }
        } catch (IOException e) {
            System.err.println("Error with handling the client: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error with ending the connection: " + e.getMessage());
            }
        }
    }

    private String processRequest(String request) {
        String[] parts = request.split(" ");
        String command = parts[0].toUpperCase();

        try {
            switch (command) {
                case "GET_ROW":
                    if (parts.length < 2) {
                        return Protocol.ERROR + "Number of row missing.";
                    }
                    int row = Integer.parseInt(parts[1]);
                    String[] rowData = csvData.getRow(row);
                    if (rowData == null) {
                        return Protocol.ERROR + "Number of row non existent.";
                    }
                    return Protocol.OK + " " + String.join(Protocol.SEPARATOR, rowData);

                case "GET_ALL":
                    StringBuilder allData = new StringBuilder(Protocol.OK);
                    for (String[] allRowData : csvData.getAllData()) {
                        allData.append(Protocol.LINE_SEPARATOR).append(String.join(Protocol.SEPARATOR, allRowData));
                    }
                    return allData.toString();

                case "SEARCH":
                    if (parts.length < 2) {
                        return Protocol.ERROR + "Number of row missing.";
                    }
                    String keyword = parts[1];
                    StringBuilder searchResults = new StringBuilder(Protocol.OK);

                    for (String[] searchRowData : csvData.search(keyword)) {
                        searchResults.append(Protocol.LINE_SEPARATOR)
                                .append(String.join(Protocol.SEPARATOR, searchRowData));
                    }
                    return searchResults.toString();
                default:
                    return Protocol.ERROR + "Try again.";
            }
        } catch (NumberFormatException e) {
            return Protocol.ERROR + "Format of the number not valid, try again.";
        } catch (Exception e) {
            return Protocol.ERROR + " " + e.getMessage();
        }
    }
}
