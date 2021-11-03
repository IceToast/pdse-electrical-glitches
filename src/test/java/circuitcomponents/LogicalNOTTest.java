package circuitcomponents;

import exceptions.MissingInputException;
import exceptions.TooManyInputsException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogicalNOTTest {

    private LogicalNOT logicalNOT;
    private Component logicalInput1;
    private Component logicalInput2;

    @Before
    public void setUp(){
        logicalNOT = new LogicalNOT();
        logicalInput1 = mock(LogicalInput.class);
        logicalInput2 = mock(LogicalInput.class);
    }

    @Test(expected = MissingInputException.class)
    public void calculateStateWithNoInput() {
        logicalNOT.calculateState();

        assertThrows(MissingInputException.class, () -> logicalNOT.calculateState());
    }

    @Test
    public void calculateStateWithOneInputF() {
        when(logicalInput1.getState()).thenReturn(false);
        logicalNOT.addInput(logicalInput1);

        logicalNOT.calculateState();

        assertTrue(logicalNOT.getState());
    }

    @Test
    public void calculateStateWithOneInputT() {
        when(logicalInput1.getState()).thenReturn(true);
        logicalNOT.addInput(logicalInput1);

        logicalNOT.calculateState();

        assertFalse(logicalNOT.getState());
    }

    @Test(expected = MissingInputException.class)
    public void addZeroInputs() {
        logicalNOT.addInput();

        assertThrows(MissingInputException.class, () -> logicalNOT.addInput());
    }

    @Test
    public void addOneInputs() {
        logicalNOT.addInput(logicalInput1);

        assertEquals(1,logicalNOT.getInputs().size());
        assertTrue(logicalNOT.getInputs().get(0) instanceof LogicalInput);
    }

    @Test(expected = TooManyInputsException.class)
    public void addTwoInputs() {
        logicalNOT.addInput(logicalInput1, logicalInput2);

        assertThrows(TooManyInputsException.class, () -> logicalNOT.addInput(logicalInput1, logicalInput2));
    }

    @Test(expected = TooManyInputsException.class)
    public void addTwoTimesInputs() {
        logicalNOT.addInput(logicalInput1);
        logicalNOT.addInput(logicalInput2);

        assertThrows(TooManyInputsException.class, () -> logicalNOT.addInput(logicalInput2));
    }
}
