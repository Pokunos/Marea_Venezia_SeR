package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
            System.err.println("Errore nel gestire il cliente: " + e.getMessage());
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
                System.err.println("Errore nel terminare la connessione: " + e.getMessage());
            }
        }
    }

    private String processRequest(String request) {
        String[] parts = request.split(" ");
        String command = parts[0].toUpperCase();

        try {
            switch (command) {
                case Protocol.GET_ROW:
                    if (parts.length < 2) {
                        return Protocol.ERROR + "Numero della riga mancante.";
                    }
                    int row = Integer.parseInt(parts[1]);
                    String[] rowData = csvData.getRow(row);
                    if (rowData == null) {
                        return Protocol.ERROR + "Numero della riga non esistente.";
                    }
                    return Protocol.OK + " " + String.join(Protocol.SEPARATOR, rowData);

                case Protocol GET_ALL:
                    StringBuilder allData = new StringBuilder(Protocol.OK);
                    for (String[] rowData : csvData.getAllData()) {
                        allData.append(Protocol.LINE_SEPARATOR).append(String.join(Protocol.SEPARATOR, rowData));
                    }    
                    return allData.toString();


                case Protocol.SEARCH:
                    if (parts.length < 2) {
                        return Protocol.ERROR + "Numero della riga mancante.";
                    }
                    String keyword = parts[1];
                    StringBuilder searchResults = new StringBuilder(Protocol.OK);

                    for (String[] rowData : csvData.search(keyword)) {
                        searchResults.append(Protocol.LINE_SEPARATOR).append(String.join(Protocol.SEPARATOR, rowData));
                    }
                    return searchResults.toString();
                default:
                    return Protocol.ERROR + "Riprova.";
            }
        } catch (NumberFormatException e) {
            return Protocol.ERROR + "Formato del numero non valido.";
        } catch (Exception e) {
            return Protocol.ERROR + " " + e.getMessage();
        }

    }
}
