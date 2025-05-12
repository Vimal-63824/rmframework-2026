package commonfunctions.qualitycontrol;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.qualitycontrol.InspectionDocumentsLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class InspectionDocumentFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

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
		flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("AshokValue"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getIDNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getIDNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			commonFunc.navigateInspectionDocumentsPage();
		}
		verifyValidateInspectionDocumentsStatus(VariableHandler.getIDNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Create Inspection Document With inspection Plan From
	 * GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void InspectionDocumentWithInspectionPlan() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateInspectionDocumentsPage();
		pendingInspectionRequestDocumentToCreateInspectionDocument();
		getInspectionDocumentNumberFromToastMessage();
		inspectionPlanNumberCheckingInInspectionDocument();
		if (VariableHandler.getInspectionPlanNumber().equals("")) {
		} else {
			enterObservationValue();
		}
		clickTickAndPostIconForGrnDocument();
		waitingForApproval();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateInspectionDocumentsPage();
		try {
			String alertText = VariableHandler.getIDAlertMessage();
			logs.logging("INFO", "Alert text: " + alertText);
			String pattern = "Pend\\. Inspection Request Moved\\s*-\\s*ID\\d{3}/\\d{5}/\\d{2}-\\d{2}\\s*to\\s*ID\\d{3}/\\d{5}/\\d{2}-\\d{2}";
			if (alertText.matches(".*" + pattern + ".*")) {
				documentSearchAndSelectLovForFindPage();
				commonFunc.logoutUser();
				logs.logging("INFO", "Specific alert found, proceeding with the process.");
			} else {
				commonFunc.logoutUser();
				logs.logging("INFO", "Other alert detected, no action taken.");
			}
		} catch (Exception e) {
			logs.logging("INFO", "No alert appeared or other error: " + e.getMessage());
		}
	}

	/**
	 * This Methods Is Used To Create Inspection Document From Pending Inspection
	 * Request
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void pendingInspectionRequestDocumentToCreateInspectionDocument() {
		flag = webDB.waitForElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Inspection Documents Is Displayed");
				webDB.switchToFrame(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
				flag = webDB.waitForElement(InspectionDocumentsLocators.VoucherLov, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.VoucherLov,
						VariableHandler.getGrnDocNumber());
				logs.logging("INFO", "Search Field Displayed");
				webDB.switchToFrame(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
					logs.logging("INFO", "Document Search Successfully");
					flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionSelectAllBtn,
							ElementType.Xpath);
					commonFunc.hardWait(3000);
					if (flag) {
						flag = webDB.clickAnElement(InspectionDocumentsLocators.PendingInspectionSelectAllBtn,
								ElementType.Xpath);
						logs.logging("INFO", "Create QC Button Displayed");
						flag = webDB.waitForElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(3000);
							webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Ok Button Is Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(InspectionDocumentsLocators.OKButton, ElementType.Xpath);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(InspectionDocumentsLocators.LocationId, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Inspection Plan Number In Inspection Document
	 * Screen
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void inspectionPlanNumberCheckingInInspectionDocument() {
		flag = webDB.waitForElement(InspectionDocumentsLocators.InspectiondocumentShowMore, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionDocumentsLocators.InspectiondocumentShowMore, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionPlanNumber, ElementType.Xpath);
			if (flag) {
				String InspectionPlanNumber = webDB.getDomPropertyFromElement(
						InspectionDocumentsLocators.InspectionPlanNumber, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				VariableHandler.setInspectionPlanNumber(InspectionPlanNumber);
				logs.logging("INFO", "Doc Number Is:" + InspectionPlanNumber);
				flag = webDB.waitForElement(InspectionDocumentsLocators.InspectiondocumentShowLess, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(InspectionDocumentsLocators.InspectiondocumentShowLess,
							ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Enter Observation Value After Verify the Inspection
	 * Plan Number Field
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void enterObservationValue() {
		flag = webDB.waitForElement(InspectionDocumentsLocators.observationTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionDocumentsLocators.observationTab, ElementType.Xpath);
			logs.logging("INFO", "Observation Tab Is Clicked");
			List<WebElement> LineNumber = webDB.getDriver()
					.findElements(By.xpath(InspectionDocumentsLocators.ObsLineNumber));
			for (int i = 1; i <= LineNumber.size(); i++) {
				flag = webDB.waitForElement(InspectionDocumentsLocators.Observationvalue(i), ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(3000);
					webDB.doubleClickAnElement(InspectionDocumentsLocators.Observationvalue(i), ElementType.Xpath);
					commonFunc.hardWait(3000);
					webDB.sendTextToAnElement(InspectionDocumentsLocators.ObservationValueInput, "10",
							ElementType.Xpath);
					logs.logging("INFO", "Value Tab Is Clicked");
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(InspectionDocumentsLocators.ObservationSaveBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(InspectionDocumentsLocators.ObservationSaveBtn, ElementType.Xpath);
						logs.logging("INFO", "Document Saved Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Get Inspection Document Number from Toast Message
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void getInspectionDocumentNumberFromToastMessage() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg, ElementType.Xpath);
		if (flag) {
			String IDAlertMessage = webDB.getTextFromElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg,
					ElementType.Xpath);
			VariableHandler.setIDAlertMessage(IDAlertMessage);
			;
			logs.logging("INFO", "Get Text From Note Alert Is(ID Alert Message:" + IDAlertMessage);
			flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg, ElementType.Xpath);
			String InspectionDocumentNumber = webDB
					.getTextFromElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg, ElementType.Xpath);
			String regex = "ID100/\\d{5}/\\d{2}-\\d{2}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(InspectionDocumentNumber);
			ArrayList<String> documentNumbers = new ArrayList<>();
			while (matcher.find()) {
				documentNumbers.add(matcher.group());
			}
			if (documentNumbers.size() == 2) {
				String fromDocument = documentNumbers.get(0);
				String toDocument = documentNumbers.get(1);
				logs.logging("INFO", "From Document: " + fromDocument);
				logs.logging("INFO", "To Document: " + toDocument);
				String fromNum = fromDocument.split("/")[1];
				String toNum = toDocument.split("/")[1];
				int startNum = Integer.parseInt(fromNum);
				int endNum = Integer.parseInt(toNum);
				ArrayList<String> allDocumentNumbers = new ArrayList<>();
				for (int i = startNum; i <= endNum; i++) {
					String generatedDocumentNumber = "ID100/" + String.format("%05d", i) + "/25-26";
					allDocumentNumbers.add(generatedDocumentNumber);
				}
				VariableHandler.setAllDocumentNumber(allDocumentNumbers);
				logs.logging("INFO", "Generated Document Numbers:");
				for (String documentNumber : allDocumentNumbers) {
					System.out.println(documentNumber);
					allDocumentNumbers.size();
				}
			} else {
				logs.logging("INFO", "Invalid format, unable to extract From and To document numbers.");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Document Number To Search And Select The Inspection
	 * Document To proceed Continues Process
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void documentSearchAndSelectLovForFindPage() {
		ArrayList<String> allDocumentNumbers = VariableHandler.getAllDocumentNumber();
		for (int i = 1; i < allDocumentNumbers.size(); i++) {
			String documentNumber = allDocumentNumbers.get(i);
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionDocumentNumberLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.InspectionDocumentNumberLov,
						documentNumber);
				flag = webDB.waitForElement(InspectionDocumentsLocators.FindPageSearchBtn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				if (flag) {
					webDB.clickAnElement(InspectionDocumentsLocators.FindPageSearchBtn, ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionDocumentNumberDrillDown,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(InspectionDocumentsLocators.InspectionDocumentNumberDrillDown,
								ElementType.Xpath);
						commonFunc.hardWait(2000);
						inspectionPlanNumberCheckingInInspectionDocument();
						commonFunc.hardWait(2000);
						if (VariableHandler.getInspectionPlanNumber().equals("")) {
						} else {
							enterObservationValue();
						}
						clickTickAndPostIconForGrnDocument();
						waitingForApproval();
						commonFunc.hardWait(2000);
						commonFunc.validCommonLogin(System.getProperty("TvsUsername"),
								System.getProperty("TvsPassword"), "TvsUsername");
						commonFunc.navigateInspectionDocumentsPage();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Document Number To
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(GrnLocators.DocAprovalIfrmae, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval,
					commonFunc.getDataFromTestDataSheet("AshokValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.OkButtonFwdForQcDocument, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.OkButtonFwdForQcDocument, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(3000);
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AshokUsername"),
							System.getProperty("AshokPassword"));
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getIDNumber());
					approvalFunc.verifyApproveWithDocNumber(VariableHandler.getIDNumber());
					commonFunc.hardWait(3000);
					logs.logging("INFO", "PASSED");
					flag = commonFunc.logoutUser();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To click Tick And Post Icon For GRN Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void clickTickAndPostIconForGrnDocument() {
		commonFunc.hardWait(2000);
		storeInspectionDocumentsNumber();
		flag = webDB.waitForElement(InspectionDocumentsLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(InspectionDocumentsLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.hardWait(2000);
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
	 * Verify Pending Inspection Documents For Sales Invoice
	 * 
	 * @author Vimal - RMITS
	 * @param DocNo
	 */
	public void verifyPendingInspectionDocumentForSalesInvoice(String DocNo) {
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
							VariableHandler.getSIdocno(), ElementType.Xpath);
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
												verifyPendingInspectionDocuments(VariableHandler.getSIdocno());
											}
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
}
