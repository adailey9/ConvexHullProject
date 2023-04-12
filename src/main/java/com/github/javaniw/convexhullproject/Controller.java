package com.github.javaniw.convexhullproject;

import com.github.javaniw.convexhullproject.BruteForceAlgorithm.BruteForceConvexHull;
import com.github.javaniw.convexhullproject.HelperClasses.PointListConversion;
import com.github.javaniw.convexhullproject.QuickHullAlgorithm.Point;
import com.github.javaniw.convexhullproject.QuickHullAlgorithm.QuickHull;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class Controller {

    /**
     * Visually plots all points of the set onto the LineChart object
     *
     * @param chart a JavaFx chart object
     * @param points a list of points to plot onto the chart (x coordinate, y coordinate)
     */
    public static void plotPoints(LineChart chart, List<Double []> points) {
//        creates a new Series which will be plotted onto the line chart
        XYChart.Series series = new XYChart.Series();
//        creates chart key for the series
        series.setName("Points of set");
//        loops through all points in the points parameter and adds each point to the Series
        for (Double[] point : points) {
            series.getData().add(new XYChart.Data<Double, Double>(point[0], point[1]));
        }

//        adds the Series to the chart which makes it appear visible
        chart.getData().add(series);
//        sets a style of the series, this style effectively removes the lines segments connecting each
//        point in the series, so they appear as a scatter plot
        series.getNode().setStyle("-fx-stroke: transparent");
    }

    /**
     * Visually plots the points which make up the convex hull onto the LineChart object
     *
     * @param lineChart a JavaFx LineChart object
     * @param convexHullPoints a list of points which make up the convex hull
     */
    public static void plotConvexHull(LineChart lineChart, List<Double[]> convexHullPoints) {
//        creates a new Series which will be plotted onto the line chart
        XYChart.Series series = new XYChart.Series();
//        creates chart key for the series
        series.setName("Convex Hull");
//        loops through all points in the points parameter and adds each point to the Series
        for (Double[] point : convexHullPoints) {
            series.getData().add(new XYChart.Data<Double, Double>(point[0], point[1]));
        }
//        re-adds the first point of the received convexHullPoints in order to create the enclosed
//        shape of the convex hull
            series.getData().add(new XYChart.Data<Double, Double>(convexHullPoints.get(0)[0], convexHullPoints.get(0)[1]));
//        adds the Series to the chart which makes it appear visible
        lineChart.getData().add(series);
    }

    /**
     * Changes the "Start" button to the "Plot" button and adds the appropriate EventHandler to the
     * "Plot" button.
     *
     * @param mainPane a JavaFx BorderPane object
     * @param setOfPoints the set of points in which the convex hull will be derived from
     */
    public static void changeButton(BorderPane mainPane, List<Double[]> setOfPoints) {
//        creates variables of the main UI elements
        HBox hbox = (HBox)mainPane.getBottom();
        LineChart lineChart = (LineChart)mainPane.getCenter();
        ChoiceBox choiceBox = (ChoiceBox)mainPane.getTop();

//        creates a new Plot button
        Button plotButton = new Button("Plot");
        plotButton.setPrefSize(100,20);
//        retrieves the current Start button
        Button startButton = (Button) hbox.getChildren().get(2);

        plotButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                stores the value of the choiceBox in a variable
                String algorithmType = (String) choiceBox.getValue();
//                calculates the convex hull using the specified algorithm and stores result in variable
                List<Double[]> convexHull = generateConvexHull(algorithmType, setOfPoints);
//                plots the points of the convex hull
                Controller.plotConvexHull(lineChart, convexHull);
//                replaces the Plot button back with the Start button
                hbox.getChildren().set(2, startButton);
            }
        });
//        replaces the "Start" button with this new "Plot" button
        hbox.getChildren().set(2, plotButton);
    }

    /**
     * Uses the appropriate algorithm to determine and return the points which make up the convex hull
     *
     * @param algorithmType a string representing the type of algorithm to be used (Brute Force or QuickHull
     * @param setOfPoints a list of points (x coordinate, y coordinate)
     * @return
     */
    public static List<Double[]> generateConvexHull(String algorithmType, List<Double[]> setOfPoints) {
//        if algorithmType is "Brute Force," use the Brute Force algorithm to calculate the convex hull
        if (algorithmType == "Brute Force") {
            return BruteForceConvexHull.findConvexHull(setOfPoints);
        }
//        if algorithmType is "QuickHull," use the QuickHull algorithm to calculate the convex hull
        if (algorithmType == "QuickHull") {
            List<Point> convexHull = QuickHull.quickHull(PointListConversion.doubleListToPointList(setOfPoints));
            return PointListConversion.pointListToDoubleList(convexHull);
        }
        return null;
    }
}