

global $radio1,$radio2,$radio3,$radio4,$msg,$state


Run("C:\Users\ps439r\Downloads\putty.exe");
sleep(2000)

WinActivate("PuTTY Configuration")
Global $Log
$Log = 1 + $Log
send("{TAB 4}") ;presses tab 4 times to get to saved session
$downMotion = $Log + 1
send("+{DOWN "&$downMotion&"}") ;goes down from default to the first saved session
send("{ENTER}") ;launches session




WinActivate("130.9.203.224 - PuTTY") 
   sleep(4000);
   
   Send("sr");

send("{ENTER}");

Global $irsa = 0

$irsa = InputBox("RSA", "please enter rsa", "", "", _
             - 1, -1, 0, 0)	
sleep(2000);

WinActivate("130.9.203.224 -PuTTY")
Global $hfo

;$hfo=FileOpen("putty.log",2);
;FileWrite($hfo,"");
;FileClose($hfo);
Global $sout
Sleep(2000);

send($irsa);

send("{ENTER}");
Sleep(8000);

Global $sfile
FileOpen("putty.log",0);

$sfile=FileRead("putty.log",1000);
   
While StringInStr($sfile,"Access Denied") 
   $hfo=FileOpen("putty.log",2);
FileWrite($hfo,"");
FileClose($hfo);

$irsa = InputBox("RSA", "please enter rsa", "", "", _
             - 1, -1, 0, 0)	
sleep(2000);
WinActivate("130.9.203.224 - PuTTY")
Global $hfo

;$hfo=FileOpen("putty.log",2);
;FileWrite($hfo,"");
;FileClose($hfo);
Global $sout
Sleep(2000);


send($irsa);

send("{ENTER}");
Sleep(4000);

$sfile="";
$sfile=FileRead("putty.log",1000);

WEnd

If StringInStr($sfile,"Last login") Then
   
   GUICreate("what would you like to do", 300, 200) 
GUISetState(@SW_SHOW)
 $radio1=GUICtrlCreateRadio("vse down", 10, 10, 120, 20);
$radio2= GUICtrlCreateRadio("vse up", 10, 40, 120, 20);
$radio3= GUICtrlCreateRadio("clean bounce", 10, 80, 120, 20);
$radio4=GUICtrlCreateRadio("remove services", 10, 120, 120, 20);
GUICtrlCreateButton("OK",10, 160, 120, 20);
Sleep(4000);

GUISetState()

    ; In this message loop we use variables to keep track of changes to the radios, another
    ; way would be to use GUICtrlRead() at the end to read in the state of each control
    While 1
        $msg = GUIGetMsg()
        Select
            Case $msg = $radio1
                MsgBox(0, "", "Dialog was closed")
                Exit
            Case $msg = $radio2
                MsgBox(0, "", "Dialog minimized", 2)
				Exit
            Case $msg = $radio3
                MsgBox(0, "", "Dialog restored", 2)
				Exit

            Case $msg = $radio4
                MsgBox(0, "Default button clicked", "Radio " & $radioval1)
				Exit


        EndSelect
    WEnd


   
   send("sudo su - t1eam4c13");
send("{ENTER}");
Sleep(1000);
send("cd CA/DevTest/bin");
send("{ENTER}");
send("./VirtualServiceEnvironmentService stop");
send("{ENTER}");
Sleep(8000);
send("./BrokerService stop");
sleep(8000);
send("./PortalService stop");
sleep(8000);
send("./RegistryService stop");
send("{ENTER}");
Sleep(8000);
send("./EnterpriseDashboardService stop");
sleep(8000);



   EndIf
   





;Run("H:\putty.log");





;send("cd CA\D

;Global $sclip
;Send("^a");
;$sclip=ClipGet();
MsgBox(0, "Text read was:", $sfile);

