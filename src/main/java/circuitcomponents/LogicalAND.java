package circuitcomponents;

import exceptions.MissingInputException;

import java.util.List;

/**
 * LogicalAND represents the logic gate AND.
 * It takes any number of inputs and only outputs true when all of them are true.
 */
public class LogicalAND extends LogicalComponent {

    @Override
    public void calculateState() throws MissingInputException {
        List<Component> inputs = getInputs();
        if (inputs.isEmpty()) throw new MissingInputException();
        if (canComponentCalculate()) {
            boolean result = true;
            for (Component input : inputs) {
                result = result && input.getState();
            }
            setState(result);
        }
        callCalculateOnChildren();
    }
}
