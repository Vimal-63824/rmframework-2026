package com.roadmapERP;

import static org.testng.Assert.assertTrue;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks May 02, 2025
 */
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.RfqPageFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class RfqTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static RfqPageFunctions Rfqfunc = new RfqPageFunctions();
	private static ReportLoger logs = new ReportLoger();

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 1, groups = {
			"Request For Quotation" }, description = "Create Purchase Request Lines For Multiple Line")
	public void VerifyCreationOfPRGeneralRfq() {
		Rfqfunc.verifyCreationOfPRGeneralRfq();
	}
	/**
	 * Create Purchase Order from approved RFQ for multiple suppliers (Only
	 * Domestic)
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 2, groups = {
			"Request For Quotation" }, description = "Create Purchase Order from approved RFQ for multiple suppliers (Only Domestic)")
	public void VerifyCreationOfPendingRfqDomestic() {
		Rfqfunc.verifyCreationOfPendingRfqDomestic();
	}

	/**
	 * Create Purchase Order from approved RFQ for multiple suppliers (Only Domestic
	 * & Import)
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 3, groups = {
			"Request For Quotation" }, description = "Create Purchase Order from approved RFQ for multiple suppliers (Both Domestic & Import)")
	public void VerifyCreationOfPendingRfqDomesticAndImport() {
		Rfqfunc.verifyCreationOfPendingRfqDomesticAndImport();
	}

	/**
	 * Create Purchase Order From Pending RfQ
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 4, groups = { "Request For Quotation" }, description = "Create Purchase Order From Pending RfQ")
	public void VerifyCreationOfPendingPOFromRfq() {
		flag =Rfqfunc.verifyCreationOfPendingPOFromRfq();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Check RFQ by fixing SOB Qty for the RFQ Lines
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 5, groups = { "GRN" }, description = " Check RFQ by fixing SOB Qty for the RFQ Lines")
	public void VerifyCreationOfSobForRfqLines() {
		logs.logging("INFO", "This Test Scenarios Is Covered Under VerifyCreationOfPendingRfqDomestic");
		// TODO Covered
		// VerifyCreationOfPendingRfqDomestic()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 6,groups = {
	"GRN" }, description = "Find the RFQ with all possible search parameters")
	public void verifyCreationOfRfqFilterAllFieldWorking(){
		Rfqfunc.verifyCreationOfRfqFilterAllFieldWorking();
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Durai-RMITS
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		commonFunc.verifyAfterMethod();
	}
}
