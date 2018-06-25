

 
 #include <IE.au3>;
Run("C:\Users\ps439r\Downloads\putty.exe");
sleep(2000)

Global $sid;
If WinActivate("PuTTY Configuration") Then  
   
$sid=  InputBox("HOST", "please enter host", "", "", _
             - 1, -1, 0, 0)	
send($sid);
EndIf
controlclick("PuTTY Configuration","","Button1");

WinActivate("130.9.203.224 - PuTTY") 
   sleep(4000);
   
   Send("tb958y");

send("{ENTER}");

Global $irsa = 0
	
$irsa = InputBox("RSA", "please enter rsa", "", "", _
             - 1, -1, 0, 0)	
sleep(2000);
WinActivate("130.9.203.224 - PuTTY")

Global $sout
Sleep(2000);

send($irsa);
Sleep(2000);
send("{ENTER}");
Sleep(4000);

Global $sfile
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");
;Run("H:\putty.log");





;send("cd CA\D

;Global $sclip
;Send("^a");
;$sclip=ClipGet();
MsgBox(0, "Text read was:", $sfile);

