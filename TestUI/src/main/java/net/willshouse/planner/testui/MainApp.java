package net.willshouse.planner.testui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.willshouse.planner.models.Aircraft;
import net.willshouse.planner.models.Runway;
import net.willshouse.planner.models.TakeoffData;
import net.willshouse.planner.models.Weather;
import net.willshouse.planner.ui.components.AircraftOverviewControl;
import net.willshouse.planner.ui.components.RunwayOverviewControl;
import net.willshouse.planner.ui.components.WeatherOverviewControl;

import java.io.IOException;

/**
 * Created by whartsell on 1/20/2015.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    @FXML
    private VBox vBox;
    private Aircraft aircraft;
    private Weather weather;
    private Runway runway;
    private AircraftOverviewControl aircraftOverview;
    private WeatherOverviewControl weatherOverviewControl;
    private RunwayOverviewControl runwayOverviewControl;
    private TakeoffData takeoffData;

    public MainApp() {
        aircraft = new Aircraft("A-10C");
        weather = new Weather();
        runway = new Runway();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TOLD Calc");

        initRootLayout();
        showAircraftOverview();


//
//        rootLayout.setCenter(aircraftOverview);
//        aircraftOverview.setAircraft(aircraft);
//        runwayOverviewControl = new RunwayOverviewControl();
//        rootLayout.setCenter(runwayOverviewControl);
//        runwayOverviewControl.setRunway(runway);

        //showAircraftOverview();

    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/net/willshouse/planner/view/RootLayout.fxml"));
            loader.setController(this);
            rootLayout = (BorderPane) loader.load();
            aircraftOverview = new AircraftOverviewControl();
            weatherOverviewControl = new WeatherOverviewControl();
            runwayOverviewControl = new RunwayOverviewControl();
            takeoffData = new TakeoffData();


            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //todo this is a test and should be removed
    private void showAircraftOverview() {
        vBox.getChildren().addAll(aircraftOverview, runwayOverviewControl, weatherOverviewControl);
        aircraftOverview.setAircraft(aircraft);
        weatherOverviewControl.setWeather(weather);
        runwayOverviewControl.setRunway(runway);
        takeoffData.setAircraft(aircraft);
        takeoffData.setWeather(weather);
        aircraft.setGrossWeight(40000);
        aircraft.setDragIndex(-.65);
        aircraft.setMaxThrust(true);
        weather.setPressureAltitude(1000);
        weather.setTemperature(15);
    }


    public Aircraft getAircraft() {
        return aircraft;
    }


    @FXML
    private void handleTest() {

        try {
            takeoffData.calculateSingleEngineROC();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(takeoffData.getSingleEngineROC());
    }
}



