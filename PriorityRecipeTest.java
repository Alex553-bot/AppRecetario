import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PriorityRecipeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PriorityRecipeTest {
    @Test
    public void testCompareToMayor() {
        PriorityRecipe receta1 = new PriorityRecipe(2, null);
        PriorityRecipe receta2 = new PriorityRecipe(5, null);
        
        assertEquals(1, receta2.compareTo(receta1));
    }
    
    @Test
    public void testCompareToMenor() {
        PriorityRecipe receta1 = new PriorityRecipe(2, null);
        PriorityRecipe receta2 = new PriorityRecipe(5, null);
        
        assertEquals(-1, receta1.compareTo(receta2));
    }
    
    @Test
    public void testCompareToIgual() {
        PriorityRecipe receta1 = new PriorityRecipe(2, null);
        PriorityRecipe receta2 = new PriorityRecipe(2, null);
        
        assertEquals(0, receta1.compareTo(receta2));
    }
}
