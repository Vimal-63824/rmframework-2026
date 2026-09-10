package commonfunctions.supplychainmanagement;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import commonlocators.supplychainmanagement.StockAdjustmentLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class StockAdjustmentFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	public PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	public ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	public MaterialRequestFunctions MatReqFunc = new MaterialRequestFunctions();
	public SalesOrderFunctions SalesOrdFunc = new SalesOrderFunctions();

	String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	ExcelUtils excelUtils = new ExcelUtils();
	String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "ItemDetails.xlsx";
	ArrayList<String> StockQty = new ArrayList<String>();
	ArrayList<String> SerialNumber = new ArrayList<String>();

	/**
	 * This method used to create stock adjustment for LOT Items
	 * 
	 * @author Sakthi-GWL
	 * @param Location
	 * @param Warehouse
	 * @param Prefix
	 * @param ItemId
	 * @param Quantity
	 * @param UnitCost
	 */
	public void verifyStockAdjustmentOnlyForLotItem(String Location, String Warehouse, String Prefix, String ItemId,
			String Quantity, String UnitCost) {
		createStockAdjustmentHeaderDetails(Location, todayDate(), Warehouse,
				commonFunc.getDataFromTestDataSheet("AdjNarrationSTD"));
		getStockAdjustmentDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		clickLineWiseAddButton();
		addLineDetails(Prefix, ItemId, Quantity, UnitCost);
		flag = webDB.waitForElement(StockAdjustmentLocators.LineItemField(), ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(StockAdjustmentLocators.LineItemField(), ElementType.Xpath);
			addLotDetailWithoutExpireDate((ItemId + "(LOT1)"), Quantity);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(StockAdjustmentLocators.PostButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				webDB.moveToElement(StockAdjustmentLocators.PostButton, ElementType.Xpath);
				webDB.clickAnElement(StockAdjustmentLocators.PostButton, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalPageFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
							VariableHandler.getStkAdjDocNumber(), webDB.getDataFromProperties("HariEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Create StockAdjustment And Increase The Opening Stock
	 * For the STD Items
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean createStockAdjustmentOB() {
		getBeforeStockQty(webDB.getDataFromProperties("StockAdjustmentOB"),
				commonFunc.getDataFromTestDataSheet("RMWarehouse100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateStockAdjustmentScreen();
		createStockAdjustmentHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"), todayDate(),
				commonFunc.getDataFromTestDataSheet("RMWarehouse100"),
				commonFunc.getDataFromTestDataSheet("AdjNarrationSTD"));
		verifyStockAdjustmentHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), todayDate(),
				commonFunc.getDataFromTestDataSheet("RMWarehouse100Desc"),
				commonFunc.getDataFromTestDataSheet("AdjNarrationSTD"), commonFunc.getDataFromTestDataSheet("Draft"));
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("StockAdjustmentOB"));
		for (int i = 1; i < itemmultiple.length; i++) {
			String PREFIX = itemmultiple[i][1];
			String ITEMID = itemmultiple[i][2];
			String QTY = itemmultiple[i][5];
			String UNITCOST = itemmultiple[i][6];
			String LOTNUMBER = itemmultiple[i][7];
			String SERIALNUMBER = itemmultiple[i][9];
			String EXPIRYDATE = itemmultiple[i][10];

			commonFunc.checkRefreshCellsDisappear();
			clickLineWiseAddButton();
			addLineDetails(PREFIX, ITEMID, QTY, UNITCOST);
			flag = webDB.waitForElement(StockAdjustmentLocators.LineItem(i), ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(StockAdjustmentLocators.LineItem(i), ElementType.Xpath);
				logs.logging("INFO", "LINE FIELD IS CLICKED SUCESSFULLY");
				commonFunc.checkRefreshCellsDisappear();
				String LotType = dbCommonFunctions.ItemMaster(ITEMID,
						commonFunc.getDataFromTestDataSheet("DBItemLotSerialType"));
				logs.logging("INFO", "Lot Type Is : " + LotType);
				String LotExpType = dbCommonFunctions.ItemMaster(ITEMID,
						commonFunc.getDataFromTestDataSheet("DBItemLotSerialExpType"));
				logs.logging("INFO", "LotExpiry Type Is : " + LotExpType);
				commonFunc.checkRefreshCellsDisappear();
				if (LotType.equals("L")) {
					addLotDetails(LOTNUMBER, QTY, LotExpType, EXPIRYDATE, i);
				} else if (LotType.equals("S")) {
					logs.logging("INFO", "Item Type Is Serial");
					addSerialDetails(ITEMID, SERIALNUMBER, QTY, EXPIRYDATE, LotExpType, i);
					commonFunc.hardWait(10000);
				} else {
					logs.logging("INFO", "Item type Is NA");
				}
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		flag = webDB.waitForElement(StockAdjustmentLocators.PostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(StockAdjustmentLocators.PostButton, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				approvalPageFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				if (flag) {
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getStkAdjDocNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
			}
		}
		getAfterStockQtyAndVerifyLotDetailsInLotSerialStock(webDB.getDataFromProperties("StockAdjustmentOB"),
				commonFunc.getDataFromTestDataSheet("RMWarehouse100"));
		return flag;

	}

	/**
	 * This Method Is Used To Create Stock Adjustment Header Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param Location
	 * @param Date
	 * @param Warehouse
	 * @param Narration
	 */
	public void createStockAdjustmentHeaderDetails(String Location, String Date, String Warehouse, String Narration) {
		flag = webDB.waitForElement(StockAdjustmentLocators.FindAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(StockAdjustmentLocators.FindAddButton, ElementType.Xpath);
			MatReqFunc.FrameSwitching(StockAdjustmentLocators.StockAdjFrame);
			flag = webDB.waitForElement(StockAdjustmentLocators.LoactionLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Stock Adjustment Find Add Button Is Clicked Successfully");
				commonFunc.searchAndSelectValueInFilters(StockAdjustmentLocators.LoactionLovButton, Location);
				webDB.pressTabKey();
				MatReqFunc.FrameSwitching(StockAdjustmentLocators.StockAdjFrame);
				flag = webDB.waitForElement(StockAdjustmentLocators.DateInputField, ElementType.Xpath);
				if (flag) {
					webDB.clearTextField(StockAdjustmentLocators.DateInputField, ElementType.Xpath);
					webDB.sendTextToAnElement(StockAdjustmentLocators.DateInputField, Date, ElementType.Xpath);
					webDB.pressTabKey();
					logs.logging("INFO", "Document Date Inserted Successfully");
					commonFunc.searchAndSelectValueInFilters(StockAdjustmentLocators.WarehouseLovbutton, Warehouse);
					webDB.pressTabKey();
					MatReqFunc.FrameSwitching(StockAdjustmentLocators.StockAdjFrame);
					flag = webDB.waitForElement(StockAdjustmentLocators.NarrationInputField, ElementType.Xpath);
					if (flag) {
						webDB.clearText(StockAdjustmentLocators.NarrationInputField, ElementType.Xpath);
						webDB.sendTextToAnElement(StockAdjustmentLocators.NarrationInputField, Narration,
								ElementType.Xpath);
						logs.logging("INFO", "Narration Inserted Successfully");
						flag = webDB.waitForElement(StockAdjustmentLocators.SaveButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(StockAdjustmentLocators.SaveButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
							flag = webDB.waitForElement(StockAdjustmentLocators.PostButton, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Save Button Is Clicked Sucessfully");
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Verify The StockAdjustment Header Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param Location
	 * @param Unit
	 * @param Date
	 * @param WarehouseDesc
	 * @param Narration
	 * @param Status
	 */
	public void verifyStockAdjustmentHeaderDetails(String Location, String Unit, String Date, String WarehouseDesc,
			String Narration, String Status) {
		purchaseOrderFunc.verifyGetTextValidation(StockAdjustmentLocators.LocationField, Location);
		purchaseOrderFunc.verifyGetTextValidation(StockAdjustmentLocators.UnitField, Unit);
		purchaseOrderFunc.verifyDomPropertyValidation(StockAdjustmentLocators.DateField, Date);
		purchaseOrderFunc.verifyGetTextValidation(StockAdjustmentLocators.WarehouseField, WarehouseDesc);
		purchaseOrderFunc.verifyDomPropertyValidation(StockAdjustmentLocators.NarrationField, Narration);
		purchaseOrderFunc.verifyGetTextValidation(StockAdjustmentLocators.StatusField, Status);
		getStockAdjustmentDocumentNumber();
	}

	/**
	 * This Method Is Used to Get The Today Date In A Specific Format
	 * 
	 * @author Harikrishna - RMITS
	 * @return String Today
	 */
	public String todayDate() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		return Today;
	}

	/**
	 * This Method Is Used To Get The Stock Adjustment Document Number
	 * 
	 * @author Harikrishna RMITS
	 */
	public void getStockAdjustmentDocumentNumber() {
		flag = webDB.waitForElement(StockAdjustmentLocators.DocNumberField, ElementType.Xpath);
		if (flag) {
			String DocNum = webDB.getDomPropertyFromElement(StockAdjustmentLocators.DocNumberField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			VariableHandler.setStkAdjDocNumber(DocNum);
			logs.logging("INFO",
					"Get Text Stock Adjustment Document Number Is : " + VariableHandler.getStkAdjDocNumber());
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Line Details In The Stock Adjustment Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void addLineDetails(String Prefix, String Item, String Qty, String UnitCost) {
		flag = webDB.waitForElement(StockAdjustmentLocators.LinePrefixField(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(StockAdjustmentLocators.LinePrefixField(), ElementType.Xpath);
			logs.logging("INFO", "Line Wise Prefix field is clicked Successfully");
			commonFunc.searchAndSelectValueInFilters(StockAdjustmentLocators.LinePrefixLovButton(), Prefix);
			webDB.pressTabKey();
			commonFunc.searchAndSelectValueInFilters(StockAdjustmentLocators.LineItemLovButton(), Item);
			webDB.pressTabKey();
			SalesOrdFunc.verifyClickAndEnterValueInTableFields(StockAdjustmentLocators.LineQtyField(),
					StockAdjustmentLocators.LineQtyInputField(), Qty);
			commonFunc.checkRefreshCellsDisappear();
			SalesOrdFunc.verifyClickAndEnterValueInTableFields(StockAdjustmentLocators.LineUnitCostField(),
					StockAdjustmentLocators.LineUnitCostInputField(), UnitCost);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(StockAdjustmentLocators.LineWiseSaveButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(StockAdjustmentLocators.LineWiseSaveButton, ElementType.Xpath);
				webDB.clickAnElement(StockAdjustmentLocators.LineWiseSaveButton, ElementType.Xpath);
				logs.logging("INFO", "Line Wise Save Button Is Clicked Successfully");
				if (webDB.isElementDisplayed(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The LIne Wise Add Button In The Stock Adjustment
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void clickLineWiseAddButton() {
		flag = webDB.waitForElement(StockAdjustmentLocators.LineWiseAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(StockAdjustmentLocators.LineWiseAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(StockAdjustmentLocators.LinePrefixLovButton(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise Add Button Is Clicked Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Lot Details In The Stock Ajdustment Screen
	 * 
	 * @author Sakthi-GWL
	 * @param LotNumber
	 * @param LotQty
	 */
	public void addLotDetailWithoutExpireDate(String LotNumber, String LotQty) {
		flag = webDB.waitForElement(StockAdjustmentLocators.LotSerialAddButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(StockAdjustmentLocators.LotSerialAddButton, ElementType.Xpath);
			webDB.clickAnElement(StockAdjustmentLocators.LotSerialAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(StockAdjustmentLocators.LotNumberField(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Lot Wise Add Button Is Clicked Successfully");
				commonFunc.verifyAddTableSpecs(StockAdjustmentLocators.LotNumberField(),
						StockAdjustmentLocators.LotNumberInputField(), LotNumber);
				webDB.pressTabKey();
				SalesOrdFunc.verifyClickAndEnterValueInTableFields(StockAdjustmentLocators.LotQtyField(),
						StockAdjustmentLocators.LotQtyInputField(), LotQty);
				webDB.pressTabKey();
				flag = webDB.waitForElement(StockAdjustmentLocators.LotWiseSaveButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(StockAdjustmentLocators.LotWiseSaveButton, ElementType.Xpath);
					logs.logging("INFO", "Lot Wise Save Button Is Clicked Successfully");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Lot Details In The Stock Ajdustment Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param LotNumber
	 * @param LotQty
	 * @param LotExp
	 * @param ExpiryDate
	 * @param i
	 */
	public void addLotDetails(String LotNumber, String LotQty, String LotExp, String ExpiryDate, int i) {
		flag = webDB.waitForElement(StockAdjustmentLocators.LotSerialAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(StockAdjustmentLocators.LotSerialAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(StockAdjustmentLocators.LotNumberField(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Lot Wise Add Button Is Clicked Successfully");
				SalesOrdFunc.verifyClickAndEnterValueInTableFields(StockAdjustmentLocators.LotNumberField(),
						StockAdjustmentLocators.LotNumberInputField(), LotNumber);
				webDB.pressTabKey();
				SalesOrdFunc.verifyClickAndEnterValueInTableFields(StockAdjustmentLocators.LotQtyField(),
						StockAdjustmentLocators.LotQtyInputField(), LotQty);
				webDB.pressTabKey();
				if (LotExp.equals("Y")) {
					SalesOrdFunc.verifyClickAndEnterValueInTableFields(StockAdjustmentLocators.LotExpiryField(),
							StockAdjustmentLocators.LotExpiryInputField(), ExpiryDate);
				}
				flag = webDB.waitForElement(StockAdjustmentLocators.LotWiseSaveButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(StockAdjustmentLocators.LotWiseSaveButton, ElementType.Xpath);
					logs.logging("INFO", "Lot Wise Save Button Is Clicked Successfully");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Serial details In The Stock Adjustmnent
	 * 
	 * @author Harikrishna - RMITS
	 * @param ItemID
	 * @param SerialNumber
	 * @param Qty
	 * @param ExpiryDate
	 * @param LotExp
	 * @param i
	 */
	public void addSerialDetails(String ItemID, String SerialNumber, String Qty, String ExpiryDate, String LotExp,
			int i) {
		flag = webDB.waitForElement(StockAdjustmentLocators.GenerateSerialButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(StockAdjustmentLocators.GenerateSerialButton, ElementType.Xpath);
			flag = webDB.waitForElement(StockAdjustmentLocators.SerialItemLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Generate Serial Button Is Clicked Sucessfully");
				commonFunc.searchAndSelectValueInFilters(StockAdjustmentLocators.SerialItemLovButton, ItemID);
				webDB.pressTabKey();
				SalesOrdFunc.verifyClickAndEnterValueInTableFields(
						StockAdjustmentLocators.SerialItemStartSerialInputField,
						StockAdjustmentLocators.SerialItemStartSerialInputField, SerialNumber);
				webDB.pressTabKey();
				long SerialNumber1 = Long.parseLong(SerialNumber);
				long Qty1 = Long.parseLong(Qty);
				long FromSerialNumber = SerialNumber1 + Qty1 + 1;
				System.out.println(FromSerialNumber);
				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("StockAdjustmentOB"), i, 9,
						String.valueOf(FromSerialNumber));
				SalesOrdFunc.verifyClickAndEnterValueInTableFields(StockAdjustmentLocators.SerialItemQtyInputField,
						StockAdjustmentLocators.SerialItemQtyInputField, Qty);
				webDB.pressTabKey();
				if (LotExp.equals("Y")) {
					flag = webDB.waitForElement(StockAdjustmentLocators.SerialItemExpiryDateInputField,
							ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(StockAdjustmentLocators.SerialItemExpiryDateInputField, ExpiryDate,
								ElementType.Xpath);
						logs.logging("INFO", "Expiry Date Inserted Successfully");
					}
					flag = webDB.waitForElement(StockAdjustmentLocators.SerialItemGenerateButton, ElementType.Xpath);
					if (flag) {
						webDB.javaScriptClickAnElement(StockAdjustmentLocators.SerialItemGenerateButton,
								ElementType.Xpath);
						logs.logging("INFO", "Generate Button Is Clicked Successfully");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Get Before Stock Qty Using DB
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void getBeforeStockQty(String SheetName, String WareHouse) {
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 1; i < itemmultiple.length; i++) {
			String ITEMID = itemmultiple[i][2];

			String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ITEMID,
					commonFunc.getDataFromTestDataSheet("DBStockQtyOnHandColumnName"));
			excelUtils.writeDataInExcel(Filepath, SheetName, i, 11, BeforeStockQty);
			StockQty.add(BeforeStockQty);

			logs.logging("INFO", "Before Stock Qty In DataBase For This Item : " + ITEMID + " Is " + BeforeStockQty);
		}
		VariableHandler.setExpectedStockQty2(StockQty);
		System.out.println(VariableHandler.getExpectedStockQty2().get(0));
		System.out.println(VariableHandler.getExpectedStockQty2().get(1));

	}

	/**
	 * This Method Is Used To Get After Stock Qty Using DB And Verify With Expected
	 * StockQty After Posting The StockAdjustment Then Verify Lot Serial Stock With
	 * DB For The Lot Type Of Item
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void getAfterStockQtyAndVerifyLotDetailsInLotSerialStock(String SheetName, String WareHouse) {
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 1; i < itemmultiple.length; i++) {
			String ITEMID = itemmultiple[i][2];
			String QTY = itemmultiple[i][5];
			String UNITCOST = itemmultiple[i][6];
			String LOTNUMBER = itemmultiple[i][7];
			String SERIALNUMBER = itemmultiple[i][9];
			String EXPIRYDATE = itemmultiple[i][10];
			String ExpectedAfterStockQty = itemmultiple[i][12];

			String AfterStockQty = dbCommonFunctions.StockQuery1(WareHouse, ITEMID,
					commonFunc.getDataFromTestDataSheet("DBStockQtyOnHandColumnName"));

			logs.logging("INFO", "After Stock Qty In DataBase For This Item : " + ITEMID + " Is " + AfterStockQty);
			SalesOrdFunc.verifyAssertionBothValues(AfterStockQty, ExpectedAfterStockQty);
			logs.logging("INFO", "After Posting The Stock Adjustment Record For This Item :" + ITEMID
					+ " Stock Qty Is Matched With Database Values");

			String LotType = dbCommonFunctions.ItemMaster(ITEMID,
					commonFunc.getDataFromTestDataSheet("DBItemLotSerialType"));
			logs.logging("INFO", "Lot Type For This Item " + ITEMID + " Is : " + LotType);

			if (LotType.equals("L")) {

				SalesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.LotSerialStockDetails(VariableHandler.getStkAdjDocNumber(), ITEMID,
								commonFunc.getDataFromTestDataSheet("DBLotSerialStockItemColumnName")),
						ITEMID);
				logs.logging("INFO", "Against This Stock Adjustment Document " + VariableHandler.getStkAdjDocNumber()
						+ " In The Lot Serial Stocks Item ID " + ITEMID + " Is Matched With DB");

				SalesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.LotSerialStockDetails(VariableHandler.getStkAdjDocNumber(), ITEMID,
								commonFunc.getDataFromTestDataSheet("DBLotSerialStockQtyColumnName")),
						QTY);
				logs.logging("INFO",
						"Against This Stock Adjustment Document " + VariableHandler.getStkAdjDocNumber()
								+ " In The Lot Serial Stocks For This Item " + ITEMID + " Stock Lot/serial Qty " + QTY
								+ " Is Matched With DB");

				SalesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.LotSerialStockDetails(VariableHandler.getStkAdjDocNumber(), ITEMID,
										commonFunc.getDataFromTestDataSheet("DBLotSerialStockUnitCostColumnName")),
								UNITCOST);
				logs.logging("INFO",
						"Against This Stock Adjustment Document " + VariableHandler.getStkAdjDocNumber()
								+ " In The Lot Serial Stocks For This Item " + ITEMID + " Stock Lot/serial Unit Cost "
								+ UNITCOST + " Is Matched With DB");

				String SysLsNumber = dbCommonFunctions.LotSerialStockDetails(VariableHandler.getStkAdjDocNumber(),
						ITEMID, commonFunc.getDataFromTestDataSheet("DBLotSerialStockSysLSNOColumnName"));
				logs.logging("INFO",
						"SysLS Number For This Item  " + ITEMID + " Against The Voucher Number "
								+ VariableHandler.getStkAdjDocNumber() + " For This Lot Is " + LOTNUMBER + " Is "
								+ SysLsNumber);

				SalesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.LotSerialStockDetails(VariableHandler.getStkAdjDocNumber(), ITEMID,
										commonFunc.getDataFromTestDataSheet("DBLotSerialStockLotNOColumnName")),
								LOTNUMBER);
				logs.logging("INFO",
						"Against This Stock Adjustment Document " + VariableHandler.getStkAdjDocNumber()
								+ " In The Lot Serial Stocks For This Item " + ITEMID + " Lot Number " + LOTNUMBER
								+ " Is Matched With DB");

				long LotNumber1 = Long.parseLong(LOTNUMBER);
				long LotNumber2 = LotNumber1 + 1;
				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("StockAdjustmentOB"), i, 7,
						String.valueOf(LotNumber2));
			}
			if (LotType.equals("S")) {
				long Qty = Long.parseLong(QTY);
				for (int j = 0; j < Qty; j++) {
					SerialNumber.add(String.valueOf(SERIALNUMBER + j));
					System.out.println(SerialNumber.get(j));

					String SerialSysLsNo1 = dbCommonFunctions.SerialNumberDetails(VariableHandler.getStkAdjDocNumber(),
							ITEMID, SerialNumber.get(j),
							commonFunc.getDataFromTestDataSheet("DBLotSerialStockSysLSNOColumnName"));
					logs.logging("INFO", " SYSLS Number For This Item " + ITEMID + " Is " + SerialSysLsNo1);

					SalesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.SerialNumberDetails(
							VariableHandler.getStkAdjDocNumber(), ITEMID, SerialNumber.get(j),
							commonFunc.getDataFromTestDataSheet("DBLotSerialStockItemColumnName")), ITEMID);

					SalesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.SerialNumberDetails(
							VariableHandler.getStkAdjDocNumber(), ITEMID, SerialNumber.get(j),
							commonFunc.getDataFromTestDataSheet("DBLotSerialStockQtyColumnName")), QTY);

					SalesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.SerialNumberDetails(
							VariableHandler.getStkAdjDocNumber(), ITEMID, SerialNumber.get(j),
							commonFunc.getDataFromTestDataSheet("DBLotSerialStockQtyColumnName")), UNITCOST);

					String SerialSysLsNo = dbCommonFunctions.SerialNumberDetails(VariableHandler.getStkAdjDocNumber(),
							ITEMID, SerialNumber.get(j),
							commonFunc.getDataFromTestDataSheet("DBLotSerialStockSysLSNOColumnName"));
					logs.logging("INFO", " SYSLS Number For This Item " + ITEMID + " Is " + SerialSysLsNo);

					SalesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.SerialNumberDetails(VariableHandler.getStkAdjDocNumber(), ITEMID,
									SerialNumber.get(j),
									commonFunc.getDataFromTestDataSheet("DBLotSerialStockSerNOColumnName")),
							SerialNumber.get(j));
				}
			}
		}
	}
}
