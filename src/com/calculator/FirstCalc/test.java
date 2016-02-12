package com.calculator.FirstCalc;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by: Iryna Borysenko
 * Date: 2/11/16
 */

public class test {
    public static double initNumber;
    public static final Strategy strategy = new Strategy();
    public static final Originator originator = new Originator();
    public static final Caretaker caretaker = new Caretaker();

    public static void main(String... args) {
        try {
            boolean cycle = true;
            System.out.println("Enter first number and choose operations:\n" +
                    "\"+\", \"-\", \"*\", \"/\",\n" +
                    "\"sin\", \"cos\", \"tg\", \"ctg\", \"exp\",\n" +
                    "\"undo\", \"redo\", \"clear\", \"end\".");
            initNumber = getFirstNumber();

            while (cycle) {
                Scanner scan = new Scanner(System.in);
                final String operationSymbol = scan.nextLine();
                switch (operationSymbol.toLowerCase()) {
                    case "+":
                        processing(OperationHolder.add, false);
                        break;
                    case "-":
                        processing(OperationHolder.substract, false);
                        break;
                    case "*":
                        processing(OperationHolder.multiplication, false);
                        break;
                    case "/":
                        processing(OperationHolder.division, false);
                        break;
                    case "sin":
                        processing(OperationHolder.sin, true);
                        break;
                    case "cos":
                        processing(OperationHolder.cos, true);
                        break;
                    case "tg":
                        processing(OperationHolder.tg, true);
                        break;
                    case "ctg":
                        processing(OperationHolder.ctg, true);
                        break;
                    case "exp":
                        processing(OperationHolder.exp, true);
                        break;
                    case "undo":
                        doUndo();
                        break;
                    case "redo":
                        doRedo();
                        break;
                    case "clear":
                        clearAll();
                        break;
                    case "end":
                        cycle = false;
                        break;
                    default:
                        System.out.println("Enter correct operation");
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(
                    "Error, enter number" +
                            (e.getMessage() != null ? " " + e.getMessage() : ""));
        }
    }

    private static double getFirstNumber() {
        final Scanner in = new Scanner(System.in);
        return in.nextDouble();
    }

    private static double getSecondNumber() {
        final Scanner in = new Scanner(System.in);
        return in.nextDouble();
    }

    private static void processing(Operation holder, boolean overload) {
        double firstNumber, secondNumber, localResult;
        firstNumber = originator.getSavedResult() != 0 ? originator.getSavedResult() : initNumber;
        strategy.setOperation(holder);
        if (!overload) {
            secondNumber = getSecondNumber();
            localResult = strategy.executeOperation(firstNumber, secondNumber);
        } else {
            localResult = strategy.executeOperation(firstNumber);
            secondNumber = 0;
        }
        System.out.println(localResult);
        originator.setData(holder, secondNumber, localResult);
        caretaker.addMemento(originator.saveData());
        System.out.println(caretaker.savedStates.size());
    }

    private static void doUndo() {
        if (caretaker.savedStates.size() == 0) {
            System.out.println(initNumber);
        } else if (caretaker.savedStates.size() == 1 || caretaker.savedStates.size() == 2) {
            double localResult = originator.undo(caretaker.getMemento(0));
            System.out.println(localResult);
        } else {
            double localResult = originator.undo(caretaker.getMemento(caretaker.savedStates.size() - 2));
            System.out.println(localResult);
        }
    }

    private static void doRedo() {
        if (caretaker.savedStates.size() == 0) {
            System.out.println("First do some operation");
        } else {
            Memento mementoLocal;
            mementoLocal = caretaker.savedStates.size() == 1 ? caretaker.getMemento(0) : caretaker.getMemento(caretaker.savedStates.size() - 1);
            double localResult = originator.redo(mementoLocal);
            originator.setData(originator.getSavedOperation(), mementoLocal.getSavedValue(), localResult);
            caretaker.addMemento(originator.saveData());
            System.out.println(localResult);
        }
    }

    private static void clearAll() {
        originator.setData(null, 0, 0);
        caretaker.addMemento(originator.saveData());
        caretaker.savedStates.clear();
        System.out.println("It's clear now. Enter number.");
        initNumber = getFirstNumber();
    }
}