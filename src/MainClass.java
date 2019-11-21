import java.util.Scanner;

public class MainClass {
	
	

	public static void main(String[] args) {
		//SNHULogOn a = new SNHULogOn();
		//a.logOn();
		
		//SNHULogOn.dataScrape.logOn();
		//String balance = SNHULogOn.dataScrape.currBalance;
		//System.out.println(balance);
		
		//SQLConnect connect = new SQLConnect();
		//System.out.println("Result: " + connect.doInBackground("getall"));
		boolean isLoggedIn = SNHULogOn.dataScrape.logOn("catherine.gallaher@snhu.edu", "3Mog,3Or,3Mb44");//PasswordEncryption.encryptionAES("3Mog,3Or,3Mb44"));
		if(isLoggedIn)
		{
			/*Prediction.predict.predictionSettings("2019-09-03", "2019-12-20", 8, 600, 1000, 1000, 1600, 1600, 2000);
			Prediction.predict.calcDaysLeft();
			System.out.println("Days Left: " + Prediction.predict.getDaysLeft());
			Prediction.predict.calcSpentPerDay();
			System.out.println("Spent per day: " + Prediction.predict.getSpentPerDay());
			Prediction.predict.calcMealTypeAverage();
			System.out.println("Meal Type Avg: ");
			for(int i = 0; i < Prediction.predict.getMealTypeAverage().length; i++)
			{
				System.out.println(Prediction.predict.getMealTypeAverage()[i]);
			}
			Prediction.predict.calcMonthAverage();*/
		}
		
		
		//SQLConnect conOne = new SQLConnect();
		//conOne.doInBackground("createTITable", "ti_catherine_gallaher", "nerdalert42!");
		
		for(int i = 0; i < SNHULogOn.dataScrape.getInfo().size(); i++)
		{
			//dates: SNHULogOn.dataScrape.getInfo().get(i).get(0);
			//times: SNHULogOn.dataScrape.getInfo().get(i).get(1);
			//amounts: SNHULogOn.dataScrape.getInfo().get(i).get(2);
			//email: 
			//SQLConnect con = new SQLConnect();
			//String result = con.doInBackground("inputVal", "transactioninfo", SNHULogOn.dataScrape.getInfo().get(i).get(0), SNHULogOn.dataScrape.getInfo().get(i).get(1), SNHULogOn.dataScrape.getInfo().get(i).get(2), "null", "catherine_gallaher");
			//System.out.println(result);
		}
		
		
		
		
		
		System.out.println("Month Average: ");
		for(int i = 0; i < Prediction.predict.getMonthAverage().length; i++)
		{
			System.out.println(Prediction.predict.getMonthAverage()[i]);
		}
		
		/*Prediction predict = new Prediction(2);
		predict.predictionSettings("October 20, 2019", "December 20, 2019", 5, 600, 1000, 1000, 1600, 1600, 2000);
		System.out.println("Settings (set in settings tab):\n\tSchool year: October 20, 2019-December 20, 2019\n\tDays off: 5\n\tBreakfast Times: 6:00AM - 10:00AM\n\t Lunch Times: 10:00AM - 4:00PM\n\tDinner Times: 4:00PM - 8:00PM");
		
		predict.calcMealTypeAverage();
		predict.calcSpentPerDay();
		//predict.calcEstAmountLeft();
		
		double[] mealTypeAverage = predict.getMealTypeAverage();
		double avgDay = predict.getSpentPerDay();
		
		System.out.println("\n-----------------------------------------------\nAverage expense per meal type (breakfast, lunch, etc.): ");
		System.out.println("Breakfast average: " + mealTypeAverage[0]);
		System.out.println("Lunch average: " + mealTypeAverage[1]);
		System.out.println("Dinner average: " + mealTypeAverage[2]);
		System.out.println("Snack average: " + mealTypeAverage[3]);
		
		System.out.println("\n------------------------------------------------\nAverage spent per day: " + avgDay);
		
		System.out.println("\n------------------------------------------------\nEnter a string to be encrypted");
		Scanner in = new Scanner(System.in);
		
		String toBeEncrypted = in.nextLine();
	
		PasswordEncryption encryp = new PasswordEncryption();
		PasswordDecryption decryp = new PasswordDecryption();
		
		String encrypted = encryp.encryptionAES(toBeEncrypted);
		
		System.out.println("Your encrypted string is: " + encrypted);
		
		System.out.println("Your decrypted string is: " + decryp.decryptAES(encrypted));*/
		
	}
}
