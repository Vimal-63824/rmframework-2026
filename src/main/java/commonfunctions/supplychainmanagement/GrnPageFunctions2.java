package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 5, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.GrnLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class GrnPageFunctions2 {
	private static WebDriverBase webDB = new WebDriverBase();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static DirectGrnPageFunctions DirectGRNFunc = new DirectGrnPageFunctions();
	private static ReportLoger logs = new ReportLoger();
	private static GrnPageFunctions GRNFunc = new GrnPageFunctions();
	private boolean flag = false;

	/**
	 * This Methods Is Used To Verify Grn Add Line Details from Sheet
	 * 
	 * @author Sakthi-GWL
	 * @param SheetName
	 * @param Supplier
	 */
	public void addGrnLineDetailsOtherThanUserDefined(String SheetName, String Supplier) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "ImportDirectGRN.xlsx";
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 22; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][68];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(3000);
					DirectGRNFunc.verifyClickingAdd();
					DirectGRNFunc.verifyClickingItemField(ITEM_NAME);
					DirectGRNFunc.verifyClickingHsnField(HSN);
					DirectGRNFunc.verifyClickingSuppQuantity(QTY);
					DirectGRNFunc.verifyClickingReceiptQuantity(QTY);
					DirectGRNFunc.verifyClickingCost(COST);
					DirectGRNFunc.verifyClickingSaveLine();
					DirectGRNFunc.verifyUnitcostAndDiscountAmountForOpenPoItemForGrn(i, SheetName, Supplier);
					DirectGRNFunc.verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForGrn(i,
							SheetName);
					DirectGRNFunc.otherThanUserdefinedDiscountAmountForGrn(i, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-General-User
	 * Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnUserDefinedSameUomGeneralTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		DirectGRNFunc.verifyGrnAddLineDetails(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		DirectGRNFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-Material-User
	 * Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnUserDefinedSameUomMaterialTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		DirectGRNFunc.verifyGrnAddLineDetails(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		DirectGRNFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-Service-User
	 * Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnUserDefinedSameUomServiceTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		DirectGRNFunc.verifyGrnAddLineDetails(webDB.getDataFromProperties("DirGRNImpSameUDService"));
		DirectGRNFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSameUDService"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameUDService"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-General-Rate
	 * Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		addGrnLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		DirectGRNFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-Material-Rate
	 * Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		addGrnLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		DirectGRNFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-Service-Rate
	 * Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		addGrnLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpSameRCService"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		DirectGRNFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSameRCService"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameRCService"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-General-Price List-Same
	 * Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		addGrnLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		DirectGRNFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-Material-Price
	 * List-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		addGrnLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		DirectGRNFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-Service-Price List-Same
	 * Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		addGrnLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpSamePLService"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		DirectGRNFunc.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSamePLService"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSamePLService"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-General-Rate
	 * Contract-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		// TODO Change Item Sheet
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		try {
			DirectGRNFunc
					.verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"));
		} catch (Exception e) {
			logs.logging("INFO", "Failed in 'verifyGrnAddLineDetailsOtherThanUserDefined' with Exception : " + e);
			Assert.fail("Failed in 'verifyGrnAddLineDetailsOtherThanUserDefined' with Exception : " + e);
		}
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-Material-Rate
	 * Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		// TODO Change Item Sheet
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		try {
			DirectGRNFunc
					.verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"));
		} catch (Exception e) {
			logs.logging("INFO", "Failed in 'verifyGrnAddLineDetailsOtherThanUserDefined' with Exception : " + e);
			Assert.fail("Failed in 'verifyGrnAddLineDetailsOtherThanUserDefined' with Exception : " + e);
		}
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-General-Price
	 * List-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		// TODO Change Item Sheet
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		try {
			DirectGRNFunc
					.verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"));
		} catch (Exception e) {
			logs.logging("INFO", "Failed in 'verifyGrnAddLineDetailsOtherThanUserDefined' with Exception : " + e);
			Assert.fail("Failed in 'verifyGrnAddLineDetailsOtherThanUserDefined' with Exception : " + e);
		}
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-Material-Price
	 * List-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		// TODO Change Item Sheet
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		try {
			DirectGRNFunc
					.verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"));
		} catch (Exception e) {
			logs.logging("INFO", "Failed in 'verifyGrnAddLineDetailsOtherThanUserDefined' with Exception : " + e);
			Assert.fail("Failed in 'verifyGrnAddLineDetailsOtherThanUserDefined' with Exception : " + e);
		}
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}
}
