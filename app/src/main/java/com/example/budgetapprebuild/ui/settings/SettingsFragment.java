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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //objects
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        if (false == settingsViewModel.objectCreated) {
            settingsViewModel = new SettingsViewModel(root);
        }

        return root;
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