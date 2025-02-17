package commonfunctions.coreprocess;
/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.financials.PaymentReceiptsFunctions;
import commonfunctions.financials.PurchaseBillsFunctions;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonlocators.supplychainmanagement.GrnLocators;
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

}
