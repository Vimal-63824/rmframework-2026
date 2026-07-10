package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks December 23, 2025
 */
import static org.testng.Assert.assertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.testng.Assert;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import commonlocators.supplychainmanagement.SupplierScheduleRequestLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SupplierScheduleRequestFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private PricelistPageFunctions pricelistPageFunctions = new PricelistPageFunctions();
	private OpenPoRcFunctions openPoRcFunc = new OpenPoRcFunctions();
	private ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	private SupplierScheduleOrderFunctions supplierScheduleOrderFunc = new SupplierScheduleOrderFunctions();
	private GateEntryPageFunctions gateEntryPageFunc = new GateEntryPageFunctions();
	private GrnPageFunctions grnPageFunc = new GrnPageFunctions();

	/**
	 * verify Create Po To MRV General RateContract Same UOM WithOutQuaity With
	 * GateEntry
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreatePoToMRVGeneralRateContractSameUOMWithOutQuaityWithGateEntry() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		openPoRcFunc.verifyClickOpenPORcButton();
		openPoRcFunc.verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		openPoRcFunc.verifyAddItemInOpenPOLine(VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		openPoRcFunc.verifyAddAggrDateAndAggrNumber();
		openPoRcFunc.verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			openPoRcFunc.verifyClickOpenPORcButton();
		}
		openPoRcFunc.verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		verifySSRCreateAndPostDocuments();
		supplierScheduleOrderFunc
				.verifyMovePendingAndPostDocuments(commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSSODocNumber();
		gateEntryPageFunc.verifygateEntryDetailsPoWithSSO();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		grnPageFunc.verifyAddItemNameInSheet(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		grnPageFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		grnPageFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.naviagteGrnPage();
		grnPageFunc.clickPendingGEAndSearchGENo();
		grnPageFunc.clickSelectAllGEButtonWithSt();
		grnPageFunc.createGrnWithGeButton();
		grnPageFunc.clickOkButton();
		grnPageFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		grnPageFunc.lotSerialQtyApportion();
		grnPageFunc.overAllSaveAndPostWithOutValidation(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		grnPageFunc.waitingForApproval();
		grnPageFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		grnPageFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		grnPageFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"), webDB.getDataFromProperties("PoToMrvSheet"));

	}

	/**
	 * This Method used to click SSR create icon - left add icon
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSSRCreateIcon() {
		flag = webDB.waitForElement(CommonUiLocators.PrCreateButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PrCreateButton, ElementType.Xpath);
			commonFunc.hardWait(4000);
			flag = webDB.verifyIsElementDisplayed(SupplierScheduleRequestLocators.CreateSSR, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SupplierScheduleRequestLocators.CreateSSR, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			webDB.switchToFrame(SupplierScheduleRequestLocators.SSRIframe, ElementType.Xpath);
			flag = webDB.waitForElement(SupplierScheduleRequestLocators.TickIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Left Side Add Icon is Displayed and Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Create SSR
	 * 
	 * @author Deepan-GWL
	 * @param location
	 *
	 */
	public void clickAddIconFillDetailsOfSSRequest(String Location, String WareHouse, String Reference) {
		commonFunc.searchAndSelectValueInFilters(SupplierScheduleRequestLocators.LocId, Location);
		webDB.switchToFrame(SupplierScheduleRequestLocators.SSRIframe, ElementType.Xpath);
		flag = webDB.waitForElement(SupplierScheduleRequestLocators.SelectWareHouse, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(SupplierScheduleRequestLocators.SelectWareHouse, WareHouse, ElementType.Xpath);
			webDB.sendTextToAnElement(SupplierScheduleRequestLocators.RefInput, Reference, ElementType.Xpath);
			flag = webDB.waitForElement(SupplierScheduleRequestLocators.TickIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SupplierScheduleRequestLocators.TickIcon, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SupplierScheduleRequestLocators.AddItemButton, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Add Item In Table
	 * 
	 * @param Quantity
	 * @param FromDate
	 * @param ToDate
	 */
	public void addItemInTable(String Quantity, String FromDate, String ToDate) {
		flag = webDB.waitForElement(SupplierScheduleRequestLocators.AddItemButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SupplierScheduleRequestLocators.AddItemButton, ElementType.Xpath);
			flag = webDB.waitForElement(SupplierScheduleRequestLocators.ItemTable, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SupplierScheduleRequestLocators.ItemTable, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(SupplierScheduleRequestLocators.ItemLovButton,
						VariableHandler.getItemName());
				commonFunc.verifyAddTableSpecs(SupplierScheduleRequestLocators.FirmQuantity,
						SupplierScheduleRequestLocators.FirmQtyInput, Quantity);
				commonFunc.verifyAddTableSpecs(SupplierScheduleRequestLocators.FromDate,
						SupplierScheduleRequestLocators.FromDateInput, FromDate);
				commonFunc.verifyAddTableSpecs(SupplierScheduleRequestLocators.ToDate,
						SupplierScheduleRequestLocators.ToDateInput, ToDate);

			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Tick Icon Create Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTickIconcreateItem() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SupplierScheduleRequestLocators.LineSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SupplierScheduleRequestLocators.LineSaveButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.verifyIsElementDisplayed(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath)) {
				logs.logging("INFO", "Item Tick Icon Is Clicked");
				webDB.clickAnElementWithoutException(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				logs.logging("INFO", "Toast Close Icon Is Clicked");
			}
			flag = webDB.waitForElement(SupplierScheduleRequestLocators.HeaderSaveButton, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Click Post Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickPostIcon() {
		flag = webDB.waitForElement(SupplierScheduleRequestLocators.HeaderSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SupplierScheduleRequestLocators.HeaderSaveButton, ElementType.Xpath);
			webDB.clickAnElement(SupplierScheduleRequestLocators.HeaderSaveButton, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			commonFunc.hardWait(2000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForElement(SupplierScheduleRequestLocators.HeaderPostButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SupplierScheduleRequestLocators.HeaderPostButton, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify SSR Create And Post Documents
	 * 
	 * @author Deepan-GWL
	 */
	public void verifySSRCreateAndPostDocuments() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePurchaseRequestPage();
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		clickSSRCreateIcon();
		clickAddIconFillDetailsOfSSRequest(commonFunc.getDataFromTestDataSheet("Location49"),
				commonFunc.getDataFromTestDataSheet("RAW MATERIAL WH -49-4"),
				commonFunc.getDataFromTestDataSheet("RAW MATERIAL WH -49-4"));
		getSSRDocNumebr();
		addItemInTable(commonFunc.getDataFromTestDataSheet("QuantityValue"), today.format(formatter),
				today.format(formatter));
		verifyTickIconcreateItem();
		verifyClickPostIcon();
		verifyWaitingForApproval();
//		flag = commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getSSRDocNumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
				System.getProperty("HariUsername"));
		searchPRAndCheckSSRStatus(VariableHandler.getSSRDocNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
	}

	/**
	 * Verify Waiting For Approval
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyWaitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval,
					commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CommonUiLocators.PurchaseRequestAddIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To PO Home Page");
				}
			}
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * Get SSR Documents Number
	 * 
	 * @author Deepan-GWL
	 */
	public void getSSRDocNumebr() {
		flag = webDB.waitForElement(SupplierScheduleRequestLocators.SSRDocumentNumber, ElementType.Xpath);
		if (flag) {
			String DocumentsNumber = webDB.getTextFromElement(SupplierScheduleRequestLocators.SSRDocumentNumber,
					ElementType.Xpath);
			VariableHandler.setSSRDocNumber(DocumentsNumber);
			logs.logging("INFO", "SSR Document Number Is: " + DocumentsNumber);
		}

	}

	/**
	 * This Method Used To Verify Check SSR Status
	 * 
	 * @author Deepan-GWL
	 * @param PRNumber
	 * @param ExpextedStatus
	 */
	public void searchPRAndCheckSSRStatus(String SSRNumber, String ExpextedStatus) {
		commonFunc.searchAndSelectValueInFilters(SupplierScheduleRequestLocators.SSRLovButton, SSRNumber);
		flag = webDB.waitForElement(SupplierScheduleRequestLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SupplierScheduleRequestLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);

				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SupplierScheduleRequestLocators.ApprovedStatus(SSRNumber),
						ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(SupplierScheduleRequestLocators.ApprovedStatus(SSRNumber),
							ElementType.Xpath);
					logs.logging("INFO", "SSR Number: '" + SSRNumber + "' searched and displaying status: " + Status);
					flag = Status.equalsIgnoreCase(ExpextedStatus);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * verify Create Po To MRV General RateContract Same UOM WithOutQuaity With
	 * GateEntry
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreatePoToMRVGeneralImportRateContractSameUOMWithOutQuaityWithGateEntry() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.navigateOpenPORCPage();
		openPoRcFunc.verifyClickOpenPORcButton();
		openPoRcFunc.verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("StandardPO"), commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		openPoRcFunc.verifyAddItemInOpenPOLine(VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		openPoRcFunc.verifyAddAggrDateAndAggrNumber();
		openPoRcFunc.verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalPageFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getOpenPoNumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
					System.getProperty("HariUsername"));
			commonFunc.navigateOpenPORCPage();
			openPoRcFunc.verifyClickOpenPORcButton();
		}
		openPoRcFunc.verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		verifySSRCreateAndPostDocuments();
		supplierScheduleOrderFunc
				.verifyMovePendingAndPostDocuments(commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSSODocNumber();
		gateEntryPageFunc.verifygateEntryDetailsPoWithSSO();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		grnPageFunc.verifyAddItemNameInSheet(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		grnPageFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		grnPageFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		commonFunc.naviagteGrnPage();
		grnPageFunc.clickPendingGEAndSearchGENo();
		grnPageFunc.clickSelectAllGEButtonWithSt();
		grnPageFunc.createGrnWithGeButton();
		grnPageFunc.clickOkButton();
		grnPageFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		grnPageFunc.lotSerialQtyApportion();
		grnPageFunc.verifyImportLrBillNumber();
		grnPageFunc.overAllSaveAndPostWithOutValidation(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		grnPageFunc.waitingForApproval();
		grnPageFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"),
				webDB.getDataFromProperties("PoToMrvSheet"));
		grnPageFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		grnPageFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToMRVWoQSUomSSOSheet"), webDB.getDataFromProperties("PoToMrvSheet"));

	}

}
