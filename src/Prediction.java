import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Prediction {
	
	private List<String> months = new ArrayList<String>();
	private int startMonth;
	private int endMonth;
	private int userID;
	private int breakfastStart, breakfastEnd, lunchStart, lunchEnd, dinnerStart, dinnerEnd;
	private double[] mealTypeAverage = {0, 0, 0, 0};
	private double[] monthAverage;
	private double spentPerDay;
	private int daysOffCampus;
	private double estAmountLeft;
	private ReadWriteSQL test = new ReadWriteSQL();
	
	public Prediction(int userID)
	{
		this.userID = userID;
		populateMonths();
	}
	
	private void populateMonths()
	{
		months.add("January");
		months.add("February");
		months.add("March");
		months.add("April");
		months.add("May");
		months.add("June");
		months.add("July");
		months.add("August");
		months.add("September");
		months.add("October");
		months.add("November");
		months.add("December");
	}
	
	public void ExtractData()
	{
		
	}
	
	private int parseMonth(String date)
	{
		String parsedDate[] = date.split(" ", 1);
		return months.indexOf(parsedDate[0]);
	}
	
	private int parseDay(String date)
	{
		String parsedDate[] = date.split(" ", 3);
		String parsedDate2[] = parsedDate[1].split(",", 2);
		
		return Integer.parseInt(parsedDate2[0]);
	}
	
	private int parseTime(String time)
	{
		return convertTime(time);
	}
	
	private double parseAmount(String amount)
	{
		String parsedAmount = amount.substring(1);
		double tester = Double.parseDouble(parsedAmount);
		return tester;
	}
	
	private int convertTime(String time)
	{
		int newTime = 0;
		time = time.replaceAll("([:])", "");
		newTime = Integer.parseInt(time.substring(0, time.length() - 2));
		if(time.substring(time.length() - 2).compareTo("PM") == 0 && (newTime < 1200))
		{
			newTime+=1200;
		}
		return newTime;
	}
	
	public void predictionSettings(String startDate, String endDate, int daysOffCampus, int breakfastStart, int breakfastEnd, int lunchStart, int lunchEnd, int dinnerStart, int dinnerEnd)
	{		
		this.startMonth = parseMonth(startDate);
		this.endMonth = parseMonth(endDate);
		this.breakfastStart = breakfastStart;
		this.breakfastEnd = breakfastEnd;
		this.lunchStart = lunchStart;
		this.lunchEnd = lunchEnd;
		this.dinnerStart = dinnerStart;
		this.dinnerEnd = dinnerEnd;
		this.daysOffCampus = daysOffCampus;
	}
	
	public void calcMealTypeAverage()
	{
		int numBreakfast = 0;
		int numLunch = 0;
		int numDinner = 0;
		int numSnack = 0;
		int transactionTime = 0;
		double breakfastAmount = 0;
		double lunchAmount = 0;
		double dinnerAmount = 0;
		double snackAmount = 0;
		
		//ReadWriteSQL mealAvg = new ReadWriteSQL();
		//mealAvg.createConnection();
		test.createConnection();
		List<String> times = test.getTimes();
		List<String> amounts = test.getAmounts();

		for(int i = 0; i < amounts.size(); i++)
		{
			transactionTime = parseTime(times.get(i));
			if(transactionTime >= breakfastStart && transactionTime <= breakfastEnd)
		 	{
		 		numBreakfast++;
		 		breakfastAmount += parseAmount(amounts.get(i));
		 	}
		 	else if(transactionTime >= lunchStart && transactionTime <= lunchEnd)
		 	{
		 		numLunch++;
		 		lunchAmount += parseAmount(amounts.get(i));
		 	}
		 	else if(transactionTime >= dinnerStart && transactionTime <= dinnerEnd)
		 	{
		 		numDinner++;
		 		dinnerAmount += parseAmount(amounts.get(i));
		 	}
		 	else
		 	{
		 		numSnack++;
		 		snackAmount += parseAmount(amounts.get(i));
		 	}
		}
		    
		  	
		
		mealTypeAverage[0] = breakfastAmount/numBreakfast;
		mealTypeAverage[1] = lunchAmount/numLunch;
		mealTypeAverage[2] = dinnerAmount/numDinner;
		mealTypeAverage[3] = snackAmount/numSnack;
		
		if(breakfastAmount == 0 || numBreakfast == 0)
		{
			mealTypeAverage[0] = 0;
		}
		if(lunchAmount == 0 || numLunch == 0)
		{
			mealTypeAverage[1] = 0;
		}
		if(dinnerAmount == 0 || numDinner == 0)
		{
			mealTypeAverage[2] = 0;
		}
		if(snackAmount == 0 || numSnack == 0)
		{
			mealTypeAverage[3] = 0;
		}
		
		//mealAvg.closeConnection();
	}
	
	public void calcMonthAverage() //Incomplete
	{
		double[] monthAverage = {endMonth - startMonth};
		
		
		this.monthAverage = monthAverage;
	}
	
	public void calcSpentPerDay()
	{
		//ReadWriteSQL perDay = new ReadWriteSQL();
		//perDay.createConnection();
		List<String> dates = test.getDates();
		List<String> amounts = test.getAmounts();
		double totalSpent = 0;
		int totalDays = 0;
		
		totalSpent += parseAmount(amounts.get(0));
		for(int i = 1; i < amounts.size(); i++)
		{
			totalSpent += parseAmount(amounts.get(i-1));
			
			if(parseDay(dates.get(i)) != parseDay(dates.get(i-1)))
			{
				totalDays++;
			}
		}
		/*try {
			estAmountLeft = Double.parseDouble(perDay.getAmountLeft("catherine.gallaher@snhu.edu").substring(1));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		test.closeConnection();
		
		spentPerDay = totalSpent/totalDays;
		
	}
	
	public void calcEstAmountLeft()
	{
		
	
	}
	
	public double[] getMealTypeAverage()
	{
		return mealTypeAverage;
	}
	
	public double[] getMonthAverage()
	{
		return monthAverage;
	}
	
	public double getSpentPerDay()
	{
		return spentPerDay;
	}
	
	public double getEstAmountLeft()
	{
		return estAmountLeft;
	}

}
