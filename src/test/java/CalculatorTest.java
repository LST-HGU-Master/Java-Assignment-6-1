import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class CalculatorTest {
    @Test
    public void testTime()
    {
        // assertion
        assertEquals(2, Calculator.time(1 , 2));
        assertEquals(36, Calculator.time(4 , 9));
    }

    @Test
    public void testDivide()
    {
        // assertion
        assertEquals(0.5, Calculator.divide(1 , 2), 0.0001f);
        assertEquals(5.0, Calculator.divide(15 , 3), 0.0001f);
    }

    @Test
    public void testDivideZero()
    {
        // assertion
        assertEquals(0, Calculator.divide(1 , 0), 0.0001f);
        assertEquals(0, Calculator.divide(26 , 0), 0.0001f);
    }
}
