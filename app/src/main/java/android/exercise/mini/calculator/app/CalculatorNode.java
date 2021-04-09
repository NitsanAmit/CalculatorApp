package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import java.io.Serializable;

public class CalculatorNode implements Serializable {

    private long value;
    private CalculatorOperation operation;
    private CalculatorNode next;

    public CalculatorNode(final long value, final CalculatorNode next) {
        this.value = value;
        this.next = next;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(final long value) {
        this.value = value;
    }

    public CalculatorOperation getOperation() {
        return this.operation;
    }

    public void setOperation(final CalculatorOperation operation) {
        this.operation = operation;
    }

    public CalculatorNode getNext() {
        return this.next;
    }

    public void setNext(final CalculatorNode next) {
        this.next = next;
    }

    @NonNull
    @Override
    public String toString() {
        return (next == null ? "" : next.toString()) + nodeToString();
    }

    private String nodeToString() {
        return value + (operation != null ? operation.toString() : "");
    }

    public long calculate() {
        if (next == null) {
            return value;
        }
        if (next.operation == CalculatorOperation.PLUS) {
            return (next.next == null ? next.value : next.calculate()) + value;
        } else if (next.operation == CalculatorOperation.MINUS) {
            return (next.next == null ? next.value : next.calculate()) - value;
        }
        return 0;
    }
}
