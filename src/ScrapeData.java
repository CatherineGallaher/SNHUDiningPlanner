public class ScrapeData {

	public void scrape()
	{
		ReadWriteSQL connect = new ReadWriteSQL();
		connect.createConnection();
		
		
		
		
		connect.closeConnection();
	}
	
}
