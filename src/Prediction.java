import java.util.ArrayList;
import java.util.List;

public class Prediction {
	
	private List<String> months = new ArrayList<String>();
	private int startMonth;
	private int endMonth;
	private int userID;
	private int breakfastStart, breakfastEnd, lunchStart, lunchEnd, dinnerStart, dinnerEnd;
	private double[] mealTypeAverage = {4};
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
		String parsedDate[] = date.split(" ", 2);
		return months.indexOf(parsedDate[0]);
	}
	
	private int parseTime(String date)
	{
		String parsedDate[] = date.split(" ");
		return convertTime(parsedDate[3]);
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

		/*	for(all the table)
		    transactionTime = parseTime(readDate)
		  	if(transactionTime >= breakfastStart && transactionTime <= breakfastEnd)
		 	{
		 		numBreakfast++;
		 		breakfastAmount += readAmount;
		 	}
		 	else if(transactionTime >= lunchStart && transactionTime <= lunchEnd)
		 	{
		 		numLunch++;
		 		lunchAmount += readAmount;
		 	}
		 	else if(transactionTime >= dinnerStart && transactionTime <= dinnerEnd)
		 	{
		 		numDinner++;
		 		dinnerAmount += readAmount;
		 	}
		 	else
		 	{
		 		numSnack++;
		 		snackAmount += readAmount;
		 	}
		 */
		
		mealTypeAverage[0] = breakfastAmount/numBreakfast;
		mealTypeAverage[1] = lunchAmount/numLunch;
		mealTypeAverage[2] = dinnerAmount/numDinner;
		mealTypeAverage[3] = snackAmount/numSnack;
	}
	
	public void calcMonthAverage()
	{
		double[] monthAverage = {endMonth - startMonth};
		
		
		this.monthAverage = monthAverage;
	}
	
	public void calcSpentPerDay()
	{
		
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
