package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
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
	private static boolean flag = false;
	static InspectionDocumentFunctions qcFunc = new InspectionDocumentFunctions();
	static MaterialReceiptVoucherFunctions mrvFunc = new MaterialReceiptVoucherFunctions();

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection() throws IOException {
		verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		
		GRNFunc.waitingForApproval();
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		GRNFunc.waitingForApproval();
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNSerUserDefined"));
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNSerUserDefined"));
		GRNFunc.waitingForApproval();
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
		String DCNO = BillNo + SupBillNo;

		flag = webDB.waitForElement(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame");
			flag = webDB.waitForElement(DirectGrnPageLocators.GrnLocLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnLocLov, Location);
				logs.logging("INFO", "Location Is Entered:" + Location);
				flag = webDB.waitForElement(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
					logs.logging("INFO", "Switched To Frame");
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnType, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByValue(DirectGrnPageLocators.GrnType, Type, ElementType.Xpath);
						logs.logging("INFO", "Type Is Entered:" + Type);
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnSupplierLov, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnSupplierLov, Supplier);
							logs.logging("INFO", "Supplier Is Entered:" + Supplier);
							flag = webDB.waitForElement(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
							if (flag) {
								webDB.switchToFrame(DirectGrnPageLocators.AddHeaderFrame, ElementType.Xpath);
								logs.logging("INFO", "Switched To Frame");
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnSupplierBillNo, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnSupplierBillNo, DCNO,
											ElementType.Xpath);
									logs.logging("INFO", "Supplier Bill NO Is Entered");
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnDcNo, ElementType.Xpath);
									if (flag) {
										webDB.sendTextToAnElement(DirectGrnPageLocators.GrnDcNo, DCNO,
												ElementType.Xpath);
										logs.logging("INFO", "DC NO Is Entered");
										flag = webDB.waitForElement(DirectGrnPageLocators.GrnCreate, ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(DirectGrnPageLocators.GrnCreate, ElementType.Xpath);
											logs.logging("INFO", "GRN Is Created");
											webDB.switchToDefaultContent();
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
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyGrnAddLineDetails(String SheetName) throws IOException {
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
			commonFunc.hardWait(3000);
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
							flag = webDB.waitForElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialItemLov, ElementType.Xpath);
								webDB.switchToDefaultContent();
								System.out.println("Nit");
								flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
								List<WebElement> removeFilter = webDB.getDriver()
										.findElements(By.xpath(GrnLocators.LotserialitemsInput));
								for (WebElement element : removeFilter) {
									if (element.isDisplayed()) {
										element.sendKeys(ITEM_NAME);
										System.out.println(1);
										break;
									}
								}
								webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
							}
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

			flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineItemField, ElementType.Xpath);
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
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineItemField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.GrnLineItemField, ElementType.Xpath);
			logs.logging("INFO", "Item Lov Is Clicked");
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnLineItemLov, ITEM_NAME);
			logs.logging("INFO", "Entered Item Is:" + ITEM_NAME);
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingHsnField(String HSN) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineHsnField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.GrnLineHsnField, ElementType.Xpath);
			logs.logging("INFO", "HSN Field Is Clicked");
			commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.GrnLineHsnLov, HSN);
			logs.logging("INFO", "Entered HSN Code Is:" + HSN);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingSuppQuantity(String QTY) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSupQty, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.GrnLineSupQty, ElementType.Xpath);
			webDB.clearText(DirectGrnPageLocators.GrnLineSupQtyInput, ElementType.Xpath);
			webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSupQtyInput, QTY, ElementType.Xpath);
			logs.logging("INFO", "Entered Quantity Is:" + QTY);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingReceiptQuantity(String QTY) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineRcpQty, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.GrnLineRcpQty, ElementType.Xpath);
			webDB.clearText(DirectGrnPageLocators.GrnLineRcpQtyInput, ElementType.Xpath);
			webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineRcpQtyInput, QTY, ElementType.Xpath);
			logs.logging("INFO", "Entered Quantity Is:" + QTY);
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingCost(String COST) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.GrnLineCost, ElementType.Xpath);
			webDB.clearText(DirectGrnPageLocators.GrnLineCostInput, ElementType.Xpath);
			webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCostInput, COST, ElementType.Xpath);
			logs.logging("INFO", "Entered Cost Is:" + COST);
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
			logs.logging("INFO", "GRN Line Is Saved");
			commonFunc.hardWait(4000);
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
							commonFunc.hardWait(4000);
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.clearText(DirectGrnPageLocators.GrnLineSpclDisc, ElementType.Xpath);
								webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Trade Discount Is:" + SpecialDiscount);
								commonFunc.hardWait(4000);
								flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.clearText(DirectGrnPageLocators.GrnLineCashDisc, ElementType.Xpath);
									webDB.sendTextToAnElement(DirectGrnPageLocators.GrnLineCashDisc, CashDiscount,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Trade Discount Is:" + CashDiscount);
									commonFunc.hardWait(4000);
									flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave,
												ElementType.Xpath);
										logs.logging("INFO", "Details Is Saved");
										commonFunc.hardWait(4000);
										webDB.pressEscapeKey();
										webDB.switchToDefaultContent();
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
		flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Item Successfully Added");
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.LotclickInput, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.LotclickInput, ElementType.Xpath);
					commonFunc.hardWait(3000);
					flag = webDB.isElementDisplayed(GrnLocators.LotNumberInput, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(GrnLocators.LotNumberInput, LOT_NUMBER, ElementType.Xpath);
						flag = webDB.waitForElement(GrnLocators.LotSerialDate, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Lot Number Insert Successfully");
							flag = webDB.isElementEnabled(GrnLocators.SerialNumberInput, ElementType.Xpath);
							if (!flag) {
								logs.logging("info", "Verified Serial Is Disabled");
							} else {
								logs.logging("INFO", "Serial Is Enabled");
							}
							flag = webDB.waitForElement(GrnLocators.LotSerialDate, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GrnLocators.LotSerialDate, ElementType.Xpath);
								flag = webDB.waitForElement(GrnLocators.DateInput, ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(5000);
									webDB.sendTextToAnElement(GrnLocators.DateInput,
											commonFunc.getDataFromTestDataSheet("LotSerialDate"), ElementType.Xpath);
									logs.logging("INFO", "Lot Date Insert Successfully");
									commonFunc.hardWait(2000);
									flag = webDB.waitForElement(GrnLocators.LotSerialSave, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(GrnLocators.LotSerialSave, ElementType.Xpath);
										commonFunc.hardWait(3000);
										closeToastIfDisplayed();
										flag = webDB.waitForElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
										if (flag) {
											commonFunc.hardWait(5000);
											webDB.clickAnElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
											logs.logging("INFO", "LotSerial Qty Field Clicked Successfully");
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
	 * This Methods Method to handle Serial_Number input logic for Lot Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void inputSerialNumber(String ITEM_NAME, String SERIAL_NUMBER) {
		flag = webDB.waitForElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.LotSerialItemSearch, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.LotserialItemSelect, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "item Successfully Added");
				webDB.switchToFrame(GrnLocators.LotSerialIframe, ElementType.Xpath);
				flag = webDB.waitForElement(GrnLocators.SerialClickInput, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.SerialClickInput, ElementType.Xpath);
					flag = webDB.isElementDisplayed(GrnLocators.SerialNumberInput, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(GrnLocators.SerialNumberInput, SERIAL_NUMBER, ElementType.Xpath);
						logs.logging("INFO", "Serial Number Insert Successfully");
						flag = webDB.isElementEnabled(GrnLocators.LotNumberInput, ElementType.Xpath);
						if (!flag) {
							logs.logging("info", "verified lot is disabled");
						} else {
							logs.logging("INFO", "lot Is Enabled");
						}
						flag = webDB.waitForElement(GrnLocators.LotSerialDate, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnLocators.LotSerialDate, ElementType.Xpath);
							flag = webDB.waitForElement(GrnLocators.DateInput, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(5000);
								webDB.sendTextToAnElement(GrnLocators.DateInput,
										commonFunc.getDataFromTestDataSheet("LotSerialDate"), ElementType.Xpath);
								logs.logging("INFO", "Serial Date Insert Successfully");
								commonFunc.hardWait(2000);
								flag = webDB.waitForElement(GrnLocators.LotSerialSave, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(GrnLocators.LotSerialSave, ElementType.Xpath);
									closeToastIfDisplayed();
									flag = webDB.waitForElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
									if (flag) {
										commonFunc.hardWait(5000);
										webDB.clickAnElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
										logs.logging("INFO", "LotSerial Qty Field Clicked Successfully");
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
	 * This Methods Method to handle Toast Message Closed
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void closeToastIfDisplayed() {
		commonFunc.hardWait(3000);
		if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
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
		if (flag) {
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.LotSerialQtyField, ElementType.Xpath);
				logs.logging("INFO", "LotSerial Qty Field Clicked Successfully");
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GrnLocators.ApportionLSQty, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
					if (flag) {
					}
				}
			}
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
			logs.logging("INFO", "Verified Actual DataBase Value 8: " + Actual + " And Expected Values : " + Expected);
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		assertTrue(flag);
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithInspection() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("FreePassDirectRateSer"));
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectRateSer"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyReturnToGrnPageHeaderStatus();
		assertTrue(flag);
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		assertTrue(flag);

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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNRateGen"));
		GRNFunc.waitingForApproval();
		verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("FreePassDirectPriceListGen"));
		assertTrue(flag);

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithInspection() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(
				webDB.getDataFromProperties("FreePassDirectGRNPriceListSer"));
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("FreePassDirectGRNPriceListSer"));
		GRNFunc.waitingForApproval();
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

	public void verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForGrn(int i1, String SheetName)
			throws IOException {
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

	private void otherThanUserdefinedDiscountAmountForGrn(int i1, String SheetName) {

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
					commonFunc.hardWait(4000);
					flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						logs.logging("INFO", "Switched To Frame");
						commonFunc.hardWait(3000);
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
									webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave, ElementType.Xpath);
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
			commonFunc.hardWait(3000);
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			flag = webDB.waitForElement(GrnLocators.OverAllSave, ElementType.Xpath);
			if (flag)
				webDB.clickAnElement(GrnLocators.OverAllSave, ElementType.Xpath);
			logs.logging("INFO", "Document Successfully Saved");
			commonFunc.hardWait(2000);
			flag = webDB.waitForClickElement(GrnLocators.PostButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PostButton, ElementType.Xpath);
				logs.logging("INFO", "Document Successfully posted");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		GRNFunc.waitingForApproval();
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefined(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirectGrnDifFreePassRateContGen"));
		GRNFunc.waitingForApproval();
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
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
		GRNFunc.waitingForApproval();
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
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
		GRNFunc.waitingForApproval();
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("FreePassDirectGRNUserDefined"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		GRNFunc.waitingForApproval();
		GRNFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	public void CheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithQuality() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		verifyGrnAdd();
		verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("FreePassDirectGRNUserDefined"));
		lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPostForExcess(webDB.getDataFromProperties("DirectgrndiffFreePassPricelistG"));
		GRNFunc.waitingForApproval();
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

}
