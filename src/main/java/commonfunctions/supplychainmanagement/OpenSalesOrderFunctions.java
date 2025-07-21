package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class OpenSalesOrderFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

	/**
	 * This Method used to select Open So Type with radio Type like Amendment or
	 * Open So
	 * 
	 * @author Sakthi-GWL
	 * @param SOtype
	 */
	public void selectOpenSOTypeWithRadioButtons(String SOtype) {
		if (SOtype.contains("Amendment")) {
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOAmendRadioBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(OpenSalesOrderLocators.OpenSOAmendRadioBtn, ElementType.Xpath);
				webDB.clickAnElement(OpenSalesOrderLocators.OpenSOAmendRadioBtn, ElementType.Xpath);
				flag = webDB.waitForElement(OpenSalesOrderLocators.FindHeadingText, ElementType.Xpath);
				if (flag) {
					String headerText = webDB.getTextFromElement(OpenSalesOrderLocators.FindHeadingText,
							ElementType.Xpath);
					logs.logging("INFO", "Page navigate to " + headerText);
				}
			}
		} else {
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSORadioBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(OpenSalesOrderLocators.OpenSORadioBtn, ElementType.Xpath);
				webDB.clickAnElement(OpenSalesOrderLocators.OpenSORadioBtn, ElementType.Xpath);
				flag = webDB.waitForElement(OpenSalesOrderLocators.FindHeadingText, ElementType.Xpath);
				if (flag) {
					String headerText = webDB.getTextFromElement(OpenSalesOrderLocators.FindHeadingText,
							ElementType.Xpath);
					logs.logging("INFO", "Page navigate to " + headerText);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Add Icon in Open SO
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddNewOpenSOPlsIcon() {
		flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSO_CreatePlusIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSalesOrderLocators.OpenSO_CreatePlusIcon, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOCustomerLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Open SO Add New Icon Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill location customer prefix in Open SO creation
	 * 
	 * @author Sakthi-GWL
	 * @param location
	 * @param customer
	 * @param Prefix
	 */
	public void fillLocationCustomerPrefixInOpenSO(String location, String customer, String Prefix) {
		commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.OpenSOLocationLovBtn, location);
		commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.OpenSOCustomerLovBtn, customer);
		flag = webDB.waitForClickElement(OpenSalesOrderLocators.OpenSoPrefixSelect, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.selectDropDownByText(OpenSalesOrderLocators.OpenSoPrefixSelect, Prefix, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Selected Prefix in Open SO: " + Prefix);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill SO Type and PO Number in Open SO Creation
	 * 
	 * @author Sakthi-GWL
	 * @param SOType
	 * @param PoNumber
	 */
	public void fillSOTypeAndPONumberInOpenSO(String SOType, String PoNumber) {
		flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOTypeSelect, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(OpenSalesOrderLocators.OpenSOTypeSelect, SOType, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSO_PONumberInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Selected SO Type in Open SO: " + SOType);
				flag = webDB.sendTextToAnElement(OpenSalesOrderLocators.OpenSO_PONumberInput, PoNumber,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Entered PO Number in Open SO: " + PoNumber);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Tick Icon and Fill PO Date in Open SO and Store
	 * Open SO Number
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo() {
		flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSO_FormTickIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.OpenSO_FormTickIcon, ElementType.Xpath);
			webDB.clickAnElement(OpenSalesOrderLocators.OpenSO_FormTickIcon, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSODisplayDocNumber, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Open SO Tick Icon Clicked");
				String DocNo = webDB.getTextFromElement(OpenSalesOrderLocators.OpenSODisplayDocNumber,
						ElementType.Xpath);
				VariableHandler.setOpenSONumber(DocNo);
				logs.logging("INFO", "Displayed SO Document Number: " + DocNo);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Fetch Customer and Select Item checkbox and click
	 * Ok
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 */
	public void clickFetchCustomerAndSelectItemInOpenSO(String Item) {
		flag = webDB.waitForElement(OpenSalesOrderLocators.FetchCustomerItemBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.FetchCustomerItemBtn, ElementType.Xpath);
			webDB.clickAnElement(OpenSalesOrderLocators.FetchCustomerItemBtn, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.FetchCustomerIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Fetch Customer Item Button Clicked");
				webDB.switchToFrame(OpenSalesOrderLocators.FetchCustomerIframe, ElementType.Xpath);
				flag = webDB.waitForElement(OpenSalesOrderLocators.fetchCustomerItemCheckedBox(Item),
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Displayed and switched successfully");
					webDB.moveToElement(OpenSalesOrderLocators.fetchCustomerItemCheckedBox(Item), ElementType.Xpath);
					webDB.clickAnElement(OpenSalesOrderLocators.fetchCustomerItemCheckedBox(Item), ElementType.Xpath);
					commonFunc.hardWait(2000);
					flag = webDB.isElementEnabled(OpenSalesOrderLocators.FetchCustomerOkBtn, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(OpenSalesOrderLocators.FetchCustomerOkBtn, ElementType.Xpath);
						webDB.clickAnElement(OpenSalesOrderLocators.FetchCustomerOkBtn, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method used to Fill Price, MRP and HSN Code for Last item and Save the
	 * line
	 * 
	 * @author Sakthi-GWL
	 * @param price
	 * @param mrp
	 * @param HSN
	 */
	public void fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(String price, String mrp, String HSN) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(OpenSalesOrderLocators.PriceLastLineData,
				OpenSalesOrderLocators.PriceLastLineInput, price);
		VariableHandler.setOpenSOPriceAmount(price);
		commonPayrollFunc.addDetailInTableWithoutLovButton(OpenSalesOrderLocators.MRPLastLineData,
				OpenSalesOrderLocators.MRPLastLineInput, mrp);
		flag = webDB.waitForElement(OpenSalesOrderLocators.HSNCodeLastLineData, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.HSNCodeLastLineData, ElementType.Xpath);
			webDB.clickAnElement(OpenSalesOrderLocators.HSNCodeLastLineData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.HSNCodeLastLineLovBtn, HSN);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Discount in Last Added Line Open SO
	 * 
	 * @author Sakthi-GWL
	 * @param Discount
	 */
	public void fillDiscountAndSaveLinesforAddedLastItemInOpenSO(String Discount) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(OpenSalesOrderLocators.DiscountLastLineData,
				OpenSalesOrderLocators.DiscountLastLineInput, Discount);
		flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOLinesSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.OpenSOLinesSaveBtn, ElementType.Xpath);
			webDB.clickAnElement(OpenSalesOrderLocators.OpenSOLinesSaveBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				logs.logging("INFO", "Lines Saved Successfully Toast Message Displayed");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to Enter Effective From, To and PO Date in Open So Header
	 * 
	 * @author Sakthi-GWL
	 * @param EffcFromDate
	 * @param EffcToDate
	 * @param PODate
	 */
	public void enterEffectiveFromAndToDate(String EffcFromDate, String EffcToDate, String PODate) {
		flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOEffDateFrom, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(OpenSalesOrderLocators.OpenSOEffDateFrom, EffcFromDate, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOEffDateTo, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Effective From Date: " + EffcFromDate);
				flag = webDB.sendTextToAnElement(OpenSalesOrderLocators.OpenSOEffDateTo, EffcToDate, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Effective To Date: " + EffcToDate);
					commonPayrollFunc.addDetailInTableWithoutLovButton(OpenSalesOrderLocators.OpenSO_PODateInput,
							OpenSalesOrderLocators.OpenSO_PODateInput, PODate);
					logs.logging("INFO", "Entered PO Date in Open SO: " + PODate);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Header Save Icon In Open SO
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickHeaderSaveAndClickPostBtnInOpenSO() {
		flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOHeaderSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.OpenSOHeaderSaveBtn, ElementType.Xpath);
			webDB.clickAnElement(OpenSalesOrderLocators.OpenSOHeaderSaveBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForClickElement(OpenSalesOrderLocators.OpenSOPostBTN, ElementType.Xpath);
				if (flag) {
					commonFunc.checkSuccessToastMessageDisappear();
					webDB.moveToElement(OpenSalesOrderLocators.OpenSOPostBTN, ElementType.Xpath);
					flag = webDB.clickAnElement(OpenSalesOrderLocators.OpenSOPostBTN, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to find Status of Open SO Item with Item And Customer
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param Customer
	 * @param Date
	 * @return
	 */
	public boolean findStatusOfOpenSOWithItemAndCustomer(String Item, String Customer, String Date, String Status) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(OpenSalesOrderLocators.FindOpenSODocDateInput,
				OpenSalesOrderLocators.FindOpenSODocDateInput, Date);
		commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.FindOpenSOItemLovBtn, Item);
		commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.FindOpenSOCustomerLovBtn, Customer);
		flag = webDB.selectDropDownByText(OpenSalesOrderLocators.FindOpenSOStatusSelect, Status, ElementType.Xpath);
		if (flag) {
			flag = webDB.waitForClickElement(OpenSalesOrderLocators.OpenSOFindPageSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(OpenSalesOrderLocators.OpenSOFindPageSearchBtn, ElementType.Xpath);
				webDB.clickAnElement(OpenSalesOrderLocators.OpenSOFindPageSearchBtn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.FindNoDataMessageContainer,
						ElementType.Xpath)) {
					logs.logging("INFO", "No Data was in Approved Status");
					flag = false;
				} else {
					flag = webDB.waitForElement(OpenSalesOrderLocators.FindStatusTableWithText(Item),
							ElementType.Xpath);
					if (flag) {
						String StatusText = webDB.getTextFromElement(
								OpenSalesOrderLocators.FindStatusTableWithText(Item), ElementType.Xpath);
						flag = Status.equalsIgnoreCase(StatusText);
					}
				}
				if (flag) {
					List<WebElement> elements = webDB.getDriver()
							.findElements(By.xpath(OpenSalesOrderLocators.FindApprovedDateTableList));
					for (WebElement element : elements) {
						commonFunc.checkRefreshCellsDisappear();
						if (element.getText().contains(Date)) {
							flag = true;
							String cost = webDB.getTextFromElement(
									OpenSalesOrderLocators.FindCostOfLastDateApprovedOpenSO(Item), ElementType.Xpath);
							VariableHandler.setOpenSOPriceAmount(cost);
							logs.logging("INFO", "Already Item was Approved Status.. Displayed Amount: " + cost);
							break;
						} else {
							flag = false;
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * This Method used to click Cancel Button in Open SO
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCancelButtonInOpenSO() {
		flag = webDB.waitForClickElement(OpenSalesOrderLocators.OpenSO_CancelHeaderBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.OpenSO_CancelHeaderBtn, ElementType.Xpath);
			webDB.clickAnElement(OpenSalesOrderLocators.OpenSO_CancelHeaderBtn, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOFindPageSearchBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				logs.logging("INFO", "Cancel button clicked Successfully in Open SO");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify Open Sale Order Status With OpenSO Document Number
	 * 
	 * @author Sakthi-GWL
	 * @param OpenSODocNo
	 * @param Status
	 */
	public void validateStatusOfOpenSOWithDocNo(String OpenSODocNo, String Status) {
		commonFunc.hardWait(2000);
		if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.OpenSOBackIcon, ElementType.Xpath)) {
			webDB.moveToElement(OpenSalesOrderLocators.OpenSOBackIcon, ElementType.Xpath);
			webDB.clickAnElementWithoutException(OpenSalesOrderLocators.OpenSOBackIcon, ElementType.Xpath);
		}
		flag = webDB.waitForElement(OpenSalesOrderLocators.FindOpenSODocNoLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.FindOpenSODocNoLovBtn, OpenSODocNo);
			logs.logging("Info", "Open So Doc Number Passed Sucessfully ");
			flag = webDB.waitForClickElement(OpenSalesOrderLocators.OpenSOFindPageSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(OpenSalesOrderLocators.OpenSOFindPageSearchBtn, ElementType.Xpath);
				webDB.clickAnElement(OpenSalesOrderLocators.OpenSOFindPageSearchBtn, ElementType.Xpath);
				flag = webDB.waitForClickElement(OpenSalesOrderLocators.FindStatusTableWithDocNo(OpenSODocNo),
						ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Open So Cancel Doc Clicked Successfully");
					String DisplayStatus = webDB.getTextFromElement(
							OpenSalesOrderLocators.FindStatusTableWithDocNo(OpenSODocNo), ElementType.Xpath);
					logs.logging("INFO", "Displaying Status: " + DisplayStatus);
					logs.logging("INFO", "Expected Status: " + Status);
					flag = DisplayStatus.equalsIgnoreCase(Status);
					if (flag) {
						logs.logging("Info", "Verified Open SO Status");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click header save Icon in Open Sale Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickHeaderSaveIconInOpenSO() {
		flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOHeaderSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.OpenSOHeaderSaveBtn, ElementType.Xpath);
			webDB.clickAnElement(OpenSalesOrderLocators.OpenSOHeaderSaveBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header Save Icon Clicked");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Sakthi-GWL
	 */
	public void verifyOpenSO() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String CustomerID = commonFunc.getDataFromTestDataSheet("ERPPrivateLimitedID");
		String Item = commonFunc.getDataFromTestDataSheet("OpenSOItem");
		String Unit = RandomStringUtils.randomNumeric(2);
		if (Unit.equals("00")) {
			Unit = "10";
		}
		String Cost = RandomStringUtils.randomNumeric(2);
		if (Cost.equals("00")) {
			Cost = "10";
		}
		String MRP = String.valueOf((Integer.parseInt(Cost) + 10));
		String OpenSOPrefix = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String OpenSOType = commonFunc.getDataFromTestDataSheet("Type_Standard");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenSO();
		selectOpenSOTypeWithRadioButtons("Open SO");
		clickAddNewOpenSOPlsIcon();
		fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CustomerID, OpenSOPrefix);
		fillSOTypeAndPONumberInOpenSO(OpenSOType, RandomStringUtils.randomNumeric(4));
		clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
		clickFetchCustomerAndSelectItemInOpenSO(Item);
		fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost, MRP, OpenSOHSN);
		fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
		enterEffectiveFromAndToDate(Today, Today, Today);
		clickHeaderSaveAndClickPostBtnInOpenSO();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenSONumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenSONumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenSO();
		validateStatusOfOpenSOWithDocNo(VariableHandler.getOpenSONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
	}

	/**
	 * @author Sakthi-GWL
	 */
	public void verifyCancelStatement() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String CustomerID = commonFunc.getDataFromTestDataSheet("ERPPrivateLimitedID");
		String Item = commonFunc.getDataFromTestDataSheet("OpenSOItem");
		String Unit = RandomStringUtils.randomNumeric(2);
		if (Unit.equals("00")) {
			Unit = "10";
		}
		String Cost = RandomStringUtils.randomNumeric(2);
		if (Cost.equals("00")) {
			Cost = "10";
		}
		String MRP = String.valueOf((Integer.parseInt(Cost) + 10));
		String OpenSOPrefix = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String OpenSOType = commonFunc.getDataFromTestDataSheet("Type_Standard");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenSO();
		selectOpenSOTypeWithRadioButtons("Open SO");
		clickAddNewOpenSOPlsIcon();
		fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CustomerID, OpenSOPrefix);
		fillSOTypeAndPONumberInOpenSO(OpenSOType, RandomStringUtils.randomNumeric(4));
		clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
		clickFetchCustomerAndSelectItemInOpenSO(Item);
		fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost, MRP, OpenSOHSN);
		fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
		enterEffectiveFromAndToDate(Today, Today, Today);
		clickHeaderSaveIconInOpenSO();
		clickCancelButtonInOpenSO();
		validateStatusOfOpenSOWithDocNo(VariableHandler.getOpenSONumber(),
				commonFunc.getDataFromTestDataSheet("CancelStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}
	
	/**
	 * This Method used to click Tick Icon and Fill PO Date in Open SO and Store
	 * Open SO Number
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickTickIconAndGetOpenSODocNo(ArrayList<String> DocNum) {
		flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSO_FormTickIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.OpenSO_FormTickIcon, ElementType.Xpath);
			webDB.clickAnElement(OpenSalesOrderLocators.OpenSO_FormTickIcon, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSODisplayDocNumber, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Open SO Tick Icon Clicked");
				String DocNo = webDB.getTextFromElement(OpenSalesOrderLocators.OpenSODisplayDocNumber,
						ElementType.Xpath);
				VariableHandler.setOpenSONumber(DocNo);
				logs.logging("INFO", "Displayed SO Document Number: " + DocNo);
				DocNum.add(VariableHandler.getOpenSONumber());
			}
		}
		assertTrue(flag);
	}
}
