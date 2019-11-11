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
import com.google.api.client.util.Data;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Arrays;
import java.util.Random;

public class GraphsFragment extends Fragment {

    private GraphsViewModel graphsViewModel;
    private LineGraphSeries<DataPoint> pointsForAverage;
    private BarGraphSeries<DataPoint> barGraphPoints;
    int[][] dataPoints;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        graphsViewModel =
                ViewModelProviders.of(this).get(GraphsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_graphs, container, false);

        //line graph code for average
        int dataPointsSize = 20;
        Random rand = new Random();
        dataPoints = new int[2][dataPointsSize];
        for (int k = 0; k < dataPointsSize; k++){
            dataPoints[0][k] = (rand.nextInt(dataPointsSize));
            dataPoints[1][k] = (rand.nextInt(dataPointsSize));
        }
        Arrays.sort(dataPoints[0]);
        pointsForAverage = new LineGraphSeries<>(setDataPoints());

        //pointsForAverage.setDataPointsRadius(10);
        GraphView averageLineGraph = root.findViewById(R.id.graph_average);
        averageLineGraph.getViewport().setMaxX(10);
        averageLineGraph.getViewport().setMaxY(10);
        averageLineGraph.getViewport().setXAxisBoundsManual(true);
        averageLineGraph.getViewport().setYAxisBoundsManual(true);

        averageLineGraph.addSeries(pointsForAverage);


        //bar graph
        barGraphPoints = new BarGraphSeries<>(setDataPoints());

        GraphView mealTimeSpending = root.findViewById(R.id.graph_mealTimeSpending);
        mealTimeSpending.getViewport().setMaxX(10);
        mealTimeSpending.getViewport().setMaxY(10);
        mealTimeSpending.getViewport().setXAxisBoundsManual(true);
        mealTimeSpending.getViewport().setYAxisBoundsManual(true);

        mealTimeSpending.addSeries(barGraphPoints);
        return root;
    }

    private DataPoint[] setDataPoints(){
        int n=dataPoints.length;
        DataPoint[] values = new DataPoint[n];
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(dataPoints[0][i], dataPoints[1][i]);
            values[i] = v;
        }
        return values;
    }

    /*private DataPoint[] setGraphPoints(){
        int n=dataPoints.length;    //to find out the no. of data-points
        Data[] values = new Data[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(dataPoints[0][i], dataPoints[1][i]);
            values[i] = v;
        }
        return values;
    }*/

}