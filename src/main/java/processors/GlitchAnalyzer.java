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
        return new HashMap<Integer, List<Boolean>>();
    }
}
