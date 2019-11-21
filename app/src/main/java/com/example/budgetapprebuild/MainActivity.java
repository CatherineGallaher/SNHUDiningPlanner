package com.example.budgetapprebuild;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import android.util.Log;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.budgetapprebuild.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("--------------------------------------------------------------------------\n");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        SQLConnect con = new SQLConnect();
        try {
            System.out.println("Result: " + con.execute("getTableInfo", "ti_catherine_gallaher2").get());
            Prediction.predict.setInfo(con.execute("getTableInfo", "ti_catherine_gallaher2").get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Jack and Catherine stuff
        //SNHULogOn a = new SNHULogOn();
        //a.logOn();

        /*
        Prediction predict = new Prediction(2);
        predict.predictionSettings("October 20, 2019", "December 20, 2019", 5, 600, 1000, 1000, 1600, 1600, 2000);
        System.out.println("Settings (set in settings tab):\n\tSchool year: October 20, 2019-December 20, 2019\n\tDays off: 5\n\tBreakfast Times: 6:00AM - 10:00AM\n\t Lunch Times: 10:00AM - 4:00PM\n\tDinner Times: 4:00PM - 8:00PM");

        predict.calcMealTypeAverage();
        predict.calcSpentPerDay();
        //predict.calcEstAmountLeft();

        double[] mealTypeAverage = predict.getMealTypeAverage();
        double avgDay = predict.getSpentPerDay();

        Log.d("Demo", "\n-----------------------------------------------\nAverage expense per meal type (breakfast, lunch, etc.): ");
        Log.d("Demo","Breakfast average: " + mealTypeAverage[0]);
        Log.d("Demo","Lunch average: " + mealTypeAverage[1]);
        Log.d("Demo","Dinner average: " + mealTypeAverage[2]);
        Log.d("Demo","Snack average: " + mealTypeAverage[3]);

        Log.d("Demo","\n------------------------------------------------\nAverage spent per day: " + avgDay);

        Log.d("Demo","\n------------------------------------------------\nEnter a string to be encrypted");
        //Scanner in = new Scanner(System.in);

        //String toBeEncrypted = in.nextLine();
        String toBeEncrypted = "ThisStringWillBeEncryptedAndDecryptedBelow";

        PasswordEncryption encryp = new PasswordEncryption();
        PasswordDecryption decryp = new PasswordDecryption();

        String encrypted = encryp.encryptionAES(toBeEncrypted);

        Log.d("Demo","Your encrypted string is: " + encrypted);

        Log.d("Demo","Your decrypted string is: " + decryp.decryptAES(encrypted));

        */
    }

}
