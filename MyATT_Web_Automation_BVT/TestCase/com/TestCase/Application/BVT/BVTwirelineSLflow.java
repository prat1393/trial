package com.TestCase.Application.BVT;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.AppSpecificLibrary.BuildValidations;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;

public class BVTwirelineSLflow {
	public String sTcName;

	@Parameters({"Environment", "browser", "iter","tcName"})
	@BeforeTest
	public void beforeTest(String sEnv, String browser, String iter, String sTcName, final ITestContext testContext) throws Exception{
		this.sTcName=sTcName;
	
		//Launch
		LaunchAndLogout.LaunchApplication(sEnv, browser, iter, testContext, this.sTcName);

		
	}

	@Test
	public void test(final ITestContext testContext) throws Exception
	{
		
		///Login
		LaunchAndLogout.LoginApplication(testContext);
		
		/* Call to application specific function */
		BuildValidations.Wireline(testContext);
	}
	
	@Parameters({"tcName"})
	@AfterTest
	public void afterTest(final ITestContext testContext,String sTcName) throws Exception{
		//logout
		LaunchAndLogout.Logout(testContext, this.sTcName );

		//close application
		LaunchAndLogout.CloseApplication(testContext);
	}
	@AfterSuite public void endSuite() throws IOException {
		Report.cleanTemp();
	}

}