package clases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.*;

public class Test_Ingrediente {
    @BeforeEach
    public void setUp() {}

    @AfterEach
    public void tearDown() {}
    @Test
    public void testIngredientesIguales() throws Exception{
        Ingrediente i1 = new Ingrediente(1, "taza", "arroz");
        Ingrediente i2 = new Ingrediente(3, "litros", "arroz");
        assertEquals(1,i1.compareTo(i2));
    }
    
    @Test 
    public void testIngredientesSimilares() {
        Ingrediente i1 = new Ingrediente(1, "sandia");
        Ingrediente i2 = new Ingrediente(5, "sandia");
        assertEquals(0, i1.compareTo(i2));
    }
    
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

}