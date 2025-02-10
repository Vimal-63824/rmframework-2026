package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;
import org.testng.Assert;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ApprovalPageFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This method used to navigate Approval Page
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToApprovals() {
		flag = webDB.waitForElement(ApprovalPageLocators.UserNotifyIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ApprovalPageLocators.UserNotifyIcon, ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.ApprovalNotifyOption, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ApprovalPageLocators.ApprovalNotifyOption, ElementType.Xpath);
				flag = webDB.waitForElementUntilGivenTime(ApprovalPageLocators.DocumentsTypeDropDown, ElementType.Xpath,
						45);
				if (flag)
					logs.logging("INFO", "Navigated to Doc Approvals page successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Filter Documents For Approval
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 */
	public void filterDocumentsforApproval(String Number) {
		flag = webDB.waitForElement(ApprovalPageLocators.SearchBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Search Box IS Displayed");
			webDB.clickAnElement(ApprovalPageLocators.SearchBoxContainer, ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.SearchBox, ElementType.Xpath);
			if (flag) {
				webDB.clearTextField(ApprovalPageLocators.SearchBox, ElementType.Xpath);
				logs.logging("INFO", "Clear Text Search Box");
				webDB.sendTextToAnElement(ApprovalPageLocators.SearchBox, Number, ElementType.Xpath);
				logs.logging("INFO", "Entered Document Number Is: " + Number);
				flag = webDB.waitForClickElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
					flag = webDB.waitForElement(ApprovalPageLocators.SearchTableResult, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Search Go Button Is Clicked");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Verify Validate Details PO Details
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 * @param ExpCurrency
	 * @param POHType
	 * @param UOM
	 */
	public void verifyValidatedetailsPoDetails(String PONumber, String ExpCurrency, String POHType, String UOM) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(ApprovalPageLocators.PODetailsInfoBtn(PONumber), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.PODetailsInfoBtn(PONumber), ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.CURRENCY, ElementType.Id);
			if (flag) {
				logs.logging("INFO", "INFO Button Is Clicked ");
				String currency = webDB.getTextFromElement(ApprovalPageLocators.CURRENCY, ElementType.Id);
				logs.logging("INFO", "Get Text Currency Is: " + currency);
				flag = currency.equals(ExpCurrency);
				if (flag) {
					logs.logging("INFO", "Verified Expected Currency With Actual Currency");
					flag = webDB.waitForElement(ApprovalPageLocators.POTYPE, ElementType.Id);
					if (flag) {
						String Potype = webDB.getTextFromElement(ApprovalPageLocators.POTYPE, ElementType.Id);
						logs.logging("INFO", "Get Text Po Type Is: " + Potype);
						flag = Potype.equals(POHType);
						if (flag) {
							logs.logging("INFO", "Verified Expected PO Type With Actual PO Type");
							flag = webDB.waitForElement(ApprovalPageLocators.UOMTYPE, ElementType.Xpath);
							if (flag) {
								String Uomtype = webDB.getTextFromElement(ApprovalPageLocators.UOMTYPE,
										ElementType.Xpath);
								logs.logging("INFO", "Get Text UOM Type Is: " + Uomtype);
								flag = Uomtype.equals(UOM);
								if (flag) {
									logs.logging("INFO", "Verified Actual And Expected UOM");
								}
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Verify Navigate Back
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyNavigateback() {
		flag = webDB.waitForElement(ApprovalPageLocators.BackButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ApprovalPageLocators.BackButton, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(ApprovalPageLocators.DocumentsTypeDropDown, ElementType.Xpath,
					45);
			if (flag) {
				logs.logging("INFO", "Back Button Is Clicked Verified Navigate To Approval Table Page");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Verify Approve PO Number
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 */
	public void verifyApproveWithDocNumber(String Number) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
			flag = webDB.waitForClickElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CheckBox Is Clicked And Process Button IS Displayed");
				webDB.javaScriptClickAnElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
				flag = webDB.waitForElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO",
							"CheckBox select for Document Number " + Number + " and Process button clicked");
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
					flag = webDB.waitForElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Approval Process Button IS Displayed");
						webDB.clickAnElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
						flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Toast Message Displayed, Posted Successfully");
							if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used to Validate the Sales Order
	 *         Details In Document Approval Screen
	 * @param SONumber
	 * @param ExpLocation
	 * @param ExpUnit
	 * @param ExpCustId
	 */
	public void validateSalesOrderDetailsInDocApproval(String SONumber, String ExpLocation, String ExpUnit,
			String ExpCustId) {
		flag = webDB.waitForClickElement(ApprovalPageLocators.SODetailsInfoBtn(SONumber), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.SODetailsInfoBtn(SONumber), ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.HeaderLocationField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "View Details Button is Clicked");
				String DocNum = webDB.getTextFromElement(SalesOrderLocators.SoNumber, ElementType.Xpath);
				logs.logging("INFO", "GetText SalesOrder Documnet Number is :" + DocNum);
				flag = DocNum.equals(VariableHandler.getSONumber());
				if (flag) {
					logs.logging("INFO", "Verified Expected and Actual Sales Order Document Number");

					flag = webDB.waitForElement(SalesOrderLocators.HeaderLocationField, ElementType.Xpath);
					if (flag) {
						String LocId = webDB.getTextFromElement(SalesOrderLocators.HeaderLocationField,
								ElementType.Xpath);
						logs.logging("INFO", "GetText Location Id is :" + LocId);
						flag = LocId.equals(ExpLocation);
						if (flag) {
							logs.logging("INFO", "Verified Expected And Actual Location Id");
						} else {
							logs.logging("INFO", "Verification Failed, Expected :" + ExpLocation + "Actual :" + LocId);
						}
						flag = webDB.waitForElement(SalesOrderLocators.HeaderUnitField, ElementType.Xpath);
						if (flag) {
							String Unit = webDB.getTextFromElement(SalesOrderLocators.HeaderUnitField,
									ElementType.Xpath);
							logs.logging("INFO", "GetText Unit Id is :" + Unit);
							flag = Unit.equals(ExpUnit);
							if (flag) {
								logs.logging("INFO", "Verified Expected And Actual Unit Id");
							} else {
								logs.logging("INFO", "Verification Failed, Expected :" + ExpUnit + "Actual " + Unit);
							}
							flag = webDB.waitForElement(SalesOrderLocators.HeaderCustDescField, ElementType.Xpath);
							if (flag) {
								String Customer = webDB.getTextFromElement(SalesOrderLocators.HeaderCustIDField,
										ElementType.Xpath);
								logs.logging("INFO", "GetText Customer Desc is :" + Customer);
								flag = Customer.equals(ExpCustId);
								if (flag) {
									logs.logging("INFO", "Verified Expected And Actual Customer Description");
									verifyNavigateback();
								}
							}
						}
					} 
				}
			}
		}
		assertTrue(flag);
	}

}
