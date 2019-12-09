package com.example.budgetapprebuild.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.budgetapprebuild.MainActivity;
import com.example.budgetapprebuild.R;
import com.example.budgetapprebuild.ui.login.LoginActivity;

import java.time.chrono.MinguoChronology;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private Button saveButton;
    private LinearLayout specificMealTime;
    private Switch customMealTime;
    private TextView daysOffCampus;
    private TextView breakStart;
    private TextView breakEnd;
    private TextView lunchStart;
    private TextView lunchEnd;
    private TextView dinnerStart;
    private TextView dinnerEnd;
    private TextView[] textboxArray = new TextView[6];

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //objects
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        TimeMenuControl(root);

        return root;
    }

    private void TimeMenuControl(View root){

        saveButton = root.findViewById(R.id.button_save);
        customMealTime = root.findViewById(R.id.switch_customMealTime);
        specificMealTime = root.findViewById(R.id.layout_mealTimes);
        daysOffCampus = root.findViewById(R.id.text_daysOffCampus);

        breakStart = root.findViewById(R.id.time_breakstart);
        breakEnd = root.findViewById(R.id.time_breakend);

        lunchStart = root.findViewById(R.id.time_lunchstart);
        lunchEnd = root.findViewById(R.id.time_lunchend);

        dinnerStart = root.findViewById(R.id.time_dinnerstart);
        dinnerEnd = root.findViewById(R.id.time_dinnerend);
        textboxArray[0] = breakStart;
        textboxArray[1] = breakEnd;
        textboxArray[2] = lunchStart;
        textboxArray[3] = lunchEnd;
        textboxArray[4] = dinnerStart;
        textboxArray[5] = dinnerEnd;


        //switch code
        customMealTime.setChecked(false);
        specificMealTime.setVisibility(root.INVISIBLE);
        customMealTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (specificMealTime.getVisibility() == view.VISIBLE){
                    specificMealTime.setVisibility(view.INVISIBLE);
                }
                else{
                    specificMealTime.setVisibility(view.VISIBLE);
                }
            }
        } );

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (specificMealTime.getVisibility() == root.VISIBLE){
                    //validate stuff
                    if (ValidateMealTimes()){
                        System.out.println("Save unsuccessful");
                    }
                    else {
                        System.out.println("Successful Save");
                    }
                    //add details to database
                }
            }
        } );

        breakStart.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


    }


    private boolean ValidateMealTimes(){
        //start has to be earlier than end
        try {
            for (int i = 0; i < 6; i++) {
                String s = textboxArray[i].getText().toString();
                if (s.length() == 0) {
                    continue;
                }

                if (s.charAt(2) != ':' || s.length() != 5) {
                    return true;
                }
                int hr = Integer.parseInt(s.substring(0, s.indexOf(':')));
                if (hr < 1 && hr > 24) {
                    return true;
                } else if (hr < 10 && (Integer.parseInt(s.substring(0, 1)) != 0)) {
                    return true;
                }
                /*int min = Integer.parseInt(s.substring(s.indexOf(':'), 2));
                if (s.length() >= 5 && min < 0 && min >= 60) {
                    return true;
                }*/
            }
        }
        catch (Exception e){
            return true;
        }
        return false;
    }

   /*         if (s.length() >= 3) {
        if (s.charAt(2) != ':') {
            s.insert(2, ":");
        }
        int hr = Integer.parseInt(s.toString().substring(0, s.toString().indexOf(':')));
        if (hr < 1 && hr > 24) {
            s.replace(0, 2, "24");
        } else if (hr < 10 && (Integer.parseInt(s.toString().substring(0, 1)) != 0)) {
            s.replace(0, 2, "0" + hr);
        }
        int min = Integer.parseInt(s.toString().substring(s.toString().indexOf(':'), s.length()));
        if (s.length() >= 5 && min < 0 && min >= 60) {
            s.replace(s.toString().indexOf(':'), s.length(), "00");
        }
    }*/
}