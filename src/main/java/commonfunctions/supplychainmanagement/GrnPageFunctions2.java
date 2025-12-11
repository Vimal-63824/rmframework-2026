package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 5, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class GrnPageFunctions2 {
	private static WebDriverBase webDB = new WebDriverBase();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	private static DirectGrnPageFunctions DirectGRNFunc = new DirectGrnPageFunctions();
	private static ReportLoger logs = new ReportLoger();
	private static GrnPageFunctions GRNFunc = new GrnPageFunctions();
	private boolean flag = false;
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	private static GateEntryPageFunctions gateEntryPageFunc = new GateEntryPageFunctions();

	/**
	 * This Methods Is Used To Verify Add Line Details in GRN as Price list
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(String SheetName, String Supplier) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "DBDETAILS.xlsx";
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 22; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][68];
				String QTY = GRNMultipleline[i][5];
				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(3000);
					DirectGRNFunc.verifyClickingAdd();
					DirectGRNFunc.verifyClickingItemField(ITEM_NAME);
					DirectGRNFunc.verifyClickingHsnField(HSN);
					DirectGRNFunc.verifyClickingSuppQuantity(QTY);
					DirectGRNFunc.verifyClickingReceiptQuantity(QTY);
					DirectGRNFunc.verifyClickingSaveLine();
					DirectGRNFunc.verifyUnitcostAndDiscountAmountForPriceListItemForGrn(i, SheetName, Supplier);
					DirectGRNFunc.verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForGrn(i,
							SheetName);
					DirectGRNFunc.otherThanUserdefinedDiscountAmountForGrn(i, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyGrnAddLineDetailsOtherThanUserDefinedRateContract(String SheetName, String Supplier) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "DBDETAILS.xlsx";
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
	 * This Methods Is Used To Add Line Details
	 * 
	 * @author Sakthi-GWL
	 * @param SheetName
	 */
	public void verifyGrnAddLineDetails(String SheetName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "DBDETAILS.xlsx";
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
					DirectGRNFunc.verifyAddingDiscount(i, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Multiple Lot serial Item for General Type
	 * Document
	 * 
	 * @author Sakthi-GWL
	 * @param SheetName
	 */
	public void multipleLotandSerialWithGeneralType(String SheetName) {
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			commonFunc.hardWait(3000);
			webDB.clickAnElement(GrnLocators.LotSerailButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "LOT/SERIAL Button Clicked");
			flag = webDB.waitForElement(GrnLocators.LotSerialIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				ExcelUtils excelUtils = new ExcelUtils();
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "DBDETAILS.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
				for (int i = 22; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][1];
					String LOT_SERIAL = itemmultiple[i][54];
					String LOT_NUMBER = itemmultiple[i][55];
					String SERIAL_NUMBER = itemmultiple[i][56];
					logs.logging("INFO", "Total Line Length: " + itemmultiple.length);
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
								List<WebElement> removeFilter = webDB.getDriver()
										.findElements(By.xpath(GrnLocators.LotserialitemsInput));
								for (WebElement element : removeFilter) {
									if (element.isDisplayed()) {
										element.sendKeys(ITEM_NAME);
										break;
									}
								}
								webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
							}
						}
					}
					if (flag) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
							DirectGRNFunc.inputLotNumber(ITEM_NAME, LOT_NUMBER);
						} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {
							DirectGRNFunc.inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

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
					+ "DBDETAILS.xlsx";
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
	 * 
	 * @param BillNo
	 * @param Date
	 */
	public void clickImportLRAndfillBillNo(String BillNo, String Date) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GRN_ImportLRBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(DirectGrnPageLocators.GRN_ImportLRBtn, ElementType.Xpath);
			webDB.clickAnElement(DirectGrnPageLocators.GRN_ImportLRBtn, ElementType.Xpath);
			flag = webDB.waitForElement(DirectGrnPageLocators.GRN_ImportBillNoInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Import / LR Button Clicked");
				commonPayrollFunc.addDetailInTableWithoutLovButton(DirectGrnPageLocators.GRN_ImportBillNoInput,
						DirectGrnPageLocators.GRN_ImportBillNoInput, BillNo);
				logs.logging("INFO", "Import / LR Bill No Entered: " + BillNo);
				commonPayrollFunc.addDetailInTableWithoutLovButton(DirectGrnPageLocators.GRN_ImportBillDateInput,
						DirectGrnPageLocators.GRN_ImportBillDateInput, Date);
				logs.logging("INFO", "Import / LR Date Entered: " + Date);
				flag = webDB.waitForElement(DirectGrnPageLocators.GRN_ImportLROkBtn, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(DirectGrnPageLocators.GRN_ImportLROkBtn, ElementType.Xpath);
					flag = webDB.clickAnElement(DirectGrnPageLocators.GRN_ImportLROkBtn, ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						logs.logging("INFO", "Import / LR Ok Button Clicked");
					}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DirGRNImpSameUDService"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpSameUDService"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameUDService"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
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
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
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
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
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
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSameRCService"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
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
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
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
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
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
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpSamePLService"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-General-User
	 * Defined-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnUserDefinedDifferentUomGeneralTypeImportSupplier() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-Material-User
	 * Defined-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnUserDefinedDifferentUomMaterialTypeImportSupplier() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		flag = commonFunc.logoutUser();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation of Direct GRN-General-Rate
	 * Contract-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeImportSupplier() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		DirectGRNFunc.verifyGrnAdd();
		DirectGRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedRateContract(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		DirectGRNFunc.verifyGrnAdd();
		DirectGRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedRateContract(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		DirectGRNFunc.verifyGrnAdd();
		DirectGRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		DirectGRNFunc.verifyGrnAdd();
		DirectGRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-General-User Defined-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryGeneralUDSameStockAndSupplierUomImport() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-Material-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryMaterialUDSameStockAndSupplierUomImport() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-Service-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryServiceUDSameStockAndSupplierUomImport() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-General-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryGeneralRCSameStockAndSupplierUomImport() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-Material-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryMaterialRCSameStockAndSupplierUomImport() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-Service-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryServiceRCSameStockAndSupplierUomImport() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-General-Price List-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryGeneralPLSameStockAndSupplierUomImport() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-Material-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryMaterialPLSameStockAndSupplierUomImport() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-Service-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryServicePLSameStockAndSupplierUomImport() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}
}
