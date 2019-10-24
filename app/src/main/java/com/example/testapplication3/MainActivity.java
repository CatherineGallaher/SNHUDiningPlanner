package com.example.testapplication3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.net.ConnectException;

public class MainActivity extends AppCompatActivity {

    TextView textBox;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        textBox = (TextView) findViewById(R.id.textBox);
        button = (Button) findViewById(R.id.Connect);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ConnectToSql con = new ConnectToSql();
                    textBox.setText(con.execute("f").get());
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
                //System.out.println("Hello World, here we go again");
                //SNHULogOn a = new SNHULogOn();
                //a.logOn();
                //SQLConnect con = new SQLConnect();
                //con.connect();
                /*String encryptThis = "Hello World";
                PasswordEncryption encryp = new PasswordEncryption();
                String encrypted = encryp.encryptionAES(encryptThis);
                PasswordDecryption decryp = new PasswordDecryption();
                String decrypted = decryp.decryptAES(encrypted);
                System.out.println(encrypted);
                System.out.println(decrypted);*/

                //ReadWriteSQL test = new ReadWriteSQL();
                //test.createConnection();

                System.out.println("This is a test for the umpteenth time");
            }
        }

        );
    }

}
