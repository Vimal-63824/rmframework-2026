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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonfunctions.ItemDetailsHandler.POLineDetailsName;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseOrderFunctions {
	private static boolean flag = false;
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
		commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.LocationIdButton, LocationId);
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
			webDB.clickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
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
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(PurchaseOrderPageLocators.HeaderTPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = (webDB.waitForElementUntilGivenTime(PurchaseOrderPageLocators.SearchButton, ElementType.Xpath,
						10) || webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
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
					commonFunc.getDataFromTestDataSheet("AshokValue"));
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
		flag = webDB.waitForClickElement(PurchaseOrderPageLocators.DPOTickButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Tick Icon Is Displayed");
			commonFunc.checkRefreshCellsDisappear();
			webDB.clickAnElement(PurchaseOrderPageLocators.DPOTickButton, ElementType.Xpath);
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
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Direct PO Domestic General Similar UOM
	 * 
	 * @return boolean flag
	 */
	public boolean verifyDirectPODomesticGeneralSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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

		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
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
		flag = webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_SelectAllCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseOrderPageLocators.PendingPRTable, ElementType.Xpath);
			webDB.moveToElement(PurchaseOrderPageLocators.PendingPR_SelectAllCheckBox, ElementType.Xpath);
			commonFunc.hardWait(5000);
			webDB.clickAnElement(PurchaseOrderPageLocators.PendingPR_SelectAllCheckBox, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElement(PurchaseOrderPageLocators.PendingPR_CreatePO_Btn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			flag = webDB.isElementEnabled(PurchaseOrderPageLocators.PendingPR_CreatePO_Btn, ElementType.Xpath);
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
	public void selectSupplierAndCurrencyPendingPRAndClickTickIcon(String Supplier) {
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("UomNos"));
		verifyHeaderTickAndPost();
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
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
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingPRAndSearchPRNo();
		selectAllPendingPRAndClickCreatePO();
		selectSupplierAndCurrencyPendingPRAndClickTickIcon(commonFunc.getDataFromTestDataSheet("WalmartSupplier"));
		verifyItemCalculationWithLineAmount();
		verifyPoUOM(commonFunc.getDataFromTestDataSheet("Kgs"));
		verifyHeaderTickAndPost();
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			BigDecimal LineAmountd = new BigDecimal(LineAmount);
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
		String Totalvalue = verifyRoundOffBigDecimalValueWithReturnString(POTotalAmount);
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
		}
		assertTrue(flag);
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
			flag = ExpectedLineAmount.equals(ActualLineAmount);
			if (flag) {
				logs.logging("INFO", "Verified Actual Line Amount With Expected Line Amount");
			} else {
				assertTrue(flag);
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
		verifyGetTextValidation(PurchaseOrderPageLocators.ItemName(1),
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEMID));
		verifyGetTextValidation(PurchaseOrderPageLocators.ItemDescription(1),
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEM_DESCRIPTION));
		verifyGetTextValidation(PurchaseOrderPageLocators.UOM(1),
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.UOM));
		verifyGetTextValidation(PurchaseOrderPageLocators.HSNSACCode(1),
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.HSN_CODE));
		if (Currency.equals(commonFunc.getDataFromTestDataSheet("CurrencyInr"))) {
			verifyGetTextValidation(PurchaseOrderPageLocators.TaxPercent(1),
					itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.TAX));
		} else {
			verifyGetTextValidation(PurchaseOrderPageLocators.TaxPercent(1),
					commonFunc.getDataFromTestDataSheet("ZeroValue"));
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
		navigateToLineDetailPage(POType);
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
	public void navigateToLineDetailPage(boolean... PoType) {
		webDB.moveToElement(PurchaseOrderPageLocators.ItemName(1), ElementType.Xpath);
		if (PoType != null) {
			webDB.moveToElement(PurchaseOrderPageLocators.ServiceLineDetailButton(1), ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ServiceLineDetailButton(1), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.ServiceLineDetailButton(1), ElementType.Xpath);
			}
		} else {
			webDB.moveToElement(PurchaseOrderPageLocators.LineDetailButton(1), ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailButton(1), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.LineDetailButton(1), ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailsHeader, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Navigated To Line Details Page");
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
		verifyEnterAmountForDiscountCalculation();
		discountCalculationVerification(Quantity);
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
			verifyPoHeaderDetails(PoType, location, SupplierName, CurrencyValue, PoPrefix);
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
		DiscAmountPerQty = (TradeAmount.add(SplDiscAmount).add(cashDiscount)).divide(Qty);
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
		verifyGetTextValidation(PurchaseOrderPageLocators.DiscountAmount(1),
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
					String TaxAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxAmount(1, j),
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
				assertTrue(flag);
			}
		} else {
			for (int j = 12; j <= 15; j++) {
				String TaxAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxAmount(1, j),
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Tax Amount Is: " + TaxAmount);
				flag = TaxAmount.equals(commonFunc.getDataFromTestDataSheet("Zero"));
				if (flag) {

				} else {
					assertTrue(false);
				}
			}
		}
		assertTrue(flag);
	}

	public void verifyGeneralTabTotalAmountCalculation() {
		BigDecimal TotalAmount = VariableHandler.getLineAmount().subtract(VariableHandler.getDiscountAmount())
				.add(VariableHandler.getIndividualAmount());
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
				RoundOffValue.toString().replace("0.", "."));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
			commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
	 * @author PremKumar.K-RM ITs
	 * @throws IOException
	 */
	public void verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItem() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
	 * @author PremKumar.K-RM ITS
	 * @throws IOException
	 */
	public void verifyDirectPODomesticGeneralSimilarUOMWithMultipleItem() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
				commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
					commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
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
				webDB.selectDropDownByValue(PurchaseOrderPageLocators.PoseriviceWithGrnSelectedValues, commonFunc.getDataFromTestDataSheet("Y"),
						ElementType.Xpath);
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
					commonFunc.validCommonLogin(System.getProperty("AshokUsername"),
							System.getProperty("AshokPassword"));
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
						commonFunc.validCommonLogin(System.getProperty("HodUsername"),
								System.getProperty("HodPassword"));
						commonFunc.navigatePurchaseOrderPage();
					}
				}
			}
			verifyValidatePoStatus(VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			assertTrue(flag);
		}
	}
}
