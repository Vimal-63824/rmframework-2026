package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;

import commonfunctions.CommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.ItemDetailsHandler.OpenSOAmdDetails;
import commonfunctions.ItemDetailsHandler.OpenSOHeaderDetailsName;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.OpenSoAmdLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class OpenSoAmdFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	private static OpenSalesOrderFunctions openso = new OpenSalesOrderFunctions();

	/**
	 * To Click a Open Sales Order Amd
	 * 
	 * @author Kowshik-RMITS
	 */
	public void ClickOpenSoAmdendmentButton() {
		flag = webDB.waitForElement(OpenSoAmdLocators.OpenSoAmdRadio, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.OpenSoAmdRadio, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.OpensoAmdPlusBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Open So Amd screen Opened Sucessfully");
			} else {
				logs.logging("Info", "Failed To Open The Open Amendment Screen");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Add button in Find Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickAddButtoninFindPage() {
		webDB.waitForElement(OpenSoAmdLocators.OpensoAmdPlusBtn, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(OpenSoAmdLocators.OpensoAmdPlusBtn, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.CurrentRateContractSearch, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Open So Add Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Passes a Open Sales Order Document In Search Box & Click Go Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void takenOpenSoDocumentFromCurrentRateContract(String oso_Docnum) {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(OpenSoAmdLocators.CurrentRateContractSearch, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(OpenSoAmdLocators.CurrentRateContractSearch, oso_Docnum, ElementType.Xpath);
			logs.logging("Info", "Open So Document is Sended Successfully " + oso_Docnum);
			flag = webDB.waitForElement(OpenSoAmdLocators.CurrentRateGObtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSoAmdLocators.CurrentRateGObtn, ElementType.Xpath);
				logs.logging("Info", "Go Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Select a Check Flag
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSelectFlagInCurrentRateContract() {
		commonFunc.hardWait(3000);
		flag = webDB.waitForElement(OpenSoAmdLocators.currentRateDefault, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.currentRateDefault, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.soAmdBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INfo", "Check Box Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a So Amdendment Button In Current Rate Contract Screen
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSoAmdendmentButtonInCurrentRateContract() {
		flag = webDB.waitForElement(OpenSoAmdLocators.soAmdBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.soAmdBtn, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.priceChange, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Succussfully Clicked SoAmd Button");
				commonFunc.hardWait(3000);
			} else {
				logs.logging("Info", "Failed to click So Amdenment Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Changes a Price Class in Open So Amdendment Screen
	 * 
	 * @author Kowshik-RMITS
	 * @param OsoamdReason
	 */
	public void clickPriceChanges(String OsoamdReason) {
		flag = webDB.waitForElement(OpenSoAmdLocators.priceChange, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.priceChange, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.AmdLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Clicked A Price Changes Button");
				commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.AmdLovBtn, OsoamdReason);
				flag = webDB.waitForElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
					flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("Info", "Ok Button Clicked Successfully & Open So Amd Doc Created Successfully");
					} else {
						logs.logging("Info", "Open So Document Not Created");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Addition Of Item Radio Button in Current Rate Screen
	 * 
	 * @author Kowshik-RMITS
	 * @param OsoamdReason
	 */
	public void clickAdditionfItemOfRadioButton(String OsoamdReason) {
		flag = webDB.waitForElement(OpenSoAmdLocators.AdditionofItemRadioButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.AdditionofItemRadioButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.AmdLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Clicked a Addition Of Item");
				commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.AmdLovBtn, OsoamdReason);
				flag = webDB.waitForElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
					flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("Info", "Ok Button Clicked Successfully & Open So Amd Doc Created Successfully");
					} else {
						logs.logging("Info", "Open So Document Not Created");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Deletion Of Item in Current Rate Contract Screen
	 * 
	 * @author Kowshik-RMITS
	 * @param OsoamdReason
	 */
	public void clickDeletationofItem(String OsoamdReason) {
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.DeletionofItem, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.AmdLovBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.AmdLovBtn, OsoamdReason);
				flag = webDB.waitForElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Deletion Of Item Clicked Successfully");
					webDB.clickAnElement(OpenSoAmdLocators.OkBtn, ElementType.Xpath);
					flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("Info", "Ok Button Clicked Successfully & Open So Amd Doc Created Successfully");

					} else {
						logs.logging("Info", "Failed To Creates Open So Document");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Close a Popup Alert Messages
	 * 
	 * @author Kowshik-RMITS
	 */
	public void closeAlertMessage() {
		commonFunc.checkRefreshCellsDisappear();
		if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
			webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Update a Open So Linedetails In Revision Price & Mrp Price InModifiacation
	 * Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Revisised_Price
	 * @param Mrp
	 */
	public void verifyOSOAlinedetailsRevPriceInModificationType(String Revisised_Price, String Mrp) {
		closeAlertMessage();
		verifyAddTableSpecs(OpenSoAmdLocators.revField, OpenSoAmdLocators.RevPrice, Revisised_Price);
		verifyAddTableSpecs(OpenSoAmdLocators.MrpField, OpenSoAmdLocators.Mrpprice, Mrp);
		flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnLineWise, ElementType.Xpath);
		if (flag) {
			logs.logging("Info", "Revision price & Mrp Price Inserted Successfully ");
		}
		assertTrue(flag);
	}

	/**
	 * Update a Open So Linedetails In Revision Price & Mrp Price InModifiacation
	 * Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Revisised_Price
	 * @param Mrp
	 */
	public void verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(String Revisised_Price, String Mrp) {
		flag = webDB.waitForElement(OpenSoAmdLocators.revField, ElementType.Xpath);
		if (flag) {
			verifyAddTableSpecs(OpenSoAmdLocators.revField, OpenSoAmdLocators.RevPrice, Revisised_Price);
			flag = webDB.waitForElement(OpenSoAmdLocators.MrpField, ElementType.Xpath);
			if (flag) {
				verifyAddTableSpecs(OpenSoAmdLocators.MrpField, OpenSoAmdLocators.Mrpprice, Mrp);
				flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Revision price & Mrp Price Inserted Successfully ");
				} else {
					logs.logging("Info", "Unable to Updated a Revision Price & Mrp Price ");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert a Discount Percent Field in Line Details in Addition Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Disc_Perent
	 */
	public void insertDiscPercentInAdditionType(String Disc_Perent) {
		flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
		if (flag) {
			verifyAddTableSpecs(OpenSoAmdLocators.DiscFieldClick, OpenSoAmdLocators.DiscPercentInsert, Disc_Perent);
			flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnLineWise, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Disc Percentage Updated By Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void toClickaSaveButton() {
		flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnLineWise, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.SaveBtnLineWise, ElementType.Xpath);
			closeAlertMessage();
			logs.logging("Info", "Save Button Clicked Successfully");
		}
		assertTrue(flag);
	}

	/**
	 * To Update a Effective Date & Effective To Date
	 * 
	 * @author Kowshik-RMITS
	 * @param Effdate
	 * @param EffTo
	 */
	public void insertEffdateAndEfftoDateInHeaderDetails(String Effdate, String EffTo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenSoAmdLocators.EffFromDate, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(OpenSoAmdLocators.EffFromDate, Effdate, ElementType.Xpath);
			webDB.pressTabKey();
			flag = webDB.waitForElement(OpenSoAmdLocators.EffToDate, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(OpenSoAmdLocators.EffToDate, EffTo, ElementType.Xpath);
				flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnHeader, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Successfully Inserted a Eff From Date & Eff To Date");
				} else {
					logs.logging("Info", "Unable Passing A Eff From Date & Eff To Date");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Save Button in Header Details
	 * 
	 * @author Kowshik-RMITS
	 */
	public void toClickSaveButtonInHeader() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenSoAmdLocators.SaveBtnHeader, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.SaveBtnHeader, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Clicked a SaveButton");
				closeAlertMessage();
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click A Post Button In Header Details
	 * 
	 * @author Kowshik-RMITS
	 */
	public void ToClickPostButtonInHeader() {
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.postBtn, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Clicked a Post Button Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Get Document Number In Transaction Screen
	 * 
	 * @author Kowshik-RMITS
	 * @param OsoaDoc
	 */
	public void GetDocumentFromScreen(String OsoaDoc) {
		flag = webDB.waitForElement(OpenSoAmdLocators.OsoaDocNumber, ElementType.Xpath);
		if (flag) {
			OsoaDoc = webDB.getTextFromElement(OpenSoAmdLocators.OsoaDocNumber, ElementType.Xpath);
			VariableHandler.setOSOAdocnum(OsoaDoc);
			logs.logging("info", "Get openso Amendment DocNo Is: " + VariableHandler.getOSOAdocnum());
		}
		assertTrue(flag);
	}

	/**
	 * @author @author Kowshik-RMITS
	 * 
	 *         This Method is used to Click And Enter A Value In A Table
	 * 
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
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
	 * verify Waiting For Aprorval
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean
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
	 * To Updated a Price Class In Line Details
	 * 
	 * @author Kowshik-RMITS
	 * @param Price_Class_OSOA
	 */
	public void ToChangesPriceChanges1(String Price_Class_OSOA) {
		flag = webDB.waitForElement(OpenSoAmdLocators.PriceClassClick, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenSoAmdLocators.PriceClassClick, ElementType.Xpath);
			webDB.clickAnElement(OpenSoAmdLocators.PriceClassClick, ElementType.Xpath);
			webDB.selectDropDownByText(OpenSoAmdLocators.PriceClassChange, Price_Class_OSOA, ElementType.Xpath);
			logs.logging("INfo", "Successfully Moves To Price Class");
		} else {
			logs.logging("Info", "Unable Passes a Price Changes");
		}
		assertTrue(flag);
	}

	/**
	 * To Click A Add Button Item In Line Details In Addition Type
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickAddButtonItemWiseInLineDetailsinAdditionMethod() {
		flag = webDB.waitForElement(OpenSoAmdLocators.AddButtoninAdditionType, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.AddButtoninAdditionType, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.ItemClickField, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Clicked A Add Button ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert A Item In Line Details In Addition Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Item_Add_Type
	 */
	public void insertIteminLineDetailsinAdditionMethod(String Item_Add_Type) {
		flag = webDB.waitForElement(OpenSoAmdLocators.ItemClickField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.ItemClickField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.ItemLovButton, Item_Add_Type);
			flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Insert A Item");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Cancel Stage In Transaction Screen While Navigates To Find Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void cancelStageinTransactions() {
		flag = webDB.waitForElement(OpenSoAmdLocators.CancelButton, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(OpenSoAmdLocators.CancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.OsoaDocSearchField, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Clicked a Cancel Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Cancel Status Record In Find Page
	 * 
	 * @author Kowshik-RMITS
	 * @param OsoaDoc
	 */
	public void verifyCancelRecordDocInFindPage(String OsoaDoc) {
		flag = webDB.waitForElement(OpenSoAmdLocators.OsoaDocSearchField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(OpenSoAmdLocators.OsoaDocSearchField, OsoaDoc, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSoAmdLocators.SearchButton, ElementType.Xpath);
				logs.logging("Info", "Successfully Executed a Cancel Stages Record ");
			} else {
				logs.logging("Info", "Unable appears a Record");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Hsn Code Field Null or Not
	 * 
	 * @author Kowshik-RMITS
	 * @param Hsn_Code
	 */
	public void verifyHsnCodeFieldIsNullOrNot(String Hsn_Code) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenSoAmdLocators.HsnCodeField, ElementType.Xpath);
		if (flag) {
			String Text = webDB.getTextFromElement(OpenSoAmdLocators.HsnCodeField, ElementType.Xpath);
			logs.logging("Info", "Hsn Code " + Text);
			if (Text == null || Text.trim().isEmpty()) {
				webDB.clickAnElement(OpenSoAmdLocators.HsnCodeField, ElementType.Xpath);
				logs.logging("Info", "Hsn Code Is Clicked");
				commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.HsnCodeLov, Hsn_Code);
				toClickaSaveButton();
			} else {
				toClickaSaveButton();
				logs.logging("Info", "Item has been Already Inserted a Hsn Code ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Valid Tax Amount In Curcy Against To The Customer
	 * 
	 * 
	 * @author Kowshik-RMITS
	 */
	public void validTaxAmountInLineDetailsForExportCustomerbigdecimal() {
		flag = webDB.waitForElement(OpenSoAmdLocators.CurcyField, ElementType.Xpath);
		if (flag) {
			String Curcy = webDB.getTextFromElement(OpenSoAmdLocators.CurcyField, ElementType.Xpath);
			logs.logging("Info", "Successfully validated currency: " + Curcy);
			if (Curcy.contains("INR")) {
				String Igst = webDB.getTextFromElement(OpenSoAmdLocators.IgstFieldInLineDetails, ElementType.Xpath);
				String Cgst = webDB.getTextFromElement(OpenSoAmdLocators.CgstFieldLineDetails, ElementType.Xpath);
				String Sgst = webDB.getTextFromElement(OpenSoAmdLocators.SgstFieldLineDetails, ElementType.Xpath);
				BigDecimal igstValue = new BigDecimal(Igst);
				BigDecimal cgstValue = new BigDecimal(Cgst);
				BigDecimal sgstValue = new BigDecimal(Sgst);
				if (igstValue.compareTo(BigDecimal.ZERO) > 0 || cgstValue.compareTo(BigDecimal.ZERO) > 0
						|| sgstValue.compareTo(BigDecimal.ZERO) > 0) {
					logs.logging("Info",
							"Tax values are valid for INR: IGST=" + Igst + ", CGST=" + Cgst + ", SGST=" + Sgst);
				} else {
					logs.logging("Error", "For INR, at least one of IGST, CGST, or SGST should be greater than zero.");
				}
			} else {
				String Igst = webDB.getTextFromElement(OpenSoAmdLocators.IgstFieldInLineDetails, ElementType.Xpath);
				String Cgst = webDB.getTextFromElement(OpenSoAmdLocators.CgstFieldLineDetails, ElementType.Xpath);
				String Sgst = webDB.getTextFromElement(OpenSoAmdLocators.SgstFieldLineDetails, ElementType.Xpath);
				BigDecimal igstValue = new BigDecimal(Igst);
				BigDecimal cgstValue = new BigDecimal(Cgst);
				BigDecimal sgstValue = new BigDecimal(Sgst);
				if (igstValue.compareTo(BigDecimal.ZERO) == 0 && cgstValue.compareTo(BigDecimal.ZERO) == 0
						&& sgstValue.compareTo(BigDecimal.ZERO) == 0) {
					logs.logging("Info", "Tax values are correctly set to zero for non-INR currency: IGST=" + Igst
							+ ", CGST=" + Cgst + ", SGST=" + Sgst);
				} else {
					logs.logging("Error", "For non-INR, all IGST, CGST, and SGST should be zero.");
				}
			}
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
			flag = text.equals(Value);
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
	 * To Verify Revision Amoount Against to the Tax Amount
	 * 
	 * @author Kowshik-RMITS
	 */
	public void validateTaxAmountInLineDetails() {
		flag = webDB.waitForElement(OpenSoAmdLocators.RevPriceField, ElementType.Xpath);
		if (flag) {
			String RevValue = webDB.getTextFromElement(OpenSoAmdLocators.RevPriceField, ElementType.Xpath);
			logs.logging("Info", "Get Revision Price in Line Details " + RevValue);
			flag = webDB.waitForElement(OpenSoAmdLocators.TaxPercent, ElementType.Xpath);
			if (flag) {
				String TaxPercent = webDB.getTextFromElement(OpenSoAmdLocators.TaxPercent, ElementType.Xpath);
				flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
				if (flag) {
					String DiscPercent = webDB.getTextFromElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
					if (flag) {
						BigDecimal RevValue1 = new BigDecimal(RevValue);
						BigDecimal TaxPercent1 = new BigDecimal(TaxPercent);
						BigDecimal DiscPercent1 = new BigDecimal(DiscPercent);
						BigDecimal Hundred = new BigDecimal("100");
						BigDecimal Two = new BigDecimal("2");

						BigDecimal discAmountGet = RevValue1.multiply(DiscPercent1).divide(Hundred);
						logs.logging("Info", "Amount Calculated by price & Disc percent " + discAmountGet);

						BigDecimal DiscamountNRevPrice = RevValue1.subtract(discAmountGet);
						logs.logging("Info", "Successfully dedcution by disc Amount" + DiscamountNRevPrice);

						BigDecimal TaxAmount = DiscamountNRevPrice.multiply(TaxPercent1).divide(Hundred);
						logs.logging("Info", "Successfully deducted a Tax amount" + TaxAmount);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Validates Header Details in Open SO Amd
	 * 
	 * @author Kowshik-RMITS
	 * 
	 * @param Location
	 */
	public void validatesOpenSoAmendmenHeader(String Location) {
		if (flag) {
			verifyGetTextValidation(OpenSoAmdLocators.LocationId,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.LOCATION_ID));
			logs.logging("Info", "Location Id Is Valid");
			verifyGetTextValidation(OpenSoAmdLocators.UnitField,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.UNIT));
			logs.logging("Info", "Unit Id Is Verified");
			verifyGetTextValidation(OpenSoAmdLocators.CustomerIdField,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.CUSTOMER_ID));
			logs.logging("Info", "Customer Id is Verified ");
			verifyDomPropertyValidation(OpenSoAmdLocators.CustomerNameField,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.CUSTOMER_NAME));
			logs.logging("Info", "Customer Name is Verified");
			verifyDomPropertyValidation(OpenSoAmdLocators.OpenSoDocumentNumber,
					itemDetailHandler.getOpensalesOrderAmdforGivenItem(Location, OpenSOAmdDetails.OSO_NO));
			logs.logging("Info", "Open Sales Order Document Is Verified ");
		} else {
			logs.logging("Info", "Record is MisMatched");
		}
		assertTrue(flag);
	}

	/**
	 * Validate Header Details Open so Amd
	 * 
	 * @author Kowshik-RMITS
	 * @param Location
	 */
	public void validateHeaderDetails(String Location) {
		commonFunc.hardWait(2000);
		verifyGetTextValidation(OpenSoAmdLocators.LocationId,
				itemDetailHandler.getOpenSOHeaderDetails(Location, OpenSOHeaderDetailsName.LOCATION_ID));
		logs.logging("Info", "Location Id is verfied " + Location);
		verifyGetTextValidation(OpenSoAmdLocators.UnitField,
				itemDetailHandler.getOpenSOHeaderDetails(Location, OpenSOHeaderDetailsName.UNIT));
		logs.logging("Info", "Unit is Verified ");
		verifyGetTextValidation(OpenSoAmdLocators.CustomerIdField,
				itemDetailHandler.getOpenSOHeaderDetails(Location, OpenSOHeaderDetailsName.CUSTOMER_ID));
		logs.logging("Info", "Customer Id is verified");
		verifyDomPropertyValidation(OpenSoAmdLocators.CustomerNameField,
				itemDetailHandler.getOpenSOHeaderDetails(Location, OpenSOHeaderDetailsName.CUSTOMER_NAME));
		logs.logging("Info", "Customer name is verifed ");
		verifyDomPropertyValidation(OpenSoAmdLocators.OpenSoDocumentNumber,
				itemDetailHandler.getOpenSOHeaderDetails(Location, OpenSOHeaderDetailsName.OSO_NO));
		logs.logging("Info", "Osoc Documnet is verified ");
	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - Local
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyDomesticCustomerLocalGstTypePriceChange() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_PC_Local"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		validateHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Class Change - Domestic Customer - Local
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyDomesticCustomerLocalGstTypePriceClassChange() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_PCC_Local"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		validateHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
		toClickaSaveButton();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Addition of Item - Domestic Customer - Local
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean veriftyLocalCustomerDomesticAdditionofItem() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_Add_Local"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		validateHeaderDetails(commonFunc.getDataFromTestDataSheet("L0001"));
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Deletion of Item - Domestic Customer - Local
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyLocalCustomerDomesticDeletationofItem() {
		createOpenSoDocLocalCustomer();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickDeletationofItem(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Verify a Cancel Scenrios With Line Items
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyaCancelStagesRecord() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_Cancel"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		validateHeaderDetails(commonFunc.getDataFromTestDataSheet("L30"));
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		cancelStageinTransactions();
		verifyCancelRecordDocInFindPage(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - Interstate
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyDomesticCustomerInterstateGstTypePriceChange() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_PC_IS"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Class Change - Domestic Customer - Interstate
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyDomesticCustomerInterstateGstTypePriceClassChange() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_PCC_IS"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
		toClickaSaveButton();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Addition of Item - Domestic Customer - Interstate
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean veriftyInterStateCustomerDomesticAdditionofItem() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_Add_IS"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Deletion of Item - Domestic Customer - Interstate
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyInterStateCustomerDomesticDeletationofItem() {
		createOpenSoDocInterstateCustomer();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickDeletationofItem(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Change - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyExportCustomerPriceChange() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_PC_EX"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Class Change - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyExportCustomerPriceClassChanges() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_PCC_EX"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order - Addition of Item - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyExportCustomerAdditionOfItem() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_Add_EX"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(3000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Amend Sales Order - Deletion of Item - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyExportCustomerDeletationOfItem() {
		createOpenSoExportCustomer();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickDeletationofItem(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - UTGST
	 * 
	 * @author Kowshik-RMITSan
	 * @return boolean flag
	 */

	public boolean verifyUTGSTpriceChanges() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_PC_UTGST"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		verifyPoDateNullOrNot(commonFunc.getDataFromTestDataSheet("PO_Date"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(1000);
		commonFunc.logoutUser();
		commonFunc.hardWait(1000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		commonFunc.logoutUser();

		return flag;

	}

	/**
	 * Amend Sales Order -Price Class Change - Domestic Customer - UTGST
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */

	public boolean verifyUTGSTpriceclassChanges() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_PCC_UTGST"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();

		return flag;

	}

	/**
	 * Amend Sales Order - Addition of Item - Domestic Customer - UTGST
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyUTGSTAdditionOfItem() {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}

		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_ADD_UTGST"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(3000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Amend Sales Order - Deletion of Item - Domestic Customer - UTGST
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */

	public boolean verifyUTGSTDeletationOfItem() {

		createOpenSoExportCustomer();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		commonFunc.clickOpensoNode();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickDeletationofItem(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen(VariableHandler.getOSOAdocnum());
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
		commonFunc.hardWait(2000);
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"AshokUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Creates Open So Document Locul Customer For Deletation Scenrios
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean createOpenSoDocLocalCustomer() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String CustomerID = commonFunc.getDataFromTestDataSheet("Cus_Local");
		String OpenSOPrefix = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String ItemDelete = commonFunc.getDataFromTestDataSheet("ItemDelete");
		String Cost1 = RandomStringUtils.randomNumeric(2);
		if (Cost1.equals("00"))
			Cost1 = "10";
		String Mrp = String.valueOf(Integer.parseInt(Cost1) + 10);
		String OpenSOPrefix1 = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		fetchingOpenSoDoc();
		openso.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CustomerID,
				OpenSOPrefix1);
		openso.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
		openso.clickFetchCustomerAndSelectItemInOpenSO(commonFunc.getDataFromTestDataSheet("ItemDelete"));
		openso.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost1, Mrp, OpenSOHSN);
		openso.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
		openso.enterEffectiveFromAndToDate(Today, Today, Today);
		openso.clickHeaderSaveAndClickPostBtnInOpenSO();
		approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOpenSONumber());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOpenSONumber());
		commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Creates Open So Document Interstate Customer For Deletation Scenrios
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */

	public boolean createOpenSoDocInterstateCustomer() {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String CustomerID = commonFunc.getDataFromTestDataSheet("Cus_Interstate");
		String OpenSOPrefix = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String ItemDelete = commonFunc.getDataFromTestDataSheet("ItemDelete");
		String Cost1 = RandomStringUtils.randomNumeric(2);
		if (Cost1.equals("00"))
			Cost1 = "10";
		String Mrp = String.valueOf(Integer.parseInt(Cost1) + 10);
		String OpenSOPrefix1 = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		fetchingOpenSoDoc();
		openso.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CustomerID,
				OpenSOPrefix1);
		openso.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
		openso.clickFetchCustomerAndSelectItemInOpenSO(commonFunc.getDataFromTestDataSheet("ItemDelete"));
		openso.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost1, Mrp, OpenSOHSN);
		openso.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
		openso.enterEffectiveFromAndToDate(Today, Today, Today);
		openso.clickHeaderSaveAndClickPostBtnInOpenSO();
		approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOpenSONumber());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOpenSONumber());
		commonFunc.logoutUser();

		return flag;

	}

	/**
	 * Creates Open So Document Export Customer For Deletation Scenrios
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */

	public boolean createOpenSoExportCustomer() {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String CustomerID = commonFunc.getDataFromTestDataSheet("Cus_Export");
		String OpenSOPrefix = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String ItemDelete = commonFunc.getDataFromTestDataSheet("ItemDelete");
		String Cost1 = RandomStringUtils.randomNumeric(2);
		if (Cost1.equals("00"))
			Cost1 = "10";
		String Mrp = String.valueOf(Integer.parseInt(Cost1) + 10);
		String OpenSOPrefix1 = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");

		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"HodUsername");
		fetchingOpenSoDoc();
		openso.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CustomerID,
				OpenSOPrefix1);
		openso.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
		openso.clickFetchCustomerAndSelectItemInOpenSO(commonFunc.getDataFromTestDataSheet("ItemDelete"));
		openso.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost1, Mrp, OpenSOHSN);
		openso.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
		openso.enterEffectiveFromAndToDate(Today, Today, Today);
		openso.clickHeaderSaveAndClickPostBtnInOpenSO();
		approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername");
		approveFunc.navigateToApprovals();
		approveFunc.filterDocumentsforApproval(VariableHandler.getOpenSONumber());
		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOpenSONumber());
		commonFunc.logoutUser();

		return flag;
	}
	
	/**
	 * Fetching Open So Document Deletation 
	 * @author Kowshik-RMITS
	 */

	public void fetchingOpenSoDoc() {

		commonFunc.navigateOpenSO();
		openso.selectOpenSOTypeWithRadioButtons("Open SO");
		openso.clickAddNewOpenSOPlsIcon();

	}
	
	/**Verify Purchase Order Date Null Or Not
	 * 
	 * @author Kowshik - RMITS
	 * @param POdate
	 */

	public void verifyPoDateNullOrNot(String POdate) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenSoAmdLocators.PoDate, ElementType.Xpath);
		if (flag) {
			String Text1 = webDB.getDomPropertyFromElement(OpenSoAmdLocators.PoDate, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("Info", "PO Date: " + Text1);
			if (Text1 == null || Text1.trim().isEmpty()) {
				webDB.clickAnElement(OpenSoAmdLocators.PoDateField, ElementType.Xpath);
				webDB.sendTextToAnElement(OpenSoAmdLocators.PoDate, POdate, ElementType.Xpath);
				logs.logging("Info", "POdate is Inserted " + POdate);
			} else {
				logs.logging("Info", "No PO date provided to insert.");
			}
			assertTrue(flag);
		}
	}

}
