package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 14, 2025
 */
import static org.testng.Assert.assertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import commonfunctions.CommonFunctions;
import commonfunctions.sysadministration.EmployeeQuickEntryFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.SetupPurchaseLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SetupPurchaseFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static EmployeeQuickEntryFunctions emplyeeQuickEntryFunc = new EmployeeQuickEntryFunctions();

	/**
	 * Navigate To Buyer Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyNavigateToBuyerTab() {
		flag = webDB.waitForElement(CommonUiLocators.BuyerTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.BuyerTab, ElementType.Xpath);
			flag = webDB.waitForElement(SetupPurchaseLocators.AddBuyerButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Buyer Tab Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Add Buyer Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickAddBuyerButton(String BuyerName, String EmployeeId, String EffTo) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		flag = webDB.waitForElement(SetupPurchaseLocators.AddBuyerButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			webDB.clickAnElement(SetupPurchaseLocators.AddBuyerButton, ElementType.Xpath);
			flag = webDB.waitForElement(SetupPurchaseLocators.BuyerNameTable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Buyer Button Is Clicked");
				webDB.clickAnElement(SetupPurchaseLocators.BuyerNameTable, ElementType.Xpath);
				flag = webDB.waitForElement(SetupPurchaseLocators.BuyerNameInput, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(SetupPurchaseLocators.BuyerNameInput, BuyerName, ElementType.Xpath);
					logs.logging("INFO", "Buyer Name Is: " + BuyerName);
					flag = webDB.waitForElement(SetupPurchaseLocators.EmployeeIdTable, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SetupPurchaseLocators.EmployeeIdTable, ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(SetupPurchaseLocators.EmployeeLovButton, EmployeeId);
						flag = webDB.waitForElement(SetupPurchaseLocators.EffFromTable, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SetupPurchaseLocators.EffFromTable, ElementType.Xpath);
							flag = webDB.waitForElement(SetupPurchaseLocators.EffFromToday, ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(SetupPurchaseLocators.EffFromToday, Today, ElementType.Xpath);
								logs.logging("INFO", "Effective From Is Selected Successfully");
								flag = webDB.waitForElement(SetupPurchaseLocators.EffToIdTable, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(SetupPurchaseLocators.EffToIdTable, ElementType.Xpath);
									flag = webDB.waitForElement(SetupPurchaseLocators.EffToInput, ElementType.Xpath);
									if (flag) {
										webDB.sendTextToAnElement(SetupPurchaseLocators.EffToInput, EffTo,
												ElementType.Xpath);
										logs.logging("INFO", "Effective To Input Is: " + EffTo);
										flag = webDB.waitForElement(SetupPurchaseLocators.TickBuyerButton,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(SetupPurchaseLocators.TickBuyerButton,
													ElementType.Xpath);
											if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
													ElementType.Xpath)) {
												webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
														ElementType.Xpath);
											}
											logs.logging("INFO", "Buyer Is Created Successfully");
										}
									}
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
	 * This Method Is Used To Filer Buyer Name And Mark CheckBox
	 * 
	 * @author Deepan-GWL
	 * @param BuyerName
	 */
	public void filterBuyerNameAndMarkCheckbox(String BuyerName) {
		commonFunc.hardWait(3000);
		webDB.clickAnElement(CommonUiLocators.BuyerTab, ElementType.Xpath);
		webDB.javaScriptClickAnElement(SetupPurchaseLocators.BuyerTable, ElementType.Xpath);
		flag = webDB.waitForElement(SetupPurchaseLocators.BuyerSearch, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(SetupPurchaseLocators.BuyerSearch, BuyerName, ElementType.Xpath);
			commonFunc.hardWait(5000);
			webDB.pressEnterKey();
			flag = webDB.waitForElement(SetupPurchaseLocators.RemoveFilter, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Filter Employee Name In Buyer Table " + BuyerName);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Click Active And Admin CheckBox
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickActiveAndAdminCheckBox() {
		commonFunc.hardWait(2500);
		webDB.clickAnElement(SetupPurchaseLocators.AdminCheckbox, ElementType.Xpath);
		webDB.clickAnElement(SetupPurchaseLocators.ActiveCheckbox, ElementType.Xpath);
		flag = webDB.waitForElement(SetupPurchaseLocators.UpdateButton, ElementType.Xpath);
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Update Buyer Details
	 * 
	 * @author Deepan-GWL
	 * @param EmployeeName
	 * @param EffTo
	 */
	public void verifyUpdateBuyerDetails(String EmployeeName, String EffTo) {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		flag = webDB.waitForElement(SetupPurchaseLocators.UpdateButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SetupPurchaseLocators.UpdateButton, ElementType.Xpath);
			flag = webDB.waitForElement(SetupPurchaseLocators.UpdateHeader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Update Button Is Clicked Successfully");
				webDB.switchToFrame(SetupPurchaseLocators.UpdateIframe, ElementType.Xpath);
				webDB.selectDropDownByText(SetupPurchaseLocators.EmployeeNameSelect, EmployeeName, ElementType.Xpath);
				logs.logging("INFO", "Employee Name Is Selected " + EmployeeName);
				webDB.sendTextToAnElement(SetupPurchaseLocators.EmployeeEffFrom, today.format(formatter),
						ElementType.Xpath);
				logs.logging("INFO", "Effective From Date Is: " + today.format(formatter));
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SetupPurchaseLocators.UpdateHeader, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SetupPurchaseLocators.UpdateHeader, ElementType.Xpath);
					webDB.switchToFrame(SetupPurchaseLocators.UpdateIframe, ElementType.Xpath);
					webDB.sendTextToAnElement(SetupPurchaseLocators.EmployeeEffTo, EffTo, ElementType.Xpath);
					logs.logging("INFO", "Effective To Date Is: " + EffTo);
					commonFunc.hardWait(1000);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(SetupPurchaseLocators.UpdateHeader, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SetupPurchaseLocators.UpdateHeader, ElementType.Xpath);
						webDB.switchToFrame(SetupPurchaseLocators.UpdateIframe, ElementType.Xpath);
						flag = webDB.waitForElement(SetupPurchaseLocators.OkButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SetupPurchaseLocators.OkButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
							if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath);
							}
							flag = webDB.waitForElement(SetupPurchaseLocators.AddBuyerButton, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Buyer Is Updated Successfully");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Create And Update Buyer Name
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateAndUpdateBuyerName() {
		String SeqNo = RandomStringUtils.randomNumeric(5);
		String EmployeeName = "AUTOMATION " + SeqNo;
		String EmployeeId = "AUTO" + SeqNo;
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"HodUsername-Admin");
		commonFunc.navigateToEmployeeQuickEntryTab();
		String Mobile = commonFunc.getDataFromTestDataSheet("Mobile") + SeqNo;
		String Mail = commonFunc.getDataFromTestDataSheet("Email") + SeqNo
				+ commonFunc.getDataFromTestDataSheet("Gmail");
		emplyeeQuickEntryFunc.verifyCreateNewEmployee(EmployeeId, EmployeeName,
				commonFunc.getDataFromTestDataSheet("PurchaseManager"), commonFunc.getDataFromTestDataSheet("Purchase"),
				Mobile, Mail);
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"HodUsername-Admin");
		String BuyerName = "AUTO " + SeqNo;
		commonFunc.navigateToPurchaseSetupTab();
		verifyNavigateToBuyerTab();
		clickAddBuyerButton(BuyerName, EmployeeName, commonFunc.getDataFromTestDataSheet("EffToDate"));
		commonFunc.logoutUser();
		verifyUpdateBuyer(EmployeeName);
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Update Buyer
	 * 
	 * @author Deepan-GWL
	 * @param EmployeeName
	 */
	public void verifyUpdateBuyer(String EmployeeName) {
		String SeqNo = RandomStringUtils.randomNumeric(5);
		String EmployeeName1 = "AUTOMATION " + SeqNo;
		String EmployeeId = "AUTO" + SeqNo;
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"HodUsername-Admin");
		commonFunc.navigateToEmployeeQuickEntryTab();
		String Mobile = commonFunc.getDataFromTestDataSheet("Mobile") + SeqNo;
		String Mail = commonFunc.getDataFromTestDataSheet("Email") + SeqNo
				+ commonFunc.getDataFromTestDataSheet("Gmail");
		emplyeeQuickEntryFunc.verifyCreateNewEmployee(EmployeeId, EmployeeName1,
				commonFunc.getDataFromTestDataSheet("PurchaseManager"), commonFunc.getDataFromTestDataSheet("Purchase"),
				Mobile, Mail);
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"HodUsername-Admin");
		commonFunc.navigateToPurchaseSetupTab();
		verifyNavigateToBuyerTab();
		filterBuyerNameAndMarkCheckbox(EmployeeName);
		verifyClickActiveAndAdminCheckBox();
		verifyUpdateBuyerDetails(EmployeeName1 + "  ", commonFunc.getDataFromTestDataSheet("EffToDate"));
	}
}
