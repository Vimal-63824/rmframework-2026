package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.MRPLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 19, 2025
 */
public class MRPFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static SalesOrderFunctions saleOrderFunc = new SalesOrderFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

	/**
	 * This Method Used to switch Release IFrame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToCreateMRPIframe() {
		flag = webDB.waitForElement(MRPLocators.MRP_CreateMRPIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(MRPLocators.MRP_CreateMRPIframe, ElementType.Xpath);
			flag = webDB.waitForElement(MRPLocators.MRP_AddMRPNameInput, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Add Icon in MRP Page
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconInMRP() {
		flag = webDB.waitForElement(MRPLocators.MRP_AddNewBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_AddNewBtn, ElementType.Xpath);
			flag = webDB.waitForElement(MRPLocators.MRP_CreateMRPIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button Clicked in MRP Page");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Details in MRP Creation
	 * 
	 * @author Sakthi-GWL
	 * @param MRPDate
	 * @param MRPName
	 * @param SafetyStkDate
	 * @param MRPType
	 */
	public void fillDetailsInMRPCreationAndClickTickIcon(String MRPDate, String MRPName, String SafetyStkDate,
			String MRPType) {
		switchToCreateMRPIframe();
		commonFunc.verifyAddTableSpecs(MRPLocators.MRP_AddMRPDateInput, MRPLocators.MRP_AddMRPDateInput, MRPDate);
		commonFunc.verifyAddTableSpecs(MRPLocators.MRP_AddMRPNameInput, MRPLocators.MRP_AddMRPNameInput, MRPName);
		commonFunc.verifyAddTableSpecs(MRPLocators.MRP_AddSafetyStkDateInput, MRPLocators.MRP_AddSafetyStkDateInput,
				SafetyStkDate);
		flag = webDB.selectDropDownByText(MRPLocators.MRP_AddSupplyExceptionSelect, MRPType, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MRPLocators.MRP_TickIcon, ElementType.Xpath);
			webDB.clickAnElement(MRPLocators.MRP_TickIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForElement(MRPLocators.MRP_MRPNumberDisplayInput, ElementType.Xpath);
			if (flag) {
				String MRPNo = webDB.getDomPropertyFromElement(MRPLocators.MRP_MRPNumberDisplayInput, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				VariableHandler.setMRPNumber(MRPNo);
				logs.logging("INFO", "Created MRP Number: " + MRPNo);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Sources Sub tab Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToSourcesTabInMRPScreen() {
		flag = webDB.waitForElement(MRPLocators.MRP_SubTab_Sources, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_SubTab_Sources, ElementType.Xpath);
			flag = webDB.waitForElement(MRPLocators.MRP_AddNewLineSourcesBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sources Sub Tab Button Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Add Line Plus Icon in source
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddLinePlusIconInSources() {
		flag = webDB.waitForElement(MRPLocators.MRP_AddNewLineSourcesBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_AddNewLineSourcesBtn, ElementType.Xpath);
			flag = webDB.waitForElement(MRPLocators.MRP_SourceTableData_Unit(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Line Plus Button Clicked in Sources Sub Tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to enter Unit And CutOff Date in Sources Line
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 * @param CutOffDate
	 */
	public void enterUnitCutOffDateInSourcesLine(String Unit, String CutOffDate) {
		flag = webDB.waitForElement(MRPLocators.MRP_SourceTableData_Unit(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_SourceTableData_Unit(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(MRPLocators.MRP_Source_UnitLovBtn(), Unit);
			logs.logging("INFO", "Unit Selected in Source Line: " + Unit);
			commonFunc.verifyAddTableSpecs(MRPLocators.MRP_SourceTableData_CutOffDate(),
					MRPLocators.MRP_Source_CutOffDateInput(), CutOffDate);
			logs.logging("INFO", "CutOff Date Entered in Source Line: " + CutOffDate);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Sales Order Demand CheckBox in Sources Line
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSalesOrderDemandCheckBoxInSourceLines() {
		flag = webDB.waitForClickElement(MRPLocators.MRP_Source_SalesOrderTableData(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MRPLocators.MRP_Source_SalesOrderTableData(), ElementType.Xpath);
			webDB.clickAnElement(MRPLocators.MRP_Source_SalesOrderTableData(), ElementType.Xpath);
			commonFunc.hardWait(2000);
			webDB.javaScriptClickAnElement(MRPLocators.MRP_Source_SalesOrderCheckbox, ElementType.Xpath);
			flag = webDB.isElementSelected(MRPLocators.MRP_Source_SalesOrderCheckbox, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "'Sales Order' Demand Checkbox Clicked in Sources Line");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Sources Lines Save Icon
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveIconInSourcesTab() {
		flag = webDB.waitForElement(MRPLocators.MRP_SaveLineSourcesBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_SaveLineSourcesBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", " Sources Lines Save Icon Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Load Demand Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickLoadDemandButtonInMRP() {
		flag = webDB.waitForElement(MRPLocators.MRP_LoadDemandBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_LoadDemandBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", " Load Demand Button Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Demand Sub tab Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToDemandTabInMRPScreen() {
		flag = webDB.waitForElement(MRPLocators.MRP_SubTab_Demand, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_SubTab_Demand, ElementType.Xpath);
			flag = webDB.waitForElement(MRPLocators.MRP_DemandSearchBar, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Demand Sub Tab Button Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to Search SO Number and select all Items in Demand Tab
	 * 
	 * @author Sakthi-GWL
	 * @param SONum
	 */
	public void searchSONumberInDemandAndSelectSOItems(String SONum) {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(MRPLocators.MRP_DemandSearchBar, MRPLocators.MRP_DemandSearchBar, SONum);
		webDB.waitForClickElement(MRPLocators.MRP_DemandSearchGoBtn, ElementType.Xpath);
		webDB.clickAnElement(MRPLocators.MRP_DemandSearchGoBtn, ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(MRPLocators.MRP_DemandTableData_SONumber(), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Search Button Clicked, Searched SO Number: " + SONum);
			List<WebElement> Elements = webDB.getDriver()
					.findElements(By.xpath(MRPLocators.MRP_DemandTableData_SONumber()));
			a: for (WebElement webElement : Elements) {
				String DisplayText = webElement.getText();
				if (DisplayText.equalsIgnoreCase(SONum) || DisplayText.contains(SONum)) {
					flag = true;
				} else {
					flag = false;
					logs.logging("INFO", "SO Number Displayed: " + DisplayText);
					logs.logging("INFO", "SO Number Differ from Searched SO Number.. Failed to fetch SO Number!!");
					break a;
				}
			}
			if (flag) {
				flag = webDB.waitForElement(MRPLocators.MRP_Demand_CheckBoxWithSONo(SONum), ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(MRPLocators.MRP_Demand_CheckBoxWithSONo(SONum), ElementType.Xpath);
					webDB.clickAnElement(MRPLocators.MRP_Demand_CheckBoxWithSONo(SONum), ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.isElementSelected(MRPLocators.MRP_Demand_CheckBoxWithSONo(SONum), ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Select All CheckBox Clicked and Verfied");
						flag = webDB.waitForElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Check Exception Button Clicked");
							webDB.moveToElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
							webDB.clickAnElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
							flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
							if (flag) {
								commonFunc.checkRefreshCellsDisappear();
								if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath)) {
									webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
		} else {
			flag = false;
			logs.logging("INFO", "No Data Shown After Searched SO Number.. Failed to select SO Number!!");
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Check Exception Button and Save Header in MRP
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCheckExceptionAndSaveHeaderInMRP() {
		flag = webDB.waitForElement(MRPLocators.MRP_CheckExceptionLabel, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MRPLocators.MRP_CheckExceptionLabel, ElementType.Xpath);
			webDB.clickAnElement(MRPLocators.MRP_CheckExceptionLabel, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Check Exception Button Clicked");
				webDB.moveToElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
				webDB.clickAnElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				} else {
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.verifyIsElementDisplayed(CommonUiLocators.ToastErrorMsg, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Error Toast Displayed Trying again to save");
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						flag = webDB.waitForElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Check Exception Button Clicked");
							webDB.clickAnElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
							flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
							if (flag) {
								commonFunc.checkRefreshCellsDisappear();
								if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath)) {
									webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
			flag = webDB.isElementEnabled(MRPLocators.MRP_RunMRPBtn, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Exceptions Sub tab Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToExceptionTabInMRPScreen() {
		flag = webDB.waitForElement(MRPLocators.MRP_SubTab_Exceptions, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_SubTab_Exceptions, ElementType.Xpath);
			flag = webDB.waitForElement(MRPLocators.MRP_ExceptionMessageTableHeader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Exceptions Sub Tab Button Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify No Exception Data Displayed or Not in Exception
	 * Tab
	 * 
	 * @author Sakthi-GWL
	 */
	public void validateNoExceptionDataShownInTableData() {
		flag = webDB.waitForElement(MRPLocators.MRP_ExceptionNoDataFoundContainer, ElementType.Xpath);
		if (flag) {
			List<WebElement> elements = webDB.getDriver()
					.findElements(By.xpath(MRPLocators.MRP_ExceptionNoDataFoundContainer));
			a: for (WebElement webElement : elements) {
				if (webElement.isDisplayed()) {
					flag = true;
					break a;
				} else {
					flag = false;
				}
			}
			if (flag) {
				logs.logging("INFO", "Verified No Data Found Container Displayed No Exception Occurs");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Run MRP Button in Header
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickRunMRPButton() {
		flag = webDB.waitForClickElement(MRPLocators.MRP_RunMRPBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(MRPLocators.MRP_RunMRPBtn, ElementType.Xpath);
			webDB.clickAnElement(MRPLocators.MRP_RunMRPBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Run MRP Button Clicked");
				commonFunc.checkRefreshCellsDisappear();
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForClickElement(MRPLocators.MRP_StatusDisplayInput, ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(MRPLocators.MRP_StatusDisplayInput, ElementType.Xpath);
					logs.logging("INFO", "After click Run MRP, Displayed Status: " + Status);
					flag = Status.equalsIgnoreCase("RUN") || Status.contains("Run");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Results Sub tab Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToResultsTabInMRPScreen() {
		flag = webDB.waitForElement(MRPLocators.MRP_SubTab_Results, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_SubTab_Results, ElementType.Xpath);
			flag = webDB.waitForElement(MRPLocators.MRP_ResultsTab_Summary, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Results Sub Tab Button Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to validate Units in Reports of MRP
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 */
	public void verifyUnitResultsDetails(String[] Unit) {
		try {
			flag = webDB.waitForElement(MRPLocators.MRP_ResultsTab_Summary, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				List<WebElement> DisplayedUnits = webDB.getDriver()
						.findElements(By.xpath(MRPLocators.MRP_ResultSummary_UnitTableDataList));
				flag = DisplayedUnits.size() == Unit.length;
				if (flag) {
					for (int i = 0; i < Unit.length; i++) {
						if (flag) {
							b: for (int j = 0; j < DisplayedUnits.size(); j++) {
								String DisplayedText = DisplayedUnits.get(j).getText();
								if (Unit[i].toString().equalsIgnoreCase(DisplayedText)) {
									flag = true;
									logs.logging("INFO", "Unit Displayed in Results: " + Unit[i].toString());
									break b;
								} else {
									flag = false;
								}
							}
						} else {
							logs.logging("INFO", "Previous Unit Not Displayed In Results Unit Column");
						}
					}
				}
			}
		} catch (Exception e) {

		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to validate Item in Reports of MRP
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 */
	public void verifyItemResultsDetails(String[] Item) {
		try {
			flag = webDB.waitForElement(MRPLocators.MRP_ResultsTab_Summary, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				List<WebElement> DisplayedItems = webDB.getDriver()
						.findElements(By.xpath(MRPLocators.MRP_ResultSummary_ItemTableDataList));
				flag = DisplayedItems.size() == Item.length;
				if (flag) {
					for (int i = 0; i < Item.length; i++) {
						if (flag) {
							b: for (int j = 0; j < DisplayedItems.size(); j++) {
								if (Item[i].toString().equalsIgnoreCase(DisplayedItems.get(j).getText())) {
									flag = true;
									logs.logging("INFO", "Item Displayed in Results: " + Item[i].toString());
									break b;
								} else {
									flag = false;
								}
							}
						} else {
							logs.logging("INFO", "Previous Item Not Displayed In Results Unit Column");
						}
					}
				}
			}
		} catch (Exception e) {

		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to validate UOM in Reports of MRP
	 * 
	 * @author Sakthi-GWL
	 * @param UOM
	 */
	public void verifyUOMResultsDetails(String[] UOM) {
		try {
			flag = webDB.waitForElement(MRPLocators.MRP_ResultsTab_Summary, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				List<WebElement> DisplayedUOM = webDB.getDriver()
						.findElements(By.xpath(MRPLocators.MRP_ResultSummary_UOMTableDataList));
				flag = DisplayedUOM.size() == UOM.length;
				if (flag) {
					for (int i = 0; i < UOM.length; i++) {
						if (flag) {
							b: for (int j = 0; j < DisplayedUOM.size(); j++) {
								if (UOM[i].toString().equalsIgnoreCase(DisplayedUOM.get(j).getText())) {
									flag = true;
									logs.logging("INFO", "UOM Displayed in Results: " + UOM[i].toString());
									break b;
								} else {
									flag = false;
								}
							}
						} else {
							logs.logging("INFO", "Previous UOM Not Displayed In Results Unit Column");
						}
					}
				}
			}
		} catch (Exception e) {

		}
		assertTrue(flag);
	}

	/**
	 * To Check Total Depend Demand For FG Item with Convention Factor
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param ConvFac
	 */
	public void checkTotalDemandInResultSummaryWithItem(String Item, String ConvFac) {
		// TODO Will Update After DB connectivity
	}

	/**
	 * This Method Used to validate calculation of Raw Material Depend Demand
	 * Quantity
	 * 
	 * @author Sakthi-GWL
	 * @param ItemQuantity
	 * @param RMDependDemand
	 * @param RMName
	 */
	public void validateCalculationOfRawMaterialQuantity(String ItemQuantity, String RMDependDemand, String RMName) {
		BigDecimal ItemQnty = new BigDecimal(ItemQuantity);
		BigDecimal RMDepend = new BigDecimal(RMDependDemand);
		BigDecimal TotalRMQnty = ItemQnty.multiply(RMDepend);
		logs.logging("INFO", "By Multipling FG Quantity and RM Depend Qnty... Expected Quantity: " + TotalRMQnty);
		flag = webDB.waitForElement(MRPLocators.MRP_ResultSummary_DependDemandOfRM(RMName), ElementType.Xpath);
		if (flag) {
			String DisplayedRMQnty = webDB.getTextFromElement(MRPLocators.MRP_ResultSummary_DependDemandOfRM(RMName),
					ElementType.Xpath);
			logs.logging("INFO", "Displayed Depend Demand Quantity for: " + RMName + " is " + DisplayedRMQnty);
			BigDecimal DisplayQnty = new BigDecimal(DisplayedRMQnty);
			flag = TotalRMQnty.equals(DisplayQnty);
			if (flag) {
				logs.logging("INFO", "Verified RM Depend Demand Quantity With Item Quantity");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to Save Header and Click Post Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickHeaderSaveAndClickPost() {
		flag = webDB.waitForElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_HeaderSaveBtn, ElementType.Xpath);
//			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
//			if (flag) {
//			logs.logging("INFO", "Header Save Button Clicked");
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			flag = webDB.waitForElement(MRPLocators.MRP_PostBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MRPLocators.MRP_PostBtn, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (flag) {
					logs.logging("INFO", "Post Button Clicked");
				}
			}
		}
//		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to validate Status of MRP with MRPNo
	 * 
	 * @author Sakthi-GWL
	 * @param MRPNo
	 * @param ExpectedStatus
	 */
	public void checkStatusOfMRP(String MRPNo, String ExpectedStatus) {
		if (webDB.verifyIsElementDisplayed(MRPLocators.FindLinesBtn, ElementType.Xpath)) {
			webDB.clickAnElement(MRPLocators.FindLinesBtn, ElementType.Xpath);
		}
		commonFunc.expandFindTable();
		commonFunc.searchAndSelectValueInFilters(MRPLocators.FindMRPNoLovBtn, MRPNo);
		flag = webDB.waitForElement(MRPLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MRPLocators.Find_Status_TableData(MRPNo), ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(MRPLocators.Find_Status_TableData(MRPNo), ElementType.Xpath);
				logs.logging("INFO", "Search Button clicked and MRP No: " + MRPNo + " Displayed");
				String Status = webDB.getTextFromElement(MRPLocators.Find_Status_TableData(MRPNo), ElementType.Xpath);
				logs.logging("INFO", "MRP Number: '" + MRPNo + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpectedStatus);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to Search and Click MRP No in Find Page
	 * 
	 * @author Sakthi-GWL
	 * @param MRPNo
	 */
	public void searchAndClickMRPNoInFindPage(String MRPNo) {
		commonFunc.expandFindTable();
		commonFunc.searchAndSelectValueInFilters(MRPLocators.FindMRPNoLovBtn, MRPNo);
		flag = webDB.waitForElement(MRPLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForClickElement(MRPLocators.Find_MRPNo_TableData(MRPNo), ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Search Button clicked and MRP No: " + MRPNo + " Displayed");
				webDB.moveToElement(MRPLocators.Find_MRPNo_TableData(MRPNo), ElementType.Xpath);
				webDB.clickAnElement(MRPLocators.Find_MRPNo_TableData(MRPNo), ElementType.Xpath);
				flag = webDB.waitForElement(MRPLocators.MRP_SubTab_Sources, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "MRP No: " + MRPNo + " Click and Navigated to MRP Page");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to Click Release Button in MRP
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickReleaseButtonInMRP() {
		flag = webDB.waitForElement(MRPLocators.MRP_ReleaseBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_ReleaseBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MRPLocators.MRP_ReleaseIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "MRP Release Button Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to switch Release IFrame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToReleaseIframe() {
		flag = webDB.waitForElement(MRPLocators.MRP_ReleaseIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(MRPLocators.MRP_ReleaseIframe, ElementType.Xpath);
			flag = webDB.waitForElement(MRPLocators.MRP_Release_ReleaseBtn, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to Choose Order Type and From and To Date in MRP Release
	 * Frame
	 * 
	 * @author Sakthi-GWL
	 * @param OrderType
	 * @param FromDate
	 * @param ToDate
	 */
	public void chooseOrderTypeFromAndToDateInReleaseDetails(String OrderType, String FromDate, String ToDate) {
		switchToReleaseIframe();
		flag = webDB.selectDropDownByText(MRPLocators.MRP_Release_OrderTypeSelect, OrderType, ElementType.Xpath);
		if (flag) {
//			commonFunc.verifyAddTableSpecs(MRPLocators.MRP_Release_FromData,
//					MRPLocators.MRP_Release_FromData, FromDate);
//			commonFunc.verifyAddTableSpecs(MRPLocators.MRP_Release_ToData,
//					MRPLocators.MRP_Release_ToData, ToDate);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(MRPLocators.MRP_ReleaseIframeCloseIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used to Click Release Button inside Release IFrame
	 * 
	 * @author Sakthi-GWL
	 * 
	 */
	public void clickReleaseButtonInReleaseFrame() {
		switchToReleaseIframe();
		flag = webDB.waitForElement(MRPLocators.MRP_Release_ReleaseBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MRPLocators.MRP_Release_ReleaseBtn, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.navigateToRefresh();
			flag = webDB.waitForClickElement(MRPLocators.MRP_StatusDisplayInput, ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(MRPLocators.MRP_StatusDisplayInput, ElementType.Xpath);
				logs.logging("INFO", "After click Release MRP, Displayed Status: " + Status);
				flag = Status.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to How to create and Run MRP through sales Order?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateAndRunMRPThroughSalesOrder() {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.now();
			String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
			String Today = date.format(formatter);
			LocalDate previousDate = LocalDate.now().minusDays(1);
			String YesterDay = previousDate.format(formatter);
			LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
			String NextMonthToday = nextmonthdate.format(formatter);
			String CustomerID = commonFunc.getDataFromTestDataSheet("CustomerName");
			VariableHandler.setItemName(commonFunc.getDataFromTestDataSheet("NewItem"));
			String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
			String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
			String Qnty = RandomStringUtils.randomNumeric(2);
			if (Qnty.equals("00")) {
				Qnty = "10";
			}
			String Cost = RandomStringUtils.randomNumeric(2);
			if (Cost.equals("00")) {
				Cost = "10";
			}
			commonFunc.navigateSalesOrderPage();
			saleOrderFunc.navigateToSalesOrderOrCustomerSchedule(webDB.getDataFromProperties("So"));
			saleOrderFunc.createSalesOrderWithHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
					CustomerID);
			saleOrderFunc.addItemsInSO(VariableHandler.getItemName());
			saleOrderFunc.addQuantityAndUnitCost(Qnty, Cost);
			saleOrderFunc.chooseHsnCodeAndClickLineWiseSaveButton(OpenSOHSN);
			saleOrderFunc.clickLineWiseSaveButton();
			saleOrderFunc.clickTotalDiscountForLineDetails();
			saleOrderFunc.switchToLineDetailFrameInSaleOrder();
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.verifyIsElementDisplayed(SalesOrderLocators.SOTradeDiscountpercentageField, ElementType.Xpath)) {
				commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOTradeDiscountAssblValue,
						SalesOrderLocators.SOTradeDiscountpercentageField,
						SalesOrderLocators.SOTradeDiscountAmountField, RandomStringUtils.randomNumeric(1));
			}
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOSpecialDiscountAssblValue,
					SalesOrderLocators.SOSpecialDiscountpercentageField,
					SalesOrderLocators.SOSpecialDiscountAmountField, RandomStringUtils.randomNumeric(1));
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkDiscountInLineDetails(SalesOrderLocators.SOCashDiscountAssblValue,
					SalesOrderLocators.SOCashDiscountpercentageField, SalesOrderLocators.SOCashDiscountAmountField,
					RandomStringUtils.randomNumeric(1));
			saleOrderFunc.clickLineWiseDetailsSaveButton();
			webDB.switchToDefaultContent();
			saleOrderFunc.clickPostButton();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getSONumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			clickAddIconInMRP();
			fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			clickAddLinePlusIconInSources();
			enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			clickSalesOrderDemandCheckBoxInSourceLines();
			clickSaveIconInSourcesTab();
			clickLoadDemandButtonInMRP();
			navigateToDemandTabInMRPScreen();
			searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			clickCheckExceptionAndSaveHeaderInMRP();
			navigateToExceptionTabInMRPScreen();
			validateNoExceptionDataShownInTableData();
			clickRunMRPButton();
			navigateToResultsTabInMRPScreen();
			verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			verifyItemResultsDetails(new String[] { VariableHandler.getItemName(), RMItem });
			verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			checkStatusOfMRP(VariableHandler.getMRPNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			clickReleaseButtonInMRP();
			chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			clickReleaseButtonInReleaseFrame();
			checkStatusOfMRP(VariableHandler.getMRPNumber(), commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyInactivateBillOfMaterials': " + e);
			return false;
		}
	}
}
