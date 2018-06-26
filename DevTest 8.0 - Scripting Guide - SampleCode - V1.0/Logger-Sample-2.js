// Copyright © 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//

String p1   = "one";
int p2      = 2;
long p3     = 3;
double p4   = 4;
boolean p5  = true;
String[] p6 = {"five", "six"};
_logger.error("this is an error message with parameters > {} {} {} {} {} {} <",
    p1, p2, p3, p4, p5, p6);