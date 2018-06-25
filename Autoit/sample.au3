#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.8.1
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
 
 #include <IE.au3>;
Run("C:\Users\ps439r\Downloads\putty.exe");

WinActivate("PuTTY Configuration");
send("130.9.203.224");
controlclick("PuTTY Configuration","","Button1");

WinActivate("130.9.203.224 - PuTTY");
send("ps439r");
