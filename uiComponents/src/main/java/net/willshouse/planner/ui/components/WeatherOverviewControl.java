package net.willshouse.planner.ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.willshouse.planner.models.Weather;

import java.io.IOException;

/**
 * Created by whartsell on 1/23/15.
 */
public class WeatherOverviewControl extends AnchorPane {

    @FXML
    private TextField temperatureField;

    @FXML
    private TextField headWindField;

    @FXML
    private TextField crossWindField;

    @FXML
    private TextField pressureAltitudeField;

    private Weather weather;

    public WeatherOverviewControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/WeatherOverview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setWeather(Weather weather) {
        this.weather = weather;
        temperatureField.setText(Integer.toString(weather.getTemperature()));
        headWindField.setText(Integer.toString(weather.getHeadWind()));
        crossWindField.setText(Integer.toString(weather.getCrossWind()));
        pressureAltitudeField.setText(Integer.toString(weather.getCrossWind()));
    }

    @FXML
    private void initialize() {
        temperatureField.textProperty().addListener(
                (obs, oldval, newval) -> weather.setTemperature(Integer.parseInt(newval))
        );

        headWindField.textProperty().addListener(
                (obs, oldval, newval) -> weather.setHeadWind(Integer.parseInt(newval))
        );

        crossWindField.textProperty().addListener(
                (obs, oldval, newval) -> weather.setCrossWind(Integer.parseInt(newval))
        );

        pressureAltitudeField.textProperty().addListener(
                (obs, oldval, newval) -> weather.setPressureAltitude(Integer.parseInt(newval))
        );
    }

}
