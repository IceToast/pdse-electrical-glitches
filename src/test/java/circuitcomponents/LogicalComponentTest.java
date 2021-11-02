package circuitcomponents;

import exceptions.MissingInputException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class LogicalComponentTest {

    private LogicalComponent logicalComponentDefault1;
    private Component logicalInput1;
    private Component logicalInput2;

    @Before
    public void setUp() {
        logicalComponentDefault1 = new ImplLogicalComponent();
        logicalInput1 = mock(LogicalInput.class);
        logicalInput2 = mock(LogicalInput.class);
    }


    @Test(expected = MissingInputException.class)
    public void addInputWithoutArgument() {
        logicalComponentDefault1.addInput();

        assertThrows(MissingInputException.class, () -> logicalComponentDefault1.addInput());
    }

    @Test
    public void addWithOneInput() {
        logicalComponentDefault1.addInput(logicalInput1);

        assertEquals(1, logicalComponentDefault1.getInputs().size());
        logicalComponentDefault1.getInputs().forEach(component -> assertTrue(component instanceof LogicalInput));
    }

    @Test
    public void addWithTwoInputs() {
        logicalComponentDefault1.addInput(logicalInput1);
        logicalComponentDefault1.addInput(logicalInput2);

        assertEquals(2, logicalComponentDefault1.getInputs().size());
        logicalComponentDefault1.getInputs().forEach(component -> assertTrue(component instanceof LogicalInput));
    }


    class ImplLogicalComponent extends LogicalComponent {

        @Override
        public void calculateState() throws MissingInputException {
            // do nothing
        }
    }

}