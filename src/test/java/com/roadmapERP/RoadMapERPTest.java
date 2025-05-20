package com.roadmapERP;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import commonfunctions.CommonFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class RoadMapERPTest {
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	String suiteName;

	/**
	 * Browser Setup And URL Navigation
	 * 
	 * @author Sakthi-GWL
	 */
	@BeforeClass(alwaysRun = true)
	public void BrowserSetup() {
		commonFunc.browserOpen();
		commonFunc.enterUrl();
	}

	/**
	 * Closing The Browser After Class
	 * 
	 * @author Deepan-GWL
	 */
	@AfterClass
	public void TearDown(ITestContext context) {
		suiteName = context.getCurrentXmlTest().getSuite().getName();
		webDB.tearDown();
	}

	/**
	 * Send Mail With Report
	 * 
	 * @author Deepan-GWL
	 */
	@AfterSuite
	public void SendMail() {
		mail.execute(suiteName);
	}
}
