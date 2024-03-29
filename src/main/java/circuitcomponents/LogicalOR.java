package circuitcomponents;

import exceptions.MissingInputException;

import java.util.List;

/**
 * LogicalOR represents the physical logic gate OR.
 * It takes up to 4 inputs and only outputs true when at least one is true.
 */
public class LogicalOR extends LogicalComponent {

    public LogicalOR() {
        super();
    }

    public LogicalOR(int delay) {
        super(delay);
    }

    @Override
    public void calculateState() throws MissingInputException {
        List<Component> inputs = getInputs();
        if (inputs.isEmpty()) throw new MissingInputException();
        if (canComponentCalculate()) {
            boolean result = false;
            for (Component input : inputs) {
                result = result || input.getState();
            }
            setState(result);
        }
        callCalculateOnChildren();
    }
}
