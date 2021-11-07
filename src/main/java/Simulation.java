import circuitcomponents.Circuit;
import examples.ExampleCircuit1;
import examples.ExampleCircuit2;
import examples.ExampleCircuit3;
import export.ConsoleGlitchLogger;
import export.GlitchLogger;
import processors.GlitchAnalyzer;

public class Simulation {
    public static void main(String[] args) {
        Circuit e1 = new ExampleCircuit1().create();
        Circuit e2 = new ExampleCircuit2().create();
        Circuit e3 = new ExampleCircuit3().create();
        GlitchAnalyzer analyzer = new GlitchAnalyzer(e1);
        GlitchLogger logger = new ConsoleGlitchLogger();
        System.out.println("Simulation Schaltung 1");
        logger.log(analyzer.analyzeForGlitches());
        analyzer = new GlitchAnalyzer(e2);
        System.out.println("Simulation Schaltung 2");
        logger.log(analyzer.analyzeForGlitches());
        analyzer = new GlitchAnalyzer(e3);
        System.out.println("Simulation Schaltung 3");
        logger.log(analyzer.analyzeForGlitches());
    }
}