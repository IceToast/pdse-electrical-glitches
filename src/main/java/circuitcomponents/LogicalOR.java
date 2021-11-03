package circuitcomponents;

import exceptions.MissingInputException;

import java.util.List;

public class LogicalOR extends LogicalComponent{
    @Override
    public void calculateState() throws MissingInputException {
        List<Component> inputs = getInputs();
        if (inputs.isEmpty()) throw new MissingInputException();

        boolean result = false;
        for (Component input:inputs) {
            result = result || input.getState();
        }
        setState(result);
        callCalculateOnChildren();
    }
}
