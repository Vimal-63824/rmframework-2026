package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Feb 10, 2026
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.financials.SubcontractPurchaseBills;
import commonfunctions.supplychainmanagement.SubcontractOrderFunctions;
import utils.WebDriverBase;

public class SubcontractPurchaseBillsTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static SubcontractPurchaseBills SubcontractPurchasebillFunc = new SubcontractPurchaseBills();
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
	 * Registered Supplier with Local GST- Against Subcontract With Deviation and
	 * Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 1, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract With Deviation and Expenses Account")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 2, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract Without Deviation")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAgainstSubcontractWithoutDeviation();
		Assert.assertTrue(flag);
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract Without Deviation and
	 * Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 3, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract Without Deviation and Expenses Account And adjustment")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 4, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract With Deviation")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAgainstSubcontractWithDeviation();
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract With Deviation
	 * Expenses Account and Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 5, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract With Deviation,Expenses Account and Adjustment")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract Without Deviation and
	 * Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 6, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Against Subcontract Without Deviation and Expenses Account")
	public void RegisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 7, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract With Deviation")
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviation();
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 8, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract With Deviation and Expenses Account")
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 9, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract With Deviation and Expenses Account And Adjustment")
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment();
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 10, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract Without Deviation")
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviation();
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 11, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract Without Deviation and Expenses Account")
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 12, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract Without Deviation and Expenses Account And Adjustment")
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment();
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With
	 * Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 13, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With Deviation")
	public void RegisteredSupplierwithLocalAllowRCMtaxAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalAllowRCMtaxAgainstSubcontractWithDeviation();
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With
	 * Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 14, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With Deviation and Expenses Account")
	public void RegisteredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With
	 * Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 15, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With Deviation and Expenses Account And Adjustment")
	public void RegisteredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment();
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 16, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract Without Deviation")
	public void RegisteredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithoutDeviation();
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 17, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract Without Deviation and Expenses Account")
	public void RegisteredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 18, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract Without Deviation and Expenses Account And Adjustment")
	public void RegisteredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment();
	}

	/**
	 * Registered Supplier with Interstate GST- Against Subcontract With Deviation
	 * and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 19, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST- Against Subcontract With Deviation and Expenses Account")
	public void RegisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Interstate GST- Against Subcontract Without
	 * Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 20, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST- Against Subcontract Without Deviation")
	public void RegisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviation();
		Assert.assertTrue(flag);
	}

	/**
	 * Registered Supplier with Interstate GST- Against Subcontract Without
	 * Deviation and Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 21, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST- Against Subcontract Without Deviation and Expenses Account And adjustment")
	public void RegisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * Registered Supplier with Interstate GST- Against Subcontract With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 22, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST- Against Subcontract With Deviation")
	public void RegisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithInterstateGSTAgainstSubcontractWithDeviation();
	}

	/**
	 * Registered Supplier with Interstate GST- Against Subcontract With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 23, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST- Against Subcontract With Deviation,Expenses Account and Adjustment")
	public void RegisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * Registered Supplier with Interstate GST- Against Subcontract Without
	 * Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 24, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST- Against Subcontract Without Deviation and Expenses Account")
	public void RegisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 25, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract With Deviation")
	public void RegisteredSupplierwithInterstateAllowRCMtaxAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractWithDeviation();
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * With Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 26, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract With Deviation and Expenses Account")
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * With Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 27, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract With Deviation and Expenses Account And Adjustment")
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment();
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 28, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract Without Deviation")
	public void registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithoutDeviation();
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 29, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract Without Deviation and Expenses Account")
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccount();
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 30, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract Without Deviation and Expenses Account And Adjustment")
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment();
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 31, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Local GST- Against Subcontract With Deviation")
	public void UnregisteredSupplierwithLocalGSTAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.unregisteredSupplierwithLocalGSTAgainstSubcontractWithDeviation();
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract With Deviation and
	 * Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 32, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Local GST- Against Subcontract With Deviation and Expenses Account")
	public void UnregisteredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc.unregisteredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract With Deviation
	 * Expenses Account and Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 33, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Local GST- Against Subcontract With Deviation,Expenses Account and Adjustment")
	public void UnregisteredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 34, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Local GST- Against Subcontract Without Deviation")
	public void UnregisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.unregisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviation();
		Assert.assertTrue(flag);
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract Without Deviation
	 * and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 35, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Local GST- Against Subcontract Without Deviation and Expenses Account")
	public void UnregisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccount();
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract Without Deviation
	 * and Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 36, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Local GST- Against Subcontract Without Deviation and Expenses Account And adjustment")
	public void UnregisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 37, groups = {
			"Subcontract" }, description = "UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract With Deviation")
	public void UnregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviation();
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 38, groups = {
			"Subcontract" }, description = "UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract With Deviation and Expenses Account")
	public void UnRegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 39, groups = {
			"Subcontract" }, description = "UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract With Deviation and Expenses Account And Adjustment")
	public void UnRegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment();
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 40, groups = {
			"Subcontract" }, description = "UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract Without Deviation")
	public void UnRegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviation();
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 41, groups = {
			"Subcontract" }, description = "UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract Without Deviation and Expenses Account")
	public void UnRegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccount();
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 42, groups = {
			"Subcontract" }, description = "UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract Without Deviation and Expenses Account And Adjustment")
	public void UnRegisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment();
	}

	/**
	 * Unregistered Supplier with Interstate GST- Against Subcontract With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 43, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Interstate GST- Against Subcontract With Deviation")
	public void UnregisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.unregisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviation();
	}

	/**
	 * Unregistered Supplier with Interstate GST- Against Subcontract With Deviation
	 * and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 44, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Interstate GST- Against Subcontract With Deviation and Expenses Account")
	public void UnregisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * Unregistered Supplier with Interstate GST- Against Subcontract With Deviation
	 * Expenses Account and Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 45, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Interstate GST- Against Subcontract With Deviation,Expenses Account and Adjustment")
	public void UnregisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 46, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Interstate GST- Against Subcontract Without Deviation")
	public void UnregisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.unregisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviation();
		Assert.assertTrue(flag);
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract Without Deviation
	 * and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 47, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Interstate GST- Against Subcontract Without Deviation and Expenses Account")
	public void UnregisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccount();
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract Without Deviation
	 * and Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 48, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Interstate GST- Against Subcontract Without Deviation and Expenses Account And adjustment")
	public void UnregisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * Unregistered Supplier imported without deviation(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 49, groups = {
			"Subcontract" }, description = "Unregistered Supplier imported with deviation(without GST)")
	public void UnregisteredSupplierwithImportedGSTAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.unregisteredSupplierwithImportedGSTAgainstSubcontractWithDeviation();
	}

	/**
	 * Unregistered Supplier imported without deviation(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 50, groups = {
			"Subcontract" }, description = "Unregistered Supplier imported without deviation(without GST)")
	public void UnregisteredSupplierwithImportedGSTAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.unregisteredSupplierwithImportedGSTAgainstSubcontractWithoutDeviation();
	}

	/**
	 * This Method used For Unregistered Supplier imported with deviation and
	 * expenses account(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 51, groups = {
			"Subcontract" }, description = "Unregistered Supplier imported with deviation and expenses account(without GST)")
	public void UnregisteredSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * This Method used For Unregistered Supplier imported with deviation and
	 * expenses account And Adjustment(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 52, groups = {
			"Subcontract" }, description = "Unregistered Supplier imported with deviation and expenses account And Adjustment(without GST)")
	public void UnregisteredSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.unregisteredSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * This Method used For NonResident Supplier imported with deviation(without
	 * GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 53, groups = {
			"Subcontract" }, description = "NonResident Supplier imported with deviation(without GST)")
	public void NonResidentSupplierwithImportedGSTAgainstSubcontractWithDeviation() {
		SubcontractPurchasebillFunc.nonResidentSupplierwithImportedGSTAgainstSubcontractWithDeviation();
	}

	/**
	 * This Method used For NonResident Supplier imported without deviation(without
	 * GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 54, groups = {
			"Subcontract" }, description = "NonResident Supplier imported without deviation(without GST)")
	public void NonResidentSupplierwithImportedGSTAgainstSubcontractWithoutDeviation() {
		SubcontractPurchasebillFunc.nonResidentSupplierwithImportedGSTAgainstSubcontractWithoutDeviation();
	}

	/**
	 * This Method used For NonResident Supplier imported with deviation and
	 * expenses account(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 55, groups = {
			"Subcontract" }, description = "NonResident Supplier imported with deviation and expenses account(without GST)")
	public void NonResidentSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractPurchasebillFunc
				.nonResidentSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccount();
	}

	/**
	 * Verify After Method
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
