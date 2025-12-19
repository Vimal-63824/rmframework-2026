package com.roadmapERP;

/**
 * @author Durai
 * @company Roadmap December 17, 2025
 */
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.financials.PurchaseBillsFunctionsGrn;
import commonfunctions.supplychainmanagement.DirectGrnPageFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class PurchaseBillTestGrn extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static DirectGrnPageFunctions Grnfunc = new DirectGrnPageFunctions();
	private static ReportLoger logs = new ReportLoger();
	MaterialReceiptVoucherFunctions MrvFunc = new MaterialReceiptVoucherFunctions();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
	static PurchaseBillsFunctionsGrn PurBillFunc = new PurchaseBillsFunctionsGrn();

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 1, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 2, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 3, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN With Deviation,Expenses Account and Adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationExpenseAndAdjustment() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 4, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 5, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 6, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN Without Deviation and Expenses Account And adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 7, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 8, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 9, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN With Deviation and Expenses Account And Adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 10, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 11, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 12, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN Without Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 13, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 14, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 15, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN Without Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 16, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 17, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 18, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN With Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 19, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 20, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationAndExpense()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 21, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN With Deviation,Expenses Account and Adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 22, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 23, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 24, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN Without Deviation and Expenses Account And adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 25, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 26, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationAndExpense()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 27, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN With Deviation and Expenses Account And Adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc
				.VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 28, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviation()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 29, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST- Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 30, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against GRN Without Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc
				.VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 31, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviation()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 32, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 33, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN Without Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc
				.VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 34, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 35, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationAndExpense()
			throws IOException {
		PurBillFunc.VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 36, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against GRN With Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc
				.VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * This Method Is Used To Logout After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		commonFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
