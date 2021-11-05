package circuitcomponents;

import java.util.List;
import java.util.Objects;

/**
 * Circuit represents a physical logic circuit.
 * It has a topNode which is the last logicalNode of the circuit and the logical Inputs.
 * It is able to calculate it's state based on the input states either
 * in multiple steps (for analyzing glitches) or in one step.
 */
public class Circuit {
    private List<LogicalInput> inputs;
    private Component topComponent;
    private boolean state;

    public Circuit(Component topComponent) {
        this.topComponent = topComponent;
    }

    public int calculateMaxToggleTime() {
        if (Objects.isNull(topComponent)) return 0;
        return topComponent.calculateToggleTime();
    }

    public List<LogicalInput> getInputs() {
        return this.inputs;
    }

    public boolean getState() {
        return this.state;
    }
}
