package commonfunctions.qualitycontrol;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.qualitycontrol.InspectionDocumentsLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class InspectionDocumentFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * Verify Pending Inspection Documents
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingInspectionDocuments(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Inspection Documents Is Displayed");
				webDB.switchToFrame(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
				flag = webDB.waitForElement(InspectionDocumentsLocators.PendingSearchField, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Inspection Pending Icon IS Clicked");
					webDB.sendTextToAnElement(InspectionDocumentsLocators.PendingSearchField,
							VariableHandler.getGrnDocNumber(), ElementType.Xpath);
					flag = webDB.waitForElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Go Button IS Displayed");
						webDB.clickAnElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
						flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo),
								ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(3000);
							logs.logging("INFO", "Inspection CheckBox Is Displayed");
							webDB.clickAnElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo),
									ElementType.Xpath);
							flag = webDB.waitForElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(3000);
								webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
								flag = webDB.waitForElement(InspectionDocumentsLocators.OKButton, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Ok Button Is Displayed");
									webDB.clickAnElement(InspectionDocumentsLocators.OKButton, ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(InspectionDocumentsLocators.LocationId,
											ElementType.Xpath);
									if (flag) {
										String AcceptedValue = webDB.getDomPropertyFromElement(
												InspectionDocumentsLocators.AcceptedQuantity, ElementType.Xpath,
												commonFunc.getDataFromTestDataSheet("Value"));
										if (AcceptedValue.equals(commonFunc.getDataFromTestDataSheet("ZeroValue"))) {
											logs.logging("INFO",
													"Inspection Documents Is Not Created Properly Retry Again....");
											flag = webDB.waitForElement(InspectionDocumentsLocators.BackButton,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(InspectionDocumentsLocators.BackButton,
														ElementType.Xpath);
												verifyPendingInspectionDocuments(VariableHandler.getGrnDocNumber());
											}
										} else {
										}
									}
									storeInspectionDocumentsNumber();
									if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
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
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Click Tick And Post Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void clickTickAndPostIcon() {
		flag = webDB.waitForElement(InspectionDocumentsLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(InspectionDocumentsLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(InspectionDocumentsLocators.HeaderPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(InspectionDocumentsLocators.HeaderPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store Inspection Documents Number
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	public void storeInspectionDocumentsNumber() {
		String Number = webDB.getTextFromElement(InspectionDocumentsLocators.InspactionDocumentsNumber,
				ElementType.Xpath);
		VariableHandler.setIDNumber(Number);
	}

	/**
	 * This Method Used To Verify Validate Inspection Documents Status
	 * 
	 * @author Deepan-GWL
	 * @param IdNo
	 * @param ExpectedStatus
	 */
	public void verifyValidateInspectionDocumentsStatus(String IdNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.QcDocumentsButton, IdNo);
		flag = webDB.waitForElement(InspectionDocumentsLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionDocumentsLocators.SearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionStatusTableText(IdNo), ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(InspectionDocumentsLocators.InspectionStatusTableText(IdNo),
						ElementType.Xpath);
				flag = Status.contains(ExpectedStatus);
				if (flag) {
					logs.logging("INFO", "Expected Status: " + ExpectedStatus);
					logs.logging("INFO", "Verified Status ,Displayed Status: " + Status);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To VErify Accept Inspection Documents
	 * 
	 * @author Deepan-GWL
	 */
	public void AcceptInspectionDocuments() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigateInspectionDocumentsPage();
		verifyPendingInspectionDocuments(VariableHandler.getGrnDocNumber());
		clickTickAndPostIcon();
		verifyValidateInspectionDocumentsStatus(VariableHandler.getIDNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}
}
