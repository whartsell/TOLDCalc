package net.willshouse.planner.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by whartsell on 1/23/15.
 */
public class Runway {

    private final StringProperty name;
    private final IntegerProperty heading;
    private final IntegerProperty length;
    private final IntegerProperty elevation;
    private final IntegerProperty rcr;


    public Runway() {
        name = new SimpleStringProperty();
        heading = new SimpleIntegerProperty();
        length = new SimpleIntegerProperty();
        elevation = new SimpleIntegerProperty();
        rcr = new SimpleIntegerProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getHeading() {
        return heading.get();
    }

    public void setHeading(int heading) {
        this.heading.set(heading);
    }

    public IntegerProperty headingProperty() {
        return heading;
    }

    public int getLength() {
        return length.get();
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public IntegerProperty lengthProperty() {
        return length;
    }

    public int getElevation() {
        return elevation.get();
    }

    public void setElevation(int elevation) {
        this.elevation.set(elevation);
    }

    public IntegerProperty elevationProperty() {
        return elevation;
    }

    public int getRcr() {
        return rcr.get();
    }

    public void setRcr(int rcr) {
        this.rcr.set(rcr);
    }

    public IntegerProperty rcrProperty() {
        return rcr;
    }
}
