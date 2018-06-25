@echo off
setlocal enableextensions enabledelayedexpansion

REM --------------------------------------------------
REM  Copyright (c) 2014 CA, Inc. All rights reserved.
REM --------------------------------------------------

REM Skip subroutines
goto :main

REM No built in sleep in Windows
REM So instead we ping an IP that won't exist
REM setting the timeout to how long we want to sleep
:sleep
set /a TIMEOUT=%1*1000
ping -n 1 -w %TIMEOUT% 1.1.1.1 > NUL 2>&1
exit /b 0

:main
REM  Set LISA_HOME
call "%~dp0..\..\setLISA_HOME.bat"
set LHB=%LISA_HOME%bin
set EXAMPLES=%LISA_HOME%examples
set DEVTEST_DEMO_SERVER_HOME=%LISA_HOME%DemoServer\lisa-demo-server

REM  Exit if we can't find our environment
if not exist "%LISA_HOME%bin\lisaenv.bat" (
   echo startall.bat: LISA_HOME=%LISA_HOME%
   echo LISA_HOME is not valid. Set LISA_HOME correctly and try again.
   exit /b 1
)

REM  Set default registry name if not defined
if defined DEVTEST_DEMO_REGISTRY (
   set REGISTRY=!DEVTEST_DEMO_REGISTRY!
) else (
   set REGISTRY=Registry
)

REM Print environment
echo -------------------------------------------------
echo Start All
echo LISA_HOME: %LISA_HOME%
echo DevTest bin: %LHB%
echo DevTest Examples: %EXAMPLES%
echo DevTest Demo Server: %DEVTEST_DEMO_SERVER_HOME%
echo DevTest Registry: %REGISTRY%
echo -------------------------------------------------

echo.
echo Starting Demo Server
start "DevTest Demo Server" /min "%DEVTEST_DEMO_SERVER_HOME%\start-windows.bat"
call :sleep 45

echo.
echo Starting Registry
start "DevTest Registry" /min "%LHB%\Registry.exe" -n "%REGISTRY%"
call :sleep 15

echo.
echo Starting Broker
start "DevTest Broker" /min "%LHB%\Broker.exe"
call :sleep 15

echo.
echo Starting Portal
start "DevTest Portal" /min "%LHB%\Portal.exe"
call :sleep 15

echo.
echo Starting Coordinator
start "DevTest Coordinator" /min "%LHB%\CoordinatorServer.exe" -m "%REGISTRY%"
call :sleep 5

echo.
echo Starting Simulator
start "DevTest Simulator" /min "%LHB%\Simulator.exe" -m "%REGISTRY%"
call :sleep 5

echo.
echo Starting VSE
start "DevTest VSE" /min "%LHB%\VirtualServiceEnvironment.exe" -m "%REGISTRY%"
call :sleep 5

echo.
echo Run %EXAMPLES%\Setup\stopall.bat to stop everything that was just started

exit /b 0
