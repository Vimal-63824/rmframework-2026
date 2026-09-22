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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	/**
	 * get Required Data From DB Using Query Using 2 Parameters
	 * 
	 * @author Harikrishna - RMITS
	 * @param Query
	 * @param Replacechar
	 * @param Replacedchar
	 * @param Replacechar1
	 * @param Replacedchar1
	 * @param ExpectedData
	 * @return String returnname
	 */
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
	 * @return String Value
	 */
	public String OSOAdetails(String OSOANO, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("OsoaQuery"),
				commonFunc.getDataFromTestDataSheet("OSOANO"), OSOANO, DetailsValue);
		return value;

	}

	/**
	 * This Method Is Used To Validate po Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String poDetails(String PONumber, String Item, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("POQuery"),
				commonFunc.getDataFromTestDataSheet("PONO"), PONumber, commonFunc.getDataFromTestDataSheet("POID"),
				Item, DBHeaderColumnValue);
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
	public String PRLineDetailsQuery(String PrNo, String ItemId, String HeaderValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PRLineDetailsQuery"),
				commonFunc.getDataFromTestDataSheet("PRNO"), PrNo, commonFunc.getDataFromTestDataSheet("ItemId"),
				ItemId, HeaderValue);
		return value;
	}

	/**
	 * POA Line Details
	 * 
	 * @author Deepan-GWL
	 * @param PrNo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String poaLineDetailsQuery(String PoaNo, String ItemId, String HeaderValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("POALineDetailsQuery"),
				commonFunc.getDataFromTestDataSheet("POANO"), PoaNo, commonFunc.getDataFromTestDataSheet("ItemId"),
				ItemId, HeaderValue);
		return value;
	}

	/**
	 * get Required Data From DB Using Query Using 3 Parameters
	 * 
	 * @author Harikrishna -RMITS
	 * @param Query
	 * @param Replacechar
	 * @param Replacedchar
	 * @param Replacechar1
	 * @param Replacedchar1
	 * @param Replacechar2
	 * @param Replacedchar2
	 * @param ExpectedData
	 * @return String returnname
	 */
	public String getDataFromDBUsingQueryWith3Parameters(String Query, String Replacechar, String Replacedchar,
			String Replacechar1, String Replacedchar1, String Replacechar2, String Replacedchar2, String ExpectedData) {
		String returnname = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (getConnection() == null) {
				establishDBConnection();
			}
			Query = Query.replace(Replacechar, Replacedchar).replace(Replacechar1, Replacedchar1).replace(Replacechar2,
					Replacedchar2);
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
	 * Item Master ShipSet Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String ItemMasterShipSetDetails(String ItemId, String SeqNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("ItemShipSetQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), ItemId, commonFunc.getDataFromTestDataSheet("PONO"), SeqNo,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * Current Rate Contract Query Details
	 * 
	 * @param Customer
	 * @param Item
	 * @param PriceClass
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String CurrentRateContractQuery(String Customer, String Item, String PriceClass,
			String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQueryWith3Parameters(getDataFromDBTestDataSheet("CurrentRateContractQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), Customer, commonFunc.getDataFromTestDataSheet("PONO"),
				Item, commonFunc.getDataFromTestDataSheet("PRC"), PriceClass, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SOA Header Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SOAHeaderDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SalesOrderAmendmentHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SOA Line Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SOANo
	 * @param SeqNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SOALineDetails(String SOANo, String SeqNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("SalesOrderAmendmentLineQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SOANo, commonFunc.getDataFromTestDataSheet("PONO"), SeqNo,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * SI Line Details
	 * 
	 * @author Vimal -RMITS
	 * @param SINo
	 * @param LineValue
	 * @return String Value
	 */
	public String SILineDetails(String SINo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SILineDetailQuery"),
				commonFunc.getDataFromTestDataSheet("SINO"), SINo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * PI Header Details
	 * 
	 * @author Vimal -RMITS
	 * @param PINo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String PIHeaderDetails(String PINo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PIHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("PINO"), PINo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * PI Line Details
	 * 
	 * @author Vimal -RMITS
	 * @param PINo
	 * @param Line Value
	 * @return String Value
	 */
	public String PILineDetails(String PINo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PILineDetailQuery"),
				commonFunc.getDataFromTestDataSheet("PINO"), PINo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * CreditNote Header Details
	 * 
	 * @author Vimal -RMITS
	 * @param CRNo
	 * @param Header Value
	 * @return String Value
	 */
	public String CRHeaderDetails(String CRNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("CreditNoteHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("CRNO"), CRNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * CreditNote Line Details
	 * 
	 * @author Vimal -RMITS
	 * @param CRNo
	 * @param Line Value
	 * @return String Value
	 */
	public String CRLineDetails(String CRNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("CreditNoteLineQuery"),
				commonFunc.getDataFromTestDataSheet("CRNO"), CRNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Sales Invoice Header Query
	 * 
	 * @author Vimal -RMITS
	 * @param SINo
	 * @param Header Value
	 * @return String Value
	 */
	public String SIHeaderDetails(String SINo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SalesInvHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("SINO"), SINo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Methods Is Used To Validate Open PO Details
	 * 
	 * @author PremKumar.K-RMITS
	 * 
	 */
	public String OpenPoDetails(String SupplierId, String Item, String PlntId, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery2(getDataFromDBTestDataSheet("OpenPoQuery1"),
				commonFunc.getDataFromTestDataSheet("SupplierID"), SupplierId,
				commonFunc.getDataFromTestDataSheet("OpenPoId"), Item, commonFunc.getDataFromTestDataSheet("PlntId"),
				PlntId, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Methods Is Used To Validate Open PO Details
	 * 
	 * @author PremKumar.K-RMITS
	 * 
	 */
	public String OpenPoDetails(String SupplierId, String Item, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("OpenPoQuery"),
				commonFunc.getDataFromTestDataSheet("SupplierID"), SupplierId,
				commonFunc.getDataFromTestDataSheet("OpenPoId"), Item, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Methods Is Used To Validate Price List Details
	 * 
	 * @author PremKumar.K-RMITS
	 * 
	 */
	public String priceListDetails(String SupplierId, String Item, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PriceListQuery"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplierID"), SupplierId,
				commonFunc.getDataFromTestDataSheet("PriceListItemID"), Item, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Methods Is Used To Validate Price List Details
	 * 
	 * @author PremKumar.K-RMITS
	 * 
	 */
	public String priceListDetails(String SupplierId, String Item, String LocId, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery2(getDataFromDBTestDataSheet("PriceListQuery1"),
				commonFunc.getDataFromTestDataSheet("PriceListSupplierID"), SupplierId,
				commonFunc.getDataFromTestDataSheet("PriceListItemID"), Item,
				commonFunc.getDataFromTestDataSheet("LocId"), LocId, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate po Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */

	public String StockQuery3(String GRNNumber, String Item, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("GRNQuery"),
				commonFunc.getDataFromTestDataSheet("GRNNO"), GRNNumber, commonFunc.getDataFromTestDataSheet("GRNID"),
				Item, DBHeaderColumnValue);
		return value;
	}

	/**
	 * PriceList Entry Validate Location Id In DataBase
	 * 
	 * @author Durai-RMITS
	 * @param PRLISTNO
	 * @param DetailsValue
	 * @return
	 */
	public String PRLISTdetails(String PRLISTNO, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PriceListQuery"),
				commonFunc.getDataFromTestDataSheet("PRLISTNO"), PRLISTNO, DetailsValue);
		return value;
	}

	/**
	 * PriceList Entry Validate Location Id In DataBase
	 * 
	 * @author Durai-RMITS
	 * @param PRLISTNO
	 * @param DetailsValue
	 * @return
	 */
	public String SSOdetails(String SSONO, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SsoQuery"),
				commonFunc.getDataFromTestDataSheet("SSONO"), SSONO, DetailsValue);
		return value;
	}

	/**
	 * PriceList Entry Validate Location Id In DataBase
	 * 
	 * @author Durai-RMITS
	 * @param PRLISTNO
	 * @param DetailsValue
	 * @return
	 */
	public String OPOdetails(String OPONO, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("OpoQuery"),
				commonFunc.getDataFromTestDataSheet("OPONO"), OPONO, DetailsValue);
		return value;
	}

	/**
	 * PriceList Entry Validate Location Id In DataBase
	 * 
	 * @author Durai-RMITS
	 * @param PRLISTNO
	 * @param DetailsValue
	 * @return
	 */
	public String GATEdetails(String GATENO, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("GateQuery"),
				commonFunc.getDataFromTestDataSheet("GATENO"), GATENO, DetailsValue);
		return value;
	}

	/**
	 * PriceList Entry Validate Location Id In DataBase
	 * 
	 * @author Durai-RMITS
	 * @param PRLISTNO
	 * @param DetailsValue
	 * @return
	 */

	public String getDataFromDBUsingQuery2(String Query, String Replacechar, String Replacedchar, String Replacechar1,
			String Replacedchar1, String Replacechar2, String Replacedchar2, String ExpectedData) {
		String returnname = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (getConnection() == null) {
				establishDBConnection();
			}
			Query = Query.replace(Replacechar, Replacedchar).replace(Replacechar1, Replacedchar1).replace(Replacechar2,
					Replacedchar2);
			System.out.println("query " + Query);
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

	public String RFQdetails(String RFQNO, String ItemId, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("RFQQuery"),
				commonFunc.getDataFromTestDataSheet("RFQNO"), RFQNO,
				commonFunc.getDataFromTestDataSheet("RFQDBItem_ID"), ItemId, DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String StockQuery2(String GATENumber, String SubSeqNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("GateQuery1"),
				commonFunc.getDataFromTestDataSheet("GATENO"), GATENumber, commonFunc.getDataFromTestDataSheet("SEQNO"),
				SubSeqNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String RFQQueryForItem(String RFQNONumber, String SeqNumber, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("RFQQuery1"),
				commonFunc.getDataFromTestDataSheet("RFQNO"), RFQNONumber,
				commonFunc.getDataFromTestDataSheet("RFQSEQNO"), SeqNumber, DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String RFQQueryForItemSupplier(String RFQNONumber, String SeqNumber, String Supplier,
			String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery2(getDataFromDBTestDataSheet("RFQQuery1"),
				commonFunc.getDataFromTestDataSheet("RFQNO"), RFQNONumber,
				commonFunc.getDataFromTestDataSheet("RFQSEQNO"), SeqNumber,
				commonFunc.getDataFromTestDataSheet("SUPNO"), Supplier, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Rework Order Header Details
	 * 
	 * @author Vimal -RMITS
	 * @param RWNo
	 * @param Header Value
	 * @return String Value
	 */
	public String RWHeaderDetails(String RWNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("ReworkOrderHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("RWNO"), RWNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Rework Order Completion Header Details
	 * 
	 * @author Vimal -RMITS
	 * @param RWCNo
	 * @param Header Value
	 * @return String Value
	 */
	public String RWCHeaderDetails(String RWCNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("ReworkCompletionQuery"),
				commonFunc.getDataFromTestDataSheet("RWCNO"), RWCNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * DebitNote Header Details
	 * 
	 * @author Vimal -RMITS
	 * @param DBNo
	 * @param Header Value
	 * @return String Value
	 */
	public String DBHeaderDetails(String DBNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("DebitNoteHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("DBNO"), DBNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Price List DataBase
	 * 
	 * @author Kowshik-RMITS
	 * @param SalesPriceList
	 * @param DetailsValue
	 * @return
	 */
	public String SalesPriceListdetails(String SalesPriceList, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SalesPricelistQuery"),
				commonFunc.getDataFromTestDataSheet("PlDocNo"), SalesPriceList, DetailsValue);
		return value;

	}

	/**
	 * Sales Catalogue Price
	 * 
	 * @author Kowshik-RMITS
	 * @param SalesCataloguePrice
	 * @param DetailsValue
	 * @return
	 */
	public String SalesCataloguePrice(String SalesCataloguePrice, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("CatalogueQuery"),
				commonFunc.getDataFromTestDataSheet("CatDocNo"), SalesCataloguePrice, DetailsValue);
		return value;
	}

	/**
	 * Sales Std Price
	 *
	 * @author Kowshik-RMITS
	 * @param SalesStdPrice
	 * @param DetailsValue
	 * @return
	 */
	public String SalesStandardPrice(String SalesStdPrice, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("StdQuery"),
				commonFunc.getDataFromTestDataSheet("StdItemUniq"), SalesStdPrice, DetailsValue);
		return value;
	}

	/**
	 * SO Line Details After SO Amendment
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param SeqNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SOLineDetailsAfterSOA(String SONo, String SeqNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("SOAfterSOALineQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, commonFunc.getDataFromTestDataSheet("PONO"), SeqNo,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * CS Header Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String CSHeaderDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("CSHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * CS Line Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SOANo
	 * @param SeqNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String CSLineDetails(String CSNo, String DocNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("CSLineQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), CSNo, commonFunc.getDataFromTestDataSheet("PONO"), DocNo,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String InAplctrl(String Aplctl, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("MRVApllicationCtrl"),
				commonFunc.getDataFromTestDataSheet("APLCTRLBU"), Aplctl, DetailsValue);
		return value;
	}

	/**
	 * 
	 * @author Premkumar.K -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public ArrayList<String> MrvDocDetails(String MRVNumber, String DetailsValue) {
		ArrayList<String> value = getDataFromDBUsingQueryWithArrayList(getDataFromDBTestDataSheet("MRVDocumentQuery"),
				commonFunc.getDataFromTestDataSheet("GRNSourceNumber"), MRVNumber, DetailsValue);
		return value;
	}

	/**
	 * get Required Data From DB Using Query
	 * 
	 * @author Deepan-GWL
	 * @param Query
	 * @param ExpectedData
	 * @return String Value
	 */
	public ArrayList<String> getDataFromDBUsingQueryWithArrayList(String Query, String Replacechar, String Replacedchar,
			String ExpectedData) {
		ArrayList<String> returnnameArray = new ArrayList<String>();
		String returnname = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (getConnection() == null) {
				establishDBConnection();
			}
			Statement statement = getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(Query.replace(Replacechar, Replacedchar));
			while (resultSet.next()) {
				returnname = resultSet.getString(ExpectedData);
				System.out.println("Data Is: " + returnname);
				returnnameArray.add(returnname);
			}
			resultSet.close();
			statement.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return returnnameArray;
	}

	/**
	 * StockQuery
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String GrnHeaderDetails(String GRNNumber, String DetailsValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("GRNHeaderStatus"),
				commonFunc.getDataFromTestDataSheet("GRNSourceNumber"), GRNNumber, DetailsValue);
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
	public String PRLineDetailsQueryKare(String PrNo, String ItemId, String HeaderValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PRLineDetailsQueryKARE"),
				commonFunc.getDataFromTestDataSheet("PRNO"), PrNo, commonFunc.getDataFromTestDataSheet("ItemId"),
				ItemId, HeaderValue);
		return value;
	}

	/**
	 * SOM Control
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String SOMControl(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SOMCONTROL"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Item Master Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String ItemMaster(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("ItemMaster"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Lot Serial Stock Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param VoucherNo
	 * @param ItemID
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String LotSerialStockDetails(String VoucherNo, String ItemID, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("LotSerialStockQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), VoucherNo, commonFunc.getDataFromTestDataSheet("PONO"),
				ItemID, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Serial Stock Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param VoucherNo
	 * @param ItemID
	 * @param SerialNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SerialNumberDetails(String VoucherNo, String ItemID, String SerialNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery2(getDataFromDBTestDataSheet("SerialStockQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), VoucherNo, commonFunc.getDataFromTestDataSheet("PONO"),
				ItemID, commonFunc.getDataFromTestDataSheet("SRNO"), SerialNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate po Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */

	public String StockQuery4(String GRNNumber, String Item, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("GRNQuery1"),
				commonFunc.getDataFromTestDataSheet("GRNNO"), GRNNumber, commonFunc.getDataFromTestDataSheet("GRNID"),
				Item, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Open SCO Price Header Details
	 * 
	 * @author Vimal -RMITS
	 * @param OSPNo
	 * @param Header Value
	 * @return String Value
	 */
	public String OSPHeaderDetails(String OSPNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("OSPHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("OSPNO"), OSPNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Open SCO Price Header Details
	 * 
	 * @author Vimal -RMITS
	 * @param OSPNo
	 * @param Line  Value
	 * @return String Value
	 */
	public String OSPLineDetails(String OSPNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("OSPLineQuery"),
				commonFunc.getDataFromTestDataSheet("OSPNO"), OSPNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String GateQuery1(String GATENumber, String SubSeqNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("GateQuery1"),
				commonFunc.getDataFromTestDataSheet("GATENO"), GATENumber, commonFunc.getDataFromTestDataSheet("SEQNO"),
				SubSeqNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SCO Header Details
	 * 
	 * @author Vimal -RMITS
	 * @param SCONo
	 * @param header Value
	 * @return String Value
	 */
	public String SCOHeaderDetails(String SCONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SCOHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("SCONO"), SCONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SCO Line Details
	 * 
	 * @author Vimal -RMITS
	 * @param SCONo
	 * @param Line  Value
	 * @return String Value
	 */
	public String SCOLineDetails(String SCONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SCOLineQuery"),
				commonFunc.getDataFromTestDataSheet("SCONO"), SCONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SCO Process Details
	 * 
	 * @author Vimal -RMITS
	 * @param SCONo
	 * @param Process Value
	 * @return String Value
	 */
	public String SCOProcessDetails(String SCONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SCOProcessQuery"),
				commonFunc.getDataFromTestDataSheet("SCONO"), SCONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SCO Material Details
	 * 
	 * @author Vimal -RMITS
	 * @param SCONo
	 * @param Material Value
	 * @return String Value
	 */
	public String SCOMaterialDetails(String SCONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SCOMaterialQuery"),
				commonFunc.getDataFromTestDataSheet("SCONO"), SCONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SCO Linewise Details
	 * 
	 * @author Vimal -RMITS
	 * @param SCONo
	 * @param Linewise Value
	 * @return String Value
	 */
	public String SCOLineWiseDetails(String SCONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SCOLineWiseDetQuery"),
				commonFunc.getDataFromTestDataSheet("SCONO"), SCONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Overtime Lines Details
	 * 
	 * @author Kishore-RMITS
	 * 
	 */
	public String OvertimeLinesDetails(String OTNO, String Emp, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("OvertimeAmount"),
				commonFunc.getDataFromTestDataSheet("OTNO"), OTNO, commonFunc.getDataFromTestDataSheet("EMPID"), Emp,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * Bank Payment Header details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String BankPaymentHeaderDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("BankPaymentHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * CS Line Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SOANo
	 * @param SeqNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String BankPaymentLineDetails(String BRNo, String SeqNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("BankPaymentLineQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), BRNo, commonFunc.getDataFromTestDataSheet("PONO"), SeqNo,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * GLM Control
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String GLMControl(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("GLMControlQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Bank Payment Header details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String BankReceiptPendingReceivablesDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("BankReceiptPendingQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Exchange Rate details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String ExchangeRateDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("ExchangeRateQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Bank Payment Header details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String BankPaymentPendingPayablesDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("BankPaymentPendingQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Bank Payment Header details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String BankPaymentPBHeaderDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PBHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * get Required Data From DB Using Query Using 2 Parameters
	 * 
	 * @author Harikrishna - RMITS
	 * @param Query
	 * @param Replacechar
	 * @param Replacedchar
	 * @param Replacechar1
	 * @param Replacedchar1
	 * @param ExpectedData
	 * @return String returnname
	 */
	public String[][] getMultipleDataFromDBUsingQuery(String Query, String Replacechar, String Replacedchar,
			String ExpectedData) {
		String[][] tableData = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (getConnection() == null) {
				establishDBConnection();
			}

			Statement statement = getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(Query.replace(Replacechar, Replacedchar));

			ResultSetMetaData meta = resultSet.getMetaData();
			int columnCount = meta.getColumnCount();

			List<String[]> rows = new ArrayList<>();

			while (resultSet.next()) {
				String[] row = new String[columnCount];
				for (int i = 0; i < columnCount; i++) {
					row[i] = resultSet.getString(i + 1); // Column index starts from 1
				}
				rows.add(row);
			}

			// Convert List<String[]> to String[][]
			tableData = rows.toArray(new String[0][0]);

			resultSet.close();
			statement.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// Print 2D array for verification
		for (String[] row : tableData) {
			System.out.println(Arrays.toString(row));
		}
		return tableData;
	}

	/**
	 * PR Line Details
	 * 
	 * @author Deepan-GWL
	 * @param PrNo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String[][] SOLineDetailsQueryWithSeq(String SONo, String HeaderValue) {
		String[][] value = getMultipleDataFromDBUsingQuery(getDataFromDBTestDataSheet("SOLineQueryWithSeq1"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, HeaderValue);
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
	public String SOLineDetailsQueryWithSeq(String SONo, String SeqNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("SOLineQueryWithSeq"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, commonFunc.getDataFromTestDataSheet("PONO"), SeqNo,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate GRN Header Details
	 * 
	 * @author Durai -RMITS
	 * @param GRNNumber
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String GRNPBHeaderQuery(String GRNNumber, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("GRNPBHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("GRNNO"), GRNNumber, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Purchase Bills Headers
	 * 
	 * @author Kowshik-RMITS
	 * @param PurchaseBillsEx
	 * @param DBHeaderColumnValue
	 * @return
	 */
	public String PurchaseBillsExHeader(String PurchaseBillsEx, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PurchaseBillsHeader"),
				commonFunc.getDataFromTestDataSheet("PurchaseBillsExNO"), PurchaseBillsEx, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Purchase Bills Lines
	 * 
	 * @author Kowshik-RMITS
	 * @param PurchaseBillsEx
	 * @param DBHeaderColumnValue
	 * @param string
	 * @return
	 */
	public String PurchaseBillsEx(String PurchaseBillsEx, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PurchaseBillsEx"),
				commonFunc.getDataFromTestDataSheet("PurchaseBillsExNO"), PurchaseBillsEx, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Is Used To Compare Po Document and Purchase Bills Document
	 * 
	 * @param PurchaseBillsEx
	 * @param item
	 * @param DBHeaderColumnValue
	 * @return
	 */
	public String PurchaseBillsLineWise(String PurchaseBillsEx, String item, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PurchaseBillsEx"),
				commonFunc.getDataFromTestDataSheet("PurchaseBillsExNO"), PurchaseBillsEx,
				commonFunc.getDataFromTestDataSheet("PRBillsId"), item, DBHeaderColumnValue);
		return value;

	}

	/**
	 * This Is Method Used To Db Valid a Item With Expenses Account
	 * 
	 * @author Kowshik-RMITS
	 * @param PurchaseBillsEx
	 * @param DBHeaderColumnValue
	 * @return
	 */
	public String PurchaseBillsItemWithExpOrdBy(String PurchaseBillsEx, String SeqNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PurchaseBillsDocOrderBy"),
				commonFunc.getDataFromTestDataSheet("PurchaseBillsExNO"), PurchaseBillsEx,
				commonFunc.getDataFromTestDataSheet("SeqNo"), SeqNo, DBHeaderColumnValue);
		return value;

	}

	/**
	 * PurchaseBills Header Details
	 * 
	 * @author Durai -RMITS
	 * @param PBNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String PBQuery(String PBNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PurchaseBillQuery"),
				commonFunc.getDataFromTestDataSheet("PBNO"), PBNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate Purchase Bills Pending GRN
	 * 
	 * @author Durai -RMITS
	 * @param GRNNumber
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String PBPendingGRNQuery(String GRNNumber, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PBPendingGRnQuery"),
				commonFunc.getDataFromTestDataSheet("GRNNO"), GRNNumber, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate PB Pending GRn Lines
	 * 
	 * @author Durai -RMITS
	 * @param GRNNumber
	 * @param SubSeqNO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */

	public String PBPendingGRNLineQuery(String GRNNumber, String SeqNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PBPendingGRNLineQuery"),
				commonFunc.getDataFromTestDataSheet("GRNNO"), GRNNumber, commonFunc.getDataFromTestDataSheet("SEQNO"),
				SeqNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate Purchase Bills line Details
	 * 
	 * @author Durai -RMITS
	 * @param GRNNumber
	 * @param SubSeqNO
	 * @param DBHeaderColumnValue
	 */

	public String PurchaseBillLineQuery(String PBNo, String SeqNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PurchaseBillLineQuery"),
				commonFunc.getDataFromTestDataSheet("PBNO"), PBNo, commonFunc.getDataFromTestDataSheet("SEQNO"), SeqNO,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate GRN Line Details
	 * 
	 * @author Durai -RMITS
	 * @param GRNNumber
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String GRNPBLineQuery(String GRNNumber, String SeqNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("GRNPBLineQuery"),
				commonFunc.getDataFromTestDataSheet("GRNNO"), GRNNumber, commonFunc.getDataFromTestDataSheet("SEQNO"),
				SeqNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate PB Pending GRn Lines
	 * 
	 * @author Durai -RMITS
	 * @param GRNNumber
	 * @param SubSeqNO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */

	public String GRNPBLineQueryHist(String GRNNumber, String SeqNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("GRNPBLineQueryHist"),
				commonFunc.getDataFromTestDataSheet("GRNNO"), GRNNumber, commonFunc.getDataFromTestDataSheet("SEQNO"),
				SeqNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate PB Pending GRn Lines
	 * 
	 * @author Durai -RMITS
	 * @param GRNNumber
	 * @param SubSeqNO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */

	public String PurchaseBillLine(String PBNo, String SeqNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PurchaseBillLine"),
				commonFunc.getDataFromTestDataSheet("PBNO"), PBNo, commonFunc.getDataFromTestDataSheet("SEQNO"), SeqNO,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate Purchase Bills Pending GRN
	 * 
	 * @author PremKumar.K -RMITS
	 * @param GRNNumber
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */

	public String GrnLandedCostQuery(String GRNNO, String SeqNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("LandedCostQuery"),
				commonFunc.getDataFromTestDataSheet("GRNNO"), GRNNO, commonFunc.getDataFromTestDataSheet("SUPID"),
				SeqNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate Purchase Bills Pending GRN
	 * 
	 * @author PremKumar.K -RMITS
	 * @param GRNNumber
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String PurchaseBillLandedCostQuery(String PBNo, String SeqNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PBLandedCost"),
				commonFunc.getDataFromTestDataSheet("PBNO"), PBNo, commonFunc.getDataFromTestDataSheet("SupSeqNo"),
				SeqNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate po Details
	 * 
	 * @author PremKumar.K -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */

	public String PBLineLevelQuery(String PBNo, String Item, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PBHSNLineWiseQuery"),
				commonFunc.getDataFromTestDataSheet("PBNO"), PBNo, commonFunc.getDataFromTestDataSheet("PBItemID"),
				Item, DBHeaderColumnValue);
		return value;
	}

	/**
	 * This Method Is Used To Validate po Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */

	public String GRNhistQuery(String GRNNumber, String Item, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("GRNHistQuery"),
				commonFunc.getDataFromTestDataSheet("GRNNO"), GRNNumber, commonFunc.getDataFromTestDataSheet("GRNID"),
				Item, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SCO GRN LINEWISE
	 * 
	 * @author Vimal -RMITS
	 * @param GRNSNO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SCOGRNLINEWISE(String GRNSNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SCOGRNLineQuery"),
				commonFunc.getDataFromTestDataSheet("GRNSNO"), GRNSNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SCO GRN LINEWISE Process Tab
	 * 
	 * @author Vimal -RMITS
	 * @param GRNSNO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SCOGRNLineWiseProcTab(String GRNSNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SCOGRNLineProcessTabQuery"),
				commonFunc.getDataFromTestDataSheet("GRNSNO"), GRNSNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Bank Payment Header details
	 * 
	 * @author PremKumar.K -RMITS
	 * @param PONO
	 * @param HeaderValue
	 * @return String Value
	 */
	public String PurchaseOrderHeaderDetails(String PONO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("POHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("PONO"), PONO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SCOA Amendment Process
	 * 
	 * @author Vimal -RMITS
	 * @param SCOANO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SCOAmendmentProcTab(String SCOANO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SCOAmendmentProcessQuery"),
				commonFunc.getDataFromTestDataSheet("SCOANO"), SCOANO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * SCOA Amendment Line
	 * 
	 * @author Vimal -RMITS
	 * @param SCOANO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SCOAmendmentLine(String SCOANO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SCOAmendmentLineQuery"),
				commonFunc.getDataFromTestDataSheet("SCOANO"), SCOANO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Bank Payment Header details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String bankReceiptPendingReceivablesAdvanceDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("BankReceipPendingAdvanceQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * CS Line Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SOANo
	 * @param SeqNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String applicationJournalViewDetails(String BRNo, String SeqNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("JournalQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), BRNo, commonFunc.getDataFromTestDataSheet("PONO"), SeqNo,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * ARM Control
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String ARMControl(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("ARMControlQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * ARM Control
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String customerDocumentQuery(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("CustomerDocumentQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * ARM Control
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String customerDocumentQueryTotalAMT(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("CustomerDocumentTOTQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * ARM Control
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String SIHeaderQuery(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SIHeaderQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * ARM Control
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String BRVHeaderQuery(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("BRVQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * ARM Control
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String PBDocQuery(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PBDocQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * PDC Query
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String PDCQuery(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PDCQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * PDC Query
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String PDCCount(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PDCCountQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * PurchaseBills Reference Query
	 * 
	 * @author Vimal -RMITS
	 * @param PBNO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String PBReferenceQuery(String PBNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PurchaseBillsReferenceQuery"),
				commonFunc.getDataFromTestDataSheet("PBNO"), PBNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * PurchaseBills Process Query
	 * 
	 * @author Vimal -RMITS
	 * @param PBNO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String PBProcessQuery(String PBNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("PurchaseBillsProcessQuery"),
				commonFunc.getDataFromTestDataSheet("PBNO"), PBNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery
	 * 
	 * @author PremKumar.K -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String PbJournalQueryPK(String PBVoucherNumber, String GLDesc, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PBJournalQueryPK"),
				commonFunc.getDataFromTestDataSheet("PBVouNum"), PBVoucherNumber,
				commonFunc.getDataFromTestDataSheet("PBGL"), GLDesc, DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery
	 * 
	 * @author PremKumar.K -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public List<String> PbJournalViewDetailsQueryPK(String PBVoucherNumber, String GLDesc, String DBHeaderColumnValue) {
		List<String> value = getDataFromDBUsingQuery2(getDataFromDBTestDataSheet("PBJournalDetailsViewPK"),
				commonFunc.getDataFromTestDataSheet("PBVouNum"), PBVoucherNumber,
				commonFunc.getDataFromTestDataSheet("PBVoucherLN"), GLDesc, DBHeaderColumnValue);
		System.out.println("value check " + value);
		return value;
	}

	public List<String> getDataFromDBUsingQuery2(String Query, String Replacechar, String Replacedchar,
			String Replacechar1, String Replacedchar1, String ExpectedData) {
		String returnname = null;
		List<String> results = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			if (getConnection() == null) {
				establishDBConnection();
			}
			Query = Query.replace(Replacechar, Replacedchar).replace(Replacechar1, Replacedchar1);
//			System.out.println("Query " + Query);
			Statement statement = getConnection().createStatement();
//			System.out.println("Ststement" + statement);
			ResultSet resultSet = statement.executeQuery(Query);
//			System.out.println("resultSet" + resultSet.toString().toString());
			while (resultSet.next()) {
//				System.out.println("Db Value" + resultSet);
				returnname = resultSet.getString(ExpectedData); // Adjust column name
//				System.out.println("Data Is: " + returnname);
				results.add(returnname);
			}
			resultSet.close();
			statement.close();
//			closeDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * StockQuery
	 * 
	 * @author PremKumar.K -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String PbJournalDetailPartForAccuralAccount(String PBVoucherNumber, String GLDesc,
			String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PBJournalDetailsQueryPK"),
				commonFunc.getDataFromTestDataSheet("PBVouNum"), PBVoucherNumber,
				commonFunc.getDataFromTestDataSheet("PBGL"), GLDesc, DBHeaderColumnValue);
		return value;
	}

	/**
	 * PO Against Advance
	 * 
	 * @author Kowshik-RMITS
	 * @param PONO
	 * @param DBHeaderColumnValue
	 * @return
	 */
	public String POAdvanceDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("POAdvanceQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
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
	public String PendingSubtotalForPendingPayables(String ADV01, String HeaderValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("pendingPayablesQuary"),
				commonFunc.getDataFromTestDataSheet("ADV01"), ADV01, HeaderValue);
		return value;
	}

	/**
	 * Purchase Bills Tds DB
	 * 
	 * @author Kowshik-RMITS
	 * @param PurchaseBillsEx
	 * @param SeqNo
	 * @param DBHeaderColumnValue
	 * @return
	 */
	public String PurchaseBillsDB(String PurchaseBillsEx, String SeqNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("PurchaseBillsDocOrderBy"),
				commonFunc.getDataFromTestDataSheet("PurchaseBillsExNO"), PurchaseBillsEx,
				commonFunc.getDataFromTestDataSheet("SeqNo"), SeqNo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * BOM Header Details
	 * 
	 * @author Sakthi-GWL
	 * @param BOMNo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String validateBOMHeaderDetails(String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("BOMHeaderQuery");
		String value = getDataFromDBUsingQuery(Query, "${BOMNO}", VariableHandler.getBOMNumber(), DBHeaderColumnValue);
		return value;
	}

	/**
	 * BOM Routing Details
	 * 
	 * @author Sakthi-GWL
	 * @param BOMNo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String validateBOMRoutingDetails(String i, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("BOMRoutingQuery").replace("${SeqNo}", i);
		String value = getDataFromDBUsingQuery(Query, "${BOMNO}", VariableHandler.getBOMNumber(), DBHeaderColumnValue);
		return value;
	}

	/**
	 * BOM Lines Details
	 * 
	 * @author Sakthi-GWL
	 * @param BOMNo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String validateBOMLinesDetails(String OprnNo, String i, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("BOMLineQuery").replace("${OprnSeqNo}", OprnNo).replace("${SeqNo}",
				i);
		String value = getDataFromDBUsingQuery(Query, "${BOMNO}", VariableHandler.getBOMNumber(), DBHeaderColumnValue);
		return value;
	}

	/**
	 * Production Order Header Details
	 * 
	 * @author Sakthi-GWL
	 * @param ProductionOrderNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateProductionOrderHeaderDetails(String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("ProductionOrderHeaderQuery");
		String value = getDataFromDBUsingQuery(Query, "${ProdctionOrderNo}", VariableHandler.getProductionOrderNumber(),
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * Production Order Lines Details
	 * 
	 * @author Sakthi-GWL
	 * @param SeqNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateProductionOrderRoutingDetails(String SeqNo, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("ProductionOrderRoutingQuery").replace("${SeqNo}", SeqNo);
		String value = getDataFromDBUsingQuery(Query, "${ProductionOrderNo}",
				VariableHandler.getProductionOrderNumber(), DBHeaderColumnValue);
		return value;
	}

	/**
	 * Production Order Material Details
	 * 
	 * @author Sakthi-GWL
	 * @param SeqNo
	 * @param ConsNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateProductionOrderMaterialDetails(String SeqNo, String ConsNo, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("ProductionOrderMaterialQuery").replace("${SeqNo}", SeqNo)
				.replace("${ConsNo}", ConsNo);
		String value = getDataFromDBUsingQuery(Query, "${ProductionOrderNo}",
				VariableHandler.getProductionOrderNumber(), DBHeaderColumnValue);
		return value;
	}

	/**
	 * Completion Production Order Header Details
	 * 
	 * @author Sakthi-GWL
	 * @param CompProductionOrderNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateCompProductionOrderHeaderDetails(String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("CompProductionOrderHeaderQuery");
		String value = getDataFromDBUsingQuery(Query, "${CompProductionOrderNo}",
				VariableHandler.getProductionCompletionOrderNo(), DBHeaderColumnValue);
		return value;
	}

	/**
	 * Completion Production Order Lines Details
	 * 
	 * @author Sakthi-GWL
	 * @param CompProductionOrderNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateCompProductionOrderLinesDetails(String SeqNo, String OprnNo, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("CompProductionOrderLinesQuery").replace("${SeqNo}", SeqNo)
				.replace("${OprnNo}", OprnNo);
		String value = getDataFromDBUsingQuery(Query, "${CompProductionOrderNo}",
				VariableHandler.getProductionCompletionOrderNo(), DBHeaderColumnValue);
		return value;
	}

	/**
	 * Material Request Header Details
	 * 
	 * @author Sakthi-GWL
	 * @param MaterialRequestNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateMaterialRequestHeaderDetails(String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("MaterialRequestHeaderQuery");
		String value = getDataFromDBUsingQuery(Query, "${MaterialRequestNo}", VariableHandler.getMaterialRequestNo(),
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * Material Request Lines Details
	 * 
	 * @author Sakthi-GWL
	 * @param MaterialRequestNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateMaterialRequestLinesDetails(String i, String LineNo, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("MaterialRequestLinesQuery").replace("${SeqNo}", i)
				.replace("${LineNo}", LineNo);
		String value = getDataFromDBUsingQuery(Query, "${MaterialRequestNo}",
				VariableHandler.getProductionOrderNumber(), DBHeaderColumnValue);
		return value;
	}

	/**
	 * Material Issuance Voucher Header Details
	 * 
	 * @author Sakthi-GWL
	 * @param MaterialIssuanceVoucherNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateMaterialIssuanceVoucherHeaderDetails(String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("MaterialIssuanceVoucherHeaderQuery");
		String value = getDataFromDBUsingQuery(Query, "${MaterialIssuanceVoucherNo}", VariableHandler.getMIVNumber(),
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * Material Issuance Voucher Lines Details
	 * 
	 * @author Sakthi-GWL
	 * @param MaterialIssuanceVoucherNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateMaterialIssuanceVoucherLinesDetails(String i, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("MaterialIssuanceVoucherLinesQuery").replace("${SeqNo}", i);
		String value = getDataFromDBUsingQuery(Query, "${MaterialIssuanceVoucherNo}", VariableHandler.getMIVNumber(),
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * Material Issuance Voucher Lines Details
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param Warehouse
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateStockCostAndStockQuantityWithItemWarehouse(String Item, String Warehouse,
			String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("StockCheckQuery").replace("${Item}", Item);
		String value = getDataFromDBUsingQuery(Query, "${Warehouse}", Warehouse, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Lot Serial Value RM Details
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param Warehouse
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateLotSerialWithRMItemWarehouse(String Item, String Warehouse, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("LotSerialStockRMQuery").replace("${RawMaterial}", Item);
		String value = getDataFromDBUsingQuery(Query, "${Warehouse}", Warehouse, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Stock Transaction RM With Material Issuance Voucher
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateStockTransactionWithRMInMIV(String Item, String BucketType, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("StockTransWithOrderNoQuery").replace("${RawMaterial}", Item)
				.replace("${BucketType}", BucketType);
		String value = getDataFromDBUsingQuery(Query, "${OrderNo}", VariableHandler.getMIVNumber(),
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * Stock Transaction RM With Completion process Details
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateStockTransactionWithRMInCompletion(String Item, String BucketType,
			String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("StockTransWithOrderNoQuery").replace("${RawMaterial}", Item)
				.replace("${BucketType}", BucketType);
		String value = getDataFromDBUsingQuery(Query, "${OrderNo}", VariableHandler.getProductionCompletionOrderNo(),
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * Stock Transaction SFG With Completion process Details
	 * 
	 * @author Sakthi-GWL
	 * @param Warehouse
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String validateStockTransactionSFGWithRMInCompletion(String Warehouse, String DBHeaderColumnValue) {
		String Query = getDataFromDBTestDataSheet("StockTransSFGCompProdQuery").replace("${Warehouse}", Warehouse);
		String value = getDataFromDBUsingQuery(Query, "${CompProdOrderNo}",
				VariableHandler.getProductionCompletionOrderNo(), DBHeaderColumnValue);
		return value;
	}

	/**
	 * Supplier GL Acct Query
	 * 
	 * @author Vimal -RMITS
	 * @param SUPNO
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String SupplierGLaccountQuery(String SUPNO, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("SupplierGLaccountQuery"),
				commonFunc.getDataFromTestDataSheet("SUPNO"), SUPNO, DBHeaderColumnValue);
		return value;
	}

	/**
	 * Bank Payment Header details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SONo
	 * @param HeaderValue
	 * @return String Value
	 */
	public String CashPaymentAdvancePendingPayablesDetails(String SONo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery(getDataFromDBTestDataSheet("CashPaymentAdvancePendingQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), SONo, DBHeaderColumnValue);
		return value;
	}

	/**
	 * CS Line Details
	 * 
	 * @author Harikrishna -RMITS
	 * @param SOANo
	 * @param SeqNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public String ApplicationJournalViewDetails(String BRNo, String SeqNo, String DBHeaderColumnValue) {
		String value = getDataFromDBUsingQuery1(getDataFromDBTestDataSheet("JournalQuery"),
				commonFunc.getDataFromTestDataSheet("SONO"), BRNo, commonFunc.getDataFromTestDataSheet("PONO"), SeqNo,
				DBHeaderColumnValue);
		return value;
	}

	/**
	 * StockQuery
	 * 
	 * @author PremKumar.K -RMITS
	 * @param AdvReceiptDocNo
	 * @param DBHeaderColumnValue
	 * @return String Value
	 */
	public List<String> PbJournalViewDetailsQueryForTDSSupplierPK(String PBVoucherNumber, String GLDesc,
			String DBHeaderColumnValue) {
		List<String> value = getDataFromDBUsingQuery2(getDataFromDBTestDataSheet("PBJournalDetailsQueryPK"),
				commonFunc.getDataFromTestDataSheet("PBVouNum"), PBVoucherNumber,
				commonFunc.getDataFromTestDataSheet("PBGL"), GLDesc, DBHeaderColumnValue);
		System.out.println("value check " + value);
		return value;
	}


}
