package commonfunctions.supplychainmanagement;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.poifs.dev.POIFSViewEngine;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.SalesCatalogueLocators;
import commonlocators.supplychainmanagement.SetupSalesLocators;
import groovyjarjarantlr4.v4.runtime.tree.xpath.XPath;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SetupSalesFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * Creates Sales Setup
	 * 
	 * @author Kowshik-RMITS
	 */
	public void CreatesSalesSetup() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSetup_Sales();
		clickSalesRegion();
		clickAddButtonSalesRegion();
		CreatesSalesRegion(commonFunc.getDataFromTestDataSheet("South"));
		commonFunc.hardWait(1000);
		InsertManagerAgainstSalesRegion(commonFunc.getDataFromTestDataSheet("SalesRegionManager"));
		commonFunc.hardWait(1000);
		getSalesTextElement();
		clickSaveButton();
		clickSalesZonetab();
		clickSalesZoneAddPlus();
		CreatesSalesZone(commonFunc.getDataFromTestDataSheet("South"));
		getSalesTextElementSalesZone();
		insertSalesRegionInSalesZoneTab();
		InsertManagerAgainstSalesZone(commonFunc.getDataFromTestDataSheet("SalesRegionManager"));
		clickSaveButtonInSaleZoneTab();
		clickSubterritoyTab();
		clickAddPlusButtonSubterritory();
		commonFunc.hardWait(1000);
		CreatesSalesSubTerritoy(commonFunc.getDataFromTestDataSheet("South"));
		commonFunc.hardWait(1000);
		InsertSalesZoneInSubTerritoryTab();
		insertSubTerritoryManagerLovField(commonFunc.getDataFromTestDataSheet("SalesRegionManager"));
		clickSaveButtonInSubTerritory();

	}

	/**
	 * Click Sales Region in Setup Sales Screen
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSalesRegion() {
		flag = webDB.waitForElement(SetupSalesLocators.salesRegion, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SetupSalesLocators.salesRegion, ElementType.Xpath);
			flag = webDB.waitForElement(SetupSalesLocators.SalesRegionAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked Sales Region");
			}

		}
		commonFunc.hardWait(2000);
		assertTrue(flag);
	}

	/**
	 * Click Add Button Sales Region Tap
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickAddButtonSalesRegion() {
		flag = webDB.waitForElement(SetupSalesLocators.SalesRegionAddButton, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(SetupSalesLocators.SalesRegionAddButton, ElementType.Xpath);
			// webDB.doubleClickAnElement(SetSalesLocators.SalesRegionAddButton,
			// ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SetupSalesLocators.SalesRegionField, ElementType.Xpath);
			if (flag) {
				logs.logging("info", "Successfully Click Add Button");
			}
		}
		commonFunc.hardWait(1000);
		assertTrue(flag);
	}

	/**
	 * Creates Sales Region
	 * 
	 * @author Kowshik-RMITS
	 * @param Itm
	 */
	public void CreatesSalesRegion(String Itm) {

		String SalesRegion = RandomStringUtils.randomNumeric(3);
		if (SalesRegion.equals("000")) {
			SalesRegion = "10";
		}
		String SimItemId = Itm + SalesRegion;

		flag = webDB.waitForElement(SetupSalesLocators.SalesRegionField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(SetupSalesLocators.SalesRegionField, SimItemId, ElementType.Xpath);
			logs.logging("INFO", "Sales Region Generated" + SimItemId);
			webDB.pressTabKey();
		}
	}

	/**
	 * Creates Sales Zone
	 * 
	 * @author Kowshik-RMITS
	 * @param Itm
	 */
	public void CreatesSalesZone(String Itm) {
		String SalesZones = RandomStringUtils.randomNumeric(4);
		if (SalesZones.equals("0000")) {
			SalesZones = "10";
		}
		String SimItemId = Itm + SalesZones;

		flag = webDB.waitForElement(SetupSalesLocators.SalesZoneField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(SetupSalesLocators.SalesZoneField, SalesZones, ElementType.Xpath);
			logs.logging("INFO", "Sales Region Generated" + SalesZones);
			webDB.pressTabKey();
		}
	}

	/**
	 * Creates Sales SubTerritory
	 * 
	 * @author Kowshik-RMITS
	 * @param Itm
	 */
	public void CreatesSalesSubTerritoy(String Itm) {
		String SalesSubTerritory = RandomStringUtils.randomNumeric(4);
		if (SalesSubTerritory.equals("0000")) {
			SalesSubTerritory = "10";
		}
		String SimItemId = Itm + SalesSubTerritory;

		flag = webDB.waitForElement(SetupSalesLocators.SubTerritoryField, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(SetupSalesLocators.SubTerritoryField, ElementType.Xpath);
			webDB.sendTextToAnElement(SetupSalesLocators.SubTerritoryInput, SalesSubTerritory, ElementType.Xpath);
			logs.logging("INFO", "Successfully Creates a Subterritory " + SalesSubTerritory);
		}
		assertTrue(flag);
	}

	/**
	 * Insert Manager Against Sales Region
	 * 
	 * 
	 * @author Kowshik-RMITS
	 * @param SalesRegionManager
	 */
	public void InsertManagerAgainstSalesRegion(String SalesRegionManager) {

		commonFunc.searchAndSelectValueInFilters(SetupSalesLocators.ManagerLovButton, SalesRegionManager);
		flag = webDB.waitForElement(SetupSalesLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Inserted a Manager " + SalesRegionManager);
		}
		assertTrue(flag);
	}

	/**
	 * Click Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButton() {
		flag = webDB.waitForElement(SetupSalesLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SetupSalesLocators.SaveButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SetupSalesLocators.SaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(SetupSalesLocators.SalesZone, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Click a Save Button ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Sales Zone Tap
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSalesZonetab() {
		flag = webDB.waitForElement(SetupSalesLocators.SalesZone, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SetupSalesLocators.SalesZone, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SetupSalesLocators.SalesZone, ElementType.Xpath);
			flag = webDB.waitForElement(SetupSalesLocators.SalesZoneAddPlus, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a Sales Zone Tap");
			}
		}
		assertTrue(flag);
	}

	/**
	 * get Text Element From Sales Region
	 * 
	 * @@author Kowshik-RMITS
	 */
	public void getSalesTextElement() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SetupSalesLocators.SalesRegionFielddoc, ElementType.Xpath);
		if (flag) {
			String SalesRegionText = webDB.getTextFromElement(SetupSalesLocators.SalesRegionFielddoc,
					ElementType.Xpath);
			VariableHandler.setSalesRegionText(SalesRegionText);
			logs.logging("info", "Get Price List Document no " + VariableHandler.getSalesRegionText());
			commonFunc.hardWait(2000);
		}
	}

	/**
	 * Click Sales Zone Add Plus
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSalesZoneAddPlus() {
		flag = webDB.waitForElement(SetupSalesLocators.SalesZoneAddPlus, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SetupSalesLocators.SalesZoneAddPlus, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SetupSalesLocators.SalesZoneAddPlus, ElementType.Xpath);
			flag = webDB.waitForElement(SetupSalesLocators.SalesZoneField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Click a Sales Zone Add Button ");
			}

			assertTrue(flag);
		}
	}

	/**
	 * get Text Element From Sales Zone
	 * 
	 * @@author Kowshik-RMITS
	 */
	public void getSalesTextElementSalesZone() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SetupSalesLocators.SalesZoneFieldDoc, ElementType.Xpath);
		if (flag) {
			String SalesRegionText = webDB.getTextFromElement(SetupSalesLocators.SalesZoneFieldDoc, ElementType.Xpath);
			VariableHandler.setSalesZoneText(SalesRegionText);
			logs.logging("info", "Get Price List Document no " + VariableHandler.getSalesRegionText());
			commonFunc.hardWait(2000);
		}
	}

	/**
	 * Insert Sales Region Sales Zone Tap
	 * 
	 * @author Kowshik-RMITS
	 */
	public void insertSalesRegionInSalesZoneTab() {

		commonFunc.searchAndSelectValueInFilters(SetupSalesLocators.SalesRegionLovSZTab,
				VariableHandler.getSalesRegionText());
		webDB.waitForElement(SetupSalesLocators.SalesRegionLovSZTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Insert a Sales Region");
		}
		assertTrue(flag);
	}

	/**
	 * Insert Manager Against Sales Zone
	 * 
	 * 
	 * @author Kowshik-RMITS
	 * @param SalesRegionManager
	 */
	public void InsertManagerAgainstSalesZone(String SalesRegionManager) {
		webDB.pressTabKey();
		commonFunc.searchAndSelectValueInFilters(SetupSalesLocators.SalesZoneManagerLovSZTab, SalesRegionManager);
		flag = webDB.waitForElement(SetupSalesLocators.SaveButtonSalesZoneTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Inserted a Manager " + SalesRegionManager);
		}
		assertTrue(flag);
	}

	/**
	 * Click Save Button SalesZone Tab
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButtonInSaleZoneTab() {
		flag = webDB.waitForElement(SetupSalesLocators.SaveButtonSalesZoneTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SetupSalesLocators.SaveButtonSalesZoneTab, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SetupSalesLocators.SaveButtonSalesZoneTab, ElementType.Xpath);
			flag = webDB.waitForElement(SetupSalesLocators.subterritoyTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Click a Save Button ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click SubTerritoy Tab
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSubterritoyTab() {
		flag = webDB.waitForElement(SetupSalesLocators.subterritoyTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SetupSalesLocators.subterritoyTab, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SetupSalesLocators.subterritoyTab, ElementType.Xpath);
			flag = webDB.waitForElement(SetupSalesLocators.AddPlusButtonSubTerritory, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a SubTerritoy Tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Add Plus SubTerriTory
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickAddPlusButtonSubterritory() {
		flag = webDB.waitForElement(SetupSalesLocators.AddPlusButtonSubTerritory, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SetupSalesLocators.AddPlusButtonSubTerritory, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SetupSalesLocators.AddPlusButtonSubTerritory, ElementType.Xpath);
			flag = webDB.waitForElement(SetupSalesLocators.SubTerritoryInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Click a Add Plus");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Sales Zone In SubTerriTory Tab
	 * 
	 * @author Kowshik-RMITS
	 */
	public void InsertSalesZoneInSubTerritoryTab() {

		webDB.pressTabKey();
		commonFunc.searchAndSelectValueInFilters(SetupSalesLocators.SalesZoneLovSubTerritoryField,
				VariableHandler.getSalesZoneText());
		flag = webDB.waitForElement(SetupSalesLocators.SubTerritoryManagerLov, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Inserted a Manager " + VariableHandler.getSalesZoneText());
		}
		assertTrue(flag);
	}

	/**
	 * Insert SubTerritory Manager
	 * 
	 * @author Kowshik-RMITS
	 * @param SalesRegionManager
	 */
	public void insertSubTerritoryManagerLovField(String SalesRegionManager) {
		webDB.pressTabKey();
		commonFunc.searchAndSelectValueInFilters(SetupSalesLocators.SubTerritoryManagerLov, SalesRegionManager);
		flag = webDB.waitForElement(SetupSalesLocators.SaveButtonInSubTerritory, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Inserted a Manager " + SalesRegionManager);
		}
		assertTrue(flag);
	}

	/**
	 * Click Save Button In SubTerritory
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButtonInSubTerritory() {
		flag = webDB.waitForElement(SetupSalesLocators.SaveButtonInSubTerritory, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(SetupSalesLocators.SaveButtonInSubTerritory, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Click a Save Button");
			}

		}
		assertTrue(flag);
	}

}
