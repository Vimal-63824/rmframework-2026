package com.roadmapERP;

import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.OpenSalesOrderFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class OpenSOTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static OpenSalesOrderFunctions OSO = new OpenSalesOrderFunctions();

	/**
	 * Open SO (Rate Contract) - Export Customer
	 * 
	 * Open SO (Rate Contract) - Domestic Customer
	 * 
	 * @author Kowshik
	 * 
	 */
	@Test(priority = 1, groups = {
			"Open Sales Order" }, description = "Open SO (Rate Contract) - Domestic Customer , Open SO (Rate Contract) - Export Customer")
	public void verifyOpenso() {
		OSO.VerifyOpenso();
	}

	/**
	 * Cancel Open SO
	 * 
	 * 
	 * @author Kowshik
	 */
	@Test(priority = 2, groups = { "Cancel Open SO" }, description = "Cancelled Open SO")
	public void verifyCancelStatement() {
		OSO.VerifyCancelStatement();
	}

}
