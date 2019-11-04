package com.example.budgetapprebuild.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.budgetapprebuild.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    LinearLayout specificMealTime;
    Switch customMealTime;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //objects
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        customMealTime = root.findViewById(R.id.switch_customMealTime);
        specificMealTime = root.findViewById(R.id.layout_mealTimes);

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
                System.out.println("Visibility: " + specificMealTime.getVisibility());
            }
        } );

        //logout

        //LinearLayout specificMealTime = (LinearLayout) root.findViewById(R.id.layout_mealTimes);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}