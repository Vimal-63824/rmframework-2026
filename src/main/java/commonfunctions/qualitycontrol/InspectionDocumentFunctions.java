package commonfunctions.qualitycontrol;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.productionplanning.BillOfMaterialsFunctions;
import commonfunctions.productionplanning.ProductionOrderFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.ApprovedSupplierPOFunctions;
import commonfunctions.supplychainmanagement.DirectGrnPageFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialIssuanceVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
import commonfunctions.supplychainmanagement.MaterialReturnFunctions;
import commonfunctions.supplychainmanagement.PricelistPageFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.qualitycontrol.InspectionDocumentsLocators;
import commonlocators.qualitycontrol.InspectionPlanLocators;
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.MaterialReceiptVoucherLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class InspectionDocumentFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static InspectionRequestFunctions insRequestFunc = new InspectionRequestFunctions();
	private static MaterialRequestFunctions MRFunc = new MaterialRequestFunctions();
	private static MaterialReturnFunctions MatReturnFunc = new MaterialReturnFunctions();
	private static MaterialIssuanceVoucherFunctions materialIssuanceVoucherFunc = new MaterialIssuanceVoucherFunctions();
	private static DirectGrnPageFunctions directGrnPageFunc = new DirectGrnPageFunctions();
	private static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
	private static PricelistPageFunctions priceListFunctions = new PricelistPageFunctions();
	private static InspectionPlanFunctions inspectionPlanFunc = new InspectionPlanFunctions();
	private static ApprovedSupplierPOFunctions approvedSupplierPoFunc = new ApprovedSupplierPOFunctions();
	private static BillOfMaterialsFunctions bomFunc = new BillOfMaterialsFunctions();
	private static ProductionOrderFunctions productionOrderFunc = new ProductionOrderFunctions();
	private static SalesInvoiceFunctions salesInvoiceFunc = new SalesInvoiceFunctions();

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
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
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
		flag = webDB.waitForElement(GrnLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.SearchButton, ElementType.Xpath);
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateInspectionDocumentsPage();
		verifyPendingInspectionDocuments(VariableHandler.getGrnDocNumber());
		clickTickAndPostIcon();
		flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("AshokValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigateInspectionDocumentsPage();
		}
		verifyValidateInspectionDocumentsStatus(VariableHandler.getIDNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
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
		if (VariableHandler.getInspectionPlanNumber().equals("--Select Insp. Plan No. --")) {
		} else {
			enterObservationValue();
		}
		clickTickAndPostIconForGrnDocument();
		waitingForApproval();
		String alertText = VariableHandler.getIDAlertMessage();
		logs.logging("INFO", "Alert text: " + alertText);
//		String pattern = "Pend\\. Inspection Request Moved\\s*-\\s*[A-Za-z0-9]+/\\d{5}/\\d{2}-\\d{2}\\s*~\\s*[A-Za-z0-9]+/\\d{5}/\\d{2}-\\d{2}";
		String pattern = "Pend\\. Inspection Request Moved\\.\\s*:\\s*([A-Za-z0-9]+)/([0-9]{5})/([0-9]{2}-[0-9]{2})\\s*to\\s*([A-Za-z0-9]+)/([0-9]{5})/([0-9]{2}-[0-9]{2})";
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
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkShowMyRecordAndUnselectAll(InspectionDocumentsLocators.PendingPOShowMyRecordBtn,
						InspectionDocumentsLocators.PendingPOShowMyRecordHideBtn,
						InspectionDocumentsLocators.PendingInspectionSelectAllBtn);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.expandFindTable();
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
					webDB.sendTextToAnElement(InspectionDocumentsLocators.ObservationValueInput, "30",
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
					flag = webDB.waitForElement(InspectionDocumentsLocators.InsDocumentDrillDown(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(InspectionDocumentsLocators.InsDocumentDrillDown(), ElementType.Xpath);
						logs.logging("INFO", "Document DrillDown SuccessFully");
						commonFunc.hardWait(2000);
						inspectionPlanNumberCheckingInInspectionDocument();
						commonFunc.hardWait(2000);
						if (VariableHandler.getInspectionPlanNumber().equals("--Select Insp. Plan No. --")) {
						} else {
							enterObservationValue();
						}
						clickTickAndPostIconForGrnDocument();
						waitingForApproval();
						commonFunc.hardWait(2000);
//						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
//								System.getProperty("LoginPassword"), "AdminUsername");
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
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
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
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateInspectionDocumentsPage();
		try {
			String alertText = VariableHandler.getIDAlertMessage();
			logs.logging("INFO", "Alert text: " + alertText);
//			String pattern = "Pend\\. Inspection Request Moved\\s*-\\s*IDU2-\\d{4}-\\d{5}\\s*~\\s*IDU2-\\d{4}-\\d{5}";
			String regex = "Pend\\. Inspection Request Moved\\.\\s*:\\s*[A-Za-z0-9]+-\\d{4}-\\d{5}\\s*to\\s*[A-Za-z0-9]+-\\d{4}-\\d{5}";
			if (alertText.matches(".*" + regex + ".*")) {
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
				commonFunc.hardWait(3000);
				commonFunc.expandFindTable();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(InspectionDocumentsLocators.VoucherLov, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.VoucherLov,
						VariableHandler.getScoGrnNumber());
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
					commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.PendingProdOrderLov, DocNo);
					webDB.switchToFrame(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
					flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionSearchBtn,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Go Button IS Displayed");
						webDB.clickAnElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
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
	 * Verify Create Pending Inspection Documents with Voucher Number
	 * 
	 * @author Sakthi-GWL
	 * @param DocNo
	 */
	public void verifyCreatePendingInspectionDocumentWithVoucherNumber(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Inspection Documents Is Displayed");
				webDB.switchToFrame(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
				flag = webDB.waitForElement(InspectionDocumentsLocators.PendingSearchField, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.PendingVoucherNoLov, DocNo);
					webDB.switchToFrame(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
					flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionSearchBtn,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Go Button IS Displayed");
						webDB.clickAnElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
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
//					commonFunc.logoutUser();
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
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
		waitingForApprovalWithEDPManager();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
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
			logs.logging("INFO", "Get Text From Note Alert Is(ID Alert Message:" + IDAlertMessage);
			flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg, ElementType.Xpath);
			String InspectionDocumentNumber = webDB
					.getTextFromElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg, ElementType.Xpath);
			String regex = "INDP-\\d{4}-\\d{4}";
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
					String generatedDocumentNumber = "INDP-" + "2627-" + String.format("%04d", i);
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
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
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateInspectionDocumentsPage();
		try {
			String alertText = VariableHandler.getIDAlertMessage();
			logs.logging("INFO", "Alert text: " + alertText);
			String pattern = "Pend\\. Inspection Request Moved\\.\\s*:\\s*INDP-\\d{4}-\\d{4}\\s*to\\s*INDP-\\d{4}-\\d{4}";
			if (alertText.matches(".*" + pattern + ".*")) {
				documentSearchAndSelectLovForFindPage();
//				commonFunc.logoutUser();
				logs.logging("INFO", "Specific alert found, proceeding with the process.");
			} else {
//				commonFunc.logoutUser();
				logs.logging("INFO", "Other alert detected, no action taken.");
			}
		} catch (Exception e) {
			logs.logging("INFO", "No alert appeared or other error: " + e.getMessage());
		}
	}

	/**
	 * Click Cancel Icon in header
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCancelInHeaderInspectionDocument() {
		flag = webDB.waitForElement(InspectionDocumentsLocators.InspDocHeaderCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionDocumentsLocators.InspDocHeaderCancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Pending Iframe switching
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchIframeInPendingInspectionDocument() {
		flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.PendingSearchField, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Pending Inspection Documents with Request Number
	 * 
	 * @author Sakthi-GWL
	 * @param DocNo
	 */
	public void verifyCreatePendingInspectionDocumentWithInsReqNumber(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
			switchIframeInPendingInspectionDocument();
			logs.logging("INFO", "Pending Icon Clicked in Ins Doc.");
			commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.PendingInsReqNoLov, DocNo);
			switchIframeInPendingInspectionDocument();
			flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
				flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search button Clicked and Doc No Displayed");
					webDB.moveToElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo), ElementType.Xpath);
					webDB.clickAnElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo), ElementType.Xpath);
					flag = webDB.waitForElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
						webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
						flag = webDB.waitForElement(InspectionDocumentsLocators.OKButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(InspectionDocumentsLocators.OKButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
							flag = webDB.waitForElement(InspectionDocumentsLocators.InspactionDocumentsNumber,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Document Created Successfully");
								storeInspectionDocumentsNumber();
							}
							if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Click Checkbox Pending Inspection Documents with Request Number
	 * 
	 * @author Sakthi-GWL
	 * @param DocNo
	 */
	public void clickPendingSelectTwoInspectDocumentWithInsReqNumber(String DocNo, String DocNo2) {
		flag = webDB.waitForElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
			switchIframeInPendingInspectionDocument();
			logs.logging("INFO", "Pending Icon Clicked in Ins Doc.");
			commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.PendingInsReqNoLov, DocNo);
			switchIframeInPendingInspectionDocument();
			flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
				flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search button Clicked and Doc No Displayed");
					webDB.moveToElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo), ElementType.Xpath);
					flag = webDB.clickAnElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo),
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFo", "Document No 1 Searched and Checkbox Clicked: " + DocNo);
						commonFunc.clearFiltersInFindPage();
						commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.PendingInsReqNoLov,
								DocNo2);
						switchIframeInPendingInspectionDocument();
						flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionSearchBtn,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(InspectionDocumentsLocators.PendingInspectionSearchBtn,
									ElementType.Xpath);
							flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo2),
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Search button Clicked and Doc No Displayed");
								webDB.moveToElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo),
										ElementType.Xpath);
								flag = webDB.clickAnElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo2),
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFo", "Document No 2 Searched and Checkbox Clicked: " + DocNo2);
								}
							}
						}
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * Verify Click Create QC button Pending Inspection Documents with Request
	 * Number
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyTwoInspectionDocumentsSelectedInPending(String Doc1, String Doc2) {
		switchIframeInPendingInspectionDocument();
		flag = webDB.waitForElement(InspectionDocumentsLocators.InspDocPendingShowDoc, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(InspectionDocumentsLocators.InspDocPendingShowDoc, ElementType.Xpath);
			flag = webDB.clickAnElement(InspectionDocumentsLocators.InspDocPendingShowDoc, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Show My Record Button Clicked");
				commonFunc.checkRefreshCellsDisappear();
				webDB.waitForElement(InspectionDocumentsLocators.InspDocPendingTableDocNo, ElementType.Xpath);
				List<WebElement> elements = webDB
						.getListOfElements(InspectionDocumentsLocators.InspDocPendingTableDocNo, ElementType.Xpath);
				flag = elements.size() == 2;
				logs.logging("INFO", "Table Documents Size: " + elements.size());
				if (flag) {
					for (WebElement webElement : elements) {
						if (webElement.getText().equalsIgnoreCase(Doc1)
								|| webElement.getText().equalsIgnoreCase(Doc1)) {
							flag = true;
						}
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * Verify Click Create QC button Pending Inspection Documents with Request
	 * Number
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCreateQCInPendingInspectionDocument() {
		switchIframeInPendingInspectionDocument();
		flag = webDB.waitForElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
			webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.OKButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionDocumentsLocators.OKButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(InspectionDocumentsLocators.InspactionDocumentsNumber, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Document Created Successfully");
					storeInspectionDocumentsNumber();
				}
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Pending Inspection Documents with partial quantity
	 * 
	 * @author Sakthi-GWL
	 * @param DocNo
	 */
	public void verifyCreatePendingInsDocWithVouNoPartialQuantity(String DocNo, String Quantity) {
		flag = webDB.waitForElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
			switchIframeInPendingInspectionDocument();
			logs.logging("INFO", "Pending Icon Clicked in Ins Doc.");
			commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.PendingVoucherNoLov, DocNo);
			switchIframeInPendingInspectionDocument();
			flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionDocumentsLocators.PendingInspectionSearchBtn, ElementType.Xpath);
				flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search button Clicked and Doc No Displayed");
					webDB.clearTextField(InspectionDocumentsLocators.InspectionQuantityInput(DocNo), ElementType.Xpath);
					flag = webDB.sendTextToAnElement(InspectionDocumentsLocators.InspectionQuantityInput(DocNo),
							Quantity, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Quantity Entered value: " + Quantity);
						webDB.moveToElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo), ElementType.Xpath);
						webDB.clickAnElement(InspectionDocumentsLocators.InspectionCheckBox(DocNo), ElementType.Xpath);
						flag = webDB.waitForElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
							webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
							flag = webDB.waitForElement(InspectionDocumentsLocators.OKButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(InspectionDocumentsLocators.OKButton, ElementType.Xpath);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(InspectionDocumentsLocators.InspactionDocumentsNumber,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Document Created Successfully");
									storeInspectionDocumentsNumber();
								}
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
		assertTrue(flag);
	}

	/**
	 * How to create Inspection document from Pending Inspection Request - Whole?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyInspectionDocumentsFromRequestWholeType() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String Quantity = "10";
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateInspectionRequest();
			insRequestFunc.clickAddIconInInspectionRequest();
			insRequestFunc.fillDateVoucherTypeAndPrefixInCreationFrame(Today,
					commonFunc.getDataFromTestDataSheet("StockQcText"),
					commonFunc.getDataFromTestDataSheet("StockQcPrefix"));
			insRequestFunc.fillWarehouseAndInspectorInCreationFrame(
					commonFunc.getDataFromTestDataSheet("FinishedGoodWH"),
					commonFunc.getDataFromTestDataSheet("GWL-RInspector"));
			insRequestFunc.clickLoadButtonInInspectionRequest();
			insRequestFunc.searchItemNameInPendingLoadAndCreate(commonFunc.getDataFromTestDataSheet("WholeInsItem"),
					Quantity);
			insRequestFunc.clickSaveHeaderAndPostDocument();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getInspectionReqNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateInspectionDocumentsPage();
			verifyCreatePendingInspectionDocumentWithInsReqNumber(VariableHandler.getInspectionReqNumber());
			clickTickAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyInspectionDocumentsFromRequestWholeType': " + e);
			return false;
		}
	}

	/**
	 * How to create Inspection document from Pending Inspection Request - partial?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyInspectionDocumentsFromRequestPartialType() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String Quantity = "10";
		String PartialQuantity = "5";
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					System.getProperty("AdminUsername"));
			commonFunc.navigateToInventoryMaterialRequest();
			MRFunc.verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("IntraTransfer"),
					commonFunc.getDataFromTestDataSheet("FG-WH-KD"),
					commonFunc.getDataFromTestDataSheet("GWL-RInspector"),
					commonFunc.getDataFromTestDataSheet("Shift_100"));
			MRFunc.verifyAddItem(commonFunc.getDataFromTestDataSheet("PartialInsItem"), Quantity);
			MRFunc.lineSaveButton();
			MRFunc.headerSaveAndPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToInventoryMaterialRequest();
			MRFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
					commonFunc.getDataFromTestDataSheet("PostedStatus"));
			commonFunc.navigateMaterialReturn();
			MatReturnFunc.clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("MaterialReturnPrefix"),
					commonFunc.getDataFromTestDataSheet("WarehouseType"),
					commonFunc.getDataFromTestDataSheet("FG-WH-KD"), commonFunc.getDataFromTestDataSheet("RMU100_WH"),
					commonFunc.getDataFromTestDataSheet("Shift_100"));
			MatReturnFunc.fillTypeAndMIVDocNumberAndFetchLoad(commonFunc.getDataFromTestDataSheet("PartialInsItem"),
					VariableHandler.getMIVNumber());
			MatReturnFunc.fillToolLifeRefAndSaveHeader(commonFunc.getDataFromTestDataSheet("RefText_Test"));
			MatReturnFunc.clickMoveToInspection();
			commonFunc.navigateInspectionDocumentsPage();
			verifyCreatePendingInsDocWithVouNoPartialQuantity(VariableHandler.getInspectionReqNumber(),
					PartialQuantity);
			clickTickAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateInspectionDocumentsPage();
			verifyCreatePendingInsDocWithVouNoPartialQuantity(VariableHandler.getInspectionReqNumber(),
					PartialQuantity);
			clickTickAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			// TODO Need to Add MRV Steps
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyInspectionDocumentsFromRequestPartialType': " + e);
			return false;
		}
	}

	/**
	 * How to Cancel the Inspection document?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyCancelInspectionDocuments() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String Quantity = "10";
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateInspectionRequest();
			insRequestFunc.clickAddIconInInspectionRequest();
			insRequestFunc.fillDateVoucherTypeAndPrefixInCreationFrame(Today,
					commonFunc.getDataFromTestDataSheet("StockQcText"),
					commonFunc.getDataFromTestDataSheet("StockQcPrefix"));
			insRequestFunc.fillWarehouseAndInspectorInCreationFrame(
					commonFunc.getDataFromTestDataSheet("FinishedGoodWH"),
					commonFunc.getDataFromTestDataSheet("GWL-RInspector"));
			insRequestFunc.clickLoadButtonInInspectionRequest();
			insRequestFunc.searchItemNameInPendingLoadAndCreate(commonFunc.getDataFromTestDataSheet("WholeInsItem"),
					Quantity);
			insRequestFunc.clickSaveHeaderAndPostDocument();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getInspectionReqNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateInspectionDocumentsPage();
			verifyCreatePendingInspectionDocumentWithInsReqNumber(VariableHandler.getInspectionReqNumber());
			clickCancelInHeaderInspectionDocument();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCancelInspectionDocuments': " + e);
			return false;
		}
	}

	/**
	 * How to create Inspection document for Multiple Pending Inspection request?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyInspectionDocumentsFromRequestMultiDocuments() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String Quantity = "10";
		String Doc1 = null;
		String Doc2 = null;
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateInspectionRequest();
			insRequestFunc.clickAddIconInInspectionRequest();
			insRequestFunc.fillDateVoucherTypeAndPrefixInCreationFrame(Today,
					commonFunc.getDataFromTestDataSheet("StockQcText"),
					commonFunc.getDataFromTestDataSheet("StockQcPrefix"));
			insRequestFunc.fillWarehouseAndInspectorInCreationFrame(
					commonFunc.getDataFromTestDataSheet("FinishedGoodWH"),
					commonFunc.getDataFromTestDataSheet("GWL-RInspector"));
			insRequestFunc.clickLoadButtonInInspectionRequest();
			insRequestFunc.searchItemNameInPendingLoadAndCreate(commonFunc.getDataFromTestDataSheet("WholeInsItem"),
					Quantity);
			insRequestFunc.clickSaveHeaderAndPostDocument();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getInspectionReqNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			Doc1 = VariableHandler.getInspectionReqNumber();
			commonFunc.navigateInspectionRequest();
			insRequestFunc.clickAddIconInInspectionRequest();
			insRequestFunc.fillDateVoucherTypeAndPrefixInCreationFrame(Today,
					commonFunc.getDataFromTestDataSheet("StockQcText"),
					commonFunc.getDataFromTestDataSheet("StockQcPrefix"));
			insRequestFunc.fillWarehouseAndInspectorInCreationFrame(
					commonFunc.getDataFromTestDataSheet("FinishedGoodWH"),
					commonFunc.getDataFromTestDataSheet("GWL-RInspector"));
			insRequestFunc.clickLoadButtonInInspectionRequest();
			insRequestFunc.searchItemNameInPendingLoadAndCreate(commonFunc.getDataFromTestDataSheet("WholeInsItem"),
					Quantity);
			insRequestFunc.clickSaveHeaderAndPostDocument();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getInspectionReqNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			Doc2 = VariableHandler.getInspectionReqNumber();
			commonFunc.navigateInspectionDocumentsPage();
			clickPendingSelectTwoInspectDocumentWithInsReqNumber(Doc1, Doc2);
			verifyTwoInspectionDocumentsSelectedInPending(Doc1, Doc2);
			clickCreateQCInPendingInspectionDocument();
			clickTickAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyInspectionDocumentsFromRequestWholeType': " + e);
			return false;
		}
	}

	/**
	 * This Methods Is Used To Create Inspection Document With inspection Plan From
	 * GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void InspectionDocumentWithInspectionPlanWithEDPManagerForStockTransfer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateInspectionDocumentsPage();
		pendingInspectionRequestDocumentToCreateInspectionDocument();
		getInspectionDocumentNumberFromToastMessageForStockTransfer();
		inspectionPlanNumberCheckingInInspectionDocument();
		if (VariableHandler.getInspectionPlanNumber().equals("")) {
		} else {
			enterObservationValue();
		}
		clickTickAndPostIconForGrnDocument();
		waitingForApprovalWithEDPManager();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateInspectionDocumentsPage();
		try {
			String alertText = VariableHandler.getIDAlertMessage();
			logs.logging("INFO", "Alert text: " + alertText);
			String pattern = "Pend\\. Inspection Request Moved\\s*-\\s*IDPO-\\d{4}-\\d{4}\\s*~\\s*IDPO-\\d{4}-\\d{4}";
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
	public void getInspectionDocumentNumberFromToastMessageForStockTransfer() {
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
			String regex = "IDPO-\\d{4}-\\d{4}";
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
					String generatedDocumentNumber = "IDPO-" + "2627-" + String.format("%04d", i);
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
	 * Verify TableSpec InputParameter
	 * 
	 * @author Vimal - RMITS
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
				webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue);
				String value = webDB.getDomPropertyFromElement(InputParameter, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Attribute Value Is: " + value);
				flag = value.contains(Enteredvalue);
				if (flag) {
					logs.logging("INFO", "Verified Entered Value With Get Attribute Value");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Reject Quanity Defect
	 * 
	 * @author Vimal - RMITS
	 */
	public void rejectQuanityDefect(String QtyApplyFor0, String Defect) {
		webDB.moveToElement(DirectGrnPageLocators.ID_AcptQty_Field, ElementType.Xpath);
		verifyAddTableSpecs(DirectGrnPageLocators.ID_AcptQty_Field, DirectGrnPageLocators.ID_AcptQty_Field,
				QtyApplyFor0);
		commonFunc.hardWait(1000);
		webDB.pressTabKey();
		flag = webDB.waitForElement(DirectGrnPageLocators.RejectQty, ElementType.Xpath);
		if (flag) {
			String RejectQty = webDB.getDomPropertyFromElement(DirectGrnPageLocators.RejectQty, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Reject Quantity Is:" + RejectQty);
			commonFunc.hardWait(2000);
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
						commonFunc.searchAndSelectValueInFilters(DirectGrnPageLocators.DefectLov, Defect);
						logs.logging("INFO", "Entered Defect Is:" + Defect);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(DirectGrnPageLocators.DefectQtyField, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DirectGrnPageLocators.DefectQtyField, ElementType.Xpath);
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(DirectGrnPageLocators.DefectQtyInput, ElementType.Xpath);
							if (flag) {
								webDB.clearText(DirectGrnPageLocators.DefectQtyInput, ElementType.Xpath);
								commonFunc.hardWait(2000);
								webDB.sendTextToAnElement(DirectGrnPageLocators.DefectQtyInput, RejectQty,
										ElementType.Xpath);
								logs.logging("INFO", "Reject Quantity Is Entered:" + RejectQty);
								flag = webDB.waitForElement(DirectGrnPageLocators.DefectPrimaryQty, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(DirectGrnPageLocators.DefectPrimaryQty, ElementType.Xpath);
									logs.logging("INFO", "Primary Reject Field Is Clicked");
									commonFunc.hardWait(1000);
									flag = webDB.waitForElement(DirectGrnPageLocators.DefectSave, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(DirectGrnPageLocators.DefectSave, ElementType.Xpath);
										logs.logging("INFO", "Line Is Saved");
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
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Create Inspection Document With inspection Plan From
	 * GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void inspectionDocumentWithInspectionPlanForDifferentLoc() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateInspectionDocumentsPage();
		pendingInspectionRequestDocumentToCreateInspectionDocument();
		getInspectionDocumentNumberFromToastMessageForDifferentLocDirectGrn();
		inspectionPlanNumberCheckingInInspectionDocument();
		if (VariableHandler.getInspectionPlanNumber().equals("")) {
		} else {
			enterObservationValue();
		}
		clickTickAndPostIconForGrnDocument();
		waitingForApproval();
		String alertText = VariableHandler.getIDAlertMessage();
		logs.logging("INFO", "Alert text: " + alertText);
//		String pattern = "Pend\\. Inspection Request Moved\\s*-\\s*[A-Za-z]+-\\d{4}-\\d{4}\\s*~\\s*[A-Za-z]+-\\d{4}-\\d{4}";
		String pattern = "Pend\\.\\s*Inspection Request Moved\\.\\s*:\\s*[A-Za-z]+-\\d{4}-\\d{4}\\s*(?:to|~)\\s*[A-Za-z]+-\\d{4}-\\d{4}";
		if (alertText.matches(".*" + pattern + ".*")) {
			documentSearchAndSelectLovForFindPage();
//			commonFunc.logoutUser();
			logs.logging("INFO", "Specific alert found, proceeding with the process.");
		} else {
//			commonFunc.logoutUser();
			logs.logging("INFO", "Other alert detected, no action taken.");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Get Inspection Document Number from Toast Message
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void getInspectionDocumentNumberFromToastMessageForDifferentLocDirectGrn() {
		String alertText = webDB.getTextFromElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg,
				ElementType.Xpath);
		String IDAlertMessage = webDB.getTextFromElement(InspectionDocumentsLocators.InspectionDocumentSuccessMsg,
				ElementType.Xpath);
		VariableHandler.setIDAlertMessage(IDAlertMessage);
		// Regex: capture patterns like ID100/12345/25-26 or IDC/00020/25-26
		String regex = "([A-Za-z0-9]+)-([0-9]{4})-([0-9]{4})";
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
			String fromNum = fromDoc.split("-")[2];
			String toNum = toDoc.split("-")[2];

			System.out.println(fromNum + " from " + toNum);

			int startNum = Integer.parseInt(fromNum);
			int endNum = Integer.parseInt(toNum);

			ArrayList<String> generatedList = new ArrayList<>();
			String prefix = fromDoc.split("-")[0];
			String session = fromDoc.split("-")[1];

			System.out.println(prefix + " prefix " + session);

			// Generate complete range
			for (int i = startNum; i <= endNum; i++) {
				generatedList.add(prefix + "-" + session + "-" + String.format("%04d", i));
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
	 * This Methods Is Used To Create Inspection Document With inspection Plan From
	 * GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void inspectionDocumentWithInspectionPlanForDifferentValues() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateInspectionDocumentsPage();
		pendingInspectionRequestDocumentToCreateInspectionDocument();
		getInspectionDocumentNumberFromToastMessage();
		inspectionPlanNumberCheckingInInspectionDocument();
		if (VariableHandler.getInspectionPlanNumber().equals("")) {
		} else {
			verifyChangeSampleAndObservationValue();
			enterObservationValue();
			verifyAttributeTabClicking();
			verifyTextTabClicking();

		}
		clickTickAndPostIconForGrnDocument();
		waitingForApproval();
		String alertText = VariableHandler.getIDAlertMessage();
		logs.logging("INFO", "Alert text: " + alertText);
		String pattern = "Pend\\. Inspection Request Moved\\s*-\\s*[A-Za-z0-9]+/\\d{5}/\\d{2}-\\d{2}\\s*~\\s*[A-Za-z0-9]+/\\d{5}/\\d{2}-\\d{2}";
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
	 * This Method Is Used to Verify Change Sample And Observation Value
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyChangeSampleAndObservationValue() {
		flag = webDB.waitForElement(InspectionDocumentsLocators.InspCharacter, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionDocumentsLocators.InspCharacter, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.SampleTableValue, ElementType.Xpath);
			if (flag) {
				List<WebElement> sampleWebElement = webDB.getDriver()
						.findElements(By.xpath(InspectionDocumentsLocators.SampleTableValue));
				List<WebElement> ObservationWebElement = webDB.getDriver()
						.findElements(By.xpath(InspectionDocumentsLocators.ObservationTableValue));
				for (int i = 0; i < sampleWebElement.size(); i++) {
					Actions action = new Actions(webDB.getDriver());
					commonFunc.hardWait(1000);
					action.doubleClick(sampleWebElement.get(i)).build().perform();
					int a = i + 1;
					webDB.clearText(InspectionDocumentsLocators.SampleTableValueInput(a), ElementType.Xpath);
					commonFunc.hardWait(1000);
					action.doubleClick(sampleWebElement.get(i)).build().perform();
					webDB.sendTextToAnElement(InspectionDocumentsLocators.SampleTableValueInput(a), "1",
							ElementType.Xpath);
					action.doubleClick(ObservationWebElement.get(i)).build().perform();
					commonFunc.hardWait(1000);
					webDB.clearText(InspectionDocumentsLocators.ObservationTableValueInput(a), ElementType.Xpath);
					commonFunc.hardWait(1000);
					action.doubleClick(ObservationWebElement.get(i)).build().perform();
					webDB.sendTextToAnElement(InspectionDocumentsLocators.ObservationTableValueInput(a), "1",
							ElementType.Xpath);

				}
				flag = webDB.waitForElement(InspectionDocumentsLocators.TickButtonInspChar, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(InspectionDocumentsLocators.TickButtonInspChar, ElementType.Xpath);
					webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Inspection Documents Incoming
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyInspectionDocumentsIncoming() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		priceListFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		inspectionPlanFunc.verifyCreateInspectionPlan();
		approvedSupplierPoFunc.verifyApprovedSupplierPO();
		verifyAddItemNameInSheet(webDB.getDataFromProperties("QualityInspectionSheet"),
				webDB.getDataFromProperties("Insp-IncommingSheet"));
		directGrnPageFunc.verifyCreationOfDirectGrnPriceListForIncomingInspection();
		grnPageFunc.aafterVerifyTheStatusInspectionDocumentMoveToQcOrMrv();
	}

	/**
	 * 
	 * This Method Is Used To Add Item Name In Sheet
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyAddItemNameInSheet(String FileName, String SheetName) {
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		excelUtils.writeDataInExcel(Filepath, SheetName, 3, 1, VariableHandler.getItemName());
	}

	/**
	 * This Method Is Used To Verify Inspetion Plan With In Process
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyInspectionPlanWithInprocessForManufacturing() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition") + RandomStringUtils.randomNumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		String RawMaterial = commonFunc.getDataFromTestDataSheet("GWL_Item_Inspection_Rm");
		commonFunc.navigateItemPage();
		priceListFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GWL_Item_Inspection"));
		String FinishedMaterial = VariableHandler.getItemName();
		commonFunc.navigateToBillOfMaterialsPage();
		bomFunc.clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
				FinishedMaterial);
		bomFunc.enterEffFromToDateAndSaveHeader(Today, Today);
		bomFunc.navigateToRoutingSubTab();
		bomFunc.clickAddLineIconInRoutingTab();
		bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
		bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
				commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
		bomFunc.clickIRFlagInRoutingTabForProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
		bomFunc.clickSaveLineIconInRoutingTab();
		bomFunc.clickIRFlagInRoutingTabForProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
		bomFunc.clickSaveLineIconInRoutingTab();
		bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
		bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
				commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
		bomFunc.navigateToMaterialsSubTab();
		bomFunc.clickAddLineIconInMaterialTab();
		bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RawMaterial,
				commonFunc.getDataFromTestDataSheet("Kgs"));
		bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
		bomFunc.clickSaveLineIconInMaterialTab();
		bomFunc.clickHeaderSaveAndPostButtonInBOM();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBOMNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToBillOfMaterialsPage();
		bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
				commonFunc.getDataFromTestDataSheet("ActiveStatus"));
		inspectionPlanFunc.verifyCreateInspectionPlanForInprocess();
		productionOrderFunc.verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create Inspection Document With inspection Plan From
	 * GRN
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void inspectionDocumentWithInspectionPlanForDifferentValuesForProductionType() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateInspectionDocumentsPage();
		pendingInspectionRequestDocumentToCreateInspectionDocumentForProduction();
		inspectionPlanNumberCheckingInInspectionDocument();
		verifyGetInspectionPlanNumber();
		verifyChangeSampleAndObservationValue();
		enterObservationValue();
		verifyAttributeTabClicking();
		verifyTextTabClicking();
		clickTickAndPostIconForGrnDocument();
		waitingForApproval();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Get Inspection Plan Number
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyGetInspectionPlanNumber() {
		flag = webDB.waitForElement(InspectionDocumentsLocators.InspectiondocumentShowMore, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionDocumentsLocators.InspectiondocumentShowMore, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionPlanNumber, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.InspectionPlanLovButton, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.InspectionPlanLovButton,
						commonFunc.getDataFromTestDataSheet("AutomationPlanText"));
				flag = webDB.waitForElement(InspectionDocumentsLocators.InspectiondocumentShowLess, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(InspectionDocumentsLocators.InspectiondocumentShowLess,
							ElementType.Xpath);
					flag = webDB.waitForElement(InspectionDocumentsLocators.HeaderTickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Header Tick Icon Is Displayed");
						webDB.clickAnElement(InspectionDocumentsLocators.HeaderTickIcon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
					}
				}
			}
		}

	}

	/**
	 * This Methods Is Used To Create Inspection Document From Pending Inspection
	 * Request
	 * 
	 * @author Deepan-GWL
	 */
	public void pendingInspectionRequestDocumentToCreateInspectionDocumentForProduction() {
		flag = webDB.waitForElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Inspection Documents Is Displayed");
				webDB.switchToFrame(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
				commonFunc.expandFindTable();
				flag = webDB.waitForElement(InspectionDocumentsLocators.ProductionOrderNoLovButton, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.ProductionOrderNoLovButton,
						VariableHandler.getProductionOrderNumber());
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
	 * This Method Is Used To Verify Inspection Plan With Final Inspection
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyInspectionPlanWithFinalInspection() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition") + RandomStringUtils.randomNumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		String RawMaterial = commonFunc.getDataFromTestDataSheet("GWL_Item_Inspection_Rm");
		commonFunc.navigateItemPage();
		priceListFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GWL_Item_Inspection"));
		approvedSupplierPoFunc.verifyItemUnitAssociationForFinalInnspection();

		String FinishedMaterial = VariableHandler.getItemName();
		commonFunc.navigateToBillOfMaterialsPage();
		bomFunc.clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
				FinishedMaterial);
		bomFunc.enterEffFromToDateAndSaveHeader(Today, Today);
		bomFunc.navigateToRoutingSubTab();
		bomFunc.clickAddLineIconInRoutingTab();
		bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
		bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
				commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
		bomFunc.clickIRFlagInRoutingTabForProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
		bomFunc.clickSaveLineIconInRoutingTab();
		bomFunc.clickIRFlagInRoutingTabForProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
		bomFunc.clickSaveLineIconInRoutingTab();
		bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
		bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
				commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
		bomFunc.navigateToMaterialsSubTab();
		bomFunc.clickAddLineIconInMaterialTab();
		bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RawMaterial,
				commonFunc.getDataFromTestDataSheet("Kgs"));
		bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
		bomFunc.clickSaveLineIconInMaterialTab();
		bomFunc.clickHeaderSaveAndPostButtonInBOM();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBOMNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToBillOfMaterialsPage();
		bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
				commonFunc.getDataFromTestDataSheet("ActiveStatus"));
		inspectionPlanFunc.verifyCreateInspectionPlanForInprocess();
		productionOrderFunc.verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithFinalInspection();
	}

	/**
	 * This Method Is Used To Verify Inspection Plan WIth Pre Dispatch
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyInspectionPlanWithPreDispatch() {
		salesInvoiceFunc.verifyDirectSalesInvoiceForPreDispatch();
		commonFunc.navigateInspectionDocumentsPage();
		pendingInspectionRequestDocumentToCreateInspectionDocument(VariableHandler.getSIdocno());
		inspectionPlanNumberCheckingInInspectionDocument();
//		verifyGetInspectionPlanNumber();
//		verifyChangeSampleAndObservationValue();
//		enterObservationValue();
//		verifyAttributeTabClicking();
//		verifyTextTabClicking();
		clickTickAndPostIconForGrnDocument();
		waitingForApproval();
	}

	/**
	 * This Methods Is Used To Create Inspection Document From Pending Inspection
	 * Request
	 * 
	 * @author Deepan-GWL
	 */
	public void pendingInspectionRequestDocumentToCreateInspectionDocument(String VoucherNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Inspection Documents Is Displayed");
				webDB.switchToFrame(InspectionDocumentsLocators.PendingInspectionFrame, ElementType.Xpath);
				commonFunc.expandFindTable();
				flag = webDB.waitForElement(InspectionDocumentsLocators.VoucherLov, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(InspectionDocumentsLocators.VoucherLov, VoucherNo);
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

}
