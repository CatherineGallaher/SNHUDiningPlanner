package com.example.budgetapprebuild.ui.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.GridLayout.LayoutParams;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.budgetapprebuild.PasswordEncryption;
import com.example.budgetapprebuild.R;
import com.example.budgetapprebuild.SNHULogOn;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends Fragment {

    private DataViewModel dataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //pre-generated code below
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_data, container, false);
        final TextView textView = root.findViewById(R.id.text_funds_remaining);
        final TextView dataBox = root.findViewById(R.id.text_dataBox);

        //Catherine Elizabeth Gallaher here is where you need to be
        //If you can change check if my array size is big enough

        String[][] info = new String[100][3];
        //SNHULogOn s = new SNHULogOn();
        //info = s.getInfo();
        for (int i = 0; i < 20; i++){
            info[i][0] = ("Date" + i);
            info[i][1] = ("Time" + i);
            info[i][2] = ("Purchase" + i);

        }

        for(int k = 0; k < info.length; k++)
        {
            if (info[k][0] == null){
                break;
            }
            dataBox.setText(((dataBox.getText() + (info[k][0]) + "\t\t\t" + (info[k][1])) + "\t\t\t" + (info[k][1])) + "\n");
        }

        System.out.println("OverHere");
       // boolean isLoggedIn = SNHULogOn.dataScrape.logOn("catherine.gallaher@snhu.edu", PasswordEncryption.encryptionAES("3Mog,3Or,3Mb44"));
        /*
        if(isLoggedIn)
        {
            List<ArrayList<String>> info = SNHULogOn.dataScrape.getInfo();
            //String[][] info = new String[100][2];
            //SNHULogOn s = new SNHULogOn();
            //info = s.getInfo();
            info[0][0] = ("Date1");
            info[0][1] = ("Purchase1");
            info[1][0] = ("Date2");
            info[1][1] = ("Purchase2");
            info[2][0] = ("Date3");
            info[2][1] = ("Purchase3");
            info[3][0] = ("Date4");
            info[3][1] = ("Purchase4");
            info[4][0] = ("Date5");
            info[4][1] = ("Purchase5");
            info[5][0] = ("Date6");
            info[5][1] = ("Purchase6");
            info[6][0] = ("Date7");
            info[6][1] = ("Purchase7");
            info[7][0] = ("Date8");
            info[7][1] = ("Purchase8");
            info[8][0] = ("Date9");
            info[8][1] = ("Purchase9");
/*
            for(int k = 0; k < info.size(); k++)
            {//System.out.print(info.get(k).get(j) + " ");
                dataBox.setText((dataBox.getText() + (info.get(k).get(0)) + "\t\t" + (info.get(k).get(1)) + "\t\t" + (info.get(k).get(2))) + "\n");
            }
        }*/

        System.out.println("Out");



        dataViewModel.getText().observe(this, new Observer<String>() {
            @Override public void onChanged(@Nullable String s) { textView.setText(s); }
        });

        return root;
    }
}