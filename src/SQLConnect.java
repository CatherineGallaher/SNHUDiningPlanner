import java.sql.*;

public class SQLConnect {
	
	public static void connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
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
			
			String instanceConnectionName = "budgetapp2";
			String databaseName = "BudgetApp";

			String IP_of_instance = "35.226.216.86";
			String username = "Septri";
			String password = "septri258";
			
			//String testURL = "jdbc:mysql://35.226.216.86:budgetapp2?user=Septri";

			String jdbcUrl = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s" + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
			IP_of_instance, databaseName, instanceConnectionName);

			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
			
			Statement stat = connection.createStatement();
			ResultSet res = stat.executeQuery("Select * from users;");
			while(res.next())
			{
				System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3));
			}
			
			connection.close();
		}catch(Exception e) {System.out.println(e);}
	}
}
