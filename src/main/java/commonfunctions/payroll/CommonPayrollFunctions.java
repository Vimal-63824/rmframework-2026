package commonfunctions.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.payroll.CommonPayrollLocators;
import commonlocators.payroll.EmployeeMasterLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CommonPayrollFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This method used to add data in table without lov button
	 * 
	 * @author Sakthi-GWL
	 * @param TableDataLocator
	 * @param InputBoxLocator
	 * @param Value
	 */
	public void addDetailInTableWithoutLovButton(String TableDataLocator, String InputBoxLocator, String Value) {
		flag = webDB.waitForElement(TableDataLocator, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(TableDataLocator, ElementType.Xpath);
			webDB.doubleClickAnElement(TableDataLocator, ElementType.Xpath);
			webDB.waitForElement(InputBoxLocator, ElementType.Xpath);
			webDB.clearText(InputBoxLocator, ElementType.Xpath);
			flag = webDB.waitForElement(TableDataLocator, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(TableDataLocator, ElementType.Xpath);
				webDB.doubleClickAnElement(TableDataLocator, ElementType.Xpath);
				flag = webDB.waitForElement(InputBoxLocator, ElementType.Xpath);
				if (flag) {
					try {
						JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
						WebElement InputElement = webDB.getDriver().findElement(By.xpath(InputBoxLocator));
						js.executeScript("arguments[0].value='" + Value + "';", InputElement);
						logs.logging("INFO", Value + " - Added in the Table Data");
					} catch (Exception e) {
						flag = false;
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to open new tab and store leave entitlement enabled ids in
	 * variable handler
	 * 
	 * @author Sakthi-GWL
	 */
	public void openLeaveLoanSetupInNewTabAndStoreLeaveEntitlement() {
		commonFunc.checkSuccessToastMessageDisappear();
		commonFunc.hardWait(3000);
		commonFunc.navigateToSetupLeaveLoanScreen();
		flag = webDB.waitForElement(CommonPayrollLocators.LeaveAndLoadScreen_LeaveSubTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Setup Leave & Loan Screen clicked");
			String LeaveLoanURL = webDB.getDriver().getCurrentUrl();
			logs.logging("INFO", "Leave And Loan Setup Page URL getted: " + LeaveLoanURL);
			webDB.navigateToBack();
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveDetailsSideNav, ElementType.Xpath);
			if (!flag) {
				webDB.navigateToBack();
			}
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveDetailsSideNav, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated Back to Employee Creation Page");
				webDB.openNewTabWithUrl(LeaveLoanURL);
				flag = webDB.waitForElement(CommonPayrollLocators.LeaveAndLoadScreen_LeaveSubTab, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "New Tab Opened With Leave Loan Page URL: " + LeaveLoanURL);
					webDB.clickAnElement(CommonPayrollLocators.LeaveAndLoadScreen_LeaveSubTab, ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.LeaveEntitlementLists, ElementType.Xpath);
				}
			}
		}
		checkLeaveEntitlementForLeaveCategory();
		webDB.closeExistingTab();
		webDB.switchToMainTab();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveDetailsSideNav, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Switched to Main Tab Successfully");
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to store leave entitlement enabled ids in variable handler
	 * 
	 * @author Sakthi-GWL
	 */
	private void checkLeaveEntitlementForLeaveCategory() {
		ArrayList<String> GreenFlagLeaveIDs = new ArrayList<String>();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(EmployeeMasterLocators.LeaveEntitlementLists, ElementType.Xpath);
		if (flag) {
			List<WebElement> IconElements = webDB.getDriver()
					.findElements(By.xpath(EmployeeMasterLocators.LeaveEntitlementLists));
			a: for (int i = 1; i < IconElements.size() + 1; i++) {
				flag = webDB.waitForElement(EmployeeMasterLocators.LeaveEntitlementIcon(i + 1), ElementType.Xpath);
				if (flag) {
					String styleColor = webDB.getDomAttributeFromElement(
							EmployeeMasterLocators.LeaveEntitlementIcon(i + 1), ElementType.Xpath, "style");
					if (styleColor.contains("green")) {
						flag = true;
						String value = webDB.getTextFromElement(
								EmployeeMasterLocators.LeaveTypeTableLeaveIDTexts(i + 1), ElementType.Xpath);
						GreenFlagLeaveIDs.add(value);
						logs.logging("INFO", "Entitled Leave ID: " + value + " added in variable handler successfully");
					}
				} else {
					flag = false;
					break a;
				}
			}
		}
		VariableHandler.setEmpSetupScreenGreenFlagLeaveIDs(GreenFlagLeaveIDs);
		flag = VariableHandler.getEmpSetupScreenGreenFlagLeaveIDs().size() != 0;
		Assert.assertTrue(flag);
	}

}
