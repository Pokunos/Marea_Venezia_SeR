# Marea_Venezia_SeR
# Client-Server Application with CSV using Java Sockets

<!-- Italian Version -->
<div align="left">
<h2>Versione Italiana</h2>

Questa applicazione permette la consultazione remota dei dati sulle maree a Venezia attraverso un'architettura client-server usando i Socket in Java.

## Requisiti
- File CSV con i dati sulle maree (posizionato in `data/pp2023_orario.csv`)

## Commandi (Commands)
-GET_ROW n - Shows row n (0-based index)

-GET_ALL - Shows all tide data

-SEARCH term - Searches for the specified term

-exit - Closes the connection

## Come compilare ed eseguire
```bash
# Compilazione
javac -d bin src/server/*.java src/client/*.java src/shared/*.java

# Avvio server
java -cp bin server.CSVServer

# Avvio client (in terminale separato)
java -cp bin client.CSVClient
```

---

# Tide_Venice_SeR
# Client-Server Application with CSV using Java Sockets
<!-- English Version -->
<div align="left">
<h2>English version</h2>

This application allows remote consultation of tide level data in Venice through a client-server architecture using Java Sockets.

## Requirements
- CSV file with tide data (placed in `data/pp2023_orario.csv`)

## How to Compile
Compile the project:
```bash
javac -d bin src/server/*.java src/client/*.java src/shared/*.java
# Compilation
javac -d bin src/server/*.java src/client/*.java src/shared/*.java

# Start server
java -cp bin server.CSVServer

# Start client (in separate terminal)
java -cp bin client.CSVClient
