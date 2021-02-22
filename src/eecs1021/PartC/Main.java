package eecs1021.PartC;

import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.FormatStringConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static SerialPort getSerialPort() {
        var sp = SerialPort.getCommPort("/dev/cu.usbserial-1410");

        sp.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        var hasOpened = sp.openPort();
        if (!hasOpened) {
            throw new IllegalStateException("Failed to open port.");
        }

        return sp;
    }

    private static TableView<DataPoint> getTableView() {
        var table = new TableView<DataPoint>();

        var timeColumn = new TableColumn<DataPoint, Long>("Date");
        timeColumn.setCellValueFactory(row -> row.getValue().timeProperty());

        // we provide a 'cell factory' which converts the date/time from ms to a human readable format for displaying
        timeColumn.setCellFactory(
                TextFieldTableCell.forTableColumn(
                        new FormatStringConverter<>(
                                DateFormat.getDateTimeInstance()
                        )
                )
        );

        var valueColumn = new TableColumn<DataPoint, Integer>("Value");
        valueColumn.setCellValueFactory(row -> row.getValue().valueProperty());

        table.getColumns().setAll(List.of(timeColumn, valueColumn));

        return table;
    }

    @Override
    public void start(Stage primaryStage) {
        var sp = getSerialPort();
        var table = getTableView();
        var controller = new Controller();

        sp.addDataListener(controller);
        table.setItems(controller.getDataPoints());

        var vbox = new VBox(table);
        var scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
