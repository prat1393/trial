@echo off

set LISA_HOME=%1

if "%LISA_HOME%" == "" set LISA_HOME=%~dp0


if exist "%LISA_HOME%\configure.bat" goto continue

echo This script must be given the LISA_HOME as a parameter, or be run from LISA_HOME.
echo Go to the base directory where LISA was started, and type "configure.bat %~dp0"
goto end


:continue

echo LISA_HOME is %LISA_HOME%



echo Configure complete.
goto end


:nolisahome


:end