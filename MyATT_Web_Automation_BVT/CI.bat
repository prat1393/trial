cd C:\OSD_Test_Automation\MyATT_Web_Automation_BVT
set ProjectPath=C:\OSD_Test_Automation\MyATT_Web_Automation_BVT
start Sel-Grid.vbs
ping 1.1.1.1 -n 1 -w 50000 > null
echo %ProjectPath%
set classpath=%ProjectPath%\bin;%ProjectPath%\IO\Jars\*
echo %classpath%
java org.testng.TestNG %ProjectPath%\testng.xml
echo 'Selenium Test cases executed successfully'
taskkill /F /FI "WindowTitle eq C:\Windows\System32\cmd.exe - java  -jar C:\OSD_Test_Automation*" /T
taskkill /F /FI "WindowTitle eq C:\Windows\System32\cmd.exe - java  -Dwebdriver*" /T
