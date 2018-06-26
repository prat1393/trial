%beanshell%

// Copyright � 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//
// You can use %beanshell%, %groovy% or %javascript% or some other installed JSR-223 scripting language
// This example is for beanshell

import com.itko.util.ParameterList;

// Convert the incoming response into a format that VSE can work with,
// for instance to unzip an HTTP response

// Manipulate the body text
String theBody = lisa_vse_response.getBodyText();
lisa_vse_response.setBodyText("New body");

// Manipulate the body as binary
byte[] b = lisa_vse_response.getBodyBytes();
lisa_vse_response.setBodyBytes(b);

// Think time
String thinkTime = lisa_vse_response.getThinkTimeSpec();
lisa_vse_response.setThinkTimeSpec(thinkTime);

// Other
String asString = lisa_vse_response.toString();
long id = lisa_vse_response.getId();

// Metadata is a ParameterList
ParameterList metadata = lisa_vse_response.getMetaData();
lisa_vse_response.setMetaData(metadata);

// Working with ParameterList

ParameterList p = new ParameterList();

// Do we want to allow dupes or not?
p.setAllowDupes(true);
boolean areDupesAllowed = p.isDupesAllowed();

// Adding parameters
p.addParameters("key1=val1&key2=val2");  // many at once
p.addParameter("key3", "val3"); // one at a time

// Looking up parameters
String theVal = p.getParameterValue("key1");

// Updating parameters
p.setParameterValue("key3", "newVal");

// Removing parameters
p.removeParameter("key1");

// Removing all parameters
p.clear();

