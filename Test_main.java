import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_main {
    public Test_main()
    {
    }

    @BeforeEach
    public void setUp()
    {
    }

    @AfterEach
    public void tearDown()
    {
    }
    @Test
    public void testInsertarIngredienteNuevo() {
        Receta rece = new Receta("puddin");
        Ingrediente ingr = new Ingrediente(1, "arroz", "libra");
        assertTrue(rece.agregarIngrediente(ingr));
    }
    @Test
    public void testMismoInsertarIngredienteRepetido() {
        Receta rece = new Receta("puddin");
        Ingrediente i1 = new Ingrediente(2,"harina","kilos");
        rece.agregarIngrediente(i1);
        rece.agregarIngrediente(i1);
        assertFalse(rece.agregarIngrediente(i1));
    }
    @Test
    public void testAlgo() {

        assertTrue(true);
    }
}
