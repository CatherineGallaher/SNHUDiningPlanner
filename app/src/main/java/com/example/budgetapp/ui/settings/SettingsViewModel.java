package com.example.budgetapp.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetapp.R;
import com.example.budgetapp.data.model.LoggedInUser;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private String userName;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is where our settings will be");
        //mText.setValue("Username: " + R.string.login_userName);
    }

    public LiveData<String> getText() {
        return mText;
    }
}