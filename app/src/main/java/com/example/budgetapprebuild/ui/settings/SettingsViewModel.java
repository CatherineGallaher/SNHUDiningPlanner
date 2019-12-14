package com.example.budgetapprebuild.ui.settings;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.example.budgetapprebuild.R;
import com.example.budgetapprebuild.data.model.LoggedInUser;

import static java.lang.Character.toUpperCase;

public class SettingsViewModel extends ViewModel {


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
    private TextView name;
    private TextView email;
    public static String username;
    private TextView[] textboxArray = new TextView[6];


    public SettingsViewModel() {

    }

    public SettingsViewModel(View root) {
        name = root.findViewById(R.id.text_name);
        email = root.findViewById(R.id.text_email);
        setName();
        setEmail();
        TimeMenuControl(root);
    }

    private void setName(){
        String firstName = username.substring(0,username.indexOf('.'));
        String lastName = username.substring(username.indexOf('.')+1, username.indexOf('@'));
        firstName = firstName.replace(firstName.charAt(0), toUpperCase(firstName.charAt(0)));
        lastName = lastName.replace(lastName.charAt(0), toUpperCase(lastName.charAt(0)));
        name.setText("Name: " + firstName + " " + lastName);
    }

    private void setEmail(){
        email.setText("Email: " + username);
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


    public static void setUsername(String u){
        username = u;
    }

}