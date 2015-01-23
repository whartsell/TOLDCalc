package net.willshouse.planner.ui.components;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.willshouse.planner.ui.components.model.Runway;

/**
 * Created by whartsell on 1/23/15.
 */
public class testRunwayOverview extends Application {
    private Stage primaryStage;
    private RunwayOverviewControl rootLayout;
    private Runway runway;

    public testRunwayOverview() {
        runway = new Runway();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TOLD Calc");
        rootLayout = new RunwayOverviewControl();
        rootLayout.setRunway(runway);
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
