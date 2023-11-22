@echo off
REM Altera o diretório atual do prompt de comando para o diretório onde o arquivo batch está localizado
cd /d %~dp0

REM Define o diretório onde os JARs estão localizados, relativo ao diretório do script
set JAR_DIR=.\..\lib

REM Define o diretório onde os arquivos .java estão localizados
set SRC_DIR=.\..\java

REM Constrói o classpath com todos os JARs no diretório especificado e o diretório java
set CLASSPATH=%SRC_DIR%;%JAR_DIR%\*

REM Compila os arquivos Grafos.java e GeradorDeGrafos.java dentro do diretório java
javac -cp "%CLASSPATH%" %SRC_DIR%\Grafo.java %SRC_DIR%\GeradorDeGrafos.java

REM Executa o programa GeradorDeGrafos, assumindo que a classe principal está no diretório java (sem pacote definido)
java -cp "%CLASSPATH%" GeradorDeGrafos

pause
