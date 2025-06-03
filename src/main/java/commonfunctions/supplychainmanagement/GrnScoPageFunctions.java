package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.GrnScoLocators;
import commonlocators.supplychainmanagement.MaterialReceiptVoucherLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class GrnScoPageFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();

	/**
	 * This Method Is Used To Create Pending Grn Sco Doc
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void createPendingGrnScoDoc(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingGrn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingGrn, ElementType.Xpath);
			flag = webDB.waitForElement(GrnScoLocators.PendingSubcontractOrder, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnScoLocators.PendingSubcontractOrder, ElementType.Xpath);
				flag = webDB.waitForClickElement(GrnScoLocators.PendingScoFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(GrnScoLocators.PendingScoFrame, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(GrnScoLocators.SCONoLovButton, DocNo);
					flag = webDB.waitForClickElement(GrnScoLocators.PendingScoFrame, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Pending Sco Frame Is Displayed");
						webDB.switchToFrame(GrnScoLocators.PendingScoFrame, ElementType.Xpath);
						flag = webDB.waitForElement(GrnScoLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GrnScoLocators.SearchButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							webDB.moveToElement(GrnScoLocators.ScoNumber(DocNo), ElementType.Xpath);
							webDB.moveToElement(GrnScoLocators.CheckBox(DocNo), ElementType.Xpath);
							flag = webDB.waitForElement(GrnScoLocators.CheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(2000);
								logs.logging("INFO", "CheckBox Is Displayed");
								webDB.clickAnElement(GrnScoLocators.CheckBox(DocNo), ElementType.Xpath);
								flag = webDB.waitForElement(GrnScoLocators.CreateGrnButton, ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(2000);
									webDB.clickAnElement(GrnScoLocators.CreateGrnButton, ElementType.Xpath);
									flag = webDB.waitForElement(GrnScoLocators.SupplierBillNumber, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Supplier Bill Number Is Displayed");
										webDB.sendTextToAnElement(GrnScoLocators.SupplierBillNumber, DocNo,
												ElementType.Xpath);
										webDB.sendTextToAnElement(GrnScoLocators.DCNumber, DocNo, ElementType.Xpath);
										logs.logging("INFO", "Send Text Dc Number Is: " + DocNo);
										flag = webDB.waitForElement(GrnScoLocators.OkButton, ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(GrnScoLocators.OkButton, ElementType.Xpath);
											webDB.switchToDefaultContent();
											flag = webDB.waitForElement(GrnScoLocators.GrnNumber, ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Grn Number Is Displayed");
												String GrnNumber = webDB.getDomPropertyFromElement(
														GrnScoLocators.GrnNumber, ElementType.Xpath,
														commonFunc.getDataFromTestDataSheet("Value"));
												VariableHandler.setScoGrnNumber(GrnNumber);
												logs.logging("INFO", "Get Grn Number Is: " + GrnNumber);
												if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
														ElementType.Xpath)) {
													webDB.clickAnElementWithoutException(
															CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
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
	 * This Method Is Used To Verify Check Consumption Before Generation Mat
	 * 
	 * @author Deepan-GWL
	 */
	public void checkConsumptionBeforeGenerationMat() {
		webDB.moveToElement(GrnScoLocators.ItemTable, ElementType.Xpath);
		webDB.moveToElement(GrnScoLocators.Consmp, ElementType.Xpath);
		flag = webDB.waitForElement(GrnScoLocators.Consmp, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Consump Button Is Displayed");
			webDB.clickAnElement(GrnScoLocators.Consmp, ElementType.Xpath);
			flag = webDB.waitForClickElement(GrnScoLocators.ConsumptionFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Consumption Frame Is Displayed");
				webDB.switchToFrame(GrnScoLocators.ConsumptionFrame, ElementType.Xpath);
				purchaseOrderFunc.verifyGetTextValidation(GrnScoLocators.BatchDetails,
						commonFunc.getDataFromTestDataSheet("DcDetails"));
				purchaseOrderFunc.verifyGetTextValidation(GrnScoLocators.ConsumptionDetails,
						commonFunc.getDataFromTestDataSheet("NoDataFound"));
				purchaseOrderFunc.verifyGetTextValidation(GrnScoLocators.DCDetails,
						commonFunc.getDataFromTestDataSheet("DcDetails"));
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(GrnScoLocators.ConsumptionCloseButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Consumption Close Button Is Displayed");
					webDB.moveToElement(GrnScoLocators.ConsumptionCloseButton, ElementType.Xpath);
					webDB.clickAnElement(GrnScoLocators.ConsumptionCloseButton, ElementType.Xpath);
					flag = webDB.waitForElement(GrnScoLocators.GrnNumber, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Gen Mat Cons And Allocate
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyGenMatConsAndAllocate() {
		flag = webDB.waitForElement(GrnScoLocators.GenLotSerial, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Gen Lot Serial Button Is Displayed");
			webDB.clickAnElement(GrnScoLocators.GenLotSerial, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForElement(GrnScoLocators.GenMatCons, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Gen Mat Cons Button Is Displayed");
				webDB.clickAnElement(GrnScoLocators.GenMatCons, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(GrnScoLocators.Allocate, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Allocate Button Is Displayed");
					webDB.clickAnElement(GrnScoLocators.Allocate, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Consumption After Generation Mat
	 * 
	 * @author Deepan-GWL
	 */
	public void checkConsumptionAfterGenerationMat() {
		webDB.moveToElement(GrnScoLocators.ItemTable, ElementType.Xpath);
		webDB.moveToElement(GrnScoLocators.Consmp, ElementType.Xpath);
		flag = webDB.waitForElement(GrnScoLocators.Consmp, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Consump Button Is Displayed");
			webDB.clickAnElement(GrnScoLocators.Consmp, ElementType.Xpath);
			flag = webDB.waitForElement(GrnScoLocators.ConsumptionFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Consump Frame Is Displayed");
				webDB.switchToFrame(GrnScoLocators.ConsumptionFrame, ElementType.Xpath);
//				purchaseOrderFunc.verifyGetTextValidation(GrnScoLocators.ConsumptionItem,
//						commonFunc.getDataFromTestDataSheet("RawMaterialSco"));
				String text = webDB.getTextFromElement(GrnScoLocators.AllatedQty, ElementType.Xpath);
				flag = text.equals(commonFunc.getDataFromTestDataSheet("ZeroValue"));
				if (!flag) {
					flag = webDB.waitForElement(GrnScoLocators.DCNumber(1), ElementType.Xpath);
					if (flag) {
						String text1 = webDB.getTextFromElement(GrnScoLocators.DCNumber(1), ElementType.Xpath);
						logs.logging("INFO", "Get Text Value Is: " + text1);
						flag = text1.contains("DC");
						if (flag) {
							webDB.switchToDefaultContent();
							flag = webDB.waitForElement(GrnScoLocators.ConsumptionCloseButton, ElementType.Xpath);
							if (flag) {
								webDB.moveToElement(GrnScoLocators.ConsumptionCloseButton, ElementType.Xpath);
								logs.logging("INFO", "Switched To Default Content Close Button Is Displayed");
								webDB.clickAnElement(GrnScoLocators.ConsumptionCloseButton, ElementType.Xpath);
								flag = webDB.waitForElement(GrnScoLocators.GrnNumber, ElementType.Xpath);
							}
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
	 * This Method Is Used To Verify View Line Details
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyViewLineDetails() {
		webDB.moveToElement(GrnScoLocators.ItemTable, ElementType.Xpath);
		webDB.moveToElement(GrnScoLocators.ViewDetails, ElementType.Xpath);
		flag = webDB.waitForElement(GrnScoLocators.ViewDetails, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "View Details Buttton Is Displayed");
			webDB.clickAnElement(GrnScoLocators.ViewDetails, ElementType.Xpath);
			flag = webDB.waitForElement(GrnScoLocators.FrameLineDetails, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GrnScoLocators.FrameLineDetails, ElementType.Xpath);
				purchaseOrderFunc.verifyDomPropertyValidation(GrnScoLocators.SCONumber, VariableHandler.getScoNumber());
				purchaseOrderFunc.verifyDomPropertyValidation(GrnScoLocators.PONumber,
						VariableHandler.getProductionOrderNumber());
				purchaseOrderFunc.verifyGetTextValidation(GrnScoLocators.QcRequired,
						commonFunc.getDataFromTestDataSheet("FreePass"));
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(GrnScoLocators.CloseLineDetials, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Close Line Detail Button Is Displayed");
					webDB.clickAnElement(GrnScoLocators.CloseLineDetials, ElementType.Xpath);
					flag = webDB.waitForElement(GrnScoLocators.GrnNumber, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Save Header And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void saveHeaderAndPost() {
		flag = webDB.waitForElement(GrnScoLocators.TickButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Tick Button Is Displayed");
			webDB.clickAnElement(GrnScoLocators.TickButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForElement(GrnScoLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Displayed");
				webDB.clickAnElement(GrnScoLocators.PostButton, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (flag) {
					logs.logging("INFO", "Post Button Clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Select Grn Sco Number
	 * 
	 * @author Deepan-GWL
	 */
	public void verifySelectGrnScoNumber() {
		flag = webDB.waitForElement(GrnScoLocators.GRNLovButton, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(GrnScoLocators.GRNLovButton, VariableHandler.getScoGrnNumber());
			flag = webDB.waitForElement(GrnScoLocators.GrnSearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Is Displayed");
				webDB.clickAnElement(GrnScoLocators.GrnSearchButton, ElementType.Xpath);
				flag = webDB.waitForElement(GrnScoLocators.GRNNumberSelect(VariableHandler.getScoGrnNumber()),
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Grn Number Is Clicked");
					webDB.clickAnElement(GrnScoLocators.GRNNumberSelect(VariableHandler.getScoGrnNumber()),
							ElementType.Xpath);
					flag = webDB.waitForElement(GrnScoLocators.GrnNumber, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Validate GRN Status
	 * 
	 * @author Deepan-GWL
	 * @param GrnNo
	 * @param ExpectedStatus
	 */
	public void verifyValidateGrnScoStatus(String GrnNo, String ExpectedStatus) {
		flag = webDB.waitForElement(GrnScoLocators.GRNLovButton, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(GrnScoLocators.GRNLovButton, VariableHandler.getScoGrnNumber());
			flag = webDB.waitForElement(GrnScoLocators.GrnSearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Is Displayed");
				webDB.clickAnElement(GrnScoLocators.GrnSearchButton, ElementType.Xpath);
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GrnScoLocators.GRNNumberStatus(GrnNo), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(GrnScoLocators.GRNNumberStatus(GrnNo), ElementType.Xpath);
					flag = Status.contains(ExpectedStatus);
					if (flag) {
						logs.logging("INFO", "Expected Status: " + ExpectedStatus);
						logs.logging("INFO", "Verified Status ,Displayed Status: " + Status);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add Heat Number And Test Number
	 * 
	 * @author Deepan-GWL
	 */
	public void addHeatNumberAndTestNumber() {
		flag = webDB.waitForElement(GrnScoLocators.SupplierLotNumber, ElementType.Xpath);
		if (flag) {
			String HeatValue = webDB.getTextFromElement(GrnScoLocators.SupplierLotNumber, ElementType.Xpath);
			commonFunc.verifyAddTableSpecs(GrnScoLocators.HeatNumber, GrnScoLocators.HeatNumberInput, HeatValue);
			commonFunc.verifyAddTableSpecs(GrnScoLocators.TestNumber, GrnScoLocators.TestNumberInput, HeatValue);
			webDB.clickAnElement(GrnScoLocators.SaveButton, ElementType.Xpath);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}
}
