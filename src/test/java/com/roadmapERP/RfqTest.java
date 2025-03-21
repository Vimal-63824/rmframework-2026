package com.roadmapERP;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.RfqPageFunctions;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import utils.Mailer;
import utils.WebDriverBase;
public class RfqTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static RfqPageFunctions Rfqfunc = new RfqPageFunctions();
	
	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai
	 */
	@Test(priority = 1,groups = {
	"Request For Quotation" }, description = "Create Purchase Request Lines For Multiple Line")
	public void VerifyCreationOfPRGeneralRfq() {
		Rfqfunc.verifyCreationOfPRGeneralRfq();
	}
	/**
	 * Create Purchase Order from approved RFQ for multiple suppliers (Only Domestic)
	 * 
	 * @author Durai
	 */
	@Test(priority = 2,groups = {
	"Request For Quotation" }, description = "Create Purchase Order from approved RFQ for multiple suppliers (Only Domestic)")
	public void VerifyCreationOfPendingRfqDomestic() {
		Rfqfunc.verifyCreationOfPendingRfqDomestic();
	}
	/**
	 * Create Purchase Order from approved RFQ for multiple suppliers (Only Domestic & Import)
	 * 
	 * @author Durai
	 */
	@Test(priority = 3,groups = {
	"Request For Quotation" }, description = "Create Purchase Order from approved RFQ for multiple suppliers (Both Domestic & Import)")
	public void VerifyCreationOfPendingRfqDomesticAndImport() {
		Rfqfunc.verifyCreationOfPendingRfqDomesticAndImport();
	}
	/**
	 * Create Purchase Order From Pending RfQ
	 * 
	 * @author Durai
	 */
	@Test(priority = 4,groups = {
	"Request For Quotation" }, description = "Create Purchase Order From Pending RfQ")
	public void VerifyCreationOfPendingPOFromRfq() {
		Rfqfunc.verifyCreationOfPendingPOFromRfq();
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
