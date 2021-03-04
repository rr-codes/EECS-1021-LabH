package eecs1021.PartD;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import eecs1021.SerialPortService;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        var sp = SerialPortService.getSerialPort(/* Your port name */);
        var outputStream = sp.getOutputStream();

        var pane = new BorderPane();

        var slider = new Slider();
        slider.setMin(0.0);
        slider.setMax(100.0);

        // TODO: Add a 'listener' to the {@code valueProperty} of the slider. The listener
        //  should write the {@code byteValue()} of the new slider value to the output stream.

        pane.setCenter(slider);
        pane.setPadding(new Insets(0, 20, 0, 20));

        var scene = new Scene(pane, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
