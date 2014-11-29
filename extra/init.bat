set FECHA= %date%
set FECHA=%FECHA:/=%
set FECHA=%FECHA: =%
set FECHA=%FECHA::=%
set FECHA=%FECHA:,=%
cd C:\ZilleProjects
java -jar dist/ZilleProjects.jar > log\log_%FECHA%.log