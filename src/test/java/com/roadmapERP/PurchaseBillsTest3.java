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

public class PurchaseBillsTest3  extends RoadMapERPTest{
	
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
