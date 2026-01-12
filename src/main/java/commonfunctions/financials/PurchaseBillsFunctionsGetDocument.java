package commonfunctions.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 17, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.coreprocess.CoreProcessPageFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.DirectGrnPageFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.PurchaseBillsLocators;
import commonlocators.financials.PurchaseBillsLocatorsGetDocument;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseBillsFunctionsGetDocument {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
	static MaterialRequestFunctions coreProcessPageFunctions = new MaterialRequestFunctions();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();

	String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	ExcelUtils excelUtils = new ExcelUtils();
	String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "DBDETAILS.xlsx";

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
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PendingGrn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PendingGrn, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.Iframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.GrnNo,
							VariableHandler.getGrnDocNumber());
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.Iframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.SearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.CheckBox(DocNo),
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.CheckBox(DocNo),
										ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.CreateDoc,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(5000);
									webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OkButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OkButton,
												ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.LocationId,
												ElementType.Xpath);
										storePurchaseVoucherNumber();
										// verifyAddTDSNumber();
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
	public void verifyAddTDSNumberForLandedCost(String TdsNo) {
		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath);
		}
		webDB.moveToElement(PurchaseBillsLocatorsGetDocument.TableData, ElementType.Xpath);
		webDB.moveToElement(PurchaseBillsLocatorsGetDocument.TDSU_SForLandedCost, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.TDSU_SForLandedCost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.TDSU_SForLandedCost, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.TDSLovButton, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.TDSLovButton, TdsNo);
				webDB.pressTabKey();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify To Create Journal And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateJournalAndPost() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.CreateJournal, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.CreateJournal, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PostButton, ElementType.Xpath);
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Premkumar.K
	 */
	public void VerifyToClickCreateJournalButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.CreateJournal, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.CreateJournal, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				logs.logging("INFO", "Create Journal Button SuccessFully Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Premkumar.K
	 */
	public void VerifyToClickPostButton() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PostButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Post Button Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PostButton, ElementType.Xpath);
			flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
					|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			logs.logging("INFO", "Post Button Is Clicked SuccessFully");
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store Purchase Voucher Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storePurchaseVoucherNumber() {
		String Number = webDB.getTextFromElement(PurchaseBillsLocatorsGetDocument.VoucherNumber, ElementType.Xpath);
		VariableHandler.setPbNumber(Number);
		System.out.println(Number);
	}

	/**
	 * waiting for approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyWaitingForApproval() {
//		commonFunc.hardWait(3000);
//		if (webDB.verifyIsElementDisplayed(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath)) {
//			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
//			logs.logging("INFO", "Switched To Workflow Frame");
//			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ApproveOkButton, ElementType.Xpath);
//			if (flag) {
//				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ApproveOkButton, ElementType.Xpath);
//				logs.logging("INFO", "Ok Button Is Clicked");
//				webDB.switchToDefaultContent();
//			}
//		}
//		assertTrue(flag);

	}

	/**
	 * This Method Used To Verify Purchase Bills Approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseBillsApproval() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"Sathi Ganesh");
		commonFunc.navigateToFinancialPurchaseBill();
//		verifyToCreateNewPurchaseBillDocument();
//		verifyToGetDocumentFromLandedCost();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber());
		verifyCreateJournalAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		approvalPageFunc.navigateToApprovals();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getPbNumber());
		approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getPbNumber());
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"Sathi Ganesh");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Purchase Bill Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseBillApprovalStatus() {
		commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.VoucherNumberLovButton,
				VariableHandler.getPbNumber());
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PurchaseBillSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PurchaseBillSearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ApprovedStatus(VariableHandler.getPbNumber()),
					ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(
						PurchaseBillsLocatorsGetDocument.ApprovedStatus(VariableHandler.getPbNumber()),
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
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyCLickPurchaseBillsFrame() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PurchaseBillsframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.PurchaseBillsframe, ElementType.Xpath);
			logs.logging("INFO", "Switch To Purchase Order Frame");
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PurchaseBillTickIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PurchaseBill Tick Icon Displayed");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Premkumar.K
	 * @param BillNo
	 * @param PartyID
	 */
	public void verifyToCreateNewPurchaseBillDocument(String BillNo, String PartyID) {
		String SupBillNo = RandomStringUtils.randomNumeric(6);
		if (SupBillNo.equals("000")) {
			SupBillNo = "10";
		}
		String DCNO = BillNo + SupBillNo;
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.DocAddBtn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Document Add Button Shown");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.DocAddBtn, ElementType.Xpath);
			verifyCLickPurchaseBillsFrame();
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.SubVouType,
					commonFunc.getDataFromTestDataSheet("LCTypePK"));
			verifyCLickPurchaseBillsFrame();
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.LocID,
					commonFunc.getDataFromTestDataSheet("Loc_100"));
			verifyCLickPurchaseBillsFrame();
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PartyID, PartyID);
			verifyCLickPurchaseBillsFrame();
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Document Voucher Type Shown");
				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.BillNumInput, DCNO, ElementType.Xpath);
					logs.logging("INFO", "Bill Number Inserted SuccessFully");
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PurchaseBillTickIcon,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PurchaseBillTickIcon, ElementType.Xpath);
						logs.logging("INFO", "Tick Icon Button Clicked Successfully");
						commonFunc.checkRefreshCellsDisappear();
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GetDoc, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Document Created Successfully");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Premkumar.K
	 */
	public void verifyToGetDocumentFromLandedCost() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GetDoc, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.GetDoc, ElementType.Xpath);
			logs.logging("INFO", "Document Add Button Shown");
			verifyCLickGetDocumentFrame();
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.GetDocumentFromGrnNumber,
					VariableHandler.getGrnDocNumber());
			// VariableHandler.getGrnDocNumber()
			// GRM10/00500/25-26
			logs.logging("INFO", "GHRN Document");
			verifyCLickGetDocumentFrame();
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GetDocumentSearchbutton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.GetDocumentSearchbutton, ElementType.Xpath);
				logs.logging("INFO", "Search SuccessFully ");
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GetDocumentSearchbutton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.GetDocumentSearchbutton, ElementType.Xpath);
					logs.logging("INFO", "Search SuccessFully ");
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GetDocumentSelectAllButton,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.GetDocumentSelectAllButton,
								ElementType.Xpath);
						logs.logging("INFO", "Select All Button Clicked Successfully");
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GetDocumentOkbtn,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.GetDocumentOkbtn, ElementType.Xpath);
							logs.logging("INFO", "Document Added And Screen Navigate To Transaction Screen ");
							webDB.switchToDefaultContent();
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Reference Tab Displayed");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyCLickGetDocumentFrame() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GetDocumentFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.GetDocumentFrame, ElementType.Xpath);
			logs.logging("INFO", "Switch To Purchase Order Frame");
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GetDocumentSearchbutton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Displayed");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToClickTdsFlagInReferenceField() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
			logs.logging("INFO", "Reference Tab Successfully CLicked");
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ExemtedTDSFlag, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ExemtedTDSFlag, ElementType.Xpath);
				logs.logging("INFO", "Exempted Flag Checked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingFromGetDocument() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostDB"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostDB"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostDB"), i);
				verifyCreateJournalAndPost();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	private void ValidateLineDetailsForLandedCost(String SupplierID, int SeqNumber) {
//		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
//		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "ItemDetails.xlsx";
//		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
//		for (int i = 1; i < itemmultiple.length; i++) {
		String SeqNo = Integer.toString(SeqNumber);
		System.out.println(SeqNo);
		verifyAssertionBothValues(
				dbCommonFunc.GrnLandedCostQuery(VariableHandler.getGrnDocNumber(), SupplierID,
						commonFunc.getDataFromTestDataSheet("GRNLCITEMID")),
				dbCommonFunc.PurchaseBillLandedCostQuery(VariableHandler.getPbNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("PBITEMID")));
		logs.logging("INFO", "Item ID Matched");
		verifyAssertionBothValues(
				dbCommonFunc.GrnLandedCostQuery(VariableHandler.getGrnDocNumber(), SupplierID,
						commonFunc.getDataFromTestDataSheet("LandedUnitCostAmount")),
				dbCommonFunc.PurchaseBillLandedCostQuery(VariableHandler.getPbNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("PBUnitCost")));
		logs.logging("INFO", "Landed Cost Amount Matched");
		verifyAssertionBothValues(
				dbCommonFunc.GrnLandedCostQuery(VariableHandler.getGrnDocNumber(), SupplierID,
						commonFunc.getDataFromTestDataSheet("LandedCostCGSTAmount")),
				dbCommonFunc.PurchaseBillLandedCostQuery(VariableHandler.getPbNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")));
		logs.logging("INFO", "CGST Amount Matched");
		verifyAssertionBothValues(
				dbCommonFunc.GrnLandedCostQuery(VariableHandler.getGrnDocNumber(), SupplierID,
						commonFunc.getDataFromTestDataSheet("LandedCostSGSTAmount")),
				dbCommonFunc.PurchaseBillLandedCostQuery(VariableHandler.getPbNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")));
		logs.logging("INFO", "SGST Amount Matched");
		verifyAssertionBothValues(
				dbCommonFunc.GrnLandedCostQuery(VariableHandler.getGrnDocNumber(), SupplierID,
						commonFunc.getDataFromTestDataSheet("LandedCostIGSTAmount")),
				dbCommonFunc.PurchaseBillLandedCostQuery(VariableHandler.getPbNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")));
		logs.logging("INFO", "IGST Amount Matched");
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateHeaderAmountForPurchaseBill(String SheetName, int i1) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String ITEM_ID = PBMultipleline[i][1];
			String PurchaseBillHeaderAmount = PBMultipleline[i][28];
			if (!ITEM_ID.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillHeaderTotalAmount")),
						PurchaseBillHeaderAmount);
				logs.logging("INFO",
						"Expected Purchase Bill  Total Amount Is Matched With DataBase Values For This Purchase Bill Document "
								+ VariableHandler.getPbNumber());
			}
		}
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateAfterChangedHsnToHeaderAmountForPurchaseBill(String SheetName, int i1) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String ITEM_ID = PBMultipleline[i][1];
			String PurchaseBillHeaderAmount = PBMultipleline[i][103];
			if (!ITEM_ID.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillHeaderTotalAmount")),
						PurchaseBillHeaderAmount);
				logs.logging("INFO",
						"Expected Purchase Bill  After Changed HSN Total Amount Is Matched With DataBase Values For This Purchase Bill Document "
								+ VariableHandler.getPbNumber());
			}
		}
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBill(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String ITEM_ID = PBMultipleline[i][1];
			String PurchaseBillHeaderAmount = PBMultipleline[i][150];
			if (!ITEM_ID.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillHeaderTotalAmount")),
						PurchaseBillHeaderAmount);
				logs.logging("INFO",
						"Expected Purchase Bill  After Changed HSN Total Amount Is Matched With DataBase Values For This Purchase Bill Document "
								+ VariableHandler.getPbNumber());
			}
		}
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateLineWiseQueryAfterHsnChanged(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String ITEM_ID = PBMultipleline[i][1];
			String ITEM_ID1 = PBMultipleline[i][3];
			System.out.println("Tax Amount is" + ITEM_ID);
			String sys = PBMultipleline[i][13];
			System.out.println("Tax Amount is" + sys);
			String TaxAmountCGST = PBMultipleline[i][96];
			System.out.println("Tax Amount is" + TaxAmountCGST);
			String TaxAmountSGST = PBMultipleline[i][97];
			String TaxAmountIGST = PBMultipleline[i][98];

			if (!ITEM_ID.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST);
				logs.logging("INFO", "CGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST);
				logs.logging("INFO", "SGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST);
				logs.logging("INFO", "IGST Matched With DB Values For This Item " + ITEM_ID);

			}
		}
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateAfterPriceDeviation(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String ITEM_ID = PBMultipleline[i][1];
			String TaxAmountCGST = PBMultipleline[i][21];
			String TaxAmountSGST = PBMultipleline[i][22];
			String TaxAmountIGST = PBMultipleline[i][23];

			if (!ITEM_ID.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST);
				logs.logging("INFO", "CGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST);
				logs.logging("INFO", "SGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST);
				logs.logging("INFO", "IGST Matched With DB Values For This Item " + ITEM_ID);

			}
		}
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateAfterPriceDeviationForAfterAddItem(String SheetName, int i1) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i < PBMultipleline.length; i++) {
			String ITEM_ID = PBMultipleline[i][1];
			String TaxAmountCGST = PBMultipleline[i][21];
			String TaxAmountSGST = PBMultipleline[i][22];
			String TaxAmountIGST = PBMultipleline[i][23];

			if (!ITEM_ID.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST);
				logs.logging("INFO", "CGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST);
				logs.logging("INFO", "SGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST);
				logs.logging("INFO", "IGST Matched With DB Values For This Item " + ITEM_ID);

			}
		}
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateAfterPriceDeviationForStockTransferType(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String ITEM_ID = PBMultipleline[i][1];
			String TaxAmountCGST = PBMultipleline[i][21];
			String TaxAmountSGST = PBMultipleline[i][22];
			String TaxAmountIGST = PBMultipleline[i][23];
			String PBTradeAsblValue = PBMultipleline[i][11];
			String PBTradeDiscountAmt = PBMultipleline[i][12];
			String PBSplAsblValue = PBMultipleline[i][13];
			String PBSplDiscountAmt = PBMultipleline[i][14];
			String PBCashAsblValue = PBMultipleline[i][15];
			String PBCashDiscountAmt = PBMultipleline[i][16];
			String PBUTGSTAmt = PBMultipleline[i][24];
			String PBCessAmt = PBMultipleline[i][26];
			if (!ITEM_ID.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST);
				logs.logging("INFO", "CGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST);
				logs.logging("INFO", "SGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST);
				logs.logging("INFO", "IGST Matched With DB Values For This Item " + ITEM_ID);
				System.out.println("tax");
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PBTradeAsblVal")), PBTradeAsblValue);
				logs.logging("INFO", "PBTradeAsblVal Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PBTradeDicAmt")), PBTradeDiscountAmt);
				logs.logging("INFO", "PBTradeDicAmt Matched With DB Values For This Item " + ITEM_ID);
				System.out.println("trade");
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PBSPLAsblVal")), PBSplAsblValue);
				logs.logging("INFO", "PBSPLAsblVal Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PBSplDicAmt")), PBSplDiscountAmt);
				logs.logging("INFO", "PBSplDicAmt Matched With DB Values For This Item " + ITEM_ID);
				System.out.println("spl");
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PBCashAsblVal")), PBCashAsblValue);
				logs.logging("INFO", "PBCashAsblVal Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PBCashDiscAmt")), PBCashDiscountAmt);
				logs.logging("INFO", "PBCashDiscAmt Matched With DB Values For This Item " + ITEM_ID);
				System.out.println("CASh");
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillUTGSTAmount")), PBUTGSTAmt);
				logs.logging("INFO", "PurchaseBillUTGSTAmount Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillCESSAmount")), PBCessAmt);
				logs.logging("INFO", "PurchaseBillCESSAmount Matched With DB Values For This Item " + ITEM_ID);

			}
		}
	}

