package com.example.budgetapprebuild.data;

import com.example.budgetapprebuild.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            //Add snhulogin here to test for real account
            if (username.equals("dbryant@retrieve.com")){
                throw new NullPointerException("Test");
            }
            LoggedInUser fakeUser = new LoggedInUser(java.util.UUID.randomUUID().toString(), username);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
