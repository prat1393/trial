
Global $msg,$gfh,$hvi

Func aisa()
$hvi=GUICreate("what would you like to do", 300, 200)
GUISetState(@SW_SHOW)
Global $radio1=GUICtrlCreateRadio("vse down", 10, 10, 120, 20);
Global $radio2= GUICtrlCreateRadio("vse up", 10, 40, 120, 20);
Global $radio3= GUICtrlCreateRadio("clean bounce", 10, 80, 120, 20);
Global $radio4=GUICtrlCreateRadio("remove services", 10, 120, 120, 20);
;GUICtrlCreateButton("OK",10, 160, 120, 20);


GUISetState();

While 1
$msg = GUIGetMsg();
gui($msg);
WEnd
EndFunc

aisa()

Func gui(ByRef $agf);
   Select
   Case $agf = $radio1
	  MsgBox(0,"radio1","");
	   GUIDelete()
	  cont()
	  
		 
	  Exit
   Case $msg = $radio2
	  MsgBox(0,"radio2","");
	  Exit
	  
   EndSelect
EndFunc

Func cont()
   MsgBox(0,"yahoo","");
   GUICreate("would you like to continue", 300, 200)
GUISetState(@SW_SHOW)
Global $radio11=GUICtrlCreateRadio("yes", 10, 10, 120, 20);
Global $radio21= GUICtrlCreateRadio("no", 10, 40, 120, 20);
   GUISetState();
   While 1
Global $msg1 = GUIGetMsg();
If $msg1=$radio11 Then
   GUIDelete()
		 aisa()
		  
		 EndIf
If $msg1=$radio21 Then
    GUIDelete()
   Exit
   EndIf
WEnd
EndFunc
