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

}
