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
   echo stopall.bat: LISA_HOME=%LISA_HOME%
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
echo Stop All
echo LISA_HOME: %LISA_HOME%
echo DevTest bin: %LHB%
echo DevTest Examples: %EXAMPLES%
echo DevTest Demo Server: %DEVTEST_DEMO_SERVER_HOME%
echo DevTest Registry: %REGISTRY%
echo -------------------------------------------------

for /f "TOKENS=1" %%a in ('wmic process where "name='VirtualServiceEnvironment.exe'" get ProcessId ^2^>NUL ^| findstr [0-9]') do (
  echo.
  echo Stopping VSE - pid %%a
  call <NUL "%LHB%\sendsignal.bat" %%a >NUL 
  call :sleep 3
)

for /f "TOKENS=1" %%a in ('wmic process where "name='Simulator.exe'" get ProcessId ^2^>NUL ^| findstr [0-9]') do (
  echo.
  echo Stopping Simulator - pid %%a
  call <NUL "%LHB%\sendsignal.bat" %%a >NUL 
  call :sleep 3
)

for /f "TOKENS=1" %%a in ('wmic process where "name='CoordinatorServer.exe'" get ProcessId ^2^>NUL ^| findstr [0-9]') do (
  echo.
  echo Stopping Coordinator - pid %%a
  call <NUL "%LHB%\sendsignal.bat" %%a >NUL 
  call :sleep 3
)

for /f "TOKENS=1" %%a in ('wmic process where "name='Portal.exe'" get ProcessId ^2^>NUL ^| findstr [0-9]') do (
  echo.
  echo Stopping Portal - pid %%a
  call <NUL "%LHB%\sendsignal.bat" %%a >NUL 
  call :sleep 3
)

for /f "TOKENS=1" %%a in ('wmic process where "name='Broker.exe'" get ProcessId ^2^>NUL ^| findstr [0-9]') do (
  echo.
  echo Stopping Broker - pid %%a
  call <NUL "%LHB%\sendsignal.bat" %%a >NUL 
  call :sleep 3
)

for /f "TOKENS=1" %%a in ('wmic process where "name='Registry.exe'" get ProcessId ^2^>NUL ^| findstr [0-9]') do (
  echo.
  echo Stopping Registry - pid %%a
  call <NUL "%LHB%\sendsignal.bat" %%a >NUL 
  call :sleep 3
)

for /f "TOKENS=1" %%a in ('wmic process where "name='java.exe' and commandline like '%%program.name=run.bat%%'" get ProcessId ^2^>NUL ^| findstr [0-9]') do (
  echo.
  echo Stopping Demo Server - pid %%a
  call <NUL "%LHB%\sendsignal.bat" %%a >NUL 
  call :sleep 3
)

exit /b 0
