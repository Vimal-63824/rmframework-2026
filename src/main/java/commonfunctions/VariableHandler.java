package commonfunctions;

import java.io.File;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks January 15, 2025
 */
public class VariableHandler {
	private static ThreadLocal<String> PONumber = new ThreadLocal<>();
	private static ThreadLocal<String> PRNumber = new ThreadLocal<>();
	private static ThreadLocal<String> PRClsShrt_DocNumber = new ThreadLocal<>();
	private static ThreadLocal<String[][]> Testdata = ThreadLocal.withInitial(() -> null);
	public static String testdataDir = System.getProperty("user.dir") + File.separator + "testdata" + File.separator;
	private static ThreadLocal<Boolean> Approvalflag = new ThreadLocal<>();

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
}
