package eecs1021.PartB;

import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.util.List;

public class Main extends Application {
    /**
     * Configures the behaviour of the specified table view
     * @param tableView the table view to configure
     */
    private void configureTableView(TableView<WeightedGrade> tableView) {
        var gradeColumn = new TableColumn<WeightedGrade, Integer>("Grade (of 100)");
        gradeColumn.setCellValueFactory(row -> row.getValue().gradeProperty());
        gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        gradeColumn.setOnEditCommit(t -> {
            var items = t.getTableView().getItems();
            var row = t.getTablePosition().getRow();
            var newGrade = t.getNewValue();
            var value = items.remove(row);

            value.setGrade(newGrade);

            if (row == items.size()) {
                items.add(value);
            } else {
                items.set(row, value);
            }
        });

        var weightColumn = new TableColumn<WeightedGrade, Integer>("Weight (of 100)");
        weightColumn.setCellValueFactory(row -> row.getValue().weightProperty());
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        weightColumn.setOnEditCommit(t -> {
            var items = t.getTableView().getItems();
            var row = t.getTablePosition().getRow();
            var newWeight = t.getNewValue();
            var value = items.remove(row);

            value.setWeight(newWeight);

            if (row == items.size()) {
                items.add(value);
            } else {
                items.set(row, value);
            }
        });

        tableView.getColumns().setAll(List.of(gradeColumn, weightColumn));
        tableView.setEditable(true);
    }

    @Override
    public void start(Stage primaryStage) {
        var controller = new Controller();

        var label = new Label("");
        label.textProperty().bind(controller.finalGradeProperty().asString());

        var table = new TableView<WeightedGrade>();

        configureTableView(table);
        table.setItems(controller.getWeightedGrades());

        var addGradeTextField = new TextField();
        addGradeTextField.setPromptText("Grade");

        var addWeightTextField = new TextField();
        addWeightTextField.setPromptText("Weight");

        var addWeightedGradeButton = new Button("Add");
        addWeightedGradeButton.setOnAction((e) -> {
            controller.addWeightedGrade(
                    Integer.parseInt(addGradeTextField.getText()),
                    Integer.parseInt(addWeightTextField.getText())
            );

            addGradeTextField.clear();
            addWeightTextField.clear();
        });

        var hbox = new HBox();
        hbox.getChildren().addAll(addGradeTextField, addWeightTextField, addWeightedGradeButton);

        var vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hbox);

        var scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
