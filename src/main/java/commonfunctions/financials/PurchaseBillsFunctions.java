package commonfunctions.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.OpenSoAmdFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.BankReceiptLocators;
import commonlocators.financials.PurchaseBillsGrnLocators;
import commonlocators.financials.PurchaseBillsLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.OpenSoAmdLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import groovyjarjarantlr4.v4.runtime.tree.xpath.XPath;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseBillsFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	private static SalesOrderFunctions SalesordFunc = new SalesOrderFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	static SalesInvoiceFunctions SalesInv = new SalesInvoiceFunctions();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();
	private String GetTextLocationValue;
	static PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	static BankPaymentFunctions BPFunc = new BankPaymentFunctions();
	static BankPaymentFunctions bankpaymntFunc = new BankPaymentFunctions();
	static GrnPageFunctions grnFunc = new GrnPageFunctions();
	static SubcontractPurchaseBills SubcontractPurchasebillFunc = new SubcontractPurchaseBills();

	LocalDate date = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String Today = date.format(formatter);

	String[][] PurchaseBills = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	ExcelUtils excelUtils = new ExcelUtils();
	String filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "DBfin.xlsx";
	private String CgstAmount;
	String KowshikFin = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "DBfin.xlsx";
	String[][] BankPayments = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "Financials.xlsx";

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingPurchaseBill(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.PendingGrn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocators.PendingGrn, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsLocators.Iframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.GrnNo,
							VariableHandler.getGrnDocNumber());
					flag = webDB.waitForElement(PurchaseBillsLocators.Iframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsLocators.Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsLocators.SearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocators.CheckBox, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsLocators.CheckBox, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(5000);
									webDB.clickAnElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
									commonFunc.searchAndSelectValueInFilters(
											PurchaseBillsLocators.Pending_Prefix_Lov_button, "PB");
									webDB.switchToFrame(PurchaseBillsLocators.Iframe, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocators.OkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(PurchaseBillsLocators.OkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(PurchaseBillsLocators.LocationId,
												ElementType.Xpath);
										storePurchaseVoucherNumber();
										verifyAddTDSNumber();
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
		assertTrue(flag);
	}

	/**
	 * verify Add TDS Number
	 * 
	 * @author Deepan
	 */
	public void verifyAddTDSNumber() {
		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocators.ExpandButton, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseBillsLocators.ExpandButton, ElementType.Xpath);
		}
		webDB.moveToElement(PurchaseBillsLocators.TableData, ElementType.Xpath);
		webDB.moveToElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.TDSLovButton, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.TDSLovButton,
						commonFunc.getDataFromTestDataSheet("TDSValue"));
			}
		}
	}

	/**
	 * This Method Used To Verify To Create Journal And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateJournalAndPost() {
		flag = webDB.waitForElement(PurchaseBillsLocators.CreateJournal, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsLocators.CreateJournal, ElementType.Xpath);
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocators.CreateJournal, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			flag = webDB.waitForElement(PurchaseBillsLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocators.PostButton, ElementType.Xpath);
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store Purchase Voucher Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storePurchaseVoucherNumber() {
		String Number = webDB.getTextFromElement(PurchaseBillsLocators.VoucherNumber, ElementType.Xpath);
		VariableHandler.setPbNumber(Number);
	}

	/**
	 * waiting for approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyWaitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath)) {
			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			flag = webDB.waitForElement(PurchaseBillsLocators.ApproveOkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocators.ApproveOkButton, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Purchase Bills Approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseBillsApproval() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		verifyCreateJournalAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus(VariableHandler.getPbNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Purchase Bill Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseBillApprovalStatus(String DocNo, String ExpStatus) {
		commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.VoucherNumberLovButton, DocNo);
		flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillSearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocators.ApprovedStatus(DocNo), ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(PurchaseBillsLocators.ApprovedStatus(DocNo),
						ElementType.Xpath);
				flag = Status.contains(ExpStatus);
				if (flag) {
					logs.logging("INFO", "Expected Status: " + ExpStatus);
					logs.logging("INFO", "Verified Status ,Displayed Status: " + ExpStatus);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Purchase Bill Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseBillApprovalStatus() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.VoucherNumberLovButton,
				VariableHandler.getScoPurchaseNumber());
		flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillSearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocators.ApprovedStatus(VariableHandler.getScoPurchaseNumber()),
					ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(
						PurchaseBillsLocators.ApprovedStatus(VariableHandler.getScoPurchaseNumber()),
						ElementType.Xpath);
				flag = Status.contains(commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				if (flag) {
					logs.logging("INFO", "Expected Status: " + commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					logs.logging("INFO", "Verified Status ,Displayed Status: " + Status);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending SCO Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingScoPurchaseBill(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.PendingSCO, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending SCO Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocators.PendingSCO, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.ScoFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsLocators.ScoFrame, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.GrnLovButton, DocNo);
					flag = webDB.waitForElement(PurchaseBillsLocators.ScoFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsLocators.ScoFrame, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocators.ScoSearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsLocators.ScoSearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocators.ScoCheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsLocators.ScoCheckBox(DocNo), ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									verifyEnableCreateButton();
									webDB.clickAnElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocators.ScoOkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(PurchaseBillsLocators.ScoOkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(PurchaseBillsLocators.LocationId,
												ElementType.Xpath);
										storeSCOPurchaseVoucherNumber();
										if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath)) {
											webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
													ElementType.Xpath);
											if (webDB.verifyIsElementDisplayed(PurchaseBillsLocators.ExpandButton,
													ElementType.Xpath)) {
												webDB.clickAnElement(PurchaseBillsLocators.ExpandButton,
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
	 * This Method Used To Verify Store SCO Purchase Voucher Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storeSCOPurchaseVoucherNumber() {
		commonFunc.checkRefreshCellsDisappear();
		String DocNum = webDB.getTextFromElement(PurchaseBillsLocators.VoucherNumber, ElementType.Xpath);
//		String Number = webDB.getTextFromElement(PurchaseBillsLocators.VoucherNumber, ElementType.Xpath);
		VariableHandler.setScoPurchaseNumber(DocNum);
		logs.logging("INFO", "Get Text Purchase Bills Document Number Is " + VariableHandler.getScoPurchaseNumber());
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Method Used To Verify Enable Create Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyEnableCreateButton() {
		for (int i = 0; i < 60; i++) {
			commonFunc.hardWait(1000);
			if (webDB.isElementEnabled(PurchaseBillsLocators.CreateDoc, ElementType.Xpath)) {
				flag = true;
				break;
			}
		}
		assertTrue(flag);
	}

	/**
	 * Creates Expenses Type
	 * 
	 * @author Kowshik-Rmits
	 * 
	 * @param SubVouType
	 * @param PurchaseRef
	 * @param Loc_100
	 * @param Prbills_Pfx
	 */
	public void createNewRecordExpensesType(String SubVouType, String PurchaseRef, String Loc_100, String Prbills_Pfx) {

		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.AddButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Button is Displayed ");
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.AddButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.PendingFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Iframe Is Displayed");
				webDB.switchToFrame(PurchaseBillsLocators.PendingFrame, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.SubVouTypeLov, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.SubVouTypeLov, SubVouType);
					logs.logging("Info ", " SubVou Is Inserted " + SubVouType);
					webDB.switchToFrame(PurchaseBillsLocators.PendingFrame, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseBillsLocators.RefField, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "REf Is Inseterd ");
						webDB.selectDropDownByText(PurchaseBillsLocators.RefDropDown, PurchaseRef, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocators.LocLov, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.LocLov, Loc_100);
							logs.logging("INFO", " Location ID Is Inserted " + Loc_100);
							webDB.switchToFrame(PurchaseBillsLocators.PendingFrame, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocators.PrefixDropDown, ElementType.Xpath);
							if (flag) {
								webDB.selectDropDownByValue(PurchaseBillsLocators.PrefixDropDown, Prbills_Pfx,
										ElementType.Xpath);
								logs.logging("INFO", " Prefix  Is Inserted ");
								commonFunc.checkRefreshCellsDisappear();
							}
						}
					}

				}

			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Purchase Bills Customer
	 * 
	 * @author Kowshikan
	 * @param CustomerName
	 */
	public void insertPartyInPurchaseBills(String CustomerName, String bills) {
		String billsNo = RandomStringUtils.randomNumeric(6);
		if (billsNo.equals("000")) {
			billsNo = "10";
		}

		flag = webDB.waitForElement(PurchaseBillsLocators.PartyDescLoc, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.PartyDescLoc, CustomerName);
			webDB.switchToFrame(PurchaseBillsLocators.PendingFrame, ElementType.Xpath);
			logs.logging("Info", "Party Name Is Inserted  " + CustomerName);
			flag = webDB.waitForElement(PurchaseBillsLocators.BillNumberField, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PurchaseBillsLocators.BillNumberField, billsNo, ElementType.Xpath);
				logs.logging("INFO", "Bill Number Gerenated " + billsNo);
				flag = webDB.waitForElement(PurchaseBillsLocators.SaveButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocators.SaveButton, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					logs.logging("Info", "Expenses Header Document Is Created");
					commonFunc.hardWait(2000);
					commonFunc.checkRefreshCellsDisappear();

//					flag = webDB.waitForElement(PurchaseBillsLocators.SideScroll, ElementType.Xpath);
//					if(flag) {
//						webDB.javaScriptClickAnElement(PurchaseBillsLocators.SideScroll, ElementType.Xpath);
//					}
				}
			}

		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * Create New Record For Expenses
	 * 
	 * @author Kowshik-RMITS
	 */
	public void addNewRecord() {
		flag = webDB.waitForElement(PurchaseBillsLocators.AddNew, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.AddNew, ElementType.Xpath);
			logs.logging("INFO", "Add Button Is Clicked ");
		}
		assertTrue(flag);
	}

	/**
	 * Insert a GlAccount For Expenses Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Gl_Account_Ex
	 */
	public void InsertGlAccountInDetails(String Gl_Account_Ex) {
		flag = webDB.waitForElement(PurchaseBillsLocators.GlAccDescField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.GlAccDescField, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.GlAccLoc, ElementType.Xpath);
			if (flag) {
				// commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.GlAccLoc,
				// Gl_Account_Ex);
				webDB.sendTextToAnElement(PurchaseBillsLocators.GlAccLoc, Gl_Account_Ex, ElementType.Xpath);
				logs.logging("INFO", "Successfully Inserted a GlAccout Field " + Gl_Account_Ex);
			}

		}
		assertTrue(flag);
	}

	/**
	 * Upadate Unit Cost And Hsn code
	 * 
	 * @author Kowshik-RMITS
	 * @param unicost
	 * @param HSNcode_SI
	 */
	public void UpdateUnitCostInLineDetail(String unicost, String HSNcode_SI) {
		String unitcost = RandomStringUtils.randomNumeric(2);
		if (unitcost.equals("00")) {
			unitcost = "10";
		}
		flag = webDB.waitForElement(PurchaseBillsLocators.UnitCostField, ElementType.Xpath);
		if (flag) {
			SalesordFunc.verifyClickAndEnterValueInTableFields(PurchaseBillsLocators.UnitCostField,
					PurchaseBillsLocators.UnitCost, unitcost);
			logs.logging("INFO", "Unit Cost Is Entered ");
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocators.HsnCodeField, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.javaScriptClickAnElement(PurchaseBillsLocators.HsnCodeField, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.HsnCodeLov, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "HSN Code Is Entered ");
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.HsnCodeLov, HSNcode_SI);
					flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonLines, ElementType.Xpath);
					webDB.pressTabKey();
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Upadate Unit Cost And Hsn code
	 * 
	 * @param unicost
	 * @author Kowshik-RMITS
	 */
	public void UpdateUnitcostInLineDetailForPartyAccount(String Unitcost) {
		String unitcost = RandomStringUtils.randomNumeric(2);
		if (unitcost.equals("00")) {
			unitcost = "10";
		}
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.UnitCostField, ElementType.Xpath);
		if (flag) {
			SalesordFunc.verifyClickAndEnterValueInTableFields(PurchaseBillsLocators.UnitCostField,
					PurchaseBillsLocators.UnitCost, unitcost);
			logs.logging("INFO", "Unit Cost Is Entered ");
			flag = webDB.waitForElement(PurchaseBillsLocators.HsnCodeField, ElementType.Xpath);
			webDB.pressTabKey();

		}
		assertTrue(flag);
	}

	/**
	 * TO Click Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void ClickSaveButton() {
		flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonLines, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.SaveButtonLines, ElementType.Xpath);
			logs.logging("INFO", "Save Button Is Clicked ");
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(PurchaseBillsLocators.CreateJvButton, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Select Drop Down For Gst Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Fin_Type_In
	 */
	public void selectDropDownTypeAndInsecTion(String Value, String Value1) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocGstType(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLocGstType(), ElementType.Xpath);
			webDB.selectDropDownByValue(PurchaseBillsLocators.GstTypeDropDown, Value, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Gst Type Is Changed ");
				commonFunc.checkRefreshCellsDisappear();
				webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocGstInelSection(), ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLocGstInelSection(),
							ElementType.Xpath);
					webDB.selectDropDownByValue(PurchaseBillsLocators.InSectionDropDown, Value1, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonLines, ElementType.Xpath);
					if (flag) {
						logs.logging("INfo", "Gst Ins Section iS Type ");
						webDB.pressTabKey();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Journal Is Created As Record
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createJvForRecord() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.CreateJvButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.CreateJvButton, ElementType.Xpath);
//			webDB.moveToElement(PurchaseBillsLocators.CreateJvButton, ElementType.Xpath);
//			webDB.javaScriptClickAnElement(PurchaseBillsLocators.CreateJvButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Journal Is Created ");
			commonFunc.hardWait(2000);
			commonFunc.checkRefreshCellsDisappear();

		}
		assertTrue(flag);
	}

	/**
	 * Click Post Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPostButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.PostButtonH, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PostButtonH, ElementType.Xpath);
//			webDB.moveToElement(PurchaseBillsLocators.PostButtonH, ElementType.Xpath);
//			webDB.javaScriptClickAnElement(PurchaseBillsLocators.PostButtonH, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * verify Waiting For Aprorval
	 * 
	 * @author Kowshik-RMITS
	 * @return
	 */
	public boolean verifyWaitingForApprovalForPurchaseBills() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.OpsoForwardApproval,
					commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseBillsLocators.WfOkButtton, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(PurchaseBillsLocators.WfOkButtton, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
			commonFunc.hardWait(2000);
			commonFunc.checkRefreshCellsDisappear();
			if (!VariableHandler.getApprovalFlag()) {
				approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * Get Text Gst Classification In Bill Fro Location
	 * 
	 * @author Kowshik-RMITS
	 */
	public void getGstClafInLocation() {
		flag = webDB.waitForElement(PurchaseBillsLocators.gstClafField, ElementType.Xpath);
		if (flag) {
			String GstClaf = webDB.getTextFromElement(PurchaseBillsLocators.gstClafField, ElementType.Xpath);
			logs.logging("Info", "Gst Classification is " + GstClaf);
		}
		assertTrue(flag);
	}

	/**
	 * Moves To Invoice Tap In Line Wise
	 * 
	 * @author Kowshik-RMITS
	 */
	public void movesToInvoiceTap() {
		commonFunc.hardWait(500);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.InvoiceLines, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsLocators.InvoiceLines, ElementType.Xpath);
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.InvoiceLines, ElementType.Xpath);
			logs.logging("INFO", "Move To Invoice Lines");
		}
		assertTrue(flag);
	}

	/**
	 * VALIDATE Tax Amount From Line Details
	 * 
	 * @author Kowshik-RMITS
	 */
	public void getValueFromTaxAmountInLineDetails() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.IgstAmountField, ElementType.Xpath);
		if (flag) {
			String IgstAmount = webDB.getTextFromElement(PurchaseBillsLocators.IgstAmountField, ElementType.Xpath);
			logs.logging("Info", "Igst Tax Amount  " + IgstAmount);
			flag = webDB.waitForElement(PurchaseBillsLocators.CgstAmountField, ElementType.Xpath);
			if (flag) {
				String CgstAmount = webDB.getTextFromElement(PurchaseBillsLocators.CgstAmountField, ElementType.Xpath);
				logs.logging("Info", "Cgst Tax Amount  " + CgstAmount);
				flag = webDB.waitForElement(PurchaseBillsLocators.SgstAmountField, ElementType.Xpath);
				if (flag) {
					String SgstAmount = webDB.getTextFromElement(PurchaseBillsLocators.SgstAmountField,
							ElementType.Xpath);
					logs.logging("Info", "SGST Tax Amount  " + SgstAmount);
					flag = webDB.waitForElement(PurchaseBillsLocators.UTgstAmountField, ElementType.Xpath);
					if (flag) {
						String UTgstAmount = webDB.getTextFromElement(PurchaseBillsLocators.UTgstAmountField,
								ElementType.Xpath);
						logs.logging("Info", "UTGST Tax Amount  " + UTgstAmount);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Valid Gst Classification In Location Details
	 * 
	 * @author Kowshik-RMITS
	 */
	public void toValidGstClaf() {
		flag = webDB.waitForElement(PurchaseBillsLocators.GstClafField, ElementType.Xpath);
		if (flag) {
			String GstClaf = webDB.getTextFromElement(PurchaseBillsLocators.GstClafField, ElementType.Xpath);
			logs.logging("INFO", "Gst Claf  is " + GstClaf);
		}
		assertTrue(flag);
	}

	/**
	 * Click Allows Igst Flag
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickValidateAllowIgstFlag(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
			logs.logging("INFO", "Reference Tap Moved ");

			String value = webDB.getDomAttributeFromElement(PurchaseBillsLocators.PurchaseBillsExpandButton,
					ElementType.Xpath, "aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.javaScriptClickAnElement(PurchaseBillsLocators.PurchaseBillsExpandButton, ElementType.Xpath);
				logs.logging("INFO", "Expand  Button is clicked");
			}
			flag = webDB.waitForElement(PurchaseBillsLocators.AllowIgstCheckFlag, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PurchaseBillsLocators.AllowIgstCheckFlag, ElementType.Xpath);
				webDB.javaScriptClickAnElement(PurchaseBillsLocators.AllowIgstCheckFlag, ElementType.Xpath);
				logs.logging("INFO ", "Allows Igst Check Box Is Clicked ");
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
					logs.logging("INFO", "Successfully Clicked Save Headers");
					commonFunc.checkRefreshCellsDisappear();
					validAllowIgstCheckboxForTaxAmountCalculation();
					movesToInvoiceTap();
					// excelUtils.writeDataInExcel(filepath, SheetName, 2, 3, "2");
					verifyTaxAmountForIgstAmountInDbValidation(SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Allows Rcm Flag
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickValidateAllowRcmFlag() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
			logs.logging("INFO", "Reference Tap Moved ");
			commonFunc.checkRefreshCellsDisappear();
			String value = webDB.getDomAttributeFromElement(PurchaseBillsLocators.PurchaseBillsExpandButton,
					ElementType.Xpath, "aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.javaScriptClickAnElement(PurchaseBillsLocators.PurchaseBillsExpandButton, ElementType.Xpath);
				logs.logging("INFO", "Expand  Button is clicked");
			}
			flag = webDB.waitForElement(PurchaseBillsLocators.AllowRcmFlag, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PurchaseBillsLocators.AllowRcmFlag, ElementType.Xpath);
				webDB.javaScriptClickAnElement(PurchaseBillsLocators.AllowRcmFlag, ElementType.Xpath);
				// webDB.clickAnElement(PurchaseBillsLocators.AllowRcmFlag, ElementType.Xpath);
				logs.logging("INFO ", "Allows RCM Check Box Is Clicked ");
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
					logs.logging("INFO", "Successfully Clicked Save Headers");
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Allows Igst Flag
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickValidateAllowIgstFlagUpdated(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
			logs.logging("INFO", "Reference Tap Moved ");
			flag = webDB.waitForElement(PurchaseBillsLocators.AllowIgstCheckFlag, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PurchaseBillsLocators.AllowIgstCheckFlag, ElementType.Xpath);
				webDB.javaScriptClickAnElement(PurchaseBillsLocators.AllowIgstCheckFlag, ElementType.Xpath);
				logs.logging("INFO ", "Allows Igst Check Box Is Clicked ");
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
					logs.logging("INFO", "Successfully Clicked Save Headers");
					commonFunc.checkRefreshCellsDisappear();
					validAllowIgstCheckboxForTaxAmountCalculation();
					// excelUtils.writeDataInExcel(filepath, SheetName, 2, 3, "2");
					verifyTaxAmountForIgstAmountInDbValidation(SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verift Tax Amount After Check a Allows Igst Flag
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */
	public void verifyTaxAmountForIgstAmountInDbValidationUpdated(String SheetName) {
		PurchaseBills = excelUtils.readAllDataInExcel(filepath, SheetName);
		for (int i = 4; i < PurchaseBills.length; i++) {
			String SeqNumber = Integer.toString(i - 3);
			String Item = PurchaseBills[i][1];
			String IgstTaxAmount = PurchaseBills[i][26];
			if (!Item.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsIgstAmount")),
						IgstTaxAmount);
				logs.logging("INFO", "Igst IS Matched With DB Values For This Item " + IgstTaxAmount);

			}
		}
		assertTrue(flag);
	}

	/**
	 * Sales Invoice Against Distribute a Values
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickInvoiceDetailsForDisturabeValue() {
		flag = webDB.waitForElement(PurchaseBillsLocators.InvoiceButtonGrid, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsLocators.InvoiceButtonGrid, ElementType.Xpath);
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.InvoiceDetailButton, ElementType.CssSelector);
			logs.logging("INFO", "Invoice Details Button Is Clicked ");
			flag = webDB.waitForElement(PurchaseBillsLocators.InvDetailsFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Iframe Is Displayed");
				webDB.switchToFrame(PurchaseBillsLocators.InvDetailsFrame, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.AddButtonInvDetails, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(PurchaseBillsLocators.AddButtonInvDetails, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					logs.logging("INFO", " Plus Is Clicked ");
					flag = webDB.waitForClickElement(PurchaseBillsLocators.InvDocGridField, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(PurchaseBillsLocators.InvDocGridField, ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.InvDetailLovField,
								VariableHandler.getSIdocno());
						logs.logging("INFO", "Sales Invoice Document is Inserted" + VariableHandler.getSIdocno());
						flag = webDB.waitForElement(PurchaseBillsLocators.InvDetailsFrame, ElementType.Xpath);
						if (flag) {
							webDB.switchToFrame(PurchaseBillsLocators.InvDetailsFrame, ElementType.Xpath);
							webDB.javaScriptClickAnElement(PurchaseBillsLocators.SaveButtonInvBT, ElementType.Xpath);
							logs.logging("INFO", "Invoice Record Is Saved ");
							flag = webDB.waitForElement(PurchaseBillsLocators.DistributeButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							if (flag) {
								webDB.moveToElement(PurchaseBillsLocators.DistributeButton, ElementType.Xpath);
								webDB.javaScriptClickAnElement(PurchaseBillsLocators.DistributeButton,
										ElementType.Xpath);
								logs.logging("INFO", "Sales invoice Recotd Is Inserted & Distributed a Item ");
								commonFunc.checkRefreshCellsDisappear();
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(PurchaseBillsLocators.CloseIcon, ElementType.Xpath);
								if (flag) {
									webDB.moveToElement(PurchaseBillsLocators.CloseIcon, ElementType.Xpath);
									webDB.javaScriptClickAnElement(PurchaseBillsLocators.CloseIcon, ElementType.Xpath);
									logs.logging("INFO", "Value Is Distributed Closed a Frame");
									flag = webDB.waitForElement(PurchaseBillsLocators.CreateJvButton,
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
	 * To Valid Tds Field Against To Party
	 * 
	 * @author Kowshik-RMITS
	 * @param Section
	 */
	public void toValidTdsFieldAgainstToParty(String Section) {
		flag = webDB.waitForElement(PurchaseBillsLocators.TdsGridField, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.TdsGridField, ElementType.Xpath);
			logs.logging("INFO", "Tds Check Box Is Selected");
			flag = webDB.waitForElement(PurchaseBillsLocators.TdsInputGrid, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocators.TdsInputGrid, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.javaScriptSendTextToAnElement(PurchaseBillsLocators.TdsFieldInput, Section, ElementType.Xpath);
				// commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.TDSLovButton,
				// Section);
				logs.logging("INFO", "Tds Section Is Inserted  " + Section);
			} else {
				flag = webDB.isElementSelected(PurchaseBillsLocators.TdsGridField, ElementType.Xpath);
				logs.logging("INFO", "TDS Section CheckBox Unselected");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Close Expand Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void closeExpandButtonDirect() {
		commonFunc.checkRefreshCellsDisappear();
		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocators.ExpandButton, ElementType.Xpath)) {
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.ExpandButton, ElementType.Xpath);
			logs.logging("INFO", "Cursor Button Is Closed ");
			flag = webDB.waitForElement(PurchaseBillsLocators.VoucherNumber, ElementType.Xpath);
			vaildateLocationInHeaderPurchaseBills(commonFunc.getDataFromTestDataSheet("Loc_100"));
			validateStatusOfPurchaseBills(commonFunc.getDataFromTestDataSheet("Draft"));
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * Open Expand Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void openExpandButtonDirect() {
		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocators.OpenExpandButton, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseBillsLocators.OpenExpandButton, ElementType.Xpath);
			logs.logging("INFO", "Cursor Button Is Opened");
			flag = webDB.waitForElement(PurchaseBillsLocators.gstClafField, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 */
	private void VerifyClickingAdjustmentTab(String AdvanceNo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.PBAdjustmentTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PBAdjustmentTab, ElementType.Xpath);
			logs.logging("INFO", "Adjustment tab is CLicked");
			flag = webDB.waitForElement(PurchaseBillsLocators.PBAdjustmentLoadBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocators.PBAdjustmentLoadBtn, ElementType.Xpath);
				logs.logging("INFO", "Load Button iS Clicked");
				VerifyAdjustmentFrame();
				flag = webDB.waitForElement(PurchaseBillsLocators.PBAdjustmentSearchField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocators.PBAdjustmentSearchField, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsLocators.PBAdjustmentSearchField, AdvanceNo,
							ElementType.Xpath);
					logs.logging("INFO", "Adjustment Search Field iS Clicked");
					flag = webDB.waitForElement(PurchaseBillsLocators.PBAdjustmentSearchGoBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocators.PBAdjustmentSearchGoBtn, ElementType.Xpath);
						logs.logging("INFO", "Adjustment Go Button iS Clicked");
						flag = webDB.waitForElement(PurchaseBillsLocators.PBAdjustValueEnterField, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocators.PBAdjustValueEnterField, ElementType.Xpath);
							logs.logging("INFO", "Value Field Is Clicked");
							webDB.clickAnElement(PurchaseBillsLocators.PBAdjustValueEnterInput, ElementType.Xpath);
							webDB.clearText(PurchaseBillsLocators.PBAdjustValueEnterInput, ElementType.Xpath);
							webDB.sendTextToAnElement(PurchaseBillsLocators.PBAdjustValueEnterInput, "5",
									ElementType.Xpath);
							logs.logging("INFO", "Value Is Entered:");
							flag = webDB.waitForElement(PurchaseBillsLocators.PBAdjustDocSelect, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsLocators.PBAdjustDocSelect, ElementType.Xpath);
								logs.logging("INFO", "Document Is Selected");
								flag = webDB.waitForElement(PurchaseBillsLocators.PBAdjustmentOk, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsLocators.PBAdjustmentOk, ElementType.Xpath);
									logs.logging("INFO", "Ok Button IS Clicked ");
									webDB.switchToDefaultContent();
								}
							}
						}
					}
				}
			}
			assertTrue(flag);
		}

	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 */
	private void VerifyAdjustmentFrame() {
		flag = webDB.waitForElement(PurchaseBillsLocators.PBAdjustmentFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseBillsLocators.PBAdjustmentFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Adjustment Frame");
		}
		assertTrue(flag);
	}

	/**
	 * Click Add Button In Line Details
	 * 
	 * @author Kowshik-RMITS
	 */
	public void addClickButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.AddButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Button is Displayed ");
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.AddButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.PendingFrame, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Switch To Frame Direct
	 * 
	 * @author Kowshik-RMITS
	 */
	public void switchToFrameDirect() {
		flag = webDB.waitForElement(PurchaseBillsLocators.PendingFrame, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Iframe Is Displayed");
			webDB.switchToFrame(PurchaseBillsLocators.PendingFrame, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.SubvouTypeInput, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Verfied Location ID
	 * 
	 * @author KowshiK-RMITS
	 * @param Location
	 */
	public void vaildateLocationInHeaderPurchaseBills(String Location) {
		flag = webDB.waitForElement(PurchaseBillsLocators.HeaderLocationField, ElementType.Xpath);
		if (flag) {
			String DisplayLocationID = webDB.getTextFromElement(PurchaseBillsLocators.HeaderLocationField,
					ElementType.Xpath);
			if (DisplayLocationID.equalsIgnoreCase(Location)) {
				logs.logging("Info", "Location Id Is Verified , Verified Location Is " + DisplayLocationID);
			} else {
				logs.logging("INFO", "Location Id Is Failed to Verified ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verfied OF Status
	 * 
	 * @author Kowshik-RMITS
	 * @param Status
	 */
	public void validateStatusOfPurchaseBills(String Status) {
		flag = webDB.waitForElement(PurchaseBillsLocators.HeaderStatusField, ElementType.Xpath);
		if (flag) {
			String DisplayStatus = webDB.getTextFromElement(PurchaseBillsLocators.HeaderStatusField, ElementType.Xpath);
			if (DisplayStatus.equalsIgnoreCase(Status)) {
				logs.logging("INFO", "Header Document Is Created , Status Is : " + DisplayStatus);
			} else {
				logs.logging("INFO", "Status Is Not Getting Matched Actual Status Is : " + DisplayStatus);
			}
		}
		assertTrue(flag);
	}

	/**
	 * ADDING GLACCOUNT Read From Excel Sheet
	 * 
	 * @author Kowshik-RMITS
	 */
	public void addingGlaccountFromReadingExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "DBfin.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			String Glaccount = itemMultiples[i][1];
			String UnitPrice = itemMultiples[i][7];
			String Hsncode = itemMultiples[i][21];
			if (!Glaccount.equals("")) {
				flag = webDB.waitForElement(PurchaseBillsLocators.AddNew, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocators.AddNew, ElementType.Xpath);
					logs.logging("INFO", "Add Button Is Clicked ");
					flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLoc(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLoc(), ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocators.GlAccLoc, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.GlAccLoc, Glaccount);
							flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocunitprice(),
									ElementType.Xpath);
							if (flag) {
								SalesordFunc.verifyClickAndEnterValueInTableFields(
										PurchaseBillsLocators.PurchaseBillsTransLocunitprice(),
										PurchaseBillsLocators.UnitCost, UnitPrice);
								logs.logging("INFO", "Unit Cost Is Entered ");
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocHsnCode(),
										ElementType.Xpath);
								if (flag) {
									commonFunc.checkRefreshCellsDisappear();
									webDB.javaScriptClickAnElement(PurchaseBillsLocators.PurchaseBillsTransLocHsnCode(),
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocators.HsnCodeLov, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "HSN Code Is Entered ");
										commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.HsnCodeLov,
												Hsncode);
										commonFunc.checkRefreshCellsDisappear();
//										webDB.sendTextToAnElement(PurchaseBillsLocators.HsnCodeLov, Hsncode,
//												ElementType.Xpath);
										flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonLines,
												ElementType.Xpath);
										webDB.pressTabKey();
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
	 * This Method is Used to add a Item In Purchase Bills
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */

	public void addingItemFromReadingExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "DBfin.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			String ItemId = itemMultiples[i][1];
			String UnitPrice = itemMultiples[i][7];
			String Hsncode = itemMultiples[i][21];
			String InvQty = itemMultiples[i][6];
			String Glaccount = itemMultiples[i][29];
			String TdDiscValue = itemMultiples[i][9];
			String SdDiscValue = itemMultiples[i][10];
			String CashDiscValue = itemMultiples[i][11];
			if (!ItemId.equals("")) {
				System.out.println(ItemId);
				System.err.println(Glaccount);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocators.AddNew, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocators.AddNew, ElementType.Xpath);
					logs.logging("INFO", "Add Button Is Clicked ");
					flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsLineWiseItem(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsLineWiseItem(), ElementType.Xpath);
//						flag = webDB.waitForElement(PurchaseBillsLocators.ItemLovField, ElementType.Xpath);
//						if (flag) {
						commonFunc.hardWait(1000);
						commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.ItemLovField, ItemId);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLoc(), ElementType.Xpath);
						if (flag) {
							webDB.doubleClickAnElement(PurchaseBillsLocators.PurchaseBillsTransLoc(),
									ElementType.Xpath);
//							flag = webDB.waitForElement(PurchaseBillsLocators.GlAccLoc, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(1000);
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.GlAccLoc, Glaccount);
								logs.logging("INFO", "Account Is Inserted" + Glaccount);
								commonFunc.checkRefreshCellsDisappear();
//						flag = webDB.waitForElement(PurchaseBillsLocators.InvQtyField, ElementType.Xpath);
								if (flag) {
									SalesordFunc.verifyClickAndEnterValueInTableFields(
											PurchaseBillsLocators.PurchaseBillsLineWiseInvQty(),
											PurchaseBillsLocators.InvQty, InvQty);
									logs.logging("INFO", "Inv Qty Is Entered");
									commonFunc.checkRefreshCellsDisappear();
									flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocunitprice(),
											ElementType.Xpath);
									if (flag) {
										SalesordFunc.verifyClickAndEnterValueInTableFields(
												PurchaseBillsLocators.PurchaseBillsTransLocunitprice(),
												PurchaseBillsLocators.UnitCost, UnitPrice);
										logs.logging("INFO", "Unit Cost Is Entered ");
										commonFunc.checkRefreshCellsDisappear();
										flag = webDB.waitForElement(
												PurchaseBillsLocators.PurchaseBillsTransLocHsnCode(),
												ElementType.Xpath);
										if (flag) {
											commonFunc.checkRefreshCellsDisappear();
											webDB.javaScriptClickAnElement(
													PurchaseBillsLocators.PurchaseBillsTransLocHsnCode(),
													ElementType.Xpath);
//										flag = webDB.waitForElement(PurchaseBillsLocators.HsnCodeLov,
//												ElementType.Xpath);
//										if (flag) {
											String GetTextHSN = webDB.getTextFromElement(
													PurchaseBillsLocators.PurchaseBillsTransLocHsnCode(),
													ElementType.Xpath);
											if (!GetTextHSN.equals(Hsncode)) {
												commonFunc.searchAndSelectValueInFilters(
														PurchaseBillsLocators.HsnCodeLov, Hsncode);
												commonFunc.checkRefreshCellsDisappear();
											}
//											webDB.clearTextField(PurchaseBillsLocators.HsnCodeLov, ElementType.Xpath);
//											webDB.sendTextToAnElement(PurchaseBillsLocators.HsnCodeLov, Hsncode,
//													ElementType.Xpath);
//											flag = webDB.waitForElement(PurchaseBillsLocators.TdDiscField,
//													ElementType.Xpath);
//											if (flag) {
//												SalesordFunc.verifyClickAndEnterValueInTableFields(
//														PurchaseBillsLocators.TdDiscField,
//														PurchaseBillsLocators.TdDiscPercent, TdDiscValue);
//												webDB.pressTabKey();
//												logs.logging("INFO", "Td Disc Percent Is Inserted");
//												commonFunc.checkRefreshCellsDisappear();
//												flag = webDB.waitForElement(PurchaseBillsLocators.SdDiscField,
//														ElementType.Xpath);
//												if (flag) {
//													SalesordFunc.verifyClickAndEnterValueInTableFields(
//															PurchaseBillsLocators.SdDiscField,
//															PurchaseBillsLocators.SdDiscPercent, SdDiscValue);
//													webDB.pressTabKey();
//													commonFunc.checkRefreshCellsDisappear();
//													logs.logging("INFO", "Sd Disc Percent Is Inserted");
//													flag = webDB.waitForElement(PurchaseBillsLocators.CashDiscField,
//															ElementType.Xpath);
//													if (flag) {
//														SalesordFunc.verifyClickAndEnterValueInTableFields(
//																PurchaseBillsLocators.CashDiscField,
//																PurchaseBillsLocators.CashDiscPercent, CashDiscValue);
//														webDB.pressTabKey();
//														commonFunc.checkRefreshCellsDisappear();
//														logs.logging("INFO", "Cash Disc Percent Is Inserted");
											flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonLines,
													ElementType.Xpath);
											ClickSaveButton();
											webDB.pressTabKey();

//										}
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

//		}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyAddTheItemBasedOnTypeForStockTransfer(String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "DBfin.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < GRNMultipleline.length; i++) {
			System.out.println(GRNMultipleline.length);
			String Type = GRNMultipleline[i][30];
			String Cost = GRNMultipleline[i][8];
			String GLAccount = GRNMultipleline[i][31];
			String ITEM_ID = GRNMultipleline[i][1];
			String Qty = GRNMultipleline[i][7];
			String HSN = GRNMultipleline[i][22];
			String TaxAmountCGST = GRNMultipleline[i][21];
			String TaxAmountSGST = GRNMultipleline[i][22];
			String TaxAmountIGST = GRNMultipleline[i][23];
			String GstSupply = GRNMultipleline[i][32];
			if (!Type.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocators.AddNew, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocators.AddNew, ElementType.Xpath);
					logs.logging("INFO", "Add Button is Clicked");
					flag = webDB.waitForElement(PurchaseBillsLocators.PBAddTypeExp, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocators.PBAddTypeExp, ElementType.Xpath);
						webDB.selectDropDownByText(PurchaseBillsLocators.PBAddTypeExp, Type, ElementType.Xpath);
						logs.logging("INFO", "Field Is Clicked");
						if (flag) {
							if (Type.equals(commonFunc.getDataFromTestDataSheet("Expensetype"))) {
								VerifyToCreateExpenseType(GLAccount, Cost, HSN, GstSupply);
							} else if (Type.equals(commonFunc.getDataFromTestDataSheet("ItemType"))) {
								VerifyToCreatetheItemTypeDocument(ITEM_ID, Cost, Qty, HSN, GLAccount, TaxAmountIGST,
										TaxAmountSGST, TaxAmountCGST, GstSupply);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods To Create Expense Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void VerifyToCreateExpenseType(String GLAccount, String Cost, String HSN, String GstSupply) {
		flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLoc(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLoc(), ElementType.Xpath);
			logs.logging("INFO", "Field Is Clicked");
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocators.GlAccLoc, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.GlAccLoc, GLAccount);
				logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocunitprice(), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLocunitprice(), ElementType.Xpath);
					webDB.clearText(PurchaseBillsLocators.PBCostInput, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsLocators.PBCostInput, Cost, ElementType.Xpath);
					logs.logging("INFO", "Entered Cost Is:" + Cost);
					flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocHsnCode(),
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLocHsnCode(), ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocators.StockTransferHsnLov, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.StockTransferHsnLov, HSN);
							logs.logging("INFO", "Entered Cost Is:" + HSN);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsLineWiseGstType(),
									ElementType.Xpath);
							if (flag) {
								commonFunc.checkRefreshCellsDisappear();
								webDB.javaScriptClickAnElement(PurchaseBillsLocators.PurchaseBillsLineWiseGstType(),
										ElementType.Xpath);
								String GetTextGstType = webDB.getTextFromElement(
										PurchaseBillsLocators.PurchaseBillsLineWiseGstType(), ElementType.Xpath);
								if (!GetTextGstType.equals(GstSupply)) {
									webDB.selectDropDownByText(PurchaseBillsLocators.GstSupplyType, GstSupply,
											ElementType.Xpath);
									logs.logging("IFO", "Entered Gst Supply : " + GetTextGstType);
								}
								webDB.pressTabKey();
								ClickSaveButton();
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Click the Table Field And
	 *         Pass The Input Value
	 * 
	 * @param Field
	 * @param InputField
	 * @param InputValue
	 */
	public void verifyClickAndEnterValueInTableFields(String Field, String InputField, String InputValue) {
		flag = webDB.waitForElement(Field, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Field, ElementType.Xpath);
			logs.logging("INFO", "Table Input Field is Clicked");
			webDB.clearTextField(InputField, ElementType.Xpath);
			logs.logging("INFO", "Input text Is Cleared");
			flag = webDB.waitForElement(Field, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(Field, ElementType.Xpath);
				webDB.sendTextToAnElement(InputField, InputValue, ElementType.Xpath);
				logs.logging("INFO", "Input Value Is :" + InputValue);
				String Value = webDB.getDomPropertyFromElement(InputField, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Attribute Value Is :" + Value);
				flag = Value.contains(InputValue);
				if (flag) {
					logs.logging("INFO", "Verified Input Value From Get Attribute Value");
				} else {
					logs.logging("INFO", "Verification failed for Input Value From Get Attribute Value");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void VerifyToCreatetheItemTypeDocument(String ITEM_ID, String Cost, String Qty, String HSN, String GLAccount,
			String TaxAmountCGST, String TaxAmountSGST, String TaxAmountIGST, String GstSupply) {
		webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsLineWiseItem(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsLineWiseItem(), ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.ItemLovField, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.ItemLovField, ITEM_ID);
				logs.logging("INFO", "Type Selected SuccessFully");
				// flag = webDB.waitForElement(PurchaseBillsLocators.StockTransferTypeQtyField,
				// ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLoc(), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLoc(), ElementType.Xpath);
					logs.logging("INFO", "Field Is Clicked");
					flag = webDB.waitForElement(PurchaseBillsLocators.GlAccLoc, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.GlAccLoc, GLAccount);
						logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
						commonFunc.checkRefreshCellsDisappear();
						if (flag) {
							webDB.javaScriptClickAnElement(PurchaseBillsLocators.StockTransferTypeQtyField,
									ElementType.Xpath);
							verifyClickAndEnterValueInTableFields(PurchaseBillsLocators.StockTransferTypeQtyField,
									PurchaseBillsLocators.StockTransferQtyInput, Qty);
//							webDB.clearTextField(PurchaseBillsLocators.StockTransferQtyInput,
//									ElementType.Xpath);
//							commonFunc.checkRefreshCellsDisappear();
//							commonFunc.hardWait(2000);
//							webDB.javaScriptSendTextToAnElement(PurchaseBillsLocators.StockTransferQtyInput, Qty,
//									ElementType.Xpath);
							logs.logging("INFO", "Entered Qty Is:" + Cost);
							flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocunitprice(),
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLocunitprice(),
										ElementType.Xpath);
								webDB.clearText(PurchaseBillsLocators.PBCostInput, ElementType.Xpath);
								webDB.sendTextToAnElement(PurchaseBillsLocators.PBCostInput, Cost, ElementType.Xpath);
								logs.logging("INFO", "Entered Cost Is:" + Cost);
								flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocHsnCode(),
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLocHsnCode(),
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocators.StockTransferHsnLov,
											ElementType.Xpath);
									if (flag) {
										commonFunc.searchAndSelectValueInFilters(
												PurchaseBillsLocators.StockTransferHsnLov, HSN);
										logs.logging("INFO", "Entered Cost Is:" + HSN);
										commonFunc.checkRefreshCellsDisappear();
										flag = webDB.waitForElement(
												PurchaseBillsLocators.PurchaseBillsLineWiseGstType(),
												ElementType.Xpath);
										if (flag) {
											commonFunc.checkRefreshCellsDisappear();
											webDB.javaScriptClickAnElement(
													PurchaseBillsLocators.PurchaseBillsLineWiseGstType(),
													ElementType.Xpath);
											String GetTextGstType = webDB.getTextFromElement(
													PurchaseBillsLocators.PurchaseBillsLineWiseGstType(),
													ElementType.Xpath);
											if (!GetTextGstType.equals(GstSupply)) {
												webDB.selectDropDownByText(PurchaseBillsLocators.GstSupplyType,
														GstSupply, ElementType.Xpath);
												logs.logging("IFO", "Entered Gst Supply : " + GetTextGstType);
											}

											webDB.pressTabKey();
											ClickSaveButton();

//										VerifyToValidateStockTransferItemTypeValidate(ITEM_ID, TaxAmountIGST,
//												TaxAmountIGST, TaxAmountIGST);

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
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingPurchaseBillForPendingPo(String DocNo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.PendingPo, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocators.PendingPo, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.IframePendingPo, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsLocators.IframePendingPo, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.PrDocNo, DocNo);
					flag = webDB.waitForElement(PurchaseBillsLocators.IframePendingPo, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsLocators.IframePendingPo, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocators.SearchButtonPo, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsLocators.SearchButtonPo, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocators.CheckBox, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsLocators.CheckBox, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(1000);
									webDB.clickAnElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocators.OkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										commonFunc.hardWait(2000);
										commonFunc.checkRefreshCellsDisappear();
										verifyInvoicePrfixHeaderPurchaseBillsForPendingPo(
												commonFunc.getDataFromTestDataSheet("PrefixPb"));
										webDB.clickAnElement(PurchaseBillsLocators.OkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(2000);

//										flag = webDB.waitForElement(PurchaseBillsLocators.LocationId,
//												ElementType.Xpath);
//										storePurchaseVoucherNumber();
//										verifyAddTDSNumber();
//										if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
//												ElementType.Xpath)) {
//											webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
//													ElementType.Xpath);

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
	 * Verify Line Details With Db In Purchase Bills
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */
	public void verifyLineDetailWithDbInPurchaseBills(String SheetName) {
		PurchaseBills = excelUtils.readAllDataInExcel(filepath, SheetName);
		for (int i = 4; i < PurchaseBills.length; i++) {
			String Glaccount = PurchaseBills[i][3];
			String OrdQty = PurchaseBills[i][6];
			String UnitPrice = PurchaseBills[i][7];
			String Hsncode = PurchaseBills[i][21];
			String TaxPercent = PurchaseBills[i][22];
			String CgstField = PurchaseBills[i][23];
			String SgstField = PurchaseBills[i][24];
			String UTgstField = PurchaseBills[i][26];
			String IgstField = PurchaseBills[i][25];
			String NetAmount = PurchaseBills[i][28];
			if (!Glaccount.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsGlAccount")), Glaccount);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsInvQty")), OrdQty);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsUnitCost")), UnitPrice);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsHsncode")), Hsncode);
//				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoGrnNumber(),
//						commonFunc.getDataFromTestDataSheet("PurchaseBillsTaxPercent")), TaxPercent);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsCgstAmount")), CgstField);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsSgstAmount")), SgstField);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsUTGSTAmount")), UTgstField);
//				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
//						commonFunc.getDataFromTestDataSheet("PurchaseBillsIgstAmount")), IgstField);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsExHeader(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("Purchase_Bills_NetAmount")), NetAmount);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This MEthod is Used Check a Validation Of Allow Igst Flag In Local Supplier
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */
	public void verifyLineDetailsWithDbValidationForAllowIGSTFlag(String SheetName) {
		PurchaseBills = excelUtils.readAllDataInExcel(filepath, SheetName);
		for (int i = 4; i < PurchaseBills.length; i++) {
			String ItemID = PurchaseBills[i][30];
			String OrdQty = PurchaseBills[i][35];
			String UnitPrice = PurchaseBills[i][36];
			String TDamt = PurchaseBills[i][42];
			String SDamt = PurchaseBills[i][44];
			String CDamt = PurchaseBills[i][46];
			String CgstField = PurchaseBills[i][52];
			String SgstField = PurchaseBills[i][53];
			String UTgstField = PurchaseBills[i][55];
			String IgstField = PurchaseBills[i][54];
			String NetAmount = PurchaseBills[i][57];
			if (!ItemID.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsItemDesc")), ItemID);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsInvQty")), OrdQty);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsUnitCost")), UnitPrice);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchasebillsTradeDiscValue")), TDamt);
//				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoGrnNumber(),
//						commonFunc.getDataFromTestDataSheet("PurchaseBillsTaxPercent")), TaxPercent);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsSplDiscAmount")), SDamt);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsCashDiscAmount")), CDamt);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsCgstAmount")), CgstField);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsSgstAmount")), SgstField);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsUTGSTAmount")), UTgstField);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsIgstAmount")), IgstField);
//				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsExHeader(VariableHandler.getScoPurchaseNumber(),
//						commonFunc.getDataFromTestDataSheet("Purchase_Bills_NetAmount")), NetAmount);
			}
		}
	}

	/**
	 * This Method is Used to DB Validation With RCM Flag
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */
	public void verifyLineDetailsWithDbValidationRcmFlag(String SheetName) {
		PurchaseBills = excelUtils.readAllDataInExcel(filepath, SheetName);
		for (int i = 4; i < PurchaseBills.length; i++) {
			String ItemID = PurchaseBills[i][30];
			String OrdQty = PurchaseBills[i][35];
			String UnitPrice = PurchaseBills[i][36];
			String TDamt = PurchaseBills[i][42];
			String SDamt = PurchaseBills[i][44];
			String CDamt = PurchaseBills[i][46];
			String CgstField = PurchaseBills[i][52];
			String SgstField = PurchaseBills[i][53];
			String UTgstField = PurchaseBills[i][55];
			String IgstField = PurchaseBills[i][54];
			String NetAmount = PurchaseBills[i][58];
			if (!ItemID.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsItemDesc")), ItemID);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsInvQty")), OrdQty);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsUnitCost")), UnitPrice);
//				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
//						commonFunc.getDataFromTestDataSheet("PurchasebillsTradeDiscValue")), TDamt);
//				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoGrnNumber(),
//						commonFunc.getDataFromTestDataSheet("PurchaseBillsTaxPercent")), TaxPercent);
//				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
//						commonFunc.getDataFromTestDataSheet("PurchaseBillsSplDiscAmount")), SDamt);
//				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
//						commonFunc.getDataFromTestDataSheet("PurchaseBillsCashDiscAmount")), CDamt);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsCgstAmount")), CgstField);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsSgstAmount")), SgstField);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsUTGSTAmount")), UTgstField);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsEx(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsIgstAmount")), IgstField);
//				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsExHeader(VariableHandler.getScoPurchaseNumber(),
//						commonFunc.getDataFromTestDataSheet("Purchase_Bills_NetAmount")), NetAmount);
			}
		}
	}

	/**
	 * This Method Is Used To Db Valid Field Wise
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */
	public void verifyLineDetailsWithDBvalidationInGrnPurchaseType(String SheetName) {
		PurchaseBills = excelUtils.readAllDataInExcel(filepath, SheetName);
		for (int i = 4; i < PurchaseBills.length; i++) {
			String SeqNumber = Integer.toString(i - 3);
			String ItemID = PurchaseBills[i][1];
			String OrdQty = PurchaseBills[i][7];
			String UnitPrice = PurchaseBills[i][8];
			String GlAccount = PurchaseBills[i][31];
			if (!ItemID.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsItemID")),
						ItemID);
				logs.logging("INFO", "ItemID IS Matched With DB Values For This Item " + ItemID);

				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsInvQty")),
						OrdQty);
				logs.logging("INFO", "OrderQty IS Matched With DB Values For This Item " + OrdQty);

				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsUnitCost")),
						UnitPrice);
				logs.logging("INFO", "UNITPRICE IS Matched With DB Values For This Item " + UnitPrice);

				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsGlAccount")),
						GlAccount);
				logs.logging("INFO", "GlAccount IS Matched With DB Values For This Item " + GlAccount);

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Is Method Used To DB Valid a Grn Purchase Type With Out Expenses Type
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */
	public void verifyDbValidationForGrnPurchaseWithOutExpenses(String SheetName) {
		PurchaseBills = excelUtils.readAllDataInExcel(filepath, SheetName);
		for (int i = 4; i < PurchaseBills.length; i++) {
			String SeqNumber = Integer.toString(i - 3);
			String ItemID = PurchaseBills[i][1];
			String OrdQty = PurchaseBills[i][6];
			String UnitPrice = PurchaseBills[i][7];
			String GlAccount = PurchaseBills[i][29];
			String TradePercent = PurchaseBills[i][9];
			String SplPercent = PurchaseBills[i][10];
			String CashPercent = PurchaseBills[i][11];
			String TradeDiscAmt = PurchaseBills[i][13];
			String SPLDiscAmt = PurchaseBills[i][15];
			String CashDiscAmt = PurchaseBills[i][17];
			if (!ItemID.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsItemID")),
						ItemID);
				logs.logging("INFO", "ItemID IS Matched With DB Values For This Item " + ItemID);

				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsInvQty")),
						OrdQty);
				logs.logging("INFO", "OrderQty IS Matched With DB Values For This Item " + OrdQty);

				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsUnitCost")),
						UnitPrice);
				logs.logging("INFO", "UNITPRICE IS Matched With DB Values For This Item " + UnitPrice);

				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsGlAccount")),
						GlAccount);
				logs.logging("INFO", "GlAccount IS Matched With DB Values For This Item " + GlAccount);

