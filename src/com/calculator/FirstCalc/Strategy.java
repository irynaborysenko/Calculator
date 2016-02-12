package com.calculator.FirstCalc;

/**
 * Created by: Iryna Borysenko
 * Date: 2/11/16
 */

public class Strategy {

    private Operation operation;

    public Strategy() {
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public double executeOperation(double numberOne, double numberTwo) {
        return operation.execute(numberOne, numberTwo);
    }

    public double executeOperation(double numberOne) {
        return operation.execute(numberOne);
    }
}