package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks January 15, 2025
 */
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonfunctions.ItemDetailsHandler.POLineDetailsName;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.OpenPoRcLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import commonlocators.supplychainmanagement.ShareOfBusinessLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseOrderFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static PurchaseRequestFunctions PR_Func = new PurchaseRequestFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author Deepan-GWL
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 */
	public void verifyCreateDirectPOAddingParameters(String LocationId, String SupplierName, String POType) {
//		verifyCLickPurchaseOrderFrame();
		commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.LocationIdButton, LocationId);
		verifyCLickPurchaseOrderFrame();
		flag = webDB.selectDropDownByValue(PurchaseOrderPageLocators.PoTypeSelect, POType, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.SupplierNameButton, SupplierName);
		}
		assertTrue(flag);
	}

	/**
	 * Verify Clicking Add Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickingAddButton() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.AddButtonPurchaseOrder, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.AddButtonPurchaseOrder, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderheader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button In Purchase Order Is Clicked");
				String Text = webDB.getTextFromElement(PurchaseOrderPageLocators.PurchaseOrderheader,
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Header From Purchase order Is: " + Text);
				flag = Text.contains(commonFunc.getDataFromTestDataSheet("PurchaseOrder"));
				if (flag) {
					logs.logging("INFO", "Verified Header Get Text Value From Purchase Order");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Item In Po
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyCreateItemInPo(String Item) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Add Button Is Displayed Successfully");
			webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.POPageItemTable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Add Button Is Clicked");
				webDB.clickAnElement(PurchaseOrderPageLocators.POPageItemTable, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ItemSelectButton, Item);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
				if (flag) {
					String value = webDB.getAttributeFromElement(PurchaseOrderPageLocators.InputItemPO,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Text Item Name Value from Table Is: " + value);
					flag = value.contains(Item);
					if (flag) {
						logs.logging("INFO", "Verified Item Value Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Add Table Specs
	 * 
	 * @author Deepan-GWL
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			commonFunc.hardWait(2500);
			while (webDB.isElementDisplayed("(//button[text()='OK'])[last()]", ElementType.Xpath)) {
				webDB.clickAnElement("(//button[text()='OK'])[last()]", ElementType.Xpath);
			}
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(Tablespec, ElementType.Xpath);
				webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue);
				String value = webDB.getAttributeFromElement(InputParameter, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Attribute Value Is: " + value);
				flag = value.contains(Enteredvalue);
				if (flag) {
					logs.logging("INFO", "Verified Entered Value With Get Attribute Value");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Item In Po
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddItemInPOWithQuantityAndUnitCost(String quantity, String unitcost) {
		try {
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1000);
			verifyAddTableSpecs(PurchaseOrderPageLocators.OrderedQuantity,
					PurchaseOrderPageLocators.OrderedQuantityInput, quantity);
			commonFunc.hardWait(1000);
			verifyAddTableSpecs(PurchaseOrderPageLocators.UnitCost, PurchaseOrderPageLocators.UnitCostInput, unitcost);
			String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
			VariableHandler.setPONumber(PONumbervalue);
		} catch (Exception e) {
		}
	}

	/**
	 * Verify Header Tick And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyHeaderTickAndPost() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTickIconPO, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTickIconPO, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			}
		}
		assertTrue(flag);
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
					commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(PurchaseOrderPageLocators.AddButtonPurchaseOrder, ElementType.Xpath);
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
	 * Verify Tick Icon Create PO
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTickIconcreatePO() {
		commonFunc.checkRefreshCellsDisappear();
		webDB.switchToDefaultContent();
		verifyCLickPurchaseOrderFrame();
		flag = webDB.waitForClickElement(PurchaseOrderPageLocators.DPOTickButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Tick Icon Is Displayed");
			commonFunc.checkRefreshCellsDisappear();
			webDB.clickAnElement(PurchaseOrderPageLocators.DPOTickButton, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Verified Navigate To PO Edit Page");
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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TickIconPO, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.TickIconPO, ElementType.Xpath);
			if (webDB.waitForElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath)) {
				logs.logging("INFO", "Item Tick Icon Is Clicked");
				webDB.clickAnElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				logs.logging("INFO", "Toast Close Icon Is Clicked");
			}

			flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTickIconPO, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Verify Search PO and Validate PO Status
	 * 
	 * @author Deepan-GWL
	 * @param POno
	 * @param ExpectedStatus
	 */
	public void verifyValidatePoStatus(String POno, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.PONumberSelect, POno);
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LineLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.LineLabel, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.SearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseOrderPageLocators.POStatusTableText(POno), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(PurchaseOrderPageLocators.POStatusTableText(POno),
							ElementType.Xpath);
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
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyLineDetailVerification(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
				commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct Po Domestic Material Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialSimilarUOM() {

		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyLineDetailVerification(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
				commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic General Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic General Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct Po Domestic Service Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticServiceSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyLineDetailVerification(commonFunc.getDataFromTestDataSheet("GwlItem_Service"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"), true);
		verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
				commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		if (VariableHandler.getApprovalFlag()) {
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("ServiceType"),
						commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
				}
			}
		}
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import General Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportGeneralSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyLineDetailVerification(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
				commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyUsd"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct Po Import Material Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportMaterialSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyLineDetailVerification(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
				commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct Po Import Service Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportServiceSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyLineDetailVerification(commonFunc.getDataFromTestDataSheet("GwlItem_Service"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
				commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		if (VariableHandler.getApprovalFlag()) {
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
						commonFunc.getDataFromTestDataSheet("ServiceType"),
						commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
				}
			}
		}
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Import General Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportGeneralDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyUsd"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Import Material Dissimilar
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportMaterialDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("DissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Delete Particular Lines Purchase Order PO
	 * 
	 * @author Deepan-GWL
	 * @param DataValue
	 */
	public void deleteParticularLinesPurchaseOrder(String DataValue) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.DeleteLineItemPO(DataValue), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Delete Button Is Displayed");
			webDB.moveToElement(PurchaseRequestLocators.ItemDataLastRow, ElementType.Xpath);
			webDB.moveToElement(PurchaseOrderPageLocators.DeleteLineItemPO(DataValue), ElementType.Xpath);
			webDB.clickAnElement(PurchaseOrderPageLocators.DeleteLineItemPO(DataValue), ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Toast Message Displayed, Line Deleted Successfully with value: " + DataValue);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Cancel Purchase Order Documents
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public boolean VerifyCancelPurchaseOrderDocument() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		webDB.switchToFrame(CommonUiLocators.PoCreateFrame, ElementType.Xpath);
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		deleteParticularLinesPurchaseOrder(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		clickHeaderCancelButton();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("CancelStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Click Header Cancel Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickHeaderCancelButton() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.CancelButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseOrderPageLocators.CancelButton, ElementType.Xpath);
			webDB.clickAnElement(PurchaseOrderPageLocators.CancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Toast Message Displayed, PO Cancelled Successfully");
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click pending and search PR no and verify
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingPRAndSearchPRNo() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPRBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PendingPRBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated to the Pending PR Successfully");
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToFrame(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_FindPRNo, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.PendingPR_FindPRNo,
							VariableHandler.getPRNumber());
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_SearchBtn, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(PurchaseOrderPageLocators.PendingPR_SearchBtn, ElementType.Xpath);
							webDB.clickAnElement(PurchaseOrderPageLocators.PendingPR_SearchBtn, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							logs.logging("INFO", "Pending PR Search Button Clicked");
							webDB.waitForElement("//table//td[3]", ElementType.Xpath);
							List<WebElement> elements = webDB.getDriver().findElements(By.xpath("//table//td[3]"));
							a: for (WebElement webElement : elements) {
								if (!webElement.getText().equals(VariableHandler.getPRNumber())) {
									flag = false;
									break a;
								} else {
									flag = true;
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
	 * This method used to click select all check box in pending PR and click
	 * 'create PO'
	 * 
	 * @author Sakthi-GWL
	 */
	public void selectAllPendingPRAndClickCreatePO() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPRTable, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(5000);
			List<WebElement> CheckBox = webDB.getDriver()
					.findElements(By.xpath(PurchaseOrderPageLocators.PendingPR_SelectAllCheckBox));
			for (WebElement element : CheckBox) {
				try {
					if (element.isDisplayed()) {
						if (!element.isSelected()) {
							element.click();
							flag = true;
							break;
						} else {
							element.click();
							Thread.sleep(10000);
							selectAllPendingPRAndClickCreatePO();
							flag = true;
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_CreatePO_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			for (int i = 0; i < 60; i++) {
				commonFunc.hardWait(1000);
				if (webDB.isElementEnabled(PurchaseOrderPageLocators.PendingPR_CreatePO_Btn, ElementType.Xpath)) {
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
			if (flag) {
				logs.logging("INFO", "Select All CheckBox Clicked in Pending PR and Create PO Enabled");
				webDB.clickAnElement(PurchaseOrderPageLocators.PendingPR_CreatePO_Btn, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_SupplierSelectBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending PR - 'Create PO' Clicked");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to select supplier in Pending PR - Create PO and click Ok
	 * Button
	 * 
	 * @param Supplier
	 * 
	 * @author Sakthi-GWL
	 */
	public void selectSupplierAndCurrencyPendingPRAndClickTickIcon(String Supplier, String prefix) {
		commonFunc.hardWait(2000);
		commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.PrefixLovButton, prefix);
		commonFunc.hardWait(2000);
		webDB.switchToFrame(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.PendingPR_SupplierSelectBtn, Supplier);
		webDB.switchToFrame(PurchaseOrderPageLocators.PendingPRiframe, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_OkBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PendingPR_OkBtn, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create PO - 'Ok' Button Clicked");
				String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Displayed PO Number Is: " + PONumbervalue);
				VariableHandler.setPONumber(PONumbervalue);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticGeneralSimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier Service Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticServiceSimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Service"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

//
	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Dissimilar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticGeneralDissimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("DissimilarItem"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Import Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportGeneralSimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Import Supplier Service Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportServiceSimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("GwlItem_Service"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Import Supplier General Item (Dissimilar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportGeneralDissimilarUOMFromPRtoPO() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		PR_Func.fillItemsQuantityCostAndSaveLine(commonFunc.getDataFromTestDataSheet("DissimilarItem"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"), true);
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic Stock Transfer Similar Uom
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticStockTransferSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location02"),
				commonFunc.getDataFromTestDataSheet("U1Supplier"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		verifyCreateDirectPO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("St_Item_Similar"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyLineDetailVerification(commonFunc.getDataFromTestDataSheet("St_Item_Similar"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
				commonFunc.getDataFromTestDataSheet("St_Item_Similar"));
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("Po_Stock_Transfer"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO With Item Charges Calculations
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOWithItemChargesCalculations() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		webDB.switchToFrame(CommonUiLocators.PoCreateFrame, ElementType.Xpath);
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		navigateToItemChargesTab();
		verifyAddItemCharges(commonFunc.getDataFromTestDataSheet("PackingCharges"));
		verifyAddItemChargesPercentages();
		itemChargesTickIcon();
		verifyItemCalculation();
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyClickPostIcon();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To verify Item Calculation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyItemCalculation() {
		String Item;
		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			Item = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i))).getText();
			logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
			String OrderedQty = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.OrderedQty(i)))
					.getText();
			logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
			String UnitCost = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.UnitCostTable(i)))
					.getText();
			logs.logging("INFO", "Get Text Unit Cost Is: " + UnitCost);

			BigDecimal Quantityd = new BigDecimal(OrderedQty);
			BigDecimal Costd = new BigDecimal(UnitCost);
			BigDecimal LineAmountd = Costd.multiply(Quantityd);

			String ExpectedLineAmount = verifyRoundOffBigDecimalValueWithReturnString(LineAmountd);
			logs.logging("INFO", "Expected Line Amount Is: " + ExpectedLineAmount);
			String ActualLineAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.LineAmount(Item),
					ElementType.Xpath);
			logs.logging("INFO", "Actual Line Amount Is: " + ActualLineAmount);
			flag = ExpectedLineAmount.equals(ActualLineAmount);
			if (flag) {
				logs.logging("INFO", "Verified Actual Line Amount With Expected Line Amount");
				verifyTaxCalculation(Item, ExpectedLineAmount);
			} else {
				assertTrue(flag);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Tax Calculation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param LineAmount
	 */
	public void verifyTaxCalculation(String Item, String LineAmount) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TaxPercentage(Item), ElementType.Xpath);
		if (flag) {
			String TaxPercentage = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxPercentage(Item),
					ElementType.Xpath);
			logs.logging("INFO", "Get Text Tax Percentage For Item " + Item + " Is: " + TaxPercentage);
			BigDecimal TaxPercentaged = new BigDecimal(TaxPercentage);
			BigDecimal LineAmountd = new BigDecimal(LineAmount);
			BigDecimal Hundred = new BigDecimal("100");
			BigDecimal Two = new BigDecimal("2");
			BigDecimal onePercentd = LineAmountd.divide(Hundred);
			logs.logging("INFO", "Calculation For One Percentage Tax Is: " + onePercentd);
			BigDecimal TotalTaxd = onePercentd.multiply(TaxPercentaged);
			BigDecimal individualTaxd = TotalTaxd.divide(Two);
			String IndividaulTaxFinal = verifyRoundOffBigDecimalValueWithReturnString(individualTaxd);
			verifyPOTaxAmountWithTable(Item, IndividaulTaxFinal);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Round Off Big Decimal Number With Return String
	 * 
	 * @author Deepan-GWL
	 * @param number
	 * @param example
	 * @return String ExpectedNumber
	 */
	public String verifyRoundOffBigDecimalValueWithReturnString(BigDecimal number) {
		BigDecimal roundedResult = number.setScale(2, RoundingMode.HALF_UP);
		String Expectednumber = roundedResult.toString();
		return Expectednumber;
	}

	/**
	 * This Method Is Used To Verify Round Off Big Decimal With Return Big Decimal
	 * 
	 * @author Deepan-GWL
	 * @param number
	 * @param example
	 * @return double ExpectedNumber
	 */
	public BigDecimal verifyRoundOffBigDecimalValueWithReturnBigDecimal(BigDecimal number) {
		BigDecimal roundedResult = number.setScale(2, RoundingMode.HALF_UP);
		return roundedResult;
	}

	/**
	 * This Method Is Used To Verify PO Tax Amount With Table
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param IndividualTax
	 */
	public void verifyPOTaxAmountWithTable(String Item, String IndividualTax) {
		logs.logging("INFO", "Expected Individual Tax Amount Is: " + IndividualTax);
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TaxCGST(Item), ElementType.Xpath);
		if (flag) {
			flag = webDB.waitForElement(PurchaseOrderPageLocators.TaxSGST(Item), ElementType.Xpath);
			if (flag) {
				String Cgst = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxCGST(Item), ElementType.Xpath);
				logs.logging("INFO", "Actual CGST Tax Value Is: " + Cgst);
				flag = Cgst.equals(IndividualTax);
				if (flag) {
					logs.logging("INFO", "Verified With CGST Tax Value With Individual Tax Value Calculated");
					String Sgst = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxSGST(Item), ElementType.Xpath);
					flag = Sgst.equals(IndividualTax);
					logs.logging("INFO", "Actual CGST Tax Value Is: " + Sgst);
					if (flag) {
						logs.logging("INFO", "Verified With SGST Tax Value With Individual Tax Value Calculated");
					}
				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Total Amount Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTotalAmountCalculation() {
		commonFunc.hardWait(10000);
		flag = webDB.waitForElement(PurchaseOrderPageLocators.POTotalAmount, ElementType.Xpath);
		if (flag) {
			String POTotalAmount = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.POTotalAmount,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			flag = POTotalAmount.equals(VariableHandler.getPOTotalAmount());

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Item Charge Addition
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyItemChargeAddition() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.AddItemCharges, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Item Charges Button Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.AddItemCharges, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargeItemTable, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Item Charges Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToItemChargesTab() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemChargesTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Charges Tab Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.ItemChargesTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.AddItemCharges, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Add Item Charges
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyAddItemCharges(String Item) {
		verifyItemChargeAddition();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargeItemTable, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Charges Add Button Is Clicked");
			webDB.clickAnElement(PurchaseOrderPageLocators.ChargeItemTable, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ChargeItemButton, Item);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargeItemInput, ElementType.Xpath);
			if (flag) {
				String value = webDB.getAttributeFromElement(PurchaseOrderPageLocators.ChargeItemInput,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Text Item Charges Value from Table Is: " + value);
				flag = value.contains(Item);
				if (flag) {
					logs.logging("INFO", "Verified Item Charges Value Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Add Item Charges Percentages
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddItemChargesPercentages() {
		try {
			commonFunc.hardWait(1000);
			verifyAddTableSpecs(PurchaseOrderPageLocators.ChargePercentage,
					PurchaseOrderPageLocators.ChargePercentageInput,
					commonFunc.getDataFromTestDataSheet("QuantityValue"));
			flag = webDB.waitForElement(PurchaseOrderPageLocators.GLAccDesc, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.GLAccDesc, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.GlAccDescButton,
						commonFunc.getDataFromTestDataSheet("GlAccDescription"));
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargesTickIcon, ElementType.Xpath);
			}

		} catch (Exception e) {
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Item Charges Click Tick Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void itemChargesTickIcon() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ChargesTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Charges Tick Button Is Displayed");
			webDB.clickAnElement(PurchaseOrderPageLocators.ChargesTickIcon, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Close Toast Icon Is Displayed");
				webDB.clickAnElementWithoutException(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.IncludeCharges, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.IncludeCharges, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					if (webDB.isElementDisplayed(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath)) {
						logs.logging("INFO", "Close Toast Icon Is Displayed");
						webDB.clickAnElementWithoutException(PurchaseOrderPageLocators.CloseToastIcon,
								ElementType.Xpath);
					}
					flag = webDB.waitForElement(PurchaseOrderPageLocators.LineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Include Charges Is Clicked");
						webDB.clickAnElement(PurchaseOrderPageLocators.LineTab, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify PO Total Amount Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPOTotalAmountCalculation() {
		BigDecimal TotalLineAmountd = new BigDecimal("0");
		BigDecimal TotalTaxAmountd = new BigDecimal("0");
		BigDecimal LinecalAmount = new BigDecimal("0");
		List<WebElement> LineAmountWE = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.LineAmountWE));
		for (int i = 1; i <= LineAmountWE.size(); i++) {
			String LineAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.LineAmount(i), ElementType.Xpath);
			logs.logging("INFO", "Get Text Line Amount Is: " + LineAmount);
			BigDecimal LineAmountd = new BigDecimal(LineAmount.replace(",", ""));
			TotalLineAmountd = LinecalAmount.add(LineAmountd);
			logs.logging("INFO", "Calculated Total Line Amount Is: " + TotalLineAmountd);
			LinecalAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(TotalLineAmountd);
		}
		BigDecimal TaxcalAmount = new BigDecimal("0");
		for (int i = 1; i <= LineAmountWE.size(); i++) {
			for (int j = 12; j <= 15; j++) {
				String TaxAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxAmount(i, j),
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Tax Amount Is: " + TaxAmount);
				BigDecimal TaxAmountd = new BigDecimal(TaxAmount);
				TotalTaxAmountd = TaxcalAmount.add(TaxAmountd);
				TaxcalAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(TotalTaxAmountd);
			}
			logs.logging("INFO", "Total Tax Amount For Line " + i + " Is: " + TotalTaxAmountd);
		}
		logs.logging("INFO", "Total Tax Amount Calculated Is: " + TotalTaxAmountd);
		BigDecimal POTotalAmount = LinecalAmount.add(TaxcalAmount);
		String Totalvalue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(POTotalAmount).stripTrailingZeros()
				.toPlainString();
		logs.logging("INFO", "Expected Total Po Value Is: " + Totalvalue);
		VariableHandler.setPOTotalAmount(Totalvalue);
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Click Post Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickPostIcon() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
			logs.logging("INFO", "Header Post Icon Is Clicked");
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To verify Item Calculation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyItemCalculationWithLineAmount(String ItemName) {
		String Item;
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			Item = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i))).getText();
			logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
			if (ItemName.equals(Item)) {
				String OrderedQty = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.OrderedQty(i)))
						.getText();
				logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
				String UnitCost = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.UnitCostTable(i)))
						.getText();
				logs.logging("INFO", "Get Text Unit Cost Is: " + UnitCost);

				BigDecimal Quantityd = new BigDecimal(OrderedQty);
				BigDecimal Costd = new BigDecimal(UnitCost);
				BigDecimal LineAmountd = Costd.multiply(Quantityd);
				BigDecimal ExpectedLineAmountd = verifyRoundOffBigDecimalValueWithReturnBigDecimal(LineAmountd);
				VariableHandler.setLineAmount(ExpectedLineAmountd);
				VariableHandler.setTotalLineAmount(VariableHandler.getTotalLineAmount().add(ExpectedLineAmountd));
				System.out.println("Expected Double Line Amount IS: " + ExpectedLineAmountd);
				String ExpectedLineAmount = verifyRoundOffBigDecimalValueWithReturnString(LineAmountd);
//			String ExpectedLineAmount = String.format("%.2f", LineAmountd);
				logs.logging("INFO", "Expected Line Amount Is: " + ExpectedLineAmount);
				String ActualLineAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.LineAmount(Item),
						ElementType.Xpath);
				logs.logging("INFO", "Actual Line Amount Is: " + ActualLineAmount);
				flag = ExpectedLineAmount.equals(ActualLineAmount.replace(",", ""));
				if (flag) {
					logs.logging("INFO", "Verified Actual Line Amount With Expected Line Amount");
				} else {
					assertTrue(flag);
				}
				break;
			} else {

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify PO UOM
	 * 
	 * @author Deepan-GWL
	 * @param ExpectedUOM
	 */
	public void verifyPoUOM(String ExpectedUOM) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.UOMTable, ElementType.Xpath);
		if (flag) {
			String ActualUOM = webDB.getTextFromElement(PurchaseOrderPageLocators.UOMTable, ElementType.Xpath);
			logs.logging("INFO", "Expected UOM Is: " + ExpectedUOM);
			logs.logging("INFO", "Actual Get Text UOM Is: " + ActualUOM);
			flag = ActualUOM.equals(ExpectedUOM);
			if (flag) {
				logs.logging("INFO", "Verified Actual UOM With Expected UOM");
			}

		}
		assertTrue(flag);
	}

	/**
	 * Verify PO Hedaer Details
	 * 
	 * @author Deepan-GWL
	 * @param PoTypeValue
	 * @param LocationValue
	 * @param SupplierHeaderValue
	 * @param CurrencyValue
	 * @param PoPrefixValue
	 */
	public void verifyPoHeaderDetails(String PoTypeValue, String LocationValue, String SupplierHeaderValue,
			String CurrencyValue, String PoPrefixValue) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PoTypeHeader, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "PO Type Header Is Displayed");
			String PoType = webDB.getTextFromElement(PurchaseOrderPageLocators.PoTypeHeader, ElementType.Xpath);
			logs.logging("INFO", "Get Text Po Type Header Vale Is: " + PoType);
			logs.logging("INFO", "Expected Po Type Value Is: " + PoTypeValue);
			flag = PoType.equals(PoTypeValue);
			if (flag) {
				logs.logging("INFO", "Verified With Actual Po Type With Expected Po Type Value");
				String Location = webDB.getTextFromElement(PurchaseOrderPageLocators.LocationHeaderId,
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Actual Value In Location Is: " + Location);
				logs.logging("INFO", "Expected Value In Location Is: " + LocationValue);
				flag = Location.equals(LocationValue);
				if (flag) {
					logs.logging("INFO", "Verified Location Value Actual Value With Expected Value");
					String SupplierName = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.SupplierHeaderName,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Attribute Supplier Name Is: " + SupplierName);
					logs.logging("INFO", "Expected Suplier Name Is: " + SupplierHeaderValue);
					flag = SupplierName.equals(SupplierHeaderValue);
					if (flag) {
						logs.logging("INFO", "Verified Suplier Name With Actual And Expected");
						String CurrencyHeader = webDB.getDomPropertyFromElement(
								PurchaseOrderPageLocators.CurrencyHeader, ElementType.Xpath,
								commonFunc.getDataFromTestDataSheet("Value"));
						flag = CurrencyHeader.equals(CurrencyValue);
						logs.logging("INFO", "Get Text Currency Value Is: " + CurrencyHeader);
						logs.logging("INFO", "Expected Currency Value Is: " + CurrencyValue);
						if (flag) {
							logs.logging("INFO", "Verified Actual Currency Value With Expected Value");
							String PrefixHeader = webDB.getTextFromElement(PurchaseOrderPageLocators.POPrefixHeader,
									ElementType.Xpath);
							logs.logging("INFO", "Get Text Prefix Header Is: " + PrefixHeader);
							logs.logging("INFO", "Expected Prefix Header Is: " + PoPrefixValue);
							flag = PrefixHeader.equals(PoPrefixValue);
							if (flag) {
								logs.logging("INFO", "Verified Actual Value With Expected Value");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Line PO Details Validation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyLinePODetailsValidations(String Item, String Currency) {
		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			String ItemName = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i))).getText();
			if (ItemName.equals(Item)) {
				verifyGetTextValidation(PurchaseOrderPageLocators.ItemName(i),
						itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEMID));
				verifyGetTextValidation(PurchaseOrderPageLocators.ItemDescription(i),
						itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEM_DESCRIPTION));
				verifyGetTextValidation(PurchaseOrderPageLocators.UOM(i),
						itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.UOM));
//				verifyGetTextValidation(PurchaseOrderPageLocators.HSNSACCode(i),
//						itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.HSN_CODE));
				if (Currency.equals(commonFunc.getDataFromTestDataSheet("CurrencyInr"))) {
					verifyGetTextValidation(PurchaseOrderPageLocators.TaxPercent(i),
							itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.TAX));
				} else {
					verifyGetTextValidation(PurchaseOrderPageLocators.TaxPercent(i),
							commonFunc.getDataFromTestDataSheet("ZeroValue"));
				}
			}
		}
	}

	/**
	 * Verify Line Details Verification
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Quantity
	 */
	public void verifyLineDetailVerification(String Item, String Quantity, String Currency, boolean... POType) {
		verifyLinePODetailsValidations(Item, Currency);
		navigateToLineDetailPage(Item, POType);
		verifyLineDetailOne(Item, Quantity);
		verifyNavigateLineDetailTwo();
		verifyLineDetailTwo(Item);
	}

	/**
	 * Navigate To Line Details Page
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void navigateToLineDetailPage(String Item, boolean... PoType) {
		webDB.waitForElement(PurchaseOrderPageLocators.OrderedQuantity, ElementType.Xpath);
		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			String ItemName = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i))).getText();
			if (ItemName.contains(Item)) {
				commonFunc.hardWait(2000);
				webDB.moveToElement(PurchaseOrderPageLocators.ItemName(i), ElementType.Xpath);
				if (PoType.length > 0) {
					webDB.moveToElement(PurchaseOrderPageLocators.ServiceLineDetailButton(i), ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.ServiceLineDetailButton(i),
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseOrderPageLocators.ServiceLineDetailButton(i), ElementType.Xpath);
					}
				} else {
					webDB.moveToElement(PurchaseOrderPageLocators.LineDetailButton(i), ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailButton(i), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseOrderPageLocators.LineDetailButton(i), ElementType.Xpath);
					}
				}
				flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailsHeader, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Navigated To Line Details Page");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Dom Property Validations
	 * 
	 * @author Deepan-GWL
	 * @param locators
	 * @param Value
	 */
	public void verifyDomPropertyValidation(String locators, String Value) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			String text = webDB.getDomPropertyFromElement(locators, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.contains(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Get Text VAlidations
	 * 
	 * @author Deepan-GWL
	 * @param locators
	 * @param Value
	 */
	public void verifyGetTextValidation(String locators, String Value) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(locators, ElementType.Xpath);
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.equals(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Line Detail One
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Quantity
	 */
	public void verifyLineDetailOne(String Item, String Quantity) {
		webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
		verifyGetTextValidation(PurchaseOrderPageLocators.SubClass,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.SUBCLASS));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.Class,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CLASS));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.Wh_Dept_Name,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.WH_DEPTNAME));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.CostBasis,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.COST_BASIS));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.StockUOM,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.STOCK_UOM));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.ConversionFactor,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CONVERSION_FACTOR));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.Gl_Acc_No,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.GL_ACC_NO));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.Gl_Acc_Desc,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.GL_ACC_DESC));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.CPC_Code,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CPC_CODE));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.CPC_Desc,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CPC_DESC));

	}

	/**
	 * Verify Navigate Line Detail Two
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyNavigateLineDetailTwo() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailTwoButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.LineDetailTwoButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.UnRegisterHeader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Line Details Two Page");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Line Detail Two
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyLineDetailTwo(String Item) {
		verifyDomPropertyValidation(PurchaseOrderPageLocators.Supplier_Item,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEMID));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.Supplier_Item_Desc,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEM_DESCRIPTION));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.ContractId,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CONTRACT_NO));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.ContractPrefix,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CONTRACT_PRFX));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.ContractAmendNumber,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CONTRACT_AMEND_NO));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.POClsQuantity,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.PO_CLS_QTY));
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TickButtonLineOne, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.TickButtonLineOne, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();

		}
		webDB.switchToDefaultContent();
		try {
			WebElement element = webDB.getDriver()
					.findElement(By.xpath(PurchaseOrderPageLocators.LineDetailsCloseIcons));
			WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LocationHeaderId, ElementType.Xpath);
		assertTrue(flag);
	}

	/**
	 * Verify Create Direct PO
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyCreateDirectPO() {
		webDB.switchToFrame(CommonUiLocators.PoCreateFrame, ElementType.Xpath);
		String PoType = null;
		String location = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.LocationIdInput, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		WebElement element = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.PoTypeSelect));
		Select select = new Select(element);
		for (WebElement Text : select.getAllSelectedOptions()) {
			PoType = Text.getText();
		}
		String SupplierName = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.SupplierName, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		commonFunc.hardWait(1000);
		String CurrencyValue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.CurrencyInput,
				ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
		WebElement prefixelement = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.PoPfxSelect));
		Select selectprefix = new Select(prefixelement);
		for (WebElement Textprefix : selectprefix.getAllSelectedOptions()) {
			String PoPrefix = Textprefix.getText();
			verifyTickIconcreatePO();
//			verifyPoHeaderDetails(PoType, location, SupplierName, CurrencyValue, PoPrefix);
		}
	}

	/**
	 * Verify Discount Amount Calculations
	 * 
	 * @author Deepan-GWL
	 * @param TotalAmount
	 * @param Percentage
	 * @return
	 */
	public BigDecimal verifyDiscountAmountCalculation(BigDecimal TotalAmount, BigDecimal Percentage) {
		BigDecimal DiscountAmount = (TotalAmount.multiply(Percentage)).divide(new BigDecimal("100"));
		DiscountAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(DiscountAmount);
		return DiscountAmount;
	}

	/**
	 * Verify Enter Amunt For Discount Calculation
	 * 
	 */
	public void verifyEnterAmountForDiscountCalculation() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TradePercentage, ElementType.Xpath);
		if (flag) {
			webDB.clearText(PurchaseOrderPageLocators.TradePercentage, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseOrderPageLocators.TradePercentage,
					commonFunc.getDataFromTestDataSheet("FiveValue"), ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			verifyDomPropertyValidation(PurchaseOrderPageLocators.TradePercentage,
					commonFunc.getDataFromTestDataSheet("FiveValue"));
			webDB.clearText(PurchaseOrderPageLocators.SpecialDiscountPercentage, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseOrderPageLocators.SpecialDiscountPercentage,
					commonFunc.getDataFromTestDataSheet("FiveValue"), ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			verifyDomPropertyValidation(PurchaseOrderPageLocators.SpecialDiscountPercentage,
					commonFunc.getDataFromTestDataSheet("FiveValue"));
			webDB.clearText(PurchaseOrderPageLocators.CashDiscountPercentage, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseOrderPageLocators.CashDiscountPercentage,
					commonFunc.getDataFromTestDataSheet("FiveValue"), ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			verifyDomPropertyValidation(PurchaseOrderPageLocators.CashDiscountPercentage,
					commonFunc.getDataFromTestDataSheet("FiveValue"));
		}
		assertTrue(flag);
	}

	public void discountCalculationVerification(String quantity) {
		BigDecimal DiscAmountPerQty = new BigDecimal("0");
		BigDecimal TotalDiscountAmount = new BigDecimal("0");
		BigDecimal LineAmount = VariableHandler.getLineAmount();
		BigDecimal TradeAmount = verifyDiscountAmountCalculation(LineAmount, new BigDecimal("5"));
		BigDecimal SpecAssembleValue = LineAmount.subtract(TradeAmount);
		SpecAssembleValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(SpecAssembleValue);
		BigDecimal SplDiscAmount = verifyDiscountAmountCalculation(SpecAssembleValue, new BigDecimal("5"));
		BigDecimal CashAssembleValue = SpecAssembleValue.subtract(SplDiscAmount);
		CashAssembleValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(CashAssembleValue);
		BigDecimal cashDiscount = verifyDiscountAmountCalculation(CashAssembleValue, new BigDecimal("5"));
		BigDecimal Qty = new BigDecimal(quantity);
		DiscAmountPerQty = (TradeAmount.add(SplDiscAmount).add(cashDiscount)).divide(Qty, 2, RoundingMode.HALF_UP);
		DiscAmountPerQty = verifyRoundOffBigDecimalValueWithReturnBigDecimal(DiscAmountPerQty);
		TotalDiscountAmount = (TradeAmount.add(SplDiscAmount).add(cashDiscount));
		VariableHandler.setDiscountAmount(TotalDiscountAmount);
		VariableHandler.setTotalDiscountAmount(VariableHandler.getTotalDiscountAmount().add(TotalDiscountAmount));
		verifyDomPropertyValidation(PurchaseOrderPageLocators.TradeAssembleValue,
				LineAmount.stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.SpecialAssembleValue,
				SpecAssembleValue.stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.CashAssembleValue,
				CashAssembleValue.stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.TradeAmount,
				TradeAmount.stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.SpecialDiscountAmount,
				SplDiscAmount.stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.CashDiscountAmount,
				cashDiscount.stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.DiscAmountPerQty,
				DiscAmountPerQty.stripTrailingZeros().toPlainString());
	}

	/**
	 * Verify Tax Calculation After Discount
	 * 
	 * @author Deepan-GWL
	 * @param Currency
	 * @param Item
	 */
	public void verifyTaxCalculationAfterDiscount(String Currency, String Item) {
		BigDecimal TaxcalAmount = new BigDecimal("0");
		BigDecimal TotalTaxAmountd = new BigDecimal("0");
		BigDecimal LineAmountAfterDiscount = VariableHandler.getLineAmount()
				.subtract(VariableHandler.getDiscountAmount());
		commonFunc.checkRefreshCellsDisappear();

		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			String ItemName = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i))).getText();
			logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
			if (ItemName.equals(Item)) {

				verifyGetTextValidation(PurchaseOrderPageLocators.DiscountAmount(Item),
						VariableHandler.getDiscountAmount().toString());
				if (Currency.equals(commonFunc.getDataFromTestDataSheet("CurrencyInr"))) {
					flag = webDB.waitForElement(PurchaseOrderPageLocators.TaxPercentage(Item), ElementType.Xpath);
					if (flag) {
						String TaxPercentage = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxPercentage(Item),
								ElementType.Xpath);
						logs.logging("INFO", "Get Text Tax Percentage For Item " + Item + " Is: " + TaxPercentage);
						BigDecimal TotalTaxAmount = (LineAmountAfterDiscount.multiply(new BigDecimal(TaxPercentage)))
								.divide(new BigDecimal(100));
						BigDecimal IndividualTax = verifyRoundOffBigDecimalValueWithReturnBigDecimal(
								TotalTaxAmount.divide(new BigDecimal("2")));
						TotalTaxAmount = IndividualTax.multiply(new BigDecimal("2"));
						for (int j = 12; j <= 15; j++) {
							String TaxAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxAmount(i, j),
									ElementType.Xpath);
							logs.logging("INFO", "Get Text Tax Amount Is: " + TaxAmount);
							BigDecimal TaxAmountd = new BigDecimal(TaxAmount);
							TotalTaxAmountd = TaxcalAmount.add(TaxAmountd);
							TaxcalAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(TotalTaxAmountd);
						}
						logs.logging("INFO", "Total Tax Amount Is: " + TotalTaxAmount);
						System.out.println(TaxcalAmount);
						flag = TotalTaxAmount.equals(TaxcalAmount);
						if (flag) {
							VariableHandler.setIndividualAmount(TaxcalAmount);
							VariableHandler.setTaxAmount(VariableHandler.getTaxAmount().add(TaxcalAmount));

						}

					}
					assertTrue(flag);
				} else {
					for (int j = 12; j <= 15; j++) {
						String TaxAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxAmount(i, j),
								ElementType.Xpath);
						logs.logging("INFO", "Get Text Tax Amount Is: " + TaxAmount);
						flag = TaxAmount.equals(commonFunc.getDataFromTestDataSheet("Zero"));
						if (flag) {

						} else {
							assertTrue(false);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	public void verifyGeneralTabTotalAmountCalculation() {
		commonFunc.hardWait(10000);
		BigDecimal TotalAmount = VariableHandler.getTotalLineAmount().subtract(VariableHandler.getTotalDiscountAmount())
				.add(VariableHandler.getTaxAmount());
		BigDecimal TotalAmountroundoff = verifyRoundOffBigDecimalValueWithReturnBigDecimal(TotalAmount, 0);
		BigDecimal RoundOffValue = TotalAmountroundoff.subtract(TotalAmount);
		verifyNavigateToGeneraTab();
		System.out.println("Variable Handler Line Amount"
				+ VariableHandler.getTotalLineAmount().stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.MatAmountInput,
				VariableHandler.getTotalLineAmount().stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.TaxAmountInput,
				VariableHandler.getTaxAmount().stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.DiscountAmountInput,
				VariableHandler.getTotalDiscountAmount().stripTrailingZeros().toPlainString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.TotalAmountInput, TotalAmountroundoff.toString());
		verifyDomPropertyValidation(PurchaseOrderPageLocators.RoundOffInput,
				RoundOffValue.stripTrailingZeros().toPlainString().replace("0.", "."));
	}

	/**
	 * This Method Is Used To Verify Round Off Big Decimal With Return Big Decimal
	 * 
	 * @author Deepan-GWL
	 * @param number
	 * @param example
	 * @return double ExpectedNumber
	 */
	public BigDecimal verifyRoundOffBigDecimalValueWithReturnBigDecimal(BigDecimal number, int digit) {
		BigDecimal roundedResult = number.setScale(digit, RoundingMode.HALF_UP);
		return roundedResult;
	}

	/**
	 * This Method Is Used To Verify Round Off Big Decimal Number With Return String
	 * 
	 * @author Deepan-GWL
	 * @param number
	 * @param example
	 * @return String ExpectedNumber
	 */
	public String verifyRoundOffBigDecimalValueWithReturnString(BigDecimal number, int digit) {
		BigDecimal roundedResult = number.setScale(digit, RoundingMode.HALF_UP);
		String Expectednumber = roundedResult.toString();
		return Expectednumber;
	}

	public void verifyNavigateToGeneraTab() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.GeneralTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.GeneralTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.MatAmountLabel, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * 
	 * This Method Is Used To
	 * CreateverifyDirectPODomesticMaterialSimilarUOMwithLotItem
	 * 
	 * @author PremKumar.K-RM IT
	 * 
	 */
	public void verifyDirectPODomesticMaterialSimilarUOMwithLotItem() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("LotItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		assertTrue(flag);
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic material with Serial Item
	 * 
	 * @author PremKumar.K-RM IT
	 * @return boolean flag
	 */
	public void verifyDirectPODomesticMaterialSimilarUOMwithSerialtItem() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("serial"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		assertTrue(flag);
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic material with N/A Item
	 * 
	 * @author PremKumar.K-RM IT
	 * @return boolean flag
	 */
	public void verifyDirectPODomesticMaterialSimilarUOMwithN_AItem() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("NAItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		assertTrue(flag);
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic Supplier for material Type
	 * with Multiple Item For UserDefined Item
	 * 
	 * @author PremKumar.K-RM ITs @
	 */
	public void verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POMultipleItemDetailsSheetName"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {

			String ITEM_NAME = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			String UNIT_COST = itemmultiple[i][2];
			if (!ITEM_NAME.equals("")) {
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Item SuccessFully Added");
						flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, ITEM_NAME,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
													ElementType.Xpath);
											webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
											webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
													ElementType.Xpath);
											flag = webDB.waitForElement(PurchaseOrderPageLocators.PoUnitCostClick,
													ElementType.Xpath);
											if (flag) {
												commonFunc.hardWait(4000);
												webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick,
														ElementType.Xpath);
												webDB.clearText(PurchaseOrderPageLocators.PoUnitCost,
														ElementType.Xpath);
												webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoUnitCost,
														UNIT_COST, ElementType.Xpath);

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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("MaterialType"),
						commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();

				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic General with Multiple Item
	 * 
	 * @author PremKumar.K-RM ITS @
	 */
	public void verifyDirectPODomesticGeneralSimilarUOMWithMultipleItem() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POMultipleGen"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			String UNIT_COST = itemmultiple[i][2];
			if (!ITEM_NAME.equals("")) {
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Item SuccessFully Added");
						flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, ITEM_NAME,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
													ElementType.Xpath);
											commonFunc.hardWait(1000);
											webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
											webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
													ElementType.Xpath);
											flag = webDB.waitForElement(PurchaseOrderPageLocators.PoUnitCostClick,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick,
														ElementType.Xpath);
												commonFunc.hardWait(2000);
												webDB.clearText(PurchaseOrderPageLocators.PoUnitCost,
														ElementType.Xpath);
												webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoUnitCost,
														UNIT_COST, ElementType.Xpath);

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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("PohType"), commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with RateContract Item for Similar
	 * UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralType() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POwithRateContractItemGeneral"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {
			String RateContractItem = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			if (!RateContractItem.equals("")) {
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					if (flag) {
						flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, RateContractItem,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
										logs.logging("INFO", "Item SuccessFully Added");
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										if (flag) {
											commonFunc.hardWait(1000);
											webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
													ElementType.Xpath);
											webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
											webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
													ElementType.Xpath);
											logs.logging("INFO", "Quantity Successfullly Updated");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			logs.logging("INFO", "Verified to LogOut for HODTVSARGOMM");
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("PohType"), commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();
					logs.logging("INFO", "Verified to LogOut for TVSPURMGR");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the PO Document for Rate
	 * ContractItem(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterial() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POWithRateContractItemMaterial"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {
			String RateContractItem = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			if (!RateContractItem.equals("")) {
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					if (flag) {
						flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, RateContractItem,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
										logs.logging("INFO", "Item SuccessFully Added");
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
													ElementType.Xpath);
											webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
											webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
													ElementType.Xpath);
											logs.logging("INFO", "Quantity Successfullly Updated");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			logs.logging("INFO", "Verified to LogOut for HODTVSARGOMM");
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("MaterialType"),
						commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();
					logs.logging("INFO", "Verified to LogOut for TVSPURMGR");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */
	public void verifyDirectPODomesticServiceSimilarUOMMultiple() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPageForPurchaseUser();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
		verifyCreateItemInPo(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PoServiceWithGrn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PoServiceWithGrn, ElementType.Xpath);
			logs.logging("INFO", "With GRN Click successfully");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PoseriviceWithGrnSelectedValues, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(1000);
				webDB.selectDropDownByValue(PurchaseOrderPageLocators.PoseriviceWithGrnSelectedValues,
						commonFunc.getDataFromTestDataSheet("Y"), ElementType.Xpath);
				logs.logging("INFO", "With GRN Changed successfully");
			}
			verifyTickIconcreateItem();
			verifyItemCalculationWithLineAmount();
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			if (VariableHandler.getApprovalFlag()) {
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
					approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("CurrencyInr"),
							commonFunc.getDataFromTestDataSheet("ServiceType"),
							commonFunc.getDataFromTestDataSheet("UomNos"));
					approvalFunc.verifyNavigateback();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
					approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
					flag = commonFunc.logoutUser();
					if (flag) {
						commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
								System.getProperty("SelvaPassword"));
						commonFunc.navigatePurchaseOrderPage();
					}
				}
			}
			verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			assertTrue(flag);
		}
	}

	/**
	 * This Method Is Used To Verify Approve PR Using Ashok User
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyApprovePOUsingAshokUser() {
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
			flag = commonFunc.logoutUser();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("SelvaUsername"));
			commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
					"SelvaUsername");
			commonFunc.navigatePurchaseOrderPage();
		}
	}

	/**
	 * This Methods Is Used To verify To Create PO For Stock Transfer RateContract
	 * Same Stock And Supplier UOM With And WithoutInspection
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToCreatePoForStockTransferRateContractSameStockandSupplierUomWithAndWithoutInspection() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPageForPurchaseUser();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0002"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POwithStockTransferItemRateContract"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			if (!ITEM_NAME.equals("")) {
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Item SuccessFully Added");
						flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, ITEM_NAME,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
													ElementType.Xpath);
											webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
											webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
													ElementType.Xpath);
											logs.logging("INFO", "Quantity Successfullly Updated");
											flag = webDB.waitForElement(PurchaseOrderPageLocators.PoUnitCostClick,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick,
														ElementType.Xpath);
												flag = webDB.isElementEnabled(PurchaseOrderPageLocators.PoUnitCost,
														ElementType.Xpath);
												logs.logging("INFO",
														"Unit Cost Field Not Able to Click and Verified It is Pricelist Item");
											} else {
												webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick,
														ElementType.Xpath);
												flag = webDB.isElementEnabled(PurchaseOrderPageLocators.PoUnitCost,
														ElementType.Xpath);
												logs.logging("INFO",
														"Unit Cost Field Able to Click and Verified It is Pricelist Item(Issue)");
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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("STTYPE"), commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify To Create PO For Stock Transfer UserDefined
	 * Same Stock and Supplier UOM With And WithoutInspection
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToCreatePoForStockTransferUserDefinedSameStockandSupplierUomWithAndWithoutInspection(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0002"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("STTYPE"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Direct PO Domestic Price List Item Similar UOM
	 * Multiple Service Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticPriceListItemSimilarUOMMultipleServiceType() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPageForPurchaseUser();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POwithPriceListItemServiceType"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {
			String PriceList_Ser_tItem = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			if (!PriceList_Ser_tItem.equals("")) {
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, PriceList_Ser_tItem,
										ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
									logs.logging("INFO", "Item SuccessFully Added");
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
										webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
												ElementType.Xpath);
										logs.logging("INFO", "Quantity Successfullly Updated");
										commonFunc.hardWait(2000);
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoServiceWithGrn,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoServiceWithGrn,
													ElementType.Xpath);
											logs.logging("INFO", "With GRN Click successfully");
											flag = webDB.waitForElement(
													PurchaseOrderPageLocators.PoseriviceWithGrnSelectedValues,
													ElementType.Xpath);
											if (flag) {
												webDB.selectDropDownByValue(
														PurchaseOrderPageLocators.PoseriviceWithGrnSelectedValues,
														commonFunc.getDataFromTestDataSheet("Y"), ElementType.Xpath);
												logs.logging("INFO", "With GRN Changed successfully");
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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			logs.logging("INFO", "Verified to LogOut for HODTVSARGOMM");
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("ServiceType"),
						commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();
					logs.logging("INFO", "Verified to LogOut for TVSPURMGR");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify To Create PO For Stock Transfer Price List
	 * Item For Same Stock and Supplier UOM With And Without Inspection
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToCreatePoForStockTransferPriceListItemForSameStockandSupplierUomWithAndWithoutInspection(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0002"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("StockTransferSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("STTYPE"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify DirectPO Domestic General Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItem() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POwithUserDefinedItemDisimilarUomGenType"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			String UNIT_COST = itemmultiple[i][2];
			if (!ITEM_NAME.equals("")) {
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Item SuccessFully Added");
						flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, ITEM_NAME,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
													ElementType.Xpath);
											commonFunc.checkRefreshCellsDisappear();
											commonFunc.hardWait(1000);
											webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
											webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
													ElementType.Xpath);
											flag = webDB.waitForElement(PurchaseOrderPageLocators.PoUnitCostClick,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick,
														ElementType.Xpath);
												commonFunc.checkRefreshCellsDisappear();
												commonFunc.hardWait(1000);
												webDB.clearText(PurchaseOrderPageLocators.PoUnitCost,
														ElementType.Xpath);
												webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoUnitCost,
														UNIT_COST, ElementType.Xpath);
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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("PohType"), commonFunc.getDataFromTestDataSheet("Kgs"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(General Type)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlan() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POwithPriceListItemGeneralType"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {
			String PricelistItem = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			if (!PricelistItem.equals("")) {
				commonFunc.hardWait(4000);
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, PricelistItem,
										ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
									logs.logging("INFO", "Item SuccessFully Added");
									commonFunc.hardWait(4000);
									commonFunc.checkRefreshCellsDisappear();
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
										webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
												ElementType.Xpath);
										logs.logging("INFO", "Quantity Successfullly Updated");
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoUnitCostClick,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick,
													ElementType.Xpath);
											flag = webDB.isElementEnabled(PurchaseOrderPageLocators.PoUnitCost,
													ElementType.Xpath);
											logs.logging("INFO",
													"Unit Cost Field Not Able to Click and Verified It is Pricelist Item");
										} else {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick,
													ElementType.Xpath);
											flag = webDB.isElementEnabled(PurchaseOrderPageLocators.PoUnitCost,
													ElementType.Xpath);
											logs.logging("INFO",
													"Unit Cost Field Able to Click and Verified It is Pricelist Item(Issue)");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			logs.logging("INFO", "Verified to LogOut for HODTVSARGOMM");
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("PohType"), commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();
					logs.logging("INFO", "Verified to LogOut for TVSPURMGR");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Verify To create PO with RateContract Item for
	 * Service Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticRateContractItemSimilarUOMMultipleServiceType() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPageForPurchaseUser();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POwithRateContrctItemServiceType"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {
			String RateContrac_Ser_tItem = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			if (!RateContrac_Ser_tItem.equals("")) {
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, RateContrac_Ser_tItem,
										ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
									logs.logging("INFO", "Item SuccessFully Added");
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
										webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
												ElementType.Xpath);
										logs.logging("INFO", "Quantity Successfullly Updated");
										commonFunc.hardWait(2000);
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoServiceWithGrn,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoServiceWithGrn,
													ElementType.Xpath);
											logs.logging("INFO", "With GRN Click successfully");
											flag = webDB.waitForElement(
													PurchaseOrderPageLocators.PoseriviceWithGrnSelectedValues,
													ElementType.Xpath);
											if (flag) {
												webDB.selectDropDownByValue(
														PurchaseOrderPageLocators.PoseriviceWithGrnSelectedValues,
														commonFunc.getDataFromTestDataSheet("Y"), ElementType.Xpath);
												logs.logging("INFO", "With GRN Changed successfully");
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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			logs.logging("INFO", "Verified to LogOut for HODTVSARGOMM");
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("ServiceType"),
						commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();
					logs.logging("INFO", "Verified to LogOut for TVSPURMGR");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify Approve With Doc Number
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyApproveWithDocNumber(String Number) {
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForClickElement(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.POSelectCheckBox(Number), ElementType.Xpath);
			flag = webDB.waitForClickElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CheckBox Is Clicked And Process Button IS Displayed");
				webDB.javaScriptClickAnElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
				flag = webDB.waitForElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO",
							"CheckBox select for Document Number " + Number + " and Process button clicked");
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
					flag = webDB.waitForElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Approval Process Button IS Displayed");
						webDB.clickAnElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
						approvalFunc.ExtractStocktransferDocNumber();
						if (flag) {
							logs.logging("INFO", "Toast Message Displayed, Posted Successfully");
							if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
								logs.logging("INFO", "Note Alert Closed");
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralSimilarUOMWithMultipleItems() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("SimilarItemSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][0];
			String Quantity = itemmultiple[i][1];
			String UnitCost = itemmultiple[i][2];
			String UOM = itemmultiple[i][3];
			verifyCreateItemInPo(item);
			verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
			verifyTickIconcreateItem();
			verifyItemCalculationWithLineAmount(item);
			verifyPoUOM(UOM);
			verifyLineDetailVerification(item, Quantity, commonFunc.getDataFromTestDataSheet("CurrencyInr"));
			verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyInr"), item);
		}
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To verify Item Calculation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyItemCalculationWithLineAmount() {
		String Item;
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			Item = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i))).getText();
			logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
			String OrderedQty = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.OrderedQty(i)))
					.getText();
			logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
			String UnitCost = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.UnitCostTable(i)))
					.getText();
			logs.logging("INFO", "Get Text Unit Cost Is: " + UnitCost);

			BigDecimal Quantityd = new BigDecimal(OrderedQty);
			BigDecimal Costd = new BigDecimal(UnitCost);
			BigDecimal LineAmountd = Costd.multiply(Quantityd);
			BigDecimal ExpectedLineAmountd = verifyRoundOffBigDecimalValueWithReturnBigDecimal(LineAmountd);
			VariableHandler.setLineAmount(ExpectedLineAmountd);
			VariableHandler.setTotalLineAmount(VariableHandler.getTotalLineAmount().add(ExpectedLineAmountd));
			System.out.println("Expected Double Line Amount IS: " + ExpectedLineAmountd);
			String ExpectedLineAmount = verifyRoundOffBigDecimalValueWithReturnString(LineAmountd);
//			String ExpectedLineAmount = String.format("%.2f", LineAmountd);
			logs.logging("INFO", "Expected Line Amount Is: " + ExpectedLineAmount);
			String ActualLineAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.LineAmount(Item),
					ElementType.Xpath);
			logs.logging("INFO", "Actual Line Amount Is: " + ActualLineAmount);
			flag = ExpectedLineAmount.equals(ActualLineAmount.replace(",", ""));
			if (flag) {
				logs.logging("INFO", "Verified Actual Line Amount With Expected Line Amount");
			} else {
				assertTrue(flag);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialSimilarUOMWithMultipleItems() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("SimilarItemSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][0];
			String Quantity = itemmultiple[i][1];
			String UnitCost = itemmultiple[i][2];
			String UOM = itemmultiple[i][3];
			verifyCreateItemInPo(item);
			verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
			verifyTickIconcreateItem();
			verifyItemCalculationWithLineAmount(item);
			verifyPoUOM(UOM);
			verifyLineDetailVerification(item, Quantity, commonFunc.getDataFromTestDataSheet("CurrencyInr"));
			verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyInr"), item);
		}
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import General Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportGeneralSimilarUOMWithMultipleItems() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("SimilarItemSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][0];
			String Quantity = itemmultiple[i][1];
			String UnitCost = itemmultiple[i][2];
			String UOM = itemmultiple[i][3];
			verifyCreateItemInPo(item);
			verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
			verifyTickIconcreateItem();
			verifyItemCalculationWithLineAmount(item);
			verifyPoUOM(UOM);
			verifyLineDetailVerification(item, Quantity, commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
			verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyUsd"), item);
		}
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import Material Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportMaterialSimilarUOMWithMultipleItems() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("SimilarItemSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][0];
			String Quantity = itemmultiple[i][1];
			String UnitCost = itemmultiple[i][2];
			String UOM = itemmultiple[i][3];
			verifyCreateItemInPo(item);
			verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
			verifyTickIconcreateItem();
			verifyItemCalculationWithLineAmount(item);
			verifyPoUOM(UOM);
			verifyLineDetailVerification(item, Quantity, commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
			verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyUsd"), item);
		}
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Service Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticServiceSimilarUOMWithMultipleItems() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POAServiceSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][0];
			String Quantity = itemmultiple[i][1];
			String UnitCost = itemmultiple[i][2];
			String UOM = itemmultiple[i][3];
			verifyCreateItemInPo(item);
			verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
			verifyTickIconcreateItem();
			verifyItemCalculationWithLineAmount(item);
			verifyPoUOM(UOM);
			verifyLineDetailVerification(item, Quantity, commonFunc.getDataFromTestDataSheet("CurrencyInr"));
			verifyTaxCalculationAfterDiscount(commonFunc.getDataFromTestDataSheet("CurrencyInr"), item);
		}
		verifyPOTotalAmountCalculation();
		verifyTotalAmountCalculation();
		verifyGeneralTabTotalAmountCalculation();
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Methods Is Used To Verify DirectPO Domestic Material Dissimilar UOM With
	 * Multiple Item
	 * 
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyDirectPODomesticMaterialDifferentUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POwithUserDefinedItemDisimilarUomMatType"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {

			String ITEM_NAME = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			String UNIT_COST = itemmultiple[i][2];
			if (!ITEM_NAME.equals("")) {
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Item SuccessFully Added");
						flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, ITEM_NAME,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
													ElementType.Xpath);
											webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
											webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
													ElementType.Xpath);
											flag = webDB.waitForElement(PurchaseOrderPageLocators.PoUnitCostClick,
													ElementType.Xpath);
											if (flag) {
												commonFunc.hardWait(4000);
												webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick,
														ElementType.Xpath);
												webDB.clearText(PurchaseOrderPageLocators.PoUnitCost,
														ElementType.Xpath);
												webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoUnitCost,
														UNIT_COST, ElementType.Xpath);

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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("MaterialType"),
						commonFunc.getDataFromTestDataSheet("Kgs"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();

				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POSimilarItemSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import General Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportGeneralSimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POSimilarItemImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import Material Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportMaterialSimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POSimilarItemImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POSimilarItemSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Service Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POServiceItemSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import Service Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportServiceSimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POServiceItemImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PODissimilarItemSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PODissimilarItemSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import General Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportGeneralDissimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PODissimilarItemImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import Material Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOImportMaterialDissimilarUOMWithMultipleItemsWithExcelInput() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PODissimilarItemImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * Verify Enter Amunt For Discount Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyEnterAmountForDiscountCalculation(String TradePercentage, String SpecialPercent,
			String CashDisPercent) {
		webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TradePercentage, ElementType.Xpath);
		if (flag) {
			webDB.clearText(PurchaseOrderPageLocators.TradePercentage, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseOrderPageLocators.TradePercentage, TradePercentage, ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			verifyDomPropertyValidation(PurchaseOrderPageLocators.TradePercentage, TradePercentage);
			webDB.clearText(PurchaseOrderPageLocators.SpecialDiscountPercentage, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseOrderPageLocators.SpecialDiscountPercentage, SpecialPercent,
					ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			verifyDomPropertyValidation(PurchaseOrderPageLocators.SpecialDiscountPercentage, SpecialPercent);
			webDB.clearText(PurchaseOrderPageLocators.CashDiscountPercentage, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseOrderPageLocators.CashDiscountPercentage, CashDisPercent,
					ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			verifyDomPropertyValidation(PurchaseOrderPageLocators.CashDiscountPercentage, CashDisPercent);
		}
		assertTrue(flag);
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Deepan-GWL
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual And Expected Values");
		} else {
			logs.logging("INFO", "Mismatch With Actual And Expected");
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Line Amount Validation And POM
	 * 
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param value
	 */
	public void verifyLineAmountValidationAndPom(String Item, String value) {
		System.out.println("Line " + value + " Item " + Item);
		verifyAssertionBothValues(value, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("LINE_AMT")));

	}

	/**
	 * This Method Is Used To Verify Disount Calculation Verification
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param TradeAssembleVal
	 * @param TDAmt
	 * @param SDAssblVal
	 * @param SDAmt
	 * @param CDAssblVal
	 * @param CDAmt
	 */
	public void verifyDiscountCalculationVerification(String Item, String TradeAssembleVal, String TDAmt,
			String SDAssblVal, String SDAmt, String CDAssblVal, String CDAmt) {
		verifyAssertionBothValues(TradeAssembleVal, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_TRD_ASSBL_VAL")));
		verifyAssertionBothValues(TDAmt, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_TRD_DISC_AMT")));
		verifyAssertionBothValues(SDAssblVal, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_SPL_ASSBL_VAL")));
		verifyAssertionBothValues(SDAmt, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_SPL_DISC_AMT")));
		verifyAssertionBothValues(CDAssblVal, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_CASH_ASSBL_VAL")));
		verifyAssertionBothValues(CDAmt, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_CASH_DISC_AMT")));
	}

	/**
	 * This Method Is Used To Verify Tax Validation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Taxpercent
	 * @param Cgst
	 */
	public void verifyTaxValidation(String Item, String Taxpercent, String Cgst) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TickButtonLineOne, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.TickButtonLineOne, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();

		}
		webDB.switchToDefaultContent();
		try {
			WebElement element = webDB.getDriver()
					.findElement(By.xpath(PurchaseOrderPageLocators.LineDetailsCloseIcons));
			WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LocationHeaderId, ElementType.Xpath);
		verifyAssertionBothValues(Cgst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_CGST_AMT")));
		verifyAssertionBothValues(Taxpercent, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_TAX_PCT")));
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheet() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRSimilarItemSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];

						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier Service Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheet() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRServiceItemSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyNavigateLineDetailTwo();
						verifyAddServiceItem();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheet() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRSimilarItemImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier Service Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheet() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRServiceItemImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyNavigateLineDetailTwo();
						verifyAddServiceItem();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDomesticGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheet() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRDissimilarItemSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyImportGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheet() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRDissimilarItemImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];

						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	public void verifyLineAmountCalculationValidation(String GrossValue, String Item, String... Entity) {
		commonFunc.hardWait(5000);
		if (Entity.length > 0) {
			verifyAssertionBothValues(GrossValue, dbCommonFunc.PRLineDetailsQueryKare(VariableHandler.getPRNumber(),
					Item, commonFunc.getDataFromTestDataSheet("Line_Amount")));
		} else {
			verifyAssertionBothValues(GrossValue, dbCommonFunc.PRLineDetailsQuery(VariableHandler.getPRNumber(), Item,
					commonFunc.getDataFromTestDataSheet("Line_Amount")));
		}
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOWithMultipleItemsWithExcelInput(String Supplier, String POType, String POSheet) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"), Supplier, POType);
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, POSheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			} else {
				break;
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Tax Validation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Taxpercent
	 * @param Cgst
	 */
	public void taxValidation(String Item, String Taxpercent, String Cgst) {
//		verifyAssertionBothValues(Cgst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
//				commonFunc.getDataFromTestDataSheet("POL_CGST_AMT")));
//		verifyAssertionBothValues(Taxpercent, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
//				commonFunc.getDataFromTestDataSheet("POL_TAX_PCT")));
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPRtoPOWithMultipleItemInExcelSheet(String Supplier, String PRType, String Sheet,
			String Prefix) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), PRType, Prefix,
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(Supplier, commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPRtoPOServiceWithMultipleItemInExcelSheet(String Supplier, String PRType, String Sheet,
			String Prefix) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), PRType, Prefix,
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(Supplier,
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
//						String TaxPercent = itemmultiple[i][20];
//						String cgst = itemmultiple[i][21];
//						String TradePercentage = itemmultiple[i][8];
//						String SplDiscPercentage = itemmultiple[i][9];
//						String CashDiscPercentage = itemmultiple[i][10];
//						String TradeAssblValue = itemmultiple[i][11];
//						String TradeAmt = itemmultiple[i][12];
//						String SDAssemblValue = itemmultiple[i][13];
//						String SDAmt = itemmultiple[i][14];
//						String CDAssemblValue = itemmultiple[i][15];
//						String CDAmt = itemmultiple[i][16];
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyNavigateLineDetailTwo();
						verifyAddServiceItem();
//						navigateToLineDetailPage(item);
//						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
//						verifyNavigateLineDetailTwo();
//						verifyTaxValidation(item, TaxPercent, cgst);
//						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
//								CDAssemblValue, CDAmt);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier Service Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyServiceFromPRtoPOWithMultipleItemInExcelSheet(String Supplier, String PRType, String Sheet,
			String Prefix) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), PRType, Prefix,
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(Supplier,
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyNavigateLineDetailTwo();
						verifyAddServiceItem();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic Stock Transfer Similar Uom
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheet() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location02"),
				commonFunc.getDataFromTestDataSheet("U1Supplier"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POStockTransferItemSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			} else {
				break;
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * Verify Add Service Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddServiceItem() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ServiceType, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(PurchaseOrderPageLocators.ServiceType,
					commonFunc.getDataFromTestDataSheet("InsideType"), ElementType.Xpath);
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TickButtonLineOne, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.TickButtonLineOne, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();

		}
		webDB.switchToDefaultContent();
		try {
			WebElement element = webDB.getDriver()
					.findElement(By.xpath(PurchaseOrderPageLocators.LineDetailsCloseIcons));
			WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LocationHeaderId, ElementType.Xpath);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RM ITS
	 */
	public void verifyDirectPODomesticServiceSimilarUOMMultiple(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyPoWithGrnServiceFlagChecked();
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("ServiceType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click Add Icon
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDirectPoItemAddIconClicked() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button SuccessFully Clicked");

			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Select the item For Excel Sheet
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDirectPoItemSelectedFromLov(String ITEM_NAME) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.POPageItemTable, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.POPageItemTable, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ItemSelectButton, ITEM_NAME);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Entered the Ordered Quantity
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDirectPoQtyEntered(String QUANTITY) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1000);
			webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY, ElementType.Xpath);
			logs.logging("INFO", "Successfully Qty Entered");

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Entered the Ordered Unit Cost
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDirectPoUnitCostEntered(String UNIT_COST) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PoUnitCostClick, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1000);
			webDB.clearText(PurchaseOrderPageLocators.PoUnitCost, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoUnitCost, UNIT_COST, ElementType.Xpath);

			logs.logging("INFO", "Successfully Unit Cost Entered");

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Check the Flag for With GRN
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyPoWithGrnServiceFlagChecked() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PoServiceWithGrn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PoServiceWithGrn, ElementType.Xpath);
			logs.logging("INFO", "With GRN Click successfully");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.PoseriviceWithGrnSelectedValues, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByValue(PurchaseOrderPageLocators.PoseriviceWithGrnSelectedValues,
						commonFunc.getDataFromTestDataSheet("Y"), ElementType.Xpath);
				logs.logging("INFO", "With GRN Changed successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify PO Line wise Save
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDirectPoLineWiseSaved() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Line Is Saved Successfully");

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Entered Discount For UserDefined Item
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void discountFromPrToGrnDocument(int i1, String SheetName) {
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];
			String TradeDiscount = PRMultipleline[i1][8];
			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();

				String Type = webDB.getTextFromElement(PurchaseOrderPageLocators.POType, ElementType.Xpath);
				logs.logging("INFO", "PO Type is:" + Type);
				if (Type.equals(commonFunc.getDataFromTestDataSheet("ServiceType"))) {
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderLineDetailForServiceItem(i - 21),
							ElementType.Xpath);

					webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderLineDetailForServiceItem(i - 21),
							ElementType.Xpath);
					logs.logging("INFO", "Service Item");
				} else {
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i - 21),
							ElementType.Xpath);

					webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Non Service Item");
				}
				flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
					logs.logging("INFO", "Switched To frame");
					commonFunc.hardWait(3000);
					webDB.clearText(PurchaseOrderPageLocators.TradePercentage, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseOrderPageLocators.TradePercentage, TradeDiscount,
							ElementType.Xpath);
					webDB.pressEnterKey();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(3000);
					webDB.clearText(PurchaseOrderPageLocators.SpecialDiscountPercentage, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseOrderPageLocators.SpecialDiscountPercentage, SpecialDiscount,
							ElementType.Xpath);
					webDB.pressEnterKey();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(3000);
					webDB.clearText(PurchaseOrderPageLocators.CashDiscountPercentage, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseOrderPageLocators.CashDiscountPercentage, CashDiscount,
							ElementType.Xpath);
					webDB.pressEnterKey();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDeatilsSave, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseOrderPageLocators.LineDeatilsSave, ElementType.Xpath);
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(PurchaseOrderPageLocators.locationDetails, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						verifyDiscountAmountForPo(i1, SheetName);

					}
				}
			}
		}

		Assert.assertTrue(flag);
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
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Amount For Rate Contract
	 * Item UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyUnitcostAndDiscountAmountForOpenPoItem(int i1, String SheetName, String RateContractSupplier) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ItemId = PRMultipleline[i1][1];
			if (!ItemId.equals("")) {
				System.out.println("tradevalueup");
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ItemId,
								commonFunc.getDataFromTestDataSheet("OpenPoUnitCost")),
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoUnitCost")));
				logs.logging("INFO", "Open PO And  PO unit Cost Matched For This " + ItemId);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.OpenPoDetails(RateContractSupplier, ItemId,
								commonFunc.getDataFromTestDataSheet("OpenPoDiscPct")),
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("TradeDiscountPct")));
				logs.logging("INFO", "Open PO And  PO Discount % Matched For This " + ItemId);
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

	public void verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(int i1, String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Unit Cost For This Item");
		for (int i = i1; i <= i1; i++) {
			String ItemId = ItemMultiple[i1][1];
			if (!ItemId.equals("")) {
				String Cost = dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PoUnitCost"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + Cost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 6, Cost);
				String TradeDiscountPct = dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("TradeDiscountPct"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + TradeDiscountPct);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 8, TradeDiscountPct);

			}
		}

	}

	/**
	 * This Method Is Used To Entered Other Than User Defined Item To Give Discount
	 * Amount
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	private void otherThanUserdefinedDiscountAmount(int i1, String SheetName) {
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		System.out.println(PRMultipleline.length);
		for (int i = i1; i <= i1; i++) {
			String ITEM_NAME = PRMultipleline[i1][1];

			String SpecialDiscount = PRMultipleline[i1][9];
			String CashDiscount = PRMultipleline[i1][10];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				String Type = webDB.getTextFromElement(PurchaseOrderPageLocators.POType, ElementType.Xpath);
				logs.logging("INFO", "PO Type is:" + Type);
				if (Type.equals(commonFunc.getDataFromTestDataSheet("ServiceType"))) {
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderLineDetailForServiceItem(i - 21),
							ElementType.Xpath);
					webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderLineDetailForServiceItem(i - 21),
							ElementType.Xpath);
					logs.logging("INFO", "Service Item");
				} else {
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i - 21),
							ElementType.Xpath);
					webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderLineDetail(i - 21), ElementType.Xpath);
					logs.logging("INFO", "Non Service Item");
				}
				flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
					logs.logging("INFO", "Switched To frame");
					commonFunc.hardWait(3000);
					webDB.clearText(PurchaseOrderPageLocators.SpecialDiscountPercentage, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseOrderPageLocators.SpecialDiscountPercentage, SpecialDiscount,
							ElementType.Xpath);
					webDB.pressEnterKey();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(3000);
					webDB.clearText(PurchaseOrderPageLocators.CashDiscountPercentage, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseOrderPageLocators.CashDiscountPercentage, CashDiscount,
							ElementType.Xpath);
					webDB.pressEnterKey();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDeatilsSave, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseOrderPageLocators.LineDeatilsSave, ElementType.Xpath);
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(PurchaseOrderPageLocators.locationDetails, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						verifyDiscountAmountForPo(i1, SheetName);
					}
				}

			}
		}
		Assert.assertTrue(flag);

	}

	/**
	 * This Methods Method to handle Create the PO Document for Rate
	 * ContractItem(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterial(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Supplier for material Type
	 * with Multiple Item For UserDefined Item
	 * 
	 * @author PremKumar.K-RM ITs
	 * @throws IOException
	 */
	public void verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		webDB.waitForElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath);
		System.out.println("This The Value:" + commonFunc.getDataFromTestDataSheet("Value") + ":");
		String PONumbervalue = webDB.getDomAttributeFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic General with Multiple Item
	 * 
	 * @author PremKumar.K-RM ITS
	 * @throws IOException
	 */
	public void verifyDirectPODomesticGeneralSimilarUOMWithMultipleItem(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Verify To create PO with RateContract Item for
	 * Service Type
	 * 
	 * @author PremKumar.K-RMITS @
	 */
	public void verifyDirectPODomesticRateContractItemSimilarUOMMultipleServiceType(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPageForPurchaseUser();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyPoWithGrnServiceFlagChecked();
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("ServiceType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(General Type)
	 * 
	 * @author PremKumar.K-RM ITS @
	 * 
	 */
	public void verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlan(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Amount For Price List
	 * Item UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyUnitcostAndDiscountAmountForPriceListItem(int i1, String SheetName, String PriceListSupplier) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ItemId = PRMultipleline[i1][1];
			if (!ItemId.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ItemId,
								commonFunc.getDataFromTestDataSheet("PriceListUnitCost")),
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("PoUnitCost")));
				logs.logging("INFO", "PriceList And PO unit Cost Matched For This " + ItemId);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(
						dbCommonFunc.priceListDetails(PriceListSupplier, ItemId,
								commonFunc.getDataFromTestDataSheet("PriceListDiscPct")),
						dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
								commonFunc.getDataFromTestDataSheet("TradeDiscountPct")));
				logs.logging("INFO", "PriceList And PO Discount % Matched For This " + ItemId);
			}
		}

	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write Price
	 * List Item
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	private void verifyPriceListItemUnitCostAndDiscPctWriteInExcel(int i1, String SheetName) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Unit Cost For This Item");
		for (int i = i1; i <= i1; i++) {
			String ItemId = ItemMultiple[i1][1];
			if (!ItemId.equals("")) {
				String Cost = dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PoUnitCost"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + Cost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 6, Cost);
				String TradeDiscountPct = dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("TradeDiscountPct"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + TradeDiscountPct);
				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 8, TradeDiscountPct);

			}
		}

	}

	/**
	 * This Methods Is Used To Verify DirectPO Domestic General Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItem(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The Discount Amount For PO Document
	 * 
	 * @author PremKumar.K-RM ITS
	 */
	public void verifyDiscountAmountForPo(int i2, String SheetName) {
		String[][] PRMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		PRMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i2; i <= i2; i++) {
			String ItemId = PRMultipleline[i2][1];
			String TradeDiscountAmount = PRMultipleline[i2][12];
			String SpecialDiscountAmount = PRMultipleline[i2][14];
			String CashDiscountAmount = PRMultipleline[i2][16];
			String TradeAssessbleValue = PRMultipleline[i2][11];
			String SpecialAssessbleValue = PRMultipleline[i2][13];
			String CashAssessbleValue = PRMultipleline[i2][15];
			String LineAmount = PRMultipleline[i2][18];
			String TaxAmountCGST = PRMultipleline[i2][21];
			String TaxAmountSGST = PRMultipleline[i2][22];
			String TaxAmountIGST = PRMultipleline[i2][23];
			String TaxAmountUTGST = PRMultipleline[i2][24];
			String TaxAmountCESS = PRMultipleline[i2][26];
			if (!ItemId.equals("")) {
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("TradeAssessableValue")), TradeAssessbleValue);
				logs.logging("INFO", "Trade Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("SpecialAssessableValue")), SpecialAssessbleValue);
				logs.logging("INFO", "Special Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("CashAssessableValue")), CashAssessbleValue);
				logs.logging("INFO", "Cash Assessable Value Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("TradeDiscountAmount")), TradeDiscountAmount);
				logs.logging("INFO", "Trade Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("SpecialDiscountAmount")), SpecialDiscountAmount);
				logs.logging("INFO", "Special Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("CashDiscountAmount")), CashDiscountAmount);
				logs.logging("INFO", "Cash Discount Amount Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("LineAmount")), LineAmount);
				logs.logging("INFO", "Line Amount" + LineAmount + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("POCgstAmount")), TaxAmountCGST);
				logs.logging("INFO", "Tax Amount CGST " + TaxAmountCGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PoSgstAmount")), TaxAmountSGST);
				logs.logging("INFO", "Tax Amount SGST" + TaxAmountSGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PoIgstAmount")), TaxAmountIGST);
				logs.logging("INFO", "Tax Amount IGST" + TaxAmountIGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PoUtgstAmount")), TaxAmountUTGST);
				logs.logging("INFO", "Tax Amount UTGST" + TaxAmountUTGST + "For This Item" + ItemId);
				verifyAssertionBothValues(dbCommonFunc.poDetails(VariableHandler.getPONumber(), ItemId,
						commonFunc.getDataFromTestDataSheet("PoCessAmount")), TaxAmountCESS);
				logs.logging("INFO", "Tax Amount CESS Amount" + TaxAmountCESS + "For This Item" + ItemId);
			}
		}
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(Material Type)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItemWithGateEntry() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POMultipleItemDetailsSheetName"));
		System.out.println(itemmultiple.length);
		for (int i = 1; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][0];
			String QUANTITY = itemmultiple[i][1];
			String UNIT_COST = itemmultiple[i][2];
			if (!ITEM_NAME.equals("")) {
				flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Item SuccessFully Added");
						flag = webDB.waitForElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseOrderPageLocators.InputItemPO, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseOrderPageLocators.ItemSelectButton, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseOrderPageLocators.POItemInput, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(PurchaseOrderPageLocators.POItemInput, ITEM_NAME,
											ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseOrderPageLocators.PoItemSelect,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(PurchaseOrderPageLocators.PoItemSelect, ElementType.Xpath);
										flag = webDB.waitForElement(PurchaseOrderPageLocators.PoQtyFieldClick,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(PurchaseOrderPageLocators.PoQtyFieldClick,
													ElementType.Xpath);
											webDB.clearText(PurchaseOrderPageLocators.PoQtyInput, ElementType.Xpath);
											webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoQtyInput, QUANTITY,
													ElementType.Xpath);
											flag = webDB.waitForElement(PurchaseOrderPageLocators.PoUnitCostClick,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(PurchaseOrderPageLocators.PoUnitCostClick,
														ElementType.Xpath);
												commonFunc.hardWait(2000);
												webDB.clearText(PurchaseOrderPageLocators.PoUnitCost,
														ElementType.Xpath);
												webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoUnitCost,
														UNIT_COST, ElementType.Xpath);

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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
		commonFunc.hardWait(3000);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PolinewiseSave, ElementType.Xpath);
			verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
			commonFunc.hardWait(3000);
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("CurrencyInr"),
						commonFunc.getDataFromTestDataSheet("MaterialType"),
						commonFunc.getDataFromTestDataSheet("UomNos"));
				approvalFunc.verifyNavigateback();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
				flag = commonFunc.logoutUser();
				if (flag) {
					commonFunc.validCommonLogin(System.getProperty("SelvaUsername"),
							System.getProperty("SelvaPassword"));
					commonFunc.navigatePurchaseOrderPage();
					verifyValidatePoStatus(VariableHandler.getPONumber(),
							commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					flag = commonFunc.logoutUser();

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Filter Search Purchase Order
	 * 
	 * @author Deepan-GWL
	 * @param LovButton
	 * @param Value
	 * @param i
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void verifyAmendFilterSearchSOB(String LovButton, String Value, int i, String Type, String selectflag) {
		webDB.scrollToAnElement(LovButton, ElementType.Xpath);
		if (selectflag.equals(commonFunc.getDataFromTestDataSheet("LovButton"))) {
			commonFunc.searchAndSelectValueInFilters(LovButton, Value);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SelectType"))) {
			webDB.selectDropDownByText(LovButton, Value, ElementType.Xpath);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SendTextInput"))) {
			webDB.sendTextToAnElement(LovButton, Value, ElementType.Xpath);
		}
		if (Type.equals(commonFunc.getDataFromTestDataSheet("HeaderSearchType"))) {
			clickSearchButton();
			commonFunc.hardWait(3000);
			verifyAmendTableValue(i, Value);
		} else if (Type.equals(commonFunc.getDataFromTestDataSheet("LineSearchType"))) {
			clickLineSearchButton();
			commonFunc.hardWait(3000);
			verifyLineAmendTableValue(i, Value);
		}
		clearSearchFilter();
	}

	/**
	 * Verify Date From And To
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDateAfterAndBefore() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PoDtFrom, ElementType.Xpath);
		webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoDtFrom, commonFunc.getDataFromTestDataSheet("EffFrom"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueAfterDate(7, commonFunc.getDataFromTestDataSheet("EffFrom"));
		clearSearchFilter();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PoDtTo, ElementType.Xpath);
		webDB.sendTextToAnElement(PurchaseOrderPageLocators.PoDtTo, commonFunc.getDataFromTestDataSheet("EffTo"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueBeforeDate(7, commonFunc.getDataFromTestDataSheet("EffTo"));
		clearSearchFilter();
//		flag = webDB.waitForElement(PurchaseOrderPageLocators.RequestDtFrom, ElementType.Xpath);
//		webDB.sendTextToAnElement(PurchaseOrderPageLocators.RequestDtFrom,
//				commonFunc.getDataFromTestDataSheet("EffFrom"), ElementType.Xpath);
//		clickLineSearchButton();
//		commonFunc.hardWait(3000);
//		verifyLineAmendTableValueAfterDate(6, commonFunc.getDataFromTestDataSheet("EffFrom"));
//		clearSearchFilter();
//		flag = webDB.waitForElement(PurchaseOrderPageLocators.RequestDtTo, ElementType.Xpath);
//		webDB.sendTextToAnElement(PurchaseOrderPageLocators.RequestDtTo, commonFunc.getDataFromTestDataSheet("EffTo"),
//				ElementType.Xpath);
//		clickLineSearchButton();
//		commonFunc.hardWait(3000);
//		verifyLineAmendTableValueBeforeDate(6, commonFunc.getDataFromTestDataSheet("EffTo"));
//		clearSearchFilter();
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header CheckBox Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseOrderPageLocators.AmendFindTable, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyAmendTableValue(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.AmendFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				flag = element.getText().contains(Value);
				if (!flag) {
					break;
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyAmendTableValueAfterDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.AmendFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isAfter(date2);
				if (!flag) {
					if (date1.equals(date2)) {
					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyAmendTableValueBeforeDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.AmendFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isBefore(date2);
				if (!flag) {
					if (date1.equals(date2)) {

					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyLineAmendTableValueAfterDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.AmendLineFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isAfter(date2);
				if (!flag) {
					if (date1.equals(date2)) {
					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyLineAmendTableValueBeforeDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.AmendLineFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date1 = LocalDate.parse(element.getText(), formatter);
				LocalDate date2 = LocalDate.parse(Value, formatter);
				flag = date1.isBefore(date2);
				if (!flag) {
					if (date1.equals(date2)) {

					} else {
						break;
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyLineAmendTableValue(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.AmendLineFindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				flag = element.getText().contains(Value);
				if (elements.getLast() == element) {
					flag = true;
				}
				if (!flag) {
					break;
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickLineSearchButton() {
		flag = webDB.waitForElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line CheckBox Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseOrderPageLocators.AmendLineFindTable, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Clear Search Filter
	 * 
	 * @author Deepan-GWL
	 */
	public void clearSearchFilter() {
		flag = webDB.waitForElement(OpenPoRcLocators.ClearButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.ClearButton, ElementType.Xpath);
			logs.logging("INFO", "Clear Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To PO Filter All Field Working
	 * 
	 * @author Deepan-GWL
	 */
	public void poFilterAllFieldWorking() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyDateAfterAndBefore();
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.LocId, commonFunc.getDataFromTestDataSheet("Loc_100"), 3,
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.Unit, commonFunc.getDataFromTestDataSheet("Unit_100"), 4,
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.PrNo, commonFunc.getDataFromTestDataSheet("PrNumber"), 79,
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.PONo, commonFunc.getDataFromTestDataSheet("PONumber"), 9,
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.SupplierNameLovButton,
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"), 12,
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.BuyerLovButton,
				commonFunc.getDataFromTestDataSheet("SelvaKumarB"), 10,
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.POTypeSelect,
				commonFunc.getDataFromTestDataSheet("PoMaterial"), 7,
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("SelectType"));
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.ItemId,
				commonFunc.getDataFromTestDataSheet("ShareOfBusinessItem"), 17,
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.ItemDesc,
				commonFunc.getDataFromTestDataSheet("ShareOfBusinessItem"), 19,
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("SendTextInput"));
		verifyAmendFilterSearchSOB(PurchaseOrderPageLocators.PO_Status,
				commonFunc.getDataFromTestDataSheet("ApproveStatus"), 15,
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("SelectType"));
		commonFunc.logoutUser();

	}

	/**
	 * This Methods Is Used To Verify Direct PO Domestic Price List Item Similar UOM
	 * Multiple Service Type
	 * 
	 * @author PremKumar.K-RMITS @
	 */
	public void verifyDirectPODomesticPriceListItemSimilarUOMMultipleServiceType(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyPoWithGrnServiceFlagChecked();
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("POSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("ServiceType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(Material Type)
	 * 
	 * @author PremKumar.K-RM ITS @
	 * 
	 */
	public void verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanForMaterialType(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic General with Multiple Item
	 * 
	 * @author PremKumar.K-RM ITS @
	 */
	public void verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemWithoutQualityEnabled(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);

	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic Supplier for material Type
	 * with Multiple Item For UserDefined Item
	 * 
	 * @author PremKumar.K-RM ITs @
	 */

	public void verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItemWithOutQuality(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		webDB.waitForElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath);
		System.out.println("This The Value:" + commonFunc.getDataFromTestDataSheet("Value") + ":");
		String PONumbervalue = webDB.getDomAttributeFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBDETAILS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String UNIT_COST = itemmultiple[i][6];
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify Direct PO with RateContract Item for Similar
	 * UOM(General)WithoutQuality Enabled
	 * 
	 * @author PremKumar.K-RM ITS @
	 * 
	 */
	public void verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForWithoutQualityEnabled(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the PO Document for Rate
	 * ContractItem(Material)
	 * 
	 * @author PremKumar.K-RMITS @
	 */

	public void verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterialWithoutQualityEnabled(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(General Type) Without Quality Enabled
	 * 
	 * @author PremKumar.K-RM ITS @
	 * 
	 */
	public void verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanWithoutQualityEnabled(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(Material Type) WithoutQuality Enabled
	 * 
	 * @author PremKumar.K-RM ITS @
	 * 
	 */
	public void verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanForMaterialTypeWithoutQualityEnabled(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("UomNos"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify DirectPO Domestic General Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemWithoutQualityEnabled(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify DirectPO Domestic Material Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public void verifyDirectPODomesticMaterialDifferentUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		webDB.waitForElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath);
		System.out.println("This The Value:" + commonFunc.getDataFromTestDataSheet("Value") + ":");
		String PONumbervalue = webDB.getDomAttributeFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with RateContract Item for Dissimilar
	 * UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS @
	 * 
	 */
	public void verifyDirectPoWithRateContractItemWithAndWithoutInspectionGeneralTypeForDissimilarUOM(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the PO Document for Rate
	 * ContractItem(Material)
	 * 
	 * @author PremKumar.K-RMITS @
	 */

	public void verifyDirectPoWithRateContractItemWithAndWithoutInspectionMaterialForDifferentUom(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("OpenPOSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPOSupplier"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(General Type)
	 * 
	 * @author PremKumar.K-RM ITS @
	 * 
	 */
	public void verifyDirectPODomesticPricelistItemWithItemandWithoutInspectionPlanForDifferentUom(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
					commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(Material Type)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void verifyDirectPODomesticDifferentUomPricelistItemWithItemandWithoutInspectionPlanForMaterialType(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"), commonFunc.getDataFromTestDataSheet("Kgs"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify From PR to PO - Domestic Supplier Material Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(String Supplier, String PRType, String Sheet,
			String Prefix) {
		commonFunc.validCommonLogin(System.getProperty("ErpAdmin"), System.getProperty("ErpAdminPassword"),
				"ErpUserName");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("U1"), PRType, Prefix, commonFunc.getDataFromTestDataSheet("T1001"),
				commonFunc.getDataFromTestDataSheet("PR_Department"), commonFunc.getDataFromTestDataSheet("ErpAdmin"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				String GlAccNumber = itemmultiple[i][8];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
				PR_Func.clickLineDetailsIconForLastitem(item);
				PR_Func.verifyAddGlAccountNumber(GlAccNumber);
			} else {
				break;
			}
		}

		PR_Func.saveHeaderAndClickPostWithDevaUser();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("ErpAdmin"), System.getProperty("ErpAdminPassword"),
				"Erp Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(Supplier,
				commonFunc.getDataFromTestDataSheet("MaterialPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ErpUserId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("DevaUserName"));
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * verify PRtoPO With Multiple Item In ExcelSheet For Material With Domestic
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyPRtoPOWithMultipleItemInExcelSheetForMaterialWithDomestic() {
		verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(commonFunc.getDataFromTestDataSheet("AsianLeathers"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("PRTOPOSimilarItemMaterialSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
	}

	/**
	 * verify PRtoPO With Multiple Item In ExcelSheet For Material With Import
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyPRtoPOWithMultipleItemInExcelSheetForMaterialWithImport() {
		verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(commonFunc.getDataFromTestDataSheet("HatsunSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("PRTOPOSimilarItemMaterialImpSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
	}

	/**
	 * verify PRtoPO With Multiple Item In ExcelSheet For Material With Domestic
	 * Dissimilar
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyPRtoPOWithMultipleItemInExcelSheetForMaterialWithDomesticDissimilar() {
		verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(commonFunc.getDataFromTestDataSheet("AsianLeathers"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("PRTOPODissimilarItemMaterialSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
	}

	/**
	 * verify PRtoPO With MultipleItem In ExcelSheet For Material With Import
	 * Dissmilar
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyPRtoPOWithMultipleItemInExcelSheetForMaterialWithImportDissimilar() {
		verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(commonFunc.getDataFromTestDataSheet("HatsunSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("PRTOPODissimilarImportMaterialSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
	}

	/**
	 * This Methods Method to handle Verify To create PO with RateContract Item for
	 * Service Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void verifyDirectPODomesticRateContractItemSimilarUOMMultipleServiceTypeWithIgstSupplier(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location14"),
				commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyPoWithGrnServiceFlagChecked();
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("ServiceType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/*
	 * This Method Is Used To Direct PO General Similar UOM Multiple Item With Excel
	 * Input Domestic Local Reg
	 * 
	 * @author Deepan-GWL
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPOGeneralSimilarUOMMultipleItemsWithExcelInputWithDomesticLocalReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POSimilarItemLocalSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
//				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Tax Validation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Taxpercent
	 * @param Cgst
	 */
	public void verifyTaxValidation(String Item, String Taxpercent, String Cgst, String Sgst, String Igst, String Utgst,
			String CessPercent, String CessAmount) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.TickButtonLineOne, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.TickButtonLineOne, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();

		}
		webDB.switchToDefaultContent();
		try {
			WebElement element = webDB.getDriver()
					.findElement(By.xpath(PurchaseOrderPageLocators.LineDetailsCloseIcons));
			WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LocationHeaderId, ElementType.Xpath);
		verifyAssertionBothValues(Cgst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_CGST_AMT")));
		verifyAssertionBothValues(Sgst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("PoSgstAmount")));
		verifyAssertionBothValues(Igst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("PoIgstAmount")));
		verifyAssertionBothValues(Utgst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("PoUtgstAmount")));
		verifyAssertionBothValues(CessPercent, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("Pol_Cess_pct")));
//		verifyAssertionBothValues(CessAmount, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
//				commonFunc.getDataFromTestDataSheet("Pol_Cess_Amt")));
		verifyAssertionBothValues(Taxpercent, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_TAX_PCT")));
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM With
	 * Multiple Item Interstate Reg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOGeneralSimilarUOMMultipleItemsWithExcelInputWithDomesticInterstateReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POSimilarItemInterstateSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM With
	 * Multiple Item Local Un Registration
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POSimilarItemLocalUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM With
	 * Multiple Item With Un Registration
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POSimilarItemInterstateUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Service Similar UOM With
	 * Excel Sheet With Local Reg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInputWithLocalReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POServiceItemLocalSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Service Similar UOM With
	 * Multiple Item With Interstate Reg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInputWithInterstateReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POServiceItemInterstateSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Service Similar UOM With
	 * Multiple Item With Local UnReg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POServiceItemLocalUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Service Similar UOM With
	 * Multiple Item With Interstate UnReg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POServiceItemInterstateUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
//				commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.GlAccountNoLovButton,
//						commonFunc.getDataFromTestDataSheet("GeneralExpense"));
				webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import General Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOGeneralSimilarUOMWithMultipleItemsWithExcelInputWithImport() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POSimilarImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Methos Is Used To Verify Direct Po Service Similar UOM With Multiple
	 * Item With Excel Sheet With Import
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOServiceSimilarUOMWithMultipleItemsWithExcelInputWithImport() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POServiceImportSheeet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Similar UOM With
	 * Multiple Item With Local Reg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInputWithLocalReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POSimilarItemLocalSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Similar UOM With
	 * Multiple Item With Interstate Reg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInputWithInterstateReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POSimilarItemInterstateSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Similar UOM With
	 * Multiple Item With Local UnReg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POSimilarItemLocalUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Similar UOM With
	 * Multiple Item With Interstate Un Reg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POSimilarItemInterstateUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Material Similar UOM With Multiple
	 * Item With Import
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOMaterialSimilarUOMWithMultipleItemsWithExcelInputWithImport() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POSimilarImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Domestic General Similar PR To PO With Local
	 * Registered
	 * 
	 * @return boolean
	 * @author Deepan-GWL
	 */
	public boolean verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRSimilarItemLocalRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To verify Domestic General Similar UOM From PR to PO With
	 * Multiple Item In Excel Sheet With Interstate Reg
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRDissimilarItemInterstateRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Add HSN Code
	 * 
	 * @param Item
	 * @param LovButton
	 * @param LovInputLocator
	 * @param LovValue
	 */
	public void addHsnCode(String Item, String LovButton, String LovInputLocator, String LovValue) {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.HsnCodeTableWithItem(Item), ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(PurchaseOrderPageLocators.HsnCodeTableWithItem(Item), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(LovButton, LovValue);
			flag = webDB.waitForElement(LovInputLocator, ElementType.Xpath);
			if (flag) {
				String value = webDB.getAttributeFromElement(LovInputLocator, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Text Value from Table Is: " + value);
				flag = value.contains(LovValue);
				if (flag) {
					logs.logging("INFO", "Verified Value Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To verify Domestic General Similar UOM From PR to PO With
	 * Multiple Item In Excel Sheet With Local UnReg
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRSimilarItemLocalUnRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To verify Domestic General Similar UOM From PR to PO With
	 * Multiple Item In Excel Sheet With Interstate UnReg
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRSimilarItemInterstateUnRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To verify Import General Similar UOM From PR to PO With
	 * Multiple Item In Excel Sheet
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithImport() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRSimilarImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	// --------------------------------------------------------------------------------------------------------------------

	/**
	 * This Method Is Used To Verify Service Similar UOM From PR to PO With Multiple
	 * Item In Excel Sheet With Local Registered
	 * 
	 * @return boolean
	 * @author Deepan-GWL
	 */
	public boolean verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRServiceItemLocalRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
//						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.GlAccountNoLovButton,
//								commonFunc.getDataFromTestDataSheet("GeneralExpense"));
//						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyNavigateLineDetailTwo();
						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ServiceItemButton,
								commonFunc.getDataFromTestDataSheet("Equip1000"));
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Verify Service Similar UOM From PR to PO With Multiple
	 * Item In Excel Sheet With Interstate Registered
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRServiceItemInterstateRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
//						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.GlAccountNoLovButton,
//								commonFunc.getDataFromTestDataSheet("GeneralExpense"));
//						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyNavigateLineDetailTwo();
						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ServiceItemButton,
								commonFunc.getDataFromTestDataSheet("Equip1000"));
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Verify Service Similar UOM From PR to PO With Multiple
	 * Item In Excel Sheet With Local UnRegistered
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRServiceItemLocalUnRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
//						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.GlAccountNoLovButton,
//								commonFunc.getDataFromTestDataSheet("GeneralExpense"));
//						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyNavigateLineDetailTwo();
						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ServiceItemButton,
								commonFunc.getDataFromTestDataSheet("Equip1000"));
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Verify Service Similar UOM From PR to PO With Multiple
	 * Item In Excel Sheet With Interstate UnRegistered
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRServiceItemInterstateUnRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
//						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.GlAccountNoLovButton,
//								commonFunc.getDataFromTestDataSheet("GeneralExpense"));
//						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyNavigateLineDetailTwo();
						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ServiceItemButton,
								commonFunc.getDataFromTestDataSheet("Equip1000"));
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Verify Service Similar UOM From PR to PO With Multiple
	 * Item In Excel Sheet With Import
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithImport() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRServiceImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ServiceItemButton,
								commonFunc.getDataFromTestDataSheet("Equip1000"));
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To verify Direct Po Domestic General Dissimilar UOM With
	 * Local Reg
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithLocalReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PODissimilarItemLocalSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Dissimilar UOM With
	 * Multiple Item With Interstate Registered
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithInterstateReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POSimilarItemInterstateSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Dissimilar UOM With
	 * Multiple Item With Local Unregistered
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PODissimilarItemLocalUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Dissimilar UOM With
	 * Multiple Item With Interstate Unregistered
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PODissimilarItemInterstateUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import General Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithImport() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PODissimilarImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct Po Domestic Material Dissimilar Uom With
	 * Local Registered
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithLocalReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PODissimilarItemLocalSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Dissimilar UOM With
	 * Multiple Item With Interstate UOM
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithInterstateReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POSimilarItemInterstateSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Dissimilar UOM With
	 * Multiple Item With Local UnRegistered
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PODissimilarItemLocalUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic Material Dissimilar UOM With
	 * Multiple Item With Interstate UnRegistered
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PODissimilarItemInterstateUnRSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Import Material Dissimilar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithImport() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PODissimilarImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Domestic General Dissimilar PR To PO Local
	 * Registered
	 * 
	 * @return boolean
	 * @author Deepan-GWL
	 */
	public boolean verifyDomesticGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRDissimilarItemLocalRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Verify Domestic General Dissimilar UOM Pr To Po With
	 * Interstate Registered
	 * 
	 * @return boolean
	 * @author Deepan-GWL
	 */
	public boolean verifyDomesticGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRSimilarItemInterstateRgSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Verify Domestic General Dissimilar UOM From PR To PO
	 * Local UnRegistered
	 * 
	 * @return boolean
	 * @author Deepan-GWL
	 */
	public boolean verifyDomesticGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRDissimilarItemLocalUnRegSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Verify Domestic General Dissimilar UOM PR To PO With
	 * Interstate UnRegistered *
	 * 
	 * @return boolean
	 * @author Deepan-GWL
	 */
	public boolean verifyDomesticGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateUnReg() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("PRDissimilarItemInterstateUnregSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Verify General Dissimilar UOM PR TO PO With Import
	 * 
	 * @return boolean
	 * @author Deepan-GWL
	 */
	public boolean verifyGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithImport() {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("PR_General"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"), commonFunc.getDataFromTestDataSheet("PR_CPCCode"),
				commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("PRDissimilarImportSheet"));
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier Material Item (Similar UOM)
	 * 
	 * @return boolean
	 * @author Deepan-GWL
	 */
	public boolean verifyPRtoPOWithMultipleItemInExcelSheetForMaterialType(String Supplier, String PRType, String Sheet,
			String Prefix) {
		commonFunc.validCommonLogin(System.getProperty("ErpAdmin"), System.getProperty("ErpAdminPassword"),
				"ErpUserName");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("U1"), PRType, Prefix, commonFunc.getDataFromTestDataSheet("T1001"),
				commonFunc.getDataFromTestDataSheet("PR_Department"), commonFunc.getDataFromTestDataSheet("ErpAdmin"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				String GlAccNumber = itemmultiple[i][8];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
				PR_Func.clickLineDetailsIconForLastitem(item);
				PR_Func.verifyAddGlAccountNumber(GlAccNumber);
			} else {
				break;
			}
		}

		PR_Func.saveHeaderAndClickPostWithDevaUser();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("ErpAdmin"), System.getProperty("ErpAdminPassword"),
				"Erp Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(Supplier,
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String LineAmount = itemmultiple[i][7];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ErpUserId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("DevaUserName"));
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(String Supplier, String POType,
			String POSheet) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"), Supplier, POType);
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, POSheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			} else {
				break;
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * This Method Is Used To Verify Tax Validation
	 * 
	 * @param Item
	 * @param Taxpercent
	 * @param Cgst
	 * @param Sgst
	 * @param Igst
	 * @param Utgst
	 * @param CessPercent
	 * @param CessAmount
	 */
	public void verifyTaxValueValidation(String Item, String Taxpercent, String Cgst, String Sgst, String Igst,
			String Utgst, String CessPercent, String CessAmount) {
		verifyAssertionBothValues(Cgst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_CGST_AMT")));
		verifyAssertionBothValues(Sgst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("PoSgstAmount")));
		verifyAssertionBothValues(Igst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("PoIgstAmount")));
		verifyAssertionBothValues(Utgst, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("PoUtgstAmount")));
		verifyAssertionBothValues(CessPercent, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("Pol_Cess_pct")));
		verifyAssertionBothValues(CessAmount, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("Pol_Cess_Amt")));
		verifyAssertionBothValues(Taxpercent, dbCommonFunc.poDetails(VariableHandler.getPONumber(), Item,
				commonFunc.getDataFromTestDataSheet("POL_TAX_PCT")));
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM With
	 * Multiple Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(String Supplier, String POType,
			String POSheet) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"), Supplier, POType);
		verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, POSheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String TaxAmount = itemmultiple[i][27];
				String NetAmount = itemmultiple[i][28];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				verifyCreateItemInPo(item);
				verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				verifyTickIconcreateItem();
				verifyLineAmountValidationAndPom(item, LineAmount);
				navigateToLineDetailPage(item);
				verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
//				commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.GlAccountNoLovButton,
//						commonFunc.getDataFromTestDataSheet("GeneralExpense"));
				webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
				verifyNavigateLineDetailTwo();
				verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);

			} else {
				break;
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HodUsername"));
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
				commonFunc.navigatePurchaseOrderPage();
				verifyValidatePoStatus(VariableHandler.getPONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}

		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier General Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(String Supplier, String PRType,
			String Sheet, String Prefix) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), PRType, Prefix,
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				System.out.println(item);
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
				PR_Func.clickLineDetailsIconForLastitem(item);
				PR_Func.verifyAddGlAccountNumber(commonFunc.getDataFromTestDataSheet("GlAccDescription"));
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(Supplier, commonFunc.getDataFromTestDataSheet("POPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyNavigateLineDetailTwo();
						verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Verify From PR to PO - Domestic Supplier Service Item (Similar UOM)
	 * 
	 * @return boolean
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyServiceFromPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(String Supplier, String PRType,
			String Sheet, String Prefix) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseRequestPage();
		PR_Func.clickAddIconFillDetailsOfPurchaseRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), PRType, Prefix,
				commonFunc.getDataFromTestDataSheet("PR_CPCCode"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("PR_Requester"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals("Total"))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String GrossValue = itemmultiple[i][7];
				PR_Func.fillItemsQuantityCostAndSaveLine(item, Quantity, UnitCost);
				verifyLineAmountCalculationValidation(GrossValue, item);
			} else {
				break;
			}
		}
		PR_Func.saveHeaderAndClickPost();
		PR_Func.searchPRAndCheckPRStatus(VariableHandler.getPRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"),
				"SelvaUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(Supplier,
				commonFunc.getDataFromTestDataSheet("ServicePOPrefix"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderGrn"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String LineAmount = itemmultiple[i][7];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						String TradeAssblValue = itemmultiple[i][11];
						String TradeAmt = itemmultiple[i][12];
						String SDAssemblValue = itemmultiple[i][13];
						String SDAmt = itemmultiple[i][14];
						String CDAssemblValue = itemmultiple[i][15];
						String CDAmt = itemmultiple[i][16];
						String TaxPercent = itemmultiple[i][20];
						String cgst = itemmultiple[i][21];
						String sgst = itemmultiple[i][22];
						String Igst = itemmultiple[i][23];
						String Utgst = itemmultiple[i][24];
						String CessPercent = itemmultiple[i][25];
						String Cess = itemmultiple[i][26];
						String TaxAmount = itemmultiple[i][27];
						String NetAmount = itemmultiple[i][28];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
						addHsnCode(item, PurchaseOrderPageLocators.HsnCodeLovButton,
								PurchaseOrderPageLocators.HsnCodeInput, HsnCode);
						verifyTickIconcreateItem();
						verifyLineAmountValidationAndPom(item, LineAmount);
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
//						commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.GlAccountNoLovButton,
//								commonFunc.getDataFromTestDataSheet("GeneralExpense"));
						webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
						verifyNavigateLineDetailTwo();
						verifyAddServiceItem();
						verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
						verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
								CDAssemblValue, CDAmt);

					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyApprovePOUsingAshokUser();
		verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To verify To Create PO For Stock Transfer RateContract
	 * Same Stock And Supplier UOM With And WithoutInspection
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void verifyToCreatePoForStockTransferRateContractSameStockandSupplierUomWithAndWithoutInspection(
			String SheetName) throws IOException {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0002"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("StockTransferSupplier"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("STTYPE"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify To Create PO For Stock Transfer UserDefined
	 * Same Stock and Supplier UOM With And WithoutInspection
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToCreatePoForStockTransferUserDefinedDissimilarUomWithAndWithoutInspection(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0002"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplier"),
				commonFunc.getDataFromTestDataSheet("StockTransferValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("STTYPE"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic General with Multiple Item
	 * 
	 * @author PremKumar.K-RM ITS
	 * @throws IOException
	 */
	public void verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemWithInterstateSupplier(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location14"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);

	}

	/**
	 * 
	 * This Method Is Used To Verify Direct PO Domestic Supplier for material Type
	 * with Multiple Item For UserDefined Item
	 * 
	 * @author PremKumar.K-RM ITs
	 * @throws IOException
	 */

	public void verifyDirectPODomesticMaterialSimilarUOMwithFreePassItemIgstSupplier(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location14"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		webDB.waitForElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath);
		System.out.println("This The Value:" + commonFunc.getDataFromTestDataSheet("Value") + ":");
		String PONumbervalue = webDB.getDomAttributeFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Service Item for Similar UOM
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */
	public void verifyDirectPODomesticServiceSimilarUOMWithIgstSupplier(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location14"),
				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyPoWithGrnServiceFlagChecked();
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("ServiceType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
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
	public void verifyDirectPoWithRateContractItemWithAndWithoutGeneralTypeFreePass(String SheetName)
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location14"),
				commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Methods Method to handle Create the PO Document for Rate
	 * ContractItem(Material)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */

	public void verifyDirectPoWithRateContractItemWithAndWithFreePassIgstSupplier(String SheetName) throws IOException {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location14"),
				commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForOpenPoItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"));
				verifyRateContractItemUnitCostAndDiscPctWriteInExcelForGeneralType(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(General Type)
	 * 
	 * @author PremKumar.K-RM ITS
	 * @throws IOException
	 * 
	 */
	public void verifyDirectPODomesticPricelistItemWithItemandWithFreePassIgstSupplier(String SheetName)
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location14"),
				commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Direct PO Domestic Price List Item Similar UOM
	 * Multiple Service Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	public void verifyDirectPODomesticPriceListItemSimilarUOMMultipleServiceTypeForFreePassAndIgstSupplier(
			String SheetName) throws IOException {
		commonFunc.validCommonLogin(System.getProperty("SelvaUsername"), System.getProperty("SelvaPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Location14"),
				commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyPoWithGrnServiceFlagChecked();
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("OpenPoIgstSup"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("ServiceType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(General Type) Without Quality Enabled
	 * 
	 * @author PremKumar.K-RM ITS
	 * @throws IOException
	 * 
	 */
	public void verifyDirectPODomesticPricelistItemWithItemGeneralTypeForFreePassItem(String SheetName)
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(Material Type) WithoutQuality Enabled
	 * 
	 * @author PremKumar.K-RM ITS
	 * @throws IOException
	 * 
	 */
	public void verifyDirectPODomesticPricelistItemWithMaterialTypeForFreePassItem(String SheetName)
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
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
			if (!ITEM_NAME.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoLineWiseSaved();
				verifyUnitcostAndDiscountAmountForPriceListItem(i, SheetName,
						commonFunc.getDataFromTestDataSheet("PriceListSupplier"));
				verifyPriceListItemUnitCostAndDiscPctWriteInExcel(i, SheetName);
				otherThanUserdefinedDiscountAmount(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}

		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
					commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("MaterialType"));
			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
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
	public void verifyCLickPurchaseOrderFrame() {
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderAddButtonframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseOrderPageLocators.PurchaseOrderAddButtonframe, ElementType.Xpath);
			logs.logging("INFO", "Switch To Purchase Order Frame");
			flag = webDB.waitForElement(CommonUiLocators.CreatePoTickIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Purchase Order Tick Icon Displayed");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO with Price list Item for Similar UOM
	 * With And Without Inspection Plan(Material Type)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */
	public void verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItemWithGateEntry(
			String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		commonFunc.navigatePurchaseOrderPage();
		clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		verifyTickIconcreatePO();
		String PONumbervalue = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.PONumber, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get PO Number Is: " + PONumbervalue);
		VariableHandler.setPONumber(PONumbervalue);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
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
				verifyDirectPoItemAddIconClicked();
				verifyDirectPoItemSelectedFromLov(ITEM_NAME);
				verifyDirectPoQtyEntered(QUANTITY);
				verifyDirectPoUnitCostEntered(UNIT_COST);
				verifyDirectPoHsnSelectedFromLov(HSN);
				verifyDirectPoLineWiseSaved();
				discountFromPrToGrnDocument(i, SheetName);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		commonFunc.hardWait(3000);
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
//			approvalFunc.verifyValidatedetailsPoDetails(VariableHandler.getPONumber(),
//					commonFunc.getDataFromTestDataSheet("CurrencyInr"), commonFunc.getDataFromTestDataSheet("PohType"),
//					commonFunc.getDataFromTestDataSheet("NOS"));
//			approvalFunc.verifyNavigateback();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getPONumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Select the item For Excel Sheet
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 *
	 */

	public void verifyDirectPoHsnSelectedFromLov(String HSN) {
		flag = webDB.waitForClickElement(PurchaseOrderPageLocators.PurchaseOrderHsnField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.PurchaseOrderHsnField, ElementType.Xpath);
			logs.logging("INFO", "Hsn Field Is Clicked");

			flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderHsnLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.PurchaseOrderHsnLov, HSN);
				logs.logging("INFO", "Entered HSN is:" + HSN);
			}
			assertTrue(flag);
		}
	}

	/**
	 * This Method used to click create icon - left add icon
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCreateIcon() {
		flag = webDB.waitForElement(CommonUiLocators.LeftSideAddIconPo, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.LeftSideAddIconPo, ElementType.Xpath);
			logs.logging("INFO", "Left Side Add Icon is Displayed and Clicked");
			if (flag) {
				flag = webDB.waitForElement(CommonUiLocators.CreatePORadioButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CommonUiLocators.CreatePORadioButton, ElementType.Xpath);
					logs.logging("INFO", "Create PO Button is Displayed and Clicked");
					flag = webDB.waitForElement(PurchaseOrderPageLocators.PurchaseOrderAddButtonframe,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Purchase order Frame is Enabled");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}
}
