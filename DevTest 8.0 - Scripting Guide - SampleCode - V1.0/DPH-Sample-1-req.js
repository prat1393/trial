%beanshell%

// Copyright © 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//

import java.text.SimpleDateFormat;

boolean runningAsVSE;
var message = "";
var args = "";

rawMessage = testExec.getStateValue("flMessage");
if (rawMessage == null) {
    rawMessage = lisa_vse_request.getBodyBytes();
    args = lisa_vse_request.getArguments();
    runningAsVSE = true;
} else {
    runningAsVSE = false;
}

String operation = "";
String theBody = "";
String asString = "";
long id;

if(runningAsVSE) {
    import com.itko.util.ParameterList;

    operation = lisa_vse_request.getOperation();
    isBinary = lisa_vse_request.isBinary();

    byte[] b;
    if(isBinary) {
        b = lisa_vse_request.getBodyBytes();
    } else {
        theBody = lisa_vse_request.getBodyText();
    }

    asString = lisa_vse_request.toString();
    id = lisa_vse_request.getId();

    ParameterList args = lisa_vse_request.getArguments();
    ParameterList attributes = lisa_vse_request.getAttributes();
    ParameterList metadata = lisa_vse_request.getMetaData();
}
else {
    flMessage = testExec.getStateString("flMessage", "");
    theBody = flMessage;
    asString = flMessage;
    operation = "myOperation";
    id = 0001;

}

operation = operation.replaceAll("/","_");
SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
Date currentTimestamp = new Date();
String myDate = timestampFormat.format(currentTimestamp);
//return myDate;

proj_root = testExec.getStateValue("LISA_PROJ_ROOT");
fileToSave = proj_root + "/data/" + operation + "_" + id + "_" + myDate;
com.itko.lisa.vse.SharedModelMap.put("transactionName", "currentOperation", fileToSave);

File file = new File(fileToSave + "-req.txt");
BufferedWriter output = new BufferedWriter(new FileWriter(file));
output.write("\n=== The Operation ==\n");
output.write(operation);
output.write("\n=== the ID ==\n");
output.write(Long.toString(id));
output.write("\n=== the body ==\n");
output.write(theBody);
output.write("\n=== the body parsed as a string ==\n");
output.write(asString);
output.write("\n=====\n");
output.close();


