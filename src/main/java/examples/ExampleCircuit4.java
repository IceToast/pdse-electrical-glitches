package examples;

import circuitcomponents.Circuit;
import circuitcomponents.LogicalInput;
import circuitcomponents.LogicalNOT;

/**
 * A simple circuit with a topComponent without inputs
 */
public class ExampleCircuit4 implements ExampleCircuit{
    @Override
    public Circuit create() {
        LogicalInput inputX0 = new LogicalInput(0);
        LogicalInput inputX1 = new LogicalInput(1);
        LogicalInput inputX2 = new LogicalInput(2);
        LogicalNOT n1 = new LogicalNOT();
        return new Circuit(n1);
    }
}
