package com.example.budgetapprebuild.ui.graphs;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetapprebuild.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Arrays;
import java.util.Random;

public class GraphsViewModel extends ViewModel {

    protected LineGraphSeries<DataPoint> pointsForAverage;
    protected BarGraphSeries<DataPoint> barGraphPoints;
    int[][] dataPoints;

    protected GraphView averageLineGraph;
    protected GraphView moneyLeftLineGraph;
    protected GraphView mealTimeSpending;

    public GraphsViewModel() {

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

    protected void setAverageGraph(){
        int dataPointsSize = 20;
        Random rand = new Random();
        dataPoints = new int[2][dataPointsSize];
        for (int k = 0; k < dataPointsSize; k++){
            dataPoints[0][k] = (k);//rand.nextInt(dataPointsSize));
            dataPoints[1][k] = (k);//rand.nextInt(dataPointsSize));
        }
        Arrays.sort(dataPoints[0]);
        pointsForAverage = new LineGraphSeries<>(setDataPoints());

        //pointsForAverage.setDataPointsRadius(10);
        averageLineGraph.getViewport().setMaxX(10);
        averageLineGraph.getViewport().setMaxY(10);
        averageLineGraph.getViewport().setXAxisBoundsManual(true);
        averageLineGraph.getViewport().setYAxisBoundsManual(true);

        averageLineGraph.addSeries(pointsForAverage);
    }

    protected void setMoneyLeftGraph(){
        int dataPointsSize = 20;
        Random rand = new Random();
        dataPoints = new int[2][dataPointsSize];
        for (int k = 0; k < dataPointsSize; k++){
            dataPoints[0][k] = (rand.nextInt(dataPointsSize));
            dataPoints[1][k] = (rand.nextInt(dataPointsSize));
        }
        Arrays.sort(dataPoints[0]);
        /*pointsForAverage = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
*/
        pointsForAverage = new LineGraphSeries<>(setDataPoints());
        //pointsForAverage.setDataPointsRadius(10);
        moneyLeftLineGraph.getViewport().setMaxX(10);
        moneyLeftLineGraph.getViewport().setMaxY(10);
        moneyLeftLineGraph.getViewport().setXAxisBoundsManual(true);
        moneyLeftLineGraph.getViewport().setYAxisBoundsManual(true);

        moneyLeftLineGraph.addSeries(pointsForAverage);
    }

    protected void setBarGraph(){
        barGraphPoints = new BarGraphSeries<>(setDataPoints());

        mealTimeSpending.getViewport().setMaxX(10);
        mealTimeSpending.getViewport().setMaxY(10);
        mealTimeSpending.getViewport().setXAxisBoundsManual(true);
        mealTimeSpending.getViewport().setYAxisBoundsManual(true);

        mealTimeSpending.addSeries(barGraphPoints);
    }


}