package net.willshouse.planner.ui.components;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.willshouse.planner.models.Weather;


/**
 * Created by whartsell on 1/23/15.
 */
public class testWeatherOverview extends Application {
    private Stage primaryStage;
    private WeatherOverviewControl rootLayout;
    private Weather weather;

    public testWeatherOverview() {
        weather = new Weather();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TOLD Calc");
        rootLayout = new WeatherOverviewControl();
        rootLayout.setWeather(weather);
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
