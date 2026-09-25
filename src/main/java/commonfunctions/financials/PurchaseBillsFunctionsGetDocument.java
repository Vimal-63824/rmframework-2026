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
import java.util.List;

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
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import org.testng.Assert;
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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostDB"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostDB"), i);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostDB"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostDB"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostDB"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("LandedCostDB"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostDB"));
				commonFunc.checkRefreshCellsDisappear();
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

//	VerifyToClickCreateJournalButton();
//	verifyToValiadteJournalPartForLandedCostType(webDB.getDataFromProperties("LandedCostDB"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(
//			webDB.getDataFromProperties("LandedCostDB"),
//			commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyToValiadteJournalDetailPartForAccuralAccountForLandedCostTypeCommon(
//			webDB.getDataFromProperties("LandedCostDB"),
//			commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();
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
			String PurchaseBillHeaderAmount = PBMultipleline[i][28];
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
			System.out.println("Tax Amount is" + ITEM_ID);
//			String sys = PBMultipleline[i][13];
//			System.out.println("Tax Amount is" + sys);
			String TaxAmountCGST = PBMultipleline[i][21];
			System.out.println("Tax Amount is" + TaxAmountCGST);
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

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		int count = 0;
		int maxRows = 1;
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
				count++;
				if (count == maxRows) {
					break;
				}

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
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(String SheetName) {

		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		int count = 0;
		int maxRows = 1;
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
				count++;
				if (count == maxRows) {
					break;
				}
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
			if (ITEM_ID.equals("")) {
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
		for (int i = 22; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String ItemID = PBMultipleline[i][1];
			String PurchaseBillHeaderRoundOfAmount = PBMultipleline[i][45];
			if (ItemID == null || ItemID.trim().isEmpty()) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillHeaderTotalAmount")),
						PurchaseBillHeaderRoundOfAmount);
				logs.logging("INFO", "RoundOff Header Amount Matched" + VariableHandler.getPbNumber());
				break;
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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostWTDS"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][70];
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
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostWTDS"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostWTDS"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED_WITH_TDS"));
				commonFunc.checkRefreshCellsDisappear();
				verifyPraxisTDSForStandardType(webDB.getDataFromProperties("LandedCostWTDS"),
						commonFunc.getDataFromTestDataSheet("PRAXAIR_INDIA_PVT_LTD"),
						commonFunc.getDataFromTestDataSheet("194C_TDS"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("LandedCostWTDS"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostWTDS"));
				commonFunc.checkRefreshCellsDisappear();
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}
//	verifyCreateJournalAndPost();
//	VerifyToClickCreateJournalButton();
//	verifyToValiadteJournalPartForLandedCostType(webDB.getDataFromProperties("LandedCostWTDS"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(
//			webDB.getDataFromProperties("LandedCostWTDS"),
//			commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED_WITH_TDS"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyPraxisTDS(webDB.getDataFromProperties("LandedCostWTDS"),
//			commonFunc.getDataFromTestDataSheet("PRAXAIR_INDIA_PVT_LTD"),
//			commonFunc.getDataFromTestDataSheet("194C_TDS"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();
//	verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(
//	webDB.getDataFromProperties("LandedCostDB"),
//	commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
//	verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
//	webDB.getDataFromProperties("LandedCostDB"),
//	commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
//verifyPraxisTDS(webDB.getDataFromProperties("LandedCostWTDS"),
//	commonFunc.getDataFromTestDataSheet("PRAXAIR_INDIA_PVT_LTD"),
//	commonFunc.getDataFromTestDataSheet("194C_TDS"));

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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostWTDSandESI"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][70];
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
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostWTDSandESI"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostWTDSandESI"),
						commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED_WITH_TDS"));
				commonFunc.checkRefreshCellsDisappear();
				verifyPraxisTDSForStandardType(webDB.getDataFromProperties("LandedCostWTDSandESI"),
						commonFunc.getDataFromTestDataSheet("SRI_LAKSHMI_ELECTRONICS"),
						commonFunc.getDataFromTestDataSheet("194C_TDS"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostWTDSandESI"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostWTDSandESI"));
				commonFunc.checkRefreshCellsDisappear();
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}
////verifyCreateJournalAndPost();
//VerifyToClickCreateJournalButton();
//verifyToValiadteJournalPartForLandedCostType(webDB.getDataFromProperties("LandedCostWTDSandESI"));
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(
//		webDB.getDataFromProperties("LandedCostWTDSandESI"),
//		commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED_WITH_TDS"));
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//verifyPraxisTDS(webDB.getDataFromProperties("LandedCostWTDSandESI"),
//		commonFunc.getDataFromTestDataSheet("SRI_LAKSHMI_ELECTRONICS"),
//		commonFunc.getDataFromTestDataSheet("194C_TDS"));
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//VerifyToClickPostButton();
//	verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(
//	webDB.getDataFromProperties("LandedCostDB"),
//	commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
//	verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
//	webDB.getDataFromProperties("LandedCostDB"),
//	commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
//verifyPraxisTDS(webDB.getDataFromProperties("LandedCostWTDS"),
//	commonFunc.getDataFromTestDataSheet("PRAXAIR_INDIA_PVT_LTD"),
//	commonFunc.getDataFromTestDataSheet("194C_TDS"));

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

		VerifyToAfterWriteTheExcelValueInNumbeForTaxPct(webDB.getDataFromProperties("LandedCostHSNCHanged"));
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostHSNCHanged"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
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
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
				commonFunc.checkRefreshCellsDisappear();
				VerifyToChangeTheHsnCode(commonFunc.getDataFromTestDataSheet("HsnAddition"));
				commonFunc.checkRefreshCellsDisappear();
				VerifySaveLine();
				verifyToWritetaxPctValue(webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateLineWiseQueryAfterHsnChanged(webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostHSNCHanged"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostHSNCHanged"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(
//			webDB.getDataFromProperties("LandedCostHSNCHanged"),
//			commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyToValiadteJournalDetailPartForAccuralAccountForLandedCostTypeCommon(
//			webDB.getDataFromProperties("LandedCostHSNCHanged"),
//			commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterChangedHsnToHeaderAmountForPurchaseBill(
//			webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();
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
	private void VerifyToChangeTheHsnCode(String HSN) {
		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath);
		}
//		commonFunc.verifyAddTableSpecs(PurchaseBillsLocatorsGetDocument.PBHSNCode(),
//				PurchaseBillsLocatorsGetDocument.PBHsnInput(), HSN);
//		logs.logging("INFO", "Entered HSN  Is:" + HSN);
//		commonFunc.checkRefreshCellsDisappear();
//		webDB.moveToElement(PurchaseBillsLocatorsGetDocument.TableData, ElementType.Xpath);
//		webDB.moveToElement(PurchaseBillsLocatorsGetDocument.HsnFieldGrid, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBHSNCode(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBHSNCode(), ElementType.Xpath);
//			flag = webDB.waitForEleme?nt(PurchaseBillsLocatorsGetDocument.HsnLov, ElementType.Xpath);
//			if (flag) {
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.HsnLov,
					HSN);
			System.out.println("HSN Changed SuccessFully");
			webDB.pressTabKey();
		}
		assertTrue(flag);
	}

	/**
	 * } This Methods Is Used for Approve the Document
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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostAdditem"));
		for (int i = 23; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				verifyAddTheItemBasedOnTypeForLandedCostType(webDB.getDataFromProperties("LandedCostAdditem"));
				VerifySaveLine();
				VerifyToValidateLineWiseQueryAfterAddTheItemForLandedCostType(
						webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostAdditem"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostAdditem"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

//	verifyToAddNewItemInLandedCostType(webDB.getDataFromProperties("LandedCostAdditem"));
//	VerifyToValidateLineWiseQueryAfterAddTheItem(webDB.getDataFromProperties("LandedCostAdditem"));
//	VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBill(
//	webDB.getDataFromProperties("LandedCostAdditem"));
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
//				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField(i - 21), ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField(i - 21), ElementType.Xpath);
//		
//					webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
//					webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost, ElementType.Xpath);
				commonFunc.verifyAddTableSpecs(PurchaseBillsLocatorsGetDocument.PBInvoiceUnitCost(),
						PurchaseBillsLocatorsGetDocument.PBInvoiceUnitCostInput(), Cost);
				logs.logging("INFO", "Entered Supply Cost Is:" + Cost);
				commonFunc.checkRefreshCellsDisappear();
					logs.logging("INFO", "Entered Cost Is:" + Cost);
					commonFunc.hardWait(3000);
					webDB.pressTabKey();

				}
			}
		}
//		assertTrue(flag);
	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingSuppQuantity(String QTY) {
		commonFunc.verifyAddTableSpecs(PurchaseBillsLocatorsGetDocument.PBInvoiceUnitCost(),
				PurchaseBillsLocatorsGetDocument.PBInvoiceUnitCostInput(), QTY);
		logs.logging("INFO", "Entered Supply Quantity Is:" + QTY);
		commonFunc.checkRefreshCellsDisappear();
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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
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
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				commonFunc.checkRefreshCellsDisappear();
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("RoundOFValue"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("RoundOFValue"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("RoundOFValue"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("RoundOFValue"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("RoundOFValue"));
				commonFunc.checkRefreshCellsDisappear();
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmount(webDB.getDataFromProperties("RoundOFValue"));
//	VerifyToValidateRoundOffAmountForHeaderValue(webDB.getDataFromProperties("RoundOFValue"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();

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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
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
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				commonFunc.checkRefreshCellsDisappear();
				verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
						webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				VerifySaveLine();
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostAddition"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostAddition"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();
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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
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
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
						LandedCostSupplier);
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyToGetDocumentFromLandedCost();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				commonFunc.checkRefreshCellsDisappear();
				verifyTowritePriceDeductionToWriteInExcelForPurchasebillDocument(
						webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
				VerifySaveLine();
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostDeduction"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostDeduction"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				System.out.println("100");
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				System.out.println("101");
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostDeduction"));
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();

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

		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostDB"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostDB"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostDB"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostDB"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostDB"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("LandedCostDB"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostDB"));
				commonFunc.checkRefreshCellsDisappear();
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}
//	grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//	webDB.getDataFromProperties("LandedCostTaxAddition"));
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//	"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//	commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//verifyCreateJournalAndPost();
//waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//	webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//	System.getProperty("AshokUsername"));
//flag = commonFunc.logoutUser();
//if (flag) {
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//		"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//verifyPurchaseBillApprovalStatus();
//flag = commonFunc.logoutUser();
//}
//assertTrue(flag);
//	VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostDB"), i);
//	verifyCreateJournalAndPost();

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTds() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostWTDS"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostWTDS"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostWTDS"), i);
				verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
				VerifySaveLine();
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostWTDS"));
				commonFunc.checkRefreshCellsDisappear();
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostWTDS"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED_WITH_TDS"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyPraxisTDSForStandardType(webDB.getDataFromProperties("LandedCostWTDS"),
						commonFunc.getDataFromTestDataSheet("PRAXAIR_INDIA_PVT_LTD"),
						commonFunc.getDataFromTestDataSheet("194C_TDS"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("LandedCostWTDS"));
				commonFunc.checkRefreshCellsDisappear();
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostWTDS"));
				commonFunc.checkRefreshCellsDisappear();
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

//	grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//	webDB.getDataFromProperties("LandedCostWithTds"));
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//	"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//	commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
//VerifySaveLine();
//verifyCreateJournalAndPost();
//waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//	webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//	System.getProperty("AshokUsername"));
//flag = commonFunc.logoutUser();
//if (flag) {
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//		"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//verifyPurchaseBillApprovalStatus();
//flag = commonFunc.logoutUser();
//}
//assertTrue(flag);
//	verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(
//	webDB.getDataFromProperties("LandedCostDB"),
//	commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
//	verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
//	webDB.getDataFromProperties("LandedCostDB"),
//	commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
//verifyPraxisTDS(webDB.getDataFromProperties("LandedCostWTDS"),
//	commonFunc.getDataFromTestDataSheet("PRAXAIR_INDIA_PVT_LTD"),
//	commonFunc.getDataFromTestDataSheet("194C_TDS"));
//	ValidateHeaderDetails();
//	verifyToGetDocumentFromLandedCost();
//	verifyCreateJournalAndPost();
//	verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//	LandedCostSupplier);
	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTdsAndEsi() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostWTDSandESI"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostWTDSandESI"));
		for (int i = 22; i < itemmultiple.length; i++) {
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
				VerifySaveLine();
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostWTDSandESI"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				System.out.println("89654");
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostWTDSandESI"),
						commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED_WITH_TDS"));
				System.out.println("123645");
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyPraxisTDSForStandardType(webDB.getDataFromProperties("LandedCostWTDSandESI"),
						commonFunc.getDataFromTestDataSheet("SRI_LAKSHMI_ELECTRONICS"),
						commonFunc.getDataFromTestDataSheet("194C_TDS"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				System.out.println("100");
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostWTDSandESI"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				System.out.println("101");
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostWTDSandESI"));
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}
//	VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostWTDSandESI"), i);
//	grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//	webDB.getDataFromProperties("LandedCostWithTdsAndEsi"));
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//	"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//	commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//verifyAddTDSNumberForLandedCost(commonFunc.getDataFromTestDataSheet("TDSValue"));
//VerifySaveLine();
//verifyCreateJournalAndPost();
//waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//	webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//	System.getProperty("AshokUsername"));
//flag = commonFunc.logoutUser();
//if (flag) {
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//		"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//verifyPurchaseBillApprovalStatus();
//flag = commonFunc.logoutUser();
//}
//assertTrue(flag);
//	verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//	LandedCostSupplier);
//	verifyToGetDocumentFromLandedCost();
//	verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(
//	webDB.getDataFromProperties("LandedCostDB"),
//	commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
//	verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
//	webDB.getDataFromProperties("LandedCostDB"),
//	commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
//verifyPraxisTDS(webDB.getDataFromProperties("LandedCostWTDS"),
//	commonFunc.getDataFromTestDataSheet("PRAXAIR_INDIA_PVT_LTD"),
//	commonFunc.getDataFromTestDataSheet("194C_TDS"));
//	verifyCreateJournalAndPost();

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTaxAddition() throws IOException {
		VerifyToAfterWriteTheExcelValueInNumbeForTaxPct(webDB.getDataFromProperties("LandedCostHSNCHanged"));
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostHSNCHanged"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
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
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
				VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				VerifyToChangeTheHsnCode(commonFunc.getDataFromTestDataSheet("HsnAddition"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				VerifySaveLine();
				verifyToWritetaxPctValue(webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				VerifyToValidateLineWiseQueryAfterHsnChanged(webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostHSNCHanged"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostHSNCHanged"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				System.out.println("100");
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostHSNCHanged"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				System.out.println("101");
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostHSNCHanged"));
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}
//	grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//	webDB.getDataFromProperties("LandedCostWithTaxAddition"));
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//	"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//	commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//VerifyToChangeTheHsnCode();
//VerifySaveLine();
//verifyCreateJournalAndPost();
//waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//	webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//	System.getProperty("AshokUsername"));
//flag = commonFunc.logoutUser();
//if (flag) {
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//		"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//verifyPurchaseBillApprovalStatus();
//flag = commonFunc.logoutUser();
//}
//assertTrue(flag);
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(
//			webDB.getDataFromProperties("LandedCostHSNCHanged"),
//			commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyToValiadteJournalDetailPartForAccuralAccountForLandedCostTypeCommon(
//			webDB.getDataFromProperties("LandedCostHSNCHanged"),
//			commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterChangedHsnToHeaderAmountForPurchaseBill(
//			webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();
//	ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
//	VerifyToValidateHeaderAmountForPurchaseBill(webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
//	VerifyToChangeTheHsnCode();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifySaveLine();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateLineWiseQueryAfterHsnChanged(webDB.getDataFromProperties("LandedCostHSNCHanged"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterChangedHsnToHeaderAmountForPurchaseBill(
//			webDB.getDataFromProperties("LandedCostHSNCHanged"), i);
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();

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
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostAdditem"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostAdditem"));
		for (int i = 23; i < itemmultiple.length; i++) {
			System.out.println("Excel Length is" + itemmultiple.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String LandedCostItem = itemmultiple[i][1];
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
				verifyAddTheItemBasedOnTypeForLandedCostType(webDB.getDataFromProperties("LandedCostAdditem"));
				VerifySaveLine();
				VerifyToValidateLineWiseQueryAfterAddTheItemForLandedCostType(
						webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostAdditem"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostAdditem"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				System.out.println("100");
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				System.out.println("101");
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostAdditem"));
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}
//	verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//	LandedCostSupplier);
////verifyToGetDocumentFromLandedCost();
//verifyToAddNewItemInLandedCostType(webDB.getDataFromProperties("LandedCostAdditem"));
//VerifySaveLine();
//VerifyToValidateLineWiseQueryAfterAddTheItem(webDB.getDataFromProperties("LandedCostAdditem"));
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//VerifyToClickCreateJournalButton();
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBill(
//		webDB.getDataFromProperties("LandedCostAdditem"));
//VerifyToClickPostButton();

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
//	grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//	webDB.getDataFromProperties("LandedCostTaxAddition"));
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//	"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//	commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//verifyToAddNewItemInLandedCostType(webDB.getDataFromProperties("LandedCostTaxAddition"));
//VerifySaveLine();
//verifyCreateJournalAndPost();
//waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//	webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//	System.getProperty("AshokUsername"));
//flag = commonFunc.logoutUser();
//if (flag) {
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//		"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//verifyPurchaseBillApprovalStatus();
//flag = commonFunc.logoutUser();
//}
//assertTrue(flag);

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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
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
			String LandedCostSupplier = itemmultiple[i][70];
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
//				verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//						webDB.getDataFromProperties("LandedCostAddition"));
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(1000);
//				VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
//				VerifySaveLine();
//				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(1000);
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(1000);
//				VerifyToClickCreateJournalButton();
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(1000);
//				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(1000);
//				VerifyToClickPostButton();
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
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostAddition"));
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostAddition"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostAddition"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				System.out.println("100");
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostAddition"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				System.out.println("101");
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostAddition"));
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
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
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
				webDB.getDataFromProperties("LandedCostDeduction"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
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
			String LandedCostSupplier = itemmultiple[i][70];
			if (!LandedCostItem.equals("")) {
				VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("InvoicePfx"));
				storePurchaseVoucherNumber();
				ValidateHeaderDetails();
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
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostDeduction"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostDeduction"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				System.out.println("100");
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostDeduction"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				System.out.println("101");
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostDeduction"));
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus();
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);

	}

//	grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost(
//	webDB.getDataFromProperties("LandedCostValueWithDeduction"));
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//	"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//VerifyToCreateBillBookingDocumentFromPendingLcDocument(VariableHandler.getGrnDocNumber(),
//	commonFunc.getDataFromTestDataSheet("InvoicePfx"));
//VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostValueWithDeduction"));
//VerifySaveLine();
//verifyCreateJournalAndPost();
//waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//	webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//	System.getProperty("AshokUsername"));
//flag = commonFunc.logoutUser();
//if (flag) {
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//		"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//verifyPurchaseBillApprovalStatus();
//flag = commonFunc.logoutUser();
//}
//assertTrue(flag);
//	ValidateHeaderDetails();
////verifyToGetDocumentFromLandedCost();
//ValidateLineDetailsForLandedCost(LandedCostSupplier, i - 21);
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//verifyTowritePriceDeductionToWriteInExcelForPurchasebillDocument(
//		webDB.getDataFromProperties("LandedCostDeduction"));
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//VerifytoPutPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
//VerifySaveLine();
//VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//VerifyToClickCreateJournalButton();
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("LandedCostDeduction"));
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(1000);
//VerifyToClickPostButton();
//	verifyToCreateNewPurchaseBillDocument(commonFunc.getDataFromTestDataSheet("BillNo"),
//	LandedCostSupplier);
//	verifyToGetDocumentFromLandedCost();
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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("LandedCostAdditem"));
		for (int i = 23; i < itemmultiple.length; i++) {
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
//				ValidateHeaderDetails();
////				verifyToGetDocumentFromLandedCost();
//				verifyToAddNewItemInLandedCostType(webDB.getDataFromProperties("LandedCostAdditem"));
//				VerifySaveLine();
//				VerifyToValidateLineWiseQueryAfterAddTheItem(webDB.getDataFromProperties("LandedCostAdditem"));
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(1000);
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(1000);
//				VerifyToClickCreateJournalButton();
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(1000);
//				VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBill(
//						webDB.getDataFromProperties("LandedCostAdditem"));
//				VerifyToClickPostButton();
				verifyAddTheItemBasedOnTypeForLandedCostType(webDB.getDataFromProperties("LandedCostAdditem"));
				VerifySaveLine();
				VerifyToValidateLineWiseQueryAfterAddTheItemForLandedCostType(
						webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				VerifyToClickCreateJournalButton();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
						webDB.getDataFromProperties("LandedCostAdditem"),
						commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
						webDB.getDataFromProperties("LandedCostAdditem"),
						commonFunc.getDataFromTestDataSheet("JJ_AUTOMOBILES"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				System.out.println("100");
				VerifyToValidateRoundOffAmountForDirectStockTransferType(
						webDB.getDataFromProperties("LandedCostAdditem"));
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				System.out.println("101");
				VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
						webDB.getDataFromProperties("LandedCostAdditem"));
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
			commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
					"PremUsername");
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitem()
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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(webDB.getDataFromProperties("StocktransferLC"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(3000);
//		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
//				webDB.getDataFromProperties("StocktransferLC"));
////		verifyCreateJournalAndPost();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferLC"),
				commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED_WITH_ROUNDOFF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
//				webDB.getDataFromProperties("StocktransferLC"),
//				commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"));
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferLC"), commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLC"));
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
//		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "DBDETAILS.xlsx";
//		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
//		for (int i = 23; i < PBMultipleline.length; i++) {
//			String TotalAmount = PBMultipleline[i][2];
//			String PurchaseBillHeaderAmount = PBMultipleline[i][45];
//			if (!TotalAmount.equals("")) {
//				verifyAssertionBothValues(
//						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
//								commonFunc.getDataFromTestDataSheet("PurchaseBillHeaderTotalAmount")),
//						PurchaseBillHeaderAmount);
//				logs.logging("INFO",
//						"After Deviation Purchase Bill Header Amount is Matched" + VariableHandler.getPbNumber());
//			}
//		}
//		public void VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(String SheetName) {

		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";

		String[][] PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);

		int lastDataRow = -1;

		// Scan from row 22 to end
		for (int i = 22; i < PBMultipleline.length; i++) {

			String TotalAmount = PBMultipleline[i][2];

			if (TotalAmount != null && !TotalAmount.trim().isEmpty()) {
				lastDataRow = i; // keep updating → this ensures last filled row is stored
			}
		}

		// Use the last row with data
		if (lastDataRow != -1) {

			String TotalAmount = PBMultipleline[lastDataRow][2];
			String PurchaseBillHeaderAmount = PBMultipleline[lastDataRow][45];

			verifyAssertionBothValues(
					dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
							commonFunc.getDataFromTestDataSheet("PurchaseBillHeaderTotalAmount")),
					PurchaseBillHeaderAmount);

			logs.logging("INFO",
					"Last updated row used: " + lastDataRow + " | PB Number: " + VariableHandler.getPbNumber());
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpenseAccountForStockitem()
			throws Exception {
////		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
////		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
////				"Sathi Ganesh");
////		commonFunc.navigateToFinancialPurchaseBill();
////		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
////				commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("DirectPoStockTransferUD"));
////		verifyAddExpenseTypeForStockTransfer(webDB.getDataFromProperties("DirectPoStockTransferUD"));
////		VerifySaveLine();
////		verifyCreateJournalAndPost();
////		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
////		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
////				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
////				System.getProperty("AshokUsername"));
////		flag = commonFunc.logoutUser();
////		assertTrue(flag);
////		try {
////			grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
////					webDB.getDataFromProperties("StockTransferGenLocalItemandExp"));
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
////				"PremUsername");
////		commonFunc.navigateToFinancialPurchaseBill();
////		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
////				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//////		 VariableHandler.getGrnDocNumber()
//////		VerifyToClickReferenceFieldAndClickRcmFlag();
//////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		verifyCreateJournalAndPost();
////		waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
////		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
////				webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
////				System.getProperty("AshokUsername"));
////		flag = commonFunc.logoutUser();
////		assertTrue(flag);
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//		commonFunc.hardWait(1000);
////		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
////		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		verifyCreateJournalAndPost();
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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(webDB.getDataFromProperties("StocktransferLC"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferLC"),
				commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED_WITH_ROUNDOFF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferLC"), commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLC"));
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
//	verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
//	webDB.getDataFromProperties("StocktransferLC"),
//	commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"));

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
	private void VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		int count = 0;
		int maxRows = 1;
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
					count++;
					if (count == maxRows) {
						break;
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithOutDeviationStockitem()
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
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//		commonFunc.hardWait(1000);
////		ValidateHeaderDetails();
////		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
////		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		verifyCreateJournalAndPost();
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
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkRefreshCellsDisappear();
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(webDB.getDataFromProperties("StocktransferLC"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferLC"),
				commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED_WITH_ROUNDOFF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferLC"), commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLC"));
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
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//			webDB.getDataFromProperties("StocktransferLC"));

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */
	public void verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpenseAccountForStockitem()
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
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//		commonFunc.hardWait(1000);
////		ValidateHeaderDetails();
////		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
////		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddLocalEX"));
////		verifyCreateJournalAndPost();
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
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkRefreshCellsDisappear();
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(webDB.getDataFromProperties("StocktransferLC"));
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferLC"),
				commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED_WITH_ROUNDOFF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferLC"), commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLC"));
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeItemAutoCheck()
			throws Exception {
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		assertTrue(flag);

	}
