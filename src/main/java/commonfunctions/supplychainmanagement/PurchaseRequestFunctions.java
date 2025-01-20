package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
	 * This Methos Is Used To Verify Creation Of PR General
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreationOfPRGeneral() {
		flag = commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		if (flag) {
			flag = commonFunc.navigatePurchaseRequestPage();
			if (flag) {
				flag = clickAddIcon_CreateNewPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("PR_General"),
						commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
						commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
						commonFunc.getDataFromTestDataSheet("PR_Department"),
						commonFunc.getDataFromTestDataSheet("PR_Requester"));
				if (flag) {
					flag = fillItemsQuantityCostAndSaveLine_PR(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
							commonFunc.getDataFromTestDataSheet("QuantityValue"),
							commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
					if (flag) {
						flag = saveHeaderAndClickPost_PR();
						if (flag) {
							flag = checkPurchaseRequestStatus_PR(VariableHandler.getPRNumber(), "Approved");
							if (flag) {
								flag = commonFunc.logoutUser();
							}
						}
					}
				}
			}
		}
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
	 * @param requestor
	 * @return boolean
	 */
	public boolean clickAddIcon_CreateNewPurchaseRequest(String location, String PRtype, String prefix, String cpcCode,
			String department, String requestor) {
		flag = commonFunc.clickCreateIcon();
		if (flag) {
			flag = commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRLocationButton, location);
			if (flag) {
				flag = selectPRType_PR(PRtype);
				if (flag) {
					flag = selectPRPrefix_PR(prefix);
					if (flag) {
						flag = commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.PRCPCCodeButton,
								cpcCode);
						if (flag) {
							flag = selectPRDepartment_PR(department);
							if (flag) {
								flag = selectPRRequester_PR(requestor);
								if (flag) {
									flag = clickTickIconCreateRequest_PR();
								}
							}
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * This Method Used To Verify Check Purchase Request Status PR
	 * 
	 * @author Sakthi-GWL
	 * @param PRNumber
	 * @param ExpextedStatus
	 * @return
	 */
	public boolean checkPurchaseRequestStatus_PR(String PRNumber, String ExpextedStatus) {
		if (webDB.isElementDisplayed(PurchaseRequestLocators.BackIconBtn, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseRequestLocators.BackIconBtn, ElementType.Xpath);
		}
		flag = commonFunc.searchAndSelectValueInFilters(PurchaseRequestLocators.FindPR_PRNoButton, PRNumber);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.PRStatusBoldText, ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(PurchaseRequestLocators.PRStatusBoldText, ElementType.Xpath);
				logs.logging("INFO", "PR Number: '" + PRNumber + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpextedStatus);
			}
		}
		return flag;
	}

	/**
	 * This Method used to fill the row lines for all modules
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param Qnty
	 * @param Cost
	 * @param addIcon
	 * @return boolean
	 */
	public boolean fillItemsQuantityCostAndSaveLine_PR(String Item, String Qnty, String Cost, boolean... addIcon) {
		if (addIcon != null) {
			flag = webDB.waitForElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(PurchaseRequestLocators.AddLineIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Add Lines Icon Clicked.");
				}
			}
		}
		flag = searchAndSelectItems_PR(Item);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			flag = enterQuantityForLastItem_PR(Qnty);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				flag = enterCostForLastItem_PR(Cost);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					flag = saveLinesEntry_PR();
				}
			}
		}
		return flag;
	}

	/**
	 * This method used to Save Header and Post Purchase Request
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean saveHeaderAndClickPost_PR() {
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
		return flag;
	}

	/*----------------------private methods---------------------------*/

	/**
	 * This method used to save the lines after enter items
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	private boolean saveLinesEntry_PR() {
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
		return flag;
	}

	/**
	 * This method used to searched and Select Items in Lines for Purchase Entry -
	 * called in updatePurchaseRequest
	 * 
	 * @param Item
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	private boolean searchAndSelectItems_PR(String Item) {
		flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataLastRow, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseRequestLocators.ItemDataLastRow, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(PurchaseRequestLocators.ItemDataListIcon, ElementType.Xpath, 60);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.ItemDataListIcon, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseRequestLocators.ItemDataPopupSearchBox, ElementType.Xpath);
				if (flag) {
					webDB.waitForElementUntilGivenTime(PurchaseRequestLocators.ItemDataSearchFirstResult,
							ElementType.Xpath, 30);
					webDB.clearTextField(PurchaseRequestLocators.ItemDataPopupSearchBox, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseRequestLocators.ItemDataPopupSearchBox, Item, ElementType.Xpath);
					flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.convertXPath(Item), ElementType.Xpath,
							60);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.clickAnElement(CommonUiLocators.convertXPath(Item), ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", Item + "- Item Searched and Selected");
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * This method used to Enter Quantity in Lines for Purchase Entry - called in
	 * updatePurchaseRequest
	 * 
	 * @param quantity
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	private boolean enterQuantityForLastItem_PR(String quantity) {
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
					js.executeScript("arguments[0].value='" + quantity + "';", element);
					flag = true;
					logs.logging("INFO", "Quantity Entered: " + quantity);
				} catch (Exception e) {
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * This method used to enter Cost in Lines for Purchase Entry - called in
	 * updatePurchaseRequest
	 * 
	 * @param Cost
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	private boolean enterCostForLastItem_PR(String Cost) {
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
					flag = true;
					logs.logging("INFO", "Cost Entered: " + Cost);
				} catch (Exception e) {
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * This method used to select PR-Type while create purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 * @return boolean
	 */
	private boolean selectPRType_PR(String Type) {
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
		return flag;
	}

	/**
	 * This method used to select PR-Department while create purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param Dept
	 * @return boolean
	 */
	private boolean selectPRDepartment_PR(String Dept) {
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
		return flag;
	}

	/**
	 * This method used to select PR-Requestor while create purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param Rqstr
	 * @return boolean
	 */
	private boolean selectPRRequester_PR(String Rqstr) {
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
		return flag;
	}

	/**
	 * This method used to select Prefix while create purchase request
	 * 
	 * @author Sakthi-GWL
	 * @param prefix
	 * @return boolean
	 */
	private boolean selectPRPrefix_PR(String prefix) {
		if (commonFunc.getDataFromTestDataSheet(prefix) != null) {
			prefix = commonFunc.getDataFromTestDataSheet(prefix);
		}
		flag = webDB.waitForElement(PurchaseRequestLocators.PRPrefixSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(PurchaseRequestLocators.PRPrefixSelect, prefix, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PR Prefix: " + prefix + " selected");
			}
		}
		return flag;
	}

	/**
	 * This method used to click tick icon after fill form
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	private boolean clickTickIconCreateRequest_PR() {
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
		return flag;
	}

}
