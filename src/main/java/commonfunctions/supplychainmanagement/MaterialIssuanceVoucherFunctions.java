package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import static org.testng.Assert.assertTrue;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.MaterialIssuanceVoucherLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class MaterialIssuanceVoucherFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This Method used to switch Material Request Lines Frame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchMaterialRequestLinesIframe() {
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMRLinesIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(MaterialIssuanceVoucherLocators.PendingMRLinesIframe, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMRSearchBar, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to search and filter with MR No
	 * 
	 * @author Sakthi-GWL
	 * @param MRNo
	 */
	public void searchParticularDocumentWithMRNo(String MRNo) {
		switchMaterialRequestLinesIframe();
		flag = webDB.sendTextToAnElement(MaterialIssuanceVoucherLocators.PendingMRSearchBar, MRNo, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MaterialIssuanceVoucherLocators.PendingMRSearchGoBtn, ElementType.Xpath);
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.PendingMRSearchGoBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.SearchResult_MRNo_TableData(MRNo),
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Succesffuly Searched Document With Material Request Number: " + MRNo);
			}
		}
		if (flag) {
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMRIframeCloseIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to validate Item Warehouse and Production Order No and FG
	 * Item with MR No in search result table
	 * 
	 * @author Sakthi-GWL
	 * @param MRNo
	 * @param Item
	 * @param Warehouse
	 * @param ProdNo
	 * @param FGItem
	 */
	public void validateItemWHProdNoFGItemWithMRNo(String MRNo, String Item, String Warehouse, String ProdNo,
			String FGItem) {
		switchMaterialRequestLinesIframe();
		String DisplItem = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.SearchResult_Item_TableData(MRNo),
				ElementType.Xpath);
		logs.logging("INFO", "Displayed Item: " + DisplItem);
		flag = Item.equalsIgnoreCase(DisplItem);
		if (flag) {
			logs.logging("INFO", "Item Validated Successfully In Material Issuance Voucher");
			String DisplWH = webDB.getTextFromElement(
					MaterialIssuanceVoucherLocators.SearchResult_Warehouse_TableData(MRNo), ElementType.Xpath);
			logs.logging("INFO", "Displayed WareHouse: " + DisplWH);
			flag = Warehouse.equalsIgnoreCase(DisplWH);
			if (flag) {
				logs.logging("INFO", "Warehouse Validated Successfully In Material Issuance Voucher");
				String DisplProdNo = webDB.getTextFromElement(
						MaterialIssuanceVoucherLocators.SearchResult_ProdOrderNo_TableData(MRNo), ElementType.Xpath);
				logs.logging("INFO", "Displayed Production Order No: " + DisplProdNo);
				flag = ProdNo.equalsIgnoreCase(DisplProdNo);
				if (flag) {
					logs.logging("INFO", "Production Order Validated Successfully In Material Issuance Voucher");
					String DisplFGItem = webDB.getTextFromElement(
							MaterialIssuanceVoucherLocators.SearchResult_FGItem_TableData(MRNo), ElementType.Xpath);
					logs.logging("INFO", "Displayed FG Item: " + DisplFGItem);
					flag = FGItem.equalsIgnoreCase(DisplFGItem);
					if (flag) {
						logs.logging("INFO", "FG Item Validated Successfully In Material Issuance Voucher");
					}
				}
			}
		}
		if (flag) {
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PendingMRIframeCloseIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select MRNo and click Create MI button
	 * 
	 * @author Sakthi-GWL
	 * @param MRNo
	 */
	public void clickCheckBoxWithMRNoAndCreateMI(String MRNo) {
		switchMaterialRequestLinesIframe();
		webDB.moveToElement(MaterialIssuanceVoucherLocators.SearchResult_TableSelectBox(MRNo), ElementType.Xpath);
		webDB.clickAnElement(MaterialIssuanceVoucherLocators.SearchResult_TableSelectBox(MRNo), ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		webDB.waitForClickElement(MaterialIssuanceVoucherLocators.PendingMRCreateMIBtn, ElementType.Xpath);
		flag = webDB.isElementEnabled(MaterialIssuanceVoucherLocators.PendingMRCreateMIBtn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "CheckBox Clicked for MR No: " + MRNo);
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.PendingMRCreateMIBtn, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.DisplayMIVNo, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create MI Button Clicked and Created Successfully");
				String MIVNo = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.DisplayMIVNo,
						ElementType.Xpath);
				VariableHandler.setMaterialIssuanceVoucherNo(MIVNo);
				logs.logging("INFO", "Displayed MIV No: " + VariableHandler.getMaterialIssuanceVoucherNo());
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to save header and click post
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveHeaderAndPostBtnInMIV() {
		commonFunc.checkSuccessToastMessageDisappear();
		flag = webDB.waitForClickElement(MaterialIssuanceVoucherLocators.SaveHeaderIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MaterialIssuanceVoucherLocators.SaveHeaderIcon, ElementType.Xpath);
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.SaveHeaderIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Save Header Clicked and Saved Toast Displayed");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.MIVPostBtn, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(MaterialIssuanceVoucherLocators.MIVPostBtn, ElementType.Xpath);
					webDB.clickAnElement(MaterialIssuanceVoucherLocators.MIVPostBtn, ElementType.Xpath);
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Post Button Clicked and Toast Message Displayed");
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Check Status Of Material Issuance Voucher
	 * 
	 * @author Deepan-GWL, Sakthi-GWL
	 * @param MrNo
	 * @param ExpectedStatus
	 */
	public void checkStatusOfMaterialIssuanceVoucher(String MrNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialIssuanceVoucherLocators.VoucherNoLovButton, MrNo);
		flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.MivSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialIssuanceVoucherLocators.MivSearchButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialIssuanceVoucherLocators.PostedMiv(MrNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MR No: " + MrNo + " Displayed");
				String Status = webDB.getTextFromElement(MaterialIssuanceVoucherLocators.PostedMiv(MrNo),
						ElementType.Xpath);
				logs.logging("INFO", "MR Number: '" + MrNo + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpectedStatus);
			}
		}
		assertTrue(flag);
	}

}
