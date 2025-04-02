package commonfunctions;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import java.io.File;
/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 24, 2025
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import utils.ExcelUtils;
import utils.WebDriverBase;

public class DBCommonFunctions {
	ExcelUtils excelUtils = new ExcelUtils();
	WebDriverBase webDB = new WebDriverBase();
	private ThreadLocal<Connection> DBConnection = new ThreadLocal<>();
	CommonFunctions commonFunc = new CommonFunctions();

	public Connection getConnection() {
		return DBConnection.get();
	}

	public void setConnection(Connection data) {
		DBConnection.set(data);
	}

	/**
	 * Establish Connections
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void establishDBConnection() {
		String url = "jdbc:oracle:thin:@//" + webDB.getDataFromProperties("IpDB") + ":"
				+ webDB.getDataFromProperties("PortDB") + "/" + webDB.getDataFromProperties("DBName");
		Properties props = new Properties();
		props.setProperty("user", System.getProperty("DBUsername"));
		props.setProperty("password", System.getProperty("DBPassword"));
		props.setProperty("oracle.net.CONNECT_TIMEOUT", "60000"); // Increase timeout to 60 sec
		props.setProperty("oracle.jdbc.ReadTimeout", "60000");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection(url, props);
			setConnection(connection);
			System.out.println("Connected to Oracle Database!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get Required Data From DB Using Query
	 * 
	 * @author Deepan-GWL
	 * @param Query
	 * @param ExpectedData
	 * @return String Value
	 */
	public String getDataFromDBUsingQuery(String Query, String Replacechar, String Replacedchar, String ExpectedData) {
		String returnname = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (getConnection() == null) {
				establishDBConnection();
			}
			Statement statement = getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(Query.replace(Replacechar, Replacedchar));
			while (resultSet.next()) {
				returnname = resultSet.getString(ExpectedData); // Adjust column name
				System.out.println("Data Is: " + returnname);
			}
			resultSet.close();
			statement.close();
//			closeDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnname;
	}

	/**
	 * PR Header Details
	 * 
	 * @author Deepan-GWL
	 * @param PrNo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String PRHeaderDetails(String PrNo, String HeaderValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PRHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("PRNO"), PrNo, HeaderValue);
		return value;
	}

	/**
	 * PR Line Details
	 * 
	 * @author Deepan-GWL
	 * @param PrNo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String PRLineDetails(String PrNo, String HeaderValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PRLineQuery"),
				commonFunc.getDataFromTestDataSheet("PRNO"), PrNo, HeaderValue);
		return value;
	}

	/**
	 * SO Header Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String SOHeaderDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SOHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SO Header Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String SOLineDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SOLineQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SO Header Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SOAdvanceDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SOAdvanceQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SO Header Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param CusId
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SOCustomerAddressDetails(String CusId, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SOCustomerDetailsQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), CusId, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SO Header Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param UnitId
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SOUnitAddressDetails(String UnitId, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SOUnitDetailsQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), UnitId, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SO Line Details 1 And 2
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SOLineDetails1(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SOLineDetails1&2"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SO Payment Term Adv Percentage
	 * 
	 * @author Harikrishna -RMITS
	 * @param PaymentTerm
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SOPaymentTermAdvPercentage(String PaymentTerm, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PaymentTermQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), PaymentTerm, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Finance Advanvce Receipt
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String FinanceAdvanceReceipt(String AdvReceiptDocNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("AdvanceReceiptQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), AdvReceiptDocNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery
	 * 
	 * @author Harikrishna -RMITS
	 * @param Warehouse
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String StockQuery(String Warehouse, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("Stock Query"),
				commonFunc.getDataFromTestDataSheet("SONO"), Warehouse, DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery1
	 * 
	 * @author Harikrishna -RMITS
	 * @param Warehouse
	 * @param Item
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String StockQuery1(String Warehouse, String Item, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("Stock Query1"),
				commonFunc.getDataFromTestDataSheet("SONO"), Warehouse, commonFunc.getDataFromTestDataSheet("PONO"),
				Item, DBHeaderColumnValue);
		return value;
	}

	public String getDataFromDBUsingQuery1(String Query, String Replacechar, String Replacedchar, String Replacechar1,
			String Replacedchar1, String ExpectedData) {
		String returnname = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (getConnection() == null) {
				establishDBConnection();
			}
			Query = Query.replace(Replacechar, Replacedchar).replace(Replacechar1, Replacedchar1);
			Statement statement = getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(Query);
			while (resultSet.next()) {
				returnname = resultSet.getString(ExpectedData); // Adjust column name
//				System.out.println("Data Is: " + returnname);
			}
			resultSet.close();
			statement.close();
//			closeDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnname;
	}

	/**
	 * StockQuery
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONumber
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SalesInvoiceLotQuery(String SONumber, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SalesInvoiceLotQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONumber, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SO Charges Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SOChargesDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SalesOrderChargesQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Get Data From DB Test Data Sheet
	 * 
	 * @author Deepan-GWL
	 * @param Key
	 * @return String value
	 */
	public String getDataFromDBTestDataSheet(String Key) {
		String value = null;
		if (VariableHandler.getDBTestData() == null) {
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "TestDatas.xlsx";
			VariableHandler.setDBTestData(
					excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("DBTestDataSheetName")));
		} else {
		}
		for (int i = 0; i < VariableHandler.getDBTestData().length; i++) {
			if (VariableHandler.getDBTestData()[i][0].equals(Key)) {
				value = VariableHandler.getDBTestData()[i][1];
			}
		}
		return value;
	}

	/**
	 * Close DB
	 * 
	 * @author Deepan-GWL
	 */
	public void closeDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			getConnection().close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open Sales Order Amdendment DataBase
	 * 
	 * @author Kowshik-RMITS
	 * @param OSOANO
	 * @param DetailsValue
	 * @return
	 */
	public String OSOAdetails(String OSOANO, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("OsoaQuery"),
				commonFunc.getDataFromTestDataSheet("OSOANO"), OSOANO, DetailsValue);
		return value;

	}

}
