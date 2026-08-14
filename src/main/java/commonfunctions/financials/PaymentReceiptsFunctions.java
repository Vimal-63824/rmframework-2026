package commonfunctions.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.PaymentReceiptsLocators;
import commonlocators.supplychainmanagement.MaterialIssuanceVoucherLocators;
import commonlocators.supplychainmanagement.OpenPoRcLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PaymentReceiptsFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();

	/**
	 * This Method Used To Verify Pending Cr Docs
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingCrDocs(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingCrDocs, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Cr Docs Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingCrDocs, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(PaymentReceiptsLocators.PendingPayable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Payable Button Is Displayed");
				webDB.clickAnElement(PaymentReceiptsLocators.PendingPayable, ElementType.Xpath);
				flag = webDB.waitForElement(PaymentReceiptsLocators.PendingPayableFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending Payable Frame Is Displayed");
					webDB.switchToFrame(PaymentReceiptsLocators.PendingPayableFrame, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PaymentReceiptsLocators.PendDocumentsNoLovBtn, DocNo);
					flag = webDB.waitForElement(PaymentReceiptsLocators.PendingPayableFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PaymentReceiptsLocators.PendingPayableFrame, ElementType.Xpath);
						flag = webDB.waitForElement(PaymentReceiptsLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Pending Payable Frame");
							webDB.clickAnElement(PaymentReceiptsLocators.SearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.CheckBox, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox Is Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(MaterialIssuanceVoucherLocators.CheckBox, ElementType.Xpath);
								verifyEnableCreateButton();
								flag = webDB.waitForElement(PaymentReceiptsLocators.CreateButton, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO",
											"CheckBox Is Clicked And Create Documents Button Is Displayed");
									webDB.clickAnElement(PaymentReceiptsLocators.CreateButton, ElementType.Xpath);
									commonFunc.checkRefreshCellsDisappear();
									commonFunc.hardWait(8000);
									webDB.selectDropDownByText(PaymentReceiptsLocators.SelectBank,
											commonFunc.getDataFromTestDataSheet("Bank"), ElementType.Xpath);
									commonFunc.hardWait(3000);
									webDB.selectDropDownByText(PaymentReceiptsLocators.PayMode,
											commonFunc.getDataFromTestDataSheet("Rtgs"), ElementType.Xpath);
									commonFunc.searchAndSelectValueInFilters(
											PaymentReceiptsLocators.Bank_Cash_LovButton,
											"STATE BANK OF INDIA - 30305325953");
									flag = webDB.waitForElement(PaymentReceiptsLocators.PendingPayableFrame,
											ElementType.Xpath);
									if (flag) {
										webDB.switchToFrame(PaymentReceiptsLocators.PendingPayableFrame,
												ElementType.Xpath);
										flag = webDB.waitForElement(PaymentReceiptsLocators.OkButton,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Ok Button Is Displayed");
											webDB.clickAnElement(PaymentReceiptsLocators.OkButton, ElementType.Xpath);
											webDB.switchToDefaultContent();
											flag = webDB.waitForElement(PaymentReceiptsLocators.BankAC,
													ElementType.Xpath);
											VerifyStoreVoucherNumber();
											commonFunc.hardWait(2000);
											webDB.sendTextToAnElement(PaymentReceiptsLocators.ReferenceInputNumber,
													DocNo, ElementType.Xpath);
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
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Enable Create Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyEnableCreateButton() {
		for (int i = 0; i < 60; i++) {
			commonFunc.hardWait(1000);
			if (webDB.isElementEnabled(PaymentReceiptsLocators.CreateButton, ElementType.Xpath)) {
				flag = true;
				break;
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Create Payment Receipt
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreatePaymentReceipt() {
		webDB.moveToElement(PaymentReceiptsLocators.DrCr, ElementType.Xpath);
		webDB.moveToElement(PaymentReceiptsLocators.OtherDetails, ElementType.Xpath);
		flag = webDB.waitForElement(PaymentReceiptsLocators.OtherDetails, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Other Details Button Is Displayed");
			webDB.clickAnElement(PaymentReceiptsLocators.OtherDetails, ElementType.Xpath);
			flag = webDB.waitForElement(PaymentReceiptsLocators.AccountFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Account Frmae Is Displayed");
				webDB.switchToFrame(PaymentReceiptsLocators.AccountFrame, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(PaymentReceiptsLocators.AccountNoLovButton,
						commonFunc.getDataFromTestDataSheet("BankofBaroda"));
				flag = webDB.waitForElement(PaymentReceiptsLocators.AccountFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Account Frame Is Displayed");
					webDB.switchToFrame(PaymentReceiptsLocators.AccountFrame, ElementType.Xpath);
					flag = webDB.waitForElement(PaymentReceiptsLocators.AccountTickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Account Tick Icon Is Displayed");
						webDB.clickAnElement(PaymentReceiptsLocators.AccountTickIcon, ElementType.Xpath);
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(PaymentReceiptsLocators.CloseIcon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Close Icon Is Displayed");
							webDB.clickAnElement(PaymentReceiptsLocators.CloseIcon, ElementType.Xpath);
							flag = webDB.waitForElement(PaymentReceiptsLocators.OtherDetails, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Close Icon Is Clicked");

							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store Voucher Number
	 * 
	 * @author Deepan-GWL
	 */
	public void VerifyStoreVoucherNumber() {
		String BpvNumber = webDB.getTextFromElement(PaymentReceiptsLocators.BpvNumber, ElementType.Xpath);
		VariableHandler.setBpvNumber(BpvNumber);
	}

	/**
	 * This Method Is Used To Click Tick And Post Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void clickTickAndPostIcon() {
		flag = webDB.waitForElement(PaymentReceiptsLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.moveToElement(PaymentReceiptsLocators.SaveButton, ElementType.Xpath);
			webDB.clickAnElement(PaymentReceiptsLocators.SaveButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			if (webDB.isElementDisplayed(PaymentReceiptsLocators.ExpandButton, ElementType.Xpath)) {
				webDB.clickAnElement(PaymentReceiptsLocators.ExpandButton, ElementType.Xpath);
			}
			flag = webDB.waitForClickElement(PaymentReceiptsLocators.PostButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(PaymentReceiptsLocators.PostButton, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
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
			flag = webDB.waitForElement(PaymentReceiptsLocators.WorkApprovalOkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PaymentReceiptsLocators.WorkApprovalOkButton, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This MEthod Used To Verify Payment Receipt Approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPaymentReceiptApproval() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPaymentReceipt();
		verifyPendingCrDocs(VariableHandler.getPbNumber());
//		verifyCreatePaymentReceipt();
		commonFunc.hardWait(2000);
		webDB.sendTextToAnElement(PaymentReceiptsLocators.ReferenceInputNumber, VariableHandler.getPbNumber(),
				ElementType.Xpath);
		clickTickAndPostIcon();
		verifyWaitingForApproval();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBPvNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPaymentReceipt();
			verifyPaymentReceiptApprovalStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Payment Receipt Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPaymentReceiptApprovalStatus() {
		commonFunc.hardWait(2000);
		commonFunc.searchAndSelectValueInFilters(PaymentReceiptsLocators.VoucherNumberLovButton,
				VariableHandler.getBPvNumber());
		flag = webDB.waitForElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line CheckBox Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(PaymentReceiptsLocators.ApproveSearchButton, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				webDB.clickAnElement(PaymentReceiptsLocators.ApproveSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PaymentReceiptsLocators.IssuedStatus(VariableHandler.getBPvNumber()),
						ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(
							PaymentReceiptsLocators.IssuedStatus(VariableHandler.getBPvNumber()), ElementType.Xpath);
					flag = Status.contains(commonFunc.getDataFromTestDataSheet("Issued"));
					if (flag) {
						logs.logging("INFO", "Expected Status: " + commonFunc.getDataFromTestDataSheet("Issued"));
						logs.logging("INFO", "Verified Status ,Displayed Status: " + Status);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Pending Dr And Pending receivables in Payment
	 * Receipts
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingDrAndPendingReceivables() {
		flag = webDB.waitForElement(PaymentReceiptsLocators.PendingDrDocsBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PaymentReceiptsLocators.PendingDrDocsBtn, ElementType.Xpath);
			flag = webDB.waitForClickElement(PaymentReceiptsLocators.PendingReceivablesBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Dr.Docs Button Clicked");
				webDB.moveToElement(PaymentReceiptsLocators.PendingReceivablesBtn, ElementType.Xpath);
				webDB.clickAnElement(PaymentReceiptsLocators.PendingReceivablesBtn, ElementType.Xpath);
				flag = webDB.waitForElement(PaymentReceiptsLocators.PendingReceivablesIframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending Receivables Button Clicked");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch Pending Receivables Iframe
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToPendingReceivableIframe() {
		flag = webDB.waitForElement(PaymentReceiptsLocators.PendingReceivablesIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PaymentReceiptsLocators.PendingReceivablesIframe, ElementType.Xpath);
			flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_SearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Switched to Pending Receivable Iframe");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch view journal Iframe
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToViewJournalIframe() {
		flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_ViewJournalIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PaymentReceiptsLocators.BankReceipt_ViewJournalIframe, ElementType.Xpath);
			flag = webDB.waitForElement(PaymentReceiptsLocators.ViewJournal_TotalAmountDebitField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Switched to view Journal Iframe");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to Choose sales invoice in Doc Type and Search Invoice No in
	 * Find page - Pending Receivables
	 * 
	 * @author Sakthi-GWL
	 * @param InvoiceNo
	 */
	public void chooseSaleInvoiceDocTypeWithInvoiceNoInFind(String InvoiceNo) {
		switchToPendingReceivableIframe();
		flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_FindDocTypeSelect, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(PaymentReceiptsLocators.PendReceive_FindDocTypeSelect, "Sales Invoice",
					ElementType.Xpath);
			flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_FindInvoiceNoLovBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PaymentReceiptsLocators.PendReceive_FindInvoiceNoLovBtn,
						InvoiceNo);
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method used to click search and create using Invoice No
	 * 
	 * @author Sakthi-GWL
	 * @param InvoiceNo
	 */
	public void clickSearchIconAndClickCreateWithInvoiceNo(String InvoiceNo) {
		switchToPendingReceivableIframe();
		flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_SearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PaymentReceiptsLocators.PendReceive_SearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
//			flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_SearchBar, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Search Button Clicked in Pending Receivables");
//				webDB.sendTextToAnElement(PaymentReceiptsLocators.PendReceive_SearchBar, InvoiceNo, ElementType.Xpath);
//				flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_SearchGoBtn, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(PaymentReceiptsLocators.PendReceive_SearchGoBtn, ElementType.Xpath);
			flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_CheckBox(InvoiceNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Searched with Invoice number: " + InvoiceNo);
				webDB.moveToElement(PaymentReceiptsLocators.PendReceive_CheckBox(InvoiceNo), ElementType.Xpath);
				webDB.clickAnElement(PaymentReceiptsLocators.PendReceive_CheckBox(InvoiceNo), ElementType.Xpath);
				commonFunc.hardWait(2000);
				flag = webDB.isElementEnabled(PaymentReceiptsLocators.PendReceive_CreateBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO",
							"CheckBox Clicked to Create Bank Receipt for the Invoice number: " + InvoiceNo);
					webDB.clickAnElement(PaymentReceiptsLocators.PendReceive_CreateBtn, ElementType.Xpath);
					flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_SingleRadioBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Create Button Clicked and Bank Receipt Page Navigated");
					}
				}
			}
		}
