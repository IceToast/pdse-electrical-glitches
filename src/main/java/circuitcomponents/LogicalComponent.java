package circuitcomponents;

import exceptions.MissingInputException;
import exceptions.NoZeroDelayException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class LogicalComponent implements Component {
    private List<Component> inputs = new ArrayList<>();
    private boolean state = false;
    private int delay;

    public LogicalComponent() {
        this.delay = 1;
    }

    public LogicalComponent(int delay) {
        if (delay <= 0) throw new NoZeroDelayException();
        this.delay = delay;
    }

    public void addInput(Component... component) {
        if (component.length <= 0)
            throw new MissingInputException("No Input to add");

        Collections.addAll(this.inputs, component);
    }

    public abstract void calculateState() throws MissingInputException;


    public int calculateToggleTime() {
        List<Component> inputs = getInputs();
        int maxTreeSize = 0;
        for (Component input : inputs) {
            maxTreeSize = Math.max(maxTreeSize, input.calculateToggleTime());
        }

        return maxTreeSize;
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