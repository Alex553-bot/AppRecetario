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
    public void testIngredientesIguales() throws Exception{
        Ingrediente i1 = new Ingrediente(1, "taza", "arroz");
        Ingrediente i2 = new Ingrediente(3, "litros", "arroz");
        assertTrue(i1.equals(i2));
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
        recetario = new Recetario();
        assertFalse(recetario.registrarReceta(null));
    }

    @Test
    public void testAgregarIngredienteSimilar() throws Exception{
        Ingrediente i1 = new Ingrediente(1, "taza", "arroz");
        Ingrediente i2 = new Ingrediente(3, "taza", "arroz");
        receta.agregarIngrediente(i1);
        receta.agregarIngrediente(i2);
        assertEquals(4, i1.getCantidad());
    }
    // funcione la instalacion de mock objects
    @Test
    public void testCrearIngredienteConValorNegativo() {
        try {
            Ingrediente i = new Ingrediente(0.0, "kg", "manzana");
            fail("construyo el objeto");
        } catch (Exception e) {
            assertEquals("cantidad negativa no permitida", e.getMessage());
        }
    }

    @Test
    public void testCrearUnaArchivoReceta() {
        recetario = new Recetario();
        File f = recetario.crearReceta("Receta.txt");
        assertTrue(f!=null);
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

    //@Test 
    public void testValidarUnaRecetaValida() throws Exception {
        receta.agregarIngrediente(new Ingrediente(1, "manzana"));
        receta.agregarIngrediente(new Ingrediente(5, "platano"));
        receta.agregarIngrediente(new Ingrediente(2, "lt", "yogurt"));
        receta.agregarProceso("este es un paso");
        receta.agregarProceso("este es otro paso");
        receta.agregarProceso("este es otro, otro paso");
        assertTrue(receta.esValida());
    }

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

    //@Test
    public void testValidarUnaRecetaInvalida() {
        assertFalse(receta.esValida());
    }
    // que hace una receta?
    // recetario-> administrar recetas
    // receta -> almacena una receta?
    //@Test
    public void testValidarUnaRecetaNula() {
        //assertFalse(recetario.validarReceta(null));
    }

    @Test
    public void testCrearArchivo() {

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

    //@Test 
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

    //@Test
    public void testArchivoInexistente() throws Exception{
        File f = new File("Recetario/receta.txt");
        Buscador mock = org.mockito.Mockito.mock(Buscador.class);
        String a = "archivo";
        //when(busc.buscarReceta(a)).thenReturn(f);
        when(mock.buscarReceta(anyString())).thenReturn(null);
        //assertEquals(f, busc.buscarReceta("archivo"));
    }

    //@Test
    public void testValidarRecetaValida() {
        try {
            ArrayList<Ingrediente> ings = new ArrayList<>();
            ings.add(new Ingrediente(1, "sandia"));
            ings.add(new Ingrediente(2, "papaya"));
            ings.add(new Ingrediente(1, "lt", "yogurt"));
            receta.setIngredientes(ings);
            receta.agregarProceso("este es un paso");
            receta.agregarProceso("este es otro paso");
            assertTrue(Validador.validarReceta(receta));
        } catch (Exception e) {
            fail("hubo algun error");
        }
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
    public void testGenerarArchivo() {
        File dir = new File("Recetario/");
        File f =GeneradorArchivos.crearReceta("prueba",dir);
        assertTrue(f.exists());
    }

    @Test 
    public void testNogenerarArchivoSiNoTenemosDirectorio() {
        File dir = new File("Recetario/");
        File f =GeneradorArchivos.crearReceta("prueba",dir);
        File nuevo = GeneradorArchivos.crearReceta("prueba2",f);
        assertEquals(nuevo, null);
    }

    @Test 
    public void testEscrituraIngredientes() {
        try {
            Ingrediente i = new Ingrediente(1, "lt", "aceite");
            String esperado = "1.0 lt aceite";
            assertEquals(esperado, i.toString());
        } catch (Exception e) {}
    }

    @Test
    public void testEscrituraIngredientes2() {
        Ingrediente i = new Ingrediente(2, "sandia");
        String esperado = "2 sandias";
        assertEquals(esperado, i.toString());
    }

    @Test 
    public void testEscrituraReceta() throws Exception {
        Receta r = new Receta("titulo", null);
        r.agregarIngrediente(new Ingrediente(2, "lb", "arroz"));
        r.agregarProceso("proceso");
        String esperado = 
            "titulo\n\n"
            +"INGREDIENTES:\n"
            +"   - 2.0 lbs arroz"
            +"\nPREPARACION:\n"
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
            +"INGREDIENTES:\n"
            +"   - 2.0 lbs arroz"
            +"\nPREPARACION:\n"
            +"   1.- proceso";
        assertEquals(esperado, r.toString());
    }

    @Test
    public void testEscrituraArchivo() throws Exception {
        File file = new File("Recetario/prueba");
        receta = new Receta("prueba", "debe funcionar");
        receta.agregarIngrediente(new Ingrediente(4, "papa"));
        receta.agregarIngrediente(new Ingrediente(2, "kg", "pollo"));
        receta.agregarProceso("este es un proceso");
        receta.agregarProceso("este es otro proceso");
        GeneradorArchivos.llenarReceta(receta.toString(),file);
        assertNotEquals(0, file.length());
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

    //@Test
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
    public void testEscrituraSobreArchivoExistente() throws IOException{
        String aux = "cadena cualquiera";
        File fileAux = new File("Recetario/prueba2.txt");
        fileAux.createNewFile();
        GeneradorArchivos.llenarReceta(aux,fileAux);
        assertNotEquals(0, fileAux.length());
    }
    // no esta permitido el uso de condifcionales y ciclos en 
}
