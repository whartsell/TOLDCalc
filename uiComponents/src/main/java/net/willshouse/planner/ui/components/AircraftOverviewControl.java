package net.willshouse.planner.ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.willshouse.planner.models.Aircraft;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.IOException;

/**
 * Created by whartsell on 1/20/15.
 */
public class AircraftOverviewControl extends AnchorPane {
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
    private ValidationSupport validationSupport = new ValidationSupport();

    public AircraftOverviewControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AircraftOverview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        try {
            fxmlLoader.load();
        } catch (IOException e) {
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
        validationSupport.registerValidator(grossWeightField, Validator.createEmptyValidator("Gross Weight is Required"));

        ValidationSupport.setRequired(grossWeightField, true);
        speedBrakesChoiceBox.getItems().add("Open");
        speedBrakesChoiceBox.getItems().add("Closed");
        flapsChoiceBox.getItems().add(0);
        flapsChoiceBox.getItems().add(7);
        thrustChoiceBox.getItems().add("Max");
        thrustChoiceBox.getItems().add("3% below PTFS");
        antiSkidChoiceBox.getItems().add("Off");
        antiSkidChoiceBox.getItems().add("On");

    }


}
