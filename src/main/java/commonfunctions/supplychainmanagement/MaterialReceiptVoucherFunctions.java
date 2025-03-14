package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.qualitycontrol.InspectionDocumentsLocators;
import commonlocators.supplychainmanagement.CompletionProductionOrderLocators;
import commonlocators.supplychainmanagement.MaterialReceiptVoucherLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class MaterialReceiptVoucherFunctions {
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
	public void verifyPendingMRV(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Receipt Voucher Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingInsurance, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Insurance IS Displayed");
				webDB.clickAnElement(MaterialReceiptVoucherLocators.PendingInsurance, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Mrv Frame Is Displayed");
					webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(InspectionDocumentsLocators.PendingSearchField, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Inspection Pending Icon IS Clicked");
						webDB.sendTextToAnElement(InspectionDocumentsLocators.PendingSearchField,
								VariableHandler.getGrnDocNumber(), ElementType.Xpath);
						flag = webDB.waitForElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Go Button Is Displayed");
							webDB.clickAnElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVCheckBox(DocNo),
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Mrv CheckBox Is Displayed");
								webDB.clickAnElement(MaterialReceiptVoucherLocators.MRVCheckBox(DocNo),
										ElementType.Xpath);
								flag = webDB.waitForElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(2000);
									logs.logging("INFO", "Create Qc Doc Is Displayed");
									webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(MaterialReceiptVoucherLocators.LocationId,
											ElementType.Xpath);
									storeMRVDocumentsNumber();
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
	 * This Method Used To Verify Store MRV Documents Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storeMRVDocumentsNumber() {
		String Number = webDB.getTextFromElement(MaterialReceiptVoucherLocators.MRVNO, ElementType.Xpath);
		VariableHandler.setMrvNumber(Number);
	}

	/**
	 * This Method Used To Verify Receipt Locators And Put Away
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyReceiptLocatorsAndPutAway() {
		commonFunc.checkSuccessToastMessageDisappear();
		if (webDB.verifyIsElementDisplayed(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath)) {
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Receptor Locators Is Displayed");
				webDB.moveToElement(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath);
				webDB.clickAnElement(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.TransferFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Transfer Frame Is Displayed");
					webDB.switchToFrame(MaterialReceiptVoucherLocators.TransferFrame, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PutAway, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(MaterialReceiptVoucherLocators.PutAway, ElementType.Xpath);
						webDB.clickAnElement(MaterialReceiptVoucherLocators.PutAway, ElementType.Xpath);
						if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CloseIcon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Put Amay Is Clicked");
							webDB.clickAnElement(MaterialReceiptVoucherLocators.CloseIcon, ElementType.Xpath);
							flag = webDB.waitForClickElement(MaterialReceiptVoucherLocators.LocationId,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Location Id Is Displayed");
							}
						}
					}
				}
			}
		} else {
			flag = !webDB.verifyIsElementDisplayed(MaterialReceiptVoucherLocators.RecptorLocators, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Receipt Locators Button Not Display for the Process...");
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
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.moveToElement(MaterialReceiptVoucherLocators.HeaderTickIcon, ElementType.Xpath);
			webDB.clickAnElement(MaterialReceiptVoucherLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(MaterialReceiptVoucherLocators.HeaderPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(MaterialReceiptVoucherLocators.HeaderPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Validate Inspection
	 * 
	 * @author Deepan-GWL
	 * @param MrvNo
	 * @param ExpectedStatus
	 */
	public void verifyValidateMaterialReceiptDocumentsStatus(String MrvNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialReceiptVoucherLocators.VoucherNumberLovButton, MrvNo);
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialReceiptVoucherLocators.SearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MaterialReceiptStatusTableText(MrvNo),
					ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(
						MaterialReceiptVoucherLocators.MaterialReceiptStatusTableText(MrvNo), ElementType.Xpath);
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
	 * This Method Used To Verify Post Material Receipt
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPostMaterialReceipt() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigateToMaterialReceiptVoucher();
		verifyPendingMRV(VariableHandler.getGrnDocNumber());
		verifyReceiptLocatorsAndPutAway();
		clickTickAndPostIcon();
		verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
				commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method used to click Pending Issuance Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingInssuanceBtn() {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingInsurance, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialReceiptVoucherLocators.PendingInsurance, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select voucher No and click create receipt button
	 * 
	 * @author Sakthi-GWL
	 * @param VoucherNo
	 */
	public void selectVoucherNoAndClickCreateReceipt(String VoucherNo) {
		switchMRVPendingIframe();
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVCheckBox(VoucherNo), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Search Go Button Clicked and Filtered with " + VoucherNo);
			webDB.clickAnElement(MaterialReceiptVoucherLocators.MRVCheckBox(VoucherNo), ElementType.Xpath);
			webDB.waitForClickElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
			flag = webDB.isElementEnabled(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(MaterialReceiptVoucherLocators.LocationId, ElementType.Xpath);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				if (flag) {
					storeMRVDocumentsNumber();
					logs.logging("INFO", "Create Qc Doc is clicked");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch pending MRV frame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchMRVPendingIframe() {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(MaterialReceiptVoucherLocators.MrvFrame, ElementType.Xpath);
			flag = webDB.waitForClickElement(InspectionDocumentsLocators.PendingSearchField, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to search with voucher no in Pending MRV
	 * 
	 * @author Sakthi-GWL
	 * @param VoucherNo
	 */
	public void searchWithVoucherNumber(String VoucherNo) {
		switchMRVPendingIframe();
		logs.logging("INFO", "Material Receipt Voucher Frame Switched");
		webDB.sendTextToAnElement(InspectionDocumentsLocators.PendingSearchField, VoucherNo, ElementType.Xpath);
		flag = webDB.waitForClickElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionDocumentsLocators.GoButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialReceiptVoucherLocators.MRVCheckBox(VoucherNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Go Button Clicked and Filtered with " + VoucherNo);
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method used to validate Process Recipient Warehouse with Completion
	 * Production Order No
	 * 
	 * @author Sakthi-GWL
	 * @param CompProOdrNo
	 * @param Process
	 * @param RecipientWH
	 */
	public void validateProcessRecipientWarehouseWithCompletionProdNo(String CompProOdrNo, String Process,
			String RecipientWH) {
		flag = webDB.waitForElement(MaterialReceiptVoucherLocators.PendingCreateMRV_TableProcess(CompProOdrNo),
				ElementType.Xpath);
		if (flag) {
			String DisplayProcess = webDB.getTextFromElement(
					MaterialReceiptVoucherLocators.PendingCreateMRV_TableProcess(CompProOdrNo), ElementType.Xpath);
			flag = DisplayProcess.equalsIgnoreCase(Process);
			logs.logging("INFO", "Expected Process: " + Process);
			logs.logging("INFO", "Displayed Status: " + DisplayProcess);
			if (flag) {
				logs.logging("INFO", "Verified Process Displayed in Create MRV Screen");
				flag = webDB.waitForElement(
						MaterialReceiptVoucherLocators.PendingCreateMRV_TableRecipientWH(CompProOdrNo),
						ElementType.Xpath);
				if (flag) {
					String DisplayWarehouse = webDB.getTextFromElement(
							MaterialReceiptVoucherLocators.PendingCreateMRV_TableRecipientWH(CompProOdrNo),
							ElementType.Xpath);
					flag = DisplayWarehouse.equalsIgnoreCase(RecipientWH);
					logs.logging("INFO", "Expected Consumption Warehouse: " + RecipientWH);
					logs.logging("INFO", "Displayed Consumption Warehouse: " + DisplayWarehouse);
					if (flag) {
						logs.logging("INFO", "Verified Recipient Warehouse Displayed in Create MRV Screen");
					}
				}
			}
		}
		assertTrue(flag);
	}
}
