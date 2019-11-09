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
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Arrays;
import java.util.Random;

public class GraphsFragment extends Fragment {

    private GraphsViewModel graphsViewModel;
    private LineGraphSeries<DataPoint> pointsForAverage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        graphsViewModel =
                ViewModelProviders.of(this).get(GraphsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_graphs, container, false);

        //line graph code for average
        Random rand = new Random();
        int[][] dataPoints = new int[2][20];
        for (int k = 0; k < 20; k++){
            dataPoints[0][k] = (rand.nextInt(10));
            dataPoints[1][k] = (rand.nextInt(10));
        }
        Arrays.sort(dataPoints[0]);
        setDataPoints(dataPoints);

        //pointsForAverage.setDataPointsRadius(10);
        GraphView averageLineGraph = root.findViewById(R.id.graph_average);
        averageLineGraph.getViewport().setMaxX(10);
        averageLineGraph.getViewport().setMaxY(10);
        averageLineGraph.getViewport().setXAxisBoundsManual(true);
        averageLineGraph.getViewport().setYAxisBoundsManual(true);

        averageLineGraph.addSeries(pointsForAverage);

        return root;
    }

    private void setDataPoints(int[][] dataPoints){
        pointsForAverage = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(dataPoints[0][0], dataPoints[1][0]),
                new DataPoint(dataPoints[0][1], dataPoints[1][1]),
                new DataPoint(dataPoints[0][2], dataPoints[1][2]),
                new DataPoint(dataPoints[0][3], dataPoints[1][3]),
                new DataPoint(dataPoints[0][4], dataPoints[1][4]),
                new DataPoint(dataPoints[0][5], dataPoints[1][5]),
                new DataPoint(dataPoints[0][6], dataPoints[1][6]),
                new DataPoint(dataPoints[0][7], dataPoints[1][7]),
                new DataPoint(dataPoints[0][8], dataPoints[1][8]),
                new DataPoint(dataPoints[0][9], dataPoints[1][9]),
                new DataPoint(dataPoints[0][10], dataPoints[1][10]),
                new DataPoint(dataPoints[0][11], dataPoints[1][11]),
                new DataPoint(dataPoints[0][12], dataPoints[1][12]),
                new DataPoint(dataPoints[0][13], dataPoints[1][13]),
                new DataPoint(dataPoints[0][14], dataPoints[1][14]),
                new DataPoint(dataPoints[0][15], dataPoints[1][15]),
                new DataPoint(dataPoints[0][16], dataPoints[1][16]),
                new DataPoint(dataPoints[0][17], dataPoints[1][17]),
                new DataPoint(dataPoints[0][18], dataPoints[1][18]),
                new DataPoint(dataPoints[0][19], dataPoints[1][19])
        });
        /*
        pointsForAverage = new LineGraphSeries<>(new DataPoint[20]);
        for (int i = 0; i < dataPoints.length; i++) {
            pointsForAverage.appendData(new DataPoint(dataPoints[0][i], dataPoints[1][i]), true, 20);
        }*/
    }
}