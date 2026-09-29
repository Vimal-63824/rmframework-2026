package commonfunctions.supplychainmanagement;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.RfqLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PricelistPageFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST for Domestic Supplier
	 * Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListDomesticSimilarUom() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		saveHeaderAndClickPost();
		waitingForApproval();
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRLISTNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST for Domestic Supplier
	 * DisSimilar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListDomesticDissimilarUom() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		enterEffFromAndToo(commonFunc.getDataFromTestDataSheet("Efffrom_Date"),
				commonFunc.getDataFromTestDataSheet("Effto_Date"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(3000);
		saveHeaderAndClickPost();
		waitingForApproval();
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRLISTNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST for Import Supplier
	 * Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListImportSimilarUom() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		enterEffFromAndToo(commonFunc.getDataFromTestDataSheet("Efffrom_Date"),
				commonFunc.getDataFromTestDataSheet("Effto_Date"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(3000);
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRLISTNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST for Import Supplier
	 * DisSimilar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListImportDissimilarUom() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		enterEffFromAndToo(commonFunc.getDataFromTestDataSheet("Efffrom_Date"),
				commonFunc.getDataFromTestDataSheet("Effto_Date"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(3000);
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRLISTNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Cancel of pricelist
	 * 
	 * @author Durai-RMITS
	 */
	public void priceListEntryCancel() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("CnclSimItem_ID"),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		enterEffFromAndToo(commonFunc.getDataFromTestDataSheet("Efffrom_Date"),
				commonFunc.getDataFromTestDataSheet("Effto_Date"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("CnclSimItem_ID"),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		saveHeaderAndClickCancel();
		enterPriceListNumForCheckingStatus(VariableHandler.getPRLISTNumber());
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify find screen checking
	 * 
	 * @author Durai-RMITS
	 */
	public void priceListEntryFindScreenChecking() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickLocationLov(commonFunc.getDataFromTestDataSheet("Loc_100"));
		commonFunc.expandFindTable();
		clickUnitLov(commonFunc.getDataFromTestDataSheet("Unit_100"));
		commonFunc.expandFindTable();
		clickItemLov(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.expandFindTable();
		clickSupplierLov(commonFunc.getDataFromTestDataSheet("DomSupplier_ID"));
		commonFunc.expandFindTable();
		clickFindEffectiveFromDate(commonFunc.getDataFromTestDataSheet("Efffrom_Date"));
		commonFunc.expandFindTable();
		clickFindEffectiveToDate(commonFunc.getDataFromTestDataSheet("Effto_Date"));
		commonFunc.expandFindTable();
		verifyStatusInFindPage();
		commonFunc.expandFindTable();
		verifyDocNoInFindPage(VariableHandler.getPRLISTNumber());
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Amendment for Domestic
	 * Supplier Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListAmendmentDomesticSimilarUom() {
		verifyCreationOfPriceListDomesticSimilarUomAmd();
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigatePricelistPage();
		verifyDocNoInFindPage(VariableHandler.getPRLISTNumber());
		verifyDrilldownTheDocNum();
		verifyAmendmentOkButton();
		verifyEnterAmndCloseDate(commonFunc.getDataFromTestDataSheet("Close_Date"),
				commonFunc.getDataFromTestDataSheet("Efffrom_Date"), commonFunc.getDataFromTestDataSheet("Effto_Date"),
				commonFunc.getDataFromTestDataSheet("Amnd_Reason"));
		verifyEnterAmndCreation();
		ExtractDocumentNumber();
		verifyAmendDocNoInFindPage(VariableHandler.getAmendmentDocNumber());
		verifyDrilldownTheDocNum();
		enterDiscount(commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		verifyEnterPriceInsideTransaction(commonFunc.getDataFromTestDataSheet("Amdcost_Value"));
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getAmendmentDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Amendment for Domestic
	 * Supplier DisSimilar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListAmendmentDomesticDisSimilarUom() {
		verifyCreationOfPriceListDomesticDissimilarUomAmd();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		verifyDocNoInFindPage(VariableHandler.getPRLISTNumber());
		verifyDrilldownTheDocNum();
		verifyAmendmentOkButton();
		verifyEnterAmndCloseDate(commonFunc.getDataFromTestDataSheet("Close_Date"),
				commonFunc.getDataFromTestDataSheet("Efffrom_Date"), commonFunc.getDataFromTestDataSheet("Effto_Date"),
				commonFunc.getDataFromTestDataSheet("Amnd_Reason"));
		verifyEnterAmndCreation();
		ExtractDocumentNumber();
		verifyAmendDocNoInFindPage(VariableHandler.getAmendmentDocNumber());
		verifyDrilldownTheDocNum();
		enterDiscount(commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		verifyEnterPriceInsideTransaction(commonFunc.getDataFromTestDataSheet("Amdcost_Value"));
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getAmendmentDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Amendment for Import
	 * Supplier Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListAmendmentImportSimilarUom() {
		verifyCreationOfPriceListImportSimilarUomAmd();
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		verifyDocNoInFindPage(VariableHandler.getPRLISTNumber());
		verifyDrilldownTheDocNum();
		verifyAmendmentOkButton();
		verifyEnterAmndCloseDate(commonFunc.getDataFromTestDataSheet("Close_Date"),
				commonFunc.getDataFromTestDataSheet("Efffrom_Date"), commonFunc.getDataFromTestDataSheet("Effto_Date"),
				commonFunc.getDataFromTestDataSheet("Amnd_Reason"));
		verifyEnterAmndCreation();
		ExtractDocumentNumber();
		verifyAmendDocNoInFindPage(VariableHandler.getAmendmentDocNumber());
		verifyDrilldownTheDocNum();
		enterDiscount(commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		verifyEnterPriceInsideTransaction(commonFunc.getDataFromTestDataSheet("Amdcost_Value"));
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getAmendmentDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Amendment for Import
	 * Supplier Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListAmendmentImportDisSimilarUom() {
		verifyCreationOfPriceListImportDissimilarUomAmd();
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		verifyDocNoInFindPage(VariableHandler.getPRLISTNumber());
		verifyDrilldownTheDocNum();
		verifyAmendmentOkButton();
		verifyEnterAmndCloseDate(commonFunc.getDataFromTestDataSheet("Close_Date"),
				commonFunc.getDataFromTestDataSheet("Efffrom_Date"), commonFunc.getDataFromTestDataSheet("Effto_Date"),
				commonFunc.getDataFromTestDataSheet("Amnd_Reason"));
		verifyEnterAmndCreation();
		ExtractDocumentNumber();
		verifyAmendDocNoInFindPage(VariableHandler.getAmendmentDocNumber());
		verifyDrilldownTheDocNum();
		enterDiscount(commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		verifyEnterPriceInsideTransaction(commonFunc.getDataFromTestDataSheet("Amdcost_Value"));
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getAmendmentDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Amendment Cancel
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListAmendmentCancel() {
		verifyCreationOfPriceListDomesticSimilarUomAmdCancel();
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		verifyDocNoInFindPage(VariableHandler.getPRLISTNumber());
		verifyDrilldownTheDocNum();
		verifyAmendmentOkButton();
		verifyEnterAmndCloseDate(commonFunc.getDataFromTestDataSheet("Close_Date"),
				commonFunc.getDataFromTestDataSheet("Efffrom_Date"), commonFunc.getDataFromTestDataSheet("Effto_Date"),
				commonFunc.getDataFromTestDataSheet("Amnd_Reason"));
		verifyEnterAmndCreation();
		ExtractDocumentNumber();
		verifyAmendDocNoInFindPage(VariableHandler.getAmendmentDocNumber());
		verifyDrilldownTheDocNum();
		enterDiscount(commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		verifyEnterPriceInsideTransaction(commonFunc.getDataFromTestDataSheet("Amdcost_Value"));
		saveHeaderAndClickCancel();
		enterPriceListNumForCheckingStatus(VariableHandler.getAmendmentDocNumber());
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Cancel
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListDomesticSimilarUomAmdCancel() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRLISTNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Amendment Domestic
	 * Similar Uom
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListDomesticSimilarUomAmd() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem3(commonFunc.getDataFromTestDataSheet("AmdSimItem_ID"));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRLISTNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Amendment Domestic
	 * DisSimilar Uom
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListDomesticDissimilarUomAmd() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem3(commonFunc.getDataFromTestDataSheet("AmdDissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		enterEffFromAndToo(commonFunc.getDataFromTestDataSheet("Efffrom_Date"),
				commonFunc.getDataFromTestDataSheet("Effto_Date"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(3000);
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRLISTNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Amendment Import
	 * Similar Uom
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListImportSimilarUomAmd() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem3(commonFunc.getDataFromTestDataSheet("AmdSimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		enterEffFromAndToo(commonFunc.getDataFromTestDataSheet("Efffrom_Date"),
				commonFunc.getDataFromTestDataSheet("Effto_Date"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(3000);
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRLISTNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of PRICE LIST Amendment Import
	 * Similar Uom
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfPriceListImportDissimilarUomAmd() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem3(commonFunc.getDataFromTestDataSheet("AmdDissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		enterEffFromAndToo(commonFunc.getDataFromTestDataSheet("Efffrom_Date"),
				commonFunc.getDataFromTestDataSheet("Effto_Date"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"), VariableHandler.getItemName(),
				commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		commonFunc.hardWait(3000);
		saveHeaderAndClickPost();
		waitingForApproval();
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPRLISTNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Add Location Supplier Item Discount
	 * 
	 * @author Durai-RMITS
	 * @param Location
	 * @param Supplier
	 * @param Item
	 * @param Discount
	 */

	public void clickAddIconFillDetailsOfPriceList(String Location, String Supplier, String Item, String Discount) {
		commonFunc.clickAddIconPricelist();
		VerifyAddFrame();
		flag = webDB.waitForElement(PricelistPageLocators.LocationLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.LocationLov, Location);
			logs.logging("INFO", "Location is Entered");
			commonFunc.checkRefreshCellsDisappear();
			VerifyAddFrame();
			flag = webDB.waitForElement(PricelistPageLocators.SuppliernameLov, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.SuppliernameLov, Supplier);
				logs.logging("INFO", "Supplier is Entered");
				VerifyAddFrame();
				flag = webDB.waitForElement(PricelistPageLocators.ItemLov, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.ItemLov, Item);
					logs.logging("INFO", "Item is Entered");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Enter Price
	 * 
	 * @author Durai-RMITS
	 * @param Cost
	 */
	public void verifyEnterPrice(String Cost) {
		VerifyAddFrame();
		flag = webDB.waitForElement(PricelistPageLocators.PriceField, ElementType.Xpath);
		if (flag) {
			webDB.clearText(PricelistPageLocators.PriceField, ElementType.Xpath);
			webDB.sendTextToAnElement(PricelistPageLocators.PriceField, Cost, ElementType.Xpath);
			logs.logging("INFO", "Cost is Entered" + Cost);
			enterEffFromAndToo(commonFunc.getDataFromTestDataSheet("Efffrom_Date"),
					commonFunc.getDataFromTestDataSheet("Effto_Date"));
			flag = webDB.waitForElement(PricelistPageLocators.PriceField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PriceList Page is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Enter Price Inside the Transaction
	 * 
	 * @author Durai-RMITS
	 * @param Cost1
	 */
	public void verifyEnterPriceInsideTransaction(String Cost1) {
		flag = webDB.waitForElement(PricelistPageLocators.CostField, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(PricelistPageLocators.CostField, ElementType.Xpath);
			logs.logging("INFO", "Cost Field is Clicked");
			webDB.clearText(PricelistPageLocators.Costinput, ElementType.Xpath);
			webDB.sendTextToAnElement(PricelistPageLocators.Costinput, Cost1, ElementType.Xpath);
			logs.logging("INFO", "Cost is Entered" + Cost1);
			webDB.pressTabKey();
			commonFunc.hardWait(2000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PricelistPageLocators.PricelistOverallsave, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Overall Save is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Create find and giving Effective From Date and Too
	 * Date
	 * 
	 * @author Durai-RMITS
	 * @param Date1
	 * @param Date2
	 */
	public void enterEffFromAndToo(String Date1, String Date2) {
		flag = webDB.waitForElement(PricelistPageLocators.EffFrom, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.EffFrom, Date1, ElementType.Xpath);
			logs.logging("INFO", "From Date Is Entered");
			flag = webDB.waitForElement(PricelistPageLocators.EffTo, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.EffTo, ElementType.Xpath);
				logs.logging("INFO", "To Date Field is Clicked");
				webDB.sendTextToAnElement(PricelistPageLocators.EffTo, Date2, ElementType.Xpath);
				logs.logging("INFO", "To Date Is Entered");
				flag = webDB.waitForElement(PricelistPageLocators.PriceField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.PriceField, ElementType.Xpath);
					logs.logging("INFO", "Price Field is Clicked");
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(PricelistPageLocators.PriceField, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Pricelist Page is clicked");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify to create New Record for Pricelist
	 * 
	 * @author Durai-RMITS
	 * @param Location
	 * @param Supplier
	 * @param Item
	 * @param Discount
	 */
	public void findCreate(String Location, String Supplier, String Item, String Discount) {
		flag = webDB.waitForElement(PricelistPageLocators.FindCreateButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.FindCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Find Create Button is Clicked");
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(PricelistPageLocators.PricelistDocNum, ElementType.Xpath);
			if (flag) {
				String PRLISTNum = webDB.getTextFromElement(PricelistPageLocators.PricelistDocNum, ElementType.Xpath);
				VariableHandler.setPRLISTNumber(PRLISTNum);
				logs.logging("INFO", "Price List Number Is :" + VariableHandler.getPRLISTNumber());
				validateLocationInPriceListHeader(Location);
				validateSupplierInPriceListHeader(Supplier);
				validateItemInPriceListHeader(Item);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Enter Discount
	 * 
	 * @author Durai-RMITS
	 * @param Discount
	 */
	public void enterDiscount(String Discount) {
		flag = webDB.waitForClickElement(PricelistPageLocators.DiscountField, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(PricelistPageLocators.DiscountField, ElementType.Xpath);
			logs.logging("INFO", "Discount Field is clicked");
			webDB.clearText(PricelistPageLocators.Discountinput, ElementType.Xpath);
			webDB.doubleClickAnElement(PricelistPageLocators.DiscountField, ElementType.Xpath);
			webDB.sendTextToAnElement(PricelistPageLocators.Discountinput, Discount, ElementType.Xpath);
			logs.logging("INFO", "Discount value Is Entered" + Discount);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the Location Filed is correctly eneterd
	 * 
	 * @author Durai-RMITS
	 * @param Location
	 */
	private void validateLocationInPriceListHeader(String Location) {
		flag = webDB.waitForElement(PricelistPageLocators.Pricelist_HeaderLoc, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(PricelistPageLocators.Pricelist_HeaderLoc, ElementType.Xpath);
			logs.logging("INFO", "Expected Text: " + Location + " Displayed: " + DisplayText);
			flag = DisplayText.equalsIgnoreCase(Location);
			if (flag) {
				logs.logging("INFO", "verified Displayed Location in PRICELIST Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Location PRICELIST Header");
				logs.logging("INFO", "Expected Text: " + Location + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the Supplier Filed is correctly eneterd
	 * 
	 * @author Durai-RMITS
	 * @param Supplier
	 */
	private void validateSupplierInPriceListHeader(String Supplier) {
		flag = webDB.waitForElement(PricelistPageLocators.Pricelist_HeaderSupplier, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(PricelistPageLocators.Pricelist_HeaderSupplier,
					ElementType.Xpath);
			logs.logging("INFO", "Expected Text: " + Supplier + " Displayed: " + DisplayText);
			flag = DisplayText.equalsIgnoreCase(Supplier);
			if (flag) {
				logs.logging("INFO", "verified Displayed Supplier in PRICELIST Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Supplier PRICELIST Header");
				logs.logging("INFO", "Expected Text: " + Supplier + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the Item Filed is correctly eneterd
	 * 
	 * @author Durai-RMITS
	 * @param Item
	 */
	private void validateItemInPriceListHeader(String Item) {
		flag = webDB.waitForElement(PricelistPageLocators.Pricelist_HeaderItem, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(PricelistPageLocators.Pricelist_HeaderItem,
					ElementType.Xpath);
			logs.logging("INFO", "Expected Text: " + Item + " Displayed: " + DisplayText);
			flag = DisplayText.equalsIgnoreCase(Item);
			if (flag) {
				logs.logging("INFO", "verified Displayed Item in PRICELIST Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Item PRICELIST Header");
				logs.logging("INFO", "Expected Text: " + Item + " Displayed: " + DisplayText);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Save the Document And click on Active Button
	 * 
	 * @author Durai-RMITS
	 */
	public void saveHeaderAndClickPost() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForClickElement(PricelistPageLocators.SAVEBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.SAVEBtn, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			commonFunc.hardWait(3500);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PricelistPageLocators.ActiveButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.ActiveButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = webDB.waitForElement(PricelistPageLocators.PriceListWorkflowFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Frame is Checked");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Waiting for Approval Dialog Box
	 * 
	 * @author Durai-RMITS
	 */
	public void waitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(PricelistPageLocators.PriceListWorkflowFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PricelistPageLocators.PriceListWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.PricelistForwardlov,
					commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PricelistPageLocators.PriceListWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PricelistPageLocators.PricelistworkflowOK, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.PricelistworkflowOK, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Button is Clicked");
				}
			}
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Forward Screen is Opening
	 * 
	 * @author Durai-RMITS
	 */
	public void forwardApprovalScreen() {
		commonFunc.hardWait(1000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"));
		approvalFunc.navigateToApprovals();
		flag = webDB.waitForElement(ApprovalPageLocators.UserNotifyIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "User Notify Icon is Clicked");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Approve the document
	 * 
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void filterDocumentsForPriceListApproval(String Number) {
		flag = webDB.waitForElement(ApprovalPageLocators.SearchBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Search Box IS Displayed");
			webDB.clickAnElement(ApprovalPageLocators.SearchBoxContainer, ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.SearchBox, ElementType.Xpath);
			if (flag) {
				webDB.clearTextField(ApprovalPageLocators.SearchBox, ElementType.Xpath);
				logs.logging("INFO", "Clear Text Search Box");
				webDB.sendTextToAnElement(ApprovalPageLocators.SearchBox, Number, ElementType.Xpath);
				logs.logging("INFO", "Entered Document Number Is: " + Number);
				flag = webDB.waitForClickElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Approve The Document
	 * 
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void verifyApproveWithDocNumber(String Number) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(PricelistPageLocators.PRLISTSelectCheckBox(Number), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(PricelistPageLocators.PRLISTSelectCheckBox(Number), ElementType.Xpath);
			logs.logging("INFO", "Select Box is Checked");
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
						flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Toast Message Displayed, Posted Successfully");
							if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Save the Document And click on cancell Button
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCancelThePricelist() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.navigatePricelistPage();
		clickAddIconFillDetailsOfPriceList(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("SimItem_ID"), commonFunc.getDataFromTestDataSheet("Disc_value"));
		verifyEnterPrice(commonFunc.getDataFromTestDataSheet("Cost_Value"));
		enterEffFromAndToo(commonFunc.getDataFromTestDataSheet("Efffrom_Date"),
				commonFunc.getDataFromTestDataSheet("Effto_Date"));
		findCreate(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("SimItem_ID"), commonFunc.getDataFromTestDataSheet("Disc_value"));
		commonFunc.hardWait(1000);
		enterDiscount(commonFunc.getDataFromTestDataSheet("Disc_Value"));
		saveHeaderAndClickCancel();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Save the Document And click on Active Button
	 * 
	 * @author Durai-RMITS
	 */
	public void saveHeaderAndClickCancel() {
		flag = webDB.waitForClickElement(PricelistPageLocators.SAVEBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.SAVEBtn, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			commonFunc.hardWait(3500);
			flag = webDB.waitForElement(PricelistPageLocators.CancellButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.CancellButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PricelistPageLocators.CancelOk, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.CancelOk, ElementType.Xpath);
					logs.logging("INFO", "Ok Button Is Clicked");
					flag = webDB.waitForElement(PricelistPageLocators.CancelSearch, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Search Button is Clicked");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify status
	 * 
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void enterPriceListNumForCheckingStatus(String Number) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PricelistPageLocators.CancelSearch, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.CancelSearch, ElementType.Xpath);
			logs.logging("INFO", "Cancel Search Button is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.PricelistNumField, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.PricelistNumField, Number);
//			webDB.sendTextToAnElement(PricelistPageLocators.PricelistNumField, Number, ElementType.Xpath);
				logs.logging("INFO", "Entered Document Number Is: " + Number);
				flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
					logs.logging("INFO", "Search Button is clicked");
					commonFunc.hardWait(2000);
					String canclsts = webDB.getTextFromElement(PricelistPageLocators.CancellStatus, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Expected Text: " + "Cancelled" + " Displayed: " + canclsts);
						flag = canclsts.equalsIgnoreCase("Cancelled");
						if (flag) {
							logs.logging("INFO", "verified Displayed Status is: " + canclsts);
						} else {
							logs.logging("INFO", "Verification failed in Status");
							logs.logging("INFO", "Expected Text: " + "Cancelled" + " Displayed: " + canclsts);
							flag = webDB.waitForElement(PricelistPageLocators.find, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PricelistPageLocators.find, ElementType.Xpath);
								logs.logging("INFO", "Find Button is clicked");
								flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Search Button is clicked");
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
	 * This Methods Is Used To click location lov in find screen
	 * 
	 * @author Durai-RMITS
	 * @param Location
	 */
	public void clickLocationLov(String Location) {
		flag = webDB.waitForClickElement(PricelistPageLocators.FindLocLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.FindLocLov, Location);
			logs.logging("INFO", "Location is filtered");
			flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Find Search Button is clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify unit lov in find screen
	 * 
	 * @author Durai-RMITS
	 * @param Unit
	 */
	public void clickUnitLov(String Unit) {
		flag = webDB.waitForClickElement(PricelistPageLocators.FindUnitLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.FindUnitLov, Unit);
			logs.logging("INFO", "Unit is filtered");
			flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Find Search Button is clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify item lov in find screen
	 * 
	 * @author Durai-RMITS
	 * @param Item
	 */
	public void clickItemLov(String Item) {
		flag = webDB.waitForClickElement(PricelistPageLocators.FindItemLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.FindItemLov, Item);
			logs.logging("INFO", "Item is Filtered");
			flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Find Search Button is clicked");
				flag = webDB.waitForElement(PricelistPageLocators.FindClearButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.FindClearButton, ElementType.Xpath);
					logs.logging("INFO", "Filtered Fields are cleared");
					flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Search button is clicked");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify supplier lov in find screen
	 * 
	 * @author Durai-RMITS
	 * @param Supplier
	 */
	public void clickSupplierLov(String Supplier) {
		flag = webDB.waitForClickElement(PricelistPageLocators.FindSupplierlov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.FindSupplierlov, Supplier);
			logs.logging("INFO", "Supplier is Filtered");
			flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Find Search Button is clicked");
				flag = webDB.waitForElement(PricelistPageLocators.FindClearButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.FindClearButton, ElementType.Xpath);
					logs.logging("INFO", "Filtered Fields are cleared");
					flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Search Button is clicked");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify effective from date
	 * 
	 * @author Durai-RMITS
	 * @param Date
	 */
	public void clickFindEffectiveFromDate(String Date) {
		flag = webDB.waitForElement(PricelistPageLocators.FindEfffromDate, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.FindEfffromDate, Date, ElementType.Xpath);
			logs.logging("INFO", "Effective From Date is Entered:" + Date);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify effective to date
	 * 
	 * @author Durai-RMITS
	 * @param Date
	 */
	public void clickFindEffectiveToDate(String Date) {
		flag = webDB.waitForElement(PricelistPageLocators.FindItemDesc, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.FindItemDesc, ElementType.Xpath);
			logs.logging("INFO", "Item Description is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.FindEffToDate, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.FindEffToDate, Date, ElementType.Xpath);
				logs.logging("INFO", "Effective To Date is entered:" + Date);
				flag = webDB.waitForElement(PricelistPageLocators.FindItemDesc, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.FindItemDesc, ElementType.Xpath);
					logs.logging("INFO", "Item Description is clicked");
					flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
						logs.logging("INFO", "Search Button is Clicked");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify status field in find page
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyStatusInFindPage() {
		flag = webDB.waitForElement(PricelistPageLocators.FindStatusField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.FindStatusField, ElementType.Xpath);
			logs.logging("INFO", "Status Field is Clicked");
			webDB.selectDropDownByValue(PricelistPageLocators.FindStatusField, "A", ElementType.Xpath);
			flag = webDB.waitForElement(PricelistPageLocators.FindItemDesc, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.FindItemDesc, ElementType.Xpath);
				logs.logging("INFO", "Item Description is Clicked");
				flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
					logs.logging("INFO", "Search Button is Clicked");
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Document Number field in find page
	 * 
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void verifyDocNoInFindPage(String Number) {
		flag = webDB.waitForElement(PricelistPageLocators.PricelistNumField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.PricelistNumField, Number, ElementType.Xpath);
			logs.logging("INFO", "Doc Number Is Filtered" + Number);
			flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button is clicked");
				commonFunc.hardWait(1000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify status in Find
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyStatusInFindPageforAmendment() {
		flag = webDB.waitForElement(PricelistPageLocators.FindStatusField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.FindStatusField, ElementType.Xpath);
			logs.logging("INFO", "Status Field is Clicked");
			webDB.selectDropDownByValue(PricelistPageLocators.FindStatusField, "A", ElementType.Xpath);
			logs.logging("INFO", "Status is Filterd");
			flag = webDB.waitForElement(PricelistPageLocators.FindItemDesc, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.FindItemDesc, ElementType.Xpath);
				logs.logging("INFO", "Item Description Filed is Clicked");
				flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
					logs.logging("INFO", "Search Button is clicked");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Drilldown the document number
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyDrilldownTheDocNum() {
		flag = webDB.waitForElement(PricelistPageLocators.FindDocNumDrilldown, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.FindDocNumDrilldown, ElementType.Xpath);
			logs.logging("INFO", "Document NO is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.PricelistDocNum, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Document Num Field is checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To click ok Button for Amendment Button
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyAmendmentOkButton() {
		flag = webDB.waitForElement(PricelistPageLocators.AmendmentButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.AmendmentButton, ElementType.Xpath);
			logs.logging("INFO", "Amendment Button is Clicked");
			flag = webDB.waitForElement(PricelistPageLocators.PriceListAmndFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Amendment OK button is Checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To enter Amendment Close Date
	 * 
	 * @author Durai-RMITS
	 * @param CloseDate
	 * @param FromDate
	 * @param ToDate
	 * @param Reason
	 */
	public void verifyEnterAmndCloseDate(String CloseDate, String FromDate, String ToDate, String Reason) {
		flag = webDB.waitForElement(PricelistPageLocators.PriceListAmndFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PricelistPageLocators.PriceListAmndFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched to Amendment Frame");
			flag = webDB.waitForElement(PricelistPageLocators.AmendcloseDate, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.AmendcloseDate, CloseDate, ElementType.Xpath);
				logs.logging("INFO", "Close Date Is Entered:" + CloseDate);
				flag = webDB.waitForElement(PricelistPageLocators.AmendFromDate, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.AmendFromDate, ElementType.Xpath);
					webDB.sendTextToAnElement(PricelistPageLocators.AmendFromDate, FromDate, ElementType.Xpath);
					logs.logging("INFO", "From date is Eneterd:" + ToDate);
					webDB.clickAnElement(PricelistPageLocators.AmendReason, ElementType.Xpath);
					flag = webDB.waitForElement(PricelistPageLocators.AmendToDate, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(PricelistPageLocators.AmendToDate, ToDate, ElementType.Xpath);
						logs.logging("INFO", "To Date Is Eneterd:" + ToDate);
						flag = webDB.waitForElement(PricelistPageLocators.AmendReason, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PricelistPageLocators.AmendReason, ElementType.Xpath);
							webDB.sendTextToAnElement(PricelistPageLocators.AmendReason, Reason, ElementType.Xpath);
							logs.logging("INFO", "Amendment Reason is Entered");
							flag = webDB.waitForElement(PricelistPageLocators.PriceListAmndOk, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Amendment Ok Buttton is checked");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Create Amendment
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyEnterAmndCreation() {
		flag = webDB.waitForElement(PricelistPageLocators.PriceListAmndOk, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.PriceListAmndOk, ElementType.Xpath);
			logs.logging("INFO", "Amendment Button is Clicked");
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button is checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Document Number Toast Message
	 * 
	 * @author Durai-RMITS
	 */
	public void ExtractDocumentNumber() {

		flag = webDB.waitForElement(PricelistPageLocators.SucessMsg, ElementType.Xpath);
		if (flag) {
			String fullText = webDB.getTextFromElement(PricelistPageLocators.SucessMsg, ElementType.Xpath);

			String AmendmentDocNumber = extractDocumentNumber(fullText);
			System.out.println("TEST");
			System.out.println("Document Number: " + AmendmentDocNumber);
			VariableHandler.setAmendmentDocNumber(AmendmentDocNumber);
			logs.logging("INFO", "Price List Amendment Doc Number Is :" + VariableHandler.getAmendmentDocNumber());
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Document Number field in Toast Message
	 * 
	 * @author Durai-RMITS
	 */
	private static String extractDocumentNumber(String text) {
		String regex = "\\b[A-Z]+-\\d+-\\d+\\b";
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
		java.util.regex.Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			return matcher.group(0);
		} else {
			return null; // or throw an exception
		}

//		String regex = "\\b[A-Za-z]+\\d+/\\d+/\\d+-\\d+\\b";
//		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
//		java.util.regex.Matcher matcher = pattern.matcher(text);
//		if (matcher.find()) {
//		}
//		return matcher.group(0);
	}

	/**
	 * This Methods Is Used To Verify Amendment Document Number field in find page
	 * 
	 * @author Durai-RMITS
	 * @param Docnumber
	 */
	public void verifyAmendDocNoInFindPage(String Docnumber) {
		flag = webDB.waitForElement(PricelistPageLocators.FindDocNumFieldlov, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.FindDocNumFieldlov, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.FindDocNumsearchField, Docnumber);
			logs.logging("INFO", "Document Nymber is:" + Docnumber);
			flag = webDB.waitForElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.FindSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button is Clicked");
				commonFunc.hardWait(1000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Searching Item in Find Screen
	 * 
	 * @author Durai-RMITS
	 * @param Item1
	 */
	public void verifySearchOfItem(String Item1) {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.ItemLovButton, Item1);
			logs.logging("INFO", "Search is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button is clicked ");
				verifySearchButtonClicking();
				verifyClickingGeneralTab();
				verifyGenerateItemSim(commonFunc.getDataFromTestDataSheet("Sim"));
				verifyItemPost();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Search Button Clicking
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySearchButtonClicking() {
		flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			logs.logging("INFO", "Search Button is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemDrillDownBtn(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.ItemDrillDownBtn(), ElementType.Xpath);
				logs.logging("INFO", "Drill Down item");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Clicking General Tab
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingGeneralTab() {
		flag = webDB.waitForElement(PricelistPageLocators.ItemGeneral, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemGeneral, ElementType.Xpath);
			logs.logging("INFO", "General  Button is clicked");
			String value = webDB.getDomAttributeFromElement(PricelistPageLocators.ItemGeneral, ElementType.Xpath,
					"aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.javaScriptClickAnElement(PricelistPageLocators.ItemGeneral, ElementType.Xpath);
				logs.logging("INFO", "General  Button is clicked");
			}
			flag = webDB.waitForElement(PricelistPageLocators.ItemCopy, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.ItemCopy, ElementType.Xpath);
				logs.logging("INFO", "Item Copy is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Generate Item for Dissimilar Uom
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGenerateItemDis(String Itm) {
		String ItemSeqNo = RandomStringUtils.randomNumeric(6);
		if (ItemSeqNo.equals("000")) {
			ItemSeqNo = "10";
		}
		String DisItemId = Itm + ItemSeqNo;

		flag = webDB.waitForElement(PricelistPageLocators.ItemIdInputCopy, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.ItemIdInputCopy, DisItemId, ElementType.Xpath);
			logs.logging("INFO", "Item ID is Entered");

			flag = webDB.waitForElement(PricelistPageLocators.ItemDescInputCopy, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.ItemDescInputCopy, DisItemId, ElementType.Xpath);
				logs.logging("INFO", "Item Desc is Entered");

				flag = webDB.waitForElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
					logs.logging("INFO", "Ok Button is clicked ");

					flag = webDB.waitForElement(PricelistPageLocators.ItemGetText, ElementType.Xpath);
					if (flag) {
						String DisItemName = webDB.getDomPropertyFromElement(PricelistPageLocators.ItemGetText,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setItemName(DisItemName);
						logs.logging("INFO", "Item ID is:" + VariableHandler.getItemName());
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Generate Item for similar Uom
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGenerateItemSim(String Itm) {
		String ItemSeqNo = RandomStringUtils.randomNumeric(6);
		if (ItemSeqNo.equals("000")) {
			ItemSeqNo = "10";
		}
		String SimItemId = Itm + ItemSeqNo;
		flag = webDB.waitForElement(PricelistPageLocators.ItemIdInputCopy, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.ItemIdInputCopy, SimItemId, ElementType.Xpath);
			logs.logging("INFO", "Item ID is Entered");
			flag = webDB.waitForElement(PricelistPageLocators.ItemDescInputCopy, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.ItemDescInputCopy, SimItemId, ElementType.Xpath);
				logs.logging("INFO", "Item Desc is Entered");
				flag = webDB.waitForElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
					logs.logging("INFO", "Ok Button is clicked ");
					flag = webDB.waitForElement(PricelistPageLocators.ItemGetText, ElementType.Xpath);
					if (flag) {
						String SimItemName = webDB.getDomPropertyFromElement(PricelistPageLocators.ItemGetText,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setItemName(SimItemName);
						logs.logging("INFO", "Item ID is:" + VariableHandler.getItemName());
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Generate Item for Amendment similar Uom
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGenerateItemAmdSim(String Itm) {
		String ItemSeqNo = RandomStringUtils.randomNumeric(6);
		if (ItemSeqNo.equals("000")) {
			ItemSeqNo = "10";
		}
		String AmdSimItemId = Itm + ItemSeqNo;

		flag = webDB.waitForElement(PricelistPageLocators.ItemIdInputCopy, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.ItemIdInputCopy, AmdSimItemId, ElementType.Xpath);
			logs.logging("INFO", "Item ID is Entered");

			flag = webDB.waitForElement(PricelistPageLocators.ItemDescInputCopy, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.ItemDescInputCopy, AmdSimItemId, ElementType.Xpath);
				logs.logging("INFO", "Item Desc is Entered");

				flag = webDB.waitForElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
					logs.logging("INFO", "Ok Button is clicked ");

					flag = webDB.waitForElement(PricelistPageLocators.ItemGetText, ElementType.Xpath);
					if (flag) {
						String AmdSimItemName = webDB.getDomPropertyFromElement(PricelistPageLocators.ItemGetText,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setItemName(AmdSimItemName);
						logs.logging("INFO", "Item ID is:" + VariableHandler.getItemName());
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Generate Item for Amendment Dissimilar Uom
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGenerateItemAmdDis(String Itm) {
		String ItemSeqNo = RandomStringUtils.randomNumeric(6);
		if (ItemSeqNo.equals("000")) {
			ItemSeqNo = "10";
		}
		String AmdDisItemId = Itm + ItemSeqNo;
		flag = webDB.waitForElement(PricelistPageLocators.ItemIdInputCopy, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.ItemIdInputCopy, AmdDisItemId, ElementType.Xpath);
			logs.logging("INFO", "Item ID is Entered");
			flag = webDB.waitForElement(PricelistPageLocators.ItemDescInputCopy, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.ItemDescInputCopy, AmdDisItemId, ElementType.Xpath);
				logs.logging("INFO", "Item Desc is Entered");
				flag = webDB.waitForElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
					logs.logging("INFO", "Ok Button is clicked ");
					flag = webDB.waitForElement(PricelistPageLocators.ItemGetText, ElementType.Xpath);
					if (flag) {
						String AmdDisItemName = webDB.getDomPropertyFromElement(PricelistPageLocators.ItemGetText,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setItemName(AmdDisItemName);
						logs.logging("INFO", "Item ID is:" + VariableHandler.getItemName());
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Post Item
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyItemPost() {
		flag = webDB.waitForElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
			logs.logging("INFO", "Item is Posted");
			commonFunc.hardWait(3000);
			flag = webDB.waitForElementUntilGivenTime(PricelistPageLocators.ItemPostSucessMsg, ElementType.Xpath, 15);
			if (flag) {
				logs.logging("INFO", "Item Add Button is Clicked");
			} else {
				flag = webDB.waitForElement(PricelistPageLocators.PRLFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(PricelistPageLocators.PRLFrame, ElementType.Xpath);
					logs.logging("INFO", "Switched To Workflow Frame");
					commonFunc.searchAndSelectValueInFilters(PricelistPageLocators.PRLForwardLov,
							commonFunc.getDataFromTestDataSheet("HodValue"));
					commonFunc.hardWait(2000);
					webDB.switchToFrame(PricelistPageLocators.PRLFrame, ElementType.Xpath);
					logs.logging("INFO", "Switched To WorkFlow Frame");
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PricelistPageLocators.PRLForwardOkBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PricelistPageLocators.PRLForwardOkBtn, ElementType.Xpath);
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						webDB.switchToDefaultContent();
//						commonFunc.logoutUser();
//						commonFunc.validCommonLogin(System.getProperty("HariUsername"),
//								System.getProperty("LoginPassword"), "HariUsername");
//						approvalFunc.navigateToApprovals();
//						approvalFunc.filterDocumentsforApproval(VariableHandler.getItemName());
//						approvalFunc.verifyApproveWithDocNumber(VariableHandler.getItemName());
//						commonFunc.logoutUser();
						commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("TvsA-BU"),
								VariableHandler.getItemName(), webDB.getDataFromProperties("HodEmpId"),
								commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));

//					commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
//							"TvsUsername");
//					commonFunc.navigateItemPage();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Post Item
	 * 
	 * @author Durai-RMITS
	 */
	public void clickPostInItemAndCheckAutoApproveflag() {
		flag = webDB.waitForElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.clickAnElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				logs.logging("INFO", "Post Button Clicked in item creation");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Clicking Find Button
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingFindBtnOfItem() {
		commonFunc.hardWait(4000);
		flag = webDB.waitForElement(PricelistPageLocators.ItemFindBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemFindBtn, ElementType.Xpath);
			logs.logging("INFO", "Find Button is clicked");
			webDB.waitForElement(PricelistPageLocators.ItemMasterAdd, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Add Button is Clicked");
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Searching for Item
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySearchOfItem2(String Item2) {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.FindScreenItemField, Item2, ElementType.Xpath);
			logs.logging("INFO", "Search is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button is clicked ");
				verifySearchButtonClicking();
				verifyClickingGeneralTab();
				verifyGenerateItemDis(commonFunc.getDataFromTestDataSheet("Dis"));
				verifyItemPost();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Searching for Item
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySearchOfItem3(String Item3) {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.FindScreenItemField, Item3, ElementType.Xpath);
			logs.logging("INFO", "Search is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button is clicked ");
				verifySearchButtonClicking();
				verifyClickingGeneralTab();
				verifyGenerateItemAmdSim(commonFunc.getDataFromTestDataSheet("Amd_Sim"));
				verifyItemPost();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Searching for Item
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySearchOfItem4(String Item4) {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.FindScreenItemField, Item4, ElementType.Xpath);
			logs.logging("INFO", "Search is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button is clicked ");
				verifySearchButtonClicking();
				verifyClickingGeneralTab();
				verifyGenerateItemAmdDis(commonFunc.getDataFromTestDataSheet("Amd_Dis"));
				verifyItemPost();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Item
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfItemforPriceList() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		verifySearchOfItem3(commonFunc.getDataFromTestDataSheet("AmdSimItem_ID"));
		verifySearchOfItem4(commonFunc.getDataFromTestDataSheet("AmdDissimItem_ID"));
	}

	/**
	 * This Method is used to search and create Item using copy function
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param CopyItemName
	 */
	public void searchItemAndCopyWithName(String Item, String CopyItemName) {
		flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.FindScreenItemField, Item, ElementType.Xpath);
			flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button is clicked ");
				verifySearchButtonClicking();
				verifyClickingGeneralTab();
				verifyGenerateItemSim(CopyItemName);
				clickPostInItemAndCheckAutoApproveflag();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("ROAU-BU"),
							VariableHandler.getItemName(), webDB.getDataFromProperties("HariEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Waiting for Approval Dialog Box
	 * 
	 * @author Durai-RMITS
	 */
	public void waitingForApprovalOpo() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(RfqLocators.RfqWorkflowFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(RfqLocators.PRForwardlov,
					commonFunc.getDataFromTestDataSheet("AshokValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(RfqLocators.ItmworkflowOK, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.ItmworkflowOK, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.hardWait(7000);
				flag = commonFunc.logoutUser();
			}
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To PO Filter All Field Working
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreationOfPricelistFilterAllFieldWorking() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		verifyAmendFilterSearchSOB(PricelistPageLocators.LocId, commonFunc.getDataFromTestDataSheet("Loc_100"), 3,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.Unit, commonFunc.getDataFromTestDataSheet("Unit_100"), 4,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.PRLNO, commonFunc.getDataFromTestDataSheet("PRLNumber"), 6,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.SupplierNameLovButton,
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"), 8,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.ItemId, commonFunc.getDataFromTestDataSheet("PRLItem"), 9,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.ItemDesc, commonFunc.getDataFromTestDataSheet("PRLItem"), 11,
				commonFunc.getDataFromTestDataSheet("SendTextInput"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.PRL_Status,
				commonFunc.getDataFromTestDataSheet("ApproveStatus"), 15,
				commonFunc.getDataFromTestDataSheet("SelectType"));
//		verifyDateAfterAndBefore();
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To PO Filter All Field Working
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreationOfPricelistAmdFilterAllFieldWorking() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePricelistPage();
		verifyAmendFilterSearchSOB(PricelistPageLocators.LocId, commonFunc.getDataFromTestDataSheet("Loc_100"), 3,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.Unit, commonFunc.getDataFromTestDataSheet("Unit_100"), 4,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.PRLNO, commonFunc.getDataFromTestDataSheet("PRLNumber"), 6,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.SupplierNameLovButton,
				commonFunc.getDataFromTestDataSheet("PriceListSupplier"), 8,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.ItemId, commonFunc.getDataFromTestDataSheet("PRLItem"), 9,
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.ItemDesc, commonFunc.getDataFromTestDataSheet("PRLItem"), 11,
				commonFunc.getDataFromTestDataSheet("SendTextInput"));
		verifyAmendFilterSearchSOB(PricelistPageLocators.PRL_Status,
				commonFunc.getDataFromTestDataSheet("AmendedStatus"), 15,
				commonFunc.getDataFromTestDataSheet("SelectType"));
//		verifyDateAfterAndBefore();
		commonFunc.logoutUser();
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
	public void verifyAmendFilterSearchSOB(String LovButton, String Value, int i, String selectflag) {
		webDB.scrollToAnElement(LovButton, ElementType.Xpath);
		if (selectflag.equals(commonFunc.getDataFromTestDataSheet("LovButton"))) {
			commonFunc.searchAndSelectValueInFilters(LovButton, Value);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SelectType"))) {
			webDB.selectDropDownByText(LovButton, Value, ElementType.Xpath);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SendTextInput"))) {
			webDB.sendTextToAnElement(LovButton, Value, ElementType.Xpath);
		}
//		if (Type.equals(commonFunc.getDataFromTestDataSheet("HeaderSearchType"))) {
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValue(i, Value);
//		} 
//		
//		else if (Type.equals(commonFunc.getDataFromTestDataSheet("LineSearchType"))) {
//			clickLineSearchButton();
//			commonFunc.hardWait(3000);
//			verifyLineAmendTableValue(i, Value);
//		}
		commonFunc.expandFindTable();
		clearSearchFilter();
	}

	/**
	 * Verify Date From And To
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDateAfterAndBefore() {
		flag = webDB.waitForElement(PricelistPageLocators.PRLDtFrom, ElementType.Xpath);
		webDB.sendTextToAnElement(PricelistPageLocators.PRLDtFrom, commonFunc.getDataFromTestDataSheet("EffFrom"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueAfterDate(5, commonFunc.getDataFromTestDataSheet("EffFrom"));
		clearSearchFilter();
		flag = webDB.waitForElement(PricelistPageLocators.PRLDtTo, ElementType.Xpath);
		webDB.sendTextToAnElement(PricelistPageLocators.PRLDtTo, commonFunc.getDataFromTestDataSheet("EffTo"),
				ElementType.Xpath);
		webDB.clickAnElement(PricelistPageLocators.ItemDesc, ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueBeforeDate(5, commonFunc.getDataFromTestDataSheet("EffTo"));
		clearSearchFilter();
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(PricelistPageLocators.PRLSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.PRLSearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");

			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PricelistPageLocators.PRLFindTable, ElementType.Xpath);
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
				.findElements(By.xpath(DirectGrnPageLocators.AmendFindTableData(i)));
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
	 * This Method Is Used To Verify Clear Search Filter
	 * 
	 * @author Deepan-GWL
	 */
	public void clearSearchFilter() {
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(PricelistPageLocators.ClearButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ClearButton, ElementType.Xpath);
			logs.logging("INFO", "Clear Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
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
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(PricelistPageLocators.PRLFindTableData(i)));
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
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(PricelistPageLocators.PRLFindTableData(i)));
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

	private void VerifyAddFrame() {
		flag = webDB.waitForElement(PricelistPageLocators.PricelistAddFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PricelistPageLocators.PricelistAddFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched to Frame");
		}
	}
}
