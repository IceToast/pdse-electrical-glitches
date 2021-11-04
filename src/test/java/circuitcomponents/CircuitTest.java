package circuitcomponents;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
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
}
