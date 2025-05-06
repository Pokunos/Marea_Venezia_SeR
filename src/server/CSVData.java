package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVData {

    private List<String[]> data;
    private String[] headers;

    public CSVData(String filePath) throws FileNotFoundException, IOException {
        data = new ArrayList<>();
        loadCSV(filePath);
    }

    private void loadCSV(String filePath) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    headers = line.split(",");
                    firstLine = false;
                } else {
                    data.add(line.split(","));
                }
            }
        }
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[] getRow(int rowIndex) {
        int adjustedIndex = rowIndex - 1;
        if (adjustedIndex >= 0 && adjustedIndex < data.size()) {
            return data.get(adjustedIndex);
        }
        return null;
    }

    public List<String[]> search(String keyword) {
        List<String[]> results = new ArrayList<>();

        for (String[] row : data) {
            for (String cell : row) {
                if (cell.contains(keyword)) {
                    results.add(row);
                    break;
                }
            }
        }
        return results;
    }

    public List<String[]> getAllData() {
        return new ArrayList<>(data);
    }
}
