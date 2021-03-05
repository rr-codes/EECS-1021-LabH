# EECS 1021 Lab H

(c) 2021 Richard Robinson and James Andrew Smith

----

**IMPORTANT**: Your JavaFX installation must be version **15.0.1** and be located in your **home directory**.

----

### Table of Contents
* [Introduction](#introduction)
* [Tips and Tricks](#tips-and-tricks)
* [Part A](#part-a)
* [Part B](#part-b)

----

## Introduction

This lab introduces you to multiple important OOP paradigms demonstrated through the use of JavaFX. While it is somewhat challenging, you will learn very important and practical concepts that you can apply in future endeavours.

Because this lab spans multiple files, we will be using Github, which should make everything substantially easier.

To set up the project in IntelliJ:
1. [Download the ZIP](https://github.com/richardrobinson0924/EECS-1021-LabG/archive/master.zip)
2. Extract the ZIP file
3. Open IntelliJ, then click `Open Project` and select the project to open it

There are two parts to this lab; each part is separated into its own Java _package_ (directory / folder).

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

## Part B

In Part B, you will be transmitting the value of a JavaFX Slider to your Arduino's OLED.

For this part, all that is required is to send the data to the Arduino by 'listening' to the value of the slider's `valueProperty()`; when the property's value changes, it should be sent to the Arduino port's output stream.
