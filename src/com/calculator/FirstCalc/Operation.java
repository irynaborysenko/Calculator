package com.calculator.FirstCalc;

/**
 * Created by: Iryna Borysenko
 * Date: 2/11/16
 */
abstract class Operation {

    double execute(double numberOne, double numberTwo) {
        return numberOne;
    }

    double execute(double number) {
        return number;
    }
}

class OperationHolder {
    static final Operation add = new Addition();
    static final Operation substract = new Subtraction();
    static final Operation multiplication = new Multiplication();
    static final Operation division = new Division();
    static final Operation sin = new Sin();
    static final Operation cos = new Cos();
    static final Operation tg = new Tg();
    static final Operation ctg = new Ctg();
    static final Operation exp = new Exp();
}

class Addition extends Operation {
    @Override
    public double execute(double numberOne, double numberTwo) {
        return numberOne + numberTwo;
    }
}

class Subtraction extends Operation {
    @Override
    public double execute(double numberOne, double numberTwo) {
        return numberOne - numberTwo;
    }
}

class Multiplication extends Operation {
    @Override
    public double execute(double numberOne, double numberTwo) {
        return numberOne * numberTwo;
    }
}

class Division extends Operation {
    @Override
    public double execute(double numberOne, double numberTwo) {
        return numberOne / numberTwo;
    }
}

class Sin extends Operation {
    @Override
    public double execute(double number) {
        return Math.sin(number);
    }
}

class Cos extends Operation {
    @Override
    public double execute(double number) {
        return Math.cos(number);
    }
}

class Tg extends Operation {
    @Override
    public double execute(double number) {
        return Math.tan(number);
    }
}

class Ctg extends Operation {
    @Override
    public double execute(double number) {
        return 1.0 / Math.tan(number);
    }
}

class Exp extends Operation {
    @Override
    public double execute(double number) {
        return Math.exp(number);
    }
}