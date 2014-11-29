set FECHA= %date%
set FECHA=%FECHA:/=%
set FECHA=%FECHA: =%
set FECHA=%FECHA::=%
set FECHA=%FECHA:,=%
set ZILLEBK=zilleprojects_%FECHA%.sql
cd C:\ZilleProjects\
mkdir BK-ZILLEPROJECTS
mysqldump -pInfoIngenieria -u zille zilleprojects > BK-ZILLEPROJECTS\%ZILLEBK%