package eecs1021.PartA;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Node makeSliderRow() {
        var hbox = new HBox();
        hbox.setSpacing(10.0);

        var slider = new Slider();
        slider.setMin(0.0);
        slider.setMax(100.0);

        var label = new Label();
        label.textProperty().bind(slider.valueProperty().asString("I am %.0f years old"));

        hbox.getChildren().addAll(slider, label);
        return hbox;
    }

    private Node makeButtonRow() {
        var hbox = new HBox();
        hbox.setSpacing(10.0);

        var textField = new TextField();
        textField.setPromptText("First name");

        var button = new Button("A button");

        button.disableProperty().bind(textField.textProperty().isEmpty());

        button.textProperty().bind(
            Bindings.when(textField.textProperty().isEmpty()).then("Disabled").otherwise("Enabled")
        );

        hbox.getChildren().addAll(textField, button);
        return hbox;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        var rowA = makeSliderRow();
        var rowB = makeButtonRow();

        var vbox = new VBox();
        vbox.setSpacing(10.0);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(rowA, rowB);

        var scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
