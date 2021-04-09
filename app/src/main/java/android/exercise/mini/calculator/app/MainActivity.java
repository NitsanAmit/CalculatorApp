package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  public static final String DEFUALT_OUTPUT = "~~~ ready to start ~~~";

  @VisibleForTesting
  public SimpleCalculator calculator;
  private TextView outputView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    for (int id : Arrays.asList(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonPlus, R.id.buttonMinus, R.id.buttonClear, R.id.buttonEquals, R.id.buttonBackSpace
    )) {
      findViewById(id).setOnClickListener(this);
    }
    outputView = findViewById(R.id.textViewCalculatorOutput);
    outputView.setText(DEFUALT_OUTPUT);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("calculator", calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    calculator.loadState(savedInstanceState.getSerializable("calculator"));
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.button0) {
      calculator.insertDigit(0);
    } else if (id == R.id.button1) {
      calculator.insertDigit(1);
    } else if (id == R.id.button2) {
      calculator.insertDigit(2);
    } else if (id == R.id.button3) {
      calculator.insertDigit(3);
    } else if (id == R.id.button4) {
      calculator.insertDigit(4);
    } else if (id == R.id.button5) {
      calculator.insertDigit(5);
    } else if (id == R.id.button6) {
      calculator.insertDigit(6);
    } else if (id == R.id.button7) {
      calculator.insertDigit(7);
    } else if (id == R.id.button8) {
      calculator.insertDigit(8);
    } else if (id == R.id.button9) {
      calculator.insertDigit(9);
    } else if (id == R.id.buttonPlus) {
      calculator.insertPlus();
    } else if (id == R.id.buttonMinus) {
      calculator.insertMinus();
    } else if (id == R.id.buttonEquals) {
      calculator.insertEquals();
    } else if (id == R.id.buttonClear) {
      calculator.clear();
    } else if (id == R.id.buttonBackSpace) {
      calculator.deleteLast();
    }
    outputView.setText(calculator.output());
  }
}