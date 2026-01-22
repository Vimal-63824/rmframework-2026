package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import commonlocators.supplychainmanagement.SupplierScheduleOrderLocators;
import commonlocators.supplychainmanagement.SupplierScheduleRequestLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SupplierScheduleOrderFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This Method Is Used To Verify Move Pending And Post Doc
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyMovePendingAndPostDocuments(String Supplier) {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickPendingIcon();
		clickPendingSSRAndSearchSSRNo();
		verifyClickCheckBox(Supplier);
		verifyStoreSSODocNumber();
		clickHeaderSaveAndPost();
		verifyWaitingForApproval();
		commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getSSODocNumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
				System.getProperty("HariUsername"));
		searchSSOAndCheckSSOStatus(VariableHandler.getSSODocNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));

	}

	/**
	 * This method used to click pending and search SSR no and verify
	 * 
	 * @author Deepan-GWL
	 */
	public void clickPendingSSRAndSearchSSRNo() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SupplierScheduleOrderLocators.PendingSSRButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SupplierScheduleOrderLocators.PendingSSRButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SupplierScheduleOrderLocators.PendingSSRFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated to the Pending PR Successfully");
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToFrame(SupplierScheduleOrderLocators.PendingSSRFrame, ElementType.Xpath);
				flag = webDB.waitForElement(SupplierScheduleOrderLocators.SSRNumberLovButton, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SupplierScheduleOrderLocators.SSRNumberLovButton,
							VariableHandler.getSSRDocNumber());
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(SupplierScheduleOrderLocators.PendingSSRFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(SupplierScheduleOrderLocators.PendingSSRFrame, ElementType.Xpath);
						flag = webDB.waitForElement(SupplierScheduleOrderLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(SupplierScheduleOrderLocators.SearchButton, ElementType.Xpath);
							webDB.clickAnElement(SupplierScheduleOrderLocators.SearchButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							logs.logging("INFO", "Pending SSR Search Button Clicked");
							webDB.waitForElement("//table//td[3]", ElementType.Xpath);
							List<WebElement> elements = webDB.getDriver().findElements(By.xpath("//table//td[3]"));
							a: for (WebElement webElement : elements) {
								if (!webElement.getText().equals(VariableHandler.getSSRDocNumber())) {
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
	 * This Method Is Used To Verify CheckBox
	 * 
	 * @author Deepan-GWL
	 * @param Supplier
	 */
	public void verifyClickCheckBox(String Supplier) {
		flag = webDB.waitForElement(SupplierScheduleOrderLocators.CheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SupplierScheduleOrderLocators.CheckBox, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElement(SupplierScheduleOrderLocators.CreateSSO, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			for (int i = 0; i < 60; i++) {
				commonFunc.hardWait(1000);
				if (webDB.isElementEnabled(SupplierScheduleOrderLocators.CreateSSO, ElementType.Xpath)) {
					webDB.clickAnElement(SupplierScheduleOrderLocators.CreateSSO, ElementType.Xpath);
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
			commonFunc.searchAndSelectValueInFilters(SupplierScheduleOrderLocators.SupplierIdLovButton, Supplier);
			webDB.switchToFrame(SupplierScheduleOrderLocators.PendingSSRFrame, ElementType.Xpath);
			flag = webDB.waitForElement(SupplierScheduleOrderLocators.OkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SupplierScheduleOrderLocators.OkButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SupplierScheduleOrderLocators.SSODocNumber, ElementType.Xpath);
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Click Header Save And Post
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void clickHeaderSaveAndPost() {

		flag = webDB.waitForElement(SupplierScheduleOrderLocators.HeaderSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SupplierScheduleOrderLocators.HeaderSaveButton, ElementType.Xpath);
			webDB.clickAnElement(SupplierScheduleOrderLocators.HeaderSaveButton, ElementType.Xpath);
			logs.logging("INFO", "Header Saved Successfully.");
			commonFunc.hardWait(2000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SupplierScheduleOrderLocators.GenerateSchedule, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				webDB.clickAnElement(SupplierScheduleOrderLocators.GenerateSchedule, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
				flag = webDB.waitForElement(SupplierScheduleOrderLocators.HeaderPostButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SupplierScheduleOrderLocators.HeaderPostButton, ElementType.Xpath);
					logs.logging("INFO", "Header Post Icon Is Clicked");
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				}
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
					commonFunc.getDataFromTestDataSheet("HariKrishnaValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SupplierScheduleOrderLocators.AddSSOButton, ElementType.Xpath);
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
	 * This Method Is Used To Verify Store SSo Document Number
	 * 
	 * @author Deepan
	 */
	public void verifyStoreSSODocNumber() {
		flag = webDB.waitForElement(SupplierScheduleOrderLocators.SSODocNumber, ElementType.Xpath);
		if (flag) {
			String SSODocNumber = webDB.getAttributeFromElement(SupplierScheduleOrderLocators.SSODocNumber,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "SSO Document Number Is: " + SSODocNumber);
			VariableHandler.setSSODocNumber(SSODocNumber);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Check SSO Status
	 * 
	 * @author Sakthi-GWL
	 * @param PRNumber
	 * @param ExpextedStatus
	 */
	public void searchSSOAndCheckSSOStatus(String SSONumber, String ExpextedStatus) {
		commonFunc.searchAndSelectValueInFilters(SupplierScheduleOrderLocators.SSOLovButton, SSONumber);
		flag = webDB.waitForElement(SupplierScheduleRequestLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SupplierScheduleRequestLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseRequestLocators.FindSearchButton, ElementType.Xpath);

				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SupplierScheduleOrderLocators.ApprovedStatus(SSONumber), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(SupplierScheduleOrderLocators.ApprovedStatus(SSONumber),
							ElementType.Xpath);
					logs.logging("INFO", "SSO Number: '" + SSONumber + "' searched and displaying status: " + Status);
					flag = Status.equalsIgnoreCase(ExpextedStatus);
				}
			}
		}
		Assert.assertTrue(flag);
	}
}
