package circuitcomponents;

import exceptions.NoInputsAllowedForInputException;

/**
 * LogicalInput represents a physical Input to a Circuit.
 * It does not implement any logic or has any behaviour.
 * Not tested, because of missing testable logic.
 * It cannot have any inputs
 * The channel is representing the integer of an LogicalInput e.g. the input x2 has channel 2 and so on
 */
public class LogicalInput extends LogicalComponent {
    private int channel;

    public LogicalInput(int channel) {
        this.channel = channel;
    }

    public int getChannel() {
        return channel;
    }

    @Override
    public void addInput(Component... component) {
        throw new NoInputsAllowedForInputException();
    }

    @Override
    public void calculateState() {
    }

    @Override
    public int calculateToggleTime() {
        return 0;
    }
}
