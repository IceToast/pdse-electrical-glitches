package circuitcomponents;

import exceptions.MissingInputException;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class LogicalAndTest{
    private LogicalAND logicalAND;

    public void setUp() {
        logicalAND = new LogicalAND();
    }

    @Test(expected = MissingInputException.class)
    public void calculateStateWithNoInput() {
        logicalAND.calculateState();

        assertThrows(MissingInputException.class, () -> logicalAND.calculateState());
    }
}