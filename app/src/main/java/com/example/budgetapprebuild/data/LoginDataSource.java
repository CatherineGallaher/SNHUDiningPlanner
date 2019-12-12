package com.example.budgetapprebuild.data;

import com.example.budgetapprebuild.Prediction;
import com.example.budgetapprebuild.SNHULogOn;
import com.example.budgetapprebuild.SQLConnect;
import com.example.budgetapprebuild.data.model.LoggedInUser;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            //Add snhulogin here to test for real account
            boolean slotest;
            SNHULogOn a = new SNHULogOn();
            a.email = username;
            a.password = password;
            a.execute("Yes");
            while (a.waiting){
                Thread.sleep(250);
            }
            if (a.successfulLogOn == false){
                throw new NullPointerException("Test");
            }

            LoggedInUser realUser = new LoggedInUser(java.util.UUID.randomUUID().toString(), username);
            return new Result.Success<>(realUser);
        } catch (NullPointerException e) {
            return new Result.Error(new IOException("Account does not exist under Penman Cash", e));
        }catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
