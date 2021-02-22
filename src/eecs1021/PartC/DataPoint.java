package eecs1021.PartC;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataPoint {
    private final Property<Long> timeProperty;
    private final Property<Integer> valueProperty;

    public DataPoint(long time, int value) {
        this.timeProperty = new SimpleLongProperty(time).asObject();
        this.valueProperty = new SimpleIntegerProperty(value).asObject();
    }

    public long getTime() {
        return timeProperty.getValue();
    }

    public int getValue() {
        return valueProperty.getValue();
    }

    public void setTime(long time) {
        timeProperty.setValue(time);
    }

    public void setValue(int value) {
        valueProperty.setValue(value);
    }

    ObservableValue<Long> timeProperty() {
        return this.timeProperty;
    }

    ObservableValue<Integer> valueProperty() {
        return this.valueProperty;
    }
}
