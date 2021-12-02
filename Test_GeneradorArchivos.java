import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.*;

import java.io.File;

import static org.mockito.Mockito.*;
import org.mockito.*;

public class Test_GeneradorArchivos {
    private Receta receta;
    @BeforeEach
    public void setUp() throws Exception{
        receta = new Receta("puddin", null);
    }
    
    @AfterEach
    public void tearDown() {}

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
    public void testArchivoInexistente() throws RuntimeException {
        File f = new File("Recetario/receta.txt");
        Buscador mock = org.mockito.Mockito.mock(Buscador.class);
        String a = "archivo";
        //when(busc.buscarReceta(a)).thenReturn(f);
        when(mock.buscarReceta(anyString())).thenReturn(null);
        assertNull(mock.buscarReceta("archivo"));
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
}