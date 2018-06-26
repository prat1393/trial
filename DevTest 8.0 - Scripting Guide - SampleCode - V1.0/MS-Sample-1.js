// Copyright © 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//
// Right-click to insert a sample match script
// The scripting language defaults to Beanshell but you can choose other JSR-223 script engines
// or change the default language

// incomingRequest and sourceRequest are automatically available to the script.
// Both are of type com.itko.lisa.vse.stateful.model.Request

// You also have access to the normal matching engine by calling
// defaultMatcher.matches(). It evaluates the requests for a match as though
// there were no match script, and returns a
// boolean - false if they do not match, true if they do.

// This script only checks the operation name for a match, and to see if the
// first 3 characters of "SomeParameterName" match.
// Note that it pays no attention to match style / tolerance or comparison
// operators.

if (!incomingRequest.getOperation.equals("SampleRequest")) {
  return defaultMatcher.matches();
}

boolean operationsMatch = incomingRequest.getOperation().equals(sourceRequest.getOperation());
if (operationsMatch) {
  String incomingValue = incomingRequest.getArguments().get("SomeParameterName");
  String sourceValue = sourceRequest.getArguments().get("SomeParameterName");

  if(incomingValue.substring(0, 3).equalsIgnoreCase(sourceValue.substring(0, 3))) {
    // true means these arguments match
    return true;
  }
}
// false means no match
return false;
