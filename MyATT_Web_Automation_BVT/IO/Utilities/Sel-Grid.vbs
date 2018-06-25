Set oShell = WScript.CreateObject ("WScript.Shell")
oShell.run "cmd.exe /K java -jar C:\OSD_Test_Automation\MyATT_Web_Automation_BVT\IO\Utilities\selenium-server-standalone-3.4.0.jar -port 4444 -role hub"
WScript.Sleep 10000

oShell.run "cmd.exe /K java -Dwebdriver.gecko.driver=C:\OSD_Test_Automation\MyATT_Web_Automation_BVT\IO\Utilities\geckodriver.exe -jar C:\OSD_Test_Automation\MyATT_Web_Automation_BVT\IO\Utilities\selenium-server-standalone-3.4.0.jar -role webdriver -hub http://localhost:4444/grid/register -browser browserName=firefox,maxInstances=4 -port 5555"
WScript.Sleep 10000

oShell.run "cmd.exe /K java -Dwebdriver.chrome.driver=C:\OSD_Test_Automation\MyATT_Web_Automation_BVT\IO\Utilities\chromedriver.exe -jar C:\OSD_Test_Automation\MyATT_Web_Automation_BVT\IO\Utilities\selenium-server-standalone-3.4.0.jar -role webdriver -hub http://localhost:4444/grid/register -browser browserName=chrome,platform=WINDOWS,maxInstances=5 -port 5559"
WScript.Sleep 10000

Set oShell = Nothing
