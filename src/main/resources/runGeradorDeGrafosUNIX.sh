#!/bin/bash

# Navegar para o diretório onde o script está localizado
cd "$(dirname "$0")"

# Define o diretório onde os JARs estão localizados
JAR_DIR=".\..\lib"

# Define o diretório onde os arquivos .java estão localizados
SRC_DIR=".\..\java"

# Construir o classpath com todos os JARs no diretório lib e o diretório java
CLASSPATH="$SRC_DIR:$JAR_DIR/*"

# Compila os arquivos Grafos.java e GeradorDeGrafos.java dentro do diretório java
javac -cp "$CLASSPATH" "$SRC_DIR/Grafo.java" "$SRC_DIR/GeradorDeGrafos.java"

# Executa o programa GeradorDeGrafos, assumindo que a classe principal está no diretório java (sem pacote definido)
java -cp "$CLASSPATH" GeradorDeGrafos

