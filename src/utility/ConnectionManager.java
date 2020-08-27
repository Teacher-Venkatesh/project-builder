package utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionManager {
	
		public static Connection getConnection() {
		Connection connection = null;
		Properties prop = null;
		try {
			prop = loadPropertiesFile();  // step 1
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		
		final String driver = prop.getProperty("driver"); // step 3
		final String url = prop.getProperty("url");
		final String username = prop.getProperty("username");
		final String password = prop.getProperty("password");
		try {
			Class.forName(driver);// load driver  step 4
			
		
			connection = DriverManager.getConnection(url,username,password);// step 5
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return connection; // step 6
	}
	
	
	public static Properties loadPropertiesFile() throws Exception { // step 2
		Properties prop = new Properties();
		InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close(); 
		return prop;
	}
}
