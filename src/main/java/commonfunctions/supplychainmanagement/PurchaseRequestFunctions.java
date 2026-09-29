package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import static org.testng.Assert.assertTrue;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.ItemDetailsHandler.PRLineDetailsName;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseRequestFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static ItemDetailsHandler itmDtlHandler = new ItemDetailsHandler();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();

	/**
	 * This Methods Is Used To Verify Creation Of PR General
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreationOfPRGeneral() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		saveHeaderAndClickPost();
		searchPRAndCheckPRStatus(VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR Service
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreationOfPRService() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Service"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		saveHeaderAndClickPost();
		searchPRAndCheckPRStatus(VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Cancel of PR and Cancel of Item Lines
	 * particularly
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCancelPRAndDeleteLine() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		deleteParticularLinesPurchaseRequest(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		cancelPurchaseRequestAndVerifyCancelStatus();
//		flag = commonFunc.logoutUser();
		return true;
	}

	/**
	 * This Methods Is Used To Verify Cancel of PR and Cancel of Item Lines
	 * particularly
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreatePRByUploadExcel() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String updatedFileName = createUpdatedCsvFileWithRequiredDate(
				commonFunc.getDataFromTestDataSheet("PRtemplate1Line"));
		verifyUploadCsvFileInPRAndDeleteUpdatedFile(updatedFileName);
		saveHeaderAndClickPost();
		searchPRAndCheckPRStatus(VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//		flag = commonFunc.logoutUser();
		return true;
	}

	/**
	 * This Methods Is Used To Verify PR Close Short
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyPRCloseShort() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		saveHeaderAndClickPost();
		searchPRAndCheckPRStatus(VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigatePurchaseRequestCloseShortPage();
		clickAddIconPurchaseRequestCloseShort();
		fillFormDetailsInPurchaseRequestCloseShort(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("RefText_Test"));
		clickTickIconAndStoreDocumentNumberInPRCloseShort();
		clickLoadPRAndSelectAllItemstoLines();
		saveHeaderAndClickCloseShort();
		searchDocNumberAndCheckPRCloseShortStatus(VariableHandler.getPRCloseShortDocNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
//		flag = commonFunc.logoutUser();
		return true;
	}

	/**
	 * This method used to select location PR-type prefix department and requester
	 * for new purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param location
	 * @param PRtype
	 * @param department
	 * @param requester
	 */
	public void clickAddIconFillDetailsOfPurchaseRequest(String Location, String Unit, String PRType, String Prefix,
			String CPCCode, String Department, String Requester) {
		commonFunc.clickPRCreateIcon();
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRLocationButton, Location);
		commonFunc.checkRefreshCellsDisappear();
		selectPRType(PRType);
		commonFunc.checkRefreshCellsDisappear();
		selectPRPrefix(Prefix);
		commonFunc.checkRefreshCellsDisappear();
		webDB.switchToFrame(CommonUiLocators.PrCreateFrame, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRCPCCodeButton, CPCCode);
		commonFunc.checkRefreshCellsDisappear();
		selectPRDepartment(Department);
		commonFunc.checkRefreshCellsDisappear();
		selectPRRequester(Requester);
		commonFunc.checkRefreshCellsDisappear();
		clickTickIconCreateRequest();
		commonFunc.checkRefreshCellsDisappear();
		validateLocationInPRHeader(Location);
		validateUnitInPRHeader(Unit);
		validatePRTypeInPRHeader(PRType);
		validatePrefixInPRHeader(Prefix);
		validateDepartmentInPRHeader(Department);
		validateRequestorInPRHeader(Requester);
//		validateCPCCodeInPRHeader(CPCCode);
		purchaseRequestHeaderValidation(Location, Unit, PRType, Prefix, CPCCode, Department, Requester);
	}

	/**
	 * This Method Used To Verify Check Purchase Request Status PR
	 * 
	 * @author Sakthi-GWL
	 * @param PRNumber
	 * @param ExpextedStatus
	 */
	public void searchPRAndCheckPRStatus(String PRNumber, String ExpextedStatus) {
		if (webDB.verifyIsElementDisplayed(PurchaseRequestLocators.BackIconBtn, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseRequestLocators.BackIconBtn, ElementType.Xpath);
		}
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_PRNoButton, PRNumber);
		flag = webDB.waitForElement(PurchaseRequestLocators.HeaderCheckbox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.HeaderCheckbox, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseRequestLocators.PRStatusBoldText(PRNumber), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(PurchaseRequestLocators.PRStatusBoldText(PRNumber),
							ElementType.Xpath);
					logs.logging("INFO", "PR Number: '" + PRNumber + "' searched and displaying status: " + Status);
					flag = Status.equalsIgnoreCase(ExpextedStatus);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to fill the row lines for all modules
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param Qnty
	 * @param Cost
	 * @param addIcon
	 */
	public void fillItemsQuantityCostAndSaveLine(String Item, String Qnty, String Cost, boolean... addIcon) {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkRefreshCellsDisappear();
		if (addIcon != null) {
			flag = webDB.waitForElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
				webDB.clickAnElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataLastRow(), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Add Lines Icon Clicked.");
				}
			}
		}
		searchAndSelectItems(Item);
		webDB.pressEscapeKey();
		enterQuantityForLastItem(Qnty);
		webDB.pressEscapeKey();
		enterCostForLastItem(Cost);
		webDB.pressEscapeKey();
		saveLinesEntry();
//		checkLineCostAmount(Cost, Qnty, Item);
		clickLineDetailsIconForLastitem(Item);
		checkPRLineDetailsForLastUpdateItem(Item);
		closeLineDetailsAndVerify();
	}

	/**
	 * This method used to Save Header and Post Purchase Request
	 * 
	 * @author Sakthi-GWL
	 */
	public void saveHeaderAndClickPost() {
		flag = webDB.waitForElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForClickElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkSuccessToastMessageDisappear();
				webDB.moveToElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
				webDB.clickAnElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Delete Particular Lines Purchase Request PR
	 * 
	 * @author Sakthi-GWL
	 * 
	 * @param DataValue
	 */
	public void deleteParticularLinesPurchaseRequest(String DataValue) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_LastItemDetailIcon(DataValue), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PR_LastItemDetailIcon(DataValue), ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.PR_LastItemDetailIcon(DataValue), ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.LineDelete, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.LineDelete, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.OkayButton, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.OkayButton, ElementType.Xpath);
				}
