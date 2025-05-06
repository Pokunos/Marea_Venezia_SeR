# Marea_Venezia_SeR
# Client-Server Application with CSV using Java Sockets

<!-- Italian Version -->
<h2>Versione Italiana</h2>

Questa applicazione permette la consultazione remota dei dati sulle maree a Venezia attraverso un'architettura client-server usando i Socket in Java,
è obbligatorio prima eseguire il codice del server e infine quello del client, visto che il client si connetterà automaticamente e si potrà avviare la communicazione.
TUTTI I COMANDI SONO CASE SENSITIVE, quindi è consigliato copiare ed incollare i commandi per non avere problemi, è consigliato anche dare una lettura al file csv per utilizzare alla meglio il commando 'SEARCH'.

## Requisiti
- File CSV con i dati sulle maree (posizionato in `data/pp2023_orario.csv`)

## Commandi 
-GET_ROW 'n' - Mostra la riga che si desidera 'n = (0-based index)'

-GET_ALL - Mostra tutti i dati

-SEARCH 'termine' - Ricerca di un dato specifico (es. "01": mostrerà tutto quello che contiene '01'; "01/01": mostrerà tutte le date contenenti la data richiesta).   

-exit - Chiude la connessione.


---

# Tide_Venice_SeR
# Client-Server Application with CSV using Java Sockets
<!-- English Version -->
<h2>English version</h2>

This application allows remote consultation of tide level data in Venice through a client-server architecture using Java Sockets.
The correct use of the client and server is to first run the server side then the client, since the client will connect automatically. 
After the connection you can freely use the commands, THEY ARE ALL CASE SENSITIVE, so its best to copy and paste the commands.
It is also recommended to read the file CSV so that you know its limits and what to 'SEARCH' more efficiently.

## Requirements
- CSV file with tide data (placed in `data/pp2023_orario.csv`)

## Commands
-GET_ROW 'n' - Shows row n (0-based index)

-GET_ALL - Shows all tide data

-SEARCH 'term' - Searches for the specified term (example;  "01" shows every data that contains those two numbers; "01/01" shows every data with this specific date).

-exit - Closes the connection
