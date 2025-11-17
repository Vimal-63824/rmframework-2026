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
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.MaterialReceiptVoucherLocators;
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
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
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
		String alertText = VariableHandler.getIDAlertMessage();
		logs.logging("INFO", "Alert text: " + alertText);
		String pattern = "Pend\\. Inspection Request Moved\\s*-\\s*[A-Za-z0-9]+/\\d{5}/\\d{2}-\\d{2}\\s*to\\s*[A-Za-z0-9]+/\\d{5}/\\d{2}-\\d{2}";
		if (alertText.matches(".*" + pattern + ".*")) {
			documentSearchAndSelectLovForFindPage();
			commonFunc.logoutUser();
			logs.logging("INFO", "Specific alert found, proceeding with the process.");
		} else {
			commonFunc.logoutUser();
			logs.logging("INFO", "Other alert detected, no action taken.");
		}
		assertTrue(flag);
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
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyAttributeTabClicking() {
		flag = webDB.waitForElement(DirectGrnPageLocators.InspAttrTabBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.InspAttrTabBtn, ElementType.Xpath);
			logs.logging("INFO", "Attribute Tab Button Is Clicked");
			if (webDB.waitForElementUntilGivenTime(DirectGrnPageLocators.InspAttrSpecField, ElementType.Xpath, 20)) {
				verifyEnteringAttributeVAlue();
			} else if (webDB.waitForElement(DirectGrnPageLocators.AttrNoDataFound, ElementType.Xpath)) {
				flag = webDB.waitForElement(DirectGrnPageLocators.AttrNoDataFound, ElementType.Xpath);
				String NoData = webDB.getTextFromElement(DirectGrnPageLocators.AttrNoDataFound, ElementType.Xpath);
				logs.logging("INFO", "Get Text Document Is:" + NoData);
				flag = webDB.waitForElement(DirectGrnPageLocators.InspTextTab, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Text Tab Is Clicked");
				}

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyEnteringAttributeVAlue() {
		flag = webDB.waitForElement(DirectGrnPageLocators.InspAttrSpecField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.InspAttrSpecField, ElementType.Xpath);
			webDB.doubleClickAnElement(DirectGrnPageLocators.InspAttrSpecField, ElementType.Xpath);
			logs.logging("INFO", "Attribute Tab Is Clicked");

			flag = webDB.waitForElement(DirectGrnPageLocators.InspAttrSpecLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.InspAttrSpecLov, "RUBBER TYPE");
				flag = webDB.waitForElement(DirectGrnPageLocators.InspAttrSave, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.InspAttrSave, ElementType.Xpath);
					logs.logging("INFO", "Document Saved Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyTextTabClicking() {
		flag = webDB.waitForElement(DirectGrnPageLocators.InspTextTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.InspTextTab, ElementType.Xpath);
			logs.logging("INFO", "Text Tab Button Is Clicked");
			if (webDB.waitForElementUntilGivenTime(DirectGrnPageLocators.InspTextField, ElementType.Xpath, 20)) {
				verifyEnteringTextVAlue();
			} else if (webDB.waitForElement(DirectGrnPageLocators.TextNoDataFound, ElementType.Xpath)) {
				flag = webDB.waitForElement(DirectGrnPageLocators.TextNoDataFound, ElementType.Xpath);
				String NoData = webDB.getTextFromElement(DirectGrnPageLocators.TextNoDataFound, ElementType.Xpath);
				logs.logging("INFO", "Get Text Document Is:" + NoData);
				flag = webDB.waitForElement(DirectGrnPageLocators.OverallSave, ElementType.Xpath);
				logs.logging("INFO", "Overall Save Is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyEnteringTextVAlue() {
		flag = webDB.waitForElement(DirectGrnPageLocators.InspTextField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.InspTextField, ElementType.Xpath);
			webDB.doubleClickAnElement(DirectGrnPageLocators.InspTextField, ElementType.Xpath);
			webDB.sendTextToAnElement(DirectGrnPageLocators.InspTextInput, "YELLOW", ElementType.Xpath);
			logs.logging("INFO", "Text Is Entered");
			flag = webDB.waitForElement(DirectGrnPageLocators.InspTextSave, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(DirectGrnPageLocators.InspTextSave, ElementType.Xpath);
				logs.logging("INFO", "Document Saved Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyRejectQuanityDefect(String Defect) {
		flag = webDB.waitForElement(DirectGrnPageLocators.RejectQty, ElementType.Xpath);
		if (flag) {
			String RejectQty = webDB.getDomPropertyFromElement(DirectGrnPageLocators.RejectQty, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Reject Quantity Is:" + RejectQty);
			if (!RejectQty.equals(commonFunc.getDataFromTestDataSheet("Reject_Qty"))) {

				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DirectGrnPageLocators.DefectOverallSave, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DirectGrnPageLocators.DefectOverallSave, ElementType.Xpath);
					logs.logging("INFO", "Overall Save Is clicked");
					flag = webDB.waitForElement(DirectGrnPageLocators.DefectTab, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(DirectGrnPageLocators.DefectTab, ElementType.Xpath);
						logs.logging("INFO", "Defect Tab Is Clicked");
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(DirectGrnPageLocators.DefectTabAdd, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DirectGrnPageLocators.DefectTabAdd, ElementType.Xpath);
							logs.logging("INFO", "Add Button Is Clicked");
							flag = webDB.waitForElement(DirectGrnPageLocators.DefectField, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(DirectGrnPageLocators.DefectField, ElementType.Xpath);
								logs.logging("INFO", "Defect Field Is Clicked");
								commonFunc.hardWait(2000);
								flag = webDB.waitForElement(DirectGrnPageLocators.DefectLov, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.DefectLov, ElementType.Xpath);
									commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.DefectLov, Defect);
									logs.logging("INFO", "Entered Defect Is:" + Defect);
									flag = webDB.waitForElement(DirectGrnPageLocators.DefectQtyField,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.DefectQtyField, ElementType.Xpath);

										flag = webDB.waitForElement(DirectGrnPageLocators.DefectQtyInput,
												ElementType.Xpath);
										if (flag) {
											webDB.clearText(DirectGrnPageLocators.DefectQtyInput, ElementType.Xpath);
											webDB.sendTextToAnElement(DirectGrnPageLocators.DefectQtyInput, RejectQty,
													ElementType.Xpath);
											logs.logging("INFO", "Reject Quantity Is Entered:" + RejectQty);
											flag = webDB.waitForElement(DirectGrnPageLocators.DefectPrimaryQty,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(DirectGrnPageLocators.DefectPrimaryQty,
														ElementType.Xpath);
												logs.logging("INFO", "Primary Reject Field Is Clicked");

												flag = webDB.waitForElement(DirectGrnPageLocators.DefectSave,
														ElementType.Xpath);
												if (flag) {
													webDB.clickAnElement(DirectGrnPageLocators.DefectSave,
															ElementType.Xpath);
													logs.logging("INFO", "Line Is Saved");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				flag = webDB.waitForClickElement(InspectionDocumentsLocators.HeaderPostIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Post Button Is Verified");
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
		String alertText = webDB.getTextFromElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg,
				ElementType.Xpath);
		String IDAlertMessage = webDB.getTextFromElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg,
				ElementType.Xpath);
		VariableHandler.setIDAlertMessage(IDAlertMessage);
		// Regex: capture patterns like ID100/12345/25-26 or IDC/00020/25-26
		String regex = "([A-Za-z0-9]+)/([0-9]{5})/([0-9]{2}-[0-9]{2})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(alertText);

		ArrayList<String> matchedDocs = new ArrayList<>();

		while (matcher.find()) {
			matchedDocs.add(matcher.group()); // Full document string
		}

		if (matchedDocs.size() == 2) {

			String fromDoc = matchedDocs.get(0);
			String toDoc = matchedDocs.get(1);

			logs.logging("INFO", "From Document: " + fromDoc);
			logs.logging("INFO", "To Document: " + toDoc);

			// Extract the numeric portion only
			String fromNum = fromDoc.split("/")[1];
			String toNum = toDoc.split("/")[1];

			int startNum = Integer.parseInt(fromNum);
			int endNum = Integer.parseInt(toNum);

			ArrayList<String> generatedList = new ArrayList<>();
			String prefix = fromDoc.split("/")[0];
			String session = fromDoc.split("/")[2];

			// Generate complete range
			for (int i = startNum; i <= endNum; i++) {
				generatedList.add(prefix + "/" + String.format("%05d", i) + "/" + session);
			}

			VariableHandler.setAllDocumentNumber(generatedList);

			logs.logging("INFO", "Generated Document Numbers:");
			for (String num : generatedList) {
				System.out.println(num);
			}

		} else {
			logs.logging("INFO", "Unable to extract valid From-to format from alert text.");
		}
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
						waitingForApprovalWithEDPManager();
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
			flag = webDB.waitForElement(InspectionDocumentsLocators.OkButtonFwdForQcDocument, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionDocumentsLocators.OkButtonFwdForQcDocument, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(3000);
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
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
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CreateMrvBtn, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(3000);
								webDB.clickAnElement(MaterialReceiptVoucherLocators.CreateMrvBtn, ElementType.Xpath);
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

	/**
	 * This Methods Is Used To Create Inspection Document With inspection Plan From
	 * GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void InspectionDocumentWithInspectionPlanForStocktransferType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateInspectionDocumentsPage();
		pendingInspectionRequestDocumentToCreateInspectionDocument();
		getInspectionDocumentNumberFromToastMessageForStocktransferType();
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
			String pattern = "Pend\\. Inspection Request Moved\\s*-\\s*IDU2-\\d{4}-\\d{5}\\s*to\\s*IDU2-\\d{4}-\\d{5}";

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
	 * This Methods Is Used To Get Inspection Document Number for Stock Transfer
	 * Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void getInspectionDocumentNumberFromToastMessageForStocktransferType() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg, ElementType.Xpath);
		if (flag) {
			String IDAlertMessage = webDB.getTextFromElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg,
					ElementType.Xpath);
			VariableHandler.setIDAlertMessage(IDAlertMessage);
			logs.logging("INFO", "Get Text From Note Alert Is (ID Alert Message): " + IDAlertMessage);
			String regex = "IDU2-\\d{4}-\\d{5}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(IDAlertMessage);

			ArrayList<String> documentNumbers = new ArrayList<>();
			while (matcher.find()) {
				documentNumbers.add(matcher.group());
			}
			if (documentNumbers.size() == 2) {
				String fromDocument = documentNumbers.get(0);
				String toDocument = documentNumbers.get(1);
				logs.logging("INFO", "From Document: " + fromDocument);
				logs.logging("INFO", "To Document: " + toDocument);
				String prefix = fromDocument.substring(0, fromDocument.lastIndexOf("-")); // e.g., IDU2-2526
				int startNum = Integer.parseInt(fromDocument.substring(fromDocument.lastIndexOf("-") + 1));
				int endNum = Integer.parseInt(toDocument.substring(toDocument.lastIndexOf("-") + 1));

				ArrayList<String> allDocumentNumbers = new ArrayList<>();
				for (int i = startNum; i <= endNum; i++) {
					String generatedDocNum = prefix + "-" + String.format("%05d", i);
					allDocumentNumbers.add(generatedDocNum);
				}
				VariableHandler.setAllDocumentNumber(allDocumentNumbers);
				logs.logging("INFO", "Generated Document Numbers:");
				for (String documentNumber : allDocumentNumbers) {
					System.out.println(documentNumber);
				}
			} else {
				logs.logging("INFO", "Invalid format, unable to extract From and To document numbers.");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Pending Inspection Documents For Subcontract GRN
	 * 
	 * @author Vimal - RMITS
	 * @param DocNo
	 */
	public void verifyPendingInspectionDocumentForSubcontractGRN(String DocNo) {
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
							VariableHandler.getScoGrnNumber(), ElementType.Xpath);
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
							flag = webDB.waitForElement(MaterialReceiptVoucherLocators.CreateMrvBtn, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(3000);
								webDB.clickAnElement(MaterialReceiptVoucherLocators.CreateMrvBtn, ElementType.Xpath);
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
												verifyPendingInspectionDocuments(VariableHandler.getScoGrnNumber());
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
	 * Verify Create Pending Inspection Documents with Production Number
	 * 
	 * @author Sakthi-GWL
	 * @param DocNo
	 */
	public void verifyCreatePendingInspectionDocumentWithProdNumber(String DocNo) {
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
							VariableHandler.getScoGrnNumber(), ElementType.Xpath);
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
												verifyPendingInspectionDocuments(VariableHandler.getScoGrnNumber());
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
	 * This Methods Is Used To Document Number To
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApprovalWithEDPManager() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(GrnLocators.DocAprovalIfrmae, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval,
					commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(InspectionDocumentsLocators.OkButtonFwdForQcDocument, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionDocumentsLocators.OkButtonFwdForQcDocument, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(3000);
					commonFunc.logoutUser();
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HodUsername"));
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Create Inspection Document With inspection Plan From
	 * GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void InspectionDocumentWithInspectionPlanWithEDPManager() {
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
		waitingForApprovalWithEDPManager();
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
	 * This Methods Is Used To Get Inspection Document Number from Toast Message
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void getInspectionDocumentNumberFromToastMessageForDifferntLoc() {
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
			String regex = "ID49-\\d{4}-\\d{5}";
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
				String fromNum = fromDocument.split("-")[2];
				String toNum = toDocument.split("-")[2];
				int startNum = Integer.parseInt(fromNum);
				int endNum = Integer.parseInt(toNum);
				ArrayList<String> allDocumentNumbers = new ArrayList<>();
				for (int i = startNum; i <= endNum; i++) {
					String generatedDocumentNumber = "ID49-" + "2526-" + String.format("%05d", i);
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
	 * This Methods Is Used To Create Inspection Document With inspection Plan From
	 * GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void InspectionDocumentWithInspectionPlanWithEDPManagerForDifferentLoc() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateInspectionDocumentsPage();
		pendingInspectionRequestDocumentToCreateInspectionDocument();
		getInspectionDocumentNumberFromToastMessageForDifferntLoc();
		inspectionPlanNumberCheckingInInspectionDocument();
		if (VariableHandler.getInspectionPlanNumber().equals("")) {
		} else {
			enterObservationValue();
		}
		clickTickAndPostIconForGrnDocument();
		waitingForApprovalWithEDPManager();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateInspectionDocumentsPage();
		try {
			String alertText = VariableHandler.getIDAlertMessage();
			logs.logging("INFO", "Alert text: " + alertText);
			String pattern = "Pend\\. Inspection Request Moved\\s*-\\s*ID\\d{2}-\\d{4}-\\d{5}\\s*to\\s*ID\\d{2}-\\d{4}-\\d{5}";
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
}