//	commonFunc.checkRefreshCellsDisappear();
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForStockTransferType(
//			webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//	VerifyToClickPostButton();
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//	webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
//commonFunc.checkRefreshCellsDisappear();
//commonFunc.hardWait(2000);
//	VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//	grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//	webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//	"Sathi Ganesh");
//commonFunc.navigateToFinancialPurchaseBill();
//VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//	commonFunc.getDataFromTestDataSheet("StocktransferLocalPurchaseBillPrefix"));
//VerifyToClickReferenceFieldAndClickIgstTax();
//VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
//verifyCreateJournalAndPost();
//waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//	webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//	System.getProperty("AshokUsername"));
//flag = commonFunc.logoutUser();
//assertTrue(flag);
//VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));

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
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
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

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */

	public void writeDataAndForceExcelRecalcForTaxPCt(String filePath, String sheetName, int rowNum, int colNum,
			String value) {
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithDeviationAndExpenseAccountStockitemserviceItemAndChargeItem()
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
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
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
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferLC"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
//		System.out.println("Test");
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationStockitemserviceItemAndChargeItem()
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
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
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
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferLC"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(2000);
////		System.out.println("AftrerPrice");
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
//		System.out.println("Test");
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationAndExpenseAccountStockitemserviceItemAndChargeItem()
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
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
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
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLC"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferLC"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLC"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(2000);
////		System.out.println("AftrerPrice");
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
//		System.out.println("Test");
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXToIGST"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferLC"));
//		assertTrue(flag);
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"),
				commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmtaxAgainstStockTransferWithDeviationStockItemserviceItemAndChargeItem()
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
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				commonFunc.getDataFromTestDataSheet("LOCAL_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
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
	public void verifyToCreateRegisteredSupplierWithInterStateGstAgainstStockTransferWithDeviationForStockitem()
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
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferInterstate"));
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferInterstate"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
////		verifyCreateJournalAndPost();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"),
				commonFunc.getDataFromTestDataSheet("BENNY_ASSOCIATES"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
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
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void verifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationAndExpensesAccountStockitemServiceitemAndChargeItem()
			throws Exception {
//		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
//				webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

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
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferInterstate"));
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferInterstate"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
////		verifyCreateJournalAndPost();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"),
				commonFunc.getDataFromTestDataSheet("BENNY_ASSOCIATES"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
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
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));

		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void verifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
//		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
//				webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferInterstate"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

//	grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
//			webDB.getDataFromProperties("StocktransferInterstate"));
//	commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//			"PremUsername");
//	commonFunc.navigateToFinancialPurchaseBill();
//	VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//			commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//	commonFunc.hardWait(1000);
//	ValidateHeaderDetails();
//	VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferInterstate"));
//	commonFunc.checkRefreshCellsDisappear();
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StocktransferInterstate"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferInterstate"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferInterstate"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
////	VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	VerifyToClickPostButton();
//	waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//	commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//			webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//			System.getProperty("HariUsername"));
//	flag = commonFunc.logoutUser();
//	assertTrue(flag);

//	try {
//		grnPageFunc
//				.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspectionInterStateSupplier(
//						webDB.getDataFromProperties("StockTransferGenInterItemType"));
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//			"Sathi Ganesh");
//	commonFunc.navigateToFinancialPurchaseBill();
//	VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//			commonFunc.getDataFromTestDataSheet("InvoicePfxForStockTransfer"));
//	// VariableHandler.getGrnDocNumber()
////	VerifyToClickReferenceFieldAndClickRcmFlag();
//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StockTransferGenInterItemType"));
//	verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenInterItemType"));
//	verifyCreateJournalAndPost();
//	waitingForApproval(commonFunc.getDataFromTestDataSheet("AshokValue"));
//	commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//			webDB.getDataFromProperties("AshokEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//			System.getProperty("AshokUsername"));
//	flag = commonFunc.logoutUser();
//	assertTrue(flag);
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//			"PremUsername");
//	commonFunc.navigateToFinancialPurchaseBill();
//	VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//			commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//	// VariableHandler.getGrnDocNumber()
////	VerifyToClickReferenceFieldAndClickRcmFlag();
//	commonFunc.hardWait(1000);
//	ValidateHeaderDetails();
//	VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferInterstate"));
//	commonFunc.checkRefreshCellsDisappear();
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StocktransferInterstate"));
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferInterstate"));
//	commonFunc.checkRefreshCellsDisappear();
//	// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferInterstate"));
//	verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
////	verifyCreateJournalAndPost();
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToClickPostButton();
//	waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//	commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//			webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//			System.getProperty("HariUsername"));
//	flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_REGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"),
				commonFunc.getDataFromTestDataSheet("BENNY_ASSOCIATES"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEX"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
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
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));

		assertTrue(flag);

	}
