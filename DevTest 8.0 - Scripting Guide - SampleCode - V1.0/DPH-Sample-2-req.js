%beanshell%

// Copyright © 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//

import com.itko.util.ParameterList;
import com.itko.util.Parameter;
_logger.debug("{}", lisa_vse_request.getOperation());
_logger.debug("All Arguments:");
ParameterList args = lisa_vse_request.getArguments();
_logger.debug("{}", args);

String valueToAdd = "";
for(i=0;i<args.size();i++) {
    _logger.debug("Argument {}", i);
    Parameter thisParameter = args.get(i);
    _logger.debug("{}", thisParameter);
    thisName = thisParameter.getName();
    thisValue = thisParameter.getValue();
    thisValue = thisValue.replaceAll(" ", "%20");
    valueToAdd = valueToAdd + thisName + ":" + thisValue + "&";
}
if(i > 0) {
    if(valueToAdd.length() > 5) valueToAdd = valueToAdd.substring(0, valueToAdd.length() - 1);
    _logger.debug("Argument {} being added", i);
    args.addParameter(new Parameter("combinedValue", "combinedValue", valueToAdd));
    _logger.debug("{}", valueToAdd);
    lisa_vse_request.setArguments(args);

    _logger.debug("All Arguments now:");
    _logger.debug("{}", lisa_vse_request.getArguments());
}
