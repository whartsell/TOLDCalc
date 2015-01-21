package net.willshouse.planner.testui.model;

import javafx.beans.property.*;

/**
 * Created by whartsell on 1/20/15.
 */
public class Aircraft {
    private final StringProperty name;
    private final IntegerProperty grossWeight;
    private final DoubleProperty dragIndex;

    public Aircraft(String name) {
        this.dragIndex = new SimpleDoubleProperty(0d);
        this.grossWeight = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty(name);
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

    public int getGrossWeight() {
        return grossWeight.get();
    }

    public void setGrossWeight(int grossWeight) {
        this.grossWeight.set(grossWeight);
    }

    public IntegerProperty grossWeightProperty() {
        return grossWeight;
    }

    public double getDragIndex() {
        return dragIndex.get();
    }

    public void setDragIndex(double dragIndex) {
        this.dragIndex.set(dragIndex);
    }

    public DoubleProperty dragIndexProperty() {
        return dragIndex;
    }


}
