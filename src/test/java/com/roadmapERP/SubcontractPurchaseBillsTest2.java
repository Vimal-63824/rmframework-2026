package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.financials.SubcontractPurchaseBills;
import utils.WebDriverBase;

public class SubcontractPurchaseBillsTest2 {
	
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
	 * This Method used For NonResident Supplier imported with deviation and
	 * expenses account And Adjustment(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 56, groups = {
			"Subcontract" }, description = "NonResident Supplier imported with deviation and expenses account And Adjustment(without GST)")
	public void NonResidentSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractPurchasebillFunc
				.nonResidentSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment();
	}

	/**
	 * This Method used For Domestic Registered Supplier - With Local Freight GST
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 57, groups = {
			"Subcontract" }, description = "Domestic Registered Supplier - With Local Freight GST")
	public void DomesticRegisteredSupplierWithLocalFreightGST() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithLocalFreightGST();
	}

	/**
	 * This Method used For Domestic Registered Supplier - With Union Territory
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 58, groups = {
			"Subcontract" }, description = "Domestic Registered Supplier - With Union Territory")
	public void DomesticRegisteredSupplierWithUnionTerritory() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithUnionTerritory();
	}

	/**
	 * Domestic Registered Supplier - With Unregistered Local Freight
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 59, groups = {
			"Subcontract" }, description = "Domestic Registered Supplier - With Unregistered Local Freight")
	public void DomesticRegisteredSupplierWithUnregisteredLocalFreight() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithUnregisteredLocalFreight();
	}

	/**
	 * Domestic UnRegistered Supplier - With Union Territory
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 60, groups = {
			"Subcontract" }, description = "Domestic UnRegistered Supplier - With Union Territory")
	public void DomesticUnRegisteredSupplierWithUnionTerritory() {
		SubcontractPurchasebillFunc.domesticUnRegisteredSupplierWithUnionTerritory();
	}

	/**
	 * Domestic UnRegistered Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 61, groups = { "Subcontract" }, description = "Domestic UnRegistered Supplier - With Composition")
	public void DomesticRegisteredSupplierWithComposition() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithComposition();
	}

	/**
	 * Domestic LocalFreight Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 62, groups = { "Subcontract" }, description = "Domestic LocalFreight Supplier - With Composition")
	public void DomesticRegisteredSupplierWithLocalFreightComposition() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithLocalFreightComposition();
	}

	/**
	 * Domestic Interstate Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 63, groups = { "Subcontract" }, description = "Domestic Interstate Supplier - With Composition")
	public void DomesticRegisteredSupplierWithInterstateComposition() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithInterstateComposition();
	}

	/**
	 * Domestic UnionTerritory Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 64, groups = {
			"Subcontract" }, description = "Domestic UnionTerritory Supplier - With Composition")
	public void DomesticRegisteredSupplierWithUnionTerritoryComposition() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithUnionTerritoryComposition();
	}

	/**
	 * Domestic Interstate Supplier - With CasualPerson
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 65, groups = { "Subcontract" }, description = "Domestic Interstate Supplier - With CasualPerson")
	public void DomesticRegisteredSupplierWithInterstateCasualPerson() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithInterstateCasualPerson();
	}

	/**
	 * Domestic UnionTerritory Supplier - With CasualPerson
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 66, groups = {
			"Subcontract" }, description = "Domestic UnionTerritory Supplier - With CasualPerson")
	public void DomesticRegisteredSupplierWithUnionTerritoryCasualPerson() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithUnionTerritoryCasualPerson();
	}

	/**
	 * Domestic Interstate Supplier - With NonResident
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 67, groups = { "Subcontract" }, description = "Domestic Interstate Supplier - With NonResident")
	public void DomesticRegisteredSupplierWithInterstateNonResident() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithInterstateNonResident();
	}

	/**
	 * Domestic UnionTerritory Supplier - With NonResident
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 68, groups = {
			"Subcontract" }, description = "Domestic UnionTerritory Supplier - With NonResident")
	public void DomesticRegisteredSupplierWithUnionTerritoryNonResident() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithUnionTerritoryNonResident();
	}

	/**
	 * Registered Supplier with Local GST- Subcontract Direct
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 69, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Subcontract Direct")
	public void registeredSupplierwithLocalGSTSubcontractDirect() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTSubcontractDirect();
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Subcontract Direct
	 * With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 70, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Subcontract Direct With ExpensesAccount")
	public void registeredSupplierwithLocalGSTSubcontractDirectWithExpensesAccount() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTSubcontractDirectWithExpensesAccount();
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Subcontract Direct
	 * With ExpensesAccount ExpensesAccount And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 71, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST- Subcontract Direct With ExpensesAccount And Adjustment")
	public void registeredSupplierwithLocalGSTSubcontractDirectWithExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTSubcontractDirectWithExpensesAccountandAdjustment();
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow IGST tax) -
	 * Against Subcontract Direct
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 72, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract Direct")
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractDirect() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractDirect();
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow IGST tax) -
	 * Against Subcontract Direct With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 73, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract Direct With ExpensesAccount")
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractDirectWithExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractDirectWithExpensesAccount();
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow IGST tax) -
	 * Against Subcontract Direct With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 74, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract Direct With ExpensesAccount And Adjustment")
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractDirectWithExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractDirectWithExpensesAccountandAdjustment();
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow RCM tax) -
	 * Against Subcontract Direct
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 75, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract Direct ")
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractDirect() {
		SubcontractPurchasebillFunc.registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractDirect();
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow RCM tax) -
	 * Against Subcontract Direct With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 76, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract Direct With ExpensesAccount ")
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractDirectWithExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractDirectWithExpensesAccount();
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow RCM tax) -
	 * Against Subcontract Direct With ExpensesAccount And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 77, groups = {
			"Subcontract" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract Direct With ExpensesAccount And Adjustment ")
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractDirectWithExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractDirectWithExpensesAccountandAdjustment();
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Subcontract
	 * Direct
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 78, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST- Subcontract Direct ")
	public void registeredSupplierwithInterstateGSTSubcontractDirect() {
		SubcontractPurchasebillFunc.registeredSupplierwithInterstateGSTSubcontractDirect();
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Subcontract
	 * Direct With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 79, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST- Subcontract Direct With ExpensesAccount")
	public void registeredSupplierwithInterstateGSTAgainstSubcontractDirectandExpensesAccount() {
		SubcontractPurchasebillFunc.registeredSupplierwithInterstateGSTAgainstSubcontractDirectandExpensesAccount();
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Subcontract
	 * Direct With ExpensesAccount And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 80, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST- Subcontract Direct With ExpensesAccount And Adjustment")
	public void registeredSupplierwithInterstateGSTAgainstSubcontractDirectWithExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateGSTAgainstSubcontractDirectWithExpensesAccountandAdjustment();
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Subcontract
	 * Direct With ExpensesAccount And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 81, groups = {
			"Subcontract" }, description = "Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract Direct")
	public void registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractDirect() {
		SubcontractPurchasebillFunc.registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractDirect();
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Subcontract
	 * Direct With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 82, groups = {
			"Subcontract" }, description = "registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract Direct and Expenses Account")
	public void registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractDirectandExpensesAccount() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractDirectandExpensesAccount();
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Subcontract
	 * Direct With ExpensesAccount and Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 83, groups = {
			"Subcontract" }, description = "registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract Direct and Expenses Account and Adjustment")
	public void registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractDirectandExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc
				.registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractDirectandExpensesAccountandAdjustment();
	}

	/**
	 * This Method used For UnRegistered Supplier with Local GST- Subcontract Direct
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 84, groups = {
			"Subcontract" }, description = "UnRegistered Supplier with Local GST- Subcontract Direct")
	public void unRegisteredSupplierwithLocalGSTSubcontractDirect() {
		SubcontractPurchasebillFunc.unRegisteredSupplierwithLocalGSTSubcontractDirect();
	}

	/**
	 * This Method used For UnRegistered Supplier with Local GST- Subcontract Direct
	 * With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 85, groups = {
			"Subcontract" }, description = "UnRegistered Supplier with Local GST- Subcontract Direct With ExpensesAccount")
	public void unRegisteredSupplierwithLocalGSTSubcontractDirectDirectandExpensesAccount() {
		SubcontractPurchasebillFunc.unRegisteredSupplierwithLocalGSTSubcontractDirectDirectandExpensesAccount();
	}

	/**
	 * This Method used For UnRegistered Supplier with Local GST- Subcontract Direct
	 * With ExpensesAccount and Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 86, groups = {
			"Subcontract" }, description = "UnRegistered Supplier with Local GST- Subcontract Direct With ExpensesAccount and Adjustment")
	public void unRegisteredSupplierwithLocalGSTSubcontractDirectDirectandExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc
				.unRegisteredSupplierwithLocalGSTSubcontractDirectDirectandExpensesAccountandAdjustment();
	}

	/**
	 * This Method used For Unregistered Supplier with Local GST (Allow IGST tax)-
	 * Subcontract Direct
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 87, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Subcontract Direct")
	public void unRegisteredSupplierwithLocalGSTAllowIGSTTaxSubcontractDirect() {
		SubcontractPurchasebillFunc.unRegisteredSupplierwithLocalGSTAllowIGSTTaxSubcontractDirect();
	}

	/**
	 * This Method used For Unregistered Supplier with Local GST (Allow IGST tax)-
	 * Subcontract Direct With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 88, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Subcontract Direct With ExpensesAccount")
	public void unRegisteredSupplierwithLocalGSTAllowIGSTTaxSubcontractDirectandExpensesAccount() {
		SubcontractPurchasebillFunc.unRegisteredSupplierwithLocalGSTAllowIGSTTaxSubcontractDirectandExpensesAccount();
	}

	/**
	 * This Method used For Unregistered Supplier with Local GST (Allow IGST tax)-
	 * Subcontract Direct With ExpensesAccount and Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 89, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Subcontract Direct With ExpensesAccount and Adjustment")
	public void unRegisteredSupplierwithLocalGSTAllowIGSTTaxSubcontractDirectandExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc
				.unRegisteredSupplierwithLocalGSTAllowIGSTTaxSubcontractDirectandExpensesAccountandAdjustment();
	}

	/**
	 * Unregistered Supplier with Interstate GST- Subcontract Direct
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 90, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Interstate GST- Subcontract Direct")
	public void unRegisteredSupplierwithInterstateGSTSubcontractDirect() {
		SubcontractPurchasebillFunc.unRegisteredSupplierwithInterstateGSTSubcontractDirect();
	}

	/**
	 * Unregistered Supplier with Interstate GST- Subcontract Direct and Expenses
	 * Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 91, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Interstate GST- Subcontract Direct and Expenses Account")
	public void unRegisteredSupplierwithInterstateGSTSubcontractDirectandExpensesAccount() {
		SubcontractPurchasebillFunc.unRegisteredSupplierwithInterstateGSTSubcontractDirectandExpensesAccount();
	}

	/**
	 * Unregistered Supplier with Interstate GST- Subcontract Direct and Expenses
	 * Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 92, groups = {
			"Subcontract" }, description = "Unregistered Supplier with Interstate GST- Subcontract Direct and Expenses Account and Adjustment")
	public void unRegisteredSupplierwithInterstateGSTSubcontractDirectandExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc
				.unRegisteredSupplierwithInterstateGSTSubcontractDirectandExpensesAccountandAdjustment();
	}

	/**
	 * Unregistered Supplier imported (without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 93, groups = { "Subcontract" }, description = "Unregistered Supplier imported (without GST)")
	public void unregisteredSupplierimportedwithoutGST() {
		SubcontractPurchasebillFunc.unregisteredSupplierimportedwithoutGST();
	}

	/**
	 * Unregistered Supplier imported (without GST) and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 94, groups = {
			"Subcontract" }, description = "Unregistered Supplier imported (without GST) and Expenses Account")
	public void unregisteredSupplierimportedwithoutGSTandExpensesAccount() {
		SubcontractPurchasebillFunc.unregisteredSupplierimportedwithoutGSTandExpensesAccount();
	}

	/**
	 * Unregistered Supplier imported (without GST) and Expenses Account And
	 * Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 95, groups = {
			"Subcontract" }, description = "Unregistered Supplier imported (without GST) and Expenses Account And Adjustment")
	public void unregisteredSupplierimportedwithoutGSTandExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc.unregisteredSupplierimportedwithoutGSTandExpensesAccountandAdjustment();
	}

	/**
	 * Non-resident Supplier imported (without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 96, groups = { "Subcontract" }, description = "Non-resident Supplier imported (without GST)")
	public void nonresidentSupplierimportedwithoutdeviationwithoutGST() {
		SubcontractPurchasebillFunc.nonresidentSupplierimportedwithoutdeviationwithoutGST();
	}

	/**
	 * Non-resident Supplier imported without deviation(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 97, groups = {
			"Subcontract" }, description = "Non-resident Supplier imported (without GST)and Expenses Account")
	public void nonresidentSupplierimportedwithoutdeviationwithoutGSTandExpensesAccount() {
		SubcontractPurchasebillFunc.nonresidentSupplierimportedwithoutdeviationwithoutGSTandExpensesAccount();
	}

	/**
	 * Non-resident Supplier imported without deviation(without GST) and Expenses
	 * Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 98, groups = {
			"Subcontract" }, description = "Non-resident Supplier imported (without GST)and Expenses Account And Adjustment")
	public void nonresidentSupplierimportedwithoutdeviationwithoutGSTandExpensesAccountandAdjustment() {
		SubcontractPurchasebillFunc
				.nonresidentSupplierimportedwithoutdeviationwithoutGSTandExpensesAccountandAdjustment();
	}

	/**
	 * Domestic Registered Supplier - With Local Freight GST
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 99, groups = {
			"Subcontract" }, description = "Domestic Registered Supplier - With Local Freight GST")
	public void directdomesticRegisteredSupplierWithLocalFreightGST() {
		SubcontractPurchasebillFunc.directdomesticRegisteredSupplierWithLocalFreightGST();
	}

	/**
	 * Domestic Registered Supplier - With Union Territory GST
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 100, groups = {
			"Subcontract" }, description = "Domestic Registered Supplier - With Union Territory GST")
	public void DirectdomesticRegisteredSupplierWithUnionTerritoryGST() {
		SubcontractPurchasebillFunc.directdomesticRegisteredSupplierWithUnionTerritoryGST();
	}

	/**
	 * Domestic Unregistered Supplier - With Local Freight GST
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 101, groups = {
			"Subcontract" }, description = "Domestic Unregistered Supplier - With Local Freight GST")
	public void directdomesticRegisteredSupplierWithUnregisteredLocalFreight() {
		SubcontractPurchasebillFunc.directdomesticRegisteredSupplierWithUnregisteredLocalFreight();
	}

	/**
	 * Domestic Unregistered Supplier - With Union Territory GST
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 102, groups = {
			"Subcontract" }, description = "Domestic Unregistered Supplier - With Union Territory GST")
	public void directdomesticUnRegisteredSupplierWithUnionTerritory() {
		SubcontractPurchasebillFunc.directdomesticUnRegisteredSupplierWithUnionTerritory();
	}

	/**
	 * Domestic LocalFreight Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 103, groups = { "Subcontract" }, description = "Domestic LocalFreight Supplier - With Composition")
	public void directdomesticRegisteredSupplierWithLocalFreightComposition() {
		SubcontractPurchasebillFunc.directdomesticRegisteredSupplierWithLocalFreightComposition();
	}

	/**
	 * Domestic Interstate Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 104, groups = { "Subcontract" }, description = "Domestic Interstate Supplier - With Composition")
	public void DirectdomesticRegisteredSupplierWithInterstateComposition() {
		SubcontractPurchasebillFunc.directdomesticRegisteredSupplierWithInterstateComposition();
	}

	/**
	 * Domestic Interstate Supplier - With CasualPerson
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 105, groups = { "Subcontract" }, description = "Domestic Interstate Supplier - With CasualPerson")
	public void directdomesticRegisteredSupplierWithInterstateCasualPerson() {
		SubcontractPurchasebillFunc.directdomesticRegisteredSupplierWithInterstateCasualPerson();
	}

	/**
	 * Domestic UnionTerritory Supplier - With CasualPerson
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 106, groups = {
			"Subcontract" }, description = "Domestic UnionTerritory Supplier - With CasualPerson")
	public void DirectdomesticRegisteredSupplierWithUnionTerritoryCasualPerson() {
		SubcontractPurchasebillFunc.directdomesticRegisteredSupplierWithUnionTerritoryCasualPerson();
	}

	/**
	 * Domestic Interstate Supplier - With NonResident
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 107, groups = { "Subcontract" }, description = "Domestic Interstate Supplier - With NonResident")
	public void DirectdomesticRegisteredSupplierWithInterstateNonResident() {
		SubcontractPurchasebillFunc.directdomesticRegisteredSupplierWithInterstateNonResident();
	}

	/**
	 * Domestic UnionTerritory Supplier - With NonResident
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 108, groups = {
			"Subcontract" }, description = "Domestic UnionTerritory Supplier - With NonResident")
	public void DirectdomesticRegisteredSupplierWithUnionTerritoryNonResident() {
		SubcontractPurchasebillFunc.directdomesticRegisteredSupplierWithUnionTerritoryNonResident();
	}

	/**
	 * Domestic Registered Supplier - With TDS (Exempted)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 109, groups = {
			"Subcontract" }, description = "Domestic Registered Supplier - With TDS (Exempted)")
	public void DomesticRegisteredSupplierWithTDSExempted() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithTDSExempted();
	}

	/**
	 * Domestic Registered Supplier - With TDS Single Under section
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 110, groups = {
			"Subcontract" }, description = "Domestic Registered Supplier - With TDS Single Under section")
	public void domesticRegisteredSupplierWithTDSSingleUndersection() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithTDSSingleUndersection();
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS single under section (Single
	 * PO)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 111, groups = {
			"Subcontract" }, description = "Domestic Registered Supplier - With Advance TDS single under section (SinglePO)")
	public void domesticRegisteredSupplierWithAdvanceTDSsingleundersectionSinglePO() {
		SubcontractPurchasebillFunc.domesticRegisteredSupplierWithAdvanceTDSsingleundersectionSinglePO();
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS single under section (Single
	 * PO with Single New reference)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 112, groups = {
			"Subcontract" }, description = "Domestic Registered Supplier - With Advance TDS single under section (SinglePO with Single New reference)")
	public void domesticRegisteredSupplierWithAdvanceTDSsingleundersectionSinglePOWithSingleNewreference() {
		SubcontractPurchasebillFunc
				.domesticRegisteredSupplierWithAdvanceTDSsingleundersectionSinglePOWithSingleNewreference();
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
