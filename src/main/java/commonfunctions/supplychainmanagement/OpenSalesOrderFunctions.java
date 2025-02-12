package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.*;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class OpenSalesOrderFunctions {

	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();

	/**
	 * To click a Radio Button Of Open Sales Order
	 * 
	 * @author Kowshik
	 */
	public void ClickaRadiobtn() {
		flag = webDB.waitForClickElement(OpenSalesOrderLocators.OpenSoRadio, ElementType.Xpath);
		if (flag) {
			flag = webDB.clickAnElement(OpenSalesOrderLocators.OpenSoRadio, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Open So Radio Button Clicked Successfully ");
				flag = webDB.waitForClickElement(OpenSalesOrderLocators.LocLOVBTN, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * click location Lov Buttn
	 * 
	 * @author Kowshik
	 * @param Locution
	 * @param CUSTOMER
	 */
	public void clicklocLOV(String Locution, String CUSTOMER) {
		flag = webDB.waitForClickElement(OpenSalesOrderLocators.LocLOVBTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSalesOrderLocators.LocLOVBTN, ElementType.Xpath);

			commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.LocLOVBTN, Locution);
			commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.cusLOVBTN, CUSTOMER);
			flag = webDB.moveToElement(OpenSalesOrderLocators.osoPFX, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Click Dft Button
	 * 
	 * @author Kowshik
	 */
	public void clickDft() {
		flag = webDB.waitForClickElement(OpenSalesOrderLocators.checkedBox, ElementType.Xpath);
		if (flag) {
			List<WebElement> checkBox = webDB.getDriver().findElements(By.xpath(OpenSalesOrderLocators.checkedBox));
			for (int i = 0; i < checkBox.size(); i++) {
				WebElement element = checkBox.get(i);
				try {
					if (element.isDisplayed()) {
						element.click();
					}
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Open So Prefix
	 * 
	 * @author Kowshik
	 */
	public void opensoPFX() {
		flag = webDB.moveToElement(OpenSalesOrderLocators.osoPFX, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByValue(OpenSalesOrderLocators.osoPFX, OpenSalesOrderLocators.pfx, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Sucessfully Inserted a Prefix");
				flag = webDB.waitForElement(OpenSalesOrderLocators.POnum, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Inpopup Screen Insert a Purchase Order Doc no
	 * 
	 * @author Kowshik
	 */
	public void insertPOnumandcreatedate() {
		flag = webDB.waitForElement(OpenSalesOrderLocators.POnum, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(OpenSalesOrderLocators.POnum, OpenSalesOrderLocators.ponumber, ElementType.Xpath);
			if (flag) {
				logs.logging("info", "successfully insett a po number");
				flag = webDB.waitForElement(OpenSalesOrderLocators.poDATE, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(OpenSalesOrderLocators.poDATE, OpenSalesOrderLocators.dateInput,
							ElementType.Xpath);
					webDB.clickAnElement(OpenSalesOrderLocators.POnum, ElementType.Xpath);
					flag = webDB.waitForClickElement(OpenSalesOrderLocators.saveBTN, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(OpenSalesOrderLocators.saveBTN, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
							flag = webDB.waitForClickElement(OpenSalesOrderLocators.fetchCUSitemBtn, ElementType.Xpath);
						}
					}
				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * Insert a item using Fetch Customer Items
	 * 
	 * @author Kowshik
	 */
	public void fetchBTN() {
		flag = webDB.waitForClickElement(OpenSalesOrderLocators.fetchCUSitemBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.fetchCUSitemBtn, ElementType.Xpath);
			webDB.doubleClickAnElement(OpenSalesOrderLocators.fetchCUSitemBtn, ElementType.Xpath);
			// ElementType.Xpath);
			if (flag) {
				logs.logging("info", "Fetch button clicked successfully ");
				flag = webDB.waitForElement(OpenSalesOrderLocators.iframe, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(OpenSalesOrderLocators.iframe, ElementType.Xpath);
					flag = webDB.waitForClickElement(OpenSalesOrderLocators.checkedBox, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(OpenSalesOrderLocators.checkedBox, ElementType.Xpath);
						webDB.clickAnElement(OpenSalesOrderLocators.checkedBox, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Insert a Item Manually in LineDetails
	 * 
	 * @param Item
	 * @param OSOdo
	 */
	public void manaullyAdditeminLines(String Item, String OSOdo) {
		if (flag)
			OSOdo = webDB.getTextFromElement(OpenSalesOrderLocators.osoDOC, ElementType.Xpath);
		logs.logging("info", "Get openso DocNo Is: " + OSOdo);
		VariableHandler.setOSOdocnum(OSOdo);

		flag = webDB.waitForClickElement(OpenSalesOrderLocators.PlusBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSalesOrderLocators.PlusBtn, ElementType.Xpath);
			flag = webDB.waitForClickElement(OpenSalesOrderLocators.itemLov, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSalesOrderLocators.itemLov, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.itemLov, Item);
				flag = webDB.waitForElement(OpenSalesOrderLocators.priceFieldcLK, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(OpenSalesOrderLocators.priceFieldcLK, ElementType.Xpath);
					webDB.clearText(OpenSalesOrderLocators.priceFieldcLK, ElementType.Xpath);
					webDB.sendTextToAnElement(OpenSalesOrderLocators.priceField, OpenSalesOrderLocators.price,
							ElementType.Xpath);

					flag = webDB.waitForClickElement(OpenSalesOrderLocators.DiscperClk, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(OpenSalesOrderLocators.DiscperClk, ElementType.Xpath);
						webDB.clearText(OpenSalesOrderLocators.DiscperClk, ElementType.Xpath);
						webDB.sendTextToAnElement(OpenSalesOrderLocators.DiscPercent, OpenSalesOrderLocators.discnumber,
								ElementType.Xpath);
						flag = webDB.waitForClickElement(OpenSalesOrderLocators.saveBTN1, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(OpenSalesOrderLocators.saveBTN1, ElementType.Xpath);

							if (flag) {
								logs.logging("INFO", "SUCCESSFULLY SAVES A LINES DETAILS ");
								flag = webDB.waitForElement(OpenSalesOrderLocators.effDate, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(OpenSalesOrderLocators.effDate,
											OpenSalesOrderLocators.effDates, ElementType.Xpath);
									flag = webDB.waitForClickElement(OpenSalesOrderLocators.saveOverall,
											ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(OpenSalesOrderLocators.saveOverall, ElementType.Xpath);

										commonFunc.checkRefreshCellsDisappear();
										if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath)) {
											webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
											flag = webDB.waitForClickElement(OpenSalesOrderLocators.POSTbTN,
													ElementType.Xpath);
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
	 * Click a Post Button for an Approval
	 * 
	 * @author Kowshik
	 */
	public void verifyPostBTN() {

		flag = webDB.waitForClickElement(OpenSalesOrderLocators.POSTbTN, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSalesOrderLocators.POSTbTN, ElementType.Xpath);
			webDB.clickAnElement(OpenSalesOrderLocators.POSTbTN, ElementType.Xpath);
			logs.logging("INFO", "POST");
			if (flag) {

				logs.logging("info ", "Successfully Posted  a Record");
				flag = webDB.waitForElement(OpenSalesOrderLocators.osoDOCnoField, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Cancelled Record
	 * 
	 * @author Kowshik
	 */
	public void verifyCancel() {
		flag = webDB.waitForClickElement(OpenSalesOrderLocators.cancelBTN, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSalesOrderLocators.cancelBTN, ElementType.Xpath);
			logs.logging("info", "Cancel");
			flag = webDB.waitForElement(OpenSalesOrderLocators.osoDOCnoField, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Searched a Cancelled Record
	 * 
	 * 
	 * @param OSOdo
	 */
	public void verifyCancelStatusRecord(String OSOdo) {
		flag = webDB.waitForElement(OpenSalesOrderLocators.osoDOCnoField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(OpenSalesOrderLocators.osoDOCnoField, OSOdo, ElementType.Xpath);
			logs.logging("Info", "Open So Doc Number Passed Sucessfully ");
			flag = webDB.waitForClickElement(OpenSalesOrderLocators.osoFindPageSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSalesOrderLocators.osoFindPageSearchBtn, ElementType.Xpath);
				logs.logging("Info", "Open So Cancel Doc Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * waiting for approval
	 * 
	 * @author Kowshik
	 */
	public boolean verifyWaitingForApproval1() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			// commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.OpsoForwardApproval,
			// commonFunc.getDataFromTestDataSheet("HodValue"));
			// commonFunc.hardWait(2000);
			// webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame,
			// ElementType.Xpath);
			// logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpsoOkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSalesOrderLocators.OpsoOkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;

	}

	/**
	 * Verify a Open So Process Pric Fixing
	 * 
	 * @param OSOdo
	 */
	public void VerifyOpenso() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickaRadiobtn();
		commonFunc.clickADDBTNoso();
		clicklocLOV(commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CUSName_OSO"));
		opensoPFX();
		insertPOnumandcreatedate();
		// fetchBTN();
		manaullyAdditeminLines(commonFunc.getDataFromTestDataSheet("ITEM_OSO"), VariableHandler.getOSONUM());
		// clickDft();
		verifyPostBTN();
		verifyWaitingForApproval1();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSONUM());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSONUM());
		flag = commonFunc.logoutUser();

	}

	/**
	 * Cancel Open SO
	 * 
	 * 
	 * @author Kowshik
	 */
	public void VerifyCancelStatement() {
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickaRadiobtn();
		commonFunc.clickADDBTNoso();
		clicklocLOV(commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("CUSName_OSO"));
		opensoPFX();
		insertPOnumandcreatedate();
		manaullyAdditeminLines(commonFunc.getDataFromTestDataSheet("ITEM_OSO"), VariableHandler.getOSONUM());
		verifyCancel();
		verifyCancelStatusRecord(VariableHandler.getOSONUM());
	}
}
