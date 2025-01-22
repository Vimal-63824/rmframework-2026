package commonfunctions.supplychainmanagement;

import java.util.List;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
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

	/**
	 * This Methods Is Used To Verify Creation Of PR General
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreationOfPRGeneral() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_Service"), commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseRequestPage();
		clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
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
	 * This method used to select location PR-type prefix department and requester
	 * for new purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param location
	 * @param PRtype
	 * @param department
	 * @param requester
	 */
	public void clickAddIconFillDetailsOfPurchaseRequest(String Location, String PRType, String Prefix, String CPCCode,
			String Department, String Requester) {
		commonFunc.clickCreateIcon();
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRLocationButton, Location);
		selectPRType(PRType);
		selectPRPrefix(Prefix);
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRCPCCodeButton, CPCCode);
		selectPRDepartment(Department);
		selectPRRequester(Requester);
		clickTickIconCreateRequest();
	}

	/**
	 * This Method Used To Verify Check Purchase Request Status PR
	 * 
	 * @author Sakthi-GWL
	 * @param PRNumber
	 * @param ExpextedStatus
	 */
	public void searchPRAndCheckPRStatus(String PRNumber, String ExpextedStatus) {
		if (webDB.isElementDisplayed(PurchaseRequestLocators.BackIconBtn, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseRequestLocators.BackIconBtn, ElementType.Xpath);
		}
		commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_PRNoButton, PRNumber);
		flag = webDB.waitForElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
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
		flag = commonFunc.checkRefreshCellsDisappear();
		if (flag) {
			searchAndSelectItems(Item);
			flag = commonFunc.checkRefreshCellsDisappear();
			if (flag) {
				enterQuantityForLastItem(Qnty);
				flag = commonFunc.checkRefreshCellsDisappear();
				if (flag) {
					enterCostForLastItem(Cost);
					flag = commonFunc.checkRefreshCellsDisappear();
					if (flag) {
						saveLinesEntry();
						flag = commonFunc.checkRefreshCellsDisappear();
					}
				}
			}
		}
		Assert.assertTrue(flag);
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

	/*----------------------private methods---------------------------*/

	/**
	 * This method used to save the lines after enter items
	 * 
	 * @author Sakthi-GWL
	 */
	private void saveLinesEntry() {
		flag = webDB.waitForElement(PurchaseRequestLocators.SaveLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseRequestLocators.SaveLineBtn, ElementType.Xpath);
			webDB.clickAnElement(PurchaseRequestLocators.SaveLineBtn, ElementType.Xpath);
			if (webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath)) {
				flag = webDB.javaScriptClickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				if (flag)
					logs.logging("INFO", "Save Item Entry Successfully");
			} else {
				flag = webDB.isElementDisplayed(CommonUiLocators.FailedNotifyMessage, ElementType.Xpath);
				if (flag) {
					flag = false;
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

}
