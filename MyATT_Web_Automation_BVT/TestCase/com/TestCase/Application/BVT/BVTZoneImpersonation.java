package com.TestCase.Application.BVT;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.AppSpecificLibrary.MyATTZoneDashboard;
import com.SupportingFiles.LaunchAndLogout;
import com.SupportingFiles.Report;

public class BVTZoneImpersonation {
	public String tcName;
	public String thisEnv;
	public String thisBrowser;
	public String thisIter;
	
	@Parameters({"Environment", "browser", "iter","tcName"})
	@BeforeTest
	public void beforeTest(String sEnv,String browser, String iter, String tcName, final ITestContext testContext) throws Exception{
		this.tcName=tcName;
		thisBrowser = browser;
		
		//Launch
		LaunchAndLogout.LaunchApplicationZone(sEnv, browser, iter, testContext, tcName);

		
	}

	@Test
	public void test(final ITestContext testContext) throws Exception{
		
		
		//Login
		LaunchAndLogout.LoginApplicationZone(testContext);

				
		/* Call to application specific function */
		MyATTZoneDashboard.ImpersonateMobileAndWeb(testContext);

		
		

	}

	@Parameters({"tcName"})
	@AfterTest
	public void afterTest(final ITestContext testContext, String tcName) throws Exception{
//		LaunchAndLogout.LogoutApplicationZone(testContext);
		//close application
		LaunchAndLogout.CloseApplication(testContext);
	}
	@AfterSuite public void endSuite() throws IOException {
		Report.cleanTemp();
	}
}


