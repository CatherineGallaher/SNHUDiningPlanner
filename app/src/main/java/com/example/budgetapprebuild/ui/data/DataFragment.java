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

import com.example.budgetapprebuild.Prediction;
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

        info = new String[100][2];
        //SNHULogOn s = new SNHULogOn();
        //info = s.getInfo();
        //Prediction.predict.calcDaysLeft(); error
        daysUntilEnd.setText("Days until end of semester: " + Prediction.predict.getDaysLeft());




        setPredictionText("30", "2", "less than", "You might want to change some habits.");
        randomlyGenerate();

        for(int k = 0; k < Prediction.predict.info.size(); k++) {
            if (info[k][0] == null){
                break;
            }
            inputData(Prediction.predict.info.get(k).get(0), Prediction.predict.info.get(k).get(2));
            //inputData(info[k][0], info[k][1]);
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