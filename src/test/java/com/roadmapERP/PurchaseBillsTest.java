package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks December 17, 2025
 */
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.financials.PurchaseBillsFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class PurchaseBillsTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	// static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseBillsFunctions purchaseBillsFunc = new PurchaseBillsFunctions();

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
				commonFunc.getDataFromTestDataSheet("Cus_Interstate"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"),
				commonFunc.getDataFromTestDataSheet("unitcost"));
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
				commonFunc.getDataFromTestDataSheet("Cus_Interstate"), commonFunc.getDataFromTestDataSheet("bills"),
				commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"), commonFunc.getDataFromTestDataSheet("unitcost"),
				commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
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
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UTGST_CustomerName"),
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
	 * Domestic Composition Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 31, groups = {
			"Purchase Bills " }, description = "Domestic Composition Supplier - With Local Freight GST")
	public void DomesticCompositionSupplierWithLocalFreightGST() {
		purchaseBillsFunc.DomesticCompositionSupplierWithLocalFreightGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Compostion_LF"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Composition Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 32, groups = {
			"Purchase Bills  " }, description = "Domestic Composition Supplier - With Inter state GST")
	public void DomesticCompositionSupplierWithInterstateGST() {
		purchaseBillsFunc.DomesticCompositionSupplierWithInterstateGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Compostion_Interstate"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Gl_Account_Ex"),
				commonFunc.getDataFromTestDataSheet("unitcost"), commonFunc.getDataFromTestDataSheet("HSNcode_SI"));
	}

	/**
	 * Domestic Composition Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 33, groups = {
			"Purchase Bills  " }, description = "Domestic Composition Supplier - With union Territory GST")
	public void DomesticCompositionSupplierWithunionTerritoryGST() {
		purchaseBillsFunc.DomesticCompositionSupplierWithunionTerritoryGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("UTGSTLocation"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UTGST_Prefix"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Compostion_UTGST"),
				commonFunc.getDataFromTestDataSheet("bills"));

	}

	/**
	 * Domestic Composition Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 34, groups = {
			"Purchase Bills  " }, description = "Domestic Composition Supplier - With union Territory GST")
	public void DomesticCasualpersonSupplierWithLocalFreightGST() {
		purchaseBillsFunc.DomesticCasualpersonSupplierWithLocalFreightGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_CasualPerson_LocalFright"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Casual person Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 35, groups = {
			"Purchase Bills  " }, description = "Domestic Casual person Supplier - With Inter state GST")
	public void DomesticCasualpersonSupplierWithInterstateGST() {
		purchaseBillsFunc.DomesticCasualpersonSupplierWithInterstateGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_CasualPerson_Interstate"),
				commonFunc.getDataFromTestDataSheet("bills"));

	}

	/**
	 * Domestic Casual person Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 36, groups = {
			"Purchase Bills  " }, description = "Domestic Casual person Supplier - With union Territory GST")
	public void DomesticCasualpersonSupplierWithunionTerritoryGST() {
		purchaseBillsFunc.DomesticCasualpersonSupplierWithunionTerritoryGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("UTGSTLocation"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UTGST_Prefix"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_CasualPerson_UTGST"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Non-resident Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 37, groups = {
			"Purchase Bills  " }, description = "Domestic Non-resident Supplier - With Local Freight GST")
	public void DomesticNonresidentSupplierWithLocalFreightGST() {
		purchaseBillsFunc.DomesticNonresidentSupplierWithLocalFreightGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Non_Res_Local_Fright"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Non-resident Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 38, groups = {
			"Purchase Bills  " }, description = "Domestic Non-resident Supplier - With Inter state GST")
	public void DomesticNonresidentSupplierWithInterstateGST() {
		purchaseBillsFunc.DomesticNonresidentSupplierWithInterstateGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Non_Res_Interstate"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * DomesticNon-resident Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 39, groups = {
			"Purchase Bills  " }, description = "DomesticNon-resident Supplier - With union Territory GST")
	public void DomesticNonresidentSupplierWithunionTerritoryGST() {
		purchaseBillsFunc.DomesticNonresidentSupplierWithunionTerritoryGST(
				commonFunc.getDataFromTestDataSheet("SubVouType"), commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Non_Res_UTGST"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 40, groups = {
			"Purchase Bills  " }, description = "Domestic Registered Supplier - Bill Against Pending PO With Local GST")
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGST() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGST();
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Local GST (Allow
	 * IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 41, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Pending PO With Local GST (Allow IGST tax)")
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGSTAllowIGSTtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGSTAllowIGSTtax();
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Local GST (Allow
	 * RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 42, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Pending PO With Local GST (Allow RCM tax)")
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGSTAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGSTAllowRCMtax();
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Local GST ITC
	 * Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 43, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Pending PO With Local GST ITC Ineligible (Allow RCM tax)")
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGSTTCIneligibleAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGSTTCIneligibleAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Interstate GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 44, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Pending PO With Interstate GST")
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithInterstateGST() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstPendingPOWithInterstateGST();
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Interstate GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 45, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Pending PO With Interstate GST (Allow RCM tax)")
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithInterstateGSTAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstPendingPOWithInterstateGSTAllowRCMtax();
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Interstate GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 46, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Pending PO With Interstate GST (Allow RCM tax)")
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithInterstateGSTITCIneligibleAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstPendingPOWithInterstateGSTITCIneligibleAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Pending PO With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 47, groups = {
			"Purchase Bills" }, description = "Domestic Unregistered Supplier - Bill Against Pending PO With Local GST")
	public void DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGST() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGST();
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Pending PO With Local GST
	 * (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 48, groups = {
			"Purchase Bills" }, description = "Domestic Unregistered Supplier - Bill Against Pending PO With Local GST (Allow IGST tax)")
	public void DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGSTAllowIGSTtax() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGSTAllowIGSTtax();
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Pending PO With Local GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 49, groups = {
			"Purchase Bills" }, description = "Domestic Unregistered Supplier - Bill Against Pending PO With Local GST (Allow RCM tax)")
	public void DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGSTAllowRCMtax() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGSTAllowRCMtax();
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Pending PO With Local GST ITC
	 * Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 50, groups = {
			"Purchase Bills " }, description = "Domestic Unregistered Supplier - Bill Against Pending PO With Local GST ITC Ineligible (Allow RCM tax)")
	public void DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGSTITCIneligibleAllowRCMtax() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGSTITCIneligibleAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Test(priority = 51, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Get Document With Local GST")
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGST() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGST(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"), commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST
	 * (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 52, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Get Document With Local GST (Allow IGST tax)")
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowIGSTtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowIGSTtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"), commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 53, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Get Document With Local GST (Allow RCM tax)")
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"), commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 54, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Get Document With Local GST (Allow RCM tax)")
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"), commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST ITC
	 * Ineligible (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 55, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Get Document With Local GST ITC Ineligible (Allow IGST tax)")
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowIGSTtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowIGSTtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"), commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Interstate GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 56, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Get Document With Interstate GST")
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithInterstateGST() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstGetDocumentWithInterstateGST(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Doc_InterState"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Interstate GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 57, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Get Document With Interstate GST (Allow RCM tax)")
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithInterstateGSTAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstGetDocumentWithInterstateGSTAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Doc_InterState"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Interstate GST
	 * ITC Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 58, groups = {
			"Purchase Bills " }, description = "Domestic Registered Supplier - Bill Against Get Document With Interstate GST ITC Ineligible (Allow RCM tax)")
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithInterstateGSTITCIneligibleAllowRCMtax() {
		purchaseBillsFunc.DomesticRegisteredSupplierBillAgainstGetDocumentWithInterstateGSTITCIneligibleAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Doc_InterState"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 59, groups = {
			"Purchase Bills " }, description = "Domestic Unregistered Supplier - Bill Against Get Document With Local GST")
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGST() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGST(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Local_Unregd"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST ITC
	 * Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 60, groups = {
			"Purchase Bills " }, description = "Domestic Unregistered Supplier - Bill Against Get Document With Local GST (Allow IGST tax)")
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowIGSTtax() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowIGSTtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Local_Unregd"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST
	 * (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 61, groups = {
			"Purchase Bills " }, description = "Domestic Unregistered Supplier - Bill Against Get Document With Local GST (Allow IGST tax)")
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowRCMtax() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Local_Unregd"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST ITC
	 * Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 62, groups = {
			"Purchase Bills " }, description = "Domestic Unregistered Supplier - Bill Against Get Document With Local GST ITC Ineligible (Allow RCM tax)")
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowRCMtax() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Local_Unregd"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST ITC
	 * Ineligible (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 63, groups = {
			"Purchase Bills " }, description = "Domestic Unregistered Supplier - Bill Against Get Document With Local GST ITC Ineligible (Allow IGST tax)")
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowIGSTtax() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowIGSTtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Local_Unregd"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Interstate
	 * GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 64, groups = {
			"Purchase Bills " }, description = "Domestic Unregistered Supplier - Bill Against Get Document With Interstate GST")
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithInterstateGST() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstGetDocumentWithInterstateGST(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Interstate_Unregd"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Interstate
	 * GST (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 65, groups = {
			"Purchase Bills " }, description = "Domestic Unregistered Supplier - Bill Against Get Document With Interstate GST (Allow RCM tax)")
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithInterstateGSTAllowRCMtax() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstGetDocumentWithInterstateGSTAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Interstate_Unregd"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Interstate
	 * GST ITC Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 66, groups = {
			"Purchase Bills " }, description = "Domestic Unregistered Supplier - Bill Against Get Document With Interstate GST ITC Ineligible (Allow RCM tax)")
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithInterstateGSTITCIneligibleAllowRCMtax() {
		purchaseBillsFunc.DomesticUnregisteredSupplierBillAgainstGetDocumentWithInterstateGSTITCIneligibleAllowRCMtax(
				commonFunc.getDataFromTestDataSheet("SubVouType_Po"),
				commonFunc.getDataFromTestDataSheet("PurchaseRefW.ref"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Interstate_Unregd"),
				commonFunc.getDataFromTestDataSheet("bills"), commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
	}

	/**
	 * Registered Supplier with Local GST- Purchase Direct (Stock item ,service item
	 * And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 67, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Local GST- Purchase Direct (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithLocalGSTPurchaseDirectStockitemAndserviceitemAndchargeitem() {
		purchaseBillsFunc.RegisteredSupplierwithLocalGSTPurchaseDirectStockitemAndserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Interstate_Unregd"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Local GST- Purchase Direct With Expenses Account
	 * (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 68, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Local GST- Purchase Direct With Expenses Account (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithLocalGSTPurchaseDirectWithExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.RegisteredSupplierwithLocalGSTPurchaseDirectWithExpensesAccountStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Purchase_Interstate_Unregd"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Purchase Direct (Stock
	 * item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 69, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Local GST (Allow IGST tax) - Purchase Direct (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.RegisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"), commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Purchase Direct and
	 * Expenses Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 70, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Local GST (Allow IGST tax) - Purchase Direct and Expenses Account (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.RegisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct (Stock
	 * item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 71, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.RegisteredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"), commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}
	
	/**Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct and Expenses Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 72, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(){
		purchaseBillsFunc.RegisteredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("bills"));
			
		}
	/**Registered Supplier with Interstate GST- Purchase Direct (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 73, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Interstate GST- Purchase Direct (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.RegisteredSupplierwithInterstateGSTPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Suppler_Interstate"),
				commonFunc.getDataFromTestDataSheet("bills"));
		
	}
	/**Registered Supplier with Interstate GST- Purchase Direct and Expenses Account (Stock item ,service item And charge item )
	 * 
	 * 
	 * @author KowshiK-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 74, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Interstate GST- Purchase Direct and Expenses Account (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.RegisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("CustomerName"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}
	/**Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 75, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("CustomerName"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}
	/**Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct and Expenses Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 76, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct and Expenses Account (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectndExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectndExpensesAccountStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("CustomerName"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}
	/**Unregistered Supplier with Local GST- Purchase Direct (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 77, groups = {
			"Purchase Bills " }, description = "Unregistered Supplier with Local GST- Purchase Direct (Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithLocalGSTPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.UnregisteredSupplierwithLocalGSTPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"),
				commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}
	
	
	

	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
