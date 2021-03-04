# EECS 1021 Lab G

(c) 2021 Richard Robinson and James Andrew Smith

----

**IMPORTANT**: Your JavaFX installation must be version **15.0.1** and be located in your **home directory**.

----

### Table of Contents
* [Introduction](#introduction)
* [Tips and Tricks](#tips-and-tricks)
* [Part A](#part-a)
* [Part B](#part-b)
* [Part C](#part-c)

----

## Introduction

This lab introduces you to multiple important OOP paradigms demonstrated through the use of JavaFX. While it is somewhat challenging, you will learn very important and practical concepts that you can apply in future endeavours.

Because this lab spans multiple files, we will be using Github, which should make everything substantially easier.

To set up the project in IntelliJ:
1. [Download the ZIP](https://github.com/richardrobinson0924/EECS-1021-LabG/archive/master.zip)
2. Extract the ZIP file
3. Open IntelliJ, then click `Open Project` and select the project to open it

There are three parts to this lab; each part is separated into its own Java _package_ (directory / folder).

## Tips and Tricks

- Suppose you have a class `Foo` whose constructor takes two parameters, `a` and `b`, both of type `int`. To create an instance of `Foo`, you can do
```
Foo myFoo = new Foo(1, 2); // `1` is `a`, `2` is `b`
```

- To initialize an `ObservableList`, you do `... = FXCollections.observableArrayList()`
- When the button to add a grade is clicked, several things should happen:
    1. The two values of the text fields should be retrieved. Assuming you have a `TextField` names `tf`, you do this via `Integer.parseInt(tf.getText())`
    2. The `addWeightedGrade` method of your controller should be invoked using the two retrieved text field values.
    3. Both text fields should be cleared. To do this for a `TextField` called `tf`, use `tf.clear()`
- If you have a `StringProperty` (`a`) that needs to be bound to a non-String property (`b`), you can use `a.bind(b.asString())` 

## Part A

Part A is designed to introduce you to the Reactive programming paradigm, which is very common and useful for GUI frameworks such as React, SwiftUI, and JavaFX.

In Reactive programming, you use states, properties / observable values, and bindings. Properties are special values / variables that are _stateful_ (can change state), and to which can be bound to other properties. 
In Java, all properties inherit from the `Property` interface, which itself inherits from the `ObservableValue` interface. 
Bindings and properties make _extensive_ use of the _Listener_ design pattern.

This makes updating content dynamically very easy. Instead of doing this:

```java
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

class Main {
    public static void main(String[] args) {
        var label = new Label("Hello");
        var textField = new TextField();

        // when the button is clicked, this action executes
        textField.textProperty().addListener((observableValue, oldValue, newValue) -> label.setText(newValue));
    }
}
```

we can simply do this:

```java
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

class Main {
    public static void main(String[] args) {
        var label = new Label("Hello");
        var textField = new TextField();

        // the label's text is bound to the text field's text
        label.textProperty().bind(textField.textProperty());
        
        // this is a trivial example, but as you get into more advanced
        // GUI programming, it will become evident why Reactive
        // programming is easier to use.
    }
}
```

In Part A, there are two comments with instructions describing what needs to be implemented. This involves using bindings to set certain JavaFX elements' properties. Everything you have to do is marked in the comments with `TODO:`.

**Hint**: One of the bindings you will have to make is a _conditional binding_, using [`Bindings.when`](https://docs.oracle.com/javase/8/javafx/api/javafx/beans/binding/Bindings.html#when-javafx.beans.value.ObservableBooleanValue-).

## Part C

In Part C, you will be transmitting timestamped data from your Arduino's potentiometer readings to a JavaFX TableView.

There are two tasks in this part. 

In the first task, you are given some pieces of the `DataPoint` class. You have to implement the rest of the class. The `DataPoint` class is again a _JavaFX Java Beans model class_, so the structure should be trivially similar to the `WeightedGrade` class in Part 2, just with a few minor changes.

For the second task, you are responsible for implementing the `serialEvent()` method of Part C's `Controller`. The logic of this method should work like this:
1. The first thing you have to do is check if the event's type matches `SerialPort.LISTENING_EVENT_DATA_RECEIVED`. If it does not, stop the method by using `return`. Otherwise, proceed to the rest of the method.
2. Get the raw byte array from the event. To do this, use the `getReceivedData()` method of the serial port event.
3. Convert the byte array to an `int`. To do this, use `ByteBuffer.wrap(data).getInt()` (assuming `data` is the name of the byte array).
4. Get the current timestamp. To do this, use `System.currentTimeMillis()`.
5. Create an instance of the `DataPoint` class using the timestamp and the new `int` value.
6. Add the data point instance to `this.dataPoints`