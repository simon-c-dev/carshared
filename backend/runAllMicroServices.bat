@echo off
setlocal enabledelayedexpansion

:: List of microservices directories
set microservices=eureka-server authentication-service route-service gateway

:: Create or overwrite the PID file
set pid_file=service_pids.txt
del %pid_file% 2>nul
type nul > %pid_file%

:: Iterate over each microservice directory and run it
for %%m in (%microservices%) do (
    echo Starting %%m...
    cd /d %%m
    start "Starting %%m" mvn spring-boot:run
    :: Small delay to ensure the process starts
    timeout /t 5 /nobreak >nul
    for /f "tokens=*" %%i in ('wmic process where "commandline like '%%spring-boot:run%%' and not (commandline like '%%wmic%%')" get ProcessId ^| findstr /r /v "^$"') do (
        set pid=%%i
        echo PID of %%m is !pid!
        echo !pid! >> ..\%pid_file%
    )
    cd ..
)
echo All microservices started.