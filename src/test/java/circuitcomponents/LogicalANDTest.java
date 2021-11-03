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

    @Before
    public void setUp() {
        logicalAND = new LogicalAND();
        logicalInput1 = mock(LogicalInput.class);
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
}