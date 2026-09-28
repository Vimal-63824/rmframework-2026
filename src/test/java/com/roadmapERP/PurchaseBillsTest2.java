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

public class PurchaseBillsTest2 extends RoadMapERPTest {
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

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct and
	 * Expenses Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 72, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.RegisteredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
						commonFunc.getDataFromTestDataSheet("bills"));

	}

	/**
	 * Registered Supplier with Interstate GST- Purchase Direct (Stock item ,service
	 * item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 73, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Interstate GST- Purchase Direct (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.RegisteredSupplierwithInterstateGSTPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Suppler_Interstate"),
				commonFunc.getDataFromTestDataSheet("bills"));

	}

	/**
	 * Registered Supplier with Interstate GST- Purchase Direct and Expenses Account
	 * (Stock item ,service item And charge item )
	 * 
	 * 
	 * @author KowshiK-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 74, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Interstate GST- Purchase Direct and Expenses Account (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.RegisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("CustomerName"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct
	 * (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 75, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"), commonFunc.getDataFromTestDataSheet("CustomerName"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct and
	 * Expenses Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 76, groups = {
			"Purchase Bills " }, description = "Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct and Expenses Account (Stock item ,service item And charge item )")
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectndExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectndExpensesAccountStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("CustomerName"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier with Local GST- Purchase Direct (Stock item ,service
	 * item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 77, groups = {
			"Purchase Bills " }, description = "Unregistered Supplier with Local GST- Purchase Direct (Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithLocalGSTPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.UnregisteredSupplierwithLocalGSTPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier with Local GST- Purchase Direct and Expenses Account
	 * (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 78, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier with Local GST- Purchase Direct and Expenses Account (Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithLocalGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.UnregisteredSupplierwithLocalGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier with Local GST- Purchase Direct and Expenses Account
	 * (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 79, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier with Local GST- Purchase Direct and Expenses Account (Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.UnregisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier with Local GST (Allow IGST tax)- Purchase Direct and
	 * Expenses Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 80, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier with Local GST- Purchase Direct and Expenses Account (Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.UnregisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier with Interstate GST- Purchase Direct (Stock item
	 * ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 81, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier with Interstate GST- Purchase Direct (Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithInterstateGSTPurchaseDirectStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc.UnregisteredSupplierwithInterstateGSTPurchaseDirectStockitemserviceitemAndchargeitem(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Unregistered Supplier with Interstate GST- Purchase Direct and Expenses
	 * Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 82, groups = {
			"Purchase Bills" }, description = "Unregistered Supplier with Interstate GST- Purchase Direct and Expenses Account (Stock item ,service item And charge item )")
	public void UnregisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem() {
		purchaseBillsFunc
				.UnregisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
						commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
						commonFunc.getDataFromTestDataSheet("PurchaseRef"),
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
						commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRed_Sup"),
						commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 83, groups = {
			"Purchase Bills" }, description = "Domestic Registered Supplier - With Local Freight GST")
	public void DomesticRegisteredSupplierWithLocalFreightGST1() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithLocalFreightGST(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Local_fright"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - With SEZ Unit GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 84, groups = {
			"Purchase Bills" }, description = "Domestic Registered Supplier - With SEZ Unit GST")
	public void DomesticRegisteredSupplierWithSEZUnitGST1() {
		purchaseBillsFunc.DomesticRegisteredSupplierWithSEZUnitGST(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Sez_Unit"),
				commonFunc.getDataFromTestDataSheet("bills"));
	}

	/**
	 * Domestic Registered Supplier - With SEZ Unit GST
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 85, groups = {
			"Purchase Bills" }, description = "Domestic Unregistered Supplier - With Local Freight GST")
	public void DomesticUnregisteredSupplierWithLocalFreightGST1() {
		purchaseBillsFunc.DomesticUnregisteredSupplierWithLocalFreightGST1(
				commonFunc.getDataFromTestDataSheet("Subvoucher_GRN"),
				commonFunc.getDataFromTestDataSheet("PurchaseRef"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Prbills_Pfx"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_Local_fright_Unreg"),
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
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
