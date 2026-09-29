package commonfunctions.supplychainmanagement;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.qualitycontrol.InspectionDocumentsLocators;
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import junit.framework.AssertionFailedError;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class DirectGrnPageFunctions {
	private static WebDriverBase webDB = new WebDriverBase();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();
	private static ReportLoger logs = new ReportLoger();
	private static GrnPageFunctions GRNFunc = new GrnPageFunctions();
	private boolean flag = false;
	static InspectionDocumentFunctions qcFunc = new InspectionDocumentFunctions();
	static MaterialReceiptVoucherFunctions mrvFunc = new MaterialReceiptVoucherFunctions();
	private static InspectionDocumentFunctions InsDocFunc = new InspectionDocumentFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static SalesOrderFunctions salesOrderFunctions = new SalesOrderFunctions();
	static GrnPageFunctions2 GRNFunc2 = new GrnPageFunctions2();
	public ArrayList<String> MultiGRNDocs = new ArrayList<String>();
	LocalDate date = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String Today = date.format(formatter);

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomMaterialTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomServiceTypeWithInspection() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNSerUserDefined"));
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNSerUserDefined"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Used To Verify stock Quantity For Quality Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyStockQtyAndCostForQualityWh(String SheetName) throws IOException {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String WareHouse = ItemMultiple[i][44];
			String ExpectedStockQuantity = ItemMultiple[i][50];
			String ExpectedStockCost = ItemMultiple[i][51];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunc.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO",
						"Expected InspectionWH Stock Qty For This Item " + ItemId + "Is : " + ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunc.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected InspectionWH Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 46, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 47, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Used To Verify stock Quantity For Source Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyStockQtyAndCostForSourceWH(String SheetName) throws IOException {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String SourecWareHouse = ItemMultiple[i][63];
			String ExpectedStockQuantity = ItemMultiple[i][67];
			String ExpectedStockCost = ItemMultiple[i][66];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunc.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO", "Expected Source WareHouse Stock Qty For This Item " + ItemId + "Is : "
						+ ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunc.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 65, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 64, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGrnAdd() {
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnAddBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.GrnAddBtn, ElementType.Xpath);
			logs.logging("INFO", "Add Button is Clicked");
		}

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGrnAddDetails(String Location, String Type, String Supplier, String BillNo) {
		String SupBillNo = RandomStringUtils.randomNumeric(6);
		if (SupBillNo.equals("000")) {
			SupBillNo = "10";
		}
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String DCNO = BillNo + SupBillNo;
		VerifyToClickGrnFrame();
		String GetTextLocation = webDB.getDomPropertyFromElement(DirectGrnPageLocators.LocationLOV, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		if (!GetTextLocation.equals(Location)) {
			flag = webDB.waitForElement(DirectGrnPageLocators.GrnLocLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnLocLov, Location);
				logs.logging("INFO", "Location Is Entered:" + Location);
			}
			VerifyToClickGrnFrame();
		}
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnType, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByValue(DirectGrnPageLocators.GrnType, Type, ElementType.Xpath);
			logs.logging("INFO", "Type Is Entered:" + Type);
			flag = webDB.waitForElement(DirectGrnPageLocators.GrnSupplierLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnSupplierLov, Supplier);
				logs.logging("INFO", "Supplier Is Entered:" + Supplier);
				VerifyToClickGrnFrame();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnSupplierBillNo, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(DirectGrnPageLocators.GrnSupplierBillNo, DCNO, ElementType.Xpath);
					logs.logging("INFO", "Supplier Bill NO Is Entered");
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnDcNo, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(DirectGrnPageLocators.GrnDcNo, DCNO, ElementType.Xpath);
						logs.logging("INFO", "DC NO Is Entered");
						commonFunc.verifyAddTableSpecsClearField(DirectGrnPageLocators.DCDateInput,
								DirectGrnPageLocators.DCDateInput, Today);
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnCreate, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DirectGrnPageLocators.GrnCreate, ElementType.Xpath);
							logs.logging("INFO", "GRN Is Created");
							webDB.switchToDefaultContent();
							flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
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
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
					verifyClickingSuppQuantity(QTY);
					verifyClickingReceiptQuantity(QTY);
					verifyClickingCost(COST);
//					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineSupQty,
//							DirectGrnPageLocators.GrnLineSupQtyInput, QTY);
//					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineRcpQty,
//							DirectGrnPageLocators.GrnLineRcpQtyInput, QTY);
//					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineCost,
//							DirectGrnPageLocators.GrnLineCostInput, COST);
					verifyClickingSaveLine();
					verifyAddingDiscount(i, SheetName);
				}
			}
		}
	}

	/**
	 * This Methods Method to handle Multiple Lot serial Item for General Type
	 * Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void multipleLotandSerialWithGeneralType(String SheetName) {
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			webDB.clickAnElement(GrnLocators.LotSerailButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "LOT/SERIAL Button Clicked");
			flag = webDB.waitForElement(GrnLocators.LotSerialIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				System.out.println("TEST");
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				ExcelUtils excelUtils = new ExcelUtils();
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "DBDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
				for (int i = 22; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][1];
					String LOT_SERIAL = itemmultiple[i][54];
					String LOT_NUMBER = itemmultiple[i][55];
					String SERIAL_NUMBER = itemmultiple[i][56];
					System.out.println(itemmultiple.length);
					System.out.println("TEST1");
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
						System.out.println("TEST2");
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
//							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
//							if (flag) {
//								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
//								webDB.switchToDefaultContent();
//								System.out.println("Nit");
//								flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
//								List<WebElement> removeFilter = webDB.getDriver()
//										.findElements(By.xpath(GrnLocators.LotserialitemsInput));
//								for (WebElement element : removeFilter) {
//									if (element.isDisplayed()) {
//										element.sendKeys(ITEM_NAME);
//										System.out.println(1);
//										break;
//									}
//								}
//								webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
//							}
						}
					}

					if (flag) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
							inputLotNumber(ITEM_NAME, LOT_NUMBER);

						} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {

							inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
						}

					}
				}

			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Quality
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyExpectedStockQtAndCostyForQualityWH(String SheetName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String WareHouse = ItemDetails[i][44];
			String AfterExcelStockCost = ItemDetails[i][51];
			if (!ItemId.equals("")) {
				String AfterStockCost = dbCommonFunc.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After GRN Actual Stock Cost For This Item " + ItemId + "Is : " + AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO",
						"Verified Expected Stock Cost For This Item " + ItemId + " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Source
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(String SheetName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String SourecWareHouse = ItemDetails[i][63];
			String AfterExcelStockQuantity = ItemDetails[i][67];
			String AfterExcelStockCost = ItemDetails[i][66];
			if (!ItemId.equals("")) {

				String AfterStockQty = dbCommonFunc.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockQty);

				verifyAssertionBothValues(AfterStockQty, AfterExcelStockQuantity);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Qty For This Item " + ItemId
						+ " With Database Values After MRV");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				String AfterStockCost = dbCommonFunc.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Cost For This Item " + ItemId
						+ " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void afterVerifyTheStatusDocumentMoveToQcOrMrv(String GrnHeaderWiseStatus) throws IOException {
		if (GrnHeaderWiseStatus.equals(commonFunc.getDataFromTestDataSheet("Draft"))) {
			qcFunc.InspectionDocumentWithInspectionPlan();
		}
		mrvFunc.verifyPostMaterialReceiptWithAndWithoutLocator();

	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void afterVerifyTheStatusMrvToCancelDocumentWise() throws IOException {
		if (VariableHandler.getGrnStatus().equals(commonFunc.getDataFromTestDataSheet("Draft"))) {
			qcFunc.InspectionDocumentWithInspectionPlan();
		}
		mrvFunc.verifyPostMaterialReceiptWithAndWithoutLocatorBasedOnDocumentWiseCancel();

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingAdd() {
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineAdd, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.GrnLineAdd, ElementType.Xpath);
			logs.logging("INFO", "GRN Line Is Added");
			flag = webDB.waitForElement(DirectGrnPageLocators.GRNPageItemTable(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Grn Item Field Is Checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingItemField(String ITEM_NAME) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNPageItemTable(), ElementType.Xpath);
		if (flag) {
			flag = webDB.doubleClickAnElement(DirectGrnPageLocators.GRNPageItemTable(), ElementType.Xpath);
			logs.logging("INFO", "Item Lov Is Clicked");
			commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnLineItemLov, ITEM_NAME);
			logs.logging("INFO", "Entered Item Is: " + ITEM_NAME);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
//			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingHsnField(String HSN) {
//		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNPageHSNTable(), ElementType.Xpath);
		if (flag) {
			String GetTextHSN = webDB.getTextFromElement(DirectGrnPageLocators.GRNPageHSNTable(), ElementType.Xpath);
			if (!GetTextHSN.equals(HSN)) {
				webDB.doubleClickAnElement(DirectGrnPageLocators.GRNPageHSNTable(), ElementType.Xpath);
				logs.logging("INFO", "HSN Field Is Clicked");
				commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnLineHsnLov, HSN);
				logs.logging("INFO", "Entered HSN Code Is:" + HSN);
				webDB.pressTabKey();
			}
			webDB.pressTabKey();
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingSuppQuantity(String QTY) {
		commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageSupQtyTable(),
				DirectGrnPageLocators.GrnPageSupQtyInput(), QTY);
		logs.logging("INFO", "Entered Supply Quantity Is:" + QTY);
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingSuppQuantityHK(String QTY) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNPageSupQtyTable(), ElementType.Xpath);
		if (flag) {
			commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageSupQtyTable(),
					DirectGrnPageLocators.GrnPageSupQtyInput(), QTY);
			webDB.pressTabKey();
//			logs.logging("INFO", "Entered Supply Quantity Is:" + QTY);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingReceiptQuantityHK(String QTY) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNPageRcptQtyTable(), ElementType.Xpath);
		if (flag) {
			commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageRcptQtyTable(),
					DirectGrnPageLocators.GRNPageRcptQtyInput(), QTY);
			webDB.pressTabKey();
			logs.logging("INFO", "Entered Supply Quantity Is:" + QTY);
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingReceiptQuantity(String QTY) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNPageRcptQtyTable(), ElementType.Xpath);
		if (flag) {
			commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageRcptQtyTable(),
					DirectGrnPageLocators.GRNPageRcptQtyInput(), QTY);
			logs.logging("INFO", "Entered Supply Quantity Is:" + QTY);
			commonFunc.checkRefreshCellsDisappear();
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingCost(String COST) {
		commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageUnitCostTable(),
				DirectGrnPageLocators.GRNPageUnitCostInput(), COST);
		logs.logging("INFO", "Entered Cost Is:" + COST);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingCostHK(String COST) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNPageUnitCostTable(), ElementType.Xpath);
		if (flag) {
			commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageUnitCostTable(),
					DirectGrnPageLocators.GRNPageUnitCostInput(), COST);
			webDB.pressTabKey();
//			webDB.clickAnElement(DirectGrnPageLocators.GrnLineCost, ElementType.Xpath);
//			webDB.clearText(DirectGrnPageLocators.GrnLineCostInput, ElementType.Xpath);
//			webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCostInput, COST, ElementType.Xpath);
//			logs.logging("INFO", "Entered Cost Is:" + COST);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingSaveLine() {
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.GrnLineSave, ElementType.Xpath);
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForElement(DirectGrnPageLocators.OverallSave, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(DirectGrnPageLocators.OverallSave, ElementType.Xpath);
				flag = webDB.clickAnElement(DirectGrnPageLocators.OverallSave, ElementType.Xpath);
				commonFunc.checkSuccessToastMessageDisappear();
				webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "GRN Line Is Saved");
					commonFunc.checkRefreshCellsDisappear();
					closeToastIfDisplayed();
				}
			}
		}
//		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingSaveLineHK() {
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSave, ElementType.Xpath);
		if (flag) {
//			webDB.moveToElement(DirectGrnPageLocators.GrnLineSave, ElementType.Xpath);
			webDB.clickAnElement(DirectGrnPageLocators.GrnLineSave, ElementType.Xpath);
			logs.logging("INFO", "GRN Line Is Saved");
			commonFunc.checkRefreshCellsDisappear();
//			closeToastIfDisplayed();
//			closeToastIfDisplayed();
//			commonFunc.checkRefreshCellsDisappear();
//			commonFunc.hardWait(4000);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyAddingDiscount(int i1, String SheetName) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {

			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(500);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);

						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						if (flag) {
							webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
							logs.logging("INFO", "Switched To Frame");

							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsexpandButton,
									ElementType.Xpath);
							String value = webDB.getDomAttributeFromElement(
									DirectGrnPageLocators.GrnLineDetailsexpandButton, ElementType.Xpath,
									"aria-expanded");
							if (value.equalsIgnoreCase("false")) {
								webDB.javaScriptClickAnElement(DirectGrnPageLocators.GrnLineDetailsexpandButton,
										ElementType.Xpath);
								logs.logging("INFO", "Discount Button is clicked");
							}

//							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);
//							if (flag) {
//								webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);

							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
								commonFunc.hardWait(1000);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
									commonFunc.hardWait(1000);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
										webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
										webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
												ElementType.Xpath);
										logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
										commonFunc.hardWait(1000);
										flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
													ElementType.Xpath);
											logs.logging("INFO", "Details Is Saved");
											commonFunc.hardWait(1000);
											webDB.pressEscapeKey();
											webDB.switchToDefaultContent();
											commonFunc.checkRefreshCellsDisappear();
											verifyDiscountAmountForGrn(i1, SheetName);

										}
									}
								}
							}
						}
					}
				}
			}
		}
//		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The Discount Amount For PO Document
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDiscountAmountForGrn(int i2, String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i2; i <= i2; i++) {
			String ItemId = GRNMultipleline[i2][1];
			String TradeDiscountAmount = GRNMultipleline[i2][12];
			String SpecialDiscountAmount = GRNMultipleline[i2][14];
			String CashDiscountAmount = GRNMultipleline[i2][16];
			String TradeAssessbleValue = GRNMultipleline[i2][11];
			String SpecialAssessbleValue = GRNMultipleline[i2][13];
			String CashAssessbleValue = GRNMultipleline[i2][15];
			String LineAmount = GRNMultipleline[i2][18];
			String TaxAmountCGST = GRNMultipleline[i2][21];
			String TaxAmountSGST = GRNMultipleline[i2][22];
			String TaxAmountIGST = GRNMultipleline[i2][23];
			if (!ItemId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNTradeAssessableValue")), TradeAssessbleValue);
				logs.logging("INFO", "Trade Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialAssessableValue")),
						SpecialAssessbleValue);
				logs.logging("INFO", "Special Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCashAssessableValue")), CashAssessbleValue);
				logs.logging("INFO", "Cash Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNTradeDiscountAmount")), TradeDiscountAmount);
				logs.logging("INFO", "Trade Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialDiscountAmount")),
						SpecialDiscountAmount);
				logs.logging("INFO", "Special Discount Amount Matched With DB Values For This Item " + ItemId);
				commonFunc.hardWait(2000);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCashDiscountAmount")), CashDiscountAmount);
				logs.logging("INFO", "Cash Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GrnLineAmount")), LineAmount);
				logs.logging("INFO", "Line Amount" + LineAmount + "Matched With DB Values For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCgstAmount")), TaxAmountCGST);
				logs.logging("INFO", "Tax Amount CGST " + TaxAmountCGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNSgstAmount")), TaxAmountSGST);
				logs.logging("INFO", "Tax Amount SGST" + TaxAmountSGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNIgstAmount")), TaxAmountIGST);
				logs.logging("INFO", "Tax Amount IGST" + TaxAmountIGST + "For This Item" + ItemId);

			}
		}
	}

	/**
	 * This Methods Method to handle LOT_NUMBER input logic for Lot Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void inputLotNumber(String ITEM_NAME, String LOT_NUMBER) {
//		flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
//			flag = webDB.waitForElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
//			if (flag) {
//				webDB.clickAnElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		logs.logging("INFO", "Item Successfully Added");
		commonFunc.searchAndSelectValueInFilters(GrnLocators.LotSerialItemLov, ITEM_NAME);
		webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
		commonFunc.verifyAddTableSpecs(GrnLocators.LotclickInput, GrnLocators.LotNumberInput, LOT_NUMBER);
		logs.logging("INFO", "LOT_NUMBER Entered :" + LOT_NUMBER);
		logs.logging("INFO", "Lot Number Insert Successfully");
		flag = webDB.isElementEnabled(GrnLocators.SerialNumberInput, ElementType.Xpath);
		if (!flag) {
			logs.logging("info", "Verified Serial Is Disabled");
		} else {
			logs.logging("INFO", "Serial Is Enabled");
		}
		commonFunc.verifyAddTableSpecs(GrnLocators.LotSerialDate, GrnLocators.DateInput,
				commonFunc.getDataFromTestDataSheet("LotSerialDate"));
		logs.logging("INFO", "Lot Date Insert Successfully");
		flag = webDB.waitForElement(GrnLocators.LotSerialSave, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(GrnLocators.LotSerialSave, ElementType.Xpath);
			webDB.clickAnElement(GrnLocators.LotSerialSave, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			closeToastIfDisplayed();
			closeToastIfDisplayed();
		}
//			}
//		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Serial_Number input logic for Lot Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void inputSerialNumber(String ITEM_NAME, String SERIAL_NUMBER) {
		flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "item Successfully Added");
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				commonFunc.verifyAddTableSpecs(GrnLocators.SerialClickInput, GrnLocators.SerialNumberInput,
						SERIAL_NUMBER);
				logs.logging("INFO", "Serial Number Insert Successfully");
				flag = webDB.isElementEnabled(GrnLocators.LotNumberInput, ElementType.Xpath);
				if (!flag) {
					logs.logging("info", "verified lot is disabled");
				} else {
					logs.logging("INFO", "lot Is Enabled");
				}
				commonFunc.verifyAddTableSpecs(GrnLocators.LotSerialDate, GrnLocators.DateInput,
						commonFunc.getDataFromTestDataSheet("LotSerialDate"));
				logs.logging("INFO", "Serial Date Insert Successfully");
				flag = webDB.waitForElement(GrnLocators.LotSerialSave, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.LotSerialSave, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					closeToastIfDisplayed();
					closeToastIfDisplayed();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Toast Message Closed
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void closeToastIfDisplayed() {
		if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
			webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			logs.logging("INFO", "Toast Alert Successfully Catched");
		}
	}

	/**
	 * This Methods Method to handle Apportion LotSerial Quantity
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void lotSerialQtyApportion() {
		flag = webDB.waitForElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			closeToastIfDisplayed();
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForClickElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
			webDB.moveToElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
			webDB.clickAnElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			closeToastIfDisplayed();
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Deepan-GWL
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual DataBase Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual DataBase Value : " + Actual + " And Expected Values : " + Expected);
		} else {
			logs.logging("INFO",
					"Mismatch With Actual DataBase Value : " + Actual + " And Expected Values " + Expected);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithInspection() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("FreePassDirectRateSer"));
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectRateSer"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectPriceListGen"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectPriceListGen"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithInspection() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("FreePassDirectGRNPriceListSer"));
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNPriceListSer"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyGrnAddLineDetailsOtherThanUserDefined(String SheetName) throws IOException {
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
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
					verifyClickingSuppQuantity(QTY);
					verifyClickingReceiptQuantity(QTY);
					verifyClickingCost(COST);
					verifyClickingSaveLine();
					verifyUnitcostAndDiscountAmountForOpenPoItemForGrn(i, SheetName,
							commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
					verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForGrn(i, SheetName);
					otherThanUserdefinedDiscountAmountForGrn(i, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Amount For Rate Contract
	 * Item UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyUnitcostAndDiscountAmountForOpenPoItemForGrn(int i1, String SheetName,
			String RateContractSupplier) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("OpenPoUnitCost")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ITEM_NAME);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("OpenPoDiscPct")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNDiscPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ITEM_NAME);
			}
		}
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForGrn(int i1, String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Unit Cost For This Item");
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = ItemMultiple[i1][1];
			if (!ITEM_NAME.equals("")) {
				String Cost = dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
						commonFunc.getDataFromTestDataSheet("GRNUnitCost"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ITEM_NAME + "Is : " + Cost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 6, Cost);
				String TradeDiscountPct = dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
						commonFunc.getDataFromTestDataSheet("GRNDiscPct"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ITEM_NAME + "Is : " + TradeDiscountPct);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 8, TradeDiscountPct);

			}
		}

	}

	/**
	 * This Method Is Used To Entered Other Than User Defined Item To Give Discount
	 * Amount
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void otherThanUserdefinedDiscountAmountForGrn(int i1, String SheetName) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];

			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(500);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						if (flag) {
							webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
							logs.logging("INFO", "Switched To Frame");
							commonFunc.hardWait(3000);

							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsexpandButton,
									ElementType.Xpath);
							String value = webDB.getDomAttributeFromElement(
									DirectGrnPageLocators.GrnLineDetailsexpandButton, ElementType.Xpath,
									"aria-expanded");
							if (value.equalsIgnoreCase("false")) {
								webDB.javaScriptClickAnElement(DirectGrnPageLocators.GrnLineDetailsexpandButton,
										ElementType.Xpath);
								logs.logging("INFO", "Discount Button is clicked");
							}

//							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);
//							if (flag) {
//								webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								webDB.pressEnterKey();
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.hardWait(3000);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									webDB.pressEnterKey();
									commonFunc.checkRefreshCellsDisappear();
									commonFunc.hardWait(3000);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										webDB.switchToDefaultContent();
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(3000);
										verifyDiscountAmountForGrn(i1, SheetName);
										commonFunc.hardWait(4000);
										webDB.pressEscapeKey();
									}
								}

							}
						}
					}
				}
			}
		}
//		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used After Give all Details to Click overall save and Forward
	 * the Approval User
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void overAllSaveAndPost() {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			flag = webDB.waitForElement(GrnLocators.OverAllSave, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(GrnLocators.OverAllSave, ElementType.Xpath);
				webDB.clickAnElement(GrnLocators.OverAllSave, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Document Successfully Saved");
					flag = webDB.waitForClickElement(GrnLocators.PostButton, ElementType.Xpath);
					if (flag) {
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						commonFunc.checkSuccessToastMessageDisappear();
						webDB.moveToElement(GrnLocators.PostButton, ElementType.Xpath);
						webDB.clickAnElement(GrnLocators.PostButton, ElementType.Xpath);
						flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(String SheetName) throws IOException {
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
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
					verifyClickingSuppQuantity(QTY);
					verifyClickingReceiptQuantity(QTY);
					verifyClickingSaveLine();
					verifyUnitcostAndDiscountAmountForPriceListItemForGrn(i, SheetName,
							commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
					verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForGrn(i, SheetName);
					otherThanUserdefinedDiscountAmountForGrn(i, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Amount For Rate Contract
	 * Item UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyUnitcostAndDiscountAmountForPriceListItemForGrn(int i1, String SheetName,
			String PriceListSupplier) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("PriceListUnitCost")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ITEM_NAME);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("PriceListDiscPct")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNDiscPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ITEM_NAME);
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGRNGeneralRateContractDifferentStockAndSupplierUOM() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGRNMaterialRateContractDifferentStockAndSupplierUOM() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void CheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithOutQuality() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("FreePassDirectGRNUserDefined"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void CheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithQuality() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("TvsMrvApplicationControl"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("TvsMrvApplicationControl"));

		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("TvsMrvApplicationControl"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusDocumentMoveToQcOrMrvBasedOnApplicationControl();

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void VerifyToCancelMrvDocument() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		GRNFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		afterVerifyTheStatusMrvToCancelDocumentWise();
	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void afterVerifyTheStatusDocumentMoveToQcOrMrvBasedOnApplicationControl() throws IOException {
		if (VariableHandler.getGrnStatus().equals(commonFunc.getDataFromTestDataSheet("Draft"))) {
			qcFunc.InspectionDocumentWithInspectionPlan();
		}
		GRNFunc.vrifyApplcontrl();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomServiceTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnService"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnService"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsForService();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnService"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DirectGrnService"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnUserDefinedSameUomGeneralTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnUserDefinedSameUomMaterialTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnUserDefinedSameUomServiceTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnServiceWOI"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnServiceWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsForService();
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnServiceWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnServiceWOI"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnServiceWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnRateContractWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnRateContractWOI"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnServiceRateContractWOI"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnServiceRateContractWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnServiceRateContractWOI"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnServiceRateContractWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnServiceRateContractWOI"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnServiceRateContractWOI"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnPriceListWOI"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnPriceListWOI"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnServicePriceList"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnServicePriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnServicePriceList"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnServicePriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnServicePriceList"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnServicePriceList"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnService"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnService"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsForService();
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnService"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnService"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DirectGrnService"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspectionWOQc() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspectionWOQc() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspectionWOQc() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnService"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnService"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsForService();
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnService"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnService"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(webDB.getDataFromProperties("DirectGrnService"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspectionWOQc() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionWOQc() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspectionWOQc() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspectionWOQc() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("DIFUOMDirectGrnPriceList"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspectionWOQc() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("DIFUOMDirectGrnMatPriceList"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspectionWOQc() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		afterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
	}

	private void VerifyToClickGrnFrame() {
		flag = webDB.waitForElement(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame");

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The Discount Amount For PO Document
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */
	public void verifyDiscountAmountForPo(int i2, String SheetName) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i2; i <= i2; i++) {
			String ItemId = PRMultipleline[i2][1];
			String TradeDiscountAmount = PRMultipleline[i2][12];
			String SpecialDiscountAmount = PRMultipleline[i2][14];
			String CashDiscountAmount = PRMultipleline[i2][16];
			String TradeAssessbleValue = PRMultipleline[i2][11];
			String SpecialAssessbleValue = PRMultipleline[i2][13];
			String CashAssessbleValue = PRMultipleline[i2][15];
			String LineAmount = PRMultipleline[i2][18];
			String TaxAmountCGST = PRMultipleline[i2][21];
			String TaxAmountSGST = PRMultipleline[i2][22];
			String TaxAmountIGST = PRMultipleline[i2][23];
			if (!ItemId.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("TradeAssessableValue")), TradeAssessbleValue);
				logs.logging("INFO", "Trade Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("SpecialAssessableValue")), SpecialAssessbleValue);
				logs.logging("INFO", "Special Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("CashAssessableValue")), CashAssessbleValue);
				logs.logging("INFO", "Cash Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("TradeDiscountAmount")), TradeDiscountAmount);
				logs.logging("INFO", "Trade Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("SpecialDiscountAmount")), SpecialDiscountAmount);
				logs.logging("INFO", "Special Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("CashDiscountAmount")), CashDiscountAmount);
				logs.logging("INFO", "Cash Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("LineAmount")), LineAmount);
				logs.logging("INFO", "Line Amount" + LineAmount + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("POCgstAmount")), TaxAmountCGST);
				logs.logging("INFO", "Tax Amount CGST " + TaxAmountCGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PoSgstAmount")), TaxAmountSGST);
				logs.logging("INFO", "Tax Amount SGST" + TaxAmountSGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunctions.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PoIgstAmount")), TaxAmountIGST);
				logs.logging("INFO", "Tax Amount IGST" + TaxAmountIGST + "For This Item" + ItemId);

			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyAddingDiscountService(int i1) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("DirectGrnService"));
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {

			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(4000);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						logs.logging("INFO", "Switched To Frame");
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.clearText(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount,
									ElementType.Xpath);
							logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										logs.logging("INFO", "Details Is Saved");
										commonFunc.hardWait(4000);
										webDB.pressEscapeKey();
										webDB.switchToDefaultContent();
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyAddingDiscountRateContract(int i1) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("DirectGrnRateContract"));
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {

			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(4000);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						logs.logging("INFO", "Switched To Frame");
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.clearText(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount,
									ElementType.Xpath);
							logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										logs.logging("INFO", "Details Is Saved");
										commonFunc.hardWait(4000);
										webDB.pressEscapeKey();
										webDB.switchToDefaultContent();
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGrnAddLineDetailsRateContract() {

		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("DirectGrnRateContract"));
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String ITEM_NAME = GRNMultipleline[i][1];
			String HSN = GRNMultipleline[i][65];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];

			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(3000);
				verifyClickingAdd();
				verifyClickingItemField(ITEM_NAME);
				verifyClickingHsnField(HSN);
				verifyClickingSuppQuantity(QTY);
				verifyClickingReceiptQuantity(QTY);
				verifyClickingCost(COST);
				verifyClickingSaveLine();
				verifyAddingDiscountRateContract(i);
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGrnAddLineDetailsForService() {

		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("DirectGrnService"));
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String ITEM_NAME = GRNMultipleline[i][1];
			String HSN = GRNMultipleline[i][68];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];

			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(3000);
				verifyClickingAdd();
				verifyClickingItemField(ITEM_NAME);
				verifyClickingHsnField(HSN);
				verifyClickingSuppQuantity(QTY);
				verifyClickingReceiptQuantity(QTY);
				verifyClickingCost(COST);
				verifyClickingSaveLine();
				verifyAddingDiscountService(i);
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGrnAddLineDetailsForRateContract() {

		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("DirectGrnRateContract"));
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String ITEM_NAME = GRNMultipleline[i][1];
			String HSN = GRNMultipleline[i][65];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];

			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(3000);
				verifyClickingAdd();
				verifyClickingItemField(ITEM_NAME);
				verifyClickingHsnField(HSN);
				verifyClickingSuppQuantity(QTY);
				verifyClickingReceiptQuantity(QTY);
				verifyClickingCost(COST);
				verifyClickingSaveLine();
				commonFunc.hardWait(4000);
			}
		}
	}

	/**
	 * This Method to handle After Approve the Status Document Move to QC or MRV
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void afterVerifyTheStatusDocumentMoveToQcOrMrv() {
		if (VariableHandler.getGrnStatus().equals(commonFunc.getDataFromTestDataSheet("Draft"))) {
			InsDocFunc.InspectionDocumentWithInspectionPlan();
		}
		mrvFunc.verifyPostMaterialReceiptWithAndWithoutLocator();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To click Tick And Post Icon For GRN Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void clickTickAndPostIconForGrnDocument() {
		InsDocFunc.storeInspectionDocumentsNumber();
		flag = webDB.waitForElement(InspectionDocumentsLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(InspectionDocumentsLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.hardWait(2000);
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(InspectionDocumentsLocators.HeaderPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(InspectionDocumentsLocators.HeaderPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Document Number To
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApproval() {
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		logs.logging("INFO", "Ok Button Is Clicked");
		webDB.switchToDefaultContent();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Document Number To Search And Select The Inspection
	 * Document To proceed Continues Process
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void documentSearchAndSelectLovForFindPage() {
		ArrayList<String> allDocumentNumbers = VariableHandler.getAllDocumentNumber();
		for (int i = 1; i < allDocumentNumbers.size(); i++) {
			String documentNumber = allDocumentNumbers.get(i);
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionDocumentNumberLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.InspectionDocumentNumberLov,
						documentNumber);
				flag = webDB.waitForElement(InspectionDocumentsLocators.FindPageSearchBtn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				if (flag) {
					webDB.clickAnElement(InspectionDocumentsLocators.FindPageSearchBtn, ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionDocumentNumberDrillDown,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(InspectionDocumentsLocators.InspectionDocumentNumberDrillDown,
								ElementType.Xpath);
						commonFunc.hardWait(2000);
						InsDocFunc.inspectionPlanNumberCheckingInInspectionDocument();
						commonFunc.hardWait(2000);
						if (VariableHandler.getInspectionPlanNumber().equals("")) {
						} else {
							InsDocFunc.enterObservationValue();
							InsDocFunc.verifyAttributeTabClicking();
							InsDocFunc.verifyTextTabClicking();
							InsDocFunc.verifyRejectQuanityDefect(commonFunc.getDataFromTestDataSheet("Defect"));
						}
						clickTickAndPostIconForGrnDocument();
						waitingForApproval();
						commonFunc.hardWait(2000);
						commonFunc.validCommonLogin(System.getProperty("TvsUsername"),
								System.getProperty("TvsPassword"), "TvsUsername");
						commonFunc.navigateInspectionDocumentsPage();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * DataBase Validation Of Text Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Location
	 * @param ExpectedDBValue
	 */
	public void verifyAssertionGetTextValidation(String Location, String ExpectedDBValue) {
		flag = webDB.waitForElement(Location, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(Location, ElementType.Xpath);
			logs.logging("INFO", "Get Text Actual UI Value Is: " + text);
			logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
			flag = text.equals(ExpectedDBValue);
			if (flag) {
				logs.logging("INFO",
						"Verified Actual UI Value : " + text + " With Expected DataBase Value :" + ExpectedDBValue);
			} else {
				logs.logging("INFO", "Verification Failed Expected DataBase Value : " + ExpectedDBValue
						+ "Get Text Actual UI Value : " + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To PO Filter All Field Working
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreationOfGrnFilterAllFieldWorking() {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyAmendFilterSearchSOB(DirectGrnPageLocators.LocId, commonFunc.getDataFromTestDataSheet("Loc_100"),
				"Loc.ID", commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(DirectGrnPageLocators.Unit, commonFunc.getDataFromTestDataSheet("Unit_100"), "Unit",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(DirectGrnPageLocators.GRNNO, commonFunc.getDataFromTestDataSheet("GRNNumber"),
				"GRN No.", commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(DirectGrnPageLocators.SupplierNameLovButton,
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"), "Supplier ID",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(DirectGrnPageLocators.ItemId, commonFunc.getDataFromTestDataSheet("GRNItem"), "Item",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(DirectGrnPageLocators.ItemDesc, commonFunc.getDataFromTestDataSheet("GRNItem"),
				"Item Desc.", commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("SendTextInput"));
		verifyAmendFilterSearchSOB(DirectGrnPageLocators.GRN_Status,
				commonFunc.getDataFromTestDataSheet("ApproveStatus"), "Status",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("SelectType"));
//		verifyDateAfterAndBefore();
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Filter Search Purchase Order
	 * 
	 * @author Deepan-GWL
	 * @param LovButton
	 * @param Value
	 * @param i
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void verifyAmendFilterSearchSOB(String LovButton, String Value, String i, String Type, String selectflag) {
		webDB.scrollToAnElement(LovButton, ElementType.Xpath);
		if (selectflag.equals(commonFunc.getDataFromTestDataSheet("LovButton"))) {
			commonFunc.searchAndSelectValueInFilters(LovButton, Value);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SelectType"))) {
			webDB.selectDropDownByText(LovButton, Value, ElementType.Xpath);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SendTextInput"))) {
			webDB.sendTextToAnElement(LovButton, Value, ElementType.Xpath);
		}
		if (Type.equals(commonFunc.getDataFromTestDataSheet("HeaderSearchType"))) {
			clickSearchButton();
			commonFunc.hardWait(3000);
			verifyAmendTableValue(i, Value);
		} else if (Type.equals(commonFunc.getDataFromTestDataSheet("LineSearchType"))) {
			clickLineSearchButton();
			commonFunc.hardWait(3000);
			verifyLineAmendTableValue(i, Value);
		}
		clearSearchFilter();
	}

	/**
	 * Verify Date From And To
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDateAfterAndBefore() {
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNDtFrom, ElementType.Xpath);
		webDB.sendTextToAnElement(DirectGrnPageLocators.GRNDtFrom, commonFunc.getDataFromTestDataSheet("EffFrom"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueAfterDate(8, commonFunc.getDataFromTestDataSheet("EffFrom"));
		clearSearchFilter();
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNDtTo, ElementType.Xpath);
		webDB.sendTextToAnElement(DirectGrnPageLocators.GRNDtTo, commonFunc.getDataFromTestDataSheet("EffTo"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueBeforeDate(8, commonFunc.getDataFromTestDataSheet("EffTo"));
		clearSearchFilter();
//		flag = webDB.waitForElement(PurchaseOrderPageLocators.RequestDtFrom, ElementType.Xpath);
//		webDB.sendTextToAnElement(PurchaseOrderPageLocators.RequestDtFrom,
//				commonFunc.getDataFromTestDataSheet("EffFrom"), ElementType.Xpath);
//		clickLineSearchButton();
//		commonFunc.hardWait(3000);
//		verifyLineAmendTableValueAfterDate(23, commonFunc.getDataFromTestDataSheet("EffFrom"));
//		clearSearchFilter();
//		flag = webDB.waitForElement(PurchaseOrderPageLocators.RequestDtTo, ElementType.Xpath);
//		webDB.sendTextToAnElement(PurchaseOrderPageLocators.RequestDtTo, commonFunc.getDataFromTestDataSheet("EffTo"),
//				ElementType.Xpath);
//		clickLineSearchButton();
//		commonFunc.hardWait(3000);
//		verifyLineAmendTableValueBeforeDate(23, commonFunc.getDataFromTestDataSheet("EffTo"));
//		clearSearchFilter();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(DirectGrnPageLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header CheckBox Is Displayed");
			webDB.clickAnElement(DirectGrnPageLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(DirectGrnPageLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(DirectGrnPageLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.AmendFindTable, ElementType.Xpath);

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickLineSearchButton() {
		flag = webDB.waitForElement(DirectGrnPageLocators.LineCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line CheckBox Is Displayed");
			webDB.clickAnElement(DirectGrnPageLocators.LineCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(DirectGrnPageLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(DirectGrnPageLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.AmendLineFindTable, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyAmendTableValue(String i, String Value) {

		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.GRNAmendFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				flag = element.getText().contains(Value);
//				System.out.println("Test A");
//				if (!flag) {
//					System.out.println("Test B");
//					break;
//				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyLineAmendTableValue(String i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.GRNAmendLineFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				flag = element.getText().contains(Value);
				if (!flag) {
					break;

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Clear Search Filter
	 * 
	 * @author Deepan-GWL
	 */
	public void clearSearchFilter() {
		commonFunc.hardWait(1000);
		System.out.println("Test2");
		flag = webDB.waitForElement(DirectGrnPageLocators.ClearButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.ClearButton, ElementType.Xpath);
			logs.logging("INFO", "Clear Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyAmendTableValueAfterDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.AmendFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isAfter(date2);
				if (!flag) {
					if (date1.equals(date2)) {
					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyAmendTableValueBeforeDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.AmendFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isBefore(date2);
				if (!flag) {
					if (date1.equals(date2)) {

					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyLineAmendTableValueAfterDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.AmendLineFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isAfter(date2);
				if (!flag) {
					if (date1.equals(date2)) {
					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyLineAmendTableValueBeforeDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.AmendLineFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isBefore(date2);
				if (!flag) {
					if (date1.equals(date2)) {

					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnGenUD"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnGenUD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnGenUD"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnGenUD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnGenUD"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnGenUD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(String Supplier, String SheetName,
			String FileName) throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWhHK(SheetName, FileName);
		verifyDirectGRNStockQtyAndCostForSourceWHHK(SheetName, FileName);
//		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("DuraiUsername"));
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), Supplier,
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsHK(SheetName, FileName);
		clickGenLotSerialButtonInHeader();
//		DirectGRNmultipleLotandSerialWithGeneralType(SheetName);
		VariableHandler.setMultipleGRNDocs(MultiGRNDocs);
		GRNFunc.clickPostButton();
		GRNFunc.waitingForApprovalHK(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWHHK(SheetName, FileName);
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		commonFunc.hardWait(2000);
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(String Supplier, String SheetName,
			String Location, String FileName) throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWhHK(SheetName, FileName);
		verifyDirectGRNStockQtyAndCostForSourceWHHK(SheetName, FileName);
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				System.getProperty("DuraiUsername"));
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(Location, commonFunc.getDataFromTestDataSheet("GRNPG"), Supplier,
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsHK(SheetName, FileName);
		clickGenLotSerialButtonInHeader();
//		DirectGRNmultipleLotandSerialWithGeneralType(SheetName);
		VariableHandler.setMultipleGRNDocs(MultiGRNDocs);
		GRNFunc.clickPostButton();
		GRNFunc.waitingForApprovalHK(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWHHK(SheetName, FileName);
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		commonFunc.hardWait(2000);
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(String Supplier, String SheetName,
			String FileName) throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWhHK(SheetName, FileName);
		verifyDirectGRNStockQtyAndCostForSourceWHHK(SheetName, FileName);
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				System.getProperty("DuraiUsername"));
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), Supplier,
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsHK(SheetName, FileName);
		clickGenLotSerialButtonInHeader();
		clickImportLRButton();
		enterBOEAndBOD(commonFunc.getDataFromTestDataSheet("SupplierBillNumber"), Today);
//		DirectGRNmultipleLotandSerialWithGeneralType(SheetName);
		VariableHandler.setMultipleGRNDocs(MultiGRNDocs);
		GRNFunc.clickPostButton();
		GRNFunc.waitingForApprovalHK(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(SheetName);
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		commonFunc.hardWait(2000);
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnMatUDD"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnMatUDD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnMatUDD"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnMatUDD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnMatUDD"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnMatUDD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomServiceTypeWithoutInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsForService();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnRateContract"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnRateContract"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnRateContract"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnRateContract"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnRateContract"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnRateContract"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnRateContract"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnRateContract"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnServiceRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnPriceList"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnPriceList"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnPriceList"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnPriceList"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnPriceList"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnPriceList"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnPriceList"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnPriceList"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DirectGrnServicePriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnUserDefinedSameUomGeneralTypeWithoutInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnGenUDWOI"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnGenUDWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnUserDefinedSameUomMaterialTypeWithoutInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnMatUDDWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnUserDefinedSameUomServiceTypeWithoutInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsForService();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithoutInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnRateContractWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithoutInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnRateContractWOI"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnRateContractWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithoutInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(
				webDB.getDataFromProperties("DirectGrnServiceRateContractWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithoutInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnPriceListWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithoutInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnPriceListWOI"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnPriceListWOI"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithoutInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DirectGrnServicePriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIfUOMDirectMatUD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsForService();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspectionD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspectionWOQcD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIfUOMDirectGrnGenUD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspectionWOQcD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIfUOMDirectMatUD"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIfUOMDirectMatUD"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspectionWOQcD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsForService();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspectionWOQcD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnRateContract"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionWOQcD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatRateContract"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspectionWOQcD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerRateContract"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspectionWOQcD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnPriceList"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspectionWOQcD() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DIFUOMDirectGrnMatPriceList"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspectionWOQcD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("DIFUOMDirectGrnSerPriceList"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyDirectGRNAddLineDetailsOtherThanUserDefined(String SheetName) throws IOException {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "DirectGrnDetails.xlsx";
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 22; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][68];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(3000);
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
//					verifyClickingSuppQuantity(QTY);
//					verifyClickingReceiptQuantity(QTY);
//					verifyClickingCost(COST);
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageSupQtyTable(),
							DirectGrnPageLocators.GrnPageSupQtyInput(), QTY);
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageRcptQtyTable(),
							DirectGrnPageLocators.GRNPageRcptQtyInput(), QTY);
					commonFunc.checkRefreshCellsDisappear();
					verifyClickingSaveLine();
					verifyUnitcostAndDiscountAmountForOpenPoItemForDirectGRN(i, SheetName,
							commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
					verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForDirectGrn(i, SheetName);
					otherThanUserdefinedDiscountAmountForDirectGRN(i, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Amount For Rate Contract
	 * Item UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyUnitcostAndDiscountAmountForOpenPoItemForDirectGRN(int i1, String SheetName,
			String RateContractSupplier) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("OpenPoUnitCost")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ITEM_NAME);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("OpenPoDiscPct")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNDiscPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ITEM_NAME);
			}
		}
	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Quality
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectGRNExpectedStockQtAndCostyForQualityWH(String SheetName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String WareHouse = ItemDetails[i][44];
			String AfterExcelStockCost = ItemDetails[i][51];
			if (!ItemId.equals("")) {
				String AfterStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After GRN Actual Stock Cost For This Item " + ItemId + "Is : " + AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO",
						"Verified Expected Stock Cost For This Item " + ItemId + " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Quality
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectGRNExpectedStockQtAndCostyForQualityWHHK(String SheetName, String FileName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String WareHouse = ItemDetails[i][44];
			String AfterExcelStockCost = ItemDetails[i][51];
			if (!ItemId.equals("")) {
				String AfterStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After GRN Actual Stock Cost For This Item " + ItemId + "Is : " + AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO",
						"Verified Expected Stock Cost For This Item " + ItemId + " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Source
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(String SheetName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String SourecWareHouse = ItemDetails[i][63];
			String AfterExcelStockQuantity = ItemDetails[i][67];
			String AfterExcelStockCost = ItemDetails[i][66];
			if (!ItemId.equals("")) {

				String AfterStockQty = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockQty);

				verifyAssertionBothValues(AfterStockQty, AfterExcelStockQuantity);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Qty For This Item " + ItemId
						+ " With Database Values After MRV");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				String AfterStockCost = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Cost For This Item " + ItemId
						+ " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods Used To Verify stock Quantity For Quality Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyDirectGRNStockQtyAndCostForQualityWh(String SheetName) throws IOException {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String WareHouse = ItemMultiple[i][44];
			String ExpectedStockQuantity = ItemMultiple[i][50];
			String ExpectedStockCost = ItemMultiple[i][51];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO",
						"Expected InspectionWH Stock Qty For This Item " + ItemId + "Is : " + ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected InspectionWH Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 46, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 47, BeforeStockCost);
			}
		}
	}

	/**
	 * This Methods Used To Verify stock Quantity For Quality Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyDirectGRNStockQtyAndCostForQualityWhHK(String SheetName, String FileName) throws IOException {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String WareHouse = ItemMultiple[i][44];
			String ExpectedStockQuantity = ItemMultiple[i][50];
			String ExpectedStockCost = ItemMultiple[i][51];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO",
						"Expected InspectionWH Stock Qty For This Item " + ItemId + "Is : " + ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected InspectionWH Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 46, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 47, BeforeStockCost);
			}
		}
	}

	/**
	 * This Methods Used To Verify stock Quantity For Source Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectGRNStockQtyAndCostForSourceWH(String SheetName) throws IOException {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String SourecWareHouse = ItemMultiple[i][63];
			String ExpectedStockQuantity = ItemMultiple[i][67];
			String ExpectedStockCost = ItemMultiple[i][66];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO", "Expected Source WareHouse Stock Qty For This Item " + ItemId + "Is : "
						+ ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 65, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 64, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Used To Verify stock Quantity For Source Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectGRNStockQtyAndCostForSourceWHHK(String SheetName, String FileName) throws IOException {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String SourecWareHouse = ItemMultiple[i][63];
			String ExpectedStockQuantity = ItemMultiple[i][67];
			String ExpectedStockCost = ItemMultiple[i][66];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO", "Expected Source WareHouse Stock Qty For This Item " + ItemId + "Is : "
						+ ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 65, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 64, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(String SheetName) throws IOException {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "DirectGrnDetails.xlsx";
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 22; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][68];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(3000);
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
					verifyClickingSuppQuantity(QTY);
					verifyClickingReceiptQuantity(QTY);
					verifyClickingCost(COST);
					verifyClickingSaveLine();
					verifyUnitcostAndDiscountAmountForPriceListItemForDirectGRNWithLoc49(i, SheetName,
							commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
					verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForDirectGrn(i, SheetName);
					otherThanUserdefinedDiscountAmountForDirectGRN(i, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Amount For Rate Contract
	 * Item UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyUnitcostAndDiscountAmountForPriceListItemForDirectGRN(int i1, String SheetName,
			String PriceListSupplier) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("PriceListUnitCost")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ITEM_NAME);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("PriceListDiscPct")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNDiscPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ITEM_NAME);
			}
		}
	}

	/**
	 * This Method Is Used To Entered Other Than User Defined Item To Give Discount
	 * Amount
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	private void otherThanUserdefinedDiscountAmountForDirectGRN(int i1, String SheetName) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];

			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
//				String Type = webDB.getTextFromElement(PurchaseOrderPageLocators.POType, ElementType.Xpath);
//				logs.logging("INFO", "PO Type is:" + Type);
//				if (Type.equals(commonFunc.getDataFromTestDataSheet("ServiceType"))) {
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(2000);
//					flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderLineDetailForServiceItem(i - 21),
//							ElementType.Xpath);
//					webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderLineDetailForServiceItem(i - 21),
//							ElementType.Xpath);
//					logs.logging("INFO", "Service Item");
//				} else {
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(2000);
//					flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i - 21),
//							ElementType.Xpath);
//					webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i - 21), ElementType.Xpath);
//					logs.logging("INFO", "Non Service Item");
//				}
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(4000);

					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);
					if (flag) {
						webDB.javaScriptClickAnElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);
						webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);

						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						if (flag) {
							webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
							logs.logging("INFO", "Switched To Frame");
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsexpandButton,
									ElementType.Xpath);
							String value = webDB.getDomAttributeFromElement(
									DirectGrnPageLocators.GrnLineDetailsexpandButton, ElementType.Xpath,
									"aria-expanded");
							if (value.equalsIgnoreCase("false")) {
								webDB.javaScriptClickAnElement(DirectGrnPageLocators.GrnLineDetailsexpandButton,
										ElementType.Xpath);
								logs.logging("INFO", "Discount Button is clicked");
							}
//						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);
//						if (flag) {
//							webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);

							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								webDB.pressEnterKey();
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.hardWait(3000);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									webDB.pressEnterKey();
									commonFunc.checkRefreshCellsDisappear();
									commonFunc.hardWait(3000);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										webDB.switchToDefaultContent();
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(3000);
										verifyDiscountAmountForDirectGRN(i1, SheetName);
										commonFunc.hardWait(4000);
										webDB.pressEscapeKey();
									}
								}

							}
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify The Discount Amount For PO Document
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDiscountAmountForDirectGRN(int i2, String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i2; i <= i2; i++) {
			String ItemId = GRNMultipleline[i2][1];
			String TradeDiscountAmount = GRNMultipleline[i2][12];
			String SpecialDiscountAmount = GRNMultipleline[i2][14];
			String CashDiscountAmount = GRNMultipleline[i2][16];
			String TradeAssessbleValue = GRNMultipleline[i2][11];
			String SpecialAssessbleValue = GRNMultipleline[i2][13];
			String CashAssessbleValue = GRNMultipleline[i2][15];
			String LineAmount = GRNMultipleline[i2][18];
			String TaxAmountCGST = GRNMultipleline[i2][21];
			String TaxAmountSGST = GRNMultipleline[i2][22];
			String TaxAmountIGST = GRNMultipleline[i2][23];
			if (!ItemId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNTradeAssessableValue")), TradeAssessbleValue);
				logs.logging("INFO", "Trade Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialAssessableValue")),
						SpecialAssessbleValue);
				logs.logging("INFO", "Special Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCashAssessableValue")), CashAssessbleValue);
				logs.logging("INFO", "Cash Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNTradeDiscountAmount")), TradeDiscountAmount);
				logs.logging("INFO", "Trade Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialDiscountAmount")),
						SpecialDiscountAmount);
				logs.logging("INFO", "Special Discount Amount Matched With DB Values For This Item " + ItemId);
				commonFunc.hardWait(2000);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCashDiscountAmount")), CashDiscountAmount);
				logs.logging("INFO", "Cash Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GrnLineAmount")), LineAmount);
				logs.logging("INFO", "Line Amount" + LineAmount + "Matched With DB Values For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCgstAmount")), TaxAmountCGST);
				logs.logging("INFO", "Tax Amount CGST " + TaxAmountCGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNSgstAmount")), TaxAmountSGST);
				logs.logging("INFO", "Tax Amount SGST" + TaxAmountSGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNIgstAmount")), TaxAmountIGST);
				logs.logging("INFO", "Tax Amount IGST" + TaxAmountIGST + "For This Item" + ItemId);

			}
		}
	}

	/**
	 * This Method Is Used To Verify The Discount Amount For PO Document
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDiscountAmountForDirectGRNHK(int i2, String SheetName, String FileName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i2; i <= i2; i++) {
			String ItemId = GRNMultipleline[i2][1];
			String TradeDiscountAmount = GRNMultipleline[i2][12];
			String SpecialDiscountAmount = GRNMultipleline[i2][14];
			String CashDiscountAmount = GRNMultipleline[i2][16];
			String TradeAssessbleValue = GRNMultipleline[i2][11];
			String SpecialAssessbleValue = GRNMultipleline[i2][13];
			String CashAssessbleValue = GRNMultipleline[i2][15];
			String LineAmount = GRNMultipleline[i2][18];
			String TaxAmountCGST = GRNMultipleline[i2][21];
			String TaxAmountSGST = GRNMultipleline[i2][22];
			String TaxAmountIGST = GRNMultipleline[i2][23];
			if (!ItemId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNTradeAssessableValue")), TradeAssessbleValue);
				logs.logging("INFO", "Trade Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialAssessableValue")),
						SpecialAssessbleValue);
				logs.logging("INFO", "Special Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCashAssessableValue")), CashAssessbleValue);
				logs.logging("INFO", "Cash Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNTradeDiscountAmount")), TradeDiscountAmount);
				logs.logging("INFO", "Trade Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialDiscountAmount")),
						SpecialDiscountAmount);
				logs.logging("INFO", "Special Discount Amount Matched With DB Values For This Item " + ItemId);
				commonFunc.hardWait(2000);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCashDiscountAmount")), CashDiscountAmount);
				logs.logging("INFO", "Cash Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GrnLineAmount")), LineAmount);
				logs.logging("INFO", "Line Amount" + LineAmount + "Matched With DB Values For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCgstAmount")), TaxAmountCGST);
				logs.logging("INFO", "Tax Amount CGST " + TaxAmountCGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNSgstAmount")), TaxAmountSGST);
				logs.logging("INFO", "Tax Amount SGST" + TaxAmountSGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNIgstAmount")), TaxAmountIGST);
				logs.logging("INFO", "Tax Amount IGST" + TaxAmountIGST + "For This Item" + ItemId);

			}
		}
	}

	/**
	 * This Methods Method to handle Multiple Lot serial Item for General Type
	 * Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void DirectGRNmultipleLotandSerialWithGeneralType(String SheetName) {
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
						+ "DirectGrnDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
				for (int i = 22; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][1];
					String LOT_SERIAL = itemmultiple[i][54];
					String LOT_NUMBER = itemmultiple[i][55];
					String SERIAL_NUMBER = itemmultiple[i][56];
					System.out.println(itemmultiple.length);
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
//							commonFunc.checkRefreshCellsDisappear();
//							commonFunc.hardWait(3000);
//							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
//							if (flag) {
//								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
//								webDB.switchToDefaultContent();
//								System.out.println("Nit");
//								flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
//								List<WebElement> removeFilter = webDB.getDriver()
//										.findElements(By.xpath(GrnLocators.LotserialitemsInput));
//								for (WebElement element : removeFilter) {
//									if (element.isDisplayed()) {
//										element.sendKeys(ITEM_NAME);
//										System.out.println(1);
//										break;
//									}
//								}
//								webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
//							}
						}
					}

					if (flag) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
							inputLotNumber(ITEM_NAME, LOT_NUMBER);

						} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {

							inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
						}

					}
				}
				lotSerialQtyApportion();

			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyDirectGRNAddLineDetailsForService() {

		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("DirectGrnService"));
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String ITEM_NAME = GRNMultipleline[i][1];
			String HSN = GRNMultipleline[i][68];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];

			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(3000);
				verifyClickingAdd();
				verifyClickingItemField(ITEM_NAME);
				verifyClickingHsnField(HSN);
				verifyClickingSuppQuantity(QTY);
				verifyClickingReceiptQuantity(QTY);
				verifyClickingCost(COST);
				verifyClickingSaveLine();
				verifyDirectGRNAddingDiscountService(i);
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyDirectGRNAddingDiscountService(int i1) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("DirectGrnService"));
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {

			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(4000);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						logs.logging("INFO", "Switched To Frame");
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.clearText(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount,
									ElementType.Xpath);
							logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										logs.logging("INFO", "Details Is Saved");
										commonFunc.hardWait(4000);
										webDB.pressEscapeKey();
										webDB.switchToDefaultContent();
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyDirectGRNAddLineDetails(String SheetName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(200);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			MultiGRNDocs.add(VariableHandler.getGrnDocNumber());// TODO

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();

			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "DirectGrnDetails.xlsx";
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 22; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][68];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(200);
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
					verifyClickingSuppQuantity(QTY);
					verifyClickingReceiptQuantity(QTY);
					verifyClickingCost(COST);
					verifyClickingSaveLine();

					verifyDirectGRNAddingDiscount(i, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyDirectGRNAddLineDetailsHK(String SheetName, String FileName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(200);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			MultiGRNDocs.add(VariableHandler.getGrnDocNumber());// TODO

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();

			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 22; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][68];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
//					commonFunc.hardWait(200);
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
					verifyClickingSuppQuantityHK(QTY);
					verifyClickingReceiptQuantityHK(QTY);
					verifyClickingCostHK(COST);
					verifyClickingSaveLineHK();
					verifyDirectGRNAddingDiscountHK(i, SheetName, FileName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyDirectGRNAddingDiscount(int i1, String SheetName) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {

			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(500);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);
					if (flag) {
						webDB.javaScriptClickAnElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);
						webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);

						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						if (flag) {
							webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
							logs.logging("INFO", "Switched To Frame");

							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsexpandButton,
									ElementType.Xpath);
							String value = webDB.getDomAttributeFromElement(
									DirectGrnPageLocators.GrnLineDetailsexpandButton, ElementType.Xpath,
									"aria-expanded");
							if (value.equalsIgnoreCase("false")) {
								webDB.javaScriptClickAnElement(DirectGrnPageLocators.GrnLineDetailsexpandButton,
										ElementType.Xpath);
								logs.logging("INFO", "Discount Button is clicked");
							}

//						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);
//						if (flag) {
//							webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);

							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
								commonFunc.hardWait(1000);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
									commonFunc.hardWait(1000);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
										webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
										webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
												ElementType.Xpath);
										logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
										commonFunc.hardWait(1000);
										flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
													ElementType.Xpath);
											logs.logging("INFO", "Details Is Saved");
											commonFunc.hardWait(1000);
											webDB.pressEscapeKey();
											webDB.switchToDefaultContent();
											commonFunc.checkRefreshCellsDisappear();
											verifyDiscountAmountForDirectGRN(i1, SheetName);

										}
									}
								}
							}
						}
					}
				}
			}
		}
//		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyDirectGRNAddingDiscountHK(int i1, String SheetName, String FileName) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {

			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
//				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(200);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						logs.logging("INFO", "Switched To Frame");
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.clearText(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount,
									ElementType.Xpath);
							logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
							commonFunc.hardWait(200);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
								commonFunc.hardWait(200);
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
									commonFunc.hardWait(500);
									commonFunc.checkRefreshCellsDisappear();
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										logs.logging("INFO", "Details Is Saved");
										commonFunc.hardWait(800);
										webDB.pressEscapeKey();
										webDB.switchToDefaultContent();
										commonFunc.checkRefreshCellsDisappear();
										verifyDiscountAmountForDirectGRNHK(i1, SheetName, FileName);

									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForDirectGrn(int i1, String SheetName)
			throws IOException {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Unit Cost For This Item");
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = ItemMultiple[i1][1];
			if (!ITEM_NAME.equals("")) {
				String Cost = dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
						commonFunc.getDataFromTestDataSheet("GRNUnitCost"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ITEM_NAME + "Is : " + Cost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 6, Cost);
				String TradeDiscountPct = dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
						commonFunc.getDataFromTestDataSheet("GRNDiscPct"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ITEM_NAME + "Is : " + TradeDiscountPct);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 8, TradeDiscountPct);

			}
		}
	}

	/**
	 * This Method Is Used To Verify Generate Lot Serial Button Excel UOM(General)
	 * 
	 * @author Durai.K-RM ITS
	 * 
	 */
	public void clickGenLotSerialButtonInHeader() {
		flag = webDB.waitForElement(GrnLocators.OverAllSave, ElementType.Xpath);
		if (flag)
			webDB.clickAnElement(GrnLocators.OverAllSave, ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		logs.logging("INFO", "Document Successfully Saved");
		flag = webDB.waitForElement(DirectGrnPageLocators.GRN_LotSerialButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.GRN_LotSerialButton, ElementType.Xpath);
			commonFunc.hardWait(500);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(GrnLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Generate Lot Serial Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Import LR Button Excel UOM(General)
	 * 
	 * @author Durai.K-RM ITS
	 * 
	 */
	public void clickImportLRButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(DirectGrnPageLocators.ImportLRButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.ImportLRButton, ElementType.Xpath);
			flag = webDB.waitForElement(DirectGrnPageLocators.BOEInputField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Import L/R Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Enter BOE and BOD Excel UOM(General)
	 * 
	 * @author Durai.K-RM ITS
	 * 
	 */
	public void enterBOEAndBOD(String BillNo, String BOEDate) {
		String SupBillNo = RandomStringUtils.randomNumeric(6);
		if (SupBillNo.equals("000")) {
			SupBillNo = "10";
		}
		String BOENumber = BillNo + SupBillNo;
		flag = webDB.waitForElement(DirectGrnPageLocators.BOEInputField, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(DirectGrnPageLocators.BOEInputField, ElementType.Xpath);
			webDB.sendTextToAnElement(DirectGrnPageLocators.BOEInputField, BOENumber, ElementType.Xpath);
			flag = webDB.waitForElement(DirectGrnPageLocators.BOEDateInputField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "BOE Number Is Entered Sucessfully");
				webDB.clearTextField(DirectGrnPageLocators.BOEDateInputField, ElementType.Xpath);
				webDB.sendTextToAnElement(DirectGrnPageLocators.BOEDateInputField, BOEDate, ElementType.Xpath);
				webDB.pressTabKey();
				flag = webDB.waitForElement(DirectGrnPageLocators.BOEOkButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Bill Entry Date Is Inserted Successfully");
					webDB.clickAnElement(DirectGrnPageLocators.BOEOkButton, ElementType.Xpath);
					flag = webDB.waitForElement(DirectGrnPageLocators.GRN_LotSerialButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Ok Button Is Clicked Sucessfully");
					}
				}
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("PurchaseBillGRN"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillImportSupplier()
			throws IOException {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		GRNFunc2.clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRNImport"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillNonResidentImportSupplier()
			throws IOException {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedNonResident"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		GRNFunc2.clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRNImport"));
	}

	/**
	 * This Methods Used To Verify stock Quantity For Quality Warehouse
	 * Document(Single Item)
	 * 
	 * @author Deepan-GWL
	 */

	public void verifyDirectGRNStockQtyAndCostForQualityWh(String FileName, String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 3; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String WareHouse = ItemMultiple[i][44];
			String ExpectedStockQuantity = ItemMultiple[i][50];
			String ExpectedStockCost = ItemMultiple[i][51];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunc.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO",
						"Expected InspectionWH Stock Qty For This Item " + ItemId + "Is : " + ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunc.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before InspectionWH Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected InspectionWH Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 46, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 47, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Used To Verify stock Quantity For Source Warehouse
	 * Document(Single Item)
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectGRNStockQtyAndCostForSourceWH(String FileName, String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Before Stock Qty For This Item ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			String SourecWareHouse = ItemMultiple[i][63];
			String ExpectedStockQuantity = ItemMultiple[i][67];
			String ExpectedStockCost = ItemMultiple[i][66];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunc.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				logs.logging("INFO", "Expected Source WareHouse Stock Qty For This Item " + ItemId + "Is : "
						+ ExpectedStockQuantity);
				String BeforeStockCost = dbCommonFunc.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO",
						"Before Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
				logs.logging("INFO",
						"Expected Source WareHouse Stock Cost For This Item " + ItemId + "Is : " + ExpectedStockCost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 65, BeforeStockQty);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 64, BeforeStockCost);
			}
		}

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate() throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("InterstateSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("UnRegisteredSupplierID"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("PurchaseBillGRN"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		String GrnHeaderWiseStatus = dbCommonFunctions.GrnHeaderDetails(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("ProcessFlowStatus"));
		logs.logging("INFO", "GRN Document ProcessFlow Status Is " + GrnHeaderWiseStatus);
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
//		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("DGSUomWISheet"),
//				webDB.getDataFromProperties("DirectGrnSheet"));
//		GRNFunc.lotSerialQtyApportion();
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWISheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyDirectGRNAddLineDetails(String FileName, String SheetName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(200);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			MultiGRNDocs.add(VariableHandler.getGrnDocNumber());// TODO

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 3; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][19];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(200);
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
					verifyClickingSuppQuantity(QTY);
					verifyClickingReceiptQuantity(QTY);
					verifyClickingCost(COST);
					verifyClickingSaveLine();
					verifyDirectGRNAddingDiscount(i, FileName, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyDirectGRNAddingDiscount(int i1, String FileName, String SheetName) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 2), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 2), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(500);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);

						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						if (flag) {
							webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
							logs.logging("INFO", "Switched To Frame");

							if (webDB.getAttributeFromElement(DirectGrnPageLocators.GrnLineDetDiscount,
									ElementType.Xpath, "aria-expanded").equals("false")) {
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetDiscount,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);
								}
							}
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
								commonFunc.hardWait(1000);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
									commonFunc.hardWait(1000);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
										webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
										webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
												ElementType.Xpath);
										logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
										commonFunc.hardWait(1000);
										flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
													ElementType.Xpath);
											logs.logging("INFO", "Details Is Saved");
											commonFunc.hardWait(1000);
											webDB.pressEscapeKey();
											webDB.switchToDefaultContent();
											commonFunc.checkRefreshCellsDisappear();
											verifyDiscountAmountForDirectGRN(i1, FileName, SheetName);

										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The Discount Amount For PO Document
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDiscountAmountForDirectGRN(int i2, String FileName, String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i2; i <= i2; i++) {
			String ItemId = GRNMultipleline[i2][1];
			String TradeDiscountAmount = GRNMultipleline[i2][12];
			String SpecialDiscountAmount = GRNMultipleline[i2][14];
			String CashDiscountAmount = GRNMultipleline[i2][16];
			String TradeAssessbleValue = GRNMultipleline[i2][11];
			String SpecialAssessbleValue = GRNMultipleline[i2][13];
			String CashAssessbleValue = GRNMultipleline[i2][15];
			String LineAmount = GRNMultipleline[i2][18];
			String TaxAmountCGST = GRNMultipleline[i2][21];
			String TaxAmountSGST = GRNMultipleline[i2][22];
			String TaxAmountIGST = GRNMultipleline[i2][23];
			if (!ItemId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNTradeAssessableValue")), TradeAssessbleValue);
				logs.logging("INFO", "Trade Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialAssessableValue")),
						SpecialAssessbleValue);
				logs.logging("INFO", "Special Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCashAssessableValue")), CashAssessbleValue);
				logs.logging("INFO", "Cash Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNTradeDiscountAmount")), TradeDiscountAmount);
				logs.logging("INFO", "Trade Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialDiscountAmount")),
						SpecialDiscountAmount);
				logs.logging("INFO", "Special Discount Amount Matched With DB Values For This Item " + ItemId);
				commonFunc.hardWait(2000);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCashDiscountAmount")), CashDiscountAmount);
				logs.logging("INFO", "Cash Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GrnLineAmount")), LineAmount);
				logs.logging("INFO", "Line Amount" + LineAmount + "Matched With DB Values For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNCgstAmount")), TaxAmountCGST);
				logs.logging("INFO", "Tax Amount CGST " + TaxAmountCGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNSgstAmount")), TaxAmountSGST);
				logs.logging("INFO", "Tax Amount SGST" + TaxAmountSGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("GRNIgstAmount")), TaxAmountIGST);
				logs.logging("INFO", "Tax Amount IGST" + TaxAmountIGST + "For This Item" + ItemId);

			}
		}
	}

	/**
	 * This Methods Method to handle Multiple Lot serial Item for General Type
	 * Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void DirectGRNmultipleLotandSerialWithGeneralType(String FileName, String SheetName) {
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
						+ FileName;
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
				for (int i = 3; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][1];
					String LOT_SERIAL = itemmultiple[i][54];
					String LOT_NUMBER = itemmultiple[i][55];
					String SERIAL_NUMBER = itemmultiple[i][56];
					System.out.println("Length" + itemmultiple.length);
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
//						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
							commonFunc.hardWait(2000);
							flag = webDB.waitForElement(GrnLocators.ItemTable, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.ItemTable, ElementType.Xpath);
//							commonFunc.searchAndSelectValueInFilters(GrnLocators.LotSerialItemLov, ITEM_NAME);

//							commonFunc.checkRefreshCellsDisappear();
//							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
//							if (flag) {
//								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
//								webDB.switchToDefaultContent();
//								System.out.println("Nit");
//								flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
//								List<WebElement> removeFilter = webDB.getDriver()
//										.findElements(By.xpath(GrnLocators.LotserialitemsInput));
//								for (WebElement element : removeFilter) {
//									if (element.isDisplayed()) {
//										element.sendKeys(ITEM_NAME);
//										System.out.println(1);
//										break;
//									}
//								}
//								webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
							}
//							}
						}
					} else {
						break;
					}

					if (flag) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Lot"))) {
							inputLotNumber(ITEM_NAME, LOT_NUMBER);

						} else if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Serial"))) {

							inputSerialNumber(ITEM_NAME, SERIAL_NUMBER);
						}

					}
					flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
					}
				}
				lotSerialQtyApportion();

			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Quality
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectGRNExpectedStockQtAndCostyForQualityWH(String FileName, String SheetName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String WareHouse = ItemDetails[i][44];
			String AfterExcelStockCost = ItemDetails[i][51];
			if (!ItemId.equals("")) {
				String AfterStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After GRN Actual Stock Cost For This Item " + ItemId + "Is : " + AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO",
						"Verified Expected Stock Cost For This Item " + ItemId + " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods Is Used To verify Expected stock Qty And Cost For Source
	 * WareHouse
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(String FileName,
			String SheetName) {
		String[][] ItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < ItemDetails.length; i++) {
			String ItemId = ItemDetails[i][1];
			String SourecWareHouse = ItemDetails[i][63];
			String AfterExcelStockQuantity = ItemDetails[i][67];
			String AfterExcelStockCost = ItemDetails[i][66];
			if (!ItemId.equals("")) {

				String AfterStockQty = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockQty);

				verifyAssertionBothValues(AfterStockQty, AfterExcelStockQuantity);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Qty For This Item " + ItemId
						+ " With Database Values After MRV");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				String AfterStockCost = dbCommonFunctions.StockQuery1(SourecWareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
				logs.logging("INFO", "After MRV Source WareHouse Actual Stock Qty For This Item " + ItemId + "Is : "
						+ AfterStockCost);

				verifyAssertionBothValues(AfterStockCost, AfterExcelStockCost);
				logs.logging("INFO", "Verified Expected Source WareHouse Stock Cost For This Item " + ItemId
						+ " With Database Values After MRV");
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomMaterialTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
//		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("DGSUomWISheet"),
//				webDB.getDataFromProperties("DirectGrnSheet"));
//		GRNFunc.lotSerialQtyApportion();
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWISheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomRateContractGeneralTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L3"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyDirectGRNAddLineDetailsOtherThanUserDefined(String FileName, String SheetName, String Supplier,
			String UnitId) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 3; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][19];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(3000);
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
//					verifyClickingSuppQuantity(QTY);
//					verifyClickingReceiptQuantity(QTY);
//					verifyClickingCost(COST);
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageSupQtyTable(),
							DirectGrnPageLocators.GrnPageSupQtyInput(), QTY);
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageRcptQtyTable(),
							DirectGrnPageLocators.GRNPageRcptQtyInput(), QTY);
					verifyClickingSaveLine();
					verifyUnitcostAndDiscountAmountForOpenPoItemForDirectGRN(i, FileName, SheetName, Supplier, UnitId);
					verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForDirectGrn(i, FileName,
							SheetName);
					otherThanUserdefinedDiscountAmountForDirectGRN(i, FileName, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForDirectGrn(int i1, String FileName,
			String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Unit Cost For This Item");
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = ItemMultiple[i1][1];
			if (!ITEM_NAME.equals("")) {
				String Cost = dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
						commonFunc.getDataFromTestDataSheet("GRNUnitCost"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ITEM_NAME + "Is : " + Cost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 6, Cost);
				String TradeDiscountPct = dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
						commonFunc.getDataFromTestDataSheet("GRNDiscPct"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ITEM_NAME + "Is : " + TradeDiscountPct);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 8, TradeDiscountPct);

			}
		}
	}

	/**
	 * This Method Is Used To Entered Other Than User Defined Item To Give Discount
	 * Amount
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	private void otherThanUserdefinedDiscountAmountForDirectGRN(int i1, String FileName, String SheetName) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];

			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
//				String Type = webDB.getTextFromElement(PurchaseOrderPageLocators.POType, ElementType.Xpath);
//				logs.logging("INFO", "PO Type is:" + Type);
//				if (Type.equals(commonFunc.getDataFromTestDataSheet("ServiceType"))) {
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(2000);
//					flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderLineDetailForServiceItem(i - 21),
//							ElementType.Xpath);
//					webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderLineDetailForServiceItem(i - 21),
//							ElementType.Xpath);
//					logs.logging("INFO", "Service Item");
//				} else {
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(2000);
//					flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i - 21),
//							ElementType.Xpath);
//					webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i - 21), ElementType.Xpath);
//					logs.logging("INFO", "Non Service Item");
//				}
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 2), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 2), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(4000);

					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsNew, ElementType.Xpath);

						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						if (flag) {
							webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
							logs.logging("INFO", "Switched To Frame");
							commonFunc.hardWait(3000);
							commonFunc.checkRefreshCellsDisappear();
							if (webDB.getAttributeFromElement(DirectGrnPageLocators.GrnLineDetDiscount,
									ElementType.Xpath, "aria-expanded").equals("false")) {
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetDiscount,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetDiscount, ElementType.Xpath);
								}
							}

							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								webDB.pressEnterKey();
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.hardWait(3000);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									webDB.pressEnterKey();
									commonFunc.checkRefreshCellsDisappear();
									commonFunc.hardWait(3000);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										webDB.switchToDefaultContent();
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(3000);
										verifyDiscountAmountForDirectGRN(i1, FileName, SheetName);
										commonFunc.hardWait(4000);
										webDB.pressEscapeKey();
									}
								}

							}
						}
					}
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Amount For Rate Contract
	 * Item UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyUnitcostAndDiscountAmountForOpenPoItemForDirectGRN(int i1, String FileName, String SheetName,
			String RateContractSupplier, String UnitId) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ITEM_NAME, UnitId,
								commonFunc.getDataFromTestDataSheet("OpenPoUnitCost")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ITEM_NAME);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ITEM_NAME, UnitId,
								commonFunc.getDataFromTestDataSheet("OpenPoDiscPct")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNDiscPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ITEM_NAME);
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomRateContractMaterialTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L3"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWIRcImpSheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(String FileName, String SheetName,
			String Supplier, String LocId) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 3; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][19];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(3000);
					verifyClickingAdd();
					verifyClickingItemField(ITEM_NAME);
					verifyClickingHsnField(HSN);
					verifyClickingSuppQuantity(QTY);
					verifyClickingReceiptQuantity(QTY);
					verifyClickingCost(COST);
					verifyClickingSaveLine();
					verifyUnitcostAndDiscountAmountForPriceListItemForDirectGRN(i, FileName, SheetName, Supplier,
							LocId);
					verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForDirectGrn(i, FileName,
							SheetName);
					otherThanUserdefinedDiscountAmountForDirectGRN(i, FileName, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Amount For Rate Contract
	 * Item UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyUnitcostAndDiscountAmountForPriceListItemForDirectGRN(int i1, String FileName, String SheetName,
			String PriceListSupplier, String LocId) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Item " + ITEM_NAME + " " + PriceListSupplier);
				verifyAssertionBothValues(

						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME, LocId,
								commonFunc.getDataFromTestDataSheet("PriceListUnitCost")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ITEM_NAME);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Supplier " + PriceListSupplier + " Item NAme" + ITEM_NAME);
				verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME, LocId,
								commonFunc.getDataFromTestDataSheet("PriceListDiscPct")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNDiscPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ITEM_NAME);
			}
		}
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Amount For Rate Contract
	 * Item UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyUnitcostAndDiscountAmountForPriceListItemForDirectGRNWithLoc49(int i1, String SheetName,
			String PriceListSupplier) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("PriceListUnitCost")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ITEM_NAME);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("PriceListDiscPct")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNDiscPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ITEM_NAME);
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L49"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L49"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWIPlImpSheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomServiceTypeImportWithoutInspectionD() {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPS"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsForService(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISerSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyDirectGRNAddLineDetailsForService(String FileName, String SheetName) {

		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < GRNMultipleline.length; i++) {
			String ITEM_NAME = GRNMultipleline[i][1];
			String HSN = GRNMultipleline[i][19];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];

			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(3000);
				verifyClickingAdd();
				verifyClickingItemField(ITEM_NAME);
				verifyClickingHsnField(HSN);
				verifyClickingSuppQuantity(QTY);
				verifyClickingReceiptQuantity(QTY);
				verifyClickingCost(COST);
				verifyClickingSaveLine();
				verifyDirectGRNAddingDiscountService(i, FileName, SheetName);
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyDirectGRNAddingDiscountService(int i1, String FileName, String SheetName) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {

			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 2), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 2), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(4000);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						logs.logging("INFO", "Switched To Frame");
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.clearText(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount,
									ElementType.Xpath);
							logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										logs.logging("INFO", "Details Is Saved");
										commonFunc.hardWait(4000);
										webDB.pressEscapeKey();
										webDB.switchToDefaultContent();
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Direct GRN With Service Rate
	 * Contract
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeImportWithInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPS"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIRcSerSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L3"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeImportWithInspectionD() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPS"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIPlSerSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L49"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
//		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("DGSUomWISheet"),
//				webDB.getDataFromProperties("DirectGrnSheet"));
//		GRNFunc.lotSerialQtyApportion();
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWISheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of Direct Grn Material Type
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
//		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("DGSUomWISheet"),
//				webDB.getDataFromProperties("DirectGrnSheet"));
//		GRNFunc.lotSerialQtyApportion();
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWISheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWISheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomRateContractGeneralTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L3"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Deepan-GWL
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomRateContractMaterialTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L3"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWIRcImpSheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of Direct Grn PriceList Different UOM
	 * 
	 * @author Deepan-GWL
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L49"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();

	}

	/**
	 * This Methods Is Used To Verify Creation Of Direct Grn PriceList Different UOM
	 * 
	 * @author Deepan-GWL
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeImportWithInspectionD() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("L49"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWIPlImpSheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeImportWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
//		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("DGSUomWISheet"),
//				webDB.getDataFromProperties("DirectGrnSheet"));
//		GRNFunc.lotSerialQtyApportion();
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWISheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWISheet"));
	}

	/**
	 * This Methods Is Used To Verify Creation Direct GRN WithOut Quality
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
//		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("DGSUomWISheet"),
//				webDB.getDataFromProperties("DirectGrnSheet"));
//		GRNFunc.lotSerialQtyApportion();
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWoQWISheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Direct GRN WithOut Quality
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomMaterialTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
//		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("DGSUomWISheet"),
//				webDB.getDataFromProperties("DirectGrnSheet"));
//		GRNFunc.lotSerialQtyApportion();
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWoQWISheet"));
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomRateContractGeneralTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L3"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomRateContractMaterialTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L3"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWIWoQRcImpSheet"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of Direct Grn
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L49"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of Direct Grn
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L49"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGSUomWIWoQPlImpSheet"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomServiceTypeImportWithoutInspectionWithOutQuality() {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPS"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsForService(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWISerSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of Direct GRN With Service Rate
	 * Contract
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeImportWithInspectionWithOutQuality()
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPS"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQRcWISerSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L3"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeImportWithInspectionWithOutQuality()
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPS"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGSUomWoQWIPlSerSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L49"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
//		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("DGSUomWISheet"),
//				webDB.getDataFromProperties("DirectGrnSheet"));
//		GRNFunc.lotSerialQtyApportion();
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWoQWISheet"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR Material RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
//		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("DGSUomWISheet"),
//				webDB.getDataFromProperties("DirectGrnSheet"));
//		GRNFunc.lotSerialQtyApportion();
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWISheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWoQWISheet"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomRateContractGeneralTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L3"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnDifferentUomRateContractMaterialTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L3"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWoQWIRcImpSheet"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of Direct Grn PriceList Different UOM
	 * 
	 * @author Deepan-GWL
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L49"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of Direct Grn PriceList Different UOM
	 * 
	 * @author Deepan-GWL
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeImportWithInspectionWithOutQuality() {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L49"), commonFunc.getDataFromTestDataSheet("GRNPM"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"), commonFunc.getDataFromTestDataSheet("L49"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));
		GRNFunc.verifyImportLrBillNumber();
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirectGrnSheet"),
				webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirectGrnSheet"), webDB.getDataFromProperties("DGDUomWoQWIPlImpSheet"));

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredInterstate()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("InterstateSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
//		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		String GrnHeaderWiseStatus = dbCommonFunctions.GrnHeaderDetails(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("ProcessFlowStatus"));
		logs.logging("INFO", "GRN Document ProcessFlow Status Is " + GrnHeaderWiseStatus);
		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierLocalFreight()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("SupplierLocalFreight"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("SupplierLocalFreight"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalFreight"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("SupplierLocalFreight"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("SupplierLocalFreight"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierLocalFreightUnRegistered()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("UnregSupplierLocalFreight"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("UnregSupplierLocalFreight"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("UnregSupplierLocalFreight"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("UnregSupplierLocalFreight"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("UnregSupplierLocalFreight"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierSEZunit()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("SupplierSEZunit"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("SupplierSEZunit"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("SupplierSEZunit"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("SupplierSEZunit"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("SupplierSEZunit"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierSEZunitUnRegistered()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("UnregSupplierSEZunit"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("UnregSupplierSEZunit"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("UnregSupplierSEZunit"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("UnregSupplierSEZunit"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("UnregSupplierSEZunit"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierUnionTerritory()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("SupplierUnionTerritory"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("SupplierUnionTerritory"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("UTUnit"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("SupplierUnionTerritoryUT"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("SupplierUnionTerritory"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("SupplierUnionTerritory"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierUnionTerritoryUnRegistered()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("UnregSupplierUnionTerritoryUT"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("UnregSupplierUnionTerritoryUT"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("UTUnit"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("UnregSupplierUnionTerritoryUT"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("UnregSupplierUnionTerritoryUT"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("UnregSupplierUnionTerritoryUT"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillCompositionSupplierLocal()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("RegSupplierComposition"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("RegSupplierComposition"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("RegSupplierComposition"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("RegSupplierComposition"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("RegSupplierComposition"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierRegSupplierComposition()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("RegSupplierComposition"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("RegSupplierComposition"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("RegSupplierComposition"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("RegSupplierComposition"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("RegSupplierComposition"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierLocalFreightComposition()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("LocalFreightComposition"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("LocalFreightComposition"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("LocalFreightComposition"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("LocalFreightComposition"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("LocalFreightComposition"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierInterstateComposition()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("InterstateComposition"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("InterstateComposition"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("InterstateComposition"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("InterstateComposition"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("InterstateComposition"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierUnionTerritoryComposition()
			throws IOException {
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("UnionTerritoryComposition"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("UnionTerritoryComposition"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("UnionTerritoryComposition"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("UnionTerritoryComposition"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("UnionTerritoryComposition"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRN"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRN"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnregSupplierLocalFreight()
			throws IOException {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("UnregSupplierLocalFreight"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		GRNFunc2.clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRNImport"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnregSupplierUnregSupplierUnionTerritory()
			throws IOException {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		verifyDirectGRNStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		verifyDirectGRNStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("UnregSupplierUnionTerritory"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		GRNFunc2.clickImportLRAndfillBillNo(BillNo, Today);
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
//		verifyDirectGRNExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
//		verifyDirectGRNExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
//				webDB.getDataFromProperties("PurchaseBillGRNImport"));
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Deepan-GWL
	 * @throws IOException
	 */
	public void verifyDirectGRNAddLineDetailsWithReverseDoc(String FileName, String SheetName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(200);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			MultiGRNDocs.add(VariableHandler.getGrnDocNumber());// TODO

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 6; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][19];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(200);
					verifyClickingSuppQuantity(QTY);
					verifyClickingReceiptQuantity(QTY);
					verifyClickingCost(COST);
					verifyClickingSaveLine();
					GRNFunc.ChangeLotSerialQty(QTY);
					GRNFunc.ChangeLotSerialReciptQty(QTY);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyDirectGRNAddingDiscountWithReverse(int i1, String FileName, String SheetName) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {

			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnDetailsBtn(i - 5), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnDetailsBtn(i - 5), ElementType.Xpath);
					logs.logging("INFO", "Details Button Is Clicked");
					commonFunc.hardWait(500);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						logs.logging("INFO", "Switched To Frame");
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.clearText(DirectGrnPageLocators.GrnLineTrdDisc, ElementType.Xpath);
							webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount,
									ElementType.Xpath);
							logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
								commonFunc.hardWait(1000);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
									commonFunc.hardWait(1000);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										logs.logging("INFO", "Details Is Saved");
										commonFunc.hardWait(1000);
										webDB.pressEscapeKey();
										webDB.switchToDefaultContent();
										commonFunc.checkRefreshCellsDisappear();
										verifyDiscountAmountForDirectGRN(i1, FileName, SheetName);

									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Deepan-GWL
	 * @throws IOException
	 */
	public void verifyDirectGRNAddLineDetailsWithReverseDocPOA(String FileName, String SheetName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(200);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			MultiGRNDocs.add(VariableHandler.getGrnDocNumber());// TODO

			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 6; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][19];
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];

				if (!ITEM_NAME.equals("")) {
					commonFunc.hardWait(200);
					GRNFunc.ChangeLotSerialQty(QTY);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Direct Grn PriceList
	 * 
	 * @author Deepan-GWL
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListForIncomingInspection() {
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("GRNPG"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyDirectGRNAddLineDetails(webDB.getDataFromProperties("QualityInspectionSheet"),
				webDB.getDataFromProperties("Insp-IncommingSheet"));
		DirectGRNmultipleLotandSerialWithGeneralType(webDB.getDataFromProperties("QualityInspectionSheet"),
				webDB.getDataFromProperties("Insp-IncommingSheet"));
		GRNFunc.overAllSaveAndPost();
		GRNFunc.waitingForApproval();

	}

	/**
	 * This Methods Is Used To Document Number To
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApprovalForGRN() {
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		logs.logging("INFO", "Ok Button Is Clicked");
		webDB.switchToDefaultContent();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		assertTrue(flag);
	}
}
