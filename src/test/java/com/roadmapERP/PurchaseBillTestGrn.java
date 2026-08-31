package com.roadmapERP;

/**
 * @author Durai
 * @company Roadmap December 17, 2025
 */
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
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
	private static int previousStatus = ITestResult.SUCCESS;

	@BeforeMethod(alwaysRun = true)
	private void VerifyLogin() {
		if (VariableHandler.getSessionID() == null || VariableHandler.getSessionID().equalsIgnoreCase("")
				|| VariableHandler.getSessionOpened() == false || VariableHandler.getSessionID().equals(null)) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
		} else {
			commonFunc.navigateToHomePage();
		}
	}

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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationAndExpense();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationAndExpense();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationAndExpense();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationAndExpense();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationAndExpense();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationAndExpense();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationAndExpense();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationAndExpense();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationAndExpense();
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
				.verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationAndExpense();
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
				.verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationAndExpense();
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
				.verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviation();
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
		PurBillFunc.verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationAndExpense();
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
				.verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 37, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST- Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 38, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST- Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationAndExpense() throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 39, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST- Against GRN With Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 40, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST- Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviation() throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 41, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST- Against GRN Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationAndExpense() throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 42, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST- Against GRN Without Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 43, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 44, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithDeviationAndExpense() throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 45, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Against GRN With Deviation and Expenses Account with adjustment(Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 46, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithoutDeviation() throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 47, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Against GRN Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 48, groups = {
			"Purchase Bill" }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Against GRN Without Deviation and Expenses Account with adjustment(Stock item ,service item And charge item )")
	public void VerifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 49, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST- Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 50, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST- Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 51, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST- Against GRN With Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationExpenseAndAdjustment() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 52, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST- Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 53, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST- Against GRN Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 54, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST- Against GRN Without Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 55, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 56, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 57, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Against GRN With Deviation and Expenses Account with adjustment(Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 58, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviation() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 59, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Against GRN Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 60, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Against GRN Without Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 61, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Interstate GST- Against GRN With Deviation (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviation() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 62, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Interstate GST- Against GRN With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 63, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Interstate GST- Against GRN With Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationExpenseAndAdjustment();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 64, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Interstate GST- Against GRN Without Deviation (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviation() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviation();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 65, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Interstate GST- Against GRN Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationAndExpense() throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationAndExpense();
	}

	/**
	 * Create Purchase Bills Against GRN
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 66, groups = {
			"Purchase Bill" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Against GRN Without Deviation and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		PurBillFunc.VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationExpenseAndAdjustment();
	}

	/**
	 * This Method Is Used To Logout After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