//			}
//		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method used to enter Reference No and Save Header in Bank Receipts
	 * 
	 * @author Sakthi-GWL
	 * @param refNo
	 */
	public void enterRefNoAndSaveHeader(String refNo) {
		flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_RefNoInput, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PaymentReceiptsLocators.BankReceipt_RefNoInput, refNo, ElementType.Xpath);
			flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_HeaderSaveBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Reference number entered: " + refNo);
				webDB.moveToElement(PaymentReceiptsLocators.BankReceipt_HeaderSaveBtn, ElementType.Xpath);
				webDB.clickAnElement(PaymentReceiptsLocators.BankReceipt_HeaderSaveBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				commonFunc.checkSuccessToastMessageDisappear();
				flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_VoucherNoText, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Header Saved Successfully");
					String VocNo = webDB.getTextFromElement(PaymentReceiptsLocators.BankReceipt_VoucherNoText,
							ElementType.Xpath);
					VariableHandler.setBpvNumber(VocNo);
					logs.logging("INFO", "Displayed Bank Receipt Voucher No " + VocNo);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click create journal and click view journal
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCreateJournalAndClickViewJournel() {
		flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_CreateJournalBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PaymentReceiptsLocators.BankReceipt_CreateJournalBtn, ElementType.Xpath);
			webDB.clickAnElement(PaymentReceiptsLocators.BankReceipt_CreateJournalBtn, ElementType.Xpath);
			commonFunc.checkSuccessToastMessageDisappear();
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_ViewJournalBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create Journal Button Clicked");
				webDB.moveToElement(PaymentReceiptsLocators.BankReceipt_ViewJournalBtn, ElementType.Xpath);
				webDB.clickAnElement(PaymentReceiptsLocators.BankReceipt_ViewJournalBtn, ElementType.Xpath);
				flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_ViewJournalIframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "View Journal Button Clicked");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify total debit and credit amount equals in view
	 * journal
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyTotalDebitAndCreditEqualsAndCloseViewJournal() {
		switchToViewJournalIframe();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(PaymentReceiptsLocators.ViewJournal_TotalAmountDebitField, ElementType.Xpath);
		if (flag) {
			String DebitText = webDB.getTextFromElement(PaymentReceiptsLocators.ViewJournal_TotalAmountDebitField,
					ElementType.Xpath);
			logs.logging("INFO", "Displayed Debit Total Amount: " + DebitText);
			flag = webDB.waitForElement(PaymentReceiptsLocators.ViewJournal_TotalAmountCreditField, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PaymentReceiptsLocators.ViewJournal_TotalAmountCreditField, ElementType.Xpath);
				String CreditText = webDB.getTextFromElement(PaymentReceiptsLocators.ViewJournal_TotalAmountCreditField,
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Credit Total Amount: " + CreditText);
				flag = DebitText.equals(CreditText);
				if (flag) {
					logs.logging("INFO", "Verified Credit and Debit Amount in Journal");
					webDB.switchToDefaultContent();
					webDB.clickAnElement(PaymentReceiptsLocators.ViewJournal_CloseIcon, ElementType.Xpath);
					flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_ViewJournalBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Journal Close Icon Clicked Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify Credits in Dr/Cr in Lines and Total TC and BC
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCreditInDrCrLinesColumnTotalTCAndTotalBC() {
		flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_TableDRCRvalue, ElementType.Xpath);
		if (flag) {
			String DrCrText = webDB.getTextFromElement(PaymentReceiptsLocators.BankReceipt_TableDRCRvalue,
					ElementType.Xpath);
			logs.logging("INFO", "Party Has " + DrCrText + " in Bank Receipt Line");
			flag = DrCrText.contains("Cr");
			if (flag) {
				String TotalTCText = webDB.getTextFromElement(PaymentReceiptsLocators.BankReceipt_TotalTCvalue,
						ElementType.Xpath);
				logs.logging("INFO", "Bank Receipt Total TC: " + TotalTCText);
				flag = TotalTCText.contains("Cr");
				if (flag) {
					String TotalBCText = webDB.getTextFromElement(PaymentReceiptsLocators.BankReceipt_TotalBCvalue,
							ElementType.Xpath);
					logs.logging("INFO", "Bank Receipt Total BC: " + TotalBCText);
					flag = TotalBCText.contains("Cr");
					if (flag) {
						logs.logging("INFO", "Verified Party Dr/Cr line detail , Total TC and BC contains Credits");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Post button in Bank Receipts
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPostButtonInBankReceipts() {
		flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_PostBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PaymentReceiptsLocators.BankReceipt_PostBtn, ElementType.Xpath);
			webDB.clickAnElement(PaymentReceiptsLocators.BankReceipt_PostBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked in Bank Receipts");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select BR Type Payment Type and Payment Mode with
	 * Bank/Cashier in bank Receipt creation
	 * 
	 * @author Sakthi-GWL
	 * @param RadioBtnType
	 * @param PaymentType
	 * @param PaymentMode
	 * @param BankOrCashier
	 */
	public void fillFormOfBankReceiptWithBankOrCashTypes(String RadioBtnType, String PaymentType, String PaymentMode,
			String BankOrCashier) {
		switchToPendingReceivableIframe();
		fillRadioButtonTypeInBankReceiptCreation(RadioBtnType);
		flag = webDB.selectDropDownByText(PaymentReceiptsLocators.PendReceive_PaymentType, PaymentType,
				ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Payment Type Selected: " + PaymentType);
			if (PaymentType.contains("Bank")) {
				flag = webDB.selectDropDownByText(PaymentReceiptsLocators.PendReceive_PaymentMode, PaymentMode,
						ElementType.Xpath);
				if (flag)
					logs.logging("INFO", "Payment Mode Selected: " + PaymentMode);
			}
			commonFunc.searchAndSelectValueInFilters(PaymentReceiptsLocators.PendReceive_BankCashierLovBtn,
					BankOrCashier);
			switchToPendingReceivableIframe();
			flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_OkBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PaymentReceiptsLocators.PendReceive_OkBtn, ElementType.Xpath);
				webDB.clickAnElement(PaymentReceiptsLocators.PendReceive_OkBtn, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(PaymentReceiptsLocators.BankReceipt_RefNoInput, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Clicked in Bank Receipt Creation");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select single or merge Bank Receipt Creation Type
	 * 
	 * @author Sakthi-GWL
	 * @param RadioBtnType
	 */
	private void fillRadioButtonTypeInBankReceiptCreation(String RadioBtnType) {
		flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_MergeRadioBtn, ElementType.Xpath);
		if (RadioBtnType.equalsIgnoreCase("Merge") & flag) {
			flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_MergeRadioBtn, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(PaymentReceiptsLocators.PendReceive_MergeRadioBtn, ElementType.Xpath);
				logs.logging("INFO", RadioBtnType + " Clicked Successfully");
			}
		} else if (RadioBtnType.equalsIgnoreCase("Single") && flag) {
			flag = webDB.waitForElement(PaymentReceiptsLocators.PendReceive_SingleRadioBtn, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(PaymentReceiptsLocators.PendReceive_SingleRadioBtn, ElementType.Xpath);
				logs.logging("INFO", RadioBtnType + " Clicked Successfully");
			}
		} else {
			flag = false;
			logs.logging("INFO", "Invalid RadioType Button");
		}
		assertTrue(flag);
	}
}
