package commonfunctions.coreprocess;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.financials.PaymentReceiptsFunctions;
import commonfunctions.financials.PurchaseBillsFunctions;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import commonfunctions.supplychainmanagement.OpenSalesOrderFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CoreProcessPageFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	private static GrnPageFunctions grnPageFunctions = new GrnPageFunctions();
	private static InspectionDocumentFunctions inspectionDocumentsFunc = new InspectionDocumentFunctions();
	private static MaterialReceiptVoucherFunctions materialReceiptFunctions = new MaterialReceiptVoucherFunctions();
	private static PurchaseBillsFunctions purchaseBillsFunc = new PurchaseBillsFunctions();
	private static PaymentReceiptsFunctions paymentReceiptFunc = new PaymentReceiptsFunctions();
	private static OpenSalesOrderFunctions openSOFunc = new OpenSalesOrderFunctions();
	private static SalesOrderFunctions saleOrderFunc = new SalesOrderFunctions();
	private static SalesInvoiceFunctions saleInvoiceFunc = new SalesInvoiceFunctions();

	/**
	 * This Method Is Used To Verify Purchase Request To Payment
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseRequestToPayment() {
		verifyDirectPODomesticMaterialSimilarUOMwithLotItem();
		verifyInspectionDocumentsToPaymentsReceipts();

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Create GRN Document from Pending PO
	 * 
	 * @author PremKumar.K-RM IT
	 */
	public void verifyDirectPODomesticMaterialSimilarUOMwithLotItem() {
		flag = purchaseOrderFunc.verifyDomesticGeneralSimilarUOMFromPRtoPO();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.naviagteGrnPage();
		flag = grnPageFunctions.clickPendingPoAndSearchPoNo();
		if (flag) {
			flag = grnPageFunctions.ClickSelectAllButton();
			if (flag) {
				flag = grnPageFunctions.CreateGrn();
				if (flag) {
					flag = grnPageFunctions.SupplierBillNumberAndDcNumber();
					if (flag) {
						flag = grnPageFunctions.AddLotNumber(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
								commonFunc.getDataFromTestDataSheet("LotNo"));
						if (flag) {
							flag = grnPageFunctions.OverAllSaveAndPost();
							if (flag) {
								flag = grnPageFunctions.WaitingForApproval();
								if (flag) {
									flag = commonFunc.logoutUser();
									commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
											System.getProperty("SelvaPassword"), "SelvaUsername");
									commonFunc.naviagteGrnPage();
									verifyValidateGrnStatus(VariableHandler.getGrnDocNumber(),
											commonFunc.getDataFromTestDataSheet("ApproveStatus"));
									flag = commonFunc.logoutUser();
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
	 * Verify Search Grn and Validate Grn Status
	 * 
	 * @author Deepan-GWL
	 * @param POno
	 * @param ExpectedStatus
	 */
	public void verifyValidateGrnStatus(String GrnNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(GrnLocators.GrnNumberSelect, GrnNo);
		flag = webDB.waitForElement(GrnLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.SearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(GrnLocators.GrnStatusTableText(GrnNo), ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(GrnLocators.GrnStatusTableText(GrnNo), ElementType.Xpath);
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
	 * This Method Used to Verify Inspection Documents To Payments Receipts
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyInspectionDocumentsToPaymentsReceipts() {
		inspectionDocumentsFunc.AcceptInspectionDocuments();
		materialReceiptFunctions.verifyPostMaterialReceipt();
		purchaseBillsFunc.verifyPurchaseBillsApproval();
		paymentReceiptFunc.verifyPaymentReceiptApproval();
	}

	/**
	 * This Method used to verify Open Sale Order to Payment Receipts ETE Flow
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifySalesOrderToPaymentReceipts() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String CustomerID = commonFunc.getDataFromTestDataSheet("ERPPrivateLimitedID");
		String Item = commonFunc.getDataFromTestDataSheet("OpenSOItem");
		String Unit = RandomStringUtils.randomNumeric(2);
		String Cost = RandomStringUtils.randomNumeric(2);
		String MRP = String.valueOf(Integer.parseInt(Cost + 10));
		String OpenSOPrefix = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String OpenSOType = commonFunc.getDataFromTestDataSheet("Type_Standard");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
		try {
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenSO();
			openSOFunc.selectOpenSOTypeWithRadioButtons("Open SO");
			flag = openSOFunc.findStatusOfOpenSOWithItemAndCustomer(Item, CustomerID, Today,
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			if (!flag) {
				openSOFunc.clickAddNewOpenSOPlsIcon();
				openSOFunc.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"),
						CustomerID, OpenSOPrefix);
				openSOFunc.fillSOTypeAndPONumberInOpenSO(OpenSOType, RandomStringUtils.randomNumeric(4));
				openSOFunc.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo(Today);
				openSOFunc.clickFetchCustomerAndSelectItemInOpenSO(Item);
				openSOFunc.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost, MRP, OpenSOHSN);
				openSOFunc.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
				openSOFunc.enterEffectiveFromAndToDate(Today, Today);
				openSOFunc.clickHeaderSaveAndClickPostBtnInOpenSO();
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenSONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenSONumber());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateOpenSO();
				openSOFunc.validateStatusOfOpenSOWithDocNo(VariableHandler.getOpenSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			}
			Cost = VariableHandler.getOpenSOPriceAmount();
			commonFunc.navigateSalesOrderPage();
			saleOrderFunc.verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
					CustomerID);
			saleOrderFunc.addItemsInSO(Item);
			saleOrderFunc.addLineItemQty(Unit);
			saleOrderFunc.chooseHsnCodeAndClickLineWiseSaveButton(OpenSOHSN);
			saleOrderFunc.checkSaleOrderTaxCalculationForLastAddedItem(Unit, Cost);
			saleOrderFunc.clickLineWiseDetailsButton();
			saleOrderFunc.switchToLineDetailFrameInSaleOrder();
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOSpecialDiscountAssblValue,
					SalesOrderLocators.SOSpecialDiscountpercentageField,
					SalesOrderLocators.SOSpecialDiscountAmountField, RandomStringUtils.randomNumeric(1));
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOCashDiscountAssblValue,
					SalesOrderLocators.SOCashDiscountpercentageField, SalesOrderLocators.SOCashDiscountAmountField,
					RandomStringUtils.randomNumeric(1));
			saleOrderFunc.clickLineWiseDetailsSaveButton();
			webDB.switchToDefaultContent();
			saleOrderFunc.getSalesOrderTotalAmount();
			saleOrderFunc.clickPostButton();
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getSONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				saleOrderFunc.validateSalesOrderDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateSalesInvoicePage();
				commonFunc.clickPendingIcon();
				saleInvoiceFunc.clickPendingSOAndSearchSONo(VariableHandler.getSONumber());
				saleInvoiceFunc.selectAllPendingSOAndClickCreateSI(VariableHandler.getSONumber());
				saleInvoiceFunc.verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
				saleInvoiceFunc.verifyHeaderTickIcon();
				saleInvoiceFunc.verifyItemFromPendingSaleOrderAndClickAllocLotSerial(Item);
				saleInvoiceFunc.validateLineAmountOfLastAddedItem();
				saleInvoiceFunc.checkSaleInvoiceTaxCalculationForLastAddedItem();
				saleInvoiceFunc.verifyLineWiseDetailsButton();
				saleInvoiceFunc.switchToLineDetailsIframeInSaleInvoice();
				commonFunc.checkDiscountInLineDetails(SalesInvoiceLocators.SalesInvoiceSpecialAssblPrice,
						SalesInvoiceLocators.SalesInvoiceSpecialDiscountPrice,
						SalesInvoiceLocators.SalesInvoiceSpecialAssblAmount, "2");
				commonFunc.checkDiscountInLineDetails(SalesInvoiceLocators.SalesInvoiceCashAssemblePrice,
						SalesInvoiceLocators.SalesInvoiceCashDiscountPrice,
						SalesInvoiceLocators.SalesInvoiceCashAssembleAmount, "1");
				saleInvoiceFunc.verifyShipFromWarehouseAndStockAndInvoiceQuantity(
						commonFunc.getDataFromTestDataSheet("FinishedGoodWH"));
				saleInvoiceFunc.verifyLineDetailsSaveIcon();
				webDB.switchToDefaultContent();
				saleInvoiceFunc.verifyPickButton();
				saleInvoiceFunc.verifyCreateInvoiceNo();
				saleInvoiceFunc.clickPostButton();
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
							"HodUsername");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
					approvalFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
							"TvsUsername");
					commonFunc.navigateToFinancialPaymentReceipt();
					paymentReceiptFunc.clickPendingDrAndPendingReceivables();
//					paymentReceiptFunc.chooseSaleInvoiceDocTypeWithInvoiceNoInFind(VariableHandler.getSIdocno());
					paymentReceiptFunc.clickSearchIconAndClickCreateWithInvoiceNo(VariableHandler.getSIdocno());
					paymentReceiptFunc.fillFormOfBankReceiptWithBankOrCashTypes(
							commonFunc.getDataFromTestDataSheet("SinglePaymentType"),
							commonFunc.getDataFromTestDataSheet("BankReceipt_BankType"),
							commonFunc.getDataFromTestDataSheet("NEFTText"),
							commonFunc.getDataFromTestDataSheet("Axisbank"));
					paymentReceiptFunc.enterRefNoAndSaveHeader("BR/Ref-" + VariableHandler.getSIdocno());
					paymentReceiptFunc.clickCreateJournalAndClickViewJournel();
					paymentReceiptFunc.verifyTotalDebitAndCreditEqualsAndCloseViewJournal();
					paymentReceiptFunc.verifyCreditInDrCrLinesColumnTotalTCAndTotalBC();
					paymentReceiptFunc.clickPostButtonInBankReceipts();
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
					flag = commonFunc.logoutUser();
					if (flag) {
						commonFunc.validCommonLogin(System.getProperty("HodUsername"),
								System.getProperty("HodPassword"), "HodUsername");
						approvalFunc.navigateToApprovals();
						approvalFunc.filterDocumentsforApproval(VariableHandler.getSIdocno());
						approvalFunc.verifyApproveWithDocNumber(VariableHandler.getSIdocno());
						flag = commonFunc.logoutUser();
					}
				}
			}
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occurs while executing 'verifySalesOrderToPaymentReceipts': " + e);
			return false;
		}
	}
}