//	verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
//	commonFunc.getDataFromTestDataSheet("PBCashAsblVal")), PBCashAsblValue);
//logs.logging("INFO", "PBCashAsblVal Matched With DB Values For This Item " + ITEM_ID);
//verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
//	commonFunc.getDataFromTestDataSheet("PBCashDiscAmt")), PBCashDiscountAmt);
//logs.logging("INFO", "PBCashDiscAmt Matched With DB Values For This Item " + ITEM_ID);
	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToValidateBeforePriceDeviationForLineLevel(String SheetName) {
//		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "DBDETAILS.xlsx";
//		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
//		for (int i = 22; i < PBMultipleline.length; i++) {
//			String ITEM_ID = PBMultipleline[i][1];
//			String TaxAmountCGST = PBMultipleline[i][21];
//			String TaxAmountSGST = PBMultipleline[i][22];
//			String TaxAmountIGST = PBMultipleline[i][23];
//
//			if (!ITEM_ID.equals("")) {
//				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
//						commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST);
//				logs.logging("INFO", "CGST Matched With DB Values For This Item " + ITEM_ID);
//				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
//						commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST);
//				logs.logging("INFO", "SGST Matched With DB Values For This Item " + ITEM_ID);
//				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
//						commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST);
//				logs.logging("INFO", "IGST Matched With DB Values For This Item " + ITEM_ID);
//
//			}
//		}

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
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHTradeAssessableValue")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PBTradeAsblVal")));
				logs.logging("INFO", "Trade Assessable Value Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHSpecialAssessableValue")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PBSPLAsblVal")));
				logs.logging("INFO", "Special Assessable Value Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHCashAssessableValue")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PBCashAsblVal")));
				logs.logging("INFO", "Cash Assessable Value Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHTradeDiscountAmount")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PBTradeDicAmt")));
				logs.logging("INFO", "Trade Discount Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHSpecialDiscountAmount")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PBSplDicAmt")));
				logs.logging("INFO", "Special Discount Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHCashDiscountAmount")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PBCashDiscAmt")));
				logs.logging("INFO", "Cash  Discount Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHIgstAmount")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")));
				logs.logging("INFO", "IGST Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHSgstAmount")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")));
				logs.logging("INFO", "SGST  Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHCgstAmount")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")));
				logs.logging("INFO", "CGST  Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHUtgstAmount")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillUTGSTAmount")));
				logs.logging("INFO", "UTGST  Tax Amount Matched For PO and GRN " + ItemId);
				verifyAssertionBothValues(
						dbCommonFunc.GRNhistQuery(VariableHandler.getGrnDocNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("GRNHCessAmount")),
						dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PurchaseBillCESSAmount")));
				logs.logging("INFO", "UTGST  Tax Amount Matched For PO and GRN " + ItemId);
			}
		}

	}

	/**
	 * @author Premkumar.K
	 */
	public void VerifyToValidateRoundOffAmount(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String ITEM_ID = PBMultipleline[i][1];
			String PurchaseBillRoundOfAmount = PBMultipleline[i][153];
			if (!ITEM_ID.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillRoundOffAmount")),
						PurchaseBillRoundOfAmount);
				logs.logging("INFO",
						"Expected RoundOff  Amount Is Matched With DataBase Values For This Purchase Bill Document "
								+ VariableHandler.getPbNumber());
			}
		}
	}

	/**
	 * @author Premkumar.K
	 */
	public void VerifyToValidateRoundOffAmountForStockTransferType(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 27; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String TotalAmount = PBMultipleline[i][2];
			String PurchaseBillRoundOfAmount = PBMultipleline[i][46];
			if (!TotalAmount.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillRoundOffAmount")),
						PurchaseBillRoundOfAmount);
				logs.logging("INFO",
						"Expected RoundOff  Amount Is Matched With DataBase Values For This Purchase Bill Document "
								+ VariableHandler.getPbNumber());
			}
		}
	}

	/**
	 * @author Premkumar.K
	 */
	public void VerifyToValidateRoundOffAmountForHeaderValue(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String ITEM_ID = PBMultipleline[i][1];
			String PurchaseBillHeaderRoundOfAmount = PBMultipleline[i][152];
			if (!ITEM_ID.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillHeaderTotalAmount")),
						PurchaseBillHeaderRoundOfAmount);
				logs.logging("INFO", "RoundOff Header Amount Matched" + VariableHandler.getPbNumber());
			}
		}
	}

	/**
	 * @author Premkumar.K
	 */
	public void VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 27; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String TotalAmount = PBMultipleline[i][2];
			String PurchaseBillHeaderRoundOfAmount = PBMultipleline[i][45];
			if (!TotalAmount.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillHeaderTotalAmount")),
						PurchaseBillHeaderRoundOfAmount);
				logs.logging("INFO", "RoundOff Header Amount Matched" + VariableHandler.getPbNumber());
			}
		}
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateLineWiseQueryAfterAddTheItem(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String ITEM_ID = PBMultipleline[i][1];
			String TaxAmountCGST = PBMultipleline[i][21];
			String TaxAmountSGST = PBMultipleline[i][22];
			String TaxAmountIGST = PBMultipleline[i][23];
			String ITEM_ID1 = PBMultipleline[i][76];
			String TaxAmountCGST1 = PBMultipleline[i][96];
			String TaxAmountSGST1 = PBMultipleline[i][97];
			String TaxAmountIGST1 = PBMultipleline[i][98];

			if (!ITEM_ID.equals("") && !ITEM_ID1.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST);
				logs.logging("INFO", "CGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST);
				logs.logging("INFO", "SGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
						commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST);
				logs.logging("INFO", "IGST Matched With DB Values For This Item " + ITEM_ID);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID1,
						commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST1);
				logs.logging("INFO", "CGST Matched With DB Values For This Item " + ITEM_ID1);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID1,
						commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST1);
				logs.logging("INFO", "SGST Matched With DB Values For This Item " + ITEM_ID1);
				verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID1,
						commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST1);
				logs.logging("INFO", "IGST Matched With DB Values For This Item " + ITEM_ID1);

			}
		}
	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApproval(String FrwdPerson) {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(GrnLocators.DocAprovalIfrmae, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval, FrwdPerson);
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.hardWait(3000);
				flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
				if (flag) {
					// commonFunc.logoutUser();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	public void verifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTds() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostWTDS"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostWTDS"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostWTDS"), i);
				verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
				VerifySaveLine();
				verifyCreateJournalAndPost();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	private void VerifySaveLine() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddSave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBAddSave, ElementType.Xpath);
			logs.logging("INFO", "Line is Saved");
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	public void verifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTdsAndEsi() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostWTDSandESI"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostWTDSandESI"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostWTDSandESI"), i);
				verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
				VerifySaveLine();
				verifyCreateJournalAndPost();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	public void verifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTaxAddition() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostHSNCHanged"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostHSNCHanged"));
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
				VerifyToChangeTheHsnCode();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifySaveLine();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateLineWiseQueryAfterHsnChanged(webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterChangedHsnToHeaderAmountForPurchaseBill(
						webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}
	// VerifyToChangeTheHsnCode();

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	private void VerifyToChangeTheHsnCode() {
		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath);
		}
		webDB.moveToElement(PurchaseBillsLocatorsGetDocument.TableData, ElementType.Xpath);
		webDB.moveToElement(PurchaseBillsLocatorsGetDocument.HsnFieldGrid, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.HsnFieldGrid, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.HsnFieldGrid, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.HsnLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.HsnLov,
						commonFunc.getDataFromTestDataSheet("HsnAddition"));
				webDB.pressTabKey();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void verifyAddExpenseTypeForStockTransfer(String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			System.out.println(GRNMultipleline.length);
			String Type = GRNMultipleline[i][75];
			String Cost = GRNMultipleline[i][71];
			String GLAccount = GRNMultipleline[i][77];
			if (!Type.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
					logs.logging("INFO", "Add Button is Clicked");
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddTypeExp, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBAddTypeExp, ElementType.Xpath);
						webDB.selectDropDownByText(PurchaseBillsLocatorsGetDocument.PBAddTypeExp, Type,
								ElementType.Xpath);
						logs.logging("INFO", "Field Is Clicked");

						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GlAccDescField, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.GlAccDescField, ElementType.Xpath);
							logs.logging("INFO", "Field Is Clicked");
							flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GlAccLov, ElementType.Xpath);
							if (flag) {
								commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.GlAccLov,
										GLAccount);
								logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField,
											ElementType.Xpath);
									webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
									webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Cost Is:" + Cost);
									commonFunc.hardWait(3000);
									webDB.pressTabKey();
									VerifySaveLine();
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
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void verifyToAddNewItemInLandedCostType(String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			System.out.println(GRNMultipleline.length);
			String Type = GRNMultipleline[i][147];
			String Item = GRNMultipleline[i][76];
			String Cost = GRNMultipleline[i][81];
			if (!Item.equals("")) {
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
					logs.logging("INFO", "Add Button is Clicked");
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddTypeExp, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBAddTypeExp, ElementType.Xpath);
						webDB.selectDropDownByValue(PurchaseBillsLocatorsGetDocument.PBAddTypeExp, Type,
								ElementType.Xpath);
						logs.logging("INFO", "Field Is Clicked");
						webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBItemField, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBItemField, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBItemLov, ElementType.Xpath);
							if (flag) {
								commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PBItemLov,
										Item);
								flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField,
											ElementType.Xpath);
									webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
									webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost,
											ElementType.Xpath);
									logs.logging("INFO", "Entered Cost Is:" + Cost);
									commonFunc.hardWait(3000);
									webDB.pressTabKey();

								}
							}
						}
					}
				}

			}
		}
		assertTrue(flag);
	}

