package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */

import static org.testng.Assert.assertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import org.apache.commons.lang3.RandomStringUtils;
import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.ItemDetailsHandler.OpenSOHeaderDetailsName;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.OpenSoAmdLocators;
import commonlocators.*;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class OpenSoAmdFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	private static OpenSalesOrderFunctions openso = new OpenSalesOrderFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static CustomerItemsFunctions cusItems = new CustomerItemsFunctions();

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
		commonFunc.checkRefreshCellsDisappear();
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
			webDB.moveToElement(OpenSoAmdLocators.currentRateDefault, ElementType.Xpath);
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
						commonFunc.checkRefreshCellsDisappear();
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
	public void GetDocumentFromScreen() {
		flag = webDB.waitForElement(OpenSoAmdLocators.OsoaDocNumber, ElementType.Xpath);
		if (flag) {
			String OsoaDoc = webDB.getTextFromElement(OpenSoAmdLocators.OsoaDocNumber, ElementType.Xpath);
			VariableHandler.setOSOAdocnum(OsoaDoc);
			logs.logging("info", "Get openso Amendment DocNo Is: " + VariableHandler.getOSOAdocnum());
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used to Click And Enter A Value In A Table
	 * 
	 * @author @author Kowshik-RMITS
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
	 * @return
	 */
	public boolean verifyWaitingForApproval1() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.OpsoForwardApproval,
					commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpsoOkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSalesOrderLocators.OpsoOkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
			commonFunc.hardWait(2000);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getOSOAdocnum(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));

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
			flag = webDB.waitForClickElement(OpenSalesOrderLocators.CancelCaution, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSalesOrderLocators.CancelCaution, ElementType.Xpath);
				flag = webDB.waitForElementUntilGivenTime(OpenSalesOrderLocators.NoteAlertMessage, ElementType.Xpath,
						45);
				flag = webDB.waitForElement(OpenSoAmdLocators.OsoaDocSearchField, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Successfully Clicked a Cancel Button");
				}
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
				ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
				// toClickaSaveButton();
			} else {
				ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
				// toClickaSaveButton();

				logs.logging("Info", "Item has been Already Inserted a Hsn Code ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Passes a Price Class
	 * 
	 * @author Kowshik-RMITS
	 * @param Priceclass
	 */
	public void verifyPriceClassNullOrNot(String Priceclass) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenSoAmdLocators.PriceClassField, ElementType.Xpath);
		if (flag) {
			String Text = webDB.getTextFromElement(OpenSoAmdLocators.PriceClassField, ElementType.Xpath);
			logs.logging("INFO", "Price Class" + Text);
			if (Text == null || Text.trim().isEmpty()) {
				webDB.clickAnElement(OpenSoAmdLocators.PriceClassField, ElementType.Xpath);
				logs.logging("info", "Price Class Is Clicked ");
				webDB.selectDropDownByText(OpenSoAmdLocators.PriceClassSelect, Priceclass, ElementType.Xpath);
			} else {
				logs.logging("Info", "Price Class Already Linked ");
			}

		}
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

		flag = webDB.waitForElement(OpenSoAmdLocators.RevPriceField(1), ElementType.Xpath);
		if (flag) {
			String RevValue = webDB.getTextFromElement(OpenSoAmdLocators.RevPriceField(1), ElementType.Xpath);
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

						VariableHandler.setRevisiedValue(RevValue1);
						VariableHandler.setTaxPercent(TaxPercent1);

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
	 * To Click All Radio Button
	 * 
	 * @author Kowshik-RMITS
	 *
	 */
	public void clickSelectAllRatioButton() {
		flag = webDB.waitForElement(OpenSoAmdLocators.CurrentRateContractSelectAll, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.CurrentRateContractSelectAll, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSoAmdLocators.soAmdBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Select All Radio Checked");
			} else {
				logs.logging("Info", "Failed to Checked Select All");
			}

		}
	}

	/**
	 * To Click All Radio Button
	 * 
	 * @author Kowshik-RMITS
	 *
	 */
	public void multiplesLinesInsert(String Hsn_code2) {

		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelutils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";

		itemMultiples = excelutils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("OpenSoAMDMultiplesLines"));
		System.out.println(itemMultiples.length);

		for (int i = 1; i < itemMultiples.length; i++) {
			String Revision_Price1 = itemMultiples[i][1];
			String Mrp_Price1 = itemMultiples[i][2];
			String Disc_Percent1 = itemMultiples[i][3];

			// add plus to creates a item
			clickAddButtonItemWiseInLineDetailsinAdditionMethod();

			// to insert a item in addition method

			flag = webDB.waitForElement(OpenSoAmdLocators.revField, ElementType.Xpath);
			if (flag) {
				verifyAddTableSpecs(OpenSoAmdLocators.revField, OpenSoAmdLocators.RevPrice, Revision_Price1);
				flag = webDB.waitForElement(OpenSoAmdLocators.MrpField, ElementType.Xpath);
				if (flag) {
					verifyAddTableSpecs(OpenSoAmdLocators.MrpField, OpenSoAmdLocators.Mrpprice, Mrp_Price1);
					flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
					if (flag) {
						verifyAddTableSpecs(OpenSoAmdLocators.DiscFieldClick, OpenSoAmdLocators.DiscPercentInsert,
								Disc_Percent1);
						flag = webDB.waitForElement(OpenSoAmdLocators.HsnCodeField, ElementType.Xpath);
						if (flag) {
							logs.logging("Info", "Successfully Insert a rev Price , Mrp Price , Disc ");
						} else {
							logs.logging("Info", "Failed to Insert ");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Purchase Order Date Null Or Not
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

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - Local
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	public boolean verifyDomesticCustomerLocalGstTypePriceChange(String locution) {

		String Revisised_Price = RandomStringUtils.randomNumeric(3);
		if (Revisised_Price.equals("000")) {
			Revisised_Price = "100";
		}
		String Mrp = RandomStringUtils.randomNumeric(3);
		if (Mrp.equals("000")) {
			Mrp = "100";
		}
		String MrpValue = String.valueOf(Integer.parseInt(Mrp) + 1000);
		cusItems.createOpenSoItemOpenSoForDomesticCustomer();
		commonFunc.logoutUser();
		openso.verifyOpenSOForLocalCustomer();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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

		cusItems.createOpenSoItemForDomesticCustomerForPriceClass();
		commonFunc.logoutUser();
		openso.verifyOpenSOForLocalCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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

		cusItems.createOpenSoItemForDomesticCustomerForAdditionOfItem();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("Open_So_Amd_Dom_Cus"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(VariableHandler.getItemName());
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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
		cusItems.createOpenSoForLocalCustomer();
		createOpenSoDocLocalCustomer();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
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
		validateTaxAmountInLineDetails();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_Cancel"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(commonFunc.getDataFromTestDataSheet("Item_Add_Type"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(commonFunc.getDataFromTestDataSheet("Revisised_Price"),
				commonFunc.getDataFromTestDataSheet("MRP_Price"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		toClickaSaveButton();
		GetDocumentFromScreen();
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

		cusItems.createOpenItemInterstateForAdditionOfItem();
		commonFunc.logoutUser();
		openso.verifyOfOpenSoItemInterstateCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();

		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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
		cusItems.createOpenItemInterstateForAdditionOfItem();
		commonFunc.logoutUser();
		openso.verifyOfOpenSoItemInterstateCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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
		cusItems.createOpenItemInterstateForAdditionOfItem();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_Add_IS"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(VariableHandler.getItemName());
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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
		cusItems.createOpenItemForInterStateForDeletaionOfProcess();
		createOpenSoDocInterstateCustomer();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
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
		validateTaxAmountInLineDetails();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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

		cusItems.createOpenSoForExportCustomer();
		commonFunc.logoutUser();
		openso.verifyOfOpenSoItemExportCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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
		cusItems.createOpenSoForExportCustomer();
		commonFunc.logoutUser();
		openso.verifyOfOpenSoItemExportCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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
		cusItems.createOpenSoForExportCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("Open_So_Amd_Ex_Doc_up"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(VariableHandler.getItemName());
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(3000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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
		cusItems.createOpenSoForExportCustomer();
		createOpenSoExportCustomer();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
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
		validateTaxAmountInLineDetails();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Amend Sales Order -Price Change - Domestic Customer - UTGST
	 * 
	 * @author Kowshik-RMITS
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

		cusItems.createOpenSoForUTGSTCustomer();
		commonFunc.logoutUser();
		openso.verifyOpenSoUTGSTCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		verifyPoDateNullOrNot(commonFunc.getDataFromTestDataSheet("PO_Date"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(1000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(1000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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

		cusItems.createOpenSoForUTGSTCustomer();
		commonFunc.logoutUser();
		openso.verifyOpenSoUTGSTCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(VariableHandler.getOpenSONumber());
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickPriceChanges(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		ToChangesPriceChanges1(commonFunc.getDataFromTestDataSheet("Price_Class_OSOA"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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

		cusItems.createOpenSoForUTGSTCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("Open_So_Amd_Utgst_Doc"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();
		clickAddButtonItemWiseInLineDetailsinAdditionMethod();
		insertIteminLineDetailsinAdditionMethod(VariableHandler.getItemName());
		verifyOSOAInsertedRevisiedPriceAndMrpPriceInAdditionType(Revisised_Price, MrpValue);
		insertDiscPercentInAdditionType(commonFunc.getDataFromTestDataSheet("Disc_Perent"));
		verifyHsnCodeFieldIsNullOrNot(commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyPriceClassNullOrNot(commonFunc.getDataFromTestDataSheet("Openso_Price"));
		toClickaSaveButton();
		validateTaxAmountInLineDetails();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();

		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();

		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(3000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
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
		cusItems.createOpenForUTGSTCustomer();
		createOpenSoForUTGSTCustomerForDeletationProcess();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
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

		validateTaxAmountInLineDetails();

		GetDocumentFromScreen();

		String locution = null;
		openSoAmdHeaderValidation(locution);
		checkDbValidationTaxAmount();

		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Verify Multiples Types In Open So Amd
	 * 
	 * @author Kowshik-RMITS
	 */
	public void verifyMutiplesAddTypes() {

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatesOpenSoAmdScreen();
		ClickOpenSoAmdendmentButton();
		clickAddButtoninFindPage();
		takenOpenSoDocumentFromCurrentRateContract(commonFunc.getDataFromTestDataSheet("oso_Docnum_Add_IS"));
		clickSelectFlagInCurrentRateContract();
		clickSoAmdendmentButtonInCurrentRateContract();
		clickAdditionfItemOfRadioButton(commonFunc.getDataFromTestDataSheet("OsoamdReason"));
		closeAlertMessage();

		multipleLinesAdditionItem(commonFunc.getDataFromTestDataSheet("Item"),
				commonFunc.getDataFromTestDataSheet("RevPrice"), commonFunc.getDataFromTestDataSheet("MrpPrice"),
				commonFunc.getDataFromTestDataSheet("DiscAmount"), commonFunc.getDataFromTestDataSheet("HSNcode"));
		toClickaSaveButton();
		validTaxAmountInLineDetailsForExportCustomerbigdecimal();
		insertEffdateAndEfftoDateInHeaderDetails(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		toClickSaveButtonInHeader();
		GetDocumentFromScreen();
		ToClickPostButtonInHeader();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"AshokUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOSOAdocnum());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOSOAdocnum());
		commonFunc.logoutUser();

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
		String Cost1 = RandomStringUtils.randomNumeric(2);
		if (Cost1.equals("00"))
			Cost1 = "10";
		String Mrp = String.valueOf(Integer.parseInt(Cost1) + 10);
		String OpenSOPrefix1 = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
		String Item = VariableHandler.getItemName();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		fetchingOpenSoDoc();
		openso.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CustomerID,
				OpenSOPrefix1);
		openso.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
		openso.clickFetchCustomerAndSelectItemInOpenSoUpdated();
		openso.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost1, Mrp, OpenSOHSN);
		openso.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
		openso.enterEffectiveFromAndToDate(Today, Today, Today);
		openso.clickHeaderSaveAndClickPostBtnInOpenSO();
		approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getOpenSONumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));

//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOpenSONumber());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOpenSONumber());
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
		String Cost1 = RandomStringUtils.randomNumeric(2);
		if (Cost1.equals("00"))
			Cost1 = "10";
		String Mrp = String.valueOf(Integer.parseInt(Cost1) + 10);
		String OpenSOPrefix1 = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
		String Item = VariableHandler.getItemName();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		fetchingOpenSoDoc();
		openso.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CustomerID,
				OpenSOPrefix1);
		openso.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
		openso.clickFetchCustomerAndSelectItemInOpenSoUpdated();
		openso.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost1, Mrp, OpenSOHSN);
		openso.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
		openso.enterEffectiveFromAndToDate(Today, Today, Today);
		openso.clickHeaderSaveAndClickPostBtnInOpenSO();
		approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getOpenSONumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOpenSONumber());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOpenSONumber());
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
		String Cost1 = RandomStringUtils.randomNumeric(2);
		if (Cost1.equals("00"))
			Cost1 = "10";
		String Mrp = String.valueOf(Integer.parseInt(Cost1) + 10);
		String OpenSOPrefix1 = commonFunc.getDataFromTestDataSheet("Prefix_OP100");
		// 1
		String Item = VariableHandler.getItemName();
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		fetchingOpenSoDoc();
		openso.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("Loc_100"), CustomerID,
				OpenSOPrefix1);
		openso.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
		// 2
		openso.clickFetchCustomerAndSelectItemInOpenSoUpdated();
		openso.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost1, Mrp, OpenSOHSN);
		openso.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
		openso.enterEffectiveFromAndToDate(Today, Today, Today);
		openso.clickHeaderSaveAndClickPostBtnInOpenSO();
		approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.hardWait(2000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getOpenSONumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOpenSONumber());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOpenSONumber());
		commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Creates Open So Document UTGST Customer For Deletation Scenrios
	 * 
	 * @author Kowshik-Rmits
	 */
	public void createOpenSoForUTGSTCustomerForDeletationProcess() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		String CustomerID = commonFunc.getDataFromTestDataSheet("UTGST_cus");
		String Cost1 = RandomStringUtils.randomNumeric(2);
		if (Cost1.equals("00"))
			Cost1 = "10";
		String Mrp = String.valueOf(Integer.parseInt(Cost1) + 10);
		String OpenSOPrefix1 = commonFunc.getDataFromTestDataSheet("Prefix_UTGST");
		String OpenSOHSN = commonFunc.getDataFromTestDataSheet("HSN2022-5%");
		String Item = VariableHandler.getItemName();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		fetchingOpenSoDoc();
		openso.fillLocationCustomerPrefixInOpenSO(commonFunc.getDataFromTestDataSheet("UTGSTLocation"), CustomerID,
				OpenSOPrefix1);
		openso.clickTickIconAndSetPODateInAddOpenSOAndStoreDocNo();
		openso.clickFetchCustomerAndSelectItemInOpenSoUpdated();
		openso.fillPriceMRPAndHSNCodeforAddedLastItemInOpenSO(Cost1, Mrp, OpenSOHSN);
		openso.fillDiscountAndSaveLinesforAddedLastItemInOpenSO("0");
		openso.enterEffectiveFromAndToDate(Today, Today, Today);
		openso.clickHeaderSaveAndClickPostBtnInOpenSO();
		approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.hardWait(2000);
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getOpenSONumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
//		commonFunc.logoutUser();
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getOpenSONumber());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getOpenSONumber());
		commonFunc.logoutUser();

	}

	/**
	 * Fetching Open So Document Deletation
	 * 
	 * @author Kowshik-RMITS
	 */
	public void fetchingOpenSoDoc() {
		commonFunc.navigateOpenSO();
		openso.selectOpenSOTypeWithRadioButtons("Open SO");
		openso.clickAddNewOpenSOPlsIcon();
	}

	/**
	 * Multiples Lines Addition Types In Open So Amd
	 * 
	 * @author Kowshik- RMITS
	 * 
	 * @param Item
	 * @param RevPrice
	 * @param MrpPrice
	 * @param DiscAmount
	 * @param HSNcode
	 */
	public void multipleLinesAdditionItem(String Item, String RevPrice, String MrpPrice, String DiscAmount,
			String HSNcode) {

		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("OpenSoMultiplesSheetName"));
		System.out.println(itemMultiples.length);
		for (int i = 1; i < itemMultiples.length; i++) {
			String Item1 = itemMultiples[i][0];
			String RevPrice1 = itemMultiples[i][1];
			String MrpPrice1 = itemMultiples[i][2];
			String DiscPercent1 = itemMultiples[i][3];
			String Hsncode1 = itemMultiples[i][4];

			flag = webDB.waitForElement(OpenSoAmdLocators.AddButtoninAdditionType, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSoAmdLocators.AddButtoninAdditionType, ElementType.Xpath);
				flag = webDB.waitForElement(OpenSoAmdLocators.ItemClickField, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Successfully Clicked A Add Button ");
					flag = webDB.waitForElement(OpenSoAmdLocators.ItemClickField, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(OpenSoAmdLocators.ItemClickField, ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.ItemLovButton, Item1);
						flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
						if (flag) {
							logs.logging("Info", "Successfully Insert A Item");

							flag = webDB.waitForElement(OpenSoAmdLocators.revField, ElementType.Xpath);
							if (flag) {
								verifyAddTableSpecs(OpenSoAmdLocators.revField, OpenSoAmdLocators.RevPrice, RevPrice1);
								flag = webDB.waitForElement(OpenSoAmdLocators.MrpField, ElementType.Xpath);
								if (flag) {
									verifyAddTableSpecs(OpenSoAmdLocators.MrpField, OpenSoAmdLocators.Mrpprice,
											MrpPrice1);
									flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick, ElementType.Xpath);
									if (flag) {
										logs.logging("Info", "Revision price & Mrp Price Inserted Successfully ");
										commonFunc.checkRefreshCellsDisappear();
										flag = webDB.waitForElement(OpenSoAmdLocators.HsnCodeField, ElementType.Xpath);
										if (flag) {
											String Text = webDB.getTextFromElement(OpenSoAmdLocators.HsnCodeField,
													ElementType.Xpath);
											logs.logging("Info", "Hsn Code " + Text);
											if (Text == null || Text.trim().isEmpty()) {
												webDB.clickAnElement(OpenSoAmdLocators.HsnCodeField, ElementType.Xpath);
												logs.logging("Info", "Hsn Code Is Clicked");
												commonFunc.searchAndSelectValueInFilters(OpenSoAmdLocators.HsnCodeLov,
														Hsncode1);

												flag = webDB.waitForElement(OpenSoAmdLocators.DiscFieldClick,
														ElementType.Xpath);
												if (flag) {
													verifyAddTableSpecs(OpenSoAmdLocators.DiscFieldClick,
															OpenSoAmdLocators.DiscPercentInsert, DiscPercent1);

												}
											}
										}
									}

								}

							}

						}
					}
				}

			}
		}

	}

	/**
	 * DataBase Validation Of Text Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Location
	 * @param ExpectedDBValue
	 */
	public void verifyAssertionGetTextValidation(String Location, String ExpectedDBValue) {
		flag = webDB.waitForElement(Location, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(Location, ElementType.Xpath);
			logs.logging("INFO", "Get Text Actual UI Value Is: " + text);
			logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
			flag = text.equals(ExpectedDBValue);
			if (flag) {
				logs.logging("INFO",
						"Verified Actual UI Value : " + text + " With Expected DataBase Value :" + ExpectedDBValue);
			} else {
				logs.logging("INFO", "Verification Failed Expected DataBase Value : " + ExpectedDBValue
						+ "Get Text Actual UI Value : " + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * DataBase Validation Of Dom Property Type
	 * 
	 * @author Kowshik - RMITS
	 * @param UIFieldLocator
	 * @param ExpectedDBValue
	 */
	public void verifyAssertionDomPropertyValidation(String UIFieldLocator, String ExpectedDBValue) {
		flag = webDB.waitForElement(UIFieldLocator, ElementType.Xpath);
		if (flag) {
			String text = webDB.getDomPropertyFromElement(UIFieldLocator, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get Text Actual UI Value Is: " + text);
			logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
			flag = text.equals(ExpectedDBValue);
			if (flag) {
				logs.logging("INFO",
						"Verified Actual UI Value : " + text + " With Expected DataBase Value :" + ExpectedDBValue);
			} else {
				logs.logging("INFO", "Verification Failed Expected DataBase Value : " + ExpectedDBValue
						+ "Get Text Actual UI Value : " + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * DataBase Validation Of Both Value Type
	 * 
	 * @author Kowshik - RMITS
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("Info", "Actual Value Is " + Actual);
		logs.logging("Info", "Expected Value Is " + Expected);
		flag = Actual.contains(Expected);
		if (flag) {
			logs.logging("Info", "Verified Actual And Expected Values");
		} else {
			logs.logging("INFO", "Mismatch With Actual And Expected");
		}
		assertTrue(flag);
	}

	/**
	 * DataBases Validation Of Header Details Open So Amd
	 * 
	 * @author Kowshik - RMITS
	 * @param locution
	 */
	public void openSoAmdHeaderValidation(String location) {
		verifyAssertionGetTextValidation(OpenSoAmdLocators.LocationId, dbCommonFunctions.OSOAdetails(
				VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbLocutionColumnName")));
		logs.logging("INFO", "Header Location Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(OpenSoAmdLocators.UnitField, dbCommonFunctions.OSOAdetails(
				VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbUnitColumnName")));
		logs.logging("INFO", "Header units Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(OpenSoAmdLocators.CustomerIdField, dbCommonFunctions.OSOAdetails(
				VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbCustomerIdColumnName")));
		logs.logging("Info", "Header Customer Id Matched With DataBase Values");

		verifyAssertionDomPropertyValidation(OpenSoAmdLocators.CustomerNameField, dbCommonFunctions.OSOAdetails(
				VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbCustomerNameColumnName")));
		logs.logging("INFO", "Header Customer Name Matched With DataBase Values");

		verifyAssertionGetTextValidation(OpenSoAmdLocators.AmdNo, dbCommonFunctions.OSOAdetails(
				VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbAmdNumberColumnName")));
		logs.logging("INFO", "Header Amendment No Matched With DataBase Values");

		verifyAssertionGetTextValidation(OpenSoAmdLocators.ItemIdField(1), dbCommonFunctions
				.OSOAdetails(VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbItemID")));
		logs.logging("INFO", "Line Details Item ID is Matched With DataBase Values");

		verifyAssertionGetTextValidation(OpenSoAmdLocators.ItemDesField(1), dbCommonFunctions.OSOAdetails(
				VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbItemDescripition")));
		logs.logging("INFO", "Item Descripition Matched With DataBase Values");

		verifyGetTextValidation(OpenSoAmdLocators.ItemUom(1), dbCommonFunctions
				.OSOAdetails(VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbItemUom")));
		logs.logging("INFO", "Item Against UOM Matched With DataBase Values");
	}

	/**
	 * DataBases Validation Of Line Details Open So Amd
	 * 
	 * @author Kowshik-RMITS
	 */
	public void checkDbValidationTaxAmount() {
		BigDecimal RevisiedValue = VariableHandler.getRevisiedValue();
		BigDecimal TaxPercent = VariableHandler.getTaxPercent();

		verifyAssertionBothValues(dbCommonFunctions.OSOAdetails(VariableHandler.getOSOAdocnum(),
				commonFunc.getDataFromTestDataSheet("OSOADbRevisieValue")), RevisiedValue.toPlainString());
		logs.logging("INFO", "DataBase Matched With Expected Revisied Value  " + VariableHandler.getRevisiedValue());

		verifyAssertionBothValues(dbCommonFunctions.OSOAdetails(VariableHandler.getOSOAdocnum(),
				commonFunc.getDataFromTestDataSheet("OSOADbTaxPercent")), TaxPercent.toPlainString());
		logs.logging("INFO", "DataBase Matched With Expected TaxPercent " + VariableHandler.getTaxPercent());

		verifyAssertionGetTextValidation(OpenSoAmdLocators.IgstFieldInLineDetails, dbCommonFunctions
				.OSOAdetails(VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbIgstAmount")));
		logs.logging("Info", "Igst Amount Matched With DataBase Value");

		verifyAssertionGetTextValidation(OpenSoAmdLocators.CgstFieldLineDetails, dbCommonFunctions
				.OSOAdetails(VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbCgstAmount")));
		logs.logging("Info", "Cgst Amount Matched With DataBase Value");

		verifyAssertionGetTextValidation(OpenSoAmdLocators.SgstFieldLineDetails, dbCommonFunctions
				.OSOAdetails(VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbSgstAmount")));
		logs.logging("Info", "Sgst Amount Matched With DataBase Value");

		verifyAssertionGetTextValidation(OpenSoAmdLocators.UTgstFieldLineDetails, dbCommonFunctions.OSOAdetails(
				VariableHandler.getOSOAdocnum(), commonFunc.getDataFromTestDataSheet("OSOADbUTgstAmount")));
		logs.logging("Info", "UTGST Amount Matched With DataBase Value");
	}
}
