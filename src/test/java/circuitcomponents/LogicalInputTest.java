package circuitcomponents;

import exceptions.NoInputsAllowedForInputException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;

public class LogicalInputTest {

    private LogicalInput logicalInput1;
    private Component logicalInput2;

    @Before
    public void setUp() {
        logicalInput1 = new LogicalInput(1);
        logicalInput2 = mock(LogicalInput.class);
    }

    @Test(expected = NoInputsAllowedForInputException.class)
    public void addInput() {
        logicalInput1.addInput(logicalInput2);

        assertThrows(NoInputsAllowedForInputException.class, () -> logicalInput1.addInput(logicalInput2));
    }
}