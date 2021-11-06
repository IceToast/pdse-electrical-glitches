package export;

import java.util.List;
import java.util.Map;

public interface GlitchLogger {
    void log(Map<Integer, List<Boolean>> glitches);
}

