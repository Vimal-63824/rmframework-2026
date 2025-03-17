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
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Service Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 1, groups = {
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
	@Test(priority = 2, groups = {
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
	@Test(priority = 3, groups = {
			"GRN" }, description = "233 And 234 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With and Without Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterial() {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterial();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and Without
	 * Inspection for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 4, groups = {
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
	@Test(priority = 5, groups = {
			"GRN" }, description = "224 And 225 PO to GRN-General-User Defined-Same Stock and Supplier UOM - With And Without Inspection Plan")

	public void PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithWithoutInspectionPlan() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * RateContract Document for Service Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 6, groups = {
			"GRN" }, description = "235 PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnServiceRateContractSameStockandSupplierUOMWoInspectionPlan() {
		grnPageFunc.poToGrnServiceRateContractSameStockAndSupplierUOMWoInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection PriceList Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 7, groups = {
			"GRN" }, description = "238 And 239 PO to GRN-General-Price List-Same Stock and Supplier UOM - With And WithOut Inspection Plan")
	public void PotoGrnGeneralPriceListSameStockAndSupplierUOMWithInspection() {
		grnPageFunc.poToGrnGeneralPriceListSameStockAndSupplierUOMWithInspection();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Stock Transfer Rate Contract Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 8, groups = {
			"GRN" }, description = "236 And 237 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With And Without Inspection Plan")
	public void PoToGrnStockTransferRateContractSameStockAndSupplierUomWithAndWithoutInspection() {
		grnPageFunc.PoToGrnStockTransferRateContractSameStockAndSupplierUomWithAndWithoutInspection();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Stock Transfer User Defined Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 9, groups = {
			"GRN" }, description = "229 And 230 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With And Without Inspection Plan")
	public void POtoGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection() {
		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Service Type(Price List Type)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 10, groups = {
			"GRN" }, description = "240 PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void POToGRNServicePriceListSameStockaAndSupplierUOMWOInspectionPlan() {
		grnPageFunc.poToGRNServicePriceListSameStockAndSupplierUOMWOInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Stock Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 11, groups = {
			"GRN" }, description = "243 And 244 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With And Without Inspection Plan")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspection() {
		grnPageFunc.poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspection();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection PriceList Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 12, groups = {
			"GRN" }, description = "245 And 246 PO to GRN-General-User Defined-Different Stock and Supplier UOM - With And Without Inspection Plan")
	public void PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithWithoutInspectionPlan() {
		grnPageFunc.poToGrnGeneralUserDefinedStockAndSupplierDisUomWithAndWithoutInspectionPlan();
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
