import java.sql.*;

public class SQLConnect {
	
	public static void connect()
	{
		try
		{
			Class.forName("com.mysql.jbdc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BudgetApp", "username", "password");
			Statement statement= con.createStatement();
			ResultSet resultSet=statement.executeQuery("select * from users");
			while(resultSet.next())
			{
				System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
				con.close();
			}
		}catch(Exception e) {System.out.println(e);}
	}
}
