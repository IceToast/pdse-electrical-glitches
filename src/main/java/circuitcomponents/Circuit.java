package circuitcomponents;

import exceptions.DuplicateInputChannelDetectedException;
import exceptions.TopComponentNoInputsSetException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
        if(this.topComponent.getInputs().size() <= 0){
            throw new TopComponentNoInputsSetException();
        }
    }

    private void sortInputsByChannel(List<LogicalInput> usedInputs) {
        validateUniqueChannels(usedInputs);
        usedInputs.sort(Comparator.comparingInt(LogicalInput::getChannel));
    }

    private void validateUniqueChannels(List<LogicalInput> usedInputs) {
        Set<Integer> duplicateChannelCheck = usedInputs.stream().map(LogicalInput::getChannel).collect(Collectors.toSet());
        if(duplicateChannelCheck.size() != usedInputs.size()){
            throw new DuplicateInputChannelDetectedException("InputChannels are unique, please check your used channels");
        }
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

    public void calculateFinalState() {
        for (int i = 0; i < calculateMaxToggleTime(); i++) {
            calculateOneIteration();
        }
    }

    public void calculateOneIteration() {
        if (!Objects.isNull(topComponent)) {
            topComponent.calculateState();
            this.state = topComponent.getState();
        }
    }
}
