
public class Prediction {
	
	public String[][] transactions;
	private String [] months = {"January", "February", "March", "April", "May", "June",  "July", "August", "September", "October",  "November", "December"};
	private int startMonth;
	private int endMonth;
	
	public Prediction(int startMonth, int endMonth)
	{
		this.startMonth = startMonth;
		this.endMonth = endMonth;
	}
	
	public void ExtractData(int userID)
	{
		
	}
	
	public int[] monthAverage()
	{
		int [] result = {endMonth - startMonth};
		
		 
		
		
		
		return result;
	}

}
