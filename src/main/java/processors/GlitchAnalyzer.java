package processors;

import circuitcomponents.Circuit;
import circuitcomponents.LogicalInput;
import exceptions.MissingCircuitException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The GlitchAnalyzer (GA) takes a circuit and if it is a valid circuit it analyzes for glitches.
 * It leads to a glitch if the output state of a circuit changes more than 1 time when only one input is shifted once.
 * To analyze the complete circuit for glitches it takes exactly (2^(number of inputs)) base calculation steps.
 * Within every step we need to shift every input once.
 * The maxcalculationtime is the time it takes max to iterate once through the circuit.
 * To simulate a clock for every inputstate you need to iterate the number of maxcalculationtime and check if the state is changed more than once.
 *
 * The interesting result is within the return of the analyzeForGlitches() Method.
 * It contains a representation of Truth Table for input xn, ..., x1, x0 but filled with true if glitching in this constellation or false if not.
 */
public class GlitchAnalyzer {
    private Circuit circuit;
    private List<LogicalInput> inputs;

    public GlitchAnalyzer(Circuit circuit) {
        if (circuit == null) throw new MissingCircuitException("No circuit set for GlitchAnalyzing");
        this.circuit = circuit;
        this.inputs = circuit.getInputs();
    }

    public Map<Integer, List<Boolean>> analyzeForGlitches() {
        Map<Integer, List<Boolean>> glitches = new HashMap<>();
        for (int i = 0; i < Math.pow(2, inputs.size()); i++) {
            glitches.put(i, analyzeGlitchesPerInputSet(i));
        }
        return glitches;
    }

    private List<Boolean> analyzeGlitchesPerInputSet(int i) {
        List<Boolean> glitches = new ArrayList<>();
        for (LogicalInput input : inputs) {
            setInputState(i);
            circuit.calculateFinalState();
            input.setState(!input.getState());
            glitches.add(isGlitching());
        }
        return glitches;
    }

    private boolean isGlitching() {
        int stateChanges = 0;
        boolean oldState;

        for (int i = 0; i < circuit.calculateMaxToggleTime(); i++) {
            if (stateChanges >= 2) break;
            oldState = circuit.getState();
            circuit.calculateOneIteration();
            if (oldState != circuit.getState()) stateChanges++;
        }
        return stateChanges >= 2;
    }

    private void setInputState(int i) {
        for (LogicalInput input : inputs) {
            input.setState(getBitForIndexOfInteger(i, inputs.indexOf(input)) != 0);
        }
    }

    private int getBitForIndexOfInteger(int n, int index) {
        return (n >> index) & 1;
    }
}
