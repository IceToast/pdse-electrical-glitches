package processors;

import circuitcomponents.Circuit;
import circuitcomponents.LogicalInput;

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

        return new HashMap<Integer, List<Boolean>>();
    }

    private List<Boolean> analyzeGlitchesPerInputSet(int i) {
        return null;
    }
}
