package circuitcomponents;

import exceptions.MissingInputException;
import exceptions.TooManyInputsException;

import java.util.Collections;
import java.util.List;

/**
 * LogicalNOT represents the physical logic gate NOT.
 * It receives one input and outputs the opposite boolean state of it.
 * If attached to multiple inputs an exception will be thrown.
 */
public class LogicalNOT extends LogicalComponent {

    @Override
    public void calculateState() {
        List<Component> inputs = getInputs();
        if (inputs.isEmpty()) throw new MissingInputException();

        Component input = inputs.get(0);
        boolean result = !input.getState();
        setState(result);
        callCalculateOnChildren();
    }

    @Override
    public void addInput(Component... component) {
        List<Component> inputs = getInputs();
        if(component.length <= 0)
            throw new MissingInputException("No Input to add");

        if(inputs.size() == 1 || component.length > 1)
            throw new TooManyInputsException("A LogicalNOT can only handle 1 input");

        Collections.addAll(getInputs(), component);
    }
}
