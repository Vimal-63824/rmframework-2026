package commonfunctions.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.PurchaseBillsLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseBillsFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingPurchaseBill(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.PendingGrn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocators.PendingGrn, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsLocators.Iframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.GrnNo,
							VariableHandler.getGrnDocNumber());
					flag = webDB.waitForElement(PurchaseBillsLocators.Iframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsLocators.Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsLocators.SearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocators.CheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsLocators.CheckBox(DocNo), ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(3000);
									webDB.clickAnElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocators.OkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(PurchaseBillsLocators.OkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(PurchaseBillsLocators.LocationId,
												ElementType.Xpath);
										storePurchaseVoucherNumber();
										verifyAddTDSNumber();
										if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath)) {
											webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
													ElementType.Xpath);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * verify Add TDS Number
	 * 
	 * @author Deepan
	 */
	public void verifyAddTDSNumber() {
		webDB.moveToElement(PurchaseBillsLocators.TableData, ElementType.Xpath);
		webDB.moveToElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.TDSLovButton, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.TDSLovButton,
						commonFunc.getDataFromTestDataSheet("TDSValue"));
			}
		}
	}

	/**
	 * This Method Used To Verify To Create Journal And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateJournalAndPost() {
		flag = webDB.waitForElement(PurchaseBillsLocators.CreateJournal, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocators.CreateJournal, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			flag = webDB.waitForElement(PurchaseBillsLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocators.PostButton, ElementType.Xpath);
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store Purchase Voucher Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storePurchaseVoucherNumber() {
		String Number = webDB.getTextFromElement(PurchaseBillsLocators.VoucherNumber, ElementType.Xpath);
		VariableHandler.setPbNumber(Number);
	}

	/**
	 * waiting for approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyWaitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath)) {
			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			flag = webDB.waitForElement(PurchaseBillsLocators.ApproveOkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocators.ApproveOkButton, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Purchase Bills Approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseBillsApproval() {
		commonFunc.validCommonLogin(System.getProperty("GanesUsername"), System.getProperty("GaneshPassword"),
				"Sathi Ganesh");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		verifyCreateJournalAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		approvalPageFunc.navigateToApprovals();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getPbNumber());
		approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getPbNumber());
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("GanesUsername"), System.getProperty("GaneshPassword"),
					"Sathi Ganesh");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Purchase Bill Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseBillApprovalStatus() {
		commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.VoucherNumberLovButton,
				VariableHandler.getPbNumber());
		flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillSearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocators.ApprovedStatus(VariableHandler.getPbNumber()),
					ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(
						PurchaseBillsLocators.ApprovedStatus(VariableHandler.getPbNumber()), ElementType.Xpath);
				flag = Status.contains(commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				if (flag) {
					logs.logging("INFO", "Expected Status: " + commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					logs.logging("INFO", "Verified Status ,Displayed Status: " + Status);
				}
			}
		}
		assertTrue(flag);
	}

}
