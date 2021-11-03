package circuitcomponents;

import exceptions.MissingInputException;

import java.util.List;

public class LogicalAND extends LogicalComponent{

    @Override
    public void calculateState() throws MissingInputException {
        List<Component> inputs = getInputs();
        if(inputs.isEmpty()) throw new MissingInputException();

        boolean result = true;
        for (Component input : inputs) {
            result = result && input.getState();
        }
        setState(result);
        callCalculateOnChildren();
    }
}
