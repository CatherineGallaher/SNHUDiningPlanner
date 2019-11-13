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

import com.example.budgetapprebuild.R;

import java.util.ArrayList;

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

        String[][] info = new String[100][2];
        //SNHULogOn s = new SNHULogOn();
        //info = s.getInfo();
        for (int k = 0; k < 20; k++){
            info[k][0] = ("Date" + k);
            info[k][1] = ("Purchase" + k);
        }

        for(int k = 0; k < info.length; k++)
        {
            if (info[k][0] == null){
                break;
            }
            dataBox.setText((dataBox.getText() + (info[k][0]) + "\t\t" + (info[k][1])) + "\n");
        }



        dataViewModel.getText().observe(this, new Observer<String>() {
            @Override public void onChanged(@Nullable String s) { textView.setText(s); }
        });

        return root;
    }
}