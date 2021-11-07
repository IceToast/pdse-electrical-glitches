package examples;

import circuitcomponents.*;

/**
 * The example circuit 2 from the task description
 */
public class ExampleCircuit2 implements ExampleCircuit{

    public Circuit create(){
        LogicalInput inputX0 = new LogicalInput(0);
        LogicalInput inputX1 = new LogicalInput(1);
        LogicalInput inputX2 = new LogicalInput(2);
        LogicalInput inputX3 = new LogicalInput(3);

        LogicalAND a1 = new LogicalAND();
        LogicalAND a2 = new LogicalAND();
        LogicalAND a3 = new LogicalAND();
        LogicalNOT n0 = new LogicalNOT();
        LogicalNOT n1 = new LogicalNOT();
        LogicalNOT n2 = new LogicalNOT();
        LogicalNOT n3 = new LogicalNOT();
        LogicalOR o1 = new LogicalOR();

        o1.addInput(a1, a2, a3);
        a1.addInput(inputX0, n1, inputX2);
        a2.addInput(inputX0, inputX1, n3);
        a3.addInput(n0, inputX1, n2);
        n0.addInput(inputX0);
        n1.addInput(inputX1);
        n2.addInput(inputX2);
        n3.addInput(inputX3);

        return new Circuit(o1);
    }
}
