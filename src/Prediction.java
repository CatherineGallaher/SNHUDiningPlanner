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
		String parsedDate[] = date.split(" ", 2);
		String parsedDate2[] = parsedDate[0].split(",", 1);
		
		return Integer.parseInt(parsedDate2[0]);
	}
	
	private int parseTime(String time)
	{
		return convertTime(time);
	}
	
	private double parseAmount(String amount)
	{
		String parsedAmount = amount.substring(1);
		return Double.parseDouble(parsedAmount);
	}
	
	private int convertTime(String time)
	{
		int newTime = 0;
		time = time.replaceAll("([:])", "");
		newTime = Integer.parseInt(time.substring(0, time.length() - 3));
		if(time.substring(time.length() - 2) == "PM")
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
		
		ReadWriteSQL mealAvg = new ReadWriteSQL();
		mealAvg.createConnection();
		List<String> times = mealAvg.getTimes();
		List<String> amounts = mealAvg.getAmounts();
		for(int i = 0; i < amounts.size(); i++) {
			System.out.println(amounts.get(i) + " " + times.get(i));
		}

		for(int i = 0; i < amounts.size(); i++)
		{
			transactionTime = parseTime(times.get(i));
			System.out.println(transactionTime);
			System.out.println(numBreakfast + " " + numLunch + " " + numDinner + " " + numSnack);
			if(transactionTime >= breakfastStart && transactionTime <= breakfastEnd)
		 	{
		 		numBreakfast++;
		 		breakfastAmount += parseAmount(amounts.get(i));
		 	}
		 	else if(transactionTime >= lunchStart && transactionTime <= lunchEnd)
		 	{
		 		numLunch++;
		 		breakfastAmount += parseAmount(amounts.get(i));
		 	}
		 	else if(transactionTime >= dinnerStart && transactionTime <= dinnerEnd)
		 	{
		 		numDinner++;
		 		breakfastAmount += parseAmount(amounts.get(i));
		 	}
		 	else
		 	{
		 		numSnack++;
		 		breakfastAmount += parseAmount(amounts.get(i));
		 	}
		}
		    
		  	
		
		mealTypeAverage[0] = breakfastAmount/numBreakfast;
		mealTypeAverage[1] = lunchAmount/numLunch;
		mealTypeAverage[2] = dinnerAmount/numDinner;
		mealTypeAverage[3] = snackAmount/numSnack;
		
		for(int i = 0; i < 4; i++)
		{
			System.out.println(mealTypeAverage[i]);
		}
		
		mealAvg.closeConnection();
	}
	
	public void calcMonthAverage() //Incomplete
	{
		double[] monthAverage = {endMonth - startMonth};
		
		
		this.monthAverage = monthAverage;
	}
	
	public void calcSpentPerDay()
	{
		ReadWriteSQL perDay = new ReadWriteSQL();
		perDay.createConnection();
		List<String> dates = perDay.getDates();
		List<String> amounts = perDay.getAmounts();
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
		perDay.closeConnection();
	}
	
	public void calcEstAmountLeft()
	{
		//get amount left from database
		//return currentAmount - ((endDate.parseDate() - currentDate)*getSpentPerDay())
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
