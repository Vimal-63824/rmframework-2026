package com.roadmapERP;

import java.time.Duration;

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
		WebDriverBase.chromeOptions.addArguments("--disable-gpu");
		WebDriverBase.chromeOptions.addArguments("--no-sandbox");
		WebDriverBase.chromeOptions.addArguments("--disable-dev-shm-usage");
		WebDriverBase.chromeOptions.addArguments("--disable-extensions");
		WebDriverBase.chromeOptions.addArguments("--disable-software-rasterizer");
		WebDriverBase.chromeOptions.addArguments("--use-fake-device-for-media-stream");
		WebDriverBase.chromeOptions.addArguments("--use-fake-ui-for-media-stream");
		WebDriverBase.chromeOptions.addArguments("--gcm-registration-url=http://localhost:1/");
		WebDriverBase.chromeOptions.addArguments("--disable-background-networking");
		WebDriverBase.chromeOptions.addArguments("--disable-device-discovery-notifications");
		WebDriverBase.chromeOptions.addArguments("--disable-features=GCM");
		WebDriverBase.chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
		WebDriverBase.chromeOptions.setAcceptInsecureCerts(true);
		commonFunc.browserOpen();
		WebDriverBase.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
//		WebDriverBase.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
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
