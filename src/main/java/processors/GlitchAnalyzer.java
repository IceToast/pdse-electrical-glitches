package processors;

import circuitcomponents.Circuit;
import circuitcomponents.LogicalInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlitchAnalyzer {
    private Circuit circuit;
    private List<LogicalInput> inputs;

    public GlitchAnalyzer(Circuit circuit) {
        this.circuit = circuit;
    }

    public HashMap<Integer, List<Boolean>> analyzeForGlitches() {
        HashMap<Integer, List<Boolean>> glitches = new HashMap<>();
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
    }
}
