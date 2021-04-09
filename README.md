# AndroidCalculator - Calculator exercise for Android developers

## In this project:
- Calculator screen with XML ready for portrait and landscape
- Calculator interface used by the Activity
- Unit tests for the calculator and the activity

### Answer to questions:
Saying we want to add a cool feature - button "x" to run multiplication.
#### What code do we need to change/add/remove to support this feature?
I would need to add the operator to the enum "CalculatorOperation". Add a new button to the UI and a listener for it.
Support the operation in the calculation method in CalculatorNode.calculate().

#### Which tests can we run on the calculator? On the activity? On the app?
Whether we choose to implement the new operator with order (multiplication before addition) - test that it's working properly.
Test the new button and that it appears in the output on the screen.
Test various flows that include multiplication with other operations.

## Author statement:
I pledge the highest level of ethical principles in support of academic excellence.
I ensure that all of my work reflects my own abilities and not those of someone else.

Nitsan.
