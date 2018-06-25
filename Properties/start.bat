@echo off
setlocal enableextensions enabledelayedexpansion

rem Skip subroutines
goto :main

:sleep
set /a TIMEOUT=%1*1000
ping -n 1 -w %TIMEOUT% 1.1.1.1 > NUL 2>&1
exit /b 0

rem Helper to give usage.
:usage
   echo Usage: %PROGNAME% [options]
   echo --agentdir=directory   Override default CAI agent directory
   echo --noagent              Start with no CAI agents
   echo esb web inv loan       Start one or Multiple components
   echo stopcars               Stops Cars components
   exit /b 1

rem Helper to complain.
:warn
   echo %PROGNAME%: %*
   exit /b 1

:main
call :sleep 1
rem Supported specification versions of Java for the demo server
rem Additional versions may be added with space as a separator character
set VALID_JAVA_SPECIFICATION_VERSIONS=1.8

rem Don't trust these settings from outside
set _JAVA_OPTS=
set JAVA_TOOL_OPTIONS=

rem Directory location and name of this script
set DIRNAME=%~dp0
set PROGNAME=%~nx0

set USE_HEAP_SS=true
set USE_LISA_AGENT=true
rem Start or stop ALL Cars components
set CARS_ALL=true

rem Stop Cars components if set to true
set STOP_CARS=false

:args
   if [%1] == [] goto :endargs
   if [%1] == [--agentdir] (
      if [%2] == [] (
         call :usage
         goto :eof
      )
      set LISA_AGENT_HOME=%~2
      shift
   ) else if [%1] == [--noheapss] (
      set USE_HEAP_SS=false
   ) else if [%1] == [--noagent] (
      set USE_LISA_AGENT=false
   ) else if [%1] == [web] (
      set WEB=true
	  set CARS_ALL=false
   ) else if [%1] == [esb] (
      set ESB=true
	  set CARS_ALL=false
   ) else if [%1] == [inv] (
      set INV=true
	  set CARS_ALL=false
   ) else if [%1] == [stopcars] (
      set STOP_CARS=true
   ) else if [%1] == [loan] (
      set LOAN=true
	  set CARS_ALL=false

   ) else (
      call :usage
      goto :eof
   )
   shift
   goto :args
:endargs

rem Figure out CARS_HOME
if exist "%DIRNAME%..\carsdemo\cars-esb.properties" (
   rem Installed Cars
   pushd !DIRNAME!
   set CARS_HOME=!CD!
   popd
) else (
   call :warn Could not determine CARS_HOME. Please run from the carsdemo folder or set the CARS_HOME environment variable
   exit /b 1
)
   cd %CARS_HOME%

rem Figure out LISA_HOME
if exist "%DIRNAME%..\..\bin\lisaenv.bat" (
   rem Installed LISA
   pushd !DIRNAME!..\..
   set LISA_HOME=!CD!
   popd
) else if exist "%DIRNAME%..\..\..\lisa\dist\bin\lisaenv.bat" (
   rem Dev environment
   pushd !DIRNAME!..\..\..\lisa\dist
   set LISA_HOME=!CD!
   popd
) else (
   call :warn Could not determine LISA_HOME
)

rem Set the name of the command to run java
if not defined JAVA set JAVA=java.exe

rem Determine which java to use

REM  Option 1: Built in JRE
if defined LISA_HOME if exist "%LISA_HOME%\jre1.8" (
   set PATH=!LISA_HOME!\jre1.8\bin;!PATH!
   goto :endjava
)
REM  Option 2: LISA_JAVA_HOME environment variable
if defined LISA_JAVA_HOME (
   set PATH=!LISA_JAVA_HOME!\bin;!PATH!
REM  Option 3: JAVA_HOME environment variable
) else if defined JAVA_HOME (
   set PATH=!JAVA_HOME!\bin;!PATH!
)
REM  Option 4: java is already on the PATH
REM  Nothing to do for this option
:endjava

rem Exit if we can't find java
for %%a in (%JAVA%) do set file=%%~$PATH:a
if not defined file (
   call :warn %JAVA% not found
   call :warn Do one of the following and try again:
   call :warn - Set the LISA_JAVA_HOME environment variable
   call :warn - Set the JAVA_HOME environment variable
   call :warn - Add %JAVA% to your PATH
   exit /b 1
)

