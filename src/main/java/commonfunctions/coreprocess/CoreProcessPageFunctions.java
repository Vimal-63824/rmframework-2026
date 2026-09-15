package commonfunctions.coreprocess;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.financials.PaymentReceiptsFunctions;
import commonfunctions.financials.PurchaseBillsFunctions;
import commonfunctions.productionplanning.BillOfMaterialsFunctions;
import commonfunctions.productionplanning.CompletionProductionOrderFunctions;
import commonfunctions.productionplanning.MRPFunctions;
import commonfunctions.productionplanning.ProductionOrderFunctions;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.CustomerItemsFunctions;
import commonfunctions.supplychainmanagement.DeliveryChallanFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.GrnScoPageFunctions;
import commonfunctions.supplychainmanagement.MaterialIssuanceVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
import commonfunctions.supplychainmanagement.OpenSalesOrderFunctions;
import commonfunctions.supplychainmanagement.PricelistPageFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonfunctions.supplychainmanagement.StockAdjustmentFunctions;
import commonfunctions.supplychainmanagement.StockTransactionPageFunctions;
import commonfunctions.supplychainmanagement.StocksPageFunctions;
import commonfunctions.supplychainmanagement.SubcontractOrderFunctions;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.OpenPoRcLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CoreProcessPageFunctions {
	private boolean flag = false;
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
	private static StocksPageFunctions stockPageFunc = new StocksPageFunctions();
	private static StockTransactionPageFunctions stockTransactionPageFunc = new StockTransactionPageFunctions();
	private static DeliveryChallanFunctions deliveryChallanFunc = new DeliveryChallanFunctions();
	private static BillOfMaterialsFunctions bomFunc = new BillOfMaterialsFunctions();
	private static PricelistPageFunctions priceListFunc = new PricelistPageFunctions();
	private static SubcontractOrderFunctions subContractOrderFunc = new SubcontractOrderFunctions();
	private static GrnScoPageFunctions grnScoPageFunc = new GrnScoPageFunctions();
	private static CustomerItemsFunctions customerItemFunc = new CustomerItemsFunctions();
	private static StockAdjustmentFunctions stockAdjustentFunc = new StockAdjustmentFunctions();

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
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
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
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.naviagteGrnPage();
			verifyValidateGrnStatus(VariableHandler.getGrnDocNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//			flag = commonFunc.logoutUser();

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
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header CheckBox Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
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
//		materialReceiptFunctions.verifyPostMaterialReceipt();
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
		LocalDate date2 = LocalDate.now().plusYears(2);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String EffTo = date2.format(formatter);
		String EffFrom = date.format(formatter);
		String CustomerID = commonFunc.getDataFromTestDataSheet("GalaxyCustomerId");
		String Unit = RandomStringUtils.randomNumeric(2);
		if (Unit.equals("00")) {
			Unit = "10";
		}
		String Cost = RandomStringUtils.randomNumeric(2);
		if (Cost.equals("00")) {
			Cost = "10";
		}
		String StockAdjQty = "10000";
		String StockAdjCost = String.valueOf((Integer.parseInt(Cost) - 5));
		String MRP = String.valueOf((Integer.parseInt(Cost) + 10));
		String OpenSOPrefix = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String OpenSOType = commonFunc.getDataFromTestDataSheet("Type_Standard");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("SK0001"), "Auto-CP-Item-");
			commonFunc.navigateItemAssoScreen();
			customerItemFunc.searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
			customerItemFunc.searchItemInFindPage();
			customerItemFunc.clickSearchButton();
			customerItemFunc.searchedItem();
			customerItemFunc.customerAssoFlag();
			customerItemFunc.changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
			customerItemFunc.clickSaveButton();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.navigateCustomerItemScreen();
			customerItemFunc.addPLusButton();
			customerItemFunc.insertCustomerFromLovField(CustomerID);
			customerItemFunc.addPlusInCustomerItem();
			customerItemFunc.insertIteminCustomerItemsAfterCustomerAssoFlag();
			customerItemFunc.priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
			customerItemFunc.priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
			commonFunc.checkRefreshCellsDisappear();
			customerItemFunc.clickDefaltFlag2();
			customerItemFunc.saveButtoninCustomerItems();
			commonFunc.navigateOpenSO();
			openSOFunc.selectOpenSOTypeWithRadioButtons("Open SO");
			openSOFunc.clickAddNewOpenSOPlsIcon();
			openSOFunc.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CustomerID,
					OpenSOPrefix);
			openSOFunc.fillSOTypeAndPONumberInOpenSO(OpenSOType, RandomStringUtils.randomNumeric(4));
			openSOFunc.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
			openSOFunc.clickFetchCustomerAndSelectItemInOpenSO(VariableHandler.getItemName());
			openSOFunc.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost, MRP, OpenSOHSN);
			openSOFunc.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
			openSOFunc.enterEffectiveFromAndToDate(EffFrom, EffTo, EffFrom);
			openSOFunc.clickHeaderSaveAndClickPostBtnInOpenSO();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getOpenSONumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateOpenSO();
			openSOFunc.selectOpenSOTypeWithRadioButtons("Open SO");
			openSOFunc.validateStatusOfOpenSOWithDocNo(VariableHandler.getOpenSONumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			commonFunc.navigateSalesOrderPage();
			saleOrderFunc.navigateToSalesOrderOrCustomerSchedule(webDB.getDataFromProperties("So"));
			saleOrderFunc.createSalesOrderWithHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
					CustomerID);
			saleOrderFunc.addItemsInSO(VariableHandler.getItemName());
			saleOrderFunc.addLineItemQty(Unit);
			saleOrderFunc.chooseHsnCodeAndClickLineWiseSaveButton(OpenSOHSN);
			saleOrderFunc.clickLineWiseSaveButton();
			saleOrderFunc.clickTotalDiscountForLineDetails();
			saleOrderFunc.switchToLineDetailFrameInSaleOrder();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOSpecialDiscountAssblValue,
					SalesOrderLocators.SOSpecialDiscountpercentageField,
					SalesOrderLocators.SOSpecialDiscountAmountField, RandomStringUtils.randomNumeric(1));
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOCashDiscountAssblValue,
					SalesOrderLocators.SOCashDiscountpercentageField, SalesOrderLocators.SOCashDiscountAmountField,
					RandomStringUtils.randomNumeric(1));
			commonFunc.checkRefreshCellsDisappear();
			saleOrderFunc.clickLineWiseDetailsSaveButton();
			webDB.switchToDefaultContent();
			saleOrderFunc.getSalesOrderTotalAmount();
			saleOrderFunc.clickPostButton();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getSONumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateStockAdjustmentScreen();
			stockAdjustentFunc.verifyStockAdjustmentOnlyForLotItem(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("FG WH"), commonFunc.getDataFromTestDataSheet("StkAdjPrefix"),
					VariableHandler.getItemName(), StockAdjQty, StockAdjCost);
			commonFunc.navigateSalesInvoicePage();
			commonFunc.clickPendingIcon();
			saleInvoiceFunc.clickPendingSOAndSearchSONo(VariableHandler.getSONumber());
			saleInvoiceFunc.selectAllPendingSOAndClickCreateSI(VariableHandler.getSONumber());
			saleInvoiceFunc.verifyInvoicePfx(commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
			saleInvoiceFunc.verifyHeaderTickIcon();
			saleInvoiceFunc.verifyItemFromPendingSaleOrderAndClickAllocLotSerial(VariableHandler.getItemName());
			saleInvoiceFunc.clickTotalDiscountForLineDetails();
			saleInvoiceFunc.switchToLineDetailsIframeInSaleInvoice();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkDiscountInLineDetails(SalesInvoiceLocators.SalesInvoiceSpecialAssblPrice,
					SalesInvoiceLocators.SalesInvoiceSpecialDiscountPrice,
					SalesInvoiceLocators.SalesInvoiceSpecialAssblAmount, "2");
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkDiscountInLineDetails(SalesInvoiceLocators.SalesInvoiceCashAssemblePrice,
					SalesInvoiceLocators.SalesInvoiceCashDiscountPrice,
					SalesInvoiceLocators.SalesInvoiceCashAssembleAmount, "1");
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			saleInvoiceFunc.verifyShipFromWarehouseAndStockAndInvoiceQuantity(
					commonFunc.getDataFromTestDataSheet("FinishedGoodWH"));
			saleInvoiceFunc.verifyLineDetailsSaveIcon();
			webDB.switchToDefaultContent();
			saleInvoiceFunc.verifyPickButton();
			saleInvoiceFunc.verifyCreateInvoiceNo();
			saleInvoiceFunc.clickPostButton();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getSIdocno(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToFinancialPaymentReceipt();
			paymentReceiptFunc.clickPendingDrAndPendingReceivables();
			paymentReceiptFunc.chooseSaleInvoiceDocTypeWithInvoiceNoInFind(VariableHandler.getSIdocno());
			paymentReceiptFunc.clickSearchIconAndClickCreateWithInvoiceNo(VariableHandler.getSIdocno());
			paymentReceiptFunc.fillFormOfBankReceiptWithBankOrCashTypes(
					commonFunc.getDataFromTestDataSheet("SinglePaymentType"),
					commonFunc.getDataFromTestDataSheet("BankReceipt_BankType"),
					commonFunc.getDataFromTestDataSheet("NEFTText"), commonFunc.getDataFromTestDataSheet("Axisbank"));
			paymentReceiptFunc.enterRefNoAndSaveHeader("BR/Ref-" + VariableHandler.getSIdocno());
			paymentReceiptFunc.clickCreateJournalAndClickViewJournel();
			paymentReceiptFunc.verifyTotalDebitAndCreditEqualsAndCloseViewJournal();
			paymentReceiptFunc.verifyCreditInDrCrLinesColumnTotalTCAndTotalBC();
			paymentReceiptFunc.clickPostButtonInBankReceipts();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getBankReceiptVouNo(), webDB.getDataFromProperties("HariEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			paymentReceiptFunc.verifyPaymentReceiptApprovalStatus();
			flag = commonFunc.logoutUser();
			return true;
		} catch (

		Exception e) {
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
			String CustomerID = commonFunc.getDataFromTestDataSheet("CustomerName");
			VariableHandler.setItemName(commonFunc.getDataFromTestDataSheet("NewItem"));
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
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateSalesOrderPage();
			saleOrderFunc.navigateToSalesOrderOrCustomerSchedule(webDB.getDataFromProperties("So"));
			saleOrderFunc.createSalesOrderWithHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
					CustomerID);
			saleOrderFunc.addItemsInSO(VariableHandler.getItemName());
			saleOrderFunc.addQuantityAndUnitCost(Qnty, Cost);
			saleOrderFunc.chooseHsnCodeAndClickLineWiseSaveButton(OpenSOHSN);
			saleOrderFunc.clickLineWiseSaveButton();
			saleOrderFunc.clickTotalDiscountForLineDetails();
			saleOrderFunc.switchToLineDetailFrameInSaleOrder();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOSpecialDiscountAssblValue,
					SalesOrderLocators.SOSpecialDiscountpercentageField,
					SalesOrderLocators.SOSpecialDiscountAmountField, RandomStringUtils.randomNumeric(1));
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOCashDiscountAssblValue,
					SalesOrderLocators.SOCashDiscountpercentageField, SalesOrderLocators.SOCashDiscountAmountField,
					RandomStringUtils.randomNumeric(1));
			commonFunc.checkRefreshCellsDisappear();
			saleOrderFunc.clickLineWiseDetailsSaveButton();
			webDB.switchToDefaultContent();
			saleOrderFunc.getSalesOrderTotalAmount();
			saleOrderFunc.clickPostButton();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getSONumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
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
			mrpFunc.verifyItemResultsDetails(new String[] { VariableHandler.getItemName(), RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
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
			LocalDate previousDate = LocalDate.now().minusDays(1);
			String YesterDay = previousDate.format(formatter);
			LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
			String NextMonthToday = nextmonthdate.format(formatter);
			String CustomerID = commonFunc.getDataFromTestDataSheet("CustomerName");
			VariableHandler.setItemName(commonFunc.getDataFromTestDataSheet("NewItem"));
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
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateSalesOrderPage();
			saleOrderFunc.navigateToSalesOrderOrCustomerSchedule(webDB.getDataFromProperties("So"));
			saleOrderFunc.createSalesOrderWithHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
					CustomerID);
			saleOrderFunc.addItemsInSO(VariableHandler.getItemName());
			saleOrderFunc.addQuantityAndUnitCost(Qnty, Cost);
			saleOrderFunc.chooseHsnCodeAndClickLineWiseSaveButton(OpenSOHSN);
			saleOrderFunc.clickLineWiseSaveButton();
			saleOrderFunc.clickTotalDiscountForLineDetails();
			saleOrderFunc.switchToLineDetailFrameInSaleOrder();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOSpecialDiscountAssblValue,
					SalesOrderLocators.SOSpecialDiscountpercentageField,
					SalesOrderLocators.SOSpecialDiscountAmountField, RandomStringUtils.randomNumeric(1));
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOCashDiscountAssblValue,
					SalesOrderLocators.SOCashDiscountpercentageField, SalesOrderLocators.SOCashDiscountAmountField,
					RandomStringUtils.randomNumeric(1));
			commonFunc.checkRefreshCellsDisappear();
			saleOrderFunc.clickLineWiseDetailsSaveButton();
			webDB.switchToDefaultContent();
			saleOrderFunc.getSalesOrderTotalAmount();
			saleOrderFunc.clickPostButton();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getSONumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
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
			mrpFunc.verifyItemResultsDetails(new String[] { VariableHandler.getItemName(), RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
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
			productionOrderFunc.verifyProductionOrderMREventAndRestOP(null, null,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			productionOrderFunc.verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			productionOrderFunc.verifyStoreProcessAndRawMaterial();
			productionOrderFunc.navigateToRoutingSubTab();
			productionOrderFunc.storeRoutingProcessAndProcessType();
			productionOrderFunc.storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			productionOrderFunc.clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HariEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickCheckBoxTableAndCreateAndStoreMRNo(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occurs while executing 'verifyProductionOrderToCompletionToMRV': " + e);
			return false;
		}
	}

	/**
	 * This Method Used To Verify Material Request To Material Receipt Voucher**
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyMaterialRequestToMaterialReceiptVoucherIntraday() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTab();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			stockPageFunc.getItemStockValue(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
			stockPageFunc.getItemStockValue(itemmultiple[i][0], commonFunc.getDataFromTestDataSheet("FGWareHouse"));
			stockPageFunc.removeFilterStock();
			System.out.println(itemmultiple[i][0] + " " + itemmultiple[i][1]);
			stockPageFunc.getItemStockCost(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
		}
		commonFunc.logoutUser();
		materialRequestFunc.verifyCreateMaterialRequest();
		materialIssuanceVoucherFunc.createMaterialIssuanceVoucher();
		materialReceiptFunctions.verifyPostMaterialRequestFromMiv();
	}

	/**
	 * This Method Used To Verify Material Request To Material Receipt Voucher Inter
	 * Transfer
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyMaterialRequestToMaterialReceiptVoucherInterday() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTab();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			stockPageFunc.getItemStockValue(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
			stockPageFunc.getItemStockValue(itemmultiple[i][0], commonFunc.getDataFromTestDataSheet("EmpWareHouseId"));
			stockPageFunc.removeFilterStock();
			stockPageFunc.getItemStockCost(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
		}
		commonFunc.logoutUser();
		materialRequestFunc.verifyCreateMaterialRequestIntertype();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToMaterialIssuanceVoucher();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
//		materialIssuanceVoucherFunc.createJournalAndViewJournal();
//		materialIssuanceVoucherFunc.verifyTableDataMiv();
		materialIssuanceVoucherFunc.headerValidationMiv(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("EmpWareHouse"),
				commonFunc.getDataFromTestDataSheet("Inter Transfer"));
		materialIssuanceVoucherFunc.headerTickAndPostIcon();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.logoutUser();
		deliveryChallanFunc.verifyDeliveryChallan();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSetupInventoryPage();
		boolean createflag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		boolean postflag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
//		commonFunc.navigateToMaterialReceiptVoucher();
//		if (createflag && !postflag) {
//			materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getMIVNumber());
//			materialReceiptFunctions.createJournalAndViewJournal();
//			materialReceiptFunctions.verifyTableDataMiv(webDB.getDataFromProperties("MRTMRVSheet"));
//			materialReceiptFunctions.clickTickAndPostIcon();
//		} else if (!createflag && !postflag) {
//			materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
//			materialReceiptFunctions.createJournalAndViewJournal();
//			materialReceiptFunctions.verifyTableDataMiv(webDB.getDataFromProperties("MRTMRVSheet"));
//			materialReceiptFunctions.clickTickAndPostIcon();
//		} else if (createflag && postflag) {
//
//		} else if (!createflag && postflag) {
//			materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
//			commonFunc.logoutUser();
//			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//					"AdminUsername");
//			commonFunc.navigateToMaterialReceiptVoucher();
//		}
//		materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
//				commonFunc.getDataFromTestDataSheet("IssuedStatus"));
//		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTab();
		materialReceiptFunctions.verifyAfterStockDataUpdation(commonFunc.getDataFromTestDataSheet("EmpWareHouseId"),
				webDB.getDataFromProperties("MRTMRVSheet"));
		commonFunc.logoutUser();
		stockPageFunc.validateStockCount(webDB.getDataFromProperties("MRTMRVSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTransactionTab();
		stockTransactionPageFunc.verifyMRVStockTransaction(commonFunc.getDataFromTestDataSheet("EmpWareHouseId"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Used To Verify Material Request To Material Receipt Voucher Inter
	 * Transfer
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyMaterialRequestToMaterialReceiptVoucherDepartmentType() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTab();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			stockPageFunc.getItemStockValue(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
			stockPageFunc.getItemStockCost(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
		}
		commonFunc.logoutUser();
		materialRequestFunc.verifyCreateMaterialRequestDepartmenttype();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToMaterialIssuanceVoucher();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
//		materialIssuanceVoucherFunc.createJournalAndViewJournal();
//		materialIssuanceVoucherFunc.verifyTableDataMiv();
		materialIssuanceVoucherFunc.headerValidationMiv(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Production100"),
				commonFunc.getDataFromTestDataSheet("DepartmentValue"));
		materialIssuanceVoucherFunc.headerTickAndPostIcon();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTransactionTab();
		stockTransactionPageFunc.verifyStockTransactionWithDocNumber(commonFunc.getDataFromTestDataSheet("Prod100"),
				VariableHandler.getMIVNumber());
		commonFunc.logoutUser();
	}

	/**
	 * This Method Used To Verify Material Request To Material Receipt Voucher Inter
	 * Transfer
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyMaterialRequestToMaterialReceiptVoucherWipType() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTab();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRToMRVWipSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			stockPageFunc.getItemStockValue(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
			stockPageFunc.getItemStockValue(itemmultiple[i][0], commonFunc.getDataFromTestDataSheet("WIP100"));
			stockPageFunc.removeFilterStock();
			stockPageFunc.getItemStockCost(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
		}
		commonFunc.logoutUser();
		materialRequestFunc.verifyCreateMaterialRequestWipType();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToMaterialIssuanceVoucher();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
		for (int i = 1; i < itemmultiple.length; i++) {
			materialIssuanceVoucherFunc.clickLineDetailsIconWithItemName(itemmultiple[i][0]);
			materialIssuanceVoucherFunc
					.selectMachineInMIVLineDetails(commonFunc.getDataFromTestDataSheet("Machines100"));
		}
		materialIssuanceVoucherFunc.headerValidationMiv(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WorkInProgressWH"),
				commonFunc.getDataFromTestDataSheet("WipType"));
		materialIssuanceVoucherFunc.headerTickAndPostIcon();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
//		commonFunc.navigateToMaterialReceiptVoucher();
//		materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
//		materialReceiptFunctions.createJournalAndViewJournal();
//		materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
//		materialReceiptFunctions.verifyTableDataMiv(webDB.getDataFromProperties("MRToMRVWipSheet"));
//		materialReceiptFunctions.clickTickAndPostIcon();
//		materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
//				commonFunc.getDataFromTestDataSheet("IssuedStatus"));
//		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTab();
		materialReceiptFunctions.verifyAfterStockDataUpdation(commonFunc.getDataFromTestDataSheet("WIP100"),
				webDB.getDataFromProperties("MRToMRVWipSheet"));
		commonFunc.logoutUser();
		stockPageFunc.validateStockCount(webDB.getDataFromProperties("MRToMRVWipSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTransactionTab();
		stockTransactionPageFunc.verifyMRVStockTransaction(commonFunc.getDataFromTestDataSheet("WIP100"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Used To Verify Material Request To Material Receipt Voucher
	 * SubContract
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyMaterialRequestToMaterialReceiptVoucherSubContract() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTab();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRToMRVWipSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			stockPageFunc.getItemStockValue(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
			stockPageFunc.getItemStockCost(itemmultiple[i][0], itemmultiple[i][1]);
			stockPageFunc.removeFilterStock();
		}
		commonFunc.logoutUser();
		materialRequestFunc.verifyCreateMaterialRequestSubcontractor();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToMaterialIssuanceVoucher();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
//		materialIssuanceVoucherFunc.createJournalAndViewJournal();
//		materialIssuanceVoucherFunc.verifyTableDataMivForWip();
//		materialIssuanceVoucherFunc.headerValidationMiv(commonFunc.getDataFromTestDataSheet("Loc_100"),
//				commonFunc.getDataFromTestDataSheet("POSupplierName"),
//				commonFunc.getDataFromTestDataSheet("Subcontractor"));
		materialIssuanceVoucherFunc.headerTickAndPostIcon();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateToGateEntryDeliveryChallan();
		deliveryChallanFunc.verifyTableData(webDB.getDataFromProperties("MRToMRVWipSheet"));
		deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTransactionTab();
		VariableHandler.setMIVNumber("");
		stockTransactionPageFunc.verifyDocumentNoStockTransaction(commonFunc.getDataFromTestDataSheet("POSupplierName"),
				VariableHandler.getMIVNumber());
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Subcontract To Payment
	 * 
	 * @author Deepan-GWL
	 */
	public void subcontractToPayment() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		productionOrderFunc.clickAddIconInCreationProductionOrder();
		productionOrderFunc.createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("StandardProd"), commonFunc.getDataFromTestDataSheet("Pro"),
				commonFunc.getDataFromTestDataSheet("Manufacturing"), commonFunc.getDataFromTestDataSheet("ScoItem"),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		productionOrderFunc.clickSaveIconInCreationProductionOrder();
		productionOrderFunc.verifySelectOrderedReleasesInPO();
		productionOrderFunc.clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
		}
		productionOrderFunc.searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		commonFunc.logoutUser();
		for (String process : VariableHandler.getTotalProductionProcess()) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToSubcontractOrder();
			subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
			subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
					commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SubcontractorName"));
			subContractOrderFunc.verifyMRPDetailsVerification(commonFunc.getDataFromTestDataSheet("RawMaterialSco"),
					commonFunc.getDataFromTestDataSheet("UomNos"), process);
//			subContractOrderFunc.verifyDetailsValidation(commonFunc.getDataFromTestDataSheet("Source" + process),
//					commonFunc.getDataFromTestDataSheet("Target" + process), process);
			subContractOrderFunc.verifyProcessQuantityCalculation();
			subContractOrderFunc.verifyAddGlAccNumberValue();
			subContractOrderFunc.verifyIncludeChargesAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
				String Text = VariableHandler.getLastApprovalAPIHeaderText().replace("Material Request Created. :", "")
						.trim();
				Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
				VariableHandler.setMRNumber(Text);
				VariableHandler.setMaterialRequestNo(Text);
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToSubcontractOrder();
			}
			subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToInventoryMaterialRequest();
			materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMaterialRequestNo());
			materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
			materialRequestFunc.headerSaveAndPost();
			purchaseBillsFunc.verifyWaitingForApproval();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMaterialRequestNo(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToInventoryMaterialRequest();
			materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMaterialRequestNo(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMaterialIssuanceVoucher();
			materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
			materialIssuanceVoucherFunc.headerTickAndPostIcon();
			materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
					commonFunc.getDataFromTestDataSheet("PostedStatus"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToGateEntryDeliveryChallan();
			deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
			grnScoPageFunc.checkConsumptionBeforeGenerationMat();
			grnScoPageFunc.verifyGenMatConsAndAllocate();
			grnScoPageFunc.checkConsumptionAfterGenerationMat();
			grnScoPageFunc.verifyViewLineDetails();
			grnScoPageFunc.saveHeaderAndPost();
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoGrnNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToGrnSco();
			grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateSetupInventoryPage();
			flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
			commonFunc.logoutUser();
//			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//					"TvsUsername");
//			commonFunc.navigateToMaterialReceiptVoucher();
//			if (flag) {
//				materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getScoGrnNumber());
//			} else {
//				commonFunc.clickPendingIcon();
//				materialReceiptFunctions.clickPendingInssuanceBtn();
//				materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getScoGrnNumber());
//				materialReceiptFunctions.selectVoucherNoAndClickCreateReceipt(VariableHandler.getScoGrnNumber());
//			}
//			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
//			materialReceiptFunctions.clickTickAndPostIcon();
//			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
//					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
//			commonFunc.logoutUser();

			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPurchaseBill();
			purchaseBillsFunc.verifyPendingScoPurchaseBill(VariableHandler.getScoGrnNumber());
			purchaseBillsFunc.verifyCreateJournalAndPost();
			purchaseBillsFunc.verifyWaitingForApproval();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoPurchaseNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToFinancialPurchaseBill();
				purchaseBillsFunc.verifyPurchaseBillApprovalStatus(VariableHandler.getScoPurchaseNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			}
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToFinancialPaymentReceipt();
			paymentReceiptFunc.verifyPendingCrDocs(VariableHandler.getScoPurchaseNumber());
			paymentReceiptFunc.clickTickAndPostIcon();
			paymentReceiptFunc.verifyWaitingForApproval();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBPvNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToFinancialPaymentReceipt();
				paymentReceiptFunc.verifyPaymentReceiptApprovalStatus();
				flag = commonFunc.logoutUser();
			}
		}
	}

	/**
	 * This Method used to verify Creation Of Bill Of Materials ETE Flow
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyCreationOfBillOfMaterials() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			bomFunc.enterEffFromToDateAndSaveHeader(Today, Today);
			bomFunc.navigateToRoutingSubTab();
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1",
					commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("2", "2",
					commonFunc.getDataFromTestDataSheet("GwlProcess2"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.navigateToMaterialsSubTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.navigateToBORSubTab();
			bomFunc.clickAddLineIconInBORTab();
			bomFunc.fillLineSnoAndProcessIdInBORTab("1", commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"));
			bomFunc.fillResourceTypeAndResourceInBORTab(commonFunc.getDataFromTestDataSheet("ResourceText"),
					commonFunc.getDataFromTestDataSheet("AutomationResource"));
			bomFunc.fillSetupHrsMinAndSetupLotQntyInBORTab("0", "15", "0");
			bomFunc.fillCycleTimeUnitOrHrsMinAndSecInBORTab("3", "0", "0", "0");
			bomFunc.fillCycleTimeBatchQntyAndHrsInBORTab("0", "0");
			bomFunc.fillLagPriorityAndNoOfUnitsInBORTab("0", "1", "3");
			bomFunc.clickSaveLineIconInBORTab();
			bomFunc.clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			bomFunc.navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			bomFunc.chooseBOMTypeAndSaveHeader(commonFunc.getDataFromTestDataSheet("PrimaryType"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.clickAddIconInCreationProductionOrder();
			productionOrderFunc.createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(), "3");
			productionOrderFunc.clickSaveIconInCreationProductionOrder();
			productionOrderFunc.verifyProductionOrderMREventAndRestOP(null, null,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			productionOrderFunc.verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			productionOrderFunc.verifyStoreProcessAndRawMaterial();
			productionOrderFunc.navigateToRoutingSubTab();
			productionOrderFunc.storeRoutingProcessAndProcessType();
			productionOrderFunc.storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			productionOrderFunc.clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HariEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickCheckBoxTableAndCreateAndStoreMRNo(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occurs while executing 'creationOfBillOfMaterials': " + e);
			return false;
		}
	}
}
