#include <GUIConstantsEx.au3>

Global $log
Global $path
Global $username
Global $host,$vtier,$plth

global $radio1,$radio2,$radio3,$radio4,$msg,$state

$log=IniRead("H:\test.ini","username","key3","notfound");
$path=IniRead("H:\test.ini","username","key2","not found");
$username=IniRead("H:\test.ini","username","key1","not found");


if $log=0 Then
$host="130.9.203.224"
$vtier="t1eam4c10"
$plth="plth238"

ElseIf $log=1 Then
$host="130.9.203.226"
$vtier="t1eam4c13"
$plth="plth237"
EndIf

Run($path);

sleep(2000)

WinActivate("PuTTY Configuration")

;$Log = 1 + $Log
;send("{TAB 4}") ;presses tab 4 times to get to saved session
;$downMotion = $Log + 1
;send("+{DOWN "&$downMotion&"}") ;goes down from default to the first saved session
;send("{ENTER}") ;launches session

send("{DEL 15}")

send($host);

controlclick("PuTTY Configuration","","Button1");

WinActivate($host&" - PuTTY")
sleep(1000);

Send($username);

send("{ENTER}");

Global $irsa = 0

$irsa = InputBox("RSA", "please enter rsa", "", "", _
- 1, -1, 0, 0)
;sleep(2000);

WinActivate($host&" - PuTTY")
Global $hfo

;$hfo=FileOpen("putty.log",2);
;FileWrite($hfo,"");
;FileClose($hfo);
Global $sout
;Sleep(2000);

send($irsa);

send("{ENTER}");
Sleep(5000);

Global $sfile
FileOpen("putty.log",0);

$sfile=FileRead("putty.log",1000);

While StringInStr($sfile,"Access Denied")
$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

$irsa = InputBox("RSA", "please enter rsa", "", "", _
- 1, -1, 0, 0)
;sleep(2000);
WinActivate($host&" - PuTTY")
Global $hfo

;$hfo=FileOpen("putty.log",2);
;FileWrite($hfo,"");
;FileClose($hfo);
Global $sout
;
;Sleep(2000);

send($irsa);

send("{ENTER}");
Sleep(5000);


$sfile=FileRead("putty.log",1000);

WEnd

If StringInStr($sfile,"Last login") Then


GUICreate("what would you like to do", 300, 200)
GUISetState(@SW_SHOW)
$radio1=GUICtrlCreateRadio("vse down", 10, 10, 120, 20);
$radio2= GUICtrlCreateRadio("vse up", 10, 40, 120, 20);
$radio3= GUICtrlCreateRadio("clean bounce", 10, 80, 120, 20);
$radio4=GUICtrlCreateRadio("remove services", 10, 120, 120, 20);
;GUICtrlCreateButton("OK",10, 160, 120, 20);


GUISetState()


While 1
$msg = GUIGetMsg()
Select
Case $msg = $radio1

;WinActivate("ps439r@plth238:~")
WinActivate($username&"@"&$plth&":~")
send("sudo su - "&$vtier);
send("{ENTER}");
Sleep(1000);
send("cd CA/DevTest/bin");
send("{ENTER}");

send("./VirtualServiceEnvironmentService stop");
send("{ENTER}");
$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);


Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")


MsgBox(0,"vse stopped","");

Exit
Case $msg = $radio2

WinActivate($username&"@"&$plth&":~")
send("sudo su - "&$vtier);
send("{ENTER}");
Sleep(1000);
send("cd CA/DevTest/bin");
send("{ENTER}");

send("./VirtualServiceEnvironmentService start");
send("{ENTER}");
$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);


Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

MsgBox(0,"vse started","");


Exit
Case $msg = $radio3
WinActivate($username&"@"&$plth&":~")
send("sudo su - "&$vtier);
send("{ENTER}");
Sleep(1000);
send("cd CA/DevTest/bin");
send("{ENTER}");
send("./VirtualServiceEnvironmentService stop");
send("{ENTER}");
$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);


Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

send("./BrokerService stop");
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
send("./PortalService stop");
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
send("./RegistryService stop");
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

send("./EnterpriseDashboardService stop");
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

MsgBox(0, "enterprise stopped", "Radio ")

send("cd");
send("{ENTER}");
send("rm -rf lisatmp_8.4.0");
send("{ENTER}");
send("cd CA/DevTest/bin");
send("{ENTER}");

send("./EnterpriseDashboardService start");
send("{ENTER}");
$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);


Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

send("./RegistryService start");
send("{ENTER}");

Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
send("./PortalService start");
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

send("./BrokerService start");
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

send("./VirtualServiceEnvironmentService start");
send("{ENTER}");

Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

Exit


Case $msg = $radio4
MsgBox(0, "Default button clicked", "Radio " & $radioval1)
Exit

Case $msg= $GUI_EVENT_CLOSE
Exit


EndSelect
WEnd




EndIf






;Run("H:\putty.log");





;send("cd CA\D

;Global $sclip
;Send("^a");
;$sclip=ClipGet();
MsgBox(0, "Text read was:", $sfile);

