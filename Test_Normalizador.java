

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class Test_Normalizador.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Test_Normalizador
{
    /**
     * Default constructor for test class Test_Normalizador
     */
    public Test_Normalizador()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void testConversionCadenaCaracteresEspeciales() {
        String dirtyString = "AèìòÚ";
        String cleanString = Normalizador.standardize(dirtyString);
        assertEquals("aeiou", cleanString);
    }

    @Test 
    public void testConversionCadenaMinusculas() {
        String cadenaMala = "EstA es \t una CADenA sin  foRMato";
        String nuevaCad = Normalizador.standardize(cadenaMala);
        nuevaCad = Normalizador.removeBlankSpaces(nuevaCad);
        assertEquals("esta es una cadena sin formato", nuevaCad);
    }
    
    @Test
    public void testVoidArguments() {
        String arg = null;
        try {
            Normalizador.emptyNullWord(arg);
        } catch (Exception e) {}
    }
    
    // matched test
}