rem Check the Java version
set valid_java=false
for %%a in (%VALID_JAVA_SPECIFICATION_VERSIONS%) do (
   if [%VALID_JAVA_SPECIFICATION_VERSIONS%] == [%%a] (
      set valid_java=true
      goto :endjavaspec
   )
)
:endjavaspec

if [%valid_java%] == [false] (
   call :warn java specification version %JAVA_SPECIFICATION_VERSION% not supported
   call :warn Supported versions are %VALID_JAVA_SPECIFICATION_VERSIONS%
   exit /b 1
)

rem Check for lisa.permissions file
if not exist "%LISA_HOME%\lisa.permissions" (
   call :warn %LISA_HOME%\lisa.permissions does not exist
)


rem Set up LISA Agent unless we were called with --noagent flag
if [%USE_LISA_AGENT%] == [false] (
   call :warn CAI Agent disabled per --noagent flag
   goto :endagent
)

rem Figure out where LISA Agent is unless already provided with --agentdir flag
if defined LISA_AGENT_HOME goto :endlisaagenthome

rem Looks like an installed LISA
if defined LISA_HOME if exist "%LISA_HOME%\agent" (
   set LISA_AGENT_HOME=!LISA_HOME!\agent
   goto :endlisaagenthome
)

rem Looks like a LISA dev environment
if exist "%DIRNAME%\..\..\..\..\..\lisa-remote\dist\agent" (
   pushd !DIRNAME!\..\..\..\..\..\lisa-remote\dist\agent
   set LISA_AGENT_HOME=!CD!
   popd
   goto :endlisaagenthome
)

:endlisaagenthome

rem If we didn't find a LISA_AGENT_HOME, skip LISA Agent setup
if not defined LISA_AGENT_HOME (
   call :warn Could not find a suitable LISA_AGENT_HOME
   goto :endagent
)

rem Look for the right native library for our platform
rem if [%os_arch%] == [x86] (
   rem set LISA_AGENT_NATIVE_LIB=JavaBinder.dll
rem ) else if [%os_arch%] == [amd64] (
   rem Populate this if we build a 64 bit Windows native agent
   rem set LISA_AGENT_NATIVE_LIB=
rem )

rem If the agent name has multi-byte characters such as Japanese characters, you need to convert this run.bat file from ASCII format to UTF8 format
rem If native library exists, use it

if [%STOP_CARS%] == [false] (

if defined LISA_AGENT_NATIVE_LIB if exist "%LISA_AGENT_HOME%\%LISA_AGENT_NATIVE_LIB%" (
   if [%USE_HEAP_SS%] == [true] (
	   set WEB_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarWeb,heap=true,ex=true,url=tcp://localhost:2009"
       set INV_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarInv,heap=true,ex=true,url=tcp://localhost:2009"
       set ESB_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarEsb,heap=true,ex=true,url=tcp://localhost:2009"
       set LOAN_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarLoan,heap=true,ex=true,url=tcp://localhost:2009"

   ) else (
       set WEB_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarWeb,url=tcp://localhost:2009"
       set INV_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarInv,url=tcp://localhost:2009"
       set ESB_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarEsb,url=tcp://localhost:2009"
       set LOAN_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarLoan,url=tcp://localhost:2009"
	   )
   goto :endagent
)

rem Else use the pure java agent if it exists
if exist "%LISA_AGENT_HOME%\InsightAgent.jar" (
       set WEB_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarWeb,url=tcp://localhost:2009"
       set INV_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarInv,url=tcp://localhost:2009"
       set ESB_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarEsb,url=tcp://localhost:2009"
       set LOAN_AGENT=-javaagent:"!LISA_AGENT_HOME!\!LISA_AGENT_NATIVE_LIB!InsightAgent.jar=name=CarLoan,url=tcp://localhost:2009"
   if [%USE_HEAP_SS%] == [true] (
      call :warn Native Agent not found, starting Pure Java Agent with heap walking and super stacks disabled
   )
   goto :endagent
)

rem Else warn, but still fire up JBoss w/o LISA Agent
call :warn Could not find native or pure LISA Agent in %LISA_AGENT_HOME%
)
:endagent

