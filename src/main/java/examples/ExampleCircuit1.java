package examples;

import circuitcomponents.*;

/**
 * The example circuit 1 from the task description
 */
public class ExampleCircuit1 implements ExampleCircuit{
    public Circuit create(){
        LogicalInput inputX0 = new LogicalInput(0);
        LogicalInput inputX1 = new LogicalInput(1);
        LogicalInput inputX2 = new LogicalInput(2);

        LogicalAND a1 = new LogicalAND();
        LogicalAND a2 = new LogicalAND();
        LogicalNOT n1 = new LogicalNOT();
        LogicalOR o1 = new LogicalOR();

        o1.addInput(a1, a2);
        a1.addInput(inputX0, inputX1);
        a2.addInput(n1, inputX2);
        n1.addInput(inputX1);

        return new Circuit(o1);
    }
}
