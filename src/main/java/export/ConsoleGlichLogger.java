package export;

import java.util.List;
import java.util.Map;

public class ConsoleGlichLogger implements GlitchLogger {
    public void log(Map<Integer, List<Boolean>> glitches) {
        boolean glitchFound = false;
        System.out.println("Simulation gestartet.");
        for (Map.Entry<Integer, List<Boolean>> pair : glitches.entrySet()) {
            List<Boolean> glitchesForInputSet = pair.getValue();
            int numberOfInputs = glitchesForInputSet.size();

            for (int i = 0; i < numberOfInputs; i++) {
                if (glitchesForInputSet.get(i)) {
                    glitchFound = true;
                    String state = padStringRightWithZeros(Integer.toBinaryString(pair.getKey()), numberOfInputs);
                    System.out.printf("Glitch bei Starteinstellung %s und Änderung an x%d %n", state, i);
                }
            }
        }
        if (!glitchFound){
            System.out.println("Es wurden keine Glitches in dieser Schaltung gefunden.");
        }
        System.out.println("Simulation beendet.");
    }

    private String padStringRightWithZeros(String s, int n) {
        return String.format("%-" + n + "s", s).replace(" ", "0");
    }
}
