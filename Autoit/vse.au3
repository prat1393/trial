#include <GUIConstantsEx.au3>

Global $log
Global $path
Global $username
Global $host,$vtier,$plth

Global $radio1,$radio2,$radio3,$radio4,$radio5,$msg1,$state,$date,$git

$log=IniRead("H:\test.ini","username","key3","notfound");
$path=IniRead("H:\test.ini","username","key2","not found");
$username=IniRead("H:\test.ini","username","key1","not found");
$git=IniRead("H:\test.ini","username","key5","not found")


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
   send("sudo su - "&$vtier);
send("{ENTER}");
choice();
   
EndIf


Func choice()
GUICreate("what would you like to do", 300, 200)
GUISetState(@SW_SHOW)
$radio1=GUICtrlCreateRadio("vse down", 10, 10, 120, 20);
$radio2= GUICtrlCreateRadio("vse up", 10, 40, 120, 20);
$radio3= GUICtrlCreateRadio("clean bounce", 10, 80, 120, 20);
$radio4=GUICtrlCreateRadio("remove services", 10, 120, 120, 20);
$radio5=GUICtrlCreateRadio("deploy services", 10, 160, 120, 20);
;GUICtrlCreateButton("OK",10, 160, 120, 20);


GUISetState()


While 1
$msg1 = GUIGetMsg()

operate($msg1);

WEnd

EndFunc

Func operate(ByRef $msg)
Select
Case $msg = $radio1
GUIDelete()
case1()

Exit
Case $msg = $radio2
GUIDelete()
case2();


Exit
Case $msg = $radio3
   GUIDelete()
case3();
Exit


Case $msg = $radio4
   GUIDelete()
case4();
Exit

Case $msg= $radio5
   GUIDelete()
   case5();
   Exit

Case $msg= $GUI_EVENT_CLOSE
   GUIDelete()
Exit


EndSelect


EndFunc


Func case1()
   ;WinActivate("ps439r@plth238:~")
WinActivate($username&"@"&$plth&":~")

send("cd ");
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
send("cd CA/DevTest/bin");

send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
send("./VirtualServiceEnvironmentService stop");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);


Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")


MsgBox(0,"vse stopped","");
choice2();

EndFunc

Func case2()
   WinActivate($username&"@"&$plth&":~")

send("cd ");
send("{ENTER}");
send("cd CA/DevTest/bin");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
send("./VirtualServiceEnvironmentService start");
WinActivate($username&"@"&$plth&":~")
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
choice2();
EndFunc

Func case3()
   WinActivate($username&"@"&$plth&":~")
   send("cd");
send("{ENTER}");
send("cd CA/DevTest/bin");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
send("./VirtualServiceEnvironmentService stop");
WinActivate($username&"@"&$plth&":~")
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
WinActivate($username&"@"&$plth&":~")
send("./BrokerService stop");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
WinActivate($username&"@"&$plth&":~")
send("./PortalService stop");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
WinActivate($username&"@"&$plth&":~")
send("./RegistryService stop");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
WinActivate($username&"@"&$plth&":~")
send("./EnterpriseDashboardService stop");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

MsgBox(0, "enterprise stopped", "vse stopped ")
WinActivate($username&"@"&$plth&":~")
send("cd");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
send("rm -rf lisatmp_8.4.0");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
send("cd CA/DevTest/bin");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
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
WinActivate($username&"@"&$plth&":~")
send("./RegistryService start");
send("{ENTER}");

Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
WinActivate($username&"@"&$plth&":~")
send("./PortalService start");
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
WinActivate($username&"@"&$plth&":~")
send("./BrokerService start");
send("{ENTER}");
Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
WinActivate($username&"@"&$plth&":~")
send("./VirtualServiceEnvironmentService start");
send("{ENTER}");

Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Starting")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
choice2();

EndFunc

Func case4()
   WinActivate($username&"@"&$plth&":~")
   
  $date= StringUpper(InputBox("date to be removed", "please enter date name", "", "", _
- 1, -1, 0, 0))
If $date="GIT" Then
   WinActivate($username&"@"&$plth&":~")
   Send("cd")
