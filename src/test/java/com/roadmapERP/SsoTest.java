package com.roadmapERP;

/**
 /**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.SsoPageFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class SsoTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SsoPageFunctions Ssofunc = new SsoPageFunctions();

	/**
	 * Create Supplier Schedule Order For Domestic Supplier Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 1, groups = {
			"Request For Quotation" }, description = "Domestic Supplier - General Items with Similar Stock and Supplier UOM")
	public void verifyCreationOfSsoDomesticSupplierSimilarUom() {
		Ssofunc.verifyCreationOfSsoDomesticSupplierSimilarUom();
	}

	/**
	 * Create Supplier Schedule Order For Import Supplier Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 2, groups = {
			"Request For Quotation" }, description = "Import Supplier - General Items with Similar Stock and Supplier UOM")
	public void verifyCreationOfSsoImportSupplierSimilarUom() {
		Ssofunc.verifyCreationOfSsoImportSupplierSimilarUom();                                 
	}

	/**
	 * Create Supplier Schedule Order For Domestic Supplier DisSimilar UOM
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 3, groups = {
			"Request For Quotation" }, description = "Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void verifyCreationOfSsoDomesticSupplierDisSimilarUom() {
		Ssofunc.verifyCreationOfSsoDomesticSupplierDisSimilarUom();
	}

	/**
	 * Create Supplier Schedule Order For Import Supplier DisSimilar UOM
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 4, groups = {
			"Request For Quotation" }, description = "Import Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void verifyCreationOfSsoImportSupplierDisSimilarUom() {
		Ssofunc.verifyCreationOfSsoImportSupplierDisSimilarUom();
	}

	/**
	 * Cancel Supplier Schedule Order For Domestic Supplier Lines
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 5, groups = { "Request For Quotation" }, description = " cancel Supplier Schedule Order Lines")
	public void CancelSupplierScheduleOrderLines() {
		Ssofunc.cancelSupplierScheduleOrderLines();
	}

	/**
	 * Cancel Supplier Schedule Order For Domestic Supplier Document
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 6, groups = { "Request For Quotation" }, description = " cancel Supplier Schedule Order Document")
	public void CancelSupplierScheduleOrderDocument() {
		Ssofunc.cancelSupplierScheduleOrderDocument();
	}
}
