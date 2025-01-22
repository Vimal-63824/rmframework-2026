package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import commonfunctions.CommonFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class RoadMapERPTest {
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();

	/**
	 * Browser Setup And URL Navigation
	 * 
	 * @author Sakthi-GWL
	 */
	@BeforeTest(alwaysRun = true)
	public void BrowserSetup() {
		commonFunc.browserOpen();
		commonFunc.enterUrl();
	}

	/**
	 * Closing The Browser After Test
	 * 
	 * @author Deepan-GWL
	 */
	@AfterTest
	public void TearDown() {
		webDB.tearDown();
	}

	/**
	 * Send Mail With Report
	 * 
	 * @author Deepan-GWL
	 */
	@AfterSuite
	public void SendMail() {
		mail.execute(this.getClass().getSimpleName());
	}
}
