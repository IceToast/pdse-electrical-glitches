package circuitcomponents;

import exceptions.MissingInputException;
import exceptions.NoZeroDelayException;
import exceptions.TooManyInputsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LogicalComponent is a abstract class which represents any logical component
 * in a circuit.
 * It has a state, inputs and a delay (physical delay)
 * It can calculate the toggleTime: toggleTime of inputs + delay
 * It can have up to 4 inputs
 */
public abstract class LogicalComponent implements Component {
    private List<Component> inputs = new ArrayList<>();
    private boolean state = false;
    private int delay;
    private int remainingDelay;

    public LogicalComponent() {
        this.delay = 1;
        resetRemainingDelay();
    }

    public LogicalComponent(int delay) {
        if (delay <= 0) throw new NoZeroDelayException();
        this.delay = delay;
        resetRemainingDelay();
    }

    private void resetRemainingDelay(){
        this.remainingDelay = this.delay;
    }

    private void decrementRemainingDelay(){
        this.remainingDelay--;
    }

    public boolean canComponentCalculate(){
        decrementRemainingDelay();
        if (this.remainingDelay != 0) return false;
        resetRemainingDelay();
        return true;
    }

    public void addInput(Component... component) {
        if (component.length <= 0)
            throw new MissingInputException("No Input to add");
        if (this.inputs.size() + component.length > 4)
            throw new TooManyInputsException("LogicalComponents only allow up to 4 inputs, cascade the component if you need more.");
        Collections.addAll(this.inputs, component);
    }

    public abstract void calculateState() throws MissingInputException;


    public int calculateToggleTime() {
        List<Component> inputs = getInputs();
        int maxTreeSize = 0;
        for (Component input : inputs) {
            maxTreeSize = Math.max(maxTreeSize, input.calculateToggleTime());
        }

        return maxTreeSize + delay;
    }

    void callCalculateOnChildren() {
        for (Component input : inputs) {
            input.calculateState();
        }
    }

    public List<Component> getInputs() {
        return inputs;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}