package examples;

import circuitcomponents.Circuit;
import circuitcomponents.LogicalAND;
import circuitcomponents.LogicalInput;
import circuitcomponents.LogicalOR;

/**
 * Based on the example circuit 1 from the task description but without the NOT Gate
 * This leads to a circuit without glitches
 */
public class ExampleCircuit3 {
    public Circuit create(){
        LogicalInput inputX0 = new LogicalInput(0);
        LogicalInput inputX1 = new LogicalInput(1);
        LogicalInput inputX2 = new LogicalInput(2);

        LogicalAND a1 = new LogicalAND();
        LogicalAND a2 = new LogicalAND();
        LogicalOR o1 = new LogicalOR();

        o1.addInput(a1, a2);
        a1.addInput(inputX0, inputX1);
        a2.addInput(inputX1, inputX2);

        return new Circuit(o1);
    }
}
