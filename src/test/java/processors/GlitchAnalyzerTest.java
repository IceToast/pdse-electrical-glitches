package processors;

import circuitcomponents.Circuit;
import circuitcomponents.LogicalInput;
import exceptions.MissingCircuitException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlitchAnalyzerTest {

    private GlitchAnalyzer glitchAnalyzer;
    private LogicalInput inputX0;
    private LogicalInput inputX1;
    private LogicalInput inputX2;
    private Circuit circuit;

    @Before
    public void setUp() {
        circuit = mock(Circuit.class);
        inputX0 = mock(LogicalInput.class);
        inputX1 = mock(LogicalInput.class);
        inputX2 = mock(LogicalInput.class);
    }

    @Test
    public void analyzeForGlitchesWithOneGlitch() {
        List<LogicalInput> inputs = new ArrayList<>();
        inputs.add(inputX0);
        inputs.add(inputX1);
        inputs.add(inputX2);

        when(circuit.getInputs()).thenReturn(inputs);
        when(circuit.getState()).thenReturn(false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, true, true, true, false,
                false, false, false, false, false, false, false, false, true, true, true, false,
                false, false, true, true, true, false, false, false, true, true, true, false,
                false, false, false, false, false, false, false, false, false, false, false, true,
                true, true, false, false, false, true, true, true, false, false, false, true,
                true, true, true, true, true, false, true, true, true, true, true, true,
                true, true, true, false, false, false, true, true, false, false, false, true,
                true, true, true, true, true, true, true, true, true, true, true, false,
                true, true, false, false, false, true, false, false, true, true, true, false,
                false, false, false, true, true, true, false, false, false, false, false, true,
                true, true, false, false, false, true, true, true, false, true, true, true, true, true, true, true);
        when(circuit.calculateMaxToggleTime()).thenReturn(3);

        glitchAnalyzer = new GlitchAnalyzer(circuit);

        Map<Integer, List<Boolean>> expectedGlitches = new HashMap<>();
        List<Boolean> lFalseFalseFalse = Arrays.asList(false, false, false);
        List<Boolean> lFalseTrueFalse = Arrays.asList(false, false, false);

        expectedGlitches.put(0, lFalseFalseFalse);
        expectedGlitches.put(1, lFalseFalseFalse);
        expectedGlitches.put(2, lFalseFalseFalse);
        expectedGlitches.put(3, lFalseFalseFalse);
        expectedGlitches.put(4, lFalseFalseFalse);
        expectedGlitches.put(5, lFalseFalseFalse);
        expectedGlitches.put(6, lFalseFalseFalse);
        expectedGlitches.put(7, lFalseTrueFalse);

        assertEquals(expectedGlitches.size(), glitchAnalyzer.analyzeForGlitches().size());
        assertTrue(glitchAnalyzer.analyzeForGlitches().entrySet().stream().allMatch(e -> e.getValue().equals(expectedGlitches.get(e.getKey()))));
    }

    @Test
    public void analyzeForGlitchesWithZeroGlitch() {
        List<LogicalInput> inputs = new ArrayList<>();
        inputs.add(inputX0);
        inputs.add(inputX1);
        inputs.add(inputX2);

        when(circuit.getInputs()).thenReturn(inputs);
        when(circuit.getState()).thenReturn(false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false,
                false, false, false, true, true, false, false, false,
                false, false, false, true, true, false, false, false,
                false, false, false, true, true, true, true, false,
                false, true, true, false, false, true, true, true,
                true, false, false, false, false, false, false, true,
                true, false, false, false, false, false, false, false,
                false, false, false, true, true, false, false, false,
                false, true, true, true, true, true, true, false,
                false, true, true, false, false, true, true, true,
                true, true, true, false, false, true, true, true);
        when(circuit.calculateMaxToggleTime()).thenReturn(2);

        glitchAnalyzer = new GlitchAnalyzer(circuit);

        Map<Integer, List<Boolean>> expectedGlitches = new HashMap<>();
        List<Boolean> lFalseFalseFalse = Arrays.asList(false, false, false);

        expectedGlitches.put(0, lFalseFalseFalse);
        expectedGlitches.put(1, lFalseFalseFalse);
        expectedGlitches.put(2, lFalseFalseFalse);
        expectedGlitches.put(3, lFalseFalseFalse);
        expectedGlitches.put(4, lFalseFalseFalse);
        expectedGlitches.put(5, lFalseFalseFalse);
        expectedGlitches.put(6, lFalseFalseFalse);
        expectedGlitches.put(7, lFalseFalseFalse);

        assertEquals(expectedGlitches.size(), glitchAnalyzer.analyzeForGlitches().size());
        assertTrue(glitchAnalyzer.analyzeForGlitches().entrySet().stream().allMatch(e -> e.getValue().equals(expectedGlitches.get(e.getKey()))));
    }

    @Test(expected = MissingCircuitException.class)
    public void testCircuitIsNull(){
        glitchAnalyzer = new GlitchAnalyzer(null);
        assertThrows(MissingCircuitException.class, () -> glitchAnalyzer.analyzeForGlitches());
    }
}