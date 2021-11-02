package circuitcomponents;

import exceptions.MissingInputException;

import java.util.ArrayList;
import java.util.List;

public abstract class LogicalComponent implements Component {
    private List<Component> inputs = new ArrayList<>();
    private boolean state = false;
    private int delay;

    public void addInput(Component... component) {
    }

    public void calculateState() throws MissingInputException {
    }


    public int calculateToggleTime() {
        return 0;
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