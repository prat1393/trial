@echo off
setlocal enableextensions enabledelayedexpansion

REM ---------------------------------------------------
REM  Copyright (c) 2010 iTKO Inc. All rights reserved.
REM ---------------------------------------------------

REM Directory location and name of this script
set DIRNAME=%~dp0
set BASENAME=%~nx0

REM ----------------------------------------------
REM  Set KIOSK_HOME
REM  If we're in a LISA_HOME, it is two levels up
REM ----------------------------------------------
set KIOSK_HOME=%DIRNAME%
pushd %KIOSK_HOME%..\..
set LISA_HOME=%CD%\
popd

REM ---------------------------------------
REM  If you don't set JAVA_EXE, we will...
REM ---------------------------------------
if not defined JAVA_EXE set JAVA_EXE=java.exe

REM -----------------------------
REM  Determine which java to use
REM -----------------------------

REM  Option 1: Built in JRE
if exist "%LISA_HOME%jre" (
   set PATH=!LISA_HOME!jre\bin;!PATH!
REM  Option 2: LISA_JAVA_HOME environment variable
) else if defined LISA_JAVA_HOME (
   set PATH=!LISA_JAVA_HOME!\bin;!PATH!
REM  Option 3: JAVA_HOME environment variable
) else if defined JAVA_HOME (
   set PATH=!JAVA_HOME!\bin;!PATH!
)
REM  Option 4: java is already on the PATH
REM  Nothing to do for this option

REM ----------------------------
REM  Exit if we can't find java
REM ----------------------------
for %%a in (%JAVA_EXE%) do set file=%%~$PATH:a

if not defined file (
   echo.
   echo kiosk.bat: !JAVA_EXE! not found
   echo Do one of the following and try again:
   echo - Set the LISA_JAVA_HOME environment variable
   echo - Set the JAVA_HOME environment variable
   echo - Add !JAVA_EXE! to your PATH
   exit /b 1
)

%JAVA_EXE% -jar "%KIOSK_HOME%kiosk.jar" %*
