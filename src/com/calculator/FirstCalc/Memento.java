package com.calculator.FirstCalc;

import java.util.ArrayList;

/**
 * Created by: Iryna Borysenko
 * Date: 2/11/16
 */

public class Memento {

    private Operation savedOperation;
    private double savedValue;
    private double savedResult;

    public Memento(Operation savedOperation, double savedValue, double savedResult) {
        this.savedOperation = savedOperation;
        this.savedValue = savedValue;
        this.savedResult = savedResult;
    }

    public Operation getSavedOperation() {
        return savedOperation;
    }

    public double getSavedValue() {
        return savedValue;
    }

    public double getSavedResult() {
        return savedResult;
    }
}

class Caretaker {
    public ArrayList<Memento> savedStates = new ArrayList<Memento>();

    private Memento memento;

    public void addMemento(Memento memento) {
        savedStates.add(memento);
    }

    public Memento getMemento(int index) {
        return savedStates.get(index);
    }
}

class Originator {
    private Operation savedOperation;
    private double savedValue;
    private double savedResult;

    public void setData(Operation savedOperation, double savedValue, double savedResult) {
        this.savedOperation = savedOperation;
        this.savedValue = savedValue;
        this.savedResult = savedResult;
    }

    public void setResult(double savedResult) {
        this.savedResult = savedResult;
    }

    public Operation getSavedOperation() {
        return savedOperation;
    }

    public double getSavedValue() {
        return savedValue;
    }

    public double getSavedResult() {
        return savedResult;
    }

    public Memento saveData() {
        return new Memento(savedOperation, savedValue, savedResult);
    }

    public double undo(Memento memento) {
        this.savedResult = memento.getSavedResult();
        this.savedOperation = memento.getSavedOperation();
        return savedResult;
    }

    public double redo(Memento memento) {
        final Strategy strategy = new Strategy();
        this.savedResult = memento.getSavedResult();
        this.savedOperation = memento.getSavedOperation();
        this.savedValue = memento.getSavedValue();
        strategy.setOperation(savedOperation);
        return strategy.executeOperation(savedResult, savedValue);
    }

}