package com.example.budgetapprebuild.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetapprebuild.SQLConnect;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        SQLConnect con = new SQLConnect();

        mText.setValue(con.connect());
    }

    public LiveData<String> getText() {
        return mText;
    }
}