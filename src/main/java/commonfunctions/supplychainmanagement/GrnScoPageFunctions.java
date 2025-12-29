package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;

import java.io.File;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.GrnScoLocators;
import commonlocators.supplychainmanagement.MaterialReceiptVoucherLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class GrnScoPageFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	SalesOrderFunctions salesOrderFunctions = new SalesOrderFunctions();
	DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();

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
		verifyGrnSFcodeWithExcelSheet(webDB.getDataFromProperties("GRNSourceSFCode"));
		commonFunc.hardWait(2000);
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
						flag = text1.contains("D");
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
			webDB.moveToElement(GrnScoLocators.HeatNumber, ElementType.Xpath);
			String HeatValue = webDB.getTextFromElement(GrnScoLocators.SupplierLotNumber, ElementType.Xpath);
			commonFunc.verifyAddTableSpecs(GrnScoLocators.HeatNumber, GrnScoLocators.HeatNumberInput, HeatValue);
			commonFunc.verifyAddTableSpecs(GrnScoLocators.TestNumber, GrnScoLocators.TestNumberInput, HeatValue);
			webDB.moveToElement(GrnScoLocators.SaveButton, ElementType.Xpath);
			webDB.clickAnElement(GrnScoLocators.SaveButton, ElementType.Xpath);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
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
	 * This Method Is Used To Create Pending Grn Sco Doc For UP Type
	 * 
	 * @author Vimal - RMITS
	 * @param DocNo
	 */
	public void createPendingGrnScoDocForUPType(String DocNo, String QtyApplyFor0) {
		flag = webDB.waitForElement(CommonUiLocators.GRNSCO_Pending_Icon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.GRNSCO_Pending_Icon, ElementType.Xpath);
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
							commonFunc.hardWait(1000);
							String CurrProcQty = webDB.getDomPropertyFromElement(GrnScoLocators.PR_Type_Field,
									ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
							logs.logging("INFO", "Get  Proc Qty Is: " + CurrProcQty);
							commonFunc.hardWait(1000);
							webDB.moveToElement(GrnScoLocators.PR_Type_Field, ElementType.Xpath);
							verifyAddTableSpecs(GrnScoLocators.PR_Type_Field,
									GrnScoLocators.PR_Type_Field, QtyApplyFor0);
							commonFunc.hardWait(1000);
							webDB.moveToElement(GrnScoLocators.UP_Type_Field, ElementType.Xpath);
							verifyAddTableSpecs(GrnScoLocators.UP_Type_Field,
									GrnScoLocators.UP_Type_Input, CurrProcQty);
							commonFunc.hardWait(1000);
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
	 * This Method Is Used To Create Pending Grn Sco Doc For UP Type
	 * 
	 * @author Vimal - RMITS
	 * @param DocNo
	 */
	public void createPendingGrnScoDocForUSType(String DocNo, String QtyApplyFor0) {
		flag = webDB.waitForElement(CommonUiLocators.GRNSCO_Pending_Icon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.GRNSCO_Pending_Icon, ElementType.Xpath);
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
							commonFunc.hardWait(1000);
							String CurrProcQty = webDB.getDomPropertyFromElement(GrnScoLocators.PR_Type_Field,
									ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
							logs.logging("INFO", "Get  Proc Qty Is: " + CurrProcQty);
							commonFunc.hardWait(1000);
							webDB.moveToElement(GrnScoLocators.PR_Type_Field, ElementType.Xpath);
							verifyAddTableSpecs(GrnScoLocators.PR_Type_Field,
									GrnScoLocators.PR_Type_Field, QtyApplyFor0);
							commonFunc.hardWait(1000);
							webDB.moveToElement(GrnScoLocators.US_Type_Field, ElementType.Xpath);
							verifyAddTableSpecs(GrnScoLocators.US_Type_Field,
									GrnScoLocators.US_Type_Field, CurrProcQty);
							commonFunc.hardWait(1000);
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
	 * This Method Is Used To Clicking Gen Mat Cons And Allocate
	 * 
	 * @author Vimal- RMITS
	 */
	public void clickingGenMatConsAndAllocate() {
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
		assertTrue(flag);
	}
	/**
	 * This Method Is Used To Create Pending Grn Sco Doc For UP Type
	 * 
	 * @author Vimal - RMITS
	 * @param DocNo
	 */
	public void createPendingGrnScoDocForCSType(String DocNo, String QtyApplyFor0) {
		flag = webDB.waitForElement(CommonUiLocators.GRNSCO_Pending_Icon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.GRNSCO_Pending_Icon, ElementType.Xpath);
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
							commonFunc.hardWait(1000);
							String CurrProcQty = webDB.getDomPropertyFromElement(GrnScoLocators.PR_Type_Field,
									ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
							logs.logging("INFO", "Get  Proc Qty Is: " + CurrProcQty);
							commonFunc.hardWait(1000);
							webDB.moveToElement(GrnScoLocators.PR_Type_Field, ElementType.Xpath);
							verifyAddTableSpecs(GrnScoLocators.PR_Type_Field, GrnScoLocators.PR_Type_Field,
									QtyApplyFor0);
							commonFunc.hardWait(1000);
							webDB.moveToElement(GrnScoLocators.CS_Type_Field, ElementType.Xpath);
							verifyAddTableSpecs(GrnScoLocators.CS_Type_Field, GrnScoLocators.CS_Type_Field,
									CurrProcQty);
							commonFunc.hardWait(1000);
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
	 * This Method Used For Verify GRN SF Code Compare Excel Sheet With DataBase
	 * 
	 * @author Vimal-RMITS
	 */
	public void verifyGrnSFcodeWithExcelSheet(String Sheetname) {
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		System.out.println(itemmultiple.length);
		for (int i = 4; i < itemmultiple.length; i++) {
			String GRNSFCODE = itemmultiple[i][0];
			String GRNTARSFCODE = itemmultiple[i][1];
			if (!GRNSFCODE.equals("")) {
				salesOrderFunctions
						.verifyAssertionBothValues(dbCommonFunctions.SCOGRNLINEWISE(VariableHandler.getScoGrnNumber(),
								commonFunc.getDataFromTestDataSheet("SCOGRNDBSFCODECOLUMN")), GRNSFCODE);
				logs.logging("INFO", "SCO SF CODE Matched With GRN SF CODE AND MATCHED DataBase Values");

				salesOrderFunctions
						.verifyAssertionBothValues(dbCommonFunctions.SCOGRNLINEWISE(VariableHandler.getScoGrnNumber(),
								commonFunc.getDataFromTestDataSheet("SCOGRNDBTARSFCODECOLUMN")), GRNTARSFCODE);
				logs.logging("INFO", "SCO TAR SF CODE Matched With GRN TAR SF CODE AND MATCHED DataBase Values");


			}
		}
	}
}
