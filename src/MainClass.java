public class MainClass {

	public static void main(String[] args) {
		//SNHULogOn a = new SNHULogOn();
		//a.logOn();
		
		ReadWriteSQL test = new ReadWriteSQL();
		test.createConnection();
		
		Prediction predict = new Prediction(2);
		predict.predictionSettings("October 20, 2019", "December 20, 2019", 5, 800, 1000, 1000, 1600, 1600, 2000);
		predict.calcMealTypeAverage();
		
		double[] mealTypeAverage = predict.getMealTypeAverage();
		
		System.out.println("Breakfast average: " + mealTypeAverage[0]);
		System.out.println("Lunch average: " + mealTypeAverage[1]);
		System.out.println("Dinner average: " + mealTypeAverage[2]);
		System.out.println("Snack average: " + mealTypeAverage[3]);

		test.closeConnection();
	}
}
