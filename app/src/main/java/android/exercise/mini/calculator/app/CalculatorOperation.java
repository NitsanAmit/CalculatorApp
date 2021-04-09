package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import java.io.Serializable;

public enum CalculatorOperation implements Serializable {
    PLUS("+"),
    MINUS("-");

    private final String stringFormat;

    CalculatorOperation(final String stringFormat) {
        this.stringFormat = stringFormat;
    }

    @NonNull
    @Override
    public String toString() {
        return this.stringFormat;
    }
}
