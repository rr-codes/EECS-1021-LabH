package eecs1021.PartB;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Like it says on the box, `Controller` dictates the main behaviour and controls the program.
 * The 'C' in 'MVC'.
 */
public class Controller implements ListChangeListener<WeightedGrade> {
    private final ObservableList<WeightedGrade> weightedGrades;
    private final SimpleDoubleProperty gradeProperty;

    /**
     * Creates a new {@code Controller}, and starts listening to the new {@code ObservableList} of grades
     */
    public Controller() {
        // 1. initialize `this.weightedGrades` and `this.gradeProperty`
        // 2. Add `this` as a listener to `this.weightedGrades`
    }

    /**
     * Returns all the {@code WeightedGrade}s of the table as an {@code ObservableList}
     * @return a list of {@code WeightedGrade}
     */
    public ObservableList<WeightedGrade> getWeightedGrades() {
        return this.weightedGrades;
    }

    /**
     * Returns the final grade value as a property.
     * @return a {@code DoubleProperty} referencing the value of the final grade of the list.
     * @see javafx.beans.property.Property
     */
    public DoubleProperty finalGradeProperty() {
        // return the grade property
    }

    /**
     * Adds an entry to the list of weighted grades
     * @param grade the raw grade, out of 100
     * @param weight the raw weight, out of 100
     */
    public void addWeightedGrade(int grade, int weight) {
        // create a WeightedGrade instance and add it to the list
    }

    /**
     * Invoked whenever a change is applied to the list of grades (ie, an item added or removed).
     * We associate this method with being called when the list is changed because we added {@code this}
     * as a listener to the list.
     *
     * @param change a {@code Change} describing what happened to the list
     * @see ObservableList#addListener(ListChangeListener)
     * @see javafx.collections.ListChangeListener.Change
     */
    @Override
    public void onChanged(Change<? extends WeightedGrade> change) {
        var list = change.getList();

        // what should happen here?
    }
}
