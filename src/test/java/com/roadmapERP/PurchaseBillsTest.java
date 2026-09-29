package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks December 17, 2025
 */
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

public class PurchaseBillsTest extends RoadMapERPTest {
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
	 * Domestic Registered Supplier - With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 1, groups = { "Purchase Bills " }, description = "Domestic Registered Supplier - With Local GST ")
	public void DomesticRegisteredSupplierWithLocalGST() {
		purchaseBillsFunc.verifyDomesticRegisteredSupplierWithLocalGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Supplier_Local"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"), commonFunc.getDataFromTestDataSheet("unitcost"),
				commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Registered Supplier - With Local GST (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 2, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - With Local GST (Allow IGST tax)")
	public void verifyDomesticRegistedSupplierWithLocalGSTAllowIGSTtax() {
		purchaseBillsFunc.verifyDomesticRegistedSupplierWithLocalGSTAllowIGSTtax(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Local_2"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Registered Supplier - With Local GST (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 3, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - With Local GST (Allow RCM tax)")
	public void verifyDomesticRegistedSupplierWithLocalAllowRCMtax() {
		purchaseBillsFunc.verifyDomesticRegistedSupplierWithLocalGSTAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Local_2"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("unitcost"),
				commonFunc.getDataFromTestDataSheet("HSNcode_SI"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"));
	}

	/**
	 * Domestic Registered Supplier - With Local GST ITC Ineligible
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 4, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - With Local GST ITC Ineligible ")
	public void DomesticRegisteredSupplierWithLocalGSTitcIneligible() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithLocalGSTIneligible(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Local_2"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Registered Supplier - With Local GST ITC Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 5, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - With Local GST ITC Ineligible (Allow RCM tax)")
	public void DomesticRegisteredSupplierWithLocalGSTitcIneligibleAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithLocalGSTitcIneligibleAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Local_2"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"), commonFunc.getDataFromTestDataSheet("unitcost"),
				commonFunc.getDataFromTestDataSheet("HSNcode_SI"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Registered Supplier - With Local GST ITC Ineligible (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 6, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - With Local GST ITC Ineligible (Allow IGST tax)")
	public void DomesticRegisteredSupplierWithLocalGSTitcIneligibleAllowIGSTtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithLocalGSTitcIneligibleAllowIGSTtax(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Local_2"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Registered Supplier - With Interstate GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 7, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - With Interstate GST")
	public void DomesticRegisteredSupplierWithInterstateGST() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithInterstateGST(commonFunc.getDataFromTestDataSheet("SubVouType"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_CasualPerson_Interstate"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("HSNcode_SI"), commonFunc.getDataFromTestDataSheet("unitcost"));
	}

	/**
	 * Domestic Registered Supplier - With Interstate GST (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 8, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - With Interstate GST (Allow RCM tax)")
	public void DomesticRegdSupplierWithInterstateGSTAllowRCMtax() {
		purchaseBillsFunc.DomesticRegdSupplierWithInterstateGSTAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Cus_Interstate"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"), commonFunc.getDataFromTestDataSheet("unitcost"),
				commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Registered Supplier - With Interstate GST ITC Ineligible (Allow RCM
	 * tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 9, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - With Interstate GST ITC Ineligible (Allow RCM tax)")
	public void DomesticRegisteredSupplierWithInterstateGSTIneligibleAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithInterstateGSTitcIneligibleAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Cus_Interstate"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"), commonFunc.getDataFromTestDataSheet("unitcost"),
				commonFunc.getDataFromTestDataSheet("HSNcode_SI"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Direct Expenses -Domestic UnRegistered Supplier - With Local GST
	 * 
	 * @author Kowshik-RMTS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 10, groups = {
			"Purchase Bills " }, description = "Direct Expenses -Domestic UnRegistered Supplier - With Local GST")
	public void DirectExpensesDomesticUnRegisteredSupplierWithLocalGST() {
		purchaseBillsFunc.DirectExpensesDomesticUnRegisteredSupplierWithLocalGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Direct Expenses -Domestic UnRegistered Supplier - With Local GST (ineligible)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 11, groups = {
			"Purchase Bills " }, description = "Direct Expenses -Domestic UnRegistered Supplier - With Local GST (ineligible)")
	public void DirectExpensesDomesticUnRegisteredSupplierWithLocalGSTineligible() {
		purchaseBillsFunc.DirectExpensesDomesticUnRegisteredSupplierWithLocalGSTineligible(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic UnRegistered Supplier - With Local GST (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 12, groups = {
			"Purchase Bills " }, description = "Domestic UnRegistered Supplier - With Local GST (Allow IGST tax)")
	public void DomesticUnRegisteredSupplierWithLocalGSTAllowIGSTtax() {
		purchaseBillsFunc.DomesticUnRegisteredSupplierWithLocalGSTAllowIGSTtax(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Supplier_Local"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"), commonFunc.getDataFromTestDataSheet("unitcost"),
				commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic UnRegistered Supplier - With Local GST (Allow IGST tax) (ineligible)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 13, groups = {
			"Purchase Bills " }, description = "Domestic UnRegistered Supplier - With Local GST (Allow IGST tax) (ineligible) ")
	public void DomesticUnRegisteredSupplierWithLocalGSTAllowIGSTtaxineligible() {
		purchaseBillsFunc.DomesticUnRegisteredSupplierWithLocalGSTAllowIGSTtaxineligible(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Supplier_Local"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"), commonFunc.getDataFromTestDataSheet("unitcost"),
				commonFunc.getDataFromTestDataSheet("HSNcode_SI"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Direct Expenses -Domestic UnRegistered Supplier - With Interstate GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 14, groups = {
			"Purchase Bills " }, description = "Direct Expenses -Domestic UnRegistered Supplier - With Interstate GST")
	public void DirectExpensesDomesticUnRegisteredSupplierWithInterstateGST() {
		purchaseBillsFunc.DirectExpensesDomesticUnRegisteredSupplierWithInterstateGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Non_Res_Interstate"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Direct Expenses -Domestic UnRegistered Supplier - With Interstate GST
	 * (ineligible)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 15, groups = {
			"Purchase Bills " }, description = "Direct Expenses -Domestic UnRegistered Supplier - With Interstate GST (ineligible)")
	public void DirectExpensesDomesticUnRegisteredSupplierWithInterstateGSTineligible() {
		purchaseBillsFunc.DirectExpensesDomesticUnRegisteredSupplierWithInterstateGSTineligible(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));

	}

	/**
	 * Domestic Registered Supplier - Party account
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 16, groups = { "Purchase Bills " }, description = "Domestic Registered Supplier - Party account ")
	public void DomesticRegisteredSupplierPartyaccount() {
		purchaseBillsFunc.DomesticRegisteredSupplierPartyaccount(commonFunc.getDataFromTestDataSheet("SubVouType"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Supplier_Local"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Glaccount_Party_Account"),
				commonFunc.getDataFromTestDataSheet("unitcost"));
	}

	/**
	 * Domestic UnRegistered Supplier - Party account
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 17, groups = { "Purchase Bills " }, description = "Domestic UnRegistered Supplier - Party account")
	public void DomesticUnRegisteredSupplierPartyaccount() {
		purchaseBillsFunc.DomesticUnRegisteredSupplierPartyaccount(commonFunc.getDataFromTestDataSheet("SubVouType"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Supplier_Local"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Glaccount_Party_Account"),
				commonFunc.getDataFromTestDataSheet("unitcost"));
	}

	/**
	 * Domestic Registered Supplier - , Distribute Invoice details
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 18, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - , Distribute Invoice details")
	public void DomesticRegisteredSupplierDistributeInvoicedetails() {
		purchaseBillsFunc.DomesticRegisteredSupplierDistributeInvoicedetails(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("CustomerName"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Glaccount_SalesInv_Dis"),
				commonFunc.getDataFromTestDataSheet("unitcost"));
	}

	/**
	 * Domestic UnRegistered Supplier - , Distribute Invoice details
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 19, groups = {
			"Purchase Bills " }, description = "Domestic UnRegistered Supplier - , Distribute Invoice details")
	public void DomesticUnRegisteredSupplierDistributeInvoicedetails() {
		purchaseBillsFunc.DomesticUnRegisteredSupplierDistributeInvoicedetails(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("CustomerName"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Glaccount_SalesInv_Dis"),
				commonFunc.getDataFromTestDataSheet("unitcost"));
	}

	/**
	 * Import Unregistered Supplier (without GST)
	 * 
	 * @author Kowshik-RITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 20, groups = { "Purchase Bills " }, description = "Import Unregistered Supplier (without GST)")
	public void ImportUnregisteredSupplierwithoutGST() {
		purchaseBillsFunc.ImportUnregisteredSupplierwithoutGST(commonFunc.getDataFromTestDataSheet("SubVouType"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("CustomerName_Import"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("SectionOfTds"));
	}

	/**
	 * Import Non-resident Supplier (without GST)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 21, groups = { "Purchase Bills " }, description = "Import Non-resident Supplier (without GST)")
	public void ImportNonresidentSupplierwithoutGST() {
		purchaseBillsFunc.ImportNonresidentSupplierwithoutGST(commonFunc.getDataFromTestDataSheet("SubVouType"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Import_Non_Res_Supplier"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	public void ImportNonresidentSupplierWithGST() {
		// purchaseBillsFunc.ImportNonresidentSupplierWithST();
	}

	/**
	 * Direct Expense - with Adjustments
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 23, groups = { "Purchase Bills  " }, description = "Direct Expense - with Adjustments ")
	public void DirectExpensewithAdjustments() {
		purchaseBillsFunc.DirectExpensewithAdjustments(commonFunc.getDataFromTestDataSheet("SubVouType"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"), commonFunc.getDataFromTestDataSheet("CustomerName"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"),
				commonFunc.getDataFromTestDataSheet("PaymentRec"));

	}

//	public void DirectExpenseForRecurringJournal() {
//		purchaseBillsFunc.DirectExpenseForRecurringJournal();
//	}
	@Author("Kowshik-RMITS")
	@Test(priority = 24, groups = { "Purchase Bills  " }, description = "Direct Expense - with Adjustments ")
	public void DomesticRegisteredSupplierWithSEZUnitGST() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithSEZUnitGST(commonFunc.getDataFromTestDataSheet("SubVouType"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Sez_Unit"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Registered Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 25, groups = {
			"Purchase Bills  " }, description = "Domestic Registered Supplier - With union Territory GST")
	public void DomesticRegisteredSupplierWithunionTerritoryGST() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithunionTerritoryGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("UTGSTLocation"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UTGST_Prefix"),
				commonFunc.getDataFromTestDataSheet("UTGST_REGRD_SUPPLIER"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Registered Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 26, groups = {
			"Purchase Bills  " }, description = "Domestic Registered Supplier - With Local Freight GST")
	public void DomesticRegisteredSupplierWithLocalFreightGST() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithLocalFreightGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Local_fright"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Unregistered Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 27, groups = {
			"Purchase Bills  " }, description = "Domestic Unregistered Supplier - With Local Freight GST")
	public void DomesticUnregisteredSupplierWithLocalFreightGST() {
		purchaseBillsFunc.DomesticUnregisteredSupplierWithLocalFreightGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Local_fright_Unreg"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic UnRegistered Supplier - With SEZ Unit GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Test(priority = 28, groups = {
			"Purchase Bills  " }, description = "Domestic UnRegistered Supplier - With SEZ Unit GST")
	public void DomesticUnRegisteredSupplierWithSEZUnitGST() {
		purchaseBillsFunc.DomesticUnRegisteredSupplierWithSEZUnitGST(commonFunc.getDataFromTestDataSheet("SubVouType"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Sez_Unit_Unreg"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Unregistered Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 29, groups = {
			"Purchase Bills  " }, description = "Domestic Unregistered Supplier - With union Territory GST")
	public void DomesticUnregisteredSupplierWithunionTerritoryGST() {
		purchaseBillsFunc.DomesticUnregisteredSupplierWithunionTerritoryGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("UTGSTLocation"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UTGST_Prefix"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UTGST_Customer_Unreg"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Composition Supplier - With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 30, groups = {
			"Purchase Bills  " }, description = "Domestic Composition Supplier - With Local GST")
	public void DomesticCompositionSupplierWithLocalGST() {
		purchaseBillsFunc.DomesticCompositionSupplierWithLocalGST(commonFunc.getDataFromTestDataSheet("SubVouType"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Compostion_Local"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
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
