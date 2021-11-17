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
    public void testInsertarIngredienteNulo() {
        Ingrediente i = null;
        Recetar receta = new Receta("sopa");
        assertFalse(rece.agregarIngrediente(i));
    }

    @Test
    public void testIngredientesIguales() {
        Ingrediente i1 = new Ingrediente(1,"arroz", "taza");
        Ingrediente i2 = new Ingrediente(3, "arroz", "taza");
        assertTrue(i1.equals(i2));
    }

    @Test
    public void testInsertarProceso() {
        try {
            Receta receta = new Receta("h");
            receta.agregarProceso(5, "este es el paso 5");
            fail("nos da otro error");
        } catch (Exception e) {
            assertEquals("error al ingresar el proceso",
                e.getMessageError());
        }
    }

    @Test
    public void testIngresarReceta() {
        Recetario admin = new Recetario();
        Receta receta = new Receta("sopa de mani");
        Ingrediente i1 = new Ingrediente(1, "arroz", "taza");
        Ingrediente i2 = new Ingrediente(2, "papa", "unidad");
        Ingrediente i3 = new Ingrediente(2, "mani", "libras");
        receta.agregarIngrediente(i1);
        receta.agregarIngrediente(i2);
        receta.agregarIngrediente(i3);
        receta.agregarProceso(1, "este es el proceso");
        assertTrue(admin.registrarReceta(receta));
    }

    @Test
    public void testRecetaInvalida() {
        Recetario admin = new Recetario();
        Receta receta = new Receta("sopa");
        assertFalse(admin.registrarReceta(receta));
    }
}