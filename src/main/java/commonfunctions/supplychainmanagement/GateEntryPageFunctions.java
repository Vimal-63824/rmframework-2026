package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks May 02, 2025
 */
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.ProductionOrderLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.GateEntryPageLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.RfqLocators;
import commonlocators.supplychainmanagement.SsoPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class GateEntryPageFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	static PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	static SsoPageFunctions Ssofunc = new SsoPageFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	LocalDate date = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String Today = date.format(formatter);
	LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yy");
	String tomorrowDate = myDateObj.format(myFormatObj);
	ArrayList<ArrayList> MultiplePODocs = new ArrayList<ArrayList>();

	/**
	 * This Methods Is Used To verify Create Gate Entry for Single Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyToCreateGateEntryFromPo() {
		POfunc.verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItemWithGateEntryD(
				"GateEntry");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		verifyToCreateGateEntryFromPendingPo();
		verifygateEntryDetailsPo();
		verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryUnitHeaderValidation();
		gateEntrySupplierHeaderValidation();
		gateEntryItemHeaderValidation();
		verifyToOverAllSaveAndPostTheGateEntryDocument();
	}

	/**
	 * This Methods Is Used To verify Create Gate Entry for Multiple Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void multiPoDocumentCreation(ArrayList<String> poList) {
		List<String> poList1 = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			System.out.println("Loop Running: " + i);
			POfunc.verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItemWithGateEntryD(
					"GateEntry");
		}
		VariableHandler.setMultiplePODocs(POfunc.MultiPODocumentNumber);
		System.out.println(VariableHandler.getMultiplePODocs().get(0));
		System.out.println(VariableHandler.getMultiplePODocs().get(1));
	}

	/**
	 * This Methods Is Used To verify Create Gate Entry for Multiple Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyToCreateGateEntryFromMultiplePo() {
		multiPoDocumentCreation(null);
//		POfunc.verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItemWithGateEntryD(
//				"GateEntry");
//		POfunc.verifyDirectPODomesticMaterialSimilarUOMwithAndWithoutInspectionMaterialTypeForUserDefinedItemWithGateEntryD(
//				"GateEntry");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		verifyToCreateGateEntryFromPendingMultiPo(VariableHandler.getMultiplePODocs());
		verifygateEntryDetailsPO();
		verifyToOverAllSaveAndPostTheGateEntryDocument();
	}

	/**
	 * This Methods Is Used To verify Create Gate Entry for Multiple Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyToCreateGateEntryFromMultipleSso() {
		Ssofunc.verifyCreationOfItemforPriceList();
		verifyCreationOfOpenPurchaseOrderGateEntry();
		Ssofunc.verifyCreationOfSsoDomesticSupplierSimilarUomGate();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		verifyToCreateGateEntryFromPendingMultiSsoo(commonFunc.getDataFromTestDataSheet("Opo_SupId"));
		verifygateEntryDetailsSso();
		verifyToOverAllSaveAndPostTheGateEntryDocument();
	}

	/**
	 * This Methods Is Used To verify Create Gate Entry for Multiple Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyToCreateDirectGateEntry() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		verifyAddingInwardDetails(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("Direct_Entry"), commonFunc.getDataFromTestDataSheet("Gate_Keeper"),
				commonFunc.getDataFromTestDataSheet("DriverName"));
		verifyAddingInwardLineSupplier(commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("Sim"));
		verifyAddingInwardLineItem();
		commonFunc.hardWait(5000);
		verifyClickingOverallSave();
		commonFunc.hardWait(5000);
		verifyClickingPost();
	}

	/**
	 * This Methods Is Used To verify Create Get Text For Gate Entry Document
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreatingGetextForGateEntryDocNumber() {
		webDB.switchToDefaultContent();
		webDB.waitUntilElementToNotBeStale(GateEntryPageLocators.GateEntryDocNum, ElementType.Xpath);
		flag = webDB.waitForElement(GateEntryPageLocators.GateEntryDocNum, ElementType.Xpath);
		if (flag) {
			String GATEDocNum = webDB.getTextFromElement(GateEntryPageLocators.GateEntryDocNum, ElementType.Xpath);
			VariableHandler.setGATEDocNum(GATEDocNum);
			logs.logging("INFO", "Gate Entry Doc num is:" + VariableHandler.getGATEDocNum());
		}
	}

	/**
	 * This Methods Is Used To verify Create Gate Entry From Pending Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyToCreateGateEntryFromPendingPo() {
//		VariableHandler.setPONumber("POGL3/00045/25-26");
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		if (flag)
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingPoButton, ElementType.Xpath);

		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingPoButton, ElementType.Xpath);
			logs.logging("INFO", "PendingPOButtonLov Shown");
			webDB.switchToFrame(GateEntryPageLocators.PendingPOGIframe, ElementType.Xpath);
			commonFunc.expandFindTable();
			flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingPoButtonLov, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(GateEntryPageLocators.ClickPendingPoButtonLov, ElementType.Xpath);
				logs.logging("INFO", "PendingPOButtonLov Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(GateEntryPageLocators.SearchAndSelectValue, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.SearchAndSelectValue,
							VariableHandler.getPONumber());
					webDB.switchToFrame(GateEntryPageLocators.PendingPOGIframe, ElementType.Xpath);
					logs.logging("INFO", "PO Number Shown");
					webDB.waitForElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
					if (flag) {
						flag = webDB.clickAnElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
						logs.logging("INFO", "Document Search And Selected Successfully");
						commonFunc.hardWait(5000);
						webDB.waitForElement(GateEntryPageLocators.GateEntryPendingPoSelectAll, ElementType.Xpath);
						if (flag) {
							flag = webDB.clickAnElement(GateEntryPageLocators.GateEntryPendingPoSelectAll,
									ElementType.Xpath);
							logs.logging("INFO", "Create Gate Entry Button shown");
							commonFunc.hardWait(2000);
							webDB.waitForElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);

							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Create Entry From Pending MultiPO
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyToCreateGateEntryFromPendingMultiPo(ArrayList<String> PONumber) {
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		if (flag)
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingPoButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingPoButton, ElementType.Xpath);
			logs.logging("INFO", "PendingPOButtonLov Shown");
			webDB.switchToFrame(GateEntryPageLocators.PendingPOGIframe, ElementType.Xpath);
			logs.logging("INFO", "Switched to Frame");
//			flag = webDB.waitForElement(GateEntryPageLocators.PendDateInput, ElementType.Xpath);
//			if (flag) {
//				webDB.clickAnElement(GateEntryPageLocators.PendDateInput, ElementType.Xpath);
//				webDB.sendTextToAnElement(GateEntryPageLocators.PendDateInput, Today, ElementType.Xpath);
//				logs.logging("INFO", "Date is Entered");
			for (int i = 0; i < VariableHandler.getMultiplePODocs().size(); i++) {
				webDB.waitForElement(GateEntryPageLocators.PendingGatePOLov, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.PendingGatePOLov, PONumber.get(i));
				webDB.switchToFrame(GateEntryPageLocators.PendingPOGIframe, ElementType.Xpath);
				logs.logging("INFO", "Switched to Frame");
				webDB.waitForElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
					logs.logging("INFO", "Document Search And Selected Successfully");
					commonFunc.hardWait(5000);
					webDB.waitForElement(GateEntryPageLocators.GateEntryPendingPoSelect, ElementType.Xpath);
					if (flag) {
						flag = webDB.clickAnElement(GateEntryPageLocators.GateEntryPendingPoSelect, ElementType.Xpath);
						logs.logging("INFO", "Create Gate Entry Button shown");
						webDB.waitForElement(GateEntryPageLocators.GateEntryPoClearButton, ElementType.Xpath);
						if (flag) {
							flag = webDB.clickAnElement(GateEntryPageLocators.GateEntryPoClearButton,
									ElementType.Xpath);
							logs.logging("INFO", "Clear Button shown");
						}
					}
				}
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			webDB.waitForElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify Create Gate Entry Pending multiple Supplier
	 * Schedule Order
	 * 
	 * @author Durai-RMITS
	 * @param Supplier
	 */
	public void verifyToCreateGateEntryFromPendingMultiSsoo(String Supplier) {
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		if (flag)
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingSsooButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingSsooButton, ElementType.Xpath);
			logs.logging("INFO", "PendingPOButtonLov Shown");
			flag = webDB.waitForElement(GateEntryPageLocators.PendingSsoGIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(GateEntryPageLocators.PendingSsoGIframe, ElementType.Xpath);
				logs.logging("INFO", "Switched to Frame");
				flag = webDB.waitForElement(GateEntryPageLocators.DocNumSso, ElementType.Xpath);
				if (flag) {
//					webDB.clickAnElement(GateEntryPageLocators.PendSupplierInput, ElementType.Xpath);
//					webDB.sendTextToAnElement(GateEntryPageLocators.PendSupplierInput, Supplier, ElementType.Xpath);
//					logs.logging("INFO", "Date is Entered");
//					webDB.sendTextToAnElement(GateEntryPageLocators.DocNumSso, VariableHandler.getSsoDocNum(),
//							ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.DocNumSso,
							VariableHandler.getSsoDocNum());
					logs.logging("INFO", "Document Number is Entered");
					webDB.switchToFrame(GateEntryPageLocators.PendingSsoGIframe, ElementType.Xpath);
					webDB.waitForElement(GateEntryPageLocators.GateEntrySsoSearchButton, ElementType.Xpath);
					if (flag) {
						flag = webDB.clickAnElement(GateEntryPageLocators.GateEntrySsoSearchButton, ElementType.Xpath);
						logs.logging("INFO", "Document Search And Selected Successfully");
						commonFunc.hardWait(4000);
						webDB.waitForElement(GateEntryPageLocators.GateEntryPendingPoSelectAll, ElementType.Xpath);
						if (flag) {
							flag = webDB.clickAnElement(GateEntryPageLocators.GateEntryPendingPoSelectAll,
									ElementType.Xpath);
							logs.logging("INFO", "Create Gate Entry Button shown");
							commonFunc.hardWait(6000);
							webDB.waitForElement(GateEntryPageLocators.CreateGeFromSso, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GateEntryPageLocators.CreateGeFromSso, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify Gate Entry Details Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifygateEntryDetailsPo() {
		flag = webDB.waitForElement(GateEntryPageLocators.SelectGateKeeper, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByValue(GateEntryPageLocators.SelectGateKeeper, "0001", ElementType.Xpath);
			logs.logging("INFO", "Gate Keeper Selected SuccessFully");
			commonFunc.verifyAddTableSpecs(GateEntryPageLocators.DcNumberField, GateEntryPageLocators.DcNumberField,
					VariableHandler.getPONumber());
			logs.logging("INFO", "DC Number SuccessFully Updated");
			commonFunc.verifyAddTableSpecs(GateEntryPageLocators.SupplierBillNumber,
					GateEntryPageLocators.SupplierBillNumber, VariableHandler.getPONumber());
			logs.logging("INFO", "Supplier Bill Number Updated");
			commonFunc.verifyAddTableSpecs(GateEntryPageLocators.VehicleNumber, GateEntryPageLocators.VehicleNumber,
					VariableHandler.getPONumber());
			logs.logging("INFO", "Vehicle Number Updated SuccessFully");
			commonFunc.verifyAddTableSpecs(GateEntryPageLocators.DriverName, GateEntryPageLocators.DriverName,
					commonFunc.getDataFromTestDataSheet("DriverName"));
			logs.logging("INFO", "Driver Name Updated SuccessFully");
			commonFunc.verifyAddTableSpecs(GateEntryPageLocators.ReferenceField, GateEntryPageLocators.ReferenceField,
					commonFunc.getDataFromTestDataSheet("ReferenceField"));
			logs.logging("INFO", "Reference Field Updated SuccessFully");
			flag = webDB.waitForElement(GateEntryPageLocators.OkButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(GateEntryPageLocators.OkButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(GateEntryPageLocators.DocNumber, ElementType.Xpath);
				logs.logging("INFO", "Gate Entry Document Created Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify Gate Entry Details
	 * 
	 * @author Durai-RMITS
	 */
	public void verifygateEntryDetailsPO() {
		flag = webDB.waitForElement(GateEntryPageLocators.DateInputPo, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.DateInputPo, ElementType.Xpath);
			logs.logging("INFO", "Date Field Is Clicked");
			flag = webDB.waitForElement(GateEntryPageLocators.SelectGateKeeperPo, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByValue(GateEntryPageLocators.SelectGateKeeperPo, "0001", ElementType.Xpath);
				logs.logging("INFO", "Gate Keeper Selected SuccessFully");
				flag = webDB.waitForElement(GateEntryPageLocators.DcNumberFieldPo, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GateEntryPageLocators.DcNumberFieldPo, ElementType.Xpath);
					flag = webDB.sendTextToAnElement(GateEntryPageLocators.DcNumberFieldPo,
							VariableHandler.getPONumber(), ElementType.Xpath);
					logs.logging("INFO", "DC Number SuccessFully Updated");
					flag = webDB.waitForElement(GateEntryPageLocators.SupplierBillNumberPo, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GateEntryPageLocators.SupplierBillNumberPo, ElementType.Xpath);
						webDB.sendTextToAnElement(GateEntryPageLocators.SupplierBillNumberPo,
								VariableHandler.getPONumber(), ElementType.Xpath);
						logs.logging("INFO", "Supplier Bill Number Updated");
						flag = webDB.waitForElement(GateEntryPageLocators.VehicleNumberPo, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GateEntryPageLocators.VehicleNumberPo, ElementType.Xpath);
							webDB.sendTextToAnElement(GateEntryPageLocators.VehicleNumberPo,
									VariableHandler.getPONumber(), ElementType.Xpath);
							logs.logging("INFO", "Vehicle Number Updated SuccessFully");
							flag = webDB.waitForElement(GateEntryPageLocators.DriverNamePo, ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(GateEntryPageLocators.DriverNamePo,
										commonFunc.getDataFromTestDataSheet("DriverName"), ElementType.Xpath);
								logs.logging("INFO", "Driver Name Updated SuccessFully");
								flag = webDB.waitForElement(GateEntryPageLocators.ReferenceFieldPo, ElementType.Xpath);
								if (flag) {
									webDB.sendTextToAnElement(GateEntryPageLocators.ReferenceFieldPo,
											commonFunc.getDataFromTestDataSheet("ReferenceField"), ElementType.Xpath);
									logs.logging("INFO", "Reference Field Updated SuccessFully");
									flag = webDB.waitForElement(GateEntryPageLocators.OkButtonPo, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(GateEntryPageLocators.OkButtonPo, ElementType.Xpath);
										logs.logging("INFO", "Gate Entry Document Created Successfully");
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(GateEntryPageLocators.DocNumber, ElementType.Xpath);

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
	 * This Methods Is Used To verify Gate Entry Details
	 * 
	 * @author Durai-RMITS
	 */
	public void verifygateEntryDetailsSso() {
		flag = webDB.waitForElement(GateEntryPageLocators.DateInputSso, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.DateInputSso, ElementType.Xpath);
			logs.logging("INFO", "Date Field Is Clicked");
			flag = webDB.waitForElement(GateEntryPageLocators.SelectGateKeeperSso, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByValue(GateEntryPageLocators.SelectGateKeeperSso, "0001",
						ElementType.Xpath);
				logs.logging("INFO", "Gate Keeper Selected SuccessFully");
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(GateEntryPageLocators.DcNumberFieldSso, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GateEntryPageLocators.DcNumberFieldSso, ElementType.Xpath);
					flag = webDB.sendTextToAnElement(GateEntryPageLocators.DcNumberFieldSso,
							VariableHandler.getOPODocNum(), ElementType.Xpath);
					logs.logging("INFO", "DC Number SuccessFully Updated");
					commonFunc.hardWait(500);
					flag = webDB.waitForElement(GateEntryPageLocators.SupplierBillNumberSso, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(500);
						webDB.clickAnElement(GateEntryPageLocators.SupplierBillNumberSso, ElementType.Xpath);
						webDB.sendTextToAnElement(GateEntryPageLocators.SupplierBillNumberSso,
								VariableHandler.getOPODocNum(), ElementType.Xpath);
						logs.logging("INFO", "Supplier Bill Number Updated");
						flag = webDB.waitForElement(GateEntryPageLocators.VehicleNumberSso, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(500);
							webDB.clickAnElement(GateEntryPageLocators.VehicleNumberSso, ElementType.Xpath);
							webDB.sendTextToAnElement(GateEntryPageLocators.VehicleNumberSso,
									VariableHandler.getOPODocNum(), ElementType.Xpath);
							logs.logging("INFO", "Vehicle Number Updated SuccessFully");
							flag = webDB.waitForElement(GateEntryPageLocators.DriverNameSso, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(500);
								webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameSso,
										commonFunc.getDataFromTestDataSheet("DriverName"), ElementType.Xpath);
								logs.logging("INFO", "Driver Name Updated SuccessFully");
								flag = webDB.waitForElement(GateEntryPageLocators.ReferenceFieldSso, ElementType.Xpath);
								if (flag) {
									commonFunc.hardWait(500);
									webDB.sendTextToAnElement(GateEntryPageLocators.ReferenceFieldSso,
											commonFunc.getDataFromTestDataSheet("ReferenceField"), ElementType.Xpath);
									logs.logging("INFO", "Reference Field Updated SuccessFully");
									flag = webDB.waitForElement(GateEntryPageLocators.OkButtonSso, ElementType.Xpath);
									if (flag) {
										webDB.clickAnElement(GateEntryPageLocators.OkButtonSso, ElementType.Xpath);
										logs.logging("INFO", "Gate Entry Document Created Successfully");
										webDB.switchToDefaultContent();
										commonFunc.hardWait(500);
										flag = webDB.waitForElement(GateEntryPageLocators.DocNumber, ElementType.Xpath);

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
	 * This Methods Is Used To verify Clicking Overall save and POst The Gate Entry
	 * Document
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyToOverAllSaveAndPostTheGateEntryDocument() {
		flag = webDB.waitForElement(GateEntryPageLocators.DocNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			String GateEntryNumber = webDB.getTextFromElement(GateEntryPageLocators.DocNumber, ElementType.Xpath);
			VariableHandler.setGateEntryNumber(GateEntryNumber);
			logs.logging("INFO", "Gate EntryNumber Is :" + VariableHandler.getGateEntryNumber());
			commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.GateKeeperSso, "VIMAL");
			commonFunc.verifyAddTableSpecs(GateEntryPageLocators.DriverNameInput, GateEntryPageLocators.DriverNameInput,
					"Vimal");
			commonFunc.clickShowMoreFindTable();
			commonFunc.verifyAddTableSpecs(GateEntryPageLocators.EwayBillNumber, GateEntryPageLocators.EwayBillNumber,
					commonFunc.getDataFromTestDataSheet("EwayBillNumber"));
			logs.logging("INFO", "E-wayBill NUMBER Entered Successfully");
			flag = webDB.waitForElement(GateEntryPageLocators.OverAllSaveButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(GateEntryPageLocators.OverAllSaveButton, ElementType.Xpath);
				webDB.clickAnElement(GateEntryPageLocators.OverAllSaveButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 5);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
				flag = webDB.waitForElement(GateEntryPageLocators.PostButton, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(GateEntryPageLocators.PostButton, ElementType.Xpath);
					webDB.clickAnElement(GateEntryPageLocators.PostButton, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (!VariableHandler.getApprovalFlag()) {
						approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
						commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
								VariableHandler.getGateEntryNumber(), webDB.getDataFromProperties("HodEmpId"),
								commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
					}
					logs.logging("INFO", "Gate Entry Document Posted Successfully");
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Open Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfOpenPurchaseOrderGateEntry() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateOpenPoOrderPage();
		verifyCreationOfOPO();
		verifyAddingFillDetailsOfOPO(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("Opo_SupId"));
		verifyClickingOpoCreate();
		verifyCreatingItemForOpo(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("SO_Special_Disc"),
				commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		verifyClickingOpoPost();
		waitingForApprovalOpo();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getOPODocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Open Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfOPO() {
		flag = webDB.waitForElement(SsoPageLocators.OPORadioBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.OPORadioBtn, ElementType.Xpath);
			logs.logging("INFO", "OPO Radio Button is Clicked");

			flag = webDB.waitForElement(SsoPageLocators.OPOAddBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.OPOAddBtn, ElementType.Xpath);
				logs.logging("INFO", "Add Button is Clicked");
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Adding Fill Details
	 * 
	 * @author Durai-RMITS
	 * @param OPOLocation
	 * @param OPOSupplier
	 */
	public void verifyAddingFillDetailsOfOPO(String OPOLocation, String OPOSupplier) {
		VerifyClickingFrame();
		flag = webDB.waitForElement(SsoPageLocators.OPOLocLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SsoPageLocators.OPOLocLov, OPOLocation);
			logs.logging("INFO", "LOcation Entered is:" + OPOLocation);
			VerifyClickingFrame();
			flag = webDB.waitForElement(SsoPageLocators.OPOSupplierLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SsoPageLocators.OPOSupplierLov, OPOSupplier);
				logs.logging("INFO", "Supplier Entered is:" + OPOSupplier);

			}
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of Open Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingOpoCreate() {
		VerifyClickingFrame();
		flag = webDB.waitForElement(SsoPageLocators.OPOCreateBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.OPOCreateBtn, ElementType.Xpath);
			logs.logging("INFO", "OPO Doc Is Created");
		}
	}

	/**
	 * This Methods Is Used To Verify Creation Of Open Purchase Order
	 * 
	 * @author Durai-RMITS
	 * @param Item
	 * @param Cost
	 * @param Disc
	 * @param HSN
	 */
	public void verifyCreatingItemForOpo(String Item, String Cost, String Disc, String HSN) {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(SsoPageLocators.OPODocNum, ElementType.Xpath);
		if (flag) {
			String OPODocNum = webDB.getTextFromElement(SsoPageLocators.OPODocNum, ElementType.Xpath);
			VariableHandler.setOPODocNum(OPODocNum);
			logs.logging("INFO", "OPO Doc num is:" + VariableHandler.getOPODocNum());
			flag = webDB.waitForElement(SsoPageLocators.OPOLineAdd, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.OPOLineAdd, ElementType.Xpath);
				logs.logging("INFO", "Line Wise Add Button is Clicked");
				flag = webDB.waitForElement(SsoPageLocators.OPOLineItemField, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(SsoPageLocators.OPOLineItemField, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(SsoPageLocators.OPOLineItemLov, Item);
					logs.logging("INFO", "Item is Entered:" + Item);

					flag = webDB.waitForElement(SsoPageLocators.OPOLineCostFiled, ElementType.Xpath);
					if (flag) {
						webDB.doubleClickAnElement(SsoPageLocators.OPOLineCostFiled, ElementType.Xpath);
						webDB.clearText(SsoPageLocators.OPOLineCostInput, ElementType.Xpath);
						webDB.sendTextToAnElement(SsoPageLocators.OPOLineCostInput, Cost, ElementType.Xpath);
						logs.logging("INFO", "Cost is Entered:" + Cost);

						flag = webDB.waitForElement(SsoPageLocators.OPOLineDiscField, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SsoPageLocators.OPOLineDiscField, ElementType.Xpath);
							webDB.clearText(SsoPageLocators.OPOLineDiscInput, ElementType.Xpath);
							webDB.sendTextToAnElement(SsoPageLocators.OPOLineDiscInput, Disc, ElementType.Xpath);
							logs.logging("INFO", "Discount is Entered:" + Disc);

							flag = webDB.waitForElement(SsoPageLocators.OPOLineHsnField, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SsoPageLocators.OPOLineHsnField, ElementType.Xpath);
								commonFunc.searchAndSelectValueInFilters(SsoPageLocators.OPOLineHsnLov, HSN);
								logs.logging("INFO", "Hsn Code is Entered:" + HSN);

								flag = webDB.waitForElement(SsoPageLocators.OPOLineSave, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(SsoPageLocators.OPOLineSave, ElementType.Xpath);
									logs.logging("INFO", "Line is Saved");
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Clicking Open Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingOpoPost() {
		commonFunc.hardWait(4000);
		flag = webDB.waitForElement(SsoPageLocators.OPOPost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.OPOPost, ElementType.Xpath);
			logs.logging("INFO", "OPO Doc Is Posted");
		}
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
			flag = webDB.waitForElement(RfqLocators.PRworkflowOK, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.PRworkflowOK, ElementType.Xpath);
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
	 * This Methods Is Used To Verify Forward Screen is Opening
	 * 
	 * @author Durai-RMITS
	 */
	public void forwardApprovalScreen() {
		commonFunc.hardWait(1000);
		commonFunc.validCommonLogin(System.getProperty("AshokUsername"), System.getProperty("AshokPassword"));
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
					flag = webDB.waitForElement(ApprovalPageLocators.SearchTableResult, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Search Go Button Is Clicked");
					}
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
	 * This Methods Is Used To Verify Adding Inward Details
	 * 
	 * @author Durai-RMITS
	 * @param Location
	 * @param Type
	 * @param Name
	 * @param Driver
	 */
	public void verifyAddingInwardDetails(String Location, String Type, String Name, String Driver) {
		flag = webDB.waitForElement(GateEntryPageLocators.GateEntryAdd, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.GateEntryAdd, ElementType.Xpath);
			logs.logging("INFO", "Add Button Is Clicked");
			VerifyClickingGateDirectFrame();
			flag = webDB.waitForElement(GateEntryPageLocators.GateEntryLocLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.GateEntryLocLov, Location);
				logs.logging("INFO", "Entered Location Is:" + Location);
				VerifyClickingGateDirectFrame();
				flag = webDB.waitForElement(GateEntryPageLocators.GateEntryType, ElementType.Xpath);
				if (flag) {
					webDB.selectDropDownByValue(GateEntryPageLocators.GateEntryType, Type, ElementType.Xpath);
					logs.logging("INFO", "Type Is Selected:" + Type);
					flag = webDB.waitForElement(GateEntryPageLocators.GateKeeperDirectLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.GateKeeperDirectLov, Name);
						logs.logging("INFO", Driver);
						VerifyClickingGateDirectFrame();
						flag = webDB.waitForElement(GateEntryPageLocators.DirectDriverNameGate, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GateEntryPageLocators.DirectDriverNameGate, ElementType.Xpath);
							webDB.sendTextToAnElement(GateEntryPageLocators.DirectDriverNameGate, Driver,
									ElementType.Xpath);
							logs.logging("INFO", "Driver Name is Entered:" + Driver);
							flag = webDB.waitForElement(GateEntryPageLocators.DirectDriverNameGate, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GateEntryPageLocators.DirectDriverNameGate, ElementType.Xpath);
								logs.logging("INFO", "Add Button Is Clicked");
								flag = webDB.waitForElement(GateEntryPageLocators.DirectGateCreate, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(GateEntryPageLocators.DirectGateCreate, ElementType.Xpath);
									logs.logging("INFO", "Document is Created");
									webDB.switchToDefaultContent();
								}
							}

						}
					}
				}
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Approve The Document
	 * 
	 * @author Durai-RMITS
	 * @param Supplier
	 * @param BillNo
	 */
	public void verifyAddingInwardLineSupplier(String Supplier, String BillNo) {
		String ItemSeqNo = RandomStringUtils.randomNumeric(6);
		if (ItemSeqNo.equals("000")) {
			ItemSeqNo = "10";
		}
		String EWAYNO = BillNo + ItemSeqNo;
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(GateEntryPageLocators.DirectGateEWayNo, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.DirectGateEWayNo, ElementType.Xpath);
			webDB.sendTextToAnElement(GateEntryPageLocators.DirectGateEWayNo, EWAYNO, ElementType.Xpath);
			logs.logging("INFO", "EWay Bill No Is Entered:" + EWAYNO);

			flag = webDB.waitForElement(GateEntryPageLocators.DirectGateUnloadBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(GateEntryPageLocators.DirectGateUnloadBtn, ElementType.Xpath);
				webDB.clickAnElement(GateEntryPageLocators.DirectGateUnloadBtn, ElementType.Xpath);
				logs.logging("INFO", "Radio Button Is Clicked");
				flag = webDB.waitForElement(GateEntryPageLocators.DirectGateAddLine, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GateEntryPageLocators.DirectGateAddLine, ElementType.Xpath);
					logs.logging("INFO", "Add Button is Clicked");
					flag = webDB.waitForElement(GateEntryPageLocators.GatePagePartyTable(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GateEntryPageLocators.GatePagePartyTable(), ElementType.Xpath);
						logs.logging("INFO", "Party Field Is Clicked");
						flag = webDB.waitForElement(GateEntryPageLocators.DirectGatePartyLov, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.DirectGatePartyLov,
									Supplier);
							logs.logging("INFO", "Party Lov Is Clicked");
							flag = webDB.waitForElement(GateEntryPageLocators.DirectGateLineSave, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GateEntryPageLocators.DirectGateLineSave, ElementType.Xpath);
								logs.logging("INFO", "Line Is Saved");
								commonFunc.hardWait(4000);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * This Methods Is Used To Verify Adding Inward Line Items
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyAddingInwardLineItem() {

		String[][] GATEITEM = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		GATEITEM = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("GATEITEM"));
		for (int i = 1; i < GATEITEM.length; i++) {
			String ITEM = GATEITEM[i][0];
			String QTY = GATEITEM[i][1];
			String COST = GATEITEM[i][2];
			if (!ITEM.equals(""))

				flag = webDB.waitForElement(GateEntryPageLocators.DirectGateItemLineAdd, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GateEntryPageLocators.DirectGateItemLineAdd, ElementType.Xpath);
				logs.logging("INFO", "Item Add Button Is Clicked");
				flag = webDB.waitForElement(GateEntryPageLocators.ItemName(i), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GateEntryPageLocators.ItemName(i), ElementType.Xpath);
					logs.logging("INFO", "Item Field Is Clicked");
					flag = webDB.waitForElement(GateEntryPageLocators.DirectGateItemLineLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.DirectGateItemLineLov, ITEM);
						logs.logging("INFO", "Entered Item Is:" + ITEM);
						flag = webDB.waitForElement(GateEntryPageLocators.QtyField(i), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(GateEntryPageLocators.QtyField(i), ElementType.Xpath);
							webDB.clearText(GateEntryPageLocators.DirectGateQtyInput, ElementType.Xpath);
							webDB.sendTextToAnElement(GateEntryPageLocators.DirectGateQtyInput, QTY, ElementType.Xpath);
							logs.logging("INFO", "Quantity Is Entered:" + QTY);
							flag = webDB.waitForElement(GateEntryPageLocators.CostField(i), ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(GateEntryPageLocators.CostField(i), ElementType.Xpath);
								webDB.clearText(GateEntryPageLocators.DirectGateCostInput, ElementType.Xpath);
								webDB.sendTextToAnElement(GateEntryPageLocators.DirectGateCostInput, COST,
										ElementType.Xpath);
								logs.logging("INFO", "Cost IS Entered:" + COST);

							}
						}
					}
				}
			}
		}
		flag = webDB.waitForElement(GateEntryPageLocators.DirectGateItemLineSave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.DirectGateItemLineSave, ElementType.Xpath);
			logs.logging("INFO", "Line Item Is Saved");
		}
	}

	/**
	 * This Methods Is Used To Verify Checking Overall Save
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingOverallSave() {
		flag = webDB.waitForElement(GateEntryPageLocators.DirectGateOverallSave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.DirectGateOverallSave, ElementType.Xpath);
			logs.logging("INFO", "Overall Save Button Is Clicked");

		}
	}

	/**
	 * This Methods Is Used To Verify Clicking POst
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingPost() {
		flag = webDB.waitForElement(GateEntryPageLocators.DirectGatePost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.DirectGatePost, ElementType.Xpath);
			logs.logging("INFO", "Post Button Is Clicked");

		}
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Durai-RMITS
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual DataBase Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual DataBase Value : " + Actual + " And Expected Values : " + Expected);
		} else {
			logs.logging("INFO",
					"Mismatch With Actual DataBase Value : " + Actual + " And Expected Values " + Expected);
		}
		assertTrue(flag);
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void gateEntryUnitHeaderValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.GATEdetails(VariableHandler.getGATEDocNum(),
						commonFunc.getDataFromTestDataSheet("GATEDbLocationColumnName")),
				commonFunc.getDataFromTestDataSheet("L3"));
		logs.logging("INFO", "Header Location Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Supplier
	 * 
	 * @author Durai-RMITS
	 */
	public void gateEntrySupplierHeaderValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.GATEdetails(VariableHandler.getGATEDocNum(),
						commonFunc.getDataFromTestDataSheet("GATEDbSupplierColumnName")),
				commonFunc.getDataFromTestDataSheet("DomSupplier_ID1"));
		logs.logging("INFO", "Header Supplier Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void gateEntryItemHeaderValidation() {
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("DirectGrnGenUD"));
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String ITEM_NAME = itemmultiple[i][1];
			String QUANTITY = itemmultiple[i][5];
			String UNIT_COST = itemmultiple[i][6];
			if (!ITEM_NAME.equals("")) {
				String SeqNumber = Integer.toString(i - 21);
				System.out.println("SeqNumber:" + SeqNumber);
				verifyAssertionBothValues(dbCommonFunctions.GateQuery1(VariableHandler.getGATEDocNum(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("GATEDbItemColumnName")), ITEM_NAME);
				logs.logging("INFO", "Header Items Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.GateQuery1(VariableHandler.getGATEDocNum(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("GATEDbItemQtyColumnName")), QUANTITY);
				logs.logging("INFO", "Header Quantity Details Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.GateQuery1(VariableHandler.getGATEDocNum(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("GATEDbItemCostColumnName")), UNIT_COST);
				logs.logging("INFO", "Header Unit Cost Details Matched With DataBase Values");
			}
		}
	}

	/**
	 * This Method Is Used To PO Filter All Field Working
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreationOfGENFilterAllFieldWorking() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"SelvaUsername");
		commonFunc.navigateToGateEntryDocument();
//		verifyDateAfterAndBefore();
		verifyAmendFilterSearchSOB(GateEntryPageLocators.Unit, commonFunc.getDataFromTestDataSheet("L3"), "Unit",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(GateEntryPageLocators.LocId, commonFunc.getDataFromTestDataSheet("L49ID"), "Loc.ID",
				commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(GateEntryPageLocators.GENNo, commonFunc.getDataFromTestDataSheet("GENNumber"),
				"GEN No.", commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
//		verifyAmendFilterSearchSOB(GateEntryPageLocators.SupplierNameLovButton,
//				commonFunc.getDataFromTestDataSheet("ARUN PACKS (P) LTD"), "Party ID",
//				commonFunc.getDataFromTestDataSheet("LineSearchType"),
//				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(GateEntryPageLocators.GateKeeperLovButton,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), "Gate Keeper Name",
				commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(GateEntryPageLocators.ItemId, commonFunc.getDataFromTestDataSheet("GENItem"),
				"Item ID", commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("LovButton"));
		verifyAmendFilterSearchSOB(GateEntryPageLocators.ItemDesc, commonFunc.getDataFromTestDataSheet("GENItem"),
				"Item Desc.", commonFunc.getDataFromTestDataSheet("LineSearchType"),
				commonFunc.getDataFromTestDataSheet("SendTextInput"));
		verifyAmendFilterSearchSOB(GateEntryPageLocators.GRN_Status, commonFunc.getDataFromTestDataSheet("GENStatus"),
				"Status", commonFunc.getDataFromTestDataSheet("HeaderSearchType"),
				commonFunc.getDataFromTestDataSheet("SelectType"));
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
	public void verifyAmendFilterSearchSOB(String LovButton, String Value, String i, String Type, String selectflag) {
		webDB.scrollToAnElement(LovButton, ElementType.Xpath);
		if (selectflag.equals(commonFunc.getDataFromTestDataSheet("LovButton"))) {
			commonFunc.searchAndSelectValueInFilters(LovButton, Value);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SelectType"))) {
			webDB.selectDropDownByText(LovButton, Value, ElementType.Xpath);
		} else if (selectflag.equals(commonFunc.getDataFromTestDataSheet("SendTextInput"))) {
			webDB.sendTextToAnElement(LovButton, Value, ElementType.Xpath);
		}
		if (Type.equals(commonFunc.getDataFromTestDataSheet("HeaderSearchType"))) {
			clickSearchButton();
			commonFunc.hardWait(3000);
			verifyAmendTableValue(i, Value);
		} else if (Type.equals(commonFunc.getDataFromTestDataSheet("LineSearchType"))) {
			clickLineSearchButton();
			commonFunc.hardWait(3000);
			verifyLineAmendTableValue(i, Value);
		}
		clearSearchFilter();
	}

	/**
	 * Verify Date From And To
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDateAfterAndBefore() {
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNDtFrom, ElementType.Xpath);
		webDB.sendTextToAnElement(DirectGrnPageLocators.GRNDtFrom, commonFunc.getDataFromTestDataSheet("EffFrom"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueAfterDate(5, commonFunc.getDataFromTestDataSheet("EffFrom"));
		clearSearchFilter();
		flag = webDB.waitForElement(DirectGrnPageLocators.GRNDtTo, ElementType.Xpath);
		webDB.sendTextToAnElement(DirectGrnPageLocators.GRNDtTo, commonFunc.getDataFromTestDataSheet("EffTo"),
				ElementType.Xpath);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyAmendTableValueBeforeDate(5, commonFunc.getDataFromTestDataSheet("EffTo"));
		clearSearchFilter();
//		flag = webDB.waitForElement(PurchaseOrderPageLocators.RequestDtFrom, ElementType.Xpath);
//		webDB.sendTextToAnElement(PurchaseOrderPageLocators.RequestDtFrom,
//				commonFunc.getDataFromTestDataSheet("EffFrom"), ElementType.Xpath);
//		clickLineSearchButton();
//		commonFunc.hardWait(3000);
//		verifyLineAmendTableValueAfterDate(23, commonFunc.getDataFromTestDataSheet("EffFrom"));
//		clearSearchFilter();
//		flag = webDB.waitForElement(PurchaseOrderPageLocators.RequestDtTo, ElementType.Xpath);
//		webDB.sendTextToAnElement(PurchaseOrderPageLocators.RequestDtTo, commonFunc.getDataFromTestDataSheet("EffTo"),
//				ElementType.Xpath);
//		clickLineSearchButton();
//		commonFunc.hardWait(3000);
//		verifyLineAmendTableValueBeforeDate(23, commonFunc.getDataFromTestDataSheet("EffTo"));
//		clearSearchFilter();
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(GateEntryPageLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header CheckBox Is Displayed");
			webDB.clickAnElement(GateEntryPageLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(GateEntryPageLocators.GENSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GateEntryPageLocators.GENSearchBtn, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GateEntryPageLocators.AmendFindTable, ElementType.Xpath);
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
		flag = webDB.waitForElement(GateEntryPageLocators.LineCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line CheckBox Is Displayed");
			webDB.clickAnElement(GateEntryPageLocators.LineCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(GateEntryPageLocators.GENSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GateEntryPageLocators.GENSearchBtn, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GateEntryPageLocators.AmendLineFindTable, ElementType.Xpath);
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
				.findElements(By.xpath(GateEntryPageLocators.GateAmendFindTableData(i)));
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
	public void verifyLineAmendTableValue(String i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(GateEntryPageLocators.GateAmendLineFindTableData(i)));
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
		System.out.println("Test2");
		flag = webDB.waitForElement(DirectGrnPageLocators.ClearButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(DirectGrnPageLocators.ClearButton, ElementType.Xpath);
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
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.AmendFindTableData(i)));
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
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.AmendFindTableData(i)));
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
	public void verifyLineAmendTableValueAfterDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.AmendLineFindTableData(i)));
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
	public void verifyLineAmendTableValueBeforeDate(int i, String Value) {
		List<WebElement> elements = webDB.getDriver()
				.findElements(By.xpath(DirectGrnPageLocators.AmendLineFindTableData(i)));
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
	 * This Methods Is Used To verify Create Gate Entry From Pending Purchase Order
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyToCreateGateEntryFromPendingPoNumber() {
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		if (flag)
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingPoButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingPoButton, ElementType.Xpath);
			logs.logging("INFO", "PendingPOButtonLov Shown");
			webDB.switchToFrame(GateEntryPageLocators.PendingPOGIframe, ElementType.Xpath);
			commonFunc.checkShowMyRecordAndUnselectAll(GateEntryPageLocators.PendingPOShowMyRecordBtn,
					GateEntryPageLocators.PendingPOShowMyRecordHideBtn,
					GateEntryPageLocators.GateEntryPendingPoSelectAll);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.expandFindTable();
			commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.ClickPendingPoButtonLov,
					VariableHandler.getPONumber());
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElement(GateEntryPageLocators.PendingPOGIframe, ElementType.Xpath);
			webDB.switchToFrame(GateEntryPageLocators.PendingPOGIframe, ElementType.Xpath);
			logs.logging("INFO", "PO Number Shown");
			webDB.waitForElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Document Search And Selected Successfully");
				verifyClickSelectAllButton();
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Create Gate Entry Button shown");
				webDB.waitForElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.moveToElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);
					webDB.clickAnElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.checkRefreshCellsDisappear();
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.CancelBtn, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.OkayButton, ElementType.Xpath);
					}
					flag = webDB.waitForElement(GateEntryPageLocators.SelectGateKeeper, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Select All Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickSelectAllButton() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkRefreshCellsDisappear();
		webDB.moveToElement(GateEntryPageLocators.GateEntryPendingPoSelectAll, ElementType.Xpath);
		webDB.scrollToAnElement(GateEntryPageLocators.GateEntryPendingPoSelectAll, ElementType.Xpath);
		List<WebElement> CheckBox = webDB.getDriver()
				.findElements(By.xpath(GateEntryPageLocators.GateEntryPendingPoSelectAll));
		for (WebElement element : CheckBox) {
			try {
				if (element.isDisplayed()) {
					Actions action = new Actions(webDB.getDriver());
					action.moveToElement(element).build().perform();
					if (!element.isSelected()) {
						element.click();
						flag = true;
						break;
					} else {
						element.click();
						verifyClickSelectAllButton();
						flag = true;
						break;
					}
				}
			} catch (Exception e) {
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify Status of Gate Entry Number
	 * 
	 * @author Deepan-GWL
	 * @param MRPNo
	 * @param ExpectedStatus
	 */
	public void searchAndVerifyStatusOfGENo(String GEONo, String ExpectedStatus) {
		commonFunc.clearFiltersInFindPage();
//		commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.FindGateEntryNoLovBtn, GEONo);
		flag = webDB.waitForElement(GateEntryPageLocators.HeaderLabelButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.HeaderLabelButton, ElementType.Xpath);
			flag = webDB.waitForElement(GateEntryPageLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GateEntryPageLocators.SearchButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.waitForElement(GateEntryPageLocators.GateEntryHeaderSearchBar, ElementType.Xpath);
				webDB.sendTextToAnElement(GateEntryPageLocators.GateEntryHeaderSearchBar, GEONo, ElementType.Xpath);
				webDB.waitForElement(GateEntryPageLocators.GateEntryHeaderSearchGoBtn, ElementType.Xpath);
				webDB.moveToElement(GateEntryPageLocators.GateEntryHeaderSearchGoBtn, ElementType.Xpath);
				webDB.clickAnElement(GateEntryPageLocators.GateEntryHeaderSearchGoBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GateEntryPageLocators.FindGateEntryNumber(GEONo), ElementType.Xpath);
				if (flag) {
					String DisplayedStatus = webDB.getTextFromElement(GateEntryPageLocators.FindGateEntryNumber(GEONo),
							ElementType.Xpath);
					logs.logging("INFO", "Displayed Status: " + DisplayedStatus);
					flag = DisplayedStatus.equalsIgnoreCase(ExpectedStatus);
					if (flag) {
						logs.logging("INFO", "Production Order Status Verified");
					}
				}
			}
		}
		assertTrue(flag);
	}

	private void VerifyClickingFrame() {
		flag = webDB.waitForElement(GateEntryPageLocators.OpoAddFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GateEntryPageLocators.OpoAddFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame");
		}

	}

	private void VerifyClickingGateDirectFrame() {
		flag = webDB.waitForElement(GateEntryPageLocators.GateDirectFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(GateEntryPageLocators.GateDirectFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame");
		}
	}

	/**
	 * This Methods Is Used To verify Create Gate Entry From Pending Purchase Order
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyToCreateGateEntryFromPendingSTNumber() {
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		if (flag)
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		flag = webDB.waitForElement(GateEntryPageLocators.PendingStockTransfer, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.PendingStockTransfer, ElementType.Xpath);
			logs.logging("INFO", "PendingPOButtonLov Shown");
			webDB.switchToFrame(GateEntryPageLocators.IframePendingST, ElementType.Xpath);
			commonFunc.expandFindTable();
			flag = webDB.waitForElement(GateEntryPageLocators.InvoiceNumberLovBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.InvoiceNumberLovBtn,
						VariableHandler.getSalesInvoiceNumber());
				logs.logging("INFO", "PendingPOButtonLov Clicked");
				webDB.switchToDefaultContent();
				webDB.switchToFrame(GateEntryPageLocators.IframePendingST, ElementType.Xpath);
				logs.logging("INFO", "PO Number Shown");
				webDB.waitForElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
					logs.logging("INFO", "Document Search And Selected Successfully");
					verifyClickSelectAllButtonForSt();
					logs.logging("INFO", "Create Gate Entry Button shown");
					commonFunc.hardWait(2000);
					webDB.waitForElement(GateEntryPageLocators.PendingMoveGE, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GateEntryPageLocators.PendingMoveGE, ElementType.Xpath);

					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Select All Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickSelectAllButtonForSt() {
		commonFunc.hardWait(7000);
//		flag = webDB.waitForElement(GateEntryPageLocators.GateEntryPendingPoSelectAll, ElementType.Xpath);
//		if (flag) {
		webDB.moveToElement(GateEntryPageLocators.SelectAll, ElementType.Xpath);
		List<WebElement> CheckBox = webDB.getDriver().findElements(By.xpath(GateEntryPageLocators.SelectAll));
		for (WebElement element : CheckBox) {
			try {
				if (element.isDisplayed()) {
					if (!element.isSelected()) {
						element.click();
						flag = true;
						break;
					} else {
						element.click();
						Thread.sleep(10000);
						verifyClickSelectAllButtonForSt();
						flag = true;
						break;
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
//			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify Create Gate Entry From Pending Purchase Order
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyToCreateGateEntryFromPendingSSODocNumber() {
//		VariableHandler.setSSODocNumber("SSO3/0087/25-26");
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		if (flag)
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		flag = webDB.waitForElement(GateEntryPageLocators.PendingSupplierSchedule, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.PendingSupplierSchedule, ElementType.Xpath);
			logs.logging("INFO", "PendingPOButtonLov Shown");
			webDB.switchToFrame(GateEntryPageLocators.IFrameSSO, ElementType.Xpath);
			flag = webDB.waitForElement(GateEntryPageLocators.SSODocumentLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.SSODocumentLov,
						VariableHandler.getSSODocNumber());
				logs.logging("INFO", "PendingPOButtonLov Clicked");
				webDB.switchToDefaultContent();
				webDB.switchToFrame(GateEntryPageLocators.IFrameSSO, ElementType.Xpath);
				logs.logging("INFO", "PO Number Shown");
				webDB.waitForElement(GateEntryPageLocators.SSOSearchButton, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(GateEntryPageLocators.SSOSearchButton, ElementType.Xpath);
					logs.logging("INFO", "Document Search And Selected Successfully");
					verifyClickSelectAllButtonForSSO();
					logs.logging("INFO", "Create Gate Entry Button shown");
					commonFunc.hardWait(2000);
					webDB.waitForElement(GateEntryPageLocators.CreateGEButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(GateEntryPageLocators.CreateGEButton, ElementType.Xpath);

					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Select All Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickSelectAllButtonForSSO() {
		commonFunc.hardWait(7000);
//		flag = webDB.waitForElement(GateEntryPageLocators.GateEntryPendingPoSelectAll, ElementType.Xpath);
//		if (flag) {
		webDB.moveToElement(GateEntryPageLocators.GateEntryPendingPoSelectAll, ElementType.Xpath);
		List<WebElement> CheckBox = webDB.getDriver()
				.findElements(By.xpath(GateEntryPageLocators.GateEntryPendingPoSelectAll));
		for (WebElement element : CheckBox) {
			try {
				if (element.isDisplayed()) {
					if (!element.isSelected()) {
						element.click();
						flag = true;
						break;
					} else {
						element.click();
						Thread.sleep(10000);
						verifyClickSelectAllButtonForSt();
						flag = true;
						break;
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
//			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To verify Gate Entry Details Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifygateEntryDetailsPoWithSSO() {
		flag = webDB.waitForElement(GateEntryPageLocators.SelectGateKeeper, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(GateEntryPageLocators.DcNumberField, ElementType.Xpath);
			flag = webDB.selectDropDownByValue(GateEntryPageLocators.SelectGateKeeper, "0001", ElementType.Xpath);
			logs.logging("INFO", "Gate Keeper Selected SuccessFully");
			flag = webDB.waitForElement(GateEntryPageLocators.DcNumberField, ElementType.Xpath);
			if (flag) {
				flag = webDB.sendTextToAnElement(GateEntryPageLocators.DcNumberField, VariableHandler.getSSODocNumber(),
						ElementType.Xpath);
				logs.logging("INFO", "DC Number SuccessFully Updated");
				commonFunc.hardWait(500);
				flag = webDB.waitForElement(GateEntryPageLocators.SupplierBillNumber, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(500);
					webDB.sendTextToAnElement(GateEntryPageLocators.SupplierBillNumber,
							VariableHandler.getSSODocNumber(), ElementType.Xpath);
					logs.logging("INFO", "Supplier Bill Number Updated");
					flag = webDB.waitForElement(GateEntryPageLocators.VehicleNumber, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(500);
						webDB.sendTextToAnElement(GateEntryPageLocators.VehicleNumber,
								VariableHandler.getSSODocNumber(), ElementType.Xpath);
						logs.logging("INFO", "Vehicle Number Updated SuccessFully");
						flag = webDB.waitForElement(GateEntryPageLocators.DriverName, ElementType.Xpath);
						if (flag) {
							commonFunc.hardWait(500);
							webDB.sendTextToAnElement(GateEntryPageLocators.DriverName,
									commonFunc.getDataFromTestDataSheet("DriverName"), ElementType.Xpath);
							logs.logging("INFO", "Driver Name Updated SuccessFully");
							flag = webDB.waitForElement(GateEntryPageLocators.ReferenceField, ElementType.Xpath);
							if (flag) {
								commonFunc.hardWait(500);
								webDB.sendTextToAnElement(GateEntryPageLocators.ReferenceField,
										commonFunc.getDataFromTestDataSheet("ReferenceField"), ElementType.Xpath);
								logs.logging("INFO", "Reference Field Updated SuccessFully");
								flag = webDB.waitForElement(GateEntryPageLocators.OkButton, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(GateEntryPageLocators.OkButton, ElementType.Xpath);
									logs.logging("INFO", "Gate Entry Document Created Successfully");
									webDB.switchToDefaultContent();
									commonFunc.hardWait(500);
									flag = webDB.waitForElement(GateEntryPageLocators.DocNumber, ElementType.Xpath);

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
	 * This Methods Is Used To verify Create Gate Entry From Pending Purchase Order
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyToCreateGateEntryFromPendingPoNumberWithQty() {
//		VariableHandler.setPONumber("POGL3/00045/25-26");
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		if (flag)
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingButton, ElementType.Xpath);
		flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingPoButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.ClickPendingPoButton, ElementType.Xpath);
			logs.logging("INFO", "PendingPOButtonLov Shown");
			webDB.switchToFrame(GateEntryPageLocators.PendingPOGIframe, ElementType.Xpath);
			flag = webDB.waitForElement(GateEntryPageLocators.ClickPendingPoButtonLov, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(GateEntryPageLocators.ClickPendingPoButtonLov, ElementType.Xpath);
				logs.logging("INFO", "PendingPOButtonLov Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(GateEntryPageLocators.SearchAndSelectValue, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.SearchAndSelectValue,
							VariableHandler.getPONumber());
					webDB.switchToFrame(GateEntryPageLocators.PendingPOGIframe, ElementType.Xpath);
					logs.logging("INFO", "PO Number Shown");
					webDB.waitForElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
					if (flag) {
						flag = webDB.clickAnElement(GateEntryPageLocators.GateEntryPoSearchButton, ElementType.Xpath);
						webDB.waitUntilElementToNotBeStale(GateEntryPageLocators.GateEntryPendingPoSelectAll,
								ElementType.Xpath);
						logs.logging("INFO", "Document Search And Selected Successfully");
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.checkRefreshCellsDisappear();
						webDB.clearText(GateEntryPageLocators.QtyValue, ElementType.Xpath);
						webDB.sendTextToAnElement(GateEntryPageLocators.QtyValue,
								commonFunc.getDataFromTestDataSheet("QuantityExcess"), ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(GateEntryPageLocators.FirstCheckBox, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
//						verifyClickSelectAllButton();
						logs.logging("INFO", "Create Gate Entry Button shown");
						webDB.waitForElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);
							flag = webDB.clickAnElement(GateEntryPageLocators.CreateGeFromPO, ElementType.Xpath);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify Status of Gate Entry Number
	 * 
	 * @author Deepan-GWL
	 * @param MRPNo
	 * @param ExpectedStatus
	 */
	public void openGateEntryDocNo(String GEONo) {
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.FindGateEntryNoLovBtn, GEONo);
		flag = webDB.waitForElement(GateEntryPageLocators.HeaderLabelButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.HeaderLabelButton, ElementType.Xpath);
			flag = webDB.waitForElement(GateEntryPageLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GateEntryPageLocators.SearchButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(GateEntryPageLocators.GateEntryDocNo(GEONo), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GateEntryPageLocators.GateEntryDocNo(GEONo), ElementType.Xpath);
					flag = webDB.waitForElement(GateEntryPageLocators.GENO, ElementType.Xpath);

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Correction Gate Entry Docs
	 * 
	 * @author Deepan-GWL
	 * @param Reason
	 */
	public void verifyCorrectionGateEntryDoc(String Reason) {
		webDB.waitForElement(GateEntryPageLocators.GECorrection, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GateEntryPageLocators.GECorrection, ElementType.Xpath);
			webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(GateEntryPageLocators.CancelButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(GateEntryPageLocators.CancelButton, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(GateEntryPageLocators.ReasonLovButton, Reason);
				flag = webDB.waitForElement(GateEntryPageLocators.ReasonOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(GateEntryPageLocators.ReasonOkButton, ElementType.Xpath);
					flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				}

			}
		}
	}
}
