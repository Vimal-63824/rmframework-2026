package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;

import org.codehaus.groovy.ast.Variable;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.CustomerScheduleLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CustomerScheduleFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions apprpagefunc = new ApprovalPageFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static ExcelUtils excelUtils = new ExcelUtils();
	private static SalesOrderFunctions salesOrderFunctions = new SalesOrderFunctions();

	/**
	 * This Method Is Used To Add The Customer Schedule Header Details And Get The
	 * CS Document Number
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void addCustomerScheduleHeaderDetails() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSAddButton, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSLocationLov,
					commonFunc.getDataFromTestDataSheet("Loc_100"));
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.searchAndSelectValueInFilters(CustomerScheduleLocators.CSBatchPrefixLov,
					commonFunc.getDataFromTestDataSheet("CSBatchPrefix"));
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CustomerScheduleLocators.CSNarrationField, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(CustomerScheduleLocators.CSNarrationField,
						commonFunc.getDataFromTestDataSheet("CSNarration"), ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(CustomerScheduleLocators.CSsaveButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Narration Entered Sucessfully");
					webDB.clickAnElement(CustomerScheduleLocators.CSsaveButton, ElementType.Xpath);
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
		}
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
			webDB.clickAnElement(CustomerScheduleLocators.CSLineWiseAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSLineWiseCustField, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CustomerScheduleLocators.CSLineWiseCustField, ElementType.Xpath);
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
		flag = webDB.waitForElement(CustomerScheduleLocators.CSLineWiseItemField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSLineWiseItemField, ElementType.Xpath);
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
	public void addQtyUnitAndMrpCost(String FirmQty, String UnitCost, String MrpPrice, String Discount,
			String EndDate) {
		commonFunc.checkRefreshCellsDisappear();
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.CSLineWiseFirmQtyField,
				CustomerScheduleLocators.CSLineWiseFirmQtyInputField, FirmQty);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.CSLineWiseUnitPriceField,
				CustomerScheduleLocators.CSLineWiseUnitPriceInputField, UnitCost);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.CSLineWiseMRPField,
				CustomerScheduleLocators.CSLineWiseMRPInputField, MrpPrice);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		salesOrderFunctions.verifyClickAndEnterValueInTableFields(CustomerScheduleLocators.CSLineWiseDiscField,
				CustomerScheduleLocators.CSLineWiseDiscInputField, Discount);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CustomerScheduleLocators.CSLineWiseEndDateField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSLineWiseEndDateField, ElementType.Xpath);
			webDB.sendTextToAnElement(CustomerScheduleLocators.CSLineWiseEndDateInputField, EndDate, ElementType.Xpath);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Line Wise Save Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickLineWiseSaveButton() {
		flag = webDB.waitForElement(CustomerScheduleLocators.CSLineWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerScheduleLocators.CSLineWiseSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSGenSchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Customer Schedule Line Wise details Button Is Clicked Sucessfully");
			}
		}
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
	public boolean forwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
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
	 * This Method Is Used To VerifyCSGeneral Similar Stock And Customer UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDomesticCSGeneralSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateCustomerScheduleScreen();
		addCustomerScheduleHeaderDetails();
		addLineWiseCustomer(commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInCS(commonFunc.getDataFromTestDataSheet("SO_Similar_Item"));
		addQtyUnitAndMrpCost(commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SO_UnitCost"), commonFunc.getDataFromTestDataSheet("SO_UnitCost"),
				commonFunc.getDataFromTestDataSheet("Disc_Value"), commonFunc.getDataFromTestDataSheet("Close_Date"));
		clickLineWiseSaveButton();
		clickGenerateScheduleButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getCSDocNumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getCSDocNumber());
			flag = commonFunc.logoutUser();
		}
		return flag;
	}
}
