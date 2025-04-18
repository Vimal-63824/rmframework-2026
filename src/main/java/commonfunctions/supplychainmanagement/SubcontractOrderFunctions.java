package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.qualitycontrol.InspectionDocumentsLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.SubcontractOrderLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SubcontractOrderFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	public static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();

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
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(SubcontractOrderLocators.CheckBox(DocNo), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SubcontractOrderLocators.CheckBox(DocNo), ElementType.Xpath);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(2000);
							logs.logging("INFO", "Create SubContract Order Doc Is Displayed");
							webDB.clickAnElement(InspectionDocumentsLocators.CreateQCDoc, ElementType.Xpath);

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
		commonFunc.navigateToSubContractOrder();
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
			commonFunc.navigateToSubContractOrder();
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

}