send("{ENTER}");
Send("cd "&$git&"stubbing/trunk/IST_Deploy/MYATT_DSS_COMBINE_2014_FINAL/MYATT_DSS_COMBINE_2014_FINAL/VServices/MERGED/JAVA");
send("{ENTER}");
send("ant remove-all");
send("{ENTER}");

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);


Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"BUILD SUCCESSFUL")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
Sleep(1000)
WinActivate($username&"@"&$plth&":~")
Send("cd ..");
send("{ENTER}");
   WinActivate($username&"@"&$plth&":~")
Send("cd WEBSERVICES")
send("{ENTER}");
   WinActivate($username&"@"&$plth&":~")
send("ant remove-all");
send("{ENTER}");

Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"BUILD SUCCESSFUL")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

MsgBox(0,"sevices removed","");
choice2();
   
Else
   WinActivate($username&"@"&$plth&":~")
Send("cd")
send("{ENTER}");
   WinActivate($username&"@"&$plth&":~")
Send("cd stubbing/trunk/IST_Deploy/MYATT_DSS_COMBINE_2014_FINAL/MYATT_DSS_COMBINE_2014_FINAL/VServices/"&$date&"/JAVA");
send("{ENTER}");
   WinActivate($username&"@"&$plth&":~")
send("ant remove-all");
send("{ENTER}");

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);


Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"BUILD SUCCESSFUL")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
Sleep(1000)
WinActivate($username&"@"&$plth&":~")
Send("cd ..");
send("{ENTER}");
Send("cd WEBSERVICES")
send("{ENTER}");

send("ant remove-all");
send("{ENTER}");

Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"BUILD SUCCESSFUL")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

MsgBox(0,"sevices removed","");
choice2();
EndIf

   EndFunc
   
   Func case5()
	  
	     WinActivate($username&"@"&$plth&":~")
   
  $date= StringUpper(InputBox("date to be deployed", "please enter date name", "", "", _
- 1, -1, 0, 0))

If $date="GIT" Then
   WinActivate($username&"@"&$plth&":~")
   Send("cd")
 
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
Send("cd "&$git&"stubbing/trunk/IST_Deploy/MYATT_DSS_COMBINE_2014_FINAL/MYATT_DSS_COMBINE_2014_FINAL/VServices/MERGED/JAVA");
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
send("ant deploy-all start-all");
send("{ENTER}");

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);


Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"BUILD SUCCESSFUL")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
Sleep(1000)
WinActivate($username&"@"&$plth&":~")
Send("cd ..");
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
Send("cd WEBSERVICES")
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
send("ant deploy-all start-all");
send("{ENTER}");

Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"BUILD SUCCESSFUL")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

MsgBox(0,"sevices deployed","");
choice2();
	  
   Else
	  WinActivate($username&"@"&$plth&":~")
Send("cd")
send("{ENTER}");
 WinActivate($username&"@"&$plth&":~")
Send("cd stubbing/trunk/IST_Deploy/MYATT_DSS_COMBINE_2014_FINAL/MYATT_DSS_COMBINE_2014_FINAL/VServices/"&$date&"/JAVA");
send("{ENTER}");
 WinActivate($username&"@"&$plth&":~")
send("ant deploy-all start-all");
send("{ENTER}");

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);


Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"BUILD SUCCESSFUL")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);
Sleep(1000)
WinActivate($username&"@"&$plth&":~")
Send("cd ..");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
Send("cd WEBSERVICES")
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");
WinActivate($username&"@"&$plth&":~")
send("ant deploy-all start-all");
WinActivate($username&"@"&$plth&":~")
send("{ENTER}");

Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"BUILD SUCCESSFUL")

$hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

MsgBox(0,"sevices deployed","");
choice2();
	  EndIf
	  EndFunc

Func choice2()
   
 
   GUICreate("would you like to continue", 300, 200)
GUISetState(@SW_SHOW)
Global $radio11=GUICtrlCreateRadio("yes", 10, 10, 120, 20);
Global $radio21= GUICtrlCreateRadio("no", 10, 40, 120, 20);
   GUISetState();
   While 1
Global $msg2 = GUIGetMsg();
If $msg2=$radio11 Then
   GUIDelete()
		 choice()
		 EndIf
If $msg2=$radio21 Then
   GUIDelete()
   Exit
   EndIf
WEnd
EndFunc







;Run("H:\putty.log");





;send("cd CA\D

;Global $sclip
;Send("^a");
;$sclip=ClipGet();
MsgBox(0, "Text read was:", $sfile);

