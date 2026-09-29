package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 28, 2025
 */
import static org.testng.Assert.assertTrue;
import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.financials.PurchaseBillsFunctions;
import commonlocators.supplychainmanagement.MaterialRequestLocators;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.testng.Assert;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import net.bytebuddy.implementation.FieldAccessor.FieldNameExtractor.ForBeanProperty;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class MaterialRequestFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	public PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	public ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static PurchaseBillsFunctions purchaseBillsFunc = new PurchaseBillsFunctions();

	/**
	 * This method used to store Material Request Number And Validate Status With
	 * Production Order Number
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 * @param ExpectedStatus
	 */
	public void storeMRNoAndCheckStatusOfMRWithProdNo(String ProdNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.FindProdNoLovBtn, ProdNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialRequestLocators.Find_Status_TableData(ProdNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MRP No: " + ProdNo + " Displayed");
				String Status = webDB.getTextFromElement(MaterialRequestLocators.Find_Status_TableData(ProdNo),
						ElementType.Xpath);
				logs.logging("INFO", "Production Number: '" + ProdNo + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpectedStatus);
				if (flag) {
					logs.logging("INFO", "Search Button clicked and MRP No: " + ProdNo + " Displayed");
					String MRNo = webDB.getTextFromElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo),
							ElementType.Xpath);
					VariableHandler.setMaterialRequestNo(MRNo);
					logs.logging("INFO", "Material Number: '" + VariableHandler.getMaterialRequestNo() + "'");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequest(String Location, String RequestType, String Requestor, String Employee,
			String Shift) {
		flag = webDB.waitForElement(CommonUiLocators.CreateMaterialRequestButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Create Material Request Button Is Dsiplayed");
			webDB.clickAnElement(CommonUiLocators.CreateMaterialRequestButton, ElementType.Xpath);
			FrameSwitching(MaterialRequestLocators.MRFrame);
			flag = webDB.waitForElement(MaterialRequestLocators.CreateLocationLov, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create Location Lov Button Is Displayed");
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.CreateLocationLov, Location);
				FrameSwitching(MaterialRequestLocators.MRFrame);
				webDB.selectDropDownByText(MaterialRequestLocators.RequestType, RequestType, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.RequestorLovButton, Requestor);
				FrameSwitching(MaterialRequestLocators.MRFrame);
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.EmployeeLovButton, Employee);
				FrameSwitching(MaterialRequestLocators.MRFrame);
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.ShiftLovButton, Shift);
				FrameSwitching(MaterialRequestLocators.MRFrame);
				flag = webDB.waitForElement(MaterialRequestLocators.TickIconMaterialRequest, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Tick Icon Is Displayed");
					webDB.clickAnElement(MaterialRequestLocators.TickIconMaterialRequest, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(MaterialRequestLocators.MaterialRequestDocumentNo, ElementType.Xpath);
					if (flag) {
						storeMaterialRequestNumber();
						logs.logging("INFO", "Location Id Is Displayed");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to validate Item Warehouse Requester Name and Request
	 * Quantity by search with Material Request Number
	 * 
	 * @author Sakthi-GWL
	 * @param MRNo
	 * @param Item
	 * @param Warehouse
	 * @param ReqName
	 */
	public void validateItemWHReqNameQuantityWithProdNo(String MRNo, String Item, String Warehouse, String ReqName) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.FindProdNoLovBtn, MRNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(3000);
			flag = webDB.waitForElement(MaterialRequestLocators.Find_Item_TableData(MRNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MR No: " + MRNo + " Displayed");
				String DisplItem = webDB.getTextFromElement(MaterialRequestLocators.Find_Item_TableData(MRNo),
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Item: " + DisplItem);
				flag = Item.equalsIgnoreCase(DisplItem);
				if (flag) {
					logs.logging("INFO", "Item Validated Successfully In Material Request");
					String DisplWH = webDB.getTextFromElement(MaterialRequestLocators.Find_Warehouse_TableData(MRNo),
							ElementType.Xpath);
					logs.logging("INFO", "Displayed WareHouse: " + DisplWH);
					flag = Warehouse.equalsIgnoreCase(DisplWH);
					if (flag) {
						logs.logging("INFO", "Warehouse Validated Successfully In Material Request");
						String DisplReqName = webDB.getTextFromElement(
								MaterialRequestLocators.Find_RequesterName_TableData(MRNo), ElementType.Xpath);
						logs.logging("INFO", "Displayed Requester Name: " + DisplReqName);
						flag = ReqName.equalsIgnoreCase(DisplReqName);
						if (flag) {
							logs.logging("INFO", "Requester Name Validated Successfully In Material Request");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Verify Add Item
	 * 
	 * @author Deepan-GWL
	 * 
	 * @param Item
	 * 
	 * @param RequestQty
	 */
	public void verifyAddItem(String Item, String RequestQty) {
		flag = webDB.waitForElement(MaterialRequestLocators.AddButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Button Is Displayed");
			webDB.clickAnElement(MaterialRequestLocators.AddButton, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialRequestLocators.ItemTableButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Table Button Is Displayed");
				webDB.clickAnElement(MaterialRequestLocators.ItemTableButton, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.ItemLovButton, Item);
				webDB.moveToElement(MaterialRequestLocators.UOM, ElementType.Xpath);
				webDB.moveToElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Request Quantity Is Displayed");
					webDB.clickAnElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialRequestLocators.RequestQuantityInput, ElementType.Xpath);
					if (flag) {
						flag = webDB.sendTextToAnElement(MaterialRequestLocators.RequestQuantityInput, RequestQty,
								ElementType.Xpath);
//						String value = webDB.getAttributeFromElement(MaterialRequestLocators.RequestQuantityInput,
//								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
//						logs.logging("INFO", "Get Text Quantity Name Value from Table Is: " + value);
//						flag = value.contains(RequestQty);
//						if (flag) {
//							logs.logging("INFO", "Quantity Entered: " + RequestQty);
//						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Save Line Button
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	public void lineSaveButton() {
		flag = webDB.waitForElement(MaterialRequestLocators.LineTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Tick Icon Is Displayed");
			webDB.clickAnElement(MaterialRequestLocators.LineTickIcon, ElementType.Xpath);
			if (webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath)) {
				webDB.javaScriptClickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			} else {
				if (webDB.isElementDisplayed(CommonUiLocators.FailedNotifyMessage, ElementType.Xpath)) {
					logs.logging("INFO", "Save Entry Item failed Notification Displayed");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Header Material Request Validation
	 * 
	 * @author Deepan-GWL
	 * @param Location
	 * @param Unit
	 * @param Source
	 * @param Requestor
	 * @param Employee
	 */
	public void verifyHeaderMaterialRequestValidation(String Location, String Unit, String SubVoucherType,
			String Requestor, String Employee, String Shift, String Source) {
		purchaseOrderFunc.verifyGetTextValidation(MaterialRequestLocators.HeaderLocationId, Location);
		purchaseOrderFunc.verifyDomPropertyValidation(MaterialRequestLocators.HeaderUnitId, Unit);
		purchaseOrderFunc.verifyGetTextValidation(MaterialRequestLocators.HeaderType, SubVoucherType);
		purchaseOrderFunc.verifyGetTextValidation(MaterialRequestLocators.HeaderRequestor, Requestor);
		purchaseOrderFunc.verifyDomPropertyValidation(MaterialRequestLocators.HeaderEmployee, Employee);
		purchaseOrderFunc.verifyGetTextValidation(MaterialRequestLocators.HeaderShiftField, Shift);
		purchaseOrderFunc.verifyGetTextValidation(MaterialRequestLocators.HeaderSource, Source);
		storeMaterialRequestNumber();
	}

	/**
	 * Verify Navigate To Line Details
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyNavigateToLineDetail(String LineAmount, String Unitcost) {
		webDB.moveToElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
		flag = webDB.waitForElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "View Line Details Is Displayed");
			webDB.clickAnElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
			flag = webDB.waitForClickElement(MaterialRequestLocators.OtherDetailsHeader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Iframe Is Displayed");
				commonFunc.hardWait(3000);
				webDB.switchToFrame(MaterialRequestLocators.IFrame, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialRequestLocators.UnitCostLineDetail, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Line Amount Unit Cost Is Displayed");
					verifyDomPropertyValidation(MaterialRequestLocators.LineDetailLineAmount, LineAmount);
					Unitcost = purchaseOrderFunc
							.verifyRoundOffBigDecimalValueWithReturnString(new BigDecimal(Unitcost));
					verifyDomPropertyValidation(MaterialRequestLocators.UnitCostLineDetail, Unitcost);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Close Line Details Is Displayed");
						webDB.clickAnElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
						flag = webDB.waitForElement(MaterialRequestLocators.HeaderLocationId, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Location Id Is Displayed");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Line Amount Calculation
	 * 
	 * @author Deepan-GWL
	 * @param OrderedQty
	 * @param UnitCost
	 * @return String LineAmount
	 */
	public String lineAmountCalculation(String OrderedQty, String UnitCost) {
		BigDecimal Quantityd = new BigDecimal(OrderedQty);
		BigDecimal Costd = new BigDecimal(UnitCost);
		BigDecimal LineAmountd = Costd.multiply(Quantityd);
		String LineAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnString(LineAmountd);
		VariableHandler.setTotalLineAmount(VariableHandler.getTotalLineAmount()
				.add(purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(LineAmountd)));
		logs.logging("INFO", "Calculated Line Amount Is: " + LineAmount);
		return LineAmount;
	}

	/**
	 * This Method Is Used To Header Save And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void headerSaveAndPost() {
		flag = webDB.waitForElement(MaterialRequestLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.moveToElement(MaterialRequestLocators.HeaderTickIcon, ElementType.Xpath);
			webDB.clickAnElement(MaterialRequestLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(MaterialRequestLocators.HeaderPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(MaterialRequestLocators.HeaderPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();

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
					commonFunc.getDataFromTestDataSheet("Value")).replace(",", "");
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.contains(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store Material Request Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storeMaterialRequestNumber() {
		String Number = webDB.getTextFromElement(MaterialRequestLocators.MaterialRequestDocumentNo, ElementType.Xpath);
		logs.logging("INFO", "Get Text Material Document Number Is: " + Number);
		VariableHandler.setMRNumber(Number);
	}

	/**
	 * This Method Is Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 * @return
	 */
	public boolean verifyCreateMaterialRequest() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("IntraTransfer"),
				commonFunc.getDataFromTestDataSheet("FGWareHouse"), commonFunc.getDataFromTestDataSheet("Vinoth"),
				commonFunc.getDataFromTestDataSheet("ShiftMR"));
//		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
//				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("IntraTransfer"),
//				commonFunc.getDataFromTestDataSheet("FGWareHouse"), commonFunc.getDataFromTestDataSheet("Vinoth"),
//				commonFunc.getDataFromTestDataSheet("ShiftMR"), commonFunc.getDataFromTestDataSheet("ManualValue"));
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			lineSaveButton();
			System.out.println(itemmultiple[i][0] + " line Amount");
			String amount = lineAmountCalculation(itemmultiple[i][2],
					VariableHandler.getStockCost().get(itemmultiple[i][0]));
			verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
		}
		headerSaveAndPost();
		purchaseBillsFunc.verifyWaitingForApproval();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		return commonFunc.logoutUser();
	}

	/**
	 * Verify Validate Details PO Details
	 * 
	 * @author Deepan-GWL
	 * @param PONumber
	 * @param ExpCurrency
	 * @param POHType
	 * @param UOM
	 */
	public void verifyValidatedetailsPoDetails(String MRNumber, String Location, String Unit, String Source,
			String Requestor, String Employee, String status) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(ApprovalPageLocators.PODetailsInfoBtn(MRNumber), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.PODetailsInfoBtn(MRNumber), ElementType.Xpath);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRLocationID, Location);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MREmployee, Employee);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRRequestor, Requestor);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRSource, Source);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRStatus, status);
			purchaseOrderFunc.verifyGetTextValidation(ApprovalPageLocators.MRUnitId, Unit);

		}
		Assert.assertTrue(flag);
	}

	/**
	 * Check Status Of Mr No
	 * 
	 * @author Deepan-GWL
	 * @param MrNo
	 * @param ExpectedStatus
	 */
	public void checkStatusOfMRNo(String MrNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.RequestNumber, MrNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialRequestLocators.MRApproveStatusTable(MrNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MR No: " + MrNo + " Displayed");
				String Status = webDB.getTextFromElement(MaterialRequestLocators.MRApproveStatusTable(MrNo),
						ElementType.Xpath);
				logs.logging("INFO", "MR Number: '" + MrNo + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpectedStatus);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestIntertype() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Inter Transfer"),
				commonFunc.getDataFromTestDataSheet("EmpWareHouse"), commonFunc.getDataFromTestDataSheet("Vinoth"),
				commonFunc.getDataFromTestDataSheet("ShiftMR"));
//		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
//				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Inter Transfer"),
//				commonFunc.getDataFromTestDataSheet("Warehouse100"), commonFunc.getDataFromTestDataSheet("Vinoth"),
//				commonFunc.getDataFromTestDataSheet("ShiftMR"), commonFunc.getDataFromTestDataSheet("ManualValue"));
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			lineSaveButton();
			String amount = lineAmountCalculation(itemmultiple[i][2],
					VariableHandler.getStockCost().get(itemmultiple[i][0]));
			verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
		}
		headerSaveAndPost();
		purchaseBillsFunc.verifyWaitingForApproval();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestDepartmenttype() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("DepartmentValue"), commonFunc.getDataFromTestDataSheet("Prod100"),
				commonFunc.getDataFromTestDataSheet("Vinoth"), commonFunc.getDataFromTestDataSheet("ShiftMR"));
//		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
//				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("DepartmentValue"),
//				commonFunc.getDataFromTestDataSheet("Prod100"), commonFunc.getDataFromTestDataSheet("Vinoth"),
//				commonFunc.getDataFromTestDataSheet("ShiftMR"), commonFunc.getDataFromTestDataSheet("ManualValue"));
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			lineSaveButton();
			String amount = lineAmountCalculation(itemmultiple[i][2],
					VariableHandler.getStockCost().get(itemmultiple[i][0]));
//			verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
		}
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.ISSCodeButton,
				commonFunc.getDataFromTestDataSheet("IssCodeValue"));
		headerSaveAndPost();
		purchaseBillsFunc.verifyWaitingForApproval();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Create Material Request With Wip Type
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestWipType() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("WipType"), commonFunc.getDataFromTestDataSheet("WIP100"),
				commonFunc.getDataFromTestDataSheet("Vinoth"), commonFunc.getDataFromTestDataSheet("ShiftMR"));
//		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
//				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("WipType"),
//				commonFunc.getDataFromTestDataSheet("WorkInProgressWH"), commonFunc.getDataFromTestDataSheet("Vinoth"),
//				commonFunc.getDataFromTestDataSheet("ShiftMR"), commonFunc.getDataFromTestDataSheet("ManualValue"));
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRToMRVWipSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			flag = webDB.waitForElement(MaterialRequestLocators.ProductionOrderNo, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Table Button Is Displayed");
				webDB.clickAnElement(MaterialRequestLocators.ProductionOrderNo, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.ProductionOrderNoLovButton,
						"PROD-2526-0006");
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Request Quantity Is Displayed");
					webDB.clickAnElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialRequestLocators.RequestQuantityInput, ElementType.Xpath);
					if (flag) {
						flag = webDB.sendTextToAnElement(MaterialRequestLocators.RequestQuantityInput,
								itemmultiple[i][2], ElementType.Xpath);
						lineSaveButton();
						String amount = lineAmountCalculation(itemmultiple[i][2],
								VariableHandler.getStockCost().get(itemmultiple[i][0]));
						verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
					}
				}
			}
		}
		headerSaveAndPost();
		purchaseBillsFunc.verifyWaitingForApproval();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestSubcontractor() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Subcontractor"), commonFunc.getDataFromTestDataSheet("POSupplier"),
				commonFunc.getDataFromTestDataSheet("Vinoth"), commonFunc.getDataFromTestDataSheet("Shift_100"));
