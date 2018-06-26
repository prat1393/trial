%beanshell%

// Copyright � 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//
// You can use %beanshell%, %groovy% or %javascript% or some other installed JSR-223 scripting language
// This example is for beanshell

import com.itko.util.ParameterList;

// Manipulate operation
String operation = lisa_vse_request.getOperation();
lisa_vse_request.setOperation(operation + " - updated");

// This is implicitly set by calling setBodyText() or setBodyBytes
boolean isBinary = lisa_vse_request.isBinary();
lisa_vse_request.setBinary(false);

// Manipulate request body text
String theBody = lisa_vse_request.getBodyText();
lisa_vse_request.setBodyText("New body");

// Manipulate request body as binary
byte[] b = lisa_vse_request.getBodyBytes();
lisa_vse_request.setBodyBytes(b);

// Other
String asString = lisa_vse_request.toString();
long id = lisa_vse_request.getId();


// Arguments, Attributes, and Metadata are all ParameterList

ParameterList args = lisa_vse_request.getArguments();
lisa_vse_request.setArguments(args);

ParameterList attributes = lisa_vse_request.getAttributes();
lisa_vse_request.setAttributes(attributes);

ParameterList metadata = lisa_vse_request.getMetaData();
lisa_vse_request.setMetaData(metadata);

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

