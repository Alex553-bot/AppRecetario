package clases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.*;

public class Test_Receta {
    private Receta receta;
    @BeforeEach
    public void setUp() throws Exception {
        receta = new Receta("puddin", null);
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testInsertarIngredienteNuevo() throws Exception{
        Ingrediente ingr = new Ingrediente(1, "libra", "arroz");
        assertTrue(receta.agregarIngrediente(ingr));
    }

    @Test
    public void testMismoInsertarIngredienteRepetido() throws Exception{
        Ingrediente i1 = new Ingrediente(2,"kilos", "harina");
        receta.agregarIngrediente(i1);
        assertFalse(receta.agregarIngrediente(i1));
    }

    @Test
    public void testInsertarIngredienteNulo() throws Exception {
        Ingrediente i = null;
        assertFalse(receta.agregarIngrediente(i));
    }

    @Test
    public void testAgregarIngredienteSimilar() throws Exception{
        Ingrediente i1 = new Ingrediente(1, "taza", "arroz");
        Ingrediente i2 = new Ingrediente(3, "taza", "arroz");
        receta.agregarIngrediente(i1);
        receta.agregarIngrediente(i2);
        assertEquals(4, i1.getCantidad());
    }
    

    /// sumar ingredientes iguales
    // enfocarse mas en la busqueda !!!
    @Test
    public void testInsertarUnPaso() {
        try {
            receta.agregarProceso("este es un paso");
        } catch(Exception e) {
            fail("hubo un error al agregar el paso");
        }
    }

    @Test
    public void testInsertarPasoRepetido() {
        try {
            receta.agregarProceso("este es el paso 1");
            receta.agregarProceso("este es el paso 1");
            fail("nos da otro error");
        } catch (Exception e) {
            assertEquals("error al ingresar el paso",
                e.getMessage());
        }
    }

    @Test
    public void testInsertarPasoSimilar() {
        try {
            receta.agregarProceso("este es un paso ");
            receta.agregarProceso("EsTe es un paso");
        } catch(Exception e) {
            assertEquals("error al ingresar el paso",
                e.getMessage());
        }
    }
    @Test
    public void testVerificarIngredienteEnUnaReceta() throws Exception {
        Ingrediente i1 = new Ingrediente(3, "tzs","harina");
        Ingrediente i2 = new Ingrediente(3.1, "gr", "harina");
        receta.agregarIngrediente(i1);
        boolean respuesta = receta.verificarIngrediente(i2);
        assertFalse(respuesta);
    }

    @Test
    public void testVerificarIngredienteEnUnaReceta2() throws Exception{
        Ingrediente i = new Ingrediente(1, "kg", "pollo");
        assertTrue(receta.verificarIngrediente(i));
    }

    @Test
    public void testVerificarProcesoExistente() throws Exception {
        receta.agregarProceso("este es un paso");
        assertFalse(receta.verificarProceso("eSte es un paso"));
    }

    @Test
    public void testVerificarProcesoNuevo() throws Exception {
        receta.agregarProceso("este es un paso");
        assertTrue(receta.verificarProceso("este es otro paso"));
    }
    public void testProcesoNulo() {
        try {
            receta = new Receta("nombre", null);
            String paso = null;
            receta.agregarProceso(paso);
            fail("error agrego paso");
        } catch (Exception e) {
            assertEquals("error al ingresar el paso", e.getMessage());
        }
    }
    @Test 
    public void testEscrituraReceta() throws Exception {
        Receta r = new Receta("titulo", null);
        r.agregarIngrediente(new Ingrediente(2, "lb", "arroz"));
        r.agregarProceso("proceso");
        String esperado = 
            "titulo\n\n"
            +"INGREDIENTES\n"
            +"   - 2.0 lbs arroz"
            +"\nPREPARACION\n"
            +"   1.- proceso";
        assertEquals(esperado, r.toString());
    }

    @Test 
    public void testEscrituraRecetaDescripcion() throws Exception {
        Receta r = new Receta("titulo", "descripcion");
        r.agregarIngrediente(new Ingrediente(2, "lb", "arroz"));
        r.agregarProceso("proceso");
        String esperado = 
            "titulo\n"
            +"descripcion\n"
            +"INGREDIENTES\n"
            +"   - 2.0 lbs arroz"
            +"\nPREPARACION\n"
            +"   1.- proceso";
        assertEquals(esperado, r.toString());
    }
}