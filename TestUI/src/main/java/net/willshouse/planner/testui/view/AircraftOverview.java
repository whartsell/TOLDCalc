package net.willshouse.planner.testui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import net.willshouse.planner.testui.MainApp;
import net.willshouse.planner.testui.model.Aircraft;

/**
 * Created by whartsell on 1/20/15.
 */
public class AircraftOverview extends AnchorPane {

    private AnchorPane anchorPane;
    private TitledPane titledPane;
    private GridPane gridPane;

    private TextField grossWeightField;
    private TextField dragIndexField;
    private ChoiceBox<String> speedBrakesChoiceBox;
    private ChoiceBox<Integer> flapsChoiceBox;
    private ChoiceBox<String> thrustChoiceBox;
    private ChoiceBox<String> antiSkidChoiceBox;
    private Button testButton;


    private MainApp mainApp;
    private Aircraft aircraft;

    public AircraftOverview() {
        titledPane = new TitledPane();
        gridPane = new GridPane();
        gridPane.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setPadding(new Insets(0, 0, 0, 0));
        grossWeightField = new TextField();
        dragIndexField = new TextField();
        speedBrakesChoiceBox = new ChoiceBox<String>();
        flapsChoiceBox = new ChoiceBox<Integer>();
        thrustChoiceBox = new ChoiceBox<String>();
        antiSkidChoiceBox = new ChoiceBox<String>();
        testButton = new Button("Test");
        testButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSet();
            }
        });
        gridPane.add(new Label("Gross Weight"), 0, 0);
        gridPane.add(grossWeightField, 1, 0);
        gridPane.add(new Label("Drag Index"), 0, 1);
        gridPane.add(dragIndexField, 1, 1);
        gridPane.add(new Label("Flaps Setting"), 0, 2);
        gridPane.add(flapsChoiceBox, 1, 2);
        gridPane.add(new Label("Thrust"), 0, 3);
        gridPane.add(thrustChoiceBox, 1, 3);
        gridPane.add(new Label("Anti Skid"), 0, 4);
        gridPane.add(antiSkidChoiceBox, 1, 4);
        gridPane.add(testButton, 0, 5);
        titledPane.setContent(gridPane);
        titledPane.setCollapsible(false);
        this.getChildren().add(titledPane);
        initialize();


    }

    private void initialize() {
        speedBrakesChoiceBox.getItems().addAll("Open", "Closed");
        flapsChoiceBox.getItems().addAll(0, 7);
        thrustChoiceBox.getItems().addAll("Max", "3% below PTFS");
        antiSkidChoiceBox.getItems().addAll("Off", "On");

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;


    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        grossWeightField.setText(Integer.toString(aircraft.getGrossWeight()));
        dragIndexField.setText(Double.toString(aircraft.getDragIndex()));

        speedBrakesChoiceBox.setValue("Open");
        flapsChoiceBox.setValue(7);
        thrustChoiceBox.setValue("Max");
        antiSkidChoiceBox.setValue("On");
        titledPane.setText(aircraft.getName() + " Details");

    }


    @FXML
    private void handleSet() {
        aircraft.setGrossWeight(Integer.valueOf(grossWeightField.getText()));
        System.out.println("gross Weight: " + aircraft.getGrossWeight());
        aircraft.setDragIndex(Double.valueOf(dragIndexField.getText()));
        System.out.println("Drag Index: " + aircraft.getDragIndex());
        aircraft.setFlapSetting(flapsChoiceBox.getValue());
        System.out.println("Flaps: " + aircraft.getFlapSetting());

    }


}