//				verifyAssertionBothValues(
//						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
//								commonFunc.getDataFromTestDataSheet("PurchaseBillsTdDiscPct")),
//						TradePercent);
//				logs.logging("INFO", "TradeDiscPercent IS Matched With DB Values For This Item " + TradePercent);

//				verifyAssertionBothValues(
//						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
//								commonFunc.getDataFromTestDataSheet("PurchaseBillsSdDiscPct")),
//						SplPercent);
//				logs.logging("INFO", "Special Disc Percent IS Matched With DB Values For This Item " + SplPercent);

//				verifyAssertionBothValues(
//						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
//								commonFunc.getDataFromTestDataSheet("purchaseBillsCashDiscPct")),
//						CashPercent	);
//				logs.logging("INFO", "Cash Disc Percent IS Matched With DB Values For This Item " + CashPercent);

//				verifyAssertionBothValues(
//						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
//								commonFunc.getDataFromTestDataSheet("PurchasebillsTradeDiscValue")),
//						TradeDiscAmt);
//				logs.logging("INFO", "Trade Disc Amount IS Matched With DB Values For This Item " + TradeDiscAmt);

//				verifyAssertionBothValues(
//						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
//								commonFunc.getDataFromTestDataSheet("PurchaseBillsSplDiscAmount")),
//						SPLDiscAmt);
//				logs.logging("INFO", "Special Disc Amount IS Matched With DB Values For This Item " + SPLDiscAmt);

