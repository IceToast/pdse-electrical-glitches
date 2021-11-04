import circuitcomponents.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LogicalComponentTest.class,
        LogicalANDTest.class,
        LogicalNOTTest.class,
        LogicalORTest.class,
        LogicalInputTest.class,
})
public class AllTests {
}
