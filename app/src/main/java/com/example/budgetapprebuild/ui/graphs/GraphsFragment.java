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

import com.example.budgetapprebuild.Prediction;
import com.example.budgetapprebuild.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GraphsFragment extends Fragment {

    private GraphsViewModel graphsViewModel;

    protected LineGraphSeries<DataPoint> pointsForAverage;
    protected LineGraphSeries<DataPoint> pointsFundsRemaining;
    protected BarGraphSeries<DataPoint> barGraphPoints;

    protected GraphView averageLineGraph;
    protected GraphView moneyLeftLineGraph;
    protected GraphView mealTimeSpending;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        graphsViewModel = ViewModelProviders.of(this).get(GraphsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_graphs, container, false);

        //Prediction.predict.getSpentPerDay();
        //Prediction.predict.getEstAmountLeft();
        Prediction.predict.calcMealTypeAverage();
        Prediction.predict.calcMonthAverage();
        Prediction.predict.calcSpentPerDay();
        Prediction.predict.calcEstAmountLeft();
        Prediction.predict.getMealTypeAverage();

        //line graph code for average
        averageLineGraph = root.findViewById(R.id.graph_average);
        setAverageGraph();

        //line graph for money left
        moneyLeftLineGraph = root.findViewById(R.id.graph_fundsRemaining);
        setMoneyLeftGraph();

        //bar graph
        mealTimeSpending = root.findViewById(R.id.graph_mealTimeSpending);
        setBarGraph();

        return root;
    }


    private DataPoint[] setDataPoints(int[][] d){
        int n=d[0].length;
        DataPoint[] values = new DataPoint[n];
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(d[0][i], d[1][i]);
            values[i] = v;
        }
        return values;
    }

    private int[][] setRandomArrayPoints(int x, int y){
        int[][] dataPoints = new int[2][x];
        Random rand = new Random();
        for (int k = 0; k < x; k++){
            dataPoints[0][k] = (rand.nextInt(x));
            dataPoints[1][k] = (rand.nextInt(y));
        }
        Arrays.sort(dataPoints[0]);
        return dataPoints;
    }

    private int[][] setMyArray(double[] p){
        int[][] dp = new int[2][p.length];
        for (int i = 0; i < p.length-1; i++){
            dp[0][i] = (i+1);
            dp[1][i] = ((int)(p[i]));
        }
        return dp;
    }

    private int[][] convertListToArray(List<Double> l){

        int[][] dp = new int[2][l.size()];

        for (int i = 0; i < l.size()-1; i++)
        {
            dp[0][i] = (i+1);
            dp[1][i] = (int) Math.round(l.get(i));
        }

        return dp;
    }

    protected void setAverageGraph(){

        //pointsForAverage = new LineGraphSeries<>(setDataPoints(setMyArray(Prediction.predict.getMonthAverage())));
        pointsForAverage = new LineGraphSeries<>(setDataPoints(setRandomArrayPoints(12, 10)));

        averageLineGraph.setTitle("Average Spending per Month");
        averageLineGraph.setTitleTextSize(50);

        //averageLineGraph.getGridLabelRenderer().setHorizontalAxisTitle("Month");
        //averageLineGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));

        averageLineGraph.getViewport().setMaxX(12);
        //averageLineGraph.getViewport().setX(10);
        averageLineGraph.getViewport().setMaxY(10);
        averageLineGraph.getViewport().setXAxisBoundsManual(true);
        averageLineGraph.getViewport().setYAxisBoundsManual(true);

        averageLineGraph.addSeries(pointsForAverage);
    }

    protected void setMoneyLeftGraph(){


        //pointsFundsRemaining = new LineGraphSeries<>(setDataPoints(convertListToArray(Prediction.predict.spentGraph())));
        pointsFundsRemaining = new LineGraphSeries<>(setDataPoints(setRandomArrayPoints(12, 1000)));
        moneyLeftLineGraph.setTitle("Funds Remaining");
        moneyLeftLineGraph.setTitleTextSize(50);

        //pointsForAverage.setDataPointsRadius(10);
        moneyLeftLineGraph.getViewport().setMaxX(12);
        moneyLeftLineGraph.getViewport().setMaxY(1000);
        moneyLeftLineGraph.getViewport().setXAxisBoundsManual(true);
        moneyLeftLineGraph.getViewport().setYAxisBoundsManual(true);

        moneyLeftLineGraph.addSeries(pointsFundsRemaining);
    }

    protected void setBarGraph(){

        //barGraphPoints = new BarGraphSeries<>(setDataPoints(setMyArray(Prediction.predict.getMealTypeAverage())));
        barGraphPoints = new BarGraphSeries<>(setDataPoints(setRandomArrayPoints(12, 20)));
        mealTimeSpending.setTitle("Spending Per Meal");
        mealTimeSpending.setTitleTextSize(50);

        mealTimeSpending.getViewport().setMaxX(12);
        mealTimeSpending.getViewport().setMaxY(20);
        mealTimeSpending.getViewport().setXAxisBoundsManual(true);
        mealTimeSpending.getViewport().setYAxisBoundsManual(true);

        mealTimeSpending.addSeries(barGraphPoints);

        barGraphPoints.setSpacing(20);
        //barGraphPoints.setDrawValuesOnTop(true);
        //barGraphPoints.setValuesOnTopColor(Color.GREEN);
    }

}