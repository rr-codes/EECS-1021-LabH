package eecs1021.PartC;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListenerWithExceptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.ByteBuffer;

public class Controller implements SerialPortMessageListenerWithExceptions {
    private static final byte[] DELIMITER = new byte[]{'\n'};

    private final ObservableList<DataPoint> dataPoints;

    public Controller() {
        this.dataPoints = FXCollections.observableArrayList();
    }

    public ObservableList<DataPoint> getDataPoints() {
        return dataPoints;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        // TODO:
        //  the serialPortEvent receives a byte array containing 4 bytes
        //  this array has to be first converted to an int
        //  then, create a {@code DataPoint} instance using the current time and the value
        //  and add it to the {@code dataPoints} list
    }

    @Override
    public void catchException(Exception e) {
        e.printStackTrace();
    }

    @Override
    public byte[] getMessageDelimiter() {
        return DELIMITER;
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return true;
    }
}
