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
import java.text.DecimalFormat;

import com.example.budgetapprebuild.R;
import com.example.budgetapprebuild.SNHULogOn;


import java.util.ArrayList;
import java.util.Random;

public class DataFragment extends Fragment {

    private DataViewModel dataViewModel;
    private TextView dataBox;
    private TextView daysUntilEnd;
    private TextView predictionText;
    String[][] info;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //pre-generated code below
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_data, container, false);
        final TextView textView = root.findViewById(R.id.text_funds_remaining);
        dataBox = root.findViewById(R.id.text_dataBox);
        daysUntilEnd = root.findViewById(R.id.text_daysUntilEnd);
        predictionText = root.findViewById(R.id.text_information);

        //Catherine Elizabeth Gallaher here is where you need to be
        //If you can change/check if my array size is big enough

        info = new String[100][2];
        //SNHULogOn s = new SNHULogOn();
        //info = s.getInfo();
        setPredictionText("30", "3", "less than", "You might want to change some habits.");
        randomlyGenerate();

        for(int k = 0; k < info.length; k++) {
            if (info[k][0] == null){
                break;
            }
            inputData(info[k][0], info[k][1]);
        }

        /*dataViewModel.getText().observe(this, new Observer<String>() {
            @Override public void onChanged(@Nullable String s) { textView.setText(s); }
        });*/

        return root;
    }

    private void randomlyGenerate(){
        Random rand = new Random();
        DecimalFormat df = new DecimalFormat("#.##");
        for (int k = 0; k < 20; k++){
            info[k][0] = ((rand.nextInt(12)+1) + "/" + (rand.nextInt(30)+1) + "/2019");
            info[k][1] = ("$" + df.format((rand.nextInt(12) + rand.nextDouble())));
        }
    }

    private void inputData(String date, String purchase){
        dataBox.setText((dataBox.getText() + (date) + "\t\t\t" + (purchase) + "\n"));
    }


    private void setPredictionText(String lastDays, String daysNeeded, String morl, String reinforcement){
        predictionText.setText("If you continue with your current rate of expenditure,your funds will last last " + lastDays +
                               ". This is " + daysNeeded + " days " + morl + " than you need. " + reinforcement);
    }

}