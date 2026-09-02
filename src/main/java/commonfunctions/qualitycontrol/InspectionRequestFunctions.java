package commonfunctions.qualitycontrol;

import static org.testng.Assert.assertTrue;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks November 20, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.qualitycontrol.InspectionRequestLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class InspectionRequestFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

	/**
	 * This method used to click add icon in IR
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconInInspectionRequest() {
		flag = webDB.waitForElement(InspectionRequestLocators.InspectionRequestAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(InspectionRequestLocators.InspectionRequestAddIcon, ElementType.Xpath);
			webDB.clickAnElement(InspectionRequestLocators.InspectionRequestAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionRequestLocators.InspectionRequestIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Icon Clicked in Inspection Request");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to switch frame in IR
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToIRCreationIframe() {
		flag = webDB.waitForElement(InspectionRequestLocators.InspectionRequestIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(InspectionRequestLocators.InspectionRequestIframe, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionRequestLocators.IRCreateLocationId, ElementType.Xpath);
			if (!flag) {
				logs.logging("INFO", "Failed to switch Frame in Creation");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill details in IR creation
	 * 
	 * @author Sakthi-GWL
	 * @param date
	 * @param type
	 * @param prefix
	 */
	public void fillDateVoucherTypeAndPrefixInCreationFrame(String date, String type, String prefix) {
		switchToIRCreationIframe();
		commonFunc.verifyAddTableSpecs(InspectionRequestLocators.IRCreateDocDateInput,
				InspectionRequestLocators.IRCreateDocDateInput, date);
		logs.logging("INFO", "Doc Date entered in Ins Req: " + date);
		flag = webDB.selectDropDownByText(InspectionRequestLocators.IRCreateSubVoucherType, type, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Voucher Type Selected in Ins Req: " + type);
			flag = webDB.waitForElement(InspectionRequestLocators.IRCreatePrefix, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(InspectionRequestLocators.IRCreatePrefix, prefix);
				logs.logging("INFO", "Prefix entered in Ins Req: " + prefix);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill details in IR creation
	 * 
	 * @author Sakthi-GWL
	 * @param wh
	 * @param Inspector
	 */
	public void fillWarehouseAndInspectorInCreationFrame(String WH, String Inspector) {
		switchToIRCreationIframe();
		commonFunc.searchAndSelectValueInFilters(InspectionRequestLocators.IRCreateWarehouseId, WH);
		logs.logging("INFO", "Warehouse entered in Ins Req: " + WH);
		switchToIRCreationIframe();
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(InspectionRequestLocators.IRCreateIncharge, Inspector);
			logs.logging("INFO", "Incharge entered in Ins Req: " + Inspector);
			switchToIRCreationIframe();
			flag = webDB.waitForElement(InspectionRequestLocators.IRCreateTickIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionRequestLocators.IRCreateTickIcon, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Inspection Document Creation Tick Icon Clicked");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					commonFunc.checkSuccessToastMessageDisappear();
					flag = webDB.waitForElement(InspectionRequestLocators.IRDisplayDocNumber, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Inspection Request Created Successfully");
						String DisplayDocNo = webDB.getTextFromElement(InspectionRequestLocators.IRDisplayDocNumber,
								ElementType.Xpath);
						VariableHandler.setInspectionReqNumber(DisplayDocNo);
						logs.logging("INFO", "Dislayed Doc no: " + DisplayDocNo);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Load Button in IR
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickLoadButtonInInspectionRequest() {
		flag = webDB.waitForElement(InspectionRequestLocators.IRLoadButton, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(InspectionRequestLocators.IRLoadButton, ElementType.Xpath);
			webDB.clickAnElement(InspectionRequestLocators.IRLoadButton, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionRequestLocators.IRLoadPendingIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Load Button Clicked in Inspection Request");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to switch Load Pending frame in IR
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToLoadPendingIframe() {
		flag = webDB.waitForElement(InspectionRequestLocators.IRLoadPendingIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(InspectionRequestLocators.IRLoadPendingIframe, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionRequestLocators.IRLoadPendingOkButton, ElementType.Xpath);
			if (!flag) {
				logs.logging("INFO", "Failed to switch Load Pending Iframe");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to load pending items in Inspection Request
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param Quantity
	 */
	public void searchItemNameInPendingLoadAndCreate(String Item, String Quantity) {
		switchToLoadPendingIframe();
		flag = webDB.waitForElement(InspectionRequestLocators.IRLoadPendingSearchBar, ElementType.Xpath);
		if (flag) {
			webDB.waitUntilElementToNotBeStale(InspectionRequestLocators.IRLoadPendingSearchBar, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(InspectionRequestLocators.IRLoadPendingSearchBar, Item, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(InspectionRequestLocators.IRLoadPendingSearchGo, ElementType.Xpath);
				webDB.clickAnElement(InspectionRequestLocators.IRLoadPendingSearchGo, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(InspectionRequestLocators.IRPendingDocQuantityInput(Item),
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Item Searched and Displayed in Load Frame");
					webDB.clearTextField(InspectionRequestLocators.IRPendingDocQuantityInput(Item), ElementType.Xpath);
					flag = webDB.sendTextToAnElement(InspectionRequestLocators.IRPendingDocQuantityInput(Item),
							Quantity, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Quantity Entered: " + Quantity);
						webDB.moveToElement(InspectionRequestLocators.IRPendingDocCheckBox(Item), ElementType.Xpath);
						webDB.clickAnElement(InspectionRequestLocators.IRPendingDocCheckBox(Item), ElementType.Xpath);
						flag = webDB.waitForElement(InspectionRequestLocators.IRLoadPendingOkButton, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(InspectionRequestLocators.IRLoadPendingOkButton, ElementType.Xpath);
							webDB.clickAnElement(InspectionRequestLocators.IRLoadPendingOkButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
							flag = webDB.waitForElement(InspectionRequestLocators.IRPostButton, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Check Box Clicked and Item Loaded");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click save and post button in Inspection Request
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveHeaderAndPostDocument() {
		flag = webDB.waitForElement(InspectionRequestLocators.IRHeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(InspectionRequestLocators.IRHeaderSaveIcon, ElementType.Xpath);
			flag = webDB.clickAnElement(InspectionRequestLocators.IRHeaderSaveIcon, ElementType.Xpath);
//			webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
				flag = webDB.waitForElement(InspectionRequestLocators.IRPostButton, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(InspectionRequestLocators.IRPostButton, ElementType.Xpath);
					webDB.clickAnElement(InspectionRequestLocators.IRPostButton, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (flag) {
						logs.logging("INFO", "Post Button Clicked");
					}
				}
			}
		}
		assertTrue(flag);
	}

}