//				verifyAssertionBothValues(
//						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
//								commonFunc.getDataFromTestDataSheet("PurchaseBillsCashDiscAmount")),
//						CashDiscAmt);
//				logs.logging("INFO", "Special Disc Amount IS Matched With DB Values For This Item " + CashDiscAmt);

			}
		}
		assertTrue(flag);
	}

	/**
	 * Verift Tax Amount After Check a Allows Igst Flag
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */
	public void verifyTaxAmountForIgstAmountInDbValidation(String SheetName) {
		PurchaseBills = excelUtils.readAllDataInExcel(filepath, SheetName);
		for (int i = 4; i < PurchaseBills.length; i++) {
			String SeqNumber = Integer.toString(i - 3);
			String Item = PurchaseBills[i][1];
			String IgstTaxAmount = PurchaseBills[i][26];
			if (!Item.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getScoPurchaseNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsIgstAmount")),
						IgstTaxAmount);
				logs.logging("INFO", "Igst IS Matched With DB Values For This Item " + IgstTaxAmount);

			}
		}
		assertTrue(flag);
	}

	/**
	 * DataBase Validation Of Dom Property Type
	 * 
	 * @author Kowshik - RMITS
	 * @param UIFieldLocator
	 * @param ExpectedDBValue
	 */
	public void verifyAssertionDomPropertyValidation(String UIFieldLocator, String ExpectedDBValue) {
		flag = webDB.waitForElement(UIFieldLocator, ElementType.Xpath);
		if (flag) {
			String text = webDB.getDomPropertyFromElement(UIFieldLocator, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get Text Actual UI Value Is: " + text);
			logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
			flag = text.equals(ExpectedDBValue);
			if (flag) {
				logs.logging("INFO",
						"Verified Actual UI Value : " + text + " With Expected DataBase Value :" + ExpectedDBValue);
			} else {
				logs.logging("INFO", "Verification Failed Expected DataBase Value : " + ExpectedDBValue
						+ "Get Text Actual UI Value : " + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * DataBase Validation Of Both Value Type
	 * 
	 * @author Kowshik - RMITS
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("Info", "Actual Value Is " + Actual);
		logs.logging("Info", "Expected Value Is " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("Info", "Verified Actual And Expected Values");
		} else {
			logs.logging("INFO", "Mismatch With Actual And Expected");
		}
		assertTrue(flag);
	}

	/**
	 * DataBase Validation Of Text Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Location
	 * @param ExpectedDBValue
	 */
	public void verifyAssertionGetTextValidation(String Location, String ExpectedDBValue) {
		flag = webDB.waitForElement(Location, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(Location, ElementType.Xpath);
			logs.logging("INFO", "Get Text Actual UI Value Is: " + text);
			logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
			flag = text.equals(ExpectedDBValue);
			if (flag) {
				logs.logging("INFO",
						"Verified Actual UI Value : " + text + " With Expected DataBase Value :" + ExpectedDBValue);
			} else {
				logs.logging("INFO", "Verification Failed Expected DataBase Value : " + ExpectedDBValue
						+ "Get Text Actual UI Value : " + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Header Details Of PurchaseBills
	 * 
	 * @author Kowshik-RMITS
	 */
	public void verifyHeaderValidationForPurchaseBillsWithDbConnections() {
		verifyAssertionGetTextValidation(PurchaseBillsLocators.LocationIdField,
				dbCommonFunc.PurchaseBillsExHeader(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsLocationId")));
		logs.logging("INFO", "Header Location Matches With DataBase ");

		verifyAssertionDomPropertyValidation(PurchaseBillsLocators.UnitIdField, dbCommonFunc.PurchaseBillsExHeader(
				VariableHandler.getScoPurchaseNumber(), commonFunc.getDataFromTestDataSheet("PurchaseBillsUnitId")));
		logs.logging("INFO", "Header Unit Id Is Matches With DataBase");

		verifyAssertionDomPropertyValidation(PurchaseBillsLocators.SupplierDescField,
				dbCommonFunc.PurchaseBillsExHeader(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("PurchaseBillsSupplierDesc")));
		logs.logging("INFO", "Header Supplier Matches With DataBase");
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticServiceSimilarUOMMultiple(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String UNIT_COST = itemmultiple[i][6];
			String HSN = itemmultiple[i][68];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				POfunc.verifyDirectPoItemAddIconClicked();
				POfunc.verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				POfunc.verifyDirectPoHsnSelectedFromLov(HSN);
				POfunc.verifyDirectPoQtyEntered(QUANTITY);
				POfunc.verifyDirectPoUnitCostEntered(UNIT_COST);
				// POfunc.verifyPoWithGrnServiceFlagChecked();
				POfunc.verifyDirectPoLineWiseSaved();
				POfunc.discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticServiceSimilarUOMMultipleLocalUnregrd(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Purchase_Bills_UnRegrd"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String UNIT_COST = itemmultiple[i][6];
			String HSN = itemmultiple[i][68];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				POfunc.verifyDirectPoItemAddIconClicked();
				POfunc.verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				POfunc.verifyDirectPoHsnSelectedFromLov(HSN);
				POfunc.verifyDirectPoQtyEntered(QUANTITY);
				POfunc.verifyDirectPoUnitCostEntered(UNIT_COST);
				// POfunc.verifyPoWithGrnServiceFlagChecked();
				POfunc.verifyDirectPoLineWiseSaved();
				POfunc.discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticServiceSimilarUOMMultipleForInterState(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Purchase_Suppler_Interstate"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String UNIT_COST = itemmultiple[i][6];
			String HSN = itemmultiple[i][68];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				POfunc.verifyDirectPoItemAddIconClicked();
				POfunc.verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				POfunc.verifyDirectPoHsnSelectedFromLov(HSN);
				POfunc.verifyDirectPoQtyEntered(QUANTITY);
				POfunc.verifyDirectPoUnitCostEntered(UNIT_COST);
				// POfunc.verifyPoWithGrnServiceFlagChecked();
				POfunc.verifyDirectPoLineWiseSaved();
				POfunc.discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticServiceSimilarUOMMultipleLocalGetDocument(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Purchase_Doc"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String UNIT_COST = itemmultiple[i][6];
			String HSN = itemmultiple[i][68];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				POfunc.verifyDirectPoItemAddIconClicked();
				POfunc.verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				POfunc.verifyDirectPoHsnSelectedFromLov(HSN);
				POfunc.verifyDirectPoQtyEntered(QUANTITY);
				POfunc.verifyDirectPoUnitCostEntered(UNIT_COST);
				// POfunc.verifyPoWithGrnServiceFlagChecked();
				POfunc.verifyDirectPoLineWiseSaved();
				POfunc.discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticServiceSimilarUOMMultipleInterststeGetDocument(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Purchase_Doc_InterState"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String UNIT_COST = itemmultiple[i][6];
			String HSN = itemmultiple[i][68];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				POfunc.verifyDirectPoItemAddIconClicked();
				POfunc.verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				POfunc.verifyDirectPoHsnSelectedFromLov(HSN);
				POfunc.verifyDirectPoQtyEntered(QUANTITY);
				POfunc.verifyDirectPoUnitCostEntered(UNIT_COST);
				// POfunc.verifyPoWithGrnServiceFlagChecked();
				POfunc.verifyDirectPoLineWiseSaved();
				POfunc.discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticServiceSimilarUOMMultipleUnregdLocalGetDocument(String SheetName) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Purchase_Local_Unregd"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String UNIT_COST = itemmultiple[i][6];
			String HSN = itemmultiple[i][68];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				POfunc.verifyDirectPoItemAddIconClicked();
				POfunc.verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				POfunc.verifyDirectPoHsnSelectedFromLov(HSN);
				POfunc.verifyDirectPoQtyEntered(QUANTITY);
				POfunc.verifyDirectPoUnitCostEntered(UNIT_COST);
				// POfunc.verifyPoWithGrnServiceFlagChecked();
				POfunc.verifyDirectPoLineWiseSaved();
				POfunc.discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
//		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticServiceSimilarUOMMultipleUnregdInterstateGetDocument(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Purchase_Interstate_Unregd"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String UNIT_COST = itemmultiple[i][6];
			String HSN = itemmultiple[i][68];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				POfunc.verifyDirectPoItemAddIconClicked();
				POfunc.verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				POfunc.verifyDirectPoHsnSelectedFromLov(HSN);
				POfunc.verifyDirectPoQtyEntered(QUANTITY);
				POfunc.verifyDirectPoUnitCostEntered(UNIT_COST);
				// POfunc.verifyPoWithGrnServiceFlagChecked();
				POfunc.verifyDirectPoLineWiseSaved();
				POfunc.discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		POfunc.verifyHeaderTickAndPost();
		POfunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * Verify Insert a Bill Date
	 * 
	 * @author Kowshik-RMITS
	 * @param TodayDate
	 */
	public void verifyInsertOfBillDate(String TodayDate, String Billno) {
		flag = webDB.waitForElement(PurchaseBillsLocators.BillDate, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PurchaseBillsLocators.BillDate, TodayDate, ElementType.Xpath);
			logs.logging("INFO", "Bill Date Is Inserted " + TodayDate);
			flag = webDB.waitForElement(PurchaseBillsLocators.BillNumber, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocators.BillNumber, ElementType.Xpath);
				webDB.sendTextToAnElement(PurchaseBillsLocators.BillNumber, Billno, ElementType.Xpath);
				logs.logging("INFO", "Bill Number Is Genrenated");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButtonHeader() {
		flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "SAVE Button Is Clicked");
		}
		assertTrue(flag);
	}

	/**
	 * Valid Allows Igst Check Box Tax Amount Calulation
	 * 
	 * @author Kowshik-RMITS
	 */
	public void validAllowIgstCheckboxForTaxAmountCalculation() {
//		flag = webDB.waitForElement(PurchaseBillsLocators.AllowIgstCheckFlag, ElementType.Xpath);
		if (flag) {
			boolean isIgstChecked = webDB.isElementSelected(PurchaseBillsLocators.AllowIgstCheckFlag,
					ElementType.Xpath);
			if (isIgstChecked) {
				logs.logging("INFO", "IGST checkbox is checked – Tax calculated by IGST");
				String IgstAmount = webDB.getTextFromElement(PurchaseBillsLocators.IgstAmountField, ElementType.Xpath);
				logs.logging("INFO", "Tax Amount" + IgstAmount);
			} else {
				String CgstAmount = webDB.getTextFromElement(PurchaseBillsLocators.CgstAmountField, ElementType.Xpath);
				logs.logging("INFO", "Tax Amount" + CgstAmount);
				commonFunc.checkRefreshCellsDisappear();
				String SgstAmount = webDB.getTextFromElement(PurchaseBillsLocators.SgstAmountField, ElementType.Xpath);
				logs.logging("INFO", "Tax Amount" + SgstAmount);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Get Document From Purchase Service Type
	 * 
	 * @author Kowshik-RMITS
	 * @param PoDoc
	 */
	public void clickGetDocumentFromPendingPoScreen(String PoDoc) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.GetDocument, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsLocators.GetDocument, ElementType.Xpath);
			webDB.clickAnElement(PurchaseBillsLocators.GetDocument, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.GetDocPendingPo, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Get Doc Button Is Clicked & Successfully Entered Iframe");
				webDB.switchToFrame(PurchaseBillsLocators.GetDocPendingPo, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseDocInputField, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(PurchaseBillsLocators.PurchaseDocInputField, PoDoc, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseBillsLocators.SearchButtonGetDoc, ElementType.Xpath);
					logs.logging("INFO", "SuccessFully Inserted Po Document" + PoDoc);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocators.SearchButtonGetDoc, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocators.CheckBoxPendingPo, ElementType.Xpath);
						logs.logging("INFO", "Search Button Is Checked");
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocators.CheckBoxPendingPo, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocators.OkButtonPendingPO, ElementType.Xpath);
							logs.logging("INFO", "Check Box Is Clicked ");
							if (flag) {
								commonFunc.checkRefreshCellsDisappear();
								webDB.clickAnElement(PurchaseBillsLocators.OkButtonPendingPO, ElementType.Xpath);
								logs.logging("INFO", "Purchase Order Service Item Lines In Inserted");
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.hardWait(1000);
							}
						}

					}

				}

			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	public void verifyComparePoDocAndPurchaseBillsWithDb(int i2, String SheetName) {
		PurchaseBills = excelUtils.readAllDataInExcel(filepath, SheetName);
		for (int i = i2; i <= i2; i++) {

		}

	}

	/**
	 * This Methods Is Used To verify GrnDocument Compare to PO document Details
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyPurchaseBillsDocumentCompareToPoDocument(String SheetName) {
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PRMultipleline.length; i++) {
			String ItemId = PRMultipleline[i][1];
			if (!ItemId.equals("")) {
				System.out.println("tradevalueup");
				verifyAssertionBothValues(
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("TradeDiscountAmount")),
						dbCommonFunc.PurchaseBillsLineWise(VariableHandler.getScoPurchaseNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchasebillsTradeDiscValue")));
				logs.logging("INFO", "Trade Discount Amount Matched For PO and PurchaseBills " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoUnitCost")),
						dbCommonFunc.PurchaseBillsLineWise(VariableHandler.getScoPurchaseNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsUnitCost")));
				logs.logging("INFO", "Unit Cost Value Matched For PO and Purchase Bills " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("POL_SPL_DISC_AMT")),
						dbCommonFunc.PurchaseBillsLineWise(VariableHandler.getScoPurchaseNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsSplDiscAmount")));
				logs.logging("INFO", "Spl Discount Amount Matched For PO and Purchase Bills " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("POL_CASH_DISC_AMT")),
						dbCommonFunc.PurchaseBillsLineWise(VariableHandler.getScoPurchaseNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsCashDiscAmount")));
				logs.logging("INFO", "Cash Disc Amount Matched For PO and Purchase Bills " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("POL_TAX_PCT")),
						dbCommonFunc.PurchaseBillsLineWise(VariableHandler.getScoPurchaseNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsTaxPercent")));
				logs.logging("INFO", "Tax Percent Matched For PO and Purchase Bills " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoIgstAmount")),
						dbCommonFunc.PurchaseBillsLineWise(VariableHandler.getScoPurchaseNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsIgstAmount")));
				logs.logging("INFO", "IGST Amount Matched For PO and Purchase Bills " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("POL_CGST_AMT")),
						dbCommonFunc.PurchaseBillsLineWise(VariableHandler.getScoPurchaseNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsCgstAmount")));
				logs.logging("INFO", "CGST Amount Matched For PO and Purchase Bills " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoSgstAmount")),
						dbCommonFunc.PurchaseBillsLineWise(VariableHandler.getScoPurchaseNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsSgstAmount")));
				logs.logging("INFO", "SGST Amount Matched For PO and Purchase Bills " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoUtgstAmount")),
						dbCommonFunc.PurchaseBillsLineWise(VariableHandler.getScoPurchaseNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillsUTGSTAmount")));
				logs.logging("INFO", "UTGST Amount Matched For PO and Purchase Bills " + ItemId);

			}
		}
	}

	/**
	 * Click View Button For Bank Payments
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickViewButton() {
		flag = webDB.waitForElement(PurchaseBillsLocators.ViewButton, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.ViewButton, ElementType.CssSelector);
			flag = webDB.waitForElement(PurchaseBillsLocators.ViewButtonFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Payment Line Wise View Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Purchase Bills Against For Adjustment
	 * 
	 * @author Kowshik-RMITS
	 * @param BankName
	 * @param Currency
	 * @param SheetName
	 */
	public void verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(String BankName, String Currency,
			String SheetName) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				System.getProperty("KowshikUserName"));
		commonFunc.navigateToFinancialPaymentReceipt();
		BPFunc.clickAddButtonInPaymentsRecipts();
		BPFunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BPFunc.getBankPaymentOrReceiptDocumentNumber();
		BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		BPFunc.verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today,
				Today);
		BPFunc.verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(KowshikFin, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				BPFunc.clickBPLineAddButton();
				BPFunc.addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				BPFunc.clickLineWiseSaveButton();
				clickViewButton();

			}

		}

	}

	/**
	 * Added New Ref Against To The Party For Adjustment Amount
	 * 
	 * @author Kowshik-RMITS
	 * @param BillsBank
	 * @param Payamount
	 */
	public void addNewRefPaymentsAgainstTotheParty(String BillsBank, String Payamount) {
		String BillsBank1 = RandomStringUtils.randomNumeric(6);
		if (BillsBank.equals("000")) {
			BillsBank = "10";
		}
		webDB.moveToElement(PurchaseBillsLocators.Payment_LineDetails_Btn, ElementType.Xpath);
		webDB.clickAnElement(PurchaseBillsLocators.Payment_LineDetails_Btn, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocators.Payment_ViewButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.Payment_ViewButton, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(PurchaseBillsLocators.ViewButtonFrame, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(PurchaseBillsLocators.ViewButtonFrame, ElementType.Xpath);
				logs.logging("INFO", "Successfully Navigates Frame");
				flag = webDB.waitForElement(PurchaseBillsLocators.AddButtonViewTap, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocators.AddButtonViewTap, ElementType.Xpath);
					logs.logging("INFO", "Successfully Clicked a Add Button");
					flag = webDB.waitForElement(PurchaseBillsLocators.BillDocField, ElementType.Xpath);
					if (flag) {
						webDB.pressTabKey();
						commonFunc.hardWait(2000);
						SalesordFunc.verifyClickAndEnterValueInTableFields(PurchaseBillsLocators.BillDocField,
								PurchaseBillsLocators.BillDocNo, BillsBank1);
						logs.logging("INFO", "Successfully Inserted a Bills Number Against To The Banks" + BillsBank1);
						flag = webDB.waitForElement(PurchaseBillsLocators.PayamountField, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(2000);
							SalesordFunc.verifyClickAndEnterValueInTableFields(PurchaseBillsLocators.PayamountField,
									PurchaseBillsLocators.PayAmt, Payamount);
							logs.logging("INFO", "Successfully Inserted a Adjustment Amount");
							flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonView, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsLocators.SaveButtonView, ElementType.Xpath);
								webDB.switchToDefaultContent();
								commonFunc.hardWait(500);
								flag = webDB.waitForElement(PurchaseBillsLocators.CloseButton, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsLocators.CloseButton, ElementType.Xpath);
									logs.logging("INFO", "Successfully Clicked a Close Button");
									commonFunc.hardWait(1000);

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
	 * Click Post Button Approval Record
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPostButtonApprovalRecordBankPayments1() {
		BPFunc.clickPostButton();
		BPFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
//			commonFunc.logoutUser();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
//		commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * Loaded Adjustment Supplier To Added In Party
	 * 
	 * @author Kowshik-RMITS
	 */
	public void loadAdjustmentForSupplierToAddedInParty(String BankpaymentNo) {
		flag = webDB.waitForElement(PurchaseBillsLocators.AdjButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.AdjButton, ElementType.Xpath);
			logs.logging("INFO", "Successfully Moves To Adjustmnet Tap");
			flag = webDB.waitForElement(PurchaseBillsLocators.LoadButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocators.LoadButton, ElementType.Xpath);
				logs.logging("INFO", "Successfully Click a Load Button");
				flag = webDB.waitForElement(PurchaseBillsLocators.FrameAdjTap, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(PurchaseBillsLocators.FrameAdjTap, ElementType.Xpath);
					logs.logging("INFO", "Successfully Moves To Frame");
					flag = webDB.waitForElement(PurchaseBillsLocators.PB_Adj_InputField_Btn, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(PurchaseBillsLocators.PB_Adj_InputField_Btn, BankpaymentNo,
								ElementType.Xpath);
						commonFunc.hardWait(1000);
						webDB.clickAnElement(PurchaseBillsLocators.PB_Adj_InputGO_Btn, ElementType.Xpath);
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(PurchaseBillsLocators.CheckBoxAdjTap, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocators.CheckBoxAdjTap, ElementType.Xpath);
							logs.logging("INFO", "Successfully Clicked a CheckBox Of Adjustment ");
							webDB.waitForElement(PurchaseBillsLocators.OkbuttonAdjTap, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(500);
								webDB.clickAnElement(PurchaseBillsLocators.OkbuttonAdjTap, ElementType.Xpath);
								logs.logging("INFO", "Successfully Clicked Ok Button");
								webDB.switchToDefaultContent();
								commonFunc.checkRefreshCellsDisappear();
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Enter Realized Amount In Header
	 * 
	 * @author Kowshik-RMITS
	 * @param RealizedAmt
	 */
	public void enterRealizedAmountInHeader(String RealizedAmt) {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptRealizedAmtInputField, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(BankReceiptLocators.BankReceiptRealizedAmtInputField, ElementType.Xpath);
			webDB.sendTextToAnElement(BankReceiptLocators.BankReceiptRealizedAmtInputField, RealizedAmt,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Realized Amount " + RealizedAmt + " Is Entered Successfully");
				enterSaveButtonInBankRecipt();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void enterSaveButtonInBankRecipt() {
		flag = webDB.waitForElement(BankReceiptLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.SaveButton, ElementType.Xpath);
			logs.logging("INFO", "Save Button is Clicked");
		}
		assertTrue(flag);
	}

	// TO DO KOWSHIK
	/**
	 * Click Post Button Approval Record
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPostButtonApprovalRecordBankPayments() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(200);
		BPFunc.clickPostButton();
		BPFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
//			commonFunc.logoutUser();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
//		commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * Loaded Adjustment Supplier To Added In Party
	 * 
	 * @author Kowshik-RMITS
	 */
	public void loadAdjustmentForSupplierToAddedInParty() {
		flag = webDB.waitForElement(PurchaseBillsLocators.AdjButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.AdjButton, ElementType.Xpath);
			logs.logging("INFO", "Successfully Moves To Adjustmnet Tap");
			flag = webDB.waitForElement(PurchaseBillsLocators.LoadButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocators.LoadButton, ElementType.Xpath);
				logs.logging("INFO", "Successfully Click a Load Button");
				flag = webDB.waitForElement(PurchaseBillsLocators.FrameAdjTap, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(PurchaseBillsLocators.FrameAdjTap, ElementType.Xpath);
					logs.logging("INFO", "Successfully Moves To Frame");
					flag = webDB.waitForElement(PurchaseBillsLocators.CheckBoxAdjTap, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocators.CheckBoxAdjTap, ElementType.Xpath);
						logs.logging("INFO", "Successfully Clicked a CheckBox Of Adjustment ");
						webDB.waitForElement(PurchaseBillsLocators.OkbuttonAdjTap, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(500);
							webDB.clickAnElement(PurchaseBillsLocators.OkbuttonAdjTap, ElementType.Xpath);
							logs.logging("INFO", "Successfully Clicked Ok Button");
							webDB.switchToDefaultContent();
						}
					}

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Get Advance Paymnet From DB
	 * 
	 * @author Kowshik-RMITS
	 */
	public void getAdvPaymentDocumentFromDatabase() {
		String poAdvanceDetails = dbCommonFunc.POAdvanceDetails(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("POAdvDBColumnDocument"));
		VariableHandler.setAdvPaymentDocumentno(poAdvanceDetails);
		logs.logging("INFO",
				"PO against to the Advance Document is Created " + VariableHandler.getAdvPaymentDocumentno());
	}

	/**
	 * To Validate Advance Amount From Pending Screen
	 * 
	 * @author Kowshik-RMITS
	 * @param Sheetname
	 */
	public void toValidatePendingAmountValueForPurchaseOrder(String Sheetname) {
		PurchaseBills = excelUtils.readAllDataInExcel(KowshikFin, Sheetname);
		for (int i = 1; i <= PurchaseBills.length; i++) {
			String AdvanceAmount = PurchaseBills[i][4];
			if (!AdvanceAmount.equals("")) {
				commonFunc.hardWait(3000);
//				verifyAssertionBothValues(dbCommonFunc
//						.PendingSubtotalForPendingPayables(VariableHandler.getAdvPaymentDocumentno(), AdvanceAmount),
//						commonFunc.getDataFromTestDataSheet("AdvancePayAmt");
				verifyAssertionBothValues(
						dbCommonFunc.PendingSubtotalForPendingPayables(VariableHandler.getAdvPaymentDocumentno(),
								commonFunc.getDataFromTestDataSheet("AdvancePayAmt")),
						AdvanceAmount);

			}
		}
		assertTrue(flag);
	}

	public void verifyLineDetailsDbValidationWithTDSsupply(String Sheetname) {
		PurchaseBills = excelUtils.readAllDataInExcel(KowshikFin, Sheetname);
		for (int i = 22; i < PurchaseBills.length; i++) {
			String SeqNumber = Integer.toString(i - 21);
			String ItemID = PurchaseBills[i][1];
			String OrdQty = PurchaseBills[i][6];
			String UnitPrice = PurchaseBills[i][7];
			String Glaccount = PurchaseBills[i][3];
			if (!ItemID.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsDB(VariableHandler.getScoPurchaseNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PurchaseBillsItemID")), ItemID);
				logs.logging("INFO", "ItemID IS Matched With DB Values For This Item " + ItemID);

				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsDB(VariableHandler.getScoPurchaseNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PurchaseBillsInvQty")), OrdQty);
				logs.logging("INFO", "ItemID IS Matched With DB Values For This Item " + OrdQty);

				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsDB(VariableHandler.getScoPurchaseNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PurchaseBillsUnitCost")), UnitPrice);
				logs.logging("INFO", "ItemID IS Matched With DB Values For This Item " + UnitPrice);

				verifyAssertionBothValues(dbCommonFunc.PurchaseBillsDB(VariableHandler.getScoPurchaseNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PurchaseBillsGlAccount")), Glaccount);
				logs.logging("INFO", "ItemID IS Matched With DB Values For This Item " + Glaccount);
			}

		}
	}

	/**
	 * Click Pending Payables Advance Buttons
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPendingpayablesAdvance() {
		flag = webDB.waitForElement(PurchaseBillsLocators.pendigPayableAdv, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.pendigPayableAdv, ElementType.Xpath);
			logs.logging("INFO", "Click a Pending Payables Adv Button");
		}
		assertTrue(flag);
	}

	/**
	 * Selecting Documnet From Pending Payables for Advance Against to the Party
	 * 
	 * @author Kowshik-RMITS
	 */
	public void selectDocumentFromPendingPayablesAdv() {
		flag = webDB.waitForElement(PurchaseBillsLocators.framePendingPayablesBP, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseBillsLocators.framePendingPayablesBP, ElementType.Xpath);
			logs.logging("INFO", "Successfully Naviagtes a Frame");
			flag = webDB.waitForElement(PurchaseBillsLocators.SearchButton1, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocators.SearchButton1, ElementType.Xpath);
				logs.logging("INFO", "Sucessfully Clicked a Search Button");
				flag = webDB.waitForElement(PurchaseBillsLocators.DocNoInput, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(PurchaseBillsLocators.DocNoInput,
							VariableHandler.getAdvPaymentDocumentno(), ElementType.Xpath);
					// VariableHandler.getAdvPaymentDocumentno()
					logs.logging("INFO", "Successfully Passed a Advance Document No");
					flag = webDB.waitForElement(PurchaseBillsLocators.SearchButton1, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocators.SearchButton1, ElementType.Xpath);
						logs.logging("INFO", "Sucessfully Clicked a Search Button");
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocators.SelectAllFlag, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocators.SelectAllFlag, ElementType.Xpath);
							logs.logging("INFO", "Document is Selected ");
							flag = webDB.waitForElement(PurchaseBillsLocators.CreateButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsLocators.CreateButton, ElementType.Xpath);
								logs.logging("info", "Successfully Created a Document From Pending Screen");
								// webDB.switchToDefaultContent();
							}
						}
					}
				}
			}

		}
		assertTrue(flag);
	}

	public void clickCreateButtonForPendingBp() {
		flag = webDB.waitForElement(PurchaseBillsLocators.CreateButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.CreateButton, ElementType.Xpath);
			logs.logging("info", "Successfully Created a Document From Pending Screen");
		}
		assertTrue(flag);
	}

	public void selectBankModeOfPayment(String BankDesc, String neftSelect, String BankName) {
		flag = webDB.waitForElement(PurchaseBillsLocators.VouSelect, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByValue(PurchaseBillsLocators.VouSelect, BankDesc, ElementType.Xpath);
			logs.logging("INFO", "Successully Selected a Bank ");
			flag = webDB.waitForElement(PurchaseBillsLocators.ModeSelect, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByValue(PurchaseBillsLocators.ModeSelect, neftSelect, ElementType.Xpath);
				logs.logging("INFO", "Successfully Selected a Mode");
				flag = webDB.waitForElement(PurchaseBillsLocators.BankLov, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.BankLov, BankName);
					logs.logging("INFO", "Successfully Inserted a Bank Name " + BankName);
					// flag = webDB.waitForElement(PurchaseBillsLocators.VouSelect,
					// ElementType.Xpath);
					// if(flag) {
					// webDB.clickAnElement(PurchaseBillsLocators.VouSelect, ElementType.Xpath);
					webDB.switchToFrame(PurchaseBillsLocators.framePendingPayablesBP, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseBillsLocators.Okbuttonbankpay, ElementType.Xpath);
					if (flag) {
						// webDB.switchToFrame(PurchaseBillsLocators.framePendingPayablesBP,
						// ElementType.Xpath);
						webDB.clickAnElement(PurchaseBillsLocators.Okbuttonbankpay, ElementType.Xpath);
						logs.logging("info", "Successfully Created a Bank Payment Document");

						// }
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	public void bankPaymentRecordOfAgainstPo() {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				System.getProperty("KowshikUserName"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymntFunc.clickPendingCRDocsButton();
		clickPendingpayablesAdvance();
		selectDocumentFromPendingPayablesAdv();
		selectBankModeOfPayment(commonFunc.getDataFromTestDataSheet("BankDesc"),
				commonFunc.getDataFromTestDataSheet("neftSelect"), commonFunc.getDataFromTestDataSheet("BankName"));
		bankpaymntFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		webDB.pressTabKey();
		bankpaymntFunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymntFunc.clickCreateJournalButton();
		bankpaymntFunc.clickPostButton();
		flag = bankpaymntFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			commonFunc.logoutUser();
		}
	}

	/**
	 * Bank Payment for Advance Amount With New Ref
	 * 
	 * @author Kowshik-RMITS
	 */

	public void bankPaymentRecordOfAgainstPoWithAddedNewRef() {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				System.getProperty("KowshikUserName"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymntFunc.clickPendingCRDocsButton();
		clickPendingpayablesAdvance();
		selectDocumentFromPendingPayablesAdv();
		selectBankModeOfPayment(commonFunc.getDataFromTestDataSheet("BankDesc"),
				commonFunc.getDataFromTestDataSheet("neftSelect"), commonFunc.getDataFromTestDataSheet("BankName"));
		// bankpaymntFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference
		// Number"));
		webDB.pressTabKey();
		bankpaymntFunc.getBankPaymentOrReceiptDocumentNumber();
		clickViewButton();
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		bankpaymntFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		bankpaymntFunc.clickCreateJournalButton();
		bankpaymntFunc.clickPostButton();
		flag = bankpaymntFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			commonFunc.logoutUser();
		}
	}

	/**
	 * Bank Payment for Advance Amount With New Ref
	 * 
	 * @author Kowshik-RMITS
	 */

	public void bankPaymentRecordOfAgainstPoWithAddedNewRefWithMultiRef() {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				System.getProperty("KowshikUserName"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymntFunc.clickPendingCRDocsButton();
		clickPendingpayablesAdvance();
		selectDocumentFromPendingPayablesAdv();
		selectBankModeOfPayment(commonFunc.getDataFromTestDataSheet("BankDesc"),
				commonFunc.getDataFromTestDataSheet("neftSelect"), commonFunc.getDataFromTestDataSheet("BankName"));
		// bankpaymntFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference
		// Number"));
		webDB.pressTabKey();
		bankpaymntFunc.getBankPaymentOrReceiptDocumentNumber();
		clickViewButton();
		multiNewRefForAnBankPayment(webDB.getDataFromProperties("BankPayment_Multi_New"));
		bankpaymntFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		bankpaymntFunc.clickCreateJournalButton();
		bankpaymntFunc.clickPostButton();
		flag = bankpaymntFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			commonFunc.logoutUser();
		}
	}

	/**
	 * Bank Payment For Against to the Multiple PO Creation
	 * 
	 * @author Kowshik-RMITS
	 */
	public void bankPaymentRecordOfAgainstWithMutiPo() {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				System.getProperty("KowshikUserName"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymntFunc.clickPendingCRDocsButton();
		clickPendingpayablesAdvance();
		selectMultiplePoDocForAnPaymentInPendingPayablesAdvance(VariableHandler.getMultiplePoDocs());
		clickCreateButtonForPendingBp();
		selectBankModeOfPayment(commonFunc.getDataFromTestDataSheet("BankDesc"),
				commonFunc.getDataFromTestDataSheet("neftSelect"), commonFunc.getDataFromTestDataSheet("BankName"));
		webDB.pressTabKey();
		bankpaymntFunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymntFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		bankpaymntFunc.clickCreateJournalButton();
		bankpaymntFunc.clickPostButton();
		flag = bankpaymntFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			commonFunc.logoutUser();
		}
	}

	/**
	 * Bank Payment For Against to the Multiple PO Creation Against to the Single
	 * Ref
	 * 
	 * @author Kowshik-RMITS
	 */
	public void bankPaymentRecordOfAgainstWithMutiPoSingleNewRef() {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				System.getProperty("KowshikUserName"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymntFunc.clickPendingCRDocsButton();
		clickPendingpayablesAdvance();
		selectMultiplePoDocForAnPaymentInPendingPayablesAdvance(VariableHandler.getMultiplePoDocs());
		clickCreateButtonForPendingBp();
		selectBankModeOfPayment(commonFunc.getDataFromTestDataSheet("BankDesc"),
				commonFunc.getDataFromTestDataSheet("neftSelect"), commonFunc.getDataFromTestDataSheet("BankName"));
		webDB.pressTabKey();
		bankpaymntFunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		clickViewButton();
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		bankpaymntFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		bankpaymntFunc.clickCreateJournalButton();
		bankpaymntFunc.clickPostButton();
		flag = bankpaymntFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			commonFunc.logoutUser();
		}
	}

	/**
	 * Bank Paymnet Record Against With Multi Po Multi New Ref
	 * 
	 * @author Kowshik-RMITS
	 */
	public void bankPaymentRecordOfAgainstwithMultiPoMultiNewRef() {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				System.getProperty("KowshikUserName"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymntFunc.clickPendingCRDocsButton();
		clickPendingpayablesAdvance();
		selectMultiplePoDocForAnPaymentInPendingPayablesAdvance(VariableHandler.getMultiplePoDocs());
		clickCreateButtonForPendingBp();
		selectBankModeOfPayment(commonFunc.getDataFromTestDataSheet("BankDesc"),
				commonFunc.getDataFromTestDataSheet("neftSelect"), commonFunc.getDataFromTestDataSheet("BankName"));
		webDB.pressTabKey();
		bankpaymntFunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		clickViewButton();
		multiNewRefForAnBankPayment(webDB.getDataFromProperties("BankPayment_Multi_New"));
		bankpaymntFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		bankpaymntFunc.clickCreateJournalButton();
		bankpaymntFunc.clickPostButton();
		flag = bankpaymntFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			commonFunc.logoutUser();
		}
	}

	/**
	 * Verify Purchase Bills Against For Adjustment
	 * 
	 * @author Kowshik-RMITS
	 * @param BankName
	 * @param Currency
	 * @param SheetName
	 */
	public void verifyPurchaseBillsAgainstPurchaseBillsForAdjustment1(String BankName, String Currency,
			String SheetName) {

		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				System.getProperty("KowshikUserName"));
		commonFunc.navigateToFinancialPaymentReceipt();
		BPFunc.clickAddButtonInPaymentsRecipts();
		BPFunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BPFunc.getBankPaymentOrReceiptDocumentNumber();
		BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		BPFunc.verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today,
				Today);
		BPFunc.verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(KowshikFin, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				BPFunc.clickBPLineAddButton();
				BPFunc.addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				BPFunc.clickLineWiseSaveButton();
				clickViewButton();

			}

		}

	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForUnregrd() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForUnregisterK(
				webDB.getDataFromProperties("po_Unregrd_kowshik"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("po_Unregrd_kowshik"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("po_Unregrd_kowshik"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();

	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForRegrd() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForRegisterAndUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();

	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForRegrdForAdvance() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForRegisterAndUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		bankPaymentRecordOfAgainstPo();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForRegrdForAdvanceWithNewRef() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForRegisterAndUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		bankPaymentRecordOfAgainstPoWithAddedNewRef();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForRegrdForAdvanceWithNewRefinMultiRef() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForRegisterAndUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		bankPaymentRecordOfAgainstPoWithAddedNewRefWithMultiRef();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForRegrdForAdvanceWithNewRefMultiTds() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForRegisterAndUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		bankPaymentRecordOfAgainstPoWithAddedNewRef();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForUnRegrdForAdvanceWithNewRefMultiTds() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		bankPaymentRecordOfAgainstPoWithAddedNewRef();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForRegrdForAdvanceForUnregrd() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		bankPaymentRecordOfAgainstPo();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForRegrdForAdvanceForUnregrdWithNewRefSingleSection() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_UnRegrd_Single_Tds"));
		bankPaymentRecordOfAgainstPoWithAddedNewRef();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_UnRegrd_Single_Tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_UnRegrd_Single_Tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		// flag = commonFunc.logoutUser();
	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForUnRegrdForAdvanceForUnregrdWithNewMultiRefSingleSection()
			throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_UnRegrd_Single_Tds"));
		bankPaymentRecordOfAgainstPoWithAddedNewRefWithMultiRef();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_UnRegrd_Single_Tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_UnRegrd_Single_Tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		// flag = commonFunc.logoutUser();
	}

	public void selectMultipleDocumentInPendingGrn(String SheetName) {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "DBfin.xlsx";
		flag = webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		flag = webDB.switchToFrame(GrnLocators.PendingPOIframe, ElementType.Xpath);
		for (int i = 1; i < itemMultiples.length; i++) {
			String PoDocs = itemMultiples[i][3];

			if (!PoDocs.equals("")) {
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPoSearchInputField, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(PurchaseBillsLocators.PendingPoSearchInputField, PoDocs,
							ElementType.Xpath);
					logs.logging("Info", "Po Document Gets " + PoDocs);
					flag = webDB.waitForElement(PurchaseBillsLocators.SearchButtonInPendingGrn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocators.SearchButtonInPendingGrn, ElementType.Xpath);
						logs.logging("INFO", "Search Button is Clicked ");
						flag = webDB.waitForElement(PurchaseBillsLocators.SelectCheckboxAgainstPO(PoDocs),
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocators.SelectCheckboxAgainstPO(PoDocs),
									ElementType.Xpath);
							logs.logging("INFO", "CheckBox Is Selected ");
							flag = webDB.waitForElement(PurchaseBillsLocators.CreateGrnButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsLocators.CreateGrnButton, ElementType.Xpath);
								logs.logging("INFO", "Successfully Creats a Grn Doc from  Pending screen ");
							}
						}

					}
				}
			}
		}
		assertTrue(flag);
	}

	public void clickPendingPo() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			webDB.clickAnElement(GrnLocators.ClickPendingButton, ElementType.Xpath);
			logs.logging("INFO", "Pending Button Clicked");
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(GrnLocators.PendingPO, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GrnLocators.PendingPO, ElementType.Xpath);
				logs.logging("INFO", "Pending PO Button Clicked");
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GrnLocators.PendingPOIframe, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	public void creationGrnDocAgainstToTheMultiplePo() throws Exception {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.naviagteGrnPage();
		POfunc.selectFromPendingPoDocusClickPendingPo();
		POfunc.selectMultiplePoDocFromPendingGrnKowshik(VariableHandler.getMultiplePoDocs());
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));

	}

	/**
	 * Purchase to GRN Entries With Auto MRV
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void poToGrnProccedWithPurchaseBillsForRegrdForAdvanceforMultiSection() throws Exception {
		POfunc.verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForRegisterAndUnregisterKForAdvance(
				webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		bankPaymentRecordOfAgainstPo();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.naviagteGrnPage();
		grnFunc.clickPendingPoAndSearchPoNo();
		grnFunc.clickSelectAllButton();
		grnFunc.createGrn();
		grnFunc.supplierBillNumberAndDcNumber();
		grnFunc.multipleLotAndSerialItem(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		grnFunc.lotSerialQtyApportion();
		grnFunc.overAllSaveAndPost(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		grnFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();

	}

	/**
	 * Multiple Po Creation With Advance Payment for Unregrd
	 * 
	 * @author Kowshik-RMITS
	 */
	public void multiPoCreationWithApprovalForUnRegrdParty() {
		List<String> poList = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			System.out.println("Loop Running: " + i);
			POfunc.verifyDirectPoWithRateContractItemWithGeneralTypeForUnregisterKForAdvanceForMultiPo(
					webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"), (ArrayList<String>) poList);
		}
		System.out.println(poList);
	}

	public void getMultiPoDocumentAfterExcution() {
		List<String> poList = new ArrayList<>();
		String poNumberValue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + poNumberValue);
		poList.add(poNumberValue);
		VariableHandler.setMultiplePoDocs(null);

	}

	/**
	 * Multiple Po Creation With Advance Payment for Regrd Party
	 * 
	 * @author Kowshik-RMITS
	 */
	public void multiPoCreationWithAdvancePaymentDocumnetForRegrdParty() {
		List<String> poList = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			POfunc.verifyDirectPoWithRateContractItemWithGeneralTypeForRegisterKForAdvanceForMultiPo(
					webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"), (ArrayList<String>) poList);
		}
		System.out.println(poList);
	}

	/**
	 * Select the Multi Purchase Order For Payment Payables
	 * 
	 * @author Kowshik-RMITS
	 * @param poList
	 */
	public void selectMultiplePoDocForAnPaymentInPendingPayablesAdvance(ArrayList<String> poList) {
		flag = webDB.switchToFrame(PurchaseBillsLocators.framePendingPayablesBP, ElementType.Xpath);
		if (flag) {
			for (int i = 1; i <= poList.size(); i++) {
				System.out.println(poList.size());
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				webDB.sendTextToAnElement(PurchaseBillsLocators.PaymentAdvancePoSearchField, poList.get(i - 1),
						ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.SearchButton1, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(3000);
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocators.SearchButton1, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseBillsLocators.SelectAllFlag, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(3000);
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(PurchaseBillsLocators.SelectAllFlag, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocators.EraseButton, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(3000);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.clickAnElement(PurchaseBillsLocators.EraseButton, ElementType.Xpath);
//							logs.logging("INFO", "Successfully Select a Record ");
//							if(flag) {
//								logs.logging("INFO", "Able to Select a Document");
//							}else {
//								logs.logging("INFO", "Not Able to Select a Document");

						}
					}
				}
			}

		}
//		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with RateContract Item for Similar
	 * UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * @throws IOException
	 * 
	 */
	public void verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralType(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		POfunc.verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String HSN = itemmultiple[i][68];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				POfunc.verifyDirectPoItemAddIconClicked();
				POfunc.verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				// verifyDirectPoQtyEntered(QUANTITY);
				commonFunc.verifyAddTableSpecs(PurchaseOrderPageLocators.PoQtyFieldClick,
						PurchaseOrderPageLocators.PoQtyInput, QUANTITY);
				POfunc.verifyDirectPoHsnSelectedFromLov(HSN);
				POfunc.verifyDirectPoLineWiseSaved();
				POfunc.verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
				POfunc.verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				POfunc.otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		POfunc.verifyHeaderTickAndPost();
		POfunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * Click Validate TDS Exempt
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickValidateTDSExempt() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
			webDB.javaScriptClickAnElement(PurchaseBillsLocators.ReferenceField, ElementType.Xpath);
			logs.logging("INFO", "Reference Tap Moved ");
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocators.TdsExemptedFlag, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PurchaseBillsLocators.TdsExemptedFlag, ElementType.Xpath);
				webDB.javaScriptClickAnElement(PurchaseBillsLocators.TdsExemptedFlag, ElementType.Xpath);
				// webDB.clickAnElement(PurchaseBillsLocators.AllowRcmFlag, ElementType.Xpath);
				logs.logging("INFO ", "Allows RCM Check Box Is Clicked ");
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
				if (flag) {
					webDB.javaScriptClickAnElement(PurchaseBillsLocators.SaveButtonHeader, ElementType.Xpath);
					logs.logging("INFO", "Successfully Clicked Save Headers");
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Inserted a TDS Section
	 * 
	 * @author Kowshik-RMITS
	 * @param TDSvalue
	 */
	public void tdsSectionLineWise(String TDSvalue) {
		flag = webDB.waitForElement(PurchaseBillsLocators.TdsSectioninput, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.TdsSectioninput, TDSvalue);
			logs.logging("INFO", "Successfully Inserted a TDS Section");
		}
		assertTrue(flag);
	}

	/**
	 * Insert TDS Section Line Wise Excel
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */
	public void insertTdsSectionLinesWiseExcel(String SheetName) {
//		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < itemMultiples.length; i++) {
			String ITEM_NAME = itemMultiples[i][1];
			String TDSSection = itemMultiples[i][69];

			if (!ITEM_NAME.equals("")) {
				flag = webDB.waitForElement(PurchaseBillsLocators.TdsSectionFieldk(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocators.TdsSectionFieldk(i - 21), ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseBillsLocators.TdsSectioninput, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.TdsSectioninput, TDSSection);
						logs.logging("INFO", "Successfully Inserted a TDS Section");
						webDB.pressTabKey();
						ClickSaveButton();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Multi New Reference in Bank Payment Against to the Po
	 * 
	 * @author Kowshik-RMITS
	 * @param SheetName
	 */
	public void multiNewRefForAnBankPayment(String SheetName) {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "DBfin.xlsx";
		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);

		for (int i = 1; i < itemMultiples.length; i++) {
			String BillDocNo = itemMultiples[i][1];
			String Amount = itemMultiples[i][2];

			if (!BillDocNo.equals("")) {
				flag = webDB.waitForElement(PurchaseBillsLocators.ViewButtonFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(PurchaseBillsLocators.ViewButtonFrame, ElementType.Xpath);
					logs.logging("INFO", "Successfully Navigates Frame");

					flag = webDB.waitForElement(PurchaseBillsLocators.AddButtonViewTap, ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(PurchaseBillsLocators.AddButtonViewTap, ElementType.Xpath);
						logs.logging("INFO", "Successfully Clicked a Add Button");
						flag = webDB.waitForElement(PurchaseBillsLocators.BillDocField, ElementType.Xpath);
						if (flag) {
//							webDB.pressTabKey();
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							// SalesordFunc.verifyClickAndEnterValueInTableFields(PurchaseBillsLocators.BillDocField,
							// PurchaseBillsLocators.BillDocNo, BillDocNo);
							webDB.doubleClickAnElement(PurchaseBillsLocators.BillDocField, ElementType.Xpath);
							webDB.clearTextField(PurchaseBillsLocators.BillDocNo, ElementType.Xpath);
							webDB.sendTextToAnElement(PurchaseBillsLocators.BillDocNo, BillDocNo, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
//							logs.logging("INFO",
//									"Successfully Inserted a Bills Number Against To The Banks" + BillDocNo);
							flag = webDB.waitForElement(PurchaseBillsLocators.PayamountField, ElementType.Xpath);
							if (flag) {
								commonFunc.checkRefreshCellsDisappear();
								SalesordFunc.verifyClickAndEnterValueInTableFields(PurchaseBillsLocators.PayamountField,
										PurchaseBillsLocators.PayAmt, Amount);
//								webDB.doubleClickAnElement(PurchaseBillsLocators.PayamountField, ElementType.Xpath);
//								commonFunc.hardWait(1000);
//								webDB.clearTextField(PurchaseBillsLocators.PayAmt, ElementType.Xpath);
//								webDB.sendTextToAnElement(PurchaseBillsLocators.PayAmt, Amount, ElementType.Xpath);
								logs.logging("INFO", "Successfully Inserted a Adjustment Amount");
								flag = webDB.waitForElement(PurchaseBillsLocators.SaveButtonView, ElementType.Xpath);
								if (flag) {
									commonFunc.checkRefreshCellsDisappear();
									webDB.clickAnElement(PurchaseBillsLocators.SaveButtonView, ElementType.Xpath);
									webDB.switchToDefaultContent();
									commonFunc.hardWait(500);
//									if (flag) {
//										webDB.clickAnElement(PurchaseBillsLocators.CloseButton, ElementType.Xpath);
//										logs.logging("INFO", "Successfully Clicked a Close Button");
//										commonFunc.hardWait(1000);

//									}
								}
//							}

							}
						}

					}
				}
			}
		}
		closeBankPaymnetNewRef();
		assertTrue(flag);
	}

	public void closeBankPaymnetNewRef() {
		flag = webDB.waitForElement(PurchaseBillsLocators.CloseButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.CloseButton, ElementType.Xpath);
			logs.logging("INFO", "Successfully Clicked a Close Button");
			commonFunc.hardWait(1000);
		}
		assertTrue(flag);
	}

	/**
	 * To Valid a Invoice Prefix Header
	 * 
	 * @author Kowshik-RMITS
	 * @param PrefixPb
	 */
	public void verifyInvoicePrfixHeaderPurchaseBillsForPendingPo(String PrefixPb) {

		String GetTextPBPrefix = webDB.getDomPropertyFromElement(PurchaseBillsLocators.Po_Inv_Prefix_Field,
				ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));

		System.out.println(GetTextPBPrefix);
		if (!GetTextPBPrefix.equals(PrefixPb)) {
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.PO_Inv_Prefix_Lov, PrefixPb);
			webDB.switchToFrame(PurchaseBillsLocators.IframePendingPo, ElementType.Xpath);
		}
		assertTrue(flag);
	}

//	System.out.println(itemMultiples.length);
//	for (int i = 22; i < itemMultiples.length; i++) {
//		String TDSSection = itemMultiples[i][71];

	/**
	 * verify Domestic Registered Supplier With LocalGST
	 * 
	 * @author Kowshik-RMITS
	 */
	public void verifyDomesticRegisteredSupplierWithLocalGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		createJvForRecord();
		commonFunc.checkRefreshCellsDisappear();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsLocal"));
		verifyHeaderValidationForPurchaseBillsWithDbConnections();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Local GST (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void verifyDomesticRegistedSupplierWithLocalGSTAllowIGSTtax(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost,
			String Hsncode) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("Purchase_Bills_Allow_IGST"));
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Verify Domestic Regd Supplier With Local IGST Allows RCM Tax
	 * 
	 * @author Kowshik-RMITS
	 */
	public void verifyDomesticRegistedSupplierWithLocalGSTAllowRCMtax(String Subvou, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost,
			String Hsncode) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvou, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillslocalUnreg"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Local GST ITC Ineligible
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierWithLocalGSTIneligible(String Subvoutype, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost, String Hsncode,
			String Fintype, String FintypeSec) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvoutype, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		selectDropDownTypeAndInsecTion(commonFunc.getDataFromTestDataSheet("Fin_Type_In"),
				commonFunc.getDataFromTestDataSheet("Fin_Type_In_Sec"));
		ClickSaveButton();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - With Local GST ITC Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierWithLocalGSTitcIneligibleAllowRCMtax(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost,
			String Hsncode, String Fintype, String FintypeSec) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillslocalUnreg"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Local GST ITC Ineligible (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierWithLocalGSTitcIneligibleAllowIGSTtax(String Subvoutype, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost,
			String Hsncode, String Fintype, String FintypeSec) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvoutype, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		selectDropDownTypeAndInsecTion(Fintype, FintypeSec);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("Purchase_Bills_Allow_IGST"));
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Interstate GST
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierWithInterstateGST(String Subvoutype, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost, String Hsncode) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvoutype, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Interstate GST (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegdSupplierWithInterstateGSTAllowRCMtax(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost, String Hsncode) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Interstate GST ITC Ineligible (Allow RCM
	 * tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierWithInterstateGSTitcIneligibleAllowRCMtax(String Subvoutype,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills, String Glaccount,
			String Unitcost, String Hsncode, String FinType, String FinTypeSec) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvoutype, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		ClickSaveButton();
		selectDropDownTypeAndInsecTion(FinType, FinTypeSec);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBills_Interstate_Un"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Direct Expenses -Domestic UnRegistered Supplier - With Local GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param Unitcost
	 * @param Hsncode
	 */
	public void DirectExpensesDomesticUnRegisteredSupplierWithLocalGST(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost,
			String Hsncode) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillslocalUnreg_RCM"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Direct Expenses -Domestic UnRegistered Supplier - With Local GST (ineligible)
	 * 
	 * @author Kowshik-RMITS
	 * @param Subvoutype
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Unitcost
	 * @param Fintype
	 * @param FintypeSec
	 * @param Glaccount
	 * @param Hsncode
	 */
	public void DirectExpensesDomesticUnRegisteredSupplierWithLocalGSTineligible(String Subvoutype, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Glaccount, String Unitcost, String Hsncode,
			String Bills, String Fintype, String FintypeSec) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvoutype, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillslocalUnreg_RCM"));
		ClickSaveButton();
		selectDropDownTypeAndInsecTion(Fintype, FintypeSec);
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillslocalUnreg_RCM"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic UnRegistered Supplier - With Local GST (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 * @param Subvoutype
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param Unitcost
	 * @param Hsncode
	 */
	public void DomesticUnRegisteredSupplierWithLocalGSTAllowIGSTtax(String Subvoutype, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost,
			String Hsncode) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvoutype, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("Purchase_Bills_Allow_IGST"));
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		commonFunc.hardWait(2000);
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		commonFunc.hardWait(2000);
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic UnRegistered Supplier - With Local GST (Allow IGST tax) (ineligible)
	 * 
	 * @author Kowshik-RMITS
	 * @param Subvoutype
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param Unitcost
	 * @param Hsncode
	 * @param Fintype
	 * @param FintypeSec
	 */
	public void DomesticUnRegisteredSupplierWithLocalGSTAllowIGSTtaxineligible(String Subvoutype, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String Unitcost,
			String Hsncode, String Fintype, String FintypeSec) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvoutype, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		selectDropDownTypeAndInsecTion(Fintype, FintypeSec);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("Purchase_Bills_Allow_IGST"));
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Direct Expenses -Domestic UnRegistered Supplier - With Interstate GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DirectExpensesDomesticUnRegisteredSupplierWithInterstateGST(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost,
			String Hsncode) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsImport"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Direct Expenses -Domestic UnRegistered Supplier - With Interstate GST
	 * (ineligible)
	 * 
	 * @author Kowshik-RMITS
	 * @param Subvoutype
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param Unitcost
	 * @param Hsncode
	 * @param Fintype
	 * @param FintypeSec
	 */
	public void DirectExpensesDomesticUnRegisteredSupplierWithInterstateGSTineligible(String Subvoutype,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills, String Glaccount,
			String Unitcost, String Hsncode, String Fintype, String FintypeSec) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvoutype, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		selectDropDownTypeAndInsecTion(Fintype, FintypeSec);
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillslocalUnreg_RCM"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Party account
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticRegisteredSupplierPartyaccount(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic UnRegistered Supplier - Party account
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param GlaccountPartyAccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticUnRegisteredSupplierPartyaccount(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String GlaccountPartyAccount, String UnitCost) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - , Distribute Invoice details
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 */
	public void DomesticRegisteredSupplierDistributeInvoicedetails(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost) {

		SalesInv.verifyDirectSalesInvoice();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		ClickSaveButton();
		clickInvoiceDetailsForDisturabeValue();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
					"KowshikUserName");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Domestic UnRegistered Supplier - , Distribute Invoice details
	 * 
	 * @author KowshiK-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 */
	public void DomesticUnRegisteredSupplierDistributeInvoicedetails(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost) {

		SalesInv.verifyDirectSalesInvoice();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		ClickSaveButton();
		clickInvoiceDetailsForDisturabeValue();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
					"KowshikUserName");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Import Unregistered Supplier (without GST)
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Section
	 */
	public void ImportUnregisteredSupplierwithoutGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Section) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsImport"));
		ClickSaveButton();
		createJvForRecord();
		verifyHeaderValidationForPurchaseBillsWithDbConnections();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Import Non-resident Supplier (without GST)
	 * 
	 * @author Kowshik-RMITS
	 * @param Subvoutype
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void ImportNonresidentSupplierwithoutGST(String Subvoutype, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(Subvoutype, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsImport"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsImport"));
		verifyHeaderValidationForPurchaseBillsWithDbConnections();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

//		public void ImportNonresidentSupplierWithST() {
//			
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			createNewRecordExpensesType(Subvoutype , PurchaseRef, Location , Prefix);
//			insertPartyInPurchaseBills(CustomerName, Bills);
//			storeSCOPurchaseVoucherNumber();
//			addNewRecord();
//			InsertGlAccountInDetails(Glaccount);		
//			UpdateUnitCostInLineDetail(UnitCost, Hsncode);
//			ClickSaveButton();
//
//			
//		}
	/**
	 * Direct Expense - with Adjustments
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 * @param PaymentDoc
	 */

	public void DirectExpensewithAdjustments(String SubvouType, String PurchaseRef, String Location, String Prefix,
			String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode, String PaymentDoc) {

		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
//		addNewRecord();
//		InsertGlAccountInDetails(Glaccount);
//		UpdateUnitCostInLineDetail(UnitCost, Hsncode);
		commonFunc.checkRefreshCellsDisappear();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsImport"));
		ClickSaveButton();
		VerifyClickingAdjustmentTab(PaymentDoc);

	}

	public void DirectExpenseForRecurringJournal(String SubvouType, String PurchaseRef, String Location, String Prefix,
			String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		storeSCOPurchaseVoucherNumber();
		addNewRecord();
		InsertGlAccountInDetails(Glaccount);
		UpdateUnitCostInLineDetail(UnitCost, Hsncode);
		ClickSaveButton();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
					"KowshikUserName");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Domestic Registered Supplier - With SEZ Unit GST
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticRegisteredSupplierWithSEZUnitGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsSez"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsSez"));
		verifyHeaderValidationForPurchaseBillsWithDbConnections();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Registered Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticRegisteredSupplierWithunionTerritoryGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsUTGST"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsUTGST"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
//		assertTrue(flag);
	}

	/**
	 * Domestic Registered Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticRegisteredSupplierWithLocalFreightGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsImport"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Unregistered Supplier - With Local Freight GST
	 * 
	 * @author Kowshik=RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticUnregisteredSupplierWithLocalFreightGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillslocalUnreg"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsImport"));
		verifyHeaderValidationForPurchaseBillsWithDbConnections();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic UnRegistered Supplier - With SEZ Unit GST
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticUnRegisteredSupplierWithSEZUnitGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsSez"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsSez"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Unregistered Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticUnregisteredSupplierWithunionTerritoryGST(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost,
			String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsUTGST"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsUTGST"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Composition Supplier - With Local GST
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticCompositionSupplierWithLocalGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Composition Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */

	public void DomesticCompositionSupplierWithLocalFreightGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsImport"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Composition Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticCompositionSupplierWithInterstateGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills, String Glaccount, String UnitCost, String Hsncode) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Composition Supplier - With Local GST
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param Glaccount
	 * @param UnitCost
	 * @param Hsncode
	 */
	public void DomesticCompositionSupplierWithunionTerritoryGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsUTGST"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsUTGST_Ureq"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Casual person Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCasualpersonSupplierWithLocalFreightGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsLocal"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Casual person Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCasualpersonSupplierWithInterstateGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);

	}

	/**
	 * Domestic Casual person Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCasualpersonSupplierWithunionTerritoryGST(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsUTGST"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsUTGST"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);

	}

	/**
	 * Domestic Non-resident Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticNonresidentSupplierWithLocalFreightGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsSez"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsSez"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Non-resident Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticNonresidentSupplierWithInterstateGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		ClickSaveButton();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);

	}

	/**
	 * DomesticNon-resident Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticNonresidentSupplierWithunionTerritoryGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingGlaccountFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBillsUTGST_Ureq"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailWithDbInPurchaseBills(webDB.getDataFromProperties("PurchaseBillsSez"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//					"KowshikUserName");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
		assertTrue(flag);
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGST() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}

		verifyDirectPODomesticServiceSimilarUOMMultiple(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		openExpandButtonDirect();
		createJvForRecord();
		verifyPurchaseBillsDocumentCompareToPoDocument(webDB.getDataFromProperties("poSerPurbills"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Local GST (Allow
	 * IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGSTAllowIGSTtax() {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}

		verifyDirectPODomesticServiceSimilarUOMMultiple(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailsWithDbValidationForAllowIGSTFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Register Supplier Bill Against Pending Po With LOcal Gst Allow Rcm
	 * Tax
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGSTAllowRCMtax() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}

		verifyDirectPODomesticServiceSimilarUOMMultiple(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Local GST ITC
	 * Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithLocalGSTTCIneligibleAllowRCMtax(String Value,
			String Value1) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}

		verifyDirectPODomesticServiceSimilarUOMMultiple(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Interstate GST
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithInterstateGST() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleForInterState(
				webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		openExpandButtonDirect();
		createJvForRecord();
		verifyPurchaseBillsDocumentCompareToPoDocument(webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Interstate GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithInterstateGSTAllowRCMtax() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleForInterState(
				webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Pending PO With Interstate GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstPendingPOWithInterstateGSTITCIneligibleAllowRCMtax(String Value,
			String Value1) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleForInterState(
				webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Pending PO With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGST() {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}

		verifyDirectPODomesticServiceSimilarUOMMultipleLocalUnregrd(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		openExpandButtonDirect();
		createJvForRecord();
		verifyPurchaseBillsDocumentCompareToPoDocument(webDB.getDataFromProperties("poSerPurbills"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Pending PO With Local GST
	 * (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGSTAllowIGSTtax() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}

		verifyDirectPODomesticServiceSimilarUOMMultipleLocalUnregrd(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailsWithDbValidationForAllowIGSTFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Pending PO With Local GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGSTAllowRCMtax() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}

		verifyDirectPODomesticServiceSimilarUOMMultipleLocalUnregrd(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Pending PO With Local GST ITC
	 * Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstPendingPOWithLocalGSTITCIneligibleAllowRCMtax(String Value,
			String Value1) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleLocalUnregrd(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillForPendingPo(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyInsertOfBillDate(Today, Billno);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGST(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleLocalGetDocument(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		clickSaveButtonHeader();
		openExpandButtonDirect();
		createJvForRecord();
		verifyPurchaseBillsDocumentCompareToPoDocument(webDB.getDataFromProperties("poSerPurbills"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST
	 * (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowIGSTtax(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleLocalGetDocument(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		clickSaveButtonHeader();
		openExpandButtonDirect();
		getGstClafInLocation();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDbValidationForAllowIGSTFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowRCMtax(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleLocalGetDocument(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		clickSaveButtonHeader();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST
	 * (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowRCMtax(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills, String Value,
			String Value1) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleLocalGetDocument(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Local GST ITC
	 * Ineligible (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowIGSTtax(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills, String Value,
			String Value1) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleLocalGetDocument(webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		movesToInvoiceTap();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		createJvForRecord();
		verifyLineDetailsWithDbValidationForAllowIGSTFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Interstate GST
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithInterstateGST(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleInterststeGetDocument(
				webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		clickSaveButtonHeader();
		openExpandButtonDirect();
		createJvForRecord();
		verifyPurchaseBillsDocumentCompareToPoDocument(webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Interstate GST
	 * 
	 * @author Kowshik-RMITS
	 */

	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithInterstateGSTAllowRCMtax(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleInterststeGetDocument(
				webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		clickSaveButtonHeader();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBills_Rcm_Updated"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - Bill Against Get Document With Interstate GST
	 * ITC Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticRegisteredSupplierBillAgainstGetDocumentWithInterstateGSTITCIneligibleAllowRCMtax(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills,
			String Value, String Value1) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleInterststeGetDocument(
				webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGST(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleUnregdLocalGetDocument(
				webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		clickSaveButtonHeader();
		openExpandButtonDirect();
		createJvForRecord();
		verifyPurchaseBillsDocumentCompareToPoDocument(webDB.getDataFromProperties("poSerPurbills"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST ITC
	 * Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowIGSTtax(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleUnregdLocalGetDocument(
				webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		clickSaveButtonHeader();
		openExpandButtonDirect();
		getGstClafInLocation();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		createJvForRecord();
		verifyLineDetailsWithDbValidationForAllowIGSTFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST
	 * (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTAllowRCMtax(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills, String Value,
			String Value1) {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleUnregdLocalGetDocument(
				webDB.getDataFromProperties("poSerPurbills"));
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		getValueFromTaxAmountInLineDetails();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		getValueFromTaxAmountInLineDetails();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST ITC
	 * Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowRCMtax(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills,
			String Value, String Value1) {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleUnregdLocalGetDocument(
				webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBillsLocal"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Local GST ITC
	 * Ineligible (Allow IGST tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithLocalGSTITCIneligibleAllowIGSTtax(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills,
			String Value, String Value1) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleUnregdLocalGetDocument(
				webDB.getDataFromProperties("poSerPurbills"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		movesToInvoiceTap();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		createJvForRecord();
		verifyLineDetailsWithDbValidationForAllowIGSTFlag(webDB.getDataFromProperties("POSer_Pr_Interstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Interstate
	 * GST
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithInterstateGST(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleUnregdInterstateGetDocument(
				webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		clickSaveButtonHeader();
		openExpandButtonDirect();
		createJvForRecord();
		verifyLineDetailsWithDbValidationForAllowIGSTFlag(webDB.getDataFromProperties("PurchaseBillsInterstate"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Interstate
	 * GST (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithInterstateGSTAllowRCMtax(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills, String Value,
			String Value1) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleUnregdInterstateGetDocument(
				webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBills_Rcm"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - Bill Against Get Document With Interstate
	 * GST ITC Ineligible (Allow RCM tax)
	 * 
	 * @author Kowshik-RMITS
	 */
	public void DomesticUnregisteredSupplierBillAgainstGetDocumentWithInterstateGSTITCIneligibleAllowRCMtax(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills,
			String Value, String Value1) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String Billno = RandomStringUtils.randomNumeric(6);
		if (Billno.equals("000")) {
			Billno = "100";
		}
		verifyDirectPODomesticServiceSimilarUOMMultipleUnregdInterstateGetDocument(
				webDB.getDataFromProperties("PoSerPurBillsInterstate"));
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickGetDocumentFromPendingPoScreen(VariableHandler.getPONumber());
		commonFunc.hardWait(2000);
		clickSaveButtonHeader();
		selectDropDownTypeAndInsecTion(Value, Value1);
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDbValidationRcmFlag(webDB.getDataFromProperties("PurchaseBills_Rcm"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Local GST- Purchase Direct (Stock item ,service item
	 * And charge item )
	 * 
	 * @author Kowshik- RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 * @param string2
	 * @param string
	 */
	public void RegisteredSupplierwithLocalGSTPurchaseDirectStockitemAndserviceitemAndchargeitem(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses("PurchaseBills_2_67");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Local GST- Purchase Direct With Expenses Account
	 * (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void RegisteredSupplierwithLocalGSTPurchaseDirectWithExpensesAccountStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Purchase Direct (Stock
	 * item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67 "));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("PurchaseBills_IGST_Validation"));
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses("PurchaseBills_2_67 ");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Purchase Direct and
	 * Expenses Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void RegisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct (Stock
	 * item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void RegisteredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */

	public void RegisteredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Interstate GST- Purchase Direct (Stock item ,service
	 * item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void RegisteredSupplierwithInterstateGSTPurchaseDirectStockitemserviceitemAndchargeitem(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Interstate GST- Purchase Direct and Expenses Account
	 * (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void RegisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct
	 * (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("purchaseBills_GRN"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax)- Purchase Direct and
	 * Expenses Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void RegisteredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectndExpensesAccountStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Unregistered Supplier with Local GST- Purchase Direct (Stock item ,service
	 * item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void UnregisteredSupplierwithLocalGSTPurchaseDirectStockitemserviceitemAndchargeitem(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("purchaseBills_GRN"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("purchaseBills_GRN"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Unregistered Supplier with Local GST- Purchase Direct and Expenses Account
	 * (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void UnregisteredSupplierwithLocalGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Unregistered Supplier with Local GST- Purchase Direct and Expenses Account
	 * (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void UnregisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("Purchasebills_IGST_Updated_1"));
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses("PurchaseBills_2_67");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Unregistered Supplier with Local GST (Allow IGST tax)- Purchase Direct and
	 * Expenses Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void UnregisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowIgstFlagUpdated(webDB.getDataFromProperties("PurchaseBills_IGST_Validation"));
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Unregistered Supplier with Interstate GST- Purchase Direct (Stock item
	 * ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void UnregisteredSupplierwithInterstateGSTPurchaseDirectStockitemserviceitemAndchargeitem(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_Ex_Grn_Igst"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_Ex_Grn_Igst"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Unregistered Supplier with Interstate GST- Purchase Direct and Expenses
	 * Account (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void UnregisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticRegisteredSupplierWithLocalFreightGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With SEZ Unit GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticRegisteredSupplierWithSEZUnitGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticUnregisteredSupplierWithLocalFreightGST1(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Domestic UnRegistered Supplier - With SEZ Unit GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticUnRegisteredSupplierWithSEZUnitGST1(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Domestic Composition Supplier - With Local GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCompositionSupplierWithLocalGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Composition Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCompositionSupplierWithLocalFreightGST1(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Composition Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCompositionSupplierWithInterstateGST1(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Casual person Supplier - With Local GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCasualpersonSupplierWithLocalGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Casual person Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCasualpersonSupplierWithLocalFreightGST1(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Casual person Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCasualpersonSupplierWithInterstateGST1(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Domestic Non-resident Supplier - With Local GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticNonresidentSupplierWithLocalGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Non-resident Supplier - With Local Freight GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticNonresidentSupplierWithLocalFreightGST1(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Non-resident Supplier - With Inter state GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticNonresidentSupplierWithInterstateGST1(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticRegisteredSupplierWithunionTerritoryGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticUnregisteredSupplierWithunionTerritoryGST1(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Domestic Composition Supplier With Union Territory
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCompositionSupplierWithunionTerritoryGST1(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Domestic Casual person Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticCasualpersonSupplierWithunionTerritoryGST1(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * DomesticNon-resident Supplier - With union Territory GST
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void DomesticNonresidentSupplierWithunionTerritoryGST1(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Unregistered Supplier imported (without GST)
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void unregisteredSupplierimportedwithoutGST(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_2_67"));
		ClickSaveButton();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_2_67"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Unregistered Supplier imported and expenses account(without GST)
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void unregisteredSupplierimportedandexpensesaccountwithoutGST2(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Unregistered Supplier imported (without GST)
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void unregisteredSupplierimportedwithoutGST1(String SubvouType, String PurchaseRef, String Location,
			String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_Ex_Grn_Igst"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_Ex_Grn_Igst"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void unregisteredSupplierimportedandexpensesaccountwithoutGST1(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Non-resident Supplier imported (without GST)
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void unregisteredSupplierImportWithGST(String SubvouType, String PurchaseRef, String Location, String Prefix,
			String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		addingItemFromReadingExcelSheet(webDB.getDataFromProperties("PurchaseBills_Ex_Grn_Igst"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		createJvForRecord();
		verifyDbValidationForGrnPurchaseWithOutExpenses(webDB.getDataFromProperties("PurchaseBills_Ex_Grn_Igst"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Non-resident Supplier imported and expenses account(without GST)
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void nonresidentSupplierimportedandexpensesaccountwithoutGST(String SubvouType, String PurchaseRef,
			String Location, String Prefix, String CustomerName, String Bills) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		ClickSaveButton();
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Registered Supplier with Local GST- Purchase Direct With Expenses Account and
	 * Adjustment (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void registeredSupplierwithLocalGSTAgainstGRNWithDeviationExpensesAccountandAdjustmentStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankPaymentSingle"));
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		clickPostButtonApprovalRecordBankPayments1();
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		ClickSaveButton();
		loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Purchase Direct and
	 * Expenses Account And Adjustment (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountAndAdjustmentStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankPaymentSingle"));
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		clickPostButtonApprovalRecordBankPayments1();

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowIgstFlag(webDB.getDataFromProperties("PurchaseBills_IGST_Validation_3"));
		loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Purchase Direct and
	 * Expenses Account with adjustment (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void registeredSupplierwithLocalGSTAllowRCMtaxPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankPaymentSingle"));
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		clickPostButtonApprovalRecordBankPayments1();

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Registered Supplier with Interstate GST- Purchase Direct and Expenses Account
	 * with adjustment (Stock item ,service item And charge item )
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void registeredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("Purchasebills_adj_interstate"));
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		clickPostButtonApprovalRecordBankPayments1();
//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Regrd Supplier With Intersate Allow RCM Tax
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void registeredSupplierwithInterstateGSTAllowRCMtaxPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("Purchasebills_adj_interstate"));
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		clickPostButtonApprovalRecordBankPayments();

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowRcmFlag();
		movesToInvoiceTap();
		loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Unregrd Supplier With Local Gst With Adjustment
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void unregisteredSupplierwithLocalGSTPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("Purchasebills_Adj_unregrd"));
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		clickPostButtonApprovalRecordBankPayments1();

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		ClickSaveButton();
		loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Unregrd Supplier With Local Allows IGST Tax
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void unregisteredSupplierwithLocalGSTAllowIGSTtaxPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("Purchasebills_Adj_unregrd"));
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		clickPostButtonApprovalRecordBankPayments1();

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		ClickSaveButton();
		openExpandButtonDirect();
		getGstClafInLocation();
		movesToInvoiceTap();
		clickValidateAllowIgstFlagUpdated(webDB.getDataFromProperties("PurchaseBills_IGST_Validation_3"));
		loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Unregrd Supplier With Interstate Gst Purchase Direct With Adjustment
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void unregisteredSupplierwithInterstateGSTPurchaseDirectandExpensesAccountwithadjustmentStockitemserviceitemAndchargeitem(
			String SubvouType, String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("Purchasebills_Adj_unregrd"));
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		clickPostButtonApprovalRecordBankPayments1();

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		ClickSaveButton();
		loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();

	}

	/**
	 * Unregrd Supplier Imported Expenses Account With Adjustment
	 * 
	 * @author Kowshik-RMITS
	 * @param SubvouType
	 * @param PurchaseRef
	 * @param Location
	 * @param Prefix
	 * @param CustomerName
	 * @param Bills
	 */
	public void unregisteredSupplierimportedexpensesaccountwithadjustmentwithoutGST(String SubvouType,
			String PurchaseRef, String Location, String Prefix, String CustomerName, String Bills) {

		verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				webDB.getDataFromProperties("PurchaseBills_Import"));
		addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		enterRealizedAmountInHeader(commonFunc.getDataFromTestDataSheet("RealizedAmount"));
		clickPostButtonApprovalRecordBankPayments1();

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		createNewRecordExpensesType(SubvouType, PurchaseRef, Location, Prefix);
		insertPartyInPurchaseBills(CustomerName, Bills);
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		ClickSaveButton();
		loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		createJvForRecord();
		verifyLineDetailsWithDBvalidationInGrnPurchaseType(webDB.getDataFromProperties("PurchaseBills_Un_Regrd_Iter"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
//		commonFunc.logoutUser();
	}

	/**
	 * Domestic Regrd Supplier with TDS Ex
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithTDSExemptedwithTdsApplyonBillQty() throws Exception {
		poToGrnProccedWithPurchaseBillsForRegrd();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickValidateTDSExempt();
		movesToInvoiceTap();
		ClickSaveButton();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered supplier - With TDS (Exempted)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithTDSExemptedwithApplyBillQty() throws Exception {
		poToGrnProccedWithPurchaseBillsForUnregrd();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		clickValidateTDSExempt();
		movesToInvoiceTap();
		ClickSaveButton();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Regrd Supplier With TDS Single Under Section
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithTDSSingleUndersection() throws Exception {

		poToGrnProccedWithPurchaseBillsForRegrd();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("po_regrd_kowshik"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier With Tds Single Under Section
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithTDSSingleUndersection() throws Exception {

		poToGrnProccedWithPurchaseBillsForUnregrd();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("po_Unregrd_kowshik"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - With TDS Multi Under section
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithTDSMultiUndersection() throws Exception {

		poToGrnProccedWithPurchaseBillsForRegrd();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered Supplier With TDS Multi Under Section
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithTDSMultiUndersection() throws Exception {

		poToGrnProccedWithPurchaseBillsForUnregrd();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier With Advance TDS Single Under Section Single Po
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSsingleundersectionSinglePO() throws Exception {
		poToGrnProccedWithPurchaseBillsForRegrdForAdvance();
		// bankPaymentRecordOfAgainstPo();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Advance TDS Multi under section (Single
	 * PO)SSS
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSMultiundersectionSinglePO() throws Exception {
		poToGrnProccedWithPurchaseBillsForRegrdForAdvanceforMultiSection();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS single under section
	 * (Single PO)
	 * 
	 * @author Kowshikan
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSsingleundersectionSinglePO() throws Exception {
		poToGrnProccedWithPurchaseBillsForRegrdForAdvanceForUnregrd();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_UnRegrd_Single_Tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS Multi under section (Single
	 * PO)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSMultiundersectionSinglePO() throws Exception {
		poToGrnProccedWithPurchaseBillsForRegrdForAdvanceForUnregrd();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Advance TDS single under section (Single
	 * PO with Single New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSsingleundersectionSinglePOwithSingleNewreference()
			throws Exception {
		poToGrnProccedWithPurchaseBillsForRegrdForAdvanceWithNewRef();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();

	}

	/**
	 * Domestic Registered Supplier - With Advance TDS Multi under section (Single
	 * PO with Single New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSMultiundersectionSinglePOwithSingleNewreference()
			throws Exception {
		poToGrnProccedWithPurchaseBillsForRegrdForAdvanceWithNewRefMultiTds();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS single under section
	 * (Single PO with Single New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSsingleundersectionSinglePOwithSingleNewreference()
			throws Exception {
		poToGrnProccedWithPurchaseBillsForRegrdForAdvanceForUnregrdWithNewRefSingleSection();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_UnRegrd_Single_Tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS Multi under section (Single
	 * PO with Single New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSMultiundersectionSinglePOwithSingleNewreference()
			throws Exception {
		poToGrnProccedWithPurchaseBillsForUnRegrdForAdvanceWithNewRefMultiTds();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS single under section (Single
	 * PO with multi New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSsingleundersectionSinglePOwithmultiNewreference()
			throws Exception {
		poToGrnProccedWithPurchaseBillsForRegrdForAdvanceWithNewRefinMultiRef();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Single_Tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS Multi under section (Single
	 * PO with multi New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSMultiundersectionSinglePOwithmultiNewreference()
			throws Exception {

		poToGrnProccedWithPurchaseBillsForRegrdForAdvanceWithNewRefinMultiRef();
		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
				"KowshikUserName");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS single under section
	 * (Single PO with multi New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSsingleunderectionSinglePOwithmultiNewreference()
			throws Exception {

		poToGrnProccedWithPurchaseBillsForUnRegrdForAdvanceForUnregrdWithNewMultiRefSingleSection();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_UnRegrd_Single_Tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS Multi under section (Single
	 * PO with multi New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSMultiundersectionSinglePOwithmultiNewreference()
			throws Exception {

		poToGrnProccedWithPurchaseBillsForUnRegrdForAdvanceForUnregrdWithNewMultiRefSingleSection();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds"));
		clickSaveButtonHeader();
		createJvForRecord();
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS Multi under section (Multi
	 * PO)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnRegisteredSupplierWithAdvanceTDSsingleundersectionMultiPO() throws Exception {
		multiPoCreationWithApprovalForUnRegrdParty();
		bankPaymentRecordOfAgainstWithMutiPo();
//		String PONumbervalue = "PCOD-2526-19615";
//		String PONumbervalue1 = "PCOD-2526-19616";
//		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
//		List<String> poList = new ArrayList<>();
//		VariableHandler.setPONumber(PONumbervalue);
//		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue1);
//		poList.add(PONumbervalue1);
//		poList.add(PONumbervalue);
//		System.out.println(poList);
//		VariableHandler.setMultiplePoDocs((ArrayList) poList);
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Regrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS single under section (Multi
	 * PO)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSsingleundersectionMultiPO() throws Exception {
		multiPoCreationWithAdvancePaymentDocumnetForRegrdParty();
		bankPaymentRecordOfAgainstWithMutiPo();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Regrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered supplier - With Advance TDS single under section (Multi
	 * PO with Single New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSsingleundersectionMultiPOwithSingleNewreference()
			throws Exception {
		multiPoCreationWithAdvancePaymentDocumnetForRegrdParty();
		bankPaymentRecordOfAgainstWithMutiPoSingleNewRef();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Regrd_Multi_tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Regrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS single under section (Multi
	 * PO with Single New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSsingleundersectionMultiPOwithSingleNewreference()
			throws Exception {
		multiPoCreationWithApprovalForUnRegrdParty();
		bankPaymentRecordOfAgainstWithMutiPoSingleNewRef();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS single under section (Multi
	 * PO with Multi New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSsingleundersectionMultiPOwithMultiNewreference()
			throws Exception {
		multiPoCreationWithAdvancePaymentDocumnetForRegrdParty();
		bankPaymentRecordOfAgainstwithMultiPoMultiNewRef();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS single under section (Multi
	 * PO with Multi New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSsingleundersectionMultiPOwithMultiNewreference()
			throws Exception {
		multiPoCreationWithApprovalForUnRegrdParty();
		bankPaymentRecordOfAgainstwithMultiPoMultiNewRef();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS Multi under section (Multi
	 * PO)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSMultiundersectionMultiPO() throws Exception {
		multiPoCreationWithAdvancePaymentDocumnetForRegrdParty();
		bankPaymentRecordOfAgainstWithMutiPoSingleNewRef();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS Multi under section (Multi PO
	 * with Single New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSMultiundersectionMultiPOwithSingleNewreference()
			throws Exception {
		multiPoCreationWithAdvancePaymentDocumnetForRegrdParty();
		bankPaymentRecordOfAgainstWithMutiPoSingleNewRef();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS Multi under section (Multi PO
	 * with Multi New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticRegisteredSupplierWithAdvanceTDSMultiundersectionMultiPOwithMultiNewreference()
			throws Exception {
		multiPoCreationWithAdvancePaymentDocumnetForRegrdParty();
		bankPaymentRecordOfAgainstwithMultiPoMultiNewRef();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS Multi under section (Multi
	 * PO)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSMultiundersectionMultiPO() throws Exception {
		multiPoCreationWithApprovalForUnRegrdParty();
		bankPaymentRecordOfAgainstWithMutiPo();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS Multi under section (Multi
	 * PO with Single New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSMultiundersectionMultiPOwithSingleNewreference()
			throws Exception {
		multiPoCreationWithApprovalForUnRegrdParty();
		bankPaymentRecordOfAgainstWithMutiPoSingleNewRef();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Domestic Unregistered supplier - With Advance TDS Multi under section (Multi
	 * PO with Multi New reference)
	 * 
	 * @author Kowshik-RMITS
	 * @throws Exception
	 */
	public void DomesticUnregisteredsupplierWithAdvanceTDSMultiundersectionMultiPOwithMultiNewreference()
			throws Exception {
		multiPoCreationWithApprovalForUnRegrdParty();
		bankPaymentRecordOfAgainstwithMultiPoMultiNewRef();
		creationGrnDocAgainstToTheMultiplePo();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		closeExpandButtonDirect();
		storeSCOPurchaseVoucherNumber();
		insertTdsSectionLinesWiseExcel(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1"));
		clickSaveButtonHeader();
		createJvForRecord();
		verifyLineDetailsDbValidationWithTDSsupply(webDB.getDataFromProperties("potoGrn_Unregrd_Multi_Tds_1db"));
		clickPostButton();
		verifyWaitingForApprovalForPurchaseBills();
		commonFunc.logoutUser();
	}

	/**
	 * Verify Purchase Bills Against For Adjustment
	 * 
	 * @author Vimal -RMITS
	 * @param BankName
	 * @param Currency
	 * @param SheetName
	 */
	public void verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(String BankName, String Currency,
			String SheetName, String Supplier) {

//		commonFunc.validCommonLogin(System.getProperty("KowshikUserName"), System.getProperty("LoginPassword"),
//				System.getProperty("KowshikUserName"));
		SubcontractPurchasebillFunc.getSupplierGLAcctFromDB(Supplier);
		commonFunc.navigateToFinancialPaymentReceipt();
		BPFunc.clickAddButtonInPaymentsRecipts();
		BPFunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BPFunc.getBankPaymentOrReceiptDocumentNumber();
		BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		BPFunc.verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today,
				Today);
		BPFunc.verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(KowshikFin, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
//			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!PartyName.equals("")) {
				BPFunc.clickBPLineAddButton();
				BPFunc.addBankPaymentLineDetailsGlaactAndCPC(VariableHandler.getSupGlAccount(), CPCCodeDesc);
				BPFunc.clickLineWiseSaveButton();
				clickViewButton();

			}

		}

	}

}
