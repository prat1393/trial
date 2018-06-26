// Copyright © 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//

// This script returns a date of a day.
// The new date is away a given delta of days from a given date, and
// is not a Saturday or Sunday.
// Delta days can be be positive or negative numbers.
//
// See http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
// For information on SimpleDateFormat, i.e. on format strings
//
// Please be aware that SimpleDateFormat is locale sensitive.
// So any date calculations or evaluations are to be based on numbers
// instead of possibly localized strings
//
// Author: Rick Brown, CA

import java.text.SimpleDateFormat;

// read input parameters from properties
// use default values if properties are missing
int daysToAdd = testExec.getStateInt("myOffset", -8);
String strDateFormat = testExec.getStateString("myFormat", "M/d/yyyy");
Date myDate = testExec.getStateString("myStartDate", null);
// if no start date is given create a new one with date of today
if(myDate == null) myDate = new Date();
// Create a SimpleDateFormat object to format Date objects
SimpleDateFormat SDFformat = new SimpleDateFormat(strDateFormat);
_logger.debug("Start date = ("+SDFformat.format(myDate)+").");
// add the amount of days
Date newDate = new Date();
newDate.setDate(myDate.getDate()+daysToAdd);
_logger.debug("Calculated date = ("+SDFformat.format(newDate)+").");
// Set the date format to retrieve the number of day of week:
// 1=Monday, 7=Sunday
SimpleDateFormat whichDay = new SimpleDateFormat("u");
// Retrieve the day of the week to check if it is on weekend.
// If so, add respective days to return Monday.
Integer dayOfWeek = Integer.parseInt(whichDay.format(newDate));
_logger.debug("Next day of the week is ("+whichDay.format(newDate)+").");
if(dayOfWeek == 6) newDate.setDate(newDate.getDate()+ 2);
if(dayOfWeek == 7) newDate.setDate(newDate.getDate()+ 1);
_logger.debug("Non-WE day of the week is ("+whichDay.format(newDate)+").");
// format the date in given format
String formattedDate = SDFformat.format(newDate);
// Set Property to new date
testExec.setStateValue("myNewDate", formattedDate);
_logger.debug("New date ("+formattedDate+").");
// Return start date and new date.
return formattedDate;
// This return value will be available in properties LASTRESPONSE
// and lisa.<step name>.rsp for subsequent processing
