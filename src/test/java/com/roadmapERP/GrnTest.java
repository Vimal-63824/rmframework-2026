package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import utils.Mailer;
import utils.WebDriverBase;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class GrnTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();

	/**
	 * This Methods Is Used To Create GRN Document from PO with and Without
	 * Inspection for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 1, groups = {
			"GRN" }, description = "226 And 227 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan() throws IOException {
		grnPageFunc.poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan();

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and Without
	 * Inspection for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 2, groups = {
			"GRN" }, description = "224 And 225 PO to GRN-General-User Defined-Same Stock and Supplier UOM - With And Without Inspection Plan")

	public void PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithWithoutInspectionPlan() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan();

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Service Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 3, groups = {
			"GRN" }, description = "228 PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnServiceUserDefinedSameStockAndSupplierUOMWOInspectionPlan() {
		grnPageFunc.verifyGrnPODomesticServiceSimilarUOMfromPo();

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection RateContract Document For General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 4, groups = {
			"GRN" }, description = "231 And 232 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With And Without Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralType() {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralType();

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection RateContract Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 5, groups = {
			"GRN" }, description = "233 And 234 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With and Without Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterial() {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterial();

	}

	/**
	 * This Method Is Used To Logout After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		commonFunc.verifyAfterMethod();

	}

}
