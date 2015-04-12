package net.willshouse.planner.ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.willshouse.planner.models.Aircraft;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;

import java.io.IOException;

/**
 * Created by whartsell on 1/20/15.
 */
public class AircraftOverviewControl extends AnchorPane {
    public ValidationSupport validationSupport = new ValidationSupport();
    @FXML
    private TextField grossWeightField;
    @FXML
    private TextField dragIndexField;
    @FXML
    private ChoiceBox<String> speedBrakesChoiceBox;
    @FXML
    private ChoiceBox<Integer> flapsChoiceBox;
    @FXML
    private ChoiceBox<String> thrustChoiceBox;
    @FXML
    private ChoiceBox<String> antiSkidChoiceBox;
    private Aircraft aircraft;


    public AircraftOverviewControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AircraftOverview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


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

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        grossWeightField.setText(Integer.toString(aircraft.getGrossWeight()));
        dragIndexField.setText(Double.toString(aircraft.getDragIndex()));
        speedBrakesChoiceBox.setValue("Open");
        flapsChoiceBox.setValue(7);
        thrustChoiceBox.setValue("Max");
        antiSkidChoiceBox.setValue("On");

    }

    @FXML
    private void initialize() {

        grossWeightField.textProperty().addListener(
                (obs, oldval, newval) -> aircraft.setGrossWeight(Integer.parseInt(newval))
        );

        dragIndexField.textProperty().addListener(
                (obs, oldval, newval) -> aircraft.setDragIndex(Double.parseDouble(newval))
        );

        speedBrakesChoiceBox.valueProperty().addListener(
                (obs, oldval, newval) -> {
                    if (newval == "Open") aircraft.setSpeedBrakes(true);
                    else aircraft.setSpeedBrakes(false);
                }
        );

        flapsChoiceBox.valueProperty().addListener(
                (obs, oldval, newval) -> aircraft.setFlapSetting(newval)
        );

        thrustChoiceBox.valueProperty().addListener(
                (obs, oldval, newval) -> {
                    if (newval == "Max") aircraft.setMaxThrust(true);
                    else aircraft.setMaxThrust(false);
                }
        );


        speedBrakesChoiceBox.getItems().addAll("Open", "Closed");
        flapsChoiceBox.getItems().addAll(0, 7);
        thrustChoiceBox.getItems().addAll("Max", "3% below PTFS");
        antiSkidChoiceBox.getItems().addAll("Off", "On");

        validationSupport.registerValidator(grossWeightField, Validator.createPredicateValidator(Aircraft.validGrossWeight, "im a dummy"));
        validationSupport.registerValidator(dragIndexField, Validator.createPredicateValidator(Aircraft.validDragIndex, "drag index"));
        validationSupport.invalidProperty().addListener(
                (obs, oldval, newval) -> System.out.println("is invalid:" + newval)
        );
        validationSupport.setValidationDecorator(new StyleClassValidationDecoration());

    }


}
