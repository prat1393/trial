@echo off
setlocal enableextensions enabledelayedexpansion

REM ---------------------------------------------------
REM  Copyright (c) 2010 iTKO Inc. All rights reserved.
REM ---------------------------------------------------

REM  Change the lists of MAR info files to load here
REM  No ".mari" extension
set LOCALHOST_MARINFO_FILES=OnlineBankingExternalCreditCheck-local OnlineBankingJMStest-local OnlineBankingTransactionMonitor-local OnlineBankingWebServices-local

REM  Set LISA_HOME if not defined
if not defined LISA_HOME (
   call "%~dp0..\..\setLISA_HOME.bat"
)

set LHB=%LISA_HOME%\bin
set EXAMPLES=%LISA_HOME%\examples

REM  Exit if we can't find our environment
if not exist "%LISA_HOME%bin\lisaenv.bat" (
   echo loadCVSMonitors.bat: LISA_HOME=%LISA_HOME%
   echo LISA_HOME is not valid. Set LISA_HOME correctly and try again.
   exit /b 1
)

REM  Set default registry name if not defined
if defined LISA_DEMO_REGISTRY (
   set REGISTRY=!LISA_DEMO_REGISTRY!
) else (
   set REGISTRY=Registry
)

REM  Print environment
echo -------------------------------------------------
echo Load CVS Monitors
echo LISA_HOME: %LISA_HOME%
echo LISA bin: %LHB%
echo LISA Examples: %EXAMPLES%
echo LISA Registry: %REGISTRY%
echo -------------------------------------------------

REM  Parse arguments
:argsstart
if [%1] == [] goto argsfinished
   if [%1] == [-u] (
      set USERNAME=%2
      shift
   ) else if [%1] == [-p] (
      set PASSWORD=%2
	  shift
   ) else (
      set COMMAND=%1
   )
   shift
goto argsstart
:argsfinished

REM  See if we have valid arguments
if not defined USERNAME goto usage
if not defined PASSWORD goto usage

REM  Delete monitors if requested
if [%COMMAND%] == [deleteAll] (
   echo.
   echo Deleting all monitors...
   "!LHB!\CVSManager" -u !USERNAME! -p !PASSWORD! -X -m "!REGISTRY!"
   echo.
   echo Listing monitors
   "!LHB!\CVSManager" -u !USERNAME! -p !PASSWORD! -l -m "!REGISTRY!"
   exit /b 0
)

REM  Use monitor list requested on command line
if [%COMMAND%] == [localhost] (
   REM  Load monitors
   for %%a in (%LOCALHOST_MARINFO_FILES%) do (
      echo.
      echo Making mar %%a
      "!LHB!\MakeMar" -c -m "!EXAMPLES!\MARInfos\%%a.mari" -a "%TMP%\%%a.mar"
      echo.
      echo Loading mar %%a
      "!LHB!\CVSManager" -u !USERNAME! -p !PASSWORD! -d "%TMP%\%%a.mar" -m "!REGISTRY!"
      del "%TMP%\%%a.mar"
   )

   REM List monitors
   echo.
   echo Listing monitors
   "!LHB!\CVSManager" -u !USERNAME! -p !PASSWORD! -l -m "!REGISTRY!"

   exit /b 0
)

:usage
echo loadCVSMonitors.bat: Usage:
echo loadCVSMonitors -u username -p password [ localhost ^| deleteAll ]
exit /b 1
