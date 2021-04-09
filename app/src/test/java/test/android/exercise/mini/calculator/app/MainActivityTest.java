package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.MainActivity;
import android.exercise.mini.calculator.app.R;
import android.exercise.mini.calculator.app.SimpleCalculator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.io.Serializable;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class MainActivityTest {

  private static final String DEFAULT_CALCULATOR_OUTPUT = "~~~ ready to start ~~~";

  private ActivityController<MainActivity> activityController;
  private MainActivity activityUnderTest;
  private SimpleCalculator mockCalculator;

  /** initialize main activity with a mock calculator */
  @Before
  public void setup(){
    mockCalculator = Mockito.mock(SimpleCalculator.class);
    Mockito.when(mockCalculator.output()).thenReturn(DEFAULT_CALCULATOR_OUTPUT);

    activityController = Robolectric.buildActivity(MainActivity.class);
    activityUnderTest = activityController.get();
    activityUnderTest.calculator = mockCalculator;
    activityController.create().start().resume();
  }

  @Test
  public void when_settingUpTheActivity_then_itShouldShowTheExpectedCalculatorOutputRightAway() {
    // setup
    String expectedText = DEFAULT_CALCULATOR_OUTPUT;
    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    // verify
    assertEquals(expectedText, activityMainTextView.getText().toString());
  }

  @Test
  public void when_userClicksButtonPlus_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
    // setup
    String expectedText = "button PLUS clicked";
    Mockito.when(mockCalculator.output()).thenReturn(expectedText);

    TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    View buttonPlus = activityUnderTest.findViewById(R.id.buttonPlus);

    // test
    buttonPlus.performClick();

    // verify
    Mockito.verify(mockCalculator).insertPlus(); // make sure that the activity called this method
    assertEquals(expectedText, activityMainTextView.getText().toString()); // make sure that the activity shows text from calculator.output()
  }

  @Test
  public void when_userClicksDigitButton_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {

    View button0 = activityUnderTest.findViewById(R.id.button0);
    View button1 = activityUnderTest.findViewById(R.id.button1);
    View button2 = activityUnderTest.findViewById(R.id.button2);
    View button3 = activityUnderTest.findViewById(R.id.button3);
    View button4 = activityUnderTest.findViewById(R.id.button4);
    View button5 = activityUnderTest.findViewById(R.id.button5);
    View button6 = activityUnderTest.findViewById(R.id.button6);
    View button7 = activityUnderTest.findViewById(R.id.button7);
    View button8 = activityUnderTest.findViewById(R.id.button8);
    View button9 = activityUnderTest.findViewById(R.id.button9);
    View buttonBackspace = activityUnderTest.findViewById(R.id.buttonBackSpace);
    View buttonMinus = activityUnderTest.findViewById(R.id.buttonMinus);
    View buttonClear = activityUnderTest.findViewById(R.id.buttonClear);
    View buttonEquals = activityUnderTest.findViewById(R.id.buttonEquals);

    //0
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(0);
    button0.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(0);

    //1
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(1);
    button1.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(1);

    //2
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(2);
    button2.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(2);

    //3
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(3);
    button3.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(3);

    //4
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(4);
    button4.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(4);

    //5
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(5);
    button5.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(5);

    //6
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(6);
    button6.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(6);

    //7
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(7);
    button7.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(7);

    //8
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(8);
    button8.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(8);

    //9
    Mockito.verify(mockCalculator, Mockito.never()).insertDigit(9);
    button9.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertDigit(9);

    //10 (backspace)
    Mockito.verify(mockCalculator, Mockito.never()).deleteLast();
    buttonBackspace.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).deleteLast();

    //11 (minus)
    Mockito.verify(mockCalculator, Mockito.never()).insertMinus();
    buttonMinus.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertMinus();

    //12 (clear)
    Mockito.verify(mockCalculator, Mockito.never()).clear();
    buttonClear.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).clear();

    //13 (equals)
    Mockito.verify(mockCalculator, Mockito.never()).insertEquals();
    buttonEquals.performClick();
    Mockito.verify(mockCalculator, Mockito.times(1)).insertEquals();

    //[0-13] buttons -> should call output 14 times
    Mockito.verify(mockCalculator, Mockito.times(14)).output();
  }

  @Test
  public void when_activityGetsStateSaved_then_shouldAlsoSaveCalculatorState() {
    // setup
    Serializable dummyState = new Serializable() {};
    Mockito.when(mockCalculator.saveState()).thenReturn(dummyState);

    Bundle spyBundle = Mockito.spy(new Bundle());

    // test
    activityController.saveInstanceState(spyBundle);

    // verify
    Mockito.verify(spyBundle).putSerializable(anyString(), eq(dummyState)); // make sure that the activity stored the calculator state into the spy bundle
  }


  @Test
  public void when_activityGetsStateRestored_then_shouldAlsoSaveCalculatorState() {
    // setup
    Serializable dummyState = new Serializable() {};
    Mockito.when(mockCalculator.saveState()).thenReturn(dummyState);

    // let the activity store the calculator state into the bundle
    Bundle spyBundle = Mockito.spy(new Bundle());
    activityController.saveInstanceState(spyBundle);

    // test
    activityController.restoreInstanceState(spyBundle);

    // verify
    Mockito.verify(mockCalculator).loadState(eq(dummyState)); // make sure that the activity passed the previously-stored state to the calculator to load
  }
}