if [%STOP_CARS%] == [false] (

rem If -server not set in JAVA_OPTS, set it
rem echo %JAVA_OPTS% | find "-server" > nul 2>&1
rem if errorlevel 1 set JAVA_OPTS=-server !JAVA_OPTS!

rem JVM memory allocation pool parameters. Modify as appropriate.
set JAVA_OPTS=%JAVA_OPTS% -Xms128m -Xmx512m

rem With Sun JVMs reduce the RMI GCs to once per hour
set JAVA_OPTS=%JAVA_OPTS% -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000

rem JPDA options. Uncomment and modify as appropriate to enable remote debugging.
rem set JAVA_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=y %JAVA_OPTS%

echo ===============================================================================
echo.
echo   Bootstrap Environment
echo.
echo   CARS_HOME: %CARS_HOME%
echo.
echo   LISA_HOME: %LISA_HOME%
echo.
echo   JAVA: %JAVA%
echo.
echo   JAVA_HOME: %JAVA_HOME%
echo.
if [%USE_LISA_AGENT%] == [false] (
  echo   CAI Agents disabled per --noagent flag
)
if [%USE_LISA_AGENT%] == [true] (
  echo   WEB_AGENT: %WEB_AGENT%
  echo   ESB_AGENT: %ESB_AGENT%
  echo   INV_AGENT: %INV_AGENT%
  echo   LOAN_AGENT: %LOAN_AGENT%
)
echo.
echo ===============================================================================
echo.
)
if [%CARS_ALL%] == [true] (
      set WEB=true
      set ESB=true
      set INV=true
      set LOAN=true
)

if [%STOP_CARS%] == [true] (
   goto :stop_cars
)

echo.
echo Starting ForwardCars

if [%WEB%] == [true] (
   echo Starting ForwardCars - WEB
      start /min "ForwardCars - WEB" java -Dfile.encoding=UTF8 %WEB_AGENT% -jar cars-app-10.1.0.war
      java -cp cars-admin-10.1.0.jar WaitForAppReady http://localhost:3434/cars-app/health 100
)

if [%ESB%] == [true] (
   echo Starting ForwardCars - ESB
      start /min "ForwardCars - ESB" java -Dfile.encoding=UTF8 %ESB_AGENT% -jar cars-esb-10.1.0.war
       java -cp cars-admin-10.1.0.jar WaitForAppReady http://localhost:3700/cars-esb/health 100
)

if [%INV%] == [true] (
   echo Starting ForwardCars - INVENTORY
      start /min "ForwardCars - INVENTORY" java -Dfile.encoding=UTF8 %INV_AGENT% -jar cars-inventory-10.1.0.war
      java -cp cars-admin-10.1.0.jar WaitForAppReady http://localhost:3500/cars-inventory/health 100
)

if [%LOAN%] == [true] (
   echo Starting ForwardCars - LOAN
      start /min "ForwardCars - LOAN" java -Dfile.encoding=UTF8 %LOAN_AGENT% -jar cars-loan-10.1.0.war
      java -cp cars-admin-10.1.0.jar WaitForAppReady http://localhost:3600/cars-loan/health 100

)

echo =========================================================================
echo ForwardCars Ready!
echo http://localhost:3434/cars-app/
echo.
echo Press any key to stop ForwardCars applications
echo =========================================================================

pause

:stop_cars
if [%WEB%] == [true] (
   echo Stopping ForwardCars - WEB
   java -cp cars-admin-10.1.0.jar ShutdownApp http://localhost:3434/cars-app/shutdown
)

if [%ESB%] == [true] (
   echo Stopping ForwardCars - ESB
   java -cp cars-admin-10.1.0.jar ShutdownApp http://localhost:3700/cars-esb/shutdown
)

if [%INV%] == [true] (
   echo Stopping ForwardCars - INVENTORY
   java -cp cars-admin-10.1.0.jar ShutdownApp http://localhost:3500/cars-inventory/shutdown
)

if [%LOAN%] == [true] (
   echo Stopping ForwardCars - LOAN
   java -cp cars-admin-10.1.0.jar ShutdownApp http://localhost:3600/cars-loan/shutdown
)

echo Stopping Complete

