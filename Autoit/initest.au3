Global $log
Global $path
Global $username
Global $host

$log=IniRead("H:\test.ini","username","key3","notfound");
$path=IniRead("H:\test.ini","username","key2","not found");
$username=IniRead("H:\test.ini","username","key1","not found");

MsgBox(0, "Text read was:", $log);
MsgBox(0, "Text read was:", $path);
MsgBox(0, "Text read was:", $username);
