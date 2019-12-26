package com.example.budgetapprebuild.data;

import com.example.budgetapprebuild.SNHULogOn;
import com.example.budgetapprebuild.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            SNHULogOn a = new SNHULogOn();
            a.email = username;
            a.password = password;
            a.execute("Yes"); //attempts to log on
            while (a.waiting){
                Thread.sleep(250); //sleeps to avoid returning true/false too soon
            }
            if (a.successfulLogOn == false){ //if unsuccessful log in, throw exception
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
