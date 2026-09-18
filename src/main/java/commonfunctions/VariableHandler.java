package commonfunctions;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks January 15, 2025
 */
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VariableHandler {
	private static ThreadLocal<String> PONumber = new ThreadLocal<>();
	private static ThreadLocal<String> PRNumber = new ThreadLocal<>();
	private static ThreadLocal<String> PRClsShrt_DocNumber = new ThreadLocal<>();
	private static ThreadLocal<String[][]> Testdata = ThreadLocal.withInitial(() -> null);
	public static String testdataDir = System.getProperty("user.dir") + File.separator + "testdata" + File.separator;
	private static ThreadLocal<Boolean> Approvalflag = new ThreadLocal<>();
	private static ThreadLocal<String> POTotalAmount = new ThreadLocal<>();
	private static ThreadLocal<Boolean> Registerflag = new ThreadLocal<>();
	private static ThreadLocal<String> SONumber = new ThreadLocal<>();
	private static ThreadLocal<String> SOTotalAmount = new ThreadLocal<>();
	private static ThreadLocal<BigDecimal> LineAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> DiscountAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> TotalTaxAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> TotalLineAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> TotalDiscountAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> IndividualTaxAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<String> POANumber = new ThreadLocal<>();
	private static ThreadLocal<BigDecimal> POALineAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> POADiscountAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> TotalPOADiscountAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<String> EmpID = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> EmpFirstName = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLastName = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSalution = new ThreadLocal<>();
	private static ThreadLocal<String> EmpGender = new ThreadLocal<>();
	private static ThreadLocal<String> EmpDOB = new ThreadLocal<>();
	private static ThreadLocal<String> EmpDOJ = new ThreadLocal<>();
	private static ThreadLocal<String> EmpShift = new ThreadLocal<>();
	private static ThreadLocal<String> EmpCalendar = new ThreadLocal<>();
	private static ThreadLocal<String> EmpZone = new ThreadLocal<>();
	private static ThreadLocal<String> EmpCompanyCategory = new ThreadLocal<>();
	private static ThreadLocal<String> EmpAccountCategory = new ThreadLocal<>();
	private static ThreadLocal<String> EmpPayrollGroup = new ThreadLocal<>();
	private static ThreadLocal<String> EmpNoticePeriod = new ThreadLocal<>();
	private static ThreadLocal<String> EmpContactName = new ThreadLocal<>();
	private static ThreadLocal<String> EmpContactRelationship = new ThreadLocal<>();
	private static ThreadLocal<String> EmpContactMobileNo = new ThreadLocal<>();
	private static ThreadLocal<String> EmpContactAddress = new ThreadLocal<>();
	private static ThreadLocal<String> EmpContactCity = new ThreadLocal<>();
	private static ThreadLocal<String> EmpContactState = new ThreadLocal<>();
	private static ThreadLocal<String> EmpQualification = new ThreadLocal<>();
	private static ThreadLocal<String> EmpQualBranchOfStudy = new ThreadLocal<>();
	private static ThreadLocal<String> EmpQualInstitute = new ThreadLocal<>();
	private static ThreadLocal<String> EmpQualTotalYears = new ThreadLocal<>();
	private static ThreadLocal<String> EmpQualYearOfPassing = new ThreadLocal<>();
	private static ThreadLocal<String> EmpQualGradeClass = new ThreadLocal<>();
	private static ThreadLocal<String> EmpQualAGG = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSkill = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSkillLevel = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSalaryDepartment = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSalaryDesignation = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSalaryGrade = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSalaryAdjmntElement = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSalaryAdjmntStartYear = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSalaryAdjmntPeriod = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSalaryAdjmntTotalPeriod = new ThreadLocal<>();
	private static ThreadLocal<String> EmpSalaryAdjmntPeriodAmnt = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblLeave = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblDays = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblEvery = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblEligibileDays = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblRoundOff = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblMaxCarryFreq = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblMaxCarryDay = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblEffYear = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblMaxDay = new ThreadLocal<>();
	private static ThreadLocal<String> EmpLeaveElgblMinDay = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> EmpSetupScreenGreenFlagLeaveIDs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<Boolean> AddEmployeDefaultExecution = ThreadLocal.withInitial(() -> false);
	private static ThreadLocal<Boolean> CheckEmployeeDataPermissionDefaultExecution = ThreadLocal
			.withInitial(() -> false);
	private static ThreadLocal<String> GrnDocNumber = new ThreadLocal<>();
	private static ThreadLocal<String> PRLISTNumber = new ThreadLocal<>();
	private static ThreadLocal<String> OpenSONumber = new ThreadLocal<>();
	private static ThreadLocal<String> EmpMaritalStatus = new ThreadLocal<>();
	private static ThreadLocal<String> SIdocno = new ThreadLocal<>();
	private static ThreadLocal<String> IDNumber = new ThreadLocal<>();
	private static ThreadLocal<String> MrvNumber = new ThreadLocal<>();
	private static ThreadLocal<String> PBNumber = new ThreadLocal<>();
	private static ThreadLocal<String> BpvNumber = new ThreadLocal<>();
	private static ThreadLocal<String> OpenSOPriceAmount = new ThreadLocal<>();
	private static ThreadLocal<String> BankReceiptVouNo = new ThreadLocal<>();
	private static ThreadLocal<BigDecimal> TotalSOLineAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SOLineAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SODiscountAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> TotalSODiscountAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SOGrossAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> TotalSOGrossAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<String> PI_DocNo = new ThreadLocal<>();
	private static ThreadLocal<String> OSOAnumber = new ThreadLocal<String>();
	private static ThreadLocal<String> AmendmentDocNumber = new ThreadLocal<>();
	private static ThreadLocal<String> MRPNumber = new ThreadLocal<>();
	private static ThreadLocal<String> ProductionOrderNumber = new ThreadLocal<>();
	private static ThreadLocal<String[][]> DBTestdata = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<Map<String, String>> StockQty = ThreadLocal.withInitial(HashMap::new);
	private static ThreadLocal<Map<String, String>> StockCost = ThreadLocal.withInitial(HashMap::new);
	private static ThreadLocal<String> GrnStatus = new ThreadLocal<>();
	private static ThreadLocal<String> MRNumber = new ThreadLocal<>();
	private static ThreadLocal<String> SimItemName = new ThreadLocal<>();
	private static ThreadLocal<String> DisItemName = new ThreadLocal<>();
	private static ThreadLocal<String> AmdSimItemName = new ThreadLocal<>();
	private static ThreadLocal<String> AmdDisItemName = new ThreadLocal<>();
	private static ThreadLocal<String> MIVNumber = new ThreadLocal<>();
	private static ThreadLocal<Boolean> SaleOrderToMRPExecution = ThreadLocal.withInitial(() -> false);
	private static ThreadLocal<String> MaterialRequestNo = new ThreadLocal<>();
	private static ThreadLocal<String> ProductionCompletionOrderNo = new ThreadLocal<>();
	private static ThreadLocal<String[][]> ProcessAndWarehousesBOM = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String[][]> ProcessAndProcessType = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> CustomerSaleGSTType = new ThreadLocal<>();
	private static ThreadLocal<BigDecimal> SOChargeAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> TotalSOChargeAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<String> AdvReceiptDocNumber = new ThreadLocal<>();
	private static ThreadLocal<String> AdvReceiptDocNumberInSO = new ThreadLocal<>();
	private static ThreadLocal<String> AdvReceiptsBalanceAmountInSO = new ThreadLocal<>();
	private static ThreadLocal<String> AdvReceiptsToBeReceivedInSO = new ThreadLocal<>();
	private static ThreadLocal<String> BankReceiptDocNumber = new ThreadLocal<>();
	private static ThreadLocal<BigDecimal> SOSpecAssesableVal = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SOCashAssesableVal = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SOTradeDiscountAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SOCashDiscountAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SOSpecialDiscountAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SODiscountAmountPerQty = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SOTaxAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> SOTotExpOrderAmount = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<Map<String, String>> AfterStockQty = ThreadLocal.withInitial(HashMap::new);
	private static ThreadLocal<Map<String, String>> AfterStockCost = ThreadLocal.withInitial(HashMap::new);
	private static ThreadLocal<String> DCNumber = new ThreadLocal<>();
	private static ThreadLocal<String> PermissionDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> BOMNumber = new ThreadLocal<>();
	private static ThreadLocal<String> GateEntryNumber = new ThreadLocal<>();
	private static ThreadLocal<String> SalesOrderDocuNumber = new ThreadLocal<>();
	private static ThreadLocal<String> SalesInvoiceNumber = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> AllDocumentNumber = new ThreadLocal<ArrayList<String>>();
	private static ThreadLocal<String> InspectionPlanNumber = new ThreadLocal<>();
	private static ThreadLocal<String> IDAlertMessage = new ThreadLocal<>();
	private static ThreadLocal<String> ReceiptLocator = new ThreadLocal<>();
	private static ThreadLocal<String> MRVDocumentNumber = new ThreadLocal<>();
	private static ThreadLocal<String> ScoNumber = new ThreadLocal<>();
	private static ThreadLocal<String> ScoGrnNumber = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> TotalProductionProcess = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> RfqDocNum = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> PONumbers = new ThreadLocal<ArrayList<String>>();
	private static ThreadLocal<String> ScoPurchaseNumber = new ThreadLocal<>();
	private static ThreadLocal<BigDecimal> RevisiedValue = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<BigDecimal> TaxPercent = ThreadLocal.withInitial(() -> BigDecimal.ZERO);
	private static ThreadLocal<String> PaymentTermAdvMatPercentage = new ThreadLocal<>();
	private static ThreadLocal<String> PaymentTermAdvTaxPercentage = new ThreadLocal<>();
	private static ThreadLocal<String> HeaderCurrency = new ThreadLocal<>();
	private static ThreadLocal<String> ExpectedStockCost = new ThreadLocal<>();
	private static ThreadLocal<String> ExpectedStockQty = new ThreadLocal<>();
	private static ThreadLocal<String[][]> ExpectedStockQty1 = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<ArrayList<String>> ExpectedStockQty2 = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> EncashmentDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> CompOffDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> EmpProfileNo = new ThreadLocal<>();
	private static ThreadLocal<String> EmpBatchNo = new ThreadLocal<>();
	private static ThreadLocal<String> LeaveRequestNo = new ThreadLocal<>();
	private static ThreadLocal<String> CasualLeaveBalance = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> OpenPoNumber = new ThreadLocal<>();
	private static ThreadLocal<String> LeaveEntryDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> SOAmdDocNumber = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> ShipSetItem = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> ShipSetItemFromDB = new ThreadLocal<>();
	private static ThreadLocal<String> CRdocno = new ThreadLocal<>();
	private static ThreadLocal<String> CNdocno = new ThreadLocal<>();
	private static ThreadLocal<String> GEdocno = new ThreadLocal<>();
	private static ThreadLocal<String> SIInvNo = new ThreadLocal<>();
	private static ThreadLocal<String> OpenPOAmendNumber = new ThreadLocal<>();
	private static ThreadLocal<String> OPODocNum = new ThreadLocal<>();
	private static ThreadLocal<String> GATEDocNum = new ThreadLocal<>();
	private static ThreadLocal<String> SsoDocNum = new ThreadLocal<>();
	private static ThreadLocal<String> OrderStockQty = new ThreadLocal<>();
	private static ThreadLocal<String> BeforeStockQty = new ThreadLocal<>();
	private static ThreadLocal<String> CSDocNumber = new ThreadLocal<>();
	private static ThreadLocal<String> PayrollDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> ApprovedSupplierNumber = new ThreadLocal<>();
	private static ThreadLocal<String> SOBNumber = new ThreadLocal<>();
	private static ThreadLocal<String> RWdocno = new ThreadLocal<>();
	private static ThreadLocal<String> RWCdocno = new ThreadLocal<>();
	private static ThreadLocal<String> DBdocno = new ThreadLocal<>();
	private static ThreadLocal<String> PriceListDocno = new ThreadLocal<>();
	private static ThreadLocal<String> CatDocno = new ThreadLocal<>();
	private static ThreadLocal<String> EmpAdjustmentVouNo = new ThreadLocal<>();
	private static ThreadLocal<String> SalesRegionText = new ThreadLocal<>();
	private static ThreadLocal<String> SalesZoneText = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> ProductionProcess = new ThreadLocal<>();
	private static ThreadLocal<Map<String, String>> ProductionProcessType = ThreadLocal.withInitial(HashMap::new);
	private static ThreadLocal<Map<String, String>> ProductionProcessItem = ThreadLocal.withInitial(HashMap::new);
	private static ThreadLocal<ArrayList<String>> MultipleOpenSODocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> CSAmdDocNumber = new ThreadLocal<>();
	private static ThreadLocal<String> SOMultiCloseShortDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> CSLineDocNumber = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> MultipleSODocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<ArrayList<String>> MultipleItems = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<ArrayList<String>> MultipleCatalogDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<ArrayList<String>> MultiplePriceListDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<Boolean> MultipleProcessflag = ThreadLocal.withInitial(() -> false);
	private static ThreadLocal<String> RawMaterialCost = new ThreadLocal<>();
	private static ThreadLocal<String> CompletionFgUnitCost = new ThreadLocal<>();
	private static ThreadLocal<String> LoanRequestDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> TDSDeclarationDocNo = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> MultiMRVDocNumber = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> MultiMRVDocStatus = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> MultipleCSLineDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> HeaderCurrencyAfterSaveTheLine = new ThreadLocal<>();
	private static ThreadLocal<String> StkAdjDocNumber = new ThreadLocal<>();
	private static ThreadLocal<String> oscdocno = new ThreadLocal<>();
	private static ThreadLocal<String> Revdocno = new ThreadLocal<>();
	private static ThreadLocal<String> SCOdocno = new ThreadLocal<>();
	private static ThreadLocal<String> EmpUpdateDocNo = new ThreadLocal<>();
	private static ThreadLocal<Boolean> EmpLeaveEntryAutoPost = ThreadLocal.withInitial(() -> false);
	private static ThreadLocal<Boolean> AutoPostMRVFlag = ThreadLocal.withInitial(() -> false);
	private static ThreadLocal<String> ProcessIDAsso = new ThreadLocal<>();
	private static ThreadLocal<String> ResourceGrp = new ThreadLocal<>();
	private static ThreadLocal<String> ManpowerResourceGrp = new ThreadLocal<>();
	private static ThreadLocal<String> SCOBatchQty = new ThreadLocal<>();
	private static ThreadLocal<String> SCOBatchCost = new ThreadLocal<>();
	private static ThreadLocal<String> SCOOrderQty = new ThreadLocal<>();
	private static ThreadLocal<String> SCOConvFact = new ThreadLocal<>();
	private static ThreadLocal<String> SCOProcUOM = new ThreadLocal<>();
	private static ThreadLocal<String> InspectionPlanDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> LastApprovalAPIHeaderText = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> MultipleSIDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> InspectionReqNumber = new ThreadLocal<>();
	private static ThreadLocal<String> MaterialReturnNumber = new ThreadLocal<>();
	private static ThreadLocal<String> OvertimeDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> InvoiceDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> BankPaymentOrReceiptDoc = new ThreadLocal<>();
	private static ThreadLocal<String> CashPaymentOrReceiptDoc = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> MultipleGRNDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<ArrayList<String>> MultiplePBDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> TravelRequestDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> TravelReimbursementDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> SCOSFcode = new ThreadLocal<>();
	private static ThreadLocal<String> SCOTarSFcode = new ThreadLocal<>();
	private static ThreadLocal<String> SCOAdocNo = new ThreadLocal<>();
	private static ThreadLocal<String> SSRDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> SSODocNo = new ThreadLocal<>();
	private static ThreadLocal<String> GeneralReimbursementDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> GRNLotNo = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> MultipleAdvRecDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> ResignationRequestDocNo = new ThreadLocal<>();
	private static ThreadLocal<String> MatchedAmt = new ThreadLocal<>();
	private static ThreadLocal<String> GRNAmt = new ThreadLocal<>();
	private static ThreadLocal<String> deviationAmt = new ThreadLocal<>();
	private static ThreadLocal<String> PBProcQty = new ThreadLocal<>();
	private static ThreadLocal<String> PBUnitcost = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> MultiplePoDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> PoAdvPaymentDocumentNO = new ThreadLocal<>();
	private static ThreadLocal<String> BPAmount = new ThreadLocal<>();
	private static ThreadLocal<String> SupGlAccount = new ThreadLocal<>();
	private static ThreadLocal<String> AdvPaymentDocNumber = new ThreadLocal<>();
	private static ThreadLocal<ArrayList<String>> MultiplePymtRecDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<ArrayList<String>> MultiplePODocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<ArrayList<String>> MultipleBankDocs = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<Boolean> SessionOpened = ThreadLocal.withInitial(() -> false);
	private static ThreadLocal<String> SessionID = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> HomePageURL = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> SignInUserName = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<String> EmployeeFullName = ThreadLocal.withInitial(() -> null);
	private static ThreadLocal<Boolean> SiteWorked = ThreadLocal.withInitial(() -> true);
	private static ThreadLocal<String> PriceBasis = new ThreadLocal<>();

	public static Boolean getSessionOpened() {
		return SessionOpened.get();
	}

	public static void setSessionOpened(Boolean data) {
		SessionOpened.set(data);
	}

	public static String getSignInUserName() {
		return SignInUserName.get();
	}

	public static void setSignInUserName(String data) {
		SignInUserName.set(data);
	}

	public static String getSessionID() {
		return SessionID.get();
	}

	public static void setSessionID(String data) {
		SessionID.set(data);
	}

	public static String getPONumber() {
		return PONumber.get();
	}

	public static void setPONumber(String data) {
		PONumber.set(data);
	}

	public static String getPRNumber() {
		return PRNumber.get();
	}

	public static void setPRNumber(String data) {
		PRNumber.set(data);
	}

	public static String[][] getTestData() {
		return Testdata.get();
	}

	public static void setTestData(String[][] data) {
		Testdata.set(data);
	}

	public static boolean getApprovalFlag() {
		return Approvalflag.get();
	}

	public static void setApprovalFlag(boolean flag) {
		Approvalflag.set(flag);
	}

	public static String getPRCloseShortDocNumber() {
		return PRClsShrt_DocNumber.get();
	}

	public static void setPRCloseShortDocNumber(String data) {
		PRClsShrt_DocNumber.set(data);
	}

	public static String getPOTotalAmount() {
		return POTotalAmount.get();
	}

	public static void setPOTotalAmount(String data) {
		POTotalAmount.set(data);
	}

	public static boolean getRegisterFlag() {
		return Registerflag.get();
	}

	public static void setRegisterFlag(boolean flag) {
		Registerflag.set(flag);
	}

	public static BigDecimal getLineAmount() {
		return LineAmount.get();
	}

	public static void setLineAmount(BigDecimal data) {
		LineAmount.set(data);
	}

	public static BigDecimal getDiscountAmount() {
		return DiscountAmount.get();
	}

	public static void setDiscountAmount(BigDecimal data) {
		DiscountAmount.set(data);
	}

	public static BigDecimal getTaxAmount() {
		return TotalTaxAmount.get();
	}

	public static void setTaxAmount(BigDecimal data) {
		TotalTaxAmount.set(data);
	}

	public static BigDecimal getTotalLineAmount() {
		return TotalLineAmount.get();
	}

	public static void setTotalLineAmount(BigDecimal data) {
		TotalLineAmount.set(data);
	}

	public static BigDecimal getTotalDiscountAmount() {
		return TotalDiscountAmount.get();
	}

	public static void setTotalDiscountAmount(BigDecimal data) {
		TotalDiscountAmount.set(data);
	}

	public static String getSONumber() {
		return SONumber.get();
	}

	public static void setSONumber(String data) {
		SONumber.set(data);
	}

	public static String getSOTotalAmount() {
		return SOTotalAmount.get();
	}

	public static void setSOTotalAmount(String data) {
		SOTotalAmount.set(data);
	}

	public static BigDecimal getIndividualAmount() {
		return IndividualTaxAmount.get();
	}

	public static void setIndividualAmount(BigDecimal data) {
		IndividualTaxAmount.set(data);
	}

	public static String getPOANumber() {
		return POANumber.get();
	}

	public static void setPOANumber(String data) {
		POANumber.set(data);
	}

	public static BigDecimal getPOALineAmount() {
		return POALineAmount.get();
	}

	public static void setPOALineAmount(BigDecimal data) {
		POALineAmount.set(data);
	}

	public static BigDecimal getTotalPOADiscountAmount() {
		return TotalPOADiscountAmount.get();
	}

	public static void setTotalPOADiscountAmount(BigDecimal data) {
		TotalPOADiscountAmount.set(data);
	}

	public static BigDecimal getPOADiscountAmount() {
		return POADiscountAmount.get();
	}

	public static void setPOADiscountAmount(BigDecimal data) {
		POADiscountAmount.set(data);
	}

	public static void setEmpID(String data) {
		EmpID.set(data);
	}

	public static void setEmpFirstName(String data) {
		EmpFirstName.set(data);
	}

	public static void setEmpLastName(String data) {
		EmpLastName.set(data);
	}

	public static void setEmpSalution(String data) {
		EmpSalution.set(data);
	}

	public static void setEmpGender(String data) {
		EmpGender.set(data);
	}

	public static void setEmpDOB(String data) {
		EmpDOB.set(data);
	}

	public static void setEmpDOJ(String data) {
		EmpDOJ.set(data);
	}

	public static void setEmpShift(String data) {
		EmpShift.set(data);
	}

	public static void setEmpCalendar(String data) {
		EmpCalendar.set(data);
	}

	public static void setEmpZone(String data) {
		EmpZone.set(data);
	}

	public static void setEmpCompanyCategory(String data) {
		EmpCompanyCategory.set(data);
	}

	public static void setEmpAccountCategory(String data) {
		EmpAccountCategory.set(data);
	}

	public static void setEmpPayrollGroup(String data) {
		EmpPayrollGroup.set(data);
	}

	public static void setEmpNoticePeriod(String data) {
		EmpNoticePeriod.set(data);
	}

	public static String getEmpID() {
		return EmpID.get();
	}

	public static String getEmpFirstName() {
		return EmpFirstName.get();
	}

	public static String getEmpLastName() {
		return EmpLastName.get();
	}

	public static String getEmpSalution() {
		return EmpSalution.get();
	}

	public static String getEmpGender() {
		return EmpGender.get();
	}

	public static String getEmpDOB() {
		return EmpDOB.get();
	}

	public static String getEmpDOJ() {
		return EmpDOJ.get();
	}

	public static String getEmpShift() {
		return EmpShift.get();
	}

	public static String getEmpCalendar() {
		return EmpCalendar.get();
	}

	public static String getEmpZone() {
		return EmpZone.get();
	}

	public static String getEmpCompanyCategory() {
		return EmpCompanyCategory.get();
	}

	public static String getEmpAccountCategory() {
		return EmpAccountCategory.get();
	}

	public static String getEmpPayrollGroup() {
		return EmpPayrollGroup.get();
	}

	public static String getEmpNoticePeriod() {
		return EmpNoticePeriod.get();
	}

	public static void setEmpContactName(String value) {
		EmpContactName.set(value);
	}

	public static void setEmpContactRelationship(String value) {
		EmpContactRelationship.set(value);
	}

	public static void setEmpContactMobileNo(String value) {
		EmpContactMobileNo.set(value);
	}

	public static void setEmpContactAddress(String value) {
		EmpContactAddress.set(value);
	}

	public static void setEmpContactCity(String value) {
		EmpContactCity.set(value);
	}

	public static void setEmpContactState(String value) {
		EmpContactState.set(value);
	}

	public static void setEmpQualification(String value) {
		EmpQualification.set(value);
	}

	public static void setEmpQualBranchOfStudy(String value) {
		EmpQualBranchOfStudy.set(value);
	}

	public static void setEmpQualInstitute(String value) {
		EmpQualInstitute.set(value);
	}

	public static void setEmpQualTotalYears(String value) {
		EmpQualTotalYears.set(value);
	}

	public static void setEmpQualYearOfPassing(String value) {
		EmpQualYearOfPassing.set(value);
	}

	public static void setEmpQualGradeClass(String value) {
		EmpQualGradeClass.set(value);
	}

	public static void setEmpQualAGG(String value) {
		EmpQualAGG.set(value);
	}

	public static void setEmpSkill(String value) {
		EmpSkill.set(value);
	}

	public static void setEmpSkillLevel(String value) {
		EmpSkillLevel.set(value);
	}

	public static String getEmpContactName() {
		return EmpContactName.get();
	}

	public static String getEmpContactRelationship() {
		return EmpContactRelationship.get();
	}

	public static String getEmpContactMobileNo() {
		return EmpContactMobileNo.get();
	}

	public static String getEmpContactAddress() {
		return EmpContactAddress.get();
	}

	public static String getEmpContactCity() {
		return EmpContactCity.get();
	}

	public static String getEmpContactState() {
		return EmpContactState.get();
	}

	public static String getEmpQualification() {
		return EmpQualification.get();
	}

	public static String getEmpQualBranchOfStudy() {
		return EmpQualBranchOfStudy.get();
	}

	public static String getEmpQualInstitute() {
		return EmpQualInstitute.get();
	}

	public static String getEmpQualTotalYears() {
		return EmpQualTotalYears.get();
	}

	public static String getEmpQualYearOfPassing() {
		return EmpQualYearOfPassing.get();
	}

	public static String getEmpQualGradeClass() {
		return EmpQualGradeClass.get();
	}

	public static String getEmpQualAGG() {
		return EmpQualAGG.get();
	}

	public static String getEmpSkill() {
		return EmpSkill.get();
	}

	public static String getEmpSkillLevel() {
		return EmpSkillLevel.get();
	}

	public static String getEmpSalaryDepartment() {
		return EmpSalaryDepartment.get();
	}

	public static void setEmpSalaryDepartment(String data) {
		EmpSalaryDepartment.set(data);
	}

	public static String getEmpSalaryDesignation() {
		return EmpSalaryDesignation.get();
	}

	public static void setEmpSalaryDesignation(String data) {
		EmpSalaryDesignation.set(data);
	}

	public static String getEmpSalaryGrade() {
		return EmpSalaryGrade.get();
	}

	public static void setEmpSalaryGrade(String data) {
		EmpSalaryGrade.set(data);
	}

	public static String getEmpSalaryAdjmntElement() {
		return EmpSalaryAdjmntElement.get();
	}

	public static void setEmpSalaryAdjmntElement(String data) {
		EmpSalaryAdjmntElement.set(data);
	}

	public static String getEmpSalaryAdjmntStartYear() {
		return EmpSalaryAdjmntStartYear.get();
	}

	public static void setEmpSalaryAdjmntStartYear(String data) {
		EmpSalaryAdjmntStartYear.set(data);
	}

	public static String getEmpSalaryAdjmntPeriod() {
		return EmpSalaryAdjmntPeriod.get();
	}

	public static void setEmpSalaryAdjmntPeriod(String data) {
		EmpSalaryAdjmntPeriod.set(data);
	}

	public static String getEmpSalaryAdjmntTotalPeriod() {
		return EmpSalaryAdjmntTotalPeriod.get();
	}

	public static void setEmpSalaryAdjmntTotalPeriod(String data) {
		EmpSalaryAdjmntTotalPeriod.set(data);
	}

	public static String getEmpSalaryAdjmntPeriodAmnt() {
		return EmpSalaryAdjmntPeriodAmnt.get();
	}

	public static void setEmpSalaryAdjmntPeriodAmnt(String data) {
		EmpSalaryAdjmntPeriodAmnt.set(data);
	}

	public static boolean getAddEmployeDefaultExecution() {
		return AddEmployeDefaultExecution.get();
	}

	public static void setAddEmployeDefaultExecution(boolean flag) {
		AddEmployeDefaultExecution.set(flag);
	}

	public static ArrayList<String> getEmpSetupScreenGreenFlagLeaveIDs() {
		return EmpSetupScreenGreenFlagLeaveIDs.get();
	}

	public static void setEmpSetupScreenGreenFlagLeaveIDs(ArrayList<String> data) {
		EmpSetupScreenGreenFlagLeaveIDs.set(data);
	}

	public static String getEmpLeaveElgblLeave() {
		return EmpLeaveElgblLeave.get();
	}

	public static void setEmpLeaveElgblLeave(String data) {
		EmpLeaveElgblLeave.set(data);
	}

	public static String getEmpLeaveElgblDays() {
		return EmpLeaveElgblDays.get();
	}

	public static void setEmpLeaveElgblDays(String data) {
		EmpLeaveElgblDays.set(data);
	}

	public static String getEmpLeaveElgblEvery() {
		return EmpLeaveElgblEvery.get();
	}

	public static void setEmpLeaveElgblEvery(String data) {
		EmpLeaveElgblEvery.set(data);
	}

	public static String getEmpLeaveElgblEligibileDays() {
		return EmpLeaveElgblEligibileDays.get();
	}

	public static void setEmpLeaveElgblEligibileDays(String data) {
		EmpLeaveElgblEligibileDays.set(data);
	}

	public static String getEmpLeaveElgblRoundOff() {
		return EmpLeaveElgblRoundOff.get();
	}

	public static void setEmpLeaveElgblRoundOff(String data) {
		EmpLeaveElgblRoundOff.set(data);
	}

	public static String getEmpLeaveElgblMaxCarryFreq() {
		return EmpLeaveElgblMaxCarryFreq.get();
	}

	public static void setEmpLeaveElgblMaxCarryFreq(String data) {
		EmpLeaveElgblMaxCarryFreq.set(data);
	}

	public static String getEmpLeaveElgblMaxCarryDay() {
		return EmpLeaveElgblMaxCarryDay.get();
	}

	public static void setEmpLeaveElgblMaxCarryDay(String data) {
		EmpLeaveElgblMaxCarryDay.set(data);
	}

	public static String getEmpLeaveElgblEffYear() {
		return EmpLeaveElgblEffYear.get();
	}

	public static void setEmpLeaveElgblEffYear(String data) {
		EmpLeaveElgblEffYear.set(data);
	}

	public static String getEmpLeaveElgblMaxDay() {
		return EmpLeaveElgblMaxDay.get();
	}

	public static void setEmpLeaveElgblMaxDay(String data) {
		EmpLeaveElgblMaxDay.set(data);
	}

	public static String getEmpLeaveElgblMinDay() {
		return EmpLeaveElgblMinDay.get();
	}

	public static void setEmpLeaveElgblMinDay(String data) {
		EmpLeaveElgblMinDay.set(data);
	}

	public static boolean getCheckEmployeeDataPermissionDefaultExecution() {
		return CheckEmployeeDataPermissionDefaultExecution.get();
	}

	public static void setCheckEmployeeDataPermissionDefaultExecution(boolean flag) {
		CheckEmployeeDataPermissionDefaultExecution.set(flag);
	}

	public static String getGrnDocNumber() {
		return GrnDocNumber.get();
	}

	public static void setGrnDocNumber(String data) {
		GrnDocNumber.set(data);
	}

	public static String getPRLISTNumber() {
		return PRLISTNumber.get();
	}

	public static void setPRLISTNumber(String data) {
		PRLISTNumber.set(data);
	}

	public static String getOpenSONumber() {
		return OpenSONumber.get();
	}

	public static void setOpenSONumber(String data) {
		OpenSONumber.set(data);
	}

	public static String getEmpMaritalStatus() {
		return EmpMaritalStatus.get();
	}

	public static void setEmpMaritalStatus(String flag) {
		EmpMaritalStatus.set(flag);
	}

	public static String getSIdocno() {
		return SIdocno.get();
	}

	public static void setSIdocno(String Data) {
		SIdocno.set(Data);
	}

	public static String getIDNumber() {
		return IDNumber.get();
	}

	public static void setIDNumber(String data) {
		IDNumber.set(data);
	}

	public static String getMrvNumber() {
		return MrvNumber.get();
	}

	public static void setMrvNumber(String data) {
		MrvNumber.set(data);
	}

	public static String getPbNumber() {
		return PBNumber.get();
	}

	public static void setPbNumber(String data) {
		PBNumber.set(data);
	}

	public static String getBPvNumber() {
		return BpvNumber.get();
	}

	public static void setBpvNumber(String data) {
		BpvNumber.set(data);
	}

	public static String getOpenSOPriceAmount() {
		return OpenSOPriceAmount.get();
	}

	public static void setOpenSOPriceAmount(String data) {
		OpenSOPriceAmount.set(data);
	}

	public static String getBankReceiptVouNo() {
		return BankReceiptVouNo.get();
	}

	public static void setBankReceiptVouNo(String data) {
		BankReceiptVouNo.set(data);
	}

	public static BigDecimal getSOLineAmount() {
		return SOLineAmount.get();
	}

	public static void setSOLineAmount(BigDecimal data) {
		SOLineAmount.set(data);
	}

	public static BigDecimal getSOTotalLineAmount() {
		return TotalSOLineAmount.get();
	}

	public static void setSOTotalLineAmount(BigDecimal data) {
		TotalSOLineAmount.set(data);
	}

	public static BigDecimal getSODiscountAmount() {
		return SODiscountAmount.get();
	}

	public static void setSODiscountAmount(BigDecimal data) {
		SODiscountAmount.set(data);
	}

	public static BigDecimal getSOTotalDiscountAmount() {
		return TotalSODiscountAmount.get();
	}

	public static void setSOTotalDiscountAmount(BigDecimal data) {
		TotalSODiscountAmount.set(data);
	}

	public static BigDecimal getSOGrossAmount() {
		return SOGrossAmount.get();
	}

	public static void setSOGrossAmount(BigDecimal data) {
		SOGrossAmount.set(data);
	}

	public static BigDecimal getSOTotalGrossAmount() {
		return TotalSOGrossAmount.get();
	}

	public static void setSOTotalGrossAmount(BigDecimal data) {
		TotalSOGrossAmount.set(data);
	}

	public static String getPI_DocNo() {
		return PI_DocNo.get();
	}

	public static void setPI_DocNo(String Data) {
		PI_DocNo.set(Data);
	}

	public static String getOSOAdocnum() {
		return OSOAnumber.get();
	}

	public static void setOSOAdocnum(String data) {
		OSOAnumber.set(data);
	}

	public static String getAmendmentDocNumber() {
		return AmendmentDocNumber.get();
	}

	public static void setAmendmentDocNumber(String data) {
		AmendmentDocNumber.set(data);
	}

	public static String getMRPNumber() {
		return MRPNumber.get();
	}

	public static void setMRPNumber(String data) {
		MRPNumber.set(data);
	}

	public static String getProductionOrderNumber() {
		return ProductionOrderNumber.get();
	}

	public static void setProductionOrderNumber(String data) {
		ProductionOrderNumber.set(data);
	}

	public static String[][] getDBTestData() {
		return DBTestdata.get();
	}

	public static void setDBTestData(String[][] data) {
		DBTestdata.set(data);
	}

	public static Map<String, String> getStockQty() {
		return StockQty.get();
	}

	public static void setStockQty(Map<String, String> data) {
		StockQty.set(data);
	}

	public static Map<String, String> getStockCost() {
		return StockCost.get();
	}

	public static void setStockCost(Map<String, String> data) {
		StockCost.set(data);
	}

	public static String getGrnStatus() {
		return GrnStatus.get();
	}

	public static void setGrnStatus(String data) {
		GrnStatus.set(data);
	}

	public static String getMRNumber() {
		return MRNumber.get();
	}

	public static void setMRNumber(String data) {
		MRNumber.set(data);
	}

	public static String getItemName() {
		return SimItemName.get();
	}

	public static void setItemName(String data) {
		SimItemName.set(data);
	}

	public static String getDisItemName() {
		return DisItemName.get();
	}

	public static void setDisItemName(String data) {
		DisItemName.set(data);
	}

	public static String getAmdSimItemName() {
		return AmdSimItemName.get();
	}

	public static void setAmdSimItemName(String data) {
		AmdSimItemName.set(data);
	}

	public static String getAmdDisItemName() {
		return AmdDisItemName.get();
	}

	public static void setAmdDisItemName(String data) {
		AmdDisItemName.set(data);
	}

	public static String getMIVNumber() {
		return MIVNumber.get();
	}

	public static void setMIVNumber(String data) {
		MIVNumber.set(data);
	}

	public static boolean getSaleOrderToMRPExecution() {
		return SaleOrderToMRPExecution.get();
	}

	public static void setSaleOrderToMRPExecution(boolean flag) {
		SaleOrderToMRPExecution.set(flag);
	}

	public static String getMaterialRequestNo() {
		return MaterialRequestNo.get();
	}

	public static void setMaterialRequestNo(String data) {
		MaterialRequestNo.set(data);
	}

	public static String getProductionCompletionOrderNo() {
		return ProductionCompletionOrderNo.get();
	}

	public static void setProductionCompletionOrderNo(String data) {
		ProductionCompletionOrderNo.set(data);
	}

	public static String[][] getProcessAndWarehousesBOM() {
		return ProcessAndWarehousesBOM.get();
	}

	public static void setProcessAndWarehousesBOM(String[][] data) {
		ProcessAndWarehousesBOM.set(data);
	}

	public static String getCustomerSaleGSTType() {
		return CustomerSaleGSTType.get();
	}

	public static void setCustomerSaleGSTType(String data) {
		CustomerSaleGSTType.set(data);
	}

	public static BigDecimal getSOChargeAmount() {
		return SOChargeAmount.get();
	}

	public static void setSOChargeAmount(BigDecimal data) {
		SOChargeAmount.set(data);
	}

	public static BigDecimal getSOTotalChargeAmount() {
		return TotalSOChargeAmount.get();
	}

	public static void setSOTotalChargeAmount(BigDecimal data) {
		TotalSOChargeAmount.set(data);
	}

	public static String getAdvReceiptDocNumber() {
		return AdvReceiptDocNumber.get();

	}

	public static void setAdvReceiptDocNumber(String data) {
		AdvReceiptDocNumber.set(data);
	}

	public static String getAdvReceiptDocNumberInSO() {
		return AdvReceiptDocNumberInSO.get();

	}

	public static void setAdvReceiptDocNumberInSO(String data) {
		AdvReceiptDocNumberInSO.set(data);
	}

	public static String getExpectedAdvReceiptBalanceAmountInSO() {
		return AdvReceiptsBalanceAmountInSO.get();

	}

	public static void setExpectedAdvReceiptBalanceAmountInSO(String data) {
		AdvReceiptsBalanceAmountInSO.set(data);
	}

	public static String getExpectedAdvToBeReceivedInSO() {
		return AdvReceiptsToBeReceivedInSO.get();

	}

	public static void setExpectedAdvToBeReceivedInSO(String data) {
		AdvReceiptsToBeReceivedInSO.set(data);
	}

	public static String getBankReceiptDocNumber() {
		return BankReceiptDocNumber.get();

	}

	public static void setBankReceiptDocNumber(String data) {
		BankReceiptDocNumber.set(data);
	}

	public static BigDecimal getSOSpecialAssesableValue() {
		return SOSpecAssesableVal.get();
	}

	public static void setSOSpecialAssesableValue(BigDecimal data) {
		SOSpecAssesableVal.set(data);
	}

	public static BigDecimal getSOCashAssesableValue() {
		return SOCashAssesableVal.get();
	}

	public static void setSOCashAssesableValue(BigDecimal data) {
		SOCashAssesableVal.set(data);
	}

	public static BigDecimal getSOTradeDiscountAmount() {
		return SOTradeDiscountAmount.get();
	}

	public static void setSOTradeDiscountAmount(BigDecimal data) {
		SOTradeDiscountAmount.set(data);
	}

	public static BigDecimal getSOSpecialDiscountAmount() {
		return SOSpecialDiscountAmount.get();
	}

	public static void setSOSpecialDiscountAmount(BigDecimal data) {
		SOSpecialDiscountAmount.set(data);
	}

	public static BigDecimal getSOCashDiscountAmount() {
		return SOCashDiscountAmount.get();
	}

	public static void setSOCashDiscountAmount(BigDecimal data) {
		SOCashDiscountAmount.set(data);
	}

	public static BigDecimal getSODiscountAmountPerQty() {
		return SODiscountAmountPerQty.get();
	}

	public static void setSODiscountAmountPerQty(BigDecimal data) {
		SODiscountAmountPerQty.set(data);
	}

	public static BigDecimal getSOTaxAmount() {
		return SOTaxAmount.get();
	}

	public static void setSOTaxAmount(BigDecimal data) {
		SOTaxAmount.set(data);
	}

	public static BigDecimal getSOEXPTotalOrderAmount() {
		return SOTotExpOrderAmount.get();
	}

	public static void setSOEXPTotalOrderAmount(BigDecimal data) {
		SOTotExpOrderAmount.set(data);
	}

	public static Map<String, String> getAfterStockQty() {
		return AfterStockQty.get();
	}

	public static void setAfterStockQty(Map<String, String> data) {
		AfterStockQty.set(data);
	}

	public static Map<String, String> getAfterStockCost() {
		return AfterStockCost.get();
	}

	public static void setAfterStockCost(Map<String, String> data) {
		AfterStockCost.set(data);
	}

	public static String getDCNumber() {
		return DCNumber.get();
	}

	public static void setDCNumber(String data) {
		DCNumber.set(data);
	}

	public static String getPermissionDocNo() {
		return PermissionDocNo.get();
	}

	public static void setPermssionDocNo(String data) {
		PermissionDocNo.set(data);
	}

	public static String getBOMNumber() {
		return BOMNumber.get();
	}

	public static void setBOMNumber(String data) {
		BOMNumber.set(data);
	}

	public static String getGateEntryNumber() {
		return GateEntryNumber.get();
	}

	public static void setGateEntryNumber(String data) {
		GateEntryNumber.set(data);
	}

	public static String getSalesOrdertDocNumber() {
		return SalesOrderDocuNumber.get();
	}

	public static void setSalesOrdertDocNumber(String data) {
		SalesOrderDocuNumber.set(data);
	}

	public static String getSalesInvoiceNumber() {
		return SalesInvoiceNumber.get();
	}

	public static void setSalesInvoiceNumber(String data) {
		SalesInvoiceNumber.set(data);
	}

	public static ArrayList<String> getAllDocumentNumber() {
		return AllDocumentNumber.get();
	}

	public static void setAllDocumentNumber(ArrayList<String> data) {
		AllDocumentNumber.set(data);
	}

	public static void setInspectionPlanNumber(String data) {
		InspectionPlanNumber.set(data);
	}

	public static String getInspectionPlanNumber() {
		return InspectionPlanNumber.get();
	}

	public static void setIDAlertMessage(String data) {
		IDAlertMessage.set(data);
	}

	public static String getIDAlertMessage() {
		return IDAlertMessage.get();
	}

	public static void setReceiptLocator(String data) {
		ReceiptLocator.set(data);
	}

	public static String getReceiptLocator() {
		return ReceiptLocator.get();
	}

	public static void setMRVDocumentNumber(String data) {
		MRVDocumentNumber.set(data);
	}

	public static String getMRVDocumentNumber() {
		return MRVDocumentNumber.get();
	}

	public static String getScoNumber() {
		return ScoNumber.get();
	}

	public static void setScoNumber(String data) {
		ScoNumber.set(data);
	}

	public static String getScoGrnNumber() {
		return ScoGrnNumber.get();
	}

	public static void setScoGrnNumber(String data) {
		ScoGrnNumber.set(data);
	}

	public static ArrayList<String> getTotalProductionProcess() {
		return TotalProductionProcess.get();
	}

	public static void setTotalProductionProcess(ArrayList<String> data) {
		TotalProductionProcess.set(data);
	}

	public static String getRfqDocNum() {
		return RfqDocNum.get();
	}

	public static void setRfqDocNum(String data) {
		RfqDocNum.set(data);
	}

	public static ArrayList<String> getPONumbers() {
		return PONumbers.get();
	}

	public static void setPONumbers(ArrayList<String> data) {
		PONumbers.set(data);
	}

	public static String getScoPurchaseNumber() {
		return ScoPurchaseNumber.get();
	}

	public static void setScoPurchaseNumber(String data) {
		ScoPurchaseNumber.set(data);
	}

	public static BigDecimal getRevisiedValue() {
		return RevisiedValue.get();
	}

	public static void setRevisiedValue(BigDecimal data) {
		RevisiedValue.set(data);
	}

	public static BigDecimal getTaxPercent() {
		return TaxPercent.get();
	}

	public static void setTaxPercent(BigDecimal data) {
		TaxPercent.set(data);
	}

	public static String getPaymentTermAdvMatPercentage() {
		return PaymentTermAdvMatPercentage.get();
	}

	public static void setPaymentTermAdvMatPercentage(String data) {
		PaymentTermAdvMatPercentage.set(data);
	}

	public static String getPaymentTermAdvTaxPercentage() {
		return PaymentTermAdvTaxPercentage.get();
	}

	public static void setPaymentTermAdvTaxPercentage(String data) {
		PaymentTermAdvTaxPercentage.set(data);
	}

	public static String getHeaderCurrency() {
		return HeaderCurrency.get();
	}

	public static void setHeaderCurrency(String data) {
		HeaderCurrency.set(data);
	}

	public static String getExpectedStockCost() {
		return ExpectedStockCost.get();
	}

	public static void setExpectedStockCost(String data) {
		ExpectedStockCost.set(data);
	}

	public static String getExpectedStockQty() {
		return ExpectedStockQty.get();
	}

	public static void setExpectedStockQty(String data) {
		ExpectedStockQty.set(data);
	}

	public static String[][] getExpectedStockQty1() {
		return ExpectedStockQty1.get();
	}

	public static void setExpectedStockQty1(String[][] data) {
		ExpectedStockQty1.set(data);
	}

	public static ArrayList<String> getExpectedStockQty2() {
		return ExpectedStockQty2.get();
	}

	public static void setExpectedStockQty2(ArrayList<String> data) {
		ExpectedStockQty2.set(data);
	}

	public static String getEncashmentDocNo() {
		return EncashmentDocNo.get();
	}

	public static void setEncashmentDocNo(String data) {
		EncashmentDocNo.set(data);
	}

	public static String getCompOffDocNo() {
		return CompOffDocNo.get();
	}

	public static void setCompOffDocNo(String data) {
		CompOffDocNo.set(data);
	}

	public static String getEmpProfileNo() {
		return EmpProfileNo.get();
	}

	public static void setEmpProfileNo(String data) {
		EmpProfileNo.set(data);
	}

	public static String getEmpBatchNo() {
		return EmpBatchNo.get();
	}

	public static void setEmpBatchNo(String data) {
		EmpBatchNo.set(data);
	}

	public static String getCasualLeaveBalance() {
		return CasualLeaveBalance.get();
	}

	public static void setCasualLeaveBalance(String data) {
		CasualLeaveBalance.set(data);
	}

	public static String getLeaveRequestNo() {
		return LeaveRequestNo.get();
	}

	public static void setLeaveRequestNo(String data) {
		LeaveRequestNo.set(data);
	}

	public static String getOpenPoNumber() {
		return OpenPoNumber.get();
	}

	public static void setOpenPoNumber(String data) {
		OpenPoNumber.set(data);
	}

	public static String getLeaveEntryDocNo() {
		return LeaveEntryDocNo.get();
	}

	public static void setLeaveEntryDocNo(String data) {
		LeaveEntryDocNo.set(data);
	}

	public static String getSOAmdNumber() {
		return SOAmdDocNumber.get();
	}

	public static void setSOAmdNumber(String data) {
		SOAmdDocNumber.set(data);
	}

	public static ArrayList<String> getShipSetItem() {
		return ShipSetItem.get();
	}

	public static void setShipSetItem(ArrayList<String> data) {
		ShipSetItem.set(data);
	}

	public static void setShipSetItemFromDB(String data) {
		ShipSetItemFromDB.set(data);
	}

	public static String getShipSetItemFromDB() {
		return ShipSetItemFromDB.get();

	}

	public static String getCNdocno() {
		return CNdocno.get();
	}

	public static void setCNdocno(String Data) {
		CNdocno.set(Data);
	}

	public static String getGEdocno() {
		return GEdocno.get();
	}

	public static void setGEdocno(String Data) {
		GEdocno.set(Data);
	}

	public static String getCRdocno() {
		return CRdocno.get();
	}

	public static void setCRdocno(String Data) {
		CRdocno.set(Data);
	}

	public static String getSIInvNo() {
		return SIInvNo.get();
	}

	public static void setSIInvNo(String Data) {
		SIInvNo.set(Data);
	}

	public static String getOpenPOAmendNumber() {
		return OpenPOAmendNumber.get();
	}

	public static void setOpenPOAmendNumber(String data) {
		OpenPOAmendNumber.set(data);
	}

	public static String getSsoDocNum() {
		return SsoDocNum.get();
	}

	public static void setSsoDocNum(String data) {
		SsoDocNum.set(data);
	}

	public static String getOPODocNum() {
		return OPODocNum.get();
	}

	public static void setOPODocNum(String data) {
		OPODocNum.set(data);
	}

	public static String getGATEDocNum() {
		return GATEDocNum.get();
	}

	public static void setGATEDocNum(String data) {
		GATEDocNum.set(data);
	}

	public static String getOrderStockQty() {
		return OrderStockQty.get();
	}

	public static void setOrderStockQty(String Data) {
		OrderStockQty.set(Data);
	}

	public static String getBeforeStockQty() {
		return BeforeStockQty.get();
	}

	public static void setBeforeStockQty(String Data) {
		BeforeStockQty.set(Data);
	}

	public static String getCSDocNumber() {
		return CSDocNumber.get();
	}

	public static void setCSDocNumber(String data) {
		CSDocNumber.set(data);
	}

	public static String getPayrollDocNo() {
		return PayrollDocNo.get();
	}

	public static void setPayrollDocNo(String Data) {
		PayrollDocNo.set(Data);
	}

	public static String getAppsNumber() {
		return ApprovedSupplierNumber.get();
	}

	public static void setAppsNumber(String data) {
		ApprovedSupplierNumber.set(data);
	}

	public static String getSOBNumber() {
		return SOBNumber.get();
	}

	public static void setSOBNumber(String data) {
		SOBNumber.set(data);
	}

	public static String getRWdocno() {
		return RWdocno.get();
	}

	public static void setRWdocno(String Data) {
		RWdocno.set(Data);
	}

	public static String getRWCdocno() {
		return RWCdocno.get();
	}

	public static void setRWCdocno(String Data) {
		RWCdocno.set(Data);
	}

	public static String getDBdocno() {
		return DBdocno.get();
	}

	public static void setDBdocno(String Data) {
		DBdocno.set(Data);
	}

	public static String getPriceListDocno() {
		return PriceListDocno.get();
	}

	public static void setPriceListDocno(String data) {
		PriceListDocno.set(data);
	}

	public static String getCatalogDocno() {
		return CatDocno.get();
	}

	public static void setCatalogDocno(String data) {
		CatDocno.set(data);
	}

	public static String getEmpAdjustmentVouNo() {
		return EmpAdjustmentVouNo.get();
	}

	public static void setEmpAdjustmentVouNo(String Data) {
		EmpAdjustmentVouNo.set(Data);
	}

	public static String getSalesRegionText() {
		return SalesRegionText.get();
	}

	public static void setSalesRegionText(String Data) {
		SalesRegionText.set(Data);
	}

	public static String getSalesZoneText() {
		return SalesZoneText.get();
	}

	public static void setSalesZoneText(String Data) {
		SalesZoneText.set(Data);
	}

	public static ArrayList<String> getProductionProcess() {
		return ProductionProcess.get();
	}

	public static void setProductionProcess(ArrayList<String> data) {
		ProductionProcess.set(data);
	}

	public static Map<String, String> getProductionProcessType() {
		return ProductionProcessType.get();
	}

	public static void setProductionProcess(Map<String, String> data) {
		ProductionProcessType.set(data);
	}

	public static Map<String, String> getProductionProcessItem() {
		return ProductionProcessItem.get();
	}

	public static void setProductionProcessItem(Map<String, String> data) {
		ProductionProcessItem.set(data);
	}

	public static String[][] getProcessAndProcessType() {
		return ProcessAndProcessType.get();
	}

	public static void setProcessAndProcessType(String[][] data) {
		ProcessAndProcessType.set(data);
	}

	public static ArrayList<String> getMultipleSoDocs() {
		return MultipleSODocs.get();
	}

	public static void setMultipleSoDocs(ArrayList<String> data) {
		MultipleSODocs.set(data);
	}

	public static String getSOMultiCloseShortDocNo() {
		return SOMultiCloseShortDocNo.get();
	}

	public static void setSOMultiCloseShortDocNo(String data) {
		SOMultiCloseShortDocNo.set(data);
	}

	public static String getCSLineDocNumber() {
		return CSLineDocNumber.get();
	}

	public static void setCSLineDocNumber(String data) {
		CSLineDocNumber.set(data);
	}

	public static String getCSAmdDocno() {
		return CSAmdDocNumber.get();
	}

	public static void setCSAmdDocno(String data) {
		CSAmdDocNumber.set(data);
	}

	public static ArrayList<String> getMultipleItems() {
		return MultipleItems.get();
	}

	public static void setMultipleItems(ArrayList<String> data) {
		MultipleItems.set(data);
	}

	public static ArrayList<String> getMultipleCatalogDocs() {
		return MultipleCatalogDocs.get();
	}

	public static void setMultipleCatalogDocs(ArrayList<String> data) {
		MultipleCatalogDocs.set(data);
	}

	public static ArrayList<String> getMultiplePriceListDocs() {
		return MultiplePriceListDocs.get();
	}

	public static void setMultiplePriceListDocs(ArrayList<String> data) {
		MultiplePriceListDocs.set(data);
	}

	public static ArrayList<String> getMultipleOpenSoDocs() {
		return MultipleOpenSODocs.get();
	}

	public static void setMultipleOpenSoDocs(ArrayList<String> data) {
		MultipleOpenSODocs.set(data);
	}

	public static boolean getMultipleProcessFlag() {
		return MultipleProcessflag.get();
	}

	public static void setMultipleProcessFlag(boolean flag) {
		MultipleProcessflag.set(flag);
	}

	public static String getRawMaterialCost() {
		return RawMaterialCost.get();
	}

	public static void setRawMaterialCost(String Cost) {
		RawMaterialCost.set(Cost);
	}

	public static String getCompletionFgUnitCost() {
		return CompletionFgUnitCost.get();
	}

	public static void setCompletionFgUnitCost(String Cost) {
		CompletionFgUnitCost.set(Cost);
	}

	public static String getLoanRequestDocNo() {
		return LoanRequestDocNo.get();
	}

	public static void setLoanRequestDocNo(String data) {
		LoanRequestDocNo.set(data);
	}

	public static ArrayList<String> getMultiMRVDocNumber() {
		return MultiMRVDocNumber.get();
	}

	public static void setMultiMRVDocNumber(ArrayList<String> data) {
		MultiMRVDocNumber.set(data);
	}

	public static ArrayList<String> getMultiMRVDocStatus() {
		return MultiMRVDocStatus.get();
	}

	public static void setMultiMRVDocStatus(ArrayList<String> data) {
		MultiMRVDocStatus.set(data);
	}

	public static ArrayList<String> getMultipleCSLineDocs() {
		return MultipleCSLineDocs.get();
	}

	public static void setMultipleCSLineDocs(ArrayList<String> data) {
		MultipleCSLineDocs.set(data);
	}

	public static String getHeaderCurrencyAfterSaveTheLine() {
		return HeaderCurrencyAfterSaveTheLine.get();
	}

	public static void setHeaderCurrencyAfterSaveTheLine(String data) {
		HeaderCurrencyAfterSaveTheLine.set(data);
	}

	public static String getStkAdjDocNumber() {
		return StkAdjDocNumber.get();
	}

	public static void setStkAdjDocNumber(String data) {
		StkAdjDocNumber.set(data);
	}

	public static String getoscdocno() {
		return oscdocno.get();
	}

	public static void setoscdocno(String data) {
		oscdocno.set(data);
	}

	public static String getRevdocno() {
		return Revdocno.get();
	}

	public static void setRevdocno(String data) {
		Revdocno.set(data);
	}

	public static String getSCOdocno() {
		return SCOdocno.get();
	}

	public static void setSCOdocno(String data) {
		SCOdocno.set(data);
	}

	public static String getEmpUpdateDocNo() {
		return EmpUpdateDocNo.get();
	}

	public static void setEmpUpdateDocNo(String data) {
		EmpUpdateDocNo.set(data);
	}

	public static void setEmpLeaveEntryAutoPost(boolean flag) {
		EmpLeaveEntryAutoPost.set(flag);
	}

	public static boolean getEmpLeaveEntryAutoPost() {
		return EmpLeaveEntryAutoPost.get();
	}

	public static String getProcessIDAsso() {
		return ProcessIDAsso.get();
	}

	public static void setProcessIDAsso(String Data) {
		ProcessIDAsso.set(Data);
	}

	public static String getResourceGrp() {
		return ResourceGrp.get();
	}

	public static void setResourceGrp(String Data) {
		ResourceGrp.set(Data);
	}

	public static String getManpowerResourceGrp() {
		return ManpowerResourceGrp.get();
	}

	public static void setManpowerResourceGrp(String Data) {
		ManpowerResourceGrp.set(Data);
	}

	public static String getSCOBatchQty() {
		return SCOBatchQty.get();
	}

	public static void setSCOBatchQty(String Data) {
		SCOBatchQty.set(Data);
	}

	public static String getSCOBatchCost() {
		return SCOBatchCost.get();
	}

	public static void setSCOBatchCost(String Data) {
		SCOBatchCost.set(Data);
	}

	public static String getSCOOrderQty() {
		return SCOOrderQty.get();
	}

	public static void setSCOOrderQty(String Data) {
		SCOOrderQty.set(Data);
	}

	public static String getSCOConvFact() {
		return SCOConvFact.get();
	}

	public static void setSCOConvFact(String Data) {
		SCOConvFact.set(Data);
	}

	public static String getSCOProcUOM() {
		return SCOProcUOM.get();
	}

	public static void setSCOProcUOM(String Data) {
		SCOProcUOM.set(Data);
	}

	public static String getInspectionPlanDocNo() {
		return InspectionPlanDocNo.get();
	}

	public static void setInspectionPlanDocNo(String Data) {
		InspectionPlanDocNo.set(Data);
	}

	public static String getLastApprovalAPIHeaderText() {
		return LastApprovalAPIHeaderText.get();
	}

	public static void setLastApprovalAPIHeaderText(String Data) {
		LastApprovalAPIHeaderText.set(Data);
	}

	public static ArrayList<String> getMultipleSIDocs() {
		return MultipleSIDocs.get();
	}

	public static void setMultipleSIDocs(ArrayList<String> data) {
		MultipleSIDocs.set(data);
	}

	public static String getInspectionReqNumber() {
		return InspectionReqNumber.get();
	}

	public static void setInspectionReqNumber(String Data) {
		InspectionReqNumber.set(Data);
	}

	public static String getMaterialReturnNumber() {
		return MaterialReturnNumber.get();
	}

	public static void setMaterialReturnNumber(String Data) {
		MaterialReturnNumber.set(Data);
	}

	public static void setAutoPostMRVFlag(boolean flag) {
		AutoPostMRVFlag.set(flag);
	}

	public static boolean getAutoPostMRVFlag() {
		return AutoPostMRVFlag.get();
	}

	public static void setTDSDeclarationDocNo(String Data) {
		TDSDeclarationDocNo.set(Data);
	}

	public static String getTDSDeclarationDocNo() {
		return TDSDeclarationDocNo.get();
	}

	public static String getOvertimeDocNo() {
		return OvertimeDocNo.get();
	}

	public static void setOvertimeDocNo(String data) {
		OvertimeDocNo.set(data);
	}

	public static String getInvoiceDocNo() {
		return InvoiceDocNo.get();
	}

	public static void setInvoiceDocNo(String Data) {
		InvoiceDocNo.set(Data);
	}

	public static String getBankPaymentOrReceiptDocNumber() {
		return BankPaymentOrReceiptDoc.get();
	}

	public static void setBankPaymentOrReceiptDocNumber(String data) {
		BankPaymentOrReceiptDoc.set(data);
	}

	public static ArrayList<String> getMultipleGRNDocs() {
		return MultipleGRNDocs.get();
	}

	public static void setMultipleGRNDocs(ArrayList<String> data) {
		MultipleGRNDocs.set(data);
	}

	public static ArrayList<String> getMultiplePBDocs() {
		return MultiplePBDocs.get();
	}

	public static void setMultiplePBDocs(ArrayList<String> data) {
		MultiplePBDocs.set(data);
	}

	public static String getCashPaymentOrReceiptDocNumber() {
		return CashPaymentOrReceiptDoc.get();
	}

	public static void setCashPaymentOrReceiptDocNumber(String data) {
		CashPaymentOrReceiptDoc.set(data);
	}

	public static String getTravelRequestDocNo() {
		return TravelRequestDocNo.get();
	}

	public static void setTravelRequestDocNo(String data) {
		TravelRequestDocNo.set(data);
	}

	public static String getTravelReimbursementtDocNo() {
		return TravelReimbursementDocNo.get();
	}

	public static void setTravelReimbursementDocNo(String data) {
		TravelReimbursementDocNo.set(data);
	}

	public static String getSCOSFcode() {
		return SCOSFcode.get();
	}

	public static void setSCOSFcode(String Data) {
		SCOSFcode.set(Data);
	}

	public static String getSCOTarSFcode() {
		return SCOTarSFcode.get();
	}

	public static void setSCOTarSFcode(String Data) {
		SCOTarSFcode.set(Data);
	}

	public static String getSCOAdocNo() {
		return SCOAdocNo.get();
	}

	public static void setSCOAdocNo(String Data) {
		SCOAdocNo.set(Data);
	}

	public static String getSSRDocNumber() {
		return SSRDocNo.get();
	}

	public static void setSSRDocNumber(String data) {
		SSRDocNo.set(data);
	}

	public static String getSSODocNumber() {
		return SSODocNo.get();
	}

	public static void setSSODocNumber(String data) {
		SSODocNo.set(data);
	}

	public static String getGeneralReimbursementDocNo() {
		return GeneralReimbursementDocNo.get();
	}

	public static void setGeneralReimbursementDocNo(String data) {
		GeneralReimbursementDocNo.set(data);
	}

	public static String getGRNLotNo() {
		return GRNLotNo.get();
	}

	public static void setGRNLotNo(String Data) {
		GRNLotNo.set(Data);
	}

	public static ArrayList<String> getMultipleAdvRecDocs() {
		return MultipleAdvRecDocs.get();
	}

	public static void setMultipleAdvRecDocs(ArrayList<String> data) {
		MultipleAdvRecDocs.set(data);
	}

	public static String getResignationRequestDocNo() {
		return ResignationRequestDocNo.get();
	}

	public static void setResignationRequestDocNo(String data) {
		ResignationRequestDocNo.set(data);
	}

	public static String getMatchedAmt() {
		return MatchedAmt.get();
	}

	public static void setMatchedAmt(String Data) {
		MatchedAmt.set(Data);
	}

	public static String getGRNAmt() {
		return GRNAmt.get();
	}

	public static void setGRNAmt(String Data) {
		GRNAmt.set(Data);
	}

	public static String getdeviationAmt() {
		return deviationAmt.get();
	}

	public static void setdeviationAmt(String Data) {
		deviationAmt.set(Data);
	}

	public static String getPBProcQty() {
		return PBProcQty.get();
	}

	public static void setPBProcQty(String Data) {
		PBProcQty.set(Data);
	}

	public static String getPBUnitcost() {
		return PBUnitcost.get();
	}

	public static void setPBUnitcost(String Data) {
		PBUnitcost.set(Data);
	}

	public static String getAdvPaymentDocumentno() {
		return PoAdvPaymentDocumentNO.get();
	}

	public static void setAdvPaymentDocumentno(String Data) {
		PoAdvPaymentDocumentNO.set(Data);
	}

	public static ArrayList<String> getMultiplePoDocs() {
		return MultiplePoDocs.get();
	}

	public static void setMultiplePoDocs(ArrayList<String> data) {
		MultiplePoDocs.set(data);
	}

	public static String getBPAmount() {
		return BPAmount.get();
	}

	public static void setBPAmount(String Data) {
		BPAmount.set(Data);
	}

	public static String getSupGlAccount() {
		return SupGlAccount.get();
	}

	public static void setSupGlAccount(String Data) {
		SupGlAccount.set(Data);
	}

	public static String getAdvPaymentDocNumber() {
		return AdvPaymentDocNumber.get();
	}

	public static void setAdvPaymentDocNumber(String data) {
		AdvPaymentDocNumber.set(data);
	}

	public static ArrayList<String> getMultipleAdvPymtDocs() {
		return MultiplePymtRecDocs.get();
	}

	public static void setMultipleAdvPymtDocs(ArrayList<String> data) {
		MultiplePymtRecDocs.set(data);
	}

	public static ArrayList<String> getMultiplePODocs() {
		return MultiplePODocs.get();
	}

	public static void setMultiplePODocs(ArrayList<String> data) {
		MultiplePODocs.set(data);
	}

	public static ArrayList<String> getMultipleBankDocs() {
		return MultipleBankDocs.get();
	}

	public static void setMultipleBankDocs(ArrayList<String> data) {
		MultipleBankDocs.set(data);
	}

	public static String getEmployeeFullName() {
		return EmployeeFullName.get();
	}

	public static void setEmployeeFullName(String Data) {
		EmployeeFullName.set(Data);
	}

	public static String getHomePageURL() {
		return HomePageURL.get();
	}

	public static void setHomePageURL(String Data) {
		HomePageURL.set(Data);
	}

	public static boolean getSiteWorked() {
		return SiteWorked.get();
	}

	public static void setSiteWorked(boolean Data) {
		SiteWorked.set(Data);
	}
	
	public static String getSOPriceBasis() {
		return PriceBasis.get();
	}

	public static void setSOPriceBasis(String data) {
		PriceBasis.set(data);
	}
}
