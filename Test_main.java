import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.*;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;

import static org.mockito.Mockito.*;
import org.mockito.*;

public class Test_main {
    private Receta receta;
    private Recetario recetario;
    public Test_main()
    {
    }

    @BeforeClass
    public void set() {
        System.out.println("se creo");
        recetario = new Recetario();
    }

    @BeforeEach
    public void setUp() throws Exception{
        recetario = new Recetario();
        receta = new Receta("sopa de mani", null);
    }

    @AfterEach
    public void tearDown()
    {
    }

    /*@Test 
    public void test1() throws Exception{
    Receta a = new Receta("nombre");
    Ingrediente i = new Ingrediente(4,"litros","aceite");
    a = mock(Receta.class);
    a.agregarIngrediente(i);
    when(a.agregarIngrediente(i)).thenReturn(true);
    System.out.println(a.toString());
    assertTrue(a.agregarIngrediente(i));
    }*/

    

    @Test
    public void testIngresarReceta() {
        try {
            Ingrediente i1 = new Ingrediente(1, "taza", "arroz");
            Ingrediente i2 = new Ingrediente(2, "papa");
            Ingrediente i3 = new Ingrediente(2, "libra", "mani");
            receta.agregarIngrediente(i1);
            receta.agregarIngrediente(i2);
            receta.agregarIngrediente(i3);

            receta.agregarProceso("este es el proceso");
            receta.agregarProceso("este es otro proceso");
        } catch (Exception e) {
            fail("algo esta mal");
        }
        recetario = new Recetario();
        assertTrue(recetario.registrarReceta(receta));
    }

    @Test
    public void testRecetaNulaInvalida() {
        assertFalse(recetario.registrarReceta(null));
    }

// funcione la instalacion de mock objects

    @Test 
    public void testInvalidarRecetaNula() {
        recetario = new Recetario();
        assertFalse(recetario.validarReceta(null));
    }

    @Test
    public void testInvalidaRecetaInvalida() throws Exception{
        receta = new Receta("a", null);
        receta.agregarIngrediente(new Ingrediente(1, "cebolla"));
        receta.agregarProceso("paso");
        recetario = new Recetario();
        assertFalse(recetario.validarReceta(receta));
    }

    // que hace una receta?
    // recetario-> administrar recetas
    // receta -> almacena una receta?
    @Test
    public void testValidarUnaRecetaNula() {
        assertFalse(recetario.validarReceta(null));
    }

    @Test
    public void testCrearUnaRecetaConContenido() throws Exception{
        Recetario recetario = new Recetario();
        Receta receta = new Receta("sopa de mani", null);
        Ingrediente i1 = new Ingrediente(1, "taza", "arroz");
        Ingrediente i2 = new Ingrediente(2, "papa");
        Ingrediente i3 = new Ingrediente(2, "libras", "mani");
        receta.agregarIngrediente(i1);
        receta.agregarIngrediente(i2);
        receta.agregarIngrediente(i3);
        receta.agregarProceso("este es el primer paso");
        receta.agregarProceso("este es el segundo paso");
        File f = recetario.crearReceta("SOPA_DE_MANI.txt");
        //recetario.escribirReceta(f, receta);
        assertNotEquals(0, f.length());
    }

    @Test
    public void testImpresionRecetaFormato() throws Exception{
        Receta receta1 = new Receta("sopa de mani", null);
        Receta receta2 = new Receta("sopa de mani", null);
        Ingrediente i1 = new Ingrediente(5, "papa");
        Ingrediente i2 = new Ingrediente(2, "taza", "arroz");
        String paso1 = "hervir la papa";
        String paso2 = "disfrute";
        receta1.agregarIngrediente(i1);
        receta2.agregarIngrediente(i1);
        receta1.agregarIngrediente(i2);
        receta2.agregarIngrediente(i2);
        receta1.agregarProceso(paso1);
        receta2.agregarProceso(paso1);
        receta1.agregarProceso(paso2);
        receta2.agregarProceso(paso2);
        System.out.println(receta1.toString());
        assertEquals(receta1.toString(), receta2.toString());
    }

    @Test 
    public void testVerificarArchivoVerificado() throws Exception {
        receta.agregarIngrediente(new Ingrediente(5, "manzana"));
        receta.agregarIngrediente(new Ingrediente(4, "sandia"));
        receta.agregarProceso("picar las frutas en cubos de alrededor de 1 cm de tamaño," 
            +"y siempre retirar las semillas en el caso de sandías, papayas, melones, etc.");
        receta.agregarProceso("mezcla las porciones de fruta");
        recetario = new Recetario("/home/alex/");
        recetario.registrarReceta(receta);
        File f = new File("/home/alex/Recetario/sopa_de_mani.txt");
        assertTrue(f.exists());
    }

    @Test 
    public void testValidarReceta() {
        try {
            ArrayList<Ingrediente> ings = new ArrayList<>();
            ings.add(new Ingrediente(1, "sandia"));
            ings.add(new Ingrediente(2, "papaya"));
            ings.add(new Ingrediente(1, "lt", "yogurt"));
            receta.setIngredientes(ings);
            receta.agregarProceso("este es un paso");
            receta.agregarProceso("este es otro paso");
            assertTrue(recetario.validarReceta(receta));
        } catch (Exception e) {
        } 
    }

    @Test
    public void testEscrituraRecetaArchivo() {
        try {
            recetario = new Recetario();
            receta = new Receta("Receta", "esta es una descripcion");
            receta.agregarIngrediente(new Ingrediente(4, "papa"));
            receta.agregarIngrediente(new Ingrediente(2, "kg", "pollo"));
            receta.agregarProceso("este es un proceso");
            receta.agregarProceso("este es otro proceso");
            assertTrue(recetario.registrarReceta(receta));
        } catch (Exception e) {
            fail("hubo un error");
        }
    } 

    @Test
    public void testEscrituraArhivoValidacion() {
        try {
            recetario = new Recetario();
            receta = new Receta("Receta", "esta es una descripcion");
            assertFalse(recetario.registrarReceta(receta));
        } catch (Exception e) {
            fail("hubo un error");
        }
    }
    
    @Test
    public void testEscrituraSobreArchivoExistente() throws IOException{
        String aux = "cadena cualquiera";
        File fileAux = new File("Recetario/prueba2.txt");
        fileAux.createNewFile();
        GeneradorArchivos.llenarReceta(aux,fileAux);
        assertNotEquals(0, fileAux.length());
    }
    // no esta permitido el uso de condifcionales y ciclos en 

}
