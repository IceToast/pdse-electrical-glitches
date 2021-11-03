package circuitcomponents;

import exceptions.MissingInputException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class LogicalANDTest {
    private LogicalAND logicalAND;
    private Component logicalInput1;
    private Component logicalInput2;

    @Before
    public void setUp() {
        logicalAND = new LogicalAND();
        logicalInput1 = mock(LogicalInput.class);
        logicalInput2 = mock(LogicalInput.class);
    }

    @Test(expected = MissingInputException.class)
    public void calculateStateWithNoInput() {
        logicalAND.calculateState();

        assertThrows(MissingInputException.class, () -> logicalAND.calculateState());
    }

    @Test
    public void calculateStateWithOneInputF() {
        when(logicalInput1.getState()).thenReturn(false);
        logicalAND.addInput(logicalInput1);

        logicalAND.calculateState();

        assertFalse(logicalAND.getState());
    }

    @Test
    public void calculateStateWithOneInputT() {
        when(logicalInput1.getState()).thenReturn(true);
        logicalAND.addInput(logicalInput1);

        logicalAND.calculateState();

        assertTrue(logicalAND.getState());
    }

    @Test
    public void calculateStateWithTwoInputsFF() {
        when(logicalInput1.getState()).thenReturn(false);
        when(logicalInput2.getState()).thenReturn(false);
        logicalAND.addInput(logicalInput1);
        logicalAND.addInput(logicalInput2);

        logicalAND.calculateState();

        assertFalse(logicalAND.getState());
    }

    @Test
    public void calculateStateWithTwoInputsFT() {
        when(logicalInput1.getState()).thenReturn(false);
        when(logicalInput2.getState()).thenReturn(true);
        logicalAND.addInput(logicalInput1);
        logicalAND.addInput(logicalInput2);

        logicalAND.calculateState();

        assertFalse(logicalAND.getState());
    }

    @Test
    public void calculateStateWithTwoInputsTT() {
        when(logicalInput1.getState()).thenReturn(true);
        when(logicalInput2.getState()).thenReturn(true);
        logicalAND.addInput(logicalInput1);
        logicalAND.addInput(logicalInput2);

        logicalAND.calculateState();

        assertTrue(logicalAND.getState());
    }
}