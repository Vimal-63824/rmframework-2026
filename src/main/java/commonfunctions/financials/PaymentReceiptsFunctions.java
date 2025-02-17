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
import commonlocators.financials.PaymentReceiptsLocators;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PaymentReceiptsFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();

	/**
	 * This Method Used To Verify Pending Cr Docs
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingCrDocs(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingCrDocs, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Cr Docs Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingCrDocs, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(PaymentReceiptsLocators.PendingPayable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Payable Button Is Displayed");
				webDB.clickAnElement(PaymentReceiptsLocators.PendingPayable, ElementType.Xpath);
				flag = webDB.waitForElement(PaymentReceiptsLocators.PendingPayableFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending Payable Frame Is Displayed");
					webDB.switchToFrame(PaymentReceiptsLocators.PendingPayableFrame, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PaymentReceiptsLocators.PendDocumentsNoLovBtn,
							VariableHandler.getPbNumber());
					flag = webDB.waitForElement(PaymentReceiptsLocators.PendingPayableFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PaymentReceiptsLocators.PendingPayableFrame, ElementType.Xpath);
						flag = webDB.waitForElement(PaymentReceiptsLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Pending Payable Frame");
							webDB.clickAnElement(PaymentReceiptsLocators.SearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(PaymentReceiptsLocators.CheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox Is Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PaymentReceiptsLocators.CheckBox(DocNo), ElementType.Xpath);
								verifyEnableCreateButton();
								flag = webDB.waitForElement(PaymentReceiptsLocators.CreateButton, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO",
											"CheckBox Is Clicked And Create Documents Button Is Displayed");
									webDB.clickAnElement(PaymentReceiptsLocators.CreateButton, ElementType.Xpath);
									commonFunc.checkRefreshCellsDisappear();
									commonFunc.hardWait(8000);
									webDB.selectDropDownByText(PaymentReceiptsLocators.PayMode,
											commonFunc.getDataFromTestDataSheet("Rtgs"), ElementType.Xpath);
									commonFunc.searchAndSelectValueInFilters(
											PaymentReceiptsLocators.Bank_Cash_LovButton,
											commonFunc.getDataFromTestDataSheet("Axisbank"));
									flag = webDB.waitForElement(PaymentReceiptsLocators.PendingPayableFrame,
											ElementType.Xpath);
									if (flag) {
										webDB.switchToFrame(PaymentReceiptsLocators.PendingPayableFrame,
												ElementType.Xpath);
										flag = webDB.waitForElement(PaymentReceiptsLocators.OkButton,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Ok Button Is Displayed");
											webDB.clickAnElement(PaymentReceiptsLocators.OkButton, ElementType.Xpath);
											webDB.switchToDefaultContent();
											flag = webDB.waitForElement(PaymentReceiptsLocators.BankAC,
													ElementType.Xpath);
											VerifyStoreVoucherNumber();
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
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Enable Create Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyEnableCreateButton() {
		for (int i = 0; i < 60; i++) {
			commonFunc.hardWait(1000);
			if (webDB.isElementEnabled(PaymentReceiptsLocators.CreateButton, ElementType.Xpath)) {
				flag = true;
				break;
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Create Payment Receipt
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreatePaymentReceipt() {
		webDB.moveToElement(PaymentReceiptsLocators.DrCr, ElementType.Xpath);
		webDB.moveToElement(PaymentReceiptsLocators.OtherDetails, ElementType.Xpath);
		flag = webDB.waitForElement(PaymentReceiptsLocators.OtherDetails, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Other Details Button Is Displayed");
			webDB.clickAnElement(PaymentReceiptsLocators.OtherDetails, ElementType.Xpath);
			flag = webDB.waitForElement(PaymentReceiptsLocators.AccountFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Account Frmae Is Displayed");
				webDB.switchToFrame(PaymentReceiptsLocators.AccountFrame, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(PaymentReceiptsLocators.AccountNoLovButton,
						commonFunc.getDataFromTestDataSheet("BankofBaroda"));
				flag = webDB.waitForElement(PaymentReceiptsLocators.AccountFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Account Frame Is Displayed");
					webDB.switchToFrame(PaymentReceiptsLocators.AccountFrame, ElementType.Xpath);
					flag = webDB.waitForElement(PaymentReceiptsLocators.AccountTickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Account Tick Icon Is Displayed");
						webDB.clickAnElement(PaymentReceiptsLocators.AccountTickIcon, ElementType.Xpath);
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(PaymentReceiptsLocators.CloseIcon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Close Icon Is Displayed");
							webDB.clickAnElement(PaymentReceiptsLocators.CloseIcon, ElementType.Xpath);
							flag = webDB.waitForElement(PaymentReceiptsLocators.OtherDetails, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Close Icon Is Clicked");
								commonFunc.hardWait(2000);
								webDB.sendTextToAnElement(PaymentReceiptsLocators.ReferenceInputNumber,
										VariableHandler.getGrnDocNumber(), ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store Voucher Number
	 * 
	 * @author Deepan-GWL
	 */
	public void VerifyStoreVoucherNumber() {
		String BpvNumber = webDB.getTextFromElement(PaymentReceiptsLocators.BpvNumber, ElementType.Xpath);
		VariableHandler.setBpvNumber(BpvNumber);
	}

	/**
	 * This Method Is Used To Click Tick And Post Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void clickTickAndPostIcon() {
		flag = webDB.waitForElement(PaymentReceiptsLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(PaymentReceiptsLocators.SaveButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(PaymentReceiptsLocators.PostButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(PaymentReceiptsLocators.PostButton, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * waiting for approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyWaitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath)) {
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			flag = webDB.waitForElement(PaymentReceiptsLocators.WorkApprovalOkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PaymentReceiptsLocators.WorkApprovalOkButton, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This MEthod Used To Verify Payment Receipt Approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPaymentReceiptApproval() {
		commonFunc.validCommonLogin(System.getProperty("GanesUsername"), System.getProperty("GaneshPassword"),
				"Sathi Ganesh");
		commonFunc.navigateToFinancialPaymentReceipt();
		verifyPendingCrDocs(VariableHandler.getPbNumber());
		verifyCreatePaymentReceipt();
		clickTickAndPostIcon();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		approvalPageFunc.navigateToApprovals();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getBPvNumber());
		approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getBPvNumber());
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("GanesUsername"), System.getProperty("GaneshPassword"),
					"Sathi Ganesh");
			commonFunc.navigateToFinancialPaymentReceipt();
			verifyPaymentReceiptApprovalStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Payment Receipt Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPaymentReceiptApprovalStatus() {
		commonFunc.searchAndSelectValueInFilters(PaymentReceiptsLocators.VoucherNumberLovButton,
				VariableHandler.getBPvNumber());
		flag = webDB.waitForElement(PaymentReceiptsLocators.ApproveSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PaymentReceiptsLocators.ApproveSearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PaymentReceiptsLocators.IssuedStatus(VariableHandler.getBPvNumber()),
					ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(
						PaymentReceiptsLocators.IssuedStatus(VariableHandler.getBPvNumber()), ElementType.Xpath);
				flag = Status.contains(commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				if (flag) {
					logs.logging("INFO", "Expected Status: " + commonFunc.getDataFromTestDataSheet("IssuedStatus"));
					logs.logging("INFO", "Verified Status ,Displayed Status: " + Status);
				}
			}
		}
		assertTrue(flag);
	}
}
