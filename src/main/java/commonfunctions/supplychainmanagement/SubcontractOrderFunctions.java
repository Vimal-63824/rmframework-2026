package commonfunctions.supplychainmanagement;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Oct 14, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonfunctions.productionplanning.ProductionOrderFunctions;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.ProductionOrderLocators;
import commonlocators.qualitycontrol.InspectionDocumentsLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.SubcontractOrderLocators;
import io.restassured.http.ContentType;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.RestUtils;
import utils.RestUtils.methodType;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SubcontractOrderFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	public static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	public static RestUtils restApi = new RestUtils();
	public static ProductionOrderFunctions ProductionordertFunc = new ProductionOrderFunctions();
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static MaterialRequestFunctions materialRequestFunc = new MaterialRequestFunctions();
	private static MaterialIssuanceVoucherFunctions materialIssuanceVoucherFunc = new MaterialIssuanceVoucherFunctions();
	private static GrnScoPageFunctions grnScoPageFunc = new GrnScoPageFunctions();
	private static MaterialReceiptVoucherFunctions materialReceiptFunctions = new MaterialReceiptVoucherFunctions();
	static MaterialReceiptVoucherFunctions mrvFunc = new MaterialReceiptVoucherFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	static InspectionDocumentFunctions qcFunc = new InspectionDocumentFunctions();

	/**
	 * Verify Pending Subcontract Order
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingSubcontractOrder(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingSubcontractOrder, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Subcontract Order Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingSubcontractOrder, ElementType.Xpath);
			flag = webDB.waitForElement(SubcontractOrderLocators.FrameProdPoc, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Frame Is Displayed");
				webDB.switchToFrame(SubcontractOrderLocators.FrameProdPoc, ElementType.Xpath);
				commonFunc.hardWait(3000);
//				flag = webDB.waitForElement(SubcontractOrderLocators.ProdOrderNo, ElementType.Xpath);
//				if (flag) {
//					logs.logging("INFO", "Switched To Pending Frame");
//					webDB.clickAnElement(SubcontractOrderLocators.ProdOrderNo, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.ProdOrderNo, DocNo);
				webDB.switchToFrame(SubcontractOrderLocators.FrameProdPoc, ElementType.Xpath);
				flag = webDB.waitForElement(SubcontractOrderLocators.SearchButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Go Button Is Displayed");
					webDB.clickAnElement(SubcontractOrderLocators.SearchButton, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(12000);
					flag = webDB.waitForElement(SubcontractOrderLocators.CheckBox(DocNo), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SubcontractOrderLocators.CheckBox(DocNo), ElementType.Xpath);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(SubcontractOrderLocators.Pending_CreateSCO_Button,
								ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(2000);
							logs.logging("INFO", "Create SubContract Order Doc Is Displayed");
							webDB.clickAnElement(SubcontractOrderLocators.Pending_CreateSCO_Button, ElementType.Xpath);

							flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractorLovButton,
									ElementType.Xpath);
							if (flag) {
								commonFunc.searchAndSelectValueInFilters(
										SubcontractOrderLocators.SubcontractorLovButton,
										commonFunc.getDataFromTestDataSheet("SubcontractorName"));
								webDB.switchToFrame(SubcontractOrderLocators.FrameProdPoc, ElementType.Xpath);
								flag = webDB.waitForElement(SubcontractOrderLocators.OkButton, ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(2000);
									logs.logging("INFO", "Ok Button Is Displayed");
									webDB.clickAnElement(SubcontractOrderLocators.OkButton, ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(SubcontractOrderLocators.LocationId, ElementType.Xpath);
									storeSCODocumentsNumber();
									if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
										webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath);
									}
								}
							}
//							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store SCO Documents Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storeSCODocumentsNumber() {
		String Number = webDB.getTextFromElement(SubcontractOrderLocators.ScoNumber, ElementType.Xpath);
		VariableHandler.setScoNumber(Number);
	}

	/**
	 * This Method Is Used To Verify Header Validation
	 * 
	 * @author Deepan-GWL
	 * @param Location
	 * @param Unit
	 * @param Supplier
	 */
	public void verifyHeaderValidation(String Location, String Unit, String Supplier) {
		purchaseOrderFunc.verifyGetTextValidation(SubcontractOrderLocators.LocationId, Location);
		purchaseOrderFunc.verifyGetTextValidation(SubcontractOrderLocators.UnitId, Unit);
		purchaseOrderFunc.verifyGetTextValidation(SubcontractOrderLocators.SupplierName, Supplier);
	}

	/**
	 * This Method Is Used To Verify MRP Detail Verification
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Uom
	 * @param Process
	 */
	public void verifyMRPDetailsVerification(String Item, String Uom, String Process) {
		flag = webDB.waitForElement(SubcontractOrderLocators.MRPButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "MRP Button Is Displayed");
			webDB.clickAnElement(SubcontractOrderLocators.MRPButton, ElementType.Xpath);
			flag = webDB.waitForElement(SubcontractOrderLocators.FrameMaterial, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Frame Material Is Displayed");
				webDB.switchToFrame(SubcontractOrderLocators.FrameMaterial, ElementType.Xpath);
//				purchaseOrderFunc.verifyGetTextValidation(SubcontractOrderLocators.Item, Item);
				purchaseOrderFunc.verifyGetTextValidation(SubcontractOrderLocators.Uom, Uom);
				purchaseOrderFunc.verifyGetTextValidation(SubcontractOrderLocators.TarProcess, Process);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SubcontractOrderLocators.CloseButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.CloseButton, ElementType.Xpath);
					flag = webDB.waitForElement(SubcontractOrderLocators.LocationId, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Verified MRP Screen Is Closed");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Details Validations
	 * 
	 * @author Deepan-GWL
	 * @param Source
	 * @param Target
	 * @param Process
	 */
	public void verifyDetailsValidation(String Source, String Target, String Process) {
		webDB.moveToElement(SubcontractOrderLocators.MRPButton, ElementType.Xpath);
		webDB.moveToElement(SubcontractOrderLocators.LineDetails, ElementType.Xpath);
		flag = webDB.waitForElement(SubcontractOrderLocators.LineDetails, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line Details Is Displayed");
			webDB.clickAnElement(SubcontractOrderLocators.LineDetails, ElementType.Xpath);
			flag = webDB.waitForElement(SubcontractOrderLocators.FrameDetails, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(SubcontractOrderLocators.FrameDetails, ElementType.Xpath);
				purchaseOrderFunc.verifyDomPropertyValidation(SubcontractOrderLocators.SourceSFCode, Source);
				purchaseOrderFunc.verifyGetTextValidation(SubcontractOrderLocators.TargetSFCode, Target);
				purchaseOrderFunc.verifyGetTextValidation(SubcontractOrderLocators.TarProcessDetails, Process);
//				purchaseOrderFunc.verifyGetTextValidation(SubcontractOrderLocators.UnitCost, Unitcost);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SubcontractOrderLocators.CloseDetails, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.CloseDetails, ElementType.Xpath);
					flag = webDB.waitForElement(SubcontractOrderLocators.LocationId, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Verified Line Details Is Closed");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Process Quantity Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyProcessQuantityCalculation() {
		flag = webDB.waitForElement(SubcontractOrderLocators.OrderedQuantity, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(2000);
			String orderedQty = webDB.getTextFromElement(SubcontractOrderLocators.OrderedQuantity, ElementType.Xpath);
			String convFactor = webDB.getTextFromElement(SubcontractOrderLocators.ConvFactor, ElementType.Xpath);
//			String LineUnitCost = webDB.getTextFromElement(SubcontractOrderLocators.LineUnitCost, ElementType.Xpath);
			String BatchQty = webDB.getTextFromElement(SubcontractOrderLocators.BatchQuantity, ElementType.Xpath);
			logs.logging("INFO", "Ordered Quantity Is: " + orderedQty);
			logs.logging("INFO", "Conversion Factor Is: " + convFactor);
			logs.logging("INFO", "Batch Qunatity Is: " + BatchQty);
			BigDecimal orderedQtyD = new BigDecimal(orderedQty);
			BigDecimal convFactorD = new BigDecimal(convFactor);
			BigDecimal BatchQtyD = new BigDecimal(BatchQty);
			BigDecimal ProcessQtyD = (orderedQtyD.multiply(convFactorD)).divide(BatchQtyD, 3, RoundingMode.HALF_UP);
			BigDecimal ProcessQty = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(ProcessQtyD, 3);
			logs.logging("INFO", "Calculated Process Quantity Is: " + ProcessQty);
			String ProcessQuantityText = webDB.getTextFromElement(SubcontractOrderLocators.ProcessQuantity,
					ElementType.Xpath);
			logs.logging("INFO", "Get Text Process Quantity Is: " + ProcessQuantityText);
			flag = ProcessQty.stripTrailingZeros().toString().equals(ProcessQuantityText);
			if (flag) {
				String BatchCost = webDB.getTextFromElement(SubcontractOrderLocators.BatchCost, ElementType.Xpath);
				logs.logging("INFO", "Batch Cost Is: " + BatchCost);
				BigDecimal BatchCostD = new BigDecimal(BatchCost);
				BigDecimal Assemblevalue = BatchCostD.multiply(ProcessQty);
				Assemblevalue = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(Assemblevalue);
				logs.logging("INFO", "Calculated Assemble Value Is: " + Assemblevalue.toPlainString());
				String AssValue = webDB.getTextFromElement(SubcontractOrderLocators.AssembleValue, ElementType.Xpath);
				logs.logging("INFO", "Get Text Assemble Value Is: " + AssValue);
				flag = AssValue.equals(Assemblevalue.stripTrailingZeros().toPlainString());
				if (flag) {
					logs.logging("INFO", "Verified Assemble Calculated With Get Text");
//					BigDecimal LineUnitCostD = ProcessQty
//							.multiply(BatchCostD.divide(orderedQtyD, 5, RoundingMode.HALF_UP));
//					flag = LineUnitCostD.toString().contains(LineUnitCost);
					String TaxPercentageValue = webDB.getTextFromElement(SubcontractOrderLocators.TaxPercentage,
							ElementType.Xpath);
					logs.logging("INFO", "Get Text Tax Percentage Is: " + TaxPercentageValue);
					BigDecimal TaxValue = Assemblevalue
							.multiply(new BigDecimal(TaxPercentageValue).divide(new BigDecimal(100)));
					BigDecimal TaxValueBD = purchaseOrderFunc
							.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TaxValue);
					logs.logging("INFO", "Calculated Tax Value Is: " + TaxValueBD.stripTrailingZeros().toPlainString());
					BigDecimal Individualvalue = TaxValueBD.stripTrailingZeros().divide(new BigDecimal(2));
					String CgstValue = webDB.getTextFromElement(SubcontractOrderLocators.CgstValue, ElementType.Xpath);
					flag = Individualvalue.stripTrailingZeros().toPlainString().equals(CgstValue);
					System.out.println(TaxValueBD.stripTrailingZeros().toPlainString());
					logs.logging("INFO", "Verified Tax Value Actual And Expected");
				}

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Add Gl Account Number Value
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddGlAccNumberValue() {
		webDB.moveToElement(SubcontractOrderLocators.BatchQuantity, ElementType.Xpath);
		webDB.moveToElement(SubcontractOrderLocators.GLAccNo, ElementType.Xpath);
		flag = webDB.waitForElement(SubcontractOrderLocators.GLAccNo, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Gl Account Number Is Displayed");
			webDB.doubleClickAnElement(SubcontractOrderLocators.GLAccNo, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.GLAccNoLovButton,
					commonFunc.getDataFromTestDataSheet("GeneralExpense"));
			commonFunc.hardWait(5000);
			flag = webDB.waitForElement(SubcontractOrderLocators.LineSave, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.LineSave, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Include Charges And Post Icon
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyIncludeChargesAndPostIcon() {
		flag = webDB.waitForElement(SubcontractOrderLocators.IncludeCharges, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Include Charges Is Displayed");
			webDB.clickAnElement(SubcontractOrderLocators.IncludeCharges, ElementType.Xpath);
			commonFunc.hardWait(1500);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForElement(SubcontractOrderLocators.Save, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.Save, ElementType.Xpath);
				commonFunc.hardWait(2000);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(SubcontractOrderLocators.HeaderPost, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(1000);
					logs.logging("INFO", "Save Button Is Clicked");
					webDB.clickAnElement(SubcontractOrderLocators.HeaderPost, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					logs.logging("INFO", "Post Button Is Clicked");

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create Sub Contract Order
	 * 
	 * @author Deepan-GWL
	 */
	public void createSubContractOrder() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder("PRC00/01322/24-25");
		verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractorName"));
		verifyMRPDetailsVerification(commonFunc.getDataFromTestDataSheet("ScoItem"),
				commonFunc.getDataFromTestDataSheet("UomNos"), commonFunc.getDataFromTestDataSheet("Washing"));
		verifyDetailsValidation(commonFunc.getDataFromTestDataSheet("Source"),
				commonFunc.getDataFromTestDataSheet("Target"), commonFunc.getDataFromTestDataSheet("Washing"));
		verifyProcessQuantityCalculation();
		verifyAddGlAccNumberValue();
		verifyIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getScoNumber());
			verifyApproveWithDocNumber(VariableHandler.getScoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToSubcontractOrder();
		}
		verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
	}

	/**
	 * This Method Is Used To Verify Extract MR Number
	 * 
	 * @author Deepan-GWL
	 * @param text
	 * @return
	 */
	public String verifyExtractMRNumber(String text) {
		String DocumentNumber = null;
		String regex = "(MR\\d+/\\d+/\\d{2}-\\d{2})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			DocumentNumber = matcher.group(1);
			logs.logging("INFO", "Extracted MR Number Is " + DocumentNumber);
			System.out.println("Extracted Document Number: " + DocumentNumber);
		} else {
			System.out.println("No document number found.");
		}
		return DocumentNumber;
	}

	/**
	 * Verify Approve Doc Number
	 * 
	 * @author Deepan-GWL
	 * @param Number
	 */
	public void verifyApproveWithDocNumber(String Number) {
		int i = 1;
		logs.logging("INFO", "Trying to approval times.. " + i);
		a: do {
			approvalPageFunc.clickCheckBoxForDocument(Number);
			approvalPageFunc.clickApproveRadioBtn(Number);
			flag = webDB.waitForElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Approval Process Button Is Displayed");
				webDB.clickAnElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
				flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 60);
				if (flag) {
					String text = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					String MRNumber = verifyExtractMRNumber(text);
					VariableHandler.setMRNumber(MRNumber);
					logs.logging("INFO", "Toast Message Displayed, Posted Successfully");
					break a;
				} else {
					webDB.navigateToRefresh();
					approvalPageFunc.filterDocumentsforApproval(Number);
					logs.logging("INFO", "Toast Message Not Displayed... Retrying.. ");
				}
			}
			i++;
		} while ((i <= 3));
		assertTrue(flag);
	}

	/**
	 * Verify Validate Inspection
	 * 
	 * @author Deepan-GWL
	 * @param MrvNo
	 * @param ExpectedStatus
	 */
	public void verifyValidateSubContractDocumentsStatus(String Number, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.ScoNo, Number);
		flag = webDB.waitForElement(SubcontractOrderLocators.ScoSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.ScoSearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SubcontractOrderLocators.ApprovedStatus(Number), ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(SubcontractOrderLocators.ApprovedStatus(Number),
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
	 * This Method Is Used To Add HSN Code
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddHsnCode() {
		flag = webDB.waitForElement(SubcontractOrderLocators.HsnTable, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(SubcontractOrderLocators.HsnTable, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.HsnLovButton,
					commonFunc.getDataFromTestDataSheet("HSN2022-5%"));
			webDB.waitForElement(SubcontractOrderLocators.TickButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.TickButton, ElementType.Xpath);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Queue Quantity For Subcontract
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyQueueQuantityForSubcontract(String ExpectedProcessName) {
		flag = webDB.waitForElement(SubcontractOrderLocators.QueueQty, ElementType.Xpath);
		if (flag) {
			String ProcessName = webDB.getTextFromElement(SubcontractOrderLocators.ProcessName, ElementType.Xpath);
			logs.logging("INFO", "Process Name Is: " + ProcessName);
			flag = ProcessName.equals(ExpectedProcessName);
			if (flag) {
				String QueueQty = webDB.getTextFromElement(SubcontractOrderLocators.QueueQty, ElementType.Xpath);
				logs.logging("INFO", "Queue Quantity Is: " + QueueQty);
				String CompQty = webDB.getDomPropertyFromElement(SubcontractOrderLocators.CompQty, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Completion Quantity Is: " + CompQty);
				flag = QueueQty.equals(CompQty);
				if (flag) {
					logs.logging("INFO", "Verified Queue Quantity And Completion Quantity");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Queue Quantity For Subcontract
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyQueueQuantityForSubcontractAfterOutside(String ExpectedProcessName) {
		flag = webDB.waitForElement(SubcontractOrderLocators.QueueQty, ElementType.Xpath);
		if (flag) {
			String ProcessName = webDB.getTextFromElement(SubcontractOrderLocators.ProcessName, ElementType.Xpath);
			logs.logging("INFO", "Process Name Is: " + ProcessName);
			flag = ProcessName.equals(ExpectedProcessName);
			if (flag) {
				String QueueQty = webDB.getTextFromElement(SubcontractOrderLocators.QueueQty, ElementType.Xpath);
				logs.logging("INFO", "Queue Quantity Is: " + QueueQty);
				String CompQty = webDB.getDomPropertyFromElement(SubcontractOrderLocators.CompQty, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				String OutsideQty = webDB.getTextFromElement(SubcontractOrderLocators.OutsideQty, ElementType.Xpath);
				flag = OutsideQty.equals(commonFunc.getDataFromTestDataSheet("ZeroValue"));
				if (!flag) {
					logs.logging("INFO", "Outside Qty Is Greater Than Zero");
					logs.logging("INFO", "Completion Quantity Is: " + CompQty);
					flag = QueueQty.equals(CompQty);
					if (flag) {
						logs.logging("INFO", "Verified Queue Quantity And Outside Quantity");
						flag = CompQty.equals(commonFunc.getDataFromTestDataSheet("ZeroValue"));
						if (flag) {
							logs.logging("INFO", "Verified Comp Quantity Is Zero");
						}
					}
				} else {
					flag = false;
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Search Pending Subcontract Order
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifySearchPendingSubcontractOrder(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingSubcontractOrder, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Subcontract Order Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingSubcontractOrder, ElementType.Xpath);
			flag = webDB.waitForElement(SubcontractOrderLocators.FrameProdPoc, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Frame Is Displayed");
				webDB.switchToFrame(SubcontractOrderLocators.FrameProdPoc, ElementType.Xpath);
				commonFunc.hardWait(3000);
				commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.ProdOrderNo, DocNo);
				webDB.switchToFrame(SubcontractOrderLocators.FrameProdPoc, ElementType.Xpath);
				flag = webDB.waitForElement(SubcontractOrderLocators.SearchButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Go Button Is Displayed");
					webDB.clickAnElement(SubcontractOrderLocators.SearchButton, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(12000);
					flag = webDB.waitForElement(SubcontractOrderLocators.CheckBox(DocNo), ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method Is Used To Verify Select Check
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyselectCheckBoxForPendingSubcontractOrder(String DocNo) {
		flag = webDB.waitForElement(SubcontractOrderLocators.CheckBox(DocNo), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.CheckBox(DocNo), ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				logs.logging("INFO", "Create SubContract Order Doc Is Displayed");
				webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);

				flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractorLovButton, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractorLovButton,
							commonFunc.getDataFromTestDataSheet("SubcontractorName"));
					webDB.switchToFrame(SubcontractOrderLocators.FrameProdPoc, ElementType.Xpath);
					flag = webDB.waitForElement(SubcontractOrderLocators.OkButton, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(2000);
						logs.logging("INFO", "Ok Button Is Displayed");
						webDB.clickAnElement(SubcontractOrderLocators.OkButton, ElementType.Xpath);
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(SubcontractOrderLocators.LocationId, ElementType.Xpath);
						storeSCODocumentsNumber();
						if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to approve document and extract Material Request Number From
	 * API Response
	 *
	 * @author Sakthi-GWL
	 * @param BU
	 * @param DocNo
	 * @param EmpId
	 * @param Unit
	 * @param User
	 */
	public void approveSCODocsAndStoreMRNumber(String BU, String DocNo, String EmpId, String Unit, String User) {
		restApi.userSetsThebaseUrlAs(webDB.getDataFromProperties("ApproveBaseUrl"));
		restApi.setQueryParam("p_bu", BU);
		restApi.setQueryParam("p_doc_no", DocNo);
		restApi.setQueryParam("p_from_empid", EmpId);
		restApi.setQueryParam("p_plnt", Unit);
		restApi.setQueryParam("p_user", User);
		restApi.setQueryParam("p_wfdc_act", "A");
		restApi.userHitsTheRequestAs(methodType.Post, ContentType.ANY);
		String Value = restApi.response.getHeader("p_out_msg");
		logs.logging("INFO", "Displayed Approval Response Header: " + Value);
		logs.logging("INFO", "Displayed Approval Response Status Code: " + restApi.response.getStatusCode());
		flag = Value.contains("Approved");
		if (flag) {
			logs.logging("INFO", "Verified Approval Response Header");
			flag = restApi.response.getStatusCode() == 200;
			if (flag) {
				logs.logging("INFO", "Verified Approval Response Status Code");
			}
		}
		assertTrue(flag);
		Pattern pattern = Pattern.compile("MR\\d+/\\d+/\\d+-\\d+");
		Matcher matcher = pattern.matcher(restApi.response.getHeader("p_out_msg"));
		if (matcher.find()) {
			String documentNumber = matcher.group();
			VariableHandler.setMRNumber(documentNumber);
			logs.logging("INFO", "Extracted MR Number from API Response: " + VariableHandler.getMRNumber());
		} else {
			logs.logging("INFO", "Extracted MR Number from API Response Failed.....");
			Assert.fail("Extracted MR Number from API Response Failed.....");
		}
	}

	/**
	 * This Method Used For Clicking Add Icon For Subcontract Order
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingAddIconForSCO() {
		flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractFindAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.SubcontractFindAddIcon, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.switchToFrame(SubcontractOrderLocators.SubcontractFindIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Subcontract Add Icon Clicked Successfuly");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Subcontract Parameters
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param SubcontractSupplier
	 */
	public void insertingSubcontractParameters(String LocationId, String SubcontractSupplier) {
		commonFunc.hardWait(1000);
		commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractLocId, LocationId);
		webDB.switchToFrame(SubcontractOrderLocators.SubcontractFindIframe, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractSupplierId, SubcontractSupplier);
		webDB.switchToFrame(SubcontractOrderLocators.SubcontractFindIframe, ElementType.Xpath);
		flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractCreateTickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.SubcontractCreateTickIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1000);
			if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
						ElementType.Xpath);
				commonFunc.hardWait(2000);
				String SCOdocno = webDB.getTextFromElement(SubcontractOrderLocators.SubcontractDocNO,
						ElementType.Xpath);
				logs.logging("INFO", "Get SCO DocNo Is: " + SCOdocno);
				VariableHandler.setSCOdocno(SCOdocno);
				commonFunc.hardWait(1000);
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
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {// TODO
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
	 * This Method Used For Adding ItemID, Order Qty, Process Line From ExcelSheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void AddingProcessLineFromExcelSheet(String SheetName) {
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String SCOItemID = SOMultipleline[i][1];
			String SCOQty = SOMultipleline[i][2];
			String SCOProcess = SOMultipleline[i][3];
			if (!SCOItemID.equals("")) {
				clickLineWiseAddIcon(SCOItemID, SCOQty, SCOProcess);
			}
		}
	}

	/**
	 * This Method Used For Clicking LineWise Add Icon
	 * 
	 * @author Vimal - RMITS
	 * @param SCOItemID
	 * @param SCOQty
	 * @param SCOProcess
	 */
	public void clickLineWiseAddIcon(String SCOItemID, String SCOQty, String SCOProcess) {
		flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractlineAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.SubcontractlineAddIcon, ElementType.Xpath);
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractItemId, SCOItemID);
			commonFunc.hardWait(1000);
			verifyAddTableSpecs(SubcontractOrderLocators.SubcontractQtyField,
					SubcontractOrderLocators.SubcontractQtyInput, SCOQty);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractLineSaveIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.SubcontractLineSaveIcon, ElementType.Xpath);
				commonFunc.hardWait(11000);
				flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractProcessAddIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.SubcontractProcessAddIcon, ElementType.Xpath);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractProcessId, SCOProcess);
					commonFunc.hardWait(1000);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractProcessSaveIcon, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SubcontractOrderLocators.SubcontractProcessSaveIcon, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1000);
						if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
								ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
									ElementType.Xpath);
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractGenMRPBtn,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Process Added Successfuly");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking LineWise Add Icon
	 * 
	 * @author Vimal - RMITS
	 * @param SCOProcess
	 */
	public void addingSubcontractOrderProcess(String SCOProcess) {
		flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractProcessAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.SubcontractProcessAddIcon, ElementType.Xpath);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractProcessId, SCOProcess);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractProcessSaveIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.SubcontractProcessSaveIcon, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
						ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractGenMRPBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Process Added Successfuly");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Gen. MRP Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingGenMRPbutton() {
		flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractGenMRPBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.SubcontractGenMRPBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1000);
			if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
						ElementType.Xpath);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractInclCostButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Gen. MRP Button Clicked Successfuly");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking SCO Include Cost Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingSCOIncludeCostbutton() {
		flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractInclCostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.SubcontractInclCostButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1000);
			if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
						ElementType.Xpath);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractPostButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Include Cost Button Clicked Successfuly");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Clicking MRP consumption Tab and Close
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	public void clickingMRPconsumptionTab() {
		flag = webDB.waitForElement(SubcontractOrderLocators.MRPButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "MRP Button Is Displayed");
			webDB.clickAnElement(SubcontractOrderLocators.MRPButton, ElementType.Xpath);
			flag = webDB.waitForElement(SubcontractOrderLocators.CloseButton, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				webDB.clickAnElement(SubcontractOrderLocators.CloseButton, ElementType.Xpath);
				flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractInclCostButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Clicking MRP Consumption Button And Closed");
				}
				commonFunc.hardWait(1000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Create Direct Subcontract Order With BOM For Standard
	 * Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createDirectSubcontractOrderWithBOMForStandardType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"));
		AddingProcessLineFromExcelSheet(webDB.getDataFromProperties("DirectSCO"));
		clickingGenMRPbutton();
		clickingSCOIncludeCostbutton();
		return flag;

	}

	/**
	 * This Method used to fill form details in Production Order Creation
	 * 
	 * @author Sakthi-GWL
	 * @param Loc
	 * @param OrdType
	 * @param Pfx
	 * @param BOMType
	 * @param Item
	 * @param OrdQnty
	 */
	public void createProdOrderAndProductionOrderParameters(String Loc, String OrdType, String Pfx, String BOMType,
			String BomForItem, String BomQty) {
		ProductionordertFunc.switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_LocationLovbtn, Loc);
		logs.logging("INFO", "Location Selected in Prod Order: " + Loc);
		ProductionordertFunc.switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_OrderTypeLovbtn, OrdType);
		logs.logging("INFO", "Order Type Selected in Prod Order: " + OrdType);
		ProductionordertFunc.switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_PrefixLovbtn, Pfx);
		logs.logging("INFO", "Prefix Selected in Prod Order: " + Pfx);
		ProductionordertFunc.switchToCreateProductionOrderFrame();
		flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, BOMType,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "BOM Type Selected in Prod Order: " + BOMType);
				commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_ItemLovbtn, BomForItem);
				logs.logging("INFO", "Item Selected in Prod Order: " + BomForItem);
				ProductionordertFunc.switchToCreateProductionOrderFrame();
				commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.CreateProdOrd_OrderQntyInput,
						ProductionOrderLocators.CreateProdOrd_OrderQntyInput, BomQty);
				logs.logging("INFO", "Order Quantity Entered in Prod Order: " + BomQty);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Clicking Include Charges And Post Icon
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	public void clickingIncludeChargesAndPostIcon() {
		flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractInclCostButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Include Charges Is Displayed");
			webDB.clickAnElement(SubcontractOrderLocators.SubcontractInclCostButton, ElementType.Xpath);
			commonFunc.hardWait(1500);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForElement(SubcontractOrderLocators.Save, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.Save, ElementType.Xpath);
				commonFunc.hardWait(2000);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractPostButton, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(1000);
					logs.logging("INFO", "Save Button Is Clicked");
					webDB.clickAnElement(SubcontractOrderLocators.SubcontractPostButton, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					logs.logging("INFO", "Post Button Is Clicked");

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Create Production Order From Subcontract order To GRN PR
	 * Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createProductionOrderFromSubcontractorderToGRNTypePR() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomForItem"), commonFunc.getDataFromTestDataSheet("BomQty"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToSubcontractOrder();
			verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
			clickingMRPconsumptionTab();
			clickingIncludeChargesAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalPageFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalPageFunc.navigateToApprovals();
				approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
				verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialRequestScreen();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc
						.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
//				grnScoPageFunc.checkConsumptionAfterGenerationMat();
//				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			}
		}
		return flag;
	}

	/**
	 * This Method Used For Create Production Order From Subcontract order To GRN
	 * Unprocess Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createProductionOrderFromSubcontractorderToGRNUnprocessType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomForItem"), commonFunc.getDataFromTestDataSheet("BomQty"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToSubcontractOrder();
			verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
			clickingMRPconsumptionTab();
			clickingIncludeChargesAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalPageFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalPageFunc.navigateToApprovals();
				approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
				verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialRequestScreen();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc
						.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDocForUPType(VariableHandler.getSCOdocno(),
						commonFunc.getDataFromTestDataSheet("QtyApplyFor0"));
//				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
//				grnScoPageFunc.checkConsumptionAfterGenerationMat();
//				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			}
		}
		return flag;
	}

	/**
	 * This Method Used For Create Production Order From Subcontract order To GRN
	 * Unprocess Standard Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createProductionOrderFromSubcontractorderToGRNUnprocessStandardType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomForItem"), commonFunc.getDataFromTestDataSheet("BomQty"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDocForUSType(VariableHandler.getSCOdocno(),
					commonFunc.getDataFromTestDataSheet("QtyApplyFor0"));
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToSubcontractOrder();
			verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
			clickingMRPconsumptionTab();
			clickingIncludeChargesAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalPageFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalPageFunc.navigateToApprovals();
				approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
				verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialRequestScreen();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc
						.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//				grnScoPageFu`nc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
//				grnScoPageFunc.checkConsumptionAfterGenerationMat();
//				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			}
		}
		return flag;
	}
	/**
	 * This Method Used For Inserting Subcontract Parameters
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param SubcontractSupplier
	 */
	public void insertingSubcontractParametersForValueAdditionType(String LocationId, String SubcontractSupplier,
			String SCOVoutype, String ValueAdditionPFX) {
		commonFunc.hardWait(1000);
		commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractLocId, LocationId);
		webDB.switchToFrame(SubcontractOrderLocators.SubcontractFindIframe, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SCO_Vou_Type_lov, SCOVoutype);
		webDB.switchToFrame(SubcontractOrderLocators.SubcontractFindIframe, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.ValueAdditionPfx, ValueAdditionPFX);
		webDB.switchToFrame(SubcontractOrderLocators.SubcontractFindIframe, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractSupplierId, SubcontractSupplier);
		webDB.switchToFrame(SubcontractOrderLocators.SubcontractFindIframe, ElementType.Xpath);
		flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractCreateTickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.SubcontractCreateTickIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1000);
			if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
						ElementType.Xpath);
				commonFunc.hardWait(2000);
				String SCOdocno = webDB.getTextFromElement(SubcontractOrderLocators.SubcontractDocNO,
						ElementType.Xpath);
				logs.logging("INFO", "Get SCO DocNo Is: " + SCOdocno);
				VariableHandler.setSCOdocno(SCOdocno);
				commonFunc.hardWait(1000);
			}
		}
		assertTrue(flag);
	}
	/**
	 * This Method Used For Create Direct Subcontract Order For Value Addition Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createDirectSubcontractOrderForValueAdditionType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParametersForValueAdditionType(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"),
				commonFunc.getDataFromTestDataSheet("SCOVoutype"),
				commonFunc.getDataFromTestDataSheet("ValueAdditionPFX"));
		AddingProcessLineFromExcelSheet(webDB.getDataFromProperties("DirectSCO"));
		clickingGenMRPbutton();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.clickingGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			return flag;
		}
		return flag;
	}

	/**
	 * This Method Used for Clicking Header Cancel Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingHeaderCancelButton() {
		flag = webDB.waitForElement(SubcontractOrderLocators.SCO_Cancel_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.SCO_Cancel_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractFindAddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Document Cancelled Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Document Cancelling For Direct Subcontract Order For
	 * Value Addition Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean documentCancellingForDirectSubcontractOrderValueAdditionType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParametersForValueAdditionType(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"),
				commonFunc.getDataFromTestDataSheet("SCOVoutype"),
				commonFunc.getDataFromTestDataSheet("ValueAdditionPFX"));
		AddingProcessLineFromExcelSheet(webDB.getDataFromProperties("DirectSCO"));
		clickingHeaderCancelButton();
		return flag;
	}

	/**
	 * This Method Used For Document Cancelling For Direct Subcontract Order For
	 * Standard Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean documentCancellingForDirectSubcontractOrderStandardType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"));
		AddingProcessLineFromExcelSheet(webDB.getDataFromProperties("DirectSCO"));
		clickingHeaderCancelButton();
		return flag;
	}

	/**
	 * This Method Used For Create Direct Subcontract Order With BOM For Standard
	 * Type In Conversion Factor
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createDirectSubcontractOrderWithBOMForStandardTypeInConversionFactor() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"));
		AddingProcessLineFromExcelSheet(webDB.getDataFromProperties("DirectSCOWithConv"));
		clickingGenMRPbutton();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			return flag;

		}
		return flag;
	}

	/**
	 * This Method Used For "Create Subcontract Order From Pending With Conversion
	 * Factor in GRN PR Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSubcontractOrderFromPendingWithConversionFactorinGRN_PRType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BOMITEMWITHCONVFACTOR"),
				commonFunc.getDataFromTestDataSheet("BomQty"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToSubcontractOrder();
			verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
			clickingMRPconsumptionTab();
			clickingIncludeChargesAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalPageFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalPageFunc.navigateToApprovals();
				approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
				verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialRequestScreen();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc
						.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
//				grnScoPageFunc.checkConsumptionAfterGenerationMat();
//				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			}
		}
		return flag;
	}
	/**
	 * This Method Used For Clicking LineWise Add Icon And Adding Sales Order Ref
	 * 
	 * @author Vimal - RMITS
	 * @param SCOItemID
	 * @param SCOQty
	 * @param SCOProcess
	 */
	public void clickLineWiseAddIconAndAddingSO(String SCOItemID, String SCOQty, String SCOProcess,
			String ProcessGLacct, String SORef) {
		flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractlineAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.SubcontractlineAddIcon, ElementType.Xpath);
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractItemId, SCOItemID);
			commonFunc.hardWait(1000);
			verifyAddTableSpecs(SubcontractOrderLocators.SubcontractQtyField,
					SubcontractOrderLocators.SubcontractQtyInput, SCOQty);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractLineSaveIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.SubcontractLineSaveIcon, ElementType.Xpath);
				commonFunc.hardWait(11000);
				flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractProcessAddIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.SubcontractProcessAddIcon, ElementType.Xpath);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.SubcontractProcessId, SCOProcess);
					commonFunc.hardWait(1000);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(SubcontractOrderLocators.GLacctField, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SubcontractOrderLocators.GLacctField, ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.ProcessGLacct_lov,
								ProcessGLacct);
						flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractProcessSaveIcon,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SubcontractOrderLocators.SubcontractProcessSaveIcon,
									ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
									ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
										ElementType.Xpath);
								commonFunc.hardWait(1000);
								flag = webDB.waitForElement(SubcontractOrderLocators.SubcontractGenMRPBtn,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Process Added Successfuly");
									flag = webDB.waitForElement(SubcontractOrderLocators.LineDetails,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Line Details Is Displayed");
										webDB.clickAnElement(SubcontractOrderLocators.LineDetails, ElementType.Xpath);
										flag = webDB.waitForElement(SubcontractOrderLocators.FrameDetails,
												ElementType.Xpath);
										if (flag) {
											webDB.switchToFrame(SubcontractOrderLocators.FrameDetails,
													ElementType.Xpath);
											flag = webDB.waitForElement(SubcontractOrderLocators.SCO_SO_Ref_Lov,
													ElementType.Xpath);
											if (flag) {
												commonFunc.searchAndSelectValueInFilters(
														SubcontractOrderLocators.SCO_SO_Ref_Lov, SORef);
												webDB.switchToFrame(SubcontractOrderLocators.FrameDetails,
														ElementType.Xpath);
												commonFunc.hardWait(1000);
												webDB.clickAnElement(SubcontractOrderLocators.SCO_LineDet_Save_Icon,
														ElementType.Xpath);
												commonFunc.hardWait(2000);
												webDB.switchToDefaultContent();
												flag = webDB.waitForElement(SubcontractOrderLocators.CloseDetails,
														ElementType.Xpath);
												if (flag) {
													webDB.clickAnElement(SubcontractOrderLocators.CloseDetails,
															ElementType.Xpath);
													flag = webDB.waitForElement(SubcontractOrderLocators.LocationId,
															ElementType.Xpath);
													if (flag) {
														logs.logging("INFO", "Verified Line Details Is Closed");
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
			}
		}
		assertTrue(flag);
	}
	/**
	 * This Method Used For Adding ItemID, Order Qty, Process Line and Sales Order
	 * Ref From ExcelSheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void AddingProcessLineAndSaleOrderRefFromExcelSheet(String SheetName) {
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String SCOItemID = SOMultipleline[i][1];
			String SCOQty = SOMultipleline[i][2];
			String SCOProcess = SOMultipleline[i][3];
			String ProcessGLacct = SOMultipleline[i][4];
			String SORef = SOMultipleline[i][5];
			if (!SCOItemID.equals("")) {
				clickLineWiseAddIconAndAddingSO(SCOItemID, SCOQty, SCOProcess, ProcessGLacct, SORef);
			}
		}
	}
	/**
	 * This Method Used For Create Direct Subcontract Order With BOM For Standard
	 * Type In Sales Order Ref
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean directSubcontractOrderWithBOMForStandardTypeInSalesOrderRef() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"));
		AddingProcessLineAndSaleOrderRefFromExcelSheet(webDB.getDataFromProperties("DirectSCOWithSO"));
		clickingGenMRPbutton();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			return flag;
		}
		return flag;
	}
	/**
	 * This Method used to fill form details in Production Order Creation
	 * 
	 * @author Vimal - RMITS
	 * @param Loc
	 * @param OrdType
	 * @param Pfx
	 * @param BOMType
	 * @param Item
	 * @param OrdQnty
	 */
	public void createProdOrderAndProductionOrderWithSORefParameters(String Loc, String OrdType, String Pfx,
			String BOMType, String BomForItem, String BomQty, String BOMSOREFWITHCONV) {
		ProductionordertFunc.switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_LocationLovbtn, Loc);
		logs.logging("INFO", "Location Selected in Prod Order: " + Loc);
		ProductionordertFunc.switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_OrderTypeLovbtn, OrdType);
		logs.logging("INFO", "Order Type Selected in Prod Order: " + OrdType);
		ProductionordertFunc.switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_PrefixLovbtn, Pfx);
		logs.logging("INFO", "Prefix Selected in Prod Order: " + Pfx);
		ProductionordertFunc.switchToCreateProductionOrderFrame();
		flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, BOMType,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "BOM Type Selected in Prod Order: " + BOMType);
				commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_ItemLovbtn, BomForItem);
				logs.logging("INFO", "Item Selected in Prod Order: " + BomForItem);
				ProductionordertFunc.switchToCreateProductionOrderFrame();
				commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.CreateProdOrd_OrderQntyInput,
						ProductionOrderLocators.CreateProdOrd_OrderQntyInput, BomQty);
				logs.logging("INFO", "Order Quantity Entered in Prod Order: " + BomQty);
				commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.SoDesc, BOMSOREFWITHCONV);
				webDB.switchToDefaultContent();
			}
		}
		assertTrue(flag);
	}
	/**
	 * This Method Used For Create Subcontract Order From Pending With Sales Order
	 * Ref
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSubcontractOrderFromPendingWithSalesOrderRef() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderWithSORefParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BOMITEMWITHSOREF"), commonFunc.getDataFromTestDataSheet("BomQty"),
				commonFunc.getDataFromTestDataSheet("BOMSOREF"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			return flag;

		}
		return flag;
	}

	/**
	 * This Method Used For Create Direct Subcontract Order With BOM For Standard
	 * Type In Sales Order Ref and Conv. Factor
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean directSubcontractOrderWithBOMForStandardTypeInSalesOrderRefandConvFactor() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"));
		AddingProcessLineAndSaleOrderRefFromExcelSheet(webDB.getDataFromProperties("DirectSCOWithSOandConv"));
		clickingGenMRPbutton();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			return flag;
		}
		return flag;
	}

	/**
	 * This Method Used For Create Subcontract Order From Pending With Sales Order
	 * Ref
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSubcontractOrderFromPendingWithSalesOrderRefandConvFactor() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderWithSORefParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BOMITEMWITHSOREFANDCONV"),
				commonFunc.getDataFromTestDataSheet("BomQty"), commonFunc.getDataFromTestDataSheet("BOMSOREFWITHCONV"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToSubcontractOrder();
			verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
			clickingMRPconsumptionTab();
			clickingIncludeChargesAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalPageFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalPageFunc.navigateToApprovals();
				approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
				verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialRequestScreen();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc
						.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
//				grnScoPageFunc.checkConsumptionAfterGenerationMat();
//				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				mrvFunc.verifyGRNSubcontractForMaterialReceipt();
				return flag;
			}
		}
		return flag;
	}

	/**
	 * This Method Used For Create Direct Subcontract Order For Value Addition Type
	 * With Conv Factor
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createDirectSubcontractOrderForValueAdditionTypeWithConvFactor() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParametersForValueAdditionType(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"),
				commonFunc.getDataFromTestDataSheet("SCOVoutype"),
				commonFunc.getDataFromTestDataSheet("ValueAdditionPFX"));
		AddingProcessLineFromExcelSheet(webDB.getDataFromProperties("DirectSCOWithConv"));
		clickingGenMRPbutton();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.clickingGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			return flag;
		}
		return flag;
	}

	/**
	 * This Method Used For Create Direct Subcontract Order For Value Addition Type
	 * With Conv Factor And SO Ref
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createDirectSubcontractOrderForValueAdditionTypeWithConvFactorAndSOref() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParametersForValueAdditionType(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"),
				commonFunc.getDataFromTestDataSheet("SCOVoutype"),
				commonFunc.getDataFromTestDataSheet("ValueAdditionPFX"));
		AddingProcessLineAndSaleOrderRefFromExcelSheet(webDB.getDataFromProperties("DirectSCOWithSOandConv"));
		clickingGenMRPbutton();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.clickingGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			return flag;
		}
		return flag;
	}

	/**
	 * This Method Used For Create Direct Subcontract Order With BOM For Standard
	 * Type With Project
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean directSubcontractOrderWithBOMForStandardTypeWithProject() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		clickingAddIconForSCO();
		insertingSubcontractParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractSupplier"));
		AddingProcessLineAndSaleOrderRefFromExcelSheet(webDB.getDataFromProperties("DirectSCOWithProject"));
		clickingGenMRPbutton();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			return flag;
		}
		return flag;
	}

	/**
	 * This Method Used For Create Subcontract Order From Pending With Project
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createSubcontractOrderFromPendingWithProject() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderWithSORefParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BOMITEMWITHSOREF"), commonFunc.getDataFromTestDataSheet("BomQty"),
				commonFunc.getDataFromTestDataSheet("BOMProject"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			return flag;

		}
		return flag;
	}

	/**
	 * This Method Used For Create Production Order From Subcontract order To GRN
	 * Cut Sample Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createProductionOrderFromSubcontractorderToGRNCutSampleType() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomForItem"), commonFunc.getDataFromTestDataSheet("BomQty"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDocForCSType(VariableHandler.getSCOdocno(),
					commonFunc.getDataFromTestDataSheet("QtyApplyFor0"));
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToSubcontractOrder();
			verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
			clickingMRPconsumptionTab();
			clickingIncludeChargesAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalPageFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalPageFunc.navigateToApprovals();
				approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
				verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialRequestScreen();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc
						.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//				grnScoPageFu`nc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
//				grnScoPageFunc.checkConsumptionAfterGenerationMat();
//				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			}
		}
		return flag;
	}

	/**
	 * This Method Used For Create Production Order From Subcontract order To GRN PR
	 * Type With QC
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createProductionOrderFromSubcontractorderToGRNTypePRWithQC() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomForItem"), commonFunc.getDataFromTestDataSheet("BomQty"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			commonFunc.navigateInspectionDocumentsPage();
			qcFunc.verifyPendingInspectionDocumentForSubcontractGRN(VariableHandler.getScoGrnNumber());
			qcFunc.clickTickAndPostIcon();
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("AshokValue"));
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getIDNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getIDNumber());
				flag = commonFunc.logoutUser();
				mrvFunc.verifyGRNSubcontractForMaterialReceipt();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToSubcontractOrder();
				verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				clickingMRPconsumptionTab();
				clickingIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalPageFunc.subcontractwaitingForApprovalWithFordwarder(
							commonFunc.getDataFromTestDataSheet("HodValue"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
							"HodUsername");
					approvalPageFunc.navigateToApprovals();
					approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
					verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
							"TvsUsername");
					commonFunc.navigateToMaterialRequestScreen();
					materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
					materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
					materialRequestFunc.headerSaveAndPost();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
							"HodUsername-Admin");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
					approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
					flag = commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
							"TvsUsername");
					commonFunc.navigateToMaterialIssuanceVoucher();
					materialIssuanceVoucherFunc
							.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
					materialIssuanceVoucherFunc.headerTickAndPostIcon();
					flag = commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
							"TvsUsername");
					commonFunc.navigateToGrnSco();
					grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
					grnScoPageFunc.verifyGenMatConsAndAllocate();
//				grnScoPageFunc.checkConsumptionAfterGenerationMat();
//				grnScoPageFunc.verifyViewLineDetails();
					grnScoPageFunc.saveHeaderAndPost();
					approvalFunc.subcontractwaitingForApprovalWithFordwarder(
							commonFunc.getDataFromTestDataSheet("HodValue"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
							"HodUsername-Admin");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
					approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
					flag = commonFunc.logoutUser();
					mrvFunc.verifyGRNSubcontractForMaterialReceipt();
				}
			}
			return flag;
		}
		return flag;
	}

	/**
	 * This Method Used For Create Production Order From Subcontract order To GRN
	 * Unprocess Type With QC
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createProductionOrderFromSubcontractorderToGRNUnprocessTypeWithQC() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomForItem"), commonFunc.getDataFromTestDataSheet("BomQty"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			mrvFunc.verifyGRNSubcontractForMaterialReceipt();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToSubcontractOrder();
			verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
			clickingMRPconsumptionTab();
			clickingIncludeChargesAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalPageFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalPageFunc.navigateToApprovals();
				approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
				verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialRequestScreen();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc
						.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDocForUPType(VariableHandler.getSCOdocno(),
						commonFunc.getDataFromTestDataSheet("QtyApplyFor0"));
//				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
//				grnScoPageFunc.checkConsumptionAfterGenerationMat();
//				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc
						.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername-Admin");
				commonFunc.navigateInspectionDocumentsPage();
				qcFunc.verifyPendingInspectionDocumentForSubcontractGRN(VariableHandler.getScoGrnNumber());
				qcFunc.clickTickAndPostIcon();
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("AshokValue"));
					flag = commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AshokUsername"),
							System.getProperty("AshokPassword"));
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getIDNumber());
					approvalFunc.verifyApproveWithDocNumber(VariableHandler.getIDNumber());
					flag = commonFunc.logoutUser();
					mrvFunc.verifyGRNSubcontractForMaterialReceipt();
				}
			}
			return flag;
		}
		return flag;
	}

	/**
	 * This Method Used For Create Production Order From Subcontract order To GRN
	 * Unprocess Standard Type With QC
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createProductionOrderFromSubcontractorderToGRNUnprocessStandardTypeWithQC() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToProductionOrderScreen();
		ProductionordertFunc.clickAddIconInCreationProductionOrder();
		createProdOrderAndProductionOrderParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PRD0Prefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomForItem"), commonFunc.getDataFromTestDataSheet("BomQty"));
		ProductionordertFunc.clickSaveIconInCreationProductionOrder();
		ProductionordertFunc.verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("Orderreleases"),
				commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		ProductionordertFunc.clickHeaderSaveAndPostProdOrder();
		ProductionordertFunc.forwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approvalFunc.navigateToApprovals();
		approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToSubcontractOrder();
		verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		clickingMRPconsumptionTab();
		clickingIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc
					.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalPageFunc.navigateToApprovals();
			approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
			verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialRequestScreen();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
			materialRequestFunc.headerSaveAndPost();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDocForUSType(VariableHandler.getSCOdocno(),
					commonFunc.getDataFromTestDataSheet("QtyApplyFor0"));
//			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
//			grnScoPageFunc.checkConsumptionAfterGenerationMat();
//			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.subcontractwaitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername-Admin");
			commonFunc.navigateInspectionDocumentsPage();
			qcFunc.verifyPendingInspectionDocumentForSubcontractGRN(VariableHandler.getScoGrnNumber());
			qcFunc.clickTickAndPostIcon();
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("AshokValue"));
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getIDNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getIDNumber());
				flag = commonFunc.logoutUser();
				mrvFunc.verifyGRNSubcontractForMaterialReceipt();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToSubcontractOrder();
				verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				clickingMRPconsumptionTab();
				clickingIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalPageFunc.subcontractwaitingForApprovalWithFordwarder(
							commonFunc.getDataFromTestDataSheet("HodValue"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
							"HodUsername");
					approvalPageFunc.navigateToApprovals();
					approvalPageFunc.filterDocumentsforApproval(VariableHandler.getSCOdocno());
					verifyApproveWithDocNumber(VariableHandler.getSCOdocno());
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
							"TvsUsername");
					commonFunc.navigateToMaterialRequestScreen();
					materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
					materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getSCOdocno());
					materialRequestFunc.headerSaveAndPost();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
							"HodUsername-Admin");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
					approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
					flag = commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
							"TvsUsername");
					commonFunc.navigateToMaterialIssuanceVoucher();
					materialIssuanceVoucherFunc
							.CreateThePendingMIVDocumentFromMaterialRequest(VariableHandler.getMRNumber());
					materialIssuanceVoucherFunc.headerTickAndPostIcon();
					flag = commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
							"TvsUsername");
					commonFunc.navigateToGrnSco();
					grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getSCOdocno());
//				grnScoPageFu`nc.checkConsumptionBeforeGenerationMat();
					grnScoPageFunc.verifyGenMatConsAndAllocate();
//				grnScoPageFunc.checkConsumptionAfterGenerationMat();
//				grnScoPageFunc.verifyViewLineDetails();
					grnScoPageFunc.saveHeaderAndPost();
					approvalFunc.subcontractwaitingForApprovalWithFordwarder(
							commonFunc.getDataFromTestDataSheet("HodValue"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
							"HodUsername-Admin");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
					approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
					flag = commonFunc.logoutUser();
					mrvFunc.verifyGRNSubcontractForMaterialReceipt();
				}
			}
			return flag;
		}
		return flag;
	}
}
