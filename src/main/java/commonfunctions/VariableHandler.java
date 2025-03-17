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
	private static ThreadLocal<String> EmpID = new ThreadLocal<>();
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
	private static ThreadLocal<String> MaterialIssuanceVoucherNo = new ThreadLocal<>();
	private static ThreadLocal<String> ProductionCompletionOrderNo = new ThreadLocal<>();
	private static ThreadLocal<String[][]> ProcessAndWarehousesBOM = ThreadLocal.withInitial(() -> null);
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

	public static String getMaterialIssuanceVoucherNo() {
		return MaterialIssuanceVoucherNo.get();
	}

	public static void setMaterialIssuanceVoucherNo(String data) {
		MaterialIssuanceVoucherNo.set(data);
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
}
