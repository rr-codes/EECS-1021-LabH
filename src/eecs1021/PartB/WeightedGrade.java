package eecs1021.PartB;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

/**
 * This is the 'model' of MVC. It describes a particular object, and is used to 'model' each row
 * of the table view.
 *
 * Specifically, this is a JavaFX POJO class (with properties)
 */
public class WeightedGrade {
    private final Property<Integer> gradeProperty;
    private final Property<Integer> weightProperty;

    public WeightedGrade(int grade, int weight) {
        this.gradeProperty = new SimpleIntegerProperty(grade).asObject();
        this.weightProperty = new SimpleIntegerProperty(weight).asObject();
    }

    public int getGrade() {
        return gradeProperty.getValue();
    }

    public int getWeight() {
        return weightProperty.getValue();
    }

    public void setGrade(int grade) {
        gradeProperty.setValue(grade);
    }

    public void setWeight(int weight) {
        weightProperty.setValue(weight);
    }

    ObservableValue<Integer> gradeProperty() {
        return this.gradeProperty;
    }

    ObservableValue<Integer> weightProperty() {
        return this.weightProperty;
    }
}
