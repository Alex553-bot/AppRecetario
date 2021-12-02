import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.*;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.PriorityQueue;

import static org.mockito.Mockito.*;
import org.mockito.*;

public class Test_main {
    private Receta receta;
    private Recetario recetario;
    private Buscador buscador;
    public Test_main()
    {
    }

    @BeforeClass
    public void set() {
    }

    @BeforeEach
    public void setUp() throws Exception{
        recetario = new Recetario();
        receta = new Receta("sopa de mani", null);
        buscador = new Buscador(recetario.getDir().getPath());
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
        File f = GeneradorArchivos.crearReceta("SOPA_DE_MANI.txt", recetario.getDir());
        recetario.escribirReceta(f, receta);
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
        File fileAux = new File("archivos/recetas/prueba2.txt");
        fileAux.createNewFile();
        GeneradorArchivos.llenarReceta(aux,fileAux);
        assertNotEquals(0, fileAux.length());
    }
    // no esta permitido el uso de condifcionales y ciclos en 
    @Test
    public void contarOcurrenciasIngredientesReceta() throws IOException{
        File receta;
        receta = new File(new File("").getAbsolutePath() + "/archivos/recetas/salchipapa.txt");
        String[] ingredientes = {"lechuga", "arroz", "leche"};

        assertEquals(0, buscador.contarIngredientesContenidos(receta, ingredientes));
    }

    @Test
    public void contarOcurrenciasIngredientesReceta2() throws IOException{
        File receta;
        receta = new File(new File("").getAbsolutePath() + "/archivos/recetas/pique.txt");
        String[] ingredientes = {"papa", "arroz", "aceite", "tomate", "lechuga"};

        assertEquals(3, buscador.contarIngredientesContenidos(receta, ingredientes));
    }

    @Test
    public void buscar_archivo_de_receta_por_nombre() throws Exception{
        String nombre = "Salchipapa";
        File r = new File("salchipapa.txt");
        assertEquals(r.getName(), buscador.buscarReceta(nombre).getName());
    }

    @Test
    public void buscarPique() throws Exception {
        String nombre = "Pique";
        assertEquals("archivos/recetas/pique.txt", buscador.buscarReceta(nombre).getPath());
    }

    @Test
    public void buscar_archivo_de_receta_por_nombre_null() throws Exception{
        String nombre = "Salchipaa";

        assertEquals(null, buscador.buscarReceta(nombre));
    }

    @Test
    public void buscar_receta_por_ingredientes() throws Exception{
        String[] ingredientes = {"papa", "ChoRizó", "pLatáNo", "tomaté"};
        assertEquals("salchipapa.txt", buscador.buscarRecetaPorIngredientes(ingredientes).getName());
    }

    @Test
    public void buscarSopaPorIngredientes() throws Exception{
        String[] ingredientes = {"papa", "Arróz", "cebolLA", "tomaté", "agua", "POLLO"};
        assertEquals("sopa.txt", buscador.buscarRecetaPorIngredientes(ingredientes).getName());
    }

    @Test
    public void buscar_receta_por_ingredientes_null() throws Exception{
        String[] ingredientes = {"papa", "Arróz", "cebolLA", "tomaté", "agua", "POLLO", "carne"};
        assertEquals(null, buscador.buscarRecetaPorIngredientes(ingredientes));
    }

    //@Test
    //public void buscarRecetasPorIngredientesSinArchivos(){}

    @Test
    public void buscarRecetasPorIngredientes() throws Exception{
        String[] ingredientes = {"papa", "Arróz", "cebolLA", "tomaté"};
        PriorityQueue<PriorityRecipe> recetas = buscador
                .buscarRecetasPorIngredientes(ingredientes);
        assertEquals(7, recetas.size());
    }

    @Test
    public void testPrioridad() throws Exception{
        String[] ingredientes = {"papa", "Arróz", "cebolLA", "tomaté"};
        PriorityQueue<PriorityRecipe> recetas = buscador.buscarRecetasPorIngredientes(ingredientes);
        assertEquals("sopa.txt", recetas.peek().getFile().getName());
    }

    //23.11.2021
    @Test
    public void verificarRecetaSinIngrediente() {
        String[] ingrediente = {"Chocolate"};
        assertEquals(11, buscador.buscarRecetaSinIngredientes(ingrediente).size());
    }

    @Test
    public void recetasSinIngredientes() {
        String[] ingredientes = {"Chocolate", "pollo"};
        assertEquals(7, buscador.buscarRecetaSinIngredientes(ingredientes).size());
    }

    @Test
    public void tortaSinChocolate() throws Exception{
        String cad = "Torta sin chocolate";
        assertEquals(3, buscador.recetasSinIngredienteEspecifico(cad).size());
    }

    @Test
    public void tortaSinHuevo() throws Exception{
        String cad = "Torta sin huevo";
        assertEquals(1, buscador.recetasSinIngredienteEspecifico(cad).size());
    }

    //24.11.2021
    @Test
    public void recetasSinIngredienteEspecifico() throws Exception{
        String cad = "Torta sin chocolate amargo";
        assertEquals(5, buscador.recetasSinIngredienteEspecifico(cad).size());
    }

    @Test
    public void recetasSinIngredienteEspecificoNameLongSmall() {
        try{
            String cad = "sin chocolate";
            assertEquals(1, buscador.recetasSinIngredienteEspecifico(cad).size());

            cad = "sopa sin cebolla de jamaica";
            assertEquals(1, buscador.recetasSinIngredienteEspecifico(cad).size());

            fail("Error...");
        }catch(Exception e) {

        }
    }

    @Test
    public void ingredientesVacios() {
        try{
            String[] ingredientes = {"", ""};
            buscador.buscarRecetasPorIngredientes(ingredientes);
            fail("Ingresientes Vacios...!");
        }catch (Exception e) {

        }
    }

    @Test
    public void testRecetasConIngredienteEspecificos() throws Exception{
        String cad = "Torta con chocolate";
        assertEquals(2, buscador.recetasConIngredienteEspecifico(cad).size());
    }

    @Test
    public void tortaConHarina() throws Exception{
        String cad = "Torta con harina";
        assertEquals(5, buscador.recetasConIngredienteEspecifico(cad).size());
    }

    @Test
    public void testSugerenciasPorReceta() throws Exception {
        String receta = "pastel de limón";
        assertEquals(3, buscador.sugerirRecetas(receta).size());
    }

    @Test
    public void testSugerirSopas() throws Exception {
        String receta = "sopa de pollo";
        //ya no se esper 4 si no 3, razon -> la sugerencia no debe mostrar la receta exacta
        assertEquals(3, buscador.sugerirRecetas(receta).size());
    }

    //25.11.2021
    @Test
    public void buscarRecetaNombreVacio() {
        try{
            String nombre = "";
            buscador.buscarReceta(nombre);
            fail("Nombre Vacio...!");
        }catch (Exception e) {

        }
    }

    @Test
    public void sugerirRecetasEnPlural() throws Exception{
        assertEquals(5, buscador.sugerirRecetas("tortas").size());
    }

    @Test
    public void sugerirRecetasNombreEspecifico() throws Exception{
        assertEquals(5/*3*/, buscador.sugerirRecetas("Sopa de Pollo").size());
    }

}
