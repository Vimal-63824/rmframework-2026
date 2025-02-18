package commonfunctions.supplychainmanagement;

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
import commonfunctions.ItemDetailsHandler;
import commonfunctions.ItemDetailsHandler.PRLineDetailsName;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseRequestFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static ItemDetailsHandler itmDtlHandler = new ItemDetailsHandler();

	/**
	 * This Methods Is Used To Verify Creation Of PR General
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreationOfPRGeneral() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
	public boolean verifyCreatePRByUploadExcel() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify PR Close Short
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyPRCloseShort() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		flag = commonFunc.logoutUser();
		return flag;
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
		commonFunc.clickCreateIcon();
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRLocationButton, Location);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		selectPRType(PRType);
		commonFunc.checkRefreshCellsDisappear();
		selectPRPrefix(Prefix);
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRCPCCodeButton, CPCCode);
		selectPRDepartment(Department);
		selectPRRequester(Requester);
		clickTickIconCreateRequest();
		validateLocationInPRHeader(Location);
		validateUnitInPRHeader(Unit);
		validatePRTypeInPRHeader(PRType);
		validatePrefixInPRHeader(Prefix);
		validateDepartmentInPRHeader(Department);
		validateRequestorInPRHeader(Requester);
		validateCPCCodeInPRHeader(CPCCode);
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
		flag = webDB.waitForElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseRequestLocators.PRStatusBoldText, ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(PurchaseRequestLocators.PRStatusBoldText, ElementType.Xpath);
				logs.logging("INFO", "PR Number: '" + PRNumber + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpextedStatus);
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
		if (addIcon != null) {
			flag = webDB.waitForElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Add Lines Icon Clicked.");
				}
			}
		}
		searchAndSelectItems(Item);
		enterQuantityForLastItem(Qnty);
		enterCostForLastItem(Cost);
		saveLinesEntry();
		checkLineCostAmount(Cost, Qnty);
		clickLineDetailsIconForLastitem();
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
			webDB.moveToElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.SaveHeaderBtn, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.moveToElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
				webDB.clickAnElement(PurchaseRequestLocators.PostBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, Posted Successfully");
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
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
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_LineDeleteIconWithData(DataValue), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.ItemDataLastRow, ElementType.Xpath);
			webDB.moveToElement(PurchaseRequestLocators.PR_LineDeleteIconWithData(DataValue), ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.PR_LineDeleteIconWithData(DataValue), ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Toast Message Displayed, Line Deleted Successfully with value: " + DataValue);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
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
		validatePRLineDetailsWithGetText(PurchaseRequestLocators.PRLineDetails_SubClass, Item,
				PRLineDetailsName.SUBCLASS);
		validatePRLineDetailsWithGetText(PurchaseRequestLocators.PRLineDetails_Class, Item, PRLineDetailsName.CLASS);
		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_Warehouse, Item,
				PRLineDetailsName.WAREHOUSE);
		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_ConventionFactor, Item,
				PRLineDetailsName.CONVENTION_FACTOR);
		validatePRLineDetailsWithGetText(PurchaseRequestLocators.PRLineDetails_PurchaseUOM, Item,
				PRLineDetailsName.PURCHASE_UOM);
		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_GLAccNo, Item,
				PRLineDetailsName.GL_ACC_NO);
		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_GLAccDesc, Item,
				PRLineDetailsName.GL_ACC_DESC);
		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_ItemCPCCode, Item,
				PRLineDetailsName.CPC_CODE);
		validatePRLineDetailsWithDomPropertyValue(PurchaseRequestLocators.PRLineDetails_ItemCPCDesc, Item,
				PRLineDetailsName.CPC_DESC);
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
		flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataLastRow, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.ItemDataLastRow, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(PurchaseRequestLocators.ItemDataListIcon, ElementType.Xpath, 60);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.ItemDataListIcon, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataPopupSearchBox, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.clearTextField(PurchaseRequestLocators.ItemDataPopupSearchBox, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseRequestLocators.ItemDataPopupSearchBox, Item, ElementType.Xpath);
					flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.convertXPath(Item), ElementType.Xpath,
							60);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(CommonUiLocators.convertXPath(Item), ElementType.Xpath);
						String value = webDB.getAttributeFromElement(PurchaseRequestLocators.ItemDataLastRowInput,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						logs.logging("INFO", "Get Text Item Name Value from Table Is: " + value);
						flag = value.contains(Item);
						if (flag) {
							logs.logging("INFO", Item + "- Item Searched and Selected");
						}
					}
				}
			}
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
		JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
		flag = webDB.waitForClickElement(PurchaseRequestLocators.QuantityDataLastRow, ElementType.Xpath);
		if (flag) {
			webDB.scrollToAnElement(PurchaseRequestLocators.QuantityDataLastRow, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.QuantityDataLastRow, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.QuantityDataLastRowInput, ElementType.Xpath);
			if (flag) {
				WebElement element = webDB.getDriver()
						.findElement(By.xpath(PurchaseRequestLocators.QuantityDataLastRowInput));
				try {
					js.executeScript("arguments[0].value='" + Quantity + "';", element);
					String value = webDB.getAttributeFromElement(PurchaseRequestLocators.QuantityDataLastRowInput,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Text Quantity Name Value from Table Is: " + value);
					flag = value.contains(Quantity);
					if (flag) {
						logs.logging("INFO", "Quantity Entered: " + Quantity);
					}
				} catch (Exception e) {
					flag = false;
				}
			}
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
		JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
		flag = webDB.waitForClickElement(PurchaseRequestLocators.UnitCostDataLastRow, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.UnitCostDataLastRow, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.UnitCostDataLastRowInput, ElementType.Xpath);
			if (flag) {
				WebElement element1 = webDB.getDriver()
						.findElement(By.xpath(PurchaseRequestLocators.UnitCostDataLastRowInput));
				try {
					js.executeScript("arguments[0].value='" + Cost + "';", element1);
					String value = webDB.getAttributeFromElement(PurchaseRequestLocators.UnitCostDataLastRowInput,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Text Cost Name Value from Table Is: " + value);
					flag = value.contains(Cost);
					if (flag) {
						logs.logging("INFO", "Cost Entered: " + Cost);
					}
				} catch (Exception e) {
					flag = false;
				}
			}
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
			flag = webDB.selectDropDownByText(PurchaseRequestLocators.PRPrefixSelect, Prefix, ElementType.Xpath);
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
		flag = webDB.waitForElement(CommonUiLocators.CreateTickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.CreateTickIcon, ElementType.Xpath);
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
		flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL", ElementType.Xpath);
		if (flag) {
			List<WebElement> elements = webDB.getDriver().findElements(By.xpath("//table//td[9]"));
			a: for (WebElement webElement : elements) {
				if (webElement.getText().equals(Item)) {
					flag = false;
					break a;
				} else {
					flag = true;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to find PR using Location
	 * 
	 * @author Sakthi-GWL
	 * @param Location
	 */
	private void findWithLocId(String Location) {
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_LocIDBtn, Location);
		flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL", ElementType.Xpath);
		if (flag) {
			List<WebElement> elements = webDB.getDriver().findElements(By.xpath("//table//td[2]"));
			a: for (WebElement webElement : elements) {
				if (webElement.getText().equals(Location)) {
					flag = false;
					break a;
				} else {
					flag = true;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to find PR using Unit
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 */
	private void findWithUnit(String Unit) {
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_UnitBtn, Unit);
		flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL", ElementType.Xpath);
		if (flag) {
			List<WebElement> elements = webDB.getDriver().findElements(By.xpath("//table//td[3]"));
			a: for (WebElement webElement : elements) {
				if (webElement.getText().equals(Unit)) {
					flag = false;
					break a;
				} else {
					flag = true;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to find PR using PRNo
	 * 
	 * @author Sakthi-GWL
	 * @param PRNo
	 */
	private void findWithPRNo(String PRNo) {
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_PRNoButton, PRNo);
		flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL", ElementType.Xpath);
		if (flag) {
			List<WebElement> elements = webDB.getDriver().findElements(By.xpath("//table//td[6]/a"));
			a: for (WebElement webElement : elements) {
				if (webElement.getText().equals(PRNo)) {
					flag = false;
					break a;
				} else {
					flag = true;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to find PR using PR-Type
	 * 
	 * @author Sakthi-GWL
	 * @param PRType
	 */
	private void findWithPRType(String PRType) {
		flag = webDB.selectDropDownByText(PurchaseRequestLocators.FindPR_PRTypeSelect, PRType, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL",
					ElementType.Xpath);
			if (flag) {
				List<WebElement> elements = webDB.getDriver().findElements(By.xpath("//table//td[4]"));
				a: for (WebElement webElement : elements) {
					if (webElement.getText().equals(PRType)) {
						flag = false;
						break a;
					} else {
						flag = true;
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to find PR using Requester
	 * 
	 * @author Sakthi-GWL
	 * @param Reqstr
	 */
	private void findWithRequester(String Reqstr) {
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_PRNoButton, Reqstr);
		flag = webDB.selectDropDownByText(PurchaseRequestLocators.PR_TableViewCountSelect, "ALL", ElementType.Xpath);
		if (flag) {
			List<WebElement> elements = webDB.getDriver().findElements(By.xpath("//table//td[8]"));
			a: for (WebElement webElement : elements) {
				if (webElement.getText().equals(Reqstr)) {
					flag = false;
					break a;
				} else {
					flag = true;
				}
			}
		}
		Assert.assertTrue(flag);
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
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_LocId, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add New Icon Clicked in PR CloseShort");
			}
		}
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
			flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_Prefix, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRCloseShort_Prefix, PRNo);
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
		flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_LoadPRBtn, ElementType.Xpath);
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
						flag = webDB.waitForElement(PurchaseRequestLocators.UnitCostDataLastRow, ElementType.Xpath);
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
			flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_SearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.PRCloseShort_SearchBtn, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.PRStatusBoldText, ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(PurchaseRequestLocators.PRStatusBoldText,
							ElementType.Xpath);
					logs.logging("INFO", "Document No: '" + DocumentNo + "' searched and displaying status: " + Status);
					flag = Status.equalsIgnoreCase(ExpextedStatus);
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
	private void checkLineCostAmount(String cost, String quantity) {
		double Cost = Double.parseDouble(cost);
		double Quantity = Double.parseDouble(quantity);
		flag = webDB.waitForElement(PurchaseRequestLocators.LineAmountDataLastRow, ElementType.Xpath);
		if (flag) {
			double LineAmount = Double.parseDouble(
					webDB.getTextFromElement(PurchaseRequestLocators.LineAmountDataLastRow, ElementType.Xpath)
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
	private void clickLineDetailsIconForLastitem() {
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_LastItemDetailIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.PR_LastItemDetailIcon, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.PR_LastItemDetailIcon, ElementType.Xpath);
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
	private void validatePRLineDetailsWithGetText(String Locator, String Item, PRLineDetailsName lineDtlName) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.PRLineDetails_Class, ElementType.Xpath);
			if (flag) {
				String DisplayText = webDB.getTextFromElement(Locator, ElementType.Xpath);
				String expectedName = itmDtlHandler.getPRLineDetailsforGivenItem(Item, lineDtlName);
				flag = DisplayText.equalsIgnoreCase(expectedName);
				logs.logging("INFO", "Displayed Text: " + DisplayText);
				logs.logging("INFO", "Expected Text: " + expectedName);
				if (flag) {
					logs.logging("INFO", "Verified Displayed and Expected Text for " + lineDtlName.name().toString());
				} else {
					logs.logging("INFO", "Verification Failed!!... Displayed and Expected Text for "
							+ lineDtlName.name().toString());
				}

			}
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
	private void validatePRLineDetailsWithDomPropertyValue(String Locator, String Item, PRLineDetailsName lineDtlName) {
		flag = webDB.waitForElement(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseRequestLocators.PR_LineDetailsIframe, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.PRLineDetails_Class, ElementType.Xpath);
			if (flag) {
				String DisplayText = webDB.getDomPropertyFromElement(Locator, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				String expectedName = itmDtlHandler.getPRLineDetailsforGivenItem(Item, lineDtlName);
				flag = DisplayText.equalsIgnoreCase(expectedName);
				logs.logging("INFO", "Displayed Text: " + DisplayText);
				logs.logging("INFO", "Expected Text: " + expectedName);
				if (flag) {
					logs.logging("INFO", "Verified Displayed and Expected Text for " + lineDtlName.name().toString());
				} else {
					logs.logging("INFO", "Verification Failed!!... Displayed and Expected Text for "
							+ lineDtlName.name().toString());
				}

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
}
