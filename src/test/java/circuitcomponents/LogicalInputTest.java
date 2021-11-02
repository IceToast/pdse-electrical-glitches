package circuitcomponents;

import exceptions.NoInputsAllowedForInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;

public class LogicalInputTest {


    private LogicalInput logicalInput1;
    private Component logicalInput2;

    @Before
    public void setUp() {
        logicalInput1 = new LogicalInput();
        logicalInput2 = mock(LogicalInput.class);
    }

    @Test(expected = NoInputsAllowedForInput.class)
    public void addInput() {
        logicalInput1.addInput(logicalInput2);

        assertThrows(NoInputsAllowedForInput.class, () -> logicalInput1.addInput(logicalInput2));
    }
}