//	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//	VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddIterstateEX"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(webDB.getDataFromProperties("StockTransferAddIterstateEX"));

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithDeviationStockitemserviceItemAndChargeItem()
			throws Exception {
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		// VariableHandler.getGrnDocNumber()
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
////		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//////		verifyCreateJournalAndPost();
//////		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
////				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifyToClickPostButton();
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
////		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		StockTransferAddIterstateEXWRCM
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				commonFunc.getDataFromTestDataSheet("BENNY_ASSOCIATES"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemserviceItemAndChargeItem()
			throws Exception {
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferISForRCM"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifyToWriteExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		assertTrue(flag);

//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//			"PremUsername");
//	commonFunc.navigateToFinancialPurchaseBill();
//	VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//			commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//	// VariableHandler.getGrnDocNumber()
//	commonFunc.hardWait(1000);
//	ValidateHeaderDetails();
//	VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
////	VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferISForRCM"));
//	VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//////	verifyCreateJournalAndPost();
//////	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
////	VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
////			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
////	commonFunc.checkRefreshCellsDisappear();
////	commonFunc.hardWait(1000);
////	VerifyToClickPostButton();
////	commonFunc.checkRefreshCellsDisappear();
////	commonFunc.hardWait(1000);
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StocktransferISForRCM"));
//	VerifyToClickPostButton();
//	waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//	commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//			webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//			System.getProperty("HariUsername"));
//	flag = commonFunc.logoutUser();
////	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	StockTransferAddIterstateEXWRCM
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				commonFunc.getDataFromTestDataSheet("BENNY_ASSOCIATES"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		assertTrue(flag);
//

//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferISForRCM"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifyToWriteExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		assertTrue(flag);

//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//			"PremUsername");
//	commonFunc.navigateToFinancialPurchaseBill();
//	VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//			commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//	// VariableHandler.getGrnDocNumber()
//	commonFunc.hardWait(1000);
//	ValidateHeaderDetails();
//	VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
////	VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferISForRCM"));
//	VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//////	verifyCreateJournalAndPost();
//////	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
////	VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
////			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
////	commonFunc.checkRefreshCellsDisappear();
////	commonFunc.hardWait(1000);
////	VerifyToClickPostButton();
////	commonFunc.checkRefreshCellsDisappear();
////	commonFunc.hardWait(1000);
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StocktransferISForRCM"));
//	VerifyToClickPostButton();
//	waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//	commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//			webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//			System.getProperty("HariUsername"));
//	flag = commonFunc.logoutUser();
////	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	StockTransferAddIterstateEXWRCM
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				commonFunc.getDataFromTestDataSheet("BENNY_ASSOCIATES"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockitemServiceItemAndChargeItem()
			throws Exception {
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		assertTrue(flag);

//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		assertTrue(flag);
//

//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferISForRCM"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifyToWriteExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferISForRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//		assertTrue(flag);

//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForLocalPurchaseBill(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//			"PremUsername");
//	commonFunc.navigateToFinancialPurchaseBill();
//	VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//			commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
//	// VariableHandler.getGrnDocNumber()
//	commonFunc.hardWait(1000);
//	ValidateHeaderDetails();
//	VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
////	VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferISForRCM"));
//	VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferISForRCM"),
//			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//////	verifyCreateJournalAndPost();
//////	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
////	VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
////			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
////	commonFunc.checkRefreshCellsDisappear();
////	commonFunc.hardWait(1000);
////	VerifyToClickPostButton();
////	commonFunc.checkRefreshCellsDisappear();
////	commonFunc.hardWait(1000);
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferISForRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StocktransferISForRCM"));
//	VerifyToClickPostButton();
//	waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//	commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//			webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//			System.getProperty("HariUsername"));
//	flag = commonFunc.logoutUser();
////	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferISForRCM"));
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	StockTransferAddIterstateEXWRCM
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("InterStatePurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//	VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
//	VerifyToClickCreateJournalButton();
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	commonFunc.checkRefreshCellsDisappear();
//	commonFunc.hardWait(1000);
//	VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//			webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//	VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"),
				commonFunc.getDataFromTestDataSheet("BENNY_ASSOCIATES"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddIterstateEXWRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//	VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
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
	public void verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitemServiceItemAndChargeItem()
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
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();

//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//
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
//
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
////				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferUR"),
				commonFunc.getDataFromTestDataSheet("LOCAL_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferUR"), commonFunc.getDataFromTestDataSheet("PKPolyMers"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferUR"));
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithDeviationandExpensesAccountStockItemServiceItemAndchargeItem()
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
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferLCRCM"),
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				commonFunc.getDataFromTestDataSheet("LOCAL_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationStockitemServiceItemAndChargeItem()
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
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferLCRCM"),
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				commonFunc.getDataFromTestDataSheet("LOCAL_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
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
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//				webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StocktransferLCRCM"),
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferLCRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLCRCM"));
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		grnPageFunc.PoToGrnStockTransferUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				commonFunc.getDataFromTestDataSheet("LOCAL_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"),
				commonFunc.getDataFromTestDataSheet("LINUX_PVT_LTD_SF"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StocktransferLCRCM"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		assertTrue(flag);

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToSelectGstSupply(String GSTSupply) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBGstSupply(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBGstSupply(), ElementType.Xpath);
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
	public void verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
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
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();

//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//
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
//
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
////				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferUR"),
				commonFunc.getDataFromTestDataSheet("LOCAL_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferUR"), commonFunc.getDataFromTestDataSheet("PKPolyMers"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
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
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//
////		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
//////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
////		VerifyToClickCreateJournalButton();
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(3000);
////		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
////				webDB.getDataFromProperties("StocktransferUR"));
//////		verifyCreateJournalAndPost();
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifyToClickPostButton();
//
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

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
	public void verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
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
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
////		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
//////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
////		VerifyToClickCreateJournalButton();
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(3000);
////		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
////				webDB.getDataFromProperties("StocktransferUR"));
//////		verifyCreateJournalAndPost();
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifyToClickPostButton();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
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
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();

//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//
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
//
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
////				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferUR"),
				commonFunc.getDataFromTestDataSheet("LOCAL_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferUR"), commonFunc.getDataFromTestDataSheet("PKPolyMers"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferUR"));
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
	public void verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
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
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

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
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		// VariableHandler.getGrnDocNumber()
////		VerifyToClickReferenceFieldAndClickRcmFlag();
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
////		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		// VerifyToValidateAfterPriceDeviation(webDB.getDataFromProperties("StocktransferLC"));
//////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferLC"));
//////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferGenLocalItemType"));
////		VerifyToClickCreateJournalButton();
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(3000);
////		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
////				webDB.getDataFromProperties("StocktransferUR"));
//////		verifyCreateJournalAndPost();
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifyToClickPostButton();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
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
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		// VariableHandler.getGrnDocNumber()
//		VerifyToClickReferenceFieldAndClickRcmFlag();

//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//
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
//
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
////		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
////				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToClickReferenceFieldAndClickRcmFlag(webDB.getDataFromProperties("StocktransferLC"));
//		VerifyToWriteExcelValue(webDB.getDataFromProperties("StockTransferAddLocalRCM"),
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalRCM"));
//		VerifyToClickPostButton();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferUR"),
				commonFunc.getDataFromTestDataSheet("LOCAL_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferUR"), commonFunc.getDataFromTestDataSheet("PKPolyMers"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferUR"));
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
	public void verifyToCreateUnregisteredSupplierWithLocalGSTAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeitem()
			throws Exception {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferURINTR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		System.out.println("Test");
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				commonFunc.getDataFromTestDataSheet("PKPolyMers"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
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
	public void verifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferURINTR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		System.out.println("Test");
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		assertTrue(flag);

//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferURINTR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		System.out.println("Test");
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				commonFunc.getDataFromTestDataSheet("PKPolyMers"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
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
	public void verifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		assertTrue(flag);

//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferURINTR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		System.out.println("Test");
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				commonFunc.getDataFromTestDataSheet("PKPolyMers"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
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
	public void verifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		assertTrue(flag);

//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		assertTrue(flag);

//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
//						webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StocktransferURINTR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferURINTR"));
//		System.out.println("Test");
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StocktransferURINTR"));
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplier(
						webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("LocalPurchaseBillURST"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
				commonFunc.getDataFromTestDataSheet("PKPolyMers"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
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
	public void verifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationStockItemserviceItemAndChargeItem()
			throws Exception {
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
						webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"),
				commonFunc.getDataFromTestDataSheet("KV_CAREERS"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
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
	public void verifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
//						webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
						webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"),
				commonFunc.getDataFromTestDataSheet("KV_CAREERS"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
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
	public void verifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationtockItemserviceItemAndChargeItem()
			throws Exception {
//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
//						webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
//						webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
						webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"),
				commonFunc.getDataFromTestDataSheet("KV_CAREERS"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
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
	public void verifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {

//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
//						webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
//						webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
						webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"),
				commonFunc.getDataFromTestDataSheet("KV_CAREERS"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifytoPutPriceDeviationForStocktransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
		assertTrue(flag);

//		grnPageFunc
//				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForInterstateSupplier(
//						webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBInterURPK"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);

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
	public void verifyToDomesticRegisteredSupplierWithLocalFreightGst() throws Exception {
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplierWithLocalFright(
						webDB.getDataFromProperties("StocktransferLocalWF"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBlocalWF"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLocalWF"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferIntrUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
////				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.hardWait(1000);
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferLocalWF"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StocktransferLocalWF"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBlocalWF"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferLocalWF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StocktransferLocalWF"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferLocalWF"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferLocalWF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferLocalWF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferLocalWF"),
				commonFunc.getDataFromTestDataSheet("LOCAL_FRIGHT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferLocalWF"), commonFunc.getDataFromTestDataSheet("KV_CAREERS"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferLocalWF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLocalWF"));
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
	public void verifyToDomesticRegisteredSupplierWithSezUnitGst() throws Exception {
		grnPageFunc
				.PoToGrnStockTransferRateContractUnregisterSupplierSameStockAndSupplierUomWithAndWithoutInspectionForLocalSupplierWithSezUnit(
						webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBlocalSezWF"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("StocktransferIntrUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		verifyTowriteGrnPriceToWriteInExcelForPurchasebillDocument(
////				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		VerifyToValidateAfterPriceDeviationForStockTransferType(webDB.getDataFromProperties("StocktransferIntrUR"));
////		commonFunc.checkRefreshCellsDisappear();
////		commonFunc.hardWait(1000);
////		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.hardWait(1000);
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		assertTrue(flag);
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBlocalSezWF"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferLocalWFSEZ"),
				commonFunc.getDataFromTestDataSheet("LOCAL_FRIGHT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferLocalWFSEZ"),
				commonFunc.getDataFromTestDataSheet("KV_CAREERS"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLocalWFSEZ"));
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
	public void verifyToCreateDomesticUnregisteredSupplierWithLocalFreightGST() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregister(
				webDB.getDataFromProperties("StocktransferURLF"));
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBlocalURWF"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURLF"));
//		commonFunc.hardWait(1000);
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferURLF"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StocktransferURLF"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBlocalURWF"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferURLF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(webDB.getDataFromProperties("StocktransferURLF"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferURLF"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferURLF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferURLF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferURLF"), commonFunc.getDataFromTestDataSheet("LOCAL_FRIGHT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferURLF"), commonFunc.getDataFromTestDataSheet("KV_CAREERS"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferURLF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferURLF"));
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
	public void verifyToCreateDomesticUnRegisteredSupplierWithSezUnitGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterSezUnit(
				webDB.getDataFromProperties("StocktransferURSEZ"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
//		commonFunc.navigateToFinancialPurchaseBill();
//		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
//				commonFunc.getDataFromTestDataSheet("PBlocalURWF"));
//		commonFunc.hardWait(1000);
//		ValidateHeaderDetails();
//		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferURSEZ"));
//		commonFunc.hardWait(1000);
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToClickCreateJournalButton();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferURSEZ"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
//				webDB.getDataFromProperties("StocktransferURSEZ"));
//		VerifyToClickPostButton();
//		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
//		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
//				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
//				System.getProperty("HariUsername"));
//		flag = commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
//				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBlocalURWF"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevelForStocktransferType(
				webDB.getDataFromProperties("StocktransferURSEZ"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
//		VerifyToWriteExcelValueForIgst(webDB.getDataFromProperties("StockTransferAddLocalEXURITR"),
//				webDB.getDataFromProperties("StockTransferAddLocalEXURITR"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		VerifytoPutPriceDeviationForStocktransferTypeForCommonStocktransfer(
//				webDB.getDataFromProperties("StockTransferAddInterEXUR"));
//		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("AftrerPrice");
		VerifyToValidateAfterPriceDeviationForStockTransferTypeCommon(
				webDB.getDataFromProperties("StocktransferURSEZ"));
		System.out.println("Test");
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("StocktransferURSEZ"));
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateAfterAddTheItemToHeaderAmountForPurchaseBillStocktransferType(
				webDB.getDataFromProperties("StocktransferURSEZ"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StocktransferURSEZ"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StocktransferURSEZ"), commonFunc.getDataFromTestDataSheet("LOCAL_FRIGHT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
				webDB.getDataFromProperties("StocktransferURSEZ"), commonFunc.getDataFromTestDataSheet("KV_CAREERS"),
				commonFunc.getDataFromTestDataSheet("RoundOffValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		System.out.println("100");
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StocktransferURSEZ"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		System.out.println("101");
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferURSEZ"));
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
	public void verifyToCreateDomesticUnregisteredSupplierWithUnionTerritoryGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferLocalURUT"), commonFunc.getDataFromTestDataSheet("L67PK"),
				commonFunc.getDataFromTestDataSheet("STLURUTPK"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STUTSIPK"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBUTURWF"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLocalURUT"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferLocalURUT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLocalURUT"));
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
	public void verifyToCreateDomesticCompositionSupplierWithLocalFreightGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferLocalComFright"),
				commonFunc.getDataFromTestDataSheet("L71PK"), commonFunc.getDataFromTestDataSheet("STLURCOMPK"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STDC4"));
//		STLURCOMPK
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBlocalDCWF"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLocalComFright"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferLocalComFright"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLocalComFright"));
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
	public void verifyToDomesticCompositionSupplierWithLocalGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferLocalComSup"), commonFunc.getDataFromTestDataSheet("L69PK"),
				commonFunc.getDataFromTestDataSheet("STLCOMSUP"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STDC2"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBLCOMSUP"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLocalComSup"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferLocalComSup"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLocalComSup"));
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
	public void verifyToDomesticCompositionSupplierWithInterStateGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferIntrerComSup"), commonFunc.getDataFromTestDataSheet("L73PK"),
				commonFunc.getDataFromTestDataSheet("STLCOMINTRSUP"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STDC6"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBLCOMINTRSUP"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferIntrerComSup"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferIntrerComSup"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferIntrerComSup"));
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
	public void verifyToDomesticCompositionSupplierWithUnionTerritoryGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferUTComSup"), commonFunc.getDataFromTestDataSheet("L75PK"),
				commonFunc.getDataFromTestDataSheet("STLCOMUTSUP"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STDC8"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBLCOMUTSUP"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferUTComSup"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferUTComSup"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferUTComSup"));
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
	public void verifyToDomesticCasualPersonSupplierWithLocalGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferLocalCPSup"), commonFunc.getDataFromTestDataSheet("L77PK"),
				commonFunc.getDataFromTestDataSheet("STLCPLOCSUP"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STDP2"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBLCOMUTSUP"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLocalCPSup"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferLocalCPSup"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLocalCPSup"));
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
	public void verifyToCreateDomesticCasualPersonSupplierWithLocalFreightGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferLocalFrightCP"), commonFunc.getDataFromTestDataSheet("L79PK"),
				commonFunc.getDataFromTestDataSheet("STLCPLOCFSUP"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STDP4"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBLCOMLFSUP"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferLocalFrightCP"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferLocalFrightCP"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferLocalFrightCP"));
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
	public void verifyToCreateDomesticCasualPersonSupplierWithUnionTerritoryGST() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferCPUT"), commonFunc.getDataFromTestDataSheet("L83PK"),
				commonFunc.getDataFromTestDataSheet("STLCPUTSUP"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STDP8"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBLCPUTSUP"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferCPUT"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferCPUT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferCPUT"));
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
	public void verifyToCreateDomesticNonResidentSupplierWithLocalFreightGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferNRLF"), commonFunc.getDataFromTestDataSheet("L87PK"),
				commonFunc.getDataFromTestDataSheet("STSINR4"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STNR4"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBLNRLFSUP"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferNRLF"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferNRLF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferNRLF"));
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
	public void verifyToCreateDomesticNonResidentSupplierWithUnionTerritoryGst() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSupplierForRegisterAndUnregisterForCommon(
				webDB.getDataFromProperties("StocktransferNRUT"), commonFunc.getDataFromTestDataSheet("L91PK"),
				commonFunc.getDataFromTestDataSheet("STUTPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"),
				commonFunc.getDataFromTestDataSheet("STNR8"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		VerifyToCreateBillBookingDocumentFromPendingStocktransferDocument(VariableHandler.getGrnDocNumber(),
				commonFunc.getDataFromTestDataSheet("PBLNRUTSUP"));
		commonFunc.hardWait(1000);
		ValidateHeaderDetails();
		VerifyToValidateBeforePriceDeviationForLineLevel(webDB.getDataFromProperties("StocktransferNRUT"));
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForStockTransferType(webDB.getDataFromProperties("StocktransferNRUT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StocktransferNRUT"));
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
	 * @throws IOException
	 */
	public void verifyToCreateRegisteredSupplierWithLocalGstStockTransferDirectStockItemServiceItemAndChargeItem()
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransfer(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeLocalSupplier(
//				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"),
				commonFunc.getDataFromTestDataSheet("LOCAL_REGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"),
				commonFunc.getDataFromTestDataSheet("LINUXPVTLTD"));
//		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplier(
//				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * @author Premkumar.K
	 * @param BillNo
	 * @param PartyID
	 */
	public void verifyToCreateNewPurchaseBillDocumentForDirectStockTransfer(String BillNo, String PartyID,
			String Type) {
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
					commonFunc.getDataFromTestDataSheet("STTypePK"));
			verifyCLickPurchaseBillsFrame();
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.LocID,
					commonFunc.getDataFromTestDataSheet("StockTransferLocalLoaction"));
			verifyCLickPurchaseBillsFrame();
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBRefType, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByValue(PurchaseBillsLocatorsGetDocument.PBRefType, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Is Entered:" + Type);
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PartyID, PartyID);
				verifyCLickPurchaseBillsFrame();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Document Voucher Type Shown");
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.BillNumInput, DCNO,
								ElementType.Xpath);
						logs.logging("INFO", "Bill Number Inserted SuccessFully");
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PurchaseBillTickIcon,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PurchaseBillTickIcon,
									ElementType.Xpath);
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
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void verifyAddTheItemBasedOnTypeForDirectStockTransfer(String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
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
								VerifyToCreateExpenseTypeForDirectStockTransfer(GLAccount, Cost, HSN, GSTSupply,
										SeqNumber, TaxAmountCGST, TaxAmountSGST, TaxAmountIGST, PBLineAmount,
										PBDiscAmount);
							} else if (Type.equals(commonFunc.getDataFromTestDataSheet("ItemType"))) {
								VerifyToCreatetheItemTypeDocumentForDirectStockTransfer(ITEM_ID, Cost, Qty, HSN,
										GLAccount, TaxAmountCGST, TaxAmountSGST, TaxAmountIGST, GSTSupply, PBLineAmount,
										PBDiscAmount);
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
	public void VerifyToCreateExpenseTypeForDirectStockTransfer(String GLAccount, String Cost, String HSN,
			String GSTSupply, String SeqNumber, String TaxAmountCGST, String TaxAmountSGST, String TaxAmountIGST,
			String PBLineAmount, String PBDiscAmount) {

//		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GlAccDescField, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.GlAccDescField, ElementType.Xpath);
//			logs.logging("INFO", "Field Is Clicked");
//			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GlAccLov, ElementType.Xpath);
//			if (flag) {
//				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.GlAccLov, GLAccount);
//				logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
		verifyClickingGLAccount(GLAccount);
		commonFunc.checkRefreshCellsDisappear();
//		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField, ElementType.Xpath);
//			webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
//			webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost, ElementType.Xpath);
//			logs.logging("INFO", "Entered Cost Is:" + Cost);
		verifyClickingPBCostField(Cost);
//			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeHsnField, ElementType.Xpath);
//			if (flag) {
//				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeHsnField, ElementType.Xpath);
//				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferHsnLov, ElementType.Xpath);
//				if (flag) {
//					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.StockTransferHsnLov, HSN);
//					logs.logging("INFO", "Entered Cost Is:" + HSN);
		VerifyToChangeTheHsnCode(HSN);
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
		verifyExpenseTypeDocumentDBValidation(SeqNumber, TaxAmountCGST, TaxAmountSGST, TaxAmountIGST, PBLineAmount,
				PBDiscAmount);

	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyClickingGLAccount(String GLAccount) {
//		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeQtyField, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBGLAccount(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBGLAccount(), ElementType.Xpath);
			logs.logging("INFO", "Field Is Clicked");
//			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GlAccLov, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.GlAccLov, GLAccount);
			logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Entered Unit Cost
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyClickingPBCostField(String Cost) {
//		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath)) {
//			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath);
//		}
		commonFunc.verifyAddTableSpecs(PurchaseBillsLocatorsGetDocument.PBInvoiceUnitCost(),
				PurchaseBillsLocatorsGetDocument.PBInvoiceUnitCostInput(), Cost);
		logs.logging("INFO", "Entered GLAccount Is:" + Cost);
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyClickingAndEnteredItem(String ITEM_ID) {
		webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBItemTable(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBItemTable(), ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBItemLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PBItemLov, ITEM_ID);
				logs.logging("INFO", "Type Selected SuccessFully"+ITEM_ID);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PR General RFQ
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyClickingQty(String Qty) {
		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ExpandButton, ElementType.Xpath);
		}
		System.out.println("test Po");
		commonFunc.verifyAddTableSpecs(PurchaseBillsLocatorsGetDocument.PBInvoiceQty(),
				PurchaseBillsLocatorsGetDocument.PBInvoiceQtyInput(), Qty);
		logs.logging("INFO", "Entered GLAccount Is:" + Qty);
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void VerifyToCreatetheItemTypeDocumentForDirectStockTransfer(String ITEM_ID, String Cost, String Qty,
			String HSN, String GLAccount, String TaxAmountCGST, String TaxAmountSGST, String TaxAmountIGST,
			String GSTSupply, String PBLineAmount, String PBDiscAmount) {

		verifyClickingAndEnteredItem(ITEM_ID);

		verifyClickingGLAccount(GLAccount);
//		if (flag) {
//
//			commonFunc.checkRefreshCellsDisappear();
//			if (flag) {
//					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeQtyField, ElementType.Xpath);
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(1000);
//					webDB.clearText(PurchaseBillsLocatorsGetDocument.StockTransferQtyInput, ElementType.Xpath);
//					webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.StockTransferQtyInput, Qty,
//							ElementType.Xpath);
//				verifyClickingQty(Qty);
//				logs.logging("INFO", "Entered Qty Is:" + Qty);
//				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBCostField, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBCostField, ElementType.Xpath);
//					webDB.clearText(PurchaseBillsLocatorsGetDocument.PBCostInput, ElementType.Xpath);
//					webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.PBCostInput, Cost, ElementType.Xpath);
//					logs.logging("INFO", "Entered Cost Is:" + Cost);
					verifyClickingPBCostField(Cost);
//					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeHsnField,
//							ElementType.Xpath);
//					if (flag) {
//						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.StockTransferTypeHsnField,
//								ElementType.Xpath);
//						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.StockTransferHsnLov,
//								ElementType.Xpath);
//						if (flag) {
//							commonFunc.searchAndSelectValueInFilters(
//									PurchaseBillsLocatorsGetDocument.StockTransferHsnLov, HSN);
					VerifyToChangeTheHsnCode(HSN);
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
					VerifyToValidateStockTransferItemTypeValidate(ITEM_ID, TaxAmountCGST, TaxAmountSGST, TaxAmountIGST,
							PBLineAmount, PBDiscAmount);

				}
			
		
	

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalPartForStocktransaferType(String SheetName) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String GL_Account_Desc = PBMultipleline[i][79];
			String DebitAmount = PBMultipleline[i][80];
			String CreditAmount = PBMultipleline[i][81];
			if (!GL_Account_Desc.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PbJournalQueryPK(VariableHandler.getPbNumber(), GL_Account_Desc,
						commonFunc.getDataFromTestDataSheet("PBDebitAmount")), DebitAmount);
				logs.logging("INFO", "Debit Amount Matched With DB Values For This GL " + GL_Account_Desc);
				verifyAssertionBothValues(dbCommonFunc.PbJournalQueryPK(VariableHandler.getPbNumber(), GL_Account_Desc,
						commonFunc.getDataFromTestDataSheet("PBCreditAmount")), CreditAmount);
				logs.logging("INFO", "Credit Amount Matched With DB Values For This GL Account " + GL_Account_Desc);

			}
		}

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(String SheetName,
			String supplyType) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();

		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";

		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);

		for (int i = 22; i < PBMultipleline.length; i++) {

			String Seqnum = PBMultipleline[i][0];

			if (!Seqnum.equals("")) {
				List<String> value = new ArrayList<>();
				List<String> value1 = new ArrayList<>();
				// Always Line Amount
				value.add(PBMultipleline[i][18]); // Debit Line Amount
				value1.add(PBMultipleline[i][82]); // Credit Line Amount
				System.out.println(PBMultipleline[i][18]);
				System.out.println(PBMultipleline[i][82]);
				System.out.println(supplyType);
				switch (supplyType.toUpperCase()) {

				case "LOCAL_REGISTERED":

					value.add(PBMultipleline[i][21]); // CGSTDebit
					value.add(PBMultipleline[i][22]); // SGSTDebit

					value1.add(PBMultipleline[i][83]); // CGST Credit
					value1.add(PBMultipleline[i][84]); // SGST Credit
					break;

				case "INTERSTATE_REGISTERED":

					value.add(PBMultipleline[i][23]); // IGSTCredit
					value1.add(PBMultipleline[i][85]); // IGST Credit
					break;

				case "LOCAL_UNREGISTERED":

					value.add(PBMultipleline[i][21]);// CGSTDebit
					value.add(PBMultipleline[i][22]); // SGSTDebit
					value.add(PBMultipleline[i][88]); // Output CGST RCMDebit
					value.add(PBMultipleline[i][89]); // Output SGST RCMDebit

					value1.add(PBMultipleline[i][83]);// CGST Credit
					value1.add(PBMultipleline[i][84]);// SGST Credit
					value1.add(PBMultipleline[i][93]); // Output CGST RCM Credit
					value1.add(PBMultipleline[i][94]); // Output SGST RCM Credit

					break;

				case "INTERSTATE_UNREGISTERED":
					value.add(PBMultipleline[i][23]);// IgstAmountDebit
					value.add(PBMultipleline[i][90]); // Output IGST RCMDebit

					value1.add(PBMultipleline[i][85]);// Igst_AmountCredit
					value1.add(PBMultipleline[i][95]);// Output IGST RCM Credit

					break;

				case "LOCAL_FRIGHT":

					break;

				case "SEZ_UNIT":

					break;

//				case "LOCAL_FRIGHT_UNRRESGISTERED":
//					value.add(PBMultipleline[i][88]); // Output CGST RCMDebit
//
//					value1.add(PBMultipleline[i][88]); // Output CGST RCM Credit
//					break;
//				case "SEZ_UNIT_UNRGISTERED":
//					value.add(PBMultipleline[i][88]); // Output CGST RCMDebit
//
//					value1.add(PBMultipleline[i][88]); // Output CGST RCM Credit
//					break;

				case "LOCAL_FRIGHT_UNRRESGISTERED":
//					value.add(PBMultipleline[i][88]); // Output CGST RCMDebit
//
//					value1.add(PBMultipleline[i][88]); // Output CGST RCM Credit
					break;
				case "SEZ_UNIT_UNRGISTERED":
//					value.add(PBMultipleline[i][88]); // Output CGST RCMDebit
//
//					value1.add(PBMultipleline[i][88]); // Output CGST RCM Credit
					break;
				case "LOCAL_REGISTERED_WITH_TDS":
					value.add(PBMultipleline[i][21]); // CGSTDebit
					value.add(PBMultipleline[i][22]); // SGSTDebit
//					value.add(PBMultipleline[i][95]); // Supplier Account_Debit
//					value.add(PBMultipleline[i][97]); // TCS Account_Debit

					value1.add(PBMultipleline[i][83]); // CGST Credit
					value1.add(PBMultipleline[i][84]); // SGST Credit
//					value1.add(PBMultipleline[i][98]); // TCS Account_Credit
					break;
				case "LOCAL_REGISTERED_WITH_ROUNDOFF":
					value.add(PBMultipleline[i][21]); // CGSTDebit
					value.add(PBMultipleline[i][22]); // SGSTDebit

					value1.add(PBMultipleline[i][83]); // CGST Credit
					value1.add(PBMultipleline[i][84]); // SGST Credit

					break;
				case "INTERSTATE_REGISTERED_WITH_TDS":

					value.add(PBMultipleline[i][23]); // IGSTCredit
					value1.add(PBMultipleline[i][85]); // IGST Credit
					break;

				}

				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")), value);

				logs.logging("INFO", "Debit Amount Matched With DB Values For Voucher Line " + Seqnum);

				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")), value1);

				logs.logging("INFO", "Credit Amount Matched With DB Values For Voucher Line " + Seqnum);
			}
		}
	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
			String SheetName, String SupplierID) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String GL_Account_Desc = PBMultipleline[i][79];
			String DebitAmount = PBMultipleline[i][80];
			String CreditAmount = PBMultipleline[i][81];
			if (GL_Account_Desc != null && !GL_Account_Desc.isEmpty() && GL_Account_Desc.equals(SupplierID)) {
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
						DebitAmount);
				logs.logging("INFO", "Detail Part Debit Amount Matched With DB Values For This GL " + GL_Account_Desc);
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
						CreditAmount);
				logs.logging("INFO",
						"Detail Part Credit Amount Matched With DB Values For This GL Account " + GL_Account_Desc);

			}
		}

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
			String SheetName, String SupplierID, String RoundOffValue) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String GL_Account_Desc = PBMultipleline[i][79];
			String DebitAmount = PBMultipleline[i][80];
			String CreditAmount = PBMultipleline[i][81];
//			if (GL_Account_Desc != null && !GL_Account_Desc.isEmpty() && GL_Account_Desc.equals(SupplierID)
//					&& GL_Account_Desc.equals(RoundOffValue)) {
			if (GL_Account_Desc.equals(SupplierID) || GL_Account_Desc.equals(RoundOffValue)) {
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
						DebitAmount);
				logs.logging("INFO", "Detail Part Debit Amount Matched With DB Values For This GL " + GL_Account_Desc);
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
						CreditAmount);
				logs.logging("INFO",
						"Detail Part Credit Amount Matched With DB Values For This GL Account " + GL_Account_Desc);

			}
		}

	}

//	/**
//	 * This Methods To Create Item Type Document
//	 * 
//	 * @author PremKumar.K-RMITS
//	 */
//	public void verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommonWithRoundOff(
//			String SheetName, String SupplierID, String RoundOffValue) {
//		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "DBDETAILS.xlsx";
//		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
//		for (int i = 22; i < PBMultipleline.length; i++) {
//			String GL_Account_Desc = PBMultipleline[i][79];
//			String DebitAmount = PBMultipleline[i][80];
//			String CreditAmount = PBMultipleline[i][81];
//			if (GL_Account_Desc != null && !GL_Account_Desc.isEmpty() && GL_Account_Desc.equals(SupplierID)
//					&& GL_Account_Desc.equals(RoundOffValue)) {
//				verifyAssertionBothValues(
//						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
//								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
//						DebitAmount);
//				logs.logging("INFO", "Detail Part Debit Amount Matched With DB Values For This GL " + GL_Account_Desc);
//				verifyAssertionBothValues(
//						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
//								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
//						CreditAmount);
//				logs.logging("INFO",
//						"Detail Part Credit Amount Matched With DB Values For This GL Account " + GL_Account_Desc);
//
//			}
//		}
//
//	}

	/**
	 * @author Premkumar.K
	 */
	public void VerifyToValidateRoundOffAmountForDirectStockTransferType(String SheetName) {
		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			System.out.println("Excel Length is" + PBMultipleline.length);
			System.out.println("Coloumn Count Is" + ExcelUtils.getColCount());
			String ItemID = PBMultipleline[i][1];
			String PurchaseBillRoundOfAmount = PBMultipleline[i][46];
			if (ItemID == null || ItemID.trim().isEmpty()) {
				verifyAssertionBothValues(
						dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PurchaseBillRoundOffAmount")),
						PurchaseBillRoundOfAmount);
				logs.logging("INFO",
						"Expected RoundOff  Amount Is Matched With DataBase Values For This Purchase Bill Document "
								+ VariableHandler.getPbNumber());
				break;
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
	public void verifyAssertionBothValues(List<String> Actual, List<String> Expected) {
		logs.logging("INFO", "Actual DataBase Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		for (int i = 0; i < Expected.size(); i++) {
			flag = false;
			for (int j = 0; j < Actual.size(); j++) {
				flag = Actual.get(j).equals(Expected.get(i));
				if (flag == true) {
					break;
				}
				if ((Actual.size() - 1 == j) && flag == false) {
					Assert.fail();
				}

			}
		}

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
	 * @throws IOException
	 */
	public void verifyToCreateRegisteredSupplierWithLocalGstStockTransferDirectWithExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransfer(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferTypeLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCT"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailsPartForStocktransaferTypeLocalSupplier(String SheetName) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String Seqnum = PBMultipleline[i][0];
			String LineAmount = PBMultipleline[i][18];
			String CgstAmount = PBMultipleline[i][21];
			String SgstAmount = PBMultipleline[i][22];
			String Line_Amount = PBMultipleline[i][82];
			String Cgst_Amount = PBMultipleline[i][83];
			String Sgst_Amount = PBMultipleline[i][84];
			if (!Seqnum.equals("")) {
				List<String> value = new ArrayList<>();
				List<String> value1 = new ArrayList<>();
				value.add(LineAmount);
				value.add(CgstAmount);
				value.add(SgstAmount);
				value1.add(Line_Amount);
				value1.add(Cgst_Amount);
				value1.add(Sgst_Amount);
				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")), value);
				value.clear();
				System.out.println(Seqnum);
				logs.logging("INFO", "Debit Amount Matched With DB Values For This Voucher Line Number " + Seqnum);
				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")), value1);
				value1.clear();
				System.out.println(Seqnum);
				logs.logging("INFO", "Credit Amount Matched With DB Values For This Voucher Line Number " + Seqnum);
			}
		}

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplier(String SheetName) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 26; i < PBMultipleline.length; i++) {
			String GL_Account_Desc = PBMultipleline[i][79];
			String DebitAmount = PBMultipleline[i][80];
			String CreditAmount = PBMultipleline[i][81];
			if (!GL_Account_Desc.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
						DebitAmount);
				logs.logging("INFO", "Debit Amount Matched With DB Values For This GL " + GL_Account_Desc);
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
						CreditAmount);
				logs.logging("INFO", "Credit Amount Matched With DB Values For This GL Account " + GL_Account_Desc);

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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxStockTransferDirectStockItemServiceItemAndChargeItem()
			throws IOException {
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransfer(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForDirectEntry();
		commonFunc.checkRefreshCellsDisappear();
		VerifyToWriteExcelValueForIgstDirectStockTransfertype(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeForInterstateSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplier(String SheetName) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String Seqnum = PBMultipleline[i][0];
			String LineAmount = PBMultipleline[i][18];
			String IgstAmount = PBMultipleline[i][23];
			String Line_Amount = PBMultipleline[i][82];
			String Igst_Amount = PBMultipleline[i][85];
			if (!Seqnum.equals("")) {
				List<String> value = new ArrayList<>();
				List<String> value1 = new ArrayList<>();
				value.add(LineAmount);
				value.add(IgstAmount);
				value1.add(Line_Amount);
				value1.add(Igst_Amount);
				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")), value);
				value.clear();
				System.out.println(Seqnum);
				logs.logging("INFO", "Debit Amount Matched With DB Values For This Voucher Line Number " + Seqnum);
				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")), value1);
				value1.clear();
				System.out.println(Seqnum);
				logs.logging("INFO", "Credit Amount Matched With DB Values For This Voucher Line Number " + Seqnum);
			}
		}

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeForInterstateSupplier(
			String SheetName) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 25; i < PBMultipleline.length; i++) {
			String GL_Account_Desc = PBMultipleline[i][79];
			String DebitAmount = PBMultipleline[i][80];
			String CreditAmount = PBMultipleline[i][81];
			if (!GL_Account_Desc.equals("")) {
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
						DebitAmount);
				logs.logging("INFO", "Debit Amount Matched With DB Values For This GL " + GL_Account_Desc);
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
						CreditAmount);
				logs.logging("INFO", "Credit Amount Matched With DB Values For This GL Account " + GL_Account_Desc);

			}
		}

	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	private void ValidateHeaderDetailsForDirectEntry() {

		verifyAssertionBothValues(
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderLocation")),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalLoaction"));
		logs.logging("INFO", "Location Is Matched For This Purchase Bill Document");
		verifyAssertionBothValues(
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderUnit")),
				commonFunc.getDataFromTestDataSheet("U-6"));
		logs.logging("INFO", "Unit Is Matched For This Purchase Bill Document ");
		verifyAssertionBothValues(
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderSupplierID")),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplier"));
		logs.logging("INFO", "Supplier ID Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("SUPHD_PUR_TYPE")),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		logs.logging("INFO", "Supplier ID Is Matched For GRN and PB ");
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
	public void VerifyToWriteExcelValueForIgstDirectStockTransfertype(String SheetName) {

		VerifyToClickReferenceFieldAndClickIgstTax(SheetName);
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransfer(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForDirectEntry();
		commonFunc.checkRefreshCellsDisappear();
		VerifyToWriteExcelValueForIgstDirectStockTransfertype(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeForInterstateSupplier(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
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
	 * @throws IOException
	 */
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmtaxStockTransferDirectStockItemServiceItemAndChargeItem()
			throws IOException {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransfer(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForDirectEntry();
		commonFunc.checkRefreshCellsDisappear();
		VerifyToWriteExcelValueForDirectRcmSupplier(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferTypeLocalSupplierForRcmCategory(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"),
				commonFunc.getDataFromTestDataSheet("LINUXPVTLTD"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailsPartForStocktransaferTypeLocalSupplierForRcmCategory(String SheetName) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String Seqnum = PBMultipleline[i][0];
			String LineAmount = PBMultipleline[i][18];
			String CgstAmount = PBMultipleline[i][21];
			String SgstAmount = PBMultipleline[i][22];
			String OutputCgstRcm = PBMultipleline[i][88];
			String OutputSgstRcm = PBMultipleline[i][89];
			String Line_Amount = PBMultipleline[i][82];
			String Cgst_Amount = PBMultipleline[i][83];
			String Sgst_Amount = PBMultipleline[i][84];
			String Output_Cgst_Rcm_Amount = PBMultipleline[i][93];
			String Output_Sgst_Rcm_Amount = PBMultipleline[i][94];
			if (!Seqnum.equals("")) {
				List<String> value = new ArrayList<>();
				List<String> value1 = new ArrayList<>();
				value.add(LineAmount);
				value.add(CgstAmount);
				value.add(SgstAmount);
				value.add(OutputCgstRcm);
				value.add(OutputSgstRcm);
				value1.add(Line_Amount);
				value1.add(Cgst_Amount);
				value1.add(Sgst_Amount);
				value1.add(Output_Cgst_Rcm_Amount);
				value1.add(Output_Sgst_Rcm_Amount);
				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")), value);
				value.clear();
				System.out.println(Seqnum);
				logs.logging("INFO", "Debit Amount Matched With DB Values For This Voucher Line Number " + Seqnum);
				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")), value1);
				value1.clear();
				System.out.println(Seqnum);
				logs.logging("INFO", "Credit Amount Matched With DB Values For This Voucher Line Number " + Seqnum);
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
	public void VerifyToWriteExcelValueForDirectRcmSupplier(String SheetName) {
		VerifyToClickReferenceFieldAndClickRcmFlag(SheetName);
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransfer(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForDirectEntry();
		commonFunc.checkRefreshCellsDisappear();
		VerifyToWriteExcelValueForDirectRcmSupplier(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferTypeLocalSupplierForRcmCategory(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"),
				commonFunc.getDataFromTestDataSheet("LINUXPVTLTD"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
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
	 * @throws IOException
	 */
	public void verifyToCreateRegisteredSupplierWithInterstateGstStockTransferDirectStockItemserviceItemAndChargeItem()
			throws IOException {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L0002"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		ValidateHeaderDetailsForDirectEntry();
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToWriteExcelValueForDirectRcmSupplier(webDB.getDataFromProperties("StockTransferAddDCTIS"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferIGSTEXP"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferIGSTEXP"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplier(
				webDB.getDataFromProperties("StockTransferIGSTEXP"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferIGSTEXP"),
				commonFunc.getDataFromTestDataSheet("BENNYASSOCIATES"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StockTransferIGSTEXP"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferIGSTEXP"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * @author Premkumar.K
	 * @param BillNo
	 * @param PartyID
	 */
	public void verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(String SubVouType,
			String LocID, String BillNo, String PartyID, String Type) {
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
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.SubVouType, SubVouType);
			verifyCLickPurchaseBillsFrame();
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.LocID, LocID);
			verifyCLickPurchaseBillsFrame();
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBRefType, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByValue(PurchaseBillsLocatorsGetDocument.PBRefType, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Is Entered:" + Type);
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PartyID, PartyID);
				verifyCLickPurchaseBillsFrame();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Document Voucher Type Shown");
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.BillNumInput, DCNO,
								ElementType.Xpath);
						logs.logging("INFO", "Bill Number Inserted SuccessFully");
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PurchaseBillTickIcon,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PurchaseBillTickIcon,
									ElementType.Xpath);
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
//		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("StockTransferAddLocalEXDCTRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L0002"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		ValidateHeaderDetailsForDirectEntry();
//		commonFunc.checkRefreshCellsDisappear();
//		VerifyToWriteExcelValueForDirectRcmSupplier(webDB.getDataFromProperties("StockTransferAddDCTIS"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferIGSTEXP"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferIGSTEXP"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplier(
				webDB.getDataFromProperties("StockTransferIGSTEXP"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferIGSTEXP"),
				commonFunc.getDataFromTestDataSheet("BENNYASSOCIATES"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StockTransferIGSTEXP"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferIGSTEXP"));
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
	 * @throws IOException
	 */
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxStockTransferDirectStockItemServiceitemAndchargeItem()
			throws IOException {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransfer(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("StockTransferLocalLoaction"),
				commonFunc.getDataFromTestDataSheet("U-6"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToWriteExcelValueForDirectRcmSupplier(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierWithRcm(
//				webDB.getDataFromProperties("STIGSTWEXPRCM"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("STIGSTWEXPRCM"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("STIGSTWEXPRCM"), commonFunc.getDataFromTestDataSheet("BENNYASSOCIATES"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	private void ValidateHeaderDetailsForCommonDirectEntry(String LocationID, String Unit, String SupplierID,
			String PurchaseType) {

		verifyAssertionBothValues(dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
				commonFunc.getDataFromTestDataSheet("PBHeaderLocation")), LocationID);
		logs.logging("INFO", "Location Is Matched For This Purchase Bill Document");
		verifyAssertionBothValues(dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
				commonFunc.getDataFromTestDataSheet("PBHeaderUnit")), Unit);
		logs.logging("INFO", "Unit Is Matched For This Purchase Bill Document ");
		verifyAssertionBothValues(dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
				commonFunc.getDataFromTestDataSheet("PBHeaderSupplierID")), SupplierID);
		logs.logging("INFO", "Supplier ID Is Matched For GRN and PB ");
		verifyAssertionBothValues(dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
				commonFunc.getDataFromTestDataSheet("SUPHD_PUR_TYPE")), PurchaseType);
		logs.logging("INFO", "Supplier ID Is Matched For GRN and PB ");
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransfer(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("StockTransferLocalLoaction"),
				commonFunc.getDataFromTestDataSheet("U-6"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToWriteExcelValueForDirectRcmSupplier(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierWithRcm(
//				webDB.getDataFromProperties("STIGSTWEXPRCM"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("STIGSTWEXPRCM"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("STIGSTWEXPRCM"), commonFunc.getDataFromTestDataSheet("BENNYASSOCIATES"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("STIGSTWEXPRCM"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(webDB.getDataFromProperties("STIGSTWEXPRCM"));
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
	 * @throws IOException
	 */
	public void verifyToCreateUnregisteredSupplierwithLocalGstStockTransferDirectStockItemserviceItemAndChargeItem()
			throws IOException {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("STUNRLocal"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L54PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L54PK"),
				commonFunc.getDataFromTestDataSheet("U12"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToWriteExcelValueForDirectRcmSupplier(webDB.getDataFromProperties("STUNRLocal"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("STUNRLocal"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("STUNRLocal"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferTypeLocalSupplierForRcmCategory(
				webDB.getDataFromProperties("STUNRLocal"));
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierWithRcm(
//				webDB.getDataFromProperties("STUNRLocal"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("STUNRLocal"), commonFunc.getDataFromTestDataSheet("PKPolyMers"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("STUNRLocal"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(webDB.getDataFromProperties("STUNRLocal"));
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
	 * @throws IOException
	 */
	public void verifyToCreateUnregisteredSupplierWithLocalGstStockTransferDirectAndExpensesAccountStockItemserviceItemAndChargeItem()
			throws IOException {
//		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("StockTransferAddLocalEXIGST"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("STUNRLocal"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L54PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L54PK"),
				commonFunc.getDataFromTestDataSheet("U12"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
//		VerifyToWriteExcelValueForDirectRcmSupplier(webDB.getDataFromProperties("STUNRLocal"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("STUNRLocal"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("STUNRLocal"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("STUNRLocal"), commonFunc.getDataFromTestDataSheet("LOCAL_UNREGISTERED"));
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierWithRcm(
//				webDB.getDataFromProperties("STUNRLocal"));webDB.getDataFromProperties("LOCAL_UNREGISTERED")
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("STUNRLocal"), commonFunc.getDataFromTestDataSheet("PKPolyMers"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("STUNRLocal"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(webDB.getDataFromProperties("STUNRLocal"));
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
	 * @throws IOException
	 */
	public void verifyToCreateUnregisteredSupplierWithLocalGstAllowIgsttTaxStockTransferDirectStockItemServiceItemAndchargeItem()
			throws IOException {
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("STUNRLocalToIgst"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("STUNRLocalToIgst"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L54PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L54PK"),
				commonFunc.getDataFromTestDataSheet("U12"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToWriteExcelValueForIgstDirectStockTransfertype(webDB.getDataFromProperties("STUNRLocalToIgst"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("STUNRLocalToIgst"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("STUNRLocalToIgst"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierforRcm(
//				webDB.getDataFromProperties("STUNRLocalToIgst"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("STUNRLocalToIgst"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("STUNRLocalToIgst"), commonFunc.getDataFromTestDataSheet("PKPolyMers"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("STUNRLocalToIgst"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("STUNRLocalToIgst"));
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
	 * @throws IOException
	 */
	public void verifyToCreateUnregisteredSupplierWithLocalGstAllowIgstTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndchargeItem()
			throws IOException {
		VerifyToAfterWriteTheExcelValueInNumberIgstField(webDB.getDataFromProperties("STUNRLocalToIgst"));
		VerifyToAfterWriteTheExcelValueInNumberRcmField(webDB.getDataFromProperties("STUNRLocalToIgst"));
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L54PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L54PK"),
				commonFunc.getDataFromTestDataSheet("U12"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToWriteExcelValueForIgstDirectStockTransfertype(webDB.getDataFromProperties("STUNRLocalToIgst"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(1000);
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("STUNRLocalToIgst"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("STUNRLocalToIgst"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierforRcm(
//				webDB.getDataFromProperties("STUNRLocalToIgst"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("STUNRLocalToIgst"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("STUNRLocalToIgst"), commonFunc.getDataFromTestDataSheet("PKPolyMers"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("STUNRLocalToIgst"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("STUNRLocalToIgst"));
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
	 * @throws IOException
	 */
	public void verifyToCreateUnregisteredSupplierWithInterstateGstStockTransferDirectStockItemServiceItemAndChargeItem()
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L55PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterISSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L55PK"),
				commonFunc.getDataFromTestDataSheet("U26"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterISSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("STUNRIGST"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("STUNRIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierforRcm(
//				webDB.getDataFromProperties("STUNRLocalToIgst"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("STUNRIGST"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("STUNRIGST"), commonFunc.getDataFromTestDataSheet("KV_CAREERS_INTR_UR_AR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("STUNRIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(webDB.getDataFromProperties("STUNRIGST"));
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
	 * @throws IOException
	 */
	public void verifyToCreateUnregisteredSupplierWithInterstateGstStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L55PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterISSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L55PK"),
				commonFunc.getDataFromTestDataSheet("U26"),
				commonFunc.getDataFromTestDataSheet("StockTransferUnregisterISSupplierPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("STUNRIGST"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("STUNRIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierforRcm(
//				webDB.getDataFromProperties("STUNRLocalToIgst"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("STUNRIGST"),
				commonFunc.getDataFromTestDataSheet("INTERSTATE_UNREGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("STUNRIGST"), commonFunc.getDataFromTestDataSheet("KV_CAREERS_INTR_UR_AR"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("STUNRIGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(webDB.getDataFromProperties("STUNRIGST"));
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
	 * @throws IOException
	 */
	public void verifyToCreateDomesticRegisteredSupplierWithLocalFreightGst() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L58PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplierWFPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L58PK"),
				commonFunc.getDataFromTestDataSheet("U31"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplierWFPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferAddLocalFright"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferAddLocalFright"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierforRcm(
//				webDB.getDataFromProperties("STUNRLocalToIgst"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferAddLocalFright"),
				commonFunc.getDataFromTestDataSheet("LOCAL_FRIGHT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferAddLocalFright"),
				commonFunc.getDataFromTestDataSheet("DOMESTIC_Register_Supplier_Firght"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalFright"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferAddLocalFright"));
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
	 * @throws IOException
	 */
	public void verifyToCreateDomesticRegisteredSupplierWithSezUnitGst() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L59PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplierSezUnitPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L59PK"),
				commonFunc.getDataFromTestDataSheet("SU1"),
				commonFunc.getDataFromTestDataSheet("StockTransferLocalSupplierSezUnitPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferDomRegSezUnit"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferDomRegSezUnit"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierforRcm(
//				webDB.getDataFromProperties("STUNRLocalToIgst"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferDomRegSezUnit"),
				commonFunc.getDataFromTestDataSheet("SEZ_UNIT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferDomRegSezUnit"),
				commonFunc.getDataFromTestDataSheet("DOMESTICREGISTEREDSUPPLIERWITHSEZUNITGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(
				webDB.getDataFromProperties("StockTransferDomRegSezUnit"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferDomRegSezUnit"));
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
	 * @throws IOException
	 */
	public void verifyToCreateDomesticRegisteredSupplierWithUnionTerritoryGst() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L62PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("StockTransferUTSupplierSezUnitPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L62PK"),
				commonFunc.getDataFromTestDataSheet("UT2"),
				commonFunc.getDataFromTestDataSheet("StockTransferUTSupplierSezUnitPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferDomRegUT"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferDomRegUT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierforRcm(
//				webDB.getDataFromProperties("STUNRLocalToIgst"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferDomRegUT"), commonFunc.getDataFromTestDataSheet("SEZ_UNIT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferDomRegUT"),
				commonFunc.getDataFromTestDataSheet("DOMESTICREGISTEREDSUPPLIERWITHSEZUNITGST"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StockTransferDomRegUT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferDomRegUT"));
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
	 * @throws IOException
	 */
	public void verifyToCreateDomesticUnregisteredSupplierWithLocalFreightGst() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L63PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"), commonFunc.getDataFromTestDataSheet("STLURSPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L63PK"),
				commonFunc.getDataFromTestDataSheet("DU1"), commonFunc.getDataFromTestDataSheet("STLURSPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferDomURLF"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferDomURLF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierforRcm(
//				webDB.getDataFromProperties("STUNRLocalToIgst"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferDomURLF"),
				commonFunc.getDataFromTestDataSheet("LOCAL_FRIGHT_UNRRESGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferDomURLF"),
				commonFunc.getDataFromTestDataSheet("DOMESTIC_UNREGISTERED_SUPPLIER_WITH_LF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StockTransferDomURLF"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferDomURLF"));
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
	 * @throws IOException
	 */
	public void verifyToCreateDomesticUnRegisteredSupplierWithSezUnitGST() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("PremUsername"), System.getProperty("LoginPassword"),
				"PremUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyToCreateNewPurchaseBillDocumentForDirectStockTransferForInterstate(
				commonFunc.getDataFromTestDataSheet("STTypePK"), commonFunc.getDataFromTestDataSheet("L65PK"),
				commonFunc.getDataFromTestDataSheet("BillNo"), commonFunc.getDataFromTestDataSheet("STLURSEZPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		storePurchaseVoucherNumber();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		ValidateHeaderDetailsForCommonDirectEntry(commonFunc.getDataFromTestDataSheet("L65PK"),
				commonFunc.getDataFromTestDataSheet("DU3"), commonFunc.getDataFromTestDataSheet("STLURSEZPK"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		commonFunc.checkRefreshCellsDisappear();
		verifyAddTheItemBasedOnTypeForDirectStockTransfer(webDB.getDataFromProperties("StockTransferDomURSU"));
		commonFunc.checkRefreshCellsDisappear();
		VerifyToClickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalPartForStocktransaferType(webDB.getDataFromProperties("StockTransferDomURSU"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyToValiadteJournalDetailsPartForStocktransaferTypeInterstateSupplierforRcm(
//				webDB.getDataFromProperties("STUNRLocalToIgst"));
		verifyToValiadteJournalDetailsPartForStocktransaferAllTypeSupplierCommon(
				webDB.getDataFromProperties("StockTransferDomURSU"),
				commonFunc.getDataFromTestDataSheet("SEZ_UNIT_UNRGISTERED"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		verifyToValiadteJournalDetailPartForAccuralAccountStocktransaferTypeLocalSupplierForCommon(
				webDB.getDataFromProperties("StockTransferDomURSU"),
				commonFunc.getDataFromTestDataSheet("DOMESTIC_UNREGISTERED_SUPPLIER_WITH_SEZ_UNIT"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForDirectStockTransferType(webDB.getDataFromProperties("StockTransferDomURSU"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		VerifyToValidateRoundOffAmountForHeaderValueForStockTransferType(
				webDB.getDataFromProperties("StockTransferDomURSU"));
		VerifyToClickPostButton();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalPartForLandedCostType(String SheetName) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String GL_Account_Desc = PBMultipleline[i][75];
			String DebitAmount = PBMultipleline[i][76];
			String CreditAmount = PBMultipleline[i][77];
			if (!GL_Account_Desc.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PbJournalQueryPK(VariableHandler.getPbNumber(), GL_Account_Desc,
						commonFunc.getDataFromTestDataSheet("PBDebitAmount")), DebitAmount);
				logs.logging("INFO", "Debit Amount Matched With DB Values For This GL " + GL_Account_Desc);
				verifyAssertionBothValues(dbCommonFunc.PbJournalQueryPK(VariableHandler.getPbNumber(), GL_Account_Desc,
						commonFunc.getDataFromTestDataSheet("PBCreditAmount")), CreditAmount);
				logs.logging("INFO", "Credit Amount Matched With DB Values For This GL Account " + GL_Account_Desc);

			}
		}

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailsPartForLandedCostAllTypeSupplierCommon(String SheetName,
			String supplyType) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();

		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";

		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);

		for (int i = 22; i < PBMultipleline.length; i++) {

			String Seqnum = PBMultipleline[i][0];

			if (!Seqnum.equals("")) {
				List<String> value = new ArrayList<>();
				List<String> value1 = new ArrayList<>();
				// Always Line Amount
				value.add(PBMultipleline[i][18]); // Debit Line Amount
				value1.add(PBMultipleline[i][78]); // Credit Line Amount
				System.out.println(PBMultipleline[i][18]);
				System.out.println(PBMultipleline[i][78]);
				System.out.println(supplyType);
				switch (supplyType.toUpperCase()) {

				case "LOCAL_REGISTERED":

					value.add(PBMultipleline[i][21]); // CGSTDebit
					value.add(PBMultipleline[i][22]); // SGSTDebit

					value1.add(PBMultipleline[i][79]); // CGST Credit
					value1.add(PBMultipleline[i][80]); // SGST Credit
					break;

				case "INTERSTATE_REGISTERED":

					value.add(PBMultipleline[i][23]); // IGSTCredit
					value1.add(PBMultipleline[i][85]); // IGST Credit
					break;

				case "LOCAL_UNREGISTERED":

					value.add(PBMultipleline[i][21]);// CGSTDebit
					value.add(PBMultipleline[i][22]); // SGSTDebit
					value.add(PBMultipleline[i][88]); // Output CGST RCMDebit
					value.add(PBMultipleline[i][89]); // Output SGST RCMDebit

					value1.add(PBMultipleline[i][83]);// CGST Credit
					value1.add(PBMultipleline[i][84]);// SGST Credit
					value1.add(PBMultipleline[i][93]); // Output CGST RCM Credit
					value1.add(PBMultipleline[i][94]); // Output SGST RCM Credit

					break;

				case "INTERSTATE_UNREGISTERED":
					value.add(PBMultipleline[i][23]);// IgstAmountDebit
					value.add(PBMultipleline[i][90]); // Output IGST RCMDebit

					value1.add(PBMultipleline[i][85]);// Igst_AmountCredit
					value1.add(PBMultipleline[i][95]);// Output IGST RCM Credit

					break;

				case "LOCAL_FRIGHT":

					break;

				case "SEZ_UNIT":

					break;

				case "LOCAL_FRIGHT_UNRRESGISTERED":
//					value.add(PBMultipleline[i][88]); // Output CGST RCMDebit
//
//					value1.add(PBMultipleline[i][88]); // Output CGST RCM Credit
					break;
				case "SEZ_UNIT_UNRGISTERED":
//					value.add(PBMultipleline[i][88]); // Output CGST RCMDebit
//
//					value1.add(PBMultipleline[i][88]); // Output CGST RCM Credit
					break;
				case "LOCAL_REGISTERED_WITH_TDS":
					value.add(PBMultipleline[i][21]); // CGSTDebit
					value.add(PBMultipleline[i][22]); // SGSTDebit
//					value.add(PBMultipleline[i][95]); // Supplier Account_Debit
//					value.add(PBMultipleline[i][97]); // TCS Account_Debit

					value1.add(PBMultipleline[i][79]); // CGST Credit
					value1.add(PBMultipleline[i][80]); // SGST Credit
//					value1.add(PBMultipleline[i][98]); // TCS Account_Credit
					break;
				case "INTERSTATE_REGISTERED_WITH_TDS":

					value.add(PBMultipleline[i][23]); // IGSTCredit
					value1.add(PBMultipleline[i][85]); // IGST Credit
					break;

				}

				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")), value);

				logs.logging("INFO", "Debit Amount Matched With DB Values For Voucher Line " + Seqnum);

				verifyAssertionBothValues(dbCommonFunc.PbJournalViewDetailsQueryPK(VariableHandler.getPbNumber(),
						Seqnum, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")), value1);

				logs.logging("INFO", "Credit Amount Matched With DB Values For Voucher Line " + Seqnum);
			}
		}
	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToValiadteJournalDetailPartForAccuralAccountForLandedCostTypeCommon(String SheetName,
			String SupplierID) {

		String[][] PBMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PBMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < PBMultipleline.length; i++) {
			String GL_Account_Desc = PBMultipleline[i][75];
			String DebitAmount = PBMultipleline[i][76];
			String CreditAmount = PBMultipleline[i][77];
			if (GL_Account_Desc != null && !GL_Account_Desc.isEmpty() && GL_Account_Desc.equals(SupplierID)) {
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
						DebitAmount);
				logs.logging("INFO", "Detail Part Debit Amount Matched With DB Values For This GL " + GL_Account_Desc);
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(),
								GL_Account_Desc, commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
						CreditAmount);
				logs.logging("INFO",
						"Detail Part Credit Amount Matched With DB Values For This GL Account " + GL_Account_Desc);

			}
		}

	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyPraxisTDS(String SheetName, String SupplierName, String TDSAccount) {
		ExcelUtils excelUtils = new ExcelUtils();
		String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		List<String> debitValues = new ArrayList<>();
		List<String> creditValues = new ArrayList<>();
		// Read all data from Excel
		String[][] PBMultipleline = excelUtils.readAllDataInExcel(filePath, SheetName);
		String TDSDebit = "0";
		String TDSCredit = "0";
		for (int i = 22; i < PBMultipleline.length; i++) {

			if (i == 22) {
				TDSDebit = getValue(PBMultipleline[i][98]);
				TDSCredit = getValue(PBMultipleline[i][99]);

				System.out.println("debit " + PBMultipleline[i][94]);
				System.out.println("Credit  " + PBMultipleline[i][97]);
				System.out.println("TDS debit " + PBMultipleline[i][98]);
				System.out.println("TDS Credit  " + PBMultipleline[i][99]);
//				String TDSDebit = PBMultipleline[i][98];
//				String TDSCredit = PBMultipleline[i][99];

				debitValues.add(getValue(PBMultipleline[i][94])); // 20
				debitValues.add(getValue(PBMultipleline[i][96])); // 0

				creditValues.add(getValue(PBMultipleline[i][95])); // 0
				creditValues.add(getValue(PBMultipleline[i][97])); // 1180

				System.out.println("Debit: " + debitValues);
				System.out.println("Credit: " + creditValues);
			}
			String name = (PBMultipleline[i][75] != "") ? PBMultipleline[i][75].trim() : "";

			if (name.equalsIgnoreCase(SupplierName)) {
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalViewDetailsQueryForTDSSupplierPK(VariableHandler.getPbNumber(), name,
								commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
						debitValues);

				verifyAssertionBothValues(
						dbCommonFunc.PbJournalViewDetailsQueryForTDSSupplierPK(VariableHandler.getPbNumber(), name,
								commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
						creditValues);
			}
			if (name.equalsIgnoreCase(TDSAccount)) {
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(), name,
								commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
						TDSDebit);

				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(), name,
								commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
						TDSCredit);
			}
		}
	}
	// Verify against DB
	// Debug (optional but useful)
	// Credit → 95 & 97 → index 94 & 96
	// Debit → 94 & 96 → index 93 & 95
//	List<String> debitValues = new ArrayList<>();
//	List<String> creditValues = new ArrayList<>();
//	// Define TDS column pairs: {DebitCol, CreditCol}
//	int[][] tdsColumns = { { 94, 95 }, { 96, 97 } };
//
//	// Loop through rows starting from row 22
//	// Loop through each TDS column pair
//	for (int[] colPair : tdsColumns) {
//		String debit = getSafe(PBMultipleline[i], colPair[0]);
//		String credit = getSafe(PBMultipleline[i], colPair[1]);

//		debitValues.add(debitValues);
//		creditValues.add(creditValues);

//		// Logging Amount vs Quantity/Other row
//		if (debit.equals("0") || credit.equals("0")) {
//			logs.logging("INFO", "Amount Row for " + name + ": Debit=" + debit + ", Credit=" + credit);
//		} else {
//			logs.logging("INFO",
//					"Quantity/Other Row for " + name + ": Debit=" + debit + ", Credit=" + credit);
//		}
//	}

//	/**
//	 * This Methods To Create Item Type Document
//	 * 
//	 * @author PremKumar.K-RMITS
//	 */
//	private String getSafe(String[] row, int index) {
//		if (row != null && index >= 0 && index < row.length) {
//			String value = row[index];
//			if (value == null || value.trim().isEmpty()) {
//				return "0"; // Treat empty or whitespace as 0
//			} else {
//				return value.trim();
//			}
//		}
//		return "0"; // Out of bounds treated as 0
//	}

	private String getValue(String val) {
		return (val == null || val.trim().isEmpty()) ? "0" : val.trim();
	}

	/**
	 * This Methods Used To Verify stock Quantity For Quality Warehouse
	 * Document(Single Item)
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyToWritetaxPctValue(String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Line Tax Percentage :");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String SeqNumber = ItemMultiple[i][0];
			if (!SeqNumber.equals("")) {
				String TaxPercentage = dbCommonFunc.PurchaseBillsItemWithExpOrdBy(VariableHandler.getPbNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PBLineTaxPct"));
				logs.logging("INFO", "Line Tax Percentage :" + TaxPercentage);
//				BigDecimal value = new BigDecimal(TaxPercentage).add(new BigDecimal("100"));
				BigDecimal value = new BigDecimal(TaxPercentage).divide(new BigDecimal("100"));
				logs.logging("INFO", "Tax Percentage Is:" + value);
//				writeDataAndForceExcelRecalc(Filepath, SheetName, i, 20, value.toString());
				String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "DBDETAILS.xlsx";

				writeDataAndForceExcelRecalcForTaxPCt(filePath, SheetName, i, 20, value.toString());
			}
		}

//		String filePath = System.getProperty("user.dir") + File.separator + "testdata"
//		        + File.separator + "DBDETAILS.xlsx";
//
//		ExcelUtils excelUtils = new ExcelUtils();
//		String[][] ItemMultiple = excelUtils.readAllDataInExcel(filePath, SheetName);
//
//		logs.logging("INFO", "Line Tax Percentage :");
//
//		for (int i = 22; i < ItemMultiple.length; i++) {
//
//		    String SeqNumber = ItemMultiple[i][0];
//
//		    if (SeqNumber != null && !SeqNumber.trim().isEmpty()) {
//
//		        String TaxPercentage = dbCommonFunc.PurchaseBillsItemWithExpOrdBy(
//		                VariableHandler.getPbNumber(),
//		                SeqNumber,
//		                commonFunc.getDataFromTestDataSheet("PBLineTaxPct")
//		        );
//
//		        logs.logging("INFO", "Line Tax Percentage :" + TaxPercentage);
//
//		        if (TaxPercentage != null && !TaxPercentage.trim().isEmpty()) {
//
//		            BigDecimal value = new BigDecimal(TaxPercentage)
//		                    .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
//
//		            logs.logging("INFO", "Tax Percentage Is:" + value);
//
//		      //      writeDataAndForceExcelRecalc(filePath, SheetName, i, 20, "2");
//		        	writeDataAndForceExcelRecalc(filePath, SheetName, 20, 2, 2);
	}

//	String WareHouse = ItemMultiple[i][44];
//	String ExpectedStockQuantity = ItemMultiple[i][50];
//	String ExpectedStockCost = ItemMultiple[i][51];
	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */

	public void VerifyToAfterWriteTheExcelValueInNumbeForTaxPct(String sheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, sheetName);
		logs.logging("INFO", "Line Tax Percentage :");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String SeqNumber = ItemMultiple[i][0];
			if (!SeqNumber.equals("")) {
				writeDataAndForceExcelRecalc(Filepath, sheetName, i, 20, 0.18);

				logs.logging("info", "Success Fully Written");
			}
		}
	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void verifyAddTheItemBasedOnTypeForLandedCostType(String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";

		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 23; i < GRNMultipleline.length; i++) {
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
						if (Type.equals(commonFunc.getDataFromTestDataSheet("Expensetype"))) {
							VerifyToCreateExpenseTypeForDirectStockTransfer(GLAccount, Cost, HSN, GSTSupply, SeqNumber,
									TaxAmountCGST, TaxAmountSGST, TaxAmountIGST, PBLineAmount, PBDiscAmount);
						} else if (Type.equals(commonFunc.getDataFromTestDataSheet("ItemType"))
								|| Type.equals(commonFunc.getDataFromTestDataSheet("LandedCost"))) {
							VerifyToCreatetheItemTypeDocumentForDirectStockTransfer(ITEM_ID, Cost, Qty, HSN, GLAccount,
									TaxAmountCGST, TaxAmountSGST, TaxAmountIGST, GSTSupply, PBLineAmount, PBDiscAmount);
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
	public void VerifyToValidateLineWiseQueryAfterAddTheItemForLandedCostType(String SheetName) {
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
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyPraxisTDSForStandardType(String SheetName, String SupplierName, String TDSAccount) {
		ExcelUtils excelUtils = new ExcelUtils();
		String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		List<String> debitValues = new ArrayList<>();
		List<String> creditValues = new ArrayList<>();
		// Read all data from Excel
		String[][] PBMultipleline = excelUtils.readAllDataInExcel(filePath, SheetName);
		String TDSDebit = "0";
		String TDSCredit = "0";
		for (int i = 22; i < PBMultipleline.length; i++) {

			if (i == 22) {
				TDSDebit = getValue(PBMultipleline[i][102]);
				TDSCredit = getValue(PBMultipleline[i][103]);

//				System.out.println("debit " + PBMultipleline[i][98]);
//				System.out.println("Credit  " + PBMultipleline[i][]);
//				System.out.println("TDS debit " + PBMultipleline[i][98]);
//				System.out.println("TDS Credit  " + PBMultipleline[i][99]);
//				String TDSDebit = PBMultipleline[i][98];
//				String TDSCredit = PBMultipleline[i][99];

				debitValues.add(getValue(PBMultipleline[i][98])); // 20
				debitValues.add(getValue(PBMultipleline[i][100])); // 0
				creditValues.add(getValue(PBMultipleline[i][99])); // 0
				creditValues.add(getValue(PBMultipleline[i][101])); // 1180

				System.out.println("Debit: " + debitValues);
				System.out.println("Credit: " + creditValues);
			}
			String name = (PBMultipleline[i][79] != "") ? PBMultipleline[i][79].trim() : "";

			if (name.equalsIgnoreCase(SupplierName)) {
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalViewDetailsQueryForTDSSupplierPK(VariableHandler.getPbNumber(), name,
								commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
						debitValues);
				System.out.println("DB Values For Debit" + commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ"));
				logs.logging("INFO", "debitValues Matched" + name + debitValues);

				verifyAssertionBothValues(
						dbCommonFunc.PbJournalViewDetailsQueryForTDSSupplierPK(VariableHandler.getPbNumber(), name,
								commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
						creditValues);
				logs.logging("INFO", "Credit Values Matched" + name + creditValues);
				System.out.println("DB Values For Credit" + commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ"));
			}
			if (name.equalsIgnoreCase(TDSAccount)) {
				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(), name,
								commonFunc.getDataFromTestDataSheet("PBDebitAmountVJ")),
						TDSDebit);

				verifyAssertionBothValues(
						dbCommonFunc.PbJournalDetailPartForAccuralAccount(VariableHandler.getPbNumber(), name,
								commonFunc.getDataFromTestDataSheet("PBCreditAmountVJ")),
						TDSCredit);
			}
		}
	}

}
