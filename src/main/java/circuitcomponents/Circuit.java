package circuitcomponents;

import exceptions.DuplicateInputChannelDetectedException;
import exceptions.MissingInputException;

import java.util.*;
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
        if (this.topComponent.getInputs().size() <= 0) {
            throw new MissingInputException("No inputs set for topComponent");
        }
        validateUniqueInputs(topComponent.getInputs());
        this.inputs = getAllLogicalInputs();
    }

    private List<LogicalInput> getAllLogicalInputs() {
        List<Component> components = this.topComponent.getInputs();
        List<Component> newFoundComponents = new ArrayList<>();
        List<Component> toRemoveComponents = new ArrayList<>();
        List<LogicalInput> usedInputs = new ArrayList<>();
        boolean allFound = false;
        while (!allFound) {
            for (Component component : components) {
                if (component instanceof LogicalInput) {
                    usedInputs.add((LogicalInput) component);
                } else {
                    newFoundComponents.addAll(component.getInputs());
                }
                toRemoveComponents.add(component);
            }
            newFoundComponents.removeAll(toRemoveComponents);
            components = newFoundComponents;
            if (components.size() == 0) {
                allFound = true;
            }
        }
        List<LogicalInput> uniqueUsedSortedInputs = usedInputs.stream()
                .distinct()
                .sorted(Comparator.comparing(LogicalInput::getChannel))
                .collect(Collectors.toList());
        return uniqueUsedSortedInputs;
    }

    private int getNumberOfUniqueInputs(List<Component> usedInputs) {
        Set<Component> duplicateCheckSet = new HashSet<>(usedInputs);
        return duplicateCheckSet.size();
    }

    private void validateUniqueInputs(List<Component> inputs) {
        if (getNumberOfUniqueInputs(inputs) != inputs.size()) {
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
