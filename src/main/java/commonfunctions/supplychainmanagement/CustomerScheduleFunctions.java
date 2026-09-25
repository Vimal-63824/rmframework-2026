package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.CustomerItemsLocators;
import commonlocators.supplychainmanagement.CustomerScheduleLocators;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.SalesCatalogueLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import commonlocators.supplychainmanagement.SalesPriceListLocators;
import commonlocators.supplychainmanagement.SalesStandardPriceLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CustomerScheduleFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions apprpagefunc = new ApprovalPageFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static ExcelUtils excelUtils = new ExcelUtils();
	private static SalesOrderFunctions salesOrderFunctions = new SalesOrderFunctions();
	private static OpenSalesOrderFunctions openSOFunc = new OpenSalesOrderFunctions();
	private static MaterialRequestFunctions MatReqFunc = new MaterialRequestFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	String[][] CSMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	String[][] CSUpload = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	String CSFilepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "CUST SCH.xlsx";
	String CSTemplateFilepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "CustomerScheduleUploadTemplate.csv";
	ArrayList<String> ArrayList = new ArrayList<String>();
	ArrayList<String> MultiOpenSODoc = new ArrayList<String>();
	ArrayList<String> MultiPriceList = new ArrayList<String>();
	ArrayList<String> MultiCatalogPriceDoc = new ArrayList<String>();
	ArrayList<String> CSLineDocNo = new ArrayList<String>();
	LocalDate date = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String Today = date.format(formatter);
	LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
	LocalDateTime myDateObj1 = LocalDateTime.now().plusDays(3);
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yy");
	DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String tomorrowDate = myDateObj.format(myFormatObj);
	String tomorrowDate1 = myDateObj1.format(myFormatObj1);

	/**
	 * This Method Is Used To Add The Customer Schedule Header Details And Get The
	 * CS Document Number
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void addCustomerScheduleHeaderDetails(String Location, String Prefix, String Narration) {
//		flag = webDB.waitForElement(CustomerScheduleLocators.CSAddButton, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CustomerScheduleLocators.CSAddButton, ElementType.Xpath);
		String GetText = webDB.getDomPropertyFromElement(CustomerScheduleLocators.CSLocationField, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		if (!GetText.equals(Location)) {
			commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSLocationLov, Location);
			commonFunc.checkRefreshCellsDisappear();
			MatReqFunc.FrameSwitching(CustomerScheduleLocators.CSFrame);
		}
		commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSBatchPrefixLov, Prefix);
		commonFunc.checkRefreshCellsDisappear();
		MatReqFunc.FrameSwitching(CustomerScheduleLocators.CSFrame);
		flag = webDB.waitForElement(CustomerScheduleLocators.CSNarrationField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(CustomerScheduleLocators.CSNarrationField, Narration, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CustomerScheduleLocators.CSsaveButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Narration Entered Sucessfully");
				webDB.clickAnElement(CustomerScheduleLocators.CSsaveButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CustomerScheduleLocators.CSPostButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Customer Schedule Header Details Is Created SucessFully");
					flag = webDB.waitForElement(CustomerScheduleLocators.CSDocNumberField, ElementType.Xpath);
					if (flag) {
						String CSDocNumber = webDB.getTextFromElement(CustomerScheduleLocators.CSDocNumberField,
								ElementType.Xpath);
						VariableHandler.setCSDocNumber(CSDocNumber);
						logs.logging("INFO",
								"Customer Schedule Document Number Is " + VariableHandler.getCSDocNumber());
					}
				}
			}
		}
//		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Customer In The Line Wise
	 * 
	 * @author Harikrishnan = RMITS
	 * @param Customer
	 */
	public void addLineWiseCustomer(String Customer) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CustomerScheduleLocators.CSLineWiseAddButton, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.javaScriptClickAnElement(CustomerScheduleLocators.CSLineWiseAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSLineWiseCustField, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(CustomerScheduleLocators.CSLineWiseCustField, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSLineWiseCustLov, Customer);
				webDB.pressTabKey();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add the Item In The Line Wise
	 * 
	 * @author Harikrishna - RMITS
	 * @param Item
	 */
	public void addItemsInCS(String Item) {
		flag = webDB.waitForElement(CustomerScheduleLocators.AddItemField(), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CustomerScheduleLocators.AddItemField(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSLineWiseItemLov, Item);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * Tis Method Is Used To Add the Qty UnitCost Mrp Price And Discount
	 * 
	 * @author Harikrishna - RMITS
	 * @param FirmQty
	 * @param UnitCost
	 * @param MrpPrice
	 * @param Discount
	 */
	public void addQtyUnitAndMrpCost(String FirmQty, String UnitCost, String MrpPrice, String Discount, String EndDate,
			String BucketType) {
		commonFunc.checkRefreshCellsDisappear();
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.FirmQtyLines(),
				CustomerScheduleLocators.CSLineWiseFirmQtyInputField, FirmQty);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.UnitPriceLines(),
				CustomerScheduleLocators.CSLineWiseUnitPriceInputField, UnitCost);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.MrpPriceLines(),
				CustomerScheduleLocators.CSLineWiseMRPInputField, MrpPrice);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.DiscFieldLines(),
				CustomerScheduleLocators.CSLineWiseDiscInputField, Discount);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(CustomerScheduleLocators.CSLineWiseEndDateField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSLineWiseEndDateField, ElementType.Xpath);
			webDB.sendTextToAnElement(CustomerScheduleLocators.CSLineWiseEndDateInputField, EndDate, ElementType.Xpath);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
			webDB.selectDropDownByValue(CustomerScheduleLocators.CSBucketTypeDropDown, BucketType, ElementType.Xpath);
			webDB.pressTabKey();
			commonFunc.hardWait(500);
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	public void addQtyInCS(String FirmQty, String EndDate, String BucketType) {
		commonFunc.checkRefreshCellsDisappear();
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.CSLineWiseFirmQtyField,
				CustomerScheduleLocators.CSLineWiseFirmQtyInputField, FirmQty);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CustomerScheduleLocators.CSLineWiseEndDateField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSLineWiseEndDateField, ElementType.Xpath);
			webDB.sendTextToAnElement(CustomerScheduleLocators.CSLineWiseEndDateInputField, EndDate, ElementType.Xpath);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
			webDB.selectDropDownByValue(CustomerScheduleLocators.CSBucketTypeDropDown, BucketType, ElementType.Xpath);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		}
	}

	/**
	 * This Method Is Used To Click The Line Wise Save Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickLineWiseSaveButton() {
		commonFunc.hardWait(500);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CustomerScheduleLocators.CSLineWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSLineWiseSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSGenSchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Customer Schedule Line Wise details Button Is Clicked Sucessfully");
			}
		}
		commonFunc.hardWait(500);
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Generate Schedule Button In The Header Of
	 * the Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickGenerateScheduleButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CustomerScheduleLocators.CSGenSchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSGenSchButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSPostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Generate Schedule Button Is Clicked Sucessfully");
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Post Button In the Header Of The Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPostButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CustomerScheduleLocators.CSPostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSPostButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used to Forward the Doucmnet from
	 *         One User To Another User
	 * @return boolean flag
	 */
	public boolean forwardForApproval(String ForwardToEmployee) {
		commonFunc.hardWait(200);
		if (webDB.verifyIsElementDisplayed(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			String ForwardEmployee = webDB.getDomPropertyFromElement(SalesOrderLocators.ForwardPersonField,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (!ForwardEmployee.equalsIgnoreCase(ForwardToEmployee)) {
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.ForwardApprovalLov, ForwardToEmployee);
				commonFunc.hardWait(200);
				webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
				logs.logging("INFO", "Switched To WorkFlow Frame");
			}
			flag = webDB.waitForElement(SalesOrderLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CommonUiLocators.ClearIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To SO Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify CS General Similar, DisSimilar Stock And
	 * Customer UOM
	 * 
	 * @author Harikrishna -RMITS
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDomesticCSGeneralSimilarDisSimilarStockAndCustomerUOM(String SimilarItem, String SheetName,
			String Location, String Prefix, String Narration) {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateItemPage();
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String SNO = CSMultipleline[i][0];
			if (!SNO.equals("")) {
				verifySearchOfItem(SimilarItem, SheetName);
			}
		}
		VariableHandler.setMultipleItems(ArrayList);
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
//				System.getProperty("AshokUsername"));
//		approvalFunc.navigateToApprovals();
		for (int i = 1; i <= ArrayList.size(); i++) {
			commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getMultipleItems().get(i - 1), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 4, VariableHandler.getMultipleItems().get(i - 1));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 6, VariableHandler.getMultipleItems().get(i - 1));
		}
//		commonFunc.logoutUser();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		salesOrderFunctions.checkSOMControl(webDB.getDataFromProperties("Cs"));
//		clickCustomerScheduleButton();
//		commonFunc.navigateCustomerScheduleScreen();
		addCustomerScheduleHeaderDetails(Location, Prefix, Narration);
		verifyCustomerScheduleDocumentStatusDraftWithDB();
		verifyCustomerScheduleHeaderDetailsUIValuesWithDBValues();
		verifyCustomerScheduleHeaderDetailsWithDB(Location, Prefix, Narration);
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String CUST_DESC = CSMultipleline[i][2];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String FIRM_QTY = CSMultipleline[i][9];
			String FIRM_PRICE = CSMultipleline[i][10];
			String DISC_PCT = CSMultipleline[i][12];
			String HSN_CODE = CSMultipleline[i][18];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			String BKT_TYPE = CSMultipleline[i][33];
			if (!CUST_ID.equals("")) {
				addLineWiseCustomer(CUST_ID);
				addItemsInCS(ITEM_ID);
				chooseScheduleType(SCH_TYPE);
				addQtyUnitAndMrpCost(FIRM_QTY, FIRM_PRICE, FIRM_PRICE, DISC_PCT, Today, BKT_TYPE);
				chooseHSNCodeInCS(HSN_CODE);
				clickLineWiseSaveButton();
				commonFunc.hardWait(1500);
				getCSLineWiseDocumentNumber(i);
				verifyCustomerScheduleLineWiseDetailsWithDB(SheetName, i);
			}
		}
		VariableHandler.setMultipleCSLineDocs(CSLineDocNo);
		clickGenerateScheduleButton();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifyCustomerScheduleDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifyCustomerScheduleDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Click The Cancel Button In The Header
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCancelButton() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSCancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SOSearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Cancel Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Cancel The Customer Schedule Document
	 * 
	 * @author Harikrishna - RMITS
	 * 
	 * @return boolean flag
	 */
	public boolean cancelCustomerScheduleDocument(String Location, String Prefix, String Narration) {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		salesOrderFunctions.checkSOMControl(webDB.getDataFromProperties("Cs"));
		addCustomerScheduleHeaderDetails(Location, Prefix, Narration);
		verifyCustomerScheduleDocumentStatusDraftWithDB();
		verifyCustomerScheduleHeaderDetailsUIValuesWithDBValues();
		verifyCustomerScheduleHeaderDetailsWithDB(Location, Prefix, Narration);
		clickCancelButton();
		verifyCustomerScheduleDocumentStatusCancelledWithDB();
//		flag = commonFunc.logoutUser();
		return true;
	}

	/**
	 * This Method Is Used To Search The Item In The Find Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param Item1
	 * @param SheetName
	 */
	public void verifySearchOfItem(String Item1, String SheetName) {
		flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.FindScreenItemField, Item1, ElementType.Xpath);
			logs.logging("INFO", "Search is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button is clicked ");
				verifySearchButtonClicking(Item1);
				verifyClickingGeneralTab();
				verifyGenerateItemSim(commonFunc.getDataFromTestDataSheet("CSItem"));
				verifyItemPost();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Search Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifySearchButtonClicking(String Item) {
		flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			logs.logging("INFO", "Search Button is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemDrillDown(Item), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.ItemDrillDown(Item), ElementType.Xpath);
				logs.logging("INFO", "Drill Down item");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The General Tab
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifyClickingGeneralTab() {
		flag = webDB.waitForElement(PricelistPageLocators.ItemGeneral, ElementType.Xpath);
		if (flag) {
			String value = webDB.getDomAttributeFromElement(PricelistPageLocators.ItemGeneral, ElementType.Xpath,
					"aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.javaScriptClickAnElement(PricelistPageLocators.ItemGeneral, ElementType.Xpath);
				logs.logging("INFO", "General  Button is clicked");
			}
			flag = webDB.waitForElement(PricelistPageLocators.ItemCopy, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.ItemCopy, ElementType.Xpath);
				logs.logging("INFO", "Item Copy is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Generate Item
	 * 
	 * @author Harikrishna - RMITS
	 * @param Itm
	 */
	public void verifyGenerateItemSim(String Itm) {
		String ItemSeqNo = RandomStringUtils.randomNumeric(7);
		if (ItemSeqNo.equals("0000")) {
			ItemSeqNo = "10";
		}
		String SimItemId = Itm + ItemSeqNo;

		flag = webDB.waitForElement(PricelistPageLocators.ItemIdInputCopy, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.ItemIdInputCopy, SimItemId, ElementType.Xpath);
			logs.logging("INFO", "Item ID is Entered");

			flag = webDB.waitForElement(PricelistPageLocators.ItemDescInputCopy, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.ItemDescInputCopy, SimItemId, ElementType.Xpath);
				logs.logging("INFO", "Item Desc is Entered");
				webDB.pressTabKey();

				flag = webDB.waitForElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
					logs.logging("INFO", "Ok Button is clicked ");

					flag = webDB.waitForElement(PricelistPageLocators.ItemGetText, ElementType.Xpath);
					if (flag) {
						String SimItemName = webDB.getDomPropertyFromElement(PricelistPageLocators.ItemGetText,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setItemName(SimItemName);
						logs.logging("INFO", "Item ID is:" + VariableHandler.getItemName());
						ArrayList.add(VariableHandler.getItemName());
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Post Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifyItemPost() {
		flag = webDB.waitForElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
			logs.logging("INFO", "Item is Posted");
			flag = webDB.waitForElement(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HodEmpId"));
//				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//						"AdminUsername");
//				commonFunc.navigateItemPage();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose The HSN Code In the CS Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param HSNCode
	 */
	public void chooseHSNCodeInCS(String HSNCode) {
		String text = webDB.getTextFromElement(CustomerScheduleLocators.HsnCode(), ElementType.Xpath);
		if (text == null || text.trim().isEmpty() || !text.equals(HSNCode)) {
			flag = webDB.waitForElement(CustomerScheduleLocators.HsnCode(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CustomerScheduleLocators.HsnCode(), ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSHsnCodeLov, HSNCode);
				webDB.pressTabKey();
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose The Schedule Type
	 * 
	 * @author Harikrishna - RMITS
	 * @param SchType
	 */
	public void chooseScheduleType(String SchType) {
		flag = webDB.waitForElement(CustomerScheduleLocators.CustomerScdhlType(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CustomerScdhlType(), ElementType.Xpath);
			webDB.selectDropDownByValue(CustomerScheduleLocators.CSScheduleDropDown, SchType, ElementType.Xpath);
			logs.logging("INFO", "Schedule Type Is Choosen Sucessfully");
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify CS General Rate Contract Similar, DisSimilar
	 * Stock And Customer UOM
	 * 
	 * @author Harikrishna -RMITS
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDomesticCSGeneralRateContractSimilarDisSimilarStockAndCustomerUOM(String SimilarItem,
			String SheetName, String Location, String Prefix, String Narration) {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateItemPage();
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String SNO = CSMultipleline[i][0];
			if (!SNO.equals("")) {
				verifySearchOfItem(SimilarItem, SheetName);
			}
		}
		VariableHandler.setMultipleItems(ArrayList);
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
//				System.getProperty("AshokUsername"));
//		approvalFunc.navigateToApprovals();
		for (int i = 1; i <= ArrayList.size(); i++) {
			commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getMultipleItems().get(i - 1), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 4, VariableHandler.getMultipleItems().get(i - 1));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 6, VariableHandler.getMultipleItems().get(i - 1));
		}
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateCustomerItemsPage();
		addPLusButton();
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String HSN_CODE = CSMultipleline[i][18];
			String PRICE_CLS = CSMultipleline[i][30];
			String CI_TYPE = CSMultipleline[i][35];
			String PRICE_BASIS = CSMultipleline[i][36];
			String CATALOG_NAME = CSMultipleline[i][38];
			if (!CUST_ID.equals("")) {
				insertCustomerFromLovField(CUST_ID);
				commonFunc.checkRefreshCellsDisappear();
				customerItems(ITEM_ID, PRICE_CLS, PRICE_BASIS, CI_TYPE, CATALOG_NAME);
			}
		}
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		commonFunc.navigateOpenSOScreen();
		openSOFunc.selectOpenSOTypeWithRadioButtons("Open SO");
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String ITEM_ID = CSMultipleline[i][4];
			String FIRM_PRICE = CSMultipleline[i][10];
			String DISC_PCT = CSMultipleline[i][12];
			String HSN_CODE = CSMultipleline[i][18];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			if (!CUST_ID.equals("")) {
				openSOFunc.clickAddNewOpenSOPlsIcon();
				openSOFunc.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CUST_ID,
						commonFunc.getDataFromTestDataSheet("Prefix_OP100"));
				openSOFunc.fillSOTypeAndPONumberInOpenSO(commonFunc.getDataFromTestDataSheet("Type_Standard"),
						RandomStringUtils.randomNumeric(4));
				openSOFunc.clickTickIconAndGetOpenSODocNo(MultiOpenSODoc);
				openSOFunc.clickFetchCustomerAndSelectItemInOpenSO(ITEM_ID);
				openSOFunc.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(FIRM_PRICE, FIRM_PRICE, HSN_CODE);
				openSOFunc.fillDiscountAndSaveLinesforAddedLastItemInOpenSO(DISC_PCT);
				openSOFunc.enterEffectiveFromAndToDate(Today, FIRM_TO_DATE, Today);
				openSOFunc.clickHeaderSaveAndClickPostBtnInOpenSO();
				approvalFunc.waitingForApprovalWithFordwarderOSO(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
			}
		}
		VariableHandler.setMultipleOpenSoDocs(MultiOpenSODoc);
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
//				"HariUsername");
//		approvalFunc.navigateToApprovals();
		for (int i = 1; i <= MultiOpenSODoc.size(); i++) {
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getMultipleOpenSoDocs().get(i - 1));
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMultipleOpenSoDocs().get(i - 1));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getMultipleOpenSoDocs().get(i - 1), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderPage();
		salesOrderFunctions.checkSOMControl(webDB.getDataFromProperties("Cs"));
		addCustomerScheduleHeaderDetails(Location, Prefix, Narration);
		verifyCustomerScheduleDocumentStatusDraftWithDB();
		verifyCustomerScheduleHeaderDetailsUIValuesWithDBValues();
		verifyCustomerScheduleHeaderDetailsWithDB(Location, Prefix, Narration);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String CUST_DESC = CSMultipleline[i][2];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String FIRM_QTY = CSMultipleline[i][9];
			String FIRM_PRICE = CSMultipleline[i][10];
			String DISC_PCT = CSMultipleline[i][12];
			String HSN_CODE = CSMultipleline[i][18];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			String BKT_TYPE = CSMultipleline[i][33];
			if (!CUST_ID.equals("")) {
				addLineWiseCustomer(CUST_ID);
				addItemsInCS(ITEM_ID);
				chooseScheduleType(SCH_TYPE);
				addQtyInCS(FIRM_QTY, FIRM_TO_DATE, BKT_TYPE);
				chooseHSNCodeInCS(HSN_CODE);
				clickLineWiseSaveButton();
				commonFunc.hardWait(1500);
			}
		}
		clickGenerateScheduleButton();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifyCustomerScheduleDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifyCustomerScheduleDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used Add The Customer Items
	 * 
	 * @author Harikrishna - RMITS
	 * @param Items
	 * @param PriceClass
	 * @param PriceBasis
	 * @param CIType
	 * @param Catalog
	 */
	public void customerItems(String Items, String PriceClass, String PriceBasis, String CIType, String Catalog) {
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag(Items);
		priceClassSelectStdFormat(PriceClass);
		priceBasisSelectInCustomerItems(PriceBasis);
		if (PriceBasis.equalsIgnoreCase("Catalog Price")) {
			catalogSelectInCustomerItems(Catalog);
		}
		clickDefaltFlag();
		saveButtoninCustomerItems();
	}

	/**
	 * Catalogue Select In Customer Items
	 * 
	 * @author Kowshik To Select Catalogue
	 */
	public void catalogSelectInCustomerItems(String catalogName) {
		webDB.waitForClickElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
		flag = webDB.moveToElement(CustomerItemsLocators.CatalogSelectDropdown, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(CustomerItemsLocators.CatalogSelectDropdown, catalogName,
					ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Catalog Fixed Done Successfully" + catalogName);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Add Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void addPLusButton() {
		flag = webDB.waitForElement(CustomerItemsLocators.AddPLusButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.AddPLusButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.CustomerLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Add Button is Clicked ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose The Customer
	 * 
	 * @author Harikrishna - RMITS
	 * @param Customer
	 */
	public void insertCustomerFromLovField(String Customer) {
		flag = webDB.waitForClickElement(CustomerItemsLocators.CustomerLovButton, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(CustomerItemsLocators.CustomerLovButton, Customer);
			flag = webDB.waitForElement(CustomerItemsLocators.AddPlusButtonItems, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Customer Name Passed");
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Add Button In The Customer Items
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void addPlusInCustomerItem() {
		flag = webDB.waitForElement(CustomerItemsLocators.AddPlusButtonItems, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.AddPlusButtonItems, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Customer Items Add Button is Clicked");
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Insert The Item In Customer Items Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param Items
	 */
	public void insertIteminCustomerItemsAfterCustomerAssoFlag(String Items) {
		flag = webDB.waitForClickElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(CustomerItemsLocators.ItemLovButton, Items);
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Select Price Class
	 * 
	 * @author Harikrishna - RMITS
	 * 
	 * @param priceClassName
	 */
	public void priceClassSelectStdFormat(String priceClassName) {

		flag = webDB.waitForElement(CustomerItemsLocators.Pricecls(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.Pricecls(), ElementType.Xpath);
			webDB.selectDropDownByText(CustomerItemsLocators.PriceClassSelectDropdown, priceClassName,
					ElementType.Xpath);
//			webDB.waitForElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Price Class " + priceClassName + " Is Choosen Sucessfully ");
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose The Price Basis
	 * 
	 * @author Harikrishna - RMITS
	 * @param priceBasisValue
	 */
	public void priceBasisSelectInCustomerItems(String priceBasisValue) {
		flag = webDB.waitForElement(CustomerItemsLocators.PriceBasisCL(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.PriceBasisCL(), ElementType.Xpath);
			webDB.selectDropDownByText(CustomerItemsLocators.PriceBasisSelectDropdown, priceBasisValue,
					ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Price Basis " + priceBasisValue + " is Choosen Sucessfully ");
				webDB.pressTabKey();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Save Button In The Customer Items Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void saveButtoninCustomerItems() {
		flag = webDB.waitForElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
			if (webDB.isElementDisplayed(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Filter The Item In The Customer Item Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void filltersaItemCustomerItems() {
		flag = webDB.waitForElement(CustomerItemsLocators.CustomerItemsSearchBox, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(CustomerItemsLocators.CustomerItemsSearchBox, VariableHandler.getItemName(),
					ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.Gobutton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Searched Item in Customer screen ");
				flag = webDB.waitForElement(CustomerItemsLocators.Gobutton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CustomerItemsLocators.Gobutton, ElementType.Xpath);
					flag = webDB.waitForElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Successfully Clicked a GO button");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Default Flag
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickDefaltFlag() {
		flag = webDB.waitForElement(CustomerItemsLocators.DefaultFlagField(), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CustomerItemsLocators.DefaultFlagField(), ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose The Item Type
	 * 
	 * @author Harikrishna - RMITS
	 * @param CIType
	 */
	public void chooseCustomerItemType(String CIType) {
		flag = webDB.waitForElement(CustomerScheduleLocators.CIDropDownType, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByValue(CustomerScheduleLocators.CIDropDownType, CIType, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			String CustItemType = webDB.getTextFromElement(CustomerScheduleLocators.CITypeField, ElementType.Xpath);
			if (CIType.equals(CustItemType)) {
				logs.logging("INFO", "Given Customer Items Type Is Choosen Sucessfully In The FrontEnd DropDown");
			} else {
				logs.logging("INFO",
						"Customer Item Type Is Not Choosen In The FrontEnd DropDown Based On The Given Input");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify CS General Price List Similar, DisSimilar Stock
	 * And Customer UOM
	 * 
	 * @author Harikrishna -RMITS
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDomesticCSGeneralPriceListSimilarDisSimilarStockAndCustomerUOM(String SimilarItem,
			String SheetName, String Location, String Prefix, String Narration) {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateItemPage();
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String SNO = CSMultipleline[i][0];
			if (!SNO.equals("")) {
				verifySearchOfItem(SimilarItem, SheetName);
			}
		}
		VariableHandler.setMultipleItems(ArrayList);
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
//				System.getProperty("AshokUsername"));
//		approvalFunc.navigateToApprovals();
		for (int i = 1; i <= ArrayList.size(); i++) {
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getMultipleItems().get(i - 1));
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMultipleItems().get(i - 1));
			commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getMultipleItems().get(i - 1), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 4, VariableHandler.getMultipleItems().get(i - 1));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 6, VariableHandler.getMultipleItems().get(i - 1));
		}
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateCustomerItemsPage();
		addPLusButton();
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String HSN_CODE = CSMultipleline[i][18];
			String PRICE_CLS = CSMultipleline[i][30];
			String CI_TYPE = CSMultipleline[i][35];
			String PRICE_BASIS = CSMultipleline[i][36];
			String CATALOG_NAME = CSMultipleline[i][38];
			if (!CUST_ID.equals("")) {
				insertCustomerFromLovField(CUST_ID);
				commonFunc.checkRefreshCellsDisappear();
				customerItems(ITEM_ID, PRICE_CLS, PRICE_BASIS, CI_TYPE, CATALOG_NAME);
			}
		}
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		commonFunc.navigatePriceList_Standard_Catalog_Screen();
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String ITEM_ID = CSMultipleline[i][4];
			String FIRM_PRICE = CSMultipleline[i][10];
			String DISC_PCT = CSMultipleline[i][12];
			String HSN_CODE = CSMultipleline[i][18];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			if (!CUST_ID.equals("")) {
				clickAddButton();
				clickPriceListButton(commonFunc.getDataFromTestDataSheet("PriceListSingle"));
				clickPlusButton();
				insertLocationLov(commonFunc.getDataFromTestDataSheet("Loc_100"));
				insertPricelistPrefix(commonFunc.getDataFromTestDataSheet("PriceListPrefix"));
				insertCustomerField(CUST_ID);
				insertItemField(ITEM_ID);
				insertSalesPrice(FIRM_PRICE, DISC_PCT);
				clickSaveButton();
//				insertEffitiveDates(Today, FIRM_TO_DATE);
//				clickSaveButton();
				getPriceListDocumentno(MultiPriceList);
				clickPostButton1();
				verifyWaitingForApproval1(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		VariableHandler.setMultiplePriceListDocs(MultiPriceList);
		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
//				"HariUsername");
//		approvalFunc.navigateToApprovals();
		for (int i = 1; i <= MultiPriceList.size(); i++) {
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getMultiplePriceListDocs().get(i - 1));
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMultiplePriceListDocs().get(i - 1));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getMultiplePriceListDocs().get(i - 1), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderPage();
		salesOrderFunctions.checkSOMControl(webDB.getDataFromProperties("Cs"));
		addCustomerScheduleHeaderDetails(Location, Prefix, Narration);
		verifyCustomerScheduleDocumentStatusDraftWithDB();
		verifyCustomerScheduleHeaderDetailsUIValuesWithDBValues();
		verifyCustomerScheduleHeaderDetailsWithDB(Location, Prefix, Narration);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String CUST_DESC = CSMultipleline[i][2];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String FIRM_QTY = CSMultipleline[i][9];
			String FIRM_PRICE = CSMultipleline[i][10];
			String DISC_PCT = CSMultipleline[i][12];
			String HSN_CODE = CSMultipleline[i][18];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			String BKT_TYPE = CSMultipleline[i][33];
			if (!CUST_ID.equals("")) {
				addLineWiseCustomer(CUST_ID);
				addItemsInCS(ITEM_ID);
				chooseScheduleType(SCH_TYPE);
				addQtyInCS(FIRM_QTY, FIRM_TO_DATE, BKT_TYPE);
				chooseHSNCodeInCS(HSN_CODE);
				clickLineWiseSaveButton();
				commonFunc.hardWait(1500);
			}
		}
		clickGenerateScheduleButton();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifyCustomerScheduleDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifyCustomerScheduleDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * Click Add Button
	 * 
	 * @author KowshiK-RMITS
	 */
	public void clickAddButton() {
		flag = webDB.waitForElement(SalesPriceListLocators.AddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.AddButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.PriceList, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked Add Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Price List Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPriceListButton(String PLType) {
		flag = webDB.waitForElement(SalesPriceListLocators.PriceList, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.PriceList, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.PriceListSingle1, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked Price List");
				if (PLType.contains("Multi")) {
					flag = webDB.waitForElement(SalesPriceListLocators.PriceListMulti, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesPriceListLocators.PriceListMulti, ElementType.Xpath);
						flag = webDB.waitForElement(SalesPriceListLocators.MultiAddButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Price List Multi Button Is Clicked Successfully");
						}
					}
				} else {
					flag = webDB.waitForElement(SalesPriceListLocators.PriceListSingle1, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesPriceListLocators.PriceListSingle1, ElementType.Xpath);
						flag = webDB.waitForElement(SalesPriceListLocators.PlusButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Price List Single Button Is Clicked Successfully");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Plus Button Price List Screen
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPlusButton() {
		flag = webDB.waitForElement(SalesPriceListLocators.PlusButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(2000);
			webDB.moveToElement(SalesPriceListLocators.PlusButton, ElementType.Xpath);
			webDB.clickAnElement(SalesPriceListLocators.PlusButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.LocationLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked Plus Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Loation Id
	 * 
	 * @author Kowshik-RMITS
	 * @param LOCATION100
	 */
	public void insertLocationLov(String LOCATION100) {
		commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.LocationLovButton, LOCATION100);
		flag = webDB.waitForElement(SalesPriceListLocators.PricePfxFiled, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Inserted Locution " + LOCATION100);
//			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * Insert Pricelist Prefix
	 * 
	 * @author Kowshik-RMITS
	 * @param Prefix
	 */
	public void insertPricelistPrefix(String Prefix) {
		flag = webDB.waitForElement(SalesPriceListLocators.PricePfxFiled, ElementType.Xpath);
		if (flag) {
			String GetText = webDB.getTextFromElement(SalesPriceListLocators.PricePfxFiled, ElementType.Xpath);
			System.out.println(GetText);
			if (!GetText.equalsIgnoreCase(Prefix)) {
				webDB.clickAnElement(SalesPriceListLocators.PricePfxFiled, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.PriceListPfx, Prefix);
				webDB.pressTabKey();
			}
			flag = webDB.waitForElement(SalesPriceListLocators.CustomerIdField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Inserted Prefix" + Prefix);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Customer Field PriceList
	 * 
	 * @author Kowshik-RMITS
	 * @param CUSTOMER_ID
	 */
	public void insertCustomerField(String CUSTOMER_ID) {
		flag = webDB.waitForElement(SalesPriceListLocators.CustomerIdField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.CustomerIdField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.CustomerFieldLov, CUSTOMER_ID);
			flag = webDB.waitForElement(SalesPriceListLocators.ItemField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Inserted Customer Id" + CUSTOMER_ID);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Inserted Item PriceList
	 * 
	 * @author Kowshik-RMITS
	 * @param ITEMPL
	 */
	public void insertItemField(String Item) {
		flag = webDB.waitForElement(SalesPriceListLocators.ItemField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.ItemField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.ItemFieldLov, Item);
			if (flag) {
				logs.logging("INFO", "Successfully Inseted Item" + Item);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Inserted Sales Price
	 * 
	 * @author Kowshik-RMITS
	 * @param Sales_Value
	 */
	public void insertSalesPrice(String Sales_Value, String Disc_Value) {
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesPriceListLocators.SalesPriceFields(1),
				SalesPriceListLocators.SalesPrice, Sales_Value);
		flag = webDB.waitForElement(SalesPriceListLocators.SalesDiscFields(1), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Entered a Sales Values");
			salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesPriceListLocators.SalesDiscFields(1),
					SalesPriceListLocators.SalesDiscPercent, Disc_Value);
			flag = webDB.waitForElement(SalesPriceListLocators.SaveButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Entered a Discount Values");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click a Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButton() {
//		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesPriceListLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(SalesPriceListLocators.SaveButton, ElementType.Xpath);
			logs.logging("INFO", "Successfully Save Button Record");
			commonFunc.hardWait(500);
			commonFunc.checkRefreshCellsDisappear();
		}
	}

	/**
	 * To Click Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSPSaveButton() {
		flag = webDB.waitForElement(SalesStandardPriceLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesStandardPriceLocators.SaveButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SalesStandardPriceLocators.SaveButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesStandardPriceLocators.ActiveButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Saved a Record ");
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This is Insert a Efficitive From Date & Efficitive To Date post
	 * 
	 * @author Kowshik-RMITS
	 * @param EFF_DATE
	 * @param EFF_TO
	 * 
	 */
	public void insertEffitiveDates(String EFF_DATE, String EFF_TO) {
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesPriceListLocators.EffDateField(1),
				SalesPriceListLocators.EffiticeFrom, EFF_DATE);
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesPriceListLocators.EffDateTo(1),
				SalesPriceListLocators.EffiticeTo, EFF_TO);
		commonFunc.hardWait(2000);
	}

	/**
	 * Get Document Number Of Price List
	 * 
	 * @author Kowshik-RMITS
	 */
	public void getPriceListDocumentno(ArrayList<String> DocNum) {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesPriceListLocators.Documentno(1), ElementType.Xpath);
		if (flag) {
			String PriceListdocno = webDB.getTextFromElement(SalesPriceListLocators.Documentno(1), ElementType.Xpath);
			VariableHandler.setPriceListDocno(PriceListdocno);
			logs.logging("info", "Get Price List Document no " + VariableHandler.getPriceListDocno());
//			commonFunc.hardWait(3000);
			DocNum.add(VariableHandler.getPriceListDocno());
			commonFunc.checkRefreshCellsDisappear();
		}
	}

	/**
	 * Click Post Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPostButton1() {

		flag = webDB.waitForElement(SalesPriceListLocators.PR_Post_Btn, ElementType.Xpath);
		if (flag) {
//			commonFunc.hardWait(2000);
			webDB.moveToElement(SalesPriceListLocators.PR_Post_Btn, ElementType.Xpath);

			webDB.clickAnElement(SalesPriceListLocators.PR_Post_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.PR_Workflow_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PriceList Post Button Clicked Sucessfully");
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verifty Waiting For Approval
	 * 
	 * @author Kowshik-RMITS
	 * @return
	 */
	private boolean verifyWaitingForApproval1(String Fwd_person) {
//		flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath, 5);
		if (flag) {
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			String GetText = webDB.getDomPropertyFromElement(CommonUiLocators.WF_Fwd_To_person, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (!GetText.equals(Fwd_person)) {
				commonFunc.searchAndSelectValueInFilters(CommonUiLocators.WorkAppoval_ForwardLovBtn, Fwd_person);
				webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
				webDB.switchToDefaultContent();
				assertTrue(flag);
			} else {
				flag = false;
				VariableHandler.setApprovalFlag(flag);
			}
		}
		return flag;
	}

	/**
	 * Waiting For Approval
	 * 
	 * @author Kowshik-RMITS
	 * @return
	 */
	private boolean verifyWaitingForApprovalCP() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.CPForwardLOV,
					commonFunc.getDataFromTestDataSheet("HodValue"));
//			 commonFunc.hardWait(2000);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpsoOkButtonForwardCat, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(OpenSalesOrderLocators.OpsoOkButtonForwardCat, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify CS General Standard Price Similar, DisSimilar
	 * Stock And Customer UOM
	 * 
	 * @author Harikrishna -RMITS
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDomesticCSGeneralStandardPriceSimilarDisSimilarStockAndCustomerUOM(String SimilarItem,
			String SheetName, String Location, String Prefix, String Narration) {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateItemPage();
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String SNO = CSMultipleline[i][0];
			if (!SNO.equals("")) {
				verifySearchOfItem(SimilarItem, SheetName);
			}
		}
		VariableHandler.setMultipleItems(ArrayList);
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
//				System.getProperty("AshokUsername"));
//		approvalFunc.navigateToApprovals();
		for (int i = 1; i <= ArrayList.size(); i++) {
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getMultipleItems().get(i - 1));
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMultipleItems().get(i - 1));
			commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getMultipleItems().get(i - 1), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 4, VariableHandler.getMultipleItems().get(i - 1));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 6, VariableHandler.getMultipleItems().get(i - 1));
		}
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateCustomerItemsPage();
		addPLusButton();
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String HSN_CODE = CSMultipleline[i][18];
			String PRICE_CLS = CSMultipleline[i][30];
			String CI_TYPE = CSMultipleline[i][35];
			String PRICE_BASIS = CSMultipleline[i][36];
			String CATALOG_NAME = CSMultipleline[i][38];
			if (!CUST_ID.equals("")) {
				insertCustomerFromLovField(CUST_ID);
				commonFunc.checkRefreshCellsDisappear();
				customerItems(ITEM_ID, PRICE_CLS, PRICE_BASIS, CI_TYPE, CATALOG_NAME);
			}
		}
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		commonFunc.navigatePriceList_Standard_Catalog_Screen();
		clickAddButton();
		clickStandardPriceButton();
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String ITEM_ID = CSMultipleline[i][4];
			String FIRM_PRICE = CSMultipleline[i][10];
			String DISC_PCT = CSMultipleline[i][12];
			String HSN_CODE = CSMultipleline[i][18];
			String PRICE_CLS = CSMultipleline[i][30];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			String CURCY = CSMultipleline[i][37];
			if (!CUST_ID.equals("")) {
				clickSPAddbutton();
				clickLocutionField(commonFunc.getDataFromTestDataSheet("Loc_100"));
				insertItemIdStandardPrice(ITEM_ID);
				insertCurcyField(CURCY);
				insertMapPriceStdPrice(FIRM_PRICE);
				insertMinDisc("1");
				insertMaxDisc(DISC_PCT);
				insertPriceClass(PRICE_CLS);
				clickSPSaveButton();
				clickActiveButton();
			}
		}
		commonFunc.navigateSalesOrderPage();
		salesOrderFunctions.checkSOMControl(webDB.getDataFromProperties("Cs"));
		addCustomerScheduleHeaderDetails(Location, Prefix, Narration);
		verifyCustomerScheduleDocumentStatusDraftWithDB();
		verifyCustomerScheduleHeaderDetailsUIValuesWithDBValues();
		verifyCustomerScheduleHeaderDetailsWithDB(Location, Prefix, Narration);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String CUST_DESC = CSMultipleline[i][2];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String FIRM_QTY = CSMultipleline[i][9];
			String FIRM_PRICE = CSMultipleline[i][10];
			String DISC_PCT = CSMultipleline[i][12];
			String HSN_CODE = CSMultipleline[i][18];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			String BKT_TYPE = CSMultipleline[i][33];
			if (!CUST_ID.equals("")) {
				addLineWiseCustomer(CUST_ID);
				addItemsInCS(ITEM_ID);
				chooseScheduleType(SCH_TYPE);
				addQtyInCS(FIRM_QTY, Today, BKT_TYPE);
				chooseHSNCodeInCS(HSN_CODE);
				clickLineWiseSaveButton();
				commonFunc.hardWait(1500);
			}
		}
		clickGenerateScheduleButton();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifyCustomerScheduleDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifyCustomerScheduleDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * Insert Successfully Min Disc Percent
	 * 
	 * @author Kowshik-RMITS
	 * @param Min_Disc_Value
	 */
	public void insertMinDisc(String Min_Disc_Value) {
		flag = webDB.waitForElement(SalesStandardPriceLocators.MinPriceField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.MinPriceField, ElementType.Xpath);
			webDB.doubleClickAnElement(SalesStandardPriceLocators.MinPriceField, ElementType.Xpath);

			flag = webDB.waitForElement(SalesStandardPriceLocators.min_DiscInput, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(SalesStandardPriceLocators.min_DiscInput, ElementType.Xpath);
				webDB.clickAnElement(SalesStandardPriceLocators.min_DiscInput, ElementType.Xpath);
				salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesStandardPriceLocators.Min_Disc(1),
						SalesStandardPriceLocators.min_DiscInput, Min_Disc_Value);
				logs.logging("INFO", "Successfully Inserted  Min Disc " + Min_Disc_Value);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Add Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSPAddbutton() {
		flag = webDB.waitForElement(SalesStandardPriceLocators.AddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.AddButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesStandardPriceLocators.LocutionIdClick, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a AddButton");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Standard Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickStandardPriceButton() {
		flag = webDB.waitForElement(SalesStandardPriceLocators.Standardprice, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.Standardprice, ElementType.Xpath);
			flag = webDB.waitForElement(SalesStandardPriceLocators.AddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a Standard Price");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Locution Id
	 * 
	 * @author Kowshik-RMITS
	 * @param LOCATION100
	 */
	public void clickLocutionField(String L100) {

		flag = webDB.waitForElement(SalesStandardPriceLocators.LocutionIdClick, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesStandardPriceLocators.LocutionIdLov, L100);
			if (flag) {
				logs.logging("Info", "Successfully Insert a Locution Id" + L100);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add One Item In The Standard Price
	 * 
	 * @author Harikrishna - RMITS
	 * @param Item
	 */
	public void insertItemIdStandardPrice(String Item) {

		flag = webDB.waitForElement(SalesStandardPriceLocators.ItemIdField(1), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.ItemIdField(1), ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesStandardPriceLocators.ItemIdLov, Item);
				webDB.waitForElement(SalesStandardPriceLocators.CurcyField, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose The Currency
	 * 
	 * @author Harikrishna - RMITS
	 * @param CatalogueCurcy
	 */
	public void insertCurcyField(String CatalogueCurcy) {

		flag = webDB.waitForElement(SalesStandardPriceLocators.CurcyField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.CurcyField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesStandardPriceLocators.CurcyLov, CatalogueCurcy);
			webDB.pressTabKey();
			webDB.waitForElement(SalesStandardPriceLocators.MapPriceField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", " Successfully Inserted a Curcy Field " + CatalogueCurcy);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Insert The MRP Price
	 * 
	 * @author Harikrishna - RMITS
	 * @param Map_Value
	 */
	public void insertMapPriceStdPrice(String Map_Value) {
		webDB.waitForElement(SalesStandardPriceLocators.MapPriceField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.MapPriceField, ElementType.Xpath);
			webDB.doubleClickAnElement(SalesStandardPriceLocators.MapPriceField, ElementType.Xpath);

			webDB.waitForElement(SalesStandardPriceLocators.MapPriceInput, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(SalesStandardPriceLocators.MapPriceInput, ElementType.Xpath);
				webDB.clickAnElement(SalesStandardPriceLocators.MapPriceInput, ElementType.Xpath);
				salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesStandardPriceLocators.MapPriceField(1),
						SalesStandardPriceLocators.MapPriceInput, Map_Value);
				if (flag) {
					logs.logging("INFO", "Successfully Insert Map Value" + Map_Value);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Landing Cost
	 * 
	 * @author Kowshik-RMITS
	 * @param Landing_Cost
	 */
	public void insertLandingCost(String Landing_Cost) {
		webDB.waitForElement(SalesStandardPriceLocators.LandingCostField(2), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesStandardPriceLocators.LandingCostInput, ElementType.Xpath);
			webDB.clickAnElement(SalesStandardPriceLocators.LandingCostInput, ElementType.Xpath);
			salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesStandardPriceLocators.LandingCostField(2),
					SalesStandardPriceLocators.LandingCostInput, Landing_Cost);
			if (flag) {
				logs.logging("INFO", "Successfully Insert Landing Cost" + Landing_Cost);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Successfully Max Disc Percent
	 * 
	 * @author Kowshik-RMITS
	 * @param Max_Disc_Value
	 */
	public void insertMaxDisc(String Max_Disc_Value) {
		flag = webDB.waitForElement(SalesStandardPriceLocators.MaxDiscField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.MaxDiscField, ElementType.Xpath);
			webDB.doubleClickAnElement(SalesStandardPriceLocators.MaxDiscField, ElementType.Xpath);

			flag = webDB.waitForElement(SalesStandardPriceLocators.MaxDiscInput, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(SalesStandardPriceLocators.MaxDiscInput, ElementType.Xpath);
				webDB.clickAnElement(SalesStandardPriceLocators.MaxDiscInput, ElementType.Xpath);
				salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesStandardPriceLocators.MaxDiscField,
						SalesStandardPriceLocators.MaxDiscInput, Max_Disc_Value);
				logs.logging("INFO", "Successfully Inserted  Min Disc " + Max_Disc_Value);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Successfully Price Class Against To The STD Price Class
	 * 
	 * @author Kowshik-RMITS
	 * @param Max_Disc_Value
	 */
	public void insertPriceClass(String priceClassName) {
		flag = webDB.waitForElement(SalesStandardPriceLocators.PriceClassClick, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.PriceClassClick, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesStandardPriceLocators.PriceClassLov, priceClassName);
			webDB.waitForElement(SalesStandardPriceLocators.SaveButton, ElementType.Xpath);
			logs.logging("INFO", " Successfully Inserted a Curcy Field " + priceClassName);
		}
		assertTrue(flag);
	}

	/**
	 * To Click Active Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickActiveButton() {
		commonFunc.hardWait(4000);
		flag = webDB.waitForElement(SalesStandardPriceLocators.ActiveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.ActiveButton, ElementType.Xpath);
			logs.logging("INFO", "Succeessfully Active Record");
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify CS General Catalog Price Similar, DisSimilar
	 * Stock And Customer UOM
	 * 
	 * @author Harikrishna -RMITS
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDomesticCSGeneralCatalogPriceSimilarDisSimilarStockAndCustomerUOM(String SimilarItem,
			String SheetName, String Location, String Prefix, String Narration) {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateItemPage();
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String SNO = CSMultipleline[i][0];
			if (!SNO.equals("")) {
				verifySearchOfItem(SimilarItem, SheetName);
			}
		}
		VariableHandler.setMultipleItems(ArrayList);
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
//				System.getProperty("AshokUsername"));
//		approvalFunc.navigateToApprovals();
		for (int i = 1; i <= ArrayList.size(); i++) {
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getMultipleItems().get(i - 1));
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMultipleItems().get(i - 1));
			commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getMultipleItems().get(i - 1), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 4, VariableHandler.getMultipleItems().get(i - 1));
			excelUtils.writeDataInExcel(CSFilepath, SheetName, i + 3, 6, VariableHandler.getMultipleItems().get(i - 1));
		}
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateCustomerItemsPage();
		addPLusButton();
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String HSN_CODE = CSMultipleline[i][18];
			String PRICE_CLS = CSMultipleline[i][30];
			String CI_TYPE = CSMultipleline[i][35];
			String PRICE_BASIS = CSMultipleline[i][36];
			String CATALOG_NAME = CSMultipleline[i][38];
			if (!CUST_ID.equals("")) {
				insertCustomerFromLovField(CUST_ID);
				commonFunc.checkRefreshCellsDisappear();
				customerItems(ITEM_ID, PRICE_CLS, PRICE_BASIS, CI_TYPE, CATALOG_NAME);
			}
		}
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		commonFunc.navigatePriceList_Standard_Catalog_Screen();
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String ITEM_ID = CSMultipleline[i][4];
			String FIRM_PRICE = CSMultipleline[i][10];
			String DISC_PCT = CSMultipleline[i][12];
			String HSN_CODE = CSMultipleline[i][18];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			String CURCY = CSMultipleline[i][37];
			String CATALOG_NAME = CSMultipleline[i][38];
			if (!CUST_ID.equals("")) {
				clickAddButton();
				clickCataloguePriceButton();
				clickAddButtonCatalogueScreen();
				commonFunc.hardWait(1000);
				insertItemCataloguePrice(ITEM_ID);
				insertCatalogueAgainstToItem(CATALOG_NAME);
				insertCurcyAgainstToItem(CURCY);
				insertCataloguePrice(FIRM_PRICE, "1", DISC_PCT);
				insertEffFromAndEffToDatesCataloguePrice(Today, Today);
				clickSaveButtonCataloguePrice();
				getCataloguePriceDocumentno(MultiCatalogPriceDoc);
				commonFunc.checkRefreshCellsDisappear();
				clickCPPostButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyWaitingForApproval1(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(CustomerScheduleLocators.CatalogFindButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CustomerScheduleLocators.CatalogFindButton, ElementType.Xpath);
				}
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(2000);
			}
		}
		VariableHandler.setMultipleCatalogDocs(MultiCatalogPriceDoc);
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
//				"HariUsername");
//		approvalFunc.navigateToApprovals();
		for (int i = 1; i <= MultiCatalogPriceDoc.size(); i++) {
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getMultipleCatalogDocs().get(i - 1));
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMultipleCatalogDocs().get(i - 1));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getMultipleCatalogDocs().get(i - 1), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderPage();
		salesOrderFunctions.checkSOMControl(webDB.getDataFromProperties("Cs"));
		addCustomerScheduleHeaderDetails(Location, Prefix, Narration);
		verifyCustomerScheduleDocumentStatusDraftWithDB();
		verifyCustomerScheduleHeaderDetailsUIValuesWithDBValues();
		verifyCustomerScheduleHeaderDetailsWithDB(Location, Prefix, Narration);
		for (int i = 4; i < CSMultipleline.length; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String CUST_DESC = CSMultipleline[i][2];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String FIRM_QTY = CSMultipleline[i][9];
			String FIRM_PRICE = CSMultipleline[i][10];
			String DISC_PCT = CSMultipleline[i][12];
			String HSN_CODE = CSMultipleline[i][18];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			String BKT_TYPE = CSMultipleline[i][33];
			if (!CUST_ID.equals("")) {
				addLineWiseCustomer(CUST_ID);
				addItemsInCS(ITEM_ID);
				chooseScheduleType(SCH_TYPE);
				addQtyInCS(FIRM_QTY, Today, BKT_TYPE);
				chooseHSNCodeInCS(HSN_CODE);
				clickLineWiseSaveButton();
				commonFunc.hardWait(1500);
			}
		}
		clickGenerateScheduleButton();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifyCustomerScheduleDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifyCustomerScheduleDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Add Catalog Price
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void catalogPrice() {
		clickCataloguePriceButton();
		clickAddButtonCatalogueScreen();
		commonFunc.hardWait(2000);
		insertCatalogueAgainstToItem(commonFunc.getDataFromTestDataSheet("catalogName"));
		insertCurcyAgainstToItem(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertEffFromAndEffToDatesCataloguePrice(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButtonCataloguePrice();
		clickPostButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyWaitingForApproval1(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
	}

	/**
	 * Click Catalogue Price Button in Find Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickCataloguePriceButton() {
		flag = webDB.waitForElement(SalesCatalogueLocators.CataloguePriceButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.CataloguePriceButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesCatalogueLocators.AddButtonCatalogue, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked Catalogue Price");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Inserted Catalogue Price Item
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickAddButtonCatalogueScreen() {
		flag = webDB.waitForElement(SalesCatalogueLocators.AddButtonCatalogue, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.AddButtonCatalogue, ElementType.Xpath);
			flag = webDB.waitForElement(SalesCatalogueLocators.CatalogueItemField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a Item Lov");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Inserted a Item In Catalogue Price Master Screen
	 * 
	 * @author Kowshik-RMITS
	 */
	public void insertItemCataloguePrice(String Item) {

		flag = webDB.waitForElement(SalesCatalogueLocators.CatalogueItemField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.CatalogueItemField, ElementType.Xpath);
			webDB.moveToElement(SalesCatalogueLocators.CatalogueItemField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesCatalogueLocators.CatalogueItemLov, Item);
			if (flag) {
				logs.logging("INFO", "Successfully Inserted a Catalogue Item " + VariableHandler.getItemName());
			}
		}
		assertTrue(flag);
	}

	/**
	 * TO Inserted a Catalogue Against To Items
	 * 
	 * @author Kowshik-Rmits
	 * @param catalogName
	 */
	public void insertCatalogueAgainstToItem(String catalogName) {
		flag = webDB.waitForElement(SalesCatalogueLocators.CatalogueField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.CatalogueField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesCatalogueLocators.CatalogueFieldLov, catalogName);
			if (flag) {
				logs.logging("INFO", "Successfully Inserted a Catalogue Price" + catalogName);
				webDB.pressTabKey();
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Curcy Against To Item
	 * 
	 * @author Kowshik-RMITS
	 * @param CatalogueCurcy
	 */
	public void insertCurcyAgainstToItem(String CatalogueCurcy) {

		commonFunc.searchAndSelectValueInFilters(SalesCatalogueLocators.CurcyFieldLov, CatalogueCurcy);
		webDB.pressEnterKey();
		flag = webDB.waitForElement(SalesCatalogueLocators.PriceFieldClick, ElementType.Xpath);
		if (flag)
			logs.logging("INFO", "Successfully Inserted a Curcy " + CatalogueCurcy);

		assertTrue(flag);
	}

	/**
	 * Insert a Catalogue Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void insertCataloguePrice(String Sales_Value, String min_Disc, String max_Disc) {
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesCatalogueLocators.PriceFieldClick,
				SalesCatalogueLocators.PriceField, Sales_Value);
		if (flag) {
			logs.logging("INFO", "Successfully Inserted a Sales Prices " + Sales_Value);

			salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesCatalogueLocators.MinDiscountPercentField(1),
					SalesCatalogueLocators.MinDiscInsert, min_Disc);
			if (flag) {
				logs.logging("INFO", " Successfully Inseted a Mini Disc Percent" + min_Disc);
				salesOrderFunctions.verifyClickAndEnterValueInTableFields(
						SalesCatalogueLocators.MaxDiscountPercentField(1), SalesCatalogueLocators.MaxDiscInsert,
						max_Disc);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Clicked a Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButtonCataloguePrice() {

		flag = webDB.waitForElement(SalesCatalogueLocators.Savebutton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.Savebutton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesCatalogueLocators.PostButton, ElementType.CssSelector);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a SaveButton");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert a Efficitove date & Efficitive To
	 * 
	 * @author Kowshik-RMITS
	 * @param EFF_DATE
	 * @param EFF_TO
	 */
	public void insertEffFromAndEffToDatesCataloguePrice(String EFF_DATE, String EFF_TO) {
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesCatalogueLocators.EffitiveFromDateField(1),
				SalesCatalogueLocators.EffFromDateClick, EFF_DATE);
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(SalesCatalogueLocators.EffToDateField(1),
				SalesCatalogueLocators.EffToDateClick, EFF_TO);
		commonFunc.hardWait(2000);
	}

	/**
	 * Catalogue Price Document Numeber
	 * 
	 * @author Kowshik-RMITS
	 */
	public void getCataloguePriceDocumentno(ArrayList<String> DocNum) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SalesCatalogueLocators.DocumentNoField(1), ElementType.Xpath);
		if (flag) {
			String CatalogDocNo = webDB.getTextFromElement(SalesCatalogueLocators.DocumentNoField(1),
					ElementType.Xpath);
			VariableHandler.setCatalogDocno(CatalogDocNo);
			logs.logging("info", "Get Price List Document no " + VariableHandler.getCatalogDocno());
			commonFunc.hardWait(1000);
			DocNum.add(VariableHandler.getCatalogDocno());
		}
		assertTrue(flag);
	}

	/**
	 * Click PostButton
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickCPPostButton() {
//		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesCatalogueLocators.PostButtonField(1), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.PostButtonField(1), ElementType.Xpath);
			logs.logging("INFO ", "Successfully Click The Post Button ");
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Add BUtton In the CS Amendment
	 * 
	 * @author Harikrishna - RMITS
	 * 
	 */
	public void clickAddButtonInCSAmd() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSAmdAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdLocationLov, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Customer Schedule Amendment Add Button Is Clicked SucessFully");
			} else {
				logs.logging("INFO", "Failed To Click Customer Schedule Amendment Add Button");
			}
			assertTrue(flag);
		}
	}

	/**
	 * This Method Is Used To Add The Header Details Of the Customer Schedule
	 * Amendment Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param Location
	 * @param DocPrefix
	 * @param Customer
	 */
	public void addHeaderDetailsOfCSAmendment(String Location, String DocPrefix, String Customer) {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdLocationLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSAmdLocationLov, Location);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdDocPfx, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSAmdDocPfx, DocPrefix);
				flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdCustomerId, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSAmdCustomerId, Customer);
					webDB.pressTabKey();
					flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdHeaderWiseSaveButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(CustomerScheduleLocators.CSAmdHeaderWiseSaveButton, ElementType.Xpath);
						flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdPostButton, ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							logs.logging("INFO", "Customer Schedule Amendment Header Is Created Sucessfully");
							flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdDocNumberField,
									ElementType.Xpath);
							if (flag) {
								String DocNumber = webDB.getDomPropertyFromElement(
										CustomerScheduleLocators.CSAmdDocNumberField, ElementType.Xpath,
										commonFunc.getDataFromTestDataSheet("Value"));
								VariableHandler.setCSAmdDocno(DocNumber);
								logs.logging("INFO",
										"Get Text Customer Schedule Amenmdment Documnet Number IS " + DocNumber);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The fetch Button In the CS Amendment
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickFetchButtonInCSAmd() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdFetchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSAmdFetchButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdFetchFrame, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(CustomerScheduleLocators.CSAmdFetchFrame, ElementType.Xpath);
				commonFunc.removeFilterStock();
				flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdFetchSearchField, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Customer Schedule Amendment Fetch Button Is Clicked Sucessfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Pass One Documnet Number And click The GO Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param DocNum
	 */
	public void passDocumentNumberAndClickOKButton(String DocNum) {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdFetchSearchField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(CustomerScheduleLocators.CSAmdFetchSearchField, DocNum, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdFetchGOButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CS Document Number Is Entered Sucessfully " + DocNum);
				webDB.clickAnElement(CustomerScheduleLocators.CSAmdFetchGOButton, ElementType.Xpath);
				flag = webDB.waitForElement(CustomerScheduleLocators.CSFetchSelectCheckBox(DocNum), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Go Button Is Clicked Sucessfully");
					webDB.moveToElement(CustomerScheduleLocators.CSFetchSelectCheckBox(DocNum), ElementType.Xpath);
					flag = webDB.waitForElement(CustomerScheduleLocators.CSFetchSelectCheckBox(DocNum),
							ElementType.Xpath);
					if (flag) {
						webDB.javaScriptClickAnElement(CustomerScheduleLocators.CSFetchSelectCheckBox(DocNum),
								ElementType.Xpath);
						commonFunc.hardWait(500);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdFetchOkButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Select Check Box IS Clicked Sucessfully");
							webDB.clickAnElement(CustomerScheduleLocators.CSAmdFetchOkButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(CustomerScheduleLocators.FirmTypeInCsa(), ElementType.Xpath);
							if (flag) {
								commonFunc.checkRefreshCellsDisappear();
								logs.logging("INFO", "Customer Schedule Fetch Ok Button IS Clicked Sucessfully");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Select All Check Box In the CS Amd Fetch
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickSelectAllCheckBox() {
		flag = webDB.moveToElement(CustomerScheduleLocators.CSAmdFetchSelectAllCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSAmdFetchSelectAllCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdFetchOkButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Select All Button IS Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The OK Button In The Fetch Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickOkButtonInFetch() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdFetchOkButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSAmdFetchOkButton, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdPostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Ok Button Is Clicked In the Fetch Screen Of Customer Schedule Amenmdment");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Cancel Button In The Customer Schedule
	 * Amendment
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCancelButtonInCSAmd() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSAmdCancelButton, ElementType.Xpath);
			//flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdCancelAlert, ElementType.Xpath);
			flag = webDB.waitForClickElement(OpenSalesOrderLocators.CancelCaution, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSalesOrderLocators.CancelCaution, ElementType.Xpath);
				if (flag) {
					String Alert = webDB.getTextFromElement(CustomerScheduleLocators.CSAmdCancelAlert,
							ElementType.Xpath);
					if (Alert.equalsIgnoreCase("Document is Cancelled.")) {
						logs.logging("INFO", "Customer Schedule Document Cancelled Sucessfully");
					} else {
						logs.logging("INFO", "Failed To Cancel The Customer Schedule Document");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Change The Amendment Type In The Customer Schedule
	 * Amendment
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void changeTypeInCSAmd(String Value) {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdTypeField, ElementType.Xpath);
		if (flag) {
			String GetText = webDB.getTextFromElement(CustomerScheduleLocators.CSAmdTypeField, ElementType.Xpath);
			System.out.println(GetText);
			if (!GetText.equalsIgnoreCase(Value)) {
				webDB.doubleClickAnElement(CustomerScheduleLocators.CSAmdTypeField, ElementType.Xpath);
				webDB.selectDropDownByText(CustomerScheduleLocators.CSAmdTypeDropDown, Value, ElementType.Xpath);
				webDB.pressTabKey();
				logs.logging("INFO", "Amendment Type Is Changed Sucessfully To " + Value);
			}
			flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdLineWiseSaveButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CustomerScheduleLocators.CSAmdLineWiseSaveButton, ElementType.Xpath);
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Line WIse Save Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Change The Amendment Type In The Customer Schedule
	 * Amendment
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void changeScheduleTypeInCSAmd(String Value) {
		flag = webDB.waitForElement(CustomerScheduleLocators.FirmTypeInCsa(), ElementType.Xpath);
		if (flag) {
			String GetText = webDB.getTextFromElement(CustomerScheduleLocators.FirmTypeInCsa(), ElementType.Xpath);
			System.out.println(GetText);
			if (!GetText.equalsIgnoreCase(Value)) {
				webDB.doubleClickAnElement(CustomerScheduleLocators.FirmTypeInCsa(), ElementType.Xpath);
				webDB.selectDropDownByText(CustomerScheduleLocators.CSAmdSchTypeDropDown, Value, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.pressTabKey();
				logs.logging("INFO", " Schedule Type Is Changed Sucessfully To " + Value);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Line Wise Save Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickLineWiseSaveButtonInCSAmd() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdLineWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSAmdLineWiseSaveButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Line WIse Save Button Is Clicked Sucessfully");
		}
	}

	/**
	 * This Method Is Used To Click The Post Button In CS Amd Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPostButtonInCSAmd() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdPostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSAmdPostButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Change The Qty In CS Amendment
	 * 
	 * @author Harikrishna - RMITS
	 * @param Qty
	 */
	public void changeQtyInCSAmd(String Qty) {
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.FirmQtyCsa(),
				CustomerScheduleLocators.CSAmdQtyInputField, Qty);
		webDB.pressTabKey();
	}

	/**
	 * This Method Is Used To Change The Price In CS Amendment
	 * 
	 * @author Harikrishna - RMITS
	 * @param UnitPrice
	 */
	public void changePriceInCSAmd(String UnitPrice) {
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.CsaPriceField(),
				CustomerScheduleLocators.CSAmdPriceInputField, UnitPrice);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Method Is Used To Change The Tentative Qty In CS Amendment Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param TentativeQty
	 */
	public void changeTentaiveQtyInCSAmd(String TentativeQty) {
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.CsaTentiveQtyField(),
				CustomerScheduleLocators.CSAmdTentativeQtyInputField, TentativeQty);
		webDB.pressTabKey();
	}

	/**
	 * This Method Is Used To Add The End Date In Tentative Field
	 * 
	 * @author Harikrishna - RMITS
	 * @param TentaiveEndDate
	 */
	public void addEndDateInTentativeField(String TentaiveEndDate) {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdTentaiveDateField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSAmdTentaiveDateField, ElementType.Xpath);
			webDB.sendTextToAnElement(CustomerScheduleLocators.CSAmdTentativeDateInputField, TentaiveEndDate,
					ElementType.Xpath);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		}
		Assert.assertTrue(flag);
	}
	/**Click and Navigates Customer Schedule Amd Screen
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickAnCreateSoaandCsaAmdButton() {
		flag = webDB.waitForElement(SalesOrderLocators.SOAmendmentPendingButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SOAmendmentPendingButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSamdButton, ElementType.Xpath);
			if(flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(CustomerScheduleLocators.CSamdButton, ElementType.Xpath);
				flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdLocationLov, ElementType.Xpath);
				if(flag) {
					logs.logging("INFO", "Customer Schedule Amd Screen Navigates Successfully");
				} else {
					logs.logging("INFO", "Failed To Navigates Customer Schedule Amd Screen");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Cancel The Customer Schedule Amendment Document
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean cancelCustomerScheduleAmendmentDocument() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderAmendmentPage();
		clickAnCreateSoaandCsaAmdButton();
		addHeaderDetailsOfCSAmendment(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSAmendPrefix"), commonFunc.getDataFromTestDataSheet("Customer"));
		clickCancelButtonInCSAmd();
//		commonFunc.logoutUser();
		return true;
	}

	/**
	 * This Method Is Used To CloseShort The Customer Schedule Amendment Document
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean CustomerScheduleAmendmentCloseShort() {
		verifyDomesticCSGeneralSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("SO_Similar_Item"), webDB.getDataFromProperties("CS"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderAmendmentPage();
		clickAnCreateSoaandCsaAmdButton();
		addHeaderDetailsOfCSAmendment(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSAmendPrefix"), commonFunc.getDataFromTestDataSheet("Customer"));
		clickFetchButtonInCSAmd();
		passDocumentNumberAndClickOKButton(VariableHandler.getCSDocNumber());
		changeTypeInCSAmd(commonFunc.getDataFromTestDataSheet("CSAmdType"));
		clickPostButtonInCSAmd();
		flag = salesOrderFunctions.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSAmdDocno(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			commonFunc.logoutUser();
			String LineStatus = dbCommonFunctions.CSLineDetails(VariableHandler.getCSDocNumber(),
					VariableHandler.getMultipleCSLineDocs().get(0),
					commonFunc.getDataFromTestDataSheet("DBCSLineStatusColumnName"));
			salesOrderFunctions.verifyAssertionBothValues(LineStatus,
					commonFunc.getDataFromTestDataSheet("CustomerScheduleAmendmentCloseShort"));
		}
		return flag;
	}

	/**
	 * This Method Is Used To Change Qty In The Customer Schedule Amendment Document
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean CustomerScheduleAmendmentQtyChange() {
		verifyDomesticCSGeneralSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("SO_Similar_Item"), webDB.getDataFromProperties("CS"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderAmendmentPage();
		clickAnCreateSoaandCsaAmdButton();
		addHeaderDetailsOfCSAmendment(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSAmendPrefix"), commonFunc.getDataFromTestDataSheet("Customer"));
		clickFetchButtonInCSAmd();
		passDocumentNumberAndClickOKButton(VariableHandler.getCSDocNumber());
		changeQtyInCSAmd("100");
//		changePriceInCSAmd("2500");
		clickLineWiseSaveButtonInCSAmd();
//		changeTypeInCSAmd(commonFunc.getDataFromTestDataSheet("CSAmdType"));
		clickPostButtonInCSAmd();
		flag = salesOrderFunctions.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSAmdDocno(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			commonFunc.logoutUser();
			String LineStatus = dbCommonFunctions.CSLineDetails(VariableHandler.getCSDocNumber(),
					VariableHandler.getMultipleCSLineDocs().get(0),
					commonFunc.getDataFromTestDataSheet("DBCSLineStatusColumnName"));
			salesOrderFunctions.verifyAssertionBothValues(LineStatus,
					commonFunc.getDataFromTestDataSheet("CSAmendedLineStatus"));
		}
		return flag;
	}

	/**
	 * This Method Is Used To Change Unit Price In The Customer Schedule Amendment
	 * Document
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean CustomerScheduleAmendmentPriceChange() {
		verifyDomesticCSGeneralSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("SO_Similar_Item"), webDB.getDataFromProperties("CS"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickAnCreateSoaandCsaAmdButton();	
		addHeaderDetailsOfCSAmendment(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSAmendPrefix"), commonFunc.getDataFromTestDataSheet("Customer"));
		clickFetchButtonInCSAmd();
		passDocumentNumberAndClickOKButton(VariableHandler.getCSDocNumber());
//		changeQtyInCSAmd("100");
		changePriceInCSAmd("2500");
		clickLineWiseSaveButtonInCSAmd();
//		changeTypeInCSAmd(commonFunc.getDataFromTestDataSheet("CSAmdType"));
		clickPostButtonInCSAmd();
		flag = salesOrderFunctions.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSAmdDocno(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			commonFunc.logoutUser();
			String LineStatus = dbCommonFunctions.CSLineDetails(VariableHandler.getCSDocNumber(),
					VariableHandler.getMultipleCSLineDocs().get(0),
					commonFunc.getDataFromTestDataSheet("DBCSLineStatusColumnName"));
			salesOrderFunctions.verifyAssertionBothValues(LineStatus,
					commonFunc.getDataFromTestDataSheet("CSAmendedLineStatus"));
		}
		return flag;
	}

	/**
	 * This Method Is Used To Change Unit Price In The Customer Schedule Amendment
	 * Document
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean CustomerScheduleAmendmentScheduleChange() {
		verifyDomesticCSGeneralSimilarDisSimilarStockAndCustomerUOM(
				commonFunc.getDataFromTestDataSheet("SO_Similar_Item"), webDB.getDataFromProperties("CS"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CSBatchPrefix"),
				commonFunc.getDataFromTestDataSheet("CSNarration"));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderAmendmentPage();
		clickAnCreateSoaandCsaAmdButton();
		addHeaderDetailsOfCSAmendment(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CSAmendPrefix"), commonFunc.getDataFromTestDataSheet("Customer"));
		clickFetchButtonInCSAmd();
		passDocumentNumberAndClickOKButton(VariableHandler.getCSDocNumber());
		changeScheduleTypeInCSAmd(commonFunc.getDataFromTestDataSheet("CSScheduleType"));
		changeQtyInCSAmd("100");
		changeTentaiveQtyInCSAmd("200");
		changePriceInCSAmd("2500");
		addEndDateInTentativeField(tomorrowDate1);
		clickLineWiseSaveButtonInCSAmd();
		clickPostButtonInCSAmd();
		flag = salesOrderFunctions.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSAmdDocno(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			commonFunc.logoutUser();
		}
		return flag;
	}

	/**
	 * This method used to upload csv file in CS
	 * 
	 * @author Harikrishna - RMITS
	 * @param UpdatedFile
	 */
	private void verifyUploadTemplateInCustomerSchedule(String UpdatedFile) {
		String File = UpdatedFile;
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CustomerScheduleLocators.CSTemplateUploadButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CustomerScheduleLocators.CSTemplateUploadButton, ElementType.Xpath);
			webDB.clickAnElement(CustomerScheduleLocators.CSTemplateUploadButton, ElementType.Xpath);
			logs.logging("INFO", "File Upload Button Clicked");
			webDB.waitForElement(CustomerScheduleLocators.CSFileInputField, ElementType.Xpath);
			webDB.sendTextToAnElement(CustomerScheduleLocators.CSFileInputField, (new File(File).getAbsolutePath()),
					ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSUploadOkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CustomerScheduleLocators.CSUploadOkButton, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					flag = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath)
							.contains("Lines Uploaded Successfully");
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					flag = webDB.waitForElement(CustomerScheduleLocators.CSGenSchButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", " Customer Schedule Lines Uploaded Successfully Toast Displayed");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Upload the Line Wise Template In The Customer Schedule
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param SimilarItem
	 * @param SheetName
	 * @param Location
	 * @param Prefix
	 * @param Narration
	 * @return Boolean flag
	 */
	public boolean customerScheduleTemplateUpload(String SimilarItem, String SheetName, String Location, String Prefix,
			String Narration) {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateItemPage();
		verifySearchOfItem(SimilarItem, SheetName);
		VariableHandler.setMultipleItems(ArrayList);
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
//				System.getProperty("AshokUsername"));
//		approvalFunc.navigateToApprovals();
		for (int i = 1; i <= ArrayList.size(); i++) {
//			approvalFunc.filterDocumentsforApproval(VariableHandler.getMultipleItems().get(i - 1));
//			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMultipleItems().get(i - 1));
			commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getMultipleItems().get(i - 1), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		salesOrderFunctions.checkSOMControl(webDB.getDataFromProperties("Cs"));
		addCustomerScheduleHeaderDetails(Location, Prefix, Narration);
		verifyCustomerScheduleDocumentStatusDraftWithDB();
		verifyCustomerScheduleHeaderDetailsUIValuesWithDBValues();
		verifyCustomerScheduleHeaderDetailsWithDB(Location, Prefix, Narration);
		String updatedFileName = createUpdatedCsvFileWithItemFromAndToDate(
				commonFunc.getDataFromTestDataSheet("CSTemplate1Line"));
		verifyUploadTemplateInCustomerSchedule(updatedFileName);
		clickGenerateScheduleButton();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifyCustomerScheduleDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCSDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifyCustomerScheduleDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Update The CSV File With ItemID, From And To Date
	 * 
	 * @author Harikrishna - RMITS
	 * @param TemplateFileName
	 * @return String updatedFile
	 */
	private String createUpdatedCsvFileWithItemFromAndToDate(String TemplateFileName) {
		String FileName = VariableHandler.testdataDir + TemplateFileName;
		String updatedFile = "updated" + TemplateFileName;

		LocalDateTime Date = LocalDateTime.now();
		LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yy");
		String tomorrowDate = myDateObj.format(myFormatObj);
		String today = Date.format(myFormatObj);
		String ItemID = VariableHandler.getMultipleItems().get(0);

		try (BufferedReader reader = new BufferedReader(new FileReader(FileName));
				BufferedWriter writer = new BufferedWriter(new FileWriter(updatedFile))) {
			String line;
			boolean isHeader = true;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) {
					break;
				}
				String[] columns = line.split(",");
				if (isHeader) {
					writer.write(String.join(",", columns));
					writer.newLine();
					isHeader = false;
				} else {
					int ItemColumnIndex = 1;
					int FromdateColumnIndex = 8;
					int TodateColumnIndex = 9;
					columns[ItemColumnIndex] = ItemID; // Replace with New Item ID
					columns[FromdateColumnIndex] = today; // Replace with Today Date
					columns[TodateColumnIndex] = tomorrowDate; // Replace with Tommorow Date
					writer.write(String.join(",", columns));
					writer.newLine();
				}
			}
			logs.logging("INFO", "File processed successfully. Modified file saved as: " + updatedFile);
		} catch (Exception e) {
			logs.logging("FAIL", "File processed Failed. Modified file failed to save as: " + updatedFile);
			Assert.fail("File processed Failed. Modified file failed to save as: " + updatedFile);
		}
		return updatedFile;
	}

	/**
	 * This Method Is Used To Verify the Document Status As Draft With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyCustomerScheduleDocumentStatusDraftWithDB() {
		String CSDocumentStatus = dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
				commonFunc.getDataFromTestDataSheet("CSDBDocumentStatus"));

		salesOrderFunctions.verifyAssertionBothValues(CSDocumentStatus, commonFunc.getDataFromTestDataSheet("Draft"));
		logs.logging("INFO", "Verified Document Status Is " + CSDocumentStatus
				+ " Against This Customer Schedule Document Number " + VariableHandler.getCSDocNumber());
	}

	/**
	 * This Method Is Used To Verify the Document Status As Cancelled With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyCustomerScheduleDocumentStatusCancelledWithDB() {
		String CSDocumentStatus = dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
				commonFunc.getDataFromTestDataSheet("CSDBDocumentStatus"));

		salesOrderFunctions.verifyAssertionBothValues(CSDocumentStatus,
				commonFunc.getDataFromTestDataSheet("CancelledStatus"));
		logs.logging("INFO", "Verified Document Status Is " + CSDocumentStatus
				+ " Against This Customer Schedule Document Number " + VariableHandler.getCSDocNumber());
	}

	/**
	 * This Method Is Used To Verify the Document Status As EntryCompleted With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyCustomerScheduleDocumentStatusEntryCompletedWithDB() {
		String CSDocumentStatus = dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
				commonFunc.getDataFromTestDataSheet("CSDBDocumentStatus"));

		salesOrderFunctions.verifyAssertionBothValues(CSDocumentStatus,
				commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"));
		logs.logging("INFO", "Verified Document Status Is " + CSDocumentStatus
				+ " Against This Customer Schedule Document Number " + VariableHandler.getCSDocNumber());
	}

	/**
	 * This Method Is Used To Verify The SO Document Status As Approved With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyCustomerScheduleDocumentStatusApprovedWithDB() {
		String CSDocumentStatus = dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
				commonFunc.getDataFromTestDataSheet("CSDBDocumentStatus"));

		salesOrderFunctions.verifyAssertionBothValues(CSDocumentStatus,
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		logs.logging("INFO", "Verified Document Status Is " + CSDocumentStatus
				+ " Against This Customer Schedule Document Number " + VariableHandler.getCSDocNumber());
	}

	/**
	 * This Method Is Used To Verify The CS Header Details UI Vlaues With Database
	 * Values
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifyCustomerScheduleHeaderDetailsUIValuesWithDBValues() {

		salesOrderFunctions.verifyAssertionDomPropertyValidation(CustomerScheduleLocators.CSHeaderLocationField,
				dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLocationID")));
		logs.logging("INFO", "Header Location Field UI Values Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionDomPropertyValidation(CustomerScheduleLocators.CSHeaderUnitField,
				dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBUnitID")));
		logs.logging("INFO", "Header Unit Field UI Values Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionGetTextValidation(CustomerScheduleLocators.CSHeaderBatchPrefix,
				dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBBatchPrefix")));
		logs.logging("INFO", "Header Batch Prefix Field UI Values Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionGetTextValidation(CustomerScheduleLocators.CSHeaderBatchNumberField,
				dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBBatchNumber")));
		logs.logging("INFO", "Header Batch Number Field UI Values Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionGetTextValidation(CustomerScheduleLocators.CSHeaderDocStatusField,
				dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBDocumentStatus")));
		logs.logging("INFO", "Header Document Status Field UI Values Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionDomPropertyValidation(CustomerScheduleLocators.CSHeaderNarrationField,
				dbCommonFunctions.CSHeaderDetails(VariableHandler.getCSDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBNarration")));
		logs.logging("INFO", "Header Narration Field UI Values Matched With DataBase Values");
	}

	/**
	 * This Method Is Used To Verify The Customer Schedule Header Details With DB
	 * Values
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifyCustomerScheduleHeaderDetailsWithDB(String Location, String Prefix, String Narration) {

		salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSHeaderDetails(
				VariableHandler.getCSDocNumber(), commonFunc.getDataFromTestDataSheet("CSDBLocationID")), Location);
		logs.logging("INFO", "Location Details In The Header Is verified Sucessfully With DB Values ");

		salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSHeaderDetails(
				VariableHandler.getCSDocNumber(), commonFunc.getDataFromTestDataSheet("CSDBBatchPrefix")), Prefix);
		logs.logging("INFO", "Batch Prefix Details In The Header Is verified Sucessfully With DB Values ");

		salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSHeaderDetails(
				VariableHandler.getCSDocNumber(), commonFunc.getDataFromTestDataSheet("CSDBNarration")), Narration);
		logs.logging("INFO", "Narration Details In The Header Is verified Sucessfully With DB Values ");
	}

	/**
	 * This Method Is Used To Get The Line Wise Document Number
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void getCSLineWiseDocumentNumber(int i) {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSLineDocNumberfield(i - 3), ElementType.Xpath);
		if (flag) {
			String LineDocNo = webDB.getTextFromElement(CustomerScheduleLocators.CSLineDocNumberfield(i - 3),
					ElementType.Xpath);
			VariableHandler.setCSLineDocNumber(LineDocNo);
			logs.logging("INFO", "Get Text Customer Schedule Line Wise Document Number Is : "
					+ VariableHandler.getCSLineDocNumber());
			CSLineDocNo.add(LineDocNo);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Validate The Line Wise Details With Database Values
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param j
	 */
	public void verifyCustomerScheduleLineWiseDetailsWithDB(String SheetName, int j) {
		CSMultipleline = excelUtils.readAllDataInExcel(CSFilepath, SheetName);
		for (int i = j; i <= j; i++) {
			String CUST_ID = CSMultipleline[i][1];
			String CUST_DESC = CSMultipleline[i][2];
			String ITEM_ID = CSMultipleline[i][4];
			String SCH_TYPE = CSMultipleline[i][7];
			String FIRM_QTY = CSMultipleline[i][9];
			String FIRM_PRICE = CSMultipleline[i][10];
			String GROSSAMT = CSMultipleline[i][11];
			String DISC_PCT = CSMultipleline[i][12];
			String ASSBL_VALUE = CSMultipleline[i][13];
			String DISC_AMT = CSMultipleline[i][14];
			String HSN_CODE = CSMultipleline[i][18];
			String CGST = CSMultipleline[i][20];
			String SGST = CSMultipleline[i][21];
			String IGST = CSMultipleline[i][22];
			String UTGST = CSMultipleline[i][23];
			String CESS_AMT = CSMultipleline[i][25];
			String CURCY = CSMultipleline[i][37];
			String FIRM_TO_DATE = CSMultipleline[i][32];
			String BKT_TYPE = CSMultipleline[i][33];
			if (!CUST_ID.equals("")) {
				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINECUSTID")), CUST_ID);
				logs.logging("INFO",
						"Line Wise Customer Id Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINECUSTNAME")), CUST_DESC);
				logs.logging("INFO",
						"Line Wise Customer Description Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINESCHTYPE")), SCH_TYPE);
				logs.logging("INFO",
						"Line Wise Schedule Type Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINEITEMID")), ITEM_ID);
				logs.logging("INFO", "Line Wise Item ID Matched With DataBase Values Against This Line Document Number "
						+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINEFIRMQTY")), FIRM_QTY);
				logs.logging("INFO",
						"Line Wise Firm Qty Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINEUNITPRICE")), FIRM_PRICE);
				logs.logging("INFO",
						"Line Wise Unit Price Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINEDISCPER")), DISC_PCT);
				logs.logging("INFO",
						"Line Wise Discount Percentage Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINEDISCAMT")), DISC_AMT);
				logs.logging("INFO",
						"Line Wise Discount Amount Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINECGSTAMT")), CGST);
				logs.logging("INFO",
						"Line Wise CGST Amount Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINESGSTAMT")), SGST);
				logs.logging("INFO",
						"Line Wise SGST Amount Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINEIGSTAMT")), IGST);
				logs.logging("INFO",
						"Line Wise IGST Amount Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINEUTGSTAMT")), UTGST);
				logs.logging("INFO",
						"Line Wise UTGST Amount Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINEFIRMBUCKET")), BKT_TYPE);
				logs.logging("INFO",
						"Line Wise Bucket Type Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.CSLineDetails(
						VariableHandler.getCSDocNumber(), VariableHandler.getCSLineDocNumber(),
						commonFunc.getDataFromTestDataSheet("CSDBLINECURCY")), CURCY);
				logs.logging("INFO",
						"Line Wise Currency Matched With DataBase Values Against This Line Document Number "
								+ VariableHandler.getCSLineDocNumber());
			}
		}
	}

	/**
	 * This Method Is Used to set the Variable Handler To Zero After Finishing One
	 * Test Scenarios
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyAfterMethod() {
		VariableHandler.setSOEXPTotalOrderAmount(BigDecimal.ZERO);
		VariableHandler.setSOTaxAmount(BigDecimal.ZERO);
		VariableHandler.setSOTotalDiscountAmount(BigDecimal.ZERO);
		VariableHandler.setSOTotalGrossAmount(BigDecimal.ZERO);
		VariableHandler.setSOTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setCSDocNumber(null);
		CSLineDocNo.clear();
		ArrayList.clear();
		MultiPriceList.clear();
		MultiOpenSODoc.clear();
		MultiCatalogPriceDoc.clear();
		webDB.navigateToRefresh();
		webDB.switchToDefaultContent();
//		commonFunc.enterUrl();
	}

	/**
	 * This Method Is Used To Click The Customer Schedule Button The Sales Order Add
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCustomerScheduleButton() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Customer Schedule Button Is clicked Sucessfully");
				MatReqFunc.FrameSwitching(CustomerScheduleLocators.CSFrame);
				flag = webDB.waitForElement(CustomerScheduleLocators.CSLocationLov, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Navigated To Customer Schedule Add Screen");
				}
			}
		}
		Assert.assertTrue(flag);
	}
}