//	/**
//	 * This Methods Is Used for Approve the Document
//	 * 
//	 * @author PremKumar.K-RMITS
//	 */
//	private void verifyChangeCostForDeviation(String SheetName) {
//		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "DirectGrnDetails.xlsx";
//		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
//		for (int i = 22; i < GRNMultipleline.length; i++) {
//			System.out.println(GRNMultipleline.length);
//			String Cost = GRNMultipleline[i][69];
//			if (!Cost.equals("")) {
//				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField, ElementType.Xpath);
//					webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
//					webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost, ElementType.Xpath);
//					logs.logging("INFO", "Entered Cost Is:" + Cost);
//					commonFunc.hardWait(3000);
//					webDB.pressTabKey();
//
//				}
//			}
//		}
//		assertTrue(flag);
//	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	public void verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithNewChargesAddition() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostAdditem"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostAdditem"));
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				verifyToAddNewItemInLandedCostType(webDB.getDataFromProperties("LandedCostAdditem"));
				VerifySaveLine();
				VerifyToValidateLineWiseQueryAfterAddTheItem(webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBill(
						webDB.getDataFromProperties("LandedCostAdditem"));
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	private void VerifytoPutPriceDeviation(String SheetName) {

		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			System.out.println(GRNMultipleline.length);
			String Cost = GRNMultipleline[i][6];
			String ITEM_ID = GRNMultipleline[i][1];
			if (!ITEM_ID.equals("")) {
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField(i - 21), ElementType.Xpath);
					webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost, ElementType.Xpath);
					logs.logging("INFO", "Entered Cost Is:" + Cost);
					commonFunc.hardWait(3000);
					webDB.pressTabKey();

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	public void verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithDeviation() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("RoundOFValue"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("RoundOFValue"));
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateRoundOffAmount(webDB.getDataFromProperties("RoundOFValue"));
				VerifyToValidateRoundOffAmountForHeaderValue(webDB.getDataFromProperties("RoundOFValue"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	public void verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithValueAddition() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostAddition"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostAddition"));
		ArrayList<String> GrnSeqNumber = new ArrayList<>();
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
						webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				VerifySaveLine();
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Unit Cost For This Item");
		int maxRows = 2;
		int count = 0;
		for (int i = 22; i < ItemMultiple.length; i++) {
			if (count >= maxRows) {
				break;
			}
			String ItemId = ItemMultiple[i][1];
			if (!ItemId.equals("")) {
				count++;
				String Cost = dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PBUnitCost"));
				BigDecimal value = new BigDecimal(Cost).add(new BigDecimal("100"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + Cost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 6, value.toString());
			}
		}

	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Unit Cost For This Item");
		int maxRows = 2;
		int count = 0;
		for (int i = 22; i < ItemMultiple.length; i++) {
			if (count >= maxRows) {
				break;
			}
			String ItemId = ItemMultiple[i][1];
			if (!ItemId.equals("")) {
				count++;
				String Cost = dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PBUnitCost"));
//				BigDecimal value = new BigDecimal(Cost).add(new BigDecimal("100"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + Cost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 6, Cost.toString());
			}
		}

	}

//	String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
//	ExcelUtils excelUtils = new ExcelUtils();
//	String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//			+ "DBDETAILS.xlsx";
//	ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
//	logs.logging("INFO", "Unit Cost For This Item");
//	for (int i = 22; i < ItemMultiple.length; i++) {
//		String ItemId = ItemMultiple[i][1];
//		if (!ItemId.equals("")) {
//			String Cost = dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
//					commonFunc.getDataFromTestDataSheet("PBUnitCost"));
//			BigDecimal value = new BigDecimal(Cost).add(new BigDecimal("100"));
//			logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + Cost);
//			excelUtils.writeDataInExcel(Filepath, SheetName, i, 6, value.toString());
//		}
//	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyTowritePriceDeductionToWriteInExcelForPurchasebillDocument(String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Unit Cost For This Item");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String ItemId = ItemMultiple[i][1];
			if (!ItemId.equals("")) {
				String Cost = dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PBUnitCost"));
				BigDecimal value = new BigDecimal(Cost).subtract(new BigDecimal("100"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + Cost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 6, value.toString());
			}
		}

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	public void verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithValueDeduction() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostDeduction"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostDeduction"));
		ArrayList<String> GrnSeqNumber = new ArrayList<>();
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyTowritePriceDeductionToWriteInExcelForPurchasebillDocument(
						webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
				VerifySaveLine();
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	private void VerifyToCreateBillBookingDocumentFromPendingLcDocument(String DocNo, String InvoicePfx) {
		flag = webDB.waitForElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PendingLc, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PendingLc, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.IframeForLc, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.IframeForLc, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PendingGrnLc, DocNo);
					// VariableHandler.getGrnDocNumber()
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.IframeForLc, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.IframeForLc, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.SearchButtonForLc,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.SearchButtonForLc, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.CheckAllForLc,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.CheckAllForLc, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.CreateDocForLc,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(5000);
									webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.CreateDocForLc,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PendingGrnLovInput,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Switched To Iframe");
										webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PendingGrnLovInput,
												ElementType.Xpath);
										commonFunc.searchAndSelectValueInFilters(
												PurchaseBillsLocatorsGetDocument.InvoiceLovForLc, InvoicePfx);
										flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.IframeForLc,
												ElementType.Xpath);
										if (flag) {
											webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.IframeForLc,
													ElementType.Xpath);
											flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OkBtnForLc,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Okay Button Is Displayed");
												webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OkBtnForLc,
														ElementType.Xpath);
												webDB.switchToDefaultContent();
												flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.LocationId,
														ElementType.Xpath);
												storePurchaseVoucherNumber();
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
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPending() throws IOException {
//		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//				webDB.getDataFromProperties("LandedCostTaxAddition"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//					"Sathi Ganesh");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
//		assertTrue(flag);

		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostDB"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostDB"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostDB"), i);
				verifyCreateJournalAndPost();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTds() throws IOException {
//		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//				webDB.getDataFromProperties("LandedCostWithTds"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//		verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//					"Sathi Ganesh");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
//		assertTrue(flag);
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostWTDS"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostWTDS"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
//				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//						LandedCostSupplier);
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
//				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostWTDS"), i);
				verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
				VerifySaveLine();
				verifyCreateJournalAndPost();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTdsAndEsi() throws IOException {
//		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//				webDB.getDataFromProperties("LandedCostWithTdsAndEsi"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//		verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//					"Sathi Ganesh");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
//		assertTrue(flag);
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostWTDSandESI"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostWTDSandESI"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
//				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//						LandedCostSupplier);
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
//				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostWTDSandESI"), i);
				verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
				VerifySaveLine();
				verifyCreateJournalAndPost();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTaxAddition() throws IOException {
//		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//				webDB.getDataFromProperties("LandedCostWithTaxAddition"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//		VerifyToChangeTheHsnCode();
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//					"Sathi Ganesh");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
//		assertTrue(flag);
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostHSNCHanged"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostHSNCHanged"));
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
				VerifyToChangeTheHsnCode();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifySaveLine();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateLineWiseQueryAfterHsnChanged(webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterChangedHsnToHeaderAmountForPurchaseBill(
						webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithNewChargeItem() throws IOException {
//		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//				webDB.getDataFromProperties("LandedCostTaxAddition"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//		verifyToAddNewItemInLandedCostType(webDB.getDataFromProperties("LandedCostTaxAddition"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//					"Sathi Ganesh");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
//		assertTrue(flag);
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostAdditem"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostAdditem"));
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
//				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//						LandedCostSupplier);
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
//				verifyToGetDocumentFromLandedCost();
				verifyToAddNewItemInLandedCostType(webDB.getDataFromProperties("LandedCostAdditem"));
				VerifySaveLine();
				VerifyToValidateLineWiseQueryAfterAddTheItem(webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBill(
						webDB.getDataFromProperties("LandedCostAdditem"));
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

//	/**
//	 * Verify Create Direct PO Adding Parameter
//	 * 
//	 * @author PremKumar.K RMIT
//	 * @param LocationId
//	 * @param SupplierName
//	 * @param POType
//	 */
//	public void verifyToCreateMaterialReceiptLandedCostInBillBookingFromGetDocument() throws IOException {
//		coreProcessPageFunctions.VerifyToCreateMaterialRequestForPurchaseBillLandedCost();
////		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
////				"Sathi Ganesh");
////		commonFunc.navigateToFinancialPurchaseBill();
////		verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
////				commonFunc.getDataFromTestDataSheet("PartyID"));
////		storePurchaseVoucherNumber();
////		verifyToGetDocumentFromLandedCost();
////		verifyCreateJournalAndPost();
////		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
////		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
////				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
////				System.getProperty("AshokUsername"));
////		flag = commonFunc.logoutUser();
////		if (flag) {
////			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
////					"Sathi Ganesh");
////			commonFunc.navigateToFinancialPurchaseBill();
////			verifyPurchaseBillApprovalStatus();
////			flag = commonFunc.logoutUser();
////		}
//		// assertTrue(flag);
//	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithValueAddition() throws IOException {
//		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//				webDB.getDataFromProperties("LandedCostTaxAddition"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//		VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostTaxAddition"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//					"Sathi Ganesh");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
//		assertTrue(flag);
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostAddition"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostAddition"));
		ArrayList<String> GrnSeqNumber = new ArrayList<>();
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
//				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//						LandedCostSupplier);
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
//				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
						webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				VerifySaveLine();
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithValueDeduction() throws IOException {
//		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//				webDB.getDataFromProperties("LandedCostValueWithDeduction"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//		VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostValueWithDeduction"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//					"Sathi Ganesh");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
//		assertTrue(flag);
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostDeduction"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostDeduction"));
		ArrayList<String> GrnSeqNumber = new ArrayList<>();
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
//				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//						LandedCostSupplier);
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
//				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyTowritePriceDeductionToWriteInExcelForPurchasebillDocument(
						webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
				VerifySaveLine();
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws IOException
	 */
	public void verifyToCreateGrnLandedCostInBillbookingFromAgainstPendingWithNewChargesAddition() throws IOException {
//		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//				webDB.getDataFromProperties("LandedCostTaxAddition"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//		verifyToAddNewItemInLandedCostType(webDB.getDataFromProperties("LandedCostTaxAddition"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		if (flag) {
//			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//					"Sathi Ganesh");
//			commonFunc.navigateToFinancialPurchaseBill();
//			verifyPurchaseBillApprovalStatus();
//			flag = commonFunc.logoutUser();
//		}
//		assertTrue(flag);
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostAdditem"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostAdditem"));
		for (int i = 22; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][69];
			if (!LandedCostItem.equals("")) {
//				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//						LandedCostSupplier);
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
//				verifyToGetDocumentFromLandedCost();
				verifyToAddNewItemInLandedCostType(webDB.getDataFromProperties("LandedCostAdditem"));
				VerifySaveLine();
				VerifyToValidateLineWiseQueryAfterAddTheItem(webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBill(
						webDB.getDataFromProperties("LandedCostAdditem"));
				VerifyToClickPostButton();
				waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				flag = webDB.waitForElement(PurchaseBillsLocators.PendingPurchaseBill, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Find Button Is clicked Successfully");
				}
			}
		}
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitem()
			throws Exception {
//		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(3000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferLC"));
//		verifyCreateJournalAndPost();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 23; i < PBMultipleline.length; i++) {
			String TotalAmount = PBMultipleline[i][2];
			String PurchaseBillHeaderAmount = PBMultipleline[i][45];
			if (!TotalAmount.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillHeaderTotalAmount")),
						PurchaseBillHeaderAmount);
				logs.logging("INFO",
						"After Deviation Purchase Bill Header Amount is Matched" + VariableHandler.getPbNumber());
			}
		}
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	private void VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(String DocNo, String InvoicePfx) {
		flag = webDB.waitForElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PendingSt, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PendingSt, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.IframeForSt, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.IframeForSt, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.GrnLovBtn, DocNo);
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.IframeForSt, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.IframeForSt, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.SearchButtonForSt,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.SearchButtonForSt, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.CheckAllForSt,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.CheckAllForSt, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.CreateDocForSt,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(5000);
									webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.CreateDocForSt,
											ElementType.Xpath);
									flag = webDB.waitForElement(
											PurchaseBillsLocatorsGetDocument.PendingGrnStInvLovInput,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Switched To Iframe");
										webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PendingGrnStInvLovInput,
												ElementType.Xpath);
										commonFunc.searchAndSelectValueInFilters(
												PurchaseBillsLocatorsGetDocument.InvoiceLovForSt, InvoicePfx);
										flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.IframeForSt,
												ElementType.Xpath);
										if (flag) {
											webDB.switchToFrame(PurchaseBillsLocatorsGetDocument.IframeForSt,
													ElementType.Xpath);
											flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OkBtnForSt,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Okay Button Is Displayed");
												webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OkBtnForSt,
														ElementType.Xpath);
												webDB.switchToDefaultContent();
												flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.LocationId,
														ElementType.Xpath);
												storePurchaseVoucherNumber();
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
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpenseAccountForStockitem()
			throws Exception {
//		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		verifyAddExpenseTypeForStockTransfer(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
//		try {
//			grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//					webDB.getDataFromProperties("StockTransferGenLocalItemandExp"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
////		 VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEX"));
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyCreateJournalAndPost();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEX"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	private void VerifytoPutPriceDeviationForStocktransferType(String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			System.out.println(GRNMultipleline.length);
			String ITEM_NAME = GRNMultipleline[i][1];
			String Cost = GRNMultipleline[i][6];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField(i - 21), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField(i - 21), ElementType.Xpath);
					webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost, ElementType.Xpath);
					logs.logging("INFO", "Entered Cost Is:" + Cost);
					commonFunc.hardWait(3000);
					webDB.pressTabKey();
					commonFunc.hardWait(1000);
					VerifySaveLine();

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithOutDeviationStockitem()
			throws Exception {
//		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		// VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
//		try {
//			grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//					webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//		// VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenLocalWOD"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyCreateJournalAndPost();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEX"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpenseAccountForStockitem()
			throws Exception {
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferGenLocalItemandExp"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemandExp"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyCreateJournalAndPost();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEX"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void verifyAddTheItemBasedOnTypeForStockTransfer(String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 23; i < GRNMultipleline.length; i++) {
			System.out.println(GRNMultipleline.length);
			String Type = GRNMultipleline[i][75];
			String Cost = GRNMultipleline[i][6];
			String GLAccount = GRNMultipleline[i][77];
			String ITEM_ID = GRNMultipleline[i][1];
			String Qty = GRNMultipleline[i][5];
			String HSN = GRNMultipleline[i][68];
			String TaxAmountCGST = GRNMultipleline[i][21];
			String TaxAmountSGST = GRNMultipleline[i][22];
			String TaxAmountIGST = GRNMultipleline[i][23];
			String GSTSupply = GRNMultipleline[i][74];
			String SeqNumber = GRNMultipleline[i][0];
			String PBLineAmount = GRNMultipleline[i][18];
			String PBDiscAmount = GRNMultipleline[i][17];
			if (!Type.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
					logs.logging("INFO", "Add Button is Clicked");
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddTypeExp, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBAddTypeExp, ElementType.Xpath);
						webDB.selectDropDownByText(PurchaseBillsLocatorsGetDocument.PBAddTypeExp, Type,
								ElementType.Xpath);
						logs.logging("INFO", "Field Is Clicked");
						if (flag) {
							if (Type.equals(commonFunc.getDataFromTestDataSheet("Expensetype"))) {
								VerifyToCreateExpenseType(GLAccount, Cost, HSN, GSTSupply, SeqNumber, TaxAmountCGST,
										TaxAmountSGST, TaxAmountIGST, PBLineAmount, PBDiscAmount);
							} else if (Type.equals(commonFunc.getDataFromTestDataSheet("ItemType"))) {
								VerifyToCreatetheItemTypeDocument(ITEM_ID, Cost, Qty, HSN, GLAccount, TaxAmountCGST,
										TaxAmountSGST, TaxAmountIGST, GSTSupply, PBLineAmount, PBDiscAmount);
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
	public void VerifyToCreateExpenseType(String GLAccount, String Cost, String HSN, String GSTSupply, String SeqNumber,
			String TaxAmountCGST, String TaxAmountSGST, String TaxAmountIGST, String PBLineAmount,
			String PBDiscAmount) {

		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GlAccDescField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.GlAccDescField, ElementType.Xpath);
			logs.logging("INFO", "Field Is Clicked");
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GlAccLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.GlAccLov, GLAccount);
				logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField, ElementType.Xpath);
					webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost, ElementType.Xpath);
					logs.logging("INFO", "Entered Cost Is:" + Cost);
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeHsnField,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeHsnField,
								ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferHsnLov,
								ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(
									PurchaseBillsLocatorsGetDocument.StockTransferHsnLov, HSN);
							logs.logging("INFO", "Entered Cost Is:" + HSN);
							commonFunc.checkRefreshCellsDisappear();
							webDB.pressTabKey();
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							verifyToSelectGstSupply(GSTSupply);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							webDB.pressTabKey();
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							VerifySaveLine();
//							 VerifyToValidateAfterPriceDeviationForAfterAddItem(i, SheetName);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							verifyExpenseTypeDocumentDBValidation(SeqNumber, TaxAmountCGST, TaxAmountSGST,
									TaxAmountIGST, PBLineAmount, PBDiscAmount);

						}

					}
				}
			}
		}
		assertTrue(flag);
	}
