package android.exercise.mini.calculator.app;

import java.io.Serializable;

public class SimpleCalculatorImpl implements SimpleCalculator {

    private CalculatorNode head;

    public SimpleCalculatorImpl() {
        clear();
    }

    @Override
    public String output() {
        return head.toString();
    }

    @Override
    public void insertDigit(int digit) {
        if (digit > 9 || digit < 0)
            throw new RuntimeException("Error, attempt to insert multiple digits at once.");

        if (head.getOperation() == null) {
            head.setValue(head.getValue() * 10 + digit);
        } else {
            head = new CalculatorNode(digit, head);
        }
    }

    @Override
    public void insertPlus() {
        head.setOperation(CalculatorOperation.PLUS);
    }

    @Override
    public void insertMinus() {
        head.setOperation(CalculatorOperation.MINUS);

    }

    @Override
    public void insertEquals() {
        long result = head.calculate();
        head = new CalculatorNode(result, null);
    }

    @Override
    public void deleteLast() {
        if (head.getOperation() != null) {
            head.setOperation(null);
        } else if (head.getValue() > 9) {
            head.setValue(head.getValue() / 10);
        } else if (head.getNext() != null) {
            head = head.getNext();
        } else {
            head.setValue(0);
        }
    }

    @Override
    public void clear() {
        head = new CalculatorNode(0, null);
    }

    @Override
    public Serializable saveState() {
        return head;
    }

    @Override
    public void loadState(Serializable prevState) {
        if (!(prevState instanceof CalculatorNode)) {
            return; // ignore
        }
        head = (CalculatorNode) prevState;
    }

}
