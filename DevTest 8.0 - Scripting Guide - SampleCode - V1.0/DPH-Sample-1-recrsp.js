%beanshell%

// Copyright © 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//

import com.itko.util.ParameterList;
String theBody = lisa_vse_response.getBodyText();
String thinkTime = lisa_vse_response.getThinkTimeSpec();
String asString = lisa_vse_response.toString();
long id = lisa_vse_response.getId();
ParameterList metadata = lisa_vse_response.getMetaData();

String fileToSave = com.itko.lisa.vse.SharedModelMap.get("transactionName", "currentOperation");


File file = new File(fileToSave + "-rsp.txt");
BufferedWriter output = new BufferedWriter(new FileWriter(file));

output.write("\n=== the ID ==\n");
output.write(Long.toString(id));
output.write("\n=== the body ==\n");
output.write(theBody);
output.write("\n=== the body parsed as a string ==\n");
output.write(asString);
output.write("\n=== The think time ==\n");
output.write(thinkTime);
output.write("\n=====\n");
output.close();
