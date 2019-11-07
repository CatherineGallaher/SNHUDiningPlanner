package com.example.budgetapprebuild.ui.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;


import com.example.budgetapprebuild.SQLConnect;

public class DataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DataViewModel() {
        mText = new MutableLiveData<>();
        SQLConnect con = new SQLConnect();

        mText.setValue(con.connect());
    }

    public LiveData<String> getText() {
        return mText;
    }
}