//	TEM_ID, TaxAmountCGST,
//	TaxAmountSGST,

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void VerifyToCreatetheItemTypeDocument(String ITEM_ID, String Cost, String Qty, String HSN, String GLAccount,
			String TaxAmountCGST, String TaxAmountSGST, String TaxAmountIGST, String GSTSupply, String PBLineAmount,
			String PBDiscAmount) {
		webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBItemField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBItemField, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBItemLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PBItemLov, ITEM_ID);
				logs.logging("INFO", "Type Selected SuccessFully");
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeQtyField,
						ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GlAccDescField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.GlAccDescField, ElementType.Xpath);
					logs.logging("INFO", "Field Is Clicked");
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GlAccLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.GlAccLov, GLAccount);
						logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
						commonFunc.checkRefreshCellsDisappear();
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeQtyField,
									ElementType.Xpath);
							webDB.clearText(PurchaseBillsLocatorsGetDocument.StockTransferQtyInput, ElementType.Xpath);
							webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.StockTransferQtyInput, Qty,
									ElementType.Xpath);
							logs.logging("INFO", "Entered Qty Is:" + Cost);
							flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField, ElementType.Xpath);
								webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
								webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Cost Is:" + Cost);
								flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeHsnField,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeHsnField,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferHsnLov,
											ElementType.Xpath);
									if (flag) {
										commonFunc.searchAndSelectValueInFilters(
												PurchaseBillsLocatorsGetDocument.StockTransferHsnLov, HSN);
										logs.logging("INFO", "Entered HSN Is:" + HSN);
										commonFunc.checkRefreshCellsDisappear();
										webDB.pressTabKey();
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(1000);
										verifyToSelectGstSupply(GSTSupply);
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(1000);
										webDB.pressTabKey();
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(1000);
										VerifySaveLine();
										commonFunc.checkRefreshCellsDisappear();
										commonFunc.hardWait(2000);
										VerifyToValidateStockTransferItemTypeValidate(ITEM_ID, TaxAmountCGST,
												TaxAmountSGST, TaxAmountIGST, PBLineAmount, PBDiscAmount);

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
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToValidateStockTransferItemTypeValidate(String ITEM_ID, String TaxAmountCGST,
			String TaxAmountSGST, String TaxAmountIGST, String PBLineAmount, String PBDiscAmount) {
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST);
		logs.logging("INFO", "CGST Matched With DB Values For This Item " + ITEM_ID);
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST);
		logs.logging("INFO", "SGST Matched With DB Values For This Item " + ITEM_ID);
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST);
		logs.logging("INFO", "IGST Matched With DB Values For This Item " + ITEM_ID);
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PBLineAmount")), PBLineAmount);
		logs.logging("INFO", "Line Amount Matched With DB Values For This Item " + ITEM_ID);
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PBDiscAmount")), PBDiscAmount);
		logs.logging("INFO", "Discount Amount Matched With DB Values For This Item " + ITEM_ID);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeItemAutoCheck()
			throws Exception {
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		VerifyToClickReferenceFieldAndClickIgstTax();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferLC"),
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		VerifyToClickReferenceFieldAndClickIgstTax();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToClickReferenceFieldAndClickIgstTax();Need to Fix it
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferLC"),
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

//	VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//	webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));	

//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
	// VariableHandler.getGrnDocNumber()
//	VerifyToClickReferenceFieldAndClickRcmFlag();
//	VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//	verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//	VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//	verifyCreateJournalAndPost();

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void OverAllSaveBtnForPurchaseBill() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Igst Flag Successfully Clicked");

			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "OverAll Save Button Successfully Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @return
	 * @throws Exception
	 */
	public boolean VerifyToClickReferenceFieldAndClickIgstTax(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Igst Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Igst Flag Successfully Clicked");
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					validAllowIgstCheckToWriteExcelValue(SheetName);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");

						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
							// validAllowIgstCheckboxForTaxAmountCalculation();
							// excelUtils.writeDataInExcel(filepath, SheetName, 2, 3, "2");
							// VerifyToWriteExcelValueInNumber(SheetName)
						}
					}
				}
			}
		}
		assertTrue(flag);
		return flag;

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToClickReferenceFieldAndClickIgstTaxWithExcelWrite(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Igst Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
				System.out.println("2");
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				validAllowIgstCheckboxForTaxAmountCalculation(SheetName);
				System.out.println("3");
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Igst Flag Successfully Clicked");

					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");

						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */

	public void VerifyToWriteExcelValueInNumber(String SheetName) {
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		excelUtils.writeDataInExcel(Filepath, SheetName, 20, 2, "2");
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */

	public void VerifyToWriteExcelValueInNumberRcmField(String SheetName) {
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		excelUtils.writeDataInExcel(Filepath, SheetName, 20, 21, "2");
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */

	public void writeDataAndForceExcelRecalc(String filePath, String sheetName, int rowNum, int colNum, double value) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			Row row = sheet.getRow(rowNum);
			if (row == null)
				row = sheet.createRow(rowNum);

			Cell cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

// Write numeric value (2)
			cell.setCellValue(value);

// ⭐ THIS IS THE ONLY THING YOU NEED
			workbook.setForceFormulaRecalculation(true);

			fis.close();

			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close();
			workbook.close();

			System.out.println("Successfully written and marked for recalculation");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	/**
//	 * Verify Create Direct PO Adding Parameter
//	 * 
//	 * @author PremKumar.K RMIT
//	 * @param LocationId
//	 * @param SupplierName
//	 * @param POType
//	 * @throws Exception
//	 */
//
//	public void VerifyToWriteExcelValueInNumberIgstField(String SheetName) {
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "DBDETAILS.xlsx";
//		excelUtils.writeDataInExcel(Filepath, SheetName, 20, 2, "2");
//	
//	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToWriteExcelValueInNumberIgstField(String sheetName) {
		String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";

		writeDataAndForceExcelRecalc(filePath, sheetName, 20, 2, 2);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */

	public void VerifyToAfterWriteTheExcelValueInNumberRcmField(String SheetName) {
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		excelUtils.writeDataInExcel(Filepath, SheetName, 20, 21, "1");
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */

	public void VerifyToAfterWriteTheExcelValueInNumberIgstField(String sheetName) {
		String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";

		writeDataAndForceExcelRecalc(filePath, sheetName, 20, 2, 1);
	}

//	ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
//	logs.logging("INFO", "Unit Cost For This Item");
//	for (int i = 22; i < ItemMultiple.length; i++) {
//		String ItemId = ItemMultiple[i][1];
//		if (!ItemId.equals("")) {
//			String Cost = dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ItemId,
//					commonFunc.getDataFromTestDataSheet("PBUnitCost"));
//			BigDecimal value = new BigDecimal(Cost).subtract(new BigDecimal("100"));
//			logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + Cost);

//public void AfterIgstflagCheckedWriteExcelValue() {
//	webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
//	flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
//
//	if (flag) {
//	    logs.logging("INFO", "Igst Flag Successfully Clicked");
//
//	    // ✅ CHECK IGST FLAG STATUS
//	    WebElement igstFlag = webDB.getElement(
//	            PurchaseBillsLocatorsGetDocument.AllowIgstFlag,
//	            ElementType.Xpath
//	    );
//
//	    if (igstFlag.isSelected()) {
//	        writeExcelValue(2);
//	        logs.logging("INFO", "IGST Flag Checked → Excel Updated with value 2");
//	    }
//
//	    webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
//	}
//
//
//}
	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithDeviationAndExpenseAccountStockitemserviceItemAndChargeItem()
			throws Exception {
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferGenLocalItemandExp"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		VerifyToClickReferenceFieldAndClickIgstTax();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenLocalItemandExp"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemandExp"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickIgstTaxWithExcelWrite((webDB.getDataFromProperties("StocktransferLC")));
//		System.out.println("test");
//		// validAllowIgstCheckboxForTaxAmountCalculation((webDB.getDataFromProperties("hdhd")));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferLC"),
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationStockitemserviceItemAndChargeItem()
			throws Exception {
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		VerifyToClickReferenceFieldAndClickIgstTax();
//		// VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifyToClickReferenceFieldAndClickIgstTax();Need to Fix it
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		// VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferLC"),
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationAndExpenseAccountStockitemserviceItemAndChargeItem()
			throws Exception {

//		try {
//			grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//					webDB.getDataFromProperties("StockTransferGenLocalItemandExp"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		VerifyToClickReferenceFieldAndClickIgstTax();
//		// VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemandExp"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		// VerifyToClickReferenceFieldAndClickIgstTax();Need to Fix it
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		// VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferLC"),
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @return
	 * @throws Exception
	 */
	public boolean VerifyToClickReferenceFieldAndClickRcmFlag(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "RCM Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "RCMt Flag Successfully Clicked");
					validAllowRcmCheckToWriteExcelValue(SheetName);
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");

						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);
		return flag;
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmtaxAgainstStockTransferWithDeviationStockItemserviceItemAndChargeItem()
			throws Exception {
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferLCRCM"),
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		assertTrue(flag);
	}

//	VerifyToClickReferenceFieldAndClickIgstTax();
	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithInterStateGstAgainstStockTransferWithDeviationForStockitem()
			throws Exception {
//		try {
//			grnPageFunc
//					.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspectionInterStateSupplier(
//							webDB.getDataFromProperties("StockTransferGenInterItemType"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenInterItemType"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenInterItemType"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferInterstate"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.checkRefreshCellsDisappear();
		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferInterstate"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		verifyCreateJournalAndPost();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationAndExpensesAccountStockitemServiceitemAndChargeItem()
			throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferInterstate"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithDeviationStockitemserviceItemAndChargeItem()
			throws Exception {
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		// VariableHandler.getGrnDocNumber()
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
////		verifyCreateJournalAndPost();
////		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickPostButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemserviceItemAndChargeItem()
			throws Exception {
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockitemServiceItemAndChargeItem()
			throws Exception {
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferISForRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToWriteExcelValue(String SheetName, String SheetName1) {
		if (flag) {
			boolean isRcmFlagClicked = VerifyToClickReferenceFieldAndClickRcmFlag(SheetName);
			if (isRcmFlagClicked) {
				VerifyToWriteExcelValueInNumberRcmField(SheetName1);
				logs.logging("INFO", "RCM flag click verified. SuccessFully Write  Excel value write in Sheet 2.");
			} else {
				logs.logging("INFO", "RCM flag click verification failed. Skipping Excel value write.");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToWriteExcelValueForIgst(String SheetName, String SheetName1) {
		if (flag) {
			boolean isIgstFlagClicked = VerifyToClickReferenceFieldAndClickIgstTax(SheetName);
			if (isIgstFlagClicked) {
				VerifyToWriteExcelValueInNumberIgstField(SheetName1);
				logs.logging("INFO", "IGST flag click verified. SuccessFully Write  Excel value write in Sheet 2.");
			} else {
				logs.logging("INFO", "IGST flag click verification failed. Skipping Excel value write.");
			}
		}
		assertTrue(flag);
	}
//	VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferInterstate"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferInterstate"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyCreateJournalAndPost();
//	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
	// VariableHandler.getGrnDocNumber()
//	VerifyToClickReferenceFieldAndClickRcmFlag();

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	private void ValidateHeaderDetails() {

		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderLocation")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderLocation")));
		logs.logging("INFO", "Location Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderUnit")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderUnit")));
		logs.logging("INFO", "Unit Is Matched For GRN and PB ");
//		verifyAssertionBothValues(
//				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
//						commonFunc.getDataFromTestDataSheet("GRNHeaderSupplierID")),
//				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
//						commonFunc.getDataFromTestDataSheet("PBHeaderSupplierID")));
//		logs.logging("INFO", "Supplier ID Is Matched For GRN and PB ");
//		verifyAssertionBothValues(
//				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
//						commonFunc.getDataFromTestDataSheet("GRNHeaderDCNO")),
//				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
//						commonFunc.getDataFromTestDataSheet("PBHeaderDCNO")));
//		logs.logging("INFO", "DC No Is Matched For GRN and PB ");
//		verifyAssertionBothValues(
//				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
//						commonFunc.getDataFromTestDataSheet("GRNHeaderDCDate")),
//				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
//						commonFunc.getDataFromTestDataSheet("PBHeaderDCDate")));
//		logs.logging("INFO", "DC Date Is Matched For GRN and PB ");
//		verifyAssertionBothValues(
//				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
//						commonFunc.getDataFromTestDataSheet("GRNHeaderGrnDate")),
//				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
//						commonFunc.getDataFromTestDataSheet("PBHeaderGrnDate")));
//		logs.logging("INFO", "GRN Date Is Matched For GRN and PB ");
//		verifyAssertionBothValues(
//				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
//						commonFunc.getDataFromTestDataSheet("GRNHeaderGrnNo")),
//				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
//						commonFunc.getDataFromTestDataSheet("PBHeaderGrnNo")));
//		logs.logging("INFO", "GRN No Is Matched For GRN and PB ");
//		verifyAssertionBothValues(
//				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
//						commonFunc.getDataFromTestDataSheet("GRNHeaderGrnNo")),
//				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
//						commonFunc.getDataFromTestDataSheet("PBHeaderGrnNo")));
//		logs.logging("INFO", "GRN Value Is Matched For GRN and PB ");
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void ValidateLandedCostFieldCompareForGrnAndPurchaseBill(String SheetName) {
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 1; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][0];
			String LandedCostSupplier = itemmultiple[i][1];
			String LandedCostAmount = itemmultiple[i][2];
			String GSTSupply = itemmultiple[i][5];
			String AICSelection = itemmultiple[i][4];
			String SeqNumber = Integer.toString(i);
			if (!LandedCostItem.equals("")) {
//				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
//						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNItemID")), LandedCostSupplier);
//				logs.logging("INFO", "GRN ItemID IS Matched With DB Values For This Item " + LandedCostSupplier);
//				verifyAssertionBothValues(
//						dbCommonFunc.GrnLandedCostQuery(VariableHandler.getGrnDocNumber(),SeqNumber,
//								commonFunc.getDataFromTestDataSheet("GRNHeaderUnit")),
//						dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),SeqNumber,
//								commonFunc.getDataFromTestDataSheet("PendingGRNHeaderUnit")));
//				logs.logging("INFO", "Pending Unit Is Matched For GRN and PendingPB ");
//			}
//		}
			}
		}
	}

//	public void ValidateLandedCostField() {
//		verifyAssertionBothValues(
//				dbCommonFunc.GrnLandedCostQuery(VariableHandler.getGrnDocNumber(),
//						commonFunc.getDataFromTestDataSheet("GRNHeaderUnit")),
//				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
//						commonFunc.getDataFromTestDataSheet("PendingGRNHeaderUnit")));
//		logs.logging("INFO", "Pending Unit Is Matched For GRN and PendingPB ");
//

//	validAllowIgstCheckboxForTaxAmountCalculation();
//	excelUtils.writeDataInExcel(filepath, SheetName, 2, 3, "2");	
//	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void validAllowIgstCheckboxForTaxAmountCalculation(String SheetName) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
		if (flag) {
			boolean isIgstChecked = webDB.isElementSelected(PurchaseBillsLocatorsGetDocument.AllowIgstFlagEnabled,
					ElementType.Xpath);
			if (isIgstChecked) {
				logs.logging("INFO", "IGST checkbox is checked");
				VerifyToWriteExcelValueInNumber(SheetName);
				logs.logging("INFO", "SucessFully Written");
			} else {
				logs.logging("INFO", "Tax Amount" + "Not Checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void validAllowRcmCheckToWriteExcelValue(String SheetName) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
		if (flag) {
			boolean isIgstChecked = webDB.isElementSelected(PurchaseBillsLocatorsGetDocument.AllowRCMFlagEnabled,
					ElementType.Xpath);
			if (isIgstChecked) {
				logs.logging("INFO", "IGST checkbox is checked");
				VerifyToWriteExcelValueInNumberRcmField(SheetName);
				logs.logging("INFO", "SucessFully Written");
			} else {
				logs.logging("INFO", "Tax Amount" + "Not Checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void validAllowIgstCheckToWriteExcelValue(String SheetName) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
		if (flag) {
			boolean isIgstChecked = webDB.isElementSelected(PurchaseBillsLocatorsGetDocument.AllowIgstFlagEnabled,
					ElementType.Xpath);
			if (isIgstChecked) {
				logs.logging("INFO", "IGST checkbox is checked");
				VerifyToWriteExcelValueInNumberIgstField(SheetName);
				logs.logging("INFO", "SucessFully Written");
			} else {
				logs.logging("INFO", "Tax Amount" + "Not Checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	private void ValidatePurchaseBillsPendingGRNDetails() {
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderUnit")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNHeaderUnit")));
		logs.logging("INFO", "Pending Unit Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderLocation")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNHeaderLocation")));
		logs.logging("INFO", "Pending Location Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderSupplierID")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNSupplierID")));
		logs.logging("INFO", "Pending Supplier ID Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderCurrency")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNCurrency")));
		logs.logging("INFO", "Pending Currency Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderDocNo")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNDocNo")));
		logs.logging("INFO", "Pending GRN Document No Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderDocDate")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNDocDate")));
		logs.logging("INFO", "Pending GRN Document Date Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PGGRNPrfx")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNPrfx")));
		logs.logging("INFO", "Pending GRN Prefix Is Matched For GRN and PendingPB ");

	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	private void ValidatePurchaseBillsPendingGRNItemDetails(String SheetName) {
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String SeqNumber = Integer.toString(i - 21);
			String ItemId = GRNMultipleline[i][1];
			String ItemDesc = GRNMultipleline[i][2];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];
			String UOM = GRNMultipleline[i][3];
			String LINEAMNT = GRNMultipleline[i][18];
			String DISCAMNT = GRNMultipleline[i][17];
			if (!ItemId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNItemID")), ItemId);
				logs.logging("INFO", "GRN ItemID IS Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNItemDesc")), ItemDesc);
				logs.logging("INFO", "GRN Item Description IS Matched With DB Values For This Item " + ItemDesc);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNQty")), QTY);
				logs.logging("INFO", "GRN Quantity IS Matched With DB Values For This Item " + QTY);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNCost")), COST);
				logs.logging("INFO", "GRN Item Cost IS Matched With DB Values For This Item " + COST);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNUOM")), UOM);
				logs.logging("INFO", "GRN UOM IS Matched With DB Values For This Item " + UOM);
//				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
//						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNLineAmnt")), LINEAMNT);
//				logs.logging("INFO", "GRN Line Amount IS Matched With DB Values For This Item " + LINEAMNT);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNDiscAmnt")), DISCAMNT);
				logs.logging("INFO", "GRN Discount Amount IS Matched With DB Values For This Item " + DISCAMNT);
			}
		}
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Deepan-GWL
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual DataBase Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual DataBase Value 8: " + Actual + " And Expected Values : " + Expected);
		} else {
			logs.logging("INFO",
					"Mismatch With Actual DataBase Value : " + Actual + " And Expected Values " + Expected);
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitemServiceItemAndChargeItem()
			throws Exception {
//		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();

		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();

//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(3000);
//		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
//				webDB.getDataFromProperties("StocktransferUR"));
////		verifyCreateJournalAndPost();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickPostButton();

		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);

		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithDeviationandExpensesAccountStockItemServiceItemAndchargeItem()
			throws Exception {

//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferLCRCM"),
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationStockitemServiceItemAndChargeItem()
			throws Exception {

//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferLCRCM"),
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {

//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferLCRCM"),
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLCRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		assertTrue(flag);

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToSelectGstSupply(String GSTSupply) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBGstSupplyergrid, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBGstSupplyergrid, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBSupplySelect, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (flag) {
				webDB.selectDropDownByValue(PurchaseBillsLocatorsGetDocument.PBSupplySelect, GSTSupply,
						ElementType.Xpath);
				logs.logging("INFO", "Gst Supply Selected Successfully");
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddSave, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Gst Supply Selected Successfully");
				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
//		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();

		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();

//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(3000);
//		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
//				webDB.getDataFromProperties("StocktransferUR"));
////		verifyCreateJournalAndPost();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickPostButton();

		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
//		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(3000);
//		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
//				webDB.getDataFromProperties("StocktransferUR"));
////		verifyCreateJournalAndPost();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickPostButton();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
//		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
//		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//				"Sathi Ganesh");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
//		VerifySaveLine();
//		verifyCreateJournalAndPost();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("AshokUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}
	// VariableHandler.getGrnDocNumber()
//	VerifyToClickReferenceFieldAndClickRcmFlag();
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//	commonFunc.checkRefreshCellsDisappear();
//	// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
////	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
////	verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(3000);
//	VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
//			webDB.getDataFromProperties("StocktransferUR"));
////	verifyCreateJournalAndPost();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();

//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);

	/**
	 * Verift Tax Amount After Check a Allows Igst Flag
	 * 
	 * @author PremKumar.K-RMITS
	 * @param SheetName
	 */
	public void verifyExpenseTypeDocumentDBValidation(String SeqNumber, String TaxAmountCGST, String TaxAmountSGST,
			String TaxAmountIGST, String PBLineAmount, String PBDiscAmount) {
		verifyAssertionBothValues(dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getPbNumber(), SeqNumber,
				commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST);
		logs.logging("INFO", "Cgst IS Matched With DB Values For This Item " + TaxAmountCGST);
		verifyAssertionBothValues(dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getPbNumber(), SeqNumber,
				commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST);
		logs.logging("INFO", "Sgst IS Matched With DB Values For This Item " + TaxAmountSGST);
		verifyAssertionBothValues(dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getPbNumber(), SeqNumber,
				commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST);
		logs.logging("INFO", "Igst IS Matched With DB Values For This Item " + TaxAmountIGST);
		verifyAssertionBothValues(dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getPbNumber(), SeqNumber,
				commonFunc.getDataFromTestDataSheet("PBLineAmount")), PBLineAmount);
		logs.logging("INFO", "Line Amount IS Matched With DB Values For This Item " + PBLineAmount);
		verifyAssertionBothValues(dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getPbNumber(), SeqNumber,
				commonFunc.getDataFromTestDataSheet("PBDiscAmount")), PBDiscAmount);
		logs.logging("INFO", "Disc Amount IS Matched With DB Values For This Item " + PBDiscAmount);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToCreateUnregisteredSupplierWithLocalGSTAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeitem()
			throws Exception {
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferURINTR"),
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferURINTR"),
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		assertTrue(flag);

	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		assertTrue(flag);

	}

}
