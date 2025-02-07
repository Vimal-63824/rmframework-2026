package commonfunctions;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks January 15, 2025
 */
import java.io.File;
import java.math.BigDecimal;

public class VariableHandler {
	private static ThreadLocal<String> PONumber = new ThreadLocal<>();
	private static ThreadLocal<String> PRNumber = new ThreadLocal<>();
	private static ThreadLocal<String> PRClsShrt_DocNumber = new ThreadLocal<>();
	private static ThreadLocal<String[][]> Testdata = ThreadLocal.withInitial(() -> null);
	public static String testdataDir = System.getProperty("user.dir") + File.separator + "testdata" + File.separator;
	private static ThreadLocal<Boolean> Approvalflag = new ThreadLocal<>();
	private static ThreadLocal<String> POTotalAmount = new ThreadLocal<>();
	private static ThreadLocal<Boolean> Registerflag = new ThreadLocal<>();
	private static ThreadLocal<BigDecimal> LineAmount = new ThreadLocal<>();
	private static ThreadLocal<BigDecimal> TotalDiscountAmount = new ThreadLocal<>();
	private static ThreadLocal<String> SONumber = new ThreadLocal<>();
	private static ThreadLocal <String> SOTotalAmount = new ThreadLocal<>();

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
		return TotalDiscountAmount.get();
	}

	public static void setDiscountAmount(BigDecimal data) {
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

}
