package com.example.budgetapprebuild;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class Prediction {
    public List<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
    public static Prediction predict = new Prediction();


    private List<String> months = new ArrayList<String>();
    private int startMonth;
    private int endMonth;
    private int userID;
    private int breakfastStart = 800, breakfastEnd = 1000, lunchStart = 1000, lunchEnd = 1400, dinnerStart = 1600, dinnerEnd = 2000;
    private double[] mealTypeAverage = {0, 0, 0, 0};
    private double[] monthAverage;
    private double spentPerDay;
    private int daysOffCampus;
    private double estAmountLeft;
    private int daysLeft;
    private String startDate;
    private String endDate;

    public Prediction(int userID)
    {
        this.userID = userID;
        populateMonths();
    }

    public Prediction()
    {
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

    public void calcDaysLeft()
    {
        //Parsing the date
        LocalDate dateBefore = LocalDate.now();
        LocalDate dateAfter = LocalDate.parse(endDate);

        //calculating number of days in between
        long numDays = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        // TODO: subtract days off campus when that number is pulled from the database
        daysLeft = (int)numDays;// - daysOffCampus

    }

    private int parseMonth(String date) //parse month from form "December 21, 1981" to "12
    {
        String parsedDate[] = date.split(" ");

        return months.indexOf(parsedDate[0]);
    }

    private int parseDay(String date) //parses day from form "December 21, 1981" to "21"
    {
        String parsedDate[] = date.split(" ", 3);
        String parsedDate2[] = parsedDate[1].split(",", 2);

        return Integer.parseInt(parsedDate2[0]);
    }

    private int parseTime(String time) //Why do I have this?  I don't know
    {
        return convertTime(time);
    }

    private double parseAmount(String amount) //parses amount from form "$100.00" to "100.00"
    {
        String parsedAmount = amount.substring(1);
        double tester = Double.parseDouble(parsedAmount);
        return tester;
    }

    private int convertTime(String time) //converts time to military time of the form 1600 (rather than 4:00PM)
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

    public void predictionSettings(String startDate, String endDate, int daysOffCampus, int breakfastStart, int breakfastEnd, int lunchStart, int lunchEnd, int dinnerStart, int dinnerEnd) //Sets settings, called from DataFragment
    {
        this.startMonth = parseMonth(startDate);
        this.endMonth = parseMonth(endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.breakfastStart = breakfastStart;
        this.breakfastEnd = breakfastEnd;
        this.lunchStart = lunchStart;
        this.lunchEnd = lunchEnd;
        this.dinnerStart = dinnerStart;
        this.dinnerEnd = dinnerEnd;
        this.daysOffCampus = daysOffCampus;
    }

    public void calcMealTypeAverage() //calculates average expense per meal type (breakfast, lunch, dinner, snacks) based on user settings.
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

        List<String> times = new ArrayList<String>();
        List<String> amounts = new ArrayList<String>();
        for(int j = 0; j < this.info.size(); j++)
        {
            times.add(this.info.get(j).get(1));
            amounts.add(this.info.get(j).get(2));
        }

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
    }

    public void calcMonthAverage() //calculates (despite its name, plans changed) the TOTAL amount spent per month
    {
        double[] monthAverage = {0,0,0,0,0,0,0,0,0,0,0,0};
        int [] monthTransactionCount = {0,0,0,0,0,0,0,0,0,0,0,0};


        this.monthAverage = monthAverage;

        List<String> dates = new ArrayList<String>();
        List<String> amounts = new ArrayList<String>();
        for(int j = 0; j < this.info.size(); j++)
        {
            dates.add(this.info.get(j).get(0));
            amounts.add(this.info.get(j).get(2));
        }

        int currMonth = parseMonth(dates.get(0));


        int monthIndex = parseMonth(dates.get(0));
        double amountSum = 0;
        for(int i = 0; i < amounts.size(); i++)
        {
            currMonth = parseMonth(dates.get(i));
            monthAverage[currMonth] += Double.parseDouble(amounts.get(i).substring(1));
            monthTransactionCount[currMonth]++;
        }
    }

    public void calcSpentPerDay() // calculates the average amount spent per day
    {
        List<String> dates = new ArrayList<String>();
        List<String> amounts = new ArrayList<String>();
        for(int j = 0; j < this.info.size(); j++)
        {
            dates.add(this.info.get(j).get(0));
            amounts.add(this.info.get(j).get(2));
        }

        double totalSpent = 0;
        int totalDays = 1;
        totalSpent += parseAmount(amounts.get(0));
        for(int i = 1; i < amounts.size(); i++)
        {
            totalSpent += parseAmount(amounts.get(i-1));

            if(parseDay(dates.get(i)) != parseDay(dates.get(i-1)))
            {
                totalDays++;
            }

            if(startMonth == parseMonth(dates.get(i-1)))
            {
            }
        }
        spentPerDay = totalSpent/totalDays;

    }

    public void calcEstAmountLeft() //calculates the estimated amount of money left at the end of the semester using the average spent per day and the days left in the semester
    {
        estAmountLeft = SNHULogOn.dataScrape.currBalance - spentPerDay*daysLeft;
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

    public int getDaysLeft()
    {
        return daysLeft;
    }

    public double parseBalance(String balance) //parses the balance from the form "$100.00" to the form "100.00"
    {
        return Double.parseDouble(balance.substring(1));
    }

    public List<Double> spentGraph() //reverse engineers the initial amount and creates a chronological representation of the user's balance as it goes down through the semester
    {
        List<Double> myList = new ArrayList<Double>();
        double spentSum = SNHULogOn.dataScrape.currBalance;
        for(int j = 0; j < this.info.size(); j++)
        {
            spentSum += Double.parseDouble(this.info.get(j).get(2).substring(1));
        }

        myList.add(spentSum);
        for(int i = this.info.size()-1; i >=0; i--)
        {
            spentSum -= Double.parseDouble(this.info.get(i).get(2).substring(1));
            if(spentSum <0.5)
            {
                spentSum = 0;
            }
            myList.add(spentSum);
        }

        return myList;
    }

    public void setInfo(String jsonText) //parses the json file obtained from the database to extract transaction data
    {
        String[] parsedRows = jsonText.split("([{])");//"}|\\{");
        String[] parsedCol;

        for(int i = 1; i < parsedRows.length-1; i++)
        {
            parsedCol = parsedRows[i].split(",");

            this.info.add(new ArrayList<String>());
            this.info.get(i-1).add(parsedCol[0] + ", " + parsedCol[1]);
            this.info.get(i-1).add(parsedCol[2]);
            this.info.get(i-1).add(parsedCol[3]);
        }
    }
}

