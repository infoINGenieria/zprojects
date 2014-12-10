#!/bin/bash 
echo "Copiando .jar"
cp ../src/dist/ZilleProjects.jar .
echo "Actualizando a la versión $1"
echo $1 > version.txt
echo "Subiendo archivo jar"
scp ZilleProjects.jar matuu@matiasvarela.com.ar:/home/matuu/matiasvarela.com.ar/app/static/shared/zille/
echo "Subiendo version.txt"
scp version.txt matuu@matiasvarela.com.ar:/home/matuu/matiasvarela.com.ar/app/static/shared/zille/
echo "Borrando archivos temporales..."
rm ZilleProjects.jar
rm version.txt
echo "Hecho."
