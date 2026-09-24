package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 5, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
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
	private static PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	private static GateEntryPageFunctions gateEntryPageFunc = new GateEntryPageFunctions();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();

	/**
	 * This Methods Is Used To Verify Add Line Details in GRN as Price list
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(String FileName, String SheetName,
			String Supplier) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 22; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][68];
				String QTY = GRNMultipleline[i][5];
				if (!ITEM_NAME.equals("")) {
					DirectGRNFunc.verifyClickingAdd();
					commonFunc.verifyAddTableLovValue(DirectGrnPageLocators.GRNPageItemTable(),
							DirectGrnPageLocators.GrnLineItemLov, ITEM_NAME);
					webDB.pressTabKey();
					webDB.pressTabKey();
					commonFunc.verifyAddTableLovValue(DirectGrnPageLocators.GRNPageHSNTable(),
							DirectGrnPageLocators.GrnLineHsnLov, HSN);
					webDB.pressTabKey();
					webDB.pressTabKey();
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageSupQtyTable(),
							DirectGrnPageLocators.GrnPageSupQtyInput(), QTY);
					logs.logging("INFO", "Entered Supply Quantity Is:" + QTY);
					webDB.pressTabKey();
					webDB.pressTabKey();
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageRcptQtyTable(),
							DirectGrnPageLocators.GRNPageRcptQtyInput(), QTY);
					logs.logging("INFO", "Entered Receipt Quantity Is:" + QTY);
					webDB.pressTabKey();
					webDB.pressTabKey();
					DirectGRNFunc.verifyClickingSaveLine();
					verifyAddingDiscount(i, FileName, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Add Line Details in GRN as Price list
	 * 
	 * @author Sakthi-GWL
	 * @param i1
	 * @param FileName
	 * @param SheetName
	 * @param PriceListSupplier
	 */
	public void verifyUnitcostAndDiscountAmountForPriceListItemForGrn(int i1, String FileName, String SheetName,
			String PriceListSupplier) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				DirectGRNFunc.verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("PriceListUnitCost")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ITEM_NAME);
				commonFunc.checkRefreshCellsDisappear();
				DirectGRNFunc.verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("PriceListDiscPct")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNDiscPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ITEM_NAME);
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Add Line Details in GRN as Price list
	 * 
	 * @author Sakthi-GWL
	 * @param i1
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralTypeForGrn(int i1, String FileName,
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
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyGrnAddLineDetailsOtherThanUserDefinedRateContract(String FileName, String SheetName,
			String Supplier) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 22; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = GRNMultipleline[i][68];
				String QTY = GRNMultipleline[i][5];
				if (!ITEM_NAME.equals("")) {
					DirectGRNFunc.verifyClickingAdd();
					commonFunc.verifyAddTableLovValue(DirectGrnPageLocators.GRNPageItemTable(),
							DirectGrnPageLocators.GrnLineItemLov, ITEM_NAME);
					webDB.pressTabKey();
					webDB.pressTabKey();
					commonFunc.verifyAddTableLovValue(DirectGrnPageLocators.GRNPageHSNTable(),
							DirectGrnPageLocators.GrnLineHsnLov, HSN);
					webDB.pressTabKey();
					webDB.pressTabKey();
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageSupQtyTable(),
							DirectGrnPageLocators.GrnPageSupQtyInput(), QTY);
					logs.logging("INFO", "Entered Supply Quantity Is:" + QTY);
					webDB.pressTabKey();
					webDB.pressTabKey();
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageRcptQtyTable(),
							DirectGrnPageLocators.GRNPageRcptQtyInput(), QTY);
					logs.logging("INFO", "Entered Receipt Quantity Is:" + QTY);
					webDB.pressTabKey();
					webDB.pressTabKey();
					DirectGRNFunc.verifyClickingSaveLine();
					verifyAddingDiscount(i, FileName, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Add Line Details in GRN as Price list
	 * 
	 * @author Sakthi-GWL
	 * @param i1
	 * @param FileName
	 * @param SheetName
	 * @param RateContractSupplier
	 */
	public void verifyUnitcostAndDiscountAmountForOpenPoItemForGrn(int i1, String FileName, String SheetName,
			String RateContractSupplier) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				DirectGRNFunc.verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("OpenPoUnitCost")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ITEM_NAME);
				commonFunc.checkRefreshCellsDisappear();
				DirectGRNFunc.verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("OpenPoDiscPct")),
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ITEM_NAME,
								commonFunc.getDataFromTestDataSheet("GRNDiscPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ITEM_NAME);
			}
		}
	}

	/**
	 * This Methods Is Used To Add Line Details
	 * 
	 * @author Sakthi-GWL
	 * @param SheetName
	 */
	public void verifyGrnAddLineDetails(String FileName, String SheetName) {
		flag = webDB.waitForElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
			for (int i = 22; i < GRNMultipleline.length; i++) {
				String ITEM_NAME = GRNMultipleline[i][1];
				String HSN = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				String QTY = GRNMultipleline[i][5];
				String COST = GRNMultipleline[i][6];
				if (!ITEM_NAME.equals("")) {
					DirectGRNFunc.verifyClickingAdd();
					commonFunc.verifyAddTableLovValue(DirectGrnPageLocators.GRNPageItemTable(),
							DirectGrnPageLocators.GrnLineItemLov, ITEM_NAME);
					commonFunc.verifyAddTableLovValue(DirectGrnPageLocators.GRNPageHSNTable(),
							DirectGrnPageLocators.GrnLineHsnLov, HSN);
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageSupQtyTable(),
							DirectGrnPageLocators.GrnPageSupQtyInput(), QTY);
					logs.logging("INFO", "Entered Supply Quantity Is:" + QTY);
					webDB.pressTabKey();
					webDB.pressTabKey();
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageRcptQtyTable(),
							DirectGrnPageLocators.GRNPageRcptQtyInput(), QTY);
					logs.logging("INFO", "Entered Receipt Quantity Is:" + QTY);
					webDB.pressTabKey();
					webDB.pressTabKey();
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageUnitCostTable(),
							DirectGrnPageLocators.GRNPageUnitCostInput(), COST);
					logs.logging("INFO", "Entered Unit Cost Is:" + COST);
					commonFunc.checkRefreshCellsDisappear();
					DirectGRNFunc.verifyClickingSaveLine();
					verifyAddingDiscount(i, FileName, SheetName);
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
	public void verifyAddingDiscount(int i1, String FileName, String SheetName) {
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
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.GrnLinesCustomMenuBtnThreeDots(1), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.GrnLinesCustomMenuBtnThreeDots(1), ElementType.Xpath);
					logs.logging("INFO", "Custom Menu Button(Three Dots) Is Clicked");
					flag = webDB.waitForElement(DirectGrnPageLocators.DetailsMenuThreeDots, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(DirectGrnPageLocators.DetailsMenuThreeDots, ElementType.Xpath);
						flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Details Button Clicked and Iframe Displayed");
							webDB.switchToFrame(DirectGrnPageLocators.GrnLineDetailsFrame, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(DirectGrnPageLocators.LnDts_DiscountExpandButton,
									ElementType.Xpath);
							if (flag) {
								String value = webDB.getDomAttributeFromElement(
										DirectGrnPageLocators.LnDts_DiscountExpandButton, ElementType.Xpath,
										"aria-expanded");
								if (value.equalsIgnoreCase("false")) {
									webDB.moveToElement(DirectGrnPageLocators.LnDts_DiscountExpandButton,
											ElementType.Xpath);
									webDB.javaScriptClickAnElement(DirectGrnPageLocators.LnDts_DiscountExpandButton,
											ElementType.Xpath);
									logs.logging("INFO", "Discount sub tab clicked and expanded");
								} else {
									logs.logging("INFO", "Discount sub tab already expanded");
								}
							}
							if (webDB.verifyIsElementDisplayed(DirectGrnPageLocators.GrnLineTrdDisc,
									ElementType.Xpath)) {
								commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineTrdDisc,
										DirectGrnPageLocators.GrnLineTrdDisc, TradeDiscount);
								logs.logging("INFO", "Entered Trade Discount Is:" + TradeDiscount);
							}
							commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineSpclDisc,
									DirectGrnPageLocators.GrnLineSpclDisc, SpecialDiscount);
							logs.logging("INFO", "Entered Special Discount Is:" + SpecialDiscount);
							commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineCashDisc,
									DirectGrnPageLocators.GrnLineCashDisc, CashDiscount);
							logs.logging("INFO", "Entered Cash Discount Is:" + CashDiscount);
							flag = webDB.waitForElement(DirectGrnPageLocators.GrnLineDetailsSave, ElementType.Xpath);
							if (flag) {
								webDB.moveToElement(DirectGrnPageLocators.GrnLineDetailsSave, ElementType.Xpath);
								webDB.clickAnElement(DirectGrnPageLocators.GrnLineDetailsSave, ElementType.Xpath);
								logs.logging("INFO", "Details Is Saved");
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.checkSuccessToastMessageDisappear();
								commonFunc.checkRefreshCellsDisappear();
								webDB.pressEscapeKey();
								webDB.switchToDefaultContent();
								verifyDiscountAmountForGrn(i1, FileName, SheetName);
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
	 * @author Sakthi-GWL
	 * 
	 *
	 */
	public void verifyDiscountAmountForGrn(int i2, String FileName, String SheetName) {
		commonFunc.hardWait(3000);
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
				DirectGRNFunc.verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(),
						ItemId, commonFunc.getDataFromTestDataSheet("GRNTradeAssessableValue")), TradeAssessbleValue);
				logs.logging("INFO", "Trade Assessable Value Matched With DB Values For This Item " + ItemId);
				DirectGRNFunc.verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialAssessableValue")),
						SpecialAssessbleValue);
				logs.logging("INFO", "Special Assessable Value Matched With DB Values For This Item " + ItemId);
				DirectGRNFunc.verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(),
						ItemId, commonFunc.getDataFromTestDataSheet("GRNCashAssessableValue")), CashAssessbleValue);
				logs.logging("INFO", "Cash Assessable Value Matched With DB Values For This Item " + ItemId);
				DirectGRNFunc.verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(),
						ItemId, commonFunc.getDataFromTestDataSheet("GRNTradeDiscountAmount")), TradeDiscountAmount);
				logs.logging("INFO", "Trade Discount Amount Matched With DB Values For This Item " + ItemId);
				DirectGRNFunc.verifyAssertionBothValues(
						dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNSpecialDiscountAmount")),
						SpecialDiscountAmount);
				logs.logging("INFO", "Special Discount Amount Matched With DB Values For This Item " + ItemId);
				DirectGRNFunc.verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(),
						ItemId, commonFunc.getDataFromTestDataSheet("GRNCashDiscountAmount")), CashDiscountAmount);
				logs.logging("INFO", "Cash Discount Amount Matched With DB Values For This Item " + ItemId);
				DirectGRNFunc.verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(),
						ItemId, commonFunc.getDataFromTestDataSheet("GrnLineAmount")), LineAmount);
				logs.logging("INFO", "Line Amount" + LineAmount + "Matched With DB Values For This Item" + ItemId);
				DirectGRNFunc.verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(),
						ItemId, commonFunc.getDataFromTestDataSheet("GRNCgstAmount")), TaxAmountCGST);
				logs.logging("INFO", "Tax Amount CGST " + TaxAmountCGST + "For This Item" + ItemId);
				DirectGRNFunc.verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(),
						ItemId, commonFunc.getDataFromTestDataSheet("GRNSgstAmount")), TaxAmountSGST);
				logs.logging("INFO", "Tax Amount SGST" + TaxAmountSGST + "For This Item" + ItemId);
				DirectGRNFunc.verifyAssertionBothValues(dbCommonFunc.StockQuery4(VariableHandler.getGrnDocNumber(),
						ItemId, commonFunc.getDataFromTestDataSheet("GRNIgstAmount")), TaxAmountIGST);
				logs.logging("INFO", "Tax Amount IGST" + TaxAmountIGST + "For This Item" + ItemId);
			}
		}
	}

	/**
	 * This Methods Method to handle Multiple Lot serial Item for General Type
	 * Document
	 * 
	 * @author Sakthi-GWL
	 * @param SheetName
	 */
	public void multipleLotandSerialWithGeneralType(String FileName, String SheetName) {
		flag = webDB.waitForElement(GrnLocators.LotSerailButton, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Successfully Created GRN and Entered GRN Transaction Screen");
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			webDB.moveToElement(GrnLocators.LotSerailButton, ElementType.Xpath);
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
				for (int i = 22; i < itemmultiple.length; i++) {
					String ITEM_NAME = itemmultiple[i][1];
					String LOT_SERIAL = itemmultiple[i][54];
					String LOT_NUMBER = itemmultiple[i][55];
					String SERIAL_NUMBER = itemmultiple[i][56];
					if (!ITEM_NAME.equals("")) {
						if (LOT_SERIAL.equals(commonFunc.getDataFromTestDataSheet("Na"))) {
							continue;
						}
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
							webDB.clickAnElement(GrnLocators.AddLotSerialItem, ElementType.Xpath);
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
	 * This method used to save GRN and POST document
	 * 
	 * @author Sakthi-GWL
	 */
	public void overAllSaveAndPostForExcess() {
		flag = webDB.waitForElement(GrnLocators.OverAllSave, ElementType.Xpath);
		if (flag) {
			String GrnNumber = webDB.getTextFromElement(GrnLocators.GrnDocNumber, ElementType.Xpath);
			VariableHandler.setGrnDocNumber(GrnNumber);
			logs.logging("INFO", "GrnNumber Is :" + VariableHandler.getGrnDocNumber());
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(GrnLocators.OverAllSave, ElementType.Xpath);
			webDB.clickAnElement(GrnLocators.OverAllSave, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Document Successfully Saved");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
				flag = webDB.waitForElement(GrnLocators.PostButton, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(GrnLocators.PostButton, ElementType.Xpath);
					webDB.clickAnElement(GrnLocators.PostButton, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to verify GRN document status as approved
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyGRNDocumentApprovedStatus() {
		commonFunc.naviagteGrnPage();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.searchAndSelectValueInFilters(GrnLocators.FindPageGrnNoLov, VariableHandler.getGrnDocNumber());
		logs.logging("INFO", "Document Number Search And Selected Successfully");
		flag = webDB.waitForElement(GrnLocators.GrnHeaderRadioBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.GrnHeaderRadioBtn, ElementType.Xpath);
			logs.logging("INFO", "Header Button Clicked");
			flag = webDB.waitForElement(GrnLocators.GrnSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.GrnSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Document Searched SuccessFully");
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GrnLocators.GrnDocumentStatus, ElementType.Xpath);
				if (flag) {
					String GRNSTATUS = webDB.getTextFromElement(GrnLocators.GrnDocumentStatus, ElementType.Xpath);
					VariableHandler.setGrnStatus(GRNSTATUS);
					logs.logging("INFO", "GRN ProcesWise STATUS: " + VariableHandler.getGrnStatus());
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Import and Fill Bill Details
	 * 
	 * @author Sakthi-GWL
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
				commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRN_ImportBillNoInput,
						DirectGrnPageLocators.GRN_ImportBillNoInput, BillNo);
				logs.logging("INFO", "Import / LR Bill No Entered: " + BillNo);
				commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRN_ImportBillDateInput,
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
	 * This method used to verify Free of Cost and update Unit Cost as 0 and Save
	 * Lines
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyEnterUnitCostZeroClickFOCAndSaveLines(String ItemType) {
		flag = webDB.waitForElement(DirectGrnPageLocators.GrnFOCTabledata(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(DirectGrnPageLocators.GrnFOCTabledata(), ElementType.Xpath);
			webDB.clickAnElement(DirectGrnPageLocators.GrnFOCTabledata(), ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.isElementSelected(DirectGrnPageLocators.GrnFOCTableInput(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "FOC Input Box Clicked in GRN Line Details");
				if (ItemType.equals(commonFunc.getDataFromTestDataSheet("UD-ItemType"))) {
					commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GRNPageUnitCostTable(),
							DirectGrnPageLocators.GRNPageUnitCostTable(), "0");
					webDB.pressTabKey();
					logs.logging("INFO", "For Free Of Cost, Entered Unit Cost Is:" + "0");
				}
				commonFunc.checkRefreshCellsDisappear();
				DirectGRNFunc.verifyClickingSaveLine();
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		assertTrue(flag);
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDService"));
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedRateContract(
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedRateContract(
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedRateContract(
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCService"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLService"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		DirectGRNFunc.verifyGrnAdd();
		DirectGRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedRateContract(
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		DirectGRNFunc.verifyGrnAdd();
		DirectGRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedRateContract(
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		DirectGRNFunc.verifyGrnAdd();
		DirectGRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.validateStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.validateStockQtyAndCostQtyForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.naviagteGrnPage();
		DirectGRNFunc.verifyGrnAdd();
		DirectGRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		verifyGrnAddLineDetailsOtherThanUserDefinedPriceList(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"));
		multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.validateExpectedStockQtyAndCostQtyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.validateExpectedStockQtyAndCostQtyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-General-User Defined-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryGeneralUDSameStockAndSupplierUomImport() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-General-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryGeneralRCSameStockAndSupplierUomImport() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-General-Price List-Same Stock and Supplier UOM
	 * 
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryGeneralPLSameStockAndSupplierUomImport() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
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
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-General-User Defined-Different Stock and Supplier UOM
	 * 
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryGeneralUDDifferentStockAndSupplierUomImport() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-Material-User Defined-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryMaterialUDDifferentStockAndSupplierUomImport() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-General-Rate Contract-Different Stock and Supplier UOM
	 * 
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryGeneralRCDifferentStockAndSupplierUomImport() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-Material-Rate Contract-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryMaterialRCDifferentStockAndSupplierUomImport() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForRateContractItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-General-Price List-Different Stock and Supplier UOM
	 * 
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryGeneralPLDifferentStockAndSupplierUomImport() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

	/**
	 * PO to GRN With Free pass Items-With Gate Entry-Import Supplier -- PO to
	 * GRN-Material-Price List-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPOtoGRNWithFPsGateEntryMaterialPLDifferentStockAndSupplierUomImport() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		POfunc.verifyDirectPODomesticCustomizeTypeSimilarUOMwithAndWithoutInspectionMaterialTypeForPriceListItem(
				commonFunc.getDataFromTestDataSheet("L49ID"), commonFunc.getDataFromTestDataSheet("HKIMPSUP01Desc"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("PoToGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		DirectGRNFunc.lotSerialQtyApportion();
		clickImportLRAndfillBillNo(BillNo, Today);
		overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
		}
		//verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
	}

}
