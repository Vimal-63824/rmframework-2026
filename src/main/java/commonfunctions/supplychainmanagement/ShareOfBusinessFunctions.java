package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.OpenPoRcLocators;
import commonlocators.supplychainmanagement.POAmendmentLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.ShareOfBusinessLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ShareOfBusinessFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	static PricelistPageFunctions priceListPageFunc = new PricelistPageFunctions();

	/**
	 * This Method Is Used To Verify Create New Share Of Business
	 * 
	 * @author Deepan-GWL
	 * @param Location
	 * @param EffectiveFrom
	 * @param EffTo
	 * @param Item
	 */
	public void verifyCreateNewShareOfBusiness(String Location, String EffectiveFrom, String EffTo, String Item) {
		flag = webDB.waitForElement(CommonUiLocators.AddShareOfBusiness, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.AddShareOfBusiness, ElementType.Xpath);
			flag = webDB.waitForElement(ShareOfBusinessLocators.SOBFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add SOB Button IS Clicked");
				webDB.switchToFrame(ShareOfBusinessLocators.SOBFrame, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(ShareOfBusinessLocators.LocationIdButton, Location);
				webDB.switchToFrame(ShareOfBusinessLocators.SOBFrame, ElementType.Xpath);
				webDB.sendTextToAnElement(ShareOfBusinessLocators.EffectiveFromInput, EffectiveFrom, ElementType.Xpath);
				commonFunc.hardWait(500);
				logs.logging("INFO", "Effective Send Text Is: " + EffectiveFrom);
				webDB.clickAnElement(ShareOfBusinessLocators.EffectiveToInput, ElementType.Xpath);
				webDB.sendTextToAnElement(ShareOfBusinessLocators.EffectiveToInput, EffTo, ElementType.Xpath);
				commonFunc.hardWait(500);
				logs.logging("INFO", "Effective To Send Text Is: " + EffTo);
				webDB.clickAnElement(ShareOfBusinessLocators.ShareOfBusinessHeader, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(ShareOfBusinessLocators.ItemLovButton, Item);
				webDB.switchToFrame(ShareOfBusinessLocators.SOBFrame, ElementType.Xpath);
				flag = webDB.waitForElement(ShareOfBusinessLocators.TickButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(ShareOfBusinessLocators.TickButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(ShareOfBusinessLocators.LocationPage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "SOB Is Created Successfully");
						storeShareOfBusinessDocNo();
						if (webDB.verifyIsElementDisplayed(PurchaseOrderPageLocators.CloseToastIcon,
								ElementType.Xpath)) {
							webDB.clickAnElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
							logs.logging("INFO", "Toast Close Icon Is Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method IS Used To Verify Store Share Of Business Doc No
	 * 
	 * @author Deepan-GWL
	 */
	public void storeShareOfBusinessDocNo() {
		String SOBDocNo = webDB.getDomPropertyFromElement(ShareOfBusinessLocators.ShareOfBusinessInput,
				ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Share Of Business Number Is: " + SOBDocNo);
		VariableHandler.setSOBNumber(SOBDocNo);
	}

	/**
	 * This Method Is USed To Verify Add Supplier Table In SOB
	 * 
	 * @author Deepan-GWL
	 * @param Type
	 */
	public void addSupplierTableInShareOfBusiness(String Type) {
		flag = webDB.waitForElement(ShareOfBusinessLocators.AddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ShareOfBusinessLocators.AddButton, ElementType.Xpath);
			flag = webDB.waitForElement(ShareOfBusinessLocators.TypeSelect, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button Is Clicked");
				webDB.selectDropDownByText(ShareOfBusinessLocators.TypeSelect, Type, ElementType.Xpath);
				logs.logging("INFO", "SOB Type Is Selected: " + Type);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Add Lov Button In Table
	 * 
	 * @author Deepan-GWL
	 * @param Table
	 * @param LovLocator
	 * @param LovButtonValue
	 * @param InputValue
	 */
	public void AddLovButtonInTable(String Table, String LovLocator, String LovButtonValue, String InputValue) {
		flag = webDB.waitForElement(Table, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Add Button Is Clicked");
			webDB.clickAnElement(Table, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(LovLocator, LovButtonValue);
			flag = webDB.waitForElement(InputValue, ElementType.Xpath);
			if (flag) {
				String value = webDB.getAttributeFromElement(InputValue, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Text Item Name Value from Table Is: " + value);
				flag = value.contains(LovButtonValue);
				if (flag) {
					logs.logging("INFO", "Verified Item Value Successfully");
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
	 * This Method Is Used To Verify Tick Icon
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTickIcon() {
		flag = webDB.waitForElement(ShareOfBusinessLocators.SOBTickButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ShareOfBusinessLocators.SOBTickButton, ElementType.Xpath);
			commonFunc.hardWait(2000);
			if (webDB.verifyIsElementDisplayed(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath)) {
				webDB.clickAnElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				logs.logging("INFO", "Toast Close Icon Is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Add Share Of Business Table Details Fo Purchase
	 * 
	 * @author Deepan-GWL
	 * @param Type
	 * @param Supplier
	 * @param Unit
	 * @param Percentage
	 * @param Priority
	 */
	public void verifyAddShareOfBusinessTableDetailsForPurchase(String Type, String Supplier, String Unit,
			String Percentage, String Priority) {
		addSupplierTableInShareOfBusiness(Type);
		AddLovButtonInTable(ShareOfBusinessLocators.SupplierIdTable, ShareOfBusinessLocators.SupplierIdLovButton,
				Supplier, ShareOfBusinessLocators.SupplierIdInput);
		AddLovButtonInTable(ShareOfBusinessLocators.UnitTable, ShareOfBusinessLocators.UnitLovButton, Unit,
				ShareOfBusinessLocators.UnitInput);
		verifyAddTableSpecs(ShareOfBusinessLocators.PercentageTable, ShareOfBusinessLocators.PercentageInput,
				Percentage);
		verifyAddTableSpecs(ShareOfBusinessLocators.PriorityTable, ShareOfBusinessLocators.PriorityInput, Priority);
		verifyTickIcon();

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Veify Add Share Os Business Table Details For
	 * Manufacturing
	 * 
	 * @author Deepan-GWL
	 * @param Type
	 * @param Unit
	 * @param Percentage
	 * @param Priority
	 */
	public void verifyAddShareOfBusinessTableDetailsForManufacture(String Type, String Unit, String Percentage,
			String Priority) {
		addSupplierTableInShareOfBusiness(Type);
		AddLovButtonInTable(ShareOfBusinessLocators.UnitTable, ShareOfBusinessLocators.UnitLovButton, Unit,
				ShareOfBusinessLocators.UnitInput);
		verifyAddTableSpecs(ShareOfBusinessLocators.PercentageTable, ShareOfBusinessLocators.PercentageInput,
				Percentage);
		verifyAddTableSpecs(ShareOfBusinessLocators.PriorityTable, ShareOfBusinessLocators.PriorityInput, Priority);
		verifyTickIcon();

		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Add SOB Table Details With Manufacturing And Purchase
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddShareOfBusinessTableDetailsWithManufactureAndPurchase() {
		verifyAddShareOfBusinessTableDetailsForPurchase(commonFunc.getDataFromTestDataSheet("PurchaseType"),
				commonFunc.getDataFromTestDataSheet("JJAutomobileId"), commonFunc.getDataFromTestDataSheet("U1"),
				commonFunc.getDataFromTestDataSheet("Perc_1"), commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		verifyAddShareOfBusinessTableDetailsForManufacture(commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("U1"), commonFunc.getDataFromTestDataSheet("Perc_1"),
				commonFunc.getDataFromTestDataSheet("Trd_Discount"));
	}

	/**
	 * This Method Is Used To Header Save And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyHeaderSaveAndPost() {
		flag = webDB.waitForElement(ShareOfBusinessLocators.HeaderTickButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(ShareOfBusinessLocators.HeaderTickButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(ShareOfBusinessLocators.HeaderPostButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(ShareOfBusinessLocators.HeaderPostButton, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (flag) {
					logs.logging("INFO", "Post Button Clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create SOB For Manufacturing And Purchase Type
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateShareOfBusinessForMnufactureAndPurchaseType() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToSearchTab(webDB.getDataFromProperties("Items"), CommonUiLocators.ItemMasterAdd);
		priceListPageFunc.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("RawMaterial10Item"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToSearchTab(webDB.getDataFromProperties("ShareOfBusinessTab"),
				CommonUiLocators.AddShareOfBusiness);
		verifyCreateNewShareOfBusiness(commonFunc.getDataFromTestDataSheet("LOCATION100"), today.format(formatter),
				commonFunc.getDataFromTestDataSheet("EffToDate"), VariableHandler.getItemName());
		verifyAddShareOfBusinessTableDetailsWithManufactureAndPurchase();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOBNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToSearchTab(webDB.getDataFromProperties("ShareOfBusinessTab"),
				CommonUiLocators.AddShareOfBusiness);
		verifySOBApprovalStatus(VariableHandler.getSOBNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Used To Verify OpenPo Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifySOBApprovalStatus(String DocNo, String ExpStatus) {
		commonFunc.searchAndSelectValueInFilters(ShareOfBusinessLocators.SobNumberLovButton, DocNo);
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header CheckBox Is Clicked");
				webDB.clickAnElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(ShareOfBusinessLocators.SobStatus(DocNo), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(ShareOfBusinessLocators.SobStatus(DocNo),
							ElementType.Xpath);
					flag = Status.contains(ExpStatus);
					if (flag) {
						logs.logging("INFO", "Expected Status: " + ExpStatus);
						logs.logging("INFO", "Verified Status ,Displayed Status: " + ExpStatus);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Cancel SOB Line And Doc
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCancelShareOfBusinessLineAndDocuments() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToSearchTab(webDB.getDataFromProperties("ShareOfBusinessTab"),
				CommonUiLocators.AddShareOfBusiness);
		verifyCreateNewShareOfBusiness(commonFunc.getDataFromTestDataSheet("LOCATION100"), today.format(formatter),
				commonFunc.getDataFromTestDataSheet("EffToDate"),
				commonFunc.getDataFromTestDataSheet("ShareOfBusinessItem"));
		verifyAddShareOfBusinessTableDetailsForPurchase(commonFunc.getDataFromTestDataSheet("PurchaseType"),
				commonFunc.getDataFromTestDataSheet("JJAutomobileId"), commonFunc.getDataFromTestDataSheet("U1"),
				commonFunc.getDataFromTestDataSheet("Perc_1"), commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		verifyShareOfBusinessCancelLine();
		verifyCancelDocuments();
		verifySOBApprovalStatus(VariableHandler.getSOBNumber(), commonFunc.getDataFromTestDataSheet("CancelStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Share Of Business Cancel Line
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyShareOfBusinessCancelLine() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(ShareOfBusinessLocators.DeleteLine, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ShareOfBusinessLocators.TypeTable, ElementType.Xpath);
			webDB.moveToElement(ShareOfBusinessLocators.DeleteLine, ElementType.Xpath);
			webDB.clickAnElement(ShareOfBusinessLocators.DeleteLine, ElementType.Xpath);
			flag = webDB.waitForElement(ShareOfBusinessLocators.OkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ShareOfBusinessLocators.OkButton, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, Line Deleted Successfully");
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Cancel Documents
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyCancelDocuments() {
		webDB.moveToElement(ShareOfBusinessLocators.CancelDocuments, ElementType.Xpath);
		flag = webDB.waitForElement(ShareOfBusinessLocators.CancelDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ShareOfBusinessLocators.CancelDocuments, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.OKButtonLast, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(POAmendmentLocators.OKButtonLast, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, SOB Cancelled Successfully");
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Filter Search Share Of Business
	 * 
	 * @author Deepan-GWL
	 * @param LovButton
	 * @param Value
	 * @param i
	 */
	public void verifyAmendFilterSearchSOB(String LovButton, String Value, String i, String Type,
			boolean... selectflag) {
		webDB.scrollToAnElement(LovButton, ElementType.Xpath);
		if (selectflag != null) {
			commonFunc.searchAndSelectValueInFilters(LovButton, Value);
		} else {
			webDB.selectDropDownByText(LovButton, Value, ElementType.Xpath);
		}
		if (Type.equals(commonFunc.getDataFromTestDataSheet("HeaderSearchType"))) {
			clickSearchButton();
			commonFunc.hardWait(3000);
		commonFunc.expandFindTable();
			verifyAmendTableValue(i, Value);
		} else if (Type.equals(commonFunc.getDataFromTestDataSheet("LineSearchType"))) {
			clickLineSearchButton();
			commonFunc.hardWait(3000);
			commonFunc.expandFindTable();
			verifyLineAmendTableValue(i, Value);
			
		}

		clearSearchFilter();
	}

	/**
	 * This Method IS Used To Verify Date After And Before
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDateAfterAndBefore() {
		flag = webDB.waitForElement(ShareOfBusinessLocators.EffFromInput, ElementType.Xpath);
		webDB.sendTextToAnElement(ShareOfBusinessLocators.EffFromInput, commonFunc.getDataFromTestDataSheet("EffFrom"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueAfterDate("Eff. From", commonFunc.getDataFromTestDataSheet("EffFrom"));
		clearSearchFilter();
		flag = webDB.waitForElement(ShareOfBusinessLocators.EffToInput, ElementType.Xpath);
		webDB.sendTextToAnElement(ShareOfBusinessLocators.EffToInput, commonFunc.getDataFromTestDataSheet("EffTo"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueBeforeDate("Eff. To", commonFunc.getDataFromTestDataSheet("EffTo"));
		clearSearchFilter();
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
				flag = webDB.waitForElement(ShareOfBusinessLocators.AmendFindTable, ElementType.Xpath);
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
	public void verifyAmendTableValue(String i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(ShareOfBusinessLocators.AmendFindTableData(i)));
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
	public void verifyAmendTableValueAfterDate(String i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(ShareOfBusinessLocators.AmendFindTableData(i)));
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
	public void verifyAmendTableValueBeforeDate(String i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(ShareOfBusinessLocators.AmendFindTableData(i)));
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
	public void verifyLineAmendTableValue(String i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(ShareOfBusinessLocators.AmendLineFindTableData(i)));
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
				flag = webDB.waitForElement(ShareOfBusinessLocators.AmendLineFindTable, ElementType.Xpath);
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
	 * This Method IS Used To Verify SOB Filter Field Are Working
	 * 
	 * @author Deepan-GWL
	 */
	public void verifySOBFilterFieldAreWorking() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"TvsUsername");
		commonFunc.navigateToSearchTab(webDB.getDataFromProperties("ShareOfBusinessTab"),
				CommonUiLocators.AddShareOfBusiness);
//		verifyDateAfterAndBefore();
		verifyAmendFilterSearchSOB(ShareOfBusinessLocators.SOBLocId, commonFunc.getDataFromTestDataSheet("Loc_100"),
				"LocId", commonFunc.getDataFromTestDataSheet("HeaderSearchType"));
		verifyAmendFilterSearchSOB(ShareOfBusinessLocators.SOBUnit, commonFunc.getDataFromTestDataSheet("Unit_100"),
				"Unit", commonFunc.getDataFromTestDataSheet("HeaderSearchType"));
		verifyAmendFilterSearchSOB(ShareOfBusinessLocators.SOBSupplierId,
				commonFunc.getDataFromTestDataSheet("POSupplierName"), "Supplier Name",
				commonFunc.getDataFromTestDataSheet("LineSearchType"));
		verifyAmendFilterSearchSOB(ShareOfBusinessLocators.SOBDocNo,
				commonFunc.getDataFromTestDataSheet("SobDocNumber"), "SOB No.",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"));
		verifyAmendFilterSearchSOB(ShareOfBusinessLocators.SOBItemName,
				commonFunc.getDataFromTestDataSheet("ShareOfBusinessItem"), "Item",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"));
//		verifyAmendFilterSearchSOB(ShareOfBusinessLocators.SOBProcess, commonFunc.getDataFromTestDataSheet("Process10"),
//				11, commonFunc.getDataFromTestDataSheet("HeaderSearchType"));
//		verifyAmendFilterSearchSOB(ShareOfBusinessLocators.SOBStatus,
//				commonFunc.getDataFromTestDataSheet("ApproveStatus"), 12,
//				commonFunc.getDataFromTestDataSheet("HeaderSearchType"));

	}

}
