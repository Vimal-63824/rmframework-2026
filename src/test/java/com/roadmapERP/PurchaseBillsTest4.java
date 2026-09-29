package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.financials.PurchaseBillsFunctions;
import utils.Mailer;
import utils.WebDriverBase;


public class PurchaseBillsTest4 extends RoadMapERPTest{

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	// static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseBillsFunctions purchaseBillsFunc = new PurchaseBillsFunctions();
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
	 * Domestic UnRegistered Supplier - With SEZ Unit GST
	 * 
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 86, groups = {
			"Purchase Bills" }, description = "Domestic UnRegistered Supplier - With SEZ Unit GST")
	public void DomesticUnRegisteredSupplierWithSEZUnitGST1() {
		purchaseBillsFunc.DomesticUnRegisteredSupplierWithSEZUnitGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Sez_Unit"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Composition Supplier - With Local GST
	 * 
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 87, groups = { "Purchase Bills" }, description = "Domestic Composition Supplier - With Local GST")
	public void DomesticCompositionSupplierWithLocalGST1() {
		purchaseBillsFunc.DomesticCompositionSupplierWithLocalGST(commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Compostion_Local"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Composition Supplier - With Local Freight GST
	 * 
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 88, groups = {
			"Purchase Bills" }, description = "Domestic Composition Supplier - With Local Freight GST")
	public void DomesticCompositionSupplierWithLocalFreightGST1() {
		purchaseBillsFunc.DomesticCompositionSupplierWithLocalFreightGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Compostion_LF"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Composition Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 89, groups = {
			"Purchase Bills" }, description = "Domestic Composition Supplier - With Inter state GST")
	public void DomesticCompositionSupplierWithInterstateGST1() {
		purchaseBillsFunc.DomesticCompositionSupplierWithInterstateGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Compostion_LF"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Casual person Supplier - With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 90, groups = {
			"Purchase Bills" }, description = "Domestic Casual person Supplier - With Local GST")
	public void DomesticCasualpersonSupplierWithLocalGST() {
		purchaseBillsFunc.DomesticCasualpersonSupplierWithLocalGST(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_CasualPerson_LocalFright"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Casual person Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 91, groups = {
			"Purchase Bills" }, description = "Domestic Casual person Supplier - With Local Freight GST")
	public void DomesticCasualpersonSupplierWithLocalFreightGST1() {
		purchaseBillsFunc.DomesticCasualpersonSupplierWithLocalFreightGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_CasualPerson_LocalFright"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Casual person Supplier - With Inter state GST
	 * 
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 92, groups = {
			"Purchase Bills" }, description = "Domestic Casual person Supplier - With Inter state GST")
	public void DomesticCasualpersonSupplierWithInterstateGST1() {
		purchaseBillsFunc.DomesticCasualpersonSupplierWithInterstateGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_CasualPerson_Interstate"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Non-resident Supplier - With Local GST
	 * 
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 93, groups = { "Purchase Bills" }, description = "Domestic Non-resident Supplier - With Local GST")
	public void DomesticNonresidentSupplierWithLocalGST() {
		purchaseBillsFunc.DomesticNonresidentSupplierWithLocalGST(commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Non_Res_Local_Fright"),
				commonFunc.getDataFromTestDataSheet("bills"));

	}

	/**
	 * Domestic Non-resident Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 94, groups = {
			"Purchase Bills" }, description = "Domestic Non-resident Supplier - With Local Freight GST")
	public void DomesticNonresidentSupplierWithLocalFreightGST1() {
		purchaseBillsFunc.DomesticNonresidentSupplierWithLocalFreightGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Non_Res_Local_Fright"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Non-resident Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 95, groups = {
			"Purchase Bills" }, description = "Domestic Non-resident Supplier - With Inter state GST")
	public void DomesticNonresidentSupplierWithInterstateGST1() {
		purchaseBillsFunc.DomesticNonresidentSupplierWithInterstateGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Non_Res_Interstate"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 96, groups = {
			"Purchase Bills" }, description = "Domestic Registered Supplier - With union Territory GST")
	public void DomesticRegisteredSupplierWithunionTerritoryGST1() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithunionTerritoryGST(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("UTGSTUnit1"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("UTGST_REGRD_SUPPLIER"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Unregistered Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 97, groups = {
			"Purchase Bills" }, description = "Domestic Unregistered Supplier - With union Territory GST")
	public void DomesticUnregisteredSupplierWithunionTerritoryGST1() {
		purchaseBillsFunc.DomesticUnregisteredSupplierWithunionTerritoryGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("UTGSTUnit1"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UTGST_Customer_Unreg"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Composition Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 98, groups = {
			"Purchase Bills" }, description = "Domestic Unregistered Supplier - With union Territory GST")
	public void DomesticCompositionSupplierWithunionTerritoryGST1() {
		purchaseBillsFunc.DomesticCompositionSupplierWithunionTerritoryGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("UTGSTUnit1"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UTGST_Customer_Unreg"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Casual person Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 99, groups = {
			"Purchase Bills" }, description = "Domestic Casual person Supplier - With union Territory GST")
	public void DomesticCasualpersonSupplierWithunionTerritoryGST1() {
		purchaseBillsFunc.DomesticCasualpersonSupplierWithunionTerritoryGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("UTGSTUnit1"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_CP_UTGST_Sup"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * DomesticNon-resident Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 100, groups = {
			"Purchase Bills" }, description = "DomesticNon-resident Supplier - With union Territory GST")
	public void DomesticNonresidentSupplierWithunionTerritoryGST1() {
		purchaseBillsFunc.DomesticNonresidentSupplierWithunionTerritoryGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("UTGSTUnit1"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Non_Res_Sup"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier imported (without GST)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 101, groups = { "Purchase Bills" }, description = "Unregistered Supplier imported (without GST)")
	public void UnregisteredSupplierimportedwithoutGST() {
		purchaseBillsFunc.unregisteredSupplierimportedwithoutGST(commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("ImportSupplier"), commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier imported and expenses account(without GST)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 102, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier imported and expenses account(without GST)")
	public void UnregisteredSupplierimportedandexpensesaccountwithoutGST() {
		purchaseBillsFunc.unregisteredSupplierimportedandexpensesaccountwithoutGST2(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("ImportSupplier"), commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier imported (without GST)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 103, groups = { "Purchase Bills" }, description = "Unregistered Supplier imported (without GST)")
	public void UnregisteredSupplierimportedwithoutGST1() {
		purchaseBillsFunc.unregisteredSupplierimportedwithoutGST1(commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("ImportSupplier"), commonFunc.getDataFromTestDataSheet("bills"));

	}

	/**
	 * Unregistered Supplier imported and expenses account(without GST)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 104, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier imported and expenses account(without GST)")
	public void UnregisteredSupplierimportedandexpensesaccountwithoutGST1() {
		purchaseBillsFunc.unregisteredSupplierimportedandexpensesaccountwithoutGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("ImportSupplier"), commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Non-resident Supplier imported (without GST)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 105, groups = { "Purchase Bills" }, description = "Non-resident Supplier imported (without GST)")
	public void UnregisteredSupplierImportWithGST() {
		purchaseBillsFunc.unregisteredSupplierImportWithGST(commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_NR"), commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Non-resident Supplier imported and expenses account(without GST)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 106, groups = {
			"Purchase Bills" }, description = "Non-resident Supplier imported and expenses account(without GST)")
	public void NonresidentSupplierimportedandexpensesaccountwithoutGST() {
		purchaseBillsFunc.nonresidentSupplierimportedandexpensesaccountwithoutGST(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_NR"), commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Local GST- Against GRN With Deviation,Expenses
	 * Account and Adjustment (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 107, groups = {
			"Purchase Bills" }, description = "Registered Supplier with Local GST- Purchase Direct With Expenses Account and Adjustment (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithLocalGSTAgainstGRNWithDeviationExpensesAccountandAdjustmentStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.registeredSupplierwithLocalGSTAgainstGRNWithDeviationExpensesAccountandAdjustmentStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Supplier_Local"),
						commonFunc.getDataFromTestDataSheet("bills"));

	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Purchase Direct and
	 * Expenses Account And Adjustment (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 108, groups = {
			"Purchase Bills" }, description = "Registered Supplier with Local GST (Allow IGST tax) - Purchase Direct and Expenses Account And Adjustment (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountAndAdjustmentStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.registeredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountAndAdjustmentStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Supplier_Local"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct and
	 * Expenses Account with adjustment (Stock item ,service item And charge item )
	 * 
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 109, groups = {
			"Purchase Bills" }, description = "Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct and Expenses Account with adjustment (Stock item ,service item And charge item )")

	public void RegisteredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.registeredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Supplier_Local"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Interstate GST- Purchase Direct and Expenses Account
	 * with adjustment (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 110, groups = {
			"Purchase Bills" }, description = "Registered Supplier with Interstate GST- Purchase Direct and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.registeredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("CustomerName"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct and
	 * Expenses Account with adjustment(Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 111, groups = {
			"Purchase Bills" }, description = "Registered Supplier with Interstate GST- Purchase Direct and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.registeredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("CustomerName"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier with Local GST- Purchase Direct and Expenses Account
	 * with adjustment (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 112, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier with Local GST- Purchase Direct and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithLocalGSTPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.unregisteredSupplierwithLocalGSTPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier with Local GST (Allow IGST tax)- Purchase Direct and
	 * Expenses Account with adjustment(Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 113, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier with Local GST (Allow IGST tax)- Purchase Direct and Expenses Account with adjustment(Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.unregisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier with Interstate GST- Purchase Direct and Expenses
	 * Account with adjustment (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 114, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier with Interstate GST- Purchase Direct and Expenses Account with adjustment (Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.unregisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier imported expenses account with adjustment (without GST)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 115, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier imported expenses account with adjustment (without GST)")
	public void UnregisteredSupplierimportedexpensesaccountwithadjustmentwithoutGST() {
		purchaseBillsFunc.unregisteredSupplierimportedexpensesaccountwithadjustmentwithoutGST(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("ImportSupplier"), commonFunc.getDataFromTestDataSheet("bills"));
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
