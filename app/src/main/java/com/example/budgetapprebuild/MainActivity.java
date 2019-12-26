package com.example.budgetapprebuild;
import java.util.concurrent.ExecutionException;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        SQLConnect con = new SQLConnect();

        try {
            Prediction.predict.setInfo(con.execute("getTableInfo", "ti_catherine_gallaher3").get()); //Get information from database
            // TODO: get table name based on user email
            con.cancel(true);
            //SQLConnect con2 = new SQLConnect();
            //System.out.println(con2.execute("inputVal", "updateusers", "catherine_gallaher3", "asdfasdfasdf", "$372.64", SNHULogOn.dataScrape.dateLastAccessed, "3").get()); //unsuccessful attempt to get data from users table
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
