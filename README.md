# Marea_Venezia_SeR
# Client-Server Applicazione con CSV, utilizzando Socket in Java

Questa applicazione permette la consultazione remota dei dati di un file CSV attraverso una architettura 'client-server' usando i Socket in Java.

## Come compilare 
Compila il progetto:
```bash
javac -d bin src/server/*.java src/client/*.java src/shared/*.java


# CSV Client-Server Application

A client-server application for remote consultation of CSV files. This code is used to read a csv file (already inside the folder) where it's written the levels of tide in Venice.

## Requirements
- CSV file to be consulted (placed in `data/pp2023_orario.csv`)

## How to Run

1. Compile the project:
```bash
javac -d bin src/server/*.java src/client/*.java src/shared/*.java