//		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
//				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Subcontractor"),
//				commonFunc.getDataFromTestDataSheet("POSupplier"), commonFunc.getDataFromTestDataSheet("Vinoth"),
//				commonFunc.getDataFromTestDataSheet("Shift"), commonFunc.getDataFromTestDataSheet("ManualValue"));

		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRToMRVWipSheet"));
		for (int i = 1; i < itemmultiple.length; i++) {
			verifyAddItem(itemmultiple[i][0], itemmultiple[i][2]);
			flag = webDB.waitForElement(MaterialRequestLocators.ProductionOrderNo, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Table Button Is Displayed");
				webDB.clickAnElement(MaterialRequestLocators.ProductionOrderNo, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.ProductionOrderNoLovButton,
						"PROD-2526-0045");
				commonFunc.hardWait(4000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Request Quantity Is Displayed");
					webDB.clickAnElement(MaterialRequestLocators.RequestQuantity, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialRequestLocators.RequestQuantityInput, ElementType.Xpath);
					if (flag) {
						flag = webDB.sendTextToAnElement(MaterialRequestLocators.RequestQuantityInput,
								itemmultiple[i][2], ElementType.Xpath);
						lineSaveButton();
						String amount = lineAmountCalculation(itemmultiple[i][2],
								VariableHandler.getStockCost().get(itemmultiple[i][0]));
						verifyNavigateToLineDetail(amount, VariableHandler.getStockCost().get(itemmultiple[i][0]));
					}
				}
			}
		}
		headerSaveAndPost();
		purchaseBillsFunc.verifyWaitingForApproval();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryMaterialRequest();
		checkStatusOfMRNo(VariableHandler.getMRNumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Create Material Request Document
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestDocument(String DocNo) {
		commonFunc.expandFindTable();
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.ReqNo, DocNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.expandFindTable();
			flag = webDB.waitForElement(MaterialRequestLocators.RequestMRNo(DocNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Is Clicked");
				webDB.clickAnElement(MaterialRequestLocators.RequestMRNo(DocNo), ElementType.Xpath);
				flag = webDB.waitForElement(MaterialRequestLocators.HeaderType, ElementType.Xpath);
				if (flag) {
					String text = webDB.getTextFromElement(MaterialRequestLocators.HeaderType, ElementType.Xpath);
					logs.logging("INFO", "Get Text Header Value Is: " + text);
					logs.logging("INFO", "Expected Value Is: " + commonFunc.getDataFromTestDataSheet("Subcontractor"));
					flag = text.equals(commonFunc.getDataFromTestDataSheet("Subcontractor"));
					if (flag) {
						logs.logging("INFO", "Verified Actual Value With Expected Value");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Navigate To Line Details
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyNavigateToLineDetail(String DocNo) {
		flag = webDB.waitForElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "View Line Details Is Displayed");
			webDB.moveToElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
			webDB.javaScriptClickAnElement(MaterialRequestLocators.ViewLineDetails, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.SI_detail_Button, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesInvoiceLocators.SI_detail_Button, ElementType.Xpath);
				flag = webDB.waitForClickElement(MaterialRequestLocators.IFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(MaterialRequestLocators.IFrame, ElementType.Xpath);
					commonFunc.expandAllInLineDetails();
					flag = webDB.waitForElement(MaterialRequestLocators.OrderNo, ElementType.Xpath);
					if (flag) {
						String text = webDB.getTextFromElement(MaterialRequestLocators.OrderNo, ElementType.Xpath);
						logs.logging("INFO", "Get Text Order Number Is: " + text);
						flag = text.equals(DocNo);
						if (flag) {
							logs.logging("INFO", "Verified Doc No With Actual And Expected");
							webDB.switchToDefaultContent();
							flag = webDB.waitForElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Close Line Details Is Displayed");
								webDB.clickAnElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
								flag = webDB.waitForElement(MaterialRequestLocators.HeaderLocationId,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Location Id Is Displayed");
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
	 * This method used to store Material Request Number And check Status approve
	 * with Production Order Number
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 * @param ExpectedStatus
	 */
	public void storeMRNoAndCheckStatusApproveWithProdNo(String ProdNo) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.FindProdNoLovBtn, ProdNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and Prod No: " + ProdNo + " Displayed");
				String MRNo = webDB.getTextFromElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo),
						ElementType.Xpath);
				VariableHandler.setMaterialRequestNo(MRNo);
				logs.logging("INFO", "Material Number: '" + VariableHandler.getMaterialRequestNo() + "'");
				flag = webDB.waitForElement(MaterialRequestLocators.Find_Status_TableData(ProdNo), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Button clicked and MR No: " + ProdNo + " Displayed");
					String Status = webDB.getTextFromElement(MaterialRequestLocators.Find_Status_TableData(ProdNo),
							ElementType.Xpath);
					logs.logging("INFO",
							"Production Number: '" + ProdNo + "' searched and displaying status: " + Status);
					flag = Status.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ApproveStatus"));
					if (flag) {
						logs.logging("INFO", "Status was approved for Material request");
					} else {
						flag = webDB.waitForElement(MaterialRequestLocators.FindStatusSelectField, ElementType.Xpath);
						if (flag) {
							webDB.selectDropDownByText(MaterialRequestLocators.FindStatusSelectField,
									commonFunc.getDataFromTestDataSheet("Draft"), ElementType.Xpath);
							webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							webDB.moveToElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo), ElementType.Xpath);
							webDB.clickAnElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo),
									ElementType.Xpath);
							flag = webDB.waitForElement(MaterialRequestLocators.HeaderTickIcon, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "MR Status not Approve selected the Document to post");
								headerSaveAndPost();
								if (!VariableHandler.getApprovalFlag()) {
									approvalPageFunc.waitingForApprovalWithFordwarder(
											commonFunc.getDataFromTestDataSheet("HodValue"));
									commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
											VariableHandler.getMaterialRequestNo(),
											webDB.getDataFromProperties("HodEmpId"),
											commonFunc.getDataFromTestDataSheet("Unit_100"),
											System.getProperty("HariUsername"));
								}
								commonFunc.navigateToInventoryMaterialRequest();
								checkStatusOfMRNo(VariableHandler.getMaterialRequestNo(),
										commonFunc.getDataFromTestDataSheet("ApproveStatus"));
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to store Material Request Number And check Status approve
	 * with Production Order Number
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 * @param ExpectedStatus
	 */
	public void storeMRNoAndCheckStatusDraftAndPostWithProdNo(String ProdNo) {
		commonFunc.expandFindTable();
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.FindProdNoLovBtn, ProdNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindStatusSelectField, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(MaterialRequestLocators.FindStatusSelectField,
					commonFunc.getDataFromTestDataSheet("Draft"), ElementType.Xpath);
			webDB.clickAnElement(MaterialRequestLocators.FindSearchLinesRadioBtn, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.expandFindTable();
				flag = webDB.waitForElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Button clicked and Prod No: " + ProdNo + " Displayed");
					String MRNo = webDB.getTextFromElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo),
							ElementType.Xpath);
					VariableHandler.setMaterialRequestNo(MRNo);
					logs.logging("INFO", "Material Number: '" + VariableHandler.getMaterialRequestNo() + "'");
					commonFunc.checkRefreshCellsDisappear();
					webDB.moveToElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo), ElementType.Xpath);
					webDB.clickAnElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo), ElementType.Xpath);
					flag = webDB.waitForElement(MaterialRequestLocators.HeaderTickIcon, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "MR Status not Approve selected the Document to post");
						headerSaveAndPost();
						if (!VariableHandler.getApprovalFlag()) {
							approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
							commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
									VariableHandler.getMaterialRequestNo(), webDB.getDataFromProperties("HariEmpId"),
									commonFunc.getDataFromTestDataSheet("Unit_100"),
									System.getProperty("HariUsername"));
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create MR Customer Type
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean createMRCustomerType() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateToInventoryMaterialRequest();
		verifyCreateMaterialRequest(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("CustomerMR"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("Vinoth"), commonFunc.getDataFromTestDataSheet("ShiftMR"));
		verifyHeaderMaterialRequestValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("CustomerMR"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("Vinoth"),
				commonFunc.getDataFromTestDataSheet("ShiftMR"), commonFunc.getDataFromTestDataSheet("ManualValue"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRCustomer"));
		for (int i = 1; i < itemmultiple.length; i++) {
			String ItemID = itemmultiple[i][0];
			String WHID = itemmultiple[i][1];
			String ReqQty = itemmultiple[i][2];
			String LineAmount = itemmultiple[i][5];
			String StockCost = dbCommonFunctions.StockQuery1(WHID, ItemID,
					commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
			logs.logging("INFO",
					"Stock Cost For This Item " + ItemID + " Against This Warehouse " + WHID + " Is " + StockCost);
			excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("MRCustomer"), i, 4, StockCost);
			BigDecimal stockCostValue = new BigDecimal(StockCost);
			stockCostValue = stockCostValue.setScale(2, RoundingMode.HALF_UP);
			excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("MRCustomer"), i, 3,
					stockCostValue.toString());
			verifyAddItem(ItemID, ReqQty);
			lineSaveButton();
			clickLineWiseDetails(i, stockCostValue.toString(), LineAmount);
		}
		clickHeaderPostButton();
		flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
		approvalPageFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		return flag;
	}

	/**
	 * This Method Is Used To Click The line Wise Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param i
	 * @param StockCost
	 * @param LineAmount
	 */
	public void clickLineWiseDetails(int i, String StockCost, String LineAmount) {
		flag = webDB.waitForElement(MaterialRequestLocators.MRLineDetails(i), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.MRLineDetails(i), ElementType.Xpath);
			FrameSwitching(MaterialRequestLocators.IFrame);
			flag = webDB.waitForElement(MaterialRequestLocators.UnitCostLineDetail, ElementType.Xpath);
			if (flag) {
				purchaseOrderFunc.verifyDomPropertyValidation(MaterialRequestLocators.UnitCostLineDetail, StockCost);
				purchaseOrderFunc.verifyDomPropertyValidation(MaterialRequestLocators.LineDetailLineAmount, LineAmount);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Close Line Details Is Displayed");
					webDB.clickAnElement(MaterialRequestLocators.CloseLineDetail, ElementType.Xpath);
					flag = webDB.waitForElement(MaterialRequestLocators.AddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Line Details Is Closed Successfully");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Click The Header Post Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickHeaderPostButton() {
		flag = webDB.waitForClickElement(MaterialRequestLocators.HeaderPostIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.clickAnElement(MaterialRequestLocators.HeaderPostIcon, ElementType.Xpath);
			logs.logging("INFO", "Header Post Icon Is Clicked");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Clicked Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used to Enter Value In All The Fields In The MR Find Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param Location
	 * @param Unit
	 * @param SubVoucherType
	 * @param MRNumber
	 * @param ProdOrdNumber
	 * @param FromDate
	 * @param ToDate
	 * @param Requestor
	 * @param SOPRJRef
	 * @param Status
	 * @param ItemID
	 * @param ItemDesc
	 * @param SourceType
	 */
	public void enterValueInAllFieldsInTheMRFindScreen(String Location, String Unit, String SubVoucherType,
			String MRNumber, String ProdOrdNumber, String FromDate, String ToDate, String Requestor, String SOPRJRef,
			String Status, String ItemID, String ItemDesc, String SourceType) {
		flag = webDB.waitForElement(MaterialRequestLocators.FindLocationInputField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(MaterialRequestLocators.FindLocationInputField, Location, ElementType.Xpath);
			flag = webDB.waitForElement(MaterialRequestLocators.FindUnitInputField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Loaction Inserted Successfully");
				webDB.pressTabKey();
				webDB.sendTextToAnElement(MaterialRequestLocators.FindUnitInputField, Unit, ElementType.Xpath);
				flag = webDB.waitForElement(MaterialRequestLocators.FindSubVoucherTypeSelectField, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Unit Inserted Successfully");
					webDB.pressTabKey();
					webDB.selectDropDownByValue(MaterialRequestLocators.FindSubVoucherTypeSelectField, SubVoucherType,
							ElementType.Xpath);
					flag = webDB.waitForElement(MaterialRequestLocators.FindMRNumberInputField, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", SubVoucherType + " Is Inserted Successfully");
						webDB.pressTabKey();
						webDB.sendTextToAnElement(MaterialRequestLocators.FindMRNumberInputField, MRNumber,
								ElementType.Xpath);
						flag = webDB.waitForElement(MaterialRequestLocators.FindProductionOrderNumberInputField,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", MRNumber + " Is Inserted Successfully");
							webDB.pressTabKey();
							webDB.sendTextToAnElement(MaterialRequestLocators.FindProductionOrderNumberInputField,
									ProdOrdNumber, ElementType.Xpath);
							flag = webDB.waitForElement(MaterialRequestLocators.FindFromDateInputField,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", ProdOrdNumber + " Is Inserted Successfully");
								webDB.pressTabKey();
								webDB.sendTextToAnElement(MaterialRequestLocators.FindFromDateInputField, FromDate,
										ElementType.Xpath);
								flag = webDB.waitForElement(MaterialRequestLocators.FindToDateInputField,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", FromDate + " Is Inserted Successfully");
									webDB.pressTabKey();
									webDB.sendTextToAnElement(MaterialRequestLocators.FindToDateInputField, ToDate,
											ElementType.Xpath);
									flag = webDB.waitForElement(MaterialRequestLocators.FindRequestorInputField,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", ToDate + " Is Inserted successfully");
										webDB.pressTabKey();
										webDB.sendTextToAnElement(MaterialRequestLocators.FindRequestorInputField,
												Requestor, ElementType.Xpath);
										flag = webDB.waitForElement(MaterialRequestLocators.FindSOPRJRefInputField,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", Requestor + " Is Inserted Successfully");
											webDB.pressTabKey();
											webDB.sendTextToAnElement(MaterialRequestLocators.FindSOPRJRefInputField,
													SOPRJRef, ElementType.Xpath);
											flag = webDB.waitForElement(MaterialRequestLocators.FindStatusSelectField,
													ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", SOPRJRef + " Is Inserted Sucessfully");
												webDB.pressTabKey();
												webDB.selectDropDownByValue(
														MaterialRequestLocators.FindStatusSelectField, Status,
														ElementType.Xpath);
												flag = webDB.waitForElement(
														MaterialRequestLocators.FindItemIDInputField,
														ElementType.Xpath);
												if (flag) {
													logs.logging("INFO", Status + " Is Inserted Successfully");
													webDB.pressTabKey();
													webDB.sendTextToAnElement(
															MaterialRequestLocators.FindItemIDInputField, ItemID,
															ElementType.Xpath);
													flag = webDB.waitForElement(
															MaterialRequestLocators.FindItemDescInputField,
															ElementType.Xpath);
													if (flag) {
														logs.logging("INFO", ItemID + " Is Inserted Successfully");
														webDB.pressTabKey();
														webDB.sendTextToAnElement(
																MaterialRequestLocators.FindItemDescInputField,
																ItemDesc, ElementType.Xpath);
														flag = webDB.waitForElement(
																MaterialRequestLocators.FindSourceSelectField,
																ElementType.Xpath);
														if (flag) {
															logs.logging("INFO",
																	ItemDesc + " Is Inserted successfully");
															webDB.pressTabKey();
															webDB.selectDropDownByValue(
																	MaterialRequestLocators.FindSourceSelectField,
																	SourceType, ElementType.Xpath);
															flag = webDB.waitForElement(
																	MaterialRequestLocators.FindSearchButton,
																	ElementType.Xpath);
															if (flag) {
																logs.logging("INFO",
																		SourceType + " Is choosen successfully");
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
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Switch Frame In Material Request Header creation
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void FrameSwitching(String Locator) {
		flag = webDB.waitForElement(Locator, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(Locator, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame Sucessfully");
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to validate the Header and Lines Details in Material Request
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 * @param StartIndex
	 */
	public void verifyHeaderValidationInMaterialRequestWithDB(String FileName, String SheetName, int StartIndex) {
		logs.logging("INFO", "****Material Request DB Validation - Raw Materials & Headers****");
		logs.logging("INFO", "Start Index: " + StartIndex);
		String LineNo, SeqNo, Process, Unit, RMName, UOM, SourceWH, ReceiverWH, ReqQnty = null;
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		a: for (int i = StartIndex; i < TotalData.length; i++) {
			Unit = TotalData[2][1];
			LineNo = TotalData[i][1];
			SeqNo = TotalData[i][0];
			Process = TotalData[i][2];
			RMName = TotalData[i][4];
			UOM = TotalData[i][5];
			SourceWH = TotalData[i][6];
			ReceiverWH = TotalData[i][7];
			ReqQnty = TotalData[i][10];
			if (SeqNo.equals("") || SeqNo == null || SeqNo.isBlank() || SeqNo.isEmpty() || SeqNo.equalsIgnoreCase("SFG")
					|| SeqNo.equalsIgnoreCase("Completion")) {
				break a;
			}
			if (Integer.parseInt(LineNo) > 7 && Integer.parseInt(LineNo) <= 14) {
				LineNo = String.valueOf(Integer.parseInt(LineNo) - 7);
			} else if (Integer.parseInt(LineNo) > 14) {
				LineNo = String.valueOf(Integer.parseInt(LineNo) - 14);
			}
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialRequestLinesDetails(SeqNo, LineNo,
					commonFunc.getDataFromTestDataSheet("MaterialRequestUnit")), Unit);
			logs.logging("INFO", "Verified Unit and DB Value Matched: " + Unit);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialRequestLinesDetails(SeqNo, LineNo,
					commonFunc.getDataFromTestDataSheet("MaterialRequestSourceWH")), ReceiverWH);
			logs.logging("INFO", "Verified ReceiverWH and DB Value Matched: " + ReceiverWH);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialRequestLinesDetails(SeqNo, LineNo,
					commonFunc.getDataFromTestDataSheet("MaterialRequestSeqNo")), SeqNo);
			logs.logging("INFO", "Verified SeqNo and DB Value Matched: " + SeqNo);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialRequestLinesDetails(SeqNo, LineNo,
					commonFunc.getDataFromTestDataSheet("MaterialRequestProcess")), Process);
			logs.logging("INFO", "Verified Process and DB Value Matched: " + Process);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialRequestLinesDetails(SeqNo, LineNo,
					commonFunc.getDataFromTestDataSheet("MaterialRequestRMName")), RMName);
			logs.logging("INFO", "Verified RMName and DB Value Matched: " + RMName);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialRequestLinesDetails(SeqNo, LineNo,
					commonFunc.getDataFromTestDataSheet("MaterialRequestUOM")), UOM);
			logs.logging("INFO", "Verified UOM and DB Value Matched: " + UOM);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialRequestLinesDetails(SeqNo, LineNo,
					commonFunc.getDataFromTestDataSheet("MaterialRequestRequestWH")), SourceWH);
			logs.logging("INFO", "Verified SourceWH and DB Value Matched: " + SourceWH);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialRequestLinesDetails(SeqNo, LineNo,
					commonFunc.getDataFromTestDataSheet("MaterialRequestReqQnty")), ReqQnty);
			logs.logging("INFO", "Verified ReqQnty and DB Value Matched: " + ReqQnty);
			String ProdOrderNo = VariableHandler.getProductionOrderNumber();
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateMaterialRequestLinesDetails(SeqNo, LineNo,
					commonFunc.getDataFromTestDataSheet("MaterialRequestProdNo")), ProdOrderNo);
			logs.logging("INFO", "Verified ProdOrderNo and DB Value Matched: " + ProdOrderNo);
		}
	}
}