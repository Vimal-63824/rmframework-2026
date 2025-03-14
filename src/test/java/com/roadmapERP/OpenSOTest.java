package com.roadmapERP;
/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
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
	static OpenSalesOrderFunctions OpenSOFunc = new OpenSalesOrderFunctions();

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
	public void VerifyOpenSo() {
//		OpenSOFunc.verifyOpenSO();
	}

	/**
	 * Cancel Open SO
	 * 
	 * 
	 * @author Kowshik
	 */
	@Test(priority = 2, groups = { "Cancel Open SO" }, description = "Cancelled Open SO")
	public void VerifyCancelStatement() {
		OpenSOFunc.verifyCancelStatement();
	}

}
