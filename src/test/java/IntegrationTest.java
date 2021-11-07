import circuitcomponents.Circuit;
import examples.ExampleCircuit1;
import examples.ExampleCircuit2;
import examples.ExampleCircuit3;
import examples.ExampleCircuit4;
import exceptions.MissingCircuitException;
import exceptions.MissingInputException;
import export.ConsoleGlitchLogger;
import export.GlitchLogger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import processors.GlitchAnalyzer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Testing the real circuits. In this part we dont need any mocked data.
 * If the circuit creation is changed the output should never differ.
 */
public class IntegrationTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testExampleCircuit1() {
        Circuit circuit = new ExampleCircuit1().create();
        GlitchAnalyzer analyzer = new GlitchAnalyzer(circuit);
        GlitchLogger logger = new ConsoleGlitchLogger();
        logger.log(analyzer.analyzeForGlitches());
        assertTrue(outContent.toString().contains("Glitch bei Starteinstellung 111 und Änderung an x1"));
    }

    @Test
    public void testExampleCircuit2(){
        Circuit circuit = new ExampleCircuit2().create();
        GlitchAnalyzer analyzer = new GlitchAnalyzer(circuit);
        GlitchLogger logger = new ConsoleGlitchLogger();
        logger.log(analyzer.analyzeForGlitches());
        assertTrue(outContent.toString().contains("Glitch bei Starteinstellung 1100 und Änderung an x0"));
        assertTrue(outContent.toString().contains("Glitch bei Starteinstellung 1110 und Änderung an x1"));
    }

    @Test
    public void testExampleCircuit3(){
        Circuit circuit = new ExampleCircuit3().create();
        GlitchAnalyzer analyzer = new GlitchAnalyzer(circuit);
        GlitchLogger logger = new ConsoleGlitchLogger();
        logger.log(analyzer.analyzeForGlitches());
        assertTrue(outContent.toString().contains("Es wurden keine Glitches in dieser Schaltung gefunden."));
    }

    @Test(expected = MissingCircuitException.class)
    public void testExampleCircuitIsNull(){
        GlitchAnalyzer analyzer = new GlitchAnalyzer(null);
        GlitchLogger logger = new ConsoleGlitchLogger();
        assertThrows(MissingCircuitException.class, () -> logger.log(analyzer.analyzeForGlitches()));
    }

    @Test(expected = MissingInputException.class)
    public void testExampleCircuit4(){
        Circuit circuit = new ExampleCircuit4().create();
        GlitchAnalyzer analyzer = new GlitchAnalyzer(circuit);
        GlitchLogger logger = new ConsoleGlitchLogger();
        assertThrows(MissingInputException.class, () -> logger.log(analyzer.analyzeForGlitches()));
    }
}
