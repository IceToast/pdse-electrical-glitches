package circuitcomponents;

import exceptions.MissingInputException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogicalORTest {
    private LogicalOR logicalOR;
    private Component logicalInput1;
    private Component logicalInput2;

    @Before
    public void setUp() {
        logicalOR = new LogicalOR();
        logicalInput1 = mock(LogicalInput.class);
        logicalInput2 = mock(LogicalInput.class);
    }

    @Test
    public void calculateStateWithOneInputF() {
        when(logicalInput1.getState()).thenReturn(false);
        logicalOR.addInput(logicalInput1);

        logicalOR.calculateState();

        assertFalse(logicalOR.getState());
    }

    @Test
    public void calculateStateWithOneInputT() {
        when(logicalInput1.getState()).thenReturn(true);
        logicalOR.addInput(logicalInput1);

        logicalOR.calculateState();

        assertTrue(logicalOR.getState());
    }

    @Test
    public void calculateStateWithTwoInputsFF() {
        when(logicalInput1.getState()).thenReturn(false);
        when(logicalInput2.getState()).thenReturn(false);
        logicalOR.addInput(logicalInput1);
        logicalOR.addInput(logicalInput2);

        logicalOR.calculateState();

        assertFalse(logicalOR.getState());
    }

    @Test
    public void calculateStateWithTwoInputsFT() {
        when(logicalInput1.getState()).thenReturn(false);
        when(logicalInput2.getState()).thenReturn(true);
        logicalOR.addInput(logicalInput1);
        logicalOR.addInput(logicalInput2);

        logicalOR.calculateState();

        assertTrue(logicalOR.getState());
    }

    @Test
    public void calculateStateWithTwoInputsTT() {
        when(logicalInput1.getState()).thenReturn(true);
        when(logicalInput2.getState()).thenReturn(true);
        logicalOR.addInput(logicalInput1);
        logicalOR.addInput(logicalInput2);

        logicalOR.calculateState();

        assertTrue(logicalOR.getState());
    }

    @Test(expected = MissingInputException.class)
    public void calculateStateWithNoInput() {
        logicalOR.calculateState();

        assertThrows(MissingInputException.class, () -> logicalOR.calculateState());
    }
}