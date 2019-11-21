package com.example.budgetapprebuild.ui.graphs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.budgetapprebuild.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Arrays;
import java.util.Random;

public class GraphsFragment extends Fragment {

    private GraphsViewModel graphsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        graphsViewModel = ViewModelProviders.of(this).get(GraphsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_graphs, container, false);



        //line graph code for average
        graphsViewModel.averageLineGraph = root.findViewById(R.id.graph_average);
        graphsViewModel.setAverageGraph();

        //line graph for money left
        graphsViewModel.moneyLeftLineGraph = root.findViewById(R.id.graph_fundsRemaining);
        graphsViewModel.setMoneyLeftGraph();

        //bar graph
        graphsViewModel.mealTimeSpending = root.findViewById(R.id.graph_mealTimeSpending);
        graphsViewModel.setBarGraph();

        return root;
    }
}