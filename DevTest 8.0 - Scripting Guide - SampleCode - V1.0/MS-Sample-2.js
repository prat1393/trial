// Copyright © 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//
// This script logs the operation of the incoming client request
// and the recorded (source) request to VS_<xxx>.log.
// Then the default matching logic is executed.

import com.itko.lisa.VSE;

VSE.error(testExec, "getTransactions - META", "Incoming request = ("
    + incomingRequest.getOperation()
    + ") = ("
    + sourceRequest.getOperation()
    + ").");

return defaultMatcher.matches();
