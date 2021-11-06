package circuitcomponents;

import exceptions.DuplicateInputChannelDetectedException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CircuitTest {
    private Circuit circuit;
    private LogicalInput inputX1;
    private LogicalInput inputX2;
    private Component logicalOR;
    private LogicalNOT logicalNOT;

    @Before
    public void setUp() {
        logicalOR = mock(LogicalOR.class);
        inputX1 = mock(LogicalInput.class);
        inputX2 = mock(LogicalInput.class);
        logicalNOT = mock(LogicalNOT.class);
        when(logicalNOT.calculateToggleTime()).thenReturn(1);
        when(inputX1.calculateToggleTime()).thenReturn(0);
        when(inputX2.calculateToggleTime()).thenReturn(0);
    }

    @Test
    public void calculateMaxToggleTimeWithOneLogicalComponent() {
        when(inputX1.getChannel()).thenReturn(1);
        when(inputX2.getChannel()).thenReturn(2);

        logicalOR.addInput(inputX1, inputX2);

        when(logicalOR.getInputs()).thenReturn(Arrays.asList(new Component[]{inputX1, inputX2}));

        circuit = new Circuit(logicalOR);

        when(logicalOR.calculateToggleTime()).thenAnswer(I -> Math.max(1 + inputX1.calculateToggleTime(), 1 + inputX2.calculateToggleTime()));
        assertEquals(1, circuit.calculateMaxToggleTime());
    }

    @Test
    public void calculateMaxToggleTimeWithTwoLogicalComponentsInARow() {
        when(inputX1.getChannel()).thenReturn(1);
        when(inputX2.getChannel()).thenReturn(2);

        logicalNOT.addInput(inputX1);
        logicalOR.addInput(inputX2, logicalNOT);

        when(logicalNOT.getInputs()).thenReturn(Arrays.asList(new Component[]{inputX1}));
        when(logicalOR.getInputs()).thenReturn(Arrays.asList(new Component[]{inputX2, logicalNOT}));

        circuit = new Circuit(logicalOR);

        when(logicalOR.calculateToggleTime()).thenAnswer(I -> Math.max(1 + inputX2.calculateToggleTime(), 1 + logicalNOT.calculateToggleTime()));
        assertEquals(2, circuit.calculateMaxToggleTime());
    }

    @Test(expected = DuplicateInputChannelDetectedException.class)
    public void addNotUniqueInputsToCircuit() {
        when(logicalOR.getInputs())
                .thenReturn(Arrays.asList(new Component[]{inputX1, inputX1}));

        new Circuit(logicalOR);
    }

    @Test
    public void calculateFinalState() {
        when(inputX1.getChannel()).thenReturn(1);
        when(inputX2.getChannel()).thenReturn(2);
        when(inputX1.getState()).thenReturn(false);
        when(inputX2.getState()).thenReturn(true);
        when(logicalNOT.getState()).thenReturn(true);

        logicalNOT.addInput(inputX1);

        when(logicalNOT.getInputs()).thenReturn(Arrays.asList(new Component[]{inputX1}));
        when(logicalOR.getState()).thenReturn(true);

        logicalOR.addInput(inputX2, logicalNOT);

        when(logicalOR.getInputs()).thenReturn(Arrays.asList(new Component[]{inputX2, logicalNOT}));

        circuit = new Circuit(logicalOR);

        when(logicalOR.calculateToggleTime()).thenAnswer(invocationOnMock -> Math.max(1 + inputX2.calculateToggleTime(), 1 + logicalNOT.calculateToggleTime()));
        circuit.calculateFinalState();
        assertTrue(circuit.getState());
    }

    /*
     * This method needs no specific test because it is already tested in the calculateFinalState() test
     */
    @Test
    public void calculateOneIteration() {
    }
}
