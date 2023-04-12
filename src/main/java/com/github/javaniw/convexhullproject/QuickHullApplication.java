package com.github.javaniw.convexhullproject;

import com.github.javaniw.convexhullproject.ConvexHull.ConvexHull;
import com.github.javaniw.convexhullproject.HelperClasses.GeneratePoints;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class QuickHullApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

//        creates a borderPane which will serve as main UI component
        BorderPane mainPane = organizeScene();

//        creates variables of the different UI elements
        LineChart lineChart = (LineChart)mainPane.getCenter();
        ChoiceBox choiceBox = (ChoiceBox) mainPane.getTop();
        HBox hBox = (HBox)mainPane.getBottom();
        TextField textField = mainPane.getBottom() instanceof HBox ? (TextField)(((HBox) mainPane.getBottom()).getChildren().get(1)) : null;
        Button startButton = mainPane.getBottom() instanceof HBox ? (Button)(((HBox) mainPane.getBottom()).getChildren().get(2)) : null;
        Button resetButton = mainPane.getBottom() instanceof HBox ? (Button)(((HBox) mainPane.getBottom()).getChildren().get(3)) : null;

//        EVENT HANDLERS

//        ensures the user only types in numeric characters
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
//                if the value typed in is not a digit, replace said value with an empty string ""
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

//        Event Handler for when the "Start" button is pressed
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                if the textField is empty, return
                if (textField.getText() == "")
                    return;

//                retrieves the amount of points to generate from the GUI
                Integer numOfPoints = Integer.parseInt(textField.getText());
//                randomly generate that number of points
                List<Double[]> setOfPoints = GeneratePoints.generate(numOfPoints, -95, 95);
//                plot the points of the set
                Controller.plotPoints(lineChart, setOfPoints);
//                changes the button to the Plot button which will plot the convex hull
                Controller.changeButton(mainPane, setOfPoints);
//                disable the start button which makes the user have to press the "Plot" or "Reset"
//                button to plot another graph
                startButton.setDisable(true);
            }
        });

//        Event Handler for when the "Reset" button is pressed
        resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                clears all the Series from the LineChart object i.e., erases previous plots
                lineChart.getData().clear();
//                clears the textField also so users have to re-enter the number of points to plot
                textField.setText("");
//                if the startButton is currently replaced by the "Plot" button, replace the "Plot" button
//                with the "Start" button
                if ((Button)hBox.getChildren().get(2) != startButton) {
                    hBox.getChildren().set(2, startButton);
                }
//                enable the "Start" button
                startButton.setDisable(false);
            }
        });

//        creates a stage using mainPane as the root
        Scene scene = new Scene(mainPane, 600, 600);
//        sets title of the stage
        stage.setTitle("Convex Hull Plotter");
//        sets the scene of the stage
        stage.setScene(scene);
//        effectively makes the stage non-resizeable
        stage.setMinWidth(600);
        stage.setMinHeight(600);
        stage.setMaxHeight(600);
        stage.setMaxWidth(600);
//        shows the scene i.e., makes the scene visible
        stage.show();
    }

    public static BorderPane organizeScene() {
        BorderPane border = new BorderPane();
//        border.setMinSize(600, 600);
        ChoiceBox<ConvexHull> choiceBox = createChoiceBox();
        HBox hbox = createHBox();
        LineChart lineChart = createLineChart();
        BorderPane.setAlignment(choiceBox, Pos.BOTTOM_CENTER);
        border.setCenter(lineChart);
        border.setBottom(hbox);
        border.setTop(choiceBox);
        return border;
    }

    /**
     * Creates a JavaFx ChoiceBox object and sets values of it
     *
     * @return a JavaFx Choice object
     */
    public static ChoiceBox createChoiceBox() {
//        creates ChoiceBox in which the user will use to select which algorithm they want to use
        ChoiceBox<String> choiceBox= new ChoiceBox<>(FXCollections.observableArrayList(new String[]{"Brute Force", "QuickHull"}));
        choiceBox.setValue("Brute Force");
        return  choiceBox;
    }

    /**
     * Creates a JavaFx LineChart object and sets some styles of it
     *
     * @return a JavaFx LineChart object
     */
    public static LineChart createLineChart() {
//        CREATE LINE CHART
//        creating axis
        NumberAxis xAxis = new NumberAxis(-100, 100, 5);
        NumberAxis yAxis = new NumberAxis(-100, 100, 5);
//        creating chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
//        making line segments be sorted in the order they are passed
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
       return lineChart;
    }

    /**
     * Creates a JavaFx HBox object and sets some properties of it
     *
     * @return a JavaFx HBox object
     */
    public static HBox createHBox() {
//        creates a JavaFx Label object to display a prompt
        Label label = new Label("Enter the number of points to create:");
//       creates a JavaFx TextField object to hold number of points to create
        TextField textField = new TextField();
//        creates a JavaFx Button object which will act as "Start" Button
        Button startButton = new Button("Start");
//        creates a JavaFx Button object which will act as a "Reset" Button
        Button clearButton = new Button("Reset");

//        sets styles of the HBox
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setSpacing(20);
        HBox.setHgrow(label, Priority.ALWAYS);

//        customizes how the HBox appears
        label.setMaxWidth(Double.MAX_VALUE);
        hbox.setStyle("-fx-background-color: lightgreen");

//        sets styles of the Label, TextField, and two Buttons
        label.setPrefSize(150,20);
        textField.setPrefSize(100,20);
        startButton.setPrefSize(100,20);
        clearButton.setPrefSize(100, 20);
//        adds Label, TextField, and two Buttons to HBox
        hbox.getChildren().addAll(label, textField, startButton, clearButton);
        return hbox;
    }

    public static void main(String[] args) {
        launch();
    }
}