//				webDB.switchToAlertAccept();
				flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, Line Deleted Successfully with value: " + DataValue);
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Cancel Purchase Request And Verify Cancel Status
	 * 
	 * @author Sakthi-GWL
	 */
	public void cancelPurchaseRequestAndVerifyCancelStatus() {
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_Cancelbtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PR_Cancelbtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.PR_Cancelbtn, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ContractPriceOkButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PurchaseOrderPageLocators.ContractPriceOkButton, ElementType.Xpath);
				webDB.clickAnElement(PurchaseOrderPageLocators.ContractPriceOkButton, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, PR Cancelled Successfully");
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
							commonFunc.getDataFromTestDataSheet("CancelStatus"));
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate the Line Details - Class, SubClass, Warehouse,
	 * Convention Factor, Purchase UOM, GL Acc No, GL Acc Desc, CPC Code, CPC Desc
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 */
	public void checkPRLineDetailsForLastUpdateItem(String Item) {
		webDB.waitForElement(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
		webDB.switchToFrame(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
		verifyItemDetailsTabOpening();
		webDB.switchToDefaultContent();
//		validatePRLineDetailsWithGetText(PurchaseRequestLocators.PRLineDetails_SubClass, Item,
//				PRLineDetailsName.SUBCLASS, dbCommonFunctions.PRLineDetails(VariableHandler.getPRNumber(),
//						commonFunc.getDataFromTestDataSheet("SubClassValue")));
//		logs.logging("INFO", "Sub Class Verified in Database");
//		validatePRLineDetailsWithGetText(PurchaseRequestLocators.PRLineDetails_Class, Item, PRLineDetailsName.CLASS,
//				dbCommonFunctions.PRLineDetails(VariableHandler.getPRNumber(),
//						commonFunc.getDataFromTestDataSheet("ClassValue")));
//		logs.logging("INFO", "Class Verified in Database");
		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_Warehouse, Item,
				PRLineDetailsName.WAREHOUSE, dbCommonFunctions.PRLineDetails(VariableHandler.getPRNumber(),
						commonFunc.getDataFromTestDataSheet("WHValue")));
		logs.logging("INFO", "Warehouse Verified in Database");
		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_ConventionFactor, Item,
				PRLineDetailsName.CONVENTION_FACTOR, dbCommonFunctions.PRLineDetails(VariableHandler.getPRNumber(),
						commonFunc.getDataFromTestDataSheet("ConvValue")));
		logs.logging("INFO", "Convention Factor Verified in Database");
		validatePRLineDetailsWithGetText(PurchaseRequestLocators.PRLineDetails_PurchaseUOM, Item,
				PRLineDetailsName.PURCHASE_UOM, dbCommonFunctions.PRLineDetails(VariableHandler.getPRNumber(),
						commonFunc.getDataFromTestDataSheet("UOMValue")));
		logs.logging("INFO", "UOM Verified in Database");
//		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_GLAccNo, Item,
//				PRLineDetailsName.GL_ACC_NO, dbCommonFunctions.PRLineDetails(VariableHandler.getPRNumber(),
//						commonFunc.getDataFromTestDataSheet("GLValue")));
//		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_GLAccDesc, Item,
//				PRLineDetailsName.GL_ACC_DESC, dbCommonFunctions.PRLineDetails(VariableHandler.getPRNumber(),
//						commonFunc.getDataFromTestDataSheet("GLDescValue")));
//		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_ItemCPCCode, Item,
//				PRLineDetailsName.CPC_CODE, dbCommonFunctions.PRLineDetails(VariableHandler.getPRNumber(),
//						commonFunc.getDataFromTestDataSheet("CCCodeLineValue")));
//		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_ItemCPCDesc, Item,
//				PRLineDetailsName.CPC_DESC, dbCommonFunctions.PRLineDetails(VariableHandler.getPRNumber(),
//						commonFunc.getDataFromTestDataSheet("CCDescValue")));
	}

	/*----------------------private methods---------------------------*/

	/**
	 * This method used to save the lines after enter items
	 * 
	 * @author Sakthi-GWL
	 */
	private void saveLinesEntry() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseRequestLocators.SaveLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.SaveLineBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.SaveLineBtn, ElementType.Xpath);
			if (webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath)) {
				webDB.javaScriptClickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			} else {
				if (webDB.isElementDisplayed(CommonUiLocators.FailedNotifyMessage, ElementType.Xpath)) {
					logs.logging("INFO", "Save Entry Item failed Notification Displayed");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to searched and Select Items in Lines for Purchase Entry -
	 * called in updatePurchaseRequest
	 * 
	 * @param Item
	 * @author Sakthi-GWL
	 */
	private void searchAndSelectItems(String Item) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataLastRow(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.ItemDataLastRow(), ElementType.Xpath);
			webDB.doubleClickAnElement(PurchaseRequestLocators.ItemDataLastRow(), ElementType.Xpath);
			if (!webDB.verifyIsElementDisplayed(PurchaseRequestLocators.ItemDataListIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(PurchaseRequestLocators.ItemDataLastRow(), ElementType.Xpath);
			}
			commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.ItemDataListIcon, Item);
//			flag = webDB.waitForElementUntilGivenTime(PurchaseRequestLocators.ItemDataListIcon, ElementType.Xpath, 60);
//			if (flag) {
//				webDB.clickAnElement(PurchaseRequestLocators.ItemDataListIcon, ElementType.Xpath);
//				flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataPopupSearchBox, ElementType.Xpath);
//				if (flag) {
//					commonFunc.checkRefreshCellsDisappear();
//					webDB.clearTextField(PurchaseRequestLocators.ItemDataPopupSearchBox, ElementType.Xpath);
//					webDB.sendTextToAnElement(PurchaseRequestLocators.ItemDataPopupSearchBox, Item, ElementType.Xpath);
//					flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.convertXPath(Item), ElementType.Xpath,
//							60);
//					if (flag) {
//						commonFunc.checkRefreshCellsDisappear();
//						webDB.clickAnElement(CommonUiLocators.convertXPath(Item), ElementType.Xpath);
//						commonFunc.hardWait(2000);
//						webDB.pressEscapeKey();
//						flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataLastRow, ElementType.Xpath);
//						if (flag) {
//							webDB.doubleClickAnElement(PurchaseRequestLocators.ItemDataLastRow, ElementType.Xpath);
//							String value = webDB.getAttributeFromElement(PurchaseRequestLocators.ItemDataLastRowInput,
//									ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
//							logs.logging("INFO", "Get Text Item Name Value from Table Is: " + value);
//							flag = value.contains(Item);
//							if (flag) {
//								logs.logging("INFO", Item + "- Item Searched and Selected");
//							}
//						}
//					}
//				}
//			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Enter Quantity in Lines for Purchase Entry - called in
	 * updatePurchaseRequest
	 * 
	 * @param Quantity
	 * @author Sakthi-GWL
	 */
	private void enterQuantityForLastItem(String Quantity) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseRequestLocators.PurchaseNo("RequestQuantity"), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PurchaseNo("RequestQuantity"), ElementType.Xpath);
			webDB.doubleClickAnElement(PurchaseRequestLocators.PurchaseNo("RequestQuantity"), ElementType.Xpath);
			if (!webDB.verifyIsElementDisplayed(PurchaseRequestLocators.QuantityDataLastRowInput, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(PurchaseRequestLocators.PurchaseNo("RequestQuantity"),
						ElementType.Xpath);
			}
			commonFunc.verifyAddTableSpecs(PurchaseRequestLocators.QuantityDataLastRowInput,
					PurchaseRequestLocators.QuantityDataLastRowInput, Quantity);
//		JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
//		flag = webDB.waitForClickElement(PurchaseRequestLocators.QuantityDataLastRow, ElementType.Xpath);
//		if (flag) {
//			webDB.scrollToAnElement(PurchaseRequestLocators.QuantityDataLastRow, ElementType.Xpath);
//			webDB.clickAnElement(PurchaseRequestLocators.QuantityDataLastRow, ElementType.Xpath);
//			flag = webDB.waitForElement(PurchaseRequestLocators.QuantityDataLastRowInput, ElementType.Xpath);
//			if (flag) {
//				WebElement element = webDB.getDriver()
//						.findElement(By.xpath(PurchaseRequestLocators.QuantityDataLastRowInput));
//				try {
//					js.executeScript("arguments[0].value='" + Quantity + "';", element);
//					String value = webDB.getAttributeFromElement(PurchaseRequestLocators.QuantityDataLastRowInput,
//							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
//					logs.logging("INFO", "Get Text Quantity Name Value from Table Is: " + value);
//					flag = value.contains(Quantity);
//					if (flag) {
//						logs.logging("INFO", "Quantity Entered: " + Quantity);
//					}
//				} catch (Exception e) {
//					flag = false;
//				}
//			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to enter Cost in Lines for Purchase Entry - called in
	 * updatePurchaseRequest
	 * 
	 * @param Cost
	 * @author Sakthi-GWL
	 */
	private void enterCostForLastItem(String Cost) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseRequestLocators.PurchaseNo("UnitCost"), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PurchaseNo("UnitCost"), ElementType.Xpath);
			webDB.doubleClickAnElement(PurchaseRequestLocators.PurchaseNo("UnitCost"), ElementType.Xpath);
			if (!webDB.verifyIsElementDisplayed(PurchaseRequestLocators.UnitCostDataLastRowInput, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(PurchaseRequestLocators.PurchaseNo("UnitCost"), ElementType.Xpath);
			}
			commonFunc.verifyAddTableSpecs(PurchaseRequestLocators.UnitCostDataLastRowInput,
					PurchaseRequestLocators.UnitCostDataLastRowInput, Cost);
//		JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
//		flag = webDB.waitForClickElement(PurchaseRequestLocators.UnitCostDataLastRow, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(PurchaseRequestLocators.UnitCostDataLastRow, ElementType.Xpath);
//			flag = webDB.waitForElement(PurchaseRequestLocators.UnitCostDataLastRowInput, ElementType.Xpath);
//			if (flag) {
//				WebElement element1 = webDB.getDriver()
//						.findElement(By.xpath(PurchaseRequestLocators.UnitCostDataLastRowInput));
//				try {
//					js.executeScript("arguments[0].value='" + Cost + "';", element1);
//					String value = webDB.getAttributeFromElement(PurchaseRequestLocators.UnitCostDataLastRowInput,
//							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
//					logs.logging("INFO", "Get Text Cost Name Value from Table Is: " + value);
//					flag = value.contains(Cost);
//					if (flag) {
//						logs.logging("INFO", "Cost Entered: " + Cost);
//					}
//				} catch (Exception e) {
//					flag = false;
//				}
//			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select PR-Type while create purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 */
	private void selectPRType(String Type) {
		if (commonFunc.getDataFromTestDataSheet(Type) != null) {
			Type = commonFunc.getDataFromTestDataSheet(Type);
		}
		webDB.waitForElement(CommonUiLocators.PrCreateFrame, ElementType.Xpath);
		webDB.switchToFrame(CommonUiLocators.PrCreateFrame, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseRequestLocators.PRTypeSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(PurchaseRequestLocators.PRTypeSelect, Type, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PR Type: " + Type + " selected");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select PR-Department while create purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param Dept
	 */
	private void selectPRDepartment(String Dept) {
		if (commonFunc.getDataFromTestDataSheet(Dept) != null) {
			Dept = commonFunc.getDataFromTestDataSheet(Dept);
		}
		webDB.switchToFrame(CommonUiLocators.PrCreateFrame, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseRequestLocators.PRDepartmentSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(PurchaseRequestLocators.PRDepartmentSelect, Dept, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PR Department: " + Dept + " selected");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select PR-Requestor while create purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param Rqstr
	 */
	private void selectPRRequester(String Rqstr) {
		if (commonFunc.getDataFromTestDataSheet(Rqstr) != null) {
			Rqstr = commonFunc.getDataFromTestDataSheet(Rqstr);
		}
		flag = webDB.waitForElement(PurchaseRequestLocators.PRRequesterSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(PurchaseRequestLocators.PRRequesterSelect, Rqstr, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PR Requester: " + Rqstr + " selected");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select Prefix while create purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param Prefix
	 */
	private void selectPRPrefix(String Prefix) {
		if (commonFunc.getDataFromTestDataSheet(Prefix) != null) {
			Prefix = commonFunc.getDataFromTestDataSheet(Prefix);
		}
		flag = webDB.waitForElement(PurchaseRequestLocators.PRPrefixSelect, ElementType.Xpath);
		if (flag) {
			webDB.waitForClickElement(PurchaseRequestLocators.PRPrefixSelect, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRPrefixSelect, Prefix);
			if (flag) {
				logs.logging("INFO", "PR Prefix: " + Prefix + " selected");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click tick icon after fill form
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickTickIconCreateRequest() {
		flag = webDB.waitForElement(CommonUiLocators.CreatePRTickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.CreatePRTickIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(PurchaseRequestLocators.PurchaseIDHeader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create Tick Icon Clicked");
				String PRNum = webDB.getTextFromElement(PurchaseRequestLocators.PurchaseIDHeader, ElementType.Xpath);
				VariableHandler.setPRNumber(PRNum);
				logs.logging("INFO", "Purchase Request ID: " + VariableHandler.getPRNumber());
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to find PR using Item
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 */
	private void findWithItemId(String Item) {
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_ItemBtn, Item);
		clickFindSearchButton();
//		flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL", ElementType.Xpath);
//		if (flag) {
		List<WebElement> elements = webDB.getListOfElements(PurchaseRequestLocators.PRResultTableItem(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Item)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
//		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Item: " + Item);
	}

	/**
	 * This method used to find PR using Location
	 * 
	 * @author Sakthi-GWL
	 * @param Location
	 */
	private void findWithLocId(String Location) {
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_LocIDBtn, Location);
		clickFindSearchButton();
//		flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL", ElementType.Xpath);
//		if (flag) {
		List<WebElement> elements = webDB.getListOfElements(PurchaseRequestLocators.PRResultTableLocation(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			System.out.println("Get Text1 " + webElement.getText());
			if (webElement.getText().equalsIgnoreCase(Location)) {
				System.out.println("Get Text1  If  " + webElement.getText());
				flag = true;
			} else {
				flag = false;
				break a;
			}
//			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Location: " + Location);
	}

	/**
	 * This method used to find PR using Unit
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 */
	private void findWithUnit(String Unit) {
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_UnitBtn, Unit);
		clickFindSearchButton();
//		flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL", ElementType.Xpath);
//		if (flag) {
		List<WebElement> elements = webDB.getListOfElements(PurchaseRequestLocators.PRResultTableUnit(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Unit)) {
				flag = true;
			} else {
				flag = false;
				break a;
//				}
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Unit: " + Unit);
	}

	/**
	 * This method used to find PR using PRNo
	 * 
	 * @author Sakthi-GWL
	 * @param PRNo
	 */
	private void findWithPRType(String PRType) {
		flag = webDB.waitForElement(PurchaseRequestLocators.FindPR_PRTypeSelect, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(PurchaseRequestLocators.FindPR_PRTypeSelect, PRType, ElementType.Xpath);
			clickFindSearchButton();
//			flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL",
//					ElementType.Xpath);
//			if (flag) {
			List<WebElement> elements = webDB.getListOfElements(PurchaseRequestLocators.PRResultTablePRType(),
					ElementType.Xpath);
			a: for (WebElement webElement : elements) {
				if (webElement.getText().equalsIgnoreCase(PRType)) {
					flag = true;
				} else {
					flag = false;
					break a;
				}
//				}
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with PR Type: " + PRType);
	}

	/**
	 * This method used to find PR using PR-Type
	 * 
	 * @author Sakthi-GWL
	 * @param PRType
	 */
	private void findWithPRStatus(String Status) {
		flag = webDB.waitForElement(PurchaseRequestLocators.FindPR_PRStatusSelect, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(PurchaseRequestLocators.FindPR_PRStatusSelect, Status, ElementType.Xpath);
			clickFindSearchButton();
//			flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL",
//					ElementType.Xpath);
//			if (flag) {
			List<WebElement> elements = webDB.getListOfElements(PurchaseRequestLocators.PRResultTableStatusText,
					ElementType.Xpath);
			a: for (WebElement webElement : elements) {
				if (webElement.getText().equalsIgnoreCase(Status)) {
					flag = true;
				} else {
					flag = false;
					break a;
				}
//				}
			}

		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Status: " + Status);
	}

	/**
	 * This method used to find PR using Requester
	 * 
	 * @author Sakthi-GWL
	 * @param Reqstr
	 */
	private void findWithRequester(String Reqstr) {
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_RequesterBtn, Reqstr);
		clickFindSearchButton();
//		flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL", ElementType.Xpath);
//		if (flag) {
		List<WebElement> elements = webDB.getListOfElements(PurchaseRequestLocators.PRResultTableRequestor(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Reqstr)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
//			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Requestor: " + Reqstr);
	}

	/**
	 * This method used to upload csv file in PR and verify uploaded or not and
	 * Delete file After delete
	 * 
	 * @author Sakthi-GWL
	 * @param uploadingFile
	 */
	private void verifyUploadCsvFileInPRAndDeleteUpdatedFile(String uploadingFile) {
		String File = uploadingFile;
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_UploadTemplateBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PR_UploadTemplateBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.PR_UploadTemplateBtn, ElementType.Xpath);
			logs.logging("INFO", "File Upload Button Clicked");
			webDB.waitForElement(PurchaseRequestLocators.PR_UploadFileInput, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseRequestLocators.PR_UploadFileInput, (new File(File).getAbsolutePath()),
					ElementType.Xpath);
			webDB.waitForElement(PurchaseRequestLocators.PR_UploadedFileText, ElementType.Xpath);
			flag = webDB.getTextFromElement(PurchaseRequestLocators.PR_UploadedFileText, ElementType.Xpath)
					.contains(uploadingFile);
			if (flag) {
				logs.logging("INFO", uploadingFile + " - File Uploaded Successfully");
				webDB.clickAnElement(PurchaseRequestLocators.PR_UploadOkBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					flag = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath)
							.contains("Lines Uploaded Successfully");
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
		deleteUpdatedCsvFileAfterUploaded(uploadingFile);
		logs.logging("INFO", " PR Lines Uploaded Successfully Toast Displayed");
	}

	/**
	 * This method used to create a new csv file with template file by replacing
	 * Required Date as next day of System date
	 * 
	 * @author Sakthi-GWL
	 * @param TemplarteFileName
	 * @return String updatedFileName
	 */
	private String createUpdatedCsvFileWithRequiredDate(String TemplarteFileName) {
		String FileName = VariableHandler.testdataDir + TemplarteFileName;
		String updatedFile = "updated" + TemplarteFileName;

		LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yy");
		String tomorrowDate = myDateObj.format(myFormatObj);

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
					int dateColumnIndex = 4;
					columns[dateColumnIndex] = tomorrowDate; // Replace with tomorrow's date
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
	 * This method used to delete the csv file after uploaded in case of failure
	 * remove the file manually
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 */
	private void deleteUpdatedCsvFileAfterUploaded(String FileName) {
		File originalFile = new File(FileName);
		if (originalFile.delete()) {
			logs.logging("INFO", "Deleted the file: " + FileName);
		} else {
			logs.logging("FAIL", "Failed to Delete the file: " + FileName);
			Assert.fail("Failed to Delete the file: " + FileName);
		}
	}

	/**
	 * This method used to click Add Icon in PR CloseShort
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddIconPurchaseRequestCloseShort() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForClickElement(PurchaseRequestLocators.PRCloseShort_AddNewBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PRCloseShort_AddNewBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.PRCloseShort_AddNewBtn, ElementType.Xpath);
			flag = webDB.waitForClickElement(PurchaseRequestLocators.CreatePRButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PurchaseRequestLocators.CreatePRButton, ElementType.Xpath);
				webDB.clickAnElement(PurchaseRequestLocators.CreatePRButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToFrame(PurchaseRequestLocators.PRAmendFrame, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_LocId, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Add New Icon Clicked in PR CloseShort");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill details in PR CloseShort Page with LocID, PRNo and
	 * Reference
	 * 
	 * @author Sakthi-GWL
	 * @param locId
	 * @param PRNo
	 * @param Ref
	 */
	private void fillFormDetailsInPurchaseRequestCloseShort(String locId, String PRNo, String Ref) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_LocId, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRCloseShort_LocId, locId);
			webDB.switchToFrame(PurchaseRequestLocators.PRAmendFrame, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_Prefix, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRCloseShort_Prefix, PRNo);
				webDB.switchToFrame(PurchaseRequestLocators.PRAmendFrame, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_Reference, ElementType.Xpath);
				if (flag) {
					flag = webDB.sendTextToAnElement(PurchaseRequestLocators.PRCloseShort_Reference, Ref,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Reference entered in PR CloseShort: " + Ref);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click the tick icon in PR CloseShort Details Page and
	 * store document number
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickTickIconAndStoreDocumentNumberInPRCloseShort() {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_TickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.PRCloseShort_TickIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_DocNoText, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				String PRClsShrtDocNum = webDB.getDomPropertyFromElement(PurchaseRequestLocators.PRCloseShort_DocNoText,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				VariableHandler.setPRCloseShortDocNumber(PRClsShrtDocNum);
				logs.logging("INFO",
						"Purchase Request Close Short Document Number: " + VariableHandler.getPRCloseShortDocNumber());
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Load PR and select all items in PR and click Ok
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickLoadPRAndSelectAllItemstoLines() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkSuccessToastMessageDisappear();
		flag = webDB.waitForClickElement(PurchaseRequestLocators.PRCloseShort_LoadPRBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.PRCloseShort_LoadPRBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_FetchPRIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Load PR Clicked in PR CloseShort");
				webDB.switchToFrame(PurchaseRequestLocators.PRCloseShort_FetchPRIframe, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_SelectAllPRItem, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Fetch PR iframe Switched Successfully");
					webDB.clickAnElement(PurchaseRequestLocators.PRCloseShort_SelectAllPRItem, ElementType.Xpath);
					webDB.waitForClickElement(PurchaseRequestLocators.PRCloseShort_OkBtn, ElementType.Xpath);
					flag = webDB.isElementEnabled(PurchaseRequestLocators.PRCloseShort_OkBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseRequestLocators.PRCloseShort_OkBtn, ElementType.Xpath);
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(PurchaseRequestLocators.PRAUnitCostDataLastRow(),
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"Select All Item Checkbox clicked and Items added Successfully in PR CloseShort");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to save header and click close short then verify with
	 * success toast message
	 * 
	 * @author Sakthi-GWL
	 */
	private void saveHeaderAndClickCloseShort() {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_SaveHeaderBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PRCloseShort_SaveHeaderBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.PRCloseShort_SaveHeaderBtn, ElementType.Xpath);
			logs.logging("INFO", "PR CloseShort Header Saved Successfully.");
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForClickElement(PurchaseRequestLocators.PRCloseShort_CloseShortBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.moveToElement(PurchaseRequestLocators.PRCloseShort_CloseShortBtn, ElementType.Xpath);
				webDB.clickAnElement(PurchaseRequestLocators.PRCloseShort_CloseShortBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, CloseShort Successfully");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to search Purchase Request CloseShort Document Number and
	 * verify status with expected status
	 * 
	 * @author Sakthi-GWL
	 * @param DocumentNo
	 * @param ExpextedStatus
	 */
	private void searchDocNumberAndCheckPRCloseShortStatus(String DocumentNo, String ExpextedStatus) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_FindDocNo, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRCloseShort_FindDocNo, DocumentNo);
			flag = webDB.waitForElement(PurchaseRequestLocators.HeaderCheckBox, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.HeaderCheckBox, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_SearchBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseRequestLocators.PRCloseShort_SearchBtn, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseRequestLocators.PRStatusBoldText(DocumentNo),
							ElementType.Xpath);
					if (flag) {
						String Status = webDB.getTextFromElement(PurchaseRequestLocators.PRStatusBoldText(DocumentNo),
								ElementType.Xpath);
						logs.logging("INFO",
								"Document No: '" + DocumentNo + "' searched and displaying status: " + Status);
						flag = Status.equalsIgnoreCase(ExpextedStatus);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate line amount with unit cost and quantity
	 * 
	 * @author Sakthi-GWL
	 * @param cost
	 * @param quantity
	 */
	private void checkLineCostAmount(String cost, String quantity, String Item) {
		double Cost = Double.parseDouble(cost);
		double Quantity = Double.parseDouble(quantity);
		flag = webDB.waitForElement(PurchaseRequestLocators.LineAmountDataLastRow(Item), ElementType.Xpath);
		if (flag) {
			double LineAmount = Double.parseDouble(
					webDB.getTextFromElement(PurchaseRequestLocators.LineAmountDataLastRow(Item), ElementType.Xpath)
							.replace(",", ""));
			flag = LineAmount == (Cost * Quantity);
			logs.logging("INFO", "Cost * Quantity amount: " + (Cost * Quantity));
			logs.logging("INFO", "Line Amount: " + LineAmount);
			if (flag) {
				logs.logging("INFO", "Verified Line Amount with UnitCost and Quantity");
			} else {
				logs.logging("INFO", "Verified Failed Mismatch of Line Amount with UnitCost and Quantity");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate location in PR header
	 * 
	 * @author Sakthi-GWL
	 * @param Location
	 */
	private void validateLocationInPRHeader(String Location) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRHeader_Location, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(PurchaseRequestLocators.PRHeader_Location, ElementType.Xpath);
			flag = DisplayText.equalsIgnoreCase(Location);
			if (flag) {
				logs.logging("INFO", "verified Displayed Location in PR Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in PR Header");
				logs.logging("INFO", "Expected Text: " + Location + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate Unit in PR header
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 */
	private void validateUnitInPRHeader(String Unit) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRHeader_Unit, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getDomPropertyFromElement(PurchaseRequestLocators.PRHeader_Unit,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			flag = DisplayText.equalsIgnoreCase(Unit);
			if (flag) {
				logs.logging("INFO", "verified Displayed Unit in PR Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in PR Header");
				logs.logging("INFO", "Expected Text: " + Unit + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate PR Type in PR header
	 * 
	 * @author Sakthi-GWL
	 * @param PRType
	 */
	private void validatePRTypeInPRHeader(String PRType) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRHeader_PRType, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(PurchaseRequestLocators.PRHeader_PRType, ElementType.Xpath);
			flag = DisplayText.equalsIgnoreCase(PRType);
			if (flag) {
				logs.logging("INFO", "verified Displayed PR Type in PR Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in PR Header");
				logs.logging("INFO", "Expected Text: " + PRType + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate PR Prefix in PR header
	 * 
	 * @author Sakthi-GWL
	 * @param Prefix
	 */
	private void validatePrefixInPRHeader(String Prefix) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRHeader_PRPrefix, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(PurchaseRequestLocators.PRHeader_PRPrefix, ElementType.Xpath);
			flag = DisplayText.equalsIgnoreCase(Prefix);
			if (flag) {
				logs.logging("INFO", "verified Displayed Prefix in PR Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in PR Header");
				logs.logging("INFO", "Expected Text: " + Prefix + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate Department in PR header
	 * 
	 * @author Sakthi-GWL
	 * @param Department
	 */
	private void validateDepartmentInPRHeader(String Department) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRDepartmentSelect, ElementType.Xpath);
		if (flag) {
			WebElement element = webDB.getDriver().findElement(By.xpath(PurchaseRequestLocators.PRDepartmentSelect));
			Select select = new Select(element);
			for (WebElement Text : select.getAllSelectedOptions()) {
				if (Text.getText().equalsIgnoreCase(Department)) {
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
			if (flag) {
				logs.logging("INFO", "Selected Department in PR Header: " + Department);
			} else {
				logs.logging("INFO", "Verification failed in PR Header");
				logs.logging("INFO", "Expected Department: " + Department);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate Requester in PR header
	 * 
	 * @author Sakthi-GWL
	 * @param Rqstr
	 */
	private void validateRequestorInPRHeader(String Rqstr) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRHeader_Requester, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getDomPropertyFromElement(PurchaseRequestLocators.PRHeader_Requester,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			flag = DisplayText.equalsIgnoreCase(Rqstr);
			if (flag) {
				logs.logging("INFO", "verified Displayed Requester in PR Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in PR Header");
				logs.logging("INFO", "Expected Text: " + Rqstr + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate CPC Code in PR header
	 * 
	 * @author Sakthi-GWL
	 * @param CPCCode
	 */
	private void validateCPCCodeInPRHeader(String CPCCode) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRHeader_CPCCode, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getDomPropertyFromElement(PurchaseRequestLocators.PRHeader_CPCCode,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			flag = DisplayText.equalsIgnoreCase(CPCCode);
			if (flag) {
				logs.logging("INFO", "verified Displayed CPC Code in PR Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in PR Header");
				logs.logging("INFO", "Expected Text: " + CPCCode + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click lines details for the last added line item
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickLineDetailsIconForLastitem(String Item) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_LastItemDetailIcon(Item), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PR_LastItemDetailIcon(Item), ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.PR_LastItemDetailIcon(Item), ElementType.Xpath);
			purchaseOrderFunc.verifyClickNavigateToLineDetails();
			flag = webDB.waitForElement(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Details Icon Clicked for Last Item");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate PR Line Details in UI and Item Detail test data
	 * with passing field locator using GetText
	 * 
	 * @author Sakthi-GWL
	 * @param Locator
	 * @param Item
	 * @param lineDtlName
	 */
	private void validatePRLineDetailsWithGetText(String Locator, String Item, PRLineDetailsName lineDtlName,
			String DBData) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.PRLineDetails_Class, ElementType.Xpath);
//			if (flag) {
//				String DisplayText = webDB.getTextFromElement(Locator, ElementType.Xpath);
////				String expectedName = itmDtlHandler.getPRLineDetailsforGivenItem(Item, lineDtlName);
////				flag = DisplayText.equalsIgnoreCase(expectedName);
//				logs.logging("INFO", "Displayed Text: " + DisplayText);
////				logs.logging("INFO", "Expected Text: " + expectedName);
////				if (flag) {
//				logs.logging("INFO", "Verified Displayed and Expected Text for " + lineDtlName.name().toString());
//				if (flag) {
//					logs.logging("INFO", "DB Value Is: " + DBData);
//					flag = DBData.contains(DisplayText);
//					if (flag) {
//						logs.logging("INFO", "Verified Actual Value With DB Value");
//					}
//				}
//			} else {
//				logs.logging("INFO",
//						"Verification Failed!!... Displayed and Expected Text for " + lineDtlName.name().toString());
//			}
//		}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);

	}

	/**
	 * This method used to validate PR Line Details in UI and Item Detail test data
	 * with passing field locator using Get DOM property value
	 * 
	 * @author Sakthi-GWL
	 * @param Locator
	 * @param Item
	 * @param lineDtlName
	 */
	private void validatePRLineDetailsWithDomPropertyValue(String Locator, String Item, PRLineDetailsName lineDtlName,
			String DBData) {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.PRLineDetails_Class, ElementType.Xpath);
			if (flag) {
				String DisplayText = webDB.getDomPropertyFromElement(Locator, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
//				String expectedName = itmDtlHandler.getPRLineDetailsforGivenItem(Item, lineDtlName);
//				flag = DisplayText.equalsIgnoreCase(expectedName);
				logs.logging("INFO", "Displayed Text: " + DisplayText);
//				logs.logging("INFO", "Expected Text: " + expectedName);
//				if (flag) {
//				logs.logging("INFO", "Verified Displayed and Expected Text for " + lineDtlName.name().toString());
				if (flag) {
					logs.logging("INFO", "DB Value Is: " + DBData);
					flag = DBData.contains(DisplayText);
					if (flag) {
						logs.logging("INFO", "Verified Actual Value With DB Value");
					}
				}
			} else {
				logs.logging("INFO",
						"Verification Failed!!... Displayed and Expected Text for " + lineDtlName.name().toString());
//				}

			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click close icon in the Line Details - PR and verified
	 * 
	 * @author Sakthi-GWL
	 */
	private void closeLineDetailsAndVerify() {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRLineDetails_CloseIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PRLineDetails_CloseIcon, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.PRLineDetails_CloseIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = !webDB.verifyIsElementDisplayed(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Details Close Icon Clicked and verified");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Deepan-GWL
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.contains(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual And Expected Values");
		} else {
			logs.logging("INFO", "Mismatch With Actual And Expected");
		}
		assertTrue(flag);
	}

	/**
	 * Purchase Request Header Validation
	 * 
	 * @author Deepan-GWL
	 * @param Location
	 * @param Unit
	 * @param PRType
	 * @param Prefix
	 * @param CPCCode
	 * @param Department
	 * @param Requester
	 */
	public void purchaseRequestHeaderValidation(String Location, String Unit, String PRType, String Prefix,
			String CPCCode, String Department, String Requester) {
//		verifyAssertionBothValues(dbCommonFunctions.PRHeaderDetails(VariableHandler.getPRNumber(),
//				commonFunc.getDataFromTestDataSheet("UnitValue")), Unit);
//		verifyAssertionBothValues(dbCommonFunctions.PRHeaderDetails(VariableHandler.getPRNumber(),
//				commonFunc.getDataFromTestDataSheet("LocationValue")), Location);
//		verifyAssertionBothValues(dbCommonFunctions.PRHeaderDetails(VariableHandler.getPRNumber(),
//				commonFunc.getDataFromTestDataSheet("SourceValue")), PRType);
//		verifyAssertionBothValues(dbCommonFunctions.PRHeaderDetails(VariableHandler.getPRNumber(),
//				commonFunc.getDataFromTestDataSheet("PrhValue")), Prefix);
//		verifyAssertionBothValues(dbCommonFunctions.PRHeaderDetails(VariableHandler.getPRNumber(),
//				commonFunc.getDataFromTestDataSheet("CCCode")), CPCCode);
//		verifyAssertionBothValues(dbCommonFunctions.PRHeaderDetails(VariableHandler.getPRNumber(),
//				commonFunc.getDataFromTestDataSheet("DeptValue")), Department);
//		verifyAssertionBothValues(dbCommonFunctions.PRHeaderDetails(VariableHandler.getPRNumber(),
//				commonFunc.getDataFromTestDataSheet("RequestorValue")), Requester);
	}

	/**
	 * This Method Is Used To Verify Approve PR Using Ashok User
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyApprovePRUsingAshokUser() {
		flag = commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPRNumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
	}

	/**
	 * This Method used to fill the row lines for all modules
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param Qnty
	 * @param Cost
	 * @param addIcon
	 */
	public void fillItemsQuantityCostAndSaveLine(String Item, String Qnty, String Cost, String GlAccNumber) {
		flag = webDB.waitForElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
		if (flag) {
			flag = webDB.clickAnElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Lines Icon Clicked.");
				searchAndSelectItems(Item);
				enterQuantityForLastItem(Qnty);
				enterCostForLastItem(Cost);
				webDB.moveToElement(PurchaseRequestLocators.QuantityDataLastRow(), ElementType.Xpath);
				webDB.moveToElement(PurchaseRequestLocators.GlAccNo, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.GlAccNo, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseRequestLocators.GlAccNo, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.GlAccNoLovButton, GlAccNumber);
					saveLinesEntry();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify From create the purchase request(s) for General Items
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPRForGeneralItemWithMultipleItemInExcelSheet() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRGeneralSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				purchaseOrderFunc.verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		saveHeaderAndClickPost();
		searchPRAndCheckPRStatus(VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
	}

	/**
	 * Verify From create the purchase request(s) for Service Items
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyPRForServiceItemWithMultipleItemInExcelSheet() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRServiceSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				purchaseOrderFunc.verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		saveHeaderAndClickPost();
		searchPRAndCheckPRStatus(VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
	}

	/**
	 * This method used to click find Search Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickFindSearchButton() {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRHeaderCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.PRHeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseRequestLocators.PRFindResultSearchBar, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Button Clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Find the Purchase request with all possible search
	 * parameters
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFindPRWithPossibleSearchParameters() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		findWithItemId(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		webDB.navigateToRefresh();
		findWithLocId(commonFunc.getDataFromTestDataSheet("Loc_100"));
		webDB.navigateToRefresh();
		findWithUnit(commonFunc.getDataFromTestDataSheet("Unit_100"));
		webDB.navigateToRefresh();
//		findWithPRType(commonFunc.getDataFromTestDataSheet("PR_General"));
//		webDB.navigateToRefresh();
		findWithPRStatus(commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		webDB.navigateToRefresh();
		findWithRequester(commonFunc.getDataFromTestDataSheet("PR_Requester"));
		webDB.navigateToRefresh();
//		flag = commonFunc.logoutUser();
		return true;
	}

	/**
	 * This Method Is Used To Verify Add GL Acccount Number
	 * 
	 * @author Deepan-GWL
	 * @param GlAccNumber
	 */
	public void verifyAddGlAccountNumber(String GlAccNumber) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PRLineDetails_CloseIcon, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
//			commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.GlAccountNoLovButton, GlAccNumber);
//			webDB.switchToFrame(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.LineDetailSaveButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.LineDetailSaveButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
				closeLineDetailsAndVerify();
				flag = webDB.waitForElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);

			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Save Header and Post Purchase Request
	 * 
	 * @author Sakthi-GWL
	 */
	public void saveHeaderAndClickPostWithDevaUser() {
		flag = webDB.waitForElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			commonFunc.hardWait(2000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.moveToElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
				webDB.clickAnElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalPageFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
					commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"),
							VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("ErpUserId"),
							commonFunc.getDataFromTestDataSheet("U1"), System.getProperty("DevaUserName"));
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * create Purchase request for Material Type
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseRequestWithMultipleItemInExcelSheetForMaterial() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRGeneralSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				purchaseOrderFunc.verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		saveHeaderAndClickPost();
		searchPRAndCheckPRStatus(VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();

//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"ErpUserName");
//		commonFunc.navigatePurchaseRequestPage();
//		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("L0001"),
//				commonFunc.getDataFromTestDataSheet("U1"), commonFunc.getDataFromTestDataSheet("PRMaterialType"),
//				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
//				commonFunc.getDataFromTestDataSheet("PR_Department"),
//				commonFunc.getDataFromTestDataSheet("PR_Requester"));
//		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "QA Scenario - Purchase.xlsx";
//		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRMaterialSheet"));
//		for (int i = 3; i < itemmultiple.length; i++) {
//			String item = itemmultiple[i][1];
//			if (!(item.equals("") || item.equals("Total"))) {
//				String Quantity = itemmultiple[i][5];
//				String UnitCost = itemmultiple[i][6];
//				String GrossValue = itemmultiple[i][7];
//				String GlAccNumber = itemmultiple[i][8];
//				fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost, GlAccNumber);
//				purchaseOrderFunc.verifyLineAmountCalculationValidation(GrossValue, item,
//						System.getProperty("HodValue"));
////				clickLineDetailsIconForLastitem();
////				verifyAddGlAccountNumber(GlAccNumber);
//			} else {
//				break;
//			}
//		}
//
//		saveHeaderAndClickPost();
//		searchPRAndCheckPRStatus(VariableHandler.getPRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
	}

//	/**
//	 * This method used to click lines details for the last added line item
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	public void clickLineDetailsIconForLastitem(String Item) {
//		flag = webDB.waitForElement(PurchaseRequestLocators.LineDetailsButton(Item), ElementType.Xpath);
//		if (flag) {
//			webDB.moveToElement(PurchaseRequestLocators.LineDetailsButton(Item), ElementType.Xpath);
//			webDB.clickAnElement(PurchaseRequestLocators.LineDetailsButton(Item), ElementType.Xpath);
//			flag = webDB.waitForElement(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Line Details Icon Clicked for Last Item");
//			}
//		}
//		Assert.assertTrue(flag);
//	}

	/**
	 * This Method Is Used To Verify PR Amendment
	 * 
	 * @author Deepan-GWL
	 * @param Location
	 */
	public void verifyPrAmendment(String Location, String Quantity) {
		commonFunc.navigatePurchaseRequestCloseShortPage();
		clickAddIconPurchaseRequestCloseShort();
		fillFormDetailsInPurchaseRequestCloseShort(Location, VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("RefText_Test"));
		clickTickIconAndStoreDocumentNumberInPRCloseShort();
		clickLoadPRAndSelectAllItemstoLines();
		verifyAddNewQuantity(Quantity);
		verifyPRAmendmentHeaderSaveAndPost();

		searchDocNumberAndCheckPRCloseShortStatus(VariableHandler.getPRCloseShortDocNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
	}

	/**
	 * This Method Is Used To Verify Add New Quantity
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddNewQuantity(String Quantity) {
		commonFunc.verifyAddTableSpecs(PurchaseRequestLocators.NewQuantityTable,
				PurchaseRequestLocators.NewQuantityInput, Quantity);
		flag = webDB.waitForElement(PurchaseRequestLocators.LineSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.LineSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccesToastCloseIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PR Amendment Line Value Entered and Saved Successfully");
				webDB.clickAnElement(CommonUiLocators.SuccesToastCloseIcon, ElementType.Xpath);

			}
			flag = webDB.waitForElement(PurchaseRequestLocators.HeaderSaveButton, ElementType.Xpath);
//		}
		}

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify PR Amendment Header Save And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRAmendmentHeaderSaveAndPost() {
		flag = webDB.waitForElement(PurchaseRequestLocators.HeaderSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.HeaderSaveButton, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.HeaderSaveButton, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(PurchaseRequestLocators.HeaderPostButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.moveToElement(PurchaseRequestLocators.HeaderPostButton, ElementType.Xpath);
				webDB.clickAnElement(PurchaseRequestLocators.HeaderPostButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalPageFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getPRCloseShortDocNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Item Details Tab Opening
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyItemDetailsTabOpening() {
		flag = webDB.waitForElement(PurchaseRequestLocators.ItemDetailsButton, ElementType.Xpath);
		if (flag) {
			String Value = webDB.getDomAttributeFromElement(PurchaseRequestLocators.ItemDetailsButton,
					ElementType.Xpath, "aria-expanded");
			if (Value.equalsIgnoreCase("false")) {
				webDB.clickAnElement(PurchaseRequestLocators.ItemDetailsButton, ElementType.Xpath);
			}
			flag = webDB.waitForElement(PurchaseRequestLocators.PRLineDetails_Warehouse, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Details Expand Button Is Displayed");
			}
		}
		assertTrue(flag);
	}
}
