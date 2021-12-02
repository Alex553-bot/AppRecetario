package tools;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;
import org.mockito.*;

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
    public void clearStringWord() {
        String dirtyString = "Holá MÜndó";
        String cleanString = Normalizador.standardize(dirtyString);
        
        assertEquals("hola mundo", cleanString);
    }
    
    @Test
    public void testEmptyNull() {
        try{
            assertTrue(Normalizador.emptyNullWord(""));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testEmptyNull2() {
        try{
            assertFalse(Normalizador.emptyNullWord("Torta de queso"));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /*@Test
    public void testParametrosIntercambiados() {
        try{
            Receta receta = mock(Receta.class);
            when(receta.getTitulo()).thenReturn("123");
            Normalizador.matched(receta.getTitulo());
            fail("Voids are not accepted");
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }*/
    
    @Test
    public void testDeleteVoidArray() throws Exception{
        String[] ingredientes = {"sal", "", "leche", "arroz", "", ""};
        assertEquals(3, Normalizador.deleteVoid(ingredientes).length);
    }
    
    @Test
    public void testDeleteVoidMany() throws Exception{
        String[] ingredientes = {"sal", "harina", "leche", "arroz", "", ""};
        assertEquals(4, Normalizador.deleteVoid(ingredientes).length);
    }
    
    @Test
    public void testDeleteVoidAll() {
        try{
            String[] ingredientes = {"", "", ""};
            assertEquals(4, Normalizador.deleteVoid(ingredientes).length);
            fail("Array is empty (>_<)");
        }catch (Exception e) {
            
        }
    }

    @Test
    public void testDeletePlural() {
        assertEquals("torta", Normalizador.deletePlural("tortas"));
    }
    
    @Test
    public void testDeletePluralVinos() {
        assertEquals("vino", Normalizador.deletePlural("vinos"));
    }
    
    @Test
    public void testDeletePluralPasteles() {
        assertEquals("pastel", Normalizador.deletePlural("pasteles"));
    }
    
    @Test
    public void testBorrarEspaciosEntrePalabras() throws Exception{
        assertEquals("tortadechocolate", Normalizador.deleteSpaces(" Torta de Chocolate"));
    }
    
    @Test
    public void testBorrarEspacios() throws Exception{
        assertEquals("pasteldelimon", Normalizador.deleteSpaces("Pastel de Limon"));
    }
}
