
$sfile=FileRead("putty.log",1000);

Do
FileOpen("putty.log",0);
$sfile=FileRead("putty.log");

Until StringInStr($sfile,"Stopped.") or StringInStr($sfile,"not running")

MsgBox(0,"vse stopped","");

