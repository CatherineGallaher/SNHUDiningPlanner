package com.example.budgetapprebuild.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetapprebuild.SQLConnect;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();

        SQLConnect con = new SQLConnect();
        try {
            String result = con.execute().get();
            System.out.println(result);
            Log.i("PHPConnect", "running php script");
            mText.setValue(result);
        }
        catch (Exception e)
        {
            String result = e.toString();
            System.out.println(result);
            Log.i("PHPConnect", result);
            mText.setValue(result);
        }

    }

    public LiveData<String> getText() {
        return mText;
    }
}