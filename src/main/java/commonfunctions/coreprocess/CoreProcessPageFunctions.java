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
import commonfunctions.productionplanning.CompletionProductionOrderFunctions;
import commonfunctions.productionplanning.MRPFunctions;
import commonfunctions.productionplanning.ProductionOrderFunctions;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialIssuanceVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
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
	private static MRPFunctions mrpFunc = new MRPFunctions();
	private static ProductionOrderFunctions productionOrderFunc = new ProductionOrderFunctions();
	private static CompletionProductionOrderFunctions completionProductionOrderFunc = new CompletionProductionOrderFunctions();
	private static MaterialRequestFunctions materialRequestFunc = new MaterialRequestFunctions();
	private static MaterialIssuanceVoucherFunctions materialIssuanceVoucherFunc = new MaterialIssuanceVoucherFunctions();

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
		grnPageFunctions.clickPendingPoAndSearchPoNo();
		grnPageFunctions.clickSelectAllButton();
		grnPageFunctions.createGrn();
		grnPageFunctions.supplierBillNumberAndDcNumber();
		grnPageFunctions.AddLotNumber(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("LotNo"));
		grnPageFunctions.overAllSaveAndPost();
		grnPageFunctions.waitingForApproval();
		if (flag) {
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
					"SelvaUsername");
			commonFunc.naviagteGrnPage();
			verifyValidateGrnStatus(VariableHandler.getGrnDocNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();

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
		if (Unit.equals("00")) {
			Unit = "10";
		}
		String Cost = RandomStringUtils.randomNumeric(2);
		if (Cost.equals("00")) {
			Cost = "10";
		}
		String MRP = String.valueOf((Integer.parseInt(Cost) + 10));
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
				openSOFunc.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
				openSOFunc.clickFetchCustomerAndSelectItemInOpenSO(Item);
				openSOFunc.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost, MRP, OpenSOHSN);
				openSOFunc.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
				openSOFunc.enterEffectiveFromAndToDate(Today, Today, Today);
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
			saleOrderFunc.checkSaleOrderTaxCalculationForLastAddedItem();
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
				saleOrderFunc.validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
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
					commonFunc.navigateSalesInvoicePage();
					saleInvoiceFunc.validateSalesInvoiceDocumentStatus(VariableHandler.getSIdocno(),
							commonFunc.getDataFromTestDataSheet("InvoicedStatus"));
					commonFunc.navigateToFinancialPaymentReceipt();
					paymentReceiptFunc.clickPendingDrAndPendingReceivables();
					paymentReceiptFunc.chooseSaleInvoiceDocTypeWithInvoiceNoInFind(VariableHandler.getSIdocno());
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
						approvalFunc.filterDocumentsforApproval(VariableHandler.getBankReceiptVouNo());
						approvalFunc.verifyApproveWithDocNumber(VariableHandler.getBankReceiptVouNo());
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

	/**
	 * This Method used to verify Sale Order to MRP ETE Flow
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifySaleOrderToMRP() {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.now();
			String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
			String Today = date.format(formatter);
			LocalDate previousDate = LocalDate.now().minusDays(1);
			String YesterDay = previousDate.format(formatter);
			LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
			String NextMonthToday = nextmonthdate.format(formatter);
			String CustomerID = commonFunc.getDataFromTestDataSheet("Customer");
			String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
			String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
			String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
			String Qnty = RandomStringUtils.randomNumeric(2);
			if (Qnty.equals("00")) {
				Qnty = "10";
			}
			String Cost = RandomStringUtils.randomNumeric(2);
			if (Cost.equals("00")) {
				Cost = "10";
			}
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateSalesOrderPage();
			saleOrderFunc.verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
					CustomerID);
			saleOrderFunc.addItemsInSO(FGItem);
			saleOrderFunc.addQuantityAndUnitCost(Qnty, Cost);
			saleOrderFunc.chooseHsnCodeAndClickLineWiseSaveButton(OpenSOHSN);
			saleOrderFunc.checkSaleOrderTaxCalculationForLastAddedItem();
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
				saleOrderFunc.validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToMRPScreenInProdPlanning();
				mrpFunc.clickAddIconInMRP();
				mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
				mrpFunc.clickAddLinePlusIconInSources();
				mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"),
						NextMonthToday);
				mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
				mrpFunc.clickSaveIconInSourcesTab();
				mrpFunc.clickLoadDemandButtonInMRP();
				mrpFunc.navigateToDemandTabInMRPScreen();
				mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
				mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
				mrpFunc.navigateToExceptionTabInMRPScreen();
				mrpFunc.validateNoExceptionDataShownInTableData();
				mrpFunc.clickRunMRPButton();
				mrpFunc.navigateToResultsTabInMRPScreen();
				mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("Unit_100") });
				mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
				mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
						commonFunc.getDataFromTestDataSheet("Kgs") });
//				mrpFunc.validateCalculationOfRawMaterialQuantity(Qnty, "0.432", RMItem);
				mrpFunc.clickHeaderSaveAndClickPost();
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRPNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRPNumber());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMRPScreenInProdPlanning();
				mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
				mrpFunc.clickReleaseButtonInMRP();
				mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
				mrpFunc.clickReleaseButtonInReleaseFrame();
				mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
						commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
				commonFunc.navigateToProductionOrderInProdPlanning();
				productionOrderFunc.searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
						commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
				flag = commonFunc.logoutUser();
				VariableHandler.setSaleOrderToMRPExecution(flag);
			}
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occurs while executing 'verifySaleOrderToMRP': " + e);
			return false;
		}
	}

	/**
	 * This Method used to verify Production Order to Completion to Material Receipt
	 * Voucher ETE Flow
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyProductionOrderToCompletionToMaterialReceiptVoucher() {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.now();
			String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
			String Today = date.format(formatter);
			LocalDate previousDate = LocalDate.now().minusDays(2);
			String YesterDay = previousDate.format(formatter);
			LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
			String NextMonthToday = nextmonthdate.format(formatter);
			String CustomerID = commonFunc.getDataFromTestDataSheet("Customer");
			String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
			String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
			String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
			String Qnty = RandomStringUtils.randomNumeric(2);
			if (Qnty.equals("00")) {
				Qnty = "10";
			}
			String Cost = RandomStringUtils.randomNumeric(2);
			if (Cost.equals("00")) {
				Cost = "10";
			}
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateSalesOrderPage();
			saleOrderFunc.verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
					CustomerID);
			saleOrderFunc.addItemsInSO(FGItem);
			saleOrderFunc.addQuantityAndUnitCost(Qnty, Cost);
			saleOrderFunc.chooseHsnCodeAndClickLineWiseSaveButton(OpenSOHSN);
			saleOrderFunc.checkSaleOrderTaxCalculationForLastAddedItem();
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
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateSalesOrderPage();
			saleOrderFunc.validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getMRPNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRPNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			productionOrderFunc.searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			productionOrderFunc.navigateToRoutingSubTab();
			productionOrderFunc.storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			productionOrderFunc.navigateToMaterialRequirementSubTab();
			productionOrderFunc.verifyItemInMaterialRequirement(new String[] { RMItem });
			productionOrderFunc.verifyProcessInMaterialRequirement(
					new String[] { commonFunc.getDataFromTestDataSheet("MouldingProcess") });
			productionOrderFunc.navigateToResourcesSubTab();
			productionOrderFunc
					.verifyProcessInResources(new String[] { commonFunc.getDataFromTestDataSheet("MouldingProcess"),
							commonFunc.getDataFromTestDataSheet("MouldingProcess") });
			productionOrderFunc.navigateToExceptionSubTab();
			productionOrderFunc.validateNoExceptionsShowsInExceptionTab();
			productionOrderFunc.clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
						"HodUsername");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToProductionOrderInProdPlanning();
			}
			webDB.navigateToRefresh();
			commonFunc.navigateToInventoryMaterialRequest();
			materialRequestFunc.validateItemWHReqNameQuantityWithProdNo(VariableHandler.getProductionOrderNumber(),
					RMItem, commonFunc.getDataFromTestDataSheet("RMU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			materialRequestFunc.storeMRNoAndCheckStatusOfMRWithProdNo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			commonFunc.clickPendingIcon();
			materialIssuanceVoucherFunc.searchParticularDocumentWithMRNo(VariableHandler.getMaterialRequestNo());
			materialIssuanceVoucherFunc.validateItemWHProdNoFGItemWithMRNo(VariableHandler.getMaterialRequestNo(),
					RMItem, "RAW MATERIAL WH U100", VariableHandler.getProductionOrderNumber(), FGItem);
			materialIssuanceVoucherFunc.clickCheckBoxWithMRNoAndCreateMI(VariableHandler.getMaterialRequestNo());
			materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(
					VariableHandler.getMaterialIssuanceVoucherNo(),
					commonFunc.getDataFromTestDataSheet("PostedStatus"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateToMaterialReceiptVoucher();
			commonFunc.clickPendingIcon();
			materialReceiptFunctions.clickPendingInssuanceBtn();
			materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getMaterialIssuanceVoucherNo());
			materialReceiptFunctions
					.selectVoucherNoAndClickCreateReceipt(VariableHandler.getMaterialIssuanceVoucherNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndWarehousesBOM()[i][0];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				logs.logging("INFO", "ProcessName: " + ProcessName);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.searchProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.validateItemAndProcessInPendingProdOrderTable(FGItem, ProcessName);
				completionProductionOrderFunc.selectPendingProdOrderAndClickCreateCompletionProcess(
						VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.validateProcessConsumptionWarehouse(ProcessName, RecipientWH);
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateToMaterialReceiptVoucher();
				commonFunc.clickPendingIcon();
				materialReceiptFunctions.clickPendingInssuanceBtn();
				materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.validateProcessRecipientWarehouseWithCompletionProdNo(
						VariableHandler.getProductionCompletionOrderNo(), ProcessName, ConsumptionWH);
				materialReceiptFunctions
						.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occurs while executing 'verifyProductionOrderToCompletionToMRV': " + e);
			return false;
		}
	}
}
