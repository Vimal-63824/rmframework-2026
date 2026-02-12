package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 15, 2025
 */
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.DirectGrnPageLocators;
import commonlocators.supplychainmanagement.GateEntryPageLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class Purchase_GRN_FOCFunctions {
	private static WebDriverBase webDB = new WebDriverBase();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	private static DirectGrnPageFunctions DirectGRNFunc = new DirectGrnPageFunctions();
	private static ReportLoger logs = new ReportLoger();
	private static GrnPageFunctions GRNFunc = new GrnPageFunctions();
	private static GrnPageFunctions2 GRN2Func = new GrnPageFunctions2();
	private boolean flag = false;
	private static PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	private static GateEntryPageFunctions gateEntryPageFunc = new GateEntryPageFunctions();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();
	private static SalesOrderFunctions saleOrderFunc = new SalesOrderFunctions();

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - General
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrGeneralItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDGeneralSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Material
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrMaterialItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDMaterialSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Service
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrServiceItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDServiceSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - General
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrGeneralItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDGeneralSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Material
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrMaterialItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDMaterialSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Service
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrServiceItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDServiceSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - General
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrGeneralItemDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDGeneralDiffUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Material
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrMaterialItemDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDMaterialDiffUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - General
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrGeneralItemDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDGeneralDiffUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Material
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPOImportSuplrMaterialItemDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("ImportPoFOCUDMaterialDiffUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("RoauBu"), VariableHandler.getPONumber(),
					webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			commonFunc.navigatePurchaseOrderPage();
		}
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - General Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrGeneralItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDGeneralSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - Material Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrMaterialItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDMaterialSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - Service Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrServiceItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDServiceSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_Service"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - General Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrGeneralItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDGeneralSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - Material Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrMaterialItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDMaterialSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - Service Items
	 * with Similar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrServiceItemSimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDServiceSameUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_Service"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - General Items
	 * with Dissimilar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrGeneralItemDissimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDGeneralDiffUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Domestic Supplier - Material Items
	 * with Dissimilar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForDomesticSuplrMaterialItemDissimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("DomesticPRPoFOCUDMaterialDiffUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - General Items
	 * with Dissimilar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrGeneralItemDissimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDGeneralDiffUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PR_General"), commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}

	/**
	 * This method used to verify From PR to PO - Import Supplier - Material Items
	 * with Dissimilar Stock and Supplier UOM Free of Cost
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyFromPRtoPOFOCForImportSuplrMaterialItemDissimilarUOM() {
		flag = POfunc.verifyFromPRtoPOWithMultipleItemInExcelSheetCustomizedForFOC(
				webDB.getDataFromProperties("Purchase-FocFileName"),
				webDB.getDataFromProperties("ImportPRPoFOCUDMaterialDiffUOM"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"),
				commonFunc.getDataFromTestDataSheet("WalmartSupId"));
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-General-User Defined-Same Stock and
	 * Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForUDGeneralItemSimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		DirectGRNFunc.closeToastIfDisplayed();
		webDB.navigateToRefresh();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineCost, DirectGrnPageLocators.GrnLineCostInput, "0");
		logs.logging("INFO", "For Free Of Cost, Entered Unit Cost Is:" + "0");
		DirectGRNFunc.verifyClickingSaveLine();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-Material-User Defined-Same Stock
	 * and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForUDMaterialItemSimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		DirectGRNFunc.closeToastIfDisplayed();
		webDB.navigateToRefresh();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineCost, DirectGrnPageLocators.GrnLineCostInput, "0");
		logs.logging("INFO", "For Free Of Cost, Entered Unit Cost Is:" + "0");
		DirectGRNFunc.verifyClickingSaveLine();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-Service-User Defined-Same Stock and
	 * Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForUDServiceItemSimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPS"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDService"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameUDService"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		DirectGRNFunc.closeToastIfDisplayed();
		webDB.navigateToRefresh();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineCost, DirectGrnPageLocators.GrnLineCostInput, "0");
		logs.logging("INFO", "For Free Of Cost, Entered Unit Cost Is:" + "0");
		DirectGRNFunc.verifyClickingSaveLine();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameUDService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-General-User Defined-Dissimilar
	 * Stock and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForUDGeneralItemDissimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		DirectGRNFunc.closeToastIfDisplayed();
		webDB.navigateToRefresh();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineCost, DirectGrnPageLocators.GrnLineCostInput, "0");
		logs.logging("INFO", "For Free Of Cost, Entered Unit Cost Is:" + "0");
		DirectGRNFunc.verifyClickingSaveLine();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffUDGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-Material-User Defined-Dissimilar
	 * Stock and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForUDMaterialItemDissimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPM"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		DirectGRNFunc.closeToastIfDisplayed();
		webDB.navigateToRefresh();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(DirectGrnPageLocators.GrnLineCost, DirectGrnPageLocators.GrnLineCostInput, "0");
		logs.logging("INFO", "For Free Of Cost, Entered Unit Cost Is:" + "0");
		DirectGRNFunc.verifyClickingSaveLine();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffUDRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-General-Rate Contract-Same Stock
	 * and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForRCGeneralItemSimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCGeneral"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-Material-Rate Contract-Same Stock
	 * and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForRCMaterialItemSimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCRawMat"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-Service-Rate Contract-Same Stock
	 * and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForRCServiceItemSimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCService"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSameRCService"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSameRCService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-General-Rate Contract-Dissimilar
	 * Stock and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForRCGeneralItemDissimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffRCGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-Material-Rate Contract-Dissimilar
	 * Stock and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForRCMaterialItemDissimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffRCRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-General-Price List-Same Stock and
	 * Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForPLGeneralItemSimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLGeneral"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSamePLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-Material-Price List-Same Stock and
	 * Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForPLMaterialItemSimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLRawMat"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSamePLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-Service-Price List-Same Stock and
	 * Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForPLServiceItemSimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLService"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpSamePLService"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpSamePLService"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-General-Price List-Dissimilar Stock
	 * and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForPLGeneralItemDissimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffPLGeneral"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This Methods Is Used To Verify Direct GRN-Material-Price List-Dissimilar
	 * Stock and Supplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyDirectGRNFOCForPLMaterialItemDissimilarUOM() {
		String BillNo = commonFunc.getDataFromTestDataSheet("ImportGRNBillNoText")
				+ RandomStringUtils.randomAlphanumeric(3);
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
		String Today = date.format(formatter);
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.naviagteGrnPage();
		GRNFunc.verifyGrnAdd();
		GRNFunc.verifyGrnAddDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GRNPG"), commonFunc.getDataFromTestDataSheet("WalmartSupId"),
				commonFunc.getDataFromTestDataSheet("SupplierBillNumber"));
		GRN2Func.verifyGrnAddLineDetails(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"));
		GRN2Func.multipleLotandSerialWithGeneralType(webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"),
				webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"));
		DirectGRNFunc.lotSerialQtyApportion();
		GRN2Func.clickImportLRAndfillBillNo(BillNo, Today);
		GRN2Func.verifyClickFOCAndSaveLines();
		GRN2Func.overAllSaveAndPostForExcess();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRN2Func.verifyGRNDocumentApprovedStatus();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("DirGRNImpDiffPLRawMat"),
				webDB.getDataFromProperties("ImportPoToGrnWithGEFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-User Defined-Same Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMGeneralUDWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDGeneralSameUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForUDItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-User Defined-Same Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMGeneralUDWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDGeneralSameUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForUDItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-User Defined-Same Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMMaterialUDWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDMaterialSameUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForUDItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-User Defined-Same Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMMaterialUDWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDMaterialSameUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForUDItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-User Defined-Diff Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMGeneralUDWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDGeneralDiffUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForUDItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-User Defined-Diff Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMGeneralUDWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDGeneralDiffUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForUDItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-User Defined-Diff Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMMaterialUDWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDMaterialDiffUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForUDItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-User Defined-Diff Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMMaterialUDWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDMaterialDiffUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForUDItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-Rate Contract-Same Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMGeneralRCWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCGeneralSameUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForRCItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-Rate Contract-Same Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMGeneralRCWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCGeneralSameUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForRCItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-Rate Contract-Same Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMMaterialRCWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCMaterialSameUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForRCItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-Rate Contract-Same Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMMaterialRCWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCMaterialSameUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForRCItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-Price List-Same Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMGeneralPLWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLGeneralSameUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForPLItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-Price List-Same Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMGeneralPLWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLGeneralSameUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForPLItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-Price List-Same Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMMaterialPLWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLMaterialSameUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForPLItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-Price List-Same Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMMaterialPLWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLMaterialSameUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForPLItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-Rate Contract-Diff Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMGeneralRCWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCGeneralDiffUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForRCItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-Rate Contract-Diff Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMGeneralRCWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCGeneralDiffUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForRCItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-Rate Contract-Diff Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMMaterialRCWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCMaterialDiffUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForRCItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-Rate Contract-Diff Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMMaterialRCWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCMaterialDiffUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForRCItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-Price List-Diff Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMGeneralPLWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLGeneralDiffUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForPLItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost General-Price List-Diff Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMGeneralPLWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLGeneralDiffUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForPLItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-Price List-Diff Stock and
	 * Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMMaterialPLWithInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLMaterialDiffUOMWI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForPLItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Material-Price List-Diff Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMMaterialPLWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLMaterialDiffUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForPLItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Service-User Defined-Same Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMServiceUDWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDServiceSameUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForUDItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Service-Rate Contract-Same Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMServiceRCWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCServiceSameUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForRCItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Service-Price List-Same Stock and
	 * Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMServicePLWithoutInspectionGEFOC() {
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLServiceSameUOMWOI");
		String FileName = webDB.getDataFromProperties("Purchase-FocFileName");
		String UnitCost = null;
		GRNFunc.verifyStockQtyAndCostForQualityWh(SheetName, FileName);
		GRNFunc.verifyStockQtyAndCostForSourceWH(SheetName, FileName);
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			UnitCost = itemmultiple[i][6];
		}
		POfunc.verifyDirectPODomesticSameWithAndWithoutInspectionForPLItemCustomizeFOC(
				commonFunc.getDataFromTestDataSheet("L0001"), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"), SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, "0");
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingPoNumber();
		gateEntryPageFunc.verifygateEntryDetailsPo();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButton();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(SheetName, FileName);
		DirectGRNFunc.lotSerialQtyApportion();
		DirectGRNFunc.overAllSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getGrnDocNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(SheetName, FileName);
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForDifferentLoc();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(SheetName, FileName);
		excelUtils.writeDataInExcel(FileName, SheetName, 3, 6, UnitCost);
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Stock
	 * Transfer Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 * @return booelan flag
	 */
	public boolean verifyFOCForDirectPODomesticSuplrStockTransferItemSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("DomesticPoFOCUDStkTrnSameUOM");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-User Defined-Same Stock
	 * and Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMStockTransferUDWithoutInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWOI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-User Defined-Same Stock
	 * and Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMStockTransferUDWithInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCUDStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-User Defined-Diff Stock
	 * and Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMStockTransferUDWithoutInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWOI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-User Defined-Diff Stock
	 * and Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMStockTransferUDWithInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCUDStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-Rate Contract-Same
	 * Stock and Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMStockTransferRCWithoutInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWOI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				commonFunc.verifyAddTableSpecs(PurchaseOrderPageLocators.PoQtyFieldClick,
						PurchaseOrderPageLocators.PoQtyInput, Quantity);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-Rate Contract-Same
	 * Stock and Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMStockTransferRCWithInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				commonFunc.verifyAddTableSpecs(PurchaseOrderPageLocators.PoQtyFieldClick,
						PurchaseOrderPageLocators.PoQtyInput, Quantity);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCRCStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-Price List-Same Stock
	 * and Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMStockTransferPLWithoutInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWOI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				commonFunc.verifyAddTableSpecs(PurchaseOrderPageLocators.PoQtyFieldClick,
						PurchaseOrderPageLocators.PoQtyInput, Quantity);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-Price List-Same Stock
	 * and Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticSameUOMStockTransferPLWithInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("PuPLhase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				commonFunc.verifyAddTableSpecs(PurchaseOrderPageLocators.PoQtyFieldClick,
						PurchaseOrderPageLocators.PoQtyInput, Quantity);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCPLStkTrnSameUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-Rate Contract-Diff
	 * Stock and Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMStockTransferRCWithoutInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWOI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-Rate Contract-Diff
	 * Stock and Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMStockTransferRCWithInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCRCStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-Price List-Diff Stock
	 * and Supplier UOM - Without Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMStockTransferPLWithoutInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWOI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWOI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * This method used to verify PO to GRN -With Quality Enabled (Domestic
	 * Supplier)-With Gate Entry&Free Of Cost Stock Transfer-Price List-Diff Stock
	 * and Supplier UOM - With Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public boolean verifyPoToGRNDomesticDiffUOMStockTransferPLWithInspectionGEFOC() {
		GRNFunc.verifyStockQtyAndCostForQualityWh(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.verifyStockQtyAndCostForSourceWH(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		POfunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0007"),
				commonFunc.getDataFromTestDataSheet("MagaPvtLtd"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		POfunc.verifyCreateDirectPO();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ webDB.getDataFromProperties("Purchase-FocFileName");
		String SheetName = webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWI");
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 3; i < itemmultiple.length; i++) {
			String item = itemmultiple[i][1];
			if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
				String Quantity = itemmultiple[i][5];
				String UnitCost = itemmultiple[i][6];
				String LineAmount = itemmultiple[i][7];
				String TradePercentage = itemmultiple[i][8];
				String SplDiscPercentage = itemmultiple[i][9];
				String CashDiscPercentage = itemmultiple[i][10];
				String TradeAssblValue = itemmultiple[i][11];
				String TradeAmt = itemmultiple[i][12];
				String SDAssemblValue = itemmultiple[i][13];
				String SDAmt = itemmultiple[i][14];
				String CDAssemblValue = itemmultiple[i][15];
				String CDAmt = itemmultiple[i][16];
				String TaxPercent = itemmultiple[i][20];
				String cgst = itemmultiple[i][21];
				String sgst = itemmultiple[i][22];
				String Igst = itemmultiple[i][23];
				String Utgst = itemmultiple[i][24];
				String CessPercent = itemmultiple[i][25];
				String Cess = itemmultiple[i][26];
				String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");
				POfunc.verifyCreateItemInPo(item);
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
				commonFunc.verifyAddTableLovDetails(PurchaseOrderPageLocators.HsnCode,
						PurchaseOrderPageLocators.HsnCodeLovButton, HsnCode, PurchaseOrderPageLocators.HsnCodeInput);
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyLineAmountValidationAndPom(item, LineAmount);
				POfunc.navigateToLineDetailPage(item);
				POfunc.verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.verifyTaxValidation(item, TaxPercent, cgst, sgst, Igst, Utgst, CessPercent, Cess);
				POfunc.verifyDiscountCalculationVerification(item, TradeAssblValue, TradeAmt, SDAssemblValue, SDAmt,
						CDAssemblValue, CDAmt);
				POfunc.navigateToLineDetailPage(item);
				POfunc.switchToLineDetailFrame();
				POfunc.verifyNavigateLineDetailTwo();
				POfunc.enableFreeOfCostCheckbox();
				POfunc.verifyAddItemInPOWithQuantityAndUnitCost(Quantity, "0");
				POfunc.verifyTickIconcreateItem();
				POfunc.verifyAssertionBothValues("0", dbCommonFunc.poDetails(VariableHandler.getPONumber(), item,
						commonFunc.getDataFromTestDataSheet("LINE_AMT")));
				POfunc.verifyTaxValidation(item, "0", "0", "0", "0", "0", "0", "0");
				POfunc.verifyDiscountCalculationVerification(item, "0", "0", "0", "0", "0", "0");
			}
		}
		POfunc.verifyHeaderTickAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approveFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			String DocumentNo = commonFunc.approveDocumentsWithDocumentNumber(webDB.getDataFromProperties("RoauBu"),
					VariableHandler.getPONumber(), webDB.getDataFromProperties("HariEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			approveFunc.ExtractStocktransferDocNumber(DocumentNo);
		}
		commonFunc.navigatePurchaseOrderPage();
		POfunc.verifyValidatePoStatus(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		GRNFunc.verifyToStockTransferSaleOrderDocumentPost();
		saleOrderFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		GRNFunc.stockTransferAfterForwardToApproval();
		GRNFunc.verifyToPostStockTransferDocumentForSalesInvoice();
		GRNFunc.selectAllPendingSOAndClickCreateSIForHodUser();
		GRNFunc.verifyToPostSalesInvoiceDocumentWithPrefix();
		GRNFunc.stockTransferAfterForwardToApprovalForInvoiceDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.verifyToCreateGateEntryFromPendingSTNumber();
		gateEntryPageFunc.verifyCreatingGetextForGateEntryDocNumber();
		commonFunc.checkRefreshCellsDisappear();
		webDB.sendTextToAnElement(GateEntryPageLocators.DriverNameInput,
				commonFunc.getDataFromTestDataSheet("GateKeeperName"), ElementType.Xpath);
		gateEntryPageFunc.verifyToOverAllSaveAndPostTheGateEntryDocument();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigateToGateEntryDocument();
		gateEntryPageFunc.searchAndVerifyStatusOfGENo(VariableHandler.getGateEntryNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		commonFunc.naviagteGrnPage();
		GRNFunc.clickPendingGEAndSearchGENo();
		GRNFunc.clickSelectAllGEButtonWithSt();
		GRNFunc.createGrnWithGeButton();
		GRNFunc.clickOkButton();
		GRNFunc.multipleLotAndSerialItemWithPoToMrvSheet(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.lotSerialQtyApportion();
		GRNFunc.overAllSaveAndPost(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.waitingForApproval();
		GRNFunc.verifyExpectedStockQtAndCostyForQualityWH(webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		GRNFunc.afterVerifyTheStatusDocumentMoveToQcOrMrvForStockTransfer();
		GRNFunc.verifyExpectedStockQtAndCostyWithDBValuesAfterMRVForSourceWH(
				webDB.getDataFromProperties("PoToGRNFOCPLStkTrnDiffUOMWI"),
				webDB.getDataFromProperties("Purchase-FocFileName"));
		flag = commonFunc.logoutUser();
		return flag;
	}
}
