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
import java.text.SimpleDateFormat;
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
    private double minx;
    private double maxx;
    private double maxy;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        graphsViewModel = ViewModelProviders.of(this).get(GraphsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_graphs, container, false);

        //Prediction.predict.getSpentPerDay();
        //Prediction.predict.getEstAmountLeft();
        Prediction.predict.predictionSettings("October 20, 2019", "December 20, 2019", 5, 600, 1000, 1000, 1600, 1600, 2000);
        Prediction.predict.calcMealTypeAverage();
        Prediction.predict.calcMonthAverage();
        Prediction.predict.calcSpentPerDay();
        Prediction.predict.calcEstAmountLeft();

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
        int init = setMaxAxis(d);
        DataPoint[] values = new DataPoint[init];
        int index = 0;
        for(int i=0;i<d[0].length;i++){
            if (d[0][i] == 0){
                continue;
            }
            DataPoint v = new DataPoint(d[0][i], d[1][i]);
            values[index++] = v;
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
        for (int i = 1; i < p.length; i++){
            if ((int)(p[i]) == 0.0){
                continue;
            }
            dp[0][i] = (i+1);
            dp[1][i] = ((int)(p[i]));
        }
        return dp;
    }

    private int[][] setMyBarArray(double[] p){
        int[][] dp = new int[2][p.length];
        for (int i = 1; i < p.length; i++){
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

        pointsForAverage = new LineGraphSeries<>(setDataPoints(setMyArray(Prediction.predict.getMonthAverage())));
        //pointsForAverage = new LineGraphSeries<>(setDataPoints(setRandomArrayPoints(12, 10)));

        averageLineGraph.setTitle("Total Spending per Month");
        averageLineGraph.setTitleTextSize(50);

        averageLineGraph.getGridLabelRenderer().setHorizontalAxisTitle("Month");
        averageLineGraph.getGridLabelRenderer().setVerticalAxisTitle("Average Money Spent");

        //SimpleDateFormat sdf = new SimpleDateFormat("MMM");
        //averageLineGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), sdf));

        averageLineGraph.getViewport().setMinX(minx);
        averageLineGraph.getViewport().setMaxX(maxx);
        averageLineGraph.getViewport().setMaxY(maxy);
        averageLineGraph.getViewport().setXAxisBoundsManual(true);
        averageLineGraph.getViewport().setYAxisBoundsManual(true);

        averageLineGraph.addSeries(pointsForAverage);
    }

    protected void setMoneyLeftGraph(){

        pointsFundsRemaining = new LineGraphSeries<>(setDataPoints(convertListToArray(Prediction.predict.spentGraph())));
        //pointsFundsRemaining = new LineGraphSeries<>(setDataPoints(setRandomArrayPoints(12, 1000)));
        moneyLeftLineGraph.setTitle("Funds Remaining");
        moneyLeftLineGraph.setTitleTextSize(50);

        moneyLeftLineGraph.getGridLabelRenderer().setHorizontalAxisTitle("Number of Purchases");
        moneyLeftLineGraph.getGridLabelRenderer().setVerticalAxisTitle("Total Money");

        //pointsForAverage.setDataPointsRadius(10);
        moneyLeftLineGraph.getViewport().setMaxX(maxx);
        moneyLeftLineGraph.getViewport().setMaxY(maxy);
        moneyLeftLineGraph.getViewport().setXAxisBoundsManual(true);
        moneyLeftLineGraph.getViewport().setYAxisBoundsManual(true);

        moneyLeftLineGraph.addSeries(pointsFundsRemaining);
    }

    protected void setBarGraph(){

        barGraphPoints = new BarGraphSeries<>(setDataPoints(setMyBarArray(Prediction.predict.getMealTypeAverage())));
        //barGraphPoints = new BarGraphSeries<>(setDataPoints(setRandomArrayPoints(12, 20)));
        mealTimeSpending.setTitle("Spending Per Meal");
        mealTimeSpending.setTitleTextSize(50);

        mealTimeSpending.getGridLabelRenderer().setHorizontalAxisTitle("Meals");
        mealTimeSpending.getGridLabelRenderer().setVerticalAxisTitle("Average Money Spent");

        mealTimeSpending.getViewport().setMaxX(5);
        mealTimeSpending.getViewport().setMaxY(20);
        mealTimeSpending.getViewport().setXAxisBoundsManual(true);
        mealTimeSpending.getViewport().setYAxisBoundsManual(true);

        mealTimeSpending.addSeries(barGraphPoints);

        barGraphPoints.setSpacing(20);
        //barGraphPoints.setDrawValuesOnTop(true);
        //barGraphPoints.setValuesOnTopColor(Color.GREEN);
    }

    private int setMaxAxis(int [][] d){
        int n = 0;
        maxy = d[1][0];
        for (int i = 0; i < d[0].length; i++){
            if (d[0][i] != 0) {
                if (n == 0){
                    minx = i;
                }
                n++;
            }
            if (maxy < d[1][i]){
                maxy = d[1][i];
            }
        }
        maxx = minx + n;
        maxx += maxx * 0.1;
        maxy += maxy * 0.1;

        return n;
    }

}