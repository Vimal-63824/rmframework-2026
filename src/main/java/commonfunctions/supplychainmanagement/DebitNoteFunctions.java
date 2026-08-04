package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.DebitNoteLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class DebitNoteFunctions {

	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	SalesOrderFunctions salesOrderFunctions = new SalesOrderFunctions();
	DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static ExcelUtils excelUtils = new ExcelUtils();
	static MaterialReceiptVoucherFunctions mrvFunc = new MaterialReceiptVoucherFunctions();
	static InspectionDocumentFunctions qcFunc = new InspectionDocumentFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	static SalesInvoiceFunctions salesInvoiceFunc = new SalesInvoiceFunctions();

	/**
	 * This Method Used For Create DebitNote With GRN General Type
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createDebitMemoWithGRNGeneral() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToDebitNoteScreen();
		ClickingDebitNotePendingIcon();
		SearchingDocumentNo();
		InsertingDNPrefixAndReason(commonFunc.getDataFromTestDataSheet("DB_DN_Pfx"),
				commonFunc.getDataFromTestDataSheet("DB_Reason"));
		ClickingCreateInvoiceandUpdateNarration();
		clickingDebitNotePostButton();
		DebitNoteforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
				"HariUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getDBdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getDBdocno());
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		return flag;
	}

	/**
	 * This Method Used For Create DebitNote With GRN General Type
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingDebitNotePendingIcon() {
		flag = webDB.waitForElement(DebitNoteLocators.DebitNote_Pend_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DebitNote_Pend_Icon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(DebitNoteLocators.DB_Purchase_Tab, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(DebitNoteLocators.DB_Purchase_Tab, ElementType.Xpath);
				flag = webDB.waitForElement(DebitNoteLocators.DB_Purchase_Back_Icon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Debit Note Purchase Tab Clicked Sucessfully");
				}

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Searching Document NO and Create The Document
	 * 
	 * @author Vimal - RMITS
	 */
	public void SearchingDocumentNo() {
		flag = webDB.waitForElement(DebitNoteLocators.DB_Pend_Search_Field, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_Pend_Search_Field, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.sendTextToAnElement(DebitNoteLocators.DB_Pend_Search_Field, VariableHandler.getGrnDocNumber(),
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Pend_Go_Button, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(DebitNoteLocators.DB_Pend_Go_Button, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(DebitNoteLocators.DB_Pend_Check_box, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DebitNoteLocators.DB_Pend_Check_box, ElementType.Xpath);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(DebitNoteLocators.DB_Pend_CreateRtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(DebitNoteLocators.DB_Pend_CreateRtn, ElementType.Xpath);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(DebitNoteLocators.DB_Pend_Create_Ok_Button, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(DebitNoteLocators.DB_Pend_Create_Ok_Button, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(DebitNoteLocators.DB_Trans_Toast_Msg, ElementType.Xpath);
							if (webDB.verifyIsElementDisplayed(DebitNoteLocators.DB_Trans_Toast_Msg,
									ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(DebitNoteLocators.DB_Trans_Toast_Msg,
										ElementType.Xpath);
								commonFunc.hardWait(2000);
								String DBdocno = webDB.getTextFromElement(DebitNoteLocators.DebitNote_Doc_No,
										ElementType.Xpath);
								logs.logging("INFO", "Get RW DocNo Is: " + DBdocno);
								VariableHandler.setDBdocno(DBdocno);
								commonFunc.hardWait(2000);
								flag = webDB.waitForElement(DebitNoteLocators.DB_Cre_Invoice_Button, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Debit Note Record Created From Pending Sucessfully");
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
	 * This Method Used For Inserting DNPrefix And Reason In Header
	 * 
	 * @author Vimal - RMITS
	 */
	public void InsertingDNPrefixAndReason(String DB_DN_Pfx, String DB_Reason) {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(DebitNoteLocators.DB_DN_Pfx_Loc_Btn, ElementType.Xpath);
		if (flag) {
			webDB.waitForClickElement(DebitNoteLocators.DB_DN_Pfx_Loc_Btn, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(DebitNoteLocators.DB_DN_Pfx_Loc_Btn, DB_DN_Pfx);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Reason_Lov, ElementType.Xpath);
			if (flag) {
				webDB.waitForClickElement(DebitNoteLocators.DB_Reason_Lov, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(DebitNoteLocators.DB_Reason_Lov, DB_Reason);
				flag = webDB.waitForElement(DebitNoteLocators.DB_Header_Save_Icon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DebitNoteLocators.DB_Header_Save_Icon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(DebitNoteLocators.DB_Cre_Invoice_Button, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Debit Note Header Created Sucessfully");
					}
				}
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Create Invoice and Update Narration, Create
	 * Journal
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingCreateInvoiceandUpdateNarration() {
		flag = webDB.waitForElement(DebitNoteLocators.DB_Cre_Invoice_Button, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_Cre_Invoice_Button, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Upd_Narr_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(DebitNoteLocators.DB_Upd_Narr_Btn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(DebitNoteLocators.DB_GenBatch_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DebitNoteLocators.DB_GenBatch_Btn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(DebitNoteLocators.DB_CreateJournal, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(DebitNoteLocators.DB_CreateJournal, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(2000);
						if (webDB.verifyIsElementDisplayed(DebitNoteLocators.DB_Trans_Toast_Msg, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(DebitNoteLocators.DB_Trans_Toast_Msg,
									ElementType.Xpath);
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(DebitNoteLocators.DB_Post_Btn, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO",
										"Cre. Inv, Upd. Narration, Gen. batch ANd Cre.Journal Clicked Sucessfully");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method Used For Clicking Debit Note Post Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingDebitNotePostButton() {
		flag = webDB.waitForElement(DebitNoteLocators.DB_Post_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_Post_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Debit Note forwardForApproval
	 * 
	 * @author Vimal - RMITS
	 */
	public void DebitNoteforwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SI_HOD"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(DebitNoteLocators.DebitNote_Pend_Icon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To Debit Note Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Debit Note Add Icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingDebitNoteAddIcon() {
		flag = webDB.waitForElement(DebitNoteLocators.DebitNote_Add_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DebitNote_Add_Icon, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Loc_ID_Lov_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Debit Note Add Icon Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Location ID And Supplier And Create The record
	 * 
	 * @author Vimal - RMITS
	 */
	public void InsertingLocationAndCustomer(String LocationId, String CustomerName) {
		flag = webDB.waitForElement(DebitNoteLocators.DB_Loc_ID_Lov_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(DebitNoteLocators.DB_Loc_ID_Lov_Btn, LocationId);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Supplier_Lov_Btn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(DebitNoteLocators.DB_Supplier_Lov_Btn, CustomerName);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(DebitNoteLocators.DB_Create_Tick_Icon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DebitNoteLocators.DB_Create_Tick_Icon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(DebitNoteLocators.DB_Trans_Toast_Msg, ElementType.Xpath);
					if (webDB.verifyIsElementDisplayed(DebitNoteLocators.DB_Trans_Toast_Msg, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(DebitNoteLocators.DB_Trans_Toast_Msg, ElementType.Xpath);
						commonFunc.hardWait(2000);
						String DBdocno = webDB.getTextFromElement(DebitNoteLocators.DebitNote_Doc_No,
								ElementType.Xpath);
						logs.logging("INFO", "Get RW DocNo Is: " + DBdocno);
						VariableHandler.setDBdocno(DBdocno);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(DebitNoteLocators.DB_Cre_Invoice_Button, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Debit Note Record Created Sucessfully");
						}
					}
				}
			}
		}
	}

	/**
	 * This Method Used For Clicking Create Invoice and Update Narration, Create
	 * Journal
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingWithoutGenBatchCreateInvoiceandUpdateNarration() {
		flag = webDB.waitForElement(DebitNoteLocators.DB_Cre_Invoice_Button, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_Cre_Invoice_Button, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Upd_Narr_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(DebitNoteLocators.DB_Upd_Narr_Btn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(DebitNoteLocators.DB_CreateJournal, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DebitNoteLocators.DB_CreateJournal, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					if (webDB.verifyIsElementDisplayed(DebitNoteLocators.DB_Trans_Toast_Msg, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(DebitNoteLocators.DB_Trans_Toast_Msg, ElementType.Xpath);
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(DebitNoteLocators.DB_Post_Btn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"Cre. Inv, Upd. Narration, Gen. batch ANd Cre.Journal Clicked Sucessfully");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Clicking Debit Note LineWise Add Icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingDebitNoteLineWiseAddIcon() {
		flag = webDB.waitForElement(DebitNoteLocators.DB_Line_Add_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_Line_Add_Icon, ElementType.Xpath);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Item_Column, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Debit Note LineWise Add Icon Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Debit Note Item
	 * 
	 * @author Vimal - RMITS
	 * @param Item
	 */
	public void verifyDebitNoteItemID(String Item) {
		flag = webDB.waitForElement(DebitNoteLocators.DB_Item_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_Item_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(DebitNoteLocators.DB_Item_Lov_Btn, Item);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Glacct_Column, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Linewise Item ID Inserted Successfully: " + Item);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding CreditNote Line IN ExcelSheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void AddingDebitNoteLineINExcelSheet(String SheetName) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DebitNotePurchase.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(itemMultiples.length);
		for (int i = 4; i < itemMultiples.length; i++) {
			// salesOrderFunctions.StockQtyUpdateInExcel(i, ArrayList, SheetName);
			String Item = itemMultiples[i][1];
			String GlAcct = itemMultiples[i][35];
			String CPC = itemMultiples[i][43];
			String Qty = itemMultiples[i][6];
			String DBUnitPrice = itemMultiples[i][7];
			String HSNcode_SI = itemMultiples[i][21];
			if (!Item.equals("")) {
				ClickingDebitNoteLineWiseAddIcon();
				verifyDebitNoteItemID(Item);
				InsertingSalesGlAccount(GlAcct);
				verifyLinewiseCPCCode(CPC);
				verifyItemReturnQty(Qty, DBUnitPrice);
				verifyLinewiseHSNCode(HSNcode_SI);
			}
		}
	}

	/**
	 * This Method Used For Inserting Sales GL Acct Desc
	 * 
	 * @author Vimal - RMITS
	 * @param SalesGL_Acct
	 */
	public void InsertingSalesGlAccount(String SalesGL_Acct) {
		flag = webDB.waitForElement(DebitNoteLocators.DB_Glacct_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_Glacct_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(DebitNoteLocators.DB_GlAcct_Lov_Btn, SalesGL_Acct);
			flag = webDB.waitForElement(DebitNoteLocators.DB_CPC_Column, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales GL Account Inserted Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Header CPC Code
	 * 
	 * @author Vimal - RMITS
	 * @param CPC_Code
	 */
	public void verifyLinewiseCPCCode(String CPC_Code) {
		flag = webDB.waitForElement(DebitNoteLocators.DB_CPC_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_CPC_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(DebitNoteLocators.DB_CPC_Lov_Btn, CPC_Code);
			flag = webDB.waitForElement(DebitNoteLocators.DB_HSN_Column, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CPC Code Inserted Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Header HSN Code
	 * 
	 * @author Vimal - RMITS
	 * @param HSNcode_SI
	 */
	public void verifyLinewiseHSNCode(String HSNcode_SI) {
		flag = webDB.waitForElement(DebitNoteLocators.DB_HSN_Column, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_HSN_Column, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(DebitNoteLocators.DB_HSN_Lov_Btn, HSNcode_SI);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Line_Save_Icon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "HSN Code Inserted Successfully");
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(DebitNoteLocators.DB_Line_Save_Icon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DebitNoteLocators.DB_Line_Save_Icon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(DebitNoteLocators.DB_Cre_Invoice_Button, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "LineWise Save Icon Clicked Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Inserting Return Qty
	 * 
	 * @author Vimal - RMITS
	 * @param ReturnQty
	 * @param CR_UnitPrice
	 */
	public void verifyItemReturnQty(String Qty, String DBUnitPrice) {
		commonFunc.hardWait(1000);
		salesInvoiceFunc.verifyAddTableSpecs(DebitNoteLocators.DB_Qty_Field, DebitNoteLocators.DB_Qty_Input, Qty);
		webDB.pressTabKey();
		commonFunc.hardWait(2000);
		salesInvoiceFunc.verifyAddTableSpecs(DebitNoteLocators.DB_UnitPrice_Field, DebitNoteLocators.DB_UnitPrice_Input,
				DBUnitPrice);
	}

	/**
	 * This Method Used For create Debit Memo Without GRN reference
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean createDebitMemoWithoutGRNreference() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToDebitNoteScreen();
		ClickingDebitNoteAddIcon();
		InsertingLocationAndCustomer(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DebitNoteSupplier"));
		InsertingDNPrefixAndReason(commonFunc.getDataFromTestDataSheet("DB_DN_Pfx"),
				commonFunc.getDataFromTestDataSheet("DB_Reason"));
		checkDebitNoteHeaderDetailsDataBaseValuesWithUIValues();
		AddingDebitNoteLineINExcelSheet(webDB.getDataFromProperties("DebitPurchase"));
		ClickingWithoutGenBatchCreateInvoiceandUpdateNarration();
		clickingDebitNotePostButton();
		DebitNoteforwardForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
				"HariUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getDBdocno());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getDBdocno());
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		return flag;

	}

	/**
	 * This Method Used For check Debit Note Header Details DataBase Values With
	 * UIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkDebitNoteHeaderDetailsDataBaseValuesWithUIValues() {
		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(DebitNoteLocators.DB_Header_LocID, ElementType.Xpath),
				dbCommonFunctions.DBHeaderDetails(VariableHandler.getDBdocno(),
						commonFunc.getDataFromTestDataSheet("DBDBLocationColumn")));
		logs.logging("INFO", "Header Detail LocID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(DebitNoteLocators.DB_Header_Unit, ElementType.Xpath),
				dbCommonFunctions.DBHeaderDetails(VariableHandler.getDBdocno(),
						commonFunc.getDataFromTestDataSheet("DBDBUnitColumn")));
		logs.logging("INFO", "Header Detail Unit Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(DebitNoteLocators.DB_Document_Pfx, ElementType.Xpath),
				dbCommonFunctions.DBHeaderDetails(VariableHandler.getDBdocno(),
						commonFunc.getDataFromTestDataSheet("DBDBDocPfxColumn")));
		logs.logging("INFO", "Header Detail LocID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(DebitNoteLocators.DB_Document_No, ElementType.Xpath),
				dbCommonFunctions.DBHeaderDetails(VariableHandler.getDBdocno(),
						commonFunc.getDataFromTestDataSheet("DBDBDocNoColumn")));
		logs.logging("INFO", "Header Detail LocID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(DebitNoteLocators.DB_Cust_Id, ElementType.Xpath),
				dbCommonFunctions.DBHeaderDetails(VariableHandler.getDBdocno(),
						commonFunc.getDataFromTestDataSheet("DBDBCustIDColumn")));
		logs.logging("INFO", "Header Detail LocID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(DebitNoteLocators.DB_Cust_name, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.DBHeaderDetails(VariableHandler.getDBdocno(),
						commonFunc.getDataFromTestDataSheet("DBDBCustNameColumn")));
		logs.logging("INFO", "Header Detail CustomerName Matched With DataBase Values");

		checkDebitNoteDraftStatusDetailsDataBaseValuesWithUIValues();
	}

	/**
	 * This Method Used For Check DebitNote Draft Status Details DataBase Values
	 * WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkDebitNoteDraftStatusDetailsDataBaseValuesWithUIValues() {
		String DBDocumentStatus = dbCommonFunctions.CRHeaderDetails(VariableHandler.getDBdocno(),
				commonFunc.getDataFromTestDataSheet("DBDBStatusColumn"));
		salesOrderFunctions.verifyAssertionBothValues(DBDocumentStatus,
				commonFunc.getDataFromTestDataSheet("DBHeaderDraftStatus"));
		logs.logging("INFO", "Verified DebitNote Document Status Is " + DBDocumentStatus
				+ " Against This DebitNote Number " + VariableHandler.getDBdocno());
	}

	/**
	 * This Method Used For Clicking Cancel Button In Debit Note
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingCancelButtonInDebitNote() {
		flag = webDB.waitForElement(DebitNoteLocators.DB_Doc_Cancel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_Doc_Cancel, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(DebitNoteLocators.DebitNote_Add_Icon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "HeaderWise Document Cancelled");
			}
			assertTrue(flag);
		}
	}

	/**
	 * This Method Used For Clicking LineWise Delete Button In DebitNote
	 * 
	 * @author Vimal - RMITS
	 */
	public void ClickingLineWiseDeleteButtonInDebitNote() {
		flag = webDB.waitForElement(DebitNoteLocators.DB_lineWise_Cancel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DebitNoteLocators.DB_lineWise_Cancel, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(DebitNoteLocators.DB_Cre_Invoice_Button, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "LineWise Record Cancelled");
			}
			assertTrue(flag);
		}
	}

	/**
	 * This Method Used For Cancel Debit Memo document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean cancelDebitMemodocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToDebitNoteScreen();
		ClickingDebitNoteAddIcon();
		InsertingLocationAndCustomer(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DebitNoteSupplier"));
		InsertingDNPrefixAndReason(commonFunc.getDataFromTestDataSheet("DB_DN_Pfx"),
				commonFunc.getDataFromTestDataSheet("DB_Reason"));
		checkDebitNoteHeaderDetailsDataBaseValuesWithUIValues();
		AddingDebitNoteLineINExcelSheet(webDB.getDataFromProperties("DebitPurchase"));
		ClickingCancelButtonInDebitNote();
		return flag;
	}

	/**
	 * This Method Used For Cancel Debit Memo Line
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean cancelDebitMemoLine() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToDebitNoteScreen();
		ClickingDebitNoteAddIcon();
		InsertingLocationAndCustomer(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DebitNoteSupplier"));
		InsertingDNPrefixAndReason(commonFunc.getDataFromTestDataSheet("DB_DN_Pfx"),
				commonFunc.getDataFromTestDataSheet("DB_Reason"));
		checkDebitNoteHeaderDetailsDataBaseValuesWithUIValues();
		ClickingLineWiseDeleteButtonInDebitNote();
		return flag;
	}
}
