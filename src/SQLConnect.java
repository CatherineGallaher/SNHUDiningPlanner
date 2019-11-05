import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLConnect {
	private List<ArrayList<String>> info;
	private String balance;
	private String email;
	private String password;
	
	public SQLConnect()
	{
	
	}
	public void connect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection con = DriverManager.getConnection("jdbc:mysql://35.226.216.86:budgetappsqlservertry2:us-central1:budgetapp2?user=Septri");
			//DriverManager.getConnection("jdbc:mysql://IP:Instance_name?user=user_name");
			/*
			Statement statement= con.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from users");
			while(resultSet.next())
			{
				System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
				con.close();
			}
			*/
			
			String instanceConnectionName = "budgetappsqlservertry2:us-central1:budgetapp2";
			String databaseName = "BudgetApp";

			String IP_of_instance = "35.226.216.86";
			String username = "Septri";
			String password = "septri258";
			
			//String testURL = "jdbc:mysql://35.226.216.86:budgetapp2?user=Septri";
			//jdbc:mysql://google/%s?cloudSqlInstance=<INSTANCE_CONNECTION_NAME>&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=<MYSQL_USER_NAME>&password=<MYSQL_USER_PASSWORD
			String jdbcUrl = String.format("jdbc:mysql://google/%s/%s?cloudSqlInstance=%s" + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=%s&password=%s",
					databaseName, IP_of_instance , instanceConnectionName, username, password);

			Connection connection = DriverManager.getConnection(jdbcUrl);
			
			Statement stat = connection.createStatement();
			ResultSet res = stat.executeQuery("Select * from users;");
			while(res.next())
			{
				System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3));
			}
			
			connection.close();
		}catch(Exception e) {System.out.println(e);}
	}
	
	public void setInfo()
	{
		SNHULogOn.dataScrape.logOn();
		balance = SNHULogOn.dataScrape.currBalance;
		//System.out.println(balance);
		this.info = SNHULogOn.dataScrape.getInfo();
	}
}
