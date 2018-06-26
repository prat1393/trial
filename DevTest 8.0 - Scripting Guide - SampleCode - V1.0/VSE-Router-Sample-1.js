// Copyright © 2015 CA, Inc. All rights reserved.
// This sample code does not contain any warranties and is provided as is and for informational purposes only.
//
// This script must return either an enum entry from ExecutionMode or
// a string that is the name of an enum entry.  The DYNAMIC entry may
// not be returned.  It will be executed for DYNAMIC execution mode
// only.
import com.itko.lisa.vse.ExecutionMode;

mode = testExec.getStateString("AA_VSM_Exec_Mode", "EFFICIENT");

return